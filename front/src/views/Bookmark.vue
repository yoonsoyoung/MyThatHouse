<template>
    <div id="bookmarkWrap">
        <div id="bookmarkTable">
            <h2>찜목록</h2>
            <div>
                <b-table striped hover :items="items" class="booktable"></b-table>
            </div>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
    name: "bookmark",
    data() {
        return {
            items: [],
        };
    },
    computed: {
        ...mapGetters(["bookmarkList", "userInfo", "city", "gugun"]),
    },
    created() {
        //console.log("찜");
        //console.log(this.userInfo);
        this.$store.dispatch("getBookmarkList", this.userInfo.id);
        for (let i = 0; i < this.bookmarkList.length; i++) {
            this.items.push({
                주소: `${this.bookmarkList[i].city} ${this.bookmarkList[i].gugun} ${this.bookmarkList[i].dong}`,
                지번: this.bookmarkList[i].jibun,
                "아파트 이름": this.bookmarkList[i].aptNAme,
                건축년도: `${this.bookmarkList[i].buildYear}년`,
                매매날짜: `${this.bookmarkList[i].dealYear}년 ${this.bookmarkList[i].dealMonth}년 ${this.bookmarkList[i].dealDay}일`,
                가격: this.bookmarkList[i].dealAmount,
                층: this.bookmarkList[i].floor,
                면적: this.bookmarkList[i].area,
            });
        }
    },
};
</script>
<style>
#bookmarkTable > div {
    overflow: auto;
    height: 740px;
}
#bookmarkTable {
    width: 80%;
    margin: 0 auto;
}
#bookmarkTable > h2 {
    font-size: 1.3em;
    font-weight: bold;
    padding: 30px 20px;
}
table > thead > tr > th {
    color: black;
    font-size: 1.1em;
}
.table > tbody > tr > td {
    padding: 16px;
}
</style>
