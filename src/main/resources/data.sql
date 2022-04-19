insert into author (id, full_name, bio)
VALUES ('4b8f2019-c44a-4b27-ae3e-9ff38ae52167', 'Эрих Мария Ремарк', 'биография Ремарка'),
       ('10bfec84-5869-48a7-97f1-e02393e20da9', 'Антон Павлович Чехов', 'биография Чехова');

insert into book (id, title, author_id, description, genre, stock)
values ('4dabe23b-4a4d-4afd-95c5-a5b373e83c10', 'Три товарища', '4b8f2019-c44a-4b27-ae3e-9ff38ae52167',
        'Описание книги Три товарища', 'Роман', 12),
       ('b3924f63-b2d3-425e-87f0-6cf90bd8c83c', 'Рассказы', '10bfec84-5869-48a7-97f1-e02393e20da9',
        'Описание книги Рассказы', 'Рассказ', 17),
       ('d70e474f-7288-497e-8827-82be301d933e', 'Ночь в Лиссабоне', '4b8f2019-c44a-4b27-ae3e-9ff38ae52167',
        'Описание книги Ночь в Лиссабоне', 'Роман', 9);

insert into reader (id, full_name, address, contacts)
VALUES ('7676c89d-5a86-4739-a51c-93f5278195e7', 'Зубенко Михаил Петрович', 'ул. Шумиловская 12', '+7(999)222-44-44'),
       ('eb8f91b8-839e-48b0-9951-72b31b0aaf31', 'Зарплатин Пупа Лупович', 'пр. Бухгалтеров 88', '+7(988)333-22-11');

insert into book_order (id, reader_id, book_id, borrowing_date, period)
VALUES ('c07b9834-73a3-458e-ac06-dc225afb24c8', '7676c89d-5a86-4739-a51c-93f5278195e7',
        '4dabe23b-4a4d-4afd-95c5-a5b373e83c10', '2021-03-12', '2 week'),
       ('b79083dc-6fb0-4184-a3d6-2ceb35708eca', '7676c89d-5a86-4739-a51c-93f5278195e7',
        'b3924f63-b2d3-425e-87f0-6cf90bd8c83c', '2021-12-02', '3 week'),
       ('c0f4df88-19a7-44f9-aa5e-b95bec1c0eff', 'eb8f91b8-839e-48b0-9951-72b31b0aaf31',
        'd70e474f-7288-497e-8827-82be301d933e', '2022-03-11', '2 week'),
       ('053aedbf-c28b-4839-b8d3-e4867fad69c8', 'eb8f91b8-839e-48b0-9951-72b31b0aaf31',
        'b3924f63-b2d3-425e-87f0-6cf90bd8c83c', '2022-03-28', '2 week'),
       ('b3fd1ffe-7941-46c1-bf8b-8f55ae926dc7', 'eb8f91b8-839e-48b0-9951-72b31b0aaf31',
        '4dabe23b-4a4d-4afd-95c5-a5b373e83c10', '2022-04-18', '2 week');