<template>
    <Header></Header>

    <!-- 主内容区域 -->
    <main class="container max-w-screen-xl mx-auto p-4">
        <!-- grid 表格布局，分为 4 列 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4 md:col-span-3 mb-3">
                <!-- 归档列表 -->
                <div v-for="(archive, index) in archives" :key="index"
                    class="p-5 mb-4 border border-gray-200 rounded-lg bg-white dark:bg-gray-800 dark:border-gray-700">
                    <time class="text-lg font-semibold text-gray-900 dark:text-white">{{ archive.month }}</time>
                    <ol class="mt-3 divide-y divider-gray-200 dark:divide-gray-700">
                        <li v-for="(article, index2) in archive.articles" :key="index2">
                            <a @click="goArticleDetailPage(article.id)"
                                class="items-center cursor-pointer block p-3 sm:flex hover:bg-gray-100 hover:rounded-lg dark:hover:bg-gray-700">
                                <img class="w-24 h-12 mb-3 mr-3 rounded-lg sm:mb-0" :src="article.cover" />
                                <div class="text-gray-600 dark:text-gray-400">
                                    <h2 class="text-base font-normal text-gray-900 dark:text-white">
                                        {{ article.title }}
                                    </h2>
                                    <span
                                        class="inline-flex items-center text-xs font-normal text-gray-500 dark:text-gray-400">
                                        <svg class="inline w-2.5 h-2.5 mr-2 text-gray-400 dark:text-white"
                                            aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                            viewBox="0 0 20 20">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                stroke-width="2"
                                                d="M5 1v3m5-3v3m5-3v3M1 7h18M5 11h10M2 3h16a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1Z" />
                                        </svg>
                                        {{ article.createDate }}
                                    </span>
                                </div>
                            </a>
                        </li>
                    </ol>
                </div>

                <!-- 分页 -->
                <nav aria-label="Page navigation example" class="mt-10 flex justify-center" v-if="pages > 1">
                    <ul class="flex items-center -space-x-px h-10 text-base">
                        <!-- 上一页 -->
                        <li>
                            <a @click="getArchives(current - 1)"
                                class="flex items-center justify-center px-4 h-10 ml-0 leading-tight text-gray-500 bg-white border border-gray-300 rounded-l-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[current > 1 ? 'cursor-pointer' : 'cursor-not-allowed']">

                                <span class="sr-only">上一页</span>
                                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                    viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M5 1 1 5l4 4" />
                                </svg>
                            </a>
                        </li>
                        <!-- 页码 -->
                        <li v-for="(pageNo, index) in pages" :key="index">
                            <a @click="getArchives(pageNo)"
                                class="cursor-pointer flex items-center justify-center px-4 h-10 leading-tight border  dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[pageNo == current ? 'text-blue-600  bg-blue-50 border-blue-300 hover:bg-blue-100 hover:text-blue-700' : 'text-gray-500 border-gray-300 bg-white hover:bg-gray-100 hover:text-gray-700']">
                                {{ index + 1 }}
                            </a>
                        </li>
                        <!-- 下一页 -->
                        <li>
                            <a @click="getArchives(current + 1)"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-r-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[current < pages ? 'cursor-pointer' : 'cursor-not-allowed']">
                                <span class="sr-only">下一页</span>
                                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                    viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="m1 9 4-4-4-4" />
                                </svg>
                            </a>
                        </li>
                    </ul>
                </nav>

            </div>

            <!-- 右边侧边栏，占用一列 -->
            <aside class="col-span-4 md:col-span-1">
                <div class="sticky">
                    <!-- 博主信息 -->
                    <UserInfoCard></UserInfoCard>

                    <!-- 分类 -->
                    <CategoryListCard></CategoryListCard>

                    <!-- 标签 -->
                    <TagListCard></TagListCard>
                </div>
            </aside>
        </div>

    </main>

    <!-- 返回顶部 -->
    <ScrollToTopButton></ScrollToTopButton>

    <Footer></Footer>
</template>

<script setup>
import Header from '@/layouts/frontend/components/Header.vue'
import Footer from '@/layouts/frontend/components/Footer.vue'
import UserInfoCard from '@/layouts/frontend/components/UserInfoCard.vue'
import TagListCard from '@/layouts/frontend/components/TagListCard.vue'
import CategoryListCard from '@/layouts/frontend/components/CategoryListCard.vue'
import ScrollToTopButton from '@/layouts/frontend/components/ScrollToTopButton.vue'
import { getArchivePageList } from '@/api/frontend/archive'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 文章归档
const archives = ref([])
// 当前页码
const current = ref(1)
// 每页显示的文章数
const size = ref(10)
// 总文章数
const total = ref(0)
// 总共多少页
const pages = ref(0)

function getArchives(currentNo) {
    // 上下页是否能点击判断，当要跳转上一页且页码小于 1 时，则不允许跳转；当要跳转下一页且页码大于总页数时，则不允许跳转
    if (currentNo < 1 || (pages.value > 0 && currentNo > pages.value)) return
    // 调用分页接口渲染数据
    getArchivePageList({ current: currentNo, size: size.value }).then((res) => {
        if (res.success) {
            archives.value = res.data
            current.value = res.current
            size.value = res.size
            total.value = res.total
            pages.value = res.pages
        }
    })

    window.scrollTo({
        top: 0,
        behavior: "smooth", // 可选，使滚动平滑
    });
}
getArchives(current.value)

// 跳转文章详情页
const goArticleDetailPage = (articleId) => {
    router.push('/article/' + articleId)
}
</script>
