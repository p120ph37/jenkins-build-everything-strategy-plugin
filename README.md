# jenkins-build-everything-strategy-plugin
Build all multibranch refs, including tags

## Build and deploy in Jenkins

docker run -it maven bash # Start a maven container
git clone https://github.com/AngryBytes/jenkins-build-everything-strategy-plugin.git # Clone this repo
cd jenkins-build-everything-strategy-plugin # go inside projet
mvn install # Build
Don't close this container, open another terminal and get the file /jenkins-build-everything-strategy-plugin/target/build-everything-strategy.hpi with docker cp
Upload this file to https://jenkins/pluginManager/advanced
