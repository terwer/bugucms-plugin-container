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
./mvnw clean package -V -e
sudo ./mvnw spring-boot:run