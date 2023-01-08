<template>
  <div>
    <v-btn
      color="#00487C"
      x-large
      block
      dark
      href="#"
      data-test="show-form-anchor"
      @click.prevent="showForm = true"
      v-if="showForm === false"
    >Submit New Survey</v-btn>

    <v-card class="mx-auto" v-if="showForm === true">
      <v-btn
        color="error"
        dark
        type="cancel"
        x-large
        block
        href="#"
        data-test="show-form-anchor"
        @click.prevent="resetForm"
      >Cancel</v-btn>

      <v-container class="white lighten-5">
        <v-card-text>
          <div class="form-container">
            <form v-if="showForm === true">
              <div class="form-field">
                <label for="user">User:</label>
                <input
                  name="user"
                  type="text"
                  v-model="user.username"
                  disabled
                  class="form-control"
                />
              </div>

              <div class="form-field">
                <label for="location">Please choose a Location:</label>
                <select name="location" v-model="newSurvey.location" class="dropdown">
                  <option
                    v-for="location in locations"
                    v-bind:key="location.city"
                    v-bind:value="location"
                  >{{location.city}}, {{location.state}}</option>
                  <i class="arrow down"></i>
                </select>
              </div>

              <div class="form-field">
                <label for="cohort">Please choose a Cohort:</label>
                <select name="cohort" v-model="newSurvey.cohort" class="dropdown">
                  <option
                    v-for="cohort in cohorts"
                    v-bind:key="cohort.cohortNumber"
                    v-bind:value="cohort"
                  >{{cohort.cohortNumber}}</option>
                </select>
              </div>

              <div class="form-field">
                <label for="instructor">Please choose an Instructor:</label>
                <select name="insructor" v-model="newSurvey.instructor" class="dropdown">
                  <option
                    v-for="instructor in instructors"
                    v-bind:key="instructor.lastName"
                    v-bind:value="instructor"
                  >{{instructor.firstName}} {{instructor.lastName}}</option>
                </select>
              </div>

              <div class="form-field">
                <label for="section">Please choose a Section:</label>
                <select name="section" v-model="newSurvey.section" class="dropdown">
                  <option
                    v-for="section in sections"
                    v-bind:key="section.sectionName"
                    v-bind:value="section"
                  >{{section.sectionName}}</option>
                </select>
              </div>

              <div class="form-field">
                <label for="topic">Please choose a Topic:</label>
                <select name="topic" v-model="newSurvey.topic" class="dropdown">
                  <option value selected>Please choose a Topic</option>
                  <option
                    v-for="topic in topics"
                    v-bind:key="topic.topicName"
                    v-bind:value="topic"
                  >{{topic.topicName}}</option>
                </select>
              </div>

              <!-- CSV UPLOADER -->
              <div id="submit-buttons">
                <csv-uploader
                  :map-fields="['present', 'studentName', 'studentId', 'totalScore', 'numberCorrect', 'pace', 'interest', 'understanding', 'energyEntry']"
                  @uploadFile="upload($event)"
                  v-model="csv"
                ></csv-uploader>

                <!-- <v-btn color="success" small dark @click="submitUpload()">Submit</v-btn> -->
                <v-btn color="error" dark type="cancel" @click.prevent="resetForm">Cancel</v-btn>
              </div>
            </form>
          </div>
        </v-card-text>
      </v-container>
    </v-card>
  </div>
</template>

<script>
import SurveysService from "@/service/SurveysService.js";
import CsvUploader from "@/components/CsvUploader.vue";
import auth from "@/auth.js";

export default {
  name: "new-survey-form",
  components: {
    CsvUploader
  },
  data() {
    return {
      showForm: false,
      user: {},
      newSurvey: {},
      cohorts: [],
      locations: [],
      instructors: [],
      sections: [],
      topics: [],
      csv: null
    };
  },
  methods: {
    resetForm() {
      this.showForm = false;
      this.newSurvey = {};
    },
    upload(studentResponses) {
      this.csv = studentResponses;
      let responseSet = this.removeEmptyResponses(studentResponses);
      responseSet = this.parseResponses(responseSet);
      this.newSurvey.user = this.user;
      this.newSurvey.surveyResponses = responseSet;
      this.$emit("submitUpload", this.newSurvey);
      this.resetForm();
    },
    removeEmptyResponses(studentResponses) {
      return studentResponses.filter(response => {
        return response.studentId != "" && response.studentId != "Student ID";
      });
    },
    parseResponses(responses) {
      responses.forEach(response => {
        response.totalScore = '0';
        response.numberCorrect = '0';
        let parsedResponse = this.parsePresent(response);
        parsedResponse = this.parsePace(parsedResponse);
        parsedResponse = this.parseInterest(parsedResponse);
        parsedResponse = this.parseUnderstanding(parsedResponse);
        parsedResponse = this.parseEnergyEntry(parsedResponse);
        return parsedResponse;
      });
      return responses;
    },
    parsePresent(response) {
      if (response.present == "Yes") {
        response.present = true;
      } else {
        response.present = false;
      }
      return response;
    },
    parsePace(response) {
      if (response.pace.toLowerCase().includes( "Way too slow".toLowerCase()) ||
        response.pace.toLowerCase() == (
        "A".toLowerCase())) {
        response.pace = 1;
      } else if (response.pace.toLowerCase().includes("A little too slow".toLowerCase()) ||
        response.pace.toLowerCase() == (
        "B".toLowerCase())) {
        response.pace = 2;
      } else if (response.pace.toLowerCase().includes( "Just right".toLowerCase()) ||
        response.pace.toLowerCase() == (
        "C".toLowerCase())) {
        response.pace = 3;
      } else if (response.pace.toLowerCase().includes( "A little too fast".toLowerCase()) ||
        response.pace.toLowerCase() == (
        "D".toLowerCase())) {
        response.pace = 4;
      } else if (response.pace.toLowerCase().includes("Way too fast".toLowerCase()) ||
        response.pace.toLowerCase() == (
        "E".toLowerCase())) {
        response.pace = 5;
      } else {
        response.pace = 0;
      }
      return response;
    },
    parseInterest(response) {
      if (response.interest.toLowerCase().includes( "Mind-numbingly boring".toLowerCase()) || response.interest.toLowerCase() == ( "A".toLowerCase())) {
        response.interest = 1;
      } else if (response.interest.toLowerCase().includes("Somewhat uninteresting".toLowerCase()) || response.interest.toLowerCase() == ( "B".toLowerCase())) {
        response.interest = 2;
      } else if (response.interest.toLowerCase().includes( "Mostly interesting".toLowerCase()) || response.interest.toLowerCase() == ( "C".toLowerCase())) {
        response.interest = 3;
      } else if (response.interest.toLowerCase().includes("Very interesting".toLowerCase()) || response.interest.toLowerCase() == ( "D".toLowerCase())) {
        response.interest = 4;
      } else if (
        response.interest.toLowerCase().includes(
        "I couldn't sleep last night because I was too excited about what I just learned!".toLowerCase() || response.interest.toLowerCase() == ( "E".toLowerCase()))
      ) {
        response.interest = 5;
      } else {
        response.interest = 0;
      }
      return response;
    },
    parseUnderstanding(response) {
      if (
        response.understanding.toLowerCase().includes(
        "Non-existent, I might as well have skipped class and spent the day at the bar".toLowerCase()) ||
        response.understanding.toLowerCase() == (
        "A".toLowerCase())
      ) {
        response.understanding = 1;
      } else if (
        response.understanding.toLowerCase().includes(
        "Poor, I understood some things but feel like a lot of it is still fuzzy".toLowerCase()) ||
        response.understanding.toLowerCase() == (
        "B".toLowerCase())
      ) {
        response.understanding = 2;
      } else if (
        response.understanding.toLowerCase().includes(
        "Fair, I have at least a general understanding of everything discussed and just need more practice to master the concepts".toLowerCase()) ||
        response.understanding.toLowerCase() == (
        "C".toLowerCase())
      ) {
        response.understanding = 3;
      } else if (
        response.understanding.toLowerCase().includes(
        "Good, I am confident I understand and can apply the presented material".toLowerCase()) ||
        response.understanding.toLowerCase() == (
        "D".toLowerCase())
      ) {
        response.understanding = 4;
      } else if (
        response.understanding.toLowerCase().includes( "Outstanding, I could teach yesterday's class".toLowerCase()) ||
        response.understanding.toLowerCase() == (
        "E".toLowerCase())
      ) {
        response.understanding = 5;
      } else {
        response.understanding = 0;
      }
      return response;
    },
    parseEnergyEntry(response) {
      try {
        response.energyInt = parseInt(response.energyEntry);
      } catch (err) {
        console.error(err);
        response.energyInt = 0;
      }
      return response;
    },
    getCohorts() {
      SurveysService.listCohorts()
        .then(cohorts => {
          cohorts.forEach(cohort => {
            if (cohort.active == true) {
              this.cohorts.push(cohort);
            }
          });
        })
        .catch(err => {
          console.error(err);
        });
    },
    getLocations() {
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
    getInstructors() {
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
    getSections() {
      SurveysService.listSections()
        .then(sections => {
          sections.forEach(section => {
            if (section.active == true) {
              this.sections.push(section);
            }
          });
        })
        .catch(err => {
          console.error(err);
        });
    },
    getTopics() {
      SurveysService.listTopics()
        .then(topics => {
          topics.forEach(topic => {
            if (topic.active == true) {
              this.topics.push(topic);
            }
          });
        })
        .catch(err => {
          console.error(err);
        });
    },
    getUser() {
      this.user.username = auth.getUser().sub;
    }
  },
  created() {
    this.getCohorts();
    this.getLocations();
    this.getInstructors();
    this.getSections();
    this.getTopics();
    this.getUser();
  }
};
</script>

<style scoped>
label {
  display: block;
}
.dropdown {
  border: 1px solid #ccc;
  border-radius: 0;
  cursor: pointer;
  display: inline-block;
  margin: 0 auto;
  padding: 10px;
  width: 100%;
  color: #666;
}
@media screen and (-webkit-min-device-pixel-ratio: 0) {
  .dropdown {
    background: #efefef
      url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/392/select-arrow.png) 95%
      50% no-repeat;
  }
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.container {
  text-align: left;
}

pre code {
  background-color: #eee;
  border: 1px solid #999;
  display: block;
  padding: 20px;
}

#app .form {
  text-align: left;
}

.form-control {
  border: 1px solid #ccc;
  border-radius: 0;
  cursor: pointer;
  display: inline-block;
  margin: 0 auto;
  padding: 10px;
  width: 100%;
  color: #666;
}

.v-btn:before {
  background-color: currentColor;
  background-color: rgb(250, 250, 250) !important;
}
.v-btn:hover {
  background-color: rgb(220, 220, 220) !important;
}
.form-container {
  display: block;
  align-self: center;
  justify-content: center;
  width: 70%;
  margin: 0 auto;
}
.form-field {
  margin-bottom: 10px;
}
.form-field label {
  font-weight: bold;
}
#submit-buttons {
  width: 50%;
  margin: 0 auto;
}
#submit-buttons * {
  width: 100%;
  margin: 5px;
}
</style>