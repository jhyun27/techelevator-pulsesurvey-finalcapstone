<template>
  <div class="survey">
    <h2>Most Recent Version</h2>

    <survey-detail v-if="surveyVersions!=null" :surveyProp="surveyVersions[0]" />
    <survey-response-details v-if="surveyVersions!=null" :surveyResponseSet="surveyVersions[0].surveyResponses" />

    <h2>Previous Versions</h2>
    <div v-if="surveys!=null">
      <div v-for="survey in surveyVersions" :key="survey.versionId">
        <survey-detail v-if="survey!=surveyVersions[0]" :surveyProp="survey" :dontShowButtons="true"/>
      </div>
    </div>
  </div>
</template>

<script>
import SurveysService from "@/service/SurveysService";
import SurveyDetail from "@/components/SurveyDetail.vue";
import SurveyResponseDetails from "@/components/SurveyResponseDetails.vue";

export default {
  name: "survey-version-history-component",
  props: {
    surveys: Array
  },
  components: {
    SurveyDetail,
    SurveyResponseDetails
  },
  data() {
    return {
      surveyVersions: []
    };
  },
  created() {
    const id = this.$route.params.surveyId;
    console.log(`Getting Survey History for Survey #${id}`);
    this.getSurveys(id);
  },
  methods: {
    getSurveys(id) {
      SurveysService.getSurveyVersions(id)
        .then(data => {
          this.surveyVersions = data;
        })
        .catch(err => {
          console.error(err);
        });
    }
  }
};
</script>

<style>
</style>