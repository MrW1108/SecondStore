<template>
  <div class="tabComponent">
    <button
      v-for="tab in tabs"
      :key="tab.title"
      :class="['tab-button', { active: currentTab === tab.title }]"
      @click="currentTab = tab.title">
      {{ tab.displayName }}
    </button>

    <keep-alive>
      <component :is="currentTabComponent" :content="content" class="tab"></component>
    </keep-alive>
  </div>
</template>

<script>
  import CommodityIntroduction from './CommodityIntroduction'
  export default {
    name:'TabComponent',
    props: {
      content: {
        type: String,
        default: ''
      }
    },
    data () {
      return {
        currentTab: 'introduction',
        tabs: [
          {title: 'introduction', displayName: '商品介绍'}, 
          {title: 'comment', displayName: '商品评价'},
          {title: 'qa', displayName: '商品问答'}
        ]
      };
    },

    components: {
      CommodityIntroduction,
      'CommodityComment' : () => import('./CommodityCommentList'),
      'CommodityQa' : () => import('./CommodityQA') 
    },

    computed: {
      currentTabComponent: function () {
        return 'Commodity-' + this.currentTab
      }
    }
  }
</script>
<style scoped>
.tabComponent{
  float: left;
  width: 100%;
  text-align: left;
  margin-top: 30px;
  margin-left: 40px;
}
.tab-button {
  padding: 6px 10px;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
  border: solid 1px #ccc;
  cursor: pointer;
  background: #f0f0f0;
  margin-bottom: -1px;
  margin-right: -1px;
}
.tab-button:hover {
  background: #e0e0e0;
}
.tab-button.active {
  background: #cdcdcd;
}
.tab {
  border: solid 1px #ccc;
  padding: 10px;
}
</style>