/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 7.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentNticInfoVO extends GamHtldRentNticDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String 	nticDt; 			/** 고지일자 */

	private String	entrpsCd;			/** 업체코드 */
	private String	entrpsNm;		/** 업체명 */
	private String 	rprsntvNm;		/** 대표자 */
	private String	paySe;				/** 납부구분 : 4 분기납 6 연납 */
	private String 	paySttus;			/** 납부상태 */

	private String 	nticSeq;			/** 고지순번 */
	private String	nticBeginDt;		/** 고지대상기간 시작일 */
	private String	nticEndDt;		/** 고지대상기간 종료일 */
	private String 	payTmlmt;		/** 납부기한 */
	private String 	rntfee;				/** 임대료 */
	private String	payinstIntr;		/** 분납이자 */
	private String	intrRate;			/** 이자율 */
	private String 	supAmt;			/** 공급가 */
	private String	vat;				/** 부가세 */
	private String	payAmt;			/** 고지금액 */
	private String 	rcivSe;				/** 수납구분 */

	private String	rm;					/** 비고 */
	
	private String 	regUsr;			/** 등록자 */
	private String 	updUsr;			/** 수정자 */
	private String	deptCd;			/** 사용자 정보 */
	private String	fcltySe;			/** 사용자 정보 */
	private String	userName; 		/** 사용자 정보 */
	/**
	 * @return the nticDt
	 */
	public String getNticDt() {
		return nticDt;
	}
	/**
	 * @param nticDt the nticDt to set
	 */
	public void setNticDt(String nticDt) {
		this.nticDt = nticDt;
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
	 * @return the rprsntvNm
	 */
	public String getRprsntvNm() {
		return rprsntvNm;
	}
	/**
	 * @param rprsntvNm the rprsntvNm to set
	 */
	public void setRprsntvNm(String rprsntvNm) {
		this.rprsntvNm = rprsntvNm;
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
	 * @return the paySttus
	 */
	public String getPaySttus() {
		return paySttus;
	}
	/**
	 * @param paySttus the paySttus to set
	 */
	public void setPaySttus(String paySttus) {
		this.paySttus = paySttus;
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
	 * @return the intrRate
	 */
	public String getIntrRate() {
		return intrRate;
	}
	/**
	 * @param intrRate the intrRate to set
	 */
	public void setIntrRate(String intrRate) {
		this.intrRate = intrRate;
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
	 * @return the deptCd
	 */
	public String getDeptCd() {
		return deptCd;
	}
	/**
	 * @param deptCd the deptCd to set
	 */
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	/**
	 * @return the fcltySe
	 */
	public String getFcltySe() {
		return fcltySe;
	}
	/**
	 * @param fcltySe the fcltySe to set
	 */
	public void setFcltySe(String fcltySe) {
		this.fcltySe = fcltySe;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
