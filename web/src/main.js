import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//全局引入ant-design  这样自动引入了样式，如果没有引入，手动引入
import Antd, {notification} from 'ant-design-vue'
//全局引入ant-design图标
import * as Icons from '@ant-design/icons-vue';
import axios from 'axios'

const app = createApp(App)
app.use(Antd)
.use(store)
.use(router)
.mount('#app')

//注册图标
const icons = Icons;
for (const i in icons) {
    //定义组件
    app.component(i, icons[i]);
}

//add interceptor
axios.interceptors.request.use(function (config) {

    console.log('请求参数', config);
    const token = store.state.token;
    if(token) {
        config.headers.token = token;
        console.log("请求headers增加token:", token);
    }
    return config;
}, error => {
    return Promise.reject(error);
})

axios.interceptors.response.use(function (response) {
    console.log("返回结果", response);
    return response;
}, error => {
    console.log("返回错误", error);
    const response = error.response;
    const status = response.status;
    if(status === 401) {
        //判断状态码是401 跳转到登陆页面
        console.log("未登录或登录超时，跳转到登录页");
        store.commit("setMember", {});
        notification.error({description:"未登录或登录超时"})
        router.push("/login")
    }
    return Promise.reject(error);
});



axios.defaults.baseURL = process.env.VUE_APP_SERVER
console.log("环境：", process.env.NODE_ENV)
console.log("服务端：", process.env.VUE_APP_SERVER)