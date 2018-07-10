启动入口BootStrap.main()
```
1. bootstrap.init();
初始化初始化CommonClassLoader，CatalinaClassLoader，SharedClassLoader
使用CatalinaClassLoader加载Catalina，
设置Catalina的父类加载器为SharedClassLoader

// 让 Bootstrap 阻塞，不退出？可以试一下吧true改为false。
daemon.setAwait(true);
2. daemon.load(args);// 执行Catalina的load(String)方法。laod("start");
设置sever.xml文件的解析规则
解析server.xml,创建Server、Connector、Engine、Host，xml配置的Context，
并设置相应的属性，没进行目录扫描创建目录部署项目对应的Context。
执行Server.init()--LifecycleBase.init();
//修改EventState为INITIALIZING，
//然后获取EventState绑定的Event
//fireLifecycleEvent(lifecycleEvent, data); lifecycleEvent为String类型
// StandardServer 的监听器做了什么没搞清楚，先不管。
// 然后执行standardService.initInternal() 
// super.initInternal()执行LifecycleMBeanBase.initInternal
//该方法最后调用Service的init方法。
// Service没有listener
// Engine.init() 只有 EngineConfig一个Listener，不过Engine只有start和shop两个逻辑
// Engine extends ContainerBase extens LifecycleMBeanBase implement Container implement LifeCycle。
// Engine初始完成之后，才执行Connector相关初始化


3. daemon.start();//执行Catalina.start()


```

```getServer().start();
可能是StandardServer父类的start方法。

```


### init
StandardEngine extends ContainerBase implements Engine
					   ContainerBase extends LifecycleMBeanBase  implements Container
					                                                        Container extends Lifecycle 


