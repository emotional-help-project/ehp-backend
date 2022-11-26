INSERT INTO users (age, email, first_name, gender, last_name, password, role)
VALUES (25, 'test@email', 'Ron', 'MALE', 'Smith', 'pass123', 'USER');

INSERT INTO test_types (title)
VALUES ('Health');
INSERT INTO tests (title, test_type_id)
VALUES ('Depression test', 1);

INSERT INTO questions (title, multiple_answers, number, test_id)
VALUES ('Do you suffer from extreme mood changes (e.g. going from extremely "happy" to extremely "sad")?',
        'false', 1, 1);
INSERT INTO questions (title, multiple_answers, number, test_id)
VALUES ('Has anyone in your family ever been diagnosed with Bipolar Disorder?',
        'false', 2, 1);

INSERT INTO answers (title, score, question_id)
VALUES ('Yes', 3, 1);
INSERT INTO answers (title, score, question_id)
VALUES ('Sometimes', 2, 1);
INSERT INTO answers (title, score, question_id)
VALUES ('No', 1, 1);
INSERT INTO answers (title, score, question_id)
VALUES ('Yes', 3, 2);
INSERT INTO answers (title, score, question_id)
VALUES ('Sometimes', 2, 2);
INSERT INTO answers (title, score, question_id)
VALUES ('No', 1, 2);

INSERT INTO advice (title, score_from, score_to, test_id)
VALUES ('According to your responses, you seem to show some symptoms of Bipolar Depression.',
        0, 70, 1);

INSERT INTO links (title, link)
VALUES ('Depression Program', 'https://thiswayup.org.au/programs/depression-program/');
INSERT INTO links (title, link)
VALUES ('Overcoming depression: How psychologists help with depressive disorders',
        'https://www.apa.org/topics/depression/overcoming');

INSERT INTO advice_links (advice_id, link_id)
VALUES (1, 1),
       (1, 2);