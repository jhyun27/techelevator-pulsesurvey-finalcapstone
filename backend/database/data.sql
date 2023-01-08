-- Password for this user is 'greatwall'
INSERT INTO users ("username", "password", "salt", "role", oneTimePass) VALUES
('user',
'FjZDm+sndmsdEDwNtfr6NA==',
'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
'user',
false);

-- Password for this user is 'greatwall'
INSERT INTO users (username, password, salt, role, oneTimePass) VALUES ('admin', 'FjZDm+sndmsdEDwNtfr6NA==', 'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=', 'admin', false);

INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 0, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 1, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 2, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 3, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 4, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 5, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 6, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 7, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 8, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 9, true);
INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, 10, true);
INSERT INTO location (location_id, city, state, isActive) VALUES (DEFAULT, 'Columbus', 'OH', true);
INSERT INTO location (location_id, city, state, isActive) VALUES (DEFAULT, 'Pittsburgh', 'PA', true);
INSERT INTO location (location_id, city, state, isActive) VALUES (DEFAULT, 'Cleveland', 'OH', true);
INSERT INTO instructor (instructor_id, first_name, last_name, isActive) VALUES (DEFAULT, 'Brian', 'Lauvray', true);
INSERT INTO instructor (instructor_id, first_name, last_name, isActive) VALUES (DEFAULT, 'John', 'Fulton', true);
INSERT INTO instructor (instructor_id, first_name, last_name, isActive) VALUES (DEFAULT, 'Steve', 'Carmichael', true);
INSERT INTO instructor (instructor_id, first_name, last_name, isActive) VALUES (DEFAULT, 'Andrew', 'Frank', true);
INSERT INTO instructor (instructor_id, first_name, last_name, isActive) VALUES (DEFAULT, 'Carson', 'Hoff', true);
INSERT INTO instructor (instructor_id, first_name, last_name, isActive) VALUES (DEFAULT, 'Rachelle', 'Rauh', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M1-04 Loops and Arrays', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M1-08 Collections Part 2', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M1-12 Polymorphism', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M1-18 FileIO Writing Out', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M2-03 JOINS', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M2-04 INSERT, UPDATE, and DELETE', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M2-06 Database Connectivity DAO', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M2-07 Integration Testing', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M3-02 CSS Layouts', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M3-03 MVC Part 1', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M3-07 HTTP POST', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M3-10 User Authentication', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M4-01 Intro to Javascript', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M4-03 DOM', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M4-06 REST APIs', true);
INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, 'M4-09 Vue Event Handling', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '08-Java-Blue-CBUS', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '09-Java-Blue-CBUS', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '10-Java-Blue-CBUS', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '08-.NET-CBUS', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '09-.NET-CBUS', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '10-.NET-CBUS', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '08-Java-Green-CBUS', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '09-Java-Green-CBUS', true);
INSERT INTO section (section_id, section_name, isActive) VALUES (DEFAULT, '10-Java-Green-CBUS', true);
INSERT INTO survey (survey_id) VALUES (DEFAULT);
INSERT INTO survey (survey_id) VALUES (DEFAULT);
INSERT INTO survey (survey_id) VALUES (DEFAULT);
INSERT INTO survey_version (version_id, survey_id, user_id, timestamp, cohort_id, location_id, instructor_id, section_id, topic_id) VALUES (DEFAULT, 1, 1, '12/12/2019 11:11:11', (SELECT cohort_id FROM cohort WHERE cohort_number = 9), (SELECT location_id FROM location WHERE city = 'Columbus'), (SELECT instructor_id FROM instructor WHERE first_name = 'Brian'), (SELECT section_id FROM section WHERE section_name = '09-Java-Blue-CBUS'), (SELECT topic_id FROM topic WHERE topic_name = 'M3-03 MVC Part 1'));
INSERT INTO survey_version (version_id, survey_id, user_id, timestamp, cohort_id, location_id, instructor_id, section_id, topic_id) VALUES (DEFAULT, 1, 1, '12/13/2019 11:11:11', (SELECT cohort_id FROM cohort WHERE cohort_number = 9), (SELECT location_id FROM location WHERE city = 'Columbus'), (SELECT instructor_id FROM instructor WHERE first_name = 'Brian'), (SELECT section_id FROM section WHERE section_name = '09-Java-Blue-CBUS'), (SELECT topic_id FROM topic WHERE topic_name = 'M3-03 MVC Part 1'));
INSERT INTO survey_version (version_id, survey_id, user_id, timestamp, cohort_id, location_id, instructor_id, section_id, topic_id) VALUES (DEFAULT, 2, 1, '12/14/2019 11:11:11', (SELECT cohort_id FROM cohort WHERE cohort_number = 8), (SELECT location_id FROM location WHERE city = 'Columbus'), (SELECT instructor_id FROM instructor WHERE first_name = 'Steve'), (SELECT section_id FROM section WHERE section_name = '08-Java-Green-CBUS'), (SELECT topic_id FROM topic WHERE topic_name = 'M1-18 FileIO Writing Out'));
INSERT INTO survey_version (version_id, survey_id, user_id, timestamp, cohort_id, location_id, instructor_id, section_id, topic_id) VALUES (DEFAULT, 3, 1, '12/15/2019 11:11:11', (SELECT cohort_id FROM cohort WHERE cohort_number = 10), (SELECT location_id FROM location WHERE city = 'Columbus'), (SELECT instructor_id FROM instructor WHERE first_name = 'John'), (SELECT section_id FROM section WHERE section_name = '10-.NET-CBUS'), (SELECT topic_id FROM topic WHERE topic_name = 'M3-10 User Authentication'));
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/12/2019 11:11:11'), true, 'Joanne Hyun', 'JHYUN', 1, 1, 1, 1, 1, '7', 7);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/12/2019 11:11:11'), true, 'Lucy Festin', 'LFESTIN', 2, 2, 2, 2, 2, '8 Great!', 8);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/12/2019 11:11:11'), true, 'Scout Wallace', 'SWALLACE', 3, 3, 3, 3, 3, '9', 9);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/13/2019 11:11:11'), true, 'Joanne Hyun', 'JHYUN', 4, 4, 4, 4, 4, '7', 7);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/13/2019 11:11:11'), true, 'Lucy Festin', 'LFESTIN', 5, 5, 5, 5, 5, '8 Great!', 8);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/13/2019 11:11:11'), true, 'Scout Wallace', 'SWALLACE', 1, 1, 1, 1, 1, '9', 9);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/14/2019 11:11:11'), true, 'Joanne Hyun', 'JHYUN', 2, 2, 2, 2, 2, '7', 7);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/14/2019 11:11:11'), true, 'Lucy Festin', 'LFESTIN', 3, 3, 3, 3, 3, '8 Great!', 8);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/14/2019 11:11:11'), true, 'Scout Wallace', 'SWALLACE', 4, 4, 4, 4, 4, '9', 9);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/15/2019 11:11:11'), true, 'Joanne Hyun', 'JHYUN', 2, 2, 2, 2, 2, '7', 7);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/15/2019 11:11:11'), true, 'Lucy Festin', 'LFESTIN', 3, 3, 3, 3, 3, '8 Great!', 8);
INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) VALUES (DEFAULT, (SELECT version_id FROM survey_version WHERE timestamp = '12/15/2019 11:11:11'), true, 'Scout Wallace', 'SWALLACE', 4, 4, 4, 4, 4, '9', 9);