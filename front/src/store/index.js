import Vue from "vue";
import Vuex from "vuex";
import jwt_decode from "jwt-decode";
import { findById } from "@/api/user.js";
import http from "@/util/http-common";
import { createInstance } from "@/api/index.js";
import createPersistedState from "vuex-persistedstate";



Vue.use(Vuex);



export default new Vuex.Store({
  state: {
    city: "",
    gugun: "",
    dong: "",
    userInfo: null,
    isLogin: false,
    houseList: [],
    bookmarkList: [],
    top10: [],
    // 공지사항
    noticeItems: [],
    noticeItem: {},

    // QnA
    qnaItems: [],
    qnaItem: {},
    ansItem: {},

    store: [],
    center: {},
  },
  getters: {
    city(state) {
      return state.city;
    },
    gugun(state) {
      return state.gugun;
    },
    dong(state) {
      return state.dong;
    },
    houseList(state) {
      return state.houseList;
    },
    login(state) {
      return state.login;
    },
    bookmarkList(state) {
      return state.bookmarkList;
    },
    noticeItems(state) {
      return state.noticeItems;
    },
    noticeItem(state) {
      return state.noticeItem;
    },
    qnaItems(state) {
      return state.qnaItems;
    },
    qnaItem(state) {
      return state.qnaItem;
    },
    userInfo(state) {
      return state.userInfo;
    },
    top10(state) {
      return state.top10;
    },
    ansItem(state) {
      return state.ansItem;
    },



    store(state) {
      return state.store;
    },
    center(state) {
      return state.center;
    }
  },
  mutations: {
    setCity(state, payload) {
      state.city = payload;
    },
    setGugun(state, payload) {
      state.gugun = payload;
    },
    setDong(state, payload) {
      state.dong = payload;
    },
    setHouseList(state, payload) {
      state.houseList = payload.houseDealList;
      // 위도 경도가 문자열로 들어와서 숫자로 바꿔줘야 함
      for (let i = 0; i < state.houseList.length; i++) {
        state.houseList[i].lat = Number(state.houseList[i].lat);
        state.houseList[i].lng = Number(state.houseList[i].lng);

      }
    },
    setIsLogined(state, isLogin) {
      state.isLogin = isLogin;
    },
    setUserInfo(state, userInfo) {
      state.isLogin = true;
      state.userInfo = userInfo;
      //console.log(state.userInfo)
    },
    setLogout(state) {
      state.isLogin = false;
      state.userInfo = null;
    },
    setBookmarkList(state, bookmarkList) {
      //console.log("mutations")
      //console.log(bookmarkList);
      state.bookmarkList = bookmarkList;

    },
    setNoticeItems(state, payload) {
      state.noticeItems = payload;
    },
    setNoticeItem(state, payload) {
      state.noticeItem = payload;
    },
    setQnaItems(state, payload) {
      state.qnaItems = payload;
    },
    setQnaItem(state, payload) {
      state.qnaItem = payload;
      //console.log(payload);
      //console.log(state.qnaItem);
    },
    setTop10(state, payload) {
      state.top10 = payload;
    },
    setAnsItem(state, payload) {
      state.ansItem = payload;
    },

    setStore(state, payload) {
      state.store = payload;
    },
    setCenter(state, payload) {
      state.center = payload;
    }


  },
  actions: {
    getCity(context, payload) {
      context.commit("setCity", payload);
    },
    getGugun({ commit }, payload) {
      commit("setGugun", payload);
    },
    getDong({ commit }, payload) {
      commit("setDong", payload);
    },
    getHouseList({ commit }, payload) {
      commit("setHouseList", payload);

      commit("setCenter", {
        lat: payload.houseDealList[0].lat,
        lng: payload.houseDealList[0].lng,

      });
    },
    async GET_MEMBER_INFO({ commit }, token) {
      let decode = jwt_decode(token);

      await findById(
        decode.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("setUserInfo", response.data.userInfo);
            // router.push("/");
            // router.go(router.currentRoute);
          } else {
            //console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );


    },
    LOGOUT({ commit }) {
      commit("setLogout");
      localStorage.removeItem("access-token");
      // axios.defaults.headers.common["auth-token"] = undefined;
    },
    addBookmarkList({ commit }, house) {
      // //console.log("ddddddddddddddddd");
      // //console.log("아이디" + this.userInfo.id);
      const instance = createInstance();
      instance.defaults.headers["access-token"] = window.localStorage.getItem(
        "access-token"
      );
      instance
        .post("/jjim/" + house.id, house)
        .then(({ data }) => {
          commit("setBookmarkList", data.jjimList);
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    getBookmarkList({ commit }, data) {
      const instance = createInstance();
      instance.defaults.headers["access-token"] = window.localStorage.getItem(
        "access-token"
      );
      instance
        .get("/jjim/" + data)
        .then(({ data }) => {
          commit("setBookmarkList", data);

        })
        .catch(() => {
          alert("!!!!!에러발생!");
        });
    },
    getNoticeItems({ commit }) {
      http.get("/board").then(({ data }) => {
        commit("setNoticeItems", data);
      });
    },
    getNoticeItem({ commit }, boardid) {
      http.get(boardid).then(({ data }) => {
        //console.log("getItem : " + data)
        commit("setNoticeItem", data);
      });
    },
    getQnaItems({ commit }) {
      http.get("/qna").then(({ data }) => {
        commit("setQnaItems", data);
      });
    },
    getQnaItem({ commit }, no) {
      http.get(no).then(({ data }) => {
        //console.log("store data" + data);
        commit("setQnaItem", data.question);
        if (data.answer) {
          commit("setAnsItem", data.answer);
        } else {
          commit("setAnsItem", "");
        }
      });
    },
    getTop10({ commit }) {
      http.get("/top10").then((data) => {
        commit("setTop10", data.data);
      });
    },
    mvCenterAction({ commit }, payload) {
      commit("setCenter", payload);
    },
    searchList({ commit }, payload) {
      http.post("/search", payload)
        .then(({ data }) => {
          commit("setHouseList", data);

          commit("setCenter", {
            lat: data.houseDealList[0].lat,
            lng: data.houseDealList[0].lng,

          });
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    searchStore({ commit }, payload) {
      http.get("/store/" + payload.gugun + "/" + payload.dong)
        .then(({ data }) => {
          commit("setStore", data);
          //console.log(data);
        })
        .catch(() => {
          alert("상권정보 실패");
        });

    }
  },
  plugins: [createPersistedState()]
});
