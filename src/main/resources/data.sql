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

insert into Authentication(user_name, email, password) values ('shoeb', 'smk@gmail.com','123');
insert into Authentication(user_name, email, password) values ('ahsan', 'mm@gmail.com','345');
insert into AuthorizationU(user_name, is_premium, is_seller) values ('shoeb', 'TRUE','TRUE');
insert into AuthorizationU(user_name, is_premium, is_seller) values ('ahsan', 'TRUE','TRUE');


insert into History(search_history, item) values('teddy bear', 'build a bear buddy');
insert into History(search_history, item) values('diet soda', 'diet coke');
insert into History(search_history, item) values('toilet paper', 'ultra soft toilet paper rolls');