APP_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
echo $APP_VERSION