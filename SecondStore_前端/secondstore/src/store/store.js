import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import cart from './modules/cart'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    waitCheck:[],//商家待审核商品列表
    soldOut:[],//商家已下架商品列表
    evaluation:[],  //单个商品的评论
  },
  mutations: {
    //商家待审核商品列表
    getWaitCheck(state, list){
      state.waitCheck = list;
    },
    addWaitCheck(state, value){
      state.waitCheck.unshift(value);
    },
    deleteWaitCheck(state, index){
      state.waitCheck.splice(index, 1);
    },

    //商家已下架商品列表 
    getSoldOut(state, list){
      state.soldOut = list;
    },
    addSoldOut(state, value){
      state.soldOut.unshift(value);
    },
    deleteSoldOut(state, index){
      state.soldOut.splice(index, 1);
    },

    //单个商品的评论
    getEvaluation(state,list){
      state.evaluation = list;
    },
    addEvaluation(state, value){
      state.evaluation.unshift(value);
    },
  },
  actions: {

  },
  modules: {
    user,
    cart
  }
})
