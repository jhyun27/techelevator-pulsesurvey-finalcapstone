<template>
  <div class="question-cards">
      <div v-if="surveyResponseSet != null" class="question-stats">
        <div v-for="question in surveyStats" v-bind:key="question.id" class="question-card">
    <v-card class="vCard" tile>
      <v-card-text>
        <div class="card-info">
          <div class="card-header">
            <h4 class="cardText cardHeader">Question #{{question.id}}</h4>
            <h5 class="questionText">{{question.question}}</h5>
          </div>
          <div class="card-table">
            <table class="stats-table">
              <tr>
                <th class="cardStat">Mean:</th>
                <td class="cardText">{{question.mean}}</td>
              </tr>
              <tr>
                <th class="cardStat">Median:</th>
                <td class="cardText">{{question.median}}</td>
              </tr>
              <tr>
                <th class="cardStat">Std Dev:</th>
                <td class="cardText">{{question.stddev}}</td>
              </tr>
            </table>
          </div>
        </div>
      </v-card-text>
      <v-card-actions>

      </v-card-actions>
    </v-card>
        </div>
      </div>
  </div>
</template>

<script>
import SurveyStats from "@/service/SurveyStats.js";

export default {
  name: "question-card",
  props: {
    surveyResponseSet: Array
  },
  data() {
    return {
      surveyStats: []
    };
  },
  methods: {
    calculateStats() {
      this.surveyStats = SurveyStats.calculateStats(this.surveyResponseSet);
    }
  },
  created() {
    this.calculateStats();
  }
};
</script>

<style>
.question-stats {
  display: flex;
  flex-direction: row;
  align-self: center;
  justify-content: center;
  margin: 5px 0;
}
.question-stats > div > * {
  margin: 3px 0;
  text-align: center;
}
.stats-table {
  table-layout: fixed;
  width: 100%;
}
.stats-table tr th {
  font-weight: normal;
  text-align: left;
}
.stats-table tr td {
  font-weight: normal;
  text-align: right;
}

.card-header h4 {
  margin: 0 auto 5px auto;
}
.card-header h5 {
  margin: 5px auto;
  font-weight: normal;
}

.question-card {
  max-height: 100%;
  width: 23%;
  margin: 8px;
  padding: 8px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-content: space-between;
}
.card-table {
  padding: 5px 0 0 0;
}
.cardText {
  color: black;
  font-size: 1.3em;
}
th {
  color: #00487C;
  font-size: 1.2em;
  font-weight: bold;
  text-transform: uppercase;
}
.questionText {
  font-size: 1em;
}
.cardHeader {
  color: #00487C;
  text-transform: uppercase;
}
.card-info {
  display: flex;
  height: 230px;
  flex-direction: column;
  justify-content: space-between;
  align-content: space-between;
}
.vCard {
  min-height: 260px;
}

@media only screen and (max-width: 1140px) {
  .cardText {
    font-size: 0.8rem;
  }
  .questionText {
    font-size: 0.8rem;
  }
}


@media only screen and (max-width: 900px) {
  .cardText {
    font-size: 0.6rem;
  }
  .questionText {
    font-size: 0.6rem;
  }
}

@media only screen and (max-width: 750px) {
  .cardText {
    font-size: 0.4rem;
  }
  .questionText {
    font-size: 0.4rem;
  }
}

</style>