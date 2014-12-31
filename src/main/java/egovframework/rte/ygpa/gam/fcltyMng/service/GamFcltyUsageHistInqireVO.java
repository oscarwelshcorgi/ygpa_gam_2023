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
	private String searchPrtAtCode;
	
	/** 신청업체(조회조건) **/
	private String entrpsNm;
	
	/** 조회시작일(조회조건) **/
	private String searchDtFr;

	/** 조회종료일(조회조건) **/
	private String searchDtTo;

	private String	dataCount;
	private String	sumAssetsAr;
	private	String	sumUsageAr;
	private String	sumFee;
	
	/**
	 * @return the searchPrtAtCode
	 */
	public String getSearchPrtAtCode() {
		return searchPrtAtCode;
	}

	/**
	 * @param searchPrtAtCode the searchPrtAtCode to set
	 */
	public void setSearchPrtAtCode(String searchPrtAtCode) {
		this.searchPrtAtCode = searchPrtAtCode;
	}

	/**
	 * @return the entrpsNm
	 */
	public String getEntrpsNm() {
		return entrpsNm;
	}

	/**
	 * @param entrpsNm the entrpsNm to set
	 */
	public void setEntrpsNm(String entrpsNm) {
		this.entrpsNm = entrpsNm;
	}

	/**
	 * @return the searchDtFr
	 */
	public String getSearchDtFr() {
		return searchDtFr;
	}

	/**
	 * @param searchDtFr the searchDtFr to set
	 */
	public void setSearchDtFr(String searchDtFr) {
		this.searchDtFr = searchDtFr;
	}

	/**
	 * @return the searchDtTo
	 */
	public String getSearchDtTo() {
		return searchDtTo;
	}

	/**
	 * @param searchDtTo the searchDtTo to set
	 */
	public void setSearchDtTo(String searchDtTo) {
		this.searchDtTo = searchDtTo;
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
