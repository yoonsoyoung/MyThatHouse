<template>
    <div>
        <b-container>
            <b-row>
                <label for="content">답변</label>
                <b-form-textarea
                    name="content"
                    id="content"
                    v-model="content"
                    :state="content.length > 0"
                >
                </b-form-textarea>
            </b-row>
            <b-row>
                <b-button pill class="mt-3 btn-main" @click="checkHandler">등록</b-button>
                <!-- <b-button pill variant="primary" class="mt-3" @click="createAns" v-else>등록</b-button> -->
            </b-row>
        </b-container>
    </div>
</template>
<script>
import http from "@/util/http-common";

export default {
    name: "ans-form",
    props: {
        type: { type: String },
    },
    data() {
        return {
            content: "",
        };
    },
    methods: {
        checkHandler() {
            let err = true;
            let msg = "";
            !this.content && err && ((msg = "내용을 입력해주세요"), (err = false));

            if (!err) alert(msg);
            else {
                this.type == "modify" ? this.updateAns() : this.createAns();
            }
        },
        createAns() {
            http.post("/qna/answer", {
                content: this.content,
                question_no: this.$route.query.no,
            }).then((data) => {
                let msg = "답변 등록에 문제가 발생했습니다.";
                if (data.status == 200) {
                    msg = "답변 등록이 완료되었습니다.";
                }
                alert(msg);
                this.moveQnaList();
            });
        },
        updateAns() {
            http.put("/qna/answer", {
                content: this.content,
                question_no: this.$route.query.no,
            }).then((data) => {
                let msg = "답변 수정에 문제가 발생했습니다.";
                if (data.status == 200) {
                    msg = "답변 수정이 완료되었습니다.";
                }
                alert(msg);
                this.moveQnaList();
            });
        },
        moveQnaList() {
            this.$router.push("/qna");
        },
    },
};
</script>
<style></style>
