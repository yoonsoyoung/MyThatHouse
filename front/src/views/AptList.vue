<template lang="">
    <div id="listWrap">
        <div id="infoWrap">
            <div id="searchWrap">
                <input type="text" name="word" @input="search" v-model="word" />
            </div>
            <div id="infoList">
                <list-item
                    v-for="(house, index) in filtered"
                    :key="index"
                    :aptName="house.aptNAme"
                    :aptPrice="house.dealAmount"
                    :no="house.no"
                    :dong="house.dong"
                    :code="house.code"
                    :buildYear="house.buildYear"
                    :jibun="house.jibun"
                    :lng="house.lng"
                    :lat="house.lat"
                    :floor="house.floor"
                    :dealYear="house.dealYear"
                    :dealMonth="house.dealMonth"
                    :dealDay="house.dealDay"
                    :area="house.area"
                />
            </div>
        </div>
        <map-item id="mapWrap" />
        <div id="infoSide">
            <div id="mark">
                <div id="ques">?</div>

                <div id="msg">
                    <h2>{{ city }} {{ gugun }} {{ dong }}의 상권정보</h2>
                    <div id="legendTemplate1"></div>
                    <div id="legend"></div>
                    <!-- <div class="px-3 py-2">
                    </div> -->
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
import ListItem from "@/components/ListItem.vue";
import MapItem from "@/components/MapItem.vue";
import bb, { pie } from "billboard.js";
export default {
    name: "AptList",
    data() {
        return {
            word: "",
        };
    },
    components: {
        ListItem,
        MapItem,
    },
    mounted() {
        // //console.log('상권정보');
        // //console.log(Object.keys(this.store[0]));
        //console.log("필터");
        //console.log(this.filtered);
        var chart = bb.generate({
            data: {
                columns: [
                    [Object.keys(this.store[0]), Object.values(this.store[0])],
                    [Object.keys(this.store[1]), Object.values(this.store[1])],
                    [Object.keys(this.store[2]), Object.values(this.store[2])],
                    [Object.keys(this.store[3]), Object.values(this.store[3])],
                    [Object.keys(this.store[4]), Object.values(this.store[4])],
                ],
                type: pie(), // for ESM specify as: pie()
                labels: true,
            },

            legend: {
                show: false,
                // contents: {
                //     bindto: "#legend",
                //     template:
                //         "<span style='color:#fff;padding:1px; margin:2px;background-color:{=COLOR}'>{=TITLE}</span>",
                // },
            },
            pie: {
                padding: 2,
                label: {
                    ratio: 1.2,
                    format: (value, ratio, id) => `${id} ${value}곳\n${(ratio * 100).toFixed(1)}%`,
                },
            },
            size: {
                width: "360",
                height: "360",
            },
            bindto: "#legendTemplate1",
        });
        chart.data.colors({
            [Object.keys(this.store[0])]: "#f1e0ed",
            [Object.keys(this.store[1])]: "#5faed8",
            [Object.keys(this.store[2])]: "#a7ccec",
            [Object.keys(this.store[3])]: "#d9daf0",
            [Object.keys(this.store[4])]: "#bfdaf5",
        });
    },
    computed: {
        ...mapGetters(["bookmarkList", "houseList", "store", "city", "gugun", "dong"]),
        filtered: function () {
            var cname = this.word.trim();
            return this.houseList.filter(function (item) {
                if (item.aptNAme.indexOf(cname) > -1) {
                    return true;
                }
            });
        },
    },
    updated() {
        //console.log("하우스 리스트");
        //console.log(this.houseList);
        //console.log("북마크 리스트");
        //console.log(this.bookmarkList);
    },
    methods: {
        search(e) {
            this.word = e.target.value;
        },
    },
};
</script>
<style>
#mapWrap,
#infoWrap {
    position: absolute;
    top: 80px;
    bottom: 0;
}
#mapWrap {
    position: absolute;
    left: 30%;
    right: 0;
    overflow: hidden;
}
#infoWrap {
    left: 0;
    right: 70%;
}
#infoSide {
    position: fixed;
    right: 10px;
    bottom: 10px;
}
#infoList {
    overflow: auto;
    height: 90%;
    overflow-x: hidden;
}

#searchWrap {
    width: 60%;
    margin: 20px auto 40px;
}
#searchWrap > input {
    border: none;
    outline: none;
    border-bottom: 1px solid black;
    width: 100%;
    background-image: url(../assets/search.png);
    background-position: 0px 2px;
    background-repeat: no-repeat;
    background-size: 22px;
    padding-left: 32px;
    box-sizing: border-box;
}
#msg {
    position: absolute;
    bottom: 0;
    right: 0;
    width: 400px;
    height: 440px;
    border-radius: 8px;
    display: none;
    z-index: 100;
    background-color: rgba(255, 255, 255, 0.5);
}
#mark:hover > #msg {
    /* color: tomato; */
    display: block;
}
#mark:hover > #ques {
    display: none;
}
#ques {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    display: block;
    font-size: 1.6em;
    font-weight: 900;
    color: black;
    border: 1px solid black;
    background-color: rgba(255, 255, 255, 0.5);
    text-align: center;
    line-height: 60px;
}
#msg > h2 {
    font-weight: 700;
    color: black;
    font-size: 1.4em;
    margin-top: 32px;
    text-align: center;
}
#legendTemplate1 {
    position: absolute !important;
    left: 50%;
    transform: translateX(-50%);
}
</style>
