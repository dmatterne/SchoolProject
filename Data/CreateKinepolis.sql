REM ======================================================================================
REM 				CreateKinepolis.sql
REM ======================================================================================

REM ======================================================================================
REM 				CREATE NIVEAU 1 en 2
REM ======================================================================================

CREATE TABLE FLM_RATINGS
(fra_id		 		NUMBER(2)	CONSTRAINT pr_fra_id 		PRIMARY KEY
,fra_name	 		VARCHAR2(20)	CONSTRAINT nn_fra_name		NOT NULL
						CONSTRAINT u_fra_name		UNIQUE
						CHECK(fra_name = initcap(fra_name))
)
/

CREATE TABLE SOUNDS
(snd_id		 		NUMBER(2)	CONSTRAINT pr_sounds 		PRIMARY KEY
,snd_name	 		VARCHAR2(10)  	CONSTRAINT nn_snd_name		NOT NULL
						CONSTRAINT u_snd_name		UNIQUE
)
/

CREATE TABLE SUBTITLES
(sbt_id		 		NUMBER(3)	CONSTRAINT pr_subtitles 	PRIMARY KEY
,sbt_name	 		VARCHAR2(10)	CONSTRAINT nn_sbt_name		NOT NULL
						CONSTRAINT u_sbt_name		UNIQUE
)
/

CREATE TABLE FORMATS
(frm_id		 		NUMBER(2)	CONSTRAINT pr_formats 		PRIMARY KEY
,frm_name	 		VARCHAR2(10)	CONSTRAINT nn_frm_name		NOT NULL
						CONSTRAINT u_frm_name		UNIQUE
)
/

CREATE TABLE COUNTRIES
(cnt_id				NUMBER(3) 	CONSTRAINT pr_countries 	PRIMARY KEY
,cnt_name	 		VARCHAR2(15)	CONSTRAINT nn_cnt_name		NOT NULL
						CHECK(cnt_name = initcap(cnt_name))
,cnt_currency	 		VARCHAR2(3)	CONSTRAINT nn_cnt_currency	NOT NULL
,cnt_tax	 		NUMBER(3)	CONSTRAINT nn_cnt_tax		NOT NULL
)
/

CREATE TABLE DISTRIBUTORS
(dst_id		 		NUMBER(3)	CONSTRAINT pr_dst_id 		PRIMARY KEY
,cnt_id		 		NUMBER(3)	CONSTRAINT rf_cnt_id 		REFERENCES COUNTRIES
,dst_name	 		VARCHAR2(30)	CONSTRAINT nn_dst_name		NOT NULL
						CHECK(dst_name = initcap(dst_name))	
,dst_address	 		VARCHAR2(50)	CONSTRAINT nn_dst_address	NOT NULL
,dst_zipcode	 		VARCHAR2(15)	CONSTRAINT nn_dst_zipcode	NOT NULL
,dst_city	 		VARCHAR2(20)	CONSTRAINT nn_dst_city		NOT NULL
,dst_phone	 		NUMBER(15)	CONSTRAINT nn_dst_phone		NOT NULL
,dst_fax	 		NUMBER(15)	CONSTRAINT nn_dst_fax		NOT NULL
,dst_email	 		VARCHAR(50)	CONSTRAINT nn_dst_email		NOT NULL
,dst_contact	 		VARCHAR(30)	CONSTRAINT nn_dst_contact	NOT NULL
)
/

REM WE HEBBEN ZIPCODE ALS VARCHAR GEKOZEN OMDAT IN SOMMIGE LANDEN ER POSTCODES BESTAAN ALS B-3434

CREATE TABLE ORG_FILMS
(orf_id 	 		NUMBER(8)	CONSTRAINT pr_org_films 	PRIMARY KEY
,orf_playing			VARCHAR(30)	CONSTRAINT nn_orf_playing	NOT NULL
,fli_id		 		NUMBER(20)	
)
/

CREATE TABLE NTN_FILMS
(ntf_id		 		NUMBER(15)	CONSTRAINT pr_ntn_films 	PRIMARY KEY
,ntf_name	 		VARCHAR(30)	CONSTRAINT nn_ntf_name		NOT NULL
,orf_id		 		NUMBER(8)	CONSTRAINT rf_orf_id 		REFERENCES ORG_FILMS	
,fra_id		 		NUMBER(2)	CONSTRAINT rf_flr_id 		REFERENCES FLM_RATINGS	
,ntf_status	 		VARCHAR2(11)	CONSTRAINT nn_ntf_status	NOT NULL
						CHECK(ntf_status = 'PLAYING' or ntf_status = 'NOT PLAYING')
,ntf_ages	 		NUMBER(2)	CONSTRAINT nn_ntf_ages		NOT NULL
)
/

REM ntf_ages is vanaf welke leeftijd deze film geschikt is in cijfers


CREATE TABLE LNG_VERSIONS
(lnv_id		 		NUMBER(15) 	CONSTRAINT pr_lng_versions PRIMARY KEY
,lnv_name	 		VARCHAR2(30)	CONSTRAINT nn_lnv_name		NOT NULL
,cnt_id		 		NUMBER(3)	CONSTRAINT rf_cnt_id2 		REFERENCES COUNTRIES
,ntf_id		 		NUMBER(11)	CONSTRAINT rf_ntn_id 		REFERENCES NTN_FILMS
,snd_id		 		NUMBER(2)	CONSTRAINT rf_snd_id 		REFERENCES SOUNDS
,sbt_id		 		NUMBER(3)	CONSTRAINT rf_sbt_id 		REFERENCES SUBTITLES
,frm_id		 		NUMBER(2)	CONSTRAINT rf_frm_id 		REFERENCES FORMATS
,lng_id		 		NUMBER(3)	
,lnv_releasedate 		DATE		CONSTRAINT nn_lnv_releasedate	NOT NULL
,lnv_length	 		NUMBER(4)	CONSTRAINT nn_lnv_length	NOT NULL
)
/

REM ORF_PLAYING IS BOOLEAN WAAR WORD GECONTROLEERD OF DE FILM OP HET MOMENT NOG IN BIOSCOOP WORD GESPEELD	


REM ======================================================================================
REM 				CREATE NIVEAU 3 en 4
REM ======================================================================================

CREATE TABLE CMP_FEATURES
(cmf_id				NUMBER(3)	CONSTRAINT pr_cmp_features 	PRIMARY KEY
,cmf_name			VARCHAR2(20)	CONSTRAINT nn_cmf_name		NOT NULL
,cnt_id				NUMBER(3)	CONSTRAINT rf_features_cnt_id	REFERENCES COUNTRIES
,cmf_address			VARCHAR2(50)	CONSTRAINT nn_cmf_address	NOT NULL
,cmf_constructionyear		NUMBER(4)	CONSTRAINT nn_cmf_constryr	NOT NULL
,cmf_type			VARCHAR2(30)	CONSTRAINT nn_cmf_type		NOT NULL
,cmf_hallamount			NUMBER(3)	CONSTRAINT nn_cmf_hallamon	NOT NULL
)
/

CREATE TABLE DISCOUNTS
(dsc_id				NUMBER(3)	CONSTRAINT pr_discounts		PRIMARY KEY
,dsc_name			VARCHAR2(30)	CONSTRAINT nn_dsc_name		NOT NULL
,dsc_amount			NUMBER(5,2)	CONSTRAINT nn_dsc_amount	NOT NULL
)
/

CREATE TABLE LANGUAGES
(lng_id				NUMBER(3)	CONSTRAINT pr_languages 	PRIMARY KEY
,lng_language			VARCHAR2(30)	CONSTRAINT nn_lng_language	NOT NULL
						CONSTRAINT u_lng_name		UNIQUE
)
/

CREATE TABLE CMP_LANGUAGES
(cmf_id				NUMBER(3)	CONSTRAINT rf_languages_cmf_id 	REFERENCES CMP_FEATURES
,lng_id				NUMBER(3)	CONSTRAINT rf_languages_lng_id	REFERENCES LANGUAGES
,CONSTRAINT pr_cmp_languages PRIMARY KEY(cmf_id, lng_id)
)
/

ALTER TABLE LNG_VERSIONS ADD
(CONSTRAINT rf_lng_id  FOREIGN KEY(lng_id) REFERENCES LANGUAGES(lng_id)		
)
/


CREATE TABLE CUSTOMERS
(cst_id				NUMBER(10)	CONSTRAINT pr_customer		PRIMARY KEY
,cst_birthdate			DATE		CONSTRAINT nn_cst_birthdate	NOT NULL
,cst_name			VARCHAR2(40) 	CONSTRAINT nn_cst_name		NOT NULL
,cst_address			VARCHAR2(40)
,cst_tel			NUMBER(15)	
,cst_email			VARCHAR2(30)
)
/


CREATE TABLE HLL_COMPLEX
(hlc_id				NUMBER(3)	
,cmf_id				NUMBER(3)	CONSTRAINT rf_hll_complx_cmf_id REFERENCES CMP_FEATURES
,hlc_twinseat			VARCHAR(3)	CONSTRAINT nn_hlc_twinseat	NOT NULL
						CHECK(hlc_twinseat = 'YES' OR hlc_twinseat = 'NO')
,hlc_wheelchair			VARCHAR(3)	CONSTRAINT nn_hlc_wheelchair	NOT NULL
						CHECK(hlc_wheelchair = 'YES' OR hlc_wheelchair = 'NO')
,hlc_digitalversion		VARCHAR(3)	CONSTRAINT nn_hlc_digitalvrs	NOT NULL
						CHECK(hlc_digitalversion = 'YES' OR hlc_digitalversion = 'NO')
,hlc_cupholder			VARCHAR(3)	CONSTRAINT nn_hlc_cupholder	NOT NULL
						CHECK(hlc_cupholder = 'YES' OR hlc_cupholder = 'NO')
,hlc_capacity			NUMBER(4)	CONSTRAINT nn_hlc_capacity	NOT NULL
,hlc_bookable			VARCHAR(3)	CONSTRAINT nn_hlc_bookable	NOT NULL
						CHECK(hlc_bookable = 'YES' OR hlc_bookable = 'NO')
,hlc_distancescreenprojection	NUMBER(4)	CONSTRAINT nn_hlc_distscrproj	NOT NULL
,hlc_spaceinfrontofscreen	VARCHAR(10)	CONSTRAINT nn_hlc_spacefrscr	NOT NULL
,hlc_screenwidth		VARCHAR(10)	CONSTRAINT nn_hlc_screenwidth	NOT NULL
,CONSTRAINT pr_hll_complex PRIMARY KEY(hlc_id, cmf_id)
)
/



CREATE TABLE USERS
(usr_id                       NUMBER(38)      CONSTRAINT pk_users               PRIMARY KEY
,usr_name                     VARCHAR2(50)    CONSTRAINT nn_usr_name            NOT NULL
,usr_address                  VARCHAR2(40)    CONSTRAINT nn_usr_address         NOT NULL
,usr_birthday                 DATE            CONSTRAINT nn_usr_birthdate       NOT NULL
,usr_email                    VARCHAR2(30)    CONSTRAINT nn_usr_email           NOT NULL
,usr_function                 VARCHAR2(30)    CONSTRAINT nn_usr_function        NOT NULL
,usr_password                 VARCHAR2(100)    CONSTRAINT nn_usr_password        NOT NULL
,usr_sex                      VARCHAR2(1)     CONSTRAINT nn_usr_sex             NOT NULL
                                              CHECK(usr_sex = 'M' OR usr_sex = 'V')
,usr_tel                      NUMBER(15)      CONSTRAINT nn_usr_tel             NOT NULL
,usr_username                 VARCHAR2(15)    CONSTRAINT nn_usr_username        NOT NULL
                                              CONSTRAINT uq_usr_username        UNIQUE
,cnt_id                       NUMBER(3)       CONSTRAINT rf_usr_countries       REFERENCES COUNTRIES(cnt_id)
)
/

REM ========================================================================================
REM 				CREATE COMATO
REM ========================================================================================

CREATE TABLE PERSONS
(prs_id		 	NUMBER(10)	CONSTRAINT pr_persons 		PRIMARY KEY
,prs_name	 	VARCHAR2(30)	CONSTRAINT nn_prs_name		NOT NULL
,prs_birthdate   	DATE		CONSTRAINT nn_prs_birthdate	NOT NULL
,prs_sex	 	VARCHAR2(1)	CONSTRAINT nn_prs_sex 		NOT NULL
				CHECK(prs_sex = 'M' OR prs_sex = 'V')
,cnt_id		 	NUMBER(3)	CONSTRAINT rf_cnt_id3		REFERENCES COUNTRIES
)
/

CREATE TABLE ROLES
(rls_id 	 	NUMBER(5)	CONSTRAINT pr_roles		PRIMARY KEY
,rls_description 	VARCHAR2(15)	CONSTRAINT nn_rls_description	NOT NULL
)
/

CREATE TABLE SCORES
(scr_id		 	NUMBER(3)	CONSTRAINT pr_scores		PRIMARY KEY
,scr_description 	VARCHAR2(50)	CONSTRAINT nn_scr_description	NOT NULL
					CONSTRAINT u_scr_description	UNIQUE
)
/

CREATE TABLE GENRES
(gnr_id		 	NUMBER(4)	CONSTRAINT pr_genres		PRIMARY KEY
,gnr_description 	VARCHAR2(40)    CONSTRAINT nn_gnr_description	NOT NULL
					CONSTRAINT u_gnr_description	UNIQUE	
)
/

CREATE TABLE FLM_INFO
(fli_id			NUMBER(15)	CONSTRAINT pr_flm_info		PRIMARY KEY	
,fli_name		VARCHAR2(30)	CONSTRAINT nn_fli_name		NOT NULL
,fli_length		NUMBER(3)	CONSTRAINT nn_fli_length	NOT NULL
,fli_website		VARCHAR2(50)	
,fli_description	LONG	
,fli_color		VARCHAR2(10)	CONSTRAINT nn_fli_color 	NOT NULL
,fli_releasedate	DATE		CONSTRAINT nn_fli_releasedate	NOT NULL
,fli_soundtrack		VARCHAR2(50)	
,fli_format		VARCHAR2(10)	CONSTRAINT nn_fli_format	NOT NULL
,fli_price		NUMBER(5)	CONSTRAINT nn_fli_price		NOT NULL
,fli_targetgroup	VARCHAR(20)	
,gnr_id			NUMBER(4)	CONSTRAINT rf_gnr_id		REFERENCES GENRES
,fli_productionhouse	VARCHAR(30)	CONSTRAINT nn_fli_prodhouse	NOT NULL
,cnt_id			NUMBER(3)	CONSTRAINT rf_cnt_id4		REFERENCES COUNTRIES
,dst_id			NUMBER(3)	CONSTRAINT rf_dst_id		REFERENCES DISTRIBUTORS
)
/


CREATE TABLE PROGRAMMING
(prg_id				NUMBER(30)	CONSTRAINT pr_programming 	PRIMARY KEY
,cmf_id				NUMBER(3)	
,hlc_id				NUMBER(3)
-- fli id instead of ntf id
--,ntf_id				NUMBER(11)	CONSTRAINT rf_prgramming_ntn_id REFERENCES NTN_FILMS
,fli_id                         NUMBER(15)      CONSTRAINT rf_prgramming_fli_id REFERENCES FLM_INFO (fli_id)
,prg_datetime			DATE		CONSTRAINT nn_prg_datetime	NOT NULL
,prg_price			NUMBER(4,2)	CONSTRAINT nn_prg_price		NOT NULL
,CONSTRAINT fk_prg_hlc FOREIGN KEY(hlc_id,cmf_id) REFERENCES HLL_COMPLEX(hlc_id,cmf_id)
)
/

CREATE TABLE TICKETS
(tck_id				NUMBER(38)	CONSTRAINT pr_tickets 		PRIMARY KEY
,prg_id				NUMBER(30)	CONSTRAINT rf_tickets_prg_id	REFERENCES PROGRAMMING
,cst_id				NUMBER(30)	CONSTRAINT rf_tickets_cst_id	REFERENCES CUSTOMERS
,dsc_id				NUMBER(3)	CONSTRAINT rf_tickets_dsc_id	REFERENCES DISCOUNTS	
,tck_price			NUMBER(3,2)	CONSTRAINT nn_tck_price		NOT NULL
,usr_id			        NUMBER(15)	CONSTRAINT rf_tck_users		REFERENCES USERS(usr_id)
)
/

ALTER TABLE ORG_FILMS ADD
(CONSTRAINT fk_fli_id FOREIGN KEY(fli_id) REFERENCES FLM_INFO
)
/

CREATE TABLE FLM_CREWS
(rls_id			NUMBER(5)	CONSTRAINT rf_rls_id		REFERENCES ROLES
,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id2		REFERENCES FLM_INFO
,prs_id			NUMBER(10)	CONSTRAINT rf_prs_id		REFERENCES PERSONS
,flc_character		VARCHAR2(10)
,flc_award		VARCHAR2(20)
,CONSTRAINT pr_flm_crews PRIMARY KEY(rls_id,fli_id,prs_id)
)
/


CREATE TABLE FLM_SCORES
(fls_id			NUMBER(11)	CONSTRAINT pr_flm_scores	PRIMARY KEY
,scr_id			NUMBER(3)	CONSTRAINT rf_scr_id		REFERENCES SCORES
,fls_name		VARCHAR2(30)	CONSTRAINT nn_fls_name		NOT NULL
,fls_public		VARCHAR2(3)	CONSTRAINT nn_fls_public	NOT NULL
					CHECK(fls_public = 'YES' OR fls_public = 'NO')
,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id4		REFERENCES FLM_INFO
)
/

CREATE TABLE FLM_REVIEWS
(fre_id			NUMBER(10)	CONSTRAINT pr_flm_reviews	PRIMARY KEY
,fre_title		VARCHAR2(40)	CONSTRAINT nn_fre_title		NOT NULL
,fre_content		LONG		CONSTRAINT nn_fre_content	NOT NULL
,fre_name		VARCHAR2(30)	CONSTRAINT nn_fre_name		NOT NULL
,fre_public		VARCHAR2(3)	CONSTRAINT nn_fre_public	NOT NULL
					CHECK(fre_public = 'YES' OR fre_public = 'NO')
,lng_id			NUMBER(3)	CONSTRAINT rf_lng_id2		REFERENCES LANGUAGES
,fls_id			NUMBER(11)	CONSTRAINT rf_fls_id3		REFERENCES FLM_SCORES
)
/

CREATE TABLE TRAILERS
(trl_id			NUMBER(2)
,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id5		REFERENCES FLM_INFO
,trl_trailer		BLOB		CONSTRAINT nn_trl_trailer	NOT NULL
,trl_length             NUMBER(15)      CONSTRAINT nn_trl_length        NOT NULL
,trl_name               VARCHAR2(50)    CONSTRAINT nn_trl_name          NOT NULL
,CONSTRAINT pr_trailers PRIMARY KEY(trl_id,fli_id)
)
/

CREATE TABLE PHOTOS
(pht_id			NUMBER(2)
,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id6		REFERENCES FLM_INFO
,pht_photo		BLOB		CONSTRAINT nn_pht_photo		NOT NULL
,pht_contenttype        VARCHAR2(50)    CONSTRAINT nn_pht_content       NOT NULL
,pht_length             NUMBER(15)      CONSTRAINT nn_pht_length        NOT NULL
,pht_name               VARCHAR2(50)    CONSTRAINT nn_pht_name          NOT NULL
,CONSTRAINT pr_photos PRIMARY KEY(pht_id,fli_id)
)
/

CREATE TABLE FLM_AWARDS
(fla_id			NUMBER(11)	CONSTRAINT pr_flm_awards	PRIMARY KEY 
,fla_name		VARCHAR2(15)	CONSTRAINT nn_fla_name		NOT NULL
,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id7		REFERENCES FLM_INFO
)
/

CREATE TABLE LOGINS
(lgn_username		VARCHAR2(15)    CONSTRAINT nn_lgn_username      NOT NULL
,lgn_password		VARCHAR2(15)	CONSTRAINT nn_lgn_password	NOT NULL
,usr_id			NUMBER(15)	CONSTRAINT rf_lgn_users		REFERENCES USERS(usr_id)
,lgn_function		VARCHAR2(70)    CONSTRAINT nn_lgn_function      NOT NULL
,cnt_id                 NUMBER(3)	CONSTRAINT rf_lgn_countries	REFERENCES COUNTRIES(cnt_id)
,lgn_address            VARCHAR2(40)    CONSTRAINT nn_lgn_address       NOT NULL
,lgn_birthday           DATE            CONSTRAINT nn_lgn_birthdate     NOT NULL
,lgn_email              VARCHAR2(30)    CONSTRAINT nn_lgn_email         NOT NULL 
,lgn_id                 NUMBER(38)      CONSTRAINT pk_logins            PRIMARY KEY
,lgn_name               VARCHAR2(15)    CONSTRAINT nn_lgn_name          NOT NULL
,lgn_sex                VARCHAR2(1)     CONSTRAINT nn_lgn_sex           NOT NULL
,lgn_tel                NUMBER(15)      CONSTRAINT nn_lgn_tel           NOT NULL
)
/

