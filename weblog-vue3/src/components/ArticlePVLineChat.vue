<template>
    <!-- PV 折线图容器 -->
    <div id="lineChat" class="overflow-x-auto w-full h-60"></div>
</template>

<script setup>
import * as echarts from 'echarts'
import { watch } from 'vue'

// 对外暴露的属性值
const props = defineProps({
    value: { // 属性值名称
        type: Object, // 类型为对象
        default: null // 默认为 null
    }
})

// 初始化折线图
function initLineChat() {
    var chartDom = document.getElementById('lineChat');
    var myChart = echarts.init(chartDom);
    var option;

    // 从 props.value 中获取日期集合和 pv 访问量集合
    const pvDates = props.value.pvDates
    const pvCounts = props.value.pvCounts

    option = {
        xAxis: {
            type: 'category',
            data: pvDates
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: pvCounts,
                type: 'line'
            }
        ]
    };

    option && myChart.setOption(option);
}

// 侦听属性, 监听 props.value 的变化，一旦 props.value 发生变化，就调用 initLineChat 初始化折线图
watch(() => props.value, () => initLineChat())
</script>