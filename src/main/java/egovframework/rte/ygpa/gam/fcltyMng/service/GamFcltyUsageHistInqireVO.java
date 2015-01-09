/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyUsageHistInqireVO extends ComDefaultVO{

	/** 항코드 (조회조건) **/
	private String sPrtAtCode;
	
	/** 자산명(조회조건) **/
	private String sGisAssetsNm;
	
	/** 신청업체(조회조건) **/
	private String sRegistEntrpsCd;
	
	/** 조회시작일(조회조건) **/
	private String sDtFr;

	/** 조회종료일(조회조건) **/
	private String sDtTo;
	
	private String	dataCount;
	private	String	sumUsageAr;
	private String	sumFee;
	private String	gisAssetsPrtAtCode;
	private String	gisAssetsCd;
	private String	gisAssetsSubCd;
	private String	prtAtCode;
	private String	mngYear;
	private String	mngNo;
	private String	mngCnt;
	private String	assetsUsageSeq;
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
	 * @return the sRegistEntrpsCd
	 */
	public String getsRegistEntrpsCd() {
		return sRegistEntrpsCd;
	}
	/**
	 * @param sRegistEntrpsCd the sRegistEntrpsCd to set
	 */
	public void setsRegistEntrpsCd(String sRegistEntrpsCd) {
		this.sRegistEntrpsCd = sRegistEntrpsCd;
	}
	/**
	 * @return the sDtFr
	 */
	public String getsDtFr() {
		return sDtFr;
	}
	/**
	 * @param sDtFr the sDtFr to set
	 */
	public void setsDtFr(String sDtFr) {
		this.sDtFr = sDtFr;
	}
	/**
	 * @return the sDtTo
	 */
	public String getsDtTo() {
		return sDtTo;
	}
	/**
	 * @param sDtTo the sDtTo to set
	 */
	public void setsDtTo(String sDtTo) {
		this.sDtTo = sDtTo;
	}
	/**
	 * @return the dataCount
	 */
	public String getDataCount() {
		return dataCount;
	}
	/**
	 * @param dataCount the dataCount to set
	 */
	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}
	/**
	 * @return the sumUsageAr
	 */
	public String getSumUsageAr() {
		return sumUsageAr;
	}
	/**
	 * @param sumUsageAr the sumUsageAr to set
	 */
	public void setSumUsageAr(String sumUsageAr) {
		this.sumUsageAr = sumUsageAr;
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
	 * @return the gisAssetsPrtAtCode
	 */
	public String getGisAssetsPrtAtCode() {
		return gisAssetsPrtAtCode;
	}
	/**
	 * @param gisAssetsPrtAtCode the gisAssetsPrtAtCode to set
	 */
	public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
		this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
	}
	/**
	 * @return the gisAssetsCd
	 */
	public String getGisAssetsCd() {
		return gisAssetsCd;
	}
	/**
	 * @param gisAssetsCd the gisAssetsCd to set
	 */
	public void setGisAssetsCd(String gisAssetsCd) {
		this.gisAssetsCd = gisAssetsCd;
	}
	/**
	 * @return the gisAssetsSubCd
	 */
	public String getGisAssetsSubCd() {
		return gisAssetsSubCd;
	}
	/**
	 * @param gisAssetsSubCd the gisAssetsSubCd to set
	 */
	public void setGisAssetsSubCd(String gisAssetsSubCd) {
		this.gisAssetsSubCd = gisAssetsSubCd;
	}
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
	 * @return the mngYear
	 */
	public String getMngYear() {
		return mngYear;
	}
	/**
	 * @param mngYear the mngYear to set
	 */
	public void setMngYear(String mngYear) {
		this.mngYear = mngYear;
	}
	/**
	 * @return the mngNo
	 */
	public String getMngNo() {
		return mngNo;
	}
	/**
	 * @param mngNo the mngNo to set
	 */
	public void setMngNo(String mngNo) {
		this.mngNo = mngNo;
	}
	/**
	 * @return the mngCnt
	 */
	public String getMngCnt() {
		return mngCnt;
	}
	/**
	 * @param mngCnt the mngCnt to set
	 */
	public void setMngCnt(String mngCnt) {
		this.mngCnt = mngCnt;
	}
	/**
	 * @return the assetsUsageSeq
	 */
	public String getAssetsUsageSeq() {
		return assetsUsageSeq;
	}
	/**
	 * @param assetsUsageSeq the assetsUsageSeq to set
	 */
	public void setAssetsUsageSeq(String assetsUsageSeq) {
		this.assetsUsageSeq = assetsUsageSeq;
	}
	
}
