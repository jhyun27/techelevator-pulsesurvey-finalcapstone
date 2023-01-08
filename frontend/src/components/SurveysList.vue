<template>
  <div class="surveys-list">
    <div class="main-menu">
      <filter-set @filter="handleFilter" id="filterSet"></filter-set>
      <new-survey-form @submitUpload="upload($event)" id="newSurvey" />
      <div class="showItems">
        <div v-if="showFilteredSurveysGraphics==true">
          <v-btn
            x-large
            color="primary accent-4"
            block
            dark
            href="#"
            data-test="show-form-anchor"
            @click.prevent="showFilteredSurveysGraphics = false"
          >Hide Filtered Survey Graphics</v-btn>
        </div>
        <div v-else>
          <v-btn
            x-large
            color="#00487C"
            block
            dark
            href="#"
            data-test="show-form-anchor"
            @click.prevent="showFilteredSurveysGraphics = true"
          >Show Filtered Survey Graphics</v-btn>
        </div>
      </div>
    </div>
    <div class="filtered-survey-graphics" v-if="showFilteredSurveysGraphics==true">
      <Graphs
        v-if="filteredSurveys!=null"
        :surveyResponseSet="filteredSurveysResponses"
        :key="componentKey"
      />
    </div>
    <div v-for="survey in filteredSurveys" :key="survey.surveyId">
      <survey-card :surveyProp="survey" />
    </div>
  </div>
</template>

<script>
import SurveysService from "@/service/SurveysService";
import FilterSet from "@/components/FilterSet.vue";
import NewSurveyForm from "@/components/NewSurveyForm.vue";
import SurveyCard from "@/components/SurveyCard.vue";
import Graphs from "@/components/Graphs.vue";

export default {
  name: "surveys-list",
  components: {
    FilterSet,
    NewSurveyForm,
    SurveyCard,
    Graphs
  },
  props: {
    filterObj: Object
  },
  data() {
    return {
      surveys: [],
      filter: {},
      newSurveyInfo: {},
      num: 0,
      showFilteredSurveysGraphics: false,
      componentKey: 0
    };
  },
  methods: {
    getSurveys() {
      SurveysService.list()
        .then(surveys => {
          this.surveys = surveys;
        })
        .catch(err => {
          console.error(err);
        });
    },
    forceRerender() {
      this.componentKey += 1;
      console.log(this.componentKey);
    },
    handleFilter(filterObj) {
      this.filter = filterObj;
      this.forceRerender();
      this.showFilteredSurveysGraphics = false;
    },
    upload(survey) {
      this.newSurveyInfo = survey;
      SurveysService.saveSurvey(survey).then(() => {
        this.getSurveys();
      });
    },
    getResponseSetsForAllFilteredSurveys(filteredSurveys) {
      let allSurveysResponseSets = [];

      filteredSurveys.forEach(survey => {
        allSurveysResponseSets.push(...survey.surveyResponses);
      });

      return allSurveysResponseSets;
    }
  },
  created() {
    this.getSurveys();
  },
  computed: {
    filteredSurveys(vm) {
      let filteredSurveys = vm.surveys;
      if (!vm.filter) {
        return filteredSurveys;
      }
      if (vm.filter.topicText) {
        filteredSurveys = filteredSurveys.filter(survey => {
          return survey.topic.topicName
            .toLowerCase()
            .includes(vm.filter.topicText.toLowerCase());
        });
      }
      if (vm.filter.instructorFirstText) {
        filteredSurveys = filteredSurveys.filter(survey => {
          return survey.instructor.firstName
            .toLowerCase()
            .includes(vm.filter.instructorFirstText.toLowerCase());
        });
      }
      if (vm.filter.instructorLastText) {
        filteredSurveys = filteredSurveys.filter(survey => {
          return survey.instructor.lastName
            .toLowerCase()
            .includes(vm.filter.instructorLastText.toLowerCase());
        });
      }
      if (vm.filter.cityText) {
        filteredSurveys = filteredSurveys.filter(survey => {
          return survey.location.city
            .toLowerCase()
            .includes(vm.filter.cityText.toLowerCase());
        });
      }
      if (vm.filter.stateText) {
        filteredSurveys = filteredSurveys.filter(survey => {
          return survey.location.state
            .toLowerCase()
            .includes(vm.filter.stateText.toLowerCase());
        });
      }
      if (vm.filter.cohortText) {
        filteredSurveys = filteredSurveys.filter(survey => {
          return survey.cohort.cohortNumber
            .toString()
            .toLowerCase()
            .includes(vm.filter.cohortText.toLowerCase());
        });
      }

      return filteredSurveys;
    },
    filteredSurveysResponses(vm) {
      return vm.getResponseSetsForAllFilteredSurveys(vm.filteredSurveys);
    }
  }
};
</script>

<style scoped>
.surveys-list {
  display: block;
}
/* #filterSet {
  width: 70%;
} */
/* #newSurvey {
  display: inline-block;
} */

#filterSet, #newSurvey {
  margin-bottom: 15px !important;
}
.showItems {
  width: 100%;
}

.v-btn:before {
  background-color: currentColor;
  background-color: rgb(250, 250, 250) !important;
}
.v-btn:hover {
  background-color: rgb(220, 220, 220) !important;
}
</style>
