package egovframework.rte.ygpa.gam.oper.htld.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : GamHtldRentMngtVO.java
 * @Description : 배후단지임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentDefaultVO extends GamHtldRentMngtVO {
    private static final long serialVersionUID = 1L;

    /** 구역 */
    private String sRentAreaCd;

    /** 항코드 */
    private String sPrtAtCode;

    /** 관리년도 */
    private String sMngYear;

    /** 관리번호 */
    private String sMngNo;

    /** 관리순번 */
    private String sMngCnt;

    /** 업체구분코드 */
    private String sReqstSeCd;

    /** 업체명 */
    private String sEntrpsNm;

    /** 업체코드 */
    private String sEntrpscd;

    /** 승낙여부 */
    private String sPrmisnYn;

    /** 사용기간 시작 */
    private String sGrUsagePdFrom;

    /** 사용기간 끝 */
    private String sGrUsagePdTo;

    /** 조회 면적 (이상) */
    private String sGrAr;
    
    /** 계약해지(변경)유무 */
    private String sTermnKnd;
         
	public String getsRentArea() {
		return sRentAreaCd;
	}

	public void setsRentArea(String sRentArea) {
		this.sRentAreaCd = sRentArea;
	}

	public String getsPrtAtCode() {
		return sPrtAtCode;
	}

	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
	}

	public String getsMngYear() {
		return sMngYear;
	}

	public void setsMngYear(String sMngYear) {
		this.sMngYear = sMngYear;
	}

	public String getsMngNo() {
		return sMngNo;
	}

	public void setsMngNo(String sMngNo) {
		this.sMngNo = sMngNo;
	}

	public String getsMngCnt() {
		return sMngCnt;
	}

	public void setsMngCnt(String sMngCnt) {
		this.sMngCnt = sMngCnt;
	}

	public String getsReqstSeCd() {
		return sReqstSeCd;
	}

	public void setsReqstSeCd(String sReqstSeCd) {
		this.sReqstSeCd = sReqstSeCd;
	}

	public String getsEntrpsNm() {
		return sEntrpsNm;
	}

	public void setsEntrpsNm(String sEntrpsNm) {
		this.sEntrpsNm = sEntrpsNm;
	}

	public String getsEntrpscd() {
		return sEntrpscd;
	}

	public void setsEntrpscd(String sEntrpscd) {
		this.sEntrpscd = sEntrpscd;
	}

	public String getsPrmisnYn() {
		return sPrmisnYn;
	}

	public void setsPrmisnYn(String sPrmisnYn) {
		this.sPrmisnYn = sPrmisnYn;
	}

	public String getsGrUsagePdFrom() {
		return sGrUsagePdFrom;
	}

	public void setsGrUsagePdFrom(String sGrUsagePdFrom) {
		this.sGrUsagePdFrom = sGrUsagePdFrom;
	}

	public String getsGrUsagePdTo() {
		return sGrUsagePdTo;
	}

	public void setsGrUsagePdTo(String sGrUsagePdTo) {
		this.sGrUsagePdTo = sGrUsagePdTo;
	}

	public String getsGrAr() {
		return sGrAr;
	}

	public void setsGrAr(String sGrAr) {
		this.sGrAr = sGrAr;
	}

	/**
	 * @return the sTermnKnd
	 */
	public String getsTermnKnd() {
		return sTermnKnd;
	}

	/**
	 * @param sTermnKnd the sTermnKnd to set
	 */
	public void setsTermnKnd(String sTermnKnd) {
		this.sTermnKnd = sTermnKnd;
	}
}
