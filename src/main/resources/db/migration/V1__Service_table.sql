CREATE TABLE subscription (
                         id SERIAL AUTO_INCREMENT,
                         discipline_id LONG,
                         workout_count INT,
                         end_date date NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         FOREIGN KEY(discipline_id) REFERENCES discipline(id),
                         PRIMARY KEY(id)
);


CREATE TABLE discipline (
                         id SERIAL AUTO_INCREMENT,
                         name varchar(255) NOT NULL,
                         description varchar(255),
                         PRIMARY KEY(id)


);


INSERT INTO subscription ( discipline_id, workout_count, end_date, price)
VALUES
    (1, 2, 23-08-21, 500.0 ),
    (2, 4, 23-05-01, 1500.0),
    (3, 8, 24-06-31, 4500.0),
    (4, 3, 25-03-21, 2500.0),
    (5, 1, 26-02-05, 5100.0);

INSERT INTO discipline ( name, description)
VALUES
    ('Йога', 'Откройте для себя внутреннюю гармонию и укрепите свое тело с помощью наших занятий йогой.'),
    ('Аэробика', 'Разогрейте свое тело и потратьте лишние калории на наших энергичных занятиях по аэробике.'),
    ('Плавание', 'Насладитесь освежающими занятиями в бассейне и улучшите свою выносливость и плавательный стиль.'),
    ('Пилатес', 'Работайте над своей осанкой, гибкостью и силой с помощью занятий по пилатесу.'),
    ('Массаж', 'Расслабьтесь и освежитесь с помощью профессионального массажа, который поможет снять напряжение и улучшить ваше физическое и психическое состояние.');
