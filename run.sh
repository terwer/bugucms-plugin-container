echo 'current workspace:'
echo $WORKSPACE
cd $WORKSPACE
cd src/main/webapp

npm set registry https://registry.npm.taobao.org && \
npm set disturl https://npm.taobao.org/dist && \
npm set sass_binary_site https://npm.taobao.org/mirrors/node-sass && \
npm set electron_mirror https://npm.taobao.org/mirrors/electron && \
npm set puppeteer_download_host https://npm.taobao.org/mirrors && \
npm set chromedriver_cdnurl https://npm.taobao.org/mirrors/chromedriver && \
npm set operadriver_cdnurl https://npm.taobao.org/mirrors/operadriver && \
npm set phantomjs_cdnurl https://npm.taobao.org/mirrors/phantomjs && \
npm set selenium_cdnurl https://npm.taobao.org/mirrors/selenium && \
npm set node_inspector_cdnurl https://npm.taobao.org/mirrors/node-inspector && \
npm cache clean --force

echo 'current npm registry:'
npm get registry
npm install
npm run build

cd $WORKSPACE
chmod +x mvnw
./mvnw clean package -DskipTests -V -e
echo "current user:"
whoami
#echo "stoping previous application..."
#sudo ./mvnw spring-boot:stop
#echo "previous application stoped."
#echo "starting previus application..."
#sudo ./mvnw spring-boot:run &
#echo "application started."
#echo "finish."
#export LANG=en_US.UTF-8
#export JAVA_HOME=/etc/java/java-1.8.0-openjdk/java-1.8.0-openjdk-1.8.0.272.b10-3.el8_3.x86_64
#export PATH=$JAVA_HOME/bin:$PATH
#export MALLOC_ARENA_MAX=4
#JAVA_OPTS="-server -Xms1024m -Xmx1024m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=384m -XX:NewSize=512m -XX:MaxNewSize=512m -XX:SurvivorRatio=8"
APP_PATH=$WORKSPACE/target
cd $APP_PATH
pwd
chmod +x springboot.sh
sh springboot.sh restart


