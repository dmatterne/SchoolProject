REM ======================================================================================
REM ViewKinepolis.sql
REM ======================================================================================

CREATE OR REPLACE VIEW VW_FLM_INFO
AS SELECT f.fli_id,f.fli_name,f.fli_length,f.fli_color,f.fli_format,fli_targetgroup,g.gnr_description,c.cnt_name,d.dst_name from SN_FLI f ,SN_GNR g , SN_DST d ,SN_CNT c WHERE
f.gnr_id = g.gnr_id AND f.cnt_id = c.cnt_id AND d.dst_id = f.dst_id
order by f.fli_id
/


CREATE OR REPLACE VIEW VW_FLM_AWARDS
AS SELECT
a.fla_id,a.fla_name,f.fli_name,f.fli_website,g.gnr_description FROM
SN_FLA a, SN_FLI f, SN_GNR g WHERE a.fli_id = f.fli_id AND g.gnr_ID = f.gnr_id
ORDER BY a.fla_id
/


CREATE OR REPLACE VIEW VW_FLM_SCORES
AS SELECT
l.fls_id,f.fli_name, s.scr_description,l.fls_name,l.fls_public 
 FROM SN_FLS l, SN_SCR s, SN_FLI f
WHERE l.scr_id = s.scr_id AND f.fli_id = l.fli_id
ORDER BY l.fls_id
/

CREATE OR REPLACE VIEW VW_FLM_REVIEWS
AS SELECT
e.fre_id,f.fli_name,e.fre_title,e.fre_content,e.fre_name,e.fre_public,l.lng_language
FROM SN_FRE e, SN_FLI f, SN_LNG l, SN_FLS s
WHERE f.fli_id = s.fli_id AND l.lng_id = e.lng_id AND s.fls_id = e.fls_id
ORDER BY e.fre_id
/

CREATE OR REPLACE VIEW VW_FLM_CREWS
AS SELECT
r.rls_description,f.fli_name,p.prs_name,c.flc_character,c.flc_award
FROM SN_RLS r, SN_FLI f ,SN_PRS p, SN_FLC c
WHERE c.rls_id = r.rls_id AND c.fli_id = f.fli_id AND p.prs_id = c.prs_id
ORDER BY f.fli_name
/

CREATE OR REPLACE VIEW VW_TICKETS
AS SELECT
t.tck_id,m.cmf_name,p.hlc_id,f.fli_name,p.prg_datetime,c.cst_name,d.dsc_name,t.tck_price
FROM SN_TCK t, SN_CMF m, SN_PRG p, SN_FLI f, SN_CST c, SN_DSC d
WHERE t.prg_id = p.prg_id AND t.cst_id =c.cst_id AND t.dsc_id = d.dsc_id AND p.cmf_id = m.cmf_id AND p.fli_id = f.fli_id
ORDER BY t.tck_id
/

CREATE OR REPLACE VIEW VW_LNG_VERSIONS
AS SELECT
l.lnv_id,l.lnv_name,c.cnt_name,n.ntf_name,s.snd_name,b.sbt_name,f.frm_id,g.lng_language,
l.lnv_releasedate,l.lnv_length
FROM SN_LNV l, SN_CNT c, SN_NTF n, SN_SND s, SN_SBT b, SN_FRM f, SN_LNG g
WHERE l.cnt_id = c.cnt_id AND l.ntf_id = n.ntf_id AND s.snd_id = l.snd_id AND l.sbt_id = b.sbt_id AND f.frm_id = l.frm_id AND l.lng_id = g.lng_id
ORDER BY l.lnv_id
/