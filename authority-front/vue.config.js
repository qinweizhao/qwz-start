let proxyObj = {};
proxyObj['/ws'] = {
    ws: false,
    target: "ws://localhost:8081"
};
proxyObj['/'] = {
    ws: false,
    target: "http://localhost:8081",
    changeOrigin: true,
    pathRewrite: {
        '^/': '/'
    }
};
module.exports = {
    publicPath:'/',
    devServer: {
        host: '127.0.0.1',
        port: 8088,
        proxy: proxyObj
    }
}