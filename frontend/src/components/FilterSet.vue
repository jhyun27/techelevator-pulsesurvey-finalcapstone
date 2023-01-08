<template>
    <v-expansion-panels :accordion="accordion">
        <v-expansion-panel>
            <v-expansion-panel-header class="filterHeader">Filter By:</v-expansion-panel-header>
            <v-expansion-panel-content>
                <v-container>
                <form @submit.prevent="handleFilter">
                    <v-row>
                        <v-col cols="12" sm="4">
                            <v-text-field @keyup="handleFilter" v-model.trim="filter.topicText" label="Topic" single-line outlined class="filter"/>
                        </v-col>
                        <v-col cols="12" sm="4">
                            <v-text-field @keyup="handleFilter" v-model.trim="filter.instructorFirstText" label="Instructor First Name" single-line outlined class="filter"/>
                        </v-col>
                        <v-col cols="12" sm="4">
                            <v-text-field @keyup="handleFilter" v-model.trim="filter.instructorLastText" label="Instructor Last Name" single-line outlined class="filter"/>
                        </v-col>
                        <v-col cols="12" sm="4">
                            <v-text-field @keyup="handleFilter" v-model.trim="filter.cityText" label="City" single-line outlined class="filter"/>
                        </v-col>
                        <v-col cols="12" sm="4">
                            <v-text-field @keyup="handleFilter" v-model.trim="filter.stateText" label="State" single-line outlined class="filter"/>
                        </v-col>
                        <v-col cols="12" sm="4">
                            <v-text-field @keyup="handleFilter" v-model.trim="filter.cohortText" label="Cohort" single-line outlined class="filter"/>
                        </v-col>
                    </v-row>
                </form>
                </v-container>
            </v-expansion-panel-content>
        </v-expansion-panel>
    </v-expansion-panels>
</template>

<script>
export default {
    name: 'filter-set',
    data() {
        return {
            filter: {},
            accordion: false
        }
    },
    methods: {
        handleFilter() {
            this.$emit('filter', this.filter) 
        }
    }

}
</script>

<style scoped>
    .filter {
        width: 100%;
        display: flex;
        flex-direction: row;
    }

    .filterHeader {
        font-size: 1.3em;
        color: #1f76d1;
        text-transform: uppercase;
        font-weight: bold;
    }
</style>