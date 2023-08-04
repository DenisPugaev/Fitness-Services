CREATE TABLE disciplines(
                            id bigserial PRIMARY KEY,
                            name VARCHAR(255),
                            description VARCHAR(255)

);

CREATE TABLE subscriptions(
                              id bigserial PRIMARY KEY,
                              discipline_id BIGINT REFERENCES disciplines(id),
                              workout_count INT,
                              days_to_expire INT,
                              price DECIMAL(10, 2)
);

INSERT INTO disciplines (id, name, description)
VALUES
    (1, 'Фитнес', 'Групповая аэробная тренирвка с небольшими весами.'),
    (2, 'Power Lifting', 'Силовая тренировка, ориентированная на базовые упражнения.'),
    (3, 'Тяжелая атлетика ', 'Работа со штангой с ТА упражнениями.'),
    (4, 'Crossfit', 'Широкий спектр силовых и функциональных упражнений с жестким таймингом.'),
    (5, 'Бачата', 'Танцевальное направление. Представляет собой чувственный танец для пар.'),
    (6, 'Йога', 'Дисциплина произрастающая из индийской философии.'),
    (7, 'Стретчинг', 'Развитие гибкости и мобильности суставов.'),
    (8, 'Кикбоксинг', 'Вид единоборств, включающий удары руками и ногами.')
;

INSERT INTO subscriptions ( discipline_id, workout_count,days_to_expire , price)
VALUES
    (1, 2,30, 500.0 ),
    (2, 4,30, 1500.0),
    (3, 8,30, 4500.0),
    (4, 3,30, 2500.0),
    (5, 1,30, 5100.0),
    (6, 1,30, 2400.0),
    (7,4,30,800.0),
    (7,8,30,1400.0),
    (7,12,30,2000.0)
;
