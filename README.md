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

-----------------------------------------------------------------------
day02
(1)build.gradle
(2)gradle wrapper
(3)更改 build.gradle 文件，版本号等
(4)将 blog-start 移植为 helloworld ，
    - 不需要编译后的文件 .gradle/ , build/
    - settings.gradle 文件修改 rootproject = 'helloworld'
    - gradle build
    - java -jar ...
(5)gradle 更改 aliyun 中央仓库来加快构建速度
    - build.gradle 中 
    repositories{
        mavenCentral() 注释掉
        maven{
            url 'http://maven.aliyun.com/nexus/content/groups/public'
        }
    }
(6)导入项目到 idea , auto-import 来自动导入依赖，并选择本地的 gradle 作为构建工具
    -- 更改 class BlogStartApplication 为 Application ， 规范化
    -- @SpringBootApplication （鼠标放到注解上显示帮助 settings -> general -> show quick documention on mouse move)
        = @Configuration(一般和 @Bean 使用) + @EnableAutoConfiguration + @ComponmentScan
    -- @RestController
        = @Controller + @Responsebody 为了更好的处理 rest 请求
    -- MockMvc 来测试 controller 是否生效
    


