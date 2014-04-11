package egovframework.rte.ygpa.gam.port_mis.service;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamCustTpSalesSttutsCreatVO.java
 * @Description : 고객군들통계(포트미스정보) DAO Class
 * @Modification Information
 *
 * @author 김종민
 * @since 2014-04-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamCustTpSalesSttutsCreatVO extends ErpCmmnCdDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 항코드  */
    private String prtAtCode;
    
    /** 업체코드 */
    private String entrpsCd;
    
    /** 생성년월 */
    private String yrMt;
    
    /** 요금종류코드 */
    private String chrgeKndCd;
    
    /** 매출액 */
    private String costVal;
    
    /** 수정자 */
    private String updUsr;
    
    /** 수정일시 */
    private String updtDt;
    
    /** 통계 생성 리턴 값 */
    private String result;

    /** 매출액 통계생성 조회 년 */
    private String grStartYr;
    
    /** 매출액 통계생성 조회 월 */
    private String grStartMn;
    
    /** 업체별 매출액 통계 조회 시작년 */
    private String eGrStartYr;
    
    /** 업체별 매출액 통계 조회 시작월 */
    private String eGrStartMn;
    
    /** 업체별 매출액 통계 조회 끝년 */
    private String eGrEndYr;
    
    /** 업체별 매출액 통계 조회 끝월 */
    private String eGrEndMn;
    
    /** 선사별 매출액 통계 조회 시작년 */
    private String sEgrStartYr;
    
    /** 선사별 매출액 통계 조회 시작월 */
    private String sEgrStartMn;
    
    /** 선사별 매출액 통계 조회 끝년 */
    private String sEgrEndYr;
    
    /** 선사별 매출액 통계 조회 끝월 */
    private String sEgrEndMn;
    
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
	 * @return the entrpsCd
	 */
	public String getEntrpsCd() {
		return entrpsCd;
	}

	/**
	 * @param entRpsCd the entrpsCd to set
	 */
	public void setEntrpsCd(String entrpsCd) {
		this.entrpsCd = entrpsCd;
	}

	/**
	 * @return the yrMt
	 */
	public String getYrMt() {
		return yrMt;
	}

	/**
	 * @param yrMt the yrMt to set
	 */
	public void setYrMt(String yrMt) {
		this.yrMt = yrMt;
	}

	/**
	 * @return the chrgeKndCd
	 */
	public String getChrgeKndCd() {
		return chrgeKndCd;
	}

	/**
	 * @param chrgeKndCo the chrgeKndCo to set
	 */
	public void setChrgeKndCd(String chrgeKndCd) {
		this.chrgeKndCd = chrgeKndCd;
	}

	/**
	 * @return the costVal
	 */
	public String getCostVal() {
		return costVal;
	}

	/**
	 * @param costVal the costVal to set
	 */
	public void setCostVal(String costVal) {
		this.costVal = costVal;
	}

	/**
	 * @return the updUsr
	 */
	public String getUpdUsr() {
		return updUsr;
	}

	/**
	 * @param updUsr the updUsr to set
	 */
	public void setUpdUsr(String updUsr) {
		this.updUsr = updUsr;
	}

	/**
	 * @return the updtDt
	 */
	public String getUpdtDt() {
		return updtDt;
	}

	/**
	 * @param updtDt the updtDt to set
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the grStartYr
	 */
	public String getGrStartYr() {
		return grStartYr;
	}

	/**
	 * @param grStartYr the grStartYr to set
	 */
	public void setGrStartYr(String grStartYr) {
		this.grStartYr = grStartYr;
	}

	/**
	 * @return the grStartMn
	 */
	public String getGrStartMn() {
		return grStartMn;
	}

	/**
	 * @param grStartMn the grStartMn to set
	 */
	public void setGrStartMn(String grStartMn) {
		this.grStartMn = grStartMn;
	}

	/**
	 * @return the eGrStartYr
	 */
	public String geteGrStartYr() {
		return eGrStartYr;
	}

	/**
	 * @param eGrStartYr the eGrStartYr to set
	 */
	public void seteGrStartYr(String eGrStartYr) {
		this.eGrStartYr = eGrStartYr;
	}

	/**
	 * @return the eGrStartMn
	 */
	public String geteGrStartMn() {
		return eGrStartMn;
	}

	/**
	 * @param eGrStartMn the eGrStartMn to set
	 */
	public void seteGrStartMn(String eGrStartMn) {
		this.eGrStartMn = eGrStartMn;
	}

	/**
	 * @return the eGrEndYr
	 */
	public String geteGrEndYr() {
		return eGrEndYr;
	}

	/**
	 * @param eGrEndYr the eGrEndYr to set
	 */
	public void seteGrEndYr(String eGrEndYr) {
		this.eGrEndYr = eGrEndYr;
	}

	/**
	 * @return the eGrEndMn
	 */
	public String geteGrEndMn() {
		return eGrEndMn;
	}

	/**
	 * @param eGrEndMn the eGrEndMn to set
	 */
	public void seteGrEndMn(String eGrEndMn) {
		this.eGrEndMn = eGrEndMn;
	}

	/**
	 * @return the sEgrStartYr
	 */
	public String getsEgrStartYr() {
		return sEgrStartYr;
	}

	/**
	 * @param sEgrStartYr the sEgrStartYr to set
	 */
	public void setsEgrStartYr(String sEgrStartYr) {
		this.sEgrStartYr = sEgrStartYr;
	}

	/**
	 * @return the sEgrStartMn
	 */
	public String getsEgrStartMn() {
		return sEgrStartMn;
	}

	/**
	 * @param sEgrStartMn the sEgrStartMn to set
	 */
	public void setsEgrStartMn(String sEgrStartMn) {
		this.sEgrStartMn = sEgrStartMn;
	}

	/**
	 * @return the sEgrEndYr
	 */
	public String getsEgrEndYr() {
		return sEgrEndYr;
	}

	/**
	 * @param sEgrEndYr the sEgrEndYr to set
	 */
	public void setsEgrEndYr(String sEgrEndYr) {
		this.sEgrEndYr = sEgrEndYr;
	}

	/**
	 * @return the sEgrEndMn
	 */
	public String getsEgrEndMn() {
		return sEgrEndMn;
	}

	/**
	 * @param sEgrEndMn the sEgrEndMn to set
	 */
	public void setsEgrEndMn(String sEgrEndMn) {
		this.sEgrEndMn = sEgrEndMn;
	}

	
}
