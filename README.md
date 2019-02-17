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
	-- 作用 ： 统一 gradle 版本
	-- 当本机没有 gradle 环境，但是项目是一个 gradle 项目，gradle wrapper 就会自动下载配置文件中版本的 gradle 环境进行构建
	-- 和 mvn wrapper 一样
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
        = @Configuration(一般和 @Bean 使用) + @EnableAutoConfiguration + @ComponentScan
    -- @RestController
        = @Controller + @Responsebody 为了更好的处理 rest 请求
    -- 在 test 中使用MockMvc 来测试 controller 是否生效
        @Runwith(SpringRunner.class)
            -- SpringRunner 和 SpringJUnit4ClassRunner 区别？
        @SpringBootTest
            -- 作用
        如何使用 MockMvc ?
			-- 需要注解
			-- 需要导入的 package
			-- 代码
（7）Controller 类的路径 : 有时候明明 controller 没有错误但是 404
	-- @SpringBootApplication 包含了 @ComponentScan 注解，会扫描@Controller 的类，但是 默认的扫描路径是 当前Applicaiton 类的包
		和其子包，如果Controller 类在其他包中，可以使用 @ComponentScan(basePackages = "com.alex.controller.*") 格式来自定义扫描包
	-- 自定义多个扫描包 basePackages = {"com.alex.*", ""}
（8）配置 gradle wrapper
	-- 更改配置文件 
		-- url 更改所需版本
		-- gradlew build 来使用 wrapper 中的版本来构建
（9）gradle bootRun 来运行 springboot 程序

-----------------------------------------------------------------------
day03
（1）thymeleaf - 模板引擎，提取出公用代码复用
		-- 语法 ognl， springel
		-- 遵从 web 标准
		-- 格式 : 写在元素内
			-- th:text="" : 需要引入命名空间
			-- data-th-text="" 
		-- 语法
			-- 变量表达式 ${}
			-- 消息表达式 #{} 又称 国际化
			-- 选择表达式 *{} : 与变量表达式的区别
			-- 链接表达式 @{} : 4种
			-- 分段表达式 
			-- 字面量： 文本、数字、布尔、
			-- 算术操作
			-- 比较操作
			-- 三目运算符
			-- 无操作 ： 什么都不做，用下划线表示 _
		-- 设置属性值
			-- th:attr : 任意属性值， vue中的 v-bind
			-- th:属性值 ,如 th:class 来设置 class 属性值
		-- 迭代器 th:each="book : ${books}"
			-- 状态变量： index,count,size,current,even,odd,first,last
		-- 条件语句
			-- th:if
			-- th:unless
			-- th:switch + th:case 最多匹配一个 case
		-- 模板布局
			-- 定义和引用片段 
				-- 定义 th:fragment
				-- 引用 th:insert、th:replace、th:include(不推荐)
		-- 属性优先级
			-- 
		-- 注释
			-- <!--/* */-->
			-- <!--/*/ /*/-->
		-- 内联表达式
			-- th:text 转义符号 [[]]
			-- th:utext 不转义  [()]
			-- 禁用内联 th:inline="none"
		-- 对象
			-- #ctx 上下文对象
			-- #locale	直接访问与 java.util.Locale 关联的当前请求
			-- request/session/param/application
			-- #request/#session/servletContext
（2）thymeleaf 和 springboot 集成
		-- 引入依赖并指定 thymeleaf 3版本 
			-- build.gradle 中
			

		
    


