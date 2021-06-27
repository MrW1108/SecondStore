<template>
  <div class="addSubtractButton">
    <input v-model="quantity" type="number">
    <div>
      <a class="add" href="javascript:;" @click="handleAdd">+</a>
      <a class="sub" @click="handleSubtract" 
        :class="{disabled: quantity === 0, actived: quantity > 0}" 
        href="javascript:;" >
        -
      </a>
    </div>
  </div>
</template>

<script>
  export default {
    data(){
      return {
        quantity: 0
      }
    },
    name:'AddSubtractButton',
    methods: {
      handleAdd(){
        this.quantity++;
        this.$emit("updateQuantity", this.quantity);
      },
      handleSubtract(){
        this.quantity--;
        this.$emit("updateQuantity", this.quantity);
      }
    }
  }
</script>
<style scoped>

.addSubtractButton input{
  height: 30px;
  width: 30px;
  float: left;
  text-align: center;
} 
.addSubtractButton div{
  width: 15px;
  height: 30px;
  float: left;
  margin: 0;
  padding: 0;
  
}
.addSubtractButton a{
  text-align: center;
  vertical-align: middle;
  height: 16px;
  width: 16px;
  background-color: #ccc;
  text-decoration: none;
  border: solid 1px gray;
  display: inline-block;
  margin: 0;
  padding: 0;
  outline: none;
} 

.addSubtractButton a.add{
  margin-top: -1px;
  color: black;
}

.addSubtractButton a.sub{
  margin-top: -3px;
  border-top: none;
}

input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
  -webkit-appearance: none !important;
  margin: 0;
}
.disabled { 
  pointer-events: none; 
  color: gray; 
  cursor: default;
}
.actived{
  color: black;
}
</style>