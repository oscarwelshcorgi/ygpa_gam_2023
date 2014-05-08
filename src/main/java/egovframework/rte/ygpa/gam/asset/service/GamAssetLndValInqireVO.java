package egovframework.rte.ygpa.gam.asset.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamAssetLndValInqireVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    /** 항코드 */
    private String sPrtAtCode;

    /** GIS 자산 코드 */
    private String sGisAssetsCd;
	
    /** GIS 자산 명 */
    private String sGisAssetsNm;
    
    /** 조회기준일자 */
    private String sSearchDT;
    
    /** 자료수, 합계 */
    private String sumCnt;
    
    private String sumArOlnlp;
    
    private String sumGisAssetsAcqPri;
    
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
	 * @return the sumArOlnlp
	 */
	public String getSumArOlnlp() {
		return sumArOlnlp;
	}

	/**
	 * @param sumArOlnlp the sumArOlnlp to set
	 */
	public void setSumArOlnlp(String sumArOlnlp) {
		this.sumArOlnlp = sumArOlnlp;
	}

	/**
	 * @return the sumGisAssetsAcqPri
	 */
	public String getSumGisAssetsAcqPri() {
		return sumGisAssetsAcqPri;
	}

	/**
	 * @param sumGisAssetsAcqPri the sumGisAssetsAcqPri to set
	 */
	public void setSumGisAssetsAcqPri(String sumGisAssetsAcqPri) {
		this.sumGisAssetsAcqPri = sumGisAssetsAcqPri;
	}

	/**
	 * @return the sSearchDT
	 */
	public String getsSearchDT() {
		return sSearchDT;
	}

	/**
	 * @param sSearchDT the sSearchDT to set
	 */
	public void setsSearchDT(String sSearchDT) {
		this.sSearchDT = sSearchDT;
	}

}
