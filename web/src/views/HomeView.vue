<template>
  <div style="">
    <LoginView></LoginView>
  </div>

</template>

<script>
// @ is an alias to /src
import LoginView from "@/views/LoginView.vue";

export default {
  name: 'HomeView',
  components: {
    LoginView
  }
}
</script>

<style>
.login_middle {

}
</style>