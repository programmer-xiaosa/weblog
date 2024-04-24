import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getBlogSettingsDetail } from '@/api/frontend/blogsettings'

export const useBlogSettingsStore = defineStore('blogsettings', () => {
  // 博客设置信息
  const blogSettings = ref({})

  // 获取博客设置信息
  function getBlogSettings() {
    // 调用后台获取博客设置信息接口
    console.log('获取博客设置信息')
    getBlogSettingsDetail().then(res => {
      if (res.success) {
        blogSettings.value = res.data
      }
    })
  }


  return { blogSettings, getBlogSettings }
})