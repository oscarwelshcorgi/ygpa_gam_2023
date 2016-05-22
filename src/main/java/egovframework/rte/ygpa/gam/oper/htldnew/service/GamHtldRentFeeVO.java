/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 18.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 18.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentFeeVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	
	private String mngYear;				/** 관리년도 */
	private String mngNo; 				/** 관리번호 */
	private String mngSeq;				/** 관리순번 */
	private String rntfeeSeq;				/** 임대료순번 */
	private String rentDetailRegistSeq;	/** 관리상세순번 */
	private String rntfeeSe; 				/** 임대료 종류 0 : 일반임대료 1 : 실적평가정산분 2 : 지적측량 정산분*/
	private String nticBeginDt;				/** 고지대상시작일 */
	private String nticEndDt;				/** 고지대상종료일 */
	private String rntfee;					/** 임대료 */
	private String payinstIntr;				/** 분납이자 */
	private String supAmt;					/** 공급가액 */
	private String vat; 						/** 부가세 */
	private String payAmt; 				/** 고지금액 */
	private String accnutYear;				/** 회계년도 */
	private String rntfeeNticNo;			/** 임대료고지번호 */
	private String nticSeq;					/** 고지순번 */
	private String payTmlmt;				/** 납부기한 */
	private String rcivSe;					/** 수납구분 이 값이 null인 경우 미고지 */
	private String rcivDt; 					/** 수납일자 */
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
	 * @return the rntfeeSeq
	 */
	public String getRntfeeSeq() {
		return rntfeeSeq;
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
