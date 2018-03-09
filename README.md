# jenkins-build-everything-strategy-plugin
Build all multibranch refs, including tags

## Build
```
# in project directory
docker run  -v <proj_dir>:/app/ -w /app/ maven mvn install
```
find the build artifact in <proj_dir>/target/build-everything-strategy.hpi

## Deploy
Upload hpi file to https://jenkins/pluginManager/advanced
