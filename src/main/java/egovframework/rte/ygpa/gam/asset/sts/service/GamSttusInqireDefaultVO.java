package egovframework.rte.ygpa.gam.asset.sts.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSttusInqireDefaultVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;

    /** 항코드 */
    private String sPrtAtCode;

    /** 사용기간 FORM */
    private String sYear;

    /** 사용기간 FORM */
    private String sSearchDtFrom;

    /** 사용기간 TO */
    private String sSearchDtTo;

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
	 * @return the sSearchDtFrom
	 */
	public String getsSearchDtFrom() {
		return sSearchDtFrom;
	}

	/**
	 * @param sSearchDtFrom the sSearchDtFrom to set
	 */
	public void setsSearchDtFrom(String sSearchDtFrom) {
		this.sSearchDtFrom = sSearchDtFrom;
	}

	/**
	 * @return the sSearchDtTo
	 */
	public String getsSearchDtTo() {
		return sSearchDtTo;
	}

	/**
	 * @param sSearchDtTo the sSearchDtTo to set
	 */
	public void setsSearchDtTo(String sSearchDtTo) {
		this.sSearchDtTo = sSearchDtTo;
	}

	/**
	 * @return the sYear
	 */
	public String getsYear() {
		return sYear;
	}

	/**
	 * @param sYear the sYear to set
	 */
	public void setsYear(String sYear) {
		this.sYear = sYear;
	}



}
