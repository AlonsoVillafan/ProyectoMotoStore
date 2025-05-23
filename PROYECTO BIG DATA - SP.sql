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

---------------------------------------------------------------------------
--COMBO BOX LISTAR EMPLEADOS
---------------------------------------------------------------------------
CREATE PROCEDURE SP_LISTAR_EMPLEADO
AS
BEGIN
	SELECT	IdEmpleado, Nombres
	FROM Empleado
END
--***********************************************************************--
--PLAN DE AUDITORIA -------------------------------------------------------
---------------------------------------------------------------------------
--TRIGGER HISTORIAL VENTAS
---------------------------------------------------------------------------
/*
Cuando haces un INSERT INTO ... SELECT ...,
no usas VALUES porque est�s insertando los datos que vienen de una consulta (SELECT) en lugar de ingresarlos manualmente.
*/
--TRIGGER PARA REGISTRAR AUDITORIA DE INSERCIONES EN LA TABLA VENTA
CREATE TRIGGER TG_INSERTAR_VENTA_AUDITORIA
ON Venta
AFTER INSERT
AS
BEGIN
    INSERT INTO Auditoria (Usuario, Accion, TablaAfectada, Descripcion)
    SELECT 
        --REGISTRAMOS AL EMPLEADO QUE REALIZO LA VENTA
        e.Nombres + ' ' + e.Apellidos AS Usuario,
        
        --REGISTRAMOS LA ACCION
        'INSERT' AS Accion,
        
        --REGISTRAMOS LA VENTA
        'Venta' AS TablaAfectada,
        
        --EN LA DESCRIPCION ESPECIFICO 
        'Se registr� la venta con ID ' + i.IdVenta + ' por el empleado '
		+ e.Nombres + ' ' + e.Apellidos AS Descripcion
    FROM inserted i
    JOIN Empleado e ON i.IdEmpleado = e.IdEmpleado; --JOIN CON LA TABLA EMPLEADO 
END
GO

---------------------------------------------------------------------------
--VISTAS PARA CONSULTAS
---------------------------------------------------------------------------
CREATE VIEW VISTA_VENTAS_POR_SUCURSAL
AS
SELECT	s.NombreSucursal,
		COUNT(v.IdVenta) AS CantVentas,
		SUM(dv.Cantidad * dv.PrecioVentaUnidad - dv.Descuento) AS TotalVentas
FROM Venta v
JOIN Sucursal s ON v.IdSucursal = s.IdSucursal
JOIN DetalleVenta dv ON v.IdVenta = dv.IdVenta
GROUP BY s.NombreSucursal;

---------------------------------------------------------------------------
--EJEMPLOS DE TRANSACCION, FUNCION Y CURSOR
---------------------------------------------------------------------------
BEGIN TRY
    BEGIN TRANSACTION;

    -- Insertar venta
    INSERT INTO Venta (IdVenta, IdCliente, FormaPago, IdSucursal, IdEmpleado)
    VALUES ('V005', 5, 'Efectivo', 'S01', 2);

    -- Insertar detalle de venta
    INSERT INTO DetalleVenta (IdVenta, IdMoto, Cantidad, PrecioVentaUnidad, Descuento)
    VALUES ('V005', 1, 1, 15000.00, 0.00);

    COMMIT TRANSACTION;
    PRINT 'Venta registrada correctamente';
END TRY
BEGIN CATCH
    ROLLBACK TRANSACTION;
    PRINT 'Error al registrar la venta: ' + ERROR_MESSAGE();
END CATCH;

---------------------------------------------------------------------------
--EJEMPLO DE FUNCION
---------------------------------------------------------------------------
CREATE FUNCTION FUNC_STOCK_DISPONIBLE (@IdMoto INT)
RETURNS INT
AS
BEGIN
    DECLARE @Stock INT;

    SELECT @Stock = Stock FROM Moto WHERE IdMoto = @IdMoto;

    RETURN @Stock;
END;
GO

--EJECUTANDO LA FUNCION
SELECT [dbo].[FUNC_STOCK_DISPONIBLE] (1) AS 'STOCK DISPONIBLE'

---------------------------------------------------------------------------
--EJEMPLO DE CURSOR
---------------------------------------------------------------------------
DECLARE @Nombre NVARCHAR(50), @Apellido NVARCHAR(100), @Total INT;

DECLARE cursor_clientes CURSOR FOR
SELECT C.Nombres, C.Apellidos, COUNT (V.IdCliente) AS 'TOTAL COMPRAS'
FROM Cliente C JOIN Venta V ON C.IdCliente = V.IdCliente
GROUP BY C.Nombres, C.Apellidos

OPEN cursor_clientes;
FETCH NEXT FROM cursor_clientes INTO @Nombre, @Apellido, @Total;

WHILE @@FETCH_STATUS = 0
BEGIN
	PRINT '******************************'
    PRINT 'Cliente: ' + @Nombre + ' ' + @Apellido;
	PRINT 'Total de Compras: ' + CAST(@Total AS NVARCHAR)
	PRINT '******************************'
	PRINT ' '
    FETCH NEXT FROM cursor_clientes INTO @Nombre, @Apellido, @Total;
END;

CLOSE cursor_clientes;
DEALLOCATE cursor_clientes;
