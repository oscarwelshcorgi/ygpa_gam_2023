package egovframework.rte.ygpa.gam.asset.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamAssetEvlDtlsInqireVO.java
 * @Description : 자산가치평가내역조회 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class GamAssetLndValInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** GIS 자산 코드 */
    private String sGisAssetsCd;
	
    /** GIS 자산 명 */
    private String sGisAssetsNm;
    
    /** 사용기간 FORM */
    private String sUsagePdFrom;
    
    /** 사용기간 TO */
    private String sUsagePdTo;
    
    /**  시작일 */
    private String sBeginDt;

    /**  종료일 */
    private String sEndDt;
    
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
	 * @return the sBeginDt
	 */
	public String getsBeginDt() {
		return sBeginDt;
	}

	/**
	 * @param sBeginDt the sBeginDt to set
	 */
	public void setsBeginDt(String sBeginDt) {
		this.sBeginDt = sBeginDt;
	}

	/**
	 * @return the sEndDt
	 */
	public String getsEndDt() {
		return sEndDt;
	}

	/**
	 * @param sEndDt the sEndDt to set
	 */
	public void setsEndDt(String sEndDt) {
		this.sEndDt = sEndDt;
	}

}
