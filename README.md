# api-yaml-db-demo
该项目主要实现接口测试从yaml文件和数据库读取测试用例。现在只实现从yaml读取测试用例，后面会实现从数据库读取测试用例。

下面讲述在yaml文件配置用例需要用到文件和类：
/src/test/resources/apiTestProject.yaml 
>配置测试用例文件

/src/test/java/api/APITestCaseFromYaml.java
>runTestCase方法运行所有测试用例，如果途中某个测试用例失败，会导致后面用例不执行，降低执行测试效率。

为了解决这个问题，使用```testNG.run()```方法能使每个测试用例单独运行。如果途中某个测试用例失败，后面用例还会执行。具体实现请看/src/test/java/api/APITestRun.java

如果在文件写很多测试用例，看起来会眼花缭乱，不利用维护。如果某个地方配置错误导致读取整个yaml文件失败。在接口测试常用到接口间数据依赖，每个测试用例需要有唯一特征值，在yaml文件写测试用例要手工维护特征值，往往容易造成错误。使用数据库存储测试用例能解决上述所述问题。






表设计如下：

| 名称            | 类型           | 说明                                       |
| ------------- | ------------ | ---------------------------------------- |
| id            | bigint(20)   | 测试用例id，自增                                |
| module_id     | bigint(20)   | 模块id                                     |
| api_path      | varchar(64)  | 接口路径                                     |
| method        | varchar(8)   | 请求类型，post、get、put、delete                 |
| request_data  | varchar(256) | 请求数据                                     |
| expect_result | varchar(256) | 预期结果，需要验证内容                              |
| final_result  | varchar(256) | 实际结果                                     |
| result_status | varchar(16)  | 结果状态，pass，failure                        |
| depend_id     | varchar(64)  | 依赖测试用例id，一个接口会依赖多个接口数据，测试用例id使用逗号分隔      |
| depend_data   | varchar(256) | 依赖数据。格式request.data_key=depend_id.response.data_key，如果有多个依赖数据使用分号分隔 |
| status        | varchar(1)   | 用例状态，1-启用，0-禁用                           |
| remarks       | varchar(258) | 用例说明                                     |
| create_time   | bigint(20)   | 创建时间戳                                    |
| update_time   | bigin(20)    | 更新时间戳                                    |




