package com.angrybytes.jenkinsplugins.buildeverythingstrategy;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.Cause;
import hudson.model.CauseAction;
import hudson.model.Job;
import hudson.model.Queue;
import hudson.model.Run;

import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

import jenkins.branch.BranchEventCause;
import jenkins.branch.BranchIndexingCause;
import jenkins.branch.BranchProperty;
import jenkins.branch.BranchPropertyDescriptor;
import jenkins.branch.JobDecorator;
import jenkins.branch.MultiBranchProject;
import jenkins.branch.NoTriggerBranchProperty;
import jenkins.branch.OverrideIndexTriggersJobProperty;

public class FirstTriggerOnly extends BranchProperty {

    @DataBoundConstructor
    public FirstTriggerOnly() {
    }

    @Override
    public <P extends Job<P, B>, B extends Run<P, B>> JobDecorator<P, B> jobDecorator(Class<P> clazz) {
        return null;
    }

    @Extension
    public static class DescriptorImpl extends BranchPropertyDescriptor {
        public String getDisplayName() {
             return "First automatic SCM triggering only";
        }
    }
    
    @Extension
    public static class Dispatcher extends Queue.QueueDecisionHandler {

        @SuppressWarnings({"unchecked", "rawtypes"}) // untypable
        @Override
        public boolean shouldSchedule(Queue.Task p, List<Action> actions) {
            for (Action action :  actions) {
                if (action instanceof CauseAction) {
                    for (Cause c : ((CauseAction) action).getCauses()) {
                        if (c instanceof BranchIndexingCause || c instanceof BranchEventCause) {
                            if (p instanceof Job) {
                                Job<?,?> j = (Job) p;
                                if (j.getParent() instanceof MultiBranchProject) {

                                    OverrideIndexTriggersJobProperty overrideProp = j.getProperty(OverrideIndexTriggersJobProperty.class);
                                    if (overrideProp != null) {
                                        return overrideProp.getEnableTriggers();
                                    } else {
                                        for (BranchProperty prop : ((MultiBranchProject) j.getParent()).getProjectFactory().getBranch(j).getProperties()) {
                                            if (prop instanceof FirstTriggerOnly) {
                                            	return j.getBuilds().isEmpty();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

    }    

}
