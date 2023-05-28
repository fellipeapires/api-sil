INSERT INTO CAD_EMPRESA (NR_CNPJ, NM_NOME, NM_FANTASIA, NM_CIDADE, CD_SITUACAO) 
VALUES ('11.111.111/1111-11', 'EMPRESA PADRAO', 'PADRAO', 'FLORIANOPOLIS', 1);

INSERT INTO CAD_REGIONAL (ID_EMPRESA, NM_NOME, NM_CIDADE, CD_SITUACAO) 
VALUES (1, 'MATRIZ', 'FLORIANOPOLIS', 1);

INSERT INTO SEG_PERFIL_ACESSO (NM_NOME, CD_SITUACAO) VALUES ('DESENVOLVEDOR', 1);
INSERT INTO SEG_PERFIL_ACESSO (NM_NOME, CD_SITUACAO) VALUES ('MOBILE', 1);
INSERT INTO SEG_PERFIL_ACESSO (NM_NOME, CD_SITUACAO) VALUES ('USUARIO', 1);
INSERT INTO SEG_PERFIL_ACESSO (NM_NOME, CD_SITUACAO) VALUES ('CLIENTE', 1);

INSERT INTO SEG_USUARIO (ID_PERFIL_ACESSO, NR_CPF, NM_NOME, NM_LOGIN, NM_SENHA, DS_EMAIL, NR_MATRICULA, ST_ALTERA_SENHA, CD_SITUACAO) 
VALUES(1, '111.111.111-11', 'DESENVOLVEDOR', 'admin', '$2a$10$fRuM.0QAYrt.m9sWlYMH5eQqCcW9QVNQL/TaZQ8AXK133EPotvfOi','ti@floripark.com.br', '0000', 1, 1);

INSERT INTO SEG_USUARIO_REGIONAL (ID_REGIONAL, ID_USUARIO) VALUES (1, 1);

INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (1, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (2, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (3, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (4, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (5, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (6, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (7, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (8, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (9, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (10, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (11, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (12, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (13, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (14, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (15, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (16, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (17, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (18, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (51, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (52, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (53, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (54, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (55, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (56, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (57, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (58, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (59, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (60, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (61, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (62, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (63, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (64, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (65, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (66, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (67, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (68, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (81, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (82, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (83, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (84, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (85, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (86, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (87, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (88, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (89, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (90, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (91, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (92, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (93, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (94, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (95, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (96, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (97, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (98, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (300, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (301, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (302, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (303, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (304, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (100, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (305, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (306, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (307, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (308, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (309, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (310, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (311, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (312, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (313, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (314, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (315, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (316, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (317, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (318, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (319, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (320, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (321, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (322, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (323, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (324, 1);
INSERT INTO MED_GRUPO_FATURAMENTO (CD_GRUPO_FATURAMENTO, CD_SITUACAO) VALUES (325, 1);

INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (60, 'Dificuldade de Acesso', 1, 1, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (61, 'Consumidor nao autorizou a leitura', 1, 1, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (62, 'Leitura Ilegivel', 1, 1, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (63, 'Posicao do Medidor impossibilita leitura', 1, 1, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (640, 'Acrilico da tampa embacado_', 0, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (65, 'Medidor Retirado', 1, 1, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (67, 'Endereco nao localizado', 1, 1, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (68, 'Local interditado', 1, 1, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (70, 'Risco de Seguranca pessoal- SSM', 1, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (72, 'Medidor Mal Posicionado', 1, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (73, 'Medidor sem Identificacao', 1, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (74, 'Numero Medidor nao Confere', 1, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (75, 'Suspeita Consumo Irregular', 1, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (77, 'Leitura Realizada pelo Consumidor', 1, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (80, 'Cheiro de Gas no Local', 1, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (90, 'Consumidor fora de Lote', 1, 0, 0);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (91, 'Medidor Remoto nao Conectado', 1, 0, 0);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (92, 'Leitura conferida por foto', 1, 0, 0);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (79, 'Conversor de vol. (PTZ) embacado', 1, 0, 0);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (0, 'Normal', 1, 0, 1);
INSERT INTO MED_OCORRENCIA (CD_OCORRENCIA, NM_NOME, CD_SITUACAO, FL_TIPO_OCORRENCIA, FL_USO_MOBILE) VALUES (64, 'Acrilico da tampa embacado', 1, 0, 1);

INSERT INTO CAD_PERGUNTA_APR (NR_PERGUNTA_APR, DS_PERGUNTA_APR, CD_SITUACAO) VALUES (1, 'Voce esta em condicoes fisicas e psicologicas para entregar as contas?', 1);
INSERT INTO CAD_PERGUNTA_APR (NR_PERGUNTA_APR, DS_PERGUNTA_APR, CD_SITUACAO) VALUES (2, 'Foi verificado a existencia de caes?', 1);
INSERT INTO CAD_PERGUNTA_APR (NR_PERGUNTA_APR, DS_PERGUNTA_APR, CD_SITUACAO) VALUES (3, 'Todos os EPIs, acessorios e seu uniforme estao disponiveis e em boas condicoes?', 1);
INSERT INTO CAD_PERGUNTA_APR (NR_PERGUNTA_APR, DS_PERGUNTA_APR, CD_SITUACAO) VALUES (4, 'O local de entrega das contas encontra-se em boas condicoes?', 1);
INSERT INTO CAD_PERGUNTA_APR (NR_PERGUNTA_APR, DS_PERGUNTA_APR, CD_SITUACAO) VALUES (5, 'Voce se identificou no local atraves de palmas ou acionou a campainha?', 1);
INSERT INTO CAD_PERGUNTA_APR (NR_PERGUNTA_APR, DS_PERGUNTA_APR, CD_SITUACAO) VALUES (6, 'Verificado a existencia de animais (peconhentos, insetos ou domesticos)?', 1);

INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_INCLUIR', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_ALTERAR', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_BUSCAR', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_PESQUISAR', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_DELETAR', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_RESET_SENHA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_ALTERAR_STATUS', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_RELATORIOS', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_ADMINISTRATIVO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_ANALISE', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_LEITURA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_RETORNO_LEITURA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_PAINEL_CONFIGURACAO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_FECHAMENTO_MES', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_HOME', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_PESQUISA_RETORNO_LEITURA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_IMPORTACAO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_DISTRIBUICAO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_LIBERACAO_CARGA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_LANCAMENTO_LEITURA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_ACOMPANHAMENTO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_TAREFA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_ROTEIRO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_EMPRESA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_LOTE', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_OCORRENCIA', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_PERFIL_ACESSO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_REGIONAL', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_USUARIO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_REPASSE', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_EXPORTACAO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_SENHA_USUARIO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_AUDITORIA', 1); 
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_JORNADA_COLABORADOR', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_PRODUTIVIDADE_USUARIO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_PAINEL_MOBILE', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_PAINEL_CLIENTE', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_LOG_ACESSO', 1);
INSERT INTO SEG_PERMISSAO (NM_PERMISSAO, CD_SITUACAO) VALUES ('ROLE_MOBILE', 1);

INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 1);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 2);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 3);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 4);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 5);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 6);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 7);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 8);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 9);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 10);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 11);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 12);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 13);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 14);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 15);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 16);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 17);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 18);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 19);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 20);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 21);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 22);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 23);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 24);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 25);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 26);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 27);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 28);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 29);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 30);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 31);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 32);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 33);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 34);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 35);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 36);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 37);
INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (1, 38);

--INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) VALUES (2, 39);
