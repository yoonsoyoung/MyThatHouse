<template>
    <div>
        <update-board type ="create" boardtype="notice"></update-board>
    </div>
</template>
<script>
import UpdateBoard from '@/components/board/include/BoardForm.vue';
export default {
    name: 'create',
    components: {
        UpdateBoard,
    },
};
</script>