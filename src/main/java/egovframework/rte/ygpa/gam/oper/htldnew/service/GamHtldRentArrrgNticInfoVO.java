/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 11.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentArrrgNticInfoVO extends GamHtldRentNticDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String	arrrgNo;			/** 연체 횟수 */
	private String 	dlyBillDt;			/** 연체고지일자 */
	private String	newPayTmlmt;	/** 연체납부기한 */
	private String 	nticAmt; 			/** 원고지금액 */
	private String	arrrgAmt;			/** 연체료 */
	private String	arrrgTariff;		/** 연체요율 */
	private String	arrrgNticAmt; 	/** 연체고지금액 */
	private String 	dlyBillRsn; 		/** 산출내역 */
	private String 	regUsr;			/** 등록자 */
	private String 	updUsr;			/** 수정자 */
	private String	deptCd;			/** 사용자 정보 */
	private String	userName; 		/** 사용자 정보 */
	private String 	emplNo; 			/** 사용자 정보 */
	/**
	 * @return the arrrgNo
	 */
	public String getArrrgNo() {
		return arrrgNo;
	}
	/**
	 * @param arrrgNo the arrrgNo to set
	 */
	public void setArrrgNo(String arrrgNo) {
		this.arrrgNo = arrrgNo;
	}
	/**
	 * @return the dlyBillDt
	 */
	public String getDlyBillDt() {
		return dlyBillDt;
	}
	/**
	 * @param dlyBillDt the dlyBillDt to set
	 */
	public void setDlyBillDt(String dlyBillDt) {
		this.dlyBillDt = dlyBillDt;
	}
	/**
	 * @return the newPayTmlmt
	 */
	public String getNewPayTmlmt() {
		return newPayTmlmt;
	}
	/**
	 * @param newPayTmlmt the newPayTmlmt to set
	 */
	public void setNewPayTmlmt(String newPayTmlmt) {
		this.newPayTmlmt = newPayTmlmt;
	}
	/**
	 * @return the nticAmt
	 */
	public String getNticAmt() {
		return nticAmt;
	}
	/**
	 * @param nticAmt the nticAmt to set
	 */
	public void setNticAmt(String nticAmt) {
		this.nticAmt = nticAmt;
	}
	/**
	 * @return the arrrgAmt
	 */
	public String getArrrgAmt() {
		return arrrgAmt;
	}
	/**
	 * @param arrrgAmt the arrrgAmt to set
	 */
	public void setArrrgAmt(String arrrgAmt) {
		this.arrrgAmt = arrrgAmt;
	}
	/**
	 * @return the arrrgTariff
	 */
	public String getArrrgTariff() {
		return arrrgTariff;
	}
	/**
	 * @param arrrgTariff the arrrgTariff to set
	 */
	public void setArrrgTariff(String arrrgTariff) {
		this.arrrgTariff = arrrgTariff;
	}
	/**
	 * @return the arrrgNticAmt
	 */
	public String getArrrgNticAmt() {
		return arrrgNticAmt;
	}
	/**
	 * @param arrrgNticAmt the arrrgNticAmt to set
	 */
	public void setArrrgNticAmt(String arrrgNticAmt) {
		this.arrrgNticAmt = arrrgNticAmt;
	}
	/**
	 * @return the dlyBillRsn
	 */
	public String getDlyBillRsn() {
		return dlyBillRsn;
	}
	/**
	 * @param dlyBillRsn the dlyBillRsn to set
	 */
	public void setDlyBillRsn(String dlyBillRsn) {
		this.dlyBillRsn = dlyBillRsn;
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
	/**
	 * @return the emplNo
	 */
	public String getEmplNo() {
		return emplNo;
	}
	/**
	 * @param emplNo the emplNo to set
	 */
	public void setEmplNo(String emplNo) {
		this.emplNo = emplNo;
	}
}
