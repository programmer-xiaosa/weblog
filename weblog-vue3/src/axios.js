import axios from "axios";
import { getToken } from "@/composables/cookie"
import { showMessage} from '@/composables/util'
import { useUserStore } from '@/stores/user'

// 创建 Axios 实例
const instance = axios.create({
    baseURL: "/api", // 你的 API 基础 URL
    timeout: 7000, // 请求超时时间
})


// 添加请求拦截器
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    const token = getToken()
    console.log('统一添加请求头中的 Token:' + token)

    // 当 token 不为空时
    if (token) {
        // 添加请求头, key 为 Authorization，value 值的前缀为 'Bearer '
        config.headers['Authorization'] = 'Bearer ' + token
    }

    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
});

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    return response.data
}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    let status = error.response.status

    // 状态码 401
    if (status == 401) {
        // 退出登录
        let userStore = useUserStore()
        userStore.logout()
        // 刷新页面
        location.reload()
    }

    // 若后台有错误提示就用提示文字，默认提示为 '请求失败'
    let errorMsg = error.response.data.message || '请求失败'
    // 弹错误提示
    showMessage(errorMsg, 'error')

    return Promise.reject(error)
})

// 暴露出去
export default instance;