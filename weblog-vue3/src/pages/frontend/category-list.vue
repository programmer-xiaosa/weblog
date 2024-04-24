<template>
  <Header></Header>

  <!-- 主内容区域 -->
  <main class="container max-w-screen-xl mx-auto p-4">
    <!-- grid 表格布局，分为 4 列 -->
    <div class="grid grid-cols-4 gap-7">
      <!-- 左边栏，占用 3 列 -->
      <div class="col-span-4 md:col-span-3 mb-3">
        <!-- 分类列表 -->
        <!-- <CategoryListCard></CategoryListCard> -->

        <div
          class="w-full p-5 pb-7 mb-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700"
        >
          <!-- 分类标题 -->
          <h2
            class="flex items-center mb-5 font-bold text-gray-900 uppercase dark:text-white"
          >
            <!-- 文件夹图标 -->
            <svg
              t="1698998570037"
              class="inline icon w-5 h-5 mr-2"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="21572"
              width="200"
              height="200"
            >
              <path
                d="M938.666667 464.592593h-853.333334v-265.481482c0-62.577778 51.2-113.777778 113.777778-113.777778h128.948148c15.17037 0 28.444444 3.792593 41.718519 11.377778l98.607407 64.474074h356.503704c62.577778 0 113.777778 51.2 113.777778 113.777778v189.62963z"
                fill="#3A69DD"
                p-id="21573"
              ></path>
              <path
                d="M805.925926 398.222222h-587.851852v-125.155555c0-24.651852 20.859259-45.511111 45.511111-45.511111h496.82963c24.651852 0 45.511111 20.859259 45.511111 45.511111V398.222222z"
                fill="#D9E3FF"
                p-id="21574"
              ></path>
              <path
                d="M843.851852 417.185185h-663.703704v-98.607407c0-28.444444 22.755556-53.096296 53.096296-53.096297h559.407408c28.444444 0 53.096296 22.755556 53.096296 53.096297V417.185185z"
                fill="#FFFFFF"
                p-id="21575"
              ></path>
              <path
                d="M786.962963 938.666667h-549.925926c-83.437037 0-151.703704-68.266667-151.703704-151.703704V341.333333s316.681481 37.925926 430.45926 37.925926c189.62963 0 422.874074-37.925926 422.874074-37.925926v445.62963c0 83.437037-68.266667 151.703704-151.703704 151.703704z"
                fill="#5F7CF9"
                p-id="21576"
              ></path>
              <path
                d="M559.407407 512h-75.851851c-20.859259 0-37.925926-17.066667-37.925926-37.925926s17.066667-37.925926 37.925926-37.925926h75.851851c20.859259 0 37.925926 17.066667 37.925926 37.925926s-17.066667 37.925926-37.925926 37.925926z"
                fill="#F9D523"
                p-id="21577"
              ></path>
            </svg>
            分类
            <span
              v-if="categories && categories.length > 0"
              class="ml-2 text-gray-600 font-normal dark:text-gray-300"
              >( {{ categories.length }} )</span
            >
          </h2>

          <!-- 分类列表 -->
          <div
            class="text-sm flex flex-wrap gap-3 font-medium text-gray-600 rounded-lg dark:border-gray-600 dark:text-white"
          >
            <a
              @click="goCategoryArticleListPage(category.id, category.name)"
              v-for="(category, index) in categories"
              :key="index"
              class="cursor-pointer inline-flex items-center px-4 py-2 text-sm font-medium text-center border rounded-lg hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-300 dark:bg-gray-800 dark:text-gray-300 dark:hover:bg-gray-700 dark:focus:ring-gray-800 dark:border-gray-700 dark:hover:text-white"
            >
              {{ category.name }}
              <span
                class="inline-flex items-center justify-center w-4 h-4 ms-2 text-xs font-semibold text-sky-800 bg-sky-200 rounded-full"
              >
                {{ category.articlesTotal }}
              </span>
            </a>
          </div>
        </div>
      </div>

      <!-- 右边侧边栏，占用一列 -->
      <aside class="col-span-4 md:col-span-1">
        <div class="sticky">
          <!-- 博主信息 -->
          <UserInfoCard></UserInfoCard>

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
import Header from "@/layouts/frontend/components/Header.vue";
import Footer from "@/layouts/frontend/components/Footer.vue";
import UserInfoCard from "@/layouts/frontend/components/UserInfoCard.vue";
import TagListCard from "@/layouts/frontend/components/TagListCard.vue";
import CategoryListCard from "@/layouts/frontend/components/CategoryListCard.vue";
import ScrollToTopButton from "@/layouts/frontend/components/ScrollToTopButton.vue";

import { getCategoryList } from "@/api/frontend/category";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

// 跳转分类文章列表页
const goCategoryArticleListPage = (id, name) => {
  // 跳转时通过 query 携带参数（分类 ID、分类名称）
  router.push({ path: "/category/article/list", query: { id, name } });
};

// 所有分类
const categories = ref([]);
getCategoryList({}).then((res) => {
  if (res.success) {
    categories.value = res.data;
  }
});
</script>
