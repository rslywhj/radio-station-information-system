<template>
  <div class="app-container">
    <el-calendar>
      <template
        slot="dateCell"
        slot-scope="{date, data}">
        <!--弹出框，显示详细安排信息-->
        <el-popover
          placement="top-start"
          trigger="hover">
          <div v-html="neirong"></div>
          <p :class="rqi.indexOf(data.day) > -1 ? 'is-selected' : rqi1.indexOf(data.day) > -1 ?'is-anpai':''"
             @click="handleBianJi(data.day)"
             slot="reference"
             v-on:mouseenter="viewDetails(data.day)" v-on:mouseout="clearCache()">
            {{ data.day.split('-').slice(1).join('-') }} {{ rqi.indexOf(data.day) > -1 ? '✔️' : ''}}
          </p>
        </el-popover>
      </template>
    </el-calendar>
  </div>
</template>

<script>
  import {selectBYEdit, selectWXEdit} from "@/api/date";
  import router from '@/router/index.js'

  export default {
    data() {
      return {
        //弹出框内容缓存
        neirong: "",
        //编辑列表
        edit: {},
        //播音列表
        broadcast: {},
        //日期数组
        rqi: [],
        rqi1: [],
        //日期内容
        rqineirong: {},
        apneirong: {},
        title: "",
        data: "",
        // 遮罩层
        loading: true,
      };
    },
    created() {
      this.getList()
      this.getList1()
    },
    activated() {
      this.getList()
    },
    methods: {
      /*鼠标逗留，查看详情*/
      viewDetails(day) {
        if (this.rqi.indexOf(day) > -1) {
          this.neirong = "写稿编辑：" +
            this.rqineirong[day].author
        }
        if (this.rqi1.indexOf(day) > -1) {
          this.neirong = this.neirong + "<br/>编辑：" +
            this.apneirong[day].edit +
            "<br/>" + "播音：" +
            this.apneirong[day].broadcast
          if (this.apneirong[day].article !== null) {
            this.neirong = this.neirong + "<br/>"
              + "稿件：" + this.apneirong[day].article
          }
        }
        console.log(this.rqi.indexOf(day) > -1)
      },
      /*鼠标离开，清除缓存*/
      clearCache() {
        this.neirong = ""
      },
      /** 查询日程管理 - 日程列表 */
      getList() {
        this.loading = true;
        this.rqineirong = {}
        this.rqi = []
        selectWXEdit().then(response => {
          for (let i = 0; i < response.data.length; i++) {
            this.rqi.push(response.data[i].arrangeDate)
            this.rqineirong[response.data[i].arrangeDate] = response.data[i]
          }
          console.log("rqi" + this.rqi)
          console.log(this.rqineirong)
        });
      },
      getList1() {
        this.loading = true;
        this.rqi1 = []
        this.apneirong = {}
        selectBYEdit().then(response => {

          for (let i = 0; i < response.data.length; i++) {
            this.rqi1.push(response.data[i].date)
            this.apneirong[response.data[i].date] = response.data[i]
          }
          console.log("rqi1" + this.rqi1)
          console.log(this.apineirong)
        });
      },
      handleBianJi(day) {
        if (this.rqi1.indexOf(day) > -1) {
          if (this.apneirong[day].article !== null) {
            this.title = this.apneirong[day].article
            console.log(this.title)
            router.push({name: 'arttt', params: {'title': this.title}})
          }
        }
        if (this.rqi.indexOf(day) > -1) {
          console.log(day)
          router.push({name: 'CreateArticle', params: {'date': day}})
        }
      }
    }
  };
</script>
<style lang="scss" scoped>
  .is-selected {
    color: white;
    background: rgba(112, 161, 255, 0.8);
  }

  .is-anpai {
    color: white;
    background: rgba(255, 127, 80, 0.8);
  }

  .el-calendar {
    background-color: rgba(255, 255, 255, 0.9);
  }
</style>
