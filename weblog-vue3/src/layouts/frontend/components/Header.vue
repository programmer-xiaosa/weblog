<template>
    <header class="sticky top-0 z-10">
        <nav class="bg-white border-gray-200 border-b dark:border-gray-800 dark:bg-gray-900">
            <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
                <!-- 博客 LOGO 、博客名称 -->
                <a href="/" class="flex items-center">
                    <img :src="blogSettingsStore.blogSettings.logo" class="h-8 mr-3 rounded-full" alt="Flowbite Logo" />
                    <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">{{
                        blogSettingsStore.blogSettings.name }}</span>
                </a>
                <div class="flex items-center md:order-2">
                    <!-- 白天黑夜切换 -->
                    <button @click="toggleDark()" class="ml-1 mr-4 vt-switch vt-switch-appearance" type="button"
                        role="switch" aria-label="切换深色模式" aria-checked="false" data-v-d401ce6f=""><span
                            class="vt-switch-check"><span class="vt-switch-icon"><!--[-->
                                <!-- 太阳图标 -->
                                <svg xmlns="http://www.w3.org/2000/svg" aria-hidden="true" focusable="false"
                                    viewBox="0 0 24 24" class="vt-switch-appearance-sun"
                                    :class="[isDark ? 'hidden' : 'block']">
                                    <path
                                        d="M12,18c-3.3,0-6-2.7-6-6s2.7-6,6-6s6,2.7,6,6S15.3,18,12,18zM12,8c-2.2,0-4,1.8-4,4c0,2.2,1.8,4,4,4c2.2,0,4-1.8,4-4C16,9.8,14.2,8,12,8z">
                                    </path>
                                    <path d="M12,4c-0.6,0-1-0.4-1-1V1c0-0.6,0.4-1,1-1s1,0.4,1,1v2C13,3.6,12.6,4,12,4z">
                                    </path>
                                    <path
                                        d="M12,24c-0.6,0-1-0.4-1-1v-2c0-0.6,0.4-1,1-1s1,0.4,1,1v2C13,23.6,12.6,24,12,24z">
                                    </path>
                                    <path
                                        d="M5.6,6.6c-0.3,0-0.5-0.1-0.7-0.3L3.5,4.9c-0.4-0.4-0.4-1,0-1.4s1-0.4,1.4,0l1.4,1.4c0.4,0.4,0.4,1,0,1.4C6.2,6.5,5.9,6.6,5.6,6.6z">
                                    </path>
                                    <path
                                        d="M19.8,20.8c-0.3,0-0.5-0.1-0.7-0.3l-1.4-1.4c-0.4-0.4-0.4-1,0-1.4s1-0.4,1.4,0l1.4,1.4c0.4,0.4,0.4,1,0,1.4C20.3,20.7,20,20.8,19.8,20.8z">
                                    </path>
                                    <path d="M3,13H1c-0.6,0-1-0.4-1-1s0.4-1,1-1h2c0.6,0,1,0.4,1,1S3.6,13,3,13z"></path>
                                    <path d="M23,13h-2c-0.6,0-1-0.4-1-1s0.4-1,1-1h2c0.6,0,1,0.4,1,1S23.6,13,23,13z">
                                    </path>
                                    <path
                                        d="M4.2,20.8c-0.3,0-0.5-0.1-0.7-0.3c-0.4-0.4-0.4-1,0-1.4l1.4-1.4c0.4-0.4,1-0.4,1.4,0s0.4,1,0,1.4l-1.4,1.4C4.7,20.7,4.5,20.8,4.2,20.8z">
                                    </path>
                                    <path
                                        d="M18.4,6.6c-0.3,0-0.5-0.1-0.7-0.3c-0.4-0.4-0.4-1,0-1.4l1.4-1.4c0.4-0.4,1-0.4,1.4,0s0.4,1,0,1.4l-1.4,1.4C18.9,6.5,18.6,6.6,18.4,6.6z">
                                    </path>
                                </svg>
                                <!-- 黑夜图标 -->
                                <svg xmlns="http://www.w3.org/2000/svg" aria-hidden="true" focusable="false"
                                    viewBox="0 0 24 24" class="vt-switch-appearance-moon"
                                    :class="[isDark ? 'block' : 'hidden']">
                                    <path
                                        d="M12.1,22c-0.3,0-0.6,0-0.9,0c-5.5-0.5-9.5-5.4-9-10.9c0.4-4.8,4.2-8.6,9-9c0.4,0,0.8,0.2,1,0.5c0.2,0.3,0.2,0.8-0.1,1.1c-2,2.7-1.4,6.4,1.3,8.4c2.1,1.6,5,1.6,7.1,0c0.3-0.2,0.7-0.3,1.1-0.1c0.3,0.2,0.5,0.6,0.5,1c-0.2,2.7-1.5,5.1-3.6,6.8C16.6,21.2,14.4,22,12.1,22zM9.3,4.4c-2.9,1-5,3.6-5.2,6.8c-0.4,4.4,2.8,8.3,7.2,8.7c2.1,0.2,4.2-0.4,5.8-1.8c1.1-0.9,1.9-2.1,2.4-3.4c-2.5,0.9-5.3,0.5-7.5-1.1C9.2,11.4,8.1,7.7,9.3,4.4z">
                                    </path>
                                </svg><!--]--></span></span>
                    </button>

                    <!-- 搜索框 -->
                    <button type="button" @click="clickSearchBtn" class="mr-2 hidden outline-none md:flex items-center text-sm leading-6 text-slate-400 rounded-md 
                        ring-1 ring-slate-900/10 shadow-sm py-1.5 pl-2 pr-3 hover:ring-slate-300 
                        dark:bg-slate-800 dark:hover:bg-slate-700 dark:hover:ring-slate-700">
                        <svg class="w-3.5 h-3.5 mr-2 text-gray-500 dark:text-gray-400" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z" />
                        </svg>
                        <span class="mr-3">搜索文章 ...</span>
                        <span
                            class="px-2 py-[1px] flex-none text-xs border text-gray-400 rounded dark:border-gray-600">Ctrl
                            K</span>
                    </button>

                    <!-- 登录 -->
                    <div class="text-gray-900 ml-1 mr-1 hover:text-blue-700 dark:text-white cursor-pointer" v-if="!isLogined"
                        @click="$router.push('/login')">登录</div>
                    <!-- 已经登录，展示用户头像 -->
                    <button id="dropdownDefaultButton" data-dropdown-toggle="dropdown" v-else
                        class="text-white ml-2 mr-2 md:mr-0 focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                        type="button">
                        <!-- 用户登录头像 -->
                        <img class="w-8 h-8 rounded-full" :src="blogSettingsStore.blogSettings.avatar" alt="user photo">
                    </button>

                    <!-- Dropdown menu -->
                    <div id="dropdown"
                        class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow dark:bg-gray-700">
                        <ul class="py-2 text-sm text-gray-700 dark:text-gray-200"
                            aria-labelledby="dropdownDefaultButton">
                            <li>
                                <a @click="router.push('/admin/index')"
                                    class="cursor-pointer block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                    <svg class="inline w-3 h-3 mb-[2px] mr-1 text-gray-700 dark:text-white"
                                        aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                        viewBox="0 0 20 20">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M10 14v4m-4 1h8M1 10h18M2 1h16a1 1 0 0 1 1 1v11a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1Z" />
                                    </svg>
                                    进入后台
                                </a>
                            </li>
                            <li>
                                <a data-modal-target="popup-modal" data-modal-toggle="popup-modal"
                                    class="cursor-pointer block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                    <svg class="inline w-3 h-3 mb-[2px] mr-1 text-gray-700 dark:text-white"
                                        aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                        viewBox="0 0 16 16">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M4 8h11m0 0-4-4m4 4-4 4m-5 3H3a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h3" />
                                    </svg>
                                    退出登录
                                </a>
                            </li>
                        </ul>
                    </div>

                    <button data-collapse-toggle="navbar-search" type="button"
                        class="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
                        aria-controls="navbar-search" aria-expanded="false">
                        <span class="sr-only">Open main menu</span>
                        <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                            viewBox="0 0 17 14">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M1 1h15M1 7h15M1 13h15" />
                        </svg>
                    </button>
                </div>
                <div class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1" id="navbar-search">
                    <!-- 移动端搜索框 -->
                    <button type="button" @click="clickSearchBtn" class="relative mt-3 flex w-full md:hidden items-center text-sm leading-6 text-slate-400 rounded-md
                         ring-1 ring-slate-900/10 shadow-sm py-1.5 pl-2 pr-3 hover:ring-slate-300 dark:bg-slate-800 
                         dark:highlight-white/5 dark:hover:bg-slate-700 dark:hover:ring-slate-700">
                        <svg class="w-3.5 h-3.5 mr-2 text-gray-500 dark:text-gray-400" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z" />
                        </svg>
                        <span class="mr-3 grow text-left">搜索文章 ...</span>
                        <span
                            class="px-2 py-[1px] flex-none text-xs border text-gray-400 rounded dark:border-gray-600">Ctrl
                            K</span>
                    </button>
                    <ul
                        class="flex flex-col p-4 md:p-0 mt-4 font-medium border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                        <li>
                            <a @click="router.push('/')"
                                :class="[currPath == '/' ? 'text-sky-600 md:border-b-2 md:border-sky-600 dark:text-sky-500 dark:md:border-sky-600' : 'text-gray-900 dark:text-white']"
                                class="block py-2 pl-3 pr-4 rounded cursor-pointer md:rounded-none hover:bg-gray-100 md:hover:bg-transparent md:hover:text-sky-600 md:bg-transparent md:p-0 dark:hover:bg-gray-700 md:dark:hover:bg-transparent" 
                                aria-current="page">首页</a>
                        </li>
                        <li>
                            <a @click="router.push('/category/list')"
                                :class="[currPath.startsWith('/category') ? 'text-sky-600 md:border-b-2 md:border-sky-600 dark:text-sky-500 dark:md:border-sky-600' : 'text-gray-900 dark:text-white']"
                                class="block py-2 pl-3 pr-4 rounded cursor-pointer md:rounded-none hover:bg-gray-100 md:hover:bg-transparent md:hover:text-sky-600 md:p-0 md:dark:hover:text-sky-500  dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">分类</a>
                        </li>
                        <li>
                            <a @click="router.push('/tag/list')"
                                :class="[currPath.startsWith('/tag') ? 'text-sky-600 md:border-b-2 md:border-sky-600 dark:text-sky-500 dark:md:border-sky-600' : 'text-gray-900 dark:text-white']"
                                class="block py-2 pl-3 pr-4 rounded cursor-pointer md:rounded-none hover:bg-gray-100 md:hover:bg-transparent md:hover:text-sky-600 md:p-0 md:dark:hover:text-sky-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">标签</a>
                        </li>
                        <li>
                            <a @click="router.push('/archive/list')"
                                :class="[currPath == '/archive/list' ? 'text-sky-600 md:border-b-2 md:border-sky-600 dark:text-sky-500 dark:md:border-sky-600' : 'text-gray-900 dark:text-white']"
                                class="block py-2 pl-3 pr-4 rounded cursor-pointer md:rounded-none hover:bg-gray-100 md:hover:bg-transparent md:hover:text-sky-600 md:p-0 md:dark:hover:text-sky-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">归档</a>
                        </li>
                        <li>
                            <a @click="router.push('/wiki/list')"
                                :class="[currPath == '/wiki/list' ? 'text-sky-600 md:border-b-2 md:border-sky-600 dark:text-sky-500 dark:md:border-sky-600' : 'text-gray-900 dark:text-white']"
                                class="block py-2 pl-3 pr-4 rounded cursor-pointer md:rounded-none hover:bg-gray-100 md:hover:bg-transparent md:hover:text-sky-600 md:p-0 md:dark:hover:text-sky-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">知识库</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <!-- 退出登录 -->
    <div id="popup-modal" tabindex="-1"
        class="fixed top-0 left-0 right-0 z-50 hidden p-4 overflow-x-hidden overflow-y-auto md:inset-0 h-[calc(100%-1rem)] max-h-full">
        <div class="relative w-full max-w-md max-h-full">
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                <button type="button"
                    class="absolute top-3 right-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ml-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                    data-modal-hide="popup-modal">
                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                        viewBox="0 0 14 14">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
                    </svg>
                    <span class="sr-only">Close modal</span>
                </button>
                <div class="p-6 text-center">
                    <svg class="mx-auto mb-4 text-gray-400 w-12 h-12 dark:text-gray-200" aria-hidden="true"
                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                    </svg>
                    <h3 class="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">是否确定退出登录?
                    </h3>
                    <button @click="logout" data-modal-hide="popup-modal" type="button"
                        class="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center mr-2">
                        确定
                    </button>
                    <button data-modal-hide="popup-modal" type="button"
                        class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-200 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">
                        取消</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 站内搜索 -->
    <div id="search-modal" tabindex="-1" aria-hidden="true"
        class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
        <div class="relative p-4 w-full max-w-2xl max-h-full">
            <!-- Modal content -->
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                <!-- Modal header -->
                <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                    <form class="w-full mr-2">
                        <label for="search"
                            class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search</label>
                        <div class="relative">
                            <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
                                <div v-if="searchLoading" role="status">
                                    <svg aria-hidden="true"
                                        class="w-4 h-4 text-gray-200 animate-spin dark:text-gray-600 fill-sky-600"
                                        viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path
                                            d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z"
                                            fill="currentColor" />
                                        <path
                                            d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z"
                                            fill="currentFill" />
                                    </svg>
                                    <span class="sr-only">Loading...</span>
                                </div>

                                <svg v-else class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z" />
                                </svg>
                            </div>
                            <input type="search" id="search" ref="searchInputRef" v-model="searchWord"
                                class="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-sky-500 focus:border-sky-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-sky-500 dark:focus:border-sky-500"
                                placeholder="请输入关键词搜索..." required>
                        </div>
                    </form>
                    <button type="button" @click="searchModal.hide()"
                        class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white">
                        <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                            viewBox="0 0 14 14">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
                        </svg>
                        <span class="sr-only">Close modal</span>
                    </button>
                </div>
                <!-- Modal body -->
                <div class="p-4 md:p-5 space-y-4">
                    <div v-if="searchArticles && searchArticles.length > 0">
                        <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
                            共搜索到 {{ total }} 篇相关文章
                        </p>
                        <ol class="mt-3 divide-y divider-gray-200 dark:divide-gray-600">
                            <li v-for="(article, index) in searchArticles" :key="index">
                                <a @click="jumpToArticleDetailPage(article.id)" class="items-center cursor-pointer block p-3 sm:flex hover:bg-gray-100 hover:rounded-lg 
                                    dark:hover:bg-gray-600">
                                    <img class="w-24 h-full mb-3 mr-3 rounded-lg sm:mb-0" :src="article.cover">
                                    <div class="text-gray-600 dark:text-gray-400">
                                        <h2 class="text-base font-normal text-gray-900 dark:text-white"
                                            v-html="article.title"></h2>
                                        <span
                                            class="inline-flex items-center text-xs font-normal text-gray-500 dark:text-gray-400">
                                            <svg class="inline w-2.5 h-2.5 mr-2 text-gray-400" aria-hidden="true"
                                                xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                                <path stroke="currentColor" stroke-linecap="round"
                                                    stroke-linejoin="round" stroke-width="2"
                                                    d="M5 1v3m5-3v3m5-3v3M1 7h18M5 11h10M2 3h16a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1Z">
                                                </path>
                                            </svg> {{ article.createDate }}</span>
                                    </div>
                                </a>
                            </li>
                        </ol>
                        <!-- 分页 -->
                        <div class="flex mt-7">
                            <a v-if="current > 1" @click="prePage" class="flex items-center justify-center px-3 h-8 me-3 text-xs font-medium text-gray-500 border 
                                border-gray-300 dark:border-gray-600 rounded-lg hover:bg-gray-100 hover:text-gray-700 
                                dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M13 5H1m0 0 4 4M1 5l4-4" />
                                </svg>
                            </a>

                            <a v-if="current < pages" @click="nextPage" class="flex ml-auto items-center justify-center px-3 h-8 text-xs font-medium text-gray-500 
                                border border-gray-300 rounded-lg hover:bg-gray-100 hover:text-gray-700 dark:border-gray-600 
                             dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9" />
                                </svg>
                            </a>
                        </div>
                    </div>
                    <!-- 未搜索到结果提示 -->
                    <div v-else class="flex items-center justify-center flex-col mb-10">
                        <svg class="w-50 h-50" height="200" node-id="1" sillyvg="true" template-height="1000"
                            template-width="1287" version="1.1" viewBox="0 0 1287 1000" width="1287"
                            xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                            <defs node-id="31"></defs>
                            <path
                                d="M 961.07 520.09 C 961.17 518.75 961.57 517.67 961.57 494.86 C 961.57 493.80 960.98 377.08 946.29 324.85 C 945.86 325.37 945.42 325.87 944.97 326.37 C 945.14 326.79 945.23 327.23 945.24 327.67 C 945.24 332.92 917.12 364.86 865.04 364.86 C 817.41 364.86 797.44 341.92 794.18 341.92 C 793.65 341.92 792.00 342.18 789.03 342.18 C 767.14 342.18 752.48 328.38 752.48 323.09 C 752.54 320.92 754.21 319.14 756.37 318.96 C 756.87 318.74 757.41 318.64 757.95 318.65 C 758.70 318.65 759.55 318.94 761.62 318.94 C 767.49 318.94 772.47 316.16 777.35 312.59 C 772.94 303.86 770.63 294.22 770.58 284.44 C 770.58 249.22 805.98 208.39 867.77 208.39 C 875.21 208.39 882.63 209.12 889.93 210.54 C 839.88 155.14 791.24 133.73 663.30 133.73 C 661.26 133.73 659.10 133.73 656.83 133.72 C 620.10 133.72 553.48 134.73 495.69 168.94 C 512.78 180.02 512.11 197.44 512.65 204.34 C 512.30 220.37 506.52 240.28 506.52 248.45 C 506.52 258.33 511.44 264.27 511.44 268.16 C 511.44 271.00 509.13 273.31 506.29 273.30 C 505.98 273.30 505.68 273.26 505.38 273.19 C 504.30 274.29 502.84 274.92 501.30 274.97 C 494.38 274.97 489.14 258.48 489.14 248.98 C 489.14 226.37 495.51 218.24 495.51 204.42 C 495.51 197.41 495.50 185.75 483.86 185.75 C 475.43 185.75 463.63 196.67 463.63 222.84 C 463.63 223.05 463.63 223.15 463.63 223.20 C 463.65 224.65 463.08 226.05 462.06 227.08 C 461.03 228.11 459.64 228.68 458.18 228.66 C 453.01 228.66 452.70 223.66 452.70 221.79 C 452.70 221.54 452.70 221.34 452.70 221.22 C 452.70 220.02 452.74 218.82 452.81 217.65 C 449.34 216.08 448.92 209.99 448.92 205.44 C 448.92 204.22 448.97 203.01 449.07 201.80 C 396.28 245.05 330.47 350.63 330.47 461.27 C 330.47 469.22 331.40 472.77 333.44 482.09 C 344.54 478.34 358.27 476.03 375.27 476.03 C 429.96 476.03 479.91 500.65 491.96 505.52 C 498.58 433.71 498.94 445.31 499.67 432.66 C 503.22 371.12 563.42 320.00 637.63 320.00 C 664.46 319.95 690.89 326.41 714.67 338.83 C 794.13 380.31 790.80 453.60 791.06 454.70 C 791.57 454.70 792.05 454.72 792.53 454.72 C 793.83 454.72 798.45 454.17 806.53 454.17 C 826.96 454.17 925.45 463.56 953.59 550.33 C 953.98 550.33 954.44 550.33 954.93 550.33 C 956.54 550.33 958.09 550.33 959.59 550.33 C 960.01 541.24 960.43 532.34 960.86 523.06 C 960.13 523.94 959.08 525.80 956.69 525.80 C 955.30 525.80 900.23 447.02 830.12 447.02 C 825.03 447.02 821.19 447.78 819.69 447.78 C 818.88 447.78 815.44 447.25 815.44 443.60 C 815.44 441.53 816.33 440.09 819.74 439.29 C 819.69 439.05 819.66 438.80 819.66 438.56 L 819.66 438.52 C 819.66 435.02 823.18 434.92 826.59 434.92 C 826.80 434.92 827.02 434.92 827.23 434.92 C 828.21 434.92 829.19 434.94 830.17 434.94 C 910.96 435.91 960.94 508.05 960.94 517.38 C 960.94 518.02 960.76 518.64 960.42 519.18 C 960.84 519.77 961.06 520.09 961.07 520.09 Z M 450.12 351.37 C 446.71 351.37 437.78 347.56 431.16 347.56 C 430.91 347.56 430.67 347.57 430.51 347.57 C 430.19 352.95 431.12 360.83 422.29 366.47 C 420.12 367.91 417.57 368.69 414.96 368.70 C 409.61 368.70 400.24 366.83 400.24 355.93 C 400.24 351.54 402.09 349.08 404.05 346.81 C 404.34 346.35 404.64 345.92 404.95 345.51 C 386.94 350.23 387.80 361.75 382.59 361.75 C 380.44 361.71 378.61 360.17 378.21 358.06 C 376.61 357.30 375.64 355.63 375.78 353.86 C 375.78 345.42 391.14 332.91 408.15 332.91 C 415.52 332.91 417.59 336.22 419.70 336.22 C 420.74 336.22 423.86 334.89 429.34 334.89 C 436.80 334.89 449.78 338.87 451.03 343.23 C 452.96 344.24 454.11 345.52 454.11 347.18 C 454.11 347.81 453.60 351.37 450.12 351.37 Z M 513.58 281.57 C 514.51 280.62 515.77 280.06 517.09 279.99 C 519.67 280.02 521.76 282.08 521.82 284.65 C 521.56 286.70 520.61 288.61 519.14 290.08 C 518.17 292.04 516.43 294.05 513.88 294.05 C 510.05 294.05 507.78 290.95 507.78 287.30 C 507.78 284.95 509.10 281.79 513.58 281.57 Z M 484.97 439.01 C 484.97 447.58 485.66 454.08 485.66 454.24 C 485.76 455.24 485.44 456.22 484.77 456.97 C 484.11 457.71 483.16 458.14 482.17 458.15 C 481.27 458.22 480.39 457.87 479.79 457.20 C 478.98 457.81 478.01 458.17 477.00 458.23 C 474.04 458.23 471.91 457.61 471.91 439.19 C 471.91 438.17 471.93 437.14 471.93 436.12 L 471.93 436.00 C 471.93 326.85 573.40 299.96 580.12 299.96 C 582.43 300.02 584.26 301.93 584.20 304.24 C 584.20 304.68 583.84 305.15 583.18 305.64 C 583.71 305.60 584.16 305.57 584.51 305.57 C 585.47 305.47 586.43 305.78 587.14 306.44 C 587.85 307.09 588.24 308.02 588.21 308.99 C 588.21 315.88 484.97 320.40 484.97 439.01 Z M 670.95 246.55 C 670.91 246.60 670.92 246.69 670.86 246.99 C 672.59 247.16 688.39 246.28 688.39 262.65 C 688.39 263.17 687.75 277.12 671.61 277.12 C 657.91 277.12 655.31 265.85 654.51 265.44 C 649.76 270.92 646.54 279.95 646.54 282.66 C 646.54 283.05 646.56 283.47 646.56 283.68 C 646.56 286.14 645.07 288.25 642.29 288.25 C 640.44 288.26 638.81 287.03 638.33 285.24 C 637.22 284.45 636.17 282.95 636.17 280.11 C 636.17 279.56 636.23 279.01 636.36 278.48 C 642.33 255.65 654.47 256.51 656.23 251.05 C 661.88 233.57 676.89 225.02 684.97 225.02 L 685.00 225.02 C 685.67 225.02 689.39 225.04 691.32 226.53 C 693.28 226.87 694.68 228.63 694.57 230.61 C 694.57 237.61 682.63 232.40 670.95 246.55 Z"
                                fill="#5fc690" fill-rule="nonzero" node-id="33" stroke="none" target-height="416.60968"
                                target-width="631.0967" target-x="330.46844" target-y="133.7226" />
                            <path
                                d="M 986.21 550.21 C 979.38 550.21 969.43 550.33 954.93 550.33 C 954.44 550.33 953.98 550.33 953.59 550.33 C 925.45 463.56 826.96 454.17 806.53 454.17 C 798.45 454.17 793.83 454.72 792.53 454.72 C 792.05 454.72 791.57 454.70 791.06 454.70 C 790.80 453.60 794.13 380.31 714.67 338.83 C 690.89 326.41 664.46 319.95 637.63 320.00 C 563.42 320.00 503.22 371.12 499.67 432.66 C 498.94 445.31 498.58 433.71 491.96 505.52 C 479.91 500.65 429.96 476.03 375.27 476.03 C 288.87 476.03 286.90 535.50 285.86 541.24 L 285.86 550.25 C 316.51 687.67 450.98 734.22 571.04 797.67 C 571.50 797.92 572.01 798.06 572.53 798.06 C 575.69 798.06 586.95 781.55 595.68 772.38 C 654.54 844.22 684.17 849.01 692.11 849.01 C 697.30 849.01 703.39 846.04 703.39 839.90 C 703.39 827.57 676.79 801.68 676.79 793.88 C 676.80 793.16 676.90 792.45 677.09 791.76 C 677.37 791.67 677.66 791.63 677.95 791.63 C 684.92 791.63 717.41 819.32 741.04 819.32 C 748.98 819.32 756.57 817.80 756.57 808.28 C 756.57 788.47 712.39 766.99 707.80 757.92 C 717.10 758.43 746.67 763.73 765.82 763.73 C 773.66 763.73 778.47 754.80 778.47 751.69 C 778.47 744.55 758.22 732.07 720.27 723.86 C 732.25 717.29 752.08 711.46 752.08 692.36 C 752.08 689.04 750.80 681.23 742.59 681.23 C 742.20 681.23 742.17 681.25 741.48 681.25 C 741.19 681.25 740.91 681.24 740.62 681.24 C 734.90 681.24 733.19 683.28 680.72 685.38 C 671.44 685.75 666.73 687.91 662.86 688.90 C 676.11 671.89 677.82 670.58 677.82 668.86 C 677.82 645.00 475.26 556.77 475.16 556.73 L 475.30 556.35 C 578.94 556.53 682.69 556.72 786.53 556.90 C 788.22 582.54 789.63 610.21 809.31 610.21 C 825.51 610.21 824.09 607.20 827.96 604.41 C 828.69 605.29 830.39 607.98 841.89 607.98 C 852.29 607.98 857.04 605.66 859.87 603.00 C 863.16 604.31 865.22 605.68 869.37 605.68 C 899.57 605.68 898.53 561.86 899.10 557.20 C 899.58 557.20 900.00 557.19 900.39 557.19 C 901.40 557.19 924.39 557.81 925.63 557.81 C 928.87 557.81 930.42 557.26 932.49 557.26 C 944.20 557.26 992.84 557.38 995.96 557.38 L 996.18 557.38 C 998.00 557.38 1001.14 557.31 1001.14 553.91 C 1001.14 550.73 1000.47 550.21 986.21 550.21 Z M 741.54 688.15 C 744.35 688.15 745.01 689.22 745.01 692.53 C 745.01 713.45 701.65 719.85 701.65 726.62 C 701.65 727.48 702.00 728.30 702.62 728.91 C 703.23 729.51 704.07 729.84 704.93 729.83 C 705.30 729.83 706.54 729.67 708.74 729.67 C 723.14 729.67 757.88 740.39 771.37 751.74 C 768.79 756.76 766.74 756.76 763.96 756.76 C 749.53 756.76 716.41 751.08 709.52 751.08 C 709.42 751.08 709.13 751.06 708.70 751.06 C 706.39 751.06 700.18 751.49 700.18 757.06 C 700.18 769.85 749.54 791.54 749.54 808.09 C 749.54 811.05 748.64 811.33 747.33 811.57 C 745.19 811.98 743.01 812.20 740.83 812.21 C 718.91 812.21 688.96 784.53 676.95 784.53 C 670.07 784.53 669.79 791.73 669.79 793.70 C 669.79 806.22 696.34 829.67 696.34 839.70 C 696.36 840.53 695.76 841.25 694.94 841.39 C 693.91 841.68 692.84 841.85 691.77 841.91 C 664.23 841.91 613.72 783.62 600.25 768.00 C 658.25 693.13 648.38 688.15 741.54 688.15 Z M 490.76 549.26 C 490.07 549.31 489.42 549.41 488.78 549.41 C 462.22 549.41 459.44 549.28 459.34 549.28 C 458.69 549.28 458.38 549.81 456.91 549.81 C 455.51 549.81 454.91 549.52 443.41 545.69 C 444.90 543.61 464.32 523.47 464.32 519.13 C 464.23 517.48 462.89 516.18 461.24 516.15 C 458.05 516.15 460.76 516.90 437.37 541.46 C 436.82 542.15 435.99 542.56 435.10 542.59 C 432.36 542.59 414.34 534.95 402.64 534.95 C 386.84 534.95 373.14 542.27 373.14 553.71 C 373.14 562.49 386.27 569.63 396.95 569.63 C 418.23 569.63 434.40 551.14 437.65 551.14 C 470.14 551.14 638.95 646.47 639.05 646.56 C 627.13 663.68 609.55 685.17 609.55 687.80 C 609.57 689.39 610.85 690.69 612.44 690.72 C 615.83 690.72 624.88 674.81 643.78 650.55 C 652.70 656.33 661.49 662.02 670.46 667.83 C 663.95 676.63 577.29 785.22 571.85 791.10 C 564.79 787.41 557.89 783.82 550.77 780.10 C 556.64 771.76 587.86 736.00 587.86 733.28 C 587.84 731.69 586.56 730.40 584.97 730.38 C 581.68 730.38 567.37 751.05 545.18 777.06 C 301.44 669.62 293.22 565.49 293.22 548.30 C 293.22 547.06 293.26 546.27 293.26 545.97 C 293.26 501.64 330.26 483.04 375.16 483.04 C 430.74 483.04 485.93 510.48 491.37 513.24 C 491.16 525.26 490.96 537.17 490.76 549.26 Z M 429.02 548.09 C 418.10 556.77 408.35 562.57 397.11 562.57 C 392.70 562.57 387.65 562.09 381.22 556.06 C 380.51 555.43 380.11 554.52 380.13 553.57 C 380.13 548.68 387.58 542.07 402.37 542.07 C 410.52 542.07 414.76 543.80 429.02 548.09 Z M 739.92 492.01 C 739.47 495.97 737.70 499.41 735.63 502.94 C 735.28 501.97 734.54 499.00 731.84 499.00 C 731.57 499.00 731.31 499.04 731.05 499.10 C 729.38 499.50 728.23 501.03 728.29 502.74 C 728.29 503.80 728.70 506.69 728.70 507.24 C 728.70 507.54 728.66 507.87 728.64 508.32 C 708.88 514.47 712.92 507.20 710.07 549.65 L 566.83 549.65 C 564.87 528.89 566.67 538.35 538.54 531.40 C 535.63 530.68 521.74 526.12 519.58 517.33 C 517.63 509.45 516.65 501.36 516.66 493.24 C 516.66 429.32 566.17 397.23 571.48 394.57 C 604.86 435.58 628.31 426.74 637.24 431.95 C 635.56 432.64 633.78 433.01 631.97 433.06 C 629.77 433.06 629.07 432.47 627.82 432.47 C 626.98 432.42 626.16 432.73 625.55 433.30 C 624.94 433.88 624.60 434.68 624.60 435.52 C 624.60 439.02 630.38 439.50 632.49 439.50 C 640.51 439.50 644.35 436.24 647.34 434.03 C 677.28 446.25 686.34 474.21 706.09 474.21 C 713.79 474.21 713.81 470.27 713.81 468.52 L 713.81 468.49 C 713.81 468.16 713.77 466.61 713.77 464.12 C 713.77 410.04 725.71 407.29 729.47 407.29 C 729.89 407.29 730.22 407.32 730.40 407.32 C 731.87 407.32 743.95 408.16 744.94 419.84 C 745.28 423.89 745.45 427.93 745.45 431.97 C 745.45 440.45 745.03 446.71 739.92 492.01 Z M 887.06 586.04 C 884.50 594.64 876.76 598.53 869.22 598.53 C 866.48 598.53 866.47 598.10 864.26 597.43 C 866.12 590.94 867.01 584.21 866.90 577.47 C 866.90 577.45 866.90 577.21 866.90 576.76 C 866.76 566.38 865.81 556.03 864.08 545.80 C 863.98 544.18 862.64 542.91 861.02 542.89 C 858.12 542.89 857.91 545.34 857.91 547.75 C 857.91 552.06 859.77 559.03 859.77 575.93 C 859.77 594.07 858.48 600.88 843.69 600.88 C 838.11 600.88 835.71 600.48 833.44 597.69 C 832.52 596.66 832.00 595.33 831.98 593.94 C 831.98 592.19 832.98 589.70 832.98 581.50 C 832.98 566.51 830.82 557.29 829.56 546.66 C 829.49 545.02 828.16 543.70 826.52 543.63 C 823.45 543.63 823.40 546.86 823.40 548.34 C 823.40 552.33 826.06 571.78 826.06 582.19 C 826.06 602.22 819.59 603.27 812.16 603.27 C 811.28 603.27 810.40 603.26 809.51 603.26 C 792.47 603.26 794.54 562.06 793.24 555.37 C 792.33 550.70 790.43 547.05 777.84 547.05 C 777.12 547.05 776.35 547.16 775.24 547.24 C 784.12 534.76 790.18 526.39 844.36 526.39 C 845.85 526.39 847.31 526.39 848.73 526.39 C 876.65 526.39 891.88 527.14 891.88 558.11 C 891.88 567.65 889.77 576.90 887.06 586.04 Z M 898.61 550.15 C 895.05 531.57 890.12 519.48 857.52 519.48 C 855.96 519.48 855.43 519.52 846.78 519.52 C 846.22 519.52 845.66 519.52 845.09 519.52 C 801.46 519.52 798.95 523.85 792.65 524.59 C 792.24 503.70 791.29 482.92 791.20 461.85 C 796.76 461.49 799.37 461.12 806.08 461.12 C 882.46 461.12 937.51 512.95 946.03 550.15 Z"
                                fill="#000000" fill-rule="nonzero" node-id="35" stroke="none" target-height="529.05273"
                                target-width="715.2872" target-x="285.85657" target-y="319.95316" />
                            <path
                                d="M 867.77 208.39 C 805.98 208.39 770.58 249.22 770.58 284.44 C 770.63 294.22 772.94 303.86 777.35 312.59 C 772.47 316.16 767.49 318.94 761.61 318.94 C 759.55 318.94 758.70 318.65 757.95 318.65 C 757.10 318.56 756.26 318.84 755.63 319.41 C 755.00 319.99 754.64 320.81 754.66 321.66 C 754.66 325.45 767.54 335.42 779.14 335.42 C 784.78 335.42 790.75 333.32 792.15 333.32 C 795.36 333.32 816.60 359.41 864.93 359.41 C 910.08 359.41 961.84 331.33 961.84 283.07 C 961.84 240.22 917.27 208.39 867.77 208.39 Z M 864.92 352.47 C 817.31 352.47 798.01 325.68 793.72 325.68 C 791.55 325.68 786.69 328.39 779.54 328.39 C 775.30 328.42 771.14 327.26 767.51 325.06 C 771.07 323.85 776.25 323.18 784.30 315.81 C 785.29 315.07 785.93 313.94 786.05 312.71 C 786.05 310.41 783.86 310.07 782.61 307.25 C 779.35 300.13 777.63 292.40 777.57 284.58 C 777.57 248.93 814.90 215.39 867.57 215.39 C 910.53 215.39 954.81 241.74 954.81 283.38 C 954.81 319.02 917.37 352.47 864.92 352.47 Z"
                                fill="#000000" fill-rule="nonzero" node-id="37" stroke="none" target-height="151.01958"
                                target-width="207.19415" target-x="754.64343" target-y="208.39409" />
                            <path
                                d="M 454.67 218.04 C 455.98 218.07 457.24 217.55 458.14 216.59 C 459.04 215.64 459.48 214.35 459.37 213.04 C 459.37 211.88 458.93 209.78 458.93 207.34 C 458.93 191.60 469.26 177.04 485.54 177.04 C 489.93 177.04 501.73 182.56 501.73 204.26 C 501.73 219.77 495.77 236.45 495.77 248.01 C 495.77 256.03 499.38 273.30 506.29 273.30 C 509.13 273.31 511.44 271.00 511.44 268.16 C 511.44 264.27 506.52 258.33 506.52 248.45 C 506.52 240.28 512.30 220.37 512.65 204.34 C 512.11 197.43 512.78 179.97 495.61 168.88 C 492.38 166.80 488.87 166.35 485.22 166.35 C 462.51 166.35 448.92 185.25 448.92 205.44 C 448.92 210.70 449.47 218.04 454.67 218.04 Z"
                                fill="#000000" fill-rule="nonzero" node-id="39" stroke="none" target-height="106.95955"
                                target-width="63.86621" target-x="448.91815" target-y="166.34673" />
                            <path
                                d="M 477.94 440.36 C 478.43 453.49 477.96 458.15 482.17 458.15 C 483.16 458.14 484.11 457.71 484.77 456.97 C 485.44 456.22 485.76 455.24 485.66 454.24 C 485.66 454.08 484.97 447.58 484.97 439.01 C 484.97 320.40 588.21 315.88 588.21 308.99 C 588.24 308.02 587.85 307.09 587.14 306.44 C 586.43 305.78 585.47 305.47 584.51 305.57 C 573.81 305.57 478.47 325.11 477.94 440.36 Z"
                                fill="#000000" fill-rule="nonzero" node-id="41" stroke="none" target-height="152.67892"
                                target-width="110.29831" target-x="477.93912" target-y="305.46857" />
                            <path
                                d="M 830.17 434.94 C 829.19 434.94 828.21 434.92 827.23 434.92 C 827.02 434.92 826.80 434.92 826.59 434.92 C 823.18 434.92 819.66 435.02 819.66 438.52 L 819.66 438.56 C 819.66 438.98 819.75 442.00 823.87 442.00 C 824.47 442.00 826.21 441.86 829.37 441.86 C 916.21 441.86 952.77 520.80 957.55 520.80 C 959.43 520.79 960.95 519.26 960.94 517.38 C 960.94 508.05 910.96 435.91 830.17 434.94 Z"
                                fill="#000000" fill-rule="nonzero" node-id="43" stroke="none" target-height="85.87799"
                                target-width="141.29077" target-x="819.6579" target-y="434.9206" />
                            <path
                                d="M 379.87 358.46 C 381.55 358.47 382.96 357.20 383.11 355.53 C 383.11 354.48 382.57 354.14 382.57 353.26 C 382.57 349.92 395.14 339.90 407.62 339.90 C 408.35 339.90 409.09 339.95 409.82 339.95 C 409.12 341.64 401.66 346.31 401.66 354.75 C 401.66 360.08 404.76 364.70 410.19 364.70 C 415.57 364.70 425.06 359.53 425.06 350.12 C 425.06 347.53 424.39 345.48 423.71 342.69 C 425.45 342.14 427.27 341.87 429.10 341.89 C 439.47 341.89 444.78 347.25 447.95 347.25 C 449.74 347.17 451.14 345.69 451.14 343.90 C 451.14 339.29 437.18 334.89 429.34 334.89 C 423.86 334.89 420.74 336.22 419.71 336.22 C 417.59 336.22 415.52 332.91 408.15 332.91 C 391.14 332.91 375.78 345.42 375.78 353.86 C 375.68 355.02 376.06 356.17 376.83 357.04 C 377.61 357.91 378.71 358.43 379.87 358.46 Z M 416.68 344.83 C 417.50 346.37 417.95 348.07 417.99 349.81 C 417.99 355.60 412.17 357.64 410.42 357.64 C 409.68 357.64 408.72 357.34 408.72 354.73 C 408.72 350.44 412.57 346.67 416.68 344.83 Z"
                                fill="#000000" fill-rule="nonzero" node-id="45" stroke="none" target-height="31.787598"
                                target-width="75.46402" target-x="375.6795" target-y="332.91104" />
                            <path
                                d="M 640.77 286.08 C 642.44 285.98 643.78 284.68 643.92 283.03 C 643.92 281.93 643.22 281.62 643.22 280.04 C 643.22 279.88 646.25 267.12 654.58 262.04 C 654.82 262.97 656.64 273.61 667.05 273.61 C 673.60 273.61 681.87 267.97 681.87 261.45 C 681.87 255.82 676.14 250.74 666.40 250.74 C 665.56 250.74 664.70 250.77 663.81 250.82 C 668.49 239.32 679.41 231.45 686.05 231.45 C 687.25 231.51 688.45 231.65 689.63 231.86 C 692.07 231.86 692.50 229.34 692.50 228.80 C 692.50 225.05 685.92 225.02 685.00 225.02 L 684.97 225.02 C 676.89 225.02 661.88 233.57 656.23 251.05 C 654.47 256.51 642.33 255.65 636.36 278.48 C 636.23 279.01 636.17 279.56 636.17 280.11 C 636.17 285.48 639.89 286.08 640.77 286.08 Z M 666.91 257.79 C 670.93 257.79 674.74 259.37 674.74 261.44 C 674.74 263.75 670.16 266.53 667.05 266.53 C 661.42 266.53 661.69 260.41 661.50 258.64 C 663.25 258.09 665.08 257.81 666.91 257.79 Z"
                                fill="#000000" fill-rule="nonzero" node-id="47" stroke="none" target-height="61.05577"
                                target-width="56.33368" target-x="636.1679" target-y="225.02061" />
                            <path
                                d="M 606.35 798.42 C 605.71 797.35 604.57 796.68 603.32 796.65 C 602.43 796.65 601.57 796.99 600.94 797.62 C 600.30 798.24 599.94 799.09 599.93 799.99 C 599.93 813.49 662.66 866.28 689.31 866.28 C 690.23 866.28 691.12 865.91 691.77 865.25 C 692.42 864.59 692.78 863.70 692.76 862.78 C 692.76 857.51 641.15 850.82 606.35 798.42 Z"
                                fill="#000000" fill-rule="nonzero" node-id="49" stroke="none" target-height="69.63153"
                                target-width="92.84186" target-x="599.93427" target-y="796.6459" />
                            <path
                                d="M 724.85 587.67 C 724.91 587.67 725.00 587.68 725.12 587.68 C 726.09 587.68 728.76 587.53 728.76 584.83 C 728.76 582.03 725.94 581.94 724.16 581.94 C 715.83 581.94 590.61 582.11 590.52 582.11 C 590.39 582.11 590.27 582.11 590.15 582.11 C 590.10 582.11 589.96 582.10 589.77 582.10 C 588.73 582.10 586.02 582.31 586.02 584.96 C 586.02 587.79 588.88 587.80 590.68 587.80 L 590.73 587.80 C 590.94 587.80 591.15 587.80 591.35 587.80 C 593.25 587.80 602.50 587.81 636.51 587.81 C 643.46 587.81 650.40 587.81 657.35 587.81 L 657.35 587.67 C 663.46 587.67 669.56 587.67 675.67 587.67 C 710.93 587.67 722.90 587.67 723.78 587.67 C 724.13 587.67 724.49 587.67 724.85 587.67 Z"
                                fill="#000000" fill-rule="nonzero" node-id="51" stroke="none" target-height="5.867737"
                                target-width="142.73828" target-x="586.01874" target-y="581.94104" />
                            <path
                                d="M 832.34 618.75 C 801.80 618.75 781.30 618.66 767.54 618.66 C 740.57 618.66 739.51 619.02 739.51 621.20 C 739.51 621.39 739.52 621.58 739.52 621.79 C 739.53 623.13 740.49 624.27 741.80 624.51 C 742.34 624.60 742.89 624.64 743.45 624.63 C 743.72 624.63 743.99 624.62 744.26 624.62 C 745.41 624.62 751.48 624.63 772.44 624.63 C 777.53 624.63 782.62 624.63 787.71 624.63 L 787.71 624.51 C 793.28 624.51 798.84 624.51 804.40 624.51 C 813.93 624.51 820.63 624.60 825.34 624.60 C 835.15 624.60 836.29 624.18 836.29 621.59 C 836.29 618.85 833.59 618.75 832.34 618.75 Z"
                                fill="#000000" fill-rule="nonzero" node-id="53" stroke="none" target-height="5.9813232"
                                target-width="96.77783" target-x="739.5124" target-y="618.6565" />
                            <path
                                d="M 698.26 674.57 C 698.75 674.63 699.24 674.66 699.73 674.66 C 743.95 669.75 743.98 669.88 743.98 666.17 C 744.00 665.21 743.61 664.30 742.91 663.65 C 742.21 663.00 741.27 662.68 740.32 662.76 C 738.63 662.76 701.04 667.37 698.26 667.90 C 696.63 668.18 695.45 669.59 695.45 671.24 C 695.45 672.88 696.64 674.29 698.26 674.57 Z"
                                fill="#000000" fill-rule="nonzero" node-id="55" stroke="none" target-height="11.983459"
                                target-width="48.551636" target-x="695.44556" target-y="662.6782" />
                            <path
                                d="M 511.39 286.08 C 511.28 287.41 511.74 288.73 512.66 289.71 C 513.57 290.69 514.85 291.24 516.19 291.23 C 520.44 291.23 521.82 285.51 521.82 284.65 C 521.76 282.08 519.67 280.02 517.09 279.99 C 514.64 279.99 511.78 282.46 511.39 286.01 C 511.39 286.04 511.39 286.06 511.39 286.08 Z"
                                fill="#000000" fill-rule="nonzero" node-id="57" stroke="none" target-height="11.24588"
                                target-width="10.538055" target-x="511.2839" target-y="279.99405" />
                            <path
                                d="M 728.74 464.25 C 733.15 436.46 733.54 434.44 733.54 433.22 C 733.54 432.30 733.17 431.42 732.51 430.77 C 731.85 430.13 730.96 429.78 730.04 429.80 C 726.09 429.80 726.73 431.72 721.35 465.94 C 721.26 466.51 721.21 467.08 721.21 467.65 C 721.21 470.07 722.27 471.32 724.33 471.32 C 725.32 471.29 726.31 471.09 727.24 470.73 C 727.57 471.71 727.73 472.74 727.72 473.78 C 727.72 479.21 726.48 486.40 726.48 488.08 C 726.48 488.76 726.91 491.71 729.96 491.71 C 732.78 491.71 733.45 488.56 733.46 488.45 C 734.11 484.42 734.49 480.36 734.58 476.28 C 734.58 467.55 732.06 465.37 728.74 464.25 Z"
                                fill="#000000" fill-rule="nonzero" node-id="59" stroke="none" target-height="61.936462"
                                target-width="13.37262" target-x="721.2057" target-y="429.77798" />
                            <path
                                d="M 603.09 434.07 C 603.09 430.80 599.81 430.66 598.90 430.47 C 583.43 431.90 576.35 445.05 576.35 448.86 C 576.35 449.78 576.72 450.65 577.38 451.28 C 578.04 451.91 578.93 452.24 579.84 452.19 C 583.56 452.19 582.66 447.46 588.26 442.30 C 596.73 434.52 603.09 440.05 603.09 434.07 Z"
                                fill="#000000" fill-rule="nonzero" node-id="61" stroke="none" target-height="21.76706"
                                target-width="26.746033" target-x="576.3489" target-y="430.46957" />
                            <path
                                d="M 643.72 491.47 C 643.72 491.50 643.71 491.54 643.71 491.58 C 625.86 492.39 622.60 504.36 622.60 505.28 C 622.66 507.09 624.16 508.52 625.97 508.49 C 630.65 508.49 629.88 498.75 642.90 498.75 C 644.62 498.75 646.73 498.93 647.93 498.93 C 651.49 498.93 651.75 496.16 651.75 495.45 C 651.75 491.64 647.23 491.89 643.72 491.47 Z"
                                fill="#000000" fill-rule="nonzero" node-id="63" stroke="none" target-height="17.049988"
                                target-width="29.152405" target-x="622.60175" target-y="491.46555" />
                            <path
                                d="M 599.31 455.34 C 596.03 455.34 595.45 460.48 595.45 462.35 C 595.47 464.05 595.75 465.74 596.27 467.37 C 596.75 468.69 598.04 469.53 599.45 469.45 C 602.92 469.45 602.77 465.26 603.19 462.18 C 602.36 458.07 602.52 455.34 599.31 455.34 Z"
                                fill="#000000" fill-rule="nonzero" node-id="65" stroke="none" target-height="14.190033"
                                target-width="7.7456055" target-x="595.44745" target-y="455.3441" />
                            <path
                                d="M 622.83 469.03 C 625.99 469.03 626.98 464.52 626.98 461.37 C 626.98 456.71 624.62 455.44 623.09 455.44 C 619.80 455.44 619.51 458.42 619.51 459.48 C 619.51 459.70 619.53 459.84 619.53 459.85 C 619.53 460.54 619.56 461.24 619.56 461.93 L 619.44 461.93 C 619.44 462.71 619.39 463.51 619.39 464.29 C 619.39 468.90 622.16 469.03 622.75 469.03 Z"
                                fill="#000000" fill-rule="nonzero" node-id="67" stroke="none" target-height="13.589539"
                                target-width="7.5849" target-x="619.3903" target-y="455.43924" />
                            <path
                                d="M 833.35 300.54 C 833.76 300.54 836.81 300.46 836.81 296.36 C 836.81 296.26 836.57 281.64 836.57 280.29 C 836.57 279.61 836.65 278.93 836.69 278.18 C 840.01 279.88 841.47 280.63 842.95 280.63 C 844.12 280.63 845.29 280.15 847.40 279.30 C 847.40 279.81 847.40 280.29 847.40 280.75 C 847.40 282.58 847.31 290.61 847.31 294.67 C 847.31 294.77 847.30 294.95 847.30 295.18 C 847.30 296.54 847.54 299.85 850.83 299.85 C 853.95 299.85 854.40 297.49 854.40 292.25 C 854.40 290.13 854.32 287.54 854.32 284.44 C 854.32 280.05 854.41 276.46 854.41 273.58 C 854.41 266.38 853.89 263.55 850.25 263.55 C 846.40 263.55 846.62 267.80 842.72 273.43 C 838.71 271.24 838.46 264.40 833.89 264.40 C 832.02 264.40 831.22 265.68 830.37 266.09 C 828.89 266.77 827.99 268.31 828.14 269.94 C 828.14 273.59 829.28 295.76 830.24 298.39 C 830.73 299.69 831.97 300.55 833.35 300.54 Z"
                                fill="#000000" fill-rule="nonzero" node-id="69" stroke="none" target-height="36.994446"
                                target-width="26.41394" target-x="827.99243" target-y="263.5507" />
                            <path
                                d="M 803.99 287.11 C 803.99 290.31 804.25 304.67 817.07 304.67 C 819.49 304.67 828.57 303.98 828.57 299.30 C 828.57 298.89 828.29 296.03 825.38 296.03 C 823.88 296.03 820.96 297.65 817.13 297.65 C 814.11 297.65 813.27 296.73 812.84 295.82 C 811.79 293.57 811.23 291.12 811.18 288.64 C 815.38 288.08 821.84 287.68 821.84 283.53 C 821.84 282.71 821.51 281.92 820.92 281.35 C 820.34 280.77 819.55 280.45 818.73 280.45 C 817.39 280.45 819.30 280.47 811.09 282.15 L 811.09 271.95 C 811.60 271.95 811.99 271.94 812.34 271.94 C 813.74 271.94 815.64 272.24 817.40 272.24 C 818.75 272.24 823.49 271.84 823.49 268.69 C 823.49 265.38 821.59 265.17 812.17 265.17 C 810.76 265.17 809.18 265.17 807.42 265.17 C 803.20 265.17 803.11 267.93 803.11 268.24 L 803.11 268.27 C 803.11 270.25 804.70 269.96 804.70 272.80 C 804.70 274.17 803.99 283.14 803.99 287.11 Z"
                                fill="#000000" fill-rule="nonzero" node-id="71" stroke="none" target-height="39.505035"
                                target-width="25.45996" target-x="803.11395" target-y="265.16733" />
                            <path
                                d="M 859.70 270.85 C 859.70 271.47 859.70 272.02 859.70 272.56 C 859.70 273.99 859.60 278.07 859.60 283.27 C 859.60 301.32 859.90 304.30 863.68 304.30 C 864.65 304.30 867.15 303.86 867.15 299.88 C 867.15 298.16 866.51 287.20 866.51 287.10 C 866.51 283.92 877.16 280.77 877.16 272.00 C 877.16 266.97 872.17 262.02 865.85 262.02 C 862.53 262.02 857.53 263.77 857.53 267.08 C 857.67 268.59 858.46 269.97 859.70 270.85 Z M 866.14 268.76 C 868.36 268.76 870.11 270.59 870.11 271.97 C 870.11 273.30 869.00 275.17 866.38 277.04 C 866.25 274.07 866.13 271.49 866.01 268.76 C 866.05 268.76 866.10 268.76 866.14 268.76 Z"
                                fill="#000000" fill-rule="nonzero" node-id="73" stroke="none" target-height="42.27545"
                                target-width="19.625977" target-x="857.5324" target-y="262.0243" />
                            <path
                                d="M 906.43 273.63 C 908.76 274.74 909.75 276.20 909.75 278.34 C 909.75 279.06 909.66 279.36 909.66 279.69 C 909.66 283.89 909.60 287.26 909.60 289.93 C 909.60 296.96 910.04 299.18 913.21 299.18 C 914.23 299.17 915.21 298.74 915.92 297.99 C 916.63 297.24 917.01 296.24 916.97 295.21 C 916.97 281.11 916.31 278.90 916.31 277.04 C 916.31 270.14 924.56 266.48 924.56 262.81 C 924.50 261.18 923.19 259.87 921.56 259.81 C 919.71 259.81 919.29 260.63 911.40 268.90 C 908.88 267.61 906.59 266.51 905.01 264.16 C 904.52 263.40 903.67 262.93 902.76 262.93 C 901.37 263.05 900.18 263.98 899.73 265.30 C 899.28 266.62 899.66 268.08 900.69 269.02 C 902.50 270.70 904.27 272.61 906.43 273.63 Z"
                                fill="#000000" fill-rule="nonzero" node-id="75" stroke="none" target-height="39.37744"
                                target-width="25.278198" target-x="899.28253" target-y="259.80588" />
                            <path
                                d="M 880.67 269.52 C 882.66 269.52 882.83 268.18 884.68 268.18 C 885.10 268.19 885.51 268.24 885.91 268.35 C 885.91 268.86 885.91 269.37 885.91 269.88 C 885.91 276.34 885.84 281.46 885.84 285.50 C 885.84 296.47 886.37 299.45 890.34 299.45 C 893.79 299.45 894.73 295.68 894.73 295.01 C 894.73 292.84 893.02 293.25 892.96 290.67 C 892.83 285.06 892.48 271.18 892.48 270.76 C 892.48 270.22 892.57 269.68 892.61 269.16 C 893.94 269.05 898.40 269.46 898.40 265.63 C 898.40 263.01 895.84 262.21 894.68 262.13 C 891.62 261.94 888.55 261.62 885.48 261.62 C 883.53 261.62 877.06 261.80 877.06 265.89 C 877.09 267.87 878.69 269.48 880.67 269.52 Z"
                                fill="#000000" fill-rule="nonzero" node-id="77" stroke="none" target-height="37.827454"
                                target-width="21.341797" target-x="877.06213" target-y="261.62155" />
                        </svg>
                        <p class="text-gray-600 text-sm dark:text-gray-400">未查询到结果, 换个姿势搜索吧~</p>
                    </div>
                </div>
                <!-- Modal footer -->
                <div class="p-4 md:p-5 border-t text-xs flex items-center text-gray-400 dark:border-gray-600">
                    <!-- Esc 退出提示 -->
                    <span class="px-2 py-[1px] flex-none border rounded dark:border-gray-600">Esc</span>
                    <span class="text-gray-400 ml-2">关闭</span>

                    <!-- 底层技术介绍 -->
                    <span class="ml-auto">基于 <a href="https://lucene.apache.org/" target="_blank"
                            class="underline">Apache Lucene</a> 全文检索引擎开发</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref, onBeforeUnmount, watch } from 'vue'
import { getArticleSearchPageList } from '@/api/frontend/search'
import { initCollapses, initDropdowns, initModals } from 'flowbite'
import { useBlogSettingsStore } from '@/stores/blogsettings'
import { useUserStore } from '@/stores/user'
import { useRouter, useRoute } from 'vue-router'
import { showMessage } from '@/composables/util'
import { Modal } from 'flowbite'
import { useDark, useToggle } from '@vueuse/core'

const isDark = useDark({disableTransition: false})
const toggleDark = useToggle(isDark)

// 初始化 flowbit 相关组件
onMounted(() => {
    initCollapses();
    initDropdowns();
    initModals();

    // 初始化
    const $modalElement = document.querySelector('#search-modal');
    searchModal.value = new Modal($modalElement, modalOptions, instanceOptions);

    // 注册键盘事件监听器
    window.addEventListener('keydown', handleKeyDown);
})

const router = useRouter()
const route = useRoute()

// 当前路由地址
const currPath = ref(route.path)

// 引入博客设置信息 store
const blogSettingsStore = useBlogSettingsStore()

// 是否登录，通过 userStore 中的 userInfo 对象是否有数据来判断
const userStore = useUserStore()
// 获取 userInfo 对象所有属性名称的数组
const keys = Object.keys(userStore.userInfo)
// 若大于零，则表示用户已登录
const isLogined = ref(keys.length > 0)

// 退出登录
const logout = () => {
    userStore.logout()
    // 标记为未登录
    isLogined.value = false
    showMessage('退出登录成功')
}

// 初始化 flowbit 相关组件
const searchModal = ref(null)

const modalOptions = {
    placement: 'top-center', // 弹框位置
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
    id: 'searchModalEl',
    override: true
};

// 初始化 flowbit 相关组件
onMounted(() => {
    initCollapses();
    initDropdowns();
    initModals();

    // 初始化
    const $modalElement = document.querySelector('#search-modal');
    searchModal.value = new Modal($modalElement, modalOptions, instanceOptions);
})

// 点击搜索框
const clickSearchBtn = () => {
    searchModal.value.show()
    // 激活搜索框
    searchInputRef.value.focus()
}

onBeforeUnmount(() => {
    // 在组件销毁前移除事件监听器，防止内存泄漏
    window.removeEventListener('keydown', handleKeyDown);
})

// 搜索输入框引用
const searchInputRef = ref(null)

// 键盘监听
const handleKeyDown = (event) => {
    // 检查是否按下了 Ctrl 键和 K 键
    if (event.ctrlKey && event.key === 'k') {
        // 阻止激活浏览器本身的搜索框
        event.preventDefault()
        console.log('ctrl k 被按了')

        searchModal.value.show()

        // 激活搜索框
        searchInputRef.value.focus()
    }
}

// 文章搜索结果
const searchArticles = ref([])
// 当前页码，给了一个默认值 1
const current = ref(1)
// 总数据量，给了个默认值 0
const total = ref(0)
// 每页显示的数据量，给了个默认值 10
const size = ref(10)
// 总共多少页
const pages = ref(0)
// 搜索关键词
const searchWord = ref('')

// 侦听属性，监听 searchWord 变量变化
watch(searchWord, (newText, oldText) => {
    console.log(`新值: ${newText}, 老值: ${oldText}`)
    if (newText && newText !== oldText) { // 若搜索关键词不为空，且和之前的值不相同
        renderSearchArticles({ current: current.value, size: size.value, word: newText })
    } else if (newText == '') { // 搜索词为空
        // 置空
        searchArticles.value = []
    }
})

// 搜索 Loading
const searchLoading = ref(false)

// 请求后台检索接口
function renderSearchArticles(data) {
    // 显示加载 Loading
    searchLoading.value = true
    getArticleSearchPageList(data).then(res => {
        console.log(res)
        if (res.success) {
            searchArticles.value = res.data
            current.value = res.current
            size.value = res.size
            total.value = res.total
            pages.value = res.pages
        }
    }).finally(() => searchLoading.value = false) // 隐藏加载 Loading
}

// 渲染下一页搜索结果
const nextPage = () => {
    renderSearchArticles({ current: current.value + 1, size: size.value, word: searchWord.value })
}

// 渲染上一页搜索结果
const prePage = () => {
    renderSearchArticles({ current: current.value - 1, size: size.value, word: searchWord.value })
}

// 点击搜索结果，跳转文章详情页
const jumpToArticleDetailPage = (articleId) => {
    // 隐藏搜索对话框
    searchModal.value.hide()
    // 路由跳转
    router.push('/article/' + articleId)
}

</script>

<style scoped>
.items-start {
    align-items: flex-start !important;
}

.vt-switch {
    --vt-c-divider-light-1: rgba(60, 60, 60, .29);
    --vt-c-divider: var(--vt-c-divider-light-1);
    --vt-c-white-mute: #f1f1f1;
    --vt-c-bg-mute: var(--vt-c-white-mute);
    position: relative;
    border-radius: 11px;
    display: block;
    width: 40px;
    height: 22px;
    flex-shrink: 0;
    border: 1px solid var(--vt-c-divider);
    background-color: var(--vt-c-bg-mute);
    transition: border-color .25s, background-color .25s;
}

.dark .vt-switch {
    --vt-c-white-mute: #f1f1f1;
    background-color: #2f2f2f;
    transition: border-color .25s, background-color .25s;
}

.vt-switch-check {
    --vt-c-white: #ffffff;
    --vt-shadow-1: 0 1px 2px rgba(0, 0, 0, .04), 0 1px 2px rgba(0, 0, 0, .06);
    position: absolute;
    top: 1px;
    left: 1px;
    width: 18px;
    height: 18px;
    border-radius: 50%;
    background-color: var(--vt-c-white);
    box-shadow: var(--vt-shadow-1);
    transition: background-color .25s, transform .25s;
}

.dark .vt-switch-appearance .vt-switch-check {
    transform: translate(18px);
}

.dark .vt-switch-check {
    --vt-c-black: #1a1a1a;
    background-color: var(--vt-c-black);
}

.vt-switch-icon {
    position: relative;
    display: block;
    width: 18px;
    height: 18px;
    border-radius: 50%;
    overflow: hidden;
}

.vt-switch-icon svg {
    position: absolute;
    top: 3px;
    left: 3px;
    width: 12px;
    height: 12px;
    fill: rgba(60, 60, 60, .7);
}

.dark .vt-switch-icon svg {
    fill: rgba(255, 255, 255, .87);
    transition: opacity .25s;
}

.dark .vt-switch-appearance-moon {
    opacity: 1;
}

.vt-switch-appearance-sun {
    opacity: 1;
}
</style>