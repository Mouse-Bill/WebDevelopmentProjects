<template>
  <el-upload class="upload-demo" drag action="" multiple:false>
    <el-icon size="100px" color="#409EFC"><upload-filled /></el-icon>
    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>

  </el-upload>
  <el-button style="margin-left: 10px;" size="small" type="success" @click="">上传到服务器</el-button>
</template>


<script setup>
// import { ref } from 'vue';
// import { ElMessageBox } from 'element-plus'
// import { reactive } from 'vue';

// const imageUrl = ref('')

</script>


<style scoped></style>

<style></style>