create database invoice_management;
use invoice_management;

CREATE TABLE User (
    userID INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'USER'),
    PRIMARY KEY (userID)
);

CREATE TABLE Product (
    ProductID INT NOT NULL AUTO_INCREMENT,
    ProductName VARCHAR(100) NOT NULL,
    Brand VARCHAR(50) NOT NULL,
    Price DECIMAL(10 , 2 ) NOT NULL,
    Stock INT NOT NULL DEFAULT 0,
    CONSTRAINT check_price_stock CHECK (Price >= 0 AND Stock >= 0),
    PRIMARY KEY (ProductID)
);

CREATE TABLE Customer (
    CustomerID INT NOT NULL AUTO_INCREMENT,
    FullName VARCHAR(100) NOT NULL,
    PhoneNumber VARCHAR(10) NOT NULL,
    Email VARCHAR(100),
    userID INT,
    PRIMARY KEY (CustomerID),
    FOREIGN KEY (userID)
        REFERENCES User (userID)
);

CREATE TABLE Invoice (
    InvoiceID INT NOT NULL AUTO_INCREMENT,
    CustomerID INT NOT NULL,
    Date DATETIME DEFAULT CURRENT_TIMESTAMP (),
    TotalAmount DECIMAL(10 , 2 ) NOT NULL,
    PRIMARY KEY (InvoiceID),
    FOREIGN KEY (CustomerID)
        REFERENCES Customer (CustomerID)
);

CREATE TABLE InvoiceDetail (
    InvoiceDetailID INT NOT NULL AUTO_INCREMENT,
    InvoiceID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    Subtotal DECIMAL(10 , 2 ) NOT NULL,
    PRIMARY KEY (InvoiceDetailID),
    FOREIGN KEY (InvoiceID)
        REFERENCES Invoice (InvoiceID),
    FOREIGN KEY (ProductID)
        REFERENCES Product (ProductID),
    CHECK (Quantity >= 0 AND Subtotal >= 0)
);

/*them dữ liệu bảng Product*/
insert  into Product(ProductName, Brand, Price, Stock) values ('iPhone 16 Pro Max 256GB','Apple',33990000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Samsung Galaxy Z Fold6 12GB 256GB','Samsung',41990000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('OPPO Find X8','OPPO',22990000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Xiaomi Redmi 14C 4GB 128GB','Xiaomi',3190000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Samsung Galaxy A16 5G 8GB 128GB','Samsung',6090000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('iPhone 14 128Gb','Apple',16990000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Nubia Music NFC 4GB 128GB','Nubia',2390000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Xiaomi Redmi Note 13 Pro Plus 5G 8GB 256GB','Xiaomi',9190000,5);

/*Thêm dữ liệu bảng User*/
insert into User(username, password, role) values ('admin','123456','ADMIN'), 
('user','123456','USER'),
('tranvanben','123456','USER'),
('nguyentuongan','123456','USER'),
('buithimy','123456','USER');

/*thêm dữ liệu bảng Customer*/
insert into Customer(FullName, PhoneNumber, Email, userID) values ('Trần Văn Bền','0857811995','tranvanben@gmail.com',3);
insert into Customer(FullName, PhoneNumber, Email, userID) values ('Nguyễn Tường An','0123456789','antuong00@gmail.com',4);
insert into Customer(FullName, PhoneNumber, Email, userID) values ('Bùi Thị Mỹ','0909090988','mymybui@gmail.com',5);

/*Thêm dữ liệu bảng Invoice*/
insert into Invoice(CustomerID, Date, TotalAmount) values (1,'2024-12-01 10:30:00',33990000),
(2,'2024-12-02 15:45:00',19380000),
(3,'2024-12-03 09:20:00',6380000);

/*Thêm dữ liệu bảng InvoiceDetail*/
insert into InvoiceDetail(InvoiceID, ProductID, Quantity, Subtotal) values (1,1,1,33990000.00),
(2,6,1,16990000.00),
(2,7,1,2390000.00),
(3,4,2,6380000);



/*Tạo trigger update lại stock sản phẩm sau khi thêm mới dữ liệu ở bảng InvoiceDetail*/
DELIMITER $$

CREATE TRIGGER update_stock_after_insert
AFTER INSERT ON InvoiceDetail
FOR EACH ROW
BEGIN
    -- Kiểm tra tồn kho có đủ để thực hiện không
    IF (SELECT Stock FROM Product WHERE ProductID = NEW.ProductID) >= NEW.Quantity THEN
        -- Nếu đủ, giảm tồn kho
        UPDATE Product
        SET Stock = Stock - NEW.Quantity
        WHERE ProductID = NEW.ProductID;
    ELSE
        -- Nếu không đủ, báo lỗi
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Not enough stock available for this product';
    END IF;
END $$

DELIMITER ;