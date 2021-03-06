REM ===========================================================
REM		TriggersKinepolis.sql
REM ===========================================================

CREATE OR REPLACE TRIGGER FLM_RATINGS_AUTONR 
BEFORE INSERT ON FLM_RATINGS 
FOR EACH ROW
BEGIN
  SELECT seq_fra.nextval into :new.fra_id from dual;
END;
/

CREATE OR REPLACE TRIGGER SOUNDS_AUTONR 
BEFORE INSERT ON SOUNDS
FOR EACH ROW
BEGIN
  SELECT seq_snd.nextval into :new.snd_id from dual;
END;
/

CREATE OR REPLACE TRIGGER SUBTITLES_AUTONR 
BEFORE INSERT ON SUBTITLES
FOR EACH ROW
BEGIN
  SELECT seq_frm.nextval into :new.sbt_id from dual;
END;
/

CREATE OR REPLACE TRIGGER FORMATS_AUTONR 
BEFORE INSERT ON FORMATS
FOR EACH ROW
BEGIN
  SELECT seq_sbt.nextval into :new.frm_id from dual;
END;
/

CREATE OR REPLACE TRIGGER COUNTRIES_AUTONR 
BEFORE INSERT ON COUNTRIES
FOR EACH ROW
BEGIN
  SELECT seq_cnt.nextval into :new.cnt_id from dual;
END;
/

CREATE OR REPLACE TRIGGER DISTRIBUTORS_AUTONR 
BEFORE INSERT ON DISTRIBUTORS
FOR EACH ROW
BEGIN
  SELECT seq_dst.nextval into :new.dst_id from dual;
END;
/

CREATE OR REPLACE TRIGGER ORG_FILMS_AUTONR 
BEFORE INSERT ON ORG_FILMS
FOR EACH ROW
BEGIN
  SELECT seq_orf.nextval into :new.orf_id from dual;
END;
/

CREATE OR REPLACE TRIGGER NTN_FILMS_AUTONR 
BEFORE INSERT ON NTN_FILMS
FOR EACH ROW
BEGIN
  SELECT seq_ntf.nextval into :new.ntf_id from dual;
END;
/

CREATE OR REPLACE TRIGGER LNG_VERSIONS_AUTONR 
BEFORE INSERT ON LNG_VERSIONS
FOR EACH ROW
BEGIN
  SELECT seq_lnv.nextval into :new.lnv_id from dual;
END;
/

CREATE OR REPLACE TRIGGER CMP_FEATURES_AUTONR 
BEFORE INSERT ON CMP_FEATURES
FOR EACH ROW
BEGIN
  SELECT seq_cmf.nextval into :new.cmf_id from dual;
END;
/

CREATE OR REPLACE TRIGGER DISCOUNTS_AUTONR 
BEFORE INSERT ON DISCOUNTS
FOR EACH ROW
BEGIN
  SELECT seq_dsc.nextval into :new.dsc_id from dual;
END;
/

CREATE OR REPLACE TRIGGER LANGUAGES_AUTONR 
BEFORE INSERT ON LANGUAGES
FOR EACH ROW
BEGIN
  SELECT seq_lng.nextval into :new.lng_id from dual;
END;
/

CREATE OR REPLACE TRIGGER CUSTOMERS_AUTONR 
BEFORE INSERT ON CUSTOMERS
FOR EACH ROW
BEGIN
  SELECT seq_cst.nextval into :new.cst_id from dual;
END;
/

CREATE OR REPLACE TRIGGER HLL_COMPLEX_AUTONR 
BEFORE INSERT ON HLL_COMPLEX
FOR EACH ROW
BEGIN
  SELECT seq_hlc.nextval into :new.hlc_id from dual;
END;
/

CREATE OR REPLACE TRIGGER PROGRAMMING_AUTONR 
BEFORE INSERT ON PROGRAMMING
FOR EACH ROW
BEGIN
  SELECT seq_prg.nextval into :new.prg_id from dual;
END;
/

CREATE OR REPLACE TRIGGER TICKETS_AUTONR 
BEFORE INSERT ON TICKETS
FOR EACH ROW
BEGIN
  SELECT seq_tck.nextval into :new.tck_id from dual;
END;
/

CREATE OR REPLACE TRIGGER PERSONS_AUTONR 
BEFORE INSERT ON PERSONS
FOR EACH ROW
BEGIN
  SELECT seq_prs.nextval into :new.prs_id from dual;
END;
/

CREATE OR REPLACE TRIGGER ROLES_AUTONR 
BEFORE INSERT ON ROLES
FOR EACH ROW
BEGIN
  SELECT seq_rls.nextval into :new.rls_id from dual;
END;
/

CREATE OR REPLACE TRIGGER SCORES_AUTONR 
BEFORE INSERT ON SCORES
FOR EACH ROW
BEGIN
  SELECT seq_scr.nextval into :new.scr_id from dual;
END;
/

CREATE OR REPLACE TRIGGER GENRES_AUTONR 
BEFORE INSERT ON GENRES
FOR EACH ROW
BEGIN
  SELECT seq_gnr.nextval into :new.gnr_id from dual;
END;
/

CREATE OR REPLACE TRIGGER FLM_INFO_AUTONR 
BEFORE INSERT ON FLM_INFO
FOR EACH ROW
BEGIN
  SELECT seq_fli.nextval into :new.fli_id from dual;
END;
/

CREATE OR REPLACE TRIGGER FLM_REVIEWS_AUTONR 
BEFORE INSERT ON FLM_REVIEWS
FOR EACH ROW
BEGIN
  SELECT seq_fre.nextval into :new.fre_id from dual;
END;
/

CREATE OR REPLACE TRIGGER FLM_SCORES_AUTONR 
BEFORE INSERT ON FLM_SCORES
FOR EACH ROW
BEGIN
  SELECT seq_fls.nextval into :new.fls_id from dual;
END;
/

CREATE OR REPLACE TRIGGER FLM_AWARDS_AUTONR 
BEFORE INSERT ON FLM_AWARDS				
FOR EACH ROW
BEGIN
  SELECT seq_fla.nextval into :new.fla_id from dual;
END;
/

CREATE OR REPLACE TRIGGER USERS_AUTONR 
BEFORE INSERT ON USERS				
FOR EACH ROW
BEGIN
  SELECT seq_usr.nextval into :new.usr_id from dual;
END;
/

CREATE OR REPLACE TRIGGER LOGINS_AUTONR 
BEFORE INSERT ON LOGINS				
FOR EACH ROW
BEGIN
  SELECT seq_lgn.nextval into :new.lgn_id from dual;
END;
/