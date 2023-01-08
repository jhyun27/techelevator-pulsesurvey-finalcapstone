BEGIN TRANSACTION;
DROP TABLE IF EXISTS survey_response;
DROP TABLE IF EXISTS survey_version;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS survey;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS section;
DROP TABLE IF EXISTS instructor;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS cohort;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) UNIQUE NOT NULL,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user'),
  oneTimePass boolean NOT NULL
);

CREATE TABLE cohort (
        cohort_id serial PRIMARY KEY,
        cohort_number integer NOT NULL,
        isActive boolean NOT NULL
);

CREATE TABLE location (
        location_id serial PRIMARY KEY,
        city varchar(50) NOT NULL,
        state varchar(50) NOT NULL,
        isActive boolean NOT NULL
);

CREATE TABLE instructor (
        instructor_id serial PRIMARY KEY,
        first_name varchar(50) NOT NULL,
        last_name varchar(50) NOT NULL,
        isActive boolean NOT NULL
);

CREATE TABLE section (
        section_id serial PRIMARY KEY,
        section_name varchar(50) NOT NULL,
        isActive boolean NOT NULL
);


CREATE TABLE topic (
        topic_id serial PRIMARY KEY,
        topic_name varchar(255) NOT NULL,
        isActive boolean NOT NULL
);


CREATE TABLE survey (
        survey_id serial PRIMARY KEY
);

CREATE TABLE survey_version (
        version_id serial PRIMARY KEY,
        survey_id integer NOT NULL,
        user_id integer NOT NULL,
        timestamp TIMESTAMP NOT NULL,
        cohort_id integer NOT NULL,
        location_id integer NOT NULL,
        instructor_id integer NOT NULL,
        section_id integer NOT NULL,
        topic_id integer NOT NULL,
        CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES location(location_id),
        CONSTRAINT fk_instructor_id FOREIGN KEY (instructor_id) REFERENCES instructor(instructor_id),
        CONSTRAINT fk_cohort_id FOREIGN KEY (cohort_id) REFERENCES cohort(cohort_id),
        CONSTRAINT fk_section_id FOREIGN KEY (section_id) REFERENCES section(section_id),
        CONSTRAINT fk_topic_id FOREIGN KEY (topic_id) REFERENCES topic(topic_id),
        CONSTRAINT fk_survey_id FOREIGN KEY (survey_id) REFERENCES survey(survey_id),
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE survey_response (
        response_id serial PRIMARY KEY,
        version_id integer NOT NULL,
        presence boolean NOT NULL,
        student_name varchar(50) NOT NULL,
        student_id varchar(35) NOT NULL,
        total_score integer,
        number_correct integer,
        pace integer,
        interest integer,
        understanding integer,
        energy_entry varchar(255),
        energy_int integer,
        CONSTRAINT fk_version_id FOREIGN KEY (version_id) REFERENCES survey_version(version_id)
);

COMMIT TRANSACTION;
