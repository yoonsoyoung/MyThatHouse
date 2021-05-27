<template>
    <b-container>
        <b-row>
            <h1 class="text-center mb-5" id="qnaTitle">QnA</h1>
        </b-row>
        <b-row class="mt-5">
            <b-col class="text-left">
                <b-input-group class="mb-2">
                    <b-input-group-prepend is-text>
                        <b-icon icon="search"></b-icon>
                    </b-input-group-prepend>
                    <b-input type="text" v-model.lazy="word" placeholder="제목으로 검색" />
                </b-input-group>
            </b-col>
            <b-col class="text-right">
                <b-button pill class="btn-main" @click="movePage">글쓰기</b-button>
            </b-col>
        </b-row>
        <div v-if="qnaItems.length" class="text-center">
            <b-table
                hover
                id="list_table"
                :items="filtered"
                :per-page="perPage"
                :current-page="currentPage"
                :fields="fields"
                :sort-by.sync="sortBy"
                :sort-desc.sync="sortDesc"
                @row-clicked="moveDetail"
            >
            </b-table>
            <b-pagination
                align="center"
                v-model="currentPage"
                :total-rows="rows"
                :per-page="perPage"
                aria-controls="list_table"
            ></b-pagination>
        </div>
        <div v-else>글이 없습니다.</div>

        <b-button id="up" @click="pageUp">▲</b-button>
    </b-container>
</template>
<script>
// import BoardRow from "@/components/board/include/BoardRow.vue";
import { mapGetters } from "vuex";
import moment from "moment";
export default {
    name: "qna",
    components: {
        // BoardRow,
    },
    data: function () {
        return {
            word: "",
            perPage: 10,
            currentPage: 1,
            sortBy: 'no',
            sortDesc: true,
            fields: [
                { key: "no", label: "번호", thStyle: { width: "10%" },sortable:true },
                { key: "userId", label: "작성자", thStyle: { width: "20%" } },
                { key: "title", label: "제목", thStyle: { width: "50%" } },
                {
                    key: "writeDate",
                    label: "작성일",
                    thStyle: { width: "20%" },
                    formatter: "getFormatDate",
                    sortable:true,
                },
            ],
        };
    },
    computed: {
        ...mapGetters(["qnaItems"]),
        filtered: function () {
            var stitle = this.word.trim();
            return this.qnaItems.filter(function (item) {
                if (item.title.indexOf(stitle) > -1) {
                    return true;
                }
            });
        },
        rows() {
            return this.filtered.length;
        },
    },
    created() {
        this.$store.dispatch("getQnaItems");
    },
    methods: {
        movePage() {
            this.$router.push("/qna-create");
        },
        search(e) {
            this.word = e.target.value;
        },
        pageUp() {
            window.scrollTo(0, 100);
        },
        moveDetail(e) {
            //console.log(this);
            //console.log(e);
            this.$router.push(`/qna-view?no=` + e.no);
        },
        getFormatDate(regtime) {
            return moment(new Date(regtime)).format("YYYY.MM.DD");
        },
    },
};
</script>
<style>
#qnaTitle {
    font-weight: bold;
    margin-top: 72px;
}
.page-link {
    font-family: Arial, Helvetica, sans-serif !important;
}
</style>
