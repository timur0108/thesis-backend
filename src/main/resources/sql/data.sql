
INSERT INTO "session" (start_date, end_date) VALUES
                                                 ('2024-01-01', '2024-06-30'),  -- session_id = 1
                                                 ('2024-09-01', '2024-12-20');  -- session_id = 2



-- =========================
-- INSERT ROLES
-- =========================
INSERT INTO "role" (role_name) VALUES
                                   ('COMMITTEE_MEMBER'),
                                   ('HEAD_OF_COMMITTEE'),
                                   ('REVIEWER'),
                                   ('SUPERVISOR'),
                                   ('ADMIN'),
                                   ('CO-SUPERVISOR');


INSERT INTO student (name, second_name, email, student_number) VALUES
                                                                   ('Karl', 'Tamm', 'karl.tamm@student.ee', 'S1001'),
                                                                   ('Maria', 'Kask', 'maria.kask@student.ee', 'S1002'),
                                                                   ('Jaan', 'Saar', 'jaan.saar@student.ee', 'S1003'),
                                                                   ('Liis', 'Mägi', 'liis.magi@student.ee', 'S1004'),
                                                                   ('Rasmus', 'Põld', 'rasmus.pold@student.ee', 'S1005'),
                                                                   ('Helen', 'Kask', 'helen.kask@student.ee', 'S1006'),
                                                                   ('Marko', 'Vaher', 'marko.vaher@student.ee', 'S1007'),
                                                                   ('Sandra', 'Kuusk', 'sandra.kuusk@student.ee', 'S1008'),
                                                                   ('Oliver', 'Põder', 'oliver.poder@student.ee', 'S1009'),
                                                                   ('Grete', 'Saar', 'grete.saar@student.ee', 'S1010');

-- =========================
-- INSERT USERS
-- =========================
INSERT INTO "user" (name, second_name, email, password) VALUES
                                                                     ('Jaan', 'Tamm', 'jaan.tamm@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                     ('Mari', 'Kask', 'mari.kask@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                     ('Peeter', 'Saar', 'peeter.saar@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                         ('Katrin', 'Mägi', 'katrin.magi@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                     ('Timur', 'Sirazitdinov', 'timur.sirazitdinov@gmail.com', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                     ('Helena', 'Kuusk', 'helena.kuusk@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                     ('Andres', 'Luts', 'andres.luts@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                     ('Kadri', 'Rebane', 'kadri.rebane@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                     ('Marek', 'Ilves', 'marek.ilves@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u'),
                                                                     ('Eva', 'Laur', 'eva.laur@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u');
-- =========================
-- INSERT THESIS
-- =========================
INSERT INTO thesis (
    student_id,
    curriculum,
    level_of_studies,
    language_of_thesis,
    volume_ects,
    title_estonian,
    title_english,
    session_id
) VALUES

-- SESSION 1
(1, 'ASDASD', 'Bachelor', 'Estonian', 6,
 'Veebirakenduse arendus mikroteenuste arhitektuuriga',
 'Development of a Web Application Using Microservices Architecture',
 1),

(2, 'AAAA', 'Master', 'English', 30,
 'Masinõppe mudelite optimeerimine suurandmete töötlemisel',
 'Optimization of Machine Learning Models for Big Data Processing',
 1),

(3, 'A3131', 'Bachelor', 'English', 12,
 'Turvaline autentimine JWT abil Spring Boot rakendustes',
 'Secure Authentication Using JWT in Spring Boot Applications',
 1),

(4, 'PPPD', 'Master', 'Estonian', 24,
 'Pilvepõhise e-kaubanduse platvormi skaleeritavus',
 'Scalability of a Cloud-Based E-Commerce Platform',
 1),

(5, 'DJKJJ', 'Bachelor', 'Estonian', 6,
 'Andmebaasi jõudluse analüüs ja optimeerimine PostgreSQL-is',
 'Database Performance Analysis and Optimization in PostgreSQL',
 1),

-- SESSION 2
(6, 'IT-2024', 'Bachelor', 'Estonian', 6,
 'Reaalajas andmetöötlus Node.js abil',
 'Real-Time Data Processing Using Node.js',
 2),

(7, 'CS-MA', 'Master', 'English', 30,
 'Neuraalvõrkude tõlgendatavus meditsiinis',
 'Interpretability of Neural Networks in Medical Diagnostics',
 2),

(8, 'INF-BA', 'Bachelor', 'English', 12,
 'Andmeturve pilvepõhistes süsteemides',
 'Data Security in Cloud-Based Systems',
 2),

(9, 'CS-MA', 'Master', 'Estonian', 24,
 'Masinõppe mudelite efektiivne treenimine GPU-del',
 'Efficient Training of Machine Learning Models on GPUs',
 2),

(10, 'IT-2024', 'Bachelor', 'English', 6,
 'Veebirakenduste testimise automatiseerimine',
 'Automated Testing of Web Applications',
 2);


-- SESSION 1
INSERT INTO session_user_role (session_id, user_id, role_id) VALUES
                                                                 (1, 1, 1), -- Jaan → COMMITTEE_MEMBER
                                                                 (1, 2, 1), -- Mari → COMMITTEE_MEMBER
                                                                 (1, 3, 2); -- Peeter → HEAD_OF_COMMITTEE

-- SESSION 2
INSERT INTO session_user_role (session_id, user_id, role_id) VALUES
                                                                 (2, 2, 1),
                                                                 (2, 6, 1),
                                                                 (2, 3, 2);


-- Thesis 1
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (1, 4, 3), -- Katrin → REVIEWER
                                                               (1, 6, 4); -- Helena → SUPERVISOR

-- Thesis 2
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (2, 4, 3),
                                                               (2, 6, 4);

-- Thesis 3
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (3, 4, 3),
                                                               (3, 6, 4);

-- Thesis 4
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (4, 4, 3),
                                                               (4, 6, 4);

-- Thesis 5
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (5, 4, 3),
                                                               (5, 6, 4);

-- Assign reviewer and supervisor for SESSION 2 theses
-- Thesis 6
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (6, 4, 3),  -- Katrin → REVIEWER
                                                               (6, 6, 4);  -- Helena → SUPERVISOR

-- Thesis 7
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (7, 4, 3),
                                                               (7, 6, 4);

-- Thesis 8
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (8, 4, 3),
                                                               (8, 6, 4);

-- Thesis 9
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (9, 4, 3),
                                                               (9, 6, 4);

-- Thesis 10
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (10, 4, 3),
                                                               (10, 6, 4);

-- Thesis 1
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (1, 7, 6),  -- Andres → co-supervisor
                                                               (1, 8, 6);

-- Thesis 2
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
                                                               (2, 9, 6),  -- Marek
                                                               (2, 10, 6);

-- Thesis 3
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
    (3, 7, 6);

-- Thesis 4
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
    (4, 8, 6);

-- Thesis 5
INSERT INTO thesis_user_role (thesis_id, user_id, role_id) VALUES
    (5, 9, 6);

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

INSERT INTO supervisor_form (
    context_of_research,
    student_contribution,
    strength_of_thesis,
    limitation_of_thesis,
    cooperation,
    additional_comments,
    thesis_id,
    user_id
) VALUES

-- Thesis 1
(
    'The research focuses on building a scalable web application using microservices architecture.',
    'The student independently implemented multiple services and handled communication between them.',
    'Strong practical implementation and clear architectural decisions.',
    'Limited discussion on distributed transactions and fault tolerance.',
    'Very good cooperation, student was proactive and responsive.',
    'Overall a strong thesis with good real-world applicability.',
    1,
    6
),

-- Thesis 2
(
    'The thesis explores optimization techniques for machine learning models in big data environments.',
    'The student conducted experiments and compared multiple optimization strategies.',
    'Excellent analytical work and strong theoretical foundation.',
    'Could include more real-world datasets for validation.',
    'Smooth and professional cooperation throughout the process.',
    'A very well-executed master-level thesis.',
    2,
    6
),

-- Thesis 3
(
    'The research investigates secure authentication mechanisms using JWT in Spring Boot.',
    'The student developed a secure authentication system and implemented token management.',
    'Clear implementation and strong understanding of security concepts.',
    'Limited exploration of alternative authentication methods.',
    'Good communication and timely progress updates.',
    'A solid and practical thesis.',
    3,
    6
),

-- Thesis 4
(
    'The thesis analyzes scalability challenges in cloud-based e-commerce platforms.',
    'The student designed and tested scalable infrastructure solutions.',
    'Strong system design and performance evaluation.',
    'Could improve documentation clarity in some sections.',
    'Very good collaboration and initiative.',
    'Demonstrates strong engineering skills.',
    4,
    6
);