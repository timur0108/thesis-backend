-- =========================
-- INSERT ROLES
-- =========================
INSERT INTO "role" (role_name) VALUES
                                   ('COMMITTEE_MEMBER'),
                                   ('HEAD_OF_COMMITTEE'),
                                   ('REVIEWER');

-- =========================
-- INSERT USERS
-- =========================
INSERT INTO "user" (name, second_name, email, password, role_id) VALUES
                                                                     ('Jaan', 'Tamm', 'jaan.tamm@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 1),
                                                                     ('Mari', 'Kask', 'mari.kask@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 1),
                                                                     ('Peeter', 'Saar', 'peeter.saar@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 2),
                                                                     ('Katrin', 'Mägi', 'katrin.magi@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 3),
                                                                     ('Timur', 'Sirazitdinov', 'timur.sirazitdinov@gmail.com', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 1);

-- =========================
-- INSERT THESIS
-- =========================
INSERT INTO thesis (
    student_name,
    supervisor_name,
    level_of_studies,
    language_of_thesis,
    volume_ects,
    title_estonian,
    title_english
) VALUES
      ('Karl Tamm', 'Dr. Andres Kalda', 'Bachelor', 'Estonian', 6,
       'Veebirakenduse arendus mikroteenuste arhitektuuriga',
       'Development of a Web Application Using Microservices Architecture'),

      ('Maria Kask', 'Prof. Liina Vaher', 'Master', 'English', 30,
       'Masinõppe mudelite optimeerimine suurandmete töötlemisel',
       'Optimization of Machine Learning Models for Big Data Processing'),

      ('Jaan Saar', 'Dr. Peeter Põder', 'Bachelor', 'English', 12,
       'Turvaline autentimine JWT abil Spring Boot rakendustes',
       'Secure Authentication Using JWT in Spring Boot Applications'),

      ('Liis Mägi', 'Prof. Tiina Mets', 'Master', 'Estonian', 24,
       'Pilvepõhise e-kaubanduse platvormi skaleeritavus',
       'Scalability of a Cloud-Based E-Commerce Platform'),

      ('Rasmus Põld', 'Dr. Martin Kuusk', 'Bachelor', 'Estonian', 6,
       'Andmebaasi jõudluse analüüs ja optimeerimine PostgreSQL-is',
       'Database Performance Analysis and Optimization in PostgreSQL');

INSERT INTO reviewer_grade (
    content_score,
    content_reasoning,
    complexity_score,
    complexity_reasoning,
    appearance_score,
    appearance_reasoning,
    evaluation_summary,
    questions,
    thesis_id,
    user_id
) VALUES (
             4,
             'The thesis demonstrates a solid understanding of microservices architecture and web application design.',
             5,
             'The system design includes multiple services and demonstrates good separation of concerns.',
             4,
             'The document is well structured with minor formatting inconsistencies.',
             'Overall, the thesis is well written and demonstrates strong practical skills in modern web development.',
             'How would the system handle distributed transactions between services?',
             1,
             4
         );

INSERT INTO committee_member_grade (
    content_score,
    complexity_score,
    appearance_score,
    presentation_score,
    thesis_id,
    user_id,
    visible_to_others
) VALUES (
             4,
             4,
             5,
             4,
             1,
             1,
          true
         );


INSERT INTO committee_member_grade (
    content_score,
    complexity_score,
    appearance_score,
    presentation_score,
    thesis_id,
    user_id,
    visible_to_others
) VALUES (
             5,
             4,
             4,
             5,
             1,
             2,
          true
         );