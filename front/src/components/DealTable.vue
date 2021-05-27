<template lang="">
    <div>
        <b-table striped hover :items="items"></b-table>
    </div>
</template>
<script>
export default {
    name: "DealTable",
    data() {
        return {
            items: [],
        };
    },
};
</script>
<style lang=""></style>
