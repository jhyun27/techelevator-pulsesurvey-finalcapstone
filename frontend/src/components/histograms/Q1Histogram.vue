<template>
  <div id="q1-histogram">
    <v-expansion-panels> 
      <v-expansion-panel class="expansion-panel">
        <v-expansion-panel-header class="title">View Responses to Q1</v-expansion-panel-header>
        <v-expansion-panel-content>
          <apexchart class="histogram" type="bar" height="350" :options="chartOptions" :series="series" />
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "q1-histogram",

  props: {
    stats: Array
  },

  components: {
    apexchart: VueApexCharts
  },

  data() {
    return {

      series: [
        {
          name: "# of students",
          data: this.getResponsesForQ1()
        }
      ],

      chartOptions: {
        chart: {
          height: 350,
          type: "bar"
        },
        plotOptions: {
          bar: {
            dataLabels: {
              position: "top"
            }
          }
        },
        dataLabels: {
          enabled: true,
          formatter: function(val) {
            return val;
          },
          offsetY: -20,
          style: {
            fontSize: "12px",
            colors: ["#304758"]
          }
        },
        xaxis: {
          categories: ["1 - Too slow", "2", "3 - Just right", "4", "5 - Too fast"],
          position: "bottom",
          labels: {
            offsetY: 0
          },
          axisBorder: {
            show: true,
            offsetY: 2
          },
          axisTicks: {
            show: true
          },
          crosshairs: {
            fill: {
              type: "gradient",
              gradient: {
                colorFrom: "#D8E3F0",
                colorTo: "#BED1E6",
                stops: [0, 100],
                opacityFrom: 0.4,
                opacityTo: 0.5
              }
            }
          },

        },
        fill: {
          gradient: {
            shade: "light",
            type: "horizontal",
            shadeIntensity: 0.25,
            gradientToColors: undefined,
            inverseColors: true,
            opacityFrom: 1,
            opacityTo: 1,
            stops: [50, 0, 100, 100]
          }
        },
        yaxis: {
          axisBorder: {
            show: true,
          },
          axisTicks: {
            show: true
          },
          labels: {
            show: true,
            formatter: function(val) {
              return val;
            }
          }
        },
        title: {
          text: "Queston #1: The pace of yesterday's class was",
          floating: true,
          position: "top",
          align: "center",
          style: {
            color: "#444"
          }
        }
      }
    };
  },

  methods: {
    getResponsesForQ1() {
      let count1 = 0;
      let count2 = 0;
      let count3 = 0;
      let count4 = 0;
      let count5 = 0;

      let array = [];
      this.stats[0].responses.forEach(value => {
        if (value === 1) {
          count1++;
        }
        if (value === 2) {
          count2++;
        }
        if (value === 3) {
          count3++;
        }
        if (value === 4) {
          count4++;
        }
        if (value === 5) {
          count5++;
        }
      });
      array.push(count1);
      array.push(count2);
      array.push(count3);
      array.push(count4);
      array.push(count5);

      return array;
    }
  }
};
</script>

<style scoped>

 .histogram {
     width: 100%;
     height: auto;
     margin: 10px;
 }

 .expansion-panel {
     margin: 10px 0;
 }

  .title {
     color: #1f76d1;
     text-transform: uppercase;
 }

</style>