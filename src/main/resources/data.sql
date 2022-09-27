
insert into Cart(Cart_ID , Order_ID, Product_Quantity ) values (111 , 555, 2 );
insert into Cart(Cart_ID , Order_ID, Product_Quantity ) values (222  , 666, 4);
insert into Orders(Order_ID, Cart_ID , User_ID) values (555 , 111 , 'majid');
insert into Orders(Order_ID , Cart_ID , User_ID) values (666 , 222 , 'shoeb');
insert into Authentication(user_name, email, password) values ('lll', 'smk@gmail.com','123');
insert into Authentication(user_name, email, password) values ('mm', 'mm@gmail.com','345');
insert into AuthorizationU(user_name, is_premium, is_seller) values ('hi', 'TRUE','TRUE');
insert into AuthorizationU(user_name, is_premium, is_seller) values ('jo', 'TRUE','TRUE');

