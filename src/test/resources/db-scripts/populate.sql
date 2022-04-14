insert into author (id, full_name, bio)
VALUES ('4b8f2019-c44a-4b27-ae3e-9ff38ae52167', 'Эрих Мария Ремарк', 'bio'),
       ('10bfec84-5869-48a7-97f1-e02393e20da9', 'Антон Павлович Чехов', 'биография');

insert into book (id, title, author_id, description, genre, stock)
values ('4dabe23b-4a4d-4afd-95c5-a5b373e83c10', 'Три товарища', '4b8f2019-c44a-4b27-ae3e-9ff38ae52167',
        'Описание книги Три товарища', 'Роман', 12),
       ('b3924f63-b2d3-425e-87f0-6cf90bd8c83c', 'Рассказы', '10bfec84-5869-48a7-97f1-e02393e20da9',
        'Описание книги Рассказы', 'Рассказ', 17);
