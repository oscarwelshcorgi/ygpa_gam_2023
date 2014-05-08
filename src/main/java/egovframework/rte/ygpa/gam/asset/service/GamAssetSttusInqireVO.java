package egovframework.rte.ygpa.gam.asset.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetSttusInqireVO.java
 * @Description : 자산정보현황조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamAssetSttusInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** GIS 자산 코드 */
    private String sGisAssetsCd;
	
    /** GIS 자산 명 */
    private String sGisAssetsNm;
    
    /** 사용기간 FORM */
    private String sUsagePdFrom;
    
    /** 사용기간 TO */
    private String sUsagePdTo;
    
    /** 검색조건 */
    private String sPrtAtCode;
    
    /** 검색조건 */
    private String sGisAssetsSubCd;
    
    /** 검색조건 */
    private String sEntrpscd;
    
    /** 검색조건 */
    private String sPrmisnYn;
    
    /** 검색조건 */
    private String deptcd;
    
    /** 검색조건 */
    private String sQuayCd;

    /** 자료수 */
    private String sumCnt;
    
    /** 면적합계 */
    private String sumAr;
    
    /** 사용료합계 */
    private String sumFee;
    
    /** 감면사용료합계 */
    private String sumRdcxptFee;

    /**
	 * @return the sGisAssetsCd
	 */
	public String getsGisAssetsCd() {
		return sGisAssetsCd;
	}

	/**
	 * @param sGisAssetsCd the sGisAssetsCd to set
	 */
	public void setsGisAssetsCd(String sGisAssetsCd) {
		this.sGisAssetsCd = sGisAssetsCd;
	}

	/**
	 * @return the sGisAssetsNm
	 */
	public String getsGisAssetsNm() {
		return sGisAssetsNm;
	}

	/**
	 * @param sGisAssetsNm the sGisAssetsNm to set
	 */
	public void setsGisAssetsNm(String sGisAssetsNm) {
		this.sGisAssetsNm = sGisAssetsNm;
	}

	/**
	 * @return the sUsagePdFrom
	 */
	public String getsUsagePdFrom() {
		return sUsagePdFrom;
	}

	/**
	 * @param sUsagePdFrom the sUsagePdFrom to set
	 */
	public void setsUsagePdFrom(String sUsagePdFrom) {
		this.sUsagePdFrom = sUsagePdFrom;
	}

	/**
	 * @return the sUsagePdTo
	 */
	public String getsUsagePdTo() {
		return sUsagePdTo;
	}

	/**
	 * @param sUsagePdTo the sUsagePdTo to set
	 */
	public void setsUsagePdTo(String sUsagePdTo) {
		this.sUsagePdTo = sUsagePdTo;
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
	 * @return the sGisAssetsSubCd
	 */
	public String getsGisAssetsSubCd() {
		return sGisAssetsSubCd;
	}

	/**
	 * @param sGisAssetsSubCd the sGisAssetsSubCd to set
	 */
	public void setsGisAssetsSubCd(String sGisAssetsSubCd) {
		this.sGisAssetsSubCd = sGisAssetsSubCd;
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
	 * @return the sPrmisnYn
	 */
	public String getsPrmisnYn() {
		return sPrmisnYn;
	}

	/**
	 * @param sPrmisnYn the sPrmisnYn to set
	 */
	public void setsPrmisnYn(String sPrmisnYn) {
		this.sPrmisnYn = sPrmisnYn;
	}

	/**
	 * @return the deptcd
	 */
	public String getDeptcd() {
		return deptcd;
	}

	/**
	 * @param deptcd the deptcd to set
	 */
	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
	}

	/**
	 * @return the sQuayCd
	 */
	public String getsQuayCd() {
		return sQuayCd;
	}

	/**
	 * @param sQuayCd the sQuayCd to set
	 */
	public void setsQuayCd(String sQuayCd) {
		this.sQuayCd = sQuayCd;
	}
	
	/**
	 * @return the sumCnt
	 */
	public String getSumCnt() {
		return sumCnt;
	}

	/**
	 * @param sumCnt the sumCnt to set
	 */
	public void setSumCnt(String sumCnt) {
		this.sumCnt = sumCnt;
	}

	/**
	 * @return the sumAr
	 */
	public String getSumAr() {
		return sumAr;
	}

	/**
	 * @param sumAr the sumAr to set
	 */
	public void setSumAr(String sumAr) {
		this.sumAr = sumAr;
	}

	/**
	 * @return the sumFee
	 */
	public String getSumFee() {
		return sumFee;
	}

	/**
	 * @param sumFee the sumFee to set
	 */
	public void setSumFee(String sumFee) {
		this.sumFee = sumFee;
	}

	/**
	 * @return the sumRdcxptFee
	 */
	public String getSumRdcxptFee() {
		return sumRdcxptFee;
	}

	/**
	 * @param sumRdcxptFee the sumRdcxptFee to set
	 */
	public void setSumRdcxptFee(String sumRdcxptFee) {
		this.sumRdcxptFee = sumRdcxptFee;
	}
}
