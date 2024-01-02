<template>
<!--  from for login-->
  <a-form
      :model="formState"
      name="basic"
      :label-col="{ span: 8 }"
      :wrapper-col="{ span: 16 }"
      autocomplete="off"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
  >
    <a-form-item
        label="phone"
        name="phone"
        :rules="[{ required: true, message: 'Please input your phone!' }]"
    >
      <a-input v-model:value="formState.phone" />
    </a-form-item>

    <a-form-item
        label="Code"
        name="code"
        :rules="[{ required: true, message: 'Please input your verification code!' }]"
    >
      <a-input-password v-model:value="formState.code" />
    </a-form-item>

    <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
      <a-button type="primary" html-type="submit">Submit</a-button>
    </a-form-item>
  </a-form>
</template>
<!--开启组合式API的写法-->
<script setup>
//登录相关
import { reactive } from 'vue';
const formState = reactive({
  phone: '',
  code: '',
});
const onFinish = values => {
  console.log('Success:', values);
  login_success();
};
const onFinishFailed = errorInfo => {
  console.log('Failed:', errorInfo);
};

//登录提示
import { message } from 'ant-design-vue';
const login_success = () => {
  message.success('Hello, Welcome to train12306!');
};

</script>