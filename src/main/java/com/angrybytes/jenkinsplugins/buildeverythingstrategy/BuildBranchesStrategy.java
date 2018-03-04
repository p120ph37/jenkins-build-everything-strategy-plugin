package com.angrybytes.jenkinsplugins.buildeverythingstrategy;

import hudson.Extension;

import java.util.concurrent.TimeUnit;

import org.kohsuke.stapler.DataBoundConstructor;
import jenkins.branch.BranchBuildStrategy;
import jenkins.branch.BranchBuildStrategyDescriptor;
import jenkins.scm.api.SCMSource;
import jenkins.scm.api.SCMHead;
import jenkins.scm.api.mixin.TagSCMHead;
import jenkins.scm.impl.UncategorizedSCMHeadCategory;

public class BuildBranchesStrategy extends BranchBuildStrategy {

    @DataBoundConstructor
    public BuildBranchesStrategy() {
    }

    @Override
    public boolean isAutomaticBuild(SCMSource source, SCMHead head) {    	
    	return UncategorizedSCMHeadCategory.DEFAULT.isMatch(head,source.getCategories());
    }

    @Extension
    public static class DescriptorImpl extends BranchBuildStrategyDescriptor {
        public String getDisplayName() {
             return "Build Branches";
        }
    }

}
