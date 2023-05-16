<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>食品管理</el-breadcrumb-item>
    </el-breadcrumb>
    <FoodAdder :user="user" ref="addVisiableDialog"></FoodAdder>
    <FoodEditor :food="food.selectedFood" ref="editVisiableDialog"></FoodEditor>
    <el-card class="main-card">
      <el-row :gutter="20">
        <el-col :span="8">
          <!-- 搜索与添加区域 -->
          <el-input placeholder="请输入内容">
            <template #append>
              <el-icon>
                <search />
              </el-icon>
            </template>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="openAdderDialog">添加食品</el-button>
        </el-col>
      </el-row>

      <el-table :data="tableData()" style="margin-bottom: 10px;">
        <el-table-column prop="fid" label="ID" width="50" />
        <el-table-column prop="fname" label="名字" width="180" />
        <el-table-column prop="fpic" label="图片" width="180" />
        <el-table-column prop="fprice" label="价格" width="180" />
        <el-table-column prop="ftype.tname" label="类型" width="180" />
        <el-table-column prop="fdesc" label="描述" />
        <el-table-column label="操作">
          <template v-slot="scope">
            <!-- 修改按钮 -->
            <el-button type="primary" v-model="scope.row.fid" size="mini" @click="handleEdit(scope.row.fid)"><el-icon>
                <edit />
              </el-icon></el-button>
            <!-- 删除按钮 -->
            <el-tooltip effect="dark" content="删除" placement="top" :enterable="false">
              <el-button type="danger" size="mini"><el-icon>
                  <delete />
                </el-icon></el-button>
            </el-tooltip>
          </template>
        </el-table-column>

      </el-table>
      <div style="margin-bottom: 30px;">
        <el-pagination v-model:currentPage="food.pagenum" :page-sizes="[10, 30, 50, 100]"
          layout=" prev, pager, next, jumper,total, sizes," :total="food.total" @size-change="handleSizeChange"
          @current-change="handleCurrentChange" />
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import http from '../utils/http/http';
import FoodAdder from '../components/Home/FoodAdder.vue'
import FoodEditor from '../components/Home/FoodEditor.vue'
import { ref } from 'vue';

const addVisiableDialog = ref(null)
const editVisiableDialog = ref(null)

// const queryInfo = reactive({
//   query: '',
//   pagenum: 1,
//   pagesize: 10
// });

var pagenum = reactive(1);
var pagesize = reactive(10);
const food = reactive({
  list: [],
  total: 0,
  pagenum: 1,
  pagesize: 10,
  total: 0,
  selectedFood: {},
});

const total = reactive(0);

// var foodList = [
//   {
//     "fid": 1,
//     "tid": 32,
//     "fname": "虾皮萝卜丝汤",
//     "fpic": "52da1ae8a9375.jpg",
//     "fprice": 14.0,
//     "forder": 1,
//     "fdesc": "清淡的虾皮萝卜丝汤!作为最流行的夏季汤品，逐渐走入大家的心中。清淡的萝卜丝配上淡味的虾皮，将百煮的浓汤浇在汤面上，一碗清淡爽口的虾皮萝卜丝汤出锅了",
//     "fregtime": "2023-05-13 11:05:13",
//     "ftype": {
//       "tid": 32,
//       "tname": "滋补"
//     }
//   },
//   {
//     "fid": 2,
//     "tid": 32,
//     "fname": "白蛤蒸蛋",
//     "fpic": "52da147fc29fb.jpg",
//     "fprice": 15.0,
//     "forder": 2,
//     "fdesc": "白蛤蒸蛋　洗净的白蛤经过腌制，放入到新鲜的鸡蛋中，加上各种调味品。海鲜加鸡蛋，不仅美味而且营养价值高，对于害怕海鲜腥味的朋友也可以品尝。",
//     "fregtime": "2023-05-13 11:05:13",
//     "ftype": {
//       "tid": 32,
//       "tname": "滋补"
//     }
//   },
//   {
//     "fid": 3,
//     "tid": 26,
//     "fname": "梅子茶泡饭",
//     "fpic": "52da0feb79de3.jpg",
//     "fprice": 10.0,
//     "forder": 5,
//     "fdesc": "梅子茶泡饭 喜欢饭泡水的朋友，一定不能错过，汤水微有酸甜味，米饭软硬适中，是快速餐饮，解饿充饥的首选",
//     "fregtime": "2023-05-13 11:05:13",
//     "ftype": {
//       "tid": 26,
//       "tname": "中式盖饭"
//     }
//   },
//   {
//     "fid": 4,
//     "tid": 15,
//     "fname": "水煮肉片",
//     "fpic": "52da0e14dce6a.jpg",
//     "fprice": 30.0,
//     "forder": 4,
//     "fdesc": "水煮肉片 使用小米辣椒，辣而不辛，肉片爽滑，轻嚼即化，不油腻，爱吃辣的朋友绝对不能错过",
//     "fregtime": "2023-05-13 11:05:14",
//     "ftype": {
//       "tid": 15,
//       "tname": "川菜"
//     }
//   },
//   {
//     "fid": 5,
//     "tid": 15,
//     "fname": "香酥小肉丸",
//     "fpic": "52fcef7a7ab06.jpg",
//     "fprice": 20.0,
//     "forder": 2,
//     "fdesc": "香酥小肉丸  不油腻，外层酥脆，内层肉质松软",
//     "fregtime": "2023-05-13 11:05:14",
//     "ftype": {
//       "tid": 15,
//       "tname": "川菜"
//     }
//   }
// ];



console.log(food.list);

function openAdderDialog() {
  addVisiableDialog.value.dialogVisble = true
  console.log(addVisiableDialog.value.dialogVisble);
}

const getFoodList = async () => {
  const data = await http.get('/food/getall');
  if (data.status === 200) {
    food.list = data.data.list
  }
  food.total = food.list.length;
  console.log(food.list);
};

getFoodList();

const tableData = () => {
  return food.list.filter(
    (item, index) =>
      index < food.pagenum * food.pagesize &&
      index >= food.pagesize * (food.pagenum - 1)
  );
};

const handleSizeChange = (val) => {
  food.pagesize = val;
};

const handleCurrentChange = (val) => {
  food.pagenum = val;
};

const handleEdit = (id) => {
  food.selectedFood = food.list.find(item => item.fid === id);
  console.log(id);
  console.log("father"+food.selectedFood);
  editVisiableDialog.value.dialogVisble = true
};



const deleteFood = async (id) => {
  const { data: { meta } } = await http.delete(`/food/${id}`);
  if (meta.status === 200) {
    this.$message({
      type: 'success',
      message: meta.msg
    });
    getFoodList();
  }
};
</script>

<style scoped>
.main-card {
  margin-top: 20px;
}

.el-pagination {
  float: left;
}
</style>