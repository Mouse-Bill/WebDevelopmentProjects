<template>
  <el-dialog title="修改食品" v-model="componentVisible" width="30%">
    <span>
      <el-form :model="data.ruleForm" :rules="data.rules" ref="ruleForm" label-width="100px" class="foodadd-ruleForm">
        <el-form-item label="食品名称" prop="fname">
          <el-input v-model="data.ruleForm.fname"></el-input>
        </el-form-item>

        <el-form-item label="食品类型" prop="ftype">
          <el-select v-model="data.ruleForm.ftype" placeholder="请选择食品类型" value-key="tid">
            <el-option v-for="item in data.foodType" :key="item.tid" :label="item.tname" :value="item"></el-option>
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
        <el-button type="primary" @click="submitUpdate('ruleForm')">确定</el-button>
        <el-button @click="resetForm()">重置</el-button>
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
import { watch } from "vue"

let { ctx: that, proxy } = getCurrentInstance();

// 定义控制弹窗显隐的变量
const componentVisible = ref(false)

// 接受父组件传过来的值
// const props = defineProps(['food'])

// 向父组件发送事件
const emit = defineEmits(['updatefood'])


const ruleForm = ref(null)

const data = reactive(
  {
    ruleForm: {
      fid: '',
      fname: '',
      fprice: '',
      ftype: {},
      fdesc: ''
    },
    rules: {
      fname: [
        { required: true, message: '请输入食品名称', trigger: 'blur' },
        { min: 0, max: 255, message: '长度在 3 到 5 个字符', trigger: 'blur' }
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
    foodType: [],
    sharedFood: {}

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
  //console.log(data.foodType);
};

getFoodType()

const submitUpdate = () => {
  if (ruleForm.value) {
    ruleForm.value.validate((valid) => {
      if (valid) {
        http.post('/food/update', data.ruleForm).then(res => {
          if (res.status === 200) {
            console.log(res.data);
            if (res.data.isOK) {
              emit('updatefood', data.ruleForm)
              ElMessageBox.alert('修改成功', '提示', {
                confirmButtonText: '确定',
                type: 'success'
              }).then(() => {
                data.sharedFood.fprice = data.ruleForm.fprice
                data.sharedFood.ftype = data.ruleForm.ftype
                data.sharedFood.fdesc = data.ruleForm.fdesc
                data.sharedFood.fname = data.ruleForm.fname
                console.log('确定');
                componentVisible.value = false
              });
            } else {
              ElMessageBox.confirm('修改失败', '提示', {
                confirmButtonText: '确定',
                type: 'error'
              }).then(() => {
                console.log('确定');
                ruleForm.value.resetFields();
                componentVisible.value = false
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



// watch(() => props.food, (newVal, oldVal) => {
//   console.log(newVal);
//   console.log(oldVal);
//   if (newVal) {
//     data.ruleForm.fname = newVal.fname
//     data.ruleForm.fprice = newVal.fprice.toString()
//     data.ruleForm.ftype = { tid: newVal.ftype.tid, tname: newVal.ftype.tname }
//     data.ruleForm.fdesc = newVal.fdesc
//     data.ruleForm.fid = newVal.fid
//   }
// })

// watch(() => data.ruleForm, (newVal, oldVal) => {
//   console.log(newVal);
//   console.log(oldVal);
// }, { deep: true, immediate: true })



function confirm() {
  ElMessageBox.confirm('确定关闭吗?').then(() => {
    console.log('你点击了确定按钮')
    componentVisible.value = false
  }).catch(() => { })
}

const resetForm = () => {
  ruleForm.value.resetFields();
}

// 将变量暴露出来
defineExpose({
  componentVisible, data
})
</script>
