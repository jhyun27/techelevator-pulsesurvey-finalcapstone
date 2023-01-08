<template>
  <modal-template :show="show" @close="close">
    <div class="modal-header">
      <h3>{{whoseSurvey}} Survey Response</h3>
    </div>
    <div class="modal-body">
      <label class="form-label">
        Student ID
        <input v-model="inputResponse.studentId" class="form-control" />
      </label>
      <label class="form-label">
        Student Name
        <input v-model="inputResponse.studentName" class="form-control" />
      </label>
      <label class="form-label">
        Present
        <select v-model="inputResponse.present" class="form-control dropdown">
          <option value="true">Yes</option>
          <option value="false">No</option>
        </select>
      </label>
      <label class="form-label">
        Question 1: Pace of Class
        <select v-model="inputResponse.pace" class="form-control dropdown">
          <option value="1">1 - Way Too Slow</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5 - Way Too Fast</option>
        </select>
      </label>
      <label class="form-label">
        Question 2: Interest level
        <select
          v-model="inputResponse.interest"
          class="form-control dropdown"
        >
          <option value="1">1 - Boring</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5 - Exciting</option>
        </select>
      </label>
      <label class="form-label">
        Question 3: Understanding
        <select
          v-model="inputResponse.understanding"
          class="form-control dropdown"
        >
          <option value="1">1 - Poor</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5 - Excellent</option>
        </select>
      </label>
      <label class="form-label">
        Question 4: Energy Level
        <select
          v-model="inputResponse.energyInt"
          class="form-control dropdown"
        >
          <option value="1">1 - No Energy</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10 - Full of Energy</option>
        </select>
      </label>
      <label class="form-label">
        Q4 Full Response
        <textarea v-model="inputResponse.energyEntry" rows="5" class="form-control"></textarea>
      </label>
    </div>
    <div class="modal-footer">
      <v-btn tile class="modal-default-button" @click="close()">
        Cancel
        <v-icon>mdi-close-circle</v-icon>
      </v-btn>
      <v-btn tile class="modal-default-button save" @click="savePost()" v-if="response.studentId">
        Save edits
        <v-icon>mdi-content-save-edit</v-icon>
      </v-btn>
      <v-btn tile class="modal-default-button save" @click="savePost()" v-else>
        Save New
        <v-icon>mdi-content-save-edit</v-icon>
      </v-btn>
    </div>
  </modal-template>
</template>

<script>
import ModalTemplate from "@/modals/ModalTemplate.vue";

export default {
  name: "modal-edit-row",
  props: {
    show: Boolean,
    response: Object
  },
  data() {
    return {
      inputResponse: this.response,
      whoseSurvey: ""
    };
  },
  components: {
    ModalTemplate
  },
  methods: {
    close() {
      this.$emit("close");
      this.title = "";
      this.body = "";
    },
    savePost() {
      this.$emit("update", this.inputResponse);
      console.log(this.inputResponse);
      //update inputResponse in survey
      this.close();
    },
    evalWhoseSurvey() {
      if (this.response.studentId) {
        this.whoseSurvey = "Editing " + this.response.studentId + "'s";
      } else {
        this.whoseSurvey = "Making New";
      }
    }
  },
  created() {
    this.evalWhoseSurvey();
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
  margin-bottom: 1rem;
}

.form-label > .form-control {
  margin-top: 0.25rem;
}

.form-control {
  display: block;
  width: 100%;
  padding: 0.25rem 1rem;
  font-size: 90%;
  line-height: 1;
  border: 1px solid #ddd;
}


.modal-footer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

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
</style>