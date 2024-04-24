<template>
  <Header></Header>

  <!-- 主内容区域 -->
  <main class="container max-w-screen-xl mx-auto px-4 md:px-6 py-4">
    <!-- grid 表格布局，分为 12 列 -->
    <div class="grid grid-cols-12 gap-7">
      <!-- 左边栏 -->
      <div class="col-span-12 md:col-span-8 lg:col-span-9 mb-3">
        <!-- grid 表格布局，分为 12 列 -->
        <div class="grid grid-cols-12 gap-7">
          <div v-if="wikis && wikis.length > 0" v-for="(wiki, index) in wikis" :key="index"
            class="col-span-12 md:col-span-6 lg:col-span-4 animate__animated animate__fadeInUp">
            <div
              class="relative bg-white h-full border border-gray-200 rounded-lg hover:scale-[1.03] dark:bg-gray-800 dark:border-gray-700 transition-all">
              <!-- 知识库封面 -->
              <a @click="goWikiArticleDetailPage(wiki.id, wiki.firstArticleId)" class="cursor-pointer">
                <img class="rounded-t-lg h-36 w-full object-cover" :src="wiki.cover" />
              </a>
              <div class="p-3">
                <!-- 知识库标题 -->
                <a @click="goWikiArticleDetailPage(wiki.id, wiki.firstArticleId)" class="cursor-pointer">
                  <h2 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
                    <span>{{ wiki.title }}</span>
                  </h2>
                </a>
                <!-- 知识库摘要 -->
                <p class="font-normal text-gray-500 dark:text-gray-400">
                  {{ wiki.summary }}
                </p>
              </div>

              <!-- 是否置顶 -->
              <div v-if="wiki.isTop"
                class="absolute inline-flex items-center justify-center w-14 h-7 text-xs font-bold text-white bg-red-500 rounded-full -top-2 -end-2">
                置顶
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 右边侧边栏 -->
      <aside class="col-span-12 md:col-span-4 lg:col-span-3">
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
import Header from "@/layouts/frontend/components/Header.vue";
import Footer from "@/layouts/frontend/components/Footer.vue";
import UserInfoCard from "@/layouts/frontend/components/UserInfoCard.vue";
import TagListCard from "@/layouts/frontend/components/TagListCard.vue";
import CategoryListCard from "@/layouts/frontend/components/CategoryListCard.vue";
import ScrollToTopButton from "@/layouts/frontend/components/ScrollToTopButton.vue";
import { getWikiList } from "@/api/frontend/wiki";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

// 知识库
const wikis = ref([]);
getWikiList().then((res) => {
  if (res.success) {
    wikis.value = res.data;
  }
});

// 跳转文章详情页
const goWikiArticleDetailPage = (wikiId, articleId) => {
  console.log("跳转" + wikiId + "," + articleId);
  router.push({ path: "/wiki/" + wikiId, query: { articleId } });
};
</script>
