# jenkins-build-everything-strategy-plugin
Build all multibranch refs, including tags

## Build or Download

### Build
```
# in project directory
docker run  -v $(pwd):/app/ -w /app/ maven mvn install
```
Find the build artifact in ./target/build-everything-strategy.hpi

### Download
Find the built artifact, courtesy of TravisCI in this repo's
[releases](https://github.com/p120ph37/jenkins-build-everything-strategy-plugin/releases)

## Deploy
Upload hpi file to https://jenkins/pluginManager/advanced
