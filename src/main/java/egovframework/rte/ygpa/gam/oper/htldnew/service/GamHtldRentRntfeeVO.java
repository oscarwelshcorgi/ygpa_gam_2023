/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Jongmin
 * @since 2016. 6. 2.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 2.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentRntfeeVO extends GamHtldRentMngDefaultVO{
	private static final long serialVersionUID = 1L;

	private String rntfeeSeq;				/** 임대료순번 */
	private String rentDetailRegistSeq;	/** 등록순번 */
	private String rntfeeSe; 				/** 임대료구분 0 - 일반 1 -실적 2- 지적 3 - 추가*/
	private String rntfeeSeNm; 			/** 임대료구분이름 */
	private String nticBeginDt; 			/** 고지대상시작일 */
	private String nticEndDt;				/** 고지대상종료일 */
	private String rntfee;					/** 임대료 */
	private String payinstIntr;				/** 분납이자 */
	private String supAmt;					/** 공급가액 */
	private String vat;						/** 부가세 */
	private String payAmt;					/** 납부금액 */

	private String applcBeginDt; 			/** 적용시작일 */
	private String applcEndDt; 			/** 적용종료일 */
	private String applcRntfee; 			/** 적용단가 */
	private String applcRentAr;			/** 적용면적 */
	private String rm; 						/** 비고 */
	private String regUsr;					/** 등록자 */
	private String updUsr;					/** 수정자 */

	private String accnutYear;				/** 회계년도 */
	private String rntfeeNticNo;			/** 임대료 고지번호 */
	private String nticSeq;					/** 고지순번 */
	private String payTmlmt;				/** 납부기한 */
	private String rcivSe;					/** 납부구분 */
	private String rcivDt;					/** 납부일자 */

	private String gisAssetsPrtAtCode;	/** 항코드 */
	private String gisAssetsCd;			/** 시설코드 */
	private String gisAssetsSubCd;		/** 시설SUB코드 */

	private String entrpsCd;		/**
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
	/** 업체코드 */
	private String boundCd;			/** 구역코드 */
	private String chrgeKndCd;		/** 요금종류코드 */
	private String rentAr;			/** 임대 면적 */
	private String paySe;			/** 납부방법 */
	private String ctrtBeginDt;		/** 계약 기간 시작 */
	private String ctrtEndDt;		/** 계약 기간 끝 */
	private String ctrtDt;			/** 계약 일자 */

	private String aseRntfee;		/**
	 * @return the ctrtBeginDt
	 */
	public String getCtrtBeginDt() {
		return ctrtBeginDt;
	}
	/**
	 * @param ctrtBeginDt the ctrtBeginDt to set
	 */
	public void setCtrtBeginDt(String ctrtBeginDt) {
		this.ctrtBeginDt = ctrtBeginDt;
	}
	/**
	 * @return the ctrtEndDt
	 */
	public String getCtrtEndDt() {
		return ctrtEndDt;
	}
	/**
	 * @param ctrtEndDt the ctrtEndDt to set
	 */
	public void setCtrtEndDt(String ctrtEndDt) {
		this.ctrtEndDt = ctrtEndDt;
	}
	/**
	 * @return the ctrtDt
	 */
	public String getCtrtDt() {
		return ctrtDt;
	}
	/**
	 * @param ctrtDt the ctrtDt to set
	 */
	public void setCtrtDt(String ctrtDt) {
		this.ctrtDt = ctrtDt;
	}
	/**
	 * @return the paySe
	 */
	public String getPaySe() {
		return paySe;
	}
	/**
	 * @param paySe the paySe to set
	 */
	public void setPaySe(String paySe) {
		this.paySe = paySe;
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
	 * @return the chrgeKndCd
	 */
	public String getChrgeKndCd() {
		return chrgeKndCd;
	}
	/**
	 * @param chrgeKndCd the chrgeKndCd to set
	 */
	public void setChrgeKndCd(String chrgeKndCd) {
		this.chrgeKndCd = chrgeKndCd;
	}
	/**
	 * @return the boundCd
	 */
	public String getBoundCd() {
		return boundCd;
	}
	/**
	 * @param boundCd the boundCd to set
	 */
	public void setBoundCd(String boundCd) {
		this.boundCd = boundCd;
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
	/** 평가 단가 */
	private String aseApplcBegin;	/** 평가 시작 일자 */
	private String aseApplcEnd;	/** 평가 시작 일자 */

	/**
	 * @return the rntfeeSeq
	 */
	public String getRntfeeSeq() {
		return rntfeeSeq;
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
	 * @param rntfeeSeq the rntfeeSeq to set
	 */
	public void setRntfeeSeq(String rntfeeSeq) {
		this.rntfeeSeq = rntfeeSeq;
	}
	/**
	 * @return the rentDetailRegistSeq
	 */
	public String getRentDetailRegistSeq() {
		return rentDetailRegistSeq;
	}
	/**
	 * @param rentDetailRegistSeq the rentDetailRegistSeq to set
	 */
	public void setRentDetailRegistSeq(String rentDetailRegistSeq) {
		this.rentDetailRegistSeq = rentDetailRegistSeq;
	}
	/**
	 * @return the rntfeeSe
	 */
	public String getRntfeeSe() {
		return rntfeeSe;
	}
	/**
	 * @param rntfeeSe the rntfeeSe to set
	 */
	public void setRntfeeSe(String rntfeeSe) {
		this.rntfeeSe = rntfeeSe;
	}
	/**
	 * @return the rntfeeSeNm
	 */
	public String getRntfeeSeNm() {
		return rntfeeSeNm;
	}
	/**
	 * @param rntfeeSeNm the rntfeeSeNm to set
	 */
	public void setRntfeeSeNm(String rntfeeSeNm) {
		this.rntfeeSeNm = rntfeeSeNm;
	}
	/**
	 * @return the nticBeginDt
	 */
	public String getNticBeginDt() {
		return nticBeginDt;
	}
	/**
	 * @param nticBeginDt the nticBeginDt to set
	 */
	public void setNticBeginDt(String nticBeginDt) {
		this.nticBeginDt = nticBeginDt;
	}
	/**
	 * @return the nticEndDt
	 */
	public String getNticEndDt() {
		return nticEndDt;
	}
	/**
	 * @param nticEndDt the nticEndDt to set
	 */
	public void setNticEndDt(String nticEndDt) {
		this.nticEndDt = nticEndDt;
	}
	/**
	 * @return the rntfee
	 */
	public String getRntfee() {
		return rntfee;
	}
	/**
	 * @param rntfee the rntfee to set
	 */
	public void setRntfee(String rntfee) {
		this.rntfee = rntfee;
	}
	/**
	 * @return the payinstIntr
	 */
	public String getPayinstIntr() {
		return payinstIntr;
	}
	/**
	 * @param payinstIntr the payinstIntr to set
	 */
	public void setPayinstIntr(String payinstIntr) {
		this.payinstIntr = payinstIntr;
	}
	/**
	 * @return the supAmt
	 */
	public String getSupAmt() {
		return supAmt;
	}
	/**
	 * @param supAmt the supAmt to set
	 */
	public void setSupAmt(String supAmt) {
		this.supAmt = supAmt;
	}
	/**
	 * @return the vat
	 */
	public String getVat() {
		return vat;
	}
	/**
	 * @param vat the vat to set
	 */
	public void setVat(String vat) {
		this.vat = vat;
	}
	/**
	 * @return the payAmt
	 */
	public String getPayAmt() {
		return payAmt;
	}
	/**
	 * @param payAmt the payAmt to set
	 */
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	/**
	 * @return the applcBeginDt
	 */
	public String getApplcBeginDt() {
		return applcBeginDt;
	}
	/**
	 * @param applcBeginDt the applcBeginDt to set
	 */
	public void setApplcBeginDt(String applcBeginDt) {
		this.applcBeginDt = applcBeginDt;
	}
	/**
	 * @return the applcEndDt
	 */
	public String getApplcEndDt() {
		return applcEndDt;
	}
	/**
	 * @param applcEndDt the applcEndDt to set
	 */
	public void setApplcEndDt(String applcEndDt) {
		this.applcEndDt = applcEndDt;
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
	 * @return the applcRentAr
	 */
	public String getApplcRentAr() {
		return applcRentAr;
	}
	/**
	 * @param applcRentAr the applcRentAr to set
	 */
	public void setApplcRentAr(String applcRentAr) {
		this.applcRentAr = applcRentAr;
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
	 * @return the accnutYear
	 */
	public String getAccnutYear() {
		return accnutYear;
	}
	/**
	 * @param accnutYear the accnutYear to set
	 */
	public void setAccnutYear(String accnutYear) {
		this.accnutYear = accnutYear;
	}
	/**
	 * @return the rntfeeNticNo
	 */
	public String getRntfeeNticNo() {
		return rntfeeNticNo;
	}
	/**
	 * @param rntfeeNticNo the rntfeeNticNo to set
	 */
	public void setRntfeeNticNo(String rntfeeNticNo) {
		this.rntfeeNticNo = rntfeeNticNo;
	}
	/**
	 * @return the nticSeq
	 */
	public String getNticSeq() {
		return nticSeq;
	}
	/**
	 * @param nticSeq the nticSeq to set
	 */
	public void setNticSeq(String nticSeq) {
		this.nticSeq = nticSeq;
	}
	/**
	 * @return the payTmlmt
	 */
	public String getPayTmlmt() {
		return payTmlmt;
	}
	/**
	 * @param payTmlmt the payTmlmt to set
	 */
	public void setPayTmlmt(String payTmlmt) {
		this.payTmlmt = payTmlmt;
	}
	/**
	 * @return the rcivSe
	 */
	public String getRcivSe() {
		return rcivSe;
	}
	/**
	 * @param rcivSe the rcivSe to set
	 */
	public void setRcivSe(String rcivSe) {
		this.rcivSe = rcivSe;
	}
	/**
	 * @return the rcivDt
	 */
	public String getRcivDt() {
		return rcivDt;
	}
	/**
	 * @param rcivDt the rcivDt to set
	 */
	public void setRcivDt(String rcivDt) {
		this.rcivDt = rcivDt;
	}
}
