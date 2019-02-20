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
			-- 导入 thymeleaf 配置 ： application.properties 中配置(注意文件编码，乱码会报错)
				-- spring.thymeleaf.encoding:utf-8 
				-- spring.thymeleaf.cache: false  //热加载，html 更改后只需 recomplie(idea 中)就可以刷新，无需重启项目
				-- spring.thymeleaf.mode:HTML5
（3）编写后台
		-- User 类
		-- UserRespository 类，
			-- 使用 ConcurrentHashMap 来存储，AtomicLong 来生成id（线程安全，比如自增操作）
		-- Controller 类
			-- Model 对象
			-- ModelAndView 对象
			-- ModelMap 对象
			-- @GetMapping , @PostMapping
（4）编写前台
		-- thymeleaf 会在 resources 目录下新建立 templates 目录，所有的前台页面可以分类写在这个目录下，ModelAndView.setViewName("/users/list") 就会去匹配 templates/users/list.html 页面
		-- 一些语法
			-- th:fragment="footer" 定义一个片段，可以在其他地方引用。如 th:replace="~{fragments/footer :: footer}" 就会去查找 templates 下的 fragments/footer.html 中名为 footer 的片段。
			-- th:replace="" 将此元素替换掉。 如 div 会被替换成另一个 div
			-- th:insert="" 将此元素中的内容替换掉。 如 div 不会被替换，其内容加上 div，即 <div><div></div></div>
			-- th:text="{text}" 元素中的内容被替换为从服务端接收的 text 变量
			-- th:value="{user.name}" 元素的 value 属性值被替换为从服务端接收的 user.name 值
			-- th:href="@{/users/list}" a 元素的 href 属性更改为 /users/list
			-- th:each="user : ${uerList}" 遍历服务端返回的 list 集合
			-- th:object="user" 将此元素关联到服务端返回的对象
				-- *{name} 取当前 object 中指定对象的 name 属性
			-- th:
		-- 动态加载
-----------------------------------------------------------------------------
day05 连接数据库
(1)spring jpa
    -- hibernate 
(2)实体
    -- 对应一个数据表，每个实体实例是一行
    -- 使用 @Entity 注解
(3)实体条件
    -- public/protected 无参构造器，便于映射
    -- 实现 Serializable 接口，实现远程传递
    -- 唯一对象标识作为主键 @Id
(4)关系
(5)EntityManager 接口
    -- 定义与持久性上下文进行交互的方法
    -- 创建和删除持久实体实例，通过实体的主键查找实体
    -- 允许在实体上运行查询
    -- EntityManagerFactory来获取
(6)常用接口
    -- CrudRepository : 
    -- PagingAndSortingRepository : 分页和排序常用
(7)自定义接口
    -- 继承 Repository 
    -- 方法名有规则
(8)导入依赖
    -- springboot data jpa
    -- mysql connector
(9)启动
    -- Consider the following:
        If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
        If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).
        原因：引入hibernate 后，框架中的 datasource 被 @Configuration 读取，但是 datasource 没有配置参数，所以会报错。
        解决：可以使用一个内置数据库，如 h2，就会自动配置
                或者暂时不读取配置项
            build.gradle 中 implementation 'com.h2database:h2:1.3.172'
(10)后台改造
        -- User 实体
            -- @Entity
            -- @Id ， @GeneratedValue(strategy= GenerationType.IDENTITY)
            -- toString , String.format()
        -- UserReposity 
            -- extends CrudRepository<实体类型，参数类型>
            -- 删除 UserRepositoryImpl
        -- UserController
            -- 重新更改增删改查方法
(11)运行
        -- Consider defining a bean of type 'com.XXX' in your configuration.
            明明已经 @Autowired ,并且 UserRepository extends CrudRepository后无需提供实现类就可
            工作，但是报此错误。
            在 Application 类中 添加 @EnableJpaRepositories(basePackages = "com.alex.*")

        -- spring boot jpa-java.lang.IllegalArgumentException: Not a managed type
            -- 上面问题解决了，再次启动。
            -- User 没有实例化成功，检查 @Entity 有没有。如果有，Application 加上 @EntityScan(basePackages = "com.alex.*")
            -- springboot 有约定的扫描规则 https://blog.csdn.net/heyewu4107/article/details/78942393
                -- @ComponmentScan(basePackages = "") // 只扫描@Component（包括 @Service，@Controller, @Service）
                -- @EntityScan(basePackages = "") // 只扫描 @Entity 的
                -- @EnableJpaRepositories(basePackages = "") // 只扫描 @Repository
(12)运行成功
        -- 数据存储到了 h2 内置数据库里（h2无需配置，是一个内存数据库。当然，如果愿意也可以配置）
        -- 查看 h2 console
            -- applicaiton.yml 中配置 spring.h2.console.enable=true
            -- http://ip:port/h2-console
(13)连接 mysql
        -- applicaiton.yml 中配置数据源
        -- 配置show-sql, ddl-auto
The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration

---------------------------------------------------
day06 - 使用 elasticsearch
(1)elastic 版本 : 6.5.0  
(2)build.gradle 添加依赖
        -- spring-boot-starter-data-elasticsearch // 注意elasticsearch 版本对应，否则报错找不到 节点
        -- jna包 // 暂时没发现作用
(3)application.yml 增加配置
        -- spring.data.elasticsearch.cluster_nodes=ip:port //port 应该是 tcp 端口， 9300
        -- spring.data.elasticsearch.repositories.enabled=true // 开启 es 仓库，默认为 true
(4)EsBlog 类： 文档类，要存储的数据
        -- @Document(indexName="blog", type="blog") // 指定文档索引和类型
(5)interface EsBlogRepository extends ElasticSerachRepository<EsBlog, String> // String 是 EsBlog 中 id 字段类型
        -- 无需注解，继承父类注解 @NoRepositoryBean ，这个注解意思是 使用了此注解的接口不创建子类实例 //我也不知道为什么会继承
        -- 自定义方法
            -- Page<EsBlog> findEsBlogsByContentContaining(String content, Pageable pageable);
                -- Page<EsBlog> 和 Pageable
                -- 
(6)Application 
        -- @EnableElasticSearchRepositories(basePackages = "")

(7)测试
        -- @RunWith(SpringRunner.class)
        -- @SpringBootTest(classes = Application.class) // 需要指定主加载类，否则默认只加载 test 类同路径下 ，会报 cant load ApplicationContext ,因为bean 无法注入


(8)总结：因为都是 spring-data 项目，所以 spring-boot-jpa 和 spring-boot-elasitcsearch 有很多相似性
        -- 无需写增删改查语句， 直接继承 CrudRepository/ElasticSearchRepository接口 就可以实现增删改查功能。  也可以根据规则自定义方法，来实现特定操作
        -- 都要加一个扫描仓库 @EnableRepositories()/@EnableElasticSearchRepositories()
        -- jpa 要指定 @Entity ，elasticsearch 要指定 @Document(indexname="", type="")

(9)bootstrap 与 springboot集成
        -- html5, meta, normalize.css 作用， reboot作用
        -- 移动设备优先
        -- 响应式
            -- 因为网格系统
        -- 网格系统
            -- viewport 尺寸的增加，系统会自动分为 12 列


-------------------------------------------------------------
day07 - 
(1)
        -- 需求分析
        -- 原型设计 ： 最终效果，反复修改原型成为产品
            -- 生成最终产品效果
            -- 和需求分析一起决定产品
(2)	博客系统
        -- 需求分析
            -- 用户管理
                -- 注册
                -- 增加
                -- 删除
            -- 安全设置
            -- 博客管理
            -- 评论管理
            -- 点赞管理
            -- 分类管理
            -- 标签管理
            -- 首页搜索
        -- 原型设计
            -- 
(3)基于角色的权限管理
        -- 角色
            -- 项目管理员、游客等等
            -- 限定用户在系统中的操作
            -- 通过账号体现
        -- RBAC
            -- Role-Based access control
            -- 隐式访问方式 ： 和角色关联 // 用户是否是某个角色
            -- 显式访问方式 ： 和权限关联 // 用户是否拥有某个权限
            -- Shiro 和 Spring Security 两个框架
                -- shiro 更简洁
                -- spring security 更强大，和 spring 兼容性更好
        -- Spring Security
            -- 后端
                
            -- 前端


		

		
    


