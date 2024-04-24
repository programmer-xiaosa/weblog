import axios from "@/axios";

// 获取文章分页数据
export function getWikiPageList(data) {
    return axios.post("/admin/wiki/list", data)
}

// 新增知识库
export function addWiki(data) {
    return axios.post("/admin/wiki/add", data)
}

// 更新知识库置顶状态
export function updateWikiIsTop(data) {
    return axios.post("/admin/wiki/isTop/update", data)
}

// 更新知识库发布状态
export function updateWikiIsPublish(data) {
    return axios.post("/admin/wiki/isPublish/update", data)
}

// 删除知识库
export function deleteWiki(id) {
    return axios.post("/admin/wiki/delete", { id })
}

// 更新知识库
export function updateWiki(data) {
    return axios.post("/admin/wiki/update", data)
}

// 获取知识库目录
export function getWikiCatalogs(id) {
    return axios.post("/admin/wiki/catalog/list", {id})
}

// 更新知识库目录
export function updateWikiCatalogs(data) {
    return axios.post("/admin/wiki/catalog/update", data)
}