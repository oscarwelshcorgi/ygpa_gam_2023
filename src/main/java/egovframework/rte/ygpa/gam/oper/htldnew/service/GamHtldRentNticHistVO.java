/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 12.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentNticHistVO extends GamHtldRentNticDefaultVO{
	private static final long serialVersionUID = 1L;
	
	private String 	entrpsCd; 		/** 업체코드 */
	
	private String 	rcivSe;				/** 납부상태 */
	private String	prvBillDt;			/** 이전고지일자 */
	private String	billDt;				/** 고지일자 */
	private String	dlyDueDt;	    	/** 연체납부기한 */
	private String	dlyBillAmnt; 		/** 연체금액 */
	private String	dbillAmnt; 		/** 연체금액 */
	private String	dlySerNo;	    	/** 연체횟수 */
	private String	arrrgTariff;		/** 연체요율 */
	private String	arrrgPayDates; 	/** 연체일수 */
	
	private String	updUsr; 			/** 수정자 */

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
	 * @return the prvBillDt
	 */
	public String getPrvBillDt() {
		return prvBillDt;
	}

	/**
	 * @param prvBillDt the prvBillDt to set
	 */
	public void setPrvBillDt(String prvBillDt) {
		this.prvBillDt = prvBillDt;
	}

	/**
	 * @return the billDt
	 */
	public String getBillDt() {
		return billDt;
	}

	/**
	 * @param billDt the billDt to set
	 */
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}

	/**
	 * @return the dlyDueDt
	 */
	public String getDlyDueDt() {
		return dlyDueDt;
	}

	/**
	 * @param dlyDueDt the dlyDueDt to set
	 */
	public void setDlyDueDt(String dlyDueDt) {
		this.dlyDueDt = dlyDueDt;
	}

	/**
	 * @return the dlyBillAmnt
	 */
	public String getDlyBillAmnt() {
		return dlyBillAmnt;
	}

	/**
	 * @param dlyBillAmnt the dlyBillAmnt to set
	 */
	public void setDlyBillAmnt(String dlyBillAmnt) {
		this.dlyBillAmnt = dlyBillAmnt;
	}

	/**
	 * @return the dbillAmnt
	 */
	public String getDbillAmnt() {
		return dbillAmnt;
	}

	/**
	 * @param dbillAmnt the dbillAmnt to set
	 */
	public void setDbillAmnt(String dbillAmnt) {
		this.dbillAmnt = dbillAmnt;
	}

	/**
	 * @return the dlySerNo
	 */
	public String getDlySerNo() {
		return dlySerNo;
	}

	/**
	 * @param dlySerNo the dlySerNo to set
	 */
	public void setDlySerNo(String dlySerNo) {
		this.dlySerNo = dlySerNo;
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
	 * @return the arrrgPayDates
	 */
	public String getArrrgPayDates() {
		return arrrgPayDates;
	}

	/**
	 * @param arrrgPayDates the arrrgPayDates to set
	 */
	public void setArrrgPayDates(String arrrgPayDates) {
		this.arrrgPayDates = arrrgPayDates;
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
}
