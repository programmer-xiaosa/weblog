<template>
    <!-- 使用 grid 网格布局，并指定列数为 2，高度占满全屏 -->
    <div class="grid grid-cols-2 h-screen">
        <!-- 默认占两列，order 用于指定排列顺序，md 用于适配非移动端（PC 端） -->
        <div class="col-span-2 order-2 p-10 md:col-span-1 md:order-1 bg-slate-900">
            <!-- 指定为 flex 布局，并设置为屏幕垂直水平居中，高度为 100% -->
            <div
                class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInLeft animate__fast">
                <h2 class="font-bold text-4xl mb-7 text-white">程序猿小萨 博客登录页</h2>
                <p class="text-white">一款由 Spring Boot + Mybaits Plus + Vue 3.2 + Vite 4 开发的前后端分离博客。</p>
                <!-- 指定图片宽度为父级元素的 1/2 -->
                <img src="@/assets/developer.png" class="w-1/2">
            </div>
        </div>
        <div class="flex flex-col col-span-2 order-1 md:col-span-1 md:order-2 bg-white dark:bg-gray-800">
            <!-- 白天黑夜开关，ml-auto 靠右显示 -->
            <label class="switch ml-auto mt-4 mr-4">
                <input type="checkbox" v-model="isLight" @click="toggleDark()">
                <span class="slider"></span>
            </label>

            <!-- flex-col 用于指定子元素垂直排列 -->
            <div
                class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInRight animate__fast">
                <!-- 大标题，设置字体粗细、大小、下边距 -->
                <h1 class="font-bold text-4xl mb-5 dark:text-white">欢迎回来</h1>
                <!-- 设置 flex 布局，内容垂直水平居中，文字颜色，以及子内容水平方向 x 轴间距 -->
                <div class="flex items-center justify-center mb-7 text-gray-400 space-x-2 dark:text-gray-500">
                    <!-- 左边横线，高度为 1px, 宽度为 16，背景色设置 -->
                    <span class="h-[1px] w-16 bg-gray-200 dark:bg-gray-700"></span>
                    <span>账号密码登录</span>
                    <!-- 右边横线 -->
                    <span class="h-[1px] w-16 bg-gray-200 dark:bg-gray-700"></span>
                </div>
                <!-- 引入 Element Plus 表单组件，移动端设置宽度为 5/6，PC 端设置为 2/5 -->
                <el-form class="w-5/6 md:w-2/5" ref="formRef" :rules="rules" :model="form">
                    <el-form-item prop="username">
                        <!-- 输入框组件 -->
                        <el-input size="large" v-model="form.username" placeholder="请输入用户名" :prefix-icon="User"
                            clearable />
                    </el-form-item>
                    <el-form-item prop="password">
                        <!-- 密码框组件 -->
                        <el-input size="large" type="password" v-model="form.password" placeholder="请输入密码"
                            :prefix-icon="Lock" clearable show-password />
                    </el-form-item>
                    <el-form-item>
                        <!-- 登录按钮，宽度设置为 100% -->
                        <el-button class="w-full mt-2" size="large" :loading="loading" type="primary"
                            @click="onSubmit">登录</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script setup>
// 引入 Element Plus 中的用户、锁图标
import { User, Lock } from '@element-plus/icons-vue'
import { login } from '@/api/admin/user'
import { ref, reactive, onMounted, onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'
import { showMessage } from '@/composables/util'
import { setToken } from '@/composables/cookie'
import { useUserStore } from '@/stores/user'
import { useDark, useToggle } from '@vueuse/core'

const userStore = useUserStore()

// 定义响应式的表单对象
const form = reactive({
    username: '',
    password: ''
})

const router = useRouter()
// 登录按钮加载
const loading = ref(false)

// 表单引用
const formRef = ref(null)
// 表单验证规则
const rules = {
    username: [
        {
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: '密码不能为空',
            trigger: 'blur',
        },
    ]
}

const onSubmit = () => {
    console.log('登录')
    // 先验证 form 表单字段
    formRef.value.validate((valid) => {
        if (!valid) {
            console.log('表单验证不通过')
            return false
        }
        // 开始加载
        loading.value = true

        // 调用登录接口
        login(form.username, form.password).then((res) => {
            console.log(res)
            // 判断是否成功
            if (res.success == true) {
                // 提示登录成功
                showMessage('登录成功')

                // 存储 Token 到 Cookie 中
                let token = res.data.token
                setToken(token)

                // 获取用户信息，并存储到全局状态中
                userStore.setUserInfo()

                // 跳转到后台首页
                router.push('/admin/index')
            } else {
                // 获取服务端返回的错误消息
                let message = res.message
                // 提示消息
                showMessage(message, 'error')
            }
        })
            .finally(() => {
                // 结束加载
                loading.value = false
            })
    })
}

// 按回车键后，执行登录事件
function onKeyUp(e) {
    console.log(e)
    if (e.key == 'Enter') {
        onSubmit()
    }
}

// 添加键盘监听
onMounted(() => {
    console.log('添加键盘监听')
    document.addEventListener('keyup', onKeyUp)
})

// 移除键盘监听
onBeforeUnmount(() => {
    document.removeEventListener('keyup', onKeyUp)
})

// 是否是白天
const isLight = ref(true)
const isDark = useDark({
  onChanged(dark) {
    // update the dom, call the API or something
    console.log('onchange:' + dark)
    if (dark) {
        // 给 body 添加 class="dark"
        document.documentElement.classList.add('dark');
        // 设置 switch 的值
        isLight.value = false
    } else {
        // 移除 body 中添加 class="dark"
        document.documentElement.classList.remove('dark');
        isLight.value = true
    }
  },
})
const toggleDark = useToggle(isDark)

</script>

<style scoped>
/* The switch - the box around the slider */
.switch {
    font-size: 14px;
    position: relative;
    display: inline-block;
    width: 3.5em;
    height: 2em;
}

/* Hide default HTML checkbox */
.switch input {
    opacity: 0;
    width: 0;
    height: 0;
}

/* The slider */
.slider {
    --background: #28096b;
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: var(--background);
    transition: .5s;
    border-radius: 30px;
}

.slider:before {
    position: absolute;
    content: "";
    height: 1.4em;
    width: 1.4em;
    border-radius: 50%;
    left: 10%;
    bottom: 15%;
    box-shadow: inset 8px -4px 0px 0px #fff000;
    background: var(--background);
    transition: .5s;
}

input:checked+.slider {
    background-color: #522ba7;
}

input:checked+.slider:before {
    transform: translateX(100%);
    box-shadow: inset 15px -4px 0px 15px #fff000;
}
</style>