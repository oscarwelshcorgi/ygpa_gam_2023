/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

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

public class GamFcltyUsageHistInqireVO extends ComDefaultVO{

	/** 항코드 (조회조건) **/
	private String prtAtCode;
	
	/** 자산코드 (조회조건) **/
	private String assetsCd;

	/** 자산부코드 (조회조건) **/
	private String assetsSubCd;
	
	/** 신청업체(조회조건) **/
	private String entrpsCd;
	
	/** 조회시작일(조회조건) **/
	private String searchDtFr;

	/** 조회종료일(조회조건) **/
	private String searchDtTo;

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
	 * @return the assetsCd
	 */
	public String getAssetsCd() {
		return assetsCd;
	}

	/**
	 * @param assetsCd the assetsCd to set
	 */
	public void setAssetsCd(String assetsCd) {
		this.assetsCd = assetsCd;
	}

	/**
	 * @return the assetsSubCd
	 */
	public String getAssetsSubCd() {
		return assetsSubCd;
	}

	/**
	 * @param assetsSubCd the assetsSubCd to set
	 */
	public void setAssetsSubCd(String assetsSubCd) {
		this.assetsSubCd = assetsSubCd;
	}

	/**
	 * @return the entrpsCd
	 */
	public String getEntrpsCd() {
		return entrpsCd;
	}

	/**
	 * @param entrpsCd the entrpsCd to set
	 */
	public void setEntrpsCd(String entrpsCd) {
		this.entrpsCd = entrpsCd;
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

	
}
