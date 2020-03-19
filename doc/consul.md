## Consul简介

Consul是一套开源的分布式服务发现和配置管理系统，由HashiCorp公司用Go语言开发。

提供了微服务系统中的服务治理、配置中心、控制总线等功能。这些功能中的每一个都可以根据需要单独使用，也可以一起使用以构建全方位的服务网络，总之Consul提供了一种完整的服务网格解决方案。


## Consul功能

* 服务发现：提供 HTTP 和 DNS 两种发现方式
* 健康监测：支持多种方式，HTTP、TCP、Docker、Shell脚本定制化
* KV存储：Key、Value的存储方式
* 多数据中心：Consul支持多数据中心
* 可视化界面


## Consul使用

```text
consul agent -dev

监听8500端口
```

## Eureka、Zookeeper、Consul的异同点

```text
1.Eureka: AP
2.Consul: CP
3.Zookeeper: CP
```
[参考](https://blog.csdn.net/qq_41211642/article/details/104836741)