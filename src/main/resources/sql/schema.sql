DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS "role";
DROP TABLE IF EXISTS reviewer_grade;
DROP TABLE IF EXISTS thesis;

CREATE TABLE IF NOT EXISTS thesis (
    id BIGSERIAL PRIMARY KEY,
    student_name VARCHAR(255) NOT NULL,
    supervisor_name VARCHAR(255) NOT NULL,
    level_of_studies VARCHAR(50) NOT NULL,
    language_of_thesis VARCHAR(50) NOT NULL,
    volume_ects INTEGER NOT NULL CHECK (volume_ects > 0),
    title_estonian TEXT NOT NULL,
    title_english TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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
    role_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_role
    FOREIGN KEY (role_id)
    REFERENCES "role"(id)
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
                        REFERENCES thesis(id)
);
