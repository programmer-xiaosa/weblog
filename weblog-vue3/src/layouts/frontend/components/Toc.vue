<template>
    <!-- text-sm/[30px] 表示文字小号，行高为 30px -->
    <div v-if="titles && titles.length > 0"
        class="sticky top-[5.5rem] text-sm/[30px] w-full p-5 mb-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
        <!-- 目录标题 -->
        <h2 class="flex items-center mb-2 font-bold text-gray-900 uppercase dark:text-white">
            <!-- 目录图标 -->
            <svg t="1699441758495" class="icon w-3.5 h-3.5 mr-2" viewBox="0 0 1024 1024" version="1.1"
                xmlns="http://www.w3.org/2000/svg" p-id="4043" width="200" height="200">
                <path
                    d="M857.6 25.6a76.8 76.8 0 0 1 76.8 76.8v819.2a76.8 76.8 0 0 1-76.8 76.8H166.4a76.8 76.8 0 0 1-76.8-76.8V102.4a76.8 76.8 0 0 1 76.8-76.8h691.2z m-102.4 678.4H473.6l-2.2528 0.064a38.4 38.4 0 0 0 0 76.672L473.6 780.8h281.6l2.2528-0.064a38.4 38.4 0 0 0 0-76.672L755.2 704z m0-230.4H473.6l-2.2528 0.064a38.4 38.4 0 0 0 0 76.672L473.6 550.4h281.6l2.2528-0.064a38.4 38.4 0 0 0 0-76.672L755.2 473.6z m0-230.4H473.6l-2.2528 0.064a38.4 38.4 0 0 0 0 76.672L473.6 320h281.6l2.2528-0.064a38.4 38.4 0 0 0 0-76.672L755.2 243.2z"
                    fill="#6B57FE" p-id="4044"></path>
                <path
                    d="M281.6 691.2a51.2 51.2 0 1 1 0 102.4 51.2 51.2 0 0 1 0-102.4z m0-230.4a51.2 51.2 0 1 1 0 102.4 51.2 51.2 0 0 1 0-102.4z m0-230.4a51.2 51.2 0 1 1 0 102.4 51.2 51.2 0 0 1 0-102.4z"
                    fill="#FFBA00" p-id="4045"></path>
            </svg>
            文章目录
        </h2>
        <div class="toc-wrapper" :class="[isDark ? 'dark' : '']">
			<ul class="toc">
                <!-- 二级标题 -->
                <li v-for="(h2, index) in titles" :key="index">
                    <span @click="scrollToView(h2.offsetTop)" class="pl-5 hover:text-sky-600" :class="[h2.index == activeHeadingIndex ? 'active py-1 text-sky-600 border-l-2 border-sky-600 font-bold' : 'text-gray-500 font-normal']">{{ h2.text }}</span>
                    <!-- 三级标题 -->
                    <ul v-if="h2.children && h2.children.length > 0">
                        <li v-for="(h3, index2) in h2.children" :key="index2">
                            <span @click="scrollToView(h3.offsetTop)" class="pl-10 hover:text-sky-600" :class="[h3.index == activeHeadingIndex ? 'active py-1 text-sky-600 border-l-2 border-sky-600 font-bold' : 'text-gray-500 font-normal']">{{ h3.text }}</span>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { useDark } from '@vueuse/core'

// 是否是暗黑模式
const isDark = useDark({disableTransition: false})

// 响应式的目录数据
const titles = ref([])
onMounted(() => {
    // 通过 .artilce-content 样式来获取父级 div
    const container = document.querySelector('.article-content')

    // 使用 MutationObserver 监视 DOM 的变化
    const observer = new MutationObserver(mutationsList => {
        for (let mutation of mutationsList) {
            if (mutation.type === 'childList') {
                // 先清空目录缓存数据
                titles.value = []
                // 计算目录数据
                initTocData(container)

                // 监听所有图片的加载事件
                const images = container.querySelectorAll('img');
                images.forEach(img => {
                    img.addEventListener('load', () => {
                        // 图片加载完成后重新计算标题的 offsetTop
                        initTocData(container)
                    })
                })

                // 添加滚动事件监听
                window.addEventListener('scroll', handleContentScroll);
            }
        }
    })

    // 配置监视子节点的变化
    const config = { childList: true, subtree: true }
    // 开始观察正文 div 的内容变化
    observer.observe(container, config)
})

// 记录当前被选中的标题下标
const activeHeadingIndex = ref(-1)
// 处理滚动事件
function handleContentScroll() {
    // 当前的滚动位置
    let scrollY = window.scrollY
    // 循环目录
    titles.value.forEach(title => {
        // 获取每个标题的 offset
        let offsetTop = title.offsetTop
        // 如果当前位置大于等于标题位置，则标记选中，记录被选中标题的下标
        if (scrollY >= offsetTop) {
            activeHeadingIndex.value = title.index
        }

        // 处理3级标题, 同样的逻辑
        let children = title.children
        if (children && children.length > 0) {
            children.forEach(child => {
                let childOffsetTop = child.offsetTop
                if (scrollY >= childOffsetTop) {
                    activeHeadingIndex.value = child.index
                }
            })
        }
    })
}

// 移除滚动监听
onBeforeUnmount(() => window.removeEventListener('scroll', handleContentScroll))

// 滚动到指定的位置
function scrollToView(offsetTop) {
    window.scrollTo({ top: offsetTop, behavior: "smooth" });
}

// 初始化标题数据
function initTocData(container) {
    // 只提取二级、三级标题
    let levels = ['h2', 'h3']
    let headings = container.querySelectorAll(levels)

    // 存放组装后的目录标题数据
    let titlesArr = []

    // 下标
    let index = 1
    headings.forEach(heading => {
        // 标题等级， h2 -> 级别 2 ； h3 -> 级别3
        let headingLevel = parseInt(heading.tagName.substring(1))
        // 标题文字
        let headingText = heading.innerText
        // 标题的位置（距离顶部的距离）
        let offsetTop = heading.offsetTop - 95

        if (headingLevel === 2) { // 二级标题
            titlesArr.push({
                index,
                level: headingLevel,
                text: headingText,
                offsetTop,
                children: [] // 二级标题下的子标题
            })
        } else { // 三级标题
            // 父级标题
            let parentHeading = titlesArr[titlesArr.length - 1]
            // 设置父级标题的 children
            parentHeading.children.push({
                index,
                level: headingLevel,
                text: headingText,
                offsetTop
            })
        }
        // 下标 +1
        index++
    })

    // 设置数据
    titles.value = titlesArr
}
</script>

<style scoped>
::v-deep(.toc-wrapper) {
    position: relative;
    overflow-x: hidden;
    overflow-y: auto;
    max-height: 75vh;
    text-overflow: ellipsis;
    white-space: nowrap;
    scroll-behavior: smooth;
}

::v-deep(.toc:before) {
    content: " ";
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    z-index: -1;
    width: 2px;
    background: #eaecef;
}

::v-deep(.dark .toc:before) {
    content: " ";
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    z-index: -1;
    width: 2px;
    background: #30363d;
}

::v-deep(.dark .toc li span) {
    color: #9e9e9e;
}

::v-deep(.dark .toc li .active) {
    color: rgb(2 132 199 / 1);
}

::v-deep(.dark .toc li span:hover) {
    color: rgb(2 132 199 / 1);
}
</style>
