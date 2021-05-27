<template lang="">
    <div id="formWrap">
        <h2 v-if="type == 'create'">회원가입</h2>
        <h2 v-if="type != 'create'">마이페이지</h2>
        <b-form>
            <b-form-group id="input-group-1" label="아이디" label-for="input-1">
                <b-form-input
                    id="input-1"
                    v-model="user.id"
                    type="text"
                    placeholder="Enter ID"
                    required
                ></b-form-input>
            </b-form-group>

            <b-form-group id="input-group-2" label="비밀번호" label-for="input-2">
                <b-form-input
                    id="input-2"
                    v-model="user.pwd"
                    type="password"
                    placeholder="Enter Password"
                    required
                ></b-form-input>
            </b-form-group>

            <b-form-group id="input-group-3" label="이름" label-for="input-3">
                <b-form-input
                    id="input-3"
                    v-model="user.name"
                    placeholder="Enter name"
                    required
                ></b-form-input>
            </b-form-group>

            <b-form-group id="input-group-4" label="핸드폰 번호" label-for="input-4">
                <b-form-input
                    id="input-4"
                    v-model="user.phone"
                    placeholder="Enter Phone Number"
                    required
                ></b-form-input>
            </b-form-group>
            <b-form-group id="input-group-5" label="이메일" label-for="input-4">
                <b-form-input
                    id="input-4"
                    v-model="user.email"
                    placeholder="Enter email"
                    required
                ></b-form-input>
            </b-form-group>
            <b-form-group id="input-group-6" label="메세지" label-for="input-4">
                <b-form-input
                    id="input-4"
                    v-model="user.message"
                    placeholder="Enter message"
                    required
                ></b-form-input>
            </b-form-group>
            <div id="btns">
                <div>
                    <b-button
                        id="regBtn"
                        type="submit"
                        variant="primary"
                        v-if="type == 'create'"
                        @click.prevent="regist"
                        >등록</b-button
                    >
                </div>
                <div>
                    <b-button
                        id="modBtn"
                        type="submit"
                        variant="primary"
                        v-if="type != 'create'"
                        @click="modify"
                        >수정</b-button
                    >
                </div>
                <div>
                    <b-button
                        id="delBtn"
                        type="submit"
                        variant="primary"
                        v-if="type != 'create'"
                        @click="del"
                        >탈퇴</b-button
                    >
                </div>
            </div>
        </b-form>
    </div>
</template>
<script>
import { mapState } from "vuex";
import http from "@/util/http-common";
export default {
    name: "writeform",
    props: {
        type: { type: String },
    },
    data() {
        return {
            user: {},
        };
    },
    created() {
        if (this.userInfo) {
            this.user = this.userInfo;
        }
    },
    computed: {
        ...mapState(["userInfo"]),
    },
    methods: {
        regist() {
            //console.log(this.user);
            http.post("/user/register", this.user)
                .then(() => {
                    alert("가입을 축하합니다.");
                    this.$router.push("/");
                })
                .catch(() => {
                    alert("에러발생!");
                });
        },
        modify() {
            http.put("/user", this.user)
                .then(() => {
                    alert("정보가 수정되었습니다.");
                })
                .catch(() => {
                    alert("에러발생!");
                });
        },
        del() {
            if (confirm("정말로 탈퇴하시겠습니까?")) {
                http.delete("/user/" + this.user.id, this.user)
                    .then(() => {
                        alert("탈퇴 완료");
                        this.$router.push("/");
                        this.$store.dispatch("LOGOUT");
                        // 세션 처리해야함
                    })
                    .catch(() => {
                        alert("에러발생!");
                    });
            }
        },
    },
};
</script>
<style>
#formWrap > h2 {
    font-weight: bold;
    margin-bottom: 32px;
}
#input-2 {
    font-family: Arial, Helvetica, sans-serif !important;
}
#formWrap {
    position: absolute;
    width: 70%;
    max-width: 1000px;
    top: 52%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 1.1em;
}
#formWrap .form-group {
    margin-bottom: 24px;
}
#formWrap label {
    padding-left: 16px;
    margin-bottom: 4px;
}
#formWrap input {
    padding-left: 20px;
    padding-top: 24px;
    padding-bottom: 24px;
    border: 1px solid #d9dfda;
    border-radius: 24px;
    background-color: #d9dfda;
}
#btns {
    margin-top: 40px;
}
#btns > div {
    width: 48%;
    float: left;
}
#btns > div:first-child {
    width: 100%;
}
#modBtn {
    margin-right: 4%;
}
#delBtn {
    margin-left: 8%;
}
#regBtn,
#modBtn,
#delBtn {
    color: white;
    border: 1px solid #597d66;
    background-color: #597d66;
    width: 100%;
    padding: 10px 20px;
    font-weight: bold;
    border-radius: 10px;
    font-size: 1.01em;
}
#regBtn:hover {
    border: 1px solid #597d66;
    color: #597d66;
    background-color: white;
}
#delBtn:hover,
#modBtn:hover {
    color: white;
    background-color: #597d66;
    border: 1px solid #597d66;
}
#modBtn,
#delBtn {
    border: 1px solid #597d66;
    color: #597d66;
    background-color: white;
}
</style>
