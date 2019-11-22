SpringCloud

#### 什么是微服务

https://martinfowler.com/articles/microservices.html

​	通常而言，微服务架构是一种架构风格，*它提供将单一应用程序划分成一组小的服务*，每个服务运行在其独立的自己的*进程*中，服务之间相互协调、相互配合，为用户提供最终价值。服务之间彩英轻量级的通信机制相互沟通（通常是基于 HTTP 的 Restful API）。每个服务都围绕着具体业务进行建模，并且能够被独立地部署到生产环境、类生产环境等

​	另外，应该避免统一的、集中式的服务管理机制，对具体的服务而言，应该根据业务上下文，选择合适的语言、工具对其进行构建，可以有一个非常轻量级的集中式管理来协调这些服务，可以使用不同的语言来编写服务，也可以使用不同的数据存储。

​	微服务强调的是服务的大小，它关注的是某一个点，是具体解决某一个问题/提供落地对应服务的一个服务应用，狭义的看，可以看作项目中的一个个微服务工程或者 Module



###  Dubbo 和 微服务 Cloud 的区别

通信机制：

​	Dubbo 通过 RPC（远程过程调用），微服务 Cloud 是基于 Rest 调用（就是 HTTP 资源 API）；



### 微服务的优缺点

 优点：

​	每个服务足够内聚，足够小，代码容易理解，这样能聚焦一个指定的业务功能或业务需求。

​	开发简单、开发效率提高，一个服务可能就是转义的只干一件事。

​	微服务能够被小团队单独开发，这个小团队是 2 到 5 人的开发人员组成。

​	微服务是松耦合的，是由功能意义的服务，无论是在开发阶段或是部署阶段都是独立的。

​	微服务可以使用不同的语言开发。

​	微服务只是业务逻辑的代码，不会和 HTML、CSS 或者其他界面组件混合。



### 微服务的技术栈

| 微服务条目                               | 落地技术                                                     |
| ---------------------------------------- | ------------------------------------------------------------ |
| 服务开发                                 | SpringBoot、Spring、SpringMVC                                |
| 服务配置与管理                           | Netflix 公司的 Archaius、阿里的 Deamond 等                   |
| 服务注册与发现                           | Rureka、Consul、Zookeeper 等                                 |
| 服务调用                                 | Rest、RPC、gRPC                                              |
| 服务熔断器                               | Hystrix、Envoy 等                                            |
| 负载均衡                                 | Ribbon、Nginx 等                                             |
| 服务接口调用（客户端调用服务的简化工具） | Feign 等                                                     |
| 消息队列                                 | Kafka、RabbitMQ、ActiveMQ 等                                 |
| 服务配置中心管理                         | SpringCloudConfig、Chef 等                                   |
| 服务路由（API 网关）                     | Zuul 等                                                      |
| 服务监控                                 | Zabbix、Nagios、Metrics、Spectator 等                        |
| 全链路追踪                               | Zipkin、Brave、Dapper 等                                     |
| 服务部署                                 | Docker、OpenStack、Kubernetes 等                             |
| 数据流操作开发包                         | SpringCloud Stream (封装与 Redis、Rabbit、Kafka 等发送接收消息) |
| 事件消息总栈                             | Spring Cloud Bus                                             |
| ....                                     | .....                                                        |



![1573920555478](C:\Users\86182\AppData\Local\Temp\1573920555478.png)



### Dubbo 与 SpringCloud 对比	

|              | Dubbo          | SpringCloud                  |
| ------------ | -------------- | ---------------------------- |
| 服务注册中心 | Zookeeper      | Spring Cloud Netflix Eureka  |
| 服务调用方式 | RPC            | Rest API                     |
| 服务监控     | Dubbo-monistor | Spring Boot Amin             |
| 断路器       | 不完善         | Spring Cloud Netflix Hystrix |
| 服务网关     | 无             | Spring Cloud Netflix Zuul    |
| 分布式配置   | 无             | Spring Cloud Config          |
| 服务跟踪     | 无             | Spring Cloud Sleuth          |
| 消息总栈     | 无             | Spring Cloud Bus             |
| 数据流       | 无             | Spring Cloud Stream          |
| 批量任务     | 无             | Spring Cloud Task            |
| ....         | ......         | .....                        |

## 热部署

``` xml
 <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>springloaded</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```





## lombok

### @Data

提供 getter 、setter、equals、canEqual、hashCode、toString 等方法

### @AllArgsConstructor

提供全参数的构造方法

### @NoArgsConstructor

提供无参数的构造方法

### @EqualsAndHashCode

提供 无参数构造方法、equals、canEqual、hashCode 方法

``` java
public class LomBokData {
    public LomBokData() {
    }
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LomBokData)) {
            return false;
        } else {
            LomBokData other = (LomBokData)o;
            return other.canEqual(this);
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof LomBokData;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }
}
```

### @NonNull

"属性" 注解，会自动产生一个关于此参数的非空校验。如果参数为空，则抛出一个异常

### @Value

要注意 Lombok 和 Spring 都有 @Value 注解

Spring 的 @Value 是注解在_方法_或者_字段_上

Lombok 的 @Value 是注解在 _类_ 上面的，提供包含所有参数的构造方法，提供equals、hashCode、toString 方法

### @SneakyThrows

"方法" 注解，将方法体放置在 try-catch 语句中，进行异常捕获。可以使用 @SneakyThrows(Exception.class) 的形式指定抛出的异常，也会产生默认构造方法

### @Accessors 注解

*通过该注解可以控制 getter 和 setter 方法的形式*

1. "fluent " 若为 true 则 getter 和 setter 方法的方法名都是属性名，且 setter 方法返回当前对象

   ``` java
   @Data
   @Accessors(fluent = true)
   class User {
       private Integer id;
       private String name;
       
       // 方法体内容省略，通过返回类型可以查看
       public Integer id() {}
       public User id(Integer id) {}
       public String name() {} 
       public User name(String name){}
   }
   ```

   

2. "chain" 若为 true 则 setter 方法返回当前的对象

   ```java
   @Data
   @Accessors(chain = true)
   class User {
       private Integer id;
       private String name;
       
       // 方法体内容省略，通过返回类型可以查看
       public Integer getId() {}
       public User setId(Integer id) {}
       public String getName() {} 
       public User setName(String name){}
   }
   ```

3. "preflix" 若为 true 则 getter 和 setter 方法会护士属性的指定前缀，方法名称遵循驼峰命名

   ``` java
   @Data
   @Accessors(prefix = 'f')
   class User {
       private Integer fId;
       private Integer fName;
       
       public Integer id(){}
       public void id(Integer id){}
       public String name() {}
       public void name(String name){}
   }
   ```

## @SuppressWarnings 注解

这是 java.lang 提供的注解，用来取消一些编译器产生的警告



## 呵呵

SpringCloud的Finchley版本中，很多在maven中央仓库是不存在的（可能，多次测试不通过）。

Finchley.RC1,Finchley.M9在中央仓库都是残缺的，无法实现dependencies功能，无法管理依赖版本 

亲测Finchley.SR1是相对稳定的版本

[ springcloud 版本说明，包含 SpringCloud 和 SpringBoot 兼容问题](https://blog.csdn.net/guofangsky/article/details/82868564)



使用 MapperScane 但是在 pom 中不指定 resources，无法扫描到 xml 文件

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/java/</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```



install 失败解决方法：  https://blog.csdn.net/whm18322394724/article/details/99991854



### RestTemplaye

RestTemplate 提供了多种便捷远程访问 http 服务的方法

是一种简单便捷的访问 restful 服务模板，是 Spring 提供的用于访问 Rest 服务的客户端模板工具集合



## Eureka 的描述

Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来是西安服务注册和发现

Eureka 采用了 C-S 的设计架构，Eureka Server 作为服务注册功能的服务器，他是服务的注册中心。

​	系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server 并维持心跳的连接，这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。 Spring Cloud 的一些其他模块（如：Zuul）就可以通过 Eureka Server 来发现系统中的其他微服务，并执行相关逻辑。

![1574061151288](C:\Users\86182\AppData\Local\Temp\1574061151288.png)



Eureka 包含两个组件：Eurelka Server 和 Eureka Client

Eureka Server 提供服务注册服务，各个节点启动后，会在 Eureka Server 中进行注册，这样 EurekaServer 中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到。



## Rureka 的自我保护机制

​	默认情况下，如果 EurekServer 在一定时间没有接收到某个服务实例的心跳，EurekaServer 将会注销该实例（默认 90 秒），但是当网络分区故障发生时，微服务与 EurekaServer 之间无法正常通信。以上行为可能变得非常危险 --- 因为微服务本身其实是健康的，此时本不该注销这个微服务。

​	Eureka 通过 "自我保护模式"来解决这个问题 --- 当 EurekaServer 节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。一旦进入该模式，EurekaServer 就会保护服务注册表中的信息，不再删除服务注册中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该 Eureka Server 节点会自动退出自我保护信息。

​	再自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例。当它收到的心跳数重新恢复到阈值以上时，该Eureka Server 节点就会自动退出保护模式。它的设计思路就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。

eureka.server.enable-self-preservation = false 禁用自我保护模式

## CAP

RDBMS（mysql / oracle / sqlServer）  ==》  ACID

​	A(Atomicity) 原子性、C(Consistency)一致性、I(Isolation)独立性、D(Durability)持久性

NSQL（redis / mongdb）==》 CAP

​	C(Consistency) 强一致性、A(Availability) 可用性、P(Partition tolerance) 分区容错性

_CAP 理论就是说在分布式存储系统中，最多只能实现两个，而由于当前网络的硬件肯定会出现延迟丢包等问题，所以，分区容错是我们必须选择的，所以我们只能在一致性和可用性之间进行权衡，没有 NoSQL 系统能够同时保证这三点。_

## Zookeeper 和 Eureka 之间的区别

Zookeeper 保证 CP , Eureka 保证 AP

Zookeeper : 当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟以前的注册信息，但是不能接受服务直接 down 掉不可用。也就是说，服务注册功能对可用性的要求高于一致性。但是 zk 会出现这样一种情况，当 master 节点因为网络故障与其他节点失去联系时，剩余节点会重新进行 leader 选举。问题在于，选举 leader 的事件太长，30s~120s 且选举期间整个 zk 集群都是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的环境下，因为网络问题使得 zk 集群时区 master 节点时较大概率会发生的事情，虽然服务能够最终恢复，但是漫长的选举时间导致的注册长时间不可用是不能容忍的。

Eureka : 各个节点都是平等的，几个节点挂掉是不会影响正常的工作，剩余的节点依然可以提供注册和查询服务。而 Eureka 的客户端在向 Eureka 注册或者如果发生连接失败，则会自动切换至其他的节点，只要有一台 Eureka 还在，就能保证注册服务的可用（保证可用性），只不过查询的信息可能不是自信的（不能保证强一致性）。除此之外，Eureka 还有一种自我保护机制，如果在 15min 只能超过 85% 的节点都没有正常的心跳，那么 Eureka 就会认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：

1. Eureka 不再从注册列表中移除因为长时间没收到心跳而应该过期的服务
2. Eureka 仍能够接受新服务的注册和查询请求，但是不会被同步到其他节点上（即保证当前节点依然可用）
3. 当网络稳定时，当前实例新的注册信息会被同步到其他的节点

_Eureka 可以很好的应对网络故障导致部分几点时区联系的情况，而不会向 zk 那样使整个注册服务瘫痪。_



## LoadBalance

负载均衡，在微服务或者分布式集群中经常用的一种应用。

负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的 HA（高可用）

常见的负载均衡软件有：Nginx、LVS、硬件 F5 等

相应的中间件，例如：dubbo 和 SpringClud 中均给出了负责均衡解决方法，SpringCloud 的负载均衡算法可以自定义。



### 负载均衡架构

![Ribbon架构图](E:\programes\microservice\shangguigu\document\Ribbon架构图.png)

Ribbon 在工作时分成两步：

第一步先选择 EurekaServer ，它有限选择在同一区域内负载较少的 server

第二步根据用户指定的策略，从 server 取到的服务注册列表中选择一个地址

其中 Ribbon 提供了多种策略，比如：轮询、随机和根据响应时间加权

## Feign

​	集成了 Ribbon，采用默认的 轮询 方法实现服务端的负载均衡。

​	目的是使编写 java Http 客户端变得更容易。通过接口的方法调用 Rest 服务。

​	使用 Ribbon + RestTemplate 时候，利用 RestTemplete 对 http 请求的封装处理，形成了一套模板化的调用方法，但是实际开发中，由于对服务依赖的调用可能不止一种，_往往一个接口会被多处调用，所以通常都会针对每个服务自行封装一些客户端类来包装这些服务依赖的调用_。所以，Feigin 在此基础上做了进一步的封装，由它来帮助我们定义和实现以来服务接口的定义，在 Feigin 的是线下，_我们只需要创建一个接口并使用注解的方式来配置_，即可完成对服务提供方的接口绑定，简化了使用 Spring Cloud Ribbon 时，自动封装服务调用客户端的开发量。



_负载均衡和熔断处理都是在 consumer 做的操作_



## Hystrix

​	hystrix 是一个用于处理分布式系统的 _延迟_ 和 _容错_ 的开源库，在分布式系统里，很多依赖不可避免地会调用失败，比如：超时、异常等，Hystrix 能够保证一个依赖问题地情况下，_不会导致整体地服务失败，避免级联故障，以提高分布式系统的弹性_

​	“断路器” 本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），_向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方法处理的异常，_这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中地蔓延，乃至雪崩。



### 服务熔断

​	在 Hystrix 添加在方法的上面 fallbackMethod  在 接口上面的是 fallbackFactory

​	熔断机制是对应雪崩效应地一种微服务道路保护机制

​	当扇出链路地某个微服务不可用或者响应时间太长，会进行服务地降级，进而熔断该节点微服务地调用，快速放回 “错误” 地响应信息。当检测到该节点微服务调用响应正常后恢复调用链路。在 Spring Cloud 框架中熔断机制通过 Hystrix 实现。Hystrix 会监控微服务间调用地状况，当失败地调用到一定阈值，缺省是 5 秒内 20 次调用失败就会启动熔断机制。



### 服务雪崩

​	多个微服务之间调用的时候，加入微服务 A 调用微服务 B 和微服务 C ，微服务 B 和微服务 C 又调用其他的微服务，这就是所谓的 “扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务 A 的调用就会占用越来越多的系统资源，进而引起系统崩溃，即所谓的 “雪崩效应”。



## Zuul

​	Zuul 包含了对请求的路由和过滤的两个最主要的功能：

​	其中路由功能负责将外面请求转发到具体的为服务实例上，是实现外部访问统一入口的基础而过滤功能负责对请求的处理过程进行干预，对于实现请求校验，服务聚合等功能的基础上 Zuul 和 Eureka 进行配合，将 Zuul 自身注册为 Eureka 服务治理下的应用，同时从 Eureka 中获取其他微服务的消息，也即以后的访问微服务都是通过 Zuul 跳转后获得。

​	注意：Zuul 服务最终还是会注册进 Eureka

提供服务 =  代理 + 路由 + 过滤 三大功能

## SpringCloud Config 分布式配置中心

![1574347580138](C:\Users\86182\AppData\Local\Temp\1574347580138.png)

### 是什么

​	SpringCloud Config 为微服务架构中的微服务提供_集中化_的外部配置支持，配置服务器为_各个不同微服务应用_的所有环境提供了一个_中心化的外部配置_。

###  怎么用

​	SpringCloud Config 分为服务端和客户端两部分

​	服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并未客户端提供获取配置信息，加密 / 解密信息等访问接口。

​	客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加密配置信息配置服务器，默认采用 git 来存储配置信息，这样有助于对环境配置进行版本管理，并且可以通过 git 客户端工具来方便的管理和访问配置内容。

## bootstrap.yml

​	SpringCloud 会创建一个 BootStrap Context 作为 Spring 应用的 Application Context 的 _父上下文_。初始化的时候， BootStrap Context 负责从外部资源加载配置属性并解析配置。这两个上下文共享从外部获取的 Environment。

BootStrap 属性拥有高优先级，默认情况下，他们不会被本地配置文件覆盖，BootStrap Context 和 Application Context 有着不同的约定。

所以，新增一个 bootstrap.yml 文件，保证 BootStrap Context 和 Application Context 配置的分离。

``` json
spring:
  cloud:
    config:
      #      意思是通过 http://config-3345.com:3345 获取 master 分支下 microservicecloud-config-client 中的 test 环境的配置信息
      name: microservicecloud-config-dept-client
      profile: test
      label: master
      uri: http://config-3345.com:3345

```

profile 属性值确定它能从 GitHub 上面获取什么样的配置



