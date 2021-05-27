<template>
    <div>
        <b-container id="formContainer">
            <b-row>
                <h1 class="text-center createFormTitle"
                v-if="boardT=='qna'">QnA</h1>
                <h1 class="text-center createFormTitle"  
                v-else>공지사항</h1>
            </b-row>
            <b-card bg-variant="light">
                <b-form-group
                    label-cols-lg="3"
                    label="✏입력"
                    label-size="lg"
                    label-class="font-weight-bold pt-0"
                    class="mb-0 mt-2 ml-3"
                >
                    <b-form-group>
                        <label for="userId">작성자</label>
                        <input
                            type="text"
                            class="form-control"
                            id="userId"
                            ref="userId"
                            v-bind:value="userInfo.id"
                            readonly
                        />
                    </b-form-group>
                    <b-form-group>
                        <label for="title">제목</label>
                        <input
                            type="text"
                            class="form-control"
                            id="title"
                            ref="title"
                            placeholder="제목을 입력하세요"
                            v-model="title"
                        />
                    </b-form-group>
                    <b-form-group>
                        <label for="content">내용</label>
                        <b-textarea
                            type="text"
                            class="form-control"
                            id="contnet"
                            ref="content"
                            placeholder="내용을 입력하세요"
                            v-model="content"
                            rows="5"
                        ></b-textarea>
                    </b-form-group>
                    <div class="text-right">
                        <b-button
                            variant="success"
                            class="mr-2"
                            v-if="type == 'create'"
                            @click="checkHandler"
                            >등록</b-button
                        >
                        <b-button variant="success" class="mr-2" v-else @click="checkHandler"
                            >수정</b-button
                        >
                        <b-button @click="moveList">목록</b-button>
                    </div>
                </b-form-group>
            </b-card>
        </b-container>
    </div>
</template>
<script>
import http from "@/util/http-common";
import { mapGetters } from "vuex";
export default {
    name: "boardForm",
    props: {
        type: { type: String },
        boardtype: { boardtype: String },
    },
    data: function () {
        return {
            boardT: "",
            boardid: "",
            writeDate: "",
            userId: "",
            title: "",
            content: "",
            answer: "",
        };
    },
    computed: {
        ...mapGetters(["noticeItem", "qnaItem", "userInfo"]),
    },
    methods: {
        checkHandler() {
            this.userId = this.userInfo.id;
            let err = true;
            let msg = "";
            !this.userId &&
                ((msg = "작성자를 입력해주세요"), (err = false), this.$refs.userId.focus());
            err &&
                !this.title &&
                ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
            err &&
                !this.content &&
                ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

            if (!err) alert(msg);
            else {
                this.type == "create" ? this.createHandler() : this.updateHandler();
            }
        },
        createHandler() {
            // 공지사항 글쓰기
            if (this.boardT == "board") {
                http.post(`/board`, {
                    userId: this.userInfo.id,
                    title: this.title,
                    content: this.content,
                })
                    .then(({ data }) => {
                        let msg = "등록 처리시 문제가 발생했습니다.";
                        //console.log(data);
                        if (data === "SUCCESS") {
                            msg = "등록이 완료되었습니다.";
                        }
                        alert(msg);
                        this.moveList();
                    })
                    .catch(() => {
                        alert("등록 처리시 에러가 발생했습니다.");
                    });
            }
            // 질문 글쓰기
            else if (this.boardT == "qna") {
                http.post(`/qna/question`, {
                    userId:this.userInfo.id,
                    title: this.title,
                    content: this.content,
                })
                    .then(({ data }) => {
                        let msg = "등록 처리시 문제가 발생했습니다.";
                        //console.log(data);
                        if (data === "success") {
                            msg = "등록이 완료되었습니다.";
                        }
                        alert(msg);
                        this.moveList();
                    })
                    .catch(() => {
                        alert("등록 처리시 에러가 발생했습니다.");
                    });
            }
        },
        updateHandler() {
            // 공지사항 글수정
            //console.log(this.boardT);
            if (this.boardT == "board") {
                //console.log("공지사항글수정");
                http.put(`/board`, {
                    boardid: this.boardid,
                    writeDate: this.writeDate,
                    userId: this.userId,
                    title: this.title,
                    content: this.content,
                })
                    .then(({ data }) => {
                        let msg = "수정 처리시 문제가 발생했습니다.";
                        if (data === "SUCCESS") {
                            msg = "수정이 완료되었습니다.";
                        }
                        alert(msg);
                        this.moveList();
                    })
                    .catch(() => {
                        alert("수정 처리시 에러가 발생했습니다.");
                    });
            }
            // 질문 글 수정
            else if (this.boardT == "qna") {
                http.put(`/qna/question`, {
                    no: this.no,
                    title: this.title,
                    content: this.content,
                })
                    .then(({ data }) => {
                        let msg = "수정 처리시 문제가 발생했습니다.";
                        if (data === "success") {
                            msg = "수정이 완료되었습니다.";
                        }
                        alert(msg);
                        this.moveList();
                    })
                    .catch(() => {
                        alert("수정 처리시 에러가 발생했습니다.");
                    });
            }
        },
        moveList() {
            //console.log(this.boardtype);
            if (this.boardtype == "notice") this.$router.push("/notice-list");
            else this.$router.push("/qna");
        },
    },
    created() {
        if (this.boardtype == "notice") this.boardT = "board";
        else this.boardT = "qna";
        if (this.type === "modify") {
            // 공지사항 게시글 하나 불러오기
            if (this.boardtype == "notice") {
                //console.log("공지사항 하나 get");
                http.get(`/board/detail/${this.$route.query.boardid}`)
                    .then(({ data }) => {
                        this.boardid = data.boardid;
                        this.writeDate = data.writeDate;
                        this.userId = data.userId;
                        this.title = data.title;
                        this.content = data.content;
                    })
                    .catch(() => {
                        alert("에러가 발생했습니다.");
                    });
            }
            // 질문 게시글 하나 불러오기
            else if (this.boardtype == "qna") {
                //console.log("qna 하나 get");
                http.get(`/qna/${this.$route.query.no}`)
                    .then(({ data }) => {
                        //console.log(data);
                        this.no = data.question.no;
                        this.writeDate = data.question.writeDate;
                        this.userId = data.question.userId;
                        this.title = data.question.title;
                        this.content = data.question.content;
                    })
                    .catch(() => {
                        alert("에러가 발생했습니다.");
                    });
            }
        }
    },
};
</script>
<style>
#formContainer {
    margin-top: 160px;
}
.createFormTitle {
    font-weight: bold;
    margin-bottom: 60px;
}
</style>
