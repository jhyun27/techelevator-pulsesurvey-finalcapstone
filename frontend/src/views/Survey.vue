<template>
  <div class="survey">
    <survey-detail v-if="survey!=null" :surveyProp="surveyReactive" class="survey-detail"/>
    <survey-response-details
      v-if="survey!=null" 
      @updateSurvey="updateSurveyObj($event)"
      :surveyResponseSet="survey.surveyResponses"
    />
    <Graphs v-if="survey!=null" 
    :surveyResponseSet="survey.surveyResponses" />
  </div>
</template>

<script>
import SurveysService from "@/service/SurveysService";
import SurveyDetail from "@/components/SurveyDetail.vue";
import SurveyResponseDetails from "@/components/SurveyResponseDetails.vue";
import Graphs from "@/components/Graphs.vue";

export default {
  name: "survey",
  components: {
    SurveyDetail,
    SurveyResponseDetails,
    Graphs
  },
  created() {
    const id = this.$route.params.surveyId;
    console.log(`Getting Survey #${id}`);
    this.getSurvey(id);
  },
  data() {
    return {
      survey: null
    };
  },
  methods: {
    getSurvey(id) {
      SurveysService.get(id)
        .then(data => {
          this.survey = data;
        })
        .catch(err => {
          console.error(err);
        });
    },
    updateSurveyObj(surveyResponseSet) {
      console.log("updating");
      this.survey.surveyResponses = surveyResponseSet;

      (async () => {
        await SurveysService.updateSurvey(this.survey);
        await this.getSurvey(this.survey.surveyId);
      })();

      this.$emit("updateStatistics", this.survey);
    }
  },
  computed: {
    surveyReactive(vm) {
      return vm.survey;
    }
  }
};
</script>

<style >
.survey > * {
  margin: 10px 0;
}
.survey > *:first-child {
  margin: 10px 0 20px 0;
}
</style>