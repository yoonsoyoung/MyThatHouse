<template>
    <div id="wrap">
        <div id="nav">
            <ul id="home">
                <li>
                    <router-link to="/"
                        ><img id="logo" src="../../assets/logo_1_cloud.png" alt="logo"
                    /></router-link>
                </li>
            </ul>
            <div id="ranking">
                <ul>
                    <!-- 아파트 클릭수 순위 -->
                    <li v-for="(item, index) in top10" :key="index">
                        <a>{{ index + 1 }}. {{ item.aptName }}</a>
                    </li>
                </ul>
            </div>
            <ul id="menu">
                <li><router-link to="/notice">공지사항</router-link></li>
                <li><router-link to="/qna">QnA</router-link></li>
                <li v-if="isLogin" @click="mvBookmarkList">
                    <a id="bookmark"><img src="../../assets/bookmark.png" alt="logo" /></a>
                </li>
                <li v-if="isLogin" @click="mvModify"><a>마이페이지</a></li>
                <li v-else @click="mvRegist"><a>회원가입</a></li>
                <li v-if="isLogin" @click="logout"><a>로그아웃</a></li>
                <li v-else>
                    <b-button v-b-modal.my-modal id="loginBtn"><a>로그인</a></b-button>
                </li>
            </ul>

            <!-- The modal -->
            <b-modal id="my-modal" @ok="confirm" title="LOGIN">
                <div>
                    <form id="loginForm">
                        <input v-model="user.id" type="text" placeholder="ID" />
                        <input id="pwd" v-model="user.pwd" type="password" placeholder="Password" />
                    </form>
                </div>
            </b-modal>
        </div>
    </div>
</template>

<script>
import { login } from "@/api/user.js";
import { mapState } from "vuex";
import { mapGetters } from "vuex";
export default {
    name: "HeaderItem",
    data() {
        return {
            user: {
                id: "",
                pwd: "",
            },
            isLoginError: false,
        };
    },
    created() {
        this.$store.dispatch("getTop10");
    },
    computed: {
        ...mapState(["isLogin"]),
        ...mapGetters(["top10"]),
    },
    mounted() {},
    methods: {
        confirm() {
            localStorage.setItem("access-token", "");
            login(
                this.user,
                (response) => {
                    //console.log(response);
                    if (response.data.message === "success") {
                        let token = response.data["access-token"];
                        this.$store.commit("setIsLogined", true);
                        localStorage.setItem("access-token", token);

                        this.$store.dispatch("GET_MEMBER_INFO", token);
                        this.$store.dispatch("getBookmarkList", this.user.id);
                        // this.$router.push("/");
                    } else {
                        //console.log("에러");
                        alert("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
                        this.isLoginError = true;
                    }
                },
                (error) => {
                    console.error(error);
                    alert("아이디 또는 비밀번호가 일치하지 않습니다.");
                }
            );
        },
        logout() {
            this.$store
                .dispatch("LOGOUT")
                .then(() => {
                    // this.$router.push({ name: "" });
                    if (this.$route.path !== "/") this.$router.replace("/");
                })
                .catch(() => {
                    //console.log("로그아웃 문제!!!");
                });
        },
        mvRegist() {
            this.$router.push("/member/regist");
        },
        mvModify() {
            this.$router.push("/member/modify");
        },
        mvBookmarkList() {
            this.$router.push("/bookmark");
        },
    },
};
</script>
<style>
* {
    font-family: "NanumSquare", sans-serif !important;
}
ul {
    padding: 0;
    margin: 0;
}
li {
    list-style: none;
}
a {
    text-decoration: none;
    color: black;
}

/* #597d66 #d9dfda */
#logo {
    width: 110px;
}
#pwd {
    font-family: Arial, Helvetica, sans-serif !important;
}
#wrap {
    font-size: 1.1em;
    font-weight: bold;
    color: white;
}
#wrap {
    background-color: #d9dfda;
    height: 80px;
}
#nav {
    background-color: #597d66;
    height: 60px;
}
#nav > * {
    float: left;
}
#home,
#menu {
    height: 60px;
    padding: 0;
    margin: 0;
}
#home > li > a {
    color: white;
    text-decoration: none;
    line-height: 60px;
    padding: 0 30px;
}
#ranking {
    background-color: white;
    width: 360px;
    height: 60px;
    overflow: hidden;
}
#ranking > ul {
    margin: 0;
    padding: 0;
    overflow: hidden;
    animation-duration: 22s;
    animation-name: newsticker;
    animation-iteration-count: infinite;
}
#ranking > ul > li {
    height: 60px;
    text-align: center;
    color: black;
}
#ranking > ul > li > a {
    padding: 5px 14px;
    border-bottom: 1px solid black;
    line-height: 60px;
}
@keyframes newsticker {
    0% {
        margin-top: 0px;
    }
    9% {
        margin-top: 0px;
    }
    18% {
        margin-top: -60px;
    }
    27% {
        margin-top: -120px;
    }
    36% {
        margin-top: -180px;
    }
    45% {
        margin-top: -240px;
    }
    54% {
        margin-top: -300px;
    }
    63% {
        margin-top: -360px;
    }
    72% {
        margin-top: -420px;
    }
    81% {
        margin-top: -480px;
    }
    90% {
        margin-top: -540px;
    }
    /* 100% {
        margin-top: -600px;
    } */
}

#menu {
    float: right;
    margin-right: 15px;
}
#menu > li {
    float: left;
    padding: 0 15px;
}
#loginBtn {
    background-color: transparent;
    margin: 0;
    padding: 0;
    border: none;
    outline: none;
    font-weight: bold;
    font-size: 17.6px;
}
#menu > li > a,
#menu a {
    line-height: 60px;
    color: white;
    text-decoration: none;
    cursor: pointer;
}
#bookmark > img {
    width: 26px;
}
#loginForm {
    text-align: center;
}
#loginForm > input:first-child {
    margin-bottom: 10px;
}
#loginForm > input::placeholder {
    color: #b4b4b4;
}
#loginForm > input {
    display: block;
    width: 100%;
    padding: 12px 20px;
    background-color: #d9dfda;
    border-radius: 24px;
    border: 1px solid #d9dfda;
}

#my-modal___BV_modal_footer_ > .btn:first-child {
    display: none;
}
#my-modal___BV_modal_footer_ > .btn {
    background-color: #597d66;
    display: block;
    width: 100%;
    border-radius: 24px;
    padding: 10px 0;
    border: 1px solid #597d66;
    font-weight: bold;
    font-size: 1.1em;
}
</style>
