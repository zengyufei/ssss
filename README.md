# 基于Solon开发的脚手架

#### 进度 10%

## 功能:
    1. 路由 / 根目录到 /department/view/list 页面
    2. 解决跨域问题
    3. 自动清除 url 入参,json入参 左右空格
    4. 自动转换 json入参空字符串为null
    5. 拦截器拦截 @Mapping (所有请求方法) 简单打印 Args 参数和 Return 返回值
    6. /upload 开头的 uri, 自动设置 multipart 协议
    7. 解决无法继承父类 @Mapping 方法问题 (作者 1.10.10 正试版已解决)
    8. 解决无法继承接口 @Mapping 方法问题 (作者 1.10.10 正试版已解决)

    9. 使用 mybatis-plus, 开启分页拦截器,关闭缓存
    10. 完全自定义化的 基类mapper(删除部分原生方法,新增自己的方法), 查看 com.xunmo.common.base.XmMapper 获取详情
    11. 优化mybatis日志输出
    12. mp自动填充功能
    13. 使用 pageHelper 做主动分页,前端传分页参数才会分页不传则不分页,查全部

    14. 方法执行时间计时 Filter 过滤器
    15. 统一异常处理 和 Controller统一渲染 互相冲突,自行选择
    16. 可以通过 EventBus 打印日志 或者直接 log.info 打印日志,自行选择
    17. 采用 AjaxJson 作为返回值包裹层
    18. 提供支持 SFunction 的 XmMap(继承HashMap),可以使用 Dict::getCode 作为key
    19. 提供 XmUtil,包含 lambda 封装简便变形方法, Javers属性变化对账,从 Context 设置获取属性值,中文数字排序, LambdaQueryWrapper动态排序字段设置,获取一个 SFunction等
    20. 提供复制模块并重命名 CopyAndRenameTest 类,可以快速拷贝Dict文件夹变成其他文件夹
   
    21. controller / service / serviceImpl /Mapper / Entity 全都有个性化继承类
    22. 采用 DTO, DO, Entity 模式, Entity数据交互使用, DO和DTO应对参数接参
    23. 全体 POST 请求, 并且每种业务一个定制DTO
    24. 使用 IController 接口模式,请求信息全部在接口上,不喜欢可以更换
    25. 自带 view 视图, 用于访问测试交互,分别是  /department/view/list 和  /dict/view/list
    
    26. 通用类介绍 XmController 主要想使用泛型 Service,少写两行注入代码,  可以与 XmSimpleController参照区别
    27. XmSimpleController 最简单 Controller 基类

    28. XmSimpleMoveController 自带4个移动排序方法, 任意类(不局限字典表)继承后自动拥有,分别是
        /upTop 置顶
        /upMove 上移
        /downMove 下移
        /changeSort 交换 (目前已知问题: 不可以与上级或下级交换,不报错,但是树结构会丢失数据)

    29. XmRelateCodeMapper 任意类(不局限部门表,但是要物理创建一张同名以_relate结尾的表)继承后自动拥有以下接口,分别是
        部门存在则返回否则报错
        根据下级code获取上级部门对象
        指定区间获取部门（不包括beginCode和endCode）
        指定区间获取部门（包括beginCode和endCode）
        获取直接下级部门
        获取所有上级部门（不包括自己）
        获取所有上级部门（包括自己）
        获取所有下级部门（不包括自己）
        获取所有下级部门（包括自己）
        删除部门 > 删除所有关联此部门子节点的闭包关系
        获取多个部门的下级部门共集（不包括自己）
        获取多个部门的下级部门共集（包括自己）
    
    30. XmSimpleMoveController 和 XmRelateCodeMapper 继承后, 
            根据业务复杂度,可能判断唯一性的因子(条件)很多个字段, 
            可以添加 @SortUni 注解解决唯一性问题, 
            可以参考 dict 和 department,都有使用该注解
            添加注解后, 在 sql 查询时会自动拼接该字段对应的数据库表名称


### 部门管理
[部门管理](https://github.com/zengyufei/ssss/blob/main/images/dept.png)


### 字典管理
[字典管理](https://github.com/zengyufei/ssss/blob/main/images/dict.png)