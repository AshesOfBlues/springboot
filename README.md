# springboot
springboot learning
-------------------------------------------
day01
(1)第一个 web 项目 start.springboot.io
(2)gradle project + springboot 1.5.9
(3)在/blog-start/ 中 gradle build
    + build/
(4)java -jar build/blog-start...jar
    Exception in thread "main" java.lang.reflect.InvocationTargetException
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
        at java.lang.reflect.Method.invoke(Unknown Source)
        at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:48)
        at org.springframework.boot.loader.Launcher.launch(Launcher.java:87)
        at org.springframework.boot.loader.Launcher.launch(Launcher.java:50)
        at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:51)
  Caused by: java.lang.NoClassDefFoundError: org/springframework/boot/SpringApplication
        at com.betteralex.blog.blogstart.BlogStartApplication.main(BlogStartApplication.java:10)
        ... 8 more
  Caused by: java.lang.ClassNotFoundException: org.springframework.boot.SpringApplication
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:94)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        ... 9 more
    原因： gradle 和 springboot 版本问题 - 使用 gradle3.5 构建 springboot1.5.9 时，生成的 jar 包中不会有 lib 目录，而lib中则包含了所有依赖
    更改： 环境变为 gradle 5.2.1 + springboot 2.1.3
(5)环境变为 gradle 5.2.1 + springboot 2.1.3 成功运行
(6)nohup java -jar .\build\libs\blog-start-0.0.1-SNAPSHOT.jar &


