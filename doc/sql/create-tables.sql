CREATE TABLE utx_db.items (
id bigint NOT NULL AUTO_INCREMENT,
nombre varchar(50),
`desc` varchar(200),
precioInicio int,
urlImagen varchar(200),
nombreUsuario varchar(8),
estado int,
historico boolean,
PRIMARY KEY (id)
);

