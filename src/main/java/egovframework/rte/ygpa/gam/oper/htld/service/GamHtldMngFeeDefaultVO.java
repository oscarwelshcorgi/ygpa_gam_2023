/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htld.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 12. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 29.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldMngFeeDefaultVO extends GamHtldRentFeeMngtVO {
	private static final long serialVersionUID = 1L;

	/**
     * 결재자 고지방법
     */
    private String sNticMth;

    /**
     * 결재자 횟수
     */
    private String sNticCnt;

    /** 검색조건 */
    private String sPrtAtCode;

    /** 검색조건 */
    private String sMngYear;

    /** 검색조건 */
    private String sMngNo;

    /** 검색조건 */
    private String sMngCnt;

    /** 검색조건 */
    private String sReqstSeCd;

    /** 검색조건 */
    private String sEntrpscd;

    /**
     * 업체명 검색
     */
    private String sEntrpsNm;

    /** 검색조건 */
    private String sUsagePrposCd;

    /** 검색조건 */
    private String sPrmisnYn;


    /** 검색조건 */
    private String sRrArFrom;

    /** 검색조건 */
    private String sRrArTo;

    /** 검색 조건 */
    private String searchOption;

    /** 검색 시작 일자 */
    private String searchFrom;

    /** 검색 종료 일자 */
    private String searchTo;

    /**
	 * @return the sNticMth
	 */
	public String getsNticMth() {
		return sNticMth;
	}

	/**
	 * @param sNticMth the sNticMth to set
	 */
	public void setsNticMth(String sNticMth) {
		this.sNticMth = sNticMth;
	}

	/**
	 * @return the sNticCnt
	 */
	public String getsNticCnt() {
		return sNticCnt;
	}

	/**
	 * @param sNticCnt the sNticCnt to set
	 */
	public void setsNticCnt(String sNticCnt) {
		this.sNticCnt = sNticCnt;
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
	 * @return the sMngYear
	 */
	public String getsMngYear() {
		return sMngYear;
	}

	/**
	 * @param sMngYear the sMngYear to set
	 */
	public void setsMngYear(String sMngYear) {
		this.sMngYear = sMngYear;
	}

	/**
	 * @return the sMngNo
	 */
	public String getsMngNo() {
		return sMngNo;
	}

	/**
	 * @param sMngNo the sMngNo to set
	 */
	public void setsMngNo(String sMngNo) {
		this.sMngNo = sMngNo;
	}

	/**
	 * @return the sMngCnt
	 */
	public String getsMngCnt() {
		return sMngCnt;
	}

	/**
	 * @param sMngCnt the sMngCnt to set
	 */
	public void setsMngCnt(String sMngCnt) {
		this.sMngCnt = sMngCnt;
	}

	/**
	 * @return the sReqstSeCd
	 */
	public String getsReqstSeCd() {
		return sReqstSeCd;
	}

	/**
	 * @param sReqstSeCd the sReqstSeCd to set
	 */
	public void setsReqstSeCd(String sReqstSeCd) {
		this.sReqstSeCd = sReqstSeCd;
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
	 * @return the sUsagePrposCd
	 */
	public String getsUsagePrposCd() {
		return sUsagePrposCd;
	}

	/**
	 * @param sUsagePrposCd the sUsagePrposCd to set
	 */
	public void setsUsagePrposCd(String sUsagePrposCd) {
		this.sUsagePrposCd = sUsagePrposCd;
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
	 * @return the sRrArFrom
	 */
	public String getsRrArFrom() {
		return sRrArFrom;
	}

	/**
	 * @param sRrArFrom the sRrArFrom to set
	 */
	public void setsRrArFrom(String sRrArFrom) {
		this.sRrArFrom = sRrArFrom;
	}

	/**
	 * @return the sRrArTo
	 */
	public String getsRrArTo() {
		return sRrArTo;
	}

	/**
	 * @param sRrArTo the sRrArTo to set
	 */
	public void setsRrArTo(String sRrArTo) {
		this.sRrArTo = sRrArTo;
	}

	/**
	 * @return the searchOption
	 */
	public String getSearchOption() {
		return searchOption;
	}

	/**
	 * @param searchOption the searchOption to set
	 */
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	/**
	 * @return the searchFrom
	 */
	public String getSearchFrom() {
		return searchFrom;
	}

	/**
	 * @param searchFrom the searchFrom to set
	 */
	public void setSearchFrom(String searchFrom) {
		this.searchFrom = searchFrom;
	}

	/**
	 * @return the searchTo
	 */
	public String getSearchTo() {
		return searchTo;
	}

	/**
	 * @param searchTo the searchTo to set
	 */
	public void setSearchTo(String searchTo) {
		this.searchTo = searchTo;
	}

	/**
	 * @return the sEntrpsNm
	 */
	public String getsEntrpsNm() {
		return sEntrpsNm;
	}

	/**
	 * @param sEntrpsNm the sEntrpsNm to set
	 */
	public void setsEntrpsNm(String sEntrpsNm) {
		this.sEntrpsNm = sEntrpsNm;
	}



}
