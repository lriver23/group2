INSERT INTO Products (Name, Description, Price) VALUES ('Deck of Cards', 'A deck of 52 playing cards.', 5.15);
INSERT INTO Products (Name, Description, Price) VALUES ('A Set of Dice', '15 ordinary dice for all occasions.', 9.90);
INSERT INTO Products (Name, Description, Price) VALUES ('Dos', 'The sequel to everyones favorite card game.', 5.15);
INSERT INTO Products (Name, Description, Price) VALUES ('80 screws', 'A box of standardized screws.', 2.80);
INSERT INTO Products (Name, Description, Price) VALUES ('Deluxe Hammer', 'A hammer with all the bells and wistles.', 85.00);
INSERT INTO Products (Name, Description, Price) VALUES ('Carpenters Bench', 'A bench that no carpenter can live without.', 299.99);

INSERT INTO Sellers (Name, Income) VALUES ('Dice and Games Industires', 6000000);
INSERT INTO Sellers (Name, Income) VALUES ('World Wide Tools', 85);

insert into Cart(Cart_ID , Order_ID, Product_Quantity ) values (111 , 555, 2 );
insert into Cart(Cart_ID , Order_ID, Product_Quantity ) values (222  , 666, 4);
insert into Orders(Order_ID, Cart_ID , User_ID) values (555 , 111 , 'majid');
insert into Orders(Order_ID , Cart_ID , User_ID) values (666 , 222 , 'shoeb');

insert into Address(location) values('hyderabad');
--Had to check Map Ids
insert into Authentication(email, password, user_name, address_id) values ('smk@gmail.com', '123','shoeb', 1);
insert into Authentication(email, password, user_name, address_id) values ('mmk@gmail.com', '456','Majid', 1);
insert into AuthorizationU(is_premium, is_seller, user_name) values ('TRUE','TRUE', 1);
insert into AuthorizationU(is_premium, is_seller, user_name) values ('TRUE','TRUE', 2);

insert into Login(is_login, Authentication_Id) values ('TRUE', 1);

insert into History(search_history, item) values('teddy bear', 'build a bear buddy');
insert into History(search_history, item) values('diet soda', 'diet coke');
insert into History(search_history, item) values('toilet paper', 'ultra soft toilet paper rolls');