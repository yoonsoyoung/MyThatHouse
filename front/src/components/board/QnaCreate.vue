<template>
    <div>
        <update-board type ="create" boardtype="qna"></update-board>
    </div>
</template>
<script>
import UpdateBoard from '@/components/board/include/BoardForm.vue';
export default {
    name: 'qnacreate',
    components: {
        UpdateBoard,
    },
};
</script>