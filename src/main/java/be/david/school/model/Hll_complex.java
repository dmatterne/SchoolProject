package be.david.school.model;

/**
 * Created by David on 15/08/2016.
 */
public class Hll_complex {
}



//    CREATE TABLE HLL_COMPLEX
//        (hlc_id				NUMBER(3)
//,cmf_id				NUMBER(3)	CONSTRAINT rf_hll_complx_cmf_id REFERENCES CMP_FEATURES
//        ,hlc_twinseat			VARCHAR(3)	CONSTRAINT nn_hlc_twinseat	NOT NULL
//        CHECK(hlc_twinseat = 'YES' OR hlc_twinseat = 'NO')
//        ,hlc_wheelchair			VARCHAR(3)	CONSTRAINT nn_hlc_wheelchair	NOT NULL
//        CHECK(hlc_wheelchair = 'YES' OR hlc_wheelchair = 'NO')
//        ,hlc_digitalversion		VARCHAR(3)	CONSTRAINT nn_hlc_digitalvrs	NOT NULL
//        CHECK(hlc_digitalversion = 'YES' OR hlc_digitalversion = 'NO')
//        ,hlc_cupholder			VARCHAR(3)	CONSTRAINT nn_hlc_cupholder	NOT NULL
//        CHECK(hlc_cupholder = 'YES' OR hlc_cupholder = 'NO')
//        ,hlc_capacity			NUMBER(4)	CONSTRAINT nn_hlc_capacity	NOT NULL
//        ,hlc_bookable			VARCHAR(3)	CONSTRAINT nn_hlc_bookable	NOT NULL
//        CHECK(hlc_bookable = 'YES' OR hlc_bookable = 'NO')
//        ,hlc_distancescreenprojection	NUMBER(4)	CONSTRAINT nn_hlc_distscrproj	NOT NULL
//        ,hlc_spaceinfrontofscreen	VARCHAR(10)	CONSTRAINT nn_hlc_spacefrscr	NOT NULL
//        ,hlc_screenwidth		VARCHAR(10)	CONSTRAINT nn_hlc_screenwidth	NOT NULL
//        ,CONSTRAINT pr_hll_complex PRIMARY KEY(hlc_id, cmf_id)
//        )
//        /