ALTER TABLE MED_DISTRIBUICAO_LEITURA_REGISTRO ADD CD_GRUPO_FATURAMENTO INT NULL, DT_ANO_MES_REF DATE NULL;

CREATE INDEX IDX_DISTRIBUICAOLEITURALOTEMES ON MED_DISTRIBUICAO_LEITURA_REGISTRO(CD_GRUPO_FATURAMENTO, DT_ANO_MES_REF);