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
