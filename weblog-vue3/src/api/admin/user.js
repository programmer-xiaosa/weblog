import axios from "@/axios";

// 登录接口
export function login(username, password) {
    return axios.post("/login", {username, password})
}

// 获取登录用户信息
export function getUserInfo() {
    return axios.post("/admin/user/info")
}

// 修改用户密码
export function updateAdminPassword(data) {
    return axios.post("/admin/password/update", data)
}