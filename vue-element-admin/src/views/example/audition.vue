<template>
  <div>
    <el-col id="jltl">
      <el-card shadow="never">
        1、本台任何成员在工作中应一律佩戴个人工作证。
      </el-card>
      <el-card shadow="never">
        2、本台任何成员进台时，播音时一律将手机关机或者调成静音，除特殊情况外不允许将手机带进播音间。
      </el-card>
      <el-card shadow="never">
        3、禁止在播音间内做与播音无关的活动，在台内严禁吸烟吐痰，严禁带零食饮料进台（瓶装饮品除外）。
      </el-card>
      <el-card shadow="never">
        4、除台内任务或特殊情况，本台任何成员非播音时间不得擅自进入广播台，进入广播台时，需穿戴整齐，不准穿高跟鞋，不准衣冠不整（如穿家常拖鞋）。
      </el-card>
      <el-card shadow="never">
        5、保证准确的播音时间，如无特殊情况，不得擅自缩短或延长（中午30分钟，晚上不少于40分钟）。
      </el-card>
      <el-card shadow="never">
        6、不得迟到、早退、缺勤。遇有特殊情况不能出勤者应提前一天向纪律督导打电话请假，日常请假需要找人代替进台，例会请假正常理由不超三次，还要向本组组长和当天值日编辑报备。
      </el-card>
      <el-card shadow="never">
        7、设备如有损坏，不可私自查看修理，需及时报告技术总监并由专业人员进行检修。
      </el-card>
      <el-card shadow="never">
        8、播音结束后，在整理完稿件，依次关闭播音设备和电源，打扫台内卫生、填写卫生表后方可离台。
      </el-card>
      <el-card shadow="never">
        9、播音结束后必须及时和下次进台人员交接钥匙，双方共同确保直播工作的正常进行。
      </el-card>
      <el-card shadow="never">
        10、本台任何成员都有义务维护保管台内公有物品，且不经允许不得私自将台内公共设备借给非本组织成员。
      </el-card>
      <el-card shadow="never">
        11、所有广播台参加或承办的活动，有节目安排的人必须按时到达。若有特殊情况，需提前与纪律督导报备并和节目负责人请假，否则，按照无故未到处理。
      </el-card>
      <el-card shadow="never">
        12、不经允许广播台内成员不许带台外人进台。
      </el-card>
      <el-card shadow="never">
        13、各组长有义务协助纪律督导组管理纪律，若有建议或发现问题应及时提出。
      </el-card>
      <el-card shadow="never">
        14、违反以上纪律条例者，抄写纪律条例并根据具体情况决定是否写检查。情节严重者给予通报批评，通报在台内公示七天。
      </el-card>
    </el-col>
    <div id="kaibo">
      <el-button  @click="play()">开播</el-button>
    </div>
    <div hidden>
      <audio ref="audio" controls="controls" class="bird-native-player" src="../../assets/片头.mp3"></audio>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'TestAudio',
    data () {
      return {
      }
    },
    methods: {
      play(){
        this.$refs.audio.play()
      },
    },
    mounted () {
    }
  }
</script>

<style scoped>
  #jltl{
    width: 1000px;
    margin: 30px 10% 2% 10%;
  }
  #kaibo {
    margin: 30px 10% 100px 65%;
  }
</style>
