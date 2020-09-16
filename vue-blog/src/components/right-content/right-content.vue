<template>
  <div class="right-content">
    <board :title='"公告"' :content='user.notice'></board>
    <board :title='"标签"'><tags></tags></board>
    <board :title='"博客日历"' :content='tip'></board>
  </div>
</template>

<script>
import Tags from 'base/tags/tags'
import Board from 'base/board/board'
import {mapGetters} from 'vuex'
export default {
  components: {
    Tags,
    Board
  },
  props: {},
  data () {
    return {
      tip: `日历组件组件又鸽了🕊<br>&emsp;&emsp;有什么问题和Bug欢迎Github留言。<br>&emsp;&emsp;留言板和日历功能等建模比赛后再做下。<br>&emsp;&emsp;第一次登录图片是一样的(图片接口问题)，刷新一下就好了~`
    }
  },
  watch: {},
  computed: {
    ...mapGetters([
      'user'
    ])
  },
  methods: {},
  created () {},
  mounted () {}
}
</script>
<style  lang="scss" scoped>
.right-content{
  margin: calc(var(--margin) * 1px);
  width: 300px;
  @media screen and (max-width:1200px){
    display: none;
  }
}
</style>
