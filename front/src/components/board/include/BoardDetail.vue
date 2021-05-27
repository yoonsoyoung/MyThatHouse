<template>
    <b-container align="center">
        <b-row>
            <div class="text-center mb-3" v-if="boardtype == 'notice'">
                <h1 id="noticeTitle">공지사항</h1>
                <b-col class="text-right mt-5">
                    <router-link :to="'/notice-modify?boardid=' + boardid"
                        ><b-button size="sm" class="m-1">수정</b-button></router-link
                    >
                    <b-button variant="outline-danger" size="sm" class="m-1" @click="deleteBoard"
                        >삭제</b-button
                    >
                </b-col>
            </div>
            <!-- qna 일 때 버튼 -->
            <div class="text-center mb-3" v-else-if="boardtype == 'qna'">
                <h1 id="noticeTitle">QnA</h1>
                <b-col class="text-right mt-5">
                    <router-link :to="'/qna-modify?no=' + no"
                        ><b-button size="sm" class="m-1">수정</b-button></router-link
                    >
                    <b-button variant="outline-danger" size="sm" class="m-1" @click="deleteBoard"
                        >삭제</b-button
                    >
                </b-col>
            </div>
        </b-row>
        <table class="table">
            <colgroup>
                <col style="width: 10%" />
                <col style="width: 70%" />
                <col style="width: 20%" />
            </colgroup>
            <tr>
                <th colspan="3" class="text-center view-title">{{ title }}</th>
            </tr>
            <tr>
                <th>게시자</th>
                <td>{{ userId }}</td>
                <td>{{ getFormatDate(writeDate) }}</td>
            </tr>
            <tr>
                <td colspan="3" class="content-row" v-html="enterToBr(content)"></td>
            </tr>
        </table>
        <hr />

        <b-row>
            <div v-if="boardtype == 'qna'">
                <table class="table table-bordered mt-3">
                    <colgroup>
                        <col style="width: 10%" />
                        <col style="width: 90%" />
                    </colgroup>
                    <tr>
                        <th>답변</th>
                        <td class="text-right">
                            <router-link :to="'/ans-form?no=' + no">
                                <b-button variant="success" v-if="!answer">작성</b-button>
                            </router-link>
                            <router-link :to="'/ans-modify?no=' + no"
                                ><b-button variant="success" size="sm" v-if="answer"
                                    >수정</b-button
                                ></router-link
                            >
                            <a
                                ><b-button
                                    variant="outline-danger"
                                    size="sm"
                                    @click="deleteAns"
                                    v-if="answer"
                                    >삭제</b-button
                                ></a
                            >
                        </td>
                    </tr>
                    <tr v-if="answerDate">
                        <th>날짜</th>
                        <td>{{ getFormatDate(answerDate) }}</td>
                    </tr>
                    <tr>
                        <td
                            colspan="2"
                            v-if="answer"
                            class="answer-row"
                            v-html="enterToBr(answer)"
                        ></td>
                        <td colspan="2" v-else>답변이 존재하지 않습니다.</td>
                    </tr>
                </table>
                <hr />
            </div>
        </b-row>
        <b-row>
            <b-col v-if="boardtype == 'notice'">
                <router-link to="/notice-list"
                    ><b-button squared class="shadow m-1 gotoListBtn">목록</b-button></router-link
                >
            </b-col>
            <b-col v-if="boardtype == 'qna'">
                <router-link to="/qna"
                    ><b-button squared class="shadow m-1 gotoListBtn">목록</b-button></router-link
                >
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
// import { mapGetters } from "vuex";
import http from "@/util/http-common";
import moment from "moment";

export default {
    name: "detail",
    props: {
        boardtype: { type: String },
        boardid: { type: Number },
        no: { type: Number },
        userId: { type: String },
        title: { type: String },
        content: { type: String },
        writeDate: { type: String },
        answer: { type: String },
        answerno: { type: Number },
        answerDate: { type: String },
    },
    methods: {
        getFormatDate(regtime) {
            return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm");
        },
        deleteBoard() {
            let boardT = "";
            if (this.boardtype == "notice") boardT = "board";
            else boardT = "qna";
            if (confirm("삭제하시겠습니까?")) {
                if (boardT == "board") {
                    http.delete(`/board/${this.boardid}`).then(({ data }) => {
                        let msg = "삭제 처리시 문제가 발생했습니다.";
                        if (data === "SUCCESS") {
                            msg = "삭제가 완료되었습니다.";
                        }
                        alert(msg);
                        this.$router.push("/notice-list");
                    });
                } else {
                    http.delete(`/qna/question/${this.no}`).then(({ data }) => {
                        let msg = "삭제 처리시 문제가 발생했습니다.";
                        if (data === "success") {
                            msg = "삭제가 완료되었습니다.";
                        }
                        alert(msg);
                        this.$router.push("/qna");
                    });
                }
            }
        },
        deleteAns() {
            if (confirm("답변을 삭제하시겠습니까?")) {
                http.delete(`/qna/answer/${this.answerno}`).then(({ data }) => {
                    let msg = "답변 삭제 처리시 문제가 발생했습니다.";
                    if (data === "success") {
                        msg = "답변 삭제가 완료되었습니다.";
                    }
                    alert(msg);
                    this.$router.push("/qna");
                });
            }
        },
        enterToBr(str) {
            if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
        },
    },
};
</script>
<style scope>
.table {
    border-top: 1px solid black !important;
}
.content-row {
    height: 200px;
}
.answer-row {
    height: 150px;
}
.gotoListBtn {
    background-color: #5a7d67 !important;
    color: white;
    width: 150px;
    height: 50px;
}
.view-title {
    border-top: 2px;
    height: 70px;
    font-size: 25px;
}
</style>
