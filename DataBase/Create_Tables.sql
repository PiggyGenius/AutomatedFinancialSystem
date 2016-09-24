CREATE TABLE Client(
clientId INTEGER AUTO_INCREMENT PRIMARY KEY,
lastName VARCHAR(64) NOT NULL,
firstName VARCHAR(64) NOT NULL,
emailAddress VARCHAR(128) NOT NULL,
password VARCHAR(256) NOT NULL,
privileges ENUM ('ADMIN','USER','PROUSER') NOT NULL,
lastLogin DATE NOT NULL
);

CREATE TABLE ClientData(
clientId INTEGER PRIMARY KEY,
sellingValue FLOAT(10,5) NOT NULL,
cashFlow FLOAT(10,5) NOT NULL,
investMoney FLOAT(10,5) NOT NULL,
investRate FLOAT(10,5) NOT NULL,
moneyWonOverall FLOAT(10,5) NOT NULL,
netMoneyWonOverall FLOAT(10,5) NOT NULL,
capitalAmount FLOAT(10,5) NOT NULL,
netCapitalAmount FLOAT(10,5) NOT NULL,
debtAmount FLOAT(10,5) NOT NULL,
safeIncome FLOAT(10,5) NOT NULL,
limitedTime FLOAT(10,5) NOT NULL,
wayOfLife BOOLEAN NOT NULL,
riskRate FLOAT(10,5) NOT NULL,
FOREIGN KEY(clientId) REFERENCES Client(clientId)
);

CREATE TABLE Groups(
groupId INTEGER AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(128) NOT NULL,
totalMembers INTEGER NOT NULL,
creationDate DATE NOT NULL,
creator INTEGER NOT NULL,
sellingValue FLOAT(10,5) NOT NULL,
cashFlow FLOAT(10,5) NOT NULL,
investMoney FLOAT(10,5) NOT NULL,
investRate FLOAT(10,5) NOT NULL,
moneyWonOverall FLOAT(10,5) NOT NULL,
netMoneyWonOverall FLOAT(10,5) NOT NULL,
riskRate FLOAT(10,5) NOT NULL,
FOREIGN KEY(creator) REFERENCES Client(clientId)
);

CREATE TABLE GroupMember(
groupId INTEGER NOT NULL,
clientId INTEGER NOT NULL,
joiningDate DATE NOT NULL,
investedMoney FLOAT(10,5) NOT NULL,
PRIMARY KEY(groupId,clientId),
FOREIGN KEY(groupId) REFERENCES Groups(groupId),
FOREIGN KEY(clientId) REFERENCES Client(clientId)
);

CREATE TABLE ClientStock(
transactionId INTEGER PRIMARY KEY,
clientId INTEGER NOT NULL,
ISIN CHAR(12) NOT NULL,
quantity INTEGER NOT NULL,
buyingDate DATE NOT NULL,
buyingPrice FLOAT(10,5) NOT NULL,
FOREIGN KEY(ISIN) REFERENCES Stock(ISIN),
FOREIGN KEY(clientId) REFERENCES Client(clientId),
FOREIGN KEY(transactionId) REFERENCES ClientStocksTransaction(transactionId)
);

CREATE TABLE ClientTracker(
transactionId INTEGER PRIMARY KEY,
clientId INTEGER NOT NULL,
ISIN CHAR(12) NOT NULL,
quantity INTEGER NOT NULL,
buyingPrice FLOAT(10,5) NOT NULL,
buyingDate DATE NOT NULL,
underlyingISIN CHAR(12) NOT NULL,
type ENUM ('Call','Put') NOT NULL,
exercicePrice FLOAT(10,5) NOT NULL,
FOREIGN KEY(clientId) REFERENCES Client(clientId),
FOREIGN KEY(underlyingISIN) REFERENCES Stock(ISIN),
FOREIGN KEY(ISIN) REFERENCES Tracker(TISIN),
FOREIGN KEY(transactionId) REFERENCES ClientTrackersTransaction(transactionId)
);

CREATE TABLE GroupTracker(
transactionId INTEGER PRIMARY KEY,
groupId INTEGER NOT NULL,
ISIN CHAR(12) NOT NULL,
quantity INTEGER NOT NULL,
buyingPrice FLOAT(10,5) NOT NULL,
buyingDate DATE NOT NULL,
underlyingISIN CHAR(12) NOT NULL,
type ENUM ('Call','Put') NOT NULL,
exercicePrice FLOAT(10,5) NOT NULL,
FOREIGN KEY(groupId) REFERENCES Groups(groupId),
FOREIGN KEY(underlyingISIN) REFERENCES Stock(ISIN),
FOREIGN KEY(ISIN) REFERENCES Tracker(TISIN),
FOREIGN KEY(transactionId) REFERENCES GroupTrackersTransaction(transactionId)
);

CREATE TABLE GroupStock(
transactionId INTEGER PRIMARY KEY,
groupId INTEGER NOT NULL,
ISIN CHAR(12) NOT NULL,
quantity INTEGER NOT NULL,
buyingDate DATE NOT NULL,
buyingPrice FLOAT(10,5) NOT NULL,
FOREIGN KEY(ISIN) REFERENCES Stock(ISIN),
FOREIGN KEY(groupId) REFERENCES Groups(groupId),
FOREIGN KEY(transactionId) REFERENCES GroupStocksTransaction(transactionId)
);

CREATE TABLE Stock(
ISIN CHAR(12) PRIMARY KEY,
SName VARCHAR(64) NOT NULL,
SPrice FLOAT(10,5) NOT NULL,
SVolume INTEGER NOT NULL,
SHigh FLOAT(10,5) NOT NULL,
SLow FLOAT(10,5) NOT NULL,
SOpening FLOAT(10,5) NOT NULL,
SLastClosing FLOAT(10,5) NOT NULL
);

CREATE TABLE Tracker(
TISIN CHAR(12) PRIMARY KEY,
TName VARCHAR(64) NOT NULL,
TUnderlyingISIN CHAR(12) NOT NULL,
TPrice FLOAT(10,5) NOT NULL,
TRateYesterdayPriceActualPrice FLOAT(10,5) NOT NULL,
TVolume INTEGER NOT NULL,
THigh FLOAT(10,5) NOT NULL,
TLow FLOAT(10,5) NOT NULL,
TOpening FLOAT(10,5) NOT NULL,
TLastClosing FLOAT(10,5) NOT NULL,
FOREIGN KEY(TunderlyingISIN) REFERENCES Stock(ISIN)
);

CREATE TABLE Warrant(
WISIN CHAR(12) PRIMARY KEY,
WName VARCHAR(64) NOT NULL,
WunderlyingISIN CHAR(12) NOT NULL,
WPrice FLOAT(10,5) NOT NULL,
WVolume INTEGER NOT NULL,
WHigh FLOAT(10,5) NOT NULL,
WLow FLOAT(10,5) NOT NULL,
WOpening FLOAT(10,5) NOT NULL,
WLastClosing FLOAT(10,5) NOT NULL,
WOffer FLOAT(10,5) NOT NULL,
WDemand FLOAT(10,5) NOT NULL,
FOREIGN KEY(WunderlyingISIN) REFERENCES Stock(ISIN)
);

CREATE TABLE ClientWarrant(
transactionId INTEGER PRIMARY KEY,
clientId INTEGER NOT NULL,
ISIN CHAR(12) NOT NULL,
quantity INTEGER NOT NULL,
buyingPrice FLOAT(10,5) NOT NULL,
buyingDate DATE NOT NULL,
underlyingISIN CHAR(12) NOT NULL,
type ENUM ('Call','Put') NOT NULL,
exercicePrice FLOAT(10,5) NOT NULL,
FOREIGN KEY(clientId) REFERENCES Client(clientId),
FOREIGN KEY(underlyingISIN) REFERENCES Stock(ISIN),
FOREIGN KEY(ISIN) REFERENCES Warrant(WISIN),
FOREIGN KEY(transactionId) REFERENCES ClientWarrantsTransaction(transactionId)
);

CREATE TABLE GroupWarrant(
transactionId INTEGER PRIMARY KEY,
groupId INTEGER NOT NULL,
ISIN CHAR(12) NOT NULL,
quantity INTEGER NOT NULL,
buyingPrice FLOAT(10,5) NOT NULL,
buyingDate DATE NOT NULL,
underlyingISIN CHAR(12) NOT NULL,
type ENUM ('Call','Put') NOT NULL,
exercicePrice FLOAT(10,5) NOT NULL,
FOREIGN KEY(groupId) REFERENCES Groups(groupId),
FOREIGN KEY(underlyingISIN) REFERENCES Stock(ISIN),
FOREIGN KEY(ISIN) REFERENCES Warrant(WISIN),
FOREIGN KEY(transactionId) REFERENCES GroupWarrantsTransaction(transactionId)
);

CREATE TABLE ClientStocksTransaction(
transactionId INTEGER AUTO_INCREMENT PRIMARY KEY,
clientId INTEGER NOT NULL,
operation ENUM ('BUY','SELL') NOT NULL,
operationPrice FLOAT(10,5) NOT NULL,
ISIN CHAR(12) NOT NULL,
underlyingPrice FLOAT(10,5) NOT NULL,
quantity INTEGER NOT NULL,
transactionTime VARCHAR(30) NOT NULL,
FOREIGN KEY(clientId) REFERENCES Client(clientId),
FOREIGN KEY(ISIN) REFERENCES Stock(ISIN)
);

CREATE TABLE ClientWarrantsTransaction(
transactionId INTEGER AUTO_INCREMENT PRIMARY KEY,
clientId INTEGER NOT NULL,
optionPrice FLOAT(10,5),
operation ENUM ('BUY','SELL') NOT NULL,
operationPrice FLOAT(10,5) NOT NULL,
underlyingISIN CHAR(12) NOT NULL,
underlyingPrice FLOAT(10,5) NOT NULL,
quantity INTEGER NOT NULL,
transactionTime VARCHAR(30) NOT NULL,
FOREIGN KEY(clientId) REFERENCES Client(clientId),
FOREIGN KEY(underlyingISIN) REFERENCES Warrant(WISIN)
);

CREATE TABLE ClientTrackersTransaction(
transactionId INTEGER AUTO_INCREMENT PRIMARY KEY,
clientId INTEGER NOT NULL,
optionPrice FLOAT(10,5),
operation ENUM ('BUY','SELL') NOT NULL,
operationPrice FLOAT(10,5) NOT NULL,
underlyingISIN CHAR(12) NOT NULL,
underlyingPrice FLOAT(10,5) NOT NULL,
quantity INTEGER NOT NULL,
transactionTime VARCHAR(30) NOT NULL,
FOREIGN KEY(clientId) REFERENCES Client(clientId),
FOREIGN KEY(underlyingISIN) REFERENCES Tracker(TISIN)
);

CREATE TABLE GroupStocksTransaction(
transactionId INTEGER AUTO_INCREMENT PRIMARY KEY,
groupId INTEGER NOT NULL,
operation ENUM ('BUY','SELL') NOT NULL,
operationPrice FLOAT(10,5) NOT NULL,
ISIN CHAR(12) NOT NULL,
underlyingPrice FLOAT(10,5) NOT NULL,
quantity INTEGER NOT NULL,
transactionTime VARCHAR(30) NOT NULL,
FOREIGN KEY(groupId) REFERENCES Groups(groupId),
FOREIGN KEY(ISIN) REFERENCES Stock(ISIN)
);

CREATE TABLE GroupWarrantsTransaction(
transactionId INTEGER AUTO_INCREMENT PRIMARY KEY,
groupId INTEGER NOT NULL,
optionPrice FLOAT(10,5),
operation ENUM ('BUY','SELL') NOT NULL,
operationPrice FLOAT(10,5) NOT NULL,
underlyingISIN CHAR(12) NOT NULL,
underlyingPrice FLOAT(10,5) NOT NULL,
quantity INTEGER NOT NULL,
transactionTime VARCHAR(30) NOT NULL,
FOREIGN KEY(groupId) REFERENCES Groups(groupId),
FOREIGN KEY(underlyingISIN) REFERENCES Warrant(WISIN)
);

CREATE TABLE GroupTrackersTransaction(
transactionId INTEGER AUTO_INCREMENT PRIMARY KEY,
groupId INTEGER NOT NULL,
optionPrice FLOAT(10,5),
operation ENUM ('BUY','SELL') NOT NULL,
operationPrice FLOAT(10,5) NOT NULL,
underlyingISIN CHAR(12) NOT NULL,
underlyingPrice FLOAT(10,5) NOT NULL,
quantity INTEGER NOT NULL,
transactionTime VARCHAR(30) NOT NULL,
FOREIGN KEY(groupId) REFERENCES Groups(groupId),
FOREIGN KEY(underlyingISIN) REFERENCES Tracker(TISIN)
);