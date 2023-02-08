<template>
  <div id="app">
    <h1>{{yearbookVO.name}}</h1>
    <h1>{{yearbookVO.cellphone}}</h1>
    <h1>{{yearbookVO.gender}}</h1>
    <h1>{{yearbookVO.birthday}}</h1>
    <h1>{{yearbookVO.email}}</h1>
    <h1>{{yearbookVO.hobby}}</h1>
    <h1>{{yearbookVO.address}}</h1>
  </div>
</template>

<script>
import router from "../router";
import {queryById} from "../api/request";

export default {
  name: "Detail",
  created() {
    // 获得此时路由参数，也就是此时对象的主键
    const id = router.currentRoute.params.id
    // 根据主键查询用户信息
    queryById(id).then(res => {
      this.yearbookVO = res.data
    }).catch()
  },
  data() {
    return {
      yearbookVO: ""
    }
  }
}
</script>

<style scoped>

</style>
