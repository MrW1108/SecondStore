const state = {
  user:null,
  sell:null,
  admin:null
}

const mutations = {
  saveUser(state, user){  //更改用户状态信息
    state.user = user
  },
  deleteUser(state){
    state.user = null
  },
  saveSell(state, sell){
    state.sell = sell;
  },
  deleteSell(state){
    state.sell = null
  },
  saveAdmin(state, admin){
    state.admin = admin
  },  
  deleteAdmin(state){
    state.admin = null
  }
}
export default{
  namespaced: true,
  state,
  mutations
}
