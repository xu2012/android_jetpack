## question:
### 1.loadstate怎么和StateView结合，怎么区分是那个请求的错误信息？
### 2.如果同时有多个接口发起请求，怎么处理？


## 解决方案：
    1.在请求的时候传入一个tag标识，然后传给loadstate状态，根据状态中的tag去判断
    2.协程中有并发处理