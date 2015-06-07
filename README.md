# DigitalMall
软件架构设计教学示例---数码城

###核心功能
（1）	注册
①	为了防止机器人注册，有图片验证码功能
②	验证手机号有效，发送短信验证码到手机
③	验证邮箱有效，发送用户激活邮件（激活有效期1天）
（2）	购物车
采用数据库实现购物车的商品添加，删除，修改（购买商品的数量，商品的销售属性等），查看等功能。
（3）	3.2下订单
显示订单信息，修改收货地址，进行订单确认。确认无误，将订单写入数据库。
（4）	网上支付
通过第三方支付接口（易宝支付接口）进行网上支付

#### 管理
* maven依赖和项目管理
* git版本控制

#### 后端
* IoC容器 spring
* web框架 springmvc
* orm框架 hibernate 
* 验证框架 hibernate validator
* 数据源 druid
* 日志 slf4j+log4j
* Json fastjson
* servlet 3.0(需要支持servlet3的servlet容器，如tomcat7)
* jcaptcha 验证码
* velocity 模板视图

#### 前端
* jquery js框架
* jquery-ui-bootstrap界面框架
* font-wesome 字体/图标框架
* jquery Validation Engine 验证框架（配合spring的验证框架，页面验证简化不少）
* 百度jello 基于组件的velocity开发，页面（js,css)压缩等

#### 数据库
 * 目前只支持mysql，建议mysql5.5及以上

###支持的浏览器
 * chrome
 * ie8及以上（建议ie9以上，获取更好的体验）
 * 其他浏览器暂时未测试
