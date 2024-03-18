DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `nick` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `email` varchar(100) UNIQUE NOT NULL,
  `hash` varchar(255) UNIQUE NOT NULL,
  `activo` BOOLEAN NOT NULL,

  constraint pk_usuarios PRIMARY KEY(id),
  constraint uk_nombre UNIQUE KEY(nombre),
  constraint uk_nick UNIQUE KEY(nick)
) ;