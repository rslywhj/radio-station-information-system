<template>
  <div>
    <input id="txt" type="file" style="display:none" @change="loadTextFromFile">
    <div>
      <input type="text" v-model="title" id="title" disabled="none"/>
      <div v-html="gg" id="word"></div>
    </div>
    <div id="shiyin">
      <el-button type="info" @click="open">开始试音</el-button>
    </div>
  </div>
</template>

<script>
  import { findArticle } from '@/api/articles'

  export default {
    data() {
      return {
        gg: '',
        file: '',
        title: '',
        time: ''
      }
    },
    mounted() {
      this.listLoading = true
      const nowDate = new Date()
      const date = {
        year: nowDate.getFullYear(),
        month: nowDate.getMonth() + 1,
        date: nowDate.getDate()
      }
      const newmonth = date.month > 10 ? date.month : '0' + date.month
      const day = date.date > 10 ? date.date : '0' + date.date
      this.time = date.year + '-' + newmonth + '-' + day
      var dateTime=this.time
      findArticle(dateTime).then(response => {
        //  更新数据devInfo.txt文件接口
        this.title = response.data
        this.title.toUpperCase()
        let xhr = new XMLHttpRequest(),
          okStatus = document.location.protocol === 'file:' ? 0 : 200
        xhr.open('GET', '/txt/'+this.title+'.txt', false)// 文件路径
        xhr.overrideMimeType('text/html;charset=utf-8')//默认为utf-8
        xhr.send(null)
        console.log(xhr) //打印文件信息
        this.dealNum(xhr.responseText)
        this.listLoading = false
      })
    },
    methods: {
      open() {
        this.$alert('开始试音', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `${ '已准备就绪' }`
            });
          }
        });
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
    text-transform: capitalize;
    font-weight:bold;
  }

  #b {
    width: 40px;
    height: 40px;
    border-radius: 10px;
  }

  #bt {
    margin: 30px 10% 100px 65%;
  }

  #shiyin {
    margin: 30px 10% 100px 65%;
  }
</style>
