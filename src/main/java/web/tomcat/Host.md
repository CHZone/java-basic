StandardHost没有initInternal方法

HostConfig使用线程池部署Contenxt。

server.xml部署：
对服务器由入侵

HostConfig扫描方式
描述文件部署（推荐部署方式）
描述文件位置--$CATALINA_BASE/conf/<Engine名称>/<Host名称>目录下，
默认为本地D:\Program Files\Java\apache-tomcat-8.5.28\conf\Catalina\localhost
HostConfig扫描该目录下得webappName.xml,使用线程池进行部署。其中path无效，path为webappName
```
<Context docBase="mywebapps/myApp" path="myApp" reloadable="false">
	<<WatchedResource>WEB-INF/web.xml</WatchedResource>
</Context>
```


Web目录部署：
其中目录指Host的appBase对应的目录，默认为 D:\Program Files\Java\apache-tomcat-8.5.28\webapps
deployXML=true ，根据appNmae/META-INF/context.xml定制Context
copyXML=true, 将appNmae/META-INF/context.xml,修改为appName.xml放到$CATALINA_BASE/conf/<Engine名称>/<Host名称>目录下。

deployXML=false，且存在appNmae/META-INF/context.xml, 则FailedContext构建失败。

War包部署：
appName.war
War包位置--Host的appBase目录，默认为 D:\Program Files\Java\apache-tomcat-8.5.28\webapps
deployXML=true, 且copyXML=false,使用war包下/META-INF/context.xml创建Context
deployXML=true, 且copyXML=true,将war包下/META-INF/context.xml,复制到$CATALINA_BASE/conf/<Engine名称>/<Host名称>目录下。
deployXML=false，且war包下存在/META-INF/context.xml, 则FailedContext构建失败。


通用过程：
contextClass可以指定自己的Context对象，默认为StandardContext
②为Context添加ContextConfig
③调用Host.addChild。如果Host启动则启动Context（所以Context在start方法中创建、启动）
④将Context描述文件，web应用根目录、web.xml添加到守护资源，当资源变更时进行应用的重新部署或重新加载。





