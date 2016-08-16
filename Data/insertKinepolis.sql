REM ======================================================
REM InsertKinepolis.sql
REM ======================================================


rem /~---------------------~\
rem |      FILM RATINGS     |
rem \~---------------------~/

INSERT INTO SN_FRA(FRA_NAME)
VALUES('Very Bad')
/

INSERT INTO SN_FRA(FRA_NAME)
VALUES('Bad')
/

INSERT INTO SN_FRA(FRA_NAME)
VALUES('Good')
/

INSERT INTO SN_FRA(FRA_NAME)
VALUES('Very Good')
/

rem /~---------------------~\
rem |         SOUND         |
rem \~---------------------~/

INSERT INTO SN_SND(SND_NAME)
VALUES('a')
/

INSERT INTO SN_SND(SND_NAME)
VALUES('b')
/

INSERT INTO SN_SND(SND_NAME)
VALUES('c')
/

rem /~---------------------~\
rem |       SUBTITLES       |
rem \~---------------------~/


INSERT INTO SN_SBT(SBT_NAME)
VALUES('Dutch')
/

INSERT INTO SN_SBT(SBT_NAME)
VALUES('French')
/

INSERT INTO SN_SBT(SBT_NAME)
VALUES('German')
/


rem /~---------------------~\
rem |        FORMATS        |
rem \~---------------------~/


INSERT INTO SN_FRM(FRM_NAME)
VALUES('a')
/

INSERT INTO SN_FRM(FRM_NAME)
VALUES('b')
/

INSERT INTO SN_FRM(FRM_NAME)
VALUES('c')
/


rem /~---------------------~\
rem |       COUNTRIES       |
rem \~---------------------~/


INSERT INTO SN_CNT(CNT_NAME, CNT_CURRENCY, CNT_TAX)
VALUES('Amerika','$',3)
/

INSERT INTO SN_CNT(CNT_NAME, CNT_CURRENCY, CNT_TAX)
VALUES('Belgium','€',5)
/

INSERT INTO SN_CNT(CNT_NAME, CNT_CURRENCY, CNT_TAX)
VALUES('Holland','€',10)
/

INSERT INTO SN_CNT(CNT_NAME, CNT_CURRENCY, CNT_TAX)
VALUES('France','€',9)
/

INSERT INTO SN_CNT(CNT_NAME, CNT_CURRENCY, CNT_TAX)
VALUES('Germany','€',7)
/

rem /~---------------------~\
rem |     DISTRIBUTORS      |
rem \~---------------------~/



INSERT INTO SN_DST(CNT_ID, DST_NAME, DST_ADDRESS, DST_ZIPCODE, DST_CITY, DST_PHONE, DST_FAX, DST_EMAIL, DST_CONTACT) 
VALUES(1,'20th Century Fox','10201 W. Pico Blvd.','CA 90035','Los Angeles', 3103691000,310-369-8726,'Example@fox.com','Vos Fox')
/

INSERT INTO SN_DST(CNT_ID, DST_NAME, DST_ADDRESS, DST_ZIPCODE, DST_CITY, DST_PHONE, DST_FAX, DST_EMAIL, DST_CONTACT) 
VALUES(1,'Warner Bros','4000 Warner Blvd Bldg 505','CA 91522','Burbank',8189546000, 8189867565 ,'privacy@wb.com','Barny Wos')
/

INSERT INTO SN_DST(CNT_ID, DST_NAME, DST_ADDRESS, DST_ZIPCODE, DST_CITY, DST_PHONE, DST_FAX, DST_EMAIL, DST_CONTACT) 
VALUES(1,'Columbia Tristar','10202 West Washington Blvd.','CA 90232','Culver City', 3102444000 ,3102442626,'email@CTristar.com','Columbus Rastir') 
/


rem /~---------------------~\
rem |   COMPLEXFEATURES     |
rem \~---------------------~/


INSERT INTO SN_CMF(CMF_NAME, CNT_ID, CMF_ADDRESS, CMF_CONSTRUCTIONYEAR, CMF_TYPE, CMF_HALLAMOUNT)
VALUES('Kinepolis Hasselt',2,'Via Media 1 3500 Hasselt',1990,'Kinepolis',14)
/

INSERT INTO SN_CMF(CMF_NAME, CNT_ID, CMF_ADDRESS, CMF_CONSTRUCTIONYEAR, CMF_TYPE, CMF_HALLAMOUNT)
VALUES('Metropolis Antwerpen',2,'Groenendaallaan 393',1988,'Metropolis',22)
/


rem /~---------------------~\
rem |       LANGUAGES       |
rem \~---------------------~/


INSERT INTO SN_LNG(LNG_LANGUAGE)
VALUES('Dutch')
/

INSERT INTO SN_LNG(LNG_LANGUAGE)
VALUES('English')
/

INSERT INTO SN_LNG(LNG_LANGUAGE)
VALUES('French')
/

INSERT INTO SN_LNG(LNG_LANGUAGE)
VALUES('Swahili')
/

INSERT INTO SN_LNG(LNG_LANGUAGE)
VALUES('German')
/

INSERT INTO SN_LNG(LNG_LANGUAGE)
VALUES('Chinese')
/

rem /~---------------------~\
rem |     CMP_LANGUAGES     |
rem \~---------------------~/

INSERT INTO SN_CLA(cmf_id, lng_id)
VALUES(1, 1)
/

INSERT INTO SN_CLA(cmf_id, lng_id)
VALUES(2, 1)
/

rem /~---------------------~\
rem |       CUSTOMERS       |
rem \~---------------------~/

INSERT INTO SN_CST(cst_birthdate, cst_name, cst_address, cst_tel, cst_email)
VALUES('06-JUL-1960', 'Jos Reekmans', 'Genkersteenweg 12',011871115, 'j.reekmans@pandora.be')
/

INSERT INTO SN_CST(cst_birthdate, cst_name, cst_address, cst_tel, cst_email)
VALUES('01-APR-1980', 'Carlo De Bakker', 'Bergstraat 198',011729199, 'carlito@hotmail.com')
/

INSERT INTO SN_CST(cst_birthdate, cst_name, cst_address, cst_tel, cst_email)
VALUES('01-APR-1971', 'Peter Ackaert', 'Havenweg 2',011272474, 'peter_ackaert@belgacom.be')
/


rem /~---------------------~\
rem |      HLL_COMPLEX      |
rem \~---------------------~/


INSERT INTO SN_HLC(cmf_id, hlc_twinseat, hlc_wheelchair, hlc_digitalversion, hlc_cupholder, hlc_capacity, hlc_bookable, hlc_distancescreenprojection, hlc_spaceinfrontofscreen, hlc_screenwidth)
VALUES(1, 'YES', 'NO', 'YES', 'YES', 210, 'NO', 23, '20 x 11','18 x 8')
/

INSERT INTO SN_HLC(cmf_id, hlc_twinseat, hlc_wheelchair, hlc_digitalversion, hlc_cupholder, hlc_capacity, hlc_bookable, hlc_distancescreenprojection, hlc_spaceinfrontofscreen, hlc_screenwidth)
VALUES(2, 'YES', 'YES', 'YES', 'NO', 390, 'NO', 26, '18 x 11','20 x 8')
/

INSERT INTO SN_HLC(cmf_id, hlc_twinseat, hlc_wheelchair, hlc_digitalversion, hlc_cupholder, hlc_capacity, hlc_bookable, hlc_distancescreenprojection, hlc_spaceinfrontofscreen, hlc_screenwidth)
VALUES(2, 'YES', 'NO', 'YES', 'NO', 220, 'YES', 20, '22 x 11','20 x 10')
/


rem /~---------------------~\
rem |   GENRES              |
rem \~---------------------~/


INSERT INTO SN_GNR(gnr_description)
VALUES('Movie for kids')
/

INSERT INTO SN_GNR(gnr_description)
VALUES('Comedy')
/

INSERT INTO SN_GNR(gnr_description)
VALUES('Horror')
/

INSERT INTO SN_GNR(gnr_description)
VALUES('Kinderen')
/

INSERT INTO SN_GNR(gnr_description)
VALUES('Humor')
/

INSERT INTO SN_GNR(gnr_description)
VALUES('Drama')
/

INSERT INTO SN_GNR(gnr_description)
VALUES('Actie')
/

INSERT INTO SN_GNR(gnr_description)
VALUES('Thriller')
/

rem /~---------------------~\
rem |       FILMINFO        |
rem \~---------------------~/

REM Hier zit nog ne fout op die prijs!

INSERT INTO SN_FLI(fli_name, fli_length, fli_website, fli_description, fli_color, fli_releasedate, fli_soundtrack, fli_format, fli_price, fli_targetgroup, gnr_id, fli_productionhouse, cnt_id, dst_id)
VALUES('The Lion King',120,'www.thelionking.com','Een hartverwarmende film over een lief leeuwtje wiens vader op vreselijke wijze sterft','Kleur','20-JAN-2000','Elton John - Can you feel the love 
tonight','Standaard',0.3,'kids',2,'Miramax',4,2)
/

INSERT INTO SN_FLI(fli_name, fli_length, fli_website, fli_description, fli_color, fli_releasedate, fli_soundtrack, fli_format, fli_price, fli_targetgroup, gnr_id, fli_productionhouse, cnt_id, dst_id)
VALUES('SAW 2',170,'www.sawthemovie.com','Een hartverwarmende film over een psychopaat', 'Kleur','28-JAN-2000','geen','Standaard',0.2,'thriller',2,'Paramount',3,2)
/


rem /~---------------------~\
rem |      ORG_FILMS      |
rem \~---------------------~/

INSERT INTO SN_ORF(orf_playing, fli_id)
VALUES('Yes',1)
/

INSERT INTO SN_ORF(orf_playing, fli_id)
VALUES('No',2)
/

rem /~---------------------~\
rem |      NTN_FILMS      |
rem \~---------------------~/

INSERT INTO SN_NTF(ntf_name, orf_id, fra_id, ntf_status, ntf_ages)
VALUES('De Leeuwenkoning', 1, 1, 'PLAYING', 12)
/

INSERT INTO SN_NTF(ntf_name, orf_id, fra_id, ntf_status, ntf_ages)
VALUES('SAW', 2, 2, 'NOT PLAYING', 18)
/


rem /~---------------------~\
rem |      PROGRAMMING      |
rem \~---------------------~/


INSERT INTO SN_PRG(cmf_id, hlc_id, fli_id, prg_datetime, prg_price)
VALUES(2,2,1,'23-DEC-2005', 7)
/

INSERT INTO SN_PRG(cmf_id, hlc_id, fli_id, prg_datetime, prg_price)
VALUES(2,3,2,'10-JUL-2005', 4)
/


rem /~---------------------~\
rem |      Discounts        |
rem \~---------------------~/

INSERT INTO SN_DSC(dsc_name, dsc_amount)
VALUES('Happy Days Korting',-2)
/

INSERT INTO SN_DSC(dsc_name, dsc_amount)
VALUES('Guido Korting',-1)
/

INSERT INTO SN_DSC(dsc_name, dsc_amount)
VALUES('Fortis Korting',-3)
/




rem /~---------------------~\
rem |   PERSONS             |
rem \~---------------------~/



INSERT INTO SN_PRS ( prs_name, prs_birthdate, prs_sex, cnt_id)
VALUES( 'Matterne David', '17-DEC-1985', 'M', 1)
/

INSERT INTO SN_PRS( prs_name, prs_birthdate, prs_sex, cnt_id)
VALUES( 'Demeer Dimitri', '05-APR-1984', 'M', 1)
/

INSERT INTO SN_PRS( prs_name, prs_birthdate, prs_sex, cnt_id)
VALUES( 'Wouters Joris', '09-AUG-1983', 'M', 1)
/

INSERT INTO SN_PRS( prs_name, prs_birthdate, prs_sex, cnt_id)
VALUES( 'Luts Ben', '12-JUN-1985', 'M', 1)
/

INSERT INTO SN_PRS( prs_name, prs_birthdate, prs_sex, cnt_id)
VALUES( 'DExelle Wim', '24-FEB-1983', 'M', 1)
/

INSERT INTO SN_PRS( prs_name, prs_birthdate, prs_sex, cnt_id)
VALUES( 'Daniels Glenn', '03-MAR-1985', 'M', 1)
/

rem /~---------------------~\
rem |   ROLES               |
rem \~---------------------~/



INSERT INTO SN_RLS(rls_id, rls_description)
VALUES(0, 'Actor')
/

INSERT INTO SN_RLS(rls_id, rls_description)
VALUES(0, 'Director')
/

INSERT INTO SN_RLS(rls_id, rls_description)
VALUES(0, 'Stuntman')
/

rem /~---------------------~\
rem |   SCORES              |
rem \~---------------------~/


INSERT INTO SN_SCR( scr_description)
VALUES('Not so good')
/

INSERT INTO SN_SCR( scr_description)
VALUES('Average')
/

INSERT INTO SN_SCR( scr_description)
VALUES('Good film , Good plot and a nice end')
/





rem /~---------------------~\
rem |   FLM_INFO            |
rem \~---------------------~/

INSERT INTO SN_FLI(fli_name, fli_length, fli_website, fli_description, fli_color, fli_releasedate, fli_soundtrack, fli_format, fli_price, fli_targetgroup, gnr_id, fli_productionhouse, cnt_id, dst_id)
VALUES('Pocahonthas',105,' ', ' ', 'color', '31-MAR-2002', ' ', ' ',3500.00, ' ',1,'Disney', 4, 1)
/


INSERT INTO SN_FLI(fli_name, fli_length, fli_website, fli_description, fli_color, fli_releasedate, fli_soundtrack, fli_format, fli_price, fli_targetgroup, gnr_id, fli_productionhouse, cnt_id, dst_id)
VALUES('Bambi 2',93,' ', ' ', 'color', '21-OCT-2004', ' ', ' ',4200.00, 'Kids',1,'Disney', 4, 2)
/


INSERT INTO SN_FLI(fli_id, fli_name, fli_length, fli_website, fli_description, fli_color, fli_releasedate, fli_soundtrack, fli_format, fli_price, fli_targetgroup, gnr_id, fli_productionhouse, cnt_id, dst_id)
VALUES(0,'Titanic',120,' ', ' ', 'color', '12-DEC-1999', ' ', ' ',5420.00, ' ',3,'Candor PH', 4, 3)
/



rem /~---------------------~\
rem |   FLM_crews           |
rem \~---------------------~/

INSERT INTO SN_FLC(rls_id, fli_id, prs_id, flc_character, flc_award)
VALUES(01, 1, 1, 'Bambi', '')
/


rem /~---------------------~\
rem |   FLM_SCORES          |
rem \~---------------------~/

INSERT INTO SN_FLS(scr_id, fls_name, fls_public, fli_id)
VALUES(1,'Lorien Haynes', 'NO', 01)
/

INSERT INTO SN_FLS( scr_id, fls_name, fls_public, fli_id)
VALUES(2,'Bart Wijnants', 'YES', 02)
/

INSERT INTO SN_FLS( scr_id, fls_name, fls_public, fli_id)
VALUES(3,'Ben Luts', 'YES', 03)
/


rem /~---------------------~\
rem |   FLM_REVIEWS         |
rem \~---------------------~/

INSERT INTO SN_FRE( fre_title, fre_content, fre_name, fre_public, lng_id, fls_id)
VALUES('The review of Pocahanthas', 'A good film, with good actors', 'Lorien Haynes', 'NO', 1, 3)
/

INSERT INTO SN_FRE( fre_title, fre_content, fre_name, fre_public, lng_id, fls_id)
VALUES('The review of Bambi 2', 'Not really a good movie', 'Bart Wijnants', 'YES', 1, 2)
/

INSERT INTO SN_FRE( fre_title, fre_content, fre_name, fre_public, lng_id, fls_id)
VALUES('The review of Titanic', 'Romantic movie', 'Ben Luts', 'YES', 2, 2)
/






rem /~---------------------~\
rem |   TRAILERS            |
rem \~---------------------~/

INSERT INTO SN_TRL( trl_id,fli_id, trl_trailer,trl_length,trl_name)
VALUES(1, 01, '23',1,'Trailer 1')
/

INSERT INTO SN_TRL( trl_id,fli_id, trl_trailer,trl_length,trl_name)
VALUES(2, 02, '4F',2,'Trailer 2')
/

INSERT INTO SN_TRL( trl_id,fli_id, trl_trailer,trl_length,trl_name)
VALUES(2, 03, '4C',3,'Trailer 3')
/



rem /~---------------------~\
rem |   PHOTOS              |
rem \~---------------------~/

INSERT INTO SN_PHT( pht_id,fli_id, pht_photo,pht_contenttype,pht_length,pht_name)
VALUES(1, 01, '23F','jpg',1,'Photo1.jpg')
/

INSERT INTO SN_PHT( pht_id,fli_id, pht_photo,pht_contenttype,pht_length,pht_name)
VALUES(1, 02, '23','jpg',2,'Photo2.jpg')
/

INSERT INTO SN_PHT( pht_id,fli_id, pht_photo,pht_contenttype,pht_length,pht_name)
VALUES(2, 03, 'CF','jpg',3,'Photo3.jpg')
/




rem /~---------------------~\
rem |   FLM_AWARDS          |
rem \~---------------------~/

INSERT INTO SN_FLA( fla_name, fli_id)
VALUES( 01, 1)
/

INSERT INTO SN_FLA( fla_name, fli_id)
VALUES( 01, 2)
/

INSERT INTO SN_FLA( fla_name, fli_id)
VALUES( 02, 1)
/

rem /~---------------------~\
rem |   USERS               |
rem \~---------------------~/

INSERT INTO SN_USR(usr_address,usr_birthday,usr_email,usr_function,usr_password,usr_sex,usr_tel,usr_username,cnt_id,usr_name)
VALUES ('Address1','17-DEC-1985','lionheartilly007@hotmail.com','Admin','password','M','0011','admin',1,'admin')
/

rem /~---------------------~\
rem |   LOGINS              |
rem \~---------------------~/

INSERT INTO SN_LGN(lgn_username, lgn_password, usr_id, lgn_function,cnt_id,lgn_birthday,lgn_email,lgn_name,lgn_sex,lgn_tel,lgn_address)
VALUES('Dmatterne', 'dm', 0000000001, 'Database verantwoordelijke, Layout- en naamgevingsverantwoordelijke',1,'17-DEC-1985','david.matterne@gmail.com','admin','M','0112','adress') 
/

/** INSERT INTO SN_LGN(lgn_username, lgn_password, prs_id, lgn_function)
VALUES('Ddemeer', 'dd', 0000000002, 'Website verantwoordelijke') 
/

INSERT INTO SN_LGN(lgn_username, lgn_password, prs_id, lgn_function)
VALUES('Jwouters', 'jw', 0000000003, 'Blackboard verantwoordelijke') 
/

INSERT INTO SN_LGN(lgn_username, lgn_password, prs_id, lgn_function)
VALUES('Bluts', 'bl', 0000000004, 'co-projectleider, netwerkverantwoordelijke') 
/


INSERT INTO SN_LGN(lgn_username, lgn_password, prs_id, lgn_function)
VALUES('Wdexelle', 'wd', 0000000005, 'Presentatie verantwoordelijke, planningsverantwoordelijke') 
/

INSERT INTO SN_LGN(lgn_username, lgn_password, prs_id, lgn_function)
VALUES('Gdaniels', 'gd', 0000000006, 'Projectleider, administratief verantwoordelijke') 
/

**/

rem /~---------------------~\
rem | Language versions     |
rem \~---------------------~/

INSERT INTO sn_lnv(lnv_name, cnt_id, ntf_id, snd_id, sbt_id, frm_id, lng_id, lnv_releasedate, lnv_length)
VALUES('The Lionking',1,1,1,1,1,1,'04-APR-2000',180)
/

INSERT INTO sn_lnv(lnv_name, cnt_id, ntf_id, snd_id, sbt_id, frm_id, lng_id, lnv_releasedate, lnv_length)
VALUES('SAW 2',2,2,1,1,1,1,'04-APR-2000',140)
/

rem /~---------------------~\
rem | Tickets    |
rem \~---------------------~/

INSERT INTO sn_tck(prg_id, cst_id, dsc_id, tck_price,usr_id)
VALUES(1,1,1,7,1)
/

INSERT INTO sn_tck(prg_id, cst_id, dsc_id, tck_price,usr_id)
VALUES(1,2,1,5,1)
/

