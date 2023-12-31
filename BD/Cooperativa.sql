
USE [Cooperativa]
GO
/****** Object:  StoredProcedure [dbo].[Pa_Beneficiario]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Pa_Beneficiario]
    @i_Operacion AS VARCHAR(15),                  --Operacion a realizar
    @i_Opcion AS INT,                             --SubOperacion
												  --Username del usuario que consulta,
@id_beneficiario	varchar(16)= null,
@Nombre_Beneficiario	varchar(70)= null,
@Telefono	varchar(15)= null,
@Direccion	varchar(100)= null,
@Parentesco varchar(30)= null,
@Identidad varchar(16)=null

	
AS
BEGIN

    IF @i_Operacion = 'LIS' --Lista los registros
    BEGIN
        IF @i_Opcion = 1
        BEGIN
            select id_beneficiario [Identidad], Nombre_Beneficiario [Nombre],Telefono,Direccion,Parentesco
			 from Beneficiario
			 where Identidad = @Identidad
			
        END;
    END;

    IF @i_Operacion = 'ADD' --Operación para Agregar Registros
    BEGIN
        IF @i_Opcion = 1
        BEGIN
			insert into Beneficiario Values (@id_beneficiario,
@Nombre_Beneficiario,
@Telefono,
@Direccion,
@Parentesco,
@Identidad


)
        END;
    END;

    IF @i_Operacion = 'UPD' --Operación para actualizar Registros
    BEGIN
        IF @i_Opcion = 1
        BEGIN
			update Beneficiario set Nombre_Beneficiario=@Nombre_Beneficiario,
			Telefono=@Telefono,
			Direccion=@Direccion,
			Parentesco=@Parentesco,
			Identidad=@Identidad

where id_beneficiario=@id_beneficiario
        END;
    END;

    IF @i_Operacion = 'DEL' --Operación para Eliminar Registros
    BEGIN
        IF @i_Opcion = 1
        BEGIN
			delete from Beneficiario
			where id_beneficiario=@id_beneficiario
        END;
    END;

END;


GO
/****** Object:  StoredProcedure [dbo].[Pa_Registros]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Pa_Registros]
    @i_Operacion AS VARCHAR(15),                  --Operacion a realizar
    @i_Opcion AS INT,                             --SubOperacion
												  --Username del usuario que consulta,
@Identidad	varchar(16)= null,
@Primer_Nombre	varchar(25)= null,
@Segundo_Nombre	varchar(25)= null,
@Primer_Apellido	varchar(25)= null,
@Segundo_Apellido	varchar(25)= null,
@Direccion	varchar(100)= null,
@Telefono	varchar(15)= null,
@Email	varchar(70)= null,
@Numero_cuenta	varchar(25)= null,
@ID_PROYECTO INT= null,
@Fecha_Registro	varchar(70) = null,
@Estado	bit = null
	
AS
BEGIN

    IF @i_Operacion = 'LIS' --Lista los registros
    BEGIN
        IF @i_Opcion = 1
        BEGIN
            select * from Registros
			where Estado = 1
        END;
    END;

    IF @i_Operacion = 'ADD' --Operación para Agregar Registros
    BEGIN
        IF @i_Opcion = 1
        BEGIN
			insert into Registros Values (@Identidad,
@Primer_Nombre,
@Segundo_Nombre,
@Primer_Apellido,
@Segundo_Apellido,
@Direccion,
@Telefono,
@Email,
@Numero_cuenta,
@ID_PROYECTO,
@Fecha_Registro,
1

)
        END;
    END;

    IF @i_Operacion = 'UPD' --Operación para actualizar Registros
    BEGIN
        IF @i_Opcion = 1
        BEGIN
			update Registros set Primer_Nombre= @Primer_Nombre,Segundo_Nombre= @Segundo_Nombre,Primer_Apellido= @Primer_Apellido,
Segundo_Apellido= @Segundo_Apellido,
Direccion= @Direccion,
Telefono= @Telefono,
Email= @Email,
Numero_cuenta= @Numero_cuenta,
ID_PROYECTO= @ID_PROYECTO,
Fecha_Registro= @Fecha_Registro

where Identidad=@Identidad 
        END;
    END;

    IF @i_Operacion = 'DEL' --Operación para Eliminar Registros
    BEGIN
        IF @i_Opcion = 1
        BEGIN
			update Registros set Estado= 0
			where Identidad=@Identidad 
        END;
    END;

END;


GO
/****** Object:  Table [dbo].[Ahorro_Fijos]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ahorro_Fijos](
	[id_ahorro_fijo] [int] NOT NULL,
	[Identidad] [varchar](16) NOT NULL,
	[Cantidad_fija_ahorrar] [numeric](6, 2) NOT NULL,
	[Fecha_deposita_fijo] [datetime] NOT NULL,
 CONSTRAINT [PK_Ahorro_Fijos] PRIMARY KEY CLUSTERED 
(
	[id_ahorro_fijo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ahorro_Retirable]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ahorro_Retirable](
	[id_retirable] [int] NOT NULL,
	[Identidad] [varchar](16) NOT NULL,
	[Cantidad_ahorrar] [numeric](6, 2) NOT NULL,
	[Fecha] [datetime] NOT NULL,
 CONSTRAINT [PK_Ahorro_Retirable] PRIMARY KEY CLUSTERED 
(
	[id_retirable] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AVAL]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[AVAL](
	[ID_AVAL] [int] NOT NULL,
	[IDENTIDAD_AVAL] [varchar](16) NOT NULL,
	[NOMBRE_AVAL] [varchar](80) NOT NULL,
	[TELEFONO] [varchar](15) NOT NULL,
	[DIRECCION] [varchar](100) NOT NULL,
	[AFILIADO] [bit] NOT NULL,
	[TRABAJA] [varchar](2) NOT NULL,
	[LUGAR_TRABAJO] [varchar](70) NOT NULL,
	[AFINIDAD] [varchar](40) NOT NULL,
	[INGRESO_MENSUAL] [varchar](20) NOT NULL,
	[Identidad] [varchar](16) NOT NULL,
 CONSTRAINT [PK_ID_AVAL] PRIMARY KEY CLUSTERED 
(
	[ID_AVAL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO

/****** Object:  Table [dbo].[usuarios]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[usuarios](
	[id] [int] NOT NULL,
	[identidad] [varchar](15) NOT NULL,
	[usuario] [varchar](20) NOT NULL,
	[contrasena] [varchar](20) NOT NULL,
	[nombre_completo] [varchar](100) NOT NULL,
 CONSTRAINT [PK_id] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO

/****** Object:  Table [dbo].[Beneficiario]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Beneficiario](
	[id_beneficiario] [varchar](16) NOT NULL,
	[Nombre_Beneficiario] [varchar](70) NOT NULL,
	[Telefono] [varchar](15) NOT NULL,
	[Direccion] [varchar](100) NOT NULL,
	[Parentesco] [varchar](30) NOT NULL,
	[Identidad] [varchar](16) NOT NULL,
 CONSTRAINT [PK_Beneficiario] PRIMARY KEY CLUSTERED 
(
	[id_beneficiario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Centros]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[Centros](
	[ID_CENTRO] [int] NOT NULL,
	[NOMBRE_CENTRO] [varchar](70) NOT NULL,
	[DESCRIPCION] [varchar](150) NULL,
	[ID_PROYECTO] [int] NOT NULL,
 CONSTRAINT [PK_ID_CENTRO] PRIMARY KEY CLUSTERED 
(
	[ID_CENTRO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Prestamos]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Prestamos](
	[Numero_Prestamo] [int] NOT NULL,
	[Identidad] [varchar](16) NOT NULL,
	[Fecha_Prestamo] [date] NOT NULL,
	[Cantidad_Prestada] [money] NOT NULL,
	[Numero_cuotas] [int] NOT NULL,
	[Porcentaje_interes] [numeric](6, 3) NOT NULL,
 CONSTRAINT [PK_Prestamos] PRIMARY KEY CLUSTERED 
(
	[Numero_Prestamo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Proyecto]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proyecto](
	[ID_PROYECTO] [int] NOT NULL,
	[Nombre_Proyecto] [varchar](100) NOT NULL,
	[Descripcion_proyecto] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Proyecto] PRIMARY KEY CLUSTERED 
(
	[ID_PROYECTO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Registros]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registros](
	[Identidad] [varchar](16) NOT NULL,
	[Primer_Nombre] [varchar](25) NOT NULL,
	[Segundo_Nombre] [varchar](25) NULL,
	[Primer_Apellido] [varchar](25) NOT NULL,
	[Segundo_Apellido] [varchar](25) NULL,
	[Direccion] [varchar](100) NOT NULL,
	[Telefono] [varchar](15) NOT NULL,
	[Email] [varchar](70) NULL,
	[Numero_cuenta] [varchar](25) NOT NULL,
	[ID_PROYECTO] [int] NOT NULL,
	[Fecha_Registro] [varchar](70) NULL,
	[Estado] [bit] NULL,
 CONSTRAINT [PK_Registros] PRIMARY KEY CLUSTERED 
(
	[Identidad] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Retiros]    Script Date: 05/10/2021 11:49:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Retiros](
	[Id_retiro] [int] NOT NULL,
	[fecha] [datetime] NOT NULL,
	[Identidad] [varchar](16) NOT NULL,
	[cantidad_retirar] [numeric](6, 2) NOT NULL,
 CONSTRAINT [PK_Retiros] PRIMARY KEY CLUSTERED 
(
	[Id_retiro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Ahorro_Fijos] ADD  DEFAULT (getdate()) FOR [Fecha_deposita_fijo]
GO
ALTER TABLE [dbo].[Ahorro_Retirable] ADD  DEFAULT (getdate()) FOR [Fecha]
GO
ALTER TABLE [dbo].[Retiros] ADD  DEFAULT (getdate()) FOR [fecha]
GO
ALTER TABLE [dbo].[Ahorro_Fijos]  WITH CHECK ADD  CONSTRAINT [FK_Ahorro_Fijos_Registros] FOREIGN KEY([Identidad])
REFERENCES [dbo].[Registros] ([Identidad])
GO
ALTER TABLE [dbo].[Ahorro_Fijos] CHECK CONSTRAINT [FK_Ahorro_Fijos_Registros]
GO
ALTER TABLE [dbo].[Ahorro_Retirable]  WITH CHECK ADD  CONSTRAINT [FK_Ahorro_Retirable_Registros] FOREIGN KEY([Identidad])
REFERENCES [dbo].[Registros] ([Identidad])
GO
ALTER TABLE [dbo].[Ahorro_Retirable] CHECK CONSTRAINT [FK_Ahorro_Retirable_Registros]
GO
ALTER TABLE [dbo].[AVAL]  WITH CHECK ADD  CONSTRAINT [FK_identidad1] FOREIGN KEY([Identidad])
REFERENCES [dbo].[Registros] ([Identidad])
GO
ALTER TABLE [dbo].[AVAL] CHECK CONSTRAINT [FK_identidad1]
GO
ALTER TABLE [dbo].[Beneficiario]  WITH CHECK ADD  CONSTRAINT [FK_Beneficiario_Registros] FOREIGN KEY([Identidad])
REFERENCES [dbo].[Registros] ([Identidad])
GO
ALTER TABLE [dbo].[Beneficiario] CHECK CONSTRAINT [FK_Beneficiario_Registros]
GO
ALTER TABLE [dbo].[Centros]  WITH CHECK ADD  CONSTRAINT [FK_id_proyecto] FOREIGN KEY([ID_PROYECTO])
REFERENCES [dbo].[Proyecto] ([ID_PROYECTO])
GO
ALTER TABLE [dbo].[Centros] CHECK CONSTRAINT [FK_id_proyecto]
GO
ALTER TABLE [dbo].[Prestamos]  WITH CHECK ADD  CONSTRAINT [FK_Prestamos_Registros] FOREIGN KEY([Identidad])
REFERENCES [dbo].[Registros] ([Identidad])
GO
ALTER TABLE [dbo].[Prestamos] CHECK CONSTRAINT [FK_Prestamos_Registros]
GO
ALTER TABLE [dbo].[Registros]  WITH CHECK ADD  CONSTRAINT [FK_Registros_Proyecto] FOREIGN KEY([ID_PROYECTO])
REFERENCES [dbo].[Proyecto] ([ID_PROYECTO])
GO
ALTER TABLE [dbo].[Registros] CHECK CONSTRAINT [FK_Registros_Proyecto]
GO
ALTER TABLE [dbo].[Retiros]  WITH CHECK ADD  CONSTRAINT [FK_Retiros_Registros] FOREIGN KEY([Identidad])
REFERENCES [dbo].[Registros] ([Identidad])
GO
ALTER TABLE [dbo].[Retiros] CHECK CONSTRAINT [FK_Retiros_Registros]
GO
