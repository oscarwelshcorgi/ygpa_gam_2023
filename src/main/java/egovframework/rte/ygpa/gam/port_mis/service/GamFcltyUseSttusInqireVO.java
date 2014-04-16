package egovframework.rte.ygpa.gam.port_mis.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamFcltyUseSttusInqireVO.java
 * @Description : 항만시설사용현황조회(포트미스정보)
 * @Modification Information
 *   수정일          수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  domh          최초 생성
  *  2014.04.14  lsl          선석별 사용현황 조회처리 -- 기존 파일은 _처리 백업
  *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamFcltyUseSttusInqireVO extends ErpCmmnCdDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 항코드 */
    private String prtAtCode;	
        
    /** 조회시작일 */
    private String sGrUsagePdFrom;	
    
    /** 조회종료일 */
    private String sGrUsagePdTo;	
    
    /** 부두코드 */
    private String fac_code;
    
    /** 선석코드 */
    private String fac_sub_code;
    
    /** 선석명 */
    private String fac_sub_kor_nm;
    
    /** 요금코드 */
    private String chrgeKndCd;
    
    /** 요금명 */
    private String chrgeKndNm;

	/**
	 * @return the prtAtCode
	 */
	public String getPrtAtCode() {
		return prtAtCode;
	}

	/**
	 * @param prtAtCode the prtAtCode to set
	 */
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}

	/**
	 * @return the sGrUsagePdFrom
	 */
	public String getsGrUsagePdFrom() {
		return sGrUsagePdFrom;
	}

	/**
	 * @param sGrUsagePdFrom the sGrUsagePdFrom to set
	 */
	public void setsGrUsagePdFrom(String sGrUsagePdFrom) {
		sGrUsagePdFrom = sGrUsagePdFrom.replace("-", "");
		this.sGrUsagePdFrom = sGrUsagePdFrom;
	}

	/**
	 * @return the sGrUsagePdTo
	 */
	public String getsGrUsagePdTo() {
		return sGrUsagePdTo;
	}

	/**
	 * @param sGrUsagePdTo the sGrUsagePdTo to set
	 */
	public void setsGrUsagePdTo(String sGrUsagePdTo) {
		sGrUsagePdTo = sGrUsagePdTo.replace("-", "");
		this.sGrUsagePdTo = sGrUsagePdTo;
	}

	/**
	 * @return the fac_code
	 */
	public String getFac_code() {
		return fac_code;
	}

	/**
	 * @param fac_code the fac_code to set
	 */
	public void setFac_code(String fac_code) {
		this.fac_code = fac_code;
	}

	/**
	 * @return the fac_sub_code
	 */
	public String getFac_sub_code() {
		return fac_sub_code;
	}

	/**
	 * @param fac_sub_code the fac_sub_code to set
	 */
	public void setFac_sub_code(String fac_sub_code) {
		this.fac_sub_code = fac_sub_code;
	}

	/**
	 * @return the fac_sub_kor_nm
	 */
	public String getFac_sub_kor_nm() {
		return fac_sub_kor_nm;
	}

	/**
	 * @param fac_sub_kor_nm the fac_sub_kor_nm to set
	 */
	public void setFac_sub_kor_nm(String fac_sub_kor_nm) {
		this.fac_sub_kor_nm = fac_sub_kor_nm;
	}

	/**
	 * @return the chrgeKndCd
	 */
	public String getChrgeKndCd() {
		return chrgeKndCd;
	}

	/**
	 * @param chrgeKndCd the chrgeKndCd to set
	 */
	public void setChrgeKndCd(String chrgeKndCd) {
		this.chrgeKndCd = chrgeKndCd;
	}

	/**
	 * @return the chrgeKndNm
	 */
	public String getChrgeKndNm() {
		return chrgeKndNm;
	}

	/**
	 * @param chrgeKndNm the chrgeKndNm to set
	 */
	public void setChrgeKndNm(String chrgeKndNm) {
		this.chrgeKndNm = chrgeKndNm;
	}
    
}
