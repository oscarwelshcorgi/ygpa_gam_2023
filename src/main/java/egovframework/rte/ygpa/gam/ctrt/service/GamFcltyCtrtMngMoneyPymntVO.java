/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service;

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

public class GamFcltyCtrtMngMoneyPymntVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String pymntCtrtNo;			// 계약 번호
	private	String pymntSeq;			// 순번
	private	String pymntCl;				// 지급 분류
	private	String pymntDt;				// 지급 일자
	private	String thisTimeEstbAmt;		// 금회 기성 금액
	private	String depositExcclcAmt;	// 선금 정산 금액
	private	String pymntAmt;			// 지급 금액
	private	String pymntAggrAmt;		// 지급 누계 금액
	private	String pymntRm;				// 비고
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String pymntClNm;			// 지급 분류 명
	private	String pymntCtrtSe;			// 계약 구분
	private	String pymntCtrtNm;			// 계약 명
	private	String pymntCtrtDt;			// 계약 일자
	private	String pymntCtrtAmt;		// 계약 금액
	private	String pymntCtrtDtFrom;		// 계약 기간 FROM
	private	String pymntCtrtDtTo;		// 계약 기간 TO
	private	String pymntCtrtMth;		// 계약 방법
	private	String pymntOrderMthd;		// 발주 방식
	private	String pymntCauseAct;		// 원인 행위

	/**
	 * @return the pymntCtrtNo
	 */
	public String getPymntCtrtNo() {
		return pymntCtrtNo;
	}
	/**
	 * @param pymntCtrtNo the pymntCtrtNo to set
	 */
	public void setPymntCtrtNo(String pymntCtrtNo) {
		this.pymntCtrtNo = pymntCtrtNo;
	}
	/**
	 * @return the pymntSeq
	 */
	public String getPymntSeq() {
		return pymntSeq;
	}
	/**
	 * @param pymntSeq the pymntSeq to set
	 */
	public void setPymntSeq(String pymntSeq) {
		this.pymntSeq = pymntSeq;
	}
	/**
	 * @return the pymntCl
	 */
	public String getPymntCl() {
		return pymntCl;
	}
	/**
	 * @param pymntCl the pymntCl to set
	 */
	public void setPymntCl(String pymntCl) {
		this.pymntCl = pymntCl;
	}
	/**
	 * @return the pymntDt
	 */
	public String getPymntDt() {
		return pymntDt;
	}
	/**
	 * @param pymntDt the pymntDt to set
	 */
	public void setPymntDt(String pymntDt) {
		this.pymntDt = pymntDt;
	}
	/**
	 * @return the thisTimeEstbAmt
	 */
	public String getThisTimeEstbAmt() {
		return thisTimeEstbAmt;
	}
	/**
	 * @param thisTimeEstbAmt the thisTimeEstbAmt to set
	 */
	public void setThisTimeEstbAmt(String thisTimeEstbAmt) {
		this.thisTimeEstbAmt = thisTimeEstbAmt;
	}
	/**
	 * @return the depositExcclcAmt
	 */
	public String getDepositExcclcAmt() {
		return depositExcclcAmt;
	}
	/**
	 * @param depositExcclcAmt the depositExcclcAmt to set
	 */
	public void setDepositExcclcAmt(String depositExcclcAmt) {
		this.depositExcclcAmt = depositExcclcAmt;
	}
	/**
	 * @return the pymntAmt
	 */
	public String getPymntAmt() {
		return pymntAmt;
	}
	/**
	 * @param pymntAmt the pymntAmt to set
	 */
	public void setPymntAmt(String pymntAmt) {
		this.pymntAmt = pymntAmt;
	}
	/**
	 * @return the pymntAggrAmt
	 */
	public String getPymntAggrAmt() {
		return pymntAggrAmt;
	}
	/**
	 * @param pymntAggrAmt the pymntAggrAmt to set
	 */
	public void setPymntAggrAmt(String pymntAggrAmt) {
		this.pymntAggrAmt = pymntAggrAmt;
	}
	/**
	 * @return the pymntRm
	 */
	public String getPymntRm() {
		return pymntRm;
	}
	/**
	 * @param pymntRm the pymntRm to set
	 */
	public void setPymntRm(String pymntRm) {
		this.pymntRm = pymntRm;
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
	 * @return the pymntClNm
	 */
	public String getPymntClNm() {
		return pymntClNm;
	}
	/**
	 * @param pymntClNm the pymntClNm to set
	 */
	public void setPymntClNm(String pymntClNm) {
		this.pymntClNm = pymntClNm;
	}
	/**
	 * @return the pymntCtrtSe
	 */
	public String getPymntCtrtSe() {
		return pymntCtrtSe;
	}
	/**
	 * @param pymntCtrtSe the pymntCtrtSe to set
	 */
	public void setPymntCtrtSe(String pymntCtrtSe) {
		this.pymntCtrtSe = pymntCtrtSe;
	}
	/**
	 * @return the pymntCtrtNm
	 */
	public String getPymntCtrtNm() {
		return pymntCtrtNm;
	}
	/**
	 * @param pymntCtrtNm the pymntCtrtNm to set
	 */
	public void setPymntCtrtNm(String pymntCtrtNm) {
		this.pymntCtrtNm = pymntCtrtNm;
	}
	/**
	 * @return the pymntCtrtDt
	 */
	public String getPymntCtrtDt() {
		return pymntCtrtDt;
	}
	/**
	 * @param pymntCtrtDt the pymntCtrtDt to set
	 */
	public void setPymntCtrtDt(String pymntCtrtDt) {
		this.pymntCtrtDt = pymntCtrtDt;
	}
	/**
	 * @return the pymntCtrtAmt
	 */
	public String getPymntCtrtAmt() {
		return pymntCtrtAmt;
	}
	/**
	 * @param pymntCtrtAmt the pymntCtrtAmt to set
	 */
	public void setPymntCtrtAmt(String pymntCtrtAmt) {
		this.pymntCtrtAmt = pymntCtrtAmt;
	}
	/**
	 * @return the pymntCtrtDtFrom
	 */
	public String getPymntCtrtDtFrom() {
		return pymntCtrtDtFrom;
	}
	/**
	 * @param pymntCtrtDtFrom the pymntCtrtDtFrom to set
	 */
	public void setPymntCtrtDtFrom(String pymntCtrtDtFrom) {
		this.pymntCtrtDtFrom = pymntCtrtDtFrom;
	}
	/**
	 * @return the pymntCtrtDtTo
	 */
	public String getPymntCtrtDtTo() {
		return pymntCtrtDtTo;
	}
	/**
	 * @param pymntCtrtDtTo the pymntCtrtDtTo to set
	 */
	public void setPymntCtrtDtTo(String pymntCtrtDtTo) {
		this.pymntCtrtDtTo = pymntCtrtDtTo;
	}
	/**
	 * @return the pymntCtrtMth
	 */
	public String getPymntCtrtMth() {
		return pymntCtrtMth;
	}
	/**
	 * @param pymntCtrtMth the pymntCtrtMth to set
	 */
	public void setPymntCtrtMth(String pymntCtrtMth) {
		this.pymntCtrtMth = pymntCtrtMth;
	}
	/**
	 * @return the pymntOrderMthd
	 */
	public String getPymntOrderMthd() {
		return pymntOrderMthd;
	}
	/**
	 * @param pymntOrderMthd the pymntOrderMthd to set
	 */
	public void setPymntOrderMthd(String pymntOrderMthd) {
		this.pymntOrderMthd = pymntOrderMthd;
	}
	/**
	 * @return the pymntCauseAct
	 */
	public String getPymntCauseAct() {
		return pymntCauseAct;
	}
	/**
	 * @param pymntCauseAct the pymntCauseAct to set
	 */
	public void setPymntCauseAct(String pymntCauseAct) {
		this.pymntCauseAct = pymntCauseAct;
	}

}
