<template>
  <p>hhhhhp</p>
  <el-upload class="avatar-uploader" action=""
    :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" :limit="1">
    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon">
      <Plus />
    </el-icon>
  </el-upload>
</template>


<script setup>
// import { ref } from 'vue';
// import { ElMessageBox } from 'element-plus'
// import { reactive } from 'vue';

// const imageUrl = ref('')

</script>


<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>