
DROP TABLE DATA;
DROP TABLE User;
DROP TABLE Foods;
DROP TABLE Medi;
DROP TABLE Wepon;


CREATE TABLE User(
	Id INTEGER auto_increment,
	Name VARCHAR(128),
	Pass VARCHAR(128),
	PRIMARY KEY (Id)
);

CREATE TABLE DATA(
	Id INTEGER,
	Exp VARCHAR(128),
	FOREIGN KEY (Id) REFERENCES User (Id)
);

CREATE TABLE Foods(
	Id INTEGER,
	Name VARCHAR(128),
	Signals VARCHAR(128)
);

CREATE TABLE Medi(
	Id INTEGER,
	Name VARCHAR(128),
	Signals VARCHAR(128)
);

CREATE TABLE Wepon(
	Id INTEGER,
	Name VARCHAR(128),
	Signals VARCHAR(128)
);
INSERT INTO User(Id,Name,Pass) VALUES(0,'ADMINISTRATER741219T','PASSUNDEFINED');
INSERT INTO Foods(Id,Name,Signals) VALUES(0,'water','・ーー ・ー ー ・ ・ー・');
INSERT INTO Foods(Id,Name,Signals) VALUES(1,'ration','・ー・ ・ー ー ・・ ーーー ー・');
INSERT INTO Foods(Id,Name,Signals) VALUES(2,'meat','ーー ・ ・ー ー');
INSERT INTO Foods(Id,Name,Signals) VALUES(3,'fish','・・ー・ ・・ ・・・ ・・・・');
INSERT INTO Foods(Id,Name,Signals) VALUES(4,'strawberry','・・・ ー ・ー・ ・ー ・ーー ー・・・ ・ ・ー・ ・ー・ ー・ーー');
INSERT INTO Foods(Id,Name,Signals) VALUES(5,'juice','・ーーー ・・ー ・・ ー・ー・ ・');
INSERT INTO Foods(Id,Name,Signals) VALUES(6,'bread','ー・・・ ・ー・ ・ ・ー ー・・');
INSERT INTO Foods(Id,Name,Signals) VALUES(7,'rice','・ー・ ・・ ー・ー・ ・');
INSERT INTO Foods(Id,Name,Signals) VALUES(8,'pasta','・ーー・ ・ー ・・・ ー ・ー');
INSERT INTO Foods(Id,Name,Signals) VALUES(9,'sugar','・・・ ・・ー ーー・ ・ー ・ー・');
INSERT INTO Foods(Id,Name,Signals) VALUES(10,'solt','・・・ ーーー ・ー・・ ー');
INSERT INTO Foods(Id,Name,Signals) VALUES(11,'pepper','・ーー・ ・ ・ーー・ ・ーー・ ・ ・ー・');
INSERT INTO Foods(Id,Name,Signals) VALUES(12,'corn','ー・ー・ ーーー ・ー・ ー・');
INSERT INTO Foods(Id,Name,Signals) VALUES(13,'orange','ーーー ・ー・ ・ー ー・ ーー・ ・');
INSERT INTO Foods(Id,Name,Signals) VALUES(14,'peach','・ーー ・ ・・ ーー・ー ・・・・');

INSERT INTO Medi(Id,Name,Signals) VALUES(0,'gauze','1100100111000');
INSERT INTO Medi(Id,Name,Signals) VALUES(1,'knife','101100000100');
INSERT INTO Medi(Id,Name,Signals) VALUES(2,'bandage','10000110100011100');
INSERT INTO Medi(Id,Name,Signals) VALUES(3,'bandaid','100001101000100100');
INSERT INTO Medi(Id,Name,Signals) VALUES(4,'towel','111101100100');
INSERT INTO Medi(Id,Name,Signals) VALUES(5,'pharmaceutical','01100000010101101101000011001010010100');
INSERT INTO Medi(Id,Name,Signals) VALUES(6,'eyedrops','0101101000101110110000');

INSERT INTO Wepon(Id,Name,Signals) VALUES(0,'handgun','0000011010011000110');
INSERT INTO Wepon(Id,Name,Signals) VALUES(1,'pistol','01100000011110100');
INSERT INTO Wepon(Id,Name,Signals) VALUES(2,'rifle','01000001001000');
INSERT INTO Wepon(Id,Name,Signals) VALUES(3,'shotgun','0000000111111000110');
INSERT INTO Wepon(Id,Name,Signals) VALUES(4,'machine','11011010000000100');
INSERT INTO Wepon(Id,Name,Signals) VALUES(5,'grenade','110010010011000');
INSERT INTO Wepon(Id,Name,Signals) VALUES(6,'sword','000011111010100');


