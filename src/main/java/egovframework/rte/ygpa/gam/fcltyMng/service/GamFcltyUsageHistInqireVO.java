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
	private String	sumAssetsAr;
	private	String	sumUsageAr;
	private String	sumFee;
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
	 * @return the sumAssetsAr
	 */
	public String getSumAssetsAr() {
		return sumAssetsAr;
	}
	/**
	 * @param sumAssetsAr the sumAssetsAr to set
	 */
	public void setSumAssetsAr(String sumAssetsAr) {
		this.sumAssetsAr = sumAssetsAr;
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
	
}
