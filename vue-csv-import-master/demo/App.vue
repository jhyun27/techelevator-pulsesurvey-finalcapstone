<template>
    <div id="app">
        <div class="container">
            <section class="py-5">
                <div class="row mt-5">
                    <div class="col-8 offset-2">
                        <vue-csv-import v-model="csv" :map-fields="['presence', 'studentName', 'studentId', 'totalScore', 'numberCorrect', 'pace', 'interest', 'understanding', 'energyEntry']"></vue-csv-import>
                        <div class="mt-2">
                            {{ csv }}
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</template>

<script>
    export default {
        name: "app",
        data() {
            return {
                csv: null,
            };
        }
    };
</script>

<style>
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
</style>
