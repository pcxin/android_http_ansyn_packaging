android_http_ansyn_packaging
============================

android http ansyn packaging android http

本demo主要对异步请求封装 可用作基本项目框架来使用
网络请求等等小细节已经ok 如有需要请进一步更改
1）封装HttpClient
2）由于用到线程池，可以进行多任务网络请求操作
3）没有网络的时候进行网络状态检查
4）对请求的数据根据URL进行缓存到本地sqlite数据库中（我刚刚想起了 post请求的时候需要对缓存url及本地数据存取做修改才可以正常用 如果不想进行修改可以用get形式）
5）本demo可以直接运行 本来想测试protobuf进行数据处理的 要放在github上就改成json了（entity中有简单的xml解析：Menu.java 其他的都是json的 json 也可用Gson更方便）

The demo is mainly used as a basic package for asynchronous requests to use the framework of the project
Network requests, and so little detail has been ok if necessary further changes
1) Package HttpClient
2) Due to use thread pool, you can multi-task operating a network request
3) when the network is no network status check
4) According to the data of the requested URL is cached to the local sqlite database (I just think it needs to post requests to the cache url and local data access to make changes before they can be modified if you do not normally use can get the form)
5) The demo can be run directly protobuf had wanted to test the data processing to be placed on github into json on the (entity has simple xml parsing: Menu.java others are also available Gson json json's more convenient)