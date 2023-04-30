# 7 задание: В подключенном MySQL репозитории создать базу данных “Друзья
# человека”

CREATE DATABASE Друзья_Человека;
USE Друзья_Человека;

# 8 Задание: Создать таблицы с иерархией из диаграммы в БД

CREATE TABLE Виды_Животных
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Название_вида VARCHAR(20)
);

INSERT INTO Виды_Животных (Название_вида)
VALUES ('вьючные'),
('домашние');  


CREATE TABLE Вьючные_животные
(
	  Id INT AUTO_INCREMENT PRIMARY KEY,
    Название_вида VARCHAR (20),
    id_Вида INT,
    FOREIGN KEY (id_Вида) REFERENCES Виды_Животных (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Вьючные_животные (Название_вида, id_Вида)
VALUES ('Лошади', 1),
('Ослы', 1),  
('Верблюды', 1); 
    
CREATE TABLE Домашние_животные
(
	  Id INT AUTO_INCREMENT PRIMARY KEY,
    Название_вида VARCHAR (20),
    id_Вида INT,
    FOREIGN KEY (id_Вида) REFERENCES Виды_Животных (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Домашние_животные (Название_вида, id_Вида)
VALUES ('Кошки', 2),
('Собаки', 2),  
('Хомяки', 2); 

# 9 задание Заполнить низкоуровневые таблицы именами(животных), командами
# которые они выполняют и датами рождения

CREATE TABLE кошки 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Имя VARCHAR(20), 
    ДР DATE,
    Комманды VARCHAR(50),
    id_Вида int,
    Foreign KEY (id_Вида) REFERENCES Домашние_животные (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO кошки (Имя, ДР, Комманды, id_Вида)
VALUES ('зевс', '2021-01-08', 'Ешь', 1),
('мия', '2019-03-11', "Мурчи", 1),  
('каспер', '2005-03-08', "Чеши лапки", 1); 

CREATE TABLE собаки 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Имя VARCHAR(20), 
    ДР DATE,
    Комманды VARCHAR(50),
    id_Вида int,
    Foreign KEY (id_Вида) REFERENCES Домашние_животные (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO собаки (Имя, ДР, Комманды, id_Вида)
VALUES ('Марс', '2020-01-08', 'сидеть, лежать, аппорт, защищай', 2),
('Рокки', '2021-01-16', "сидеть, лежать, круг", 2),  
('Белка', '2005-05-01', "сидеть, лежать, фу, дай лапу, стой", 2), 
('Стрелка', '2005-04-16', "сидеть, лежать, жди, охраняй", 2);

CREATE TABLE Хомяки 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Имя VARCHAR(20), 
    ДР DATE,
    Комманды VARCHAR(50),
    id_Вида int,
    Foreign KEY (id_Вида) REFERENCES Домашние_животные (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Хомяки (Имя, ДР, Комманды, id_Вида)
VALUES ('Боб', '2022-11-11', 'Спать', 3),
('Джон', '2020-06-10', "Умри", 3),  
('Какун', '2021-04-12', 'Бегай в колесе', 3), 
('Пискун', '2019-01-10', 'Грызи', 3);

CREATE TABLE лошади 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Имя VARCHAR(20), 
    ДР DATE,
    Комманды VARCHAR(50),
    id_Вида int,
    Foreign KEY (id_Вида) REFERENCES Вьючные_животные (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO лошади (Имя, ДР, Комманды, id_Вида)
VALUES ('Спирит', '2019-03-14', 'галоп, вальс', 1),
('Комета', '2018-04-11', "галоп, поклон, прыжок", 1),  
('Ракета', '2017-01-18', "галоп, дыбы, лечь,", 1), 
('Ержан', '2021-10-14', "галоп, рысь, танец", 1);

CREATE TABLE ослы 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Имя VARCHAR(20), 
    ДР DATE,
    Комманды VARCHAR(50),
    id_Вида int,
    Foreign KEY (id_Вида) REFERENCES Вьючные_животные (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO ослы (Имя, ДР, Комманды, id_Вида)
VALUES ('Силач', '2019-11-10', NULL, 2),
('Мулик', '2022-10-10', "", 2),  
('Ишка', '2020-01-12', "И_А", 2), 
('Ждун', '2020-06-10', 'И-А', 2);

CREATE TABLE верблюды 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Имя VARCHAR(20), 
    ДР DATE,
    Комманды VARCHAR(50),
    id_Вида int,
    Foreign KEY (id_Вида) REFERENCES Вьючные_животные (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO верблюды (Имя, ДР, Комманды, id_Вида)верблюды
VALUES ('Двугорбый', '2020-02-10', '', 3),
('Одногорбый', '2014-01-10', 'Ищи воду', 3),  
('Горбунок', '2011-08-10', '', 3), 
('Горбуша', '2001-06-15', 'Лежать', 3);

#10 задание: Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
# питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу

SET SQL_SAFE_UPDATES = 0;
DELETE FROM верблюды;

SELECT Имя, ДР, Комманды FROM лошади
UNION SELECT  Имя, ДР, Комманды FROM ослы;

#10 Задание: Создать новую таблицу “молодые животные” в которую попадут все
# животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
# до месяца подсчитать возраст животных в новой таблице

CREATE TABLE животные AS 
SELECT * FROM друзья_человека.животные LIMIT 0, 1000;

CREATE TABLE молодые_животные AS
SELECT Имя, ДР, Комманды, id_Вида, TIMESTAMPDIFF(MONTH, ДР, CURDATE()) AS Возраст
FROM животные WHERE ДР BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);

# 11 Задание: Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

SELECT л.Имя, л.ДР, л.Комманды, в.Название_вида, м.Возраст 
FROM лошади л
LEFT JOIN молодые_животные м ON м.Имя = л.Имя
LEFT JOIN вьючные_животные в ON в.Id = л.id_Вида
UNION 
SELECT о.Имя, о.ДР, о.Комманды, в.Название_вида, м.Возраст
FROM ослы о 
LEFT JOIN молодые_животные м ON м.Имя = о.Имя
LEFT JOIN вьючные_животные в ON в.Id = о.id_Вида
UNION
SELECT к.Имя, к.ДР, к.Комманды, д.Название_вида, м.Возраст 
FROM кошки к
LEFT JOIN молодые_животные м ON м.Имя = к.Имя
LEFT JOIN Домашние_животные д ON д.Id = к.id_Вида
UNION
SELECT с.Имя, с.ДР, с.Комманды, д.Название_вида, м.Возраст 
FROM собаки с
LEFT JOIN молодые_животные м ON м.Имя = с.Имя
LEFT JOIN Домашние_животные д ON д.Id = с.id_Вида
UNION
SELECT х.Имя, х.ДР, х.Комманды, д.Название_вида, м.Возраст 
FROM хомяки х
LEFT JOIN молодые_животные м ON м.Имя = х.Имя
LEFT JOIN Домашние_животные д ON д.Id = х.id_Вида;