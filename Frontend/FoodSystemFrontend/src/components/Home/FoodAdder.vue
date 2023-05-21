<template>
  <el-dialog title="提示" v-model="componentVisible" width="30%">
    <span>
      <el-form :model="data.ruleForm" :rules="data.rules" ref="ruleForm" label-width="100px" class="foodadd-ruleForm">
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

const emit = defineEmits(['updatefood'])

const data = reactive(
  {
    ruleForm: {
      fname: '',
      fpic:'',
      fprice: '',
      ftype: {},
      fdesc: ''
    },
    rules: {
      fname: [
        { required: true, message: '请输入食品名称', trigger: 'blur' },
        { min: 0, max: 255, message: '长度在 255 个字符以内', trigger: 'blur' }
      ],
      ftype: [
        { required: true, message: '请选择食品类型', trigger: 'change' }
      ],
      fprice: [
        { required: true, message: '请填写食品价格', trigger: 'blur' }
      ],
      fdesc: [
        { required: true, message: '请填写食品描述', trigger: 'blur' }
      ]
    },
    foodType: [],
    foodList:[]
  }
)


const componentVisible = ref(false)

const props = defineProps(['user'])


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
  console.log(ruleForm.value);
  if (ruleForm.value) {
    ruleForm.value.validate((valid) => {
      if (valid) {
        http.post('/food/add', data.ruleForm).then(res => {
          if (res.status === 200) {
            console.log(res.data);
            if (res.data.isOK) {
              emit('updatefood')
              ElMessageBox.confirm('添加成功', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'success'
              }).then(() => {
                console.log('确定');
                ruleForm.value.resetFields();
                componentVisible.value = false
              }).catch(() => {
                console.log('取消');
                ruleForm.value.resetFields();
                componentVisible.value = false
              });
            } else {
              ElMessageBox.confirm('添加失败', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'error'
              }).then(() => {
                console.log('确定');
                ruleForm.value.resetFields();
                componentVisible.value = false
              }).catch(() => {
                console.log('取消');
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

const resetForm = (formName) => {
  if (ruleForm.value) {
    ruleForm.value.resetFields();
  }
}


// 将变量暴露出来
defineExpose({
  componentVisible,data
})
</script>
