<template>
    <div id="selectWrap">
        <div id="selectBox">
            <select v-model="city" @change="changeCity">
                <option value="" disabled selected hidden>선택하세요</option>
                <option v-for="(c, index) in cityList" :key="index" v-bind:value="c">
                    {{ c }}
                </option>
            </select>
            <select v-model="gugun" @change="changeGu">
                <option value="" disabled selected hidden>선택하세요</option>
                <option v-for="(g, index) in gugunList" :key="index" v-bind:value="g">
                    {{ g }}
                </option>
            </select>
            <select v-model="dong" @change="changeDong">
                <option value="" disabled selected hidden>선택하세요</option>
                <option v-for="(d, index) in dongList" :key="index" v-bind:value="d">
                    {{ d }}
                </option>
            </select>
            <button @click="search">
                <img id="searchIcon" src="../assets/search.png" alt="icon" />
            </button>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
import http from "@/util/http-common";
export default {
    name: "index",
    data() {
        return {
            cityList: [
                "서울특별시",
                "부산광역시",
                "대구광역시",
                "인천광역시",
                "광주광역시",
                "대전광역시",
                "울산광역시",
                "세종특별자치시",
                "경기도",
                "강원도",
                "충청북도",
                "충청남도",
                "전라북도",
                "전라남도",
                "경상북도",
                "경상남도",
                "제주특별자치시도",
            ],
            gugunList: [],
            dongList: [],
            city: "",
            gugun: "",
            dong: "",
        };
    },
    created() {
        this.$store.dispatch("getTop10");
    },
    computed: {
        ...mapGetters(["userInfo"]),
    },
    methods: {
        changeCity() {
            this.$store.dispatch("getCity", this.city);
            http.get("/getGu/" + this.city)
                .then(({ data }) => {
                    this.gugunList = data;
                    //console.log(this.gugunList);
                })
                .catch(() => {
                    alert("에러발생!");
                });
        },
        changeGu() {
            this.$store.dispatch("getGugun", this.gugun);
            http.get("/getDong/" + this.gugun)
                .then(({ data }) => {
                    this.dongList = data;
                })
                .catch(() => {
                    alert("에러발생!");
                });
        },
        changeDong() {
            this.$store.dispatch("getDong", this.dong);
        },
        search() {
            // this.$store.dispatch("searchList", {
            //     city: this.city,
            //     gu: this.gugun,
            //     dong: this.dong,
            // });
            // this.$store.dispatch("searchStore", {
            //     gugun: this.gugun,
            //     dong: this.dong,
            // });
            // this.$router.push("/aptList");

            http.post("/search", {
                city: this.city,
                gu: this.gugun,
                dong: this.dong,
            })
                .then(({ data }) => {
                    this.$store.dispatch("getHouseList", data);
                    // 비동기 아니고 뮤테이션으로 이 구조를 가져가던가
                    // v-for에서 자식까지 동기화시킬 수 있는 방법을 찾거나
                    // 현재 상태에서 mutation으로 상태 전이만 시킬건지..
                    this.$router.push("/aptList");
                    http.get("/store/" + this.gugun + "/" + this.dong)
                        .then(({ data }) => {
                            this.$store.commit("setStore", data);
                            //console.log(data);
                        })
                        .catch(() => {
                            alert("상권정보 실패");
                        });
                })
                .catch(() => {
                    alert("아파트 정보가 없습니다.");
                });
        },
    },
};
</script>
<style>
#selectWrap {
    position: absolute;
    top: 80px;
    left: 0;
    bottom: 0;
    right: 0;
}
#searchIcon {
    width: 40px;
}
#selectBox {
    position: absolute;
    top: 36%;
    left: 50%;
    transform: translate(-50%, -50%);
}
#selectBox > select {
    border: none;
    border-bottom: 1px solid black;

    margin: 0 20px;
    height: 40px;
    width: 140px;
    font-size: 1.4em;
}
select > option {
    color: black;
    padding: 4px;
    padding: 10px 0;
}
button {
    outline: none;
    border: none;
    background-color: transparent;
    margin: 0;
    padding: 10px;
}
</style>
