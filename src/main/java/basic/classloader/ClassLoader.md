## target
基本核心目的
1. 理解双亲委托机制：标准过程loadClass中的findClass才是自身正则
2. 会实现标准的自定义类加载器。
3. 冲破双亲类加载机制方法

深入
1. 类的比较中可能会忽略的加载器归属因素
2. 方法区中常量相关String.intern()方法相关
3. SPI（jdbc相关）
4. 传说中的类加载器之大成--OSGi
5. HotSwap（tomcat ServletWarpper）
6. 令人不适的SecurityManager

###标准类加载过程
双亲委托机制实现--java.lang.ClassLoader.loadClass方法主要过程: 
1. 查找当前类加载器已加载的类 Class<?> c = findLoadedClass(name);
2. 调用父类加载器的loadClass方法
3. 调用自身的findClass()

AppClassClader.loadClass：



标准自定义类加载器实现：
1. 继承ClassLoader
2. parent默认会从Launcher中获取AppClassLoader，也可以手动设定父类
3. 实现findClass方法（默认的findClass方法会直接抛出异常）

因此冲破双亲加载机制：就覆盖loadClass



类加载器的作用：
类的共享和隔离。
