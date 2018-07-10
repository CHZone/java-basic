### 流程
Connector 接收接收请求，解析 传递给Container。
父子级Container之间通过Pipeline连接。pipeline中有Vavle是数据处理的节点。

### Server
Tomcat服务器
根据server.xml的描述--server不是容器，因此不能添加subcomponent，例如Vavle
### Service
Catalina service (服务器下可以有多个service)
server.xml中描述--一个Service可以包含一个以上的Connector和一个Container(Engine)，这一级也不能有Vavle这样的子subcomponent。

### Connector
Connector： recive requests，return responses

### Engine  
(servlet engine?)
processes every request，stand alone
analyzes the HTTP headers included with the request, and passes them
on to the appropriate Host (virtual host).
用于单点分析http header，然后传递给合适的Host
Engine与Cluster同级，二选一？

### Host


### 2.1.2
一个Server 包含多个Service（Service之间相互独立，共享一个jvm和系统类库）。
一个Service维护多个Connector和一个Container。Connector将请求传递给同一个Service下的Container处理。
Service使得Connector不需要连接Container。
Connectory接收封装网络请求（Http、AJP）
这一级的Container称为Engine，表示Servlet引擎，而非Servlet容器,只负责处理封装后的请求。
tomcat是web应用服务器，Context表示Web应用，Engine将封装后的请求交给合适的web引用处理。
这使得Engine下可以有多个web应用。（这种设计支持其他类型的应用保留了扩展性）
Context的start和stop可以进行再启动时（tomcat？）进行资源的加载和释放。这种方式将加载和释放的过程分解到组件中，使得组件充分解耦，提高了服务器的可扩展性和可维护性。
域名对应Host，使得一个Server支持多个域名。Host时虚拟主机的抽象，让一台机器可以模拟多个计算机，也能让多个计算机运行一个应用。
Engine中既可以包含Host，也可以包含Context，由具体的Engine决定，再Tomcat中默认提供StandardEngine（Catanila）只包含Host。
Context支持多个Servlet来处理不同的请求，Servlet对应Wrapper容器组件

各自组件的一致行为是接收请求和返回响应数据

8.5.6之前Service中的持有的是Container接口
8.5.6之后持有的是Engine接口。
8.5.6之前可以直接由Service维护一个Context。

Tomcat 后台正对后台处理，再Container接口中定义backgroundProgress()方法，并且在ContainerBase确保在启动组件的同时，异步启动后台处理。个组件在实现的过程中只用确保实现了backgroundProgress()方法即可，不必考虑创建异步线程。

### lifecycle
Lifecycle接口关联组件声明周期--初始化、启动、停止、销毁。
各核心组件继承LifecycleMBeanBase抽象类，处理实现状态转换及相应的相应的事件处理，还将各组件组成为MBean，以便Tomcat的管理工具进行动态维护。
每个声明周期方法可能对应多个状态转换（及事件处理），例如start()方法中由启动前、启动中、启动后三个自动转换（自动转换不需要额外方法调用。）
并不是每个状态都会触发声明周期事件、也不是所有声明周期事件都有对应的状态。

### Pipeline和Valve
上面是对各个模块的拆解，让模块`之间`具有可伸缩性和可扩展性。请求处理也是责任链模型的典型应用场景。
Pipeline用于构造责任链，Valve代表处处理逻辑功能模块。
Engine、Host、Context、Wrapper均有对应的基础Valve和Pipeline。我们可以对任何层级的容器进行扩展。（Spring MVC是不是对Wrapper的Valve进行了扩展？）

### Connector设计
Endpoint负责启动线程监听端口服务，Processor进行数据读取和处理。

Processor根据映射关系找容器，但是Connector与Container不是多对一吗？？
Mapper负责维护映射关系，MapperListener实现了ContainerListener和LifecycleListener用于在容器状态变更时注册或注销容器映射信息。
Tomcat7 之前Mapper由Connector维护。
Tomcat8中由Service维护。
使用适配器对Connector与Mapper、Container解耦。
Connector(Coyote)对应的适配器CoyoteAdapter。
（可以通过实现自己的适配器来改造请求处理逻辑，摆脱Servlet规范。）

虚线非三角箭头是弱包含（引用）关系？

### Executor
并发所必须的，
由Service维护的，在同一个Service下的容器可以共享。
Endpoint创建的不能共享。

### Catalina和Bootstrap
Catalina 解析server.xml、web.xml等，启动和停止Server  -- shell
Bootstrap为启动入口

为什么不用Catalina直接启动。
catalinar.jar在`$CATALINA_HOME/lib`目录下，bootstrap.jar在`$CATALINA_HOME/bin`目录下。BootStrap直接在jre创建SharedClassLoader,通过反射创建Catalina实例及整个tomcat服务器。
这是一种好的中间件设计方案。将启动如可与核心环境解耦，简化了启动环境（不必配置依赖，仅使用几个独立的API），便于我们灵活的组织中间件产品结构，尤其是类加载器的方案，否则我们所有的依赖都将放置的一个类加载器中，无法灵活定制。（又是类加载器，这段话理解起来有点不同顺畅）

Catalinar通过解析配置文件来设置服务器，因此我们可以通过手动的调用Tomcat类的接口在程序中设置这些配置，并自定义启动服务器，如YAML。这是Tomcat灵活的架构设计带来的遍历，也是中间件产品的架构关注点之一。

### 类加载
Endorsed Standards Override Mechanism ： %JAVA_HOME%/lib/endorsed目录下的类覆盖JAXP（如rt.jar）中的类。也可通过`java.endorsed.dir`来修改。
JBoss Endorsed目录：%JBOSS_HOME%/lib/endorsed
Tomcat 启动参数相关配置 $CATALINA_HOME/endorsed
但并不是所有的核心类库都可以覆盖

tomcat ClassLoader

CommonClassLoader : $CATALINA_HOME/lib    for Catalina 和所有Web App
CatalinaClassLoader: server.loader Catalina Only
SharedClassLoader: no Catalina ,for all web app
WebAppClassLoader: shared.loader  for specific Web app    WEB-INFO/classes下的`*.class`和WEB-INF/lib下的`*.jar`

应用：
实现自己的会话存储方案。配置server.loader创建独立的CatalinaClassLoader

最后通过，$CATALINA_HOME/bin下的包由AppClassLoaer加载。简化了Tomcat的启动，同时增加了灵活性。

### WebAppClassloader




