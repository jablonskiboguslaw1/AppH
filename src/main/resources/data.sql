SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE product;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO `product`
(`id`,`name`,`description`,`created`,`updated`,`price`,`type`)
VALUES
('1', 'Rower 01', 'To jest opis produktu', '2020-07-22 13:29:39', '2020-07-22 13:29:39', '19.99', 'REAL'),
('2', 'Rower 02', 'To jest opis produktu', '2020-07-22 13:29:39', '2020-07-22 13:29:39', '19.99', 'REAL'),
('3', 'Rower 03', 'To jest opis produktu', '2020-07-22 13:30:06', '2020-07-22 13:30:06', '19.99', 'REAL'),
('4', 'Rower 04', 'To jest opis produktu', '2020-07-22 13:31:45', '2020-07-22 13:31:45', '19.99', 'REAL'),
('5', 'Rower 05', 'To jest opis produktu', '2020-07-22 13:31:54', '2020-07-22 13:31:54', '19.99', 'REAL');

INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(1,1,'Treść opinii 1',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(2,1,'Treść opinii 2',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(3,1,'Treść opinii 3',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(4,1,'Treść opinii 4',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(5,1,'Treść opinii 5',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(6,2,'Treść opinii 1',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(7,2,'Treść opinii 2',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(8,2,'Treść opinii 3',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(9,2,'Treść opinii 4',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(10,2,'Treść opinii 5',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(11,3,'Treść opinii 1',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(12,3,'Treść opinii 2',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(13,3,'Treść opinii 3',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(14,3,'Treść opinii 4',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(15,3,'Treść opinii 5',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(16,4,'Treść opinii 1',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(17,4,'Treść opinii 2',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(18,4,'Treść opinii 3',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(19,4,'Treść opinii 4',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(20,4,'Treść opinii 5',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(21,5,'Treść opinii 1',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(22,5,'Treść opinii 2',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(23,5,'Treść opinii 3',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(24,5,'Treść opinii 4',5);
INSERT INTO `review`(`id`,`product_id`,`content`,`rating`)VALUES(25,5,'Treść opinii 5',5);