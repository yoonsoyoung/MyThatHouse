<template>
  <b-tr class="list-tr">
    <b-td v-if="boardtype=='notice'">{{ boardid }}</b-td>
    <b-td v-else-if="boardtype=='qna'">{{ no }}</b-td>
    <b-td>{{ userId }}</b-td>
    <b-td v-if="boardtype == 'notice'">
      <router-link :to="`/notice-view?boardid=${boardid}`" boardtype="notice" class="list-title">{{ title }}</router-link>
    </b-td>
    <b-td v-else-if="boardtype == 'qna'">
      <router-link :to="`/qna-view?no=${no}`" boardtype="qna" class="list-title">{{ title }}</router-link>
    </b-td>
    <b-td>{{ getFormatDate(writeDate) }}</b-td>
    <!-- <b-td v-if="ansYes">Y</b-td>
    <b-td v-else></b-td> -->
  </b-tr>
  
</template>
<script>
import moment from 'moment';
export default {
  name: "board-row",
  props: {
    boardtype: String,
    no: Number,
    boardid: Number,
    userId: String,
    title: String,
    writeDate: String,
    ansYes: Boolean,
  },
  methods: {
    getFormatDate(writeDate) {
      return moment(new Date(writeDate)).format('YYYY.MM.DD');
    },
  },
};
</script>
<style scope>
.list-title {
  text-decoration: none;
  color: black;
}
.list-title:hover {
  text-decoration: none;
  font-weight: bold;
  color: #5a7d67;
}

</style>
