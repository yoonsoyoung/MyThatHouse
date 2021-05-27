<template lang="">
    <div>
        <div v-if="houseList.length" id="map">
            <GmapMap
                :center="{ lat: center.lat, lng: center.lng }"
                :zoom="18"
                map-type-id="roadmap"
            >
                <GmapMarker
                    :key="index"
                    v-for="(m, index) in houseList"
                    :position="{ lat: houseList[index].lat, lng: houseList[index].lng }"
                    :clickable="true"
                    :draggable="false"
                    :animation="2"
                    @click="center = m.position"
                />
            </GmapMap>
        </div>
        <div v-else>정보가 없습니다.</div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
    name: "MapItem",
    computed: {
        ...mapGetters(["houseList", "center"]),
    },
};
</script>
<style>
.vue-map-container {
    width: 1342;
    height: 886px;
}
</style>
