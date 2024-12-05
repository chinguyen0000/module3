create database invoice_management;
use invoice_management;

CREATE TABLE Product (
    ProductID INT NOT NULL AUTO_INCREMENT,
    ProductName VARCHAR(100) NOT NULL,
    Brand VARCHAR(50) NOT NULL,
    Price DECIMAL(10 , 2) NOT NULL,
    Stock INT NOT NULL DEFAULT 0,
    CONSTRAINT check_price_stock CHECK (Price >= 0 AND Stock >= 0),
    PRIMARY KEY (ProductID)
);

CREATE TABLE Customer (
    CustomerID INT NOT NULL AUTO_INCREMENT,
    FullName VARCHAR(100) NOT NULL,
    PhoneNumber VARCHAR(10) NOT NULL,
    Email VARCHAR(100),
    PRIMARY KEY (CustomerID)
);

CREATE TABLE Invoice (
    InvoiceID INT NOT NULL AUTO_INCREMENT,
    CustomerID INT NOT NULL,
    Date DATETIME DEFAULT CURRENT_TIMESTAMP(),
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
insert  into Product(ProductName, Brand, Price) values ('iPhone 16 Pro Max 256GB','Apple',33990000);
insert  into Product(ProductName, Brand, Price, Stock) values ('Samsung Galaxy Z Fold6 12GB 256GB','Samsung',41990000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('OPPO Find X8','OPPO',22990000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Xiaomi Redmi 14C 4GB 128GB','Xiaomi',3190000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Samsung Galaxy A16 5G 8GB 128GB','Samsung',6090000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('iPhone 14 128Gb','Apple',16990000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Nubia Music NFC 4GB 128GB','Nubia',2390000,5);
insert  into Product(ProductName, Brand, Price, Stock) values ('Xiaomi Redmi Note 13 Pro Plus 5G 8GB 256GB','Xiaomi',9190000,5);

/*thêm dữ liệu bảng Customer*/
insert into Customer(FullName, PhoneNumber, Email) values ('Trần Văn Bền','0857811995','tranvanben@gmail.com');
insert into Customer(FullName, PhoneNumber, Email) values ('Nguyễn Tường An','0123456789','antuong00@gmail.com');
insert into Customer(FullName, PhoneNumber, Email) values ('Bùi Thị Mỹ','0909090988','mymybui@gmail.com');


