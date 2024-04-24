import axios from "@/axios";

// 获取文章分页数据
export function getArticlePageList(data) {
    return axios.post("/admin/article/list", data)
}

// 删除文章
export function deleteArticle(id) {
    return axios.post("/admin/article/delete", {id})
}

// 发布文章
export function publishArticle(data) {
    return axios.post("/admin/article/publish", data)
}

// 获取文章详情
export function getArticleDetail(id) {
    return axios.post("/admin/article/detail", {id})
}

// 更新文章
export function updateArticle(data) {
    return axios.post("/admin/article/update", data)
}

// 更新文章置顶状态
export function updateArticleIsTop(data) {
    return axios.post("/admin/article/isTop/update", data)
}