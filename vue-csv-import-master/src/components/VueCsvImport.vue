<template>
    <div class="vue-csv-uploader">
        <div class="form">
            <div class="vue-csv-uploader">
                <div class="form-group csv-import-file">
                    <input ref="csv" type="file" @change.prevent="validFileMimeType" :class="inputClass" name="csv">
                    <slot name="error" v-if="showErrorMessage">
                        <div class="invalid-feedback d-block">
                            File type is invalid
                        </div>
                    </slot>
                </div>
            </div>

            <div class="vue-csv-uploader-part-two">
                <div class="vue-csv-mapping">
                    <div class="form-group">
                        <slot name="submit" :submit="submit">
                            <input type="submit" :class="buttonClass" @click.prevent="submit" :value="submitBtnText">
                        </slot>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import _ from 'lodash';
    import axios from 'axios';
    import Papa from 'papaparse';
    import mimeTypes from "mime-types";

    export default {
        name: 'vue-csv-import',
        props: {
            value: Array,
            url: {
                type: String
            },
            mapFields: {
                required: true
            },
            callback: {
                type: Function,
                default: () => ({})
            },
            catch: {
                type: Function,
                default: () => ({})
            },
            finally: {
                type: Function,
                default: () => ({})
            },
            parseConfig: {
                type: Object,
                default() {
                    return {};
                }
            },
            headers: {
                default: null
            },
            loadBtnText: {
                type: String,
                default: "Next"
            },
            submitBtnText: {
                type: String,
                default: "Submit"
            },
            tableClass: {
                type: String,
                default: "table"
            },
            checkboxClass: {
                type: String,
                default: "form-check-input"
            },
            buttonClass: {
                type: String,
                default: "btn btn-primary"
            },
            inputClass: {
                type: String,
                default: "form-control-file"
            },
            validation: {
                type: Boolean,
                default: true,
            },
            fileMimeTypes: {
                type: Array,
                default: () => {
                    return ["text/csv", "text/x-csv", "application/vnd.ms-excel", "text/plain"];
                }
            }
        },

        data: () => ({
            form: {
                csv: null,
            },
            fieldsToMap: [],
            map: {presence: 0, studentName: 1, studentId: 2, totalScore: 3, numberCorrect: 4, pace: 5, interest: 6, understanding: 7, energyEntry: 8},
            hasHeaders: true,
            csv: null,
            sample: null,
            isValidFileMimeType: false,
            fileSelected: false
        }),

        created() {
            this.hasHeaders = this.headers;

            if (_.isArray(this.mapFields)) {
                this.fieldsToMap = _.map(this.mapFields, (item) => {
                    return {
                        key: item,
                        label: item
                    };
                });
            } else {
                this.fieldsToMap = _.map(this.mapFields, (label, key) => {
                    return {
                        key: key,
                        label: label
                    };
                });
            }
        },

        methods: {
            upload() {
                this.load();
                this.submit();
            },
            submit() {
                const _this = this;
                this.form.csv = this.buildMappedCsv();
                this.$emit('input', this.form.csv);
                if (this.url) {
                    axios.post(this.url, this.form).then(response => {
                        _this.callback(response);
                    }).catch(response => {
                        _this.catch(response);
                    }).finally(response => {
                        _this.finally(response);
                    });
                } else {
                    _this.callback(this.form.csv);
                }
            },
            buildMappedCsv() {
                const _this = this;
                let csv = this.hasHeaders ? _.drop(this.csv) : this.csv;
                return _.map(csv, (row) => {
                    let newRow = {};
                    _.forEach(_this.map, (column, field) => {
                        _.set(newRow, field, _.get(row, column));
                    });
                    return newRow;
                });
            },
            validFileMimeType() {
                let file = this.$refs.csv.files[0];
                const mimeType = file.type === "" ? mimeTypes.lookup(file.name) : file.type;
                if (file) {
                    this.fileSelected = true;
                    this.isValidFileMimeType = this.validation ? this.validateMimeType(mimeType) : true;
                    this.load();
                } else {
                    this.isValidFileMimeType = !this.validation;
                    this.fileSelected = false;
                }
            },
            validateMimeType(type) {
                return this.fileMimeTypes.indexOf(type) > -1;
            },
            load() {
                const _this = this;
                this.readFile((output) => {
                    _this.sample = _.get(Papa.parse(output, { preview: 2, skipEmptyLines: true }), "data");
                    _this.csv = _.get(Papa.parse(output, { skipEmptyLines: true }), "data");
                });
            },
            readFile(callback) {
                let file = this.$refs.csv.files[0];
                if (file) {
                    let reader = new FileReader();
                    reader.readAsText(file, "UTF-8");
                    reader.onload = function (evt) {
                        callback(evt.target.result);
                    };
                    reader.onerror = function () {
                    };
                }
            },
            toggleHasHeaders() {
                this.hasHeaders = !this.hasHeaders;
            },
            makeId(id) {
                return `${id}${this._uid}`;
            }
        },
        watch: {
            map: {
                deep: true,
                handler: function (newVal) {
                    if (!this.url) {
                        let hasAllKeys = Array.isArray(this.mapFields) ? _.every(this.mapFields, function (item) {
                            return newVal.hasOwnProperty(item);
                        }) : _.every(this.mapFields, function (item, key) {
                            return newVal.hasOwnProperty(key);
                        });
                        if (hasAllKeys) {
                            this.submit();
                        }
                    }
                }
            }
        },
        computed: {
            firstRow() {
                return _.get(this, "sample.0");
            },
            showErrorMessage() {
                return this.fileSelected && !this.isValidFileMimeType;
            },
            // disabledNextButton() {
            //     return !this.isValidFileMimeType;
            // }
        },
    };
</script>
