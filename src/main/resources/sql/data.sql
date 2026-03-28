-- =========================
-- INSERT ROLES
-- =========================
INSERT INTO "role" (role_name) VALUES
                                   ('COMMITTEE_MEMBER'),
                                   ('HEAD_OF_COMMITTEE'),
                                   ('REVIEWER'),
                                   ('SUPERVISOR');

-- =========================
-- INSERT USERS
-- =========================
INSERT INTO "user" (name, second_name, email, password, role_id) VALUES
                                                                     ('Jaan', 'Tamm', 'jaan.tamm@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 1),
                                                                     ('Mari', 'Kask', 'mari.kask@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 1),
                                                                     ('Peeter', 'Saar', 'peeter.saar@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 2),
                                                                         ('Katrin', 'Mägi', 'katrin.magi@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 3),
                                                                     ('Timur', 'Sirazitdinov', 'timur.sirazitdinov@gmail.com', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 1),
                                                                     ('Helena', 'Kuusk', 'helena.kuusk@university.ee', '$2a$10$WXufcyCXcMrs.sOGq/nsIeosdQG6/qJiQHVc0utq8i2KcaEb2MS1u', 4);
-- =========================
-- INSERT THESIS
-- =========================
INSERT INTO thesis (
    student_name,
    supervisor_name,
    curriculum,
    level_of_studies,
    language_of_thesis,
    volume_ects,
    title_estonian,
    title_english
) VALUES
      ('Karl Tamm', 'Dr. Andres Kalda', 'ASDASD', 'Bachelor', 'Estonian', 6,
       'Veebirakenduse arendus mikroteenuste arhitektuuriga',
       'Development of a Web Application Using Microservices Architecture'),

      ('Maria Kask', 'Prof. Liina Vaher', 'AAAA', 'Master', 'English', 30,
       'Masinõppe mudelite optimeerimine suurandmete töötlemisel',
       'Optimization of Machine Learning Models for Big Data Processing'),

      ('Jaan Saar', 'Dr. Peeter Põder', 'A3131', 'Bachelor', 'English', 12,
       'Turvaline autentimine JWT abil Spring Boot rakendustes',
       'Secure Authentication Using JWT in Spring Boot Applications'),

      ('Liis Mägi', 'Prof. Tiina Mets', 'pppd', 'Master', 'Estonian', 24,
       'Pilvepõhise e-kaubanduse platvormi skaleeritavus',
       'Scalability of a Cloud-Based E-Commerce Platform'),

      ('Rasmus Põld', 'Dr. Martin Kuusk', 'djkjj', 'Bachelor', 'Estonian', 6,
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
),

-- Thesis 5
(
    'The research focuses on database performance optimization in PostgreSQL.',
    'The student performed benchmarking and applied optimization techniques.',
    'Good experimental work and practical insights.',
    'Limited coverage of distributed database systems.',
    'Consistent and reliable cooperation.',
    'A well-structured and useful thesis.',
    5,
    6
);