# api-yaml-db-demo
该项目主要实现接口测试从yaml文件和数据库读取测试用例。现在只实现从yaml读取测试用例，后面会实现从数据库读取测试用例。

下面讲述项目主要文件和类：
/src/test/resources/apiTestProject.yaml 
>配置测试用例文件

/src/test/java/api/APITestCaseFromYaml.java
>runTestCase方法运行所有测试用例，如果途中某个测试用例失败，会导致后面用例不执行，降低执行测试效率。

为了解决这个问题，使用```testNG.run()```方法能使每个测试用例单独运行。如果途中某个测试用例失败，后面用例还会执行。具体实现请看/src/test/java/api/APITestRun.java
