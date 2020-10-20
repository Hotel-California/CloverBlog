<template>
  <router-link tag="div" class="outer" :to="{name: 'blog', params: {id: blog.userId, blogid: blog.blogId }}" v-if="blog.visible" v-show="(category===''||blog.category===category)&&(isOfTags(blog.tags))">
    <transition name="fade-in">
      <div class="blog-box" v-if="blogBox" key="0">
        <div class="pic" :style="{backgroundImage : user.api ? `url(${user.api}/?t=${blog.blogId})` : `url(https://random.52ecy.cn/randbg.php/?t=${blog.blogId})`}"></div>
        <div class="mask"></div>
        <div class="title">{{blog.title}}</div>
        <div class="foot">
          <div class="description">{{blog.preview}}</div>
          <div class="line"></div>
          <div class="sum">
            <div class="time">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-shijian"></use>
              </svg>
              {{blog.uploadTime}}
            </div>
            <div class="count">
              <div>
                <svg class="icon" aria-hidden="true">
                  <use xlink:href="#icon-liulan2"></use>
                </svg>
                {{blog.views}}
              </div>
              <div>
                <svg class="icon" aria-hidden="true">
                  <use xlink:href="#icon-pinglun2"></use>
                </svg>
                {{blog.comments}}
              </div>
              <div>
                <svg class="icon" aria-hidden="true">
                  <use xlink:href="#icon-dianzan1"></use>
                </svg>
                {{blog.likes}}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-else class="list" key="1">
        <div style="width:40px;margin-left:5px;">{{index+1}}</div>
        <div class="list-title">{{blog.title}}</div>
        <div class="list-desc">{{blog.preview}}</div>
        <div class="list-uptm">{{blog.uploadTime.substring(0,blog.uploadTime.length-3)}}</div>
      </div>
    </transition>
  </router-link>
</template>

<script>
// https://api.imo6.cn/nice/api.php
// https://api.imo6.cn/anime720/api.php
import {mapGetters} from 'vuex'
export default {
  components: {},
  props: {
    blog: {
      type: Object,
      default () {
        return {}
      }
    },
    index: {
      type: Number,
      default () {
        return 0
      }
    }
  },
  data () {
    return {}
  },
  watch: {},
  computed: {
    ...mapGetters([
      'user',
      'category',
      'tagsOn',
      'blogBox'
    ])
  },
  methods: {
    isOfTags (tags) {
      for (let tagOn of this.tagsOn) {
        if (!tags.includes(tagOn)) {
          return false
        }
      }
      return true
    }
  },
  created () {},
  mounted () {}
}
</script>
<style  lang="scss" scoped>
.outer {
  min-height: 60px;
  position: relative;
  margin-bottom: 10px;
}
.blog-box{
  box-shadow: 1px 1px 3px 1px rgba(0,0,0,.2);
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  transition: box-shadow 0.4s ease;
  position: relative;
  width: 100%;
  padding-top: 40%;
  // height: 200px;
  margin-bottom: 3%;
  overflow: hidden;
  .pic {
    top: 0;
    position: absolute;
    width: 100%;
    height: 100%;
    // background: url(https://random.52ecy.cn/randbg.php);
    background-position: center 36%;
    background-size: cover;
    opacity: calc(var(--opacity) * 2);
    transition: transform 0.2s ease, filter 0.6s ease;
  }
  .mask {
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
    transition: background-color 0.6s ease;
    background-color: rgba($color: #000000, $alpha: 0.15);
    opacity: var(--opacity);
  }
  .title {
    width: 80%;
    line-height: 50px;
    font-size: 3.6vw;
    line-height: 4vw;
    position: absolute;
    transition: all 0.2s ease;
    top: 40%;
    left: 50%;
    color: #fff;
    text-shadow: 0 0 5px #000;
    transform: translate(-50%, -50%);
    @media screen and (max-width:1200px){
      font-size: 6vw;
    }
    @media screen and (max-width:1600px) and (min-width:1200px){
      font-size: 3vw;
    }
  }
  .foot {
    position: absolute;
    bottom: 10%;
    height:30%;
    width: 100%;
    opacity: 0;
    transition: all 0.2s ease;
    font-size: 1.3rem;
    display: flex;
    flex-direction: column;
    @media screen and (max-width:1200px){
      font-size: 2vw;
    }
    @media screen and (max-width:1600px) and (min-width:1200px){
      font-size: 1.2vw;
    }
    @media screen and (min-width:2000px){
      font-size: 1.8vw;
    }
    .description {
      flex: 1;
      top: 0;
      margin: 0 1vw;
      color: #fff;
      text-align: left;
      overflow: hidden;
      text-indent: 4rem;
      font-size: 2vw;
    }
    .line {
      width: 96%;
      height: 1px;
      margin: 0.2rem auto;
      background-color: rgba($color: #ffffff, $alpha: 0.5);;
    }
    .sum {
      color: #fff;
      width: 96%;
      margin: 0.3rem auto;
      margin-top: 0;
      display: flex;
      .icon {
        font-size: 1.3rem;
        margin-bottom: 0.15rem;
        @media screen and (max-width:1200px){
          font-size: 2vw;
        }
        @media screen and (max-width:1600px) and (min-width:1200px){
          font-size: 1.2vw;
        }
        @media screen and (min-width:2000px){
          font-size: 1.8vw;
        }
      }
      .time {
        text-align: left;
        flex: 1;
      }
      .count {
        div {
          display: inline-block;
          margin-left: 1vw;
        }
      }
    }
  }
  &:hover > .pic {
    transform: scale(1.2);
    filter: blur(2px);
    -webkit-filter: blur(2px);
  }
  &:hover {
    box-shadow: 3px 3px 12px 3px rgba(0,0,0,.2)!important;
  }
  &:hover > .mask{
    background-color: rgba($color: #4d4c4c, $alpha: 0.5);
  }
  &:hover > .title{
    text-shadow: 0 0 2px #fff;
    top: 22%;
    transform: translate(-50%, -50%) scale(1.2);
  }
  &:hover > .foot{
    bottom: 0;
    height:54%;
    opacity: 1;
  }
}
.list {
  position: absolute;
  top: 0;
  background-color: var(--color);
  width: 100%;
  height: 60px;
  font-size: 1.7rem;
  display: flex;
  align-items: center;
  border-radius: 9px;
  opacity: 0.95;
  &:hover {
    background-color: rgb(231, 231, 231);
  }
  .list-title {
    width: 36%;
    text-align: left;
  }
  .list-desc {
    text-align: left;
    flex: 1;
    font-size: 16px;
  }
  .list-uptm {
    margin-right: 10px;
    font-size: 20px;
  }
}
.fade-in-enter-active {
  transition: all 1.2s ease;
}
.fade-in-leave-active {
  transition: all 1.2s ease-out;
}
.fade-in-enter, .fade-in-leave-to {
  padding-top: 0;
  opacity: 0;
  margin-bottom: 0;
}
</style>
