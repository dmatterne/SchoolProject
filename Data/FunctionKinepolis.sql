create or replace function f_zoek_land(f_cntnr NUMBER)
return Varchar2

IS

f_cntname 		SN_CNT.cnt_name%TYPE;

BEGIN

	SELECT cnt_name 
	INTO 	f_cntname 
	FROM 	SN_CNT c
	WHERE 	f_cntnr = c.cnt_id;
	
	return f_cntname;

EXCEPTION
	when no_data_found then
		dbms_output.put_line('Land Bestaat niet');

END;
/



CREATE OR REPLACE FUNCTION F_AGE_CUSTOMER
(p_cst_id  in CUSTOMERS.CST_ID%TYPE)
   RETURN  NUMBER
IS
    v_birthdate        CUSTOMERS.CST_BIRTHDATE%TYPE;
    v_day      	    number(2);
    v_month         number(2);
    v_year          number(4);
    v_current_day   number(2);
    v_current_month number(2);
    v_current_year  number(4);
    v_age           number(3);
BEGIN
    select CST_BIRTHDAte
    into v_birthdate
    from CUSTOMERS
    where CST_ID = P_CST_ID;
    v_day   := substr(v_birthdate, 1, 2);
    v_month := to_number(to_char(v_birthdate, 'mm'));
    v_year  := substr(v_birthdate, 8, 4);
    v_current_day   := substr(trunc(sysdate,'dd'),1,2);
    v_current_month := to_number(to_char(sysdate,'mm'));
    v_current_year  := substr(trunc(sysdate,'YYYY'),8,4);
    v_age   := v_current_year - v_year - 1;
    if (v_month < v_current_month) then
            v_age := v_age + 1;
    else
            if v_month = v_current_month then
                    if v_day < v_current_day then
                            v_age :=  v_age + 1;
                    end if;
            end if;
    end if;
    RETURN (v_age);
END F_AGE_CUSTOMER;
/

CREATE OR REPLACE FUNCTION f_FLI_NAME(p_flinr	IN NUMBER)
RETURN VARCHAR2
IS
p_fliname	SN_FLI.fli_name%TYPE;
BEGIN

select fli_name
	INTO p_fliname
	FROM SN_FLI n
	WHERE fli_id = p_flinr;

return p_fliname;

Exception

	When no_data_found then
		dbms_output.put_line('Land niet gevonden');


END;
/

create or replace function f_bereken_prijs(v_prijs in number, v_korting in number) return number
IS

	v_effectief	sn_tck.tck_price%type;
BEGIN

	v_effectief:= v_prijs - v_korting;

	return v_effectief;
End;
/