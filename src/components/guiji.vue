<template>
    <div>
        <div class="error">
            <el-date-picker
                v-model="startTime"
                type="datetime"
                placeholder="选择开始时间"
                style="margin-right: 20px;"
            >
            </el-date-picker>
            <el-date-picker
                v-model="endTime"
                type="datetime"
                placeholder="选择结束时间"
                style="margin-right: 20px;"
            >
            </el-date-picker>
            <el-input v-model="msg" style="width: 200px;margin-right: 20px;" placeholder="请输入eid"></el-input>
            <el-button type="primary" @click="search">查询</el-button>            
        </div>
        <div id="allmap" v-bind:style="mapStyle"></div>
        <el-table border :data="data" height="450" style="margin-top: 30px;">
            <el-table-column label="address" prop="address"></el-table-column>
            <el-table-column label="longitude" prop="longitude"></el-table-column>
            <el-table-column label="latitude" prop="latitude"></el-table-column>           
        </el-table>
<!-- 
        <baidu-map class="map"></baidu-map> -->
    </div>
</template>

<script>
import Vue from 'vue';
import axios from 'axios'
export default {
    name: 'guiji',
    data() {
        return {
            msg: '',
            startTime: '',
            endTime: '',
            mapStyle:{
                width:'100%',
                height:'500px'
            },
            map: {},
            marker: {},
            point: {},
            data: []
        }
    },
    methods: {
        async search() {
            console.log(1)
            let data = await axios.get('/api/track',{
                params: {
                    start: this.getTime(this.startTime),
                    end: this.getTime(this.endTime),
                    eId: this.msg
                }
            })

            console.log(data)
            this.data = data.data.data
            // this.addMarker()
            // console.log(1)
        },
        getTime(time) {
            var y = time.getFullYear();  
            var m = time.getMonth() + 1;  
            m = m < 10 ? ('0' + m) : m;  
            var d = time.getDate();  
            d = d < 10 ? ('0' + d) : d;  
            var h = time.getHours();  
            var minute = time.getMinutes();  
            minute = minute < 10 ? ('0' + minute) : minute; 
            var second= time.getSeconds();  
            second = minute < 10 ? ('0' + second) : second;  
            return y + '-' + m + '-' + d+' '+h+':'+minute+':'+ second;  
        },


        addMarkerEnd(point, name,trackUnit) {
            if(name=="终点"){
                if(carMk){//先判断第一次进来的时候这个值有没有定义，有的话就清除掉上一次的。然后在进行画图标。第一次进来时候没有定义也就不走这块，直接进行画图标
                    mapInit.removeOverlay(carMk);
                }
                carMk = new BMap.Marker(point);  // 创建标注
                carMk.setRotation(trackUnit.route);//trackUnit.route
                this.map.addOverlay(carMk);               // 将标注添加到地图中
                carMk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        
            }
        },

        addMarker(point, name,trackUnit) {
            if(name=="起点"){
                // var myIcon = new BMap.Icon("css/img/startMap.svg", new BMap.Size(45,45),{
                //     anchor: new BMap.Size(20, 45)//这句表示图片相对于所加的点的位置mapStart
                //     // offset: new BMap.Size(-10, 45), // 指定定位位置
                //     // imageOffset: new BMap.Size(0, 0 - 10 * 25) // 设置图片偏移
                // });
                this.marker = new BMap.Marker(point);  // 创建标注
                this.map.addOverlay(this.marker);               // 将标注添加到地图中
                this.marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            }
        },

        ready(){
            this.map = new BMap.Map('allmap');
            this.point = new BMap.Point(116.3331398, 39.897445);
            this.map.centerAndZoom(this.point, 14);
            this.map.addControl(new BMap.MapTypeControl());
            this.map.enableScrollWheelZoom(true);
            this.map.enableDoubleClickZoom(true);
            // var marker = new BMap.Marker(point);
            // map.addOverlay(marker);
        },
        drew() {

        },
        getLocalTime(nS) {     
            return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');     
        }
    },     
    mounted() {
        this.ready()
    }
}
</script>

<style >
.map {
  width: 100%;
  height: 300px;
}
.error {
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  justify-content: center;
  margin-bottom: 20px;
}
</style>