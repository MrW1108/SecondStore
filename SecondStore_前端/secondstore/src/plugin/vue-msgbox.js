import MessageBox from './MessageBox'

const msgBox = {};

msgBox.install = function(Vue){
  Vue.prototype.$msgBox = msgBox;
  msgBox.show = (function(){
    // 组件数据属性和事件响应函数的默认值
    let defaults = {
      title: '',
      ok: '确定',
      cancel: '',
      handleOk: function(){},
      handleCancel: function(){}
    }
    
    let MessageBoxImpl = Vue.extend(MessageBox);
    // 调用msgBox.show函数时需要提供一个选项对象，
    // 用于初始化组件内的各个选项
    return function(opts){ //配置参数
      for(let attr in opts){
        defaults[attr] = opts[attr];
      }
  
      let vm = new MessageBoxImpl({
        el: document.createElement("div"),  // 创建一个组件挂载的根元素
        data() {
          return {
            title: defaults.title,
            ok: defaults.ok,
            cancel: defaults.cancel, 
          }
        },
        methods: {
          handleOk(){
            defaults.handleOk.call(this);
            // 点击“确定”按钮时，从DOM中删除提示框组件
            document.body.removeChild(vm.$el);
          },
          handleCancel(){
            defaults.handleCancel.call(this);
            // 点击“取消”按钮时，从DOM中删除提示框组件
            document.body.removeChild(vm.$el);
          }
        }
      });
      // 将组件绑定的根元素添加到HTML body元素内
      document.body.appendChild(vm.$el)
    }
  })();
  
}
export default msgBox;
