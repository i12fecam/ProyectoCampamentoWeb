
-- Base de datos: `i12fecam`
--
--CREATE DATABASE `i12fecam` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
--USE i12fecam;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Monitores`
--

DROP TABLE IF EXISTS `Monitores`;
CREATE TABLE `Monitores` (
                             `id_monitor` int(11) NOT NULL AUTO_INCREMENT,
                             `nombre` char(120) NOT NULL,
                             `apellidos` varchar(200) NOT NULL,
                             `fecha_nacimiento` date NOT NULL,
                             `especial` bit(1) DEFAULT NULL,
                             PRIMARY KEY (`id_monitor`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Actividades`
--

DROP TABLE IF EXISTS `Actividades`;
CREATE TABLE `Actividades` (
                               `id_actividad` int(11) NOT NULL AUTO_INCREMENT,
                               `nombre` varchar(120) NOT NULL,
                               `nivel_educativo` enum('Infantil','Juvenil','Adolescente') NOT NULL,
                               `horario` enum('parcial','completa') NOT NULL,
                               `max_participantes` int(11) NOT NULL,
                               `monitores_necesarios` int(11) NOT NULL,
                               PRIMARY KEY (`id_actividad`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Asistentes`
--

DROP TABLE IF EXISTS `Asistentes`;
CREATE TABLE `Asistentes` (
                              `id_asistente` int(11) NOT NULL AUTO_INCREMENT,
                              `nombre` varchar(120) NOT NULL,
                              `fecha_nacimiento` date NOT NULL,
                              `especial` bit(1) NOT NULL,
                              `apellidos` varchar(200) NOT NULL,
                              PRIMARY KEY (`id_asistente`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
CREATE TABLE `Usuarios` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `tipo_usuario` enum('asistente','administrador') NOT NULL,
                            `username` varchar(30) NOT NULL,
                            `password` varchar(30) NOT NULL,
                            `fk_asistente` int(11) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            FOREIGN KEY (`fk_asistente`) references Asistentes(id_asistente)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Campamentos`
--



DROP TABLE IF EXISTS `Campamentos`;
CREATE TABLE `Campamentos` (
                               `id_campamento` int(11) NOT NULL AUTO_INCREMENT,
                               `fecha_inicio` date NOT NULL,
                               `fecha_final` date NOT NULL,
                               `nivel_educativo` enum('Infantil','Juvenil','Adolescente') NOT NULL,
                               `max_asistentes` int(11) NOT NULL,
                               `monitor_responsable` int(11) DEFAULT NULL,
                               `monitor_especial` int(11) DEFAULT NULL,
                               PRIMARY KEY (`id_campamento`),
                               FOREIGN KEY (`monitor_responsable`) references Monitores(id_monitor),
                               FOREIGN KEY (`monitor_especial`) references Monitores(id_monitor)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Campamento_Actividades`
--


DROP TABLE IF EXISTS `Campamento_Actividades`;
CREATE TABLE `Campamento_Actividades` (
                                          `fk_campamento` int(11) NOT NULL,
                                          `fk_actividad` int(11) NOT NULL,
                                          PRIMARY KEY (`fk_campamento`,`fk_actividad`),
                                          Foreign Key  (`fk_campamento`) references Campamentos(id_campamento),
                                          Foreign Key (`fk_actividad`) references Actividades(id_actividad)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Inscripciones`
--

DROP TABLE IF EXISTS `Inscripciones`;
CREATE TABLE `Inscripciones` (
                                 `fecha_inscripcion` date NOT NULL,
                                 `precio` float NOT NULL,
                                 `horario` enum('parcial','completa') NOT NULL,
                                 `fk_asistente` int(11) NOT NULL,
                                 `fk_campamento` int(11) NOT NULL,
                                 `tipo_inscripcion` enum('temprana','tardia') NOT NULL,
                                 PRIMARY KEY (`fk_campamento`,`fk_asistente`),
                                 FOREIGN KEY (`fk_campamento`) references Campamentos(`id_campamento`),
                                 FOREIGN KEY (`fk_asistente`) references Asistentes(id_asistente)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Monitores_Actividades`
--

DROP TABLE IF EXISTS `Monitores_Actividades`;
CREATE TABLE `Monitores_Actividades` (
                                         `fk_monitor` int(11) NOT NULL,
                                         `fk_actividad` int(11) NOT NULL,
                                         PRIMARY KEY (`fk_actividad`,`fk_monitor`),
                                         FOREIGN KEY (`fk_monitor`) references Monitores(`id_monitor`),
                                         FOREIGN KEY (`fk_actividad`) references Actividades(id_actividad)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;









insert into Actividades (id_actividad, nombre, nivel_educativo, horario, max_participantes, monitores_necesarios)
values (1,'Baloncesto','Infantil','parcial',8,2);

insert into Actividades (id_actividad, nombre, nivel_educativo, horario, max_participantes, monitores_necesarios)
values (2,'Baloncesto','Juvenil','completa',6,2);

insert into Actividades (id_actividad, nombre, nivel_educativo, horario, max_participantes, monitores_necesarios)
values (3,'Marionetas','Adolescente','completa',12,4);


insert into Actividades (id_actividad, nombre, nivel_educativo, horario, max_participantes, monitores_necesarios)
values (4,'Pintura,','Infantil','parcial',7,4);

insert into Actividades (id_actividad, nombre, nivel_educativo, horario, max_participantes, monitores_necesarios)
values (5,'Natacion','Juvenil','completa',2,1);

insert into Actividades (id_actividad, nombre, nivel_educativo, horario, max_participantes, monitores_necesarios)
values (6,'Escalada','Adolescente','completa',11,3);

Insert into Asistentes ( nombre,apellidos, fecha_nacimiento, especial)
values ('Juana','Fernandez Carrera','2003-04-02',0);

Insert into Asistentes ( nombre,apellidos, fecha_nacimiento, especial)
values ('Daniela','Sanchez Gutierrez','2003-04-01',1);

Insert into Asistentes ( nombre,apellidos, fecha_nacimiento, especial)
values ('Diana','Lopez Perez','2005-04-02',0);

Insert into Asistentes ( nombre,apellidos, fecha_nacimiento, especial)
values ('Pablo','Dueñas Martinez','2002-01-01',1);

Insert into Asistentes ( nombre,apellidos, fecha_nacimiento, especial)
values ('Jose','Martinez Torres','2008-01-10',0);

Insert into Asistentes ( nombre,apellidos, fecha_nacimiento, especial)
values ('Alfredo','Flores de la fuente','1980-05-12',0);

Insert into Campamentos (fecha_inicio, fecha_final, nivel_educativo, max_asistentes, monitor_responsable, monitor_especial)
values ('2003-04-01','2003-05-01','Juvenil',3,0,0);

Insert into Campamentos (fecha_inicio, fecha_final, nivel_educativo, max_asistentes, monitor_responsable, monitor_especial)
values ('2030-04-01','2030-05-08','Adolescente',12,0,0);

Insert into Campamentos (fecha_inicio, fecha_final, nivel_educativo, max_asistentes, monitor_responsable, monitor_especial)
values ('2025-04-01','2025-05-01','Infantil',2,0,0);

Insert into Monitores (nombre, apellidos, fecha_nacimiento, especial)
values ('Daniel','Rivera Torres', 1980-04-02,1);

Insert into Monitores (nombre, apellidos, fecha_nacimiento, especial)
values ('Nuria','Castro Silva', 1985-11-02,0);

Insert into Monitores (nombre, apellidos, fecha_nacimiento, especial)
values ('Jose','Herrera Morales', 1983-01-23,1);

Insert into Monitores (nombre, apellidos, fecha_nacimiento, especial)
values ('Daniel','Rivera Torres', 1980-04-02,1);

Insert into Monitores (nombre, apellidos, fecha_nacimiento, especial)
values ('Lucia','Castro Vargas', 1980-04-02,1);