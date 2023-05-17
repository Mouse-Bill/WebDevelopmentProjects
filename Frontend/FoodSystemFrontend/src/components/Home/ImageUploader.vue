<template>
  <el-dialog title="上传/修改图片" v-model="componentVisible" width="30%">
    <!-- 虚线圆角矩形框 -->
    <div style="text-align: left; padding: 1%;">当前图片预览: </div>
    <div style="border: 1px dashed #409EFF; border-radius: 5px; padding: 10px; margin-bottom: 10px;">
      <img w-full :src="'/api/foods/'+data.food.fpic" alt="" style="width: 100%;" />
    </div>
    <div style="text-align: left; padding: 1%;">上传图片: </div>
    <el-upload ref="upload" class="upload-demo" drag action="" multiple:false :limit="1" :auto-upload="false"
      list-type="picture" accept="image/png, image/jpeg, image/jpg" :http-request="uploadFile">
      <el-icon size="100px" color="#409EFC"><upload-filled /></el-icon>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
      <template #tip>
        <div style="text-align: left; padding: 1%; margin-top: 2%;">上传图片预览: </div>
      </template>
      <template #file="{ file }" style="padding-top: -1%;">
        <div>
          <span>
            <img style="width:100%;" :src="file.url" alt="" />
            <span @click="handlePictureCardPreview(file)" style="margin-right: 10%;">
              <el-icon><zoom-in /></el-icon>
            </span>
            <span @click="handleRemove()">
              <el-icon>
                <Delete />
              </el-icon>
            </span>
          </span>
        </div>
      </template>
    </el-upload>

    <el-dialog v-model="previewVisible">
      <img w-full :src="previewFile.url" alt="Preview Image" style="width: 100%;" />
    </el-dialog>

    <el-button style="margin-top: 10px;" size="small" type="success" @click="onSubmit()">上传到服务器</el-button>

  </el-dialog>
</template>


<script setup>
import { ref } from 'vue';
import { ElMessageBox } from 'element-plus'
import { reactive } from 'vue';
import http from '../../utils/http/http';
import { getCurrentInstance } from 'vue'
import { watch } from "vue"

// 定义控制整个组件显隐的变量
const componentVisible = ref(false)
// 预览显示
const previewVisible = ref(false)

const emit = defineEmits(['updatefood'])

const upload = ref(null)

let { ctx: that, proxy } = getCurrentInstance();

const file = reactive({
  url: ''
})

const previewFile = reactive({
  url: ''
})


// const ruleForm = ref(null)

const data = reactive(
  {
    food: []
  }
)



const handlePictureCardPreview = (file) => {
  previewVisible.value = true
  previewFile.url = file.url
  // console.log(file);
  // console.log(file.url);
}

const handleRemove = () => {
  upload.value.clearFiles()
}

const onSubmit = () => {
  upload.value.submit()
}

const uploadFile = async (val) => {
  let formData = new FormData();
  formData.append('file', val.file);
  http.post('/img/upload', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  }).then(res => {
    if (res.data.isOK) {
      file.url = res.data.url
      onUploadSuccess()
    } else {
      ElMessageBox.alert('上传失败', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          componentVisible.value = false
          upload.value.clearFiles()
        }
      })
    }
  })


}

const onUploadSuccess = () => {
  const dbUpdate = {
    fid: data.food.fid,
    fpic: file.url
  }
  http.post('/food/update', dbUpdate).then(res => {
    if (res.data.isOK) {
      emit('updatefood')
      ElMessageBox.alert('上传成功', '提示', {
        confirmButtonText: '确定',
        type: 'success',
        callback: action => {
          componentVisible.value = false
          upload.value.clearFiles()
          data.food.fpic = file.url 
        }
      })
    } else {
      ElMessageBox.alert('上传失败', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          componentVisible.value = false
          upload.value.clearFiles()
        }
      })
    }
  })
}


// 将变量暴露出来
defineExpose({
  componentVisible,data
})
</script>

<style scoped></style>

<style>
.el-upload-list {
  margin: 0px;
}

.el-upload-list--picture .el-upload-list__item {
  margin-top: 0px;
}
</style>
