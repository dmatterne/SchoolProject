REM ============================================
REM DropKinepolis.sql
REM ============================================


REM ====================================
REM DROP INDEXEN
REM ====================================
DROP INDEX IND_LNV6 
/

DROP INDEX IND_LNV7
/

DROP INDEX IND_CST
/

DROP INDEX IND_PRG
/

DROP INDEX IND_PRG2
/

DROP INDEX IND_PRG3
/

DROP INDEX IND_TCK
/

DROP INDEX IND_TCK2
/

DROP INDEX IND_TCK3
/

DROP INDEX IND_PRS
/

DROP INDEX IND_PRS2
/

DROP INDEX IND_FLI
/

DROP INDEX IND_FLI2
/

DROP INDEX IND_FLI3
/

DROP INDEX IND_FLI4
/

DROP INDEX IND_FRE
/

DROP INDEX IND_FRE2
/

DROP INDEX IND_FLS
/

DROP INDEX IND_FLS2
/

DROP INDEX IND_FLA
/

REM ====================================
REM DROP SEQUENCES
REM ====================================

DROP SEQUENCE seq_ntf
/

DROP SEQUENCE seq_lnv
/

DROP SEQUENCE seq_prs
/

DROP SEQUENCE seq_rls
/

DROP SEQUENCE seq_scr
/

DROP SEQUENCE seq_gnr
/

DROP SEQUENCE seq_fli
/

DROP SEQUENCE seq_fre
/

DROP SEQUENCE seq_fls
/

DROP SEQUENCE seq_fla
/

DROP SEQUENCE seq_cmf	
/

DROP SEQUENCE seq_dsc	
/

DROP SEQUENCE seq_lng	
/

DROP SEQUENCE seq_cst	
/

DROP SEQUENCE seq_hlc	
/

DROP SEQUENCE seq_prg	
/

DROP SEQUENCE seq_tck
/

DROP SEQUENCE seq_orf
/

DROP SEQUENCE seq_dst
/

DROP SEQUENCE seq_fra
/

DROP SEQUENCE seq_snd
/

DROP SEQUENCE seq_frm
/

DROP SEQUENCE seq_sbt
/

DROP SEQUENCE seq_cnt
/

DROP SEQUENCE seq_lgn
/

DROP SEQUENCE seq_usr
/

REM ====================================
REM DROP Synoniemen
REM ====================================

DROP SYNONYM SN_FRA 
/

DROP SYNONYM SN_SND 
/

DROP SYNONYM SN_SBT 
/

DROP SYNONYM SN_FRM 
/

DROP SYNONYM SN_CNT 
/

DROP SYNONYM SN_DST 
/

DROP SYNONYM SN_NTF 
/

DROP SYNONYM SN_LNV 
/

DROP SYNONYM SN_ORF 
/

DROP SYNONYM SN_PRS
/

DROP SYNONYM SN_RLS
/

DROP SYNONYM SN_SCR
/

DROP SYNONYM SN_GNR
/

DROP SYNONYM SN_FLI
/

DROP SYNONYM SN_FLC
/

DROP SYNONYM SN_FRE
/

DROP SYNONYM SN_FLS
/

DROP SYNONYM SN_TRL 
/

DROP SYNONYM SN_PHT 
/

DROP SYNONYM SN_FLA 
/

DROP SYNONYM SN_TCK 
/

DROP SYNONYM SN_PRG 
/

DROP SYNONYM SN_LNG 
/

DROP SYNONYM SN_HLC
/

DROP SYNONYM SN_DSC
/

DROP SYNONYM SN_CST 
/

DROP SYNONYM SN_CMF 
/

DROP SYNONYM SN_CLA 
/

DROP SYNONYM SN_LGN
/

DROP SYNONYM SN_USR
/

REM ==================================
REM  Droppen van tabellen
REM ==================================

DROP TABLE FLM_RATINGS 		CASCADE CONSTRAINTS
/

DROP TABLE SOUNDS 		CASCADE CONSTRAINTS
/

DROP TABLE SUBTITLES 		CASCADE CONSTRAINTS
/

DROP TABLE FORMATS		CASCADE CONSTRAINTS
/
	
DROP TABLE COUNTRIES 		CASCADE CONSTRAINTS
/

DROP TABLE DISTRIBUTORS 	CASCADE CONSTRAINTS
/

DROP TABLE NTN_FILMS		CASCADE CONSTRAINTS
/

DROP TABLE LNG_VERSIONS		CASCADE CONSTRAINTS
/

DROP TABLE ORG_FILMS		CASCADE CONSTRAINTS
/

DROP TABLE PERSONS		CASCADE CONSTRAINTS
/

DROP TABLE ROLES		CASCADE CONSTRAINTS
/

DROP TABLE SCORES		CASCADE CONSTRAINTS
/

DROP TABLE GENRES		CASCADE CONSTRAINTS
/

DROP TABLE FLM_INFO		CASCADE CONSTRAINTS
/

DROP TABLE FLM_CREWS		CASCADE CONSTRAINTS
/

DROP TABLE FLM_REVIEWS		CASCADE CONSTRAINTS
/

DROP TABLE FLM_SCORES		CASCADE CONSTRAINTS
/

DROP TABLE TRAILERS		CASCADE CONSTRAINTS
/

DROP TABLE PHOTOS		CASCADE CONSTRAINTS
/

DROP TABLE FLM_AWARDS		CASCADE CONSTRAINTS
/

DROP TABLE CMP_FEATURES 	CASCADE CONSTRAINTS
/

DROP TABLE CMP_LANGUAGES 	CASCADE CONSTRAINTS
/

DROP TABLE DISCOUNTS 		CASCADE CONSTRAINTS
/

DROP TABLE HLL_COMPLEX 		CASCADE CONSTRAINTS
/

DROP TABLE LANGUAGES 		CASCADE CONSTRAINTS
/

DROP TABLE TICKETS 		CASCADE CONSTRAINTS
/

DROP TABLE PROGRAMMING 		CASCADE CONSTRAINTS
/

DROP TABLE CUSTOMERS 		CASCADE CONSTRAINTS
/

DROP TABLE LOGINS 		CASCADE CONSTRAINTS
/

DROP TABLE USERS                CASCADE CONSTRAINTS
/

REM ==================================
REM  Droppen van views
REM ==================================

DROP VIEW VW_FLM_INFO
/

DROP VIEW VW_FLM_AWARDS
/

REM ============================================