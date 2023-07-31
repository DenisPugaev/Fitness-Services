CREATE TABLE service (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         description TEXT
);

INSERT INTO service (title, price, description)
VALUES
    ('Занятие с персональным тренером', 500.0, 'Получите индивидуальное руководство и мотивацию во время тренировки с нашими опытными персональными тренерами.'),
    ('Групповые тренировки', 300.0, 'Присоединитесь к нашим групповым занятиям, чтобы повысить силу и физическую форму.'),
    ('Йога-класс', 350.0, 'Откройте для себя внутреннюю гармонию и укрепите свое тело с помощью наших занятий йогой.'),
    ('Аэробика', 250.0, 'Разогрейте свое тело и потратьте лишние калории на наших энергичных занятиях по аэробике.'),
    ('Плавание', 400.0, 'Насладитесь освежающими занятиями в бассейне и улучшите свою выносливость и плавательный стиль.'),
    ('Занятия с тренажерами', 60.0, 'Используйте нашу современную тренажерную зону для тренировки различных мышц и улучшения общей физической формы.'),
    ('Пилатес', 300.0, 'Работайте над своей осанкой, гибкостью и силой с помощью занятий по пилатесу.'),
    ('Кардиотренировки на тренажерах', 400.0, 'Улучшите сердечно-сосудистую выносливость и сжигайте калории на наших кардиотренировках на тренажерах.'),
    ('Танцевальные классы', 300.0, 'Выразите себя через танец и получите физические преимущества наших занятий по танцам.'),
    ('Массаж', 100.0, 'Расслабьтесь и освежитесь с помощью профессионального массажа, который поможет снять напряжение и улучшить ваше физическое и психическое состояние.');