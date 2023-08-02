CREATE TABLE discipline (
                            id INT AUTO_INCREMENT,
                            name VARCHAR(255),
                            description VARCHAR(255),
                            PRIMARY KEY(id)
);

CREATE TABLE subscription (
                              id INT AUTO_INCREMENT,
                              discipline_id INT,
                              workout_count INT,
                              end_date DATE,
                              price DECIMAL(10, 2) NOT NULL,
                              PRIMARY KEY(id),
                              FOREIGN KEY (discipline_id) REFERENCES discipline(id)
);

INSERT INTO discipline (id, name, description)
VALUES
    (1, 'Discipline 1', 'Description 1'),
    (2, 'Discipline 2', 'Description 2'),
    (3, 'Discipline 3', 'Description 3'),
    (4, 'Discipline 4', 'Description 4'),
    (5, 'Discipline 5', 'Description 5');

INSERT INTO subscription ( discipline_id, workout_count, end_date, price)
VALUES
    (1, 2, DATE '2021-08-23', 500.0 ),
    (2, 4, DATE '2021-08-23', 1500.0),
    (3, 8, DATE '2021-08-23', 4500.0),
    (4, 3, DATE '2021-08-23', 2500.0),
    (5, 1, DATE '2021-08-23', 5100.0);
