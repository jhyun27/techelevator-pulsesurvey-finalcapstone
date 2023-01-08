<template>
  <div class="home">
    <h1>Surveys</h1>
    <surveys-list/>
  </div>
</template>

<script>
import SurveysList from "@/components/SurveysList.vue";

export default {
  name: "home",
  components: {
    SurveysList
  }
};
</script>

<style scoped>
.home {
  text-align: center;
  justify-content: center;
}

h1 {
  text-align: center;
  justify-content: center;
  margin-bottom: 10px;
}
</style>
