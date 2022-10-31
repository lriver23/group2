insert into Cart(Cart_ID , Order_ID, Product_Quantity ) values (111 , 555, 2 );
insert into Cart(Cart_ID , Order_ID, Product_Quantity ) values (222  , 666, 4);
insert into Orders(Order_ID, Cart_ID , User_ID) values (555 , 111 , 'majid');
insert into Orders(Order_ID , Cart_ID , User_ID) values (666 , 222 , 'shoeb');

insert into Authentication(user_name, email, password) values ('shoeb', 'smk@gmail.com','123');
insert into Authentication(user_name, email, password) values ('ahsan', 'mm@gmail.com','345');
insert into AuthorizationU(user_name, is_premium, is_seller) values ('shoeb', 'TRUE','TRUE');
insert into AuthorizationU(user_name, is_premium, is_seller) values ('ahsan', 'TRUE','TRUE');


insert into History(search_history, item) values('teddy bear', 'build a bear buddy');
insert into History(search_history, item) values('diet soda', 'diet coke');
insert into History(search_history, item) values('toilet paper', 'ultra soft toilet paper rolls');


-- Inventory Related Queries

INSERT INTO Stocks (Id, Quantity_Available) VALUES (1, 15);
INSERT INTO Stocks (Id, Quantity_Available) VALUES (2, 32);
INSERT INTO Stocks (Id, Quantity_Available) VALUES (3, 41);
INSERT INTO Stocks (Id, Quantity_Available) VALUES (4, 3);
INSERT INTO Stocks (Id, Quantity_Available) VALUES (5, 111);
INSERT INTO Stocks (Id, Quantity_Available) VALUES (6, 9);

INSERT INTO Sellers (Id, Name, Income) VALUES (1, 'Dice and Games Industires', 6000000);
INSERT INTO Sellers (Id, Name, Income) VALUES (2, 'World Wide Tools', 85);

INSERT INTO Products (Id, Name, Description, Price, Stock_Id, Seller_Id) VALUES (1, 'Deck of Cards', 'A deck of 52 playing cards.', 5.15, 1, 1);
INSERT INTO Products (Id, Name, Description, Price, Stock_Id, Seller_Id) VALUES (2, 'A Set of Dice', '15 ordinary dice for all occasions.', 9.90, 2, 1);
INSERT INTO Products (Id, Name, Description, Price, Stock_Id, Seller_Id) VALUES (3, 'Dos', 'The sequel to everyones favorite card game.', 5.15, 3, 1);
INSERT INTO Products (Id, Name, Description, Price, Stock_Id, Seller_Id) VALUES (4, '80 screws', 'A box of standardized screws.', 2.80, 4, 2);
INSERT INTO Products (Id, Name, Description, Price, Stock_Id, Seller_Id) VALUES (5, 'Deluxe Hammer', 'A hammer with all the bells and wistles.', 85.00, 5, 2);
INSERT INTO Products (Id, Name, Description, Price, Stock_Id, Seller_Id) VALUES (6, 'Carpenters Bench', 'A bench that no carpenter can live without.', 299.99, 6, 2);