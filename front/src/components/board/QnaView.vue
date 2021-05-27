<template>
    <div>
        <detail
            boardtype="qna"
            :no="qnaItem.no"
            :userId="qnaItem.userId"
            :title="qnaItem.title"
            :writeDate="qnaItem.writeDate"
            :content="qnaItem.content"
            :answer="ansItem.content"
            :answerno="ansItem.no"
            :answerDate="ansItem.writedate"
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
        ...mapGetters(["qnaItem", "ansItem"]),
    },
    created() {
        this.$store.dispatch("getQnaItem", `/qna/${this.$route.query.no}`);
        // this.$store.dispatch("getAnsItem", `/qna/${this.$route.query.no}`)
        //console.log(`qnaView : ${this.$route.query.no}`);
        //console.log(`qnaView ansCont : ${this.ansItem.content}`);
        //console.log(`qnaView ansNo : ${this.ansItem.answerno}`);
        //console.log(`qnaView ansDate : ${this.ansItem.writedate}`);
    },
};
</script>
