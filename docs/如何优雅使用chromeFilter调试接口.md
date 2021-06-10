# 如何优雅使用chromeFilter调试接口



## 问题

> 用chrome调试模式查看功能/按钮的后端请求，因为心跳ping、网速检测speed、日志logs等轮询请求频繁刷屏导致无法快速定位目标URL。



## 解决方法

**排除指定url**

> 在filter栏输入“ **-url:ping -url:speed -url:heart -url:logs**”排除轮询请求。

### 排除options

> -method:OPTIONS

### 排除图片

> -mime-type:image/png



## 收益

- 避免人眼搜索，高效调试。
- 消灭误判链路的case。