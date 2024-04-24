<template>
  <div>
    <!-- 表头分页查询条件， shadow="never" 指定 card 卡片组件没有阴影 -->
    <el-card shadow="never" class="mb-5">
      <!-- flex 布局，内容垂直居中 -->
      <div class="flex items-center">
        <el-text>知识库标题</el-text>
        <div class="ml-3 w-52 mr-5"><el-input v-model="searchWikiTitle" placeholder="请输入（模糊查询）" clearable /></div>

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
      <!-- 新增知识库按钮 -->
      <div class="mb-5">
        <el-button type="primary" @click="addWikiBtnClick">
          <el-icon class="mr-1">
            <Plus />
          </el-icon>
          新增知识库</el-button>
      </div>

      <!-- 分页列表 -->
      <el-table :data="tableData" border stripe v-loading="tableLoading" table-layout="auto">
        <el-table-column type="index" label="序号" width="60" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="cover" label="封面">
          <template #default="scope">
            <el-image style="width: 100px;" :src="scope.row.cover" />
          </template>
        </el-table-column>
        <el-table-column prop="isTop" label="是否置顶">
          <template #default="scope">
            <el-switch @change="handleIsTopChange(scope.row)" v-model="scope.row.isTop" inline-prompt
              :active-icon="Check" :inactive-icon="Close" />
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="发布时间" />
        <el-table-column prop="isPublish" label="是否发布">
          <template #default="scope">
            <el-switch @change="handleIsPublishChange(scope.row)" v-model="scope.row.isPublish" inline-prompt
              :active-icon="Check" :inactive-icon="Close" />
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="160">
          <template #default="scope">
            <el-tooltip class="box-item" effect="dark" content="编辑" placement="bottom">
              <el-button size="small" @click="showEditWikiDialog(scope.row)" :icon="Edit" circle>
              </el-button>
            </el-tooltip>

            <el-tooltip class="box-item" effect="dark" content="编辑目录" placement="bottom">
              <el-button size="small" @click="showEditWikiCatalogDialog(scope.row)" :icon="Tickets" circle>
              </el-button>
            </el-tooltip>

            <el-tooltip class="box-item" effect="dark" content="预览" placement="bottom">
              <el-button size="small" :icon="View" circle>
              </el-button>
            </el-tooltip>

            <el-tooltip class="box-item" effect="dark" content="删除" placement="bottom">
              <el-button type="danger" size="small" @click="deleteWikiSubmit(scope.row)" :icon="Delete" circle>
              </el-button>
            </el-tooltip>

          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="mt-10 flex justify-center">
        <el-pagination v-model:current-page="current" v-model:page-size="size" :page-sizes="[10, 20, 50]" :small="false"
          :background="true" layout="total, sizes, prev, pager, next, jumper" :total="total"
          @size-change="handleSizeChange" @current-change="getTableData" />
      </div>

    </el-card>

    <!-- 新增知识库 -->
    <FormDialog ref="formDialogRef" title="新增知识库" destroyOnClose @submit="onSubmit">
      <el-form ref="formRef" :rules="rules" :model="form">
        <el-form-item label="标题" prop="title" label-width="80px" size="large">
          <el-input v-model="form.title" placeholder="请输入知识库标题" maxlength="20" show-word-limit clearable />
        </el-form-item>
        <el-form-item label="封面" prop="cover" label-width="80px" size="large">
          <el-upload class="avatar-uploader" action="#" :on-change="handleCoverChange" :auto-upload="false"
            :show-file-list="false">
            <img v-if="form.cover" :src="form.cover" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon">
              <Plus />
            </el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="摘要" prop="summary" label-width="80px" size="large">
          <!-- :rows="3" 指定 textarea 默认显示 3 行 -->
          <el-input v-model="form.summary" :rows="3" maxlength="30" show-word-limit type="textarea"
            placeholder="请输入知识库摘要" clearable />
        </el-form-item>
      </el-form>
    </FormDialog>

    <!-- 编辑知识库 -->
    <FormDialog ref="editFormDialogRef" title="编辑知识库" destroyOnClose @submit="onEditWikiSubmit">
      <el-form ref="editFormRef" :rules="rules" :model="editForm">
        <el-form-item label="标题" prop="title" label-width="80px" size="large">
          <el-input v-model="editForm.title" placeholder="请输入知识库标题" maxlength="20" show-word-limit clearable />
        </el-form-item>
        <el-form-item label="封面" prop="cover" label-width="80px" size="large">
          <el-upload class="avatar-uploader" action="#" :on-change="handleUpdateCoverChange" :auto-upload="false"
            :show-file-list="false">
            <img v-if="editForm.cover" :src="editForm.cover" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon">
              <Plus />
            </el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="摘要" prop="summary" label-width="80px" size="large">
          <!-- :rows="3" 指定 textarea 默认显示 3 行 -->
          <el-input v-model="editForm.summary" :rows="5" maxlength="200" show-word-limit type="textarea"
            placeholder="请输入知识库摘要" clearable />
        </el-form-item>
      </el-form>
    </FormDialog>

    <WikiCatalogEditDialog ref="editCatalogFormDialogRef" title="编辑目录" width="70%" destroyOnClose>
    </WikiCatalogEditDialog>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { Search, RefreshRight, Check, Close, Delete, Edit, Tickets, View } from '@element-plus/icons-vue'
import moment from 'moment'
import { getWikiPageList, addWiki, updateWikiIsTop, updateWikiIsPublish, deleteWiki, updateWiki } from '@/api/admin/wiki'
import { showMessage, showModel } from '@/composables/util'
import FormDialog from '@/components/FormDialog.vue'
import WikiCatalogEditDialog from '@/components/WikiCatalogEditDialog.vue'
import { uploadFile } from '@/api/admin/file'

// 模糊搜索的知识库标题
const searchWikiTitle = ref('')
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
  searchWikiTitle.value = ''
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
  // 调用后台分页接口，并传入所需参数
  getWikiPageList({ current: current.value, size: size.value, startDate: startDate.value, endDate: endDate.value, title: searchWikiTitle.value })
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
getTableData()

// 每页展示数量变更事件
const handleSizeChange = (chooseSize) => {
  console.log('选择的页码' + chooseSize)
  size.value = chooseSize
  getTableData()
}

// 对话框引用
const formDialogRef = ref(null)

// 新增知识库按钮点击事件, 弹出 Dialog
const addWikiBtnClick = () => {
  formDialogRef.value.open()
}

// 表单引用
const formRef = ref(null)
// 表单对象
const form = reactive({
  title: '',
  cover: '',
  summary: ''
})

// 表单校验规则
const rules = {
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' },
    { min: 1, max: 20, message: '标题要求大于1个字符，小于20个字符', trigger: 'blur' },
  ],
  summary: [
    { required: true, message: '请输入摘要', trigger: 'blur' },
    { min: 1, max: 200, message: '摘要要求大于1个字符，小于30个字符', trigger: 'blur' },
  ],
  cover: [{ required: true, message: '请上传封面', trigger: 'blur' }],
}

// 上传封面图片
const handleCoverChange = (file) => {
  // 表单对象
  let formData = new FormData()
  // 添加 file 字段，并将文件传入 
  formData.append('file', file.raw)
  uploadFile(formData).then((e) => {
    // 响参失败，提示错误消息
    if (e.success == false) {
      let message = e.message
      showMessage(message, 'error')
      return
    }

    // 成功则设置表单对象中的封面链接，并提示上传成功
    form.cover = e.data.url
    showMessage('上传成功')
  })
}

const onSubmit = () => {
  // 先验证 form 表单字段
  formRef.value.validate((valid) => {
    if (!valid) {
      console.log('表单验证不通过')
      return false
    }

    // 显示提交按钮 loading
    formDialogRef.value.showBtnLoading()
    addWiki(form).then((res) => {
      if (!res.success) {
        // 获取服务端返回的错误消息
        let message = res.message
        // 提示错误消息
        showMessage(message, 'error')
        return
      }

      showMessage('添加成功')
      // 将表单中数据置空
      form.title = ''
      form.cover = ''
      form.summary = ''
      // 隐藏对话框
      formDialogRef.value.close()
      // 重新请求分页接口，渲染数据
      getTableData()
    }).finally(() => formDialogRef.value.closeBtnLoading()) // 隐藏提交按钮 loading

  })
}

// 更新置顶
const handleIsTopChange = (row) => {
  updateWikiIsTop({ id: row.id, isTop: row.isTop }).then((res) => {
    // 重新请求分页接口，渲染列表数据
    getTableData()

    if (res.success == false) {
      // 获取服务端返回的错误消息
      let message = res.message
      // 提示错误消息
      showMessage(message, 'error')
      return
    }

    showMessage(row.isTop ? '置顶成功' : "已取消置顶")
  })
}

// 更新发布状态
const handleIsPublishChange = (row) => {
  updateWikiIsPublish({ id: row.id, isPublish: row.isPublish }).then((res) => {
    // 重新请求分页接口，渲染列表数据
    getTableData()

    if (res.success == false) {
      // 获取服务端返回的错误消息
      let message = res.message
      // 提示错误消息
      showMessage(message, 'error')
      return
    }

    showMessage(row.isPublish ? '发布成功' : "已取消发布")
  })
}

// 删除知识库
const deleteWikiSubmit = (row) => {
  showModel('是否确定要删除该知识库？').then(() => {
    deleteWiki(row.id).then((res) => {
      if (res.success == false) {
        // 获取服务端返回的错误消息
        let message = res.message
        // 提示错误消息
        showMessage(message, 'error')
        return
      }

      showMessage('删除成功')
      // 重新请求分页接口，渲染数据
      getTableData()
    })
  }).catch((e) => {
    console.log('取消了')
  })
}

// 更新知识库对话框引用
const editFormDialogRef = ref(null)
// 弹出知识库编辑对话框

// 弹出知识库编辑对话框
const showEditWikiDialog = (row) => {
  editFormDialogRef.value.open()
  editForm.id = row.id
  editForm.title = row.title
  editForm.cover = row.cover
  editForm.summary = row.summary
}

// 表单引用
const editFormRef = ref(null)
// 表单对象
const editForm = reactive({
  id: null,
  title: '',
  cover: '',
  summary: ''
})

// 知识库编辑：上传封面图片
const handleUpdateCoverChange = (file) => {
  // 表单对象
  let formData = new FormData()
  // 添加 file 字段，并将文件传入 
  formData.append('file', file.raw)
  uploadFile(formData).then((e) => {
    // 响参失败，提示错误消息
    if (e.success == false) {
      let message = e.message
      showMessage(message, 'error')
      return
    }

    // 成功则设置表单对象中的封面链接，并提示上传成功
    editForm.cover = e.data.url
    showMessage('上传成功')
  })
}

// 编辑知识库提交事件
const onEditWikiSubmit = () => {
  // 先验证 form 表单字段
  editFormRef.value.validate((valid) => {
    if (!valid) {
      console.log('表单验证不通过')
      return false
    }

    // 显示提交按钮 loading
    editFormDialogRef.value.showBtnLoading()
    updateWiki(editForm).then((res) => {
      if (!res.success) {
        // 获取服务端返回的错误消息
        let message = res.message
        // 提示错误消息
        showMessage(message, 'error')
        return
      }

      showMessage('更新成功')
      // 将编辑表单中数据置空
      editForm.id = null
      editForm.title = ''
      editForm.cover = ''
      editForm.summary = ''
      // 隐藏对话框
      editFormDialogRef.value.close()
      // 重新请求分页接口，渲染数据
      getTableData()
    }).finally(() => editFormDialogRef.value.closeBtnLoading()) // 隐藏提交按钮 loading

  })
}

// 编辑目录对话框是否显示
const editCatalogFormDialogRef = ref(null)

// 编辑目录按钮点击事件
const showEditWikiCatalogDialog = (row) => {
    // 显示编辑目录对话框, 并传入知识库 ID
    editCatalogFormDialogRef.value.open(row.id)
}

</script>

<style scoped>
/* 封面图片样式 */
.avatar-uploader .avatar {
  width: 200px;
  height: 100px;
  display: block;
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 200px;
  height: 100px;
  text-align: center;
}
</style>