--EMPRESA
CREATE TABLE [CAD_EMPRESA]
(
	[ID_EMPRESA] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_EMPRESA]),
	[NR_CNPJ] VARCHAR(18) NOT NULL, UNIQUE ([NR_CNPJ]),
	[NM_NOME] VARCHAR(200) NULL,
	[NM_FANTASIA] VARCHAR(100) NULL,
	[NM_CIDADE] VARCHAR(200) NULL,
	[CD_SITUACAO] TINYINT NULL,
PRIMARY KEY ([ID_EMPRESA])
) 
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--REGIONAL
CREATE TABLE [CAD_REGIONAL]
(
	[ID_REGIONAL] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_REGIONAL]),
	[ID_EMPRESA] [BIGINT] NOT NULL,
	[NM_NOME] [varchar](200) NULL,
	[NM_CIDADE] [varchar](100) NULL,
	[CD_SITUACAO] [tinyint] NULL,
PRIMARY KEY ([ID_REGIONAL]),
CONSTRAINT FK_CAD_EMP_REF_CAD_REG FOREIGN KEY([ID_EMPRESA]) REFERENCES [CAD_EMPRESA] ([ID_EMPRESA])  ON UPDATE NO ACTION ON DELETE NO ACTION 
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--CAD_FUNCIONARIO
/*CREATE TABLE [CAD_FUNCIONARIO](
	[ID_FUNCIONARIO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_FUNCIONARIO]),
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[NM_NOME] [varchar](150) NULL,
	[NM_LOGIN] [varchar](50) NULL, UNIQUE ([NM_LOGIN]),
	[NM_SENHA] [varchar](50) NULL,
	[NR_MATRICULA] [varchar](20) NOT NULL, UNIQUE ([NR_MATRICULA]),
	[NR_TELEFONE] [varchar](50) NULL,
	[ID_DEVICE] [varchar](50) NULL,
	[CD_SITUACAO] [tinyint] NULL,
	[CD_BASEDADOS] [tinyint] NULL,
PRIMARY KEY ([ID_FUNCIONARIO]),
CONSTRAINT FK_CAD_FUN_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL])  ON UPDATE NO ACTION ON DELETE NO ACTION 
)
GO*/
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--MUNICIPIO
CREATE TABLE [CAD_MUNICIPIO](
	[ID_MUNICIPIO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_MUNICIPIO]),
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[NM_NOME] [varchar](45) NULL, UNIQUE ([NM_NOME]),
PRIMARY KEY ([ID_MUNICIPIO]),
CONSTRAINT FK_CAD_MUN_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL])  ON UPDATE NO ACTION ON DELETE NO ACTION 
)
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--PERGUNTA APR
CREATE TABLE [CAD_PERGUNTA_APR](
	[ID_PERGUNTA_APR] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_PERGUNTA_APR]),
	[NR_PERGUNTA_APR] [int] NOT NULL,
	[DS_PERGUNTA_APR] [varchar](150) NULL,
	[CD_SITUACAO] [int] NULL,
PRIMARY KEY (ID_PERGUNTA_APR)
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--TAREFA PADRAO (IGNORADO)
/*
CREATE TABLE [CAD_TAREFA_PADRAO]
(
	[ID_TAREFA_PADRAO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_TAREFA_PADRAO])
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[CD_CODIGO_TAREFA] [int] NOT NULL,
PRIMARY KEY ([ID_TAREFA_PADRAO]),
CONSTRAINT FK_CAD_TAR_PAD_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL])  ON UPDATE NO ACTION ON DELETE NO ACTION 
)
GO
*/
----------------------------------------------------------------------------------------
--PERFIL ACESSO
CREATE TABLE [SEG_PERFIL_ACESSO]
(
	[ID_PERFIL_ACESSO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_PERFIL_ACESSO]),
	[NM_NOME] VARCHAR(150) NOT NULL, UNIQUE ([NM_NOME]),
	[CD_SITUACAO] [tinyint] NULL,
PRIMARY KEY ([ID_PERFIL_ACESSO])
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--GRUPO FATURAMENTO
CREATE TABLE [MED_GRUPO_FATURAMENTO]
(
	[ID_GRUPO_FATURAMENTO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_GRUPO_FATURAMENTO]),
	[CD_GRUPO_FATURAMENTO] [int] NULL, UNIQUE ([CD_GRUPO_FATURAMENTO]),
	[CD_SITUACAO] [tinyint] NULL,
PRIMARY KEY ([ID_GRUPO_FATURAMENTO])
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--PERMISSAO
CREATE TABLE [SEG_PERMISSAO]
(
	[ID_PERMISSAO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_PERMISSAO]),
	[DS_DESCRICAO] VARCHAR(200) NULL, UNIQUE ([DS_DESCRICAO]),
	[CD_SITUACAO] TINYINT NULL,
PRIMARY KEY ([ID_PERMISSAO])
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--USUARIO
CREATE TABLE [SEG_USUARIO]
(
	[ID_USUARIO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_USUARIO]),
	[ID_PERFIL_ACESSO] [BIGINT] NOT NULL,
	[NR_CPF] VARCHAR(20) NOT NULL, UNIQUE ([NR_CPF]),
	[NM_NOME] VARCHAR(200) NULL,
	[NM_LOGIN] VARCHAR(20) NOT NULL, UNIQUE ([NM_LOGIN]),
	[NM_SENHA] VARCHAR(100) NULL,
	[DS_EMAIL] VARCHAR(100) NULL,
	[NR_MATRICULA] VARCHAR(20) NOT NULL, UNIQUE ([NR_MATRICULA]),
	[ST_ALTERA_SENHA] TINYINT NULL,
	[CD_SITUACAO] TINYINT NULL,
PRIMARY KEY ([ID_USUARIO]),
CONSTRAINT FK_SEG_USU_REF_SEG_PERFI FOREIGN KEY([ID_PERFIL_ACESSO]) REFERENCES [SEG_PERFIL_ACESSO] ([ID_PERFIL_ACESSO])  ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--USUARIO PERMISSAO
CREATE TABLE [USUARIO_PERMISSAO]
(
	[ID_USUARIO_PERMISSAO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_USUARIO_PERMISSAO]),
	[ID_USUARIO] [BIGINT] NOT NULL,
	[ID_PERMISSAO] [BIGINT] NOT NULL,
	PRIMARY KEY ([ID_USUARIO_PERMISSAO]),
CONSTRAINT FK_USU_PER_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_USU_PER_REF_SEG_PER FOREIGN KEY([ID_PERMISSAO]) REFERENCES [SEG_PERMISSAO] ([ID_PERMISSAO])  ON UPDATE NO ACTION ON DELETE NO ACTION,
)
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--USUARIO_REGIONAL
CREATE TABLE [SEG_USUARIO_REGIONAL]
(
	[ID_USUARIO_REGIONAL] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_USUARIO_REGIONAL]),
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[ID_USUARIO] [BIGINT] NOT NULL,
PRIMARY KEY ([ID_USUARIO_REGIONAL]),
CONSTRAINT FK_USU_REG_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_USU_REG_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO])  ON UPDATE NO ACTION ON DELETE NO ACTION,
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--LIGACAO NOVA
CREATE TABLE [CAD_LIGACAO_NOVA](
	[ID_LIGACAO_NOVA] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_LIGACAO_NOVA]),
	[ID_USUARIO] [BIGINT] NOT NULL,	
	[DT_EXECUCAO] [smalldatetime] NULL,
	[NR_MEDIDOR] [varchar](20) NULL,
	[NR_LEITURA_MEDIDA] [int] NULL,
	[DS_OBSERVACAO] [varchar](200) NULL,
	[CD_LATITUDE] [varchar](20) NULL,
	[CD_LONGITUDE] [varchar](20) NULL,
	[CD_TAREFA_LEITURA] [int] NULL,
	[CD_CEP] [varchar](20) NULL,
	[NM_ENDERECO] [varchar](150) NULL,
 PRIMARY KEY ([ID_LIGACAO_NOVA]),
CONSTRAINT FK_CAD_LIG_NOV_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO])  ON UPDATE NO ACTION ON DELETE NO ACTION, 
)
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--ARQUIVO IMPORTACAO
/*CREATE TABLE [MED_ARQUIVO_IMPORTACAO]
(
	[ID_ARQUIVO_IMPORTACAO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_ARQUIVO_IMPORTACAO]),
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[ID_GRUPO_FATURAMENTO] [BIGINT] NOT NULL,
	[NM_ARQUIVO] [varchar](200) NULL, UNIQUE ([NM_ARQUIVO]),
	[DT_IMPORTACAO] [smalldatetime] NULL,
	[DS_IMPORTACAO] [varchar](250) NULL,
	[CD_SITUACAO] TINYINT NULL,
	[DS_PATH] [varchar](200) NULL,
PRIMARY KEY ([ID_ARQUIVO_IMPORTACAO]),
CONSTRAINT FK_MED_ARQ_IMP_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_ARQ_IMP_REF_MED_GRUP_FAT FOREIGN KEY([ID_GRUPO_FATURAMENTO]) REFERENCES [MED_GRUPO_FATURAMENTO] ([ID_GRUPO_FATURAMENTO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
) 
GO*/
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--IMPORTACAO
CREATE TABLE [MED_IMPORTACAO]
(
	[ID_IMPORTACAO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_IMPORTACAO]),
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[ID_GRUPO_FATURAMENTO] [BIGINT] NOT NULL,	
	[ID_USUARIO] [BIGINT] NOT NULL,
	[DT_ANO_MES_REF] [date] NOT NULL,
	[QT_REGISTRO] [int] NOT NULL,
	[DT_PREVISAO_INICIO] [date] NOT NULL,
	[DT_PREVISAO_FIM] [date] NOT NULL,
	[NM_ARQUIVO] [varchar](200) NOT NULL,
	[DT_IMPORTACAO] [datetime] NOT NULL,
	[DS_IMPORTACAO] [varchar](100) NOT NULL,
	[DS_PATH] [varchar](200) NOT NULL,
PRIMARY KEY ([ID_IMPORTACAO]),
CONSTRAINT FK_MED_IMP_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_IMP_REF_MED_GRUP_FAT FOREIGN KEY([ID_GRUPO_FATURAMENTO]) REFERENCES [MED_GRUPO_FATURAMENTO] ([ID_GRUPO_FATURAMENTO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_IMP_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--LEITURA
CREATE TABLE [MED_LEITURA]
(
	[ID_LEITURA] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_LEITURA]),
	[ID_IMPORTACAO] [BIGINT] NOT NULL,
	[ID_GRUPO_FATURAMENTO] [BIGINT] NOT NULL,
	[DT_LEITURA] [datetime] NULL,
	[HORA_LEITURA] [varchar](10) NULL,
	[NR_LEITURISTA] [varchar](10) NULL,
	[NR_ORDEM_LEITURA] [varchar](20) NULL,
	[NR_UNIDADE_LEITURA] [varchar](8) NULL,
	[NM_CLIENTE] [varchar](50) NULL,
	[NM_ENDERECO] [varchar](90) NULL,
	[NM_COMPLEMENTO] [varchar](50) NULL,
	[NM_MUNICIPIO] [varchar](50) NULL,
	[CD_CEP] [varchar](10) NULL,
	[NR_INSTALACAO] [varchar](10) NULL,
	[CD_LOGRADOURO] [varchar](15) NULL,
	[NR_MEDIDOR] [varchar](20) NULL,
	[CD_TIPO_MEDIDOR] [varchar](10) NULL,
	[CD_SEQUENCIA] [varchar](2) NULL,
	[CD_UNIDADE_MEDIDA] [varchar](4) NULL,
	[DS_MENS_AVISO_MOBILE] [varchar](50) NULL,
	[CD_SEGUIMENTO] [varchar](10) NULL,
	[CD_RAMO_ATIVIDADE] [varchar](50) NULL,
	[NR_ULTIMA_LEITURA] [int] NULL,
	[NR_MEDIA3_MESES] [int] NULL,
	[NR_LEITURA_MEDIDA] [int] NULL,
	[FL_LEITURA_REPASSE] [char](1) NULL,
	[CD_TAREFA_LEITURA] [VARCHAR](10) NULL,
	[CD_ORDENACAO_LEITURA] [int] NULL,
	[CD_TAREFA_ENTREGA] [VARCHAR](10) NULL,
	[NR_MATRICULA_LEITURISTA] [varchar](10) NULL,
	[CD_LATITUDE] [varchar](20) NULL,
	[CD_LONGITUDE] [varchar](20) NULL,
	[ST_TIPOLEITURA] [varchar](10) NULL,
	[CD_FAIXAMINIMA] [int] NULL,
	[CD_FAIXAMAXIMA] [int] NULL,
	[CD_OCORRENCIA] [tinyint] NULL,
PRIMARY KEY ([ID_LEITURA]),
CONSTRAINT FK_MED_LEIT_REF_MED_IMP FOREIGN KEY([ID_IMPORTACAO]) REFERENCES [MED_IMPORTACAO] ([ID_IMPORTACAO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_LEIT_REF_MED_GRUP_FAT FOREIGN KEY([ID_GRUPO_FATURAMENTO]) REFERENCES [MED_GRUPO_FATURAMENTO] ([ID_GRUPO_FATURAMENTO]) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--DISTRIBUICAO LEITURA MOBILE
/*CREATE TABLE [MED_DISTRIBUICAO_LEITURA_MOBILE]
(
	[ID_DISTRIBUICAO_MOBILE] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_DISTRIBUICAO_MOBILE]),
	[ID_FUNCIONARIO] [BIGINT] NOT NULL,
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[DT_DISTRIBUICAO] [smalldatetime] NULL,
	[FL_ASSOCIADO] [char](1) NULL,
PRIMARY KEY ([ID_DISTRIBUICAO_MOBILE]),
CONSTRAINT FK_MED_DIST_MOB_REF_CAD_FUNCI FOREIGN KEY([ID_FUNCIONARIO]) REFERENCES [CAD_FUNCIONARIO] ([ID_FUNCIONARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_DIST_MOB_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL]) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO*/
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--DISTRIBUICAO LEITURA REGISTRO
CREATE TABLE [MED_DISTRIBUICAO_LEITURA_REGISTRO]
(
	[ID_DISTRIBUICAO_LEITURA_REGISTRO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_DISTRIBUICAO_LEITURA_REGISTRO]),
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[ID_LEITURA] [BIGINT] NOT NULL,
	[ID_USUARIO] [BIGINT] NOT NULL,
	[FL_ASSOCIADO] [tinyint] NOT NULL,
	[DT_DISTRIBUICAO] [datetime] NOT NULL,
PRIMARY KEY ([ID_DISTRIBUICAO_LEITURA_REGISTRO]),
CONSTRAINT FK_MED_DIST_LEIT_REG_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_DIST_LEIT_REG_REF_MED_LEIT FOREIGN KEY([ID_LEITURA]) REFERENCES [MED_LEITURA] ([ID_LEITURA]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_DIST_LEIT_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--DESASSOCIADO
CREATE TABLE [MED_DESASSOCIADO]
(
	[ID_DESASSOCIADO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_DESASSOCIADO]),
	[ID_USUARIO] [BIGINT] NOT NULL,
	[ID_LEITURA] [BIGINT] NOT NULL,
PRIMARY KEY ([ID_DESASSOCIADO]),
CONSTRAINT FK_MED_DESA_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_DESA_REF_MED_LEIT FOREIGN KEY([ID_LEITURA]) REFERENCES [MED_LEITURA] ([ID_LEITURA]) ON UPDATE NO ACTION ON DELETE NO ACTION,
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--EXPORTACAO
CREATE TABLE [MED_EXPORTACAO]
(
	[ID_EXPORTACAO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_EXPORTACAO]),
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[ID_GRUPO_FATURAMENTO] [BIGINT] NOT NULL,
	[ID_USUARIO] [BIGINT] NOT NULL,
	[DT_ANO_MES_REF] [date] NOT NULL,
	[NM_ARQUIVO] [varchar](50) NOT NULL,
	[QT_REGISTRO] [int] NOT NULL,
	[DT_EXPORTACAO] [datetime] NOT NULL,
	[DS_EXPORTACAO] [varchar](100) NOT NULL,
	[DS_PATH] [varchar](200) NOT NULL,
PRIMARY KEY ([ID_EXPORTACAO]),
CONSTRAINT FK_MED_EXPO_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_EXPO_REF_MED_GRUP_FAT FOREIGN KEY([ID_GRUPO_FATURAMENTO]) REFERENCES [MED_GRUPO_FATURAMENTO] ([ID_GRUPO_FATURAMENTO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_EXPO_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--OCORRENCIA
CREATE TABLE [MED_OCORRENCIA]
(
	[ID_OCORRENCIA] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_OCORRENCIA]),
	[CD_OCORRENCIA] [int] NULL, UNIQUE ([CD_OCORRENCIA]),
	[NM_NOME] [VARCHAR](50) NULL, UNIQUE ([NM_NOME]),
	[CD_SITUACAO] [tinyint] NULL,
	[FL_TIPO_OCORRENCIA] [tinyint] NULL,
PRIMARY KEY ([ID_OCORRENCIA])
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--RETORNO LEITURA
CREATE TABLE [MED_RETORNO_LEITURA]
(
	[ID_RETORNO_LEITURA] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_RETORNO_LEITURA]),
	[ID_LEITURA] [BIGINT] NOT NULL,
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[ID_USUARIO] [BIGINT] NOT NULL,
	[ID_OCORRENCIA] [BIGINT] NOT NULL,
	[ID_GRUPO_FATURAMENTO] [BIGINT] NOT NULL,
	[DT_ANO_MES_REF] [date] NOT NULL,
	[NR_LEITURA_MEDIDA] [int] NOT NULL,
	[NR_VARIACAO_LEITURA] [DECIMAL](12,2) NOT NULL,
	[DT_LEITURA] [datetime] NOT NULL,
	[CD_TAREFA_LEITURA] [VARCHAR](10) NOT NULL,
	[CD_ORDENACAO_LEITURA] [int] NOT NULL,
	[CD_TAREFA_ENTREGA] [VARCHAR](10) NOT NULL,
	[CD_LATITUDE] [varchar](20) NOT NULL,
	[CD_LONGITUDE] [varchar](20) NOT NULL,
	[FL_CRITICA] [tinyint] NOT NULL,
	[DS_OBSERVACAO] [varchar](500) NOT NULL,
	[FL_MEDIA] [tinyint] NOT NULL,
	[NR_INSTALACAO] [varchar](20) NOT NULL,
	[NR_LEITURA_ANTERIOR] [int] NOT NULL,
	[NR_MEDIDOR] [varchar](20) NOT NULL,
	[DT_ATUALIZACAO] [datetime] NULL,
	[FL_FOTO] [tinyint] NOT NULL,
	[FL_ATIVO] [tinyint] NOT NULL,
PRIMARY KEY ([ID_RETORNO_LEITURA]),
CONSTRAINT FK_MED_RET_LEIT_REF_MED_LEIT FOREIGN KEY([ID_LEITURA]) REFERENCES [MED_LEITURA] ([ID_LEITURA]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_RET_LEIT_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_RET_LEIT_REF_MED_OCO FOREIGN KEY([ID_OCORRENCIA]) REFERENCES [MED_OCORRENCIA] ([ID_OCORRENCIA]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_RET_LEIT_REF_MED_GRUP_FAT FOREIGN KEY([ID_GRUPO_FATURAMENTO]) REFERENCES [MED_GRUPO_FATURAMENTO] ([ID_GRUPO_FATURAMENTO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_MED_RET_LEIT_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL])  ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--PARAMETRO MOBILE
CREATE TABLE [PARAMETRO_MOBILE]
(
	[ID_PARAMETRO_MOBILE] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_PARAMETRO_MOBILE]),
	[ID_REGIONAL] [BIGINT] NOT NULL,
	[NR_MINUTOS_SINCRONIZAR] [int] NULL,
	[NR_MINUTOS_SINC_GPS] [int] NULL,
	[QT_MAX_TAREFA_SICRO] [int] NULL,
	[QT_FOTO_MINIMO] [int] NULL,
	[QT_FOTO_MAXIMO] [int] NULL,
	[CD_VERSAO] [int] NULL,
	[CD_RELEASE] [int] NULL,
	[CD_BUILD] [int] NULL,
	[DT_DATA] [smalldatetime] NULL,
PRIMARY KEY ([ID_PARAMETRO_MOBILE]),
CONSTRAINT FK_PARAM_MOB_REF_CAD_REG FOREIGN KEY([ID_REGIONAL]) REFERENCES [CAD_REGIONAL] ([ID_REGIONAL]) ON UPDATE NO ACTION ON DELETE NO ACTION,
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--RETORNO APR
CREATE TABLE [RETORNO_APR]
(
	[ID_RETORNO_APR] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_RETORNO_APR]),
	[ID_PERGUNTA] [BIGINT] NOT NULL,
	[ID_USUARIO] [BIGINT] NOT NULL,	
	[DS_RESPOSTA] [varchar](50) NULL,
	[DT_APR] [smalldatetime] NULL,
PRIMARY KEY ([ID_RETORNO_APR]),
CONSTRAINT FK_RET_APR_REF_CAD_PER_APR FOREIGN KEY([ID_PERGUNTA]) REFERENCES [CAD_PERGUNTA_APR] ([ID_PERGUNTA_APR]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_RET_APR_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--RETORNO_FOTO
CREATE TABLE [RETORNO_FOTO]
(
	[ID_RETORNO_FOTO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_RETORNO_FOTO]),
	[ID_LEITURA] [BIGINT] NOT NULL,
	[ID_USUARIO] [BIGINT] NOT NULL,
	[NM_NOME] [varchar](150) NULL,
	[DS_PATH] [varchar](300) NULL,
	[DT_FOTO] [datetime] NULL,
	[CD_LATITUDE] [varchar](50) NULL,
	[CD_LONGITUDE] [varchar](50) NULL,
	[NR_INSTALACAO] [varchar](20) NULL,
	[NR_MEDIDOR] [varchar](20) NULL,
	[DS_IMAGEM] [varchar](50) NULL,
	[DS_MARCA] [varchar](50) NULL, 
	[DS_MODELO] [varchar](50) NULL,
PRIMARY KEY ([ID_RETORNO_FOTO]),
CONSTRAINT FK_RET_FOTO_REF_MED_LEIT FOREIGN KEY([ID_LEITURA]) REFERENCES [MED_LEITURA] ([ID_LEITURA]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_RET_FOTO_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--LOG ACESSO
CREATE TABLE [LOG_ACESSO]
(
	[ID_LOG_ACESSO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_LOG_ACESSO]),
	[ID_USUARIO] [BIGINT] NOT NULL,
	[NR_IP] [varchar](100) NOT NULL,
	[NM_BROWSER] [varchar](50) NOT NULL,
	[DT_ACESSO] [datetime] NOT NULL,
PRIMARY KEY ([ID_LOG_ACESSO]),
CONSTRAINT FK_LOG_ACESSO_USE_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--LOG RETORNO LEITURA
CREATE TABLE [LOG_RETORNO_LEITURA]
(
	[ID_LOG_RETORNO_LEITURA] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_LOG_RETORNO_LEITURA]),
	[ID_USUARIO] [BIGINT] NOT NULL,
	[ID_RETORNO_LEITURA_ANTERIOR] [BIGINT] NOT NULL,
	[ID_RETORNO_LEITURA_ATUAL] [BIGINT] NOT NULL,
	[DS_ALTERACAO] [varchar](50) NOT NULL,
	[DT_ATUALIZACAO] [datetime] NOT NULL,
PRIMARY KEY ([ID_LOG_RETORNO_LEITURA]),
CONSTRAINT FK_LOG_RET_LEIT_USE_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_LOG_RET_LEIT_ANT_REF_MED_RET_LEIT FOREIGN KEY([ID_RETORNO_LEITURA_ANTERIOR]) REFERENCES [MED_RETORNO_LEITURA] ([ID_RETORNO_LEITURA]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_LOG_RET_LEIT_AT_REF_MED_RET_LEIT FOREIGN KEY([ID_RETORNO_LEITURA_ATUAL]) REFERENCES [MED_RETORNO_LEITURA] ([ID_RETORNO_LEITURA]) ON UPDATE NO ACTION ON DELETE NO ACTION,
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
--LOG RETORNO_FOTO
CREATE TABLE [LOG_RETORNO_FOTO]
(
	[ID_LOG_RETORNO_FOTO] [BIGINT] IDENTITY(1,1) NOT NULL, UNIQUE ([ID_LOG_RETORNO_FOTO]),
	[ID_RETORNO_FOTO] [BIGINT] NOT NULL,
	[ID_USUARIO] [BIGINT] NOT NULL,
	[DS_ALTERACAO] [varchar](50) NOT NULL,
	[DT_ATUALIZACAO] [datetime] NOT NULL,
PRIMARY KEY ([ID_LOG_RETORNO_FOTO]),
CONSTRAINT FK_LOG_RET_FOTO_REF_RET_FOTO FOREIGN KEY([ID_RETORNO_FOTO]) REFERENCES [RETORNO_FOTO] ([ID_RETORNO_FOTO]) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT FK_LOG_RET_FOTO_REF_SEG_USE FOREIGN KEY([ID_USUARIO]) REFERENCES [SEG_USUARIO] ([ID_USUARIO]) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
