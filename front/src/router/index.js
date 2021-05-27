import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";
import Index from "../views/index.vue";
import AptList from "../views/AptList.vue";
import ModifyMember from "../views/ModifyMember";
import RegistMember from "../views/RegistMember";
import List from "@/components/board/List.vue";
import BoardView from "@/components/board/BoardView.vue";
import Create from "@/components/board/Create.vue";
import Modify from "@/components/board/Modify.vue";
import QnaList from "@/components/board/QnaList.vue";
import QnaCreate from "@/components/board/QnaCreate.vue";
import QnaModify from "@/components/board/QnaModify.vue";
import QnaView from "@/components/board/QnaView.vue";
import Bookmark from "@/views/Bookmark.vue";
import AnswerForm from "@/components/board/include/AnswerForm.vue";
import AnswerModify from "@/components/board/AnswerModify.vue";
Vue.use(VueRouter);
const onlyAuthUser = async (to, from, next) => {
  let token = localStorage.getItem("access-token");
  if (store.state.userInfo == null && token) {
    await store.dispatch("GET_MEMBER_INFO", token);
  }
  if (store.state.userInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    // router.push({ name: "login" });
  } else {
    next();
  }
};
const routes = [
  {
    path: "/",
    name: "Index",
    component: Index,
  },
  {
    path: "/aptList",
    name: "AptList",
    beforeEnter: onlyAuthUser,
    component: AptList
  },
  {
    path: "/member/modify",
    name: "ModifyMember",
    beforeEnter: onlyAuthUser,
    component: ModifyMember
  },
  {
    path: "/member/regist",
    name: "RegistMember",
    component: RegistMember
  },
  {
    path: "/notice",
    name: "Notice",
    beforeEnter: onlyAuthUser,
    component: List,
  },
  {
    path: "/notice-list",
    name: "notice-list",
    beforeEnter: onlyAuthUser,
    component: List,
  },
  {
    path: "/notice-create",
    name: "notice-create",
    beforeEnter: onlyAuthUser,
    component: Create,
  },
  {
    path: "/notice-view",
    name: "notice-view",
    beforeEnter: onlyAuthUser,
    component: BoardView,
  },
  {
    path: "/notice-modify",
    name: "notice-modify",
    beforeEnter: onlyAuthUser,
    component: Modify,
  },
  {
    path: "/qna",
    name: "Qna",
    beforeEnter: onlyAuthUser,
    component: QnaList,
  },
  {
    path: "/qna-create",
    name: "qna-create",
    beforeEnter: onlyAuthUser,
    component: QnaCreate,
  },
  {
    path: "/qna-modify",
    name: "qna-modify",
    beforeEnter: onlyAuthUser,
    component: QnaModify,
  },
  {
    path: "/qna-view",
    name: "qna-view",
    beforeEnter: onlyAuthUser,
    component: QnaView,
  },
  {
    path: "/bookmark",
    name: "Bookmark",
    beforeEnter: onlyAuthUser,
    component: Bookmark
  },
  {
    path: "/ans-form",
    name: "ans-form",
    beforeEnter: onlyAuthUser,
    component: AnswerForm,
  },
  {
    path: "/ans-modify",
    name: "ans-modify",
    beforeEnter: onlyAuthUser,
    component: AnswerModify,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
