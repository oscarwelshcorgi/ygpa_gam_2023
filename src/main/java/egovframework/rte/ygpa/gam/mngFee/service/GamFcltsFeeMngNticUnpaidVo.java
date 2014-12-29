/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 29.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltsFeeMngNticUnpaidVo extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private String	prtAtCode;			//항 코드
	private String	chrgeKnd;			//요금 종류
	private String	accnutYear;			//회계 년도
	private String	nticNo;				//고지 번호
	private String	feeTp;				//요금 종류
	private String	fiscalYr;			//회계 년도
	private String	billNo;				//고지 번호
	private String	dlySerNo;			//연체 횟수
	private String	prvBillDt;			//이전 고지 일자
	private String	prvDueDt;			//이전 납부 기한
	private String	dlyBillDt;			//연체 고지 일자
	private String	dlyDueDt;			//연체 납부 기한
	private String	dlyBillAmnt;		//연체 금액
	private String	dlyBillPrtYn;		//출력 여부
	private String	dbillAmnt;			//순 연체 금액
	private String	dlyRcvdTp;			//연체 수납 구분
	private String	dlyRcvdTpNm;		//연체 수납 구분 명
	private String	dlyRcvdDt;			//연체 수납 일자
	private String	arrrgTariff;		//연체 요율
	private String	arrrgPayDates;		//연체 일수
	private String	dlyBillRsn;			//산출 내역
	private String	djiroAmnt;			//지로 금액

	/**
	 * @return the prtAtCode
	 */
	public String getPrtAtCode() {
		return prtAtCode;
	}
	/**
	 * @param prtAtCode the prtAtCode to set
	 */
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}
	/**
	 * @return the chrgeKnd
	 */
	public String getChrgeKnd() {
		return chrgeKnd;
	}
	/**
	 * @param chrgeKnd the chrgeKnd to set
	 */
	public void setChrgeKnd(String chrgeKnd) {
		this.chrgeKnd = chrgeKnd;
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
	 * @return the nticNo
	 */
	public String getNticNo() {
		return nticNo;
	}
	/**
	 * @param nticNo the nticNo to set
	 */
	public void setNticNo(String nticNo) {
		this.nticNo = nticNo;
	}
	/**
	 * @return the feeTp
	 */
	public String getFeeTp() {
		return feeTp;
	}
	/**
	 * @param feeTp the feeTp to set
	 */
	public void setFeeTp(String feeTp) {
		this.feeTp = feeTp;
	}
	/**
	 * @return the fiscalYr
	 */
	public String getFiscalYr() {
		return fiscalYr;
	}
	/**
	 * @param fiscalYr the fiscalYr to set
	 */
	public void setFiscalYr(String fiscalYr) {
		this.fiscalYr = fiscalYr;
	}
	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}
	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
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
	 * @return the prvDueDt
	 */
	public String getPrvDueDt() {
		return prvDueDt;
	}
	/**
	 * @param prvDueDt the prvDueDt to set
	 */
	public void setPrvDueDt(String prvDueDt) {
		this.prvDueDt = prvDueDt;
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
	 * @return the dlyBillPrtYn
	 */
	public String getDlyBillPrtYn() {
		return dlyBillPrtYn;
	}
	/**
	 * @param dlyBillPrtYn the dlyBillPrtYn to set
	 */
	public void setDlyBillPrtYn(String dlyBillPrtYn) {
		this.dlyBillPrtYn = dlyBillPrtYn;
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
	 * @return the dlyRcvdTp
	 */
	public String getDlyRcvdTp() {
		return dlyRcvdTp;
	}
	/**
	 * @param dlyRcvdTp the dlyRcvdTp to set
	 */
	public void setDlyRcvdTp(String dlyRcvdTp) {
		this.dlyRcvdTp = dlyRcvdTp;
	}
	/**
	 * @return the dlyRcvdTpNm
	 */
	public String getDlyRcvdTpNm() {
		return dlyRcvdTpNm;
	}
	/**
	 * @param dlyRcvdTpNm the dlyRcvdTpNm to set
	 */
	public void setDlyRcvdTpNm(String dlyRcvdTpNm) {
		this.dlyRcvdTpNm = dlyRcvdTpNm;
	}
	/**
	 * @return the dlyRcvdDt
	 */
	public String getDlyRcvdDt() {
		return dlyRcvdDt;
	}
	/**
	 * @param dlyRcvdDt the dlyRcvdDt to set
	 */
	public void setDlyRcvdDt(String dlyRcvdDt) {
		this.dlyRcvdDt = dlyRcvdDt;
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
	 * @return the djiroAmnt
	 */
	public String getDjiroAmnt() {
		return djiroAmnt;
	}
	/**
	 * @param djiroAmnt the djiroAmnt to set
	 */
	public void setDjiroAmnt(String djiroAmnt) {
		this.djiroAmnt = djiroAmnt;
	}

}
