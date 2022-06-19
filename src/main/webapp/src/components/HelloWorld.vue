<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h3>导航</h3>
    <ul>
      <li><a href="/">首页</a></li>
      <li><a href="/main">插件容器</a></li>
      <li><a href="/blog">博客</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data(){
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
