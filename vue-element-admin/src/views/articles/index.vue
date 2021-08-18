<template>
  <div class="app-container">
    <aside>
      此处应该有说明文字
    </aside>

    <el-button  type="primary" @click.prevent.stop="articles">
      查看
    </el-button>
    <div> {{hh}}</div>
  </div>
</template>

<script>

  import {article} from "@/api/articles";


  export default {
    name: 'articles',
    data() {
      return {
        hh:""
      }
    },
    methods: {
      articles() {
        article().then(response =>{
          console.log(response.data);
          this.hh = response.data
        })
      }
    }
  }
</script>
