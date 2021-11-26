import axios from "axios";
import Element from 'element-ui';

//axios.defaults.baseURL = "/http://127.0.0.1:8081";

const request = axios.create({
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    }
})

request.interceptors.request.use(config => {
    // 请求头带上token
    config.headers['Authorization'] = localStorage.getItem("token");
    return config;
})

request.interceptors.response.use(response => {
    let res = response.data;
    console.log(res);
    if (res.code === 200) {
        return response;
    } else {
        Element.Message.error(!res.message ? '系统异常' : res.message)
        return Promise.reject(response.data.message)
    }
},
    error => {
        if (error.response.data) {
            error.message = error.response.data.message
        }
        ;
        if (error.response.status === 401) {
            router.push("/login")
        }
        ;
        Element.Message.error(error.message, {
            duration: 3 * 1000
        });
        return Promise.reject(error);
    }
)
export default request