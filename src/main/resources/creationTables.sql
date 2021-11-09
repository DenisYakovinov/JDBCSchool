DROP TABLE IF EXISTS groups CASCADE;

CREATE TABLE groups (
    group_id SERIAL NOT NULL PRIMARY KEY,
    group_name VARCHAR(5)
);

DROP TABLE IF EXISTS students CASCADE;

CREATE TABLE students (
    student_id SERIAL NOT NULL PRIMARY KEY,
    group_id INTEGER REFERENCES groups ON DELETE SET NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

DROP TABLE IF EXISTS courses CASCADE;

CREATE TABLE courses (
    course_id SERIAL NOT NULL PRIMARY KEY,
    course_name VARCHAR(50),
    course_description VARCHAR(50)
);

DROP TABLE IF EXISTS courses_students CASCADE;

CREATE TABLE courses_students (
    student_id INTEGER REFERENCES students ON UPDATE CASCADE ON DELETE CASCADE,
    course_id INTEGER REFERENCES courses ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (student_id, course_id)
);