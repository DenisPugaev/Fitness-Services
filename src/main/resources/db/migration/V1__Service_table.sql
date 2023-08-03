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
    (1, 'Discipline 1', 'Description 1'),
    (2, 'Discipline 2', 'Description 2'),
    (3, 'Discipline 3', 'Description 3'),
    (4, 'Discipline 4', 'Description 4'),
    (5, 'Discipline 5', 'Description 5');

INSERT INTO subscriptions ( discipline_id, workout_count, end_date, price)
VALUES
    (1, 2,30, 500.0 ),
    (2, 4,30, 1500.0),
    (3, 8,30, 4500.0),
    (4, 3,30, 2500.0),
    (5, 1,30, 5100.0);
