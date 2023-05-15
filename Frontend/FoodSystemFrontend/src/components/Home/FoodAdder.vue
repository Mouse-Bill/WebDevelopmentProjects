<template>
  <el-dialog title="提示" v-model="dialogVisble" width="30%">
    <span>
      <el-form :model="data.ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="foodadd-ruleForm">
        <el-form-item label="食品名称" prop="fname">
          <el-input v-model="data.ruleForm.fname"></el-input>
        </el-form-item>

        <el-form-item label="食品类型" prop="ftype">
          <el-select v-model="data.ruleForm.ftype" placeholder="请选择食品类型" value-key="tid">
            <el-option v-for="item in data.foodType" :key="item.tid" :label="item.tname"
              :value="item"></el-option>
          </el-select>
        </el-form-item>
        
        <el-form-item label="食品价格" prop="fprice">
          <el-input v-model="data.ruleForm.fprice"></el-input>
        </el-form-item>

        <el-form-item label="食品描述" prop="fdesc">
          <el-input type="textarea" v-model="data.ruleForm.fdesc"></el-input>
        </el-form-item>

      </el-form>
    </span>
    <template #footer>
      <span class="dialog-footer">
        <!-- <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="confirm">确 定</el-button> -->
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessageBox } from 'element-plus'
import { reactive } from 'vue';
import http from '../../utils/http/http';
import { getCurrentInstance } from 'vue'

const ruleForm = ref(null)

const data = reactive(
  {
    ruleForm: {
      fname: '',
      fprice: '',
      ftype: {},
      fdesc: ''
    },
    rules: {
      fname: [
        { required: true, message: '请输入食品名称', trigger: 'blur' },
        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
      ],
      ftype: [
        { required: true, message: '请选择食品类型', trigger: 'change' }
      ],
      fprice: [
        { required: true, message: '请填写食品价格', trigger: 'blur' }
      ],
      fdesc: [
        { required: true, message: '请填写活动形式', trigger: 'blur' }
      ]
    },
    foodType: []
  }
)



const getFoodType = async () => {
  const res = await http.get('/ftype/getall');
  if (res.status === 200) {
    const array = [];
    for (let key in res.data.list) {
      array.push({ tid: res.data.list[key].tid, tname: res.data.list[key].tname })
    }
    data.foodType = array;
  }
  console.log(data.foodType);
};

getFoodType()

const submitForm = (formName) => {
  if (ruleForm.value) {
    ruleForm.value.validate((valid) => {
      if (valid) {
        http.post('/food/add', data.ruleForm).then(res => {
          if (res.status === 200) {
            console.log(res.data);
            if (res.data.isOK) {
              ElMessageBox.confirm('添加成功', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'success'
              }).then(() => {
                console.log('确定');
                ruleForm.value.resetFields();
                dialogVisble.value = false
              }).catch(() => {
                console.log('取消');
                ruleForm.value.resetFields();
                dialogVisble.value = false
              });
            } else {
              ElMessageBox.confirm('添加失败', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'error'
              }).then(() => {
                console.log('确定');
                ruleForm.value.resetFields();
                dialogVisble.value = false
              }).catch(() => {
                console.log('取消');
                ruleForm.value.resetFields();
                dialogVisble.value = false
              });
            }
          }
        })
      } else {
        console.log('error submit!!');
        return false;
      }
    });
  }
}

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
const props = defineProps(['user'])

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
