f<template>
  <div id="scatter-plot">
    <v-expansion-panels>
      
      <v-expansion-panel class="expansion-panel">
        <v-expansion-panel-header class="title">View Trends (vs. Energy)</v-expansion-panel-header>
        <v-expansion-panel-content>
            <div id="scatterVenergy">
                <h3>Student Response Trends (Pace, Interest, Understanding vs. Energy)</h3>
                <apexchart class="scatter" type=scatter height=350 :options="chartOptions10" :series="series10" />
            </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <v-expansion-panels>
      <v-expansion-panel class="expansion-panel">
        <v-expansion-panel-header class="title">View Trends (Other)</v-expansion-panel-header>
        <v-expansion-panel-content>
            <div id="scatter-other">
                <h3>Student Response Trends (Other)</h3>
                <apexchart class="scatter" type=scatter height=350 :options="chartOptions5" :series="series5" />
            </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
    
    </v-expansion-panels>
  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts';

export default {
    name: 'scatter-plot',
    
    props: {
        stats: Array,
        relationalStats: Array
    },
    components: {
        apexchart: VueApexCharts,
    },
    data() {
        return {
            surveyStats: [],
            series10: [
                {
                    name: "Pace vs. Energy",
                    data: this.plotPaceVsEnergy()
                }, 
                { 
                    name: "Understanding vs. Energy",
                    data: this.plotUnderstandingVsEnergy()
                }, 
                {
                    name: "Interest vs. Energy",
                    data: this.plotInterestVsEnergy()
                }
            ],
            series5: [
                {
                    name: "Pace vs. Understanding",
                    data: this.plotPaceVsUnderstanding()
                },
                {
                    name: "Interest vs. Pace",
                    data: this.plotInterestVsPace()
                },
                {
                    name: "Interest vs. Understanding",
                    data: this.plotInterestVsUnderstanding()
                },
            ],
            chartOptions10: {
                chart: {
                    zoom: {
                        enabled: true,
                        type: 'xy'
                    }
                },
                xaxis: {
                    tickAmount: 10,
                    min: 0,
                    max: 10,
                    labels: {
                        formatter: function(val) {
                            return parseFloat(val).toFixed(1)
                        }
                    }
                },
                yaxis: {
                    min: 0,
                    max: 5,
                    tickAmount: 5
                },
            },
            chartOptions5: {
                chart: {
                    zoom: {
                        enabled: true,
                        type: 'xy'
                    }
                },
                xaxis: {
                    min: 0,
                    max: 5,
                    tickAmount: 5,
                    labels: {
                        formatter: function(val) {
                            return parseFloat(val).toFixed(1)
                        }
                    }
                },
                yaxis: {
                    min: 0,
                    max: 5,
                    tickAmount: 5
                },
            },
        }
    },
    methods: {
        plotPaceVsEnergy() {
            let array = [];
            this.relationalStats.forEach( (response) => {
                array.push([response.q4, response.q1]);
            });
            return array;
        },
        plotUnderstandingVsEnergy() {
            let array = [];
            this.relationalStats.forEach( response => {
                array.push([response.q4, response.q3]);
            });
            return array;
        },
        plotInterestVsEnergy() {
            let array = [];
            this.relationalStats.forEach( response => {
                array.push([response.q4, response.q2]);
            });
            return array;
        },
        plotPaceVsUnderstanding() {
            let array = [];
            this.relationalStats.forEach( response => {
                array.push([response.q3, response.q1]);
            });
            return array;
        },
        plotInterestVsUnderstanding() {
            let array = [];
            this.relationalStats.forEach( response => {
                array.push([response.q2, response.q3]);
            });
            return array;
        },
        plotInterestVsPace() {
            let array = [];
            this.relationalStats.forEach( response => {
                array.push([response.q1, response.q2]);
            });
            return array;
        }
    }
}
</script>

<style scoped>

 .scatter {
     width: 100%;
     height: auto;
 }

 .expansion-panel {
     margin: 10px 0;
 }

 .title {
     color: #1f76d1;
     text-transform: uppercase;
 }

</style>