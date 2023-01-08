<template>
  <v-card class="mx-auto survey-detail" shaped>
    <v-container class="white lighten-5">
      <v-card-text>
        <v-row>
          <v-col class="column">
            <div class="classroom-info">
              <h2 class="details">{{ survey.section.sectionName }}</h2>
              <h1 class="details topicName">{{ survey.topic.topicName }}</h1>
              <h3 class="details">Instructor: {{ survey.instructor.firstName }} {{survey.instructor.lastName}}</h3>
            </div>
          </v-col>

          <v-col class="column" id="buttonColumn">
            <div class="location-info">
              <v-btn
                color="primary accent-4"
                block
                type="button"
                class="link"
                :to="{name :'survey', params : {surveyId : survey.surveyId }}"
              >
                View This Survey's Details
                <v-icon>mdi-details</v-icon>
              </v-btn>

              <v-btn
                color="primary accent-4"
                block
                v-if="isAdminUser"
                type="button"
                class="btn"
                @click="showModal(survey)"
              >
                Edit Survey
                <v-icon>mdi-pencil-outline</v-icon>
              </v-btn>
              <modal-edit-survey
                v-if="isModalVisible"
                :show="survey.showModal"
                @close="closeModal"
                :survey="survey"
              />

              <v-btn
                block
                color="primary accent-4"
                type="button"
                class="link"
                :to="{name :'survey-version-history', params : {surveyId : survey.surveyId }}"
              >
                View Version History
                <v-icon>mdi-delta</v-icon>
              </v-btn>
            </div>
          </v-col>
        </v-row>

        <v-row>
          <h4>Last edited by {{ survey.user.username }} at {{ timestamp }}</h4>
        </v-row>

      </v-card-text>

    </v-container>
  </v-card>
</template>

<script>
import ModalEditSurvey from "@/modals/ModalEditSurvey.vue";
import auth from "@/auth.js";

export default {
  name: "survey-card",
  components: {
    ModalEditSurvey
  },
  props: {
    surveyProp: Object,
    surveyResponseSet: Array,
    dontShowButtons: Boolean
  },
  data() {
    return {
      survey: this.surveyProp,
      timestamp: "",
      surveyStats: [],
      surveyQuestions: [],
      isModalVisible: false,
      currentModalSurvey: "",
      hover: false,
      user: {},
      isAdminUser: false
    };
  },
  methods: {
    parseTimeStamp() {
      const date = new Date(this.survey.timestamp);
      const hours = date.getHours();
      const minutes = "0" + date.getMinutes();
      const seconds = "0" + date.getSeconds();
      const formattedTime =
        hours + ":" + minutes.substr(-2) + ":" + seconds.substr(-2);
      const months = [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
      ];
      const year = date.getFullYear();
      const month = months[date.getMonth()];
      const dayOfMonth = date.getDate();
      const formattedDate = `${month} ${dayOfMonth}, ${year}`;
      this.timestamp = `${formattedTime}, ${formattedDate}`;
    },

    showModal(survey) {
      this.survey.showModal = true;
      this.isModalVisible = true;
      this.currentModalSurvey = survey;
    },

    closeModal(survey) {
      this.survey = survey;
      this.survey.showModal = false;
      this.isModalVisible = false;
      this.currentModalSurvey = "";
    },

    updateSurveyObj(returnedResponse) {
      this.surveyResponseSet.forEach(response => {
        if (response.studentId == returnedResponse.studentId) {
          response = returnedResponse;
        }
      });
      this.$emit("updateSurvey", this.surveyResponseSet);
    },
    getUserRole() {
      this.getUser();
      if (this.user.rol.toLowerCase().includes("admin")) {
        this.isAdminUser = true;
      } else {
        this.isAdminUser = false;
      }
    },
    getUser() {
      this.user = auth.getUser();
      if (this.user.rol.toLowerCase().includes("admin")) {
        this.isAdminUser = true;
      } else {
        this.isAdminUser = false;
      }
    }
  },
  created() {
    this.parseTimeStamp();
    this.getUserRole();
  }
};
</script>

<style scoped>
.column {
  padding: 0;
}

.survey-detail {
  margin: 20px;
  padding: 10px;
}

div.details {
  /* border: 1px black solid; */
  /* border-radius: 6px; */
  padding: 30px;
  margin: 10px;
}
.questions {
  display: flex;
  flex-direction: row;
  align-self: center;
  justify-content: center;
  margin: 5px 0;
}
.questions div {
  display: block;
  width: 23%;
  margin: 8px;
  padding: 8px;
  /* border: 1px solid black; */
  /* border-radius: 5px; */
}
.questions div > * {
  margin: 3px 0;
  text-align: center;
}
.question-card {
  table-layout: fixed;
  width: 100%;
}
.question-card tr th {
  font-weight: normal;
  text-align: left;
}
.question-card tr td {
  font-weight: normal;
  text-align: right;
}
.card-footer-container {
  margin: 8px 0 0 0;
}
.card-footer,
.card-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-self: center;
  font-weight: normal;
}
.card-footer * {
  margin: 2px 0;
  font-weight: normal;
  justify-content: space-between;
}
div.meta-info {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-areas: "classroom topic location";
  margin: 4px 0;
}
div.meta-info div h4 {
  margin: 0;
}
.location-info {
  grid-area: "location";
  text-align: right;
}
.classroom-info {
  grid-area: "classroom";
  text-align: left;
}
.topic-info {
  grid-area: "topic";
  text-align: center;
  font-size: 120%;
  font-weight: bolder;
  color: green;
}
h4 {
  font-size: 90%;
}
.buttons {
  margin: 10px -4px 0 -4px;
}

.v-btn {
  background-color: rgb(250, 250, 250) !important;
}
.v-btn:before {
  background-color: currentColor;
  background-color: rgb(250, 250, 250) !important;
}
.v-btn:hover {
  background-color: rgb(220, 220, 220) !important;
}
.link {
    margin-bottom: 10px;
}
.btn {
    margin-bottom: 10px;
}
#buttonColumn {
    width: 80%;
}
.details {
    padding: 7px;
}
.topicName {
  color: #1f76d1;
}
</style>

