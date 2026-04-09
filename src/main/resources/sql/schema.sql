DROP TABLE IF EXISTS session_user_role;
DROP TABLE IF EXISTS thesis_user_role ;
DROP TABLE IF EXISTS reviewer_grade;
DROP TABLE IF EXISTS final_grade;
DROP TABLE IF EXISTS committee_member_grade;
DROP TABLE IF EXISTS supervisor_form;
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS "role";
DROP TABLE IF EXISTS thesis;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS "session";


CREATE TABLE IF NOT EXISTS "session" (
    id BIGSERIAL PRIMARY KEY,
    start_date DATE,
    end_date DATE
);

CREATE TABLE student (
                         id BIGSERIAL PRIMARY KEY,

                         name VARCHAR(100) NOT NULL,
                         second_name VARCHAR(100) NOT NULL,
                         email VARCHAR(255),

                         student_number VARCHAR(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS thesis (
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL,
    curriculum VARCHAR(255) NOT NULL,
    level_of_studies VARCHAR(50) NOT NULL,
    language_of_thesis VARCHAR(50) NOT NULL,
    volume_ects INTEGER NOT NULL CHECK (volume_ects > 0),
    title_estonian TEXT NOT NULL,
    title_english TEXT NOT NULL,
    session_id BIGINT,
    grades_visible BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_thesis_session
    FOREIGN KEY (session_id)
    REFERENCES "session"(id),
    CONSTRAINT fk_thesis_student
    FOREIGN KEY (student_id)
    REFERENCES student(id)
);



CREATE TABLE IF NOT EXISTS "role" (
                                      id BIGSERIAL PRIMARY KEY,
                                      role_name VARCHAR(50) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS "user" (
                                     id BIGSERIAL PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
    second_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE session_user_role (
                                   id BIGSERIAL PRIMARY KEY,

                                   session_id BIGINT NOT NULL,
                                   user_id BIGINT NOT NULL,
                                   role_id BIGINT NOT NULL,

                                   CONSTRAINT fk_sur_session FOREIGN KEY (session_id)
                                       REFERENCES "session"(id),

                                   CONSTRAINT fk_sur_user FOREIGN KEY (user_id)
                                       REFERENCES "user"(id),

                                   CONSTRAINT fk_sur_role FOREIGN KEY (role_id)
                                       REFERENCES role(id),

                                   CONSTRAINT uq_session_user_role UNIQUE (session_id, user_id, role_id)
);

CREATE TABLE thesis_user_role (
                                  id BIGSERIAL PRIMARY KEY,

                                  thesis_id BIGINT NOT NULL,
                                  user_id BIGINT NOT NULL,
                                  role_id BIGINT NOT NULL,

                                  CONSTRAINT fk_tur_thesis FOREIGN KEY (thesis_id)
                                      REFERENCES thesis(id),

                                  CONSTRAINT fk_tur_user FOREIGN KEY (user_id)
                                      REFERENCES "user"(id),

                                  CONSTRAINT fk_tur_role FOREIGN KEY (role_id)
                                      REFERENCES role(id),

                                  CONSTRAINT uq_thesis_user_role UNIQUE (thesis_id, user_id, role_id)
);

CREATE TABLE reviewer_grade (
                        id BIGSERIAL PRIMARY KEY,

                        content_score INTEGER NOT NULL,
                        content_reasoning TEXT,

                        complexity_score INTEGER NOT NULL,
                        complexity_reasoning TEXT,

                        appearance_score INTEGER NOT NULL,
                        appearance_reasoning TEXT,

                        evaluation_summary TEXT,
                        questions TEXT,

                        thesis_id BIGINT,
                        CONSTRAINT fk_reviewer_grade_thesis
                        FOREIGN KEY (thesis_id)
                        REFERENCES thesis(id),

                        user_id BIGINT,
                        CONSTRAINT fk_reviewer_grade_user
                        FOREIGN KEY (user_id)
                        REFERENCES "user"(id),

                        CONSTRAINT uq_user_thesis UNIQUE (user_id, thesis_id)
);

CREATE TABLE committee_member_grade (
                                id BIGSERIAL PRIMARY KEY,

                                content_score INTEGER NOT NULL,


                                complexity_score INTEGER NOT NULL,


                                appearance_score INTEGER NOT NULL,

                                presentation_score INTEGER NOT NULL,
                                visible_to_others BOOLEAN NOT NULL DEFAULT FALSE,

                                thesis_id BIGINT,
                                CONSTRAINT fk_committee_member_grade_thesis
                                    FOREIGN KEY (thesis_id)
                                        REFERENCES thesis(id),

                                user_id BIGINT,
                                CONSTRAINT fk_committee_member_grade_user
                                    FOREIGN KEY (user_id)
                                        REFERENCES "user"(id),

                                CONSTRAINT commitee_member_grade_uq_user_thesis UNIQUE (user_id, thesis_id)
);


CREATE TABLE supervisor_form(
                                id BIGSERIAL PRIMARY KEY,
                                context_of_research TEXT,
                                student_contribution TEXT,
                                strength_of_thesis TEXT,
                                limitation_of_thesis TEXT,
                                cooperation TEXT,
                                additional_comments TEXT,
                                thesis_id BIGINT,
                                CONSTRAINT fk_supervisor_form_thesis
                                    FOREIGN KEY (thesis_id)
                                        REFERENCES thesis(id),

                                user_id BIGINT,
                                CONSTRAINT fk_supervisor_form_user
                                    FOREIGN KEY (user_id)
                                        REFERENCES "user"(id),

                                CONSTRAINT supervisor_form_uq_user_thesis UNIQUE (user_id, thesis_id)
);

CREATE TABLE final_grade(
                                        id BIGSERIAL PRIMARY KEY,

                                        content_score INTEGER NOT NULL,

                                        complexity_score INTEGER NOT NULL,
                                        appearance_score INTEGER NOT NULL,
                                        presentation_score INTEGER NOT NULL,
                                        total_score INTEGER NOT NULL,
                                        letter_grade VARCHAR(1) CHECK (letter_grade IN ('A','B','C','D','E','F')),
                                        thesis_id BIGINT,
                                        CONSTRAINT fk_final_grade_thesis
                                            FOREIGN KEY (thesis_id)
                                                REFERENCES thesis(id),



                                        CONSTRAINT uq_final_grade UNIQUE (thesis_id)
);