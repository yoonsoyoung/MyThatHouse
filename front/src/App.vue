<template>
    <div id="app">
        <header-item />
        <router-view />
    </div>
</template>
<script>
import HeaderItem from "@/components/common/HeaderItem.vue";
export default {
    name: "App",
    components: {
        HeaderItem,
    },
};
</script>
<style>
#app {
    overflow: hidden;
}
</style>
