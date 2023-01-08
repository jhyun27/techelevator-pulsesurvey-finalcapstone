<template>
  <div class="plots">
    <scatter-plot :relationalStats="relationalStats"></scatter-plot>
    <q1-histogram :stats="stats"></q1-histogram>
    <q2-histogram :stats="stats"></q2-histogram>
    <q3-histogram :stats="stats"></q3-histogram>
    <q4-histogram :stats="stats"></q4-histogram>
    <!-- <q5-histogram :stats="stats"></q5-histogram> -->
    
  </div>
</template>

<script>
import SurveyStats from "@/service/SurveyStats.js";
import ScatterPlot from "@/components/ScatterPlot.vue";
import Q1Histogram from "@/components/histograms/Q1Histogram.vue";
import Q2Histogram from "@/components/histograms/Q2Histogram.vue";
import Q3Histogram from "@/components/histograms/Q3Histogram.vue";
import Q4Histogram from "@/components/histograms/Q4Histogram.vue";
// import Q5Histogram from "@/components/histograms/Q2Histogram.vue";


export default {
  name: "graphics",
  components: {
    ScatterPlot,
    Q1Histogram,
    Q2Histogram,
    Q3Histogram,
    Q4Histogram
    // Q5Histogram,
    
  },
  props: {
    surveyResponseSet: Array
  },
  data() {
    return {
      stats: [],
      relationalStats: []
    };
  },
  methods: {
    calculateStats() {
      this.stats = SurveyStats.calculateStats(this.surveyResponseSet);
      this.relationalStats = SurveyStats.getRelationalStats(
        this.surveyResponseSet
      );
    }
  },
  created() {
    this.calculateStats();
  }
};
</script>
<style>
.plots > * { 
  margin: 10px 0
}

</style>