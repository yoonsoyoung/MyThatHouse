<template>
    <div>
        <detail
            boardtype="notice"
            :boardid="noticeItem.boardid"
            :userId="noticeItem.userId"
            :title="noticeItem.title"
            :writeDate="noticeItem.writeDate"
            :content="noticeItem.content"
        />
    </div>
</template>

<script>
import Detail from "@/components/board/include/BoardDetail.vue";
import { mapGetters } from "vuex";
export default {
    name: "boardview",
    components: {
        Detail,
    },
    computed: {
        ...mapGetters(["noticeItem"]),
    },
    created() {
        this.$store.dispatch("getNoticeItem", `/board/detail/${this.$route.query.boardid}`);
        //////console.log(`boardView : ${this.$route.query.boardid}`);
    },
};
</script>
