/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 3.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentCtrtDetailVO extends ComDefaultVO{
	private static final long serialVersionUID = 1L;

	private String mngYear;				/** 관리년도 */
	private String mngNo; 				/** 관리번호 */
	private String mngSeq;				/** 관리순번 */
	private String registSeq; 				/** 등록순번 */
	private String histSeq;					/** 이력순번 */
	private String gisAssetsPrtAtCode;	/** GIS 자산 항코드 */
	private String gisAssetsCd;			/** GIS 자산	코드 */
	private String gisAssetsSubCd;		/** GIS 자산 SUB코드 */
	private String assetsNm;				/** 자산명 */
	private String detailPdBegin;			/** 상세 기간 시작일 */
	private String detailPdEnd;			/** 상세 기간 종료일 */
	private String rentAr;					/** 임대면적 */
	private String rentArSe;				/** 부지구분 */
	private String applcRntfee;			/** 적용 임대료 */
	private String priceSe;					/** 적용 임대료 구분 */
	private String aseRntfee;				/** 실적평가 임대료 */
	private String aseApplcBegin;			/** 실적평가 적용기간 시작일 */
	private String aseApplcEnd;			/** 실적평가 적용기간 종료일 */
	private String applcRsn;				/** 실적평가 적용 사유 */
	private String rm;						/** 비고 */
	private String regUsr; 					/** 등록자 */
	private String registDt; 				/** 등록일시 */
	private String updUsr;					/** 수정자 */
	private String updtDt;					/** 수정일시 */
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
	 * @return the mngSeq
	 */
	public String getMngSeq() {
		return mngSeq;
	}
	/**
	 * @param mngSeq the mngSeq to set
	 */
	public void setMngSeq(String mngSeq) {
		this.mngSeq = mngSeq;
	}
	/**
	 * @return the registSeq
	 */
	public String getRegistSeq() {
		return registSeq;
	}
	/**
	 * @param registSeq the registSeq to set
	 */
	public void setRegistSeq(String registSeq) {
		this.registSeq = registSeq;
	}
	/**
	 * @return the histSeq
	 */
	public String getHistSeq() {
		return histSeq;
	}
	/**
	 * @param histSeq the histSeq to set
	 */
	public void setHistSeq(String histSeq) {
		this.histSeq = histSeq;
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
	 * @return the assetsNm
	 */
	public String getAssetsNm() {
		return assetsNm;
	}
	/**
	 * @param assetsNm the assetsNm to set
	 */
	public void setAssetsNm(String assetsNm) {
		this.assetsNm = assetsNm;
	}
	/**
	 * @return the detailPdBegin
	 */
	public String getDetailPdBegin() {
		return detailPdBegin;
	}
	/**
	 * @param detailPdBegin the detailPdBegin to set
	 */
	public void setDetailPdBegin(String detailPdBegin) {
		this.detailPdBegin = detailPdBegin;
	}
	/**
	 * @return the detailPdEnd
	 */
	public String getDetailPdEnd() {
		return detailPdEnd;
	}
	/**
	 * @param detailPdEnd the detailPdEnd to set
	 */
	public void setDetailPdEnd(String detailPdEnd) {
		this.detailPdEnd = detailPdEnd;
	}
	/**
	 * @return the rentAr
	 */
	public String getRentAr() {
		return rentAr;
	}
	/**
	 * @param rentAr the rentAr to set
	 */
	public void setRentAr(String rentAr) {
		this.rentAr = rentAr;
	}
	/**
	 * @return the rentArSe
	 */
	public String getRentArSe() {
		return rentArSe;
	}
	/**
	 * @param rentArSe the rentArSe to set
	 */
	public void setRentArSe(String rentArSe) {
		this.rentArSe = rentArSe;
	}
	/**
	 * @return the applcRntfee
	 */
	public String getApplcRntfee() {
		return applcRntfee;
	}
	/**
	 * @param applcRntfee the applcRntfee to set
	 */
	public void setApplcRntfee(String applcRntfee) {
		this.applcRntfee = applcRntfee;
	}
	/**
	 * @return the priceSe
	 */
	public String getPriceSe() {
		return priceSe;
	}
	/**
	 * @param priceSe the priceSe to set
	 */
	public void setPriceSe(String priceSe) {
		this.priceSe = priceSe;
	}
	/**
	 * @return the aseRntfee
	 */
	public String getAseRntfee() {
		return aseRntfee;
	}
	/**
	 * @param aseRntfee the aseRntfee to set
	 */
	public void setAseRntfee(String aseRntfee) {
		this.aseRntfee = aseRntfee;
	}
	/**
	 * @return the aseApplcBegin
	 */
	public String getAseApplcBegin() {
		return aseApplcBegin;
	}
	/**
	 * @param aseApplcBegin the aseApplcBegin to set
	 */
	public void setAseApplcBegin(String aseApplcBegin) {
		this.aseApplcBegin = aseApplcBegin;
	}
	/**
	 * @return the aseApplcEnd
	 */
	public String getAseApplcEnd() {
		return aseApplcEnd;
	}
	/**
	 * @param aseApplcEnd the aseApplcEnd to set
	 */
	public void setAseApplcEnd(String aseApplcEnd) {
		this.aseApplcEnd = aseApplcEnd;
	}
	/**
	 * @return the applcRsn
	 */
	public String getApplcRsn() {
		return applcRsn;
	}
	/**
	 * @param applcRsn the applcRsn to set
	 */
	public void setApplcRsn(String applcRsn) {
		this.applcRsn = applcRsn;
	}
	/**
	 * @return the rm
	 */
	public String getRm() {
		return rm;
	}
	/**
	 * @param rm the rm to set
	 */
	public void setRm(String rm) {
		this.rm = rm;
	}
	/**
	 * @return the regUsr
	 */
	public String getRegUsr() {
		return regUsr;
	}
	/**
	 * @param regUsr the regUsr to set
	 */
	public void setRegUsr(String regUsr) {
		this.regUsr = regUsr;
	}
	/**
	 * @return the registDt
	 */
	public String getRegistDt() {
		return registDt;
	}
	/**
	 * @param registDt the registDt to set
	 */
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}
	/**
	 * @return the updUsr
	 */
	public String getUpdUsr() {
		return updUsr;
	}
	/**
	 * @param updUsr the updUsr to set
	 */
	public void setUpdUsr(String updUsr) {
		this.updUsr = updUsr;
	}
	/**
	 * @return the updtDt
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * @param updtDt the updtDt to set
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
}
