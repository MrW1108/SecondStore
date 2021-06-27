<template>
  <div>
    <p>{{ content }}</p>
  </div>
</template>

<script>
  export default {
    name: 'CommodityIntroduction',
    props: {
      content: {
        type: String,
        default: ''
      }
    },
    
  }
</script>
<style scoped>

</style>