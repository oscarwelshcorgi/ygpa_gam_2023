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
    
    /** 생성항코드  */
    private String sPrtAtCode;
    
    /** 업체코드 */
    private String sEntrpscd;
    
       
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

	/** 매출액 통계생성 년 */
    private String grCreatYr;
    
    /** 매출액 통계생성 월 */
    private String grCreatMn;
    
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
    
    /**  자료수 천단위 화면단 */
    private String dpTotCnt;
    
    /** 매출액합계금액 */
    private String sumCostval;

    /** 항코드  */
    private String prtAtCode2;
      
    /** 업체코드 */
    private String sEntrpscd2;

    /** 요금종류코드 */
    private String chrgeKndCd2;
    
    /** 항코드  */
    private String prtAtCode3;
      
    /** 업체코드 */
    private String sEntrpscd3;

    /** 요금종류코드 */
    private String chrgeKndCd3;

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
	 * @return the sPrtAtCode
	 */
	public String getsPrtAtCode() {
		return sPrtAtCode;
	}

	/**
	 * @param sPrtAtCode the sPrtAtCode to set
	 */
	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
	}

	/**
	 * @return the sEntrpscd
	 */
	public String getsEntrpscd() {
		return sEntrpscd;
	}

	/**
	 * @param sEntrpscd the sEntrpscd to set
	 */
	public void setsEntrpscd(String sEntrpscd) {
		this.sEntrpscd = sEntrpscd;
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
	 * @return the grCreatYr
	 */
	public String getGrCreatYr() {
		return grCreatYr;
	}

	/**
	 * @param grCreatYr the grCreatYr to set
	 */
	public void setGrCreatYr(String grCreatYr) {
		this.grCreatYr = grCreatYr;
	}

	/**
	 * @return the grCreatMn
	 */
	public String getGrCreatMn() {
		return grCreatMn;
	}

	/**
	 * @param grCreatMn the grCreatMn to set
	 */
	public void setGrCreatMn(String grCreatMn) {
		this.grCreatMn = grCreatMn;
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

	/**
	 * @return the dpTotCnt
	 */
	public String getDpTotCnt() {
		return dpTotCnt;
	}

	/**
	 * @param dpTotCnt the dpTotCnt to set
	 */
	public void setDpTotCnt(String dpTotCnt) {
		this.dpTotCnt = dpTotCnt;
	}

	/**
	 * @return the sumCostval
	 */
	public String getSumCostval() {
		return sumCostval;
	}

	/**
	 * @param sumCostval the sumCostval to set
	 */
	public void setSumCostval(String sumCostval) {
		this.sumCostval = sumCostval;
	}

    /**
	 * @return the prtAtCode2
	 */
	public String getPrtAtCode2() {
		return prtAtCode2;
	}

	/**
	 * @param prtAtCode2 the prtAtCode2 to set
	 */
	public void setPrtAtCode2(String prtAtCode2) {
		this.prtAtCode2 = prtAtCode2;
	}
	
	/**
	 * @return the sEntrpscd2
	 */
	public String getsEntrpscd2() {
		return sEntrpscd2;
	}

	/**
	 * @param sEntrpscd2 the sEntrpscd2 to set
	 */
	public void setsEntrpscd2(String sEntrpscd2) {
		this.sEntrpscd2 = sEntrpscd2;
	}

	/**
	 * @return the chrgeKndCd2
	 */
	public String getChrgeKndCd2() {
		return chrgeKndCd2;
	}

	/**
	 * @param chrgeKndCd2 the chrgeKndCd2 to set
	 */
	public void setChrgeKndCd2(String chrgeKndCd2) {
		this.chrgeKndCd2 = chrgeKndCd2;
	}

    /**
	 * @return the prtAtCode3
	 */
	public String getPrtAtCode3() {
		return prtAtCode3;
	}

	/**
	 * @param prtAtCode3 the prtAtCode3 to set
	 */
	public void setPrtAtCode3(String prtAtCode3) {
		this.prtAtCode3 = prtAtCode3;
	}
	
	/**
	 * @return the sEntrpscd3
	 */
	public String getsEntrpscd3() {
		return sEntrpscd3;
	}

	/**
	 * @param sEntrpscd3 the sEntrpscd3 to set
	 */
	public void setsEntrpscd3(String sEntrpscd3) {
		this.sEntrpscd3 = sEntrpscd3;
	}

	/**
	 * @return the chrgeKndCd3
	 */
	public String getChrgeKndCd3() {
		return chrgeKndCd3;
	}

	/**
	 * @param chrgeKndCd3 the chrgeKndCd3 to set
	 */
	public void setChrgeKndCd3(String chrgeKndCd3) {
		this.chrgeKndCd3 = chrgeKndCd3;
	}
}
