
CREATE DATABASE Data;

use Data;

create table Usuario (
       usuario  varchar(10)  not null,
       clave varchar(10) not null,  
       Primary Key (usuario)         
     );

insert into Usuario values('001','001');
insert into Usuario values('002','002');
insert into Usuario values('003','003');

-- source C:\jsanchez\UnaPrograIIIJava\Materiales\Sockets\Server-Client-Protocol\Server\script.sql
