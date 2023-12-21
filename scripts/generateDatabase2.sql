/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `i12qugaa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Actividades`
--
DROP TABLE IF EXISTS `Actividades`;
CREATE TABLE `Actividades` (
                               `id_actividad` int(11) NOT NULL,
                               `nombre` varchar(120) NOT NULL,
                               `nivel_educativo` enum('Infantil','Juvenil','Adolescente') NOT NULL,
                               `horario` enum('parcial','completa') NOT NULL,
                               `max_participantes` int(11) NOT NULL,
                               `monitores_necesarios` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Actividades`
--

INSERT INTO `Actividades` (`id_actividad`, `nombre`, `nivel_educativo`, `horario`, `max_participantes`, `monitores_necesarios`) VALUES
    (1, '1', 'Infantil', 'parcial', 3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Asistentes`
--
DROP TABLE IF EXISTS `Asistentes`;
CREATE TABLE `Asistentes` (
                              `id_asistente` int(11) NOT NULL,
                              `nombre` varchar(120) NOT NULL,
                              `fecha_nacimiento` date NOT NULL,
                              `especial` bit(1) NOT NULL,
                              `apellidos` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Asistentes`
--

INSERT INTO `Asistentes` (`id_asistente`, `nombre`, `fecha_nacimiento`, `especial`, `apellidos`) VALUES
    (1, 'jkl', '2023-12-12', b'1', 'hj');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Campamentos`
--
DROP TABLE IF EXISTS `Campamentos`;
CREATE TABLE `Campamentos` (
                               `id_campamento` int(11) NOT NULL,
                               `fecha_inicio` date NOT NULL,
                               `fecha_final` date NOT NULL,
                               `nivel_educativo` enum('Infantil','Juvenil','Adolescente') NOT NULL,
                               `max_asistentes` int(11) NOT NULL,
                               `monitor_responsable` int(11) DEFAULT NULL,
                               `monitor_especial` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Campamentos`
--

INSERT INTO `Campamentos` (`id_campamento`, `fecha_inicio`, `fecha_final`, `nivel_educativo`, `max_asistentes`, `monitor_responsable`, `monitor_especial`) VALUES
                                                                                                                                                               (1, '2023-12-12', '2023-12-12', 'Juvenil', 30, NULL, NULL),
                                                                                                                                                               (3, '2023-12-12', '2023-12-12', 'Adolescente', 30, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Campamento_Actividades`
--
DROP TABLE IF EXISTS `Campamento_Actividades`;
CREATE TABLE `Campamento_Actividades` (
                                          `fk_campamento` int(11) NOT NULL,
                                          `fk_actividad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Campamento_Actividades`
--

INSERT INTO `Campamento_Actividades` (`fk_campamento`, `fk_actividad`) VALUES
                                                                           (1, 1),
                                                                           (3, 1);

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
                                 `tipo_inscripcion` enum('temprana','tardia') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Inscripciones`
--

INSERT INTO `Inscripciones` (`fecha_inscripcion`, `precio`, `horario`, `fk_asistente`, `fk_campamento`, `tipo_inscripcion`) VALUES
                                                                                                                                ('2023-12-18', 12, 'completa', 1, 1, 'temprana'),
                                                                                                                                ('2023-12-27', 12, 'completa', 1, 3, 'temprana');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Monitores`
--
DROP TABLE IF EXISTS `Monitores`;
CREATE TABLE `Monitores` (
                             `id_monitor` int(11) NOT NULL,
                             `nombre` char(120) NOT NULL,
                             `apellidos` varchar(200) NOT NULL,
                             `fecha_nacimiento` date NOT NULL,
                             `especial` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Monitores`
--

INSERT INTO `Monitores` (`id_monitor`, `nombre`, `apellidos`, `fecha_nacimiento`, `especial`) VALUES
    (2, 'as', 'as', '2023-12-12', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Monitores_Actividades`
--
DROP TABLE IF EXISTS `Monitores_Actividades`;
CREATE TABLE `Monitores_Actividades` (
                                         `fk_monitor` int(11) NOT NULL,
                                         `fk_actividad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuarios`
--
DROP TABLE IF EXISTS `Usuarios`;
CREATE TABLE `Usuarios` (
                            `id` int(11) NOT NULL,
                            `tipo_usuario` enum('asistente','administrador') NOT NULL,
                            `username` varchar(30) NOT NULL,
                            `password` varchar(30) NOT NULL,
                            `fk_asistente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Usuarios`
--

INSERT INTO `Usuarios` (`id`, `tipo_usuario`, `username`, `password`, `fk_asistente`) VALUES
                                                                                          (2, 'asistente', 'sdd', 'adf', 1),
                                                                                          (3, 'administrador', 'sdd', 'adf', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Actividades`
--
ALTER TABLE `Actividades`
    ADD PRIMARY KEY (`id_actividad`);

--
-- Indices de la tabla `Asistentes`
--
ALTER TABLE `Asistentes`
    ADD PRIMARY KEY (`id_asistente`);

--
-- Indices de la tabla `Campamentos`
--
ALTER TABLE `Campamentos`
    ADD PRIMARY KEY (`id_campamento`),
  ADD KEY `monitor_responsable` (`monitor_responsable`),
  ADD KEY `monitor_especial` (`monitor_especial`);

--
-- Indices de la tabla `Campamento_Actividades`
--
ALTER TABLE `Campamento_Actividades`
    ADD PRIMARY KEY (`fk_campamento`,`fk_actividad`),
  ADD KEY `fk_campamento` (`fk_campamento`),
  ADD KEY `fk_actividad` (`fk_actividad`);

--
-- Indices de la tabla `Inscripciones`
--
ALTER TABLE `Inscripciones`
    ADD PRIMARY KEY (`fk_campamento`,`fk_asistente`),
  ADD KEY `fk_asistente` (`fk_asistente`);

--
-- Indices de la tabla `Monitores`
--
ALTER TABLE `Monitores`
    ADD PRIMARY KEY (`id_monitor`);

--
-- Indices de la tabla `Monitores_Actividades`
--
ALTER TABLE `Monitores_Actividades`
    ADD PRIMARY KEY (`fk_actividad`,`fk_monitor`),
  ADD KEY `fk_monitor` (`fk_monitor`);

--
-- Indices de la tabla `Usuarios`
--
ALTER TABLE `Usuarios`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_asistente` (`fk_asistente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Actividades`
--
ALTER TABLE `Actividades`
    MODIFY `id_actividad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `Asistentes`
--
ALTER TABLE `Asistentes`
    MODIFY `id_asistente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `Campamentos`
--
ALTER TABLE `Campamentos`
    MODIFY `id_campamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `Monitores`
--
ALTER TABLE `Monitores`
    MODIFY `id_monitor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `Usuarios`
--
ALTER TABLE `Usuarios`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Campamentos`
--
ALTER TABLE `Campamentos`
    ADD CONSTRAINT `Campamentos_ibfk_2` FOREIGN KEY (`monitor_especial`) REFERENCES `Monitores` (`id_monitor`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Campamentos_ibfk_1` FOREIGN KEY (`monitor_responsable`) REFERENCES `Monitores` (`id_monitor`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `Campamento_Actividades`
--
ALTER TABLE `Campamento_Actividades`
    ADD CONSTRAINT `Campamento_Actividades_ibfk_2` FOREIGN KEY (`fk_campamento`) REFERENCES `Campamentos` (`id_campamento`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Campamento_Actividades_ibfk_1` FOREIGN KEY (`fk_actividad`) REFERENCES `Actividades` (`id_actividad`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `Inscripciones`
--
ALTER TABLE `Inscripciones`
    ADD CONSTRAINT `Inscripciones_ibfk_2` FOREIGN KEY (`fk_campamento`) REFERENCES `Campamentos` (`id_campamento`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Inscripciones_ibfk_1` FOREIGN KEY (`fk_asistente`) REFERENCES `Asistentes` (`id_asistente`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `Monitores_Actividades`
--
ALTER TABLE `Monitores_Actividades`
    ADD CONSTRAINT `Monitores_Actividades_ibfk_2` FOREIGN KEY (`fk_actividad`) REFERENCES `Actividades` (`id_actividad`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Monitores_Actividades_ibfk_1` FOREIGN KEY (`fk_monitor`) REFERENCES `Monitores` (`id_monitor`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `Usuarios`
--
ALTER TABLE `Usuarios`
    ADD CONSTRAINT `Usuarios_ibfk_1` FOREIGN KEY (`fk_asistente`) REFERENCES `Asistentes` (`id_asistente`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
