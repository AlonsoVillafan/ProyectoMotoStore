--FORMULARIO CLIENTE--------------------------------------------------------
----------------------------------------------------------------------------
--COMBO BOX DISTRITOS
----------------------------------------------------------------------------
CREATE PROCEDURE SP_LISTAR_DISTRITOS
AS
BEGIN
	SELECT IdDistrito, NombreDistrito
	FROM Distrito
END
--
---------------------------------------------------------------------------
--BOTON GUARDAR - REGISTRAR EL CLIENTE
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

--FORMULARIO DETALLE DE VENTA ---------------------------------------------
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
    @precioVenta DECIMAL(10,2),
    @descuento DECIMAL(10,2)
AS
BEGIN
    -- Validar que haya stock suficiente
    IF EXISTS (SELECT 1 FROM Moto WHERE IdMoto = @idMoto AND Stock >= @cantidad)
    BEGIN
        -- Insertar el detalle de la venta
        INSERT INTO DetalleVenta (IdVenta, IdMoto, Cantidad, PrecioVentaUnidad, Descuento)
        VALUES (@idVenta, @idMoto, @cantidad , @precioVenta, @descuento)

        -- Actualizar el stock de la moto
        UPDATE Moto
        SET Stock = Stock - @cantidad
        WHERE IdMoto = @idMoto
    END
    ELSE
    BEGIN
        -- Lanzar error si no hay stock suficiente
        RAISERROR('Stock insuficiente para la moto seleccionada.', 16, 1)
    END
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


--FORMULARIO VENTA ---------------------------------------------
---------------------------------------------------------------------------
--BOTON REGISTRAR VENTA
---------------------------------------------------------------------------
CREATE PROCEDURE SP_REGISTRAR_VENTA
    @idVenta CHAR(4),
    @idCliente INT,
	@formaPago NVARCHAR(20),
	@idSucursal CHAR(3),
	@idEmpleado int
AS
BEGIN
    INSERT INTO Venta(IdVenta, IdCliente, FormaPago, IdSucursal, IdEmpleado)
    VALUES (@idVenta, @idCliente, @formaPago , @idSucursal, @idEmpleado)
END

---------------------------------------------------------------------------
--COMBO BOX LISTAR SUCURSAL
---------------------------------------------------------------------------
CREATE PROCEDURE SP_LISTAR_SUCURSAL
AS
BEGIN
	SELECT	*
	FROM Sucursal
END

---------------------------------------------------------------------------
--OBTENER EL ULTIMO ID DE VENTA, PARA MOSTRARLO E INCREMENTARLO DESDE LA APLICACION
---------------------------------------------------------------------------
CREATE PROCEDURE SP_OBTENER_ULTIMO_IDVENTA
AS
BEGIN
    SELECT TOP 1 idVenta
    FROM Venta
    ORDER BY idVenta DESC
END
--
select * from Empleado

---------------------------------------------------------------------------
--COMBO BOX LISTAR EMPLEADOS
---------------------------------------------------------------------------
CREATE PROCEDURE SP_LISTAR_EMPLEADO
AS
BEGIN
	SELECT	IdEmpleado, Nombres
	FROM Empleado
END

---------------------------------------------------------------------------
SELECT * FROM Cliente
select * from Moto
select * from DetalleVenta
