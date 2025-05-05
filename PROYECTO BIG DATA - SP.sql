--FORMULARIO CLIENTE--------------------------------------------------------
----------------------------------------------------------------------------
--COMBO BOX
----------------------------------------------------------------------------
CREATE PROCEDURE SP_LISTAR_DISTRITOS
AS
BEGIN
	SELECT IdDistrito, NombreDistrito
	FROM Distrito
END
--
EXEC SP_LISTAR_DISTRITOS

---------------------------------------------------------------------------
--BOTON GUARDAR
---------------------------------------------------------------------------
CREATE PROCEDURE SP_REGISTRAR_CLIENTE
    @nombres VARCHAR(100),
    @apellidos VARCHAR(100),
	@dni VARCHAR(8),
	@fechaNac DATE,
	@Telefono NVARCHAR(15),
    @idDistrito INT
AS
BEGIN
    --VALIDAMOS QUE TENGA AL MENOS 18 ANIOS
	--CALCULAMOS LA DIFERENCIA EN ANIOS ENTRE FECHA NACIMIENTO Y HOY
    IF DATEDIFF(YEAR, @fechaNac, GETDATE()) >= 18
    BEGIN
    INSERT INTO Cliente (Nombres, Apellidos, Dni, FechaNacimiento, Telefono, IdDistrito)
    VALUES (@nombres, @apellidos, @dni , @fechaNac, @Telefono, @idDistrito)
    END

    ELSE
    BEGIN
        -- Puedes manejarlo como desees: error, mensaje o simplemente no hacer nada
        RAISERROR('El cliente debe ser mayor de edad.', 16, 1);
    END
END

---------------------------------------------------------------------------
--PARA CARGAR LA DATA EN EL JTABLE
---------------------------------------------------------------------------
CREATE PROCEDURE SP_LISTAR_CLIENTES
AS
BEGIN
	SELECT 
		C.IdCliente,
		C.Nombres,
		C.Apellidos,
		C.Dni,
		C.FechaNacimiento,
		C.Telefono,
		C.FechaRegistro,
		C.IdDistrito,
		D.NombreDistrito
	FROM Cliente AS C
	INNER JOIN Distrito AS D ON C.IdDistrito = D.IdDistrito
END

---------------------------------------------------------------------------
--BOTON BUSCAR CLIENTE
---------------------------------------------------------------------------
CREATE PROCEDURE SP_BUSCAR_CLIENTE
    @texto NVARCHAR(100)
AS
BEGIN
    SELECT 
		C.IdCliente,
		C.Nombres,
		C.Apellidos,
		C.Dni,
		C.FechaNacimiento,
		C.Telefono,
		C.FechaRegistro,
		C.IdDistrito,
		D.NombreDistrito
    FROM Cliente c
    INNER JOIN Distrito d ON c.IdDistrito = d.IdDistrito
    WHERE c.Nombres LIKE '%' + @texto + '%' 
       OR c.Apellidos LIKE '%' + @texto + '%'
       OR c.Dni LIKE '%' + @texto + '%'
END

--FORMULARIO DETALLE DE VENTA ------------------------------------------------
---------------------------------------------------------------------------
--BOTON BUSCAR MOTO
---------------------------------------------------------------------------
CREATE PROCEDURE SP_BUSCAR_MOTO
	@texto NVARCHAR (50)
AS
BEGIN
	SELECT	M.IdMoto,
			TM.NombreTipoMoto,
			MARC.NombreMarca,
			M.Color,
			M.Precio,
			M.Stock
	FROM Moto M
	JOIN TipoDeMoto TM ON M.IdTipoMoto = TM.IdTipoMoto
	JOIN Marca MARC ON M.IdMarca = MARC.IdMarca
	WHERE TM.NombreTipoMoto LIKE '%' + @texto + '%'
		OR MARC.NombreMarca LIKE '%' + @texto + '%'
END

---------------------------------------------------------------------------
--PARA REGISTRAR EL DETALLE DE LA VENTA
---------------------------------------------------------------------------
CREATE PROCEDURE SP_REGISTRAR_DETALLE_VENTA
    @idVenta CHAR(4),
    @idMoto INT,
	@cantidad TINYINT,
	@precio DECIMAL(10,2),
	@descuento DECIMAL(10,2)
AS
BEGIN
    INSERT INTO DetalleVenta (IdVenta, IdMoto, Cantidad, PrecioVentaUnidad, Descuento)
    VALUES (@idVenta, @idMoto, @cantidad , @precio, @descuento)
END

---------------------------------------------------------------------------
--PARA MOSTRARLO EN UN LABEL
---------------------------------------------------------------------------
CREATE PROCEDURE SP_MOTO_MAS_BARATA
AS
BEGIN
	SELECT TOP (1)
		T.NombreTipoMoto + MAR.NombreMarca + CONVERT(VARCHAR, MO.Precio) AS 'MOTO MAS BARATA'
	FROM Moto MO
	JOIN Marca MAR ON MO.IdMarca = MAR.IdMarca
	JOIN TipoDeMoto T ON MO.IdTipoMoto = T.IdTipoMoto
	ORDER BY MO.Precio ASC
END

--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 TOKEN NETBEANS

















