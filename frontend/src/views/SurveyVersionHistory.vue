<template>
  <div class="survey">
    <survey-version-history-component :surveys="surveys"/>
  </div>
</template>

<script>
import SurveysService from "@/service/SurveysService";
import SurveyVersionHistoryComponent from "@/components/SurveyVersionHistory.vue";

export default {
  name: "survey-version-history-view",
  components: {
    SurveyVersionHistoryComponent
  },
  data() {
    return {
      surveys: []
    };
  },
  methods: {
    getSurveyVersionHistory(id) {
      SurveysService.getSurveyVersions(id)
        .then(surveys => {
          this.surveys = surveys;
        })
        .catch(err => {
          console.error(err);
        });
    },
    created() {
      const id = this.$route.params.surveyId;
      console.log(`Getting Survey History for Survey #${id}`);
      this.getSurveyVersionHistory(id);
    }
  }
};
</script>

<style>
</style>