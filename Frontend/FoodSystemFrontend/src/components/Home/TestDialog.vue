<template>
  <el-dialog
    class="base-dialog"
    :title="title"
    width="60%"
    v-model="visible"
  >
    <div
      class="body-wrapper"
      :class="autoHeight ? 'autoHeight' : 'fixedHeight'"
    >
      <slot></slot>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button v-if="showCancelButton" @click="">取消</el-button>
        <el-button
          v-if="showConfirmButton"
          type="primary"
          @click=""
          >确认</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>
 
<script setup>
import { computed } from 'vue'
const props = defineProps({
  visible: {
    type: Boolean,
    default: false,
  }
})
// 打开
const handleOpen = () => {
  emit('open')
}
// 子组件定义自己的visible
const visible = computed({
  get: () => props.visible,
  set: (value) => {
    // emit('update:modelValue', value)
  },
})
</script>
 
<style scoped>
.body-wrapper {
  overflow: auto;
}
.autoHeight {
  max-height: 56vh;
}
.fixedHeight {
  height: 56vh;
}
</style>
