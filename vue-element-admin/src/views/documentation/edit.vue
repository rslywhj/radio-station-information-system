<template>
  <div>
    <button @click="txtbtn" class="el-icon-document" id="b"></button>
    <input id="txt" type="file" style="display:none" ref="file" @change="loadTextFromFile">
    <div>
      <input type="text" v-model="title" id="title"/>
      <div v-html="gg" id="word"></div>
      <div>
        <el-button type="primary" icon="el-icon-upload2" id="bt" @click="submit()">上传</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  import {submitArticle} from '@/api/articles'
  import router from '@/router/index.js'

  export default {
    data() {
      return {
        gg: '',
        title: '',
        file: null
      }
    },
    methods: {
      submit() {
        this.listLoading = true
        let data = new FormData() // 创建form对象
        data.append('file', this.$refs.file.files[0])
        var date = JSON.stringify(router.currentRoute.params.date)
        console.log(router.currentRoute.params.date)
        data.append('date',date)
        submitArticle(data).then(response => {
          console.log("上传文件响应：" + response)
          this.listLoading = false
          this.$message.success('上传成功')
        })
      },
      // txt文本
      txtbtn() {
        document.getElementById('txt').click()
      },
      loadTextFromFile(e) {
        const file = e.target.files[0]
        const name = file.name.split('.').splice(-1).toString()
        const a = file.name.split('.')[0]
        if (name !== 'txt') {
          this.$message.success('文件类型错误,请重新选择文件')
          return
        }
        const reader = new FileReader()
        if (typeof FileReader === 'undefined') {
          alert('您的浏览器不支持FileReader接口')
        }
        reader.onload = (e) => this.$emit('load', this.dealNum(e.target.result))
        reader.readAsText(file, 'utf-8')
        this.title = a
        this.file = file
      },
      dealNum(item) {
        /* this.gg = item
        console.log(item)*/
        const word = item.replace(/\n|\r\n/g, '<br/>')
        this.gg = word
      }
    }
  }
</script>

<style>
  #word {
    background: rgba(255, 255, 255, 0.5);
    width: 800px;
    min-height: 600px;
    margin: 0 10% 0 25%;
    padding: 30px 50px 50px 50px;
    border-radius: 10px;
  }

  #title {
    width: 400px;
    height: 50px;
    border-radius: 10px;
    margin: 0 0 20px 35%;
    text-align: center;
    background: rgba(255, 255, 255, 0.5);
  }

  #b {
    width: 40px;
    height: 40px;
    border-radius: 10px;
  }

  #bt {
    margin: 30px 10% 100px 65%;
  }
</style>
