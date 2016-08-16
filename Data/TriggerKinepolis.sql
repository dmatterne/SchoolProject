REM 	Kijkt na of het formaat van het ingegeven email adress goed is
REM     voor de tabel customers

CREATE OR REPLACE TRIGGER CUSTOMERS_CHECK_MAIL
BEFORE INSERT or update ON CUSTOMERS
FOR EACH ROW
BEGIN
	if not(:new.cst_email like '%@%.%') then
		raise_application_error(-20225, 'Wrong email adress');
	end if;
END;
/



REM 	Kijkt na of het formaat van het ingegeven email adress goed is
REM     voor de tabel distributors

CREATE OR REPLACE TRIGGER DISTRIBUTORS_CHECK_MAIL
BEFORE INSERT or update ON DISTRIBUTORS
FOR EACH ROW
BEGIN
	if not(:new.dst_email like '%@%.%') then
		raise_application_error(-20225, 'Wrong email adress');
	end if;
END;
/



REM 	Als een film op wereld niveau op niet spelend word gezet, 
REM 	dan word dit doorgevoerd over alle nationale films

CREATE OR REPLACE TRIGGER ORG_FILMS_CHANGE_PLAYING
BEFORE UPDATE ON ORG_FILMS
FOR EACH ROW

BEGIN
	if not(:new.orf_playing = :old.orf_playing) then
		UPDATE NTN_FILMS SET ntf_status = :new.orf_playing where orf_id = :new.orf_id;
	end if;
END;
/


CREATE OR REPLACE TRIGGER CMP_FEATURES
BEFORE insert or update ON CMP_FEATURES
FOR EACH ROW
DECLARE
	today 		date;
	current_year 	number(4);
BEGIN
	select sysdate into today from dual;
	current_year := TO_CHAR(today, 'YYYY');
	if (:new.cmf_constructionyear > current_year) then
		raise_application_error(-20225, 'Wrong year, give a year equal or smaller than this year');
	end if;
END;
/

CREATE OR REPLACE TRIGGER COUNTRIES_INITCAP
BEFORE insert or update ON COUNTRIES
FOR EACH ROW
BEGIN
	:new.cnt_name := initcap(:new.cnt_name);
END;
/

CREATE OR REPLACE TRIGGER PRG_DATE
BEFORE insert or update ON PROGRAMMING
FOR EACH ROW
DECLARE
   v_release_date  LNG_VERSIONS.LNV_RELEASEDATE%TYPE;
BEGIN
   select LNV_RELEASEDATE into v_release_date
   from LNG_VERSIONS;
   if (:new.PRG_DATETIME < v_release_date) then
           raise_application_error(-20300, 'Wrong date, please give a date equal or bigger than ' || v_release_date || '.');
   end if;
END;
/

REM Controle of de releasedatum van de film voor de datum ligt
REM dat de film getoond wordt.

CREATE OR REPLACE TRIGGER FLM_INFO_DATE
BEFORE insert or update ON FLM_INFO
FOR EACH ROW
DECLARE
   v_release_date  PROGRAMMING.PRG_DATETIME%TYPE;
BEGIN
   select PRG_DATETIME into v_release_date
   from PROGRAMMING;
   if (:new.FLI_RELEASEDATE < v_release_date) then
           raise_application_error(-20300, 'Wrong date, please give a date equal or bigger than ' || v_release_date || '.');
   end if;
END;
/

CREATE OR REPLACE TRIGGER FLM_RATINGS_INITCAP
BEFORE insert or update ON FLM_RATINGS
FOR EACH ROW
BEGIN
	:new.fra_name := initcap(:new.fra_name);
END;
/

CREATE OR REPLACE TRIGGER DISTRIBUTORS_INITCAP
BEFORE insert or update ON DISTRIBUTORS
FOR EACH ROW
BEGIN
	:new.dst_name := initcap(:new.dst_name);
END;
/

CREATE OR REPLACE TRIGGER DISTRIBUTORS_INITCAP
BEFORE insert or update ON DISTRIBUTORS
FOR EACH ROW
BEGIN
	:new.dst_name := initcap(:new.dst_name);
END;
/