<template>
    <div>
        <el-container>
            <el-header>
                <div style="width:400px">
                    <p style="float: left;">
                        <el-icon color="#409EFF" :size="50">
                            <ElementPlus />
                        </el-icon>
                    </p>
                    <p style="float: left;font-size: 25px; font-weight: bold">FoodSystem</p>
                </div>
            </el-header>
            <el-main>
                <el-card class="login_card">
                    <el-form :model="form" :rules="rules" ref="ruleFormRef" label-width="80px">
                        <el-form-item label="Username:" prop="username">
                            <el-input v-model="form.username" placeholder="Username" />
                        </el-form-item>
                        <el-form-item label="Password" prop="passwd">
                            <el-input type="password" placeholder="Password" v-model="form.passwd" />
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit()">Login</el-button>
                            <el-button type="primary" @click="resetForm()">Reset</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-main>
        </el-container>
    </div>
    <nest></nest>
</template>



<script setup>
import userApi from "../api/user";
import { reactive, ref, getCurrentInstance } from "vue";
import { ElMessage } from "element-plus";
import router from "../router/index";
import nest from "../utils/canvas-nest.js";

const { proxy } = getCurrentInstance();
const form = reactive({
  username: "",
  passwd: "",
});
const ruleFormRef = ref();
const rules = reactive({
  username: [{ required: true, message: "账号不能为空", trigger: "blur" }],
  passwd: [{ required: true, message: "密码不能为空", trigger: "blur" }],
});
// if (sessionStorage.getItem("token")) {
//   router.push("/home");
// }

const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.login(form);
      if (res.data) {
        sessionStorage.setItem("token", res.headers.token);
        console.log(res.data);
        console.log(res.headers.token);
        if (res.data.isOK) {
          // proxy.$commonJs.changeView('/home');
          router.push("/home/index");
        } else {
          ElMessage.error(res.data.message);
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {
      return false;
    }
  });
};
const resetForm = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.resetFields();
};
</script>





<style scoped>
.el-container {
  height: 800px;
}

.el-header {
  height: 10%;
}

.el-main {
  height: 80%;
  background-repeat: no-repeat;
  background-size: 100% 120%;
  background-position-y: bottom;
}

.login_card {
  margin: 100px 200px;
  width: 20%;
  min-width: 300px;
  height: 200px;
  border-radius: 10px;
}
</style>

<style>
</style>