<template>
  <div >    
    <el-table border style="margin-top: 30px;" :data="data" height="450"> 
      <el-table-column label="eid" prop="eid"></el-table-column>
      <el-table-column label="time" prop="time"></el-table-column>
      <el-table-column label="address" prop="address"></el-table-column>
      <el-table-column label="longitude" prop="longitude"></el-table-column>
      <el-table-column label="latitude" prop="latitude"></el-table-column>
    </el-table>  
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'error',
  data() {
    return {
      msg: '',
      data: []
    }
  },
    methods: {
      async search() {
        let data = await axios.get('/api/we')
        console.log(data)
        this.data = data.data.data
      }
    },
    mounted() {
      this.search()
    }
}
</script>

<style scoped>
.error {
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  justify-content: center;
}
</style>
