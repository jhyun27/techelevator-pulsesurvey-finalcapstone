
export default {
    calculateStatsMultipleSurveys(surveys) {
        const allSurveysResponseSets = [];
        surveys.forEach(survey => {
            allSurveysResponseSets.push(survey.surveyResponseSet);
        })
        
        const output = {
            stats: this.calculateStats(allSurveysResponseSets),
            relationalStats: this.getRelationalStats(allSurveysResponseSets)
        }

        return output;
        
    },
    calculateStats(surveyResponseSet) {
        const q1 = {
            id: 1,
            question:
                "The pace of yesterday's class was: (1 - too slow, 5 - too fast)",
            mean: 0,
            median: 0,
            stddev: 0,
            responses: []
        };
        const q2 = {
            id: 2,
            question:
                "The content of the previous class was: (1 - uninteresting, 5 - extremely interesting)",
            mean: 0,
            median: 0,
            stddev: 0,
            responses: []
        };
        const q3 = {
            id: 3,
            question:
                "I feel my level of understanding of the previous day's material is: (1 - poor, 5 - excellent)",
            mean: 0,
            median: 0,
            stddev: 0,
            responses: []
        };
        const q4 = {
            id: 4,
            question:
                "On a scale of 1-10, my energy level today is: (1 - none, 10 - energetic)",
            mean: 0,
            median: 0,
            stddev: 0,
            responses: []
        };
        const surveyStats = [q1, q2, q3, q4];

        surveyResponseSet.forEach(singleResponse => {
            if (singleResponse.pace > 0 && singleResponse.pace <= 5) {
                q1.responses.push(singleResponse.pace);
            }
            if (singleResponse.interest > 0 && singleResponse.interest <= 5) {
                q2.responses.push(singleResponse.interest);
            } 
            if (singleResponse.understanding > 0 && singleResponse.understanding <= 5) {
                q3.responses.push(singleResponse.understanding);
            } 
            if (singleResponse.energyInt > 0 && singleResponse.energyInt <= 10) {
                q4.responses.push(singleResponse.energyInt);
            }
        });
        surveyStats.forEach(singleQuestion => {
            this.calculateMean(singleQuestion);
            this.calculateMedian(singleQuestion);
            this.calculateStdDev(singleQuestion);
        });
        return surveyStats;
    },
    calculateMean(singleQuestion) {
        singleQuestion.mean = this.roundToMax2Decimals(
            this.mean(singleQuestion.responses)
        );
    },
    calculateMedian(singleQuestion) {
        singleQuestion.median = this.roundToMax2Decimals(
            this.median(singleQuestion.responses)
        );
    },
    calculateStdDev(singleQuestion) {
        const sqr_diffs = singleQuestion.responses.map(value => {
            const diff = singleQuestion.mean - value;
            return diff * diff;
        });
        singleQuestion.stddev = this.roundToMax2Decimals(Math.sqrt(this.mean(sqr_diffs)));
    },
    mean(numbers) {
        let total = 0,
            i;
        for (i = 0; i < numbers.length; i += 1) {
            if (numbers[i]) total += numbers[i];
        }
        if (numbers.length > 0) {
            return total / numbers.length;
        } else {
            return 0;
        }
    },
    median(numbers) {
        let median = 0,
            numsLen = numbers.length;
        numbers.sort();

        if (numsLen % 2 === 0) {
            median = (numbers[numsLen / 2 - 1] + numbers[numsLen / 2]) / 2;
        } else {
            median = numbers[(numsLen - 1) / 2];
        }

        return median;
    },
    roundToMax2Decimals(number) {
        return Math.round(100 * number) / 100;
    },
    getRelationalStats(surveyResponseSet) {
        const responses = [];
        
        surveyResponseSet.forEach(singleResponse => {
            const singleSurveyResponseNumbers = {
                id: "",
                q1: 0,
                q2: 0,
                q3: 0,
                q4: 0,
            }

            singleSurveyResponseNumbers.id = singleResponse.studentId;
            singleSurveyResponseNumbers.q1 = singleResponse.pace + this.roundToMax2Decimals(Math.random()*0.2-0.1);
            singleSurveyResponseNumbers.q2 = singleResponse.interest + this.roundToMax2Decimals(Math.random()*0.2-0.1);
            singleSurveyResponseNumbers.q3 = singleResponse.understanding + this.roundToMax2Decimals(Math.random()*0.2-0.1);
            singleSurveyResponseNumbers.q4 = singleResponse.energyInt + this.roundToMax2Decimals(Math.random()*0.2-0.1);
        
            responses.push(singleSurveyResponseNumbers);
        })
        
        return responses;
    }
}