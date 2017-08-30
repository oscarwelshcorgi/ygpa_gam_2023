/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.ocr.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2017. 8. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2017. 8. 25.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamOcrRecvInquireVO extends ComDefaultVO{
	private static final long serialVersionUID = 1L;

	private String	prtAtCode;
	private String	feeTp;
	private String	fiscalYr;
	private String 	billNo;
	private String	dlySerNo;
	private String 	billDt;
	private String 	ocrDt;
	private String 	dueDate;
	private String 	rcvdDt;
	private String	agentName;
	private String  rcvdAmnt;
	private String 	commission;
	private String 	custommerNum;
	private String	revResult;
	private String	revResultNm;
	private String	retMsg;
	private String  vacctNo;
	private String 	rcvoTp;
	private String	gubun;
	private String 	fileGubun;
	
/*	private String 	sRcvdPdFrom;
	private String 	sRcvdPdTo;
	private String	sOcrPdFrom;
	private String	sOcrPdTo;
*/
	private String	sFrom;
	private String	sTo;
	private String 	sPrtAtCode;
	private String	sFiscalYr;
	private String	sFeeTp;
	private String	sBillNo;
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
	 * @return the ocrDt
	 */
	public String getOcrDt() {
		return ocrDt;
	}
	/**
	 * @param ocrDt the ocrDt to set
	 */
	public void setOcrDt(String ocrDt) {
		this.ocrDt = ocrDt;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the rcvdDt
	 */
	public String getRcvdDt() {
		return rcvdDt;
	}
	/**
	 * @param rcvdDt the rcvdDt to set
	 */
	public void setRcvdDt(String rcvdDt) {
		this.rcvdDt = rcvdDt;
	}
	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	 * @return the rcvdAmnt
	 */
	public String getRcvdAmnt() {
		return rcvdAmnt;
	}
	/**
	 * @param rcvdAmnt the rcvdAmnt to set
	 */
	public void setRcvdAmnt(String rcvdAmnt) {
		this.rcvdAmnt = rcvdAmnt;
	}
	/**
	 * @return the commission
	 */
	public String getCommission() {
		return commission;
	}
	/**
	 * @param commission the commission to set
	 */
	public void setCommission(String commission) {
		this.commission = commission;
	}
	/**
	 * @return the custommerNum
	 */
	public String getCustommerNum() {
		return custommerNum;
	}
	/**
	 * @param custommerNum the custommerNum to set
	 */
	public void setCustommerNum(String custommerNum) {
		this.custommerNum = custommerNum;
	}
	/**
	 * @return the revResult
	 */
	public String getRevResult() {
		return revResult;
	}
	/**
	 * @param revResult the revResult to set
	 */
	public void setRevResult(String revResult) {
		this.revResult = revResult;
	}
	/**
	 * @return the revResultNm
	 */
	public String getRevResultNm() {
		return revResultNm;
	}
	/**
	 * @param revResultNm the revResultNm to set
	 */
	public void setRevResultNm(String revResultNm) {
		this.revResultNm = revResultNm;
	}
	/**
	 * @return the retMsg
	 */
	public String getRetMsg() {
		return retMsg;
	}
	/**
	 * @param retMsg the retMsg to set
	 */
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	/**
	 * @return the vacctNo
	 */
	public String getVacctNo() {
		return vacctNo;
	}
	/**
	 * @param vacctNo the vacctNo to set
	 */
	public void setVacctNo(String vacctNo) {
		this.vacctNo = vacctNo;
	}
	/**
	 * @return the rcvoTp
	 */
	public String getRcvoTp() {
		return rcvoTp;
	}
	/**
	 * @param rcvoTp the rcvoTp to set
	 */
	public void setRcvoTp(String rcvoTp) {
		this.rcvoTp = rcvoTp;
	}
	/**
	 * @return the gubun
	 */
	public String getGubun() {
		return gubun;
	}
	/**
	 * @param gubun the gubun to set
	 */
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	/**
	 * @return the fileGubun
	 */
	public String getFileGubun() {
		return fileGubun;
	}
	/**
	 * @param fileGubun the fileGubun to set
	 */
	public void setFileGubun(String fileGubun) {
		this.fileGubun = fileGubun;
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
	 * @return the sFiscalYr
	 */
	public String getsFiscalYr() {
		return sFiscalYr;
	}
	/**
	 * @param sFiscalYr the sFiscalYr to set
	 */
	public void setsFiscalYr(String sFiscalYr) {
		this.sFiscalYr = sFiscalYr;
	}
	/**
	 * @return the sFeeTp
	 */
	public String getsFeeTp() {
		return sFeeTp;
	}
	/**
	 * @param sFeeTp the sFeeTp to set
	 */
	public void setsFeeTp(String sFeeTp) {
		this.sFeeTp = sFeeTp;
	}
	/**
	 * @return the sBillNo
	 */
	public String getsBillNo() {
		return sBillNo;
	}
	/**
	 * @param sBillNo the sBillNo to set
	 */
	public void setsBillNo(String sBillNo) {
		this.sBillNo = sBillNo;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the sTo
	 */
	public String getsTo() {
		return sTo;
	}
	/**
	 * @param sTo the sTo to set
	 */
	public void setsTo(String sTo) {
		this.sTo = sTo;
	}
	/**
	 * @return the sFrom
	 */
	public String getsFrom() {
		return sFrom;
	}
	/**
	 * @param sFrom the sFrom to set
	 */
	public void setsFrom(String sFrom) {
		this.sFrom = sFrom;
	}
}
