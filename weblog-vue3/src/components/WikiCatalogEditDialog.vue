<template>
    <el-dialog v-model="dialogVisible" :title="title" :width="width" :destroy-on-close="destroyOnClose"
        :draggable="true" :close-on-click-modal="true" :close-on-press-escape="true">

        <!-- 添加一级目录按钮 -->
        <div class="mb-5">
            <el-button type="primary" @click="addCatalogDialogRef.open">
                <el-icon class="mr-1">
                    <Plus />
                </el-icon>
                添加目录</el-button>
        </div>

        <!-- 内容 -->
        <div id="accordion-flush" data-accordion="collapse"
            data-active-classes="bg-white dark:bg-gray-900 text-gray-900 dark:text-white"
            data-inactive-classes="text-gray-500 dark:text-gray-400">
            <h2 id="accordion-flush-heading-1" v-for="(catalog, index) in catalogs" :key="index">
                <!-- 一级目录 -->
                <button type="button" class="hover:bg-gray-100 py-3 px-3 rounded-lg flex items-center w-full font-medium rtl:text-right 
                    text-gray-500 dark:border-gray-700 dark:text-gray-400"
                    data-accordion-target="#accordion-flush-body-1" aria-expanded="true"
                    aria-controls="accordion-flush-body-1">
                    <svg data-accordion-icon class="w-3 h-3 mr-2 rotate-180 shrink-0" aria-hidden="true"
                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M9 5 5 1 1 5" />
                    </svg>
                    <!-- 一级目录标题 -->
                    <span v-if="!catalog.editing" class="flex items-center grow" v-html="catalog.title"></span>
                    <!-- 标题输入框 -->
                    <span v-else class="w-full">
                        <el-input v-model="catalog.title" @blur="onEditTitleInputBlur(catalog.id)" placeholder="请输入目录标题"
                            clearable />
                    </span>
                    <!-- 右侧竖着的三个点：操作图标 -->
                    <div class="hover:bg-gray-200 rounded py-2 px-2">
                        <el-dropdown @command="handleCommand">
                            <span class="el-dropdown-link">
                                <svg t="1705472726484" class="icon w-5 h-5 outline-none" viewBox="0 0 1024 1024"
                                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5968" width="200"
                                    height="200">
                                    <path
                                        d="M512 298.6496a85.3504 85.3504 0 1 0 0-170.6496 85.3504 85.3504 0 0 0 0 170.6496z"
                                        fill="#707070" p-id="5969"></path>
                                    <path
                                        d="M512 512m-85.3504 0a85.3504 85.3504 0 1 0 170.7008 0 85.3504 85.3504 0 1 0-170.7008 0Z"
                                        fill="#707070" p-id="5970"></path>
                                    <path
                                        d="M512 896a85.3504 85.3504 0 1 0 0-170.7008 85.3504 85.3504 0 0 0 0 170.7008z"
                                        fill="#707070" p-id="5971"></path>
                                </svg>
                            </span>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item
                                        :command="{ id: catalog.id, sort: catalog.sort, action: 'rename' }">
                                        <el-icon>
                                            <EditPen />
                                        </el-icon>
                                        重命名
                                    </el-dropdown-item>
                                    <el-dropdown-item
                                        :command="{ id: catalog.id, sort: catalog.sort, action: 'addArticle' }">
                                        <el-icon>
                                            <Plus />
                                        </el-icon>
                                        添加文章
                                    </el-dropdown-item>
                                    <el-dropdown-item
                                        :command="{ id: catalog.id, sort: catalog.sort, action: 'moveUp' }" divided
                                        v-if="(index + 1) > 1">
                                        <el-icon>
                                            <Top />
                                        </el-icon>
                                        上移
                                    </el-dropdown-item>
                                    <el-dropdown-item
                                        :command="{ id: catalog.id, sort: catalog.sort, action: 'moveDown' }"
                                        v-if="(index + 1) < catalogs.length">
                                        <el-icon>
                                            <Bottom />
                                        </el-icon>
                                        下移
                                    </el-dropdown-item>
                                    <el-dropdown-item
                                        :command="{ id: catalog.id, sort: catalog.sort, action: 'removeFromCatalog' }"
                                        divided>
                                        <el-icon>
                                            <DocumentRemove />
                                        </el-icon>
                                        移出目录
                                    </el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </div>
                </button>

                <!-- 二级目录 -->
                <ul v-if="catalog.children && catalog.children.length > 0">
                    <VueDraggable ref="el" v-model="catalog.children" @end="onDragEnd">
                        <li v-for="(childCatalog, index2) in catalog.children" :key="index2"
                            class="flex items-center ps-10 py-2 pe-3 rounded-lg hover:bg-gray-100">
                            <!-- 二级标题 -->
                            <span class="w-full">
                                <span v-if="!childCatalog.editing" v-html="childCatalog.title"
                                    class="flex items-center"></span>
                                <span v-else>
                                    <el-input v-model="childCatalog.title" autofocus="true"
                                        @blur="onEditTitleInputBlur(childCatalog.id)" placeholder="请输入目录标题" clearable />
                                </span>
                            </span>
                            <span class="grow"></span>
                            <!-- 重命名 -->
                            <span class="hover:bg-gray-200 rounded py-2 px-2 ml-2 mr-2" @click="editTitle(item.id)">
                                <svg t="1705474417719" class="icon w-4 h-4" viewBox="0 0 1024 1024" version="1.1"
                                    xmlns="http://www.w3.org/2000/svg" p-id="8306" width="200" height="200">
                                    <path
                                        d="M402.24 753.12l417.984-417.952a35.552 35.552 0 0 0 0-50.304l-75.424-75.424a35.552 35.552 0 0 0-50.304 0L276.576 627.392l-8.992 134.72 134.688-8.992zM193.664 801.92l13.76-205.92L644.192 159.168a106.656 106.656 0 0 1 150.848 0l75.424 75.424a106.656 106.656 0 0 1 0 150.848L433.632 822.304l-205.92 13.728A32 32 0 0 1 193.6 801.92zM644.224 259.744l-50.272 50.24 125.696 125.76 50.272-50.304-125.696-125.696z"
                                        fill="#707070" p-id="8307"></path>
                                </svg>
                            </span>
                            <!-- 移出目录 -->
                            <el-tooltip class="box-item" effect="dark" content="移出目录" placement="right">
                                <span class="hover:bg-gray-200 rounded py-2 px-2"
                                    @click="removeArticleFromCatalog(childCatalog.id)">
                                    <svg t="1705473517473" class="icon w-4 h-4" viewBox="0 0 1024 1024" version="1.1"
                                        xmlns="http://www.w3.org/2000/svg" p-id="7174" width="200" height="200">
                                        <path
                                            d="M607.897867 768.043004c-17.717453 0-31.994625-14.277171-31.994625-31.994625L575.903242 383.935495c0-17.717453 14.277171-31.994625 31.994625-31.994625s31.994625 14.277171 31.994625 31.994625l0 351.94087C639.892491 753.593818 625.61532 768.043004 607.897867 768.043004z"
                                            fill="#707070" p-id="7175"></path>
                                        <path
                                            d="M415.930119 768.043004c-17.717453 0-31.994625-14.277171-31.994625-31.994625L383.935495 383.935495c0-17.717453 14.277171-31.994625 31.994625-31.994625 17.717453 0 31.994625 14.277171 31.994625 31.994625l0 351.94087C447.924744 753.593818 433.647573 768.043004 415.930119 768.043004z"
                                            fill="#707070" p-id="7176"></path>
                                        <path
                                            d="M928.016126 223.962372l-159.973123 0L768.043004 159.973123c0-52.980346-42.659499-95.983874-95.295817-95.983874L351.94087 63.989249c-52.980346 0-95.983874 43.003528-95.983874 95.983874l0 63.989249-159.973123 0c-17.717453 0-31.994625 14.277171-31.994625 31.994625s14.277171 31.994625 31.994625 31.994625l832.032253 0c17.717453 0 31.994625-14.277171 31.994625-31.994625S945.73358 223.962372 928.016126 223.962372zM319.946246 159.973123c0-17.545439 14.449185-31.994625 31.994625-31.994625l320.806316 0c17.545439 0 31.306568 14.105157 31.306568 31.994625l0 63.989249L319.946246 223.962372 319.946246 159.973123 319.946246 159.973123z"
                                            fill="#707070" p-id="7177"></path>
                                        <path
                                            d="M736.048379 960.010751 288.123635 960.010751c-52.980346 0-95.983874-43.003528-95.983874-95.983874L192.139761 383.591466c0-17.717453 14.277171-31.994625 31.994625-31.994625s31.994625 14.277171 31.994625 31.994625l0 480.435411c0 17.717453 14.449185 31.994625 31.994625 31.994625l448.096758 0c17.717453 0 31.994625-14.277171 31.994625-31.994625L768.215018 384.795565c0-17.717453 14.277171-31.994625 31.994625-31.994625s31.994625 14.277171 31.994625 31.994625l0 479.231312C832.032253 916.835209 789.028725 960.010751 736.048379 960.010751z"
                                            fill="#707070" p-id="7178"></path>
                                    </svg>
                                </span>
                            </el-tooltip>
                        </li>
                    </VueDraggable>
                </ul>
            </h2>
        </div>


    </el-dialog>

    <!-- 添加目录 -->
    <FormDialog ref="addCatalogDialogRef" title="添加目录" destroyOnClose @submit="onAddCatalogSubmit">
        <el-form ref="addCatalogFormRef" :rules="rules" :model="addCatalogForm">
            <el-form-item label="标题" prop="title" label-width="80px" size="large">
                <el-input v-model="addCatalogForm.title" placeholder="请输入目录标题" clearable />
            </el-form-item>
        </el-form>
    </FormDialog>

    <!-- 添加文章到目录 -->
    <FormDialog ref="addArticle2CatalogDialogRef" title="添加文章" width="80%" confirmText="添加" destroyOnClose
        @submit="onAddArticle2CatalogSubmit">
        <div>
            <!-- 表头分页查询条件， shadow="never" 指定 card 卡片组件没有阴影 -->
            <el-card shadow="never" class="mb-5">
                <!-- flex 布局，内容垂直居中 -->
                <div class="flex items-center">
                    <el-text>文章标题</el-text>
                    <div class="ml-3 w-52 mr-5"><el-input v-model="searchArticleTitle" placeholder="请输入（模糊查询）"
                            clearable />
                    </div>

                    <el-text>创建日期</el-text>
                    <div class="ml-3 w-30 mr-5">
                        <!-- 日期选择组件（区间选择） -->
                        <el-date-picker v-model="pickDate" type="daterange" range-separator="至" start-placeholder="开始时间"
                            end-placeholder="结束时间" size="default" :shortcuts="shortcuts" @change="datepickerChange" />
                    </div>

                    <el-button type="primary" class="ml-3" :icon="Search" @click="getTableData">查询</el-button>
                    <el-button class="ml-3" :icon="RefreshRight" @click="reset">重置</el-button>
                </div>
            </el-card>

            <el-card shadow="never">
                <!-- 分页列表 -->
                <el-table :data="tableData" border stripe v-loading="tableLoading" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="45" />
                    <el-table-column prop="id" label="ID" width="50" />
                    <el-table-column prop="title" label="标题" width="380" />
                    <el-table-column prop="cover" label="封面" width="180">
                        <template #default="scope">
                            <el-image style="width: 100px;" :src="scope.row.cover" />
                        </template>
                    </el-table-column>

                    <el-table-column prop="isPublish" label="是否发布" width="100">
                        <template #default="scope">
                            <el-switch v-model="scope.row.isPublish" inline-prompt :active-icon="Check"
                                :inactive-icon="Close" disabled />
                        </template>
                    </el-table-column>
                    <el-table-column prop="createTime" label="发布时间" />
                </el-table>

                <!-- 分页 -->
                <div class="mt-10 flex justify-center">
                    <el-pagination v-model:current-page="current" v-model:page-size="size" :page-sizes="[10, 20, 50]"
                        :small="false" :background="true" layout="total, sizes, prev, pager, next, jumper"
                        :total="total" @size-change="handleSizeChange" @current-change="getTableData" />
                </div>

            </el-card>


        </div>
    </FormDialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { showModel } from '@/composables/util'
import { VueDraggable } from 'vue-draggable-plus'
import FormDialog from '@/components/FormDialog.vue'
import { getArticlePageList } from '@/api/admin/article'
import { Search, RefreshRight, Check, Close } from '@element-plus/icons-vue'
import { getWikiCatalogs, updateWikiCatalogs } from '@/api/admin/wiki'

// 对话框是否显示
const dialogVisible = ref(false)

// 确认按钮加载 loading
const btnLoading = ref(false)
// 显示 loading
const showBtnLoading = () => btnLoading.value = true
// 隐藏 loading
const closeBtnLoading = () => btnLoading.value = false

// 对外暴露属性
const props = defineProps({
    title: String,
    width: {
        type: String,
        default: '40%'
    },
    destroyOnClose: {
        type: Boolean,
        default: false
    },
    confirmText: {
        type: String,
        default: '提交'
    }
})

// 目录数据
const catalogs = ref([])

// 当前知识库 ID
const currWikiId = ref(null)
// 获取当前知识库的目录数据
function getCatalogs() {
    getWikiCatalogs(currWikiId.value).then(res => {
        if (res.success) {
            catalogs.value = res.data
        }
    })
}

// 打开
const open = (wikiId) => {
    dialogVisible.value = true
    console.log("知识库 ID: " + wikiId)
    currWikiId.value = wikiId
    getCatalogs()
}

// 关闭
const close = () => dialogVisible.value = false

// 对外暴露方法
defineExpose({
    open,
    close,
    showBtnLoading,
    closeBtnLoading
})

// 编辑标题
const editTitle = (catalogId) => {
    // 根据目录 ID 查找对应的目录
    let targetCatalog = findCatalogById(catalogs.value, catalogId)
    // 将编辑状态置为 true
    targetCatalog.editing = true
}

// 查找对应的目录
function findCatalogById(catalogs, targetId) {
    for (const catalog of catalogs) {
        if (catalog.id === targetId) {
            return catalog;  // 找到目标目录，返回它
        }

        if (catalog.children && catalog.children.length > 0) {
            // 递归
            const foundInChildren = findCatalogById(catalog.children, targetId);
            if (foundInChildren) {
                return foundInChildren;  // 在子目录中找到目标目录，返回它
            }
        }
    }

    return null;  // 没有找到目标目录
}

// 标题输入框 blur 事件
const onEditTitleInputBlur = (catalogId) => {
    let targetCatalog = findCatalogById(catalogs.value, catalogId)
    // 将目标目录的 editing 字段置为 false
    targetCatalog.editing = false
    // 若标题被更新成了空字符串，则给个默认的标题, 提示用户需要输入标题
    targetCatalog.title = targetCatalog.title !== '' ? targetCatalog.title : '请输入标题'

    updateWikiCatalogsData()
}

// 移出目录
const removeCatalog = (catalogId) => {
    showModel('是否确定移除该目录？').then(() => {
        deleteCatalog(catalogs.value, catalogId)
        console.log(catalogs.value)
        updateWikiCatalogsData()
    }).catch((e) => {
        console.log('取消了')
    })
}

// 删除 catalogs 数组中对应的目录对象
function deleteCatalog(catalogs, targetId) {
    for (let i = 0; i < catalogs.length; i++) {
        const catalog = catalogs[i];

        // 一级目录删除
        if (catalog.id === targetId) {
            catalogs.splice(i, 1);
            return catalogs;
        }

        // 二级目录删除
        if (catalog.children) {
            // 递归
            catalog.children = deleteCatalog(catalog.children, targetId);
        }
    }

    return catalogs;
}

// 移出二级目录中的文章
const removeArticleFromCatalog = (catalogId) => {
    showModel('是否确定移除该篇文章？').then(() => {
        deleteCatalog(catalogs.value, catalogId)
        console.log(catalogs.value)
        updateWikiCatalogsData()
    }).catch((e) => {
        console.log('取消了')
    })
}

// 当前被编辑的目录 ID
const currCatalogId = ref(null)

// 一级目录: 操作按钮下拉菜单
const handleCommand = (command) => {
    if (command.action == 'rename') { // 重命名
        editTitle(command.id)
    } else if (command.action == 'moveUp') { // 上移
        catalogMove(command.id, command.sort, 'up')
    } else if (command.action == 'moveDown') { // 下移
        catalogMove(command.id, command.sort, 'down')
    } else if (command.action == 'removeFromCatalog') { // 移除出目录
        removeCatalog(command.id)
    } else if (command.action == 'addArticle') {
        // 记录当前被编辑的目录 ID
        currCatalogId.value = command.id
        getTableData()
        addArticle2CatalogDialogRef.value.open()
    }
}

// 菜单上移
function catalogMove(catalogId, sort, action) {
    // 被移动的目录
    let sourceCatalog = findCatalogById(catalogs.value, catalogId)
    // 目标目录
    let targetCatalog = getCatalogBySort(sort, action)

    // 若没有找到替换的目标目录，则 return
    if (targetCatalog === null) return

    // 各自的排序号
    let sourceSort = sourceCatalog.sort
    let targetSort = targetCatalog.sort
    // 互换排序号
    sourceCatalog.sort = targetSort
    targetCatalog.sort = sourceSort
    // 重新排序
    sortCatalogs()
    console.log(catalogs.value)

    updateWikiCatalogsData()
}

// 根据排序规则，得到其需要互换位置的目录
function getCatalogBySort(sort, action) {
    if (action == 'up') { // 上移
        // 复制一份临时数组，防止等会使用 reverse() 方法后，影响原数组的顺序
        const tmpCatalogs = [...catalogs.value]
        for (const catalog of tmpCatalogs.reverse()) {
            if (catalog.sort < sort) {
                return catalog;  // 找到目标目录，返回它
            }
        }
    } else if (action == 'down') {  // 下移
        for (const catalog of catalogs.value) {
            if (catalog.sort > sort) {
                return catalog;  // 找到目标目录，返回它
            }
        }

    }

    return null;  // 没有找到目标目录
}

// 重新排序目录
function sortCatalogs() {
    // 使用 sort 方法对 sort 字段升序排序
    catalogs.value = catalogs.value.sort((a, b) => a.sort - b.sort);
}

// 添加目录对话框引用
const addCatalogDialogRef = ref(null)
// 添加目录表单引用
const addCatalogFormRef = ref(null)

// 添加目录表单对象
const addCatalogForm = reactive({
    title: ''
})

// 规则校验
const rules = {
    title: [
        {
            required: true,
            message: '目录标题不能为空',
            trigger: 'blur',
        },
    ]
}

// 临时 ID
const tmpId = ref(-1)
// 添加一级目录提交事件
const onAddCatalogSubmit = () => {
    // 先验证 form 表单字段
    addCatalogFormRef.value.validate((valid) => {
        if (!valid) {
            console.log('表单验证不通过')
            return false
        }

        // 获取最后一个一级目录对象
        let lastCatalog = catalogs.value[catalogs.value.length - 1]

        // 构造新的目录对象
        let newCatalog = {
            id: tmpId.value, // 新的目录由于没有 ID, 这里给个临时 ID, 负数表示, 标识是一个新添加的目录  
            title: addCatalogForm.title,
            editing: false,
            level: 1,
            sort: lastCatalog.sort + 1, // 最后一个一级目录的 sort 值加一
            children: []
        }
        // 添加新目录
        catalogs.value.push(newCatalog)
        // 减一
        tmpId.value -= 1
        // 关闭表单对话框
        addCatalogDialogRef.value.close()
        // 将表单中的 title 置空
        addCatalogForm.title = ''
        console.log(catalogs.value)

        updateWikiCatalogsData()
    })
}

// 添加文章到目录对话框引用
const addArticle2CatalogDialogRef = ref(null)


// 模糊搜索的文章标题
const searchArticleTitle = ref('')
// 日期
const pickDate = ref('')

// 查询条件：开始结束时间
const startDate = reactive({})
const endDate = reactive({})

// 监听日期组件改变事件，并将开始结束时间设置到变量中
const datepickerChange = (e) => {
    startDate.value = moment(e[0]).format('YYYY-MM-DD')
    endDate.value = moment(e[1]).format('YYYY-MM-DD')

    console.log('开始时间：' + startDate.value + ', 结束时间：' + endDate.value)
}

const shortcuts = [
    {
        text: '最近一周',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            return [start, end]
        },
    },
    {
        text: '最近一个月',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            return [start, end]
        },
    },
    {
        text: '最近三个月',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            return [start, end]
        },
    },
]

// 重置
const reset = () => {
    pickDate.value = ''
    startDate.value = null
    endDate.value = null
    searchArticleTitle.value = ''
}

// 表格加载 Loading
const tableLoading = ref(false)
// 表格数据
const tableData = ref([])
// 当前页码，给了一个默认值 1
const current = ref(1)
// 总数据量，给了个默认值 0
const total = ref(0)
// 每页显示的数据量，给了个默认值 10
const size = ref(10)

// 获取分页数据
function getTableData() {
    // 显示表格 loading
    tableLoading.value = true
    // 调用后台分页接口，并传入所需参数 (指定只查询未被收录进知识库的文章)
    getArticlePageList({
        current: current.value, size: size.value, startDate: startDate.value, endDate: endDate.value,
        title: searchArticleTitle.value, type: 1
    })
        .then((res) => {
            if (res.success == true) {
                tableData.value = res.data
                current.value = res.current
                size.value = res.size
                total.value = res.total
            }
        })
        .finally(() => tableLoading.value = false) // 隐藏表格 loading
}

// 更新知识库目录数据
function updateWikiCatalogsData() {
    updateWikiCatalogs({ id: currWikiId.value, catalogs: catalogs.value }).then(res => {
        // 响参失败，提示错误消息
        if (res.success == false) {
            let message = res.message
            showMessage(message, 'error')
        }

        // 重新渲染目录数据
        getCatalogs(currWikiId.value)
    })
}

// 被选择的文章
const selectionArticles = ref([])
// 表格选择事件
const handleSelectionChange = (articles) => {
    console.log(articles)
    selectionArticles.value = articles
}

// 添加文章到目录下
const onAddArticle2CatalogSubmit = () => {
    // 校验是否选中文章
    if (!selectionArticles.value || selectionArticles.value.length === 0) {
        showMessage('请勾选需要添加的文章', 'warning')
        return
    }

    for (const catalog of catalogs.value) {
        // 找到当前被编辑的目录
        if (catalog.id === currCatalogId.value) {
            // 循环添加被选中的文章
            for (const selectionArticle of selectionArticles.value) {
                // 文章标题
                let articleTitle = selectionArticle.title
                // 构建新的二级目录
                let newCatalog = {
                    id: tmpId.value,
                    articleId: selectionArticle.id,
                    title: articleTitle,
                    editing: false,
                    level: 2,
                }
                // 添加到目录数组中
                catalog.children.push(newCatalog)
                tmpId.value -= 1
            }
        }
    }
    // 关闭对话框
    addArticle2CatalogDialogRef.value.close()
    // 置空被选择的文章
    selectionArticles.value = []
    updateWikiCatalogsData()
}

// 拖拽结束事件
const onDragEnd = (event) => {
    console.log('拖拽结束')
    updateWikiCatalogsData()
}
</script>