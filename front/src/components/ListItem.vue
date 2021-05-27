<template lang="">
    <div id="listItem">
        <b-button
            :class="visible ? null : 'collapsed'"
            :aria-expanded="visible ? 'true' : 'false'"
            :aria-controls="no"
            @click="viewDetail"
        >
            <div>
                <div id="aptName">
                    <span>{{ aptName }}</span>
                </div>
                <div id="aptPrice">
                    <span>{{ aptPrice }}</span>
                </div>
            </div>
            <button v-if="show" @click="addBookmark"><img src="../assets/heart2.png" /></button>
            <button v-else @click="deleteBookmark"><img src="../assets/heart.png" /></button>
        </b-button>
        <b-collapse :id="`${no}`" v-model="visible" class="mt-2">
            <b-card>
                <table class="infoTable">
                    <thead>
                        <tr>
                            <th>주소</th>
                            <th>지번</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ city }} {{ gugun }} {{ dong }}</td>
                            <td>{{ jibun }}</td>
                        </tr>
                    </tbody>
                </table>
                <table class="infoTable">
                    <thead>
                        <tr>
                            <th>아파트 이름</th>
                            <th>건축년도</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ aptName }}</td>
                            <td>{{ buildYear }}년</td>
                        </tr>
                    </tbody>
                </table>
                <table class="infoTable">
                    <thead>
                        <tr>
                            <th>매매날짜</th>
                            <th>가격</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ dealYear }}년 {{ dealMonth }}월 {{ dealDay }}일</td>
                            <td>{{ aptPriceCheck }}</td>
                        </tr>
                    </tbody>
                </table>
                <table class="infoTable">
                    <thead>
                        <tr>
                            <th>층</th>
                            <th>면적</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ floor }}층</td>
                            <td>{{ area }}</td>
                        </tr>
                    </tbody>
                </table>
            </b-card>
        </b-collapse>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
import { createInstance } from "@/api/index.js";
// import http from "@/util/http-common";
export default {
    name: "ListItem",
    props: [
        "aptName",
        "aptPrice",
        "no",
        "dong",
        "code",
        "buildYear",
        "jibun",
        "lng",
        "lat",
        "floor",
        "area",
        "dealYear",
        "dealMonth",
        "dealDay",
    ],
    data() {
        return {
            show: { type: Boolean },
            visible: false,
        };
    },
    computed: {
        ...mapGetters(["bookmarkList", "userInfo", "city", "gugun"]),
        aptPriceCheck() {
            return this.aptPrice.replace(/^\s+|\s+$/g, "");
        },
    },
    created() {
        // this.$store.dispatch("getBookmarkList", this.userInfo);
        // this.$store.dispatch("getBookmarkList", this.userInfo.id);
        // //console.log("indexOF" + this.bookmarkList.indexOf(this.no));
        //console.log("북마크");

        //console.log(this.bookmarkList);
        //console.log(this.no);
        //console.log(this.aptName);
        for (let i = 0; i < this.bookmarkList.length; i++) {
            if (this.bookmarkList[i].no === this.no) {
                //console.log("번호 : " + this.no);
                //console.log("비교 번호 : " + this.bookmarkList[i].no);
                this.show = false;
            }
        }
    },
    methods: {
        addBookmark(e) {
            e.stopPropagation();

            let house = {
                city: this.city,
                gugun: this.gugun,
                id: this.userInfo.id,
                no: this.no,
                dong: this.dong,
                aptName: this.aptName,
                code: this.code,
                buildYear: this.buildYear,
                jibun: this.jibun,
                lat: this.lat,
                lng: this.lng,
            };
            this.$store.dispatch("addBookmarkList", house);
            this.$store.dispatch("getBookmarkList", this.userInfo.id);
            this.show = false;
        },
        deleteBookmark(e) {
            e.stopPropagation();
            let houseInfo = {
                city: this.city,
                gugun: this.gugun,
                no: this.no,
                dong: this.dong,
                aptName: this.aptName,
                code: this.code,
                buildYear: this.buildYear,
                jibun: this.jibun,
                lat: this.lat,
                lng: this.lng,
            };
            //console.log("/jjim/" + this.userInfo.id);
            const instance = createInstance();
            //console.log(houseInfo);
            //console.log(this.userInfo.id);
            instance.defaults.headers["access-token"] = window.localStorage.getItem("access-token");
            instance
                .post("/jjim/delete/" + this.userInfo.id, houseInfo)
                .then(() => {
                    this.$store.dispatch("getBookmarkList", this.userInfo.id);
                    this.show = true;
                })
                .catch(() => {
                    alert("에러발생!");
                });
        },
        viewDetail() {
            this.visible = !this.visible;
            this.$store.dispatch("mvCenterAction", {
                lat: this.lat,
                lng: this.lng,
                zoom: 20,
            });
        },
    },
};
</script>
<style>
#listItem {
    background-color: #f6f6f6;
    width: 90%;
    margin: 30px auto 0;
}
#listItem > button {
    width: 100%;

    padding: 20px 0;
    border-radius: 0;
    background-color: transparent;
    border: none;
    padding-right: 20px;
    padding-left: 24px;
}
#listItem > button > button {
    float: right;
}
#listItem > button > div {
    float: left;
}
#listItem > button {
    content: "";
    display: block;
    clear: both;
}
#aptName {
    color: black;
    font-weight: bold;
    font-size: 1.1em;
    text-align: left;
}
#aptPrice {
    color: #909090;
    text-align: left;
}
button > img {
    width: 24px;
}
th {
    color: black;
}
.infoTable {
    width: 100%;
    margin-bottom: 15px;
}
.infoTable th {
    color: black;
    background-color: transparent;
    width: 50%;
}
</style>
