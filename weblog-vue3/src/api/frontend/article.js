import axios from "@/axios";

// 获取文章列表
export function getArticlePageList(data) {
    return axios.post("/article/list", data)
}

// 获取文章详情
export function getArticleDetail(articleId) {
    return axios.post("/article/detail", {articleId})
}


