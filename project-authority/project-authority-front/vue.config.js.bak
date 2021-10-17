let proxyObj = {};
proxyObj['/ws'] = {
    ws: false,
    target: "ws://localhost:8080"
};
proxyObj['/'] = {
    ws: false,
    target: "http://localhost:8080",
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
};
module.exports = {
    devServer: {
        host: 'localhost',
        port: 8088,
        proxy: proxyObj
    }
}