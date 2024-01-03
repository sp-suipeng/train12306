import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//全局引入ant-design  这样自动引入了样式，如果没有引入，手动引入
import Antd from 'ant-design-vue'
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
    return config;
}, error => {
    return Promise.reject(error);
})

axios.interceptors.response.use(function (response) {
    console.log("返回结果", response);
    return response;
}, error => {
    console.log("返回错误", error);
    return Promise.reject(error);
});