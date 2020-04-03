drop database if exists project; 

create database project; 

use project; 

create table amministratore (

	codiceAccesso varchar(5),
	nome varchar(30),
    cognome varchar(30),
    
    primary key (codiceAccesso)
);

create table cliente (

	id int auto_increment,
	username varchar(30),
	nome varchar(30), 
	cognome varchar(30),
	email varchar(30),
	passwordUtente varchar(30),
        
	primary key (id)
);



create table auto (
	
    nome varchar(20) not null,
    casaAuto varchar(30) not null,
    alimentazione varchar(20) not null,
    tramissione varchar(15) not null,
    categoria varchar(15) not null,
    lunghezza decimal(6, 5) not null,
    larghezza decimal(6, 5) not null,
    altezza decimal(6, 5) not null,
    porte int not null,
    posti int not null,
    peso int not null,
    cilindri int not null,
    cilindrata int not null,
    potenza int not null,
    rapporti int not null,
    trazione varchar(20) null,
    velocitaMassima int not null,
    accelerazione double not null,
    emissioneCO2 int,
    kW int not null,
    prezzo decimal(15, 2) not null,
    
	primary key(nome, casaAuto)
); 

create table inserimento (

	nomeAuto varchar(20),
    casaAuto varchar(30),
    codiceAmministratore varchar(5),
    
    foreign key (nomeAuto, casaAuto) references auto(nome, casaAuto),
    foreign key (codiceAmministratore) references amministratore(codiceAccesso),
    
    primary key (nomeAuto, casaAuto, codiceAmministratore)
);

create table acquisto (

	codice int,
	nomeAuto varchar(20),
    casaAuto varchar(30),
    cliente int,
    
    foreign key (nomeAuto, casaAuto) references auto(nome, casaAuto), 
    foreign key (cliente) references cliente(id), 
    
    primary key (codice)
);


insert into amministratore(codiceAccesso, nome, cognome)
values ("ak-55", "Paolo", "Plomitallo"),
	   ("s5-99", "Emanuele", "Mezzi");
 
insert into cliente(id, username, nome, cognome, email, passwordUtente)
values (1, "bill55", "Bill", "Gates", "billgates55@gmail.com", "WindowstIsAVirus"), 
	   (2, "steve55", "Steve", "Jobs", "stevejobs55@gmail.com", "AppleISBest"),
       (3, "bestPlayer", "Lionel", "Messi", "lionelmessi87@gmail.com", "LionBetterThanRonaldo");
       
insert into auto(nome, casaAuto, alimentazione, tramissione, categoria, 
				lunghezza, larghezza, altezza, porte, posti, peso, cilindri, 
                cilindrata, potenza, trazione, rapporti, velocitaMassima, accelerazione, 
				emissioneCO2, kW, prezzo)

values ("488 GTB", "Ferrari", "benzina", "automatico",  "berlinetta", 4.61, 1.98, 
		1.21, 2, 2, 1358, 8, 3900, 670, null, 7, 330, 3, 263, 530, 290000.00),
        ("GTC4Lusso", "Ferrari", "benzina", "automatico", "coupè", 4.92, 1.98, 
        1.38, 3, 4, 1920, 12, 6300, 690, null, 7, 335, 3, 350, 507, 270000.00),
        ("488 Spider", "Ferrari", "benzina", "automatico", "berlinetta", 4.61, 1.98, 
        1.21, 2, 2, 1485, 8, 3900, 720, null, 7, 340, 3, 266, 530, 325000.00),
        
		("720S Cabrio", "McLaren", "benzina", "manuale", "cabrio", 4.54, 1.93,
		1.2, 2, 2, 1322, 8, 4000, 721, null, 7, 341, 2.9, 249, 530, 293000.00),
		("720S Coupè", "McLaren", "benzina", "manuale", "coupè", 4.54, 1.93, 
		1.2, 2, 2, 1322, 8, 4000, 721, null, 7, 341, 2.9, 249, 530, 266000.00),
         
		("Chiron", "Bugatti", "benzina", "automatico", "coupè", 4.54, 2.03, 
		1.21, 2, 2, 1995, 16, 8000, 1500, null, 7, 420, 2.4, 516, 1103, 2920000.00),
		("Divo", "Bugatti", "benzina", "automatico", "coupè", 4.54, 2.01,
		1.21, 2, 2, 1961, 16, 8000, 1500, null, 7, 380, 2.4, null, 1103, 5800000.00),
         
         
		("DBS GT Zagato", "Aston Martin", "benzina", "manuale", "coupè", 4.26, 1.57,
		1.27, 2, 2, 1225, 8, 5431, 770, null, 6, 339, 3.2, 285, 525, 7500000.00),
		("DB10", "Aston Martin", "benzina", "manuale", "coupè", 4.41, 1.93,
		1.25, 2, 2, 1500, 8, 4735, 436, null, 6, 300, 3.5, 245, 321, 3000000.00),
         
		("Valkyrie", "Aston Martin", "benzina", "manuale", "coupè", 4.30, 1.60, 
		1.33, 2, 2, 1365, 10, 6013, 1176, null, 8, 350, 2.5, 356, 635, 7000000),
		("Vulcan Street-legal", "Aston Martin", "benzina", "manuale", "coupè", 4.27, 1.58, 
		1.40, 2, 2, 1489, 8, 5873, 1145, null, 8, 290, 3.00, 391, 567, 2350000),
         
		("Veneno Roadster", "Lamborghini", "benzina", "automatico", "coupè", 5.02, 2.07, 
		1.16, 2, 2, 1490, 12, 6498, 750, null, 7, 355, 2.8, 211, 552, 5699999.00),
		("SC18 Alston", "Lamborghini", "benzina", "automatico", "coupè", 4.7, 2.4, 
		1.13, 2, 2, 1721, 10, 6498, 770, null, 7, 350, 2.9, 370, 566, 4560000.00),
		("Huracán", "Lamborghini", "benzina", "automatico", "coupè", 4.45, 2.23, 
		1.16, 2, 2, 1422, 10, 5204, 640, null, 7, 325, 3.2, 290, 448, 206790.00),
		("Gallardo", "Lamborghini", "benzina", "manuale", "coupè", 4.3, 1.9, 
		1.16, 2, 2, 1570, 10, 5204, 500, null, 7, 315, 3.8, 450, 367, 195000),
         
         
		("Gemera", "Koenigsegg", "metanolo", "automatico", "coupè", 4.29, 1.96, 
		1.29, 2, 2, 1715, 12, 1988, 1700, null, 8, 400, 1.9, 0, 400, 7350000),
		("Jesko", "Koenigsegg", "benzina", "automatico", "coupè", 4.61, 1.96,
		1.12, 2, 2, 1420, 10, 4700, 1625, null, 8, 450, 2.1, 320, 955, 3000000),
		("Regera", "Koenigsegg", "benzina", "automatico", "coupè", 4.56, 2.05,
		1.11, 2, 2, 1420, 10, 5065, 1500, null, 7, 500, 2.5, 310, 820, 1900000),
		("Agera", "Koenigsegg", "benzina", "automatico", "coupè", 4.00, 1.96, 
		1.11, 2, 2, 1435, 10, 4990, 1040, null, 7, 450, 2.8, 270, 706, 2100000),
         
         
		("Rapier", "Bentley", "benzina", "manuale", "coupè", 4.80, 1.91,
		1.52, 2, 2, 3200, 10, 3996, 635, null, 8, 380, 2.8, 350, 404, 4500000.00),
		("Mulsanne", "Bentley", "benzina", "automatico", "coupè", 5.8, 1.92,
		1.47, 2, 2, 3173, 8, 6752, 333, null, 7, 305, 5.3, 365, 222, 317000),
		("Mulliner Bacalar", "Bentley", "benzina", "automatico", "spider", 6.95, 1.87, 
        1.55, 2, 2, 3450, 8, 5678, 690, null, 7, 320, 3.6, 302, 485, 2000000),
		("Hunaudieres", "Bentley", "benzina", "manuale", "coupè", 5.20, 2.10,
		1.48, 2, 2, 3250, 8, 6752, 240, null, 7, 350, 3.8, 300, 475, 1570000),
		
		("Model X", "Tesla", "elettrico", "automatico", "crossover suv", 5.00, 2.83,
		1.62, 4, 7, 2468, 3, 4000, 364, null, 5, 250, 2.9, null, 250, 125000),
		("Model S", "Tesla", "elettrico", "automatico", "berlina", 4.97, 1.96,
		1.44, 4, 5, 1964, 3, 3123, 475, null, 5, 250, 2.6, null, 370, 88000.00),
        ("Roadster", "Tesla", "elettrico", "automatico", "roadster", 3.95, 1.87, 
		1.14, 2, 2, 1140, 8, 3457, 678, null, 6, 200, 4.2, null, 200, 200000);

	

