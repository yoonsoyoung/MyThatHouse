<template lang="">
    <div>
        <search-word />
        <dong-chart />
        <deal-table />
        <map-item />
    </div>
</template>
<script>
import SearchWord from "@/components/searchWord.vue";
import DealTable from "@/components/DealTable.vue";
import MapItem from "@/components/MapItem.vue";
import DongChart from "@/components/DongChart.vue";
export default {
    name: "AptDetail",
    components: {
        SearchWord,
        DealTable,
        DongChart,
        MapItem,
    },
};
</script>
<style lang=""></style>
