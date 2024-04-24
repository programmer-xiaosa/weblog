<template>
  <div
    class="w-full py-5 px-2 mb-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700"
  >
    <div class="flex flex-col items-center">
      <!-- 博主头像 -->
      <div class="relative mb-4">
        <img
          class="w-14 h-14 rounded-full shadow"
          :src="blogSettingsStore.blogSettings.avatar"
        />
        <span
          class="bottom-0 left-10 absolute w-3.5 h-3.5 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"
        ></span>
      </div>
      <!-- 博主昵称 -->
      <h5 class="mb-2 text-xl font-medium text-gray-900 dark:text-white">
        {{ blogSettingsStore.blogSettings.author }}
      </h5>
      <!-- 介绍语 -->
      <span
        class="mb-5 text-sm text-gray-500 dark:text-gray-400 pl-3 pr-3"
        data-tooltip-target="introduction-tooltip-bottom"
        data-tooltip-placement="bottom"
        >{{ blogSettingsStore.blogSettings.introduction }}</span
      >

      <div
        id="introduction-tooltip-bottom"
        role="tooltip"
        class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700"
      >
        介绍语
        <div class="tooltip-arrow" data-popper-arrow></div>
      </div>

      <!-- 文章数量、分类数量、标签数量、总访问量 -->
      <div class="flex justify-center gap-5 mb-2 dark:text-gray-400">
        <div
          @click="router.push('/archive/list')"
          class="flex items-center flex-col gap-1 hover:text-sky-600 hover:scale-110 cursor-pointer transition-all"
        >
          <CountTo
            :value="statisticsInfo.articleTotalCount"
            customClass="text-lg font-bold"
          ></CountTo>
          <div class="text-sm">文章</div>
        </div>
        <div
          @click="router.push('/category/list')"
          class="flex items-center flex-col gap-1 hover:text-sky-600 hover:scale-110 cursor-pointer transition-all"
        >
          <CountTo
            :value="statisticsInfo.categoryTotalCount"
            customClass="text-lg font-bold"
          ></CountTo>
          <div class="text-sm">分类</div>
        </div>
        <div
          @click="router.push('/tag/list')"
          class="flex items-center flex-col gap-1 hover:text-sky-600 hover:scale-110 cursor-pointer transition-all"
        >
          <CountTo
            :value="statisticsInfo.tagTotalCount"
            customClass="text-lg font-bold"
          ></CountTo>
          <div class="text-sm">标签</div>
        </div>
        <div
          class="flex items-center flex-col gap-1 hover:text-sky-600 hover:scale-110 cursor-pointer transition-all"
        >
          <CountTo
            :value="statisticsInfo.pvTotalCount"
            customClass="text-lg font-bold"
          ></CountTo>
          <div class="text-sm">总访问量</div>
        </div>
      </div>

      <!-- 第三方平台主页跳转（如 GitHub 等） -->
      <div class="flex justify-center gap-2">
        <!-- GitHub -->
        <svg
          @click="jump(blogSettingsStore.blogSettings.githubHomepage)"
          v-if="blogSettingsStore.blogSettings.githubHomepage"
          t="1698029949662"
          data-tooltip-target="github-tooltip-bottom"
          data-tooltip-placement="bottom"
          class="hover:scale-110 cursor-pointer icon mt-5 w-7 h-7 transition-all"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="1447"
          width="200"
          height="200"
        >
          <path
            d="M512 512m-512 0a512 512 0 1 0 1024 0 512 512 0 1 0-1024 0Z"
            fill="#4186F5"
            p-id="1448"
          ></path>
          <path
            d="M611.944 302.056c0-15.701 2.75-30.802 7.816-44.917a384.238 384.238 0 0 0-186.11 2.956c-74.501-50.063-93.407-71.902-107.975-39.618a136.243 136.243 0 0 0-3.961 102.287 149.515 149.515 0 0 0-39.949 104.806c0 148.743 92.139 181.875 179.961 191.61a83.898 83.898 0 0 0-25.192 51.863c-40.708 22.518-91.94 8.261-115.181-32.058a83.117 83.117 0 0 0-60.466-39.98s-38.871-0.361-2.879 23.408a102.97 102.97 0 0 1 43.912 56.906s23.398 75.279 133.531 51.863v65.913c0 10.443 13.548 42.63 102.328 42.63 71.275 0 94.913-30.385 94.913-42.987V690.485a90.052 90.052 0 0 0-26.996-72.03c83.996-9.381 173.328-40.204 179.6-176.098a164.706 164.706 0 0 1-21.129 1.365c-84.07 0-152.223-63.426-152.223-141.666z"
            fill="#FFFFFF"
            p-id="1449"
          ></path>
          <path
            d="M743.554 322.765a136.267 136.267 0 0 0-3.961-102.289s-32.396-10.445-107.979 39.618a385.536 385.536 0 0 0-11.853-2.956 132.623 132.623 0 0 0-7.816 44.917c0 78.24 68.152 141.667 152.222 141.667 7.171 0 14.222-0.472 21.129-1.365 0.231-5.03 0.363-10.187 0.363-15.509a149.534 149.534 0 0 0-42.105-104.083z"
            fill="#FFFFFF"
            opacity=".4"
            p-id="1450"
          ></path>
        </svg>
        <div
          id="github-tooltip-bottom"
          role="tooltip"
          class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
        >
          GitHub
          <div class="tooltip-arrow" data-popper-arrow></div>
        </div>
        <!-- Gitee -->
        <svg
          @click="jump(blogSettingsStore.blogSettings.giteeHomepage)"
          v-if="blogSettingsStore.blogSettings.giteeHomepage"
          t="1698030969736"
          data-tooltip-target="gitee-tooltip-bottom"
          data-tooltip-placement="bottom"
          class="hover:scale-110 cursor-pointer icon mt-5 w-7 h-7 transition-all"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="2438"
          width="200"
          height="200"
        >
          <path
            d="M512 1024C229.222 1024 0 794.778 0 512S229.222 0 512 0s512 229.222 512 512-229.222 512-512 512z m259.149-568.883h-290.74a25.293 25.293 0 0 0-25.292 25.293l-0.026 63.206c0 13.952 11.315 25.293 25.267 25.293h177.024c13.978 0 25.293 11.315 25.293 25.267v12.646a75.853 75.853 0 0 1-75.853 75.853h-240.23a25.293 25.293 0 0 1-25.267-25.293V417.203a75.853 75.853 0 0 1 75.827-75.853h353.946a25.293 25.293 0 0 0 25.267-25.292l0.077-63.207a25.293 25.293 0 0 0-25.268-25.293H417.152a189.62 189.62 0 0 0-189.62 189.645V771.15c0 13.977 11.316 25.293 25.294 25.293h372.94a170.65 170.65 0 0 0 170.65-170.65V480.384a25.293 25.293 0 0 0-25.293-25.267z"
            fill="#C71D23"
            p-id="2439"
          ></path>
        </svg>
        <div
          id="gitee-tooltip-bottom"
          role="tooltip"
          class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
        >
          Gitee
          <div class="tooltip-arrow" data-popper-arrow></div>
        </div>
        <!-- 个人博客 -->
        <svg
          @click="jump(blogSettingsStore.blogSettings.zhihuHomepage)"
          v-if="blogSettingsStore.blogSettings.zhihuHomepage"
          t="1713285809640"
          data-tooltip-target="blog-tooltip-bottom"
          data-tooltip-placement="bottom"
          class="icon hover:scale-110 cursor-pointer icon mt-5 w-7 h-7 transition-all"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="1077"
          width="200"
          height="200"
        >
          <path
            d="M512 512m-497.58814815 0a497.58814815 497.58814815 0 1 0 995.1762963 0 497.58814815 497.58814815 0 1 0-995.1762963 0Z"
            fill="#1296db"
            p-id="1078"
          ></path>
          <path
            d="M344.31279408 786.43806815V237.56193185h177.21419851c62.42910815 0 109.25093925 10.92266667 140.47762963 32.7558637 31.21455408 21.83319703 46.82183111 54.88033185 46.82183111 99.10499556 0 13.72615111-1.61412741 27.08821333-4.83024592 40.11045926-3.21611852 13.02224592-8.192 24.98863408-14.90337186 35.91130074-6.72350815 10.92266667-15.12182518 20.58315852-25.1949511 28.98147556-10.07312592 8.39831703-21.97883259 14.97618963-35.69284742 19.73361778 13.71401482 2.24521482 26.80907852 6.65069037 39.26091852 13.22856296a116.69777067 116.69777067 0 0 1 32.7558637 25.61972148c9.38135703 10.4978963 16.79663408 22.88905482 22.25796742 37.16133926 5.46133333 14.27228445 8.192 30.23151408 8.192 47.87768888 0 55.99687111-18.76271408 98.06127408-56.27600592 126.19320889-37.52542815 28.13193482-91.68971852 42.21003852-162.51714372 42.21003853H344.31279408zM601.32314075 387.4816c0-10.35226075-1.67480889-19.73361778-5.03656297-28.13193482-3.36175408-8.39831703-8.75026963-15.53445925-16.16554667-21.42056296-7.41527703-5.87396741-16.94226963-10.42507852-28.55670519-13.65333333-11.62657185-3.21611852-25.69253925-4.83024592-42.21003851-4.83024592h-62.57474371v145.29573925h62.57474371c16.79663408 0 31.00823703-2.02676148 42.62267259-6.09242074 11.61443555-4.05352297 21.12929185-9.66049185 28.55670518-16.79663407 7.41527703-7.13614222 12.73097482-15.32814222 15.95922964-24.56386371 3.21611852-9.22358518 4.83024592-19.16321185 4.83024593-29.8067437z m-82.32049778 314.54852741c16.51749925 0 31.00823703-2.09957925 43.46007703-6.29873778 12.45184-4.19915852 22.88905482-10.01244445 31.28737185-17.42772148 8.39831703-7.41527703 14.7577363-16.16554667 19.10253037-26.2508089 4.33265778-10.07312592 6.50505482-20.85015703 6.50505481-32.33109333 0-23.78714075-8.89590518-42.41635555-26.66344295-55.85123555-17.77967408-13.43488-45.00138667-20.15838815-81.67727408-20.15838815h-64.24955259v158.31798519h72.23523556z"
            fill="#ffffff"
            p-id="1079"
          ></path>
        </svg>
        <div
          id="blog-tooltip-bottom"
          role="tooltip"
          class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
        >
          技术文档
          <div class="tooltip-arrow" data-popper-arrow></div>
        </div>
        <!-- b站 -->
        <svg
          @click="jump(blogSettingsStore.blogSettings.csdnHomepage)"
          v-if="blogSettingsStore.blogSettings.csdnHomepage"
          class="hover:scale-110 cursor-pointer icon mt-5 w-7 h-7 transition-all"
          data-tooltip-target="bilibili-tooltip-bottom"
          data-tooltip-placement="bottom"
          t="1713285992094"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="5226"
          width="200"
          height="200"
        >
          <path
            d="M512 512m-512 0a512 512 0 1 0 1024 0 512 512 0 1 0-1024 0Z"
            fill="#FFFFFF"
            p-id="5227"
          ></path>
          <path
            d="M743.424 349.44H281.856A25.088 25.088 0 0 0 256 374.208v301.312c0 14.08 11.776 24.512 25.856 24.512h461.568c14.08 0 24.576-10.432 24.576-24.512V374.144a24 24 0 0 0-24.576-24.64zM320 460.8l119.04-24.512 8.96 48.064-117.76 24.512-10.24-48z m191.488 146.432c-30.976 42.88-63.488-13.504-63.488-13.504l16.512-13.568s22.08 50.624 46.72-16.448c23.68 65.152 49.792 17.088 49.792 17.408L576 593.408s-27.968 56.768-64.512 13.824z m182.592-98.368L576 484.352l9.28-48L704 460.8l-9.92 48.064zM512 0a512 512 0 1 0 0 1024A512 512 0 0 0 512 0z m240.896 760.96c-32-1.024-42.752 0-42.752 0s-2.368 36.672-33.664 37.312c-31.616 0.32-36.352-25.6-37.376-35.328-19.2 0-249.728 1.024-249.728 1.024s-4.032 33.92-35.328 33.92c-31.68 0-33.344-28.16-35.392-33.92-20.48 0-48.128-0.64-48.128-0.64S201.216 748.8 192.128 658.56c1.024-90.24 0-268.672 0-268.672s-6.4-83.136 76.416-107.008A4914.56 4914.56 0 0 1 413.248 281.6l-58.88-57.216s-9.088-11.52 6.4-24.256c15.808-12.8 16.448-7.552 21.888-3.84 5.376 3.648 87.808 84.928 87.808 84.928h-11.072c31.616 0 64.256 0.512 95.616 0.512 12.096-12.096 81.088-79.68 85.76-83.008 5.44-3.392 6.4-9.088 21.952 3.712 15.424 12.8 6.4 24.32 6.4 24.32l-57.6 55.488 140.032 1.024s78.08 17.152 80.128 106.688A20278.4 20278.4 0 0 0 832 659.584s-4.352 87.552-79.104 101.312z"
            fill="#FB7299"
            p-id="5228"
          ></path>
        </svg>
        <div
          id="bilibili-tooltip-bottom"
          role="tooltip"
          class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
        >
          BiliBili
          <div class="tooltip-arrow" data-popper-arrow></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useBlogSettingsStore } from "@/stores/blogsettings";
import { initTooltips } from "flowbite";
import { onMounted, ref } from "vue";
import { getStatisticsInfo } from "@/api/frontend/statistics";
import CountTo from "@/components/CountTo.vue";

// 初始化 Flowbit 组件
onMounted(() => {
  initTooltips();
});

// 引入博客设置信息 store
const blogSettingsStore = useBlogSettingsStore();

const jump = (url) => {
  // 在新窗口访问新的链接地址
  window.open(url, "_blank");
};

// 统计信息(文章、分类、标签数量、总访问量)
const statisticsInfo = ref({});
getStatisticsInfo().then((res) => {
  if (res.success) {
    statisticsInfo.value = res.data;
  }
});
</script>
