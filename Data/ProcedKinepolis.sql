create or replace procedure P_zoek_prs_info
(p_prsnr	IN	number
,p_flinr	IN 	number
,p_rlsnr	IN 	number)

IS

p_charac		SN_FLC.flc_character%TYPE;
p_award			SN_FLC.flc_award%TYPE;
p_name			SN_PRS.prs_name%TYPE;
p_birthdate		SN_PRS.prs_birthdate%TYPE;
p_sex			SN_PRS.prs_sex%TYPE;
p_cntid 		SN_PRS.cnt_id%TYPE;
p_cntname 		SN_CNT.cnt_name%TYPE;
p_rlsdesc		SN_RLS.rls_description%TYPE;
p_flinaam		SN_FLI.fli_name%TYPE;




BEGIN



SELECT flc_character, flc_award 
	INTO p_charac,p_award 
	FROM SN_FLC c
	WHERE c.fli_id = p_flinr AND c.prs_id = p_prsnr AND c.rls_id = p_rlsnr;

SELECT prs_name,prs_birthdate,prs_sex,cnt_id 
	INTO p_name,p_birthdate,p_sex,p_cntid
	FROM SN_PRS p
	WHERE p_prsnr = p.prs_id;

p_cntname := f_zoek_land(p_cntid);

SELECT rls_description
	INTO 	p_rlsdesc 
	FROM 	SN_RLS r
	WHERE 	r.rls_id = p_rlsnr;

SELECT fli_name 
	INTO 	p_flinaam
	FROM 	SN_FLI f
	WHERE	f.fli_id = p_flinr;

	
IF (p_charac <> NULL AND p_award <> NULL) THEN
	dbms_output.put_line('Persoon           :' || p_name);
	dbms_output.put_line('Geboortedatum     :' || p_birthdate);
	dbms_output.put_line('Geslacht          :' || p_sex);
	dbms_output.put_line('Land              :' || p_cntname);
	dbms_output.put_line('Film              :' || p_flinaam);
	dbms_output.put_line('Rol 		:' || p_rlsdesc);
	dbms_output.put_line('Character         :' || p_charac);
	dbms_output.put_line('Award             :' || p_award);
ELSE
	IF(p_charac = NULL) THEN
		dbms_output.put_line('Persoon           :' || p_name);
		dbms_output.put_line('Geboortedatum     :' || p_birthdate);
		dbms_output.put_line('Geslacht          :' || p_sex);
		dbms_output.put_line('Land              :' || p_cntname);
		dbms_output.put_line('Film              :' || p_flinaam);
		dbms_output.put_line('Rol               :' || p_rlsdesc);
		dbms_output.put_line('Award             :' || p_award);
	
		ELSE
			IF(p_award = NULL) THEN

				dbms_output.put_line('Persoon           :' || p_name);
				dbms_output.put_line('Geboortedatum     :' || p_birthdate);
				dbms_output.put_line('Geslacht          :' || p_sex);
				dbms_output.put_line('Land              :' || p_cntname);
				dbms_output.put_line('Film              :' || p_flinaam);
				dbms_output.put_line('Rol               :' || p_rlsdesc);
				dbms_output.put_line('Character         :' || p_charac);

			   ELSE 
				IF(p_charac = NULL AND p_award = NULL) THEN
				
					dbms_output.put_line('Persoon           :' || p_name);
					dbms_output.put_line('Geboortedatum     :' || p_birthdate);
					dbms_output.put_line('Geslacht          :' || p_sex);
					dbms_output.put_line('Land              :' || p_cntname);
					dbms_output.put_line('Film              :' || p_flinaam);
					dbms_output.put_line('Rol               :' || p_rlsdesc);
					

				END IF;

			END IF;
	END IF;
		
END IF;

EXCEPTION

	when no_data_found then
	dbms_output.put_line('Geen gegevens gevonden');

END;
/

create or replace procedure p_programming
(p_progid	IN	NUMBER)

IS

p_cmfid 	SN_PRG.cmf_id%TYPE;
p_hlcid 	SN_PRG.hlc_id%TYPE;
p_fliid 	SN_PRG.fli_id%TYPE;
p_prgdatetime	SN_PRG.prg_datetime%TYPE;
p_prgprice	SN_PRG.prg_price%TYPE;
p_cmfname	SN_CMF.cmf_name%TYPE;
p_cntname	SN_CNT.cnt_name%TYPE;
p_fliname	SN_FLI.fli_name%TYPE;
p_cntid		SN_CNT.cnt_id%TYPE;


BEGIN



select cmf_id,hlc_id,fli_id,prg_datetime,prg_price 
	INTO	p_cmfid,p_hlcid,p_fliid,p_prgdatetime,p_prgprice 
	FROM	SN_PRG p
	WHERE   p_progid = p.prg_id ;




select cmf_name,cnt_id 
	INTO p_cmfname,p_cntid
	FROM SN_CMF c
	WHERE c.cmf_id = p_cmfid;

p_cntname := f_zoek_land(p_cntid);

p_fliname := f_FLI_NAME(p_fliid);


dbms_output.put_line('Film           :' || p_fliname);

dbms_output.put_line('Complex Naam   :' || p_cmfname);

dbms_output.put_line('Land           :' || p_cntname);

dbms_output.put_line('Tijd           :' || p_prgdatetime  );

dbms_output.put_line('Prijs          :' || p_prgprice);


END;
/


create or replace procedure p_Distributor_Overzicht
IS
	v_naam		sn_dst.dst_name%type;
	v_address	sn_dst.dst_address%type;
	v_zipcode	sn_dst.dst_zipcode%type;
	v_city		sn_dst.dst_city%type;
	v_email		sn_dst.dst_email%type;
	v_contact	sn_dst.dst_contact%type;
	v_aantal	number(2);
	v_teller	number(2);
BEGIN
	DBMS_OUTPUT.PUT_LINE('Distributor overzicht');
	DBMS_OUTPUT.PUT_LINE('---------------------');
  
	Select count(dst_id) 
	into v_aantal
	from sn_dst; 
	
	v_teller:=0;

	while v_teller < v_aantal loop
		v_teller:=v_teller + 1;
		Select dst_name, dst_address,dst_zipcode ,dst_city,
		dst_email,dst_contact
		into v_naam, v_address, v_zipcode, v_city, v_email, v_contact
		from sn_dst where dst_id=v_teller;
		
		DBMS_OUTPUT.PUT_LINE('------------------------------------');
		DBMS_OUTPUT.PUT_LINE('Naam    : '||v_naam);
		DBMS_OUTPUT.PUT_LINE('Adres   : '||v_address);
		DBMS_OUTPUT.PUT_LINE('zipcode : '||v_zipcode);
		DBMS_OUTPUT.PUT_LINE('city    : '||v_city);
		DBMS_OUTPUT.PUT_LINE('Email   : '||v_email);
		DBMS_OUTPUT.PUT_LINE('Contact : '||v_contact);
		
	end loop;
		DBMS_OUTPUT.PUT_LINE('------------------------------------');		

Exception
	when no_data_found then
		DBMS_OUTPUT.PUT_LINE('Geen gegevens');	

end;
/

create or replace procedure p_ticket_info(v_ticketId in number)
IS

	v_prijs		sn_tck.tck_price%type;
	v_prgPrijs	sn_prg.prg_price%type;
	v_prgId		sn_prg.prg_id%type;
	v_titel		sn_fli.fli_name%type;
	v_uur		sn_prg.prg_datetime%type;
	v_discountid	sn_dsc.dsc_id%type;
	v_korting	sn_dsc.dsc_amount%type;
	v_fli_id	sn_fli.fli_id%type;
BEGIN

	select dsc_id,prg_id into v_discountid, v_prgId from sn_tck where tck_id = v_ticketId;
	select dsc_amount into v_korting from sn_dsc where dsc_id = v_discountid;
	
	select prg_price,fli_id,prg_datetime into v_prgPrijs, v_fli_id, v_uur from sn_prg where prg_id = v_prgId;

	v_prijs := f_bereken_prijs(v_prgPrijs,v_korting);

	v_titel := f_FLI_name(v_fli_id);


	DBMS_OUTPUT.PUT_LINE('Ticket gegevens : ');
	DBMS_OUTPUT.PUT_LINE('------------------');

	DBMS_OUTPUT.PUT_LINE('Film    : '||v_titel);
	DBMS_OUTPUT.PUT_LINE('Uur     : '||v_uur);
	DBMS_OUTPUT.PUT_LINE('Prijs   : '||v_prijs);
	DBMS_OUTPUT.PUT_LINE('------------------------------------');
				

Exception
	when no_data_found then
		DBMS_OUTPUT.PUT_LINE('Geen gegevens');
End;	
/