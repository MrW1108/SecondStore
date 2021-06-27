<template>
<div class="home">
    <Navigation/>
    <Header/>
    <router-view/>
</div>
</template>

<script>
import Header from '../components/Header'
import Navigation from '../components/Navigation'
export default {
    name: 'home',
    components: {
        Header,
        Navigation
    }
}
</script>

<style scoped>
.home {
  margin: 0 auto;
  text-align: center;
  min-height: 100vh;
  background-color: #f4f4f4;
  color: #846e6e;
  overflow: hidden;
}

</style>