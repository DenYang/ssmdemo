1.导入各种依赖，本项目用的依赖如下：
    servlet和jsp依赖：
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.2</version>
    </dependency>

    mybatis依赖：
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.7</version>
    </dependency>
    <dependency>
       <groupId>org.mybatis</groupId>
       <artifactId>mybatis-spring</artifactId>
       <version>2.0.6</version>
    </dependency>

    spring框架依赖：
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.12</version>
    </dependency>

    springmvc框架依赖：
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.12</version>
    </dependency>

    mysql依赖：
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.27</version>
    </dependency>

    spring 事务依赖：
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>5.3.12</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.3.12</version>
    </dependency>

    阿里巴巴druid数据库连接池依赖：
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.2.8</version>
    </dependency>

    JSON jackson依赖：
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.13.0</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.13.0</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>2.13.0</version>
    </dependency>

2.在web.xml配置文件中注册各种对象
    注册dispatcherServlet控制器
    <servlet>
            <servlet-name>springmvc</servlet-name>
            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:conf/springmvc.xml</param-value>
            </init-param>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>springmvc</servlet-name>
            <url-pattern>*.do</url-pattern>
        </servlet-mapping>

    注册ContextLoaderListener监听器
        <context-param>
            <!--contextConfigLocation表示配置文件的位置-->
            <param-name>contextConfigLocation</param-name>
            <param-value>
                classpath:conf/ApplicationContext.xml
            </param-value>
        </context-param>
        <listener>
            <listener-class>org.springframework.web.context.ContextLoaderListener
            </listener-class>
        </listener>

        注册声明过滤器，解决post请求乱码的问题
        <filter>
            <filter-name>characterEncodingFilter</filter-name>
            <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
            <!--设置项目中使用的字符编码-->
            <init-param>
                <param-name>encoding</param-name>
                <param-value>utf-8</param-value>
            </init-param>
            <!--强制请求对象使用encoding编码的值-->
            <init-param>
                <param-name>forceRequestEncoding</param-name>
                <param-value>true</param-value>
            </init-param>
            <!--强制响应对象使用encoding编码的值-->
            <init-param>
                <param-name>forceResponseEncoding</param-name>
                <param-value>true</param-value>
            </init-param>
        </filter>
        <filter-mapping>
            <!--/*：表示强制所有的请求先通过过滤器处理-->
            <filter-name>characterEncodingFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>

3.创建四种包，分别是controller、dao、domain、service
4.创建配置文件。该项目用的地址为：/resources/conf
    springmvc配置文件：
        声明组件扫描器，指定@Controller注解所在的包名
        <context:component-scan base-package="com.denyang.controller" />
            加入注解驱动，解决冲突问题
            <mvc:annotation-driven/>

    spring配置首先要在conf目录下新建一个资源文件
        内容为：
            jdbc.url=jdbc:mysql://localhost:3306/company?characterEncoding=utf8
            jdbc.username=root
            jdbc.password=Denyang5246
            jdbc.max=20
        然后在spring配置文件中加入：
            <!--数据源-->
                <context:property-placeholder location="classpath:conf/config.properties" />
                <bean id="myDataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
                    <property name="url" value="${jdbc.url}" />
                    <property name="username" value="${jdbc.username}" />
                    <property name="password" value="${jdbc.password}" />
                    <property name="maxActive" value="${jdbc.max}" />
                </bean>

        新建一个mybatis配置文件，内容如下：
            <typeAliases>
                    <!--实体类文件所在包名-->
                    <package name="com.denyang.domain"/>
                </typeAliases>
                <mappers>
                    <!--mapper文件所在包名-->
                    <package name="com.denyang.dao"/>
                </mappers>
        然后在spring配置文件中声明sqlSessionFactoryBean，使用它来创建sqlSessionFactory：
             <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
                    <property name="dataSource" ref="myDataSource"/>
                    <property name="configLocation" value="classpath:conf/mybatis.xml"/>
                </bean>

        接下来声明mybatis扫描器来声明dao对象：
            <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
                    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
                    <property name="basePackage" value="com.denyang.dao" />
                </bean>

        再接下来声明service的注解@service所在包名位置：
            	<context:component-scan base-package="com.denyang.service" />
        至此，骨架搭建完成


