/**
 * Service class to extract the functionality of list and getting users.
 */
// const API_URL = 'http://5de9402acb3e3800141b8f9c.mockapi.io/api/surveys';
const API_URL = 'http://localhost:8080/AuthenticationApplication/api/surveys';
const API_ADMIN_URL = 'http://localhost:8080/AuthenticationApplication/api/users';

export default {

    list() {
        return fetch(`${API_URL}`)
            .then((response) => {
                return response.json();
            });
    },
    listCohorts() {
        return fetch(`${API_URL}/cohorts`)
            .then((response) => {
                return response.json();
            });
    },
    listLocations() {
        return fetch(`${API_URL}/locations`)
            .then((response) => {
                return response.json();
            });
    },
    listInstructors() {
        return fetch(`${API_URL}/instructors`)
            .then((response) => {
                return response.json();
            });
    },
    listSections() {
        return fetch(`${API_URL}/sections`)
            .then((response) => {
                return response.json();
            });
    },
    listTopics() {
        return fetch(`${API_URL}/topics`)
            .then((response) => {
                return response.json();
            });
    },
    get(surveyId) {
        return fetch(`${API_URL}/${surveyId}`)
            .then((response) => {
                return response.json();
            });
    },

    getSurveyVersions(surveyId) {
        return fetch(`${API_URL}/${surveyId}/version`)
            .then((response) => {
                return response.json();
            });
    },

    saveSurvey(survey) {
        return fetch(`${API_URL}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(survey)
        })
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    console.error(`Post failed ${response}`)
                }
            })
            .catch(err => { console.error(err) });
    },

    updateSurvey(survey) {
        return fetch(`${API_URL}/${survey.surveyId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(survey)
        })
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    console.error(`Post failed ${response}`)
                    console.error(response);
                }
            })
            .catch(err => { console.error(err) });

    },
    listUsers() {
        return fetch(`${API_ADMIN_URL}`)
            .then((response) => {
                return response.json();
            })
    },
    getUser(username) {
        return fetch(`${API_ADMIN_URL}/${username}`)
            .then((response) => {
                return response.json();
            });
    },
    saveUser(user) {
        return fetch(`${API_ADMIN_URL}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then((response) => {
                if (response.ok) {
                    console.log(`Post successful`);
                } else {
                    console.error(`Post failed ${response}`)
                }
            })
            .catch(err => { console.error(err) });
    },
    updateUserRole(user) {
        return fetch(`${API_ADMIN_URL}/updateRole`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    console.error(`Post failed ${response}`)
                    console.error(response);
                }
            })
            .catch(err => { console.error(err) });

    },
    deleteUser(id) {
        return fetch(`${API_ADMIN_URL}/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((response) => {
                if (response.ok) {
                    console.log(`Delete successful`);
                    // return response.json();
                } else {
                    console.error(`Post failed ${response}`)
                    console.error(response);
                }
            })
            .catch(err => { console.error(err) });
    }
    
}