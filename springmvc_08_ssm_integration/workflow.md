(1) 创建工程

* 创建一个Maven的web工程
* pom.xml添加SSM需要的依赖jar包
* 编写Web项目的入口配置类，实现`AbstractAnnotationConfigDispatcherServletInitializer`重写以下方法
    * getRootConfigClasses()	：返回Spring的配置类->需要==SpringConfig==配置类
    * getServletConfigClasses() ：返回SpringMVC的配置类->需要==SpringMvcConfig==配置类
    * getServletMappings()      : 设置SpringMVC请求拦截路径规则
    * getServletFilters()       ：设置过滤器，解决POST请求中文乱码问题

(2)SSM整合[==重点是各个配置的编写==]

* SpringConfig
    * 标识该类为配置类 @Configuration
    * 扫描Service所在的包 @ComponentScan
    * 在Service层要管理事务 @EnableTransactionManagement
    * 读取外部的properties配置文件 @PropertySource
    * 整合Mybatis需要引入Mybatis相关配置类 @Import
        * 第三方数据源配置类 JdbcConfig
            * 构建DataSource数据源，DruidDataSource,需要注入数据库连接四要素， @Bean @Value
            * 构建平台事务管理器，DataSourceTransactionManager,@Bean
        * Mybatis配置类 MybatisConfig
            * 构建SqlSessionFactoryBean并设置别名扫描与数据源，@Bean
            * 构建MapperScannerConfigurer并设置DAO层的包扫描
* SpringMvcConfig
    * 标识该类为配置类 @Configuration
    * 扫描Controller所在的包 @ComponentScan
    * 开启SpringMVC注解支持 @EnableWebMvc

(3)功能模块[与具体的业务模块有关]

* 创建数据库表
* 根据数据库表创建对应的模型类
* 通过Dao层完成数据库表的增删改查(接口+自动代理)
* 编写Service层[Service接口+实现类]
    * @Service
    * @Transactional
    * 整合Junit对业务层进行单元测试
        * @RunWith
        * @ContextConfiguration
        * @Test
* 编写Controller层
    * 接收请求 @RequestMapping @GetMapping @PostMapping @PutMapping @DeleteMapping
    * 接收数据 简单、POJO、嵌套POJO、集合、数组、JSON数据类型
        * @RequestParam
        * @PathVariable
        * @RequestBody
    * 转发业务层
        * @Autowired
    * 响应结果
        * @ResponseBody
