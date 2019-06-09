<template>
  <div id="app">
    <el-container>
      <el-header>
        <img src="./assets/logo.png" style="width: 40px;height:25px; position: absolute; top: 16px; left: 10px;">
        <span style="position: absolute; top: 0px; left: 50px;">
          <span>云计算课程</span>  
          <span>SE</span>
          <span>5035</span>
        </span>

      </el-header>
      <el-container>
        <el-aside width="200px" style="height: 700px;">
           <el-menu
            :default-active="name"
            class="el-menu-vertical-demo"
            style="height: 100%;"
            @select="select"
          >
            <!-- <el-menu-item index="项目介绍" @click="toRouter('index')">
              <span slot="title">项目介绍</span>
            </el-menu-item> -->
            <el-menu-item index="过车统计" @click="toRouter('car')">
              <span slot="title">过车统计</span>
            </el-menu-item>
            <el-menu-item index="轨迹重现" @click="toRouter('guiji')">
              <span slot="title">轨迹重现</span>
            </el-menu-item>
            <el-menu-item index="错误数据" @click="toRouter('errMsg')">
              <span slot="title">错误数据</span>
            </el-menu-item>
            <el-menu-item index="相遇次数" @click="toRouter('xiangyu')">
              <span slot="title">相遇次数</span>
            </el-menu-item>
            <el-menu-item index="规则查询" @click="toRouter('guize')">
              <span slot="title">规则查询</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main style="height: 700px;">
          <title-bar :name="name"></title-bar>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import titleBar from './components/title.vue'
import axios from 'axios'
export default {
  name: 'App',
  data() {
    return {
      name: '项目介绍'
    }
  },
  components: {
    titleBar
  },
  methods: {
    select(index, indexPath) {
      this.name = index
    },
    toRouter(name) {
      this.$router.push(name)
    }
  },
  mounted() {

  }
}
</script>

<style>
html,body,#app{
  height: 100%;
  width: 100%;
}
body {
  margin: 0px;
}
.el-header{
  background-color: #343a40!important;
  color: white;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  height: 100%;;
  background-color: #f8f9fa!important;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  height: 100%;;
  background-color: #E9EEF3;
  color: #333;
}

</style>
