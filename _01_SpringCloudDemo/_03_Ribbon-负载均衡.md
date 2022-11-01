## Ribbon

[负债均衡的原理-Ribbon]   
    [3-1 自定义负载规则]
        [3-1-1 注入IRule的实现类(Bean)来选择负债均衡规则](order-service/src/main/java/com/hui/config/RibbonConfig.java)
        [3-1-2 配置文件中对指定微服务配置单独的负责均衡规则](order-service/src/main/resources/application.yml)
        
    1. 选择负载均衡规则主要有两种方式:
        方式1:
            由IRule的实现类(ctrl+h可查看实现类)来确定
            可通过注入IRule的Bean(new IRule的实现类) 或 自定义IRule的实现类来自定义负债均衡规则
        方式2:
            在服务消费者的yml中指定服务名(服务名必须小写)配置单独的负载均衡规则
            如: userservice:
                   ribbon:
                     NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
                     
[Ribbon使用饥饿加载策略]
    [3-2 Ribbon开启饥饿加载](order-service/src/main/resources/application.yml)
        
    1. Ribbon的饥饿加载
        Ribbon默认采用懒加载, 也就是第一次访问是才会去创建LoadBalanceClient, 因此第一次请求时间会很长
        饥饿加载策略则会在项目启动时创建, 降低第一次访问的耗时, 可在yml中配置如下内容开启:
            ribbon:
              eager-load:
                enabled: true         # 开启饥饿加载
                clients:
                 - userservice  # 指定对userservice服务进行饥饿加载
    
        
[Ribbon知识点总结]
    
    1. Ribbon负载均衡guize
        规则接口是IRule
        默认实现是ZoneAviodanceRule, 根据zone选择服务列表, 然后轮询
    2. 负载均衡自定义方式
        代码方式: 一次性对所有服务配置负载均衡策略, 但修改是需要改代码打包
        配置方式: 无需打包发布, 当时无法做全局配置
    3. 饥饿加载
        开启饥饿加载
        指定饥饿加载的微服务名称(配置项是一个列表)
            