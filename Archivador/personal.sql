/*crear la base de datos*/
create database personal;

/*Usar la base de datos*/
use personal;

/*creacion de las tablas*/
create table Datos_Generales (RFC varchar(15), 
                              nom varchar(100), 
                              Clave_Servidor_Publico varchar(9), 
                              CURP varchar(18), 
                              Domicilio varchar (100),
                              localidad varchar (100),
							  Municipio varchar (100),
                              Codigo_Postal int(5), 
                              Estado_Civil varchar (20), 
                              lugar_nacimiento varchar(200),
                              Correo_Electronico varchar (40), 
							  Tel_Particular int (12), 
                              Tel_Celular int(12),
                              Tel_Laboral int(12), 
                              primary key(RFC));
                              
create table tipo(id_tipo int auto_increment,
                         tipo varchar(200),
                         primary key(id_tipo));
                         
create table entrega(id_entrega int auto_increment,
                     nombre_doc varchar(200),
                     id_tipo int,
                     primary key(id_entrega),
                     foreign key(id_tipo) references tipo(id_tipo));
                     
insert into tipo(tipo) values('PERSONAL');
insert into entrega(nombre_doc,id_tipo) values('acta de nacimiento',1);

SELECT * FROM DOCUMENTO;                         


                             
create table documento(id_doc int auto_increment,
                          RFC varchar(15),
                          nombre varchar(200),
                          ruta longblob,
                          descripcion varchar(255),
                          id_entrega int,
                          primary key(id_doc),
                          foreign key(RFC) references datos_generales(RFC),
                          foreign key(id_entrega) references entrega(id_entrega));
                              

create table estudios(id_estudio int auto_increment,
                       nombre_estudio varchar(100),
                       especialidad varchar(100),
                       primary key(id_estudio));
                       

create table Preparacion_Profecional (id_pp int auto_increment,
                                      RFC varchar (15), 
                                      id_estudio int, 
                                      Titulo_o_Grado boolean,
                                      primary key(id_pp),
									  foreign key(RFC) references Datos_Generales(RFC),                                      
                                      foreign key(id_estudio) references estudios(id_estudio));
                                     

create table Situacion_laboral (id_sl int auto_increment,
								RFC varchar(15),
                                id_PP int,
                                Movimientos_Administrativos varchar (19),
                                Revision int(6), 
                                Departamento varchar (30),
                                foreign key (RFC) references Datos_Generales(RFC),
                                foreign key (id_PP) references Preparacion_Profecional(id_PP),
                                primary key (id_sl,RFC, id_PP));

create table usuarios(RFC varchar (15),
                      Nom_usuario varchar (20),
                      contraseña varchar (10),
                      id_tipo_usuario int (20),
                      primary key (RFC),
                      foreign key (RFC) references Datos_Generales(RFC),
                      foreign key (id_tipo_usuario) references Tipo_de_Usuario (id_tipo_usuario));
                      
create table Tipo_de_Usuario (id_tipo_usuario int auto_increment,
		                      tipo varchar (20),
                              primary key (id_tipo_usuario));
                           										