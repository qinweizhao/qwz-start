// 引入 mockjs
const Mock = require('mockjs')

// 获取 mock.Random 对象
//参考：https://github.com/nuysoft/Mock/wiki/Mock.Random

const Random = Mock.Random
let Result = {
    code: 200,
    msg: '操作成功',
    data: null
}

// 验证码
Mock.mock('/captcha', 'get', () => {
    Result.data = {
        // 获取一个 32 位的随机字符串
        token: Random.string(32),
        // 生成验证码
        captchaImg: Random.dataImage("120x40", "111111")
    }
    return Result
})

// 登录
Mock.mock(RegExp('/login*'), 'post', (config) => {
    // 这里无法在 header 添加 authorization，直接跳过
    console.log("mock-------login"+config)
    return Result
})