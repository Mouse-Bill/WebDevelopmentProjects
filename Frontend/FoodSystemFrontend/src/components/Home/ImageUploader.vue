<template>
  <el-dialog title="上传/修改图片" v-model="dialogVisble" width="30%">
    <template>
      <p>hhhhhp</p>
      <!-- <el-upload class="avatar-uploader" action=""
        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" :limit="1">
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon">
          <Plus />
        </el-icon>
      </el-upload> -->
    </template>

  </el-dialog>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessageBox } from 'element-plus'
import { reactive } from 'vue';
import http from '../../utils/http/http';
import { getCurrentInstance } from 'vue'
import { watch } from "vue"

let { ctx: that, proxy } = getCurrentInstance();


import { genFileId } from 'element-plus'

const imageUrl = ref('')



// const ruleForm = ref(null)

// const data = reactive(
//   {
//     ruleForm: {
//       fid: '',
//       fname: '',
//       fprice: '',
//       ftype: {},
//       fdesc: ''
//     },
//     rules: {
//       fname: [
//         { required: true, message: '请输入食品名称', trigger: 'blur' },
//         { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
//       ],
//       ftype: [
//         { required: true, message: '请选择食品类型', trigger: 'change' }
//       ],
//       fprice: [
//         { required: true, message: '请填写食品价格', trigger: 'blur' }
//       ],
//       fdesc: [
//         { required: true, message: '请填写活动形式', trigger: 'blur' }
//       ]
//     },
//     foodType: []
//   }
// )



// const getFoodType = async () => {
//   const res = await http.get('/ftype/getall');
//   if (res.status === 200) {
//     const array = [];
//     for (let key in res.data.list) {
//       array.push({ tid: res.data.list[key].tid, tname: res.data.list[key].tname })
//     }
//     data.foodType = array;
//   }
//   console.log(data.foodType);
// };

// getFoodType()

// const submitUpdate = (formName) => {
//   if (ruleForm.value) {
//     ruleForm.value.validate((valid) => {
//       if (valid) {
//         http.post('/food/update', data.ruleForm).then(res => {
//           if (res.status === 200) {
//             console.log(res.data);
//             if (res.data.isOK) {
//               ElMessageBox.confirm('修改成功', '提示', {
//                 confirmButtonText: '确定',
//                 cancelButtonText: '取消',
//                 type: 'success'
//               }).then(() => {
//                 console.log('确定');
//                 ruleForm.value.resetFields();
//                 dialogVisble.value = false
//               }).catch(() => {
//                 console.log('取消');
//                 ruleForm.value.resetFields();
//                 dialogVisble.value = false
//               });
//             } else {
//               ElMessageBox.confirm('修改失败', '提示', {
//                 confirmButtonText: '确定',
//                 cancelButtonText: '取消',
//                 type: 'error'
//               }).then(() => {
//                 console.log('确定');
//                 ruleForm.value.resetFields();
//                 dialogVisble.value = false
//               }).catch(() => {
//                 console.log('取消');
//                 ruleForm.value.resetFields();
//                 dialogVisble.value = false
//               });
//             }
//           }
//         })
//       } else {
//         console.log('error submit!!');
//         return false;
//       }
//     });
//   }
// }

// 定义控制弹窗显隐的变量
const dialogVisble = ref(false)

// 接受父组件传过来的值
// const props = defineProps({
//   user: {
//     type: Object,
//     default: {}
//   }
// })
// 或者
const props = defineProps(['food'])

watch(() => props.food, (newVal, oldVal) => {
  // console.log(newVal);
  // console.log(oldVal);
  if (newVal) {
    data.ruleForm.fname = newVal.fname
    data.ruleForm.fprice = newVal.fprice.toString()
    data.ruleForm.ftype = { tid: newVal.ftype.tid, tname: newVal.ftype.tname }
    data.ruleForm.fdesc = newVal.fdesc
    data.ruleForm.fid = newVal.fid
  }
})

// console.log(props.food);
// data.ruleForm.fname = props.food.fname
// data.ruleForm.fprice = props.food.fprice.toString()
// // data.ruleForm.ftype = { tid: props.food.ftype.tid, tname: props.food.ftype.tname}
// data.ruleForm.fdesc = props.food.fdesc
//console.log(data.ruleForm);





function confirm() {
  ElMessageBox.confirm('确定关闭吗?').then(() => {
    console.log('你点击了确定按钮')
    dialogVisble.value = false
  }).catch(() => { })
}

function close() {
  dialogVisble.value = false
}

// 将变量暴露出来
defineExpose({
  dialogVisble
})
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
