<template>
  <div class="response-details">
    <v-expansion-panels>
      <v-expansion-panel>
        <v-expansion-panel-header class="title">View Student Responses</v-expansion-panel-header>
        <v-expansion-panel-content>
          <div v-if="surveyResponseSet != null">
            <h2>Survey Responses:</h2>
            <table>
              <tr class="response-table-header">
                <th>
                  <div v-if="showNames==true">
                    <v-btn type="button" outlined block @click="showHideNames">
                      Hide Student
                      <br />Names
                    </v-btn>
                  </div>
                  <div v-else>
                    <v-btn type="button" outlined block @click="showHideNames">
                      Show Student
                      <br />Names
                    </v-btn>
                  </div>
                </th>
                <th>Q1</th>
                <th>Q2</th>
                <th>Q3</th>
                <th>Q4</th>
                <th>
                  Q4
                  <br />(full text)
                </th>
                <th>Edit</th>
              </tr>

              <tr v-for="response in responsesComputed" :key="response.id" class="response-item">
                <td>
                  <div v-if="showNames==true">
                    <v-btn disabled type="button" small block class="student-names">{{response.studentName}}</v-btn>
                  </div>
                  <div v-else>
                    <v-btn disabled type="button" small block>Hidden</v-btn>
                  </div>
                </td>
                <td>{{response.pace}}</td>
                <td>{{response.interest}}</td>
                <td>{{response.understanding}}</td>
                <td>{{response.energyInt}}</td>
                <td>{{response.energyEntry}}</td>
                <td>
                  <v-btn type="button" small class="btn" @click="showModal(response)">Edit</v-btn>

                  <modal-edit-row
                    v-if="isModalVisible"
                    :show="response.showModal"
                    @close="closeModal(response)"
                    :response="response"
                    @update="updateSurveyObj($event)"
                  />
                </td>
              </tr>
              <tr class="response-item">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                  <v-btn type="button" small class="btn" @click="showNewModal">New</v-btn>
                </td>
              </tr>
            </table>
            </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
import ModalEditRow from "@/modals/ModalEditRow.vue";

export default {
  name: "survey-response-details",
  components: {
    ModalEditRow
  },
  props: {
    surveyResponseSet: Array
  },
  data() {
    return {
      isNewModalVisible: false,
      newResponse: {},
      currentModalResponse: "",
      showNames: false
    };
  },
  methods: {
    showNewModal() {
      this.isNewModalVisible = true;
    },
    showModal(response) {
      response.showModal = true;
      this.currentModalResponse = response;
    },
    closeNewModal() {
      this.isNewModalVisible = false;
    },
    closeModal(response) {
      response.showModal = false;
      this.currentModalResponse = "";
    },
    updateSurveyObj(returnedResponse) {
      this.surveyResponseSet.forEach(response => {
        if (response.studentId == returnedResponse.studentId) {
          response = returnedResponse;
        }
      });
      this.$emit("updateSurvey", this.surveyResponseSet);
    },
    addToSurveyObj(returnedResponse) {
      this.surveyResponseSet.push(returnedResponse);
      this.$emit("updateSurvey", this.surveyResponseSet);
    },
    showHideNames() {
      this.showNames = !this.showNames;
    }
  },
  created() {
    this.surveyResponseSet.forEach(response => {
      response.showModal = false;
      response.showName = false;
    });
  },
  computed: {
    isModalVisible(vm) {
      return vm.currentModalResponse.showModal;
    },
    responsesComputed(vm) {
      return vm.surveyResponseSet;
    }
  }
};
</script>

<style scoped>
div.response-details {
  /* border: 0px red solid;
  border-radius: 6px;
  padding: 1rem; */
  text-align: center;
}

table {
  margin-top: 10px;
  border-collapse: collapse;
  width: 100%;
}

tr {
  border-bottom: 2px solid rgba(0, 0, 0, 0.3);
  padding: 2px;
  line-height: 20px;
  height: 40px;
}
tr * {
  min-width: 60px;
}

tr:first-child {
  border-top: 2px solid rgba(0, 0, 0, 0.6);
  border-bottom: 2px solid rgba(0, 0, 0, 0.6);
}
tr:last-child {
  background-color: rgba(0, 0, 0, 0.1);
}

td,
th {
  text-align: center;
  padding: 2px 8px;
  border-right: 1px solid rgba(0, 0, 0, 0.05);
}
td:last-child,
th:last-child {
  border-right: none;
}
tr td:first-child,
tr th:first-child {
  width: 180px;
  max-width: 180px;
  min-width: 180px;
}
.student-names {
  color: white;
  pointer-events: none;
  background-color: white !important;
}
.theme--light.v-btn.v-btn--disabled.student-names:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined) {
    background-color: rgba(0, 0, 0, 0) !important;
}
.theme--light.v-btn.v-btn--disabled.student-names {
    color: rgba(0, 0, 0, 1) !important;
}
 .title {
     color: #1f76d1;
     text-transform: uppercase;
 }
</style>