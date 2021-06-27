const state = {
  items: [],  //购物车
  payItems:[],//要结算的商品
}
// mutations
const mutations = {
  //获取购物车数据
  getItems(state,list){
    state.items = list;
  },
  // 添加商品到购物车中
  pushProductToCart(state, { id, commodityId, photoOne, name, price, quantity}) {
    if(! quantity)
      quantity = 1;
    state.items.push({ id, commodityId, photoOne, name, price, quantity });
  },
  // 增加商品数量
  incrementItemQuantity(state, { id, quantity }) {
    let cartItem = state.items.find(item => item.id == id);
    cartItem.quantity += quantity;
  },
  //减少商品数量
  reduceItemQuantity(state, {id, quantity}){
    let cartItem = state.items.find(item => item.id == id);
    cartItem.quantity -= quantity;
  },
  // 用于清空购物车
  setCartItems(state) {
    state.items = []
  },
  // 删除购物车中的产品(可以多个)
  deleteCartItem(state, list){
    console.log(list)
    for(let i=0; i<list.length; i++){
      let index = state.items.findIndex(item => item.id === list[i].id);       
      if(index > -1)
        state.items.splice(index, 1);
    }
  },

  //获得要结算的商品列表
  getPayItems(state, list){
    state.payItems = list;
  }
}


const getters = {
  // 获取购物车中商品的数量
  itemsCount: (state) => {
    return state.items.length;
  },

  //获得待结算的商品总价
  totalPrice: (state) => {
    return  state.payItems.reduce((total, product) => {
      return total + product.price * product.discount * product.quantity
    },0).toFixed(2)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  getters,
}