## authority-back（后端源码）

### 工程结构
``` 
authority-back
├── common -- 公共部分
├    ├── controller -- 公共 Controller
├    ├── entity -- 公共实体
├    ├── enums -- 公共枚举
├    ├── exception -- 异常和异常处理
├    ├── security -- SpringSecurity 的相关支持
├    ├── util -- 工具类
├── config -- 业务模块
├    ├── KaptchaConfig -- 验证码配置 
├    ├── MyBatisPlusConfig -- MyBatisPlus 配置
├    ├── SecurityConfig -- SpringSecurity 配置
├    └── WebMvcConfig -- Mvc 配置 
├── modules -- 业务模块api封装
├    ├── gen -- 代码生成
└──  ├── sys -- 系统功能 
```