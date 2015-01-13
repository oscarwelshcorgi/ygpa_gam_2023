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
public class GamFcltyCtrtMngFulfillCaryFwdVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String caryFwdCtrtNo;		// 계약 번호
	private	String caryFwdSeq;			// 순번
	private	String fulfillCaryFwdYear;	// 이행 이월 년도
	private	String fulfillAmt;			// 이행 금액
	private	String caryFwdAmt;			// 이월 금액
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String caryFwdCtrtSe;		// 계약 구분
	private	String caryFwdCtrtNm;		// 계약 명
	private	String caryFwdCtrtDt;		// 계약 일자
	private	String caryFwdCtrtAmt;		// 계약 금액
	private	String caryFwdCtrtDtFrom;	// 계약 기간 FROM
	private	String caryFwdCtrtDtTo;		// 계약 기간 TO
	private	String caryFwdCtrtMth;		// 계약 방법
	private	String caryFwdOrderMthd;	// 발주 방식
	private	String caryFwdCauseAct;		// 원인 행위

	/**
	 * @return the caryFwdCtrtNo
	 */
	public String getCaryFwdCtrtNo() {
		return caryFwdCtrtNo;
	}
	/**
	 * @param caryFwdCtrtNo the caryFwdCtrtNo to set
	 */
	public void setCaryFwdCtrtNo(String caryFwdCtrtNo) {
		this.caryFwdCtrtNo = caryFwdCtrtNo;
	}
	/**
	 * @return the caryFwdSeq
	 */
	public String getCaryFwdSeq() {
		return caryFwdSeq;
	}
	/**
	 * @param caryFwdSeq the caryFwdSeq to set
	 */
	public void setCaryFwdSeq(String caryFwdSeq) {
		this.caryFwdSeq = caryFwdSeq;
	}
	/**
	 * @return the fulfillCaryFwdYear
	 */
	public String getFulfillCaryFwdYear() {
		return fulfillCaryFwdYear;
	}
	/**
	 * @param fulfillCaryFwdYear the fulfillCaryFwdYear to set
	 */
	public void setFulfillCaryFwdYear(String fulfillCaryFwdYear) {
		this.fulfillCaryFwdYear = fulfillCaryFwdYear;
	}
	/**
	 * @return the fulfillAmt
	 */
	public String getFulfillAmt() {
		return fulfillAmt;
	}
	/**
	 * @param fulfillAmt the fulfillAmt to set
	 */
	public void setFulfillAmt(String fulfillAmt) {
		this.fulfillAmt = fulfillAmt;
	}
	/**
	 * @return the caryFwdAmt
	 */
	public String getCaryFwdAmt() {
		return caryFwdAmt;
	}
	/**
	 * @param caryFwdAmt the caryFwdAmt to set
	 */
	public void setCaryFwdAmt(String caryFwdAmt) {
		this.caryFwdAmt = caryFwdAmt;
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
	 * @return the caryFwdCtrtSe
	 */
	public String getCaryFwdCtrtSe() {
		return caryFwdCtrtSe;
	}
	/**
	 * @param caryFwdCtrtSe the caryFwdCtrtSe to set
	 */
	public void setCaryFwdCtrtSe(String caryFwdCtrtSe) {
		this.caryFwdCtrtSe = caryFwdCtrtSe;
	}
	/**
	 * @return the caryFwdCtrtNm
	 */
	public String getCaryFwdCtrtNm() {
		return caryFwdCtrtNm;
	}
	/**
	 * @param caryFwdCtrtNm the caryFwdCtrtNm to set
	 */
	public void setCaryFwdCtrtNm(String caryFwdCtrtNm) {
		this.caryFwdCtrtNm = caryFwdCtrtNm;
	}
	/**
	 * @return the caryFwdCtrtDt
	 */
	public String getCaryFwdCtrtDt() {
		return caryFwdCtrtDt;
	}
	/**
	 * @param caryFwdCtrtDt the caryFwdCtrtDt to set
	 */
	public void setCaryFwdCtrtDt(String caryFwdCtrtDt) {
		this.caryFwdCtrtDt = caryFwdCtrtDt;
	}
	/**
	 * @return the caryFwdCtrtAmt
	 */
	public String getCaryFwdCtrtAmt() {
		return caryFwdCtrtAmt;
	}
	/**
	 * @param caryFwdCtrtAmt the caryFwdCtrtAmt to set
	 */
	public void setCaryFwdCtrtAmt(String caryFwdCtrtAmt) {
		this.caryFwdCtrtAmt = caryFwdCtrtAmt;
	}
	/**
	 * @return the caryFwdCtrtDtFrom
	 */
	public String getCaryFwdCtrtDtFrom() {
		return caryFwdCtrtDtFrom;
	}
	/**
	 * @param caryFwdCtrtDtFrom the caryFwdCtrtDtFrom to set
	 */
	public void setCaryFwdCtrtDtFrom(String caryFwdCtrtDtFrom) {
		this.caryFwdCtrtDtFrom = caryFwdCtrtDtFrom;
	}
	/**
	 * @return the caryFwdCtrtDtTo
	 */
	public String getCaryFwdCtrtDtTo() {
		return caryFwdCtrtDtTo;
	}
	/**
	 * @param caryFwdCtrtDtTo the caryFwdCtrtDtTo to set
	 */
	public void setCaryFwdCtrtDtTo(String caryFwdCtrtDtTo) {
		this.caryFwdCtrtDtTo = caryFwdCtrtDtTo;
	}
	/**
	 * @return the caryFwdCtrtMth
	 */
	public String getCaryFwdCtrtMth() {
		return caryFwdCtrtMth;
	}
	/**
	 * @param caryFwdCtrtMth the caryFwdCtrtMth to set
	 */
	public void setCaryFwdCtrtMth(String caryFwdCtrtMth) {
		this.caryFwdCtrtMth = caryFwdCtrtMth;
	}
	/**
	 * @return the caryFwdOrderMthd
	 */
	public String getCaryFwdOrderMthd() {
		return caryFwdOrderMthd;
	}
	/**
	 * @param caryFwdOrderMthd the caryFwdOrderMthd to set
	 */
	public void setCaryFwdOrderMthd(String caryFwdOrderMthd) {
		this.caryFwdOrderMthd = caryFwdOrderMthd;
	}
	/**
	 * @return the caryFwdCauseAct
	 */
	public String getCaryFwdCauseAct() {
		return caryFwdCauseAct;
	}
	/**
	 * @param caryFwdCauseAct the caryFwdCauseAct to set
	 */
	public void setCaryFwdCauseAct(String caryFwdCauseAct) {
		this.caryFwdCauseAct = caryFwdCauseAct;
	}

}
