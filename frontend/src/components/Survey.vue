<template>
  <div class="survey">
    <survey-detail :surveyProp="survey" />
  </div>
</template>

<script>
import SurveysService from "@/service/SurveysService";
import SurveyDetail from "@/components/SurveyDetail.vue";

export default {
  name: "survey",
  components: {
    SurveyDetail
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
        .then(survey => {
          this.survey = survey;
        })
        .catch(err => {
          console.error(err);
        });
    },
    updateSurvey(survey) {
      SurveysService.updateSurvey(survey);
    }
  }
};
</script>

<style>
</style>