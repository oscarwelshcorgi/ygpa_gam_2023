/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author 정성현
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------			--------	---------------------------
 *  2014.12.9.  	 정성현		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamInfoTechFcltySpecInqireVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/** 항코드 (조회조건) **/
	private String sPrtAtCode;

	/** 자산코드 (조회조건) **/
	private String sAssetsCd;

	/** 자산부코드 (조회조건) **/
	private String sAssetsSubCd;

	/** 시설코드 (조회조건) **/
	private String sPrtFcltyCd;

	/** 시설명 (조회조건) **/
	private String sPrtFcltyNm;

	/** 시설물 관리 번호 (조회조건-첨부파일) **/
	private String sFcltsMngNo;

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
	 * @return the sAssetsCd
	 */
	public String getsAssetsCd() {
		return sAssetsCd;
	}

	/**
	 * @param sAssetsCd the sAssetsCd to set
	 */
	public void setsAssetsCd(String sAssetsCd) {
		this.sAssetsCd = sAssetsCd;
	}

	/**
	 * @return the sAssetsSubCd
	 */
	public String getsAssetsSubCd() {
		return sAssetsSubCd;
	}

	/**
	 * @param sAssetsSubCd the sAssetsSubCd to set
	 */
	public void setsAssetsSubCd(String sAssetsSubCd) {
		this.sAssetsSubCd = sAssetsSubCd;
	}

	/**
	 * @return the sPrtFcltyCd
	 */
	public String getsPrtFcltyCd() {
		return sPrtFcltyCd;
	}

	/**
	 * @param sFcltyCd the sFcltyCd to set
	 */
	public void setsPrtFcltyCd(String sPrtFcltyCd) {
		this.sPrtFcltyCd = sPrtFcltyCd;
	}

	/**
	 * @return the sPrtFcltyNm
	 */
	public String getsPrtFcltyNm() {
		return sPrtFcltyNm;
	}

	/**
	 * @param sPrtFcltyNm the sPrtFcltyNm to set
	 */
	public void setsPrtFcltyNm(String sPrtFcltyNm) {
		this.sPrtFcltyNm = sPrtFcltyNm;
	}

	/**
	 * @return the sFcltsMngNo
	 */
	public String getsFcltsMngNo() {
		return sFcltsMngNo;
	}

	/**
	 * @param sFcltsMngNo the sFcltsMngNo to set
	 */
	public void setsFcltsMngNo(String sFcltsMngNo) {
		this.sFcltsMngNo = sFcltsMngNo;
	}

}
