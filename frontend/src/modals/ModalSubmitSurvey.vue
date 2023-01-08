<template>
  <modal-template :show="show" @close="close">
    <div class="modal-header">
      <h3>Submit Survey</h3>
    </div>
    <div class="modal-body">
      <label class="form-label">
        User
        <input :readonly="true" v-model="inputSurvey.user" class="form-control" />
      </label>

      <label class="form-label">
        Instructor
        <input v-model="inputSurvey.instructor" class="form-control" />
      </label>

      <label class="form-label">
        City
        <select v-model="inputSurvey.city" class="form-control dropdown">
      </select>
      </label>

      <label class="form-label">
       State
        <select v-model="inputSurvey.state" class="form-control dropdown">
        </select>
      </label>

      <label class="form-label">
        Cohort
      <select v-model="inputSurvey.cohort" class="form-control dropdown">
      </select>
      </label>

      <label class="form-label">
       Section
        <select v-model="inputSurvey.section" class="form-control dropdown">
        </select>
      </label>

      <label class="form-label">
       Topic
      <select v-model="inputSurvey.topic" class="form-control dropdown">
      </select>
      </label>

      <h4>Upload</h4>
      
    </div>
    <div class="modal-footer">
      <v-btn title class="modal-default-button" @click="close()">
        Cancel
        <v-icon>mdi-close-circle</v-icon>
      </v-btn>
      <v-btn title class="modal-default-button save" @click="savePost()" v-if="inputSurvey">
        Save
        <v-icon>mdi-content-save-edit</v-icon>
      </v-btn>
    </div>
  </modal-template>
</template>

<script>
import ModalTemplate from "@/modals/ModalTemplate.vue";

export default {
  name: "modal-submit-survey",
  props: {
    show: Boolean,
    survey: Object,
  },
  data() {
    return {
        inputSurvey: this.survey,
        editSurvey: ""
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
      this.$emit('update', this.inputSurvey);
      console.log(this.inputSurvey);
      //update inputSurvey in survey
      this.close();
    }

  },

  created() {
       this.evalEditSurvey();
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
  width: 92%;
  max-width: 92%;
  min-width: 92%;
  padding: 0.5em 1em;
  line-height: 1;
  border: 1px solid #ddd;
}

.dropdown {
  width: 100%;
  max-width: 100%;
  min-width: 100%;
  height: 1.9;
  font-size: 90%;
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
</style>