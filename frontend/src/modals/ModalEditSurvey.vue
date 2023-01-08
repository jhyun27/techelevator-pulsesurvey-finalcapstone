<template>
  <modal-template :show="show" @close="close" class="modal-survey">
    <div class="modal-header">
      <h3>Survey</h3>
    </div>
    <div class="modal-body">
      <label for="user" class="form-label">
        User
        <input name="user" v-model="user.sub" class="form-control" disabled />
      </label>

      <label for="instructor" class="form-label">
        Please select an instructor
        <select
          name="instructors"
          v-model="updatedSurvey.instructor"
          class="form-control dropdown"
        >
          <option
            v-for="instructor in instructors"
            v-bind:key="instructor.instructors"
            v-bind:value="instructor"
          >{{instructor.firstName}} {{instructor.lastName}}</option>
        </select>
      </label>

      <label for="location" class="form-label">
        Please choose a Location:
        <select
          name="location"
          v-model="updatedSurvey.location"
          class="form-control dropdown"
        >
          <option
            v-for="location in locations"
            v-bind:key="location.city"
            v-bind:value="location"
          >{{location.city}}, {{location.state}}</option>
          <i class="arrow down"></i>
        </select>
      </label>

      <label for="cohort" class="form-label">
        Please select a cohort
        <select
          name="cohort"
          v-model="updatedSurvey.cohort"
          class="form-control dropdown"
        >
          <option
            v-for="cohort in cohort"
            v-bind:key="cohort.cohortNumber"
            v-bind:value="cohort"
          >{{cohort.cohortNumber}}</option>
          <i class="arrow down"></i>
        </select>
      </label>

      <label for="section" class="form-label">
        Please select a section
        <select
          name="section"
          v-model="updatedSurvey.section"
          class="form-control dropdown"
        >
          <option
            v-for="section in section"
            v-bind:key="section.sectionName"
            v-bind:value="section"
          >{{section.sectionName}}</option>
          <i class="arrow down"></i>
        </select>
      </label>

      <label for="topic" class="form-label">
        Please select a topic
        <select
          name="topic"
          v-model="updatedSurvey.topic"
          class="form-control dropdown"
        >
          <option
            v-for="topic in topic"
            v-bind:key="topic.topicName"
            v-bind:value="topic"
          >{{topic.topicName}}</option>
          <i class="arrow down"></i>
        </select>
      </label>
    </div>
    <div class="modal-footer">
      <v-btn title class="modal-default-button" @click="close()">
        Cancel
        <v-icon>mdi-close-circle</v-icon>
      </v-btn>
      <v-btn title class="modal-default-button save" @click="savePost()" v-if="updatedSurvey">
        Save
        <v-icon>mdi-content-save-edit</v-icon>
      </v-btn>
    </div>
  </modal-template>
</template>

<script>
import ModalTemplate from "@/modals/ModalTemplate.vue";
import SurveysService from "@/service/SurveysService.js";
import auth from "@/auth.js";

export default {
  name: "modal-edit-survey",
  props: {
    show: Boolean,
    survey: Object
  },

  data() {
    return {
      updatedSurvey: JSON.parse(JSON.stringify(this.survey)),
      oldSurvey: {},
      editSurvey: "",
      instructors: [],
      locations: [],
      cohort: [],
      section: [],
      topic: [],
      user: {}
    };
  },

  components: {
    ModalTemplate
  },

  methods: {
    close() {
      this.updatedSurvey = JSON.parse(JSON.stringify(this.oldSurvey));
      this.$emit("close", this.updatedSurvey);
      this.title = "";
      this.body = "";
    },

    savePost() {
      this.updatedSurvey.user.username = this.user.sub;
      SurveysService.updateSurvey(this.updatedSurvey);
      this.oldSurvey = JSON.parse(JSON.stringify(this.updatedSurvey));
      this.close();
    },

    getSelectInstructor() {
      SurveysService.listInstructors()
        .then(instructors => {
          instructors.forEach(instructor => {
            if (instructor.active == true) {
              this.instructors.push(instructor);
            }
          });
        })
        .catch(err => {
          console.error(err);
        });
    },

    getSelectLocations() {
      SurveysService.listLocations()
        .then(locations => {
          locations.forEach(location => {
            if (location.active == true) {
              this.locations.push(location);
            }
          });
        })
        .catch(err => {
          console.error(err);
        });
    },

    getSelectCohort() {
      SurveysService.listCohorts()
        .then(cohorts => {
          cohorts.forEach(cohort => {
            if (cohort.active == true) {
              this.cohort.push(cohort);
            }
          });
        })
        .catch(err => {
          console.error(err);
        });
    },

    getSelectSection() {
      SurveysService.listSections()
        .then(sections => {
          sections.forEach(section => {
            if (section.active == true) {
              this.section.push(section);
            }
          });
        })
        .catch(err => {
          console.error(err);
        });
    },

    getSelectTopic() {
      SurveysService.listTopics()
        .then(topics => {
          topics.forEach(topic => {
            if (topic.active == true) {
              this.topic.push(topic);
            }
          });
        })
        .catch(err => {
          console.error(err);
        });
    },
    getUser() {
      this.user = auth.getUser();
    }
  },

  created() {
    this.getSelectInstructor();
    this.getSelectLocations();
    this.getSelectCohort();
    this.getSelectSection();
    this.getSelectTopic();
    this.getUser();
    this.oldSurvey = JSON.parse(JSON.stringify(this.survey));
  }
};
</script>

<style scoped>
.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.text-right {
  text-align: right;
}

.save {
  align-self: right;
}

.form-label {
  display: block;
  margin-bottom: 1em;
}

.form-label > .form-control {
  margin-top: 0.5em;
}

.form-control {
  display: block;
  width: 100%;
  max-width: 100%;
  min-width: 100%;
  padding: 0.5em 1em;
  line-height: 1;
  border: 1px solid #ddd;
}

.dropdown {
  width: 100%;
  max-width: 100%;
  min-width: 100%;
  height: 2.9;
  font-size: 90%;
}

.modal-footer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

@media screen and (-webkit-min-device-pixel-ratio: 0) {
  .dropdown {
    background: #efefef
      url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/392/select-arrow.png) 95%
      50% no-repeat;
  }
}
.modal-survey {
  text-align: left;
}

</style>