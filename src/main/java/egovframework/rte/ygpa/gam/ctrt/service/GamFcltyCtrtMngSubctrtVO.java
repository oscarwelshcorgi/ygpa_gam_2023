/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 1. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 1. 8.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyCtrtMngSubctrtVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String subCtrtNo;			// 계약 번호
	private	String subSeq;				// 순번
	private	String subEntrpsNm;			// 업체 명
	private	String moneyPymntAgree;		// 대금 지급 합의
	private	String workClass;			// 공종
	private	String subctrtRate;			// 하도급 율
	private	String orginlContrAmt;		// 원 도급 금액
	private	String subctrtCtrtAmt;		// 하도급 계약 금액
	private	String subctrtCtrtDtFrom;	// 계약 기간 FROM
	private	String subctrtCtrtDtTo;		// 계약 기간 TO
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String moneyPymntAgreeNm;	// 대금 지급 합의 명
	private	String subCtrtSe;			// 계약 구분
	private	String subCtrtNm;			// 계약 명
	private	String subCtrtDt;			// 계약 일자
	private	String subCtrtAmt;			// 계약 금액
	private	String subCtrtDtFrom;		// 계약 기간 FROM
	private	String subCtrtDtTo;			// 계약 기간 TO
	private	String subCtrtMth;			// 계약 방법
	private	String subOrderMthd;		// 발주 방식
	private	String subCauseAct;			// 원인 행위

	/**
	 * @return the subCtrtNo
	 */
	public String getSubCtrtNo() {
		return subCtrtNo;
	}
	/**
	 * @param subCtrtNo the subCtrtNo to set
	 */
	public void setSubCtrtNo(String subCtrtNo) {
		this.subCtrtNo = subCtrtNo;
	}
	/**
	 * @return the subSeq
	 */
	public String getSubSeq() {
		return subSeq;
	}
	/**
	 * @param subSeq the subSeq to set
	 */
	public void setSubSeq(String subSeq) {
		this.subSeq = subSeq;
	}
	/**
	 * @return the subEntrpsNm
	 */
	public String getSubEntrpsNm() {
		return subEntrpsNm;
	}
	/**
	 * @param subEntrpsNm the subEntrpsNm to set
	 */
	public void setSubEntrpsNm(String subEntrpsNm) {
		this.subEntrpsNm = subEntrpsNm;
	}
	/**
	 * @return the moneyPymntAgree
	 */
	public String getMoneyPymntAgree() {
		return moneyPymntAgree;
	}
	/**
	 * @param moneyPymntAgree the moneyPymntAgree to set
	 */
	public void setMoneyPymntAgree(String moneyPymntAgree) {
		this.moneyPymntAgree = moneyPymntAgree;
	}
	/**
	 * @return the workClass
	 */
	public String getWorkClass() {
		return workClass;
	}
	/**
	 * @param workClass the workClass to set
	 */
	public void setWorkClass(String workClass) {
		this.workClass = workClass;
	}
	/**
	 * @return the subctrtRate
	 */
	public String getSubctrtRate() {
		return subctrtRate;
	}
	/**
	 * @param subctrtRate the subctrtRate to set
	 */
	public void setSubctrtRate(String subctrtRate) {
		this.subctrtRate = subctrtRate;
	}
	/**
	 * @return the orginlContrAmt
	 */
	public String getOrginlContrAmt() {
		return orginlContrAmt;
	}
	/**
	 * @param orginlContrAmt the orginlContrAmt to set
	 */
	public void setOrginlContrAmt(String orginlContrAmt) {
		this.orginlContrAmt = orginlContrAmt;
	}
	/**
	 * @return the subctrtCtrtAmt
	 */
	public String getSubctrtCtrtAmt() {
		return subctrtCtrtAmt;
	}
	/**
	 * @param subctrtCtrtAmt the subctrtCtrtAmt to set
	 */
	public void setSubctrtCtrtAmt(String subctrtCtrtAmt) {
		this.subctrtCtrtAmt = subctrtCtrtAmt;
	}
	/**
	 * @return the subctrtCtrtDtFrom
	 */
	public String getSubctrtCtrtDtFrom() {
		return subctrtCtrtDtFrom;
	}
	/**
	 * @param subctrtCtrtDtFrom the subctrtCtrtDtFrom to set
	 */
	public void setSubctrtCtrtDtFrom(String subctrtCtrtDtFrom) {
		this.subctrtCtrtDtFrom = subctrtCtrtDtFrom;
	}
	/**
	 * @return the subctrtCtrtDtTo
	 */
	public String getSubctrtCtrtDtTo() {
		return subctrtCtrtDtTo;
	}
	/**
	 * @param subctrtCtrtDtTo the subctrtCtrtDtTo to set
	 */
	public void setSubctrtCtrtDtTo(String subctrtCtrtDtTo) {
		this.subctrtCtrtDtTo = subctrtCtrtDtTo;
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
	/**
	 * @return the moneyPymntAgreeNm
	 */
	public String getMoneyPymntAgreeNm() {
		return moneyPymntAgreeNm;
	}
	/**
	 * @param moneyPymntAgreeNm the moneyPymntAgreeNm to set
	 */
	public void setMoneyPymntAgreeNm(String moneyPymntAgreeNm) {
		this.moneyPymntAgreeNm = moneyPymntAgreeNm;
	}
	/**
	 * @return the subCtrtSe
	 */
	public String getSubCtrtSe() {
		return subCtrtSe;
	}
	/**
	 * @param subCtrtSe the subCtrtSe to set
	 */
	public void setSubCtrtSe(String subCtrtSe) {
		this.subCtrtSe = subCtrtSe;
	}
	/**
	 * @return the subCtrtNm
	 */
	public String getSubCtrtNm() {
		return subCtrtNm;
	}
	/**
	 * @param subCtrtNm the subCtrtNm to set
	 */
	public void setSubCtrtNm(String subCtrtNm) {
		this.subCtrtNm = subCtrtNm;
	}
	/**
	 * @return the subCtrtDt
	 */
	public String getSubCtrtDt() {
		return subCtrtDt;
	}
	/**
	 * @param subCtrtDt the subCtrtDt to set
	 */
	public void setSubCtrtDt(String subCtrtDt) {
		this.subCtrtDt = subCtrtDt;
	}
	/**
	 * @return the subCtrtAmt
	 */
	public String getSubCtrtAmt() {
		return subCtrtAmt;
	}
	/**
	 * @param subCtrtAmt the subCtrtAmt to set
	 */
	public void setSubCtrtAmt(String subCtrtAmt) {
		this.subCtrtAmt = subCtrtAmt;
	}
	/**
	 * @return the subCtrtDtFrom
	 */
	public String getSubCtrtDtFrom() {
		return subCtrtDtFrom;
	}
	/**
	 * @param subCtrtDtFrom the subCtrtDtFrom to set
	 */
	public void setSubCtrtDtFrom(String subCtrtDtFrom) {
		this.subCtrtDtFrom = subCtrtDtFrom;
	}
	/**
	 * @return the subCtrtDtTo
	 */
	public String getSubCtrtDtTo() {
		return subCtrtDtTo;
	}
	/**
	 * @param subCtrtDtTo the subCtrtDtTo to set
	 */
	public void setSubCtrtDtTo(String subCtrtDtTo) {
		this.subCtrtDtTo = subCtrtDtTo;
	}
	/**
	 * @return the subCtrtMth
	 */
	public String getSubCtrtMth() {
		return subCtrtMth;
	}
	/**
	 * @param subCtrtMth the subCtrtMth to set
	 */
	public void setSubCtrtMth(String subCtrtMth) {
		this.subCtrtMth = subCtrtMth;
	}
	/**
	 * @return the subOrderMthd
	 */
	public String getSubOrderMthd() {
		return subOrderMthd;
	}
	/**
	 * @param subOrderMthd the subOrderMthd to set
	 */
	public void setSubOrderMthd(String subOrderMthd) {
		this.subOrderMthd = subOrderMthd;
	}
	/**
	 * @return the subCauseAct
	 */
	public String getSubCauseAct() {
		return subCauseAct;
	}
	/**
	 * @param subCauseAct the subCauseAct to set
	 */
	public void setSubCauseAct(String subCauseAct) {
		this.subCauseAct = subCauseAct;
	}

}
