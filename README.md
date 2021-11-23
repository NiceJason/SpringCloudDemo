## :sunny:SpringCloudDemo

<p align='center'>微服务试验地</p>

>这是实践微服务知识的地方，眼过千遍不如手过一遍，很多时候在动手时会碰到新问题，发现以前从未关注过的点。再加上工作中，很多框架底层都是大佬们搭建好的，我们大多数时刻只能关注业务逻辑，无法更上一层楼，所以就有了这个试验地，让自己成为一次上帝，建造自己的代码世界~

本项目是基于SpringCloud搭建的，里面使用到的知识，有些会分享在<a href='https://www.cnblogs.com/top-housekeeper/'>我的博客</a>，有任何疑问欢迎`留言和讨论`

如果想使用本项目，请看：https://github.com/NiceJason/SpringCloudDemo/wiki/%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97

>咳咳，如果有收获的话记得给个赞哦！

项目演示地址：目前还没部署到服务器呢

以下编辑更新于：2021/11/23

## :loudspeaker:项目主要用到的技术

**微服务基础框架**：SpringCloud

**服务发现和配置管理**：Nacos

**网关**：SpringCloudGateway

**负载均衡**：Nginx，Ribbon

**远程调用**：Feign

**服务保护**：Sentinel

**数据库框架及数据库**：Mybatis-plus，Druid，MySQL

**容器部署**：Docker

## :golf:项目主要结构

* 服务层次分明
    * 通过Maven层层递进服务，统一在最外层控制包和版本
    * MQ，缓存，公共代码都在各自包中，一目了然
    * 服务间功能职责分明
    * 通过Nacos和依赖间的合理规划，统一在Nacos中加载配置

## :partly_sunny:项目主要已实现的功能

* 微服务的试验
    * Nacos进行统一配置管理
    * Nginx（这个在项目看不出来）的负载均衡，及静态资源部署
    * Ribbon的负载均衡和配置试验（主要是重试机制）
    * 网关的试验，包括对请求的header，body及cookie的增删改试验
    * Feign的试验，包括异常处理和配置试验（主要涉及okhttp）
    * Sentinel的熔断降级试验，包括dashboard的使用
    * Druid的试验，包括其控制面板的操作
    * Docker的试验（没有结合项目）
    * ElasticSearch和kibana的简单试验
    

* RabbitMQ的试验
    * 测试每种交换机
    * 测试死信队列和延迟队列
    * 测试异常处理和手动ack应答
    

* Redis的试验
    * 结合SpringCache，实现会自动刷新的缓存，防止缓存雪崩（具体实现看博客）


* Mybatis-plus试验
    * 自动生成Mapper等代码
    * 常用Mybatis的操作
    
## :rainbow:总结

虽然目前实现的功能很少，主要是在搭建底层框架，但在这过程中对平时项目用到的技术有更深刻的理解，会去搜索别人实际是如何实现的，会去模仿他人的设计模式，会去感受到技术不是十全十美的，有很多取舍...

最大的收获是：当我从NG开始做到数据库，整个微服务流程在脑海里是清晰的，好比是大树有了主干，那么相关的知识和优化也能顺利的开枝散叶了

微服务不是单机系统，每个步骤都要考虑并发和可靠性，麻烦的同时也是一种挑战（当然，这个只是简单的练手项目）
所以积跬步以至千里、积小流以成江河，每一点思考，都是在进步呀~
