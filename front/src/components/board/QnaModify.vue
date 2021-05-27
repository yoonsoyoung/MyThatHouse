<template>
  <div>
    <update-form type="modify" boardtype="qna"/>
  </div>
</template>

<script>
import UpdateForm from '@/components/board/include/BoardForm.vue';
export default {
  name: 'modify',
  components: {
    UpdateForm,
  },
};
</script>

<style></style>