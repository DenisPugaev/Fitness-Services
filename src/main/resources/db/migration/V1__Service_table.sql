CREATE TABLE subscription (
                         id SERIAL AUTO_INCREMENT,
                         discipline_id INT8,
                         workout_count INT,
                         end_date DATE NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         PRIMARY KEY(id)
);


INSERT INTO subscription ( discipline_id, workout_count, end_date, price)
VALUES
    (1, 2, DATE '23-08-21', 500.0 ),
    (2, 4,DATE '23-08-21', 1500.0),
    (3, 8,DATE '23-08-21', 4500.0),
    (4, 3,DATE '23-08-21', 2500.0),
    (5, 1,DATE '23-08-21', 5100.0);

