/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 13.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 13.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyCtrtMngFlawGrntyVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String flawGrntyCtrtNo;			// 계약 번호
	private	String flawGrntySeq;			// 순번
	private	String flawEndDt;				// 하자 만료 일자
	private	String flawGrntyStartDt;		// 하자 보증 시작 일자
	private	String flawGrntyEndDt;			// 하자 보증 종료 일자
	private	String flawGrntySrbctAmt;		// 하자 보증 가입 금액
	private	String flawGrntyRate;			// 하자 보증 율
	private	String flawGrntyCtrtAmt;		// 하자 보증 계약 금액
	private	String flawGrntyInsuNo;			// 하자 보증 보험 증권 번호
	private	String flawGrntyInsuCmpy;		// 하자 보증 보험 회사
	private	String flawGrntyInsuAddr;		// 하자 보증 보험 회사 주소
	private	String flawGrntyInsuRprsntv;	// 하자 보증 보험 회사 대표자
	private	String flawGrntyRm;				// 비고
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시
	private	String flawCtrtSe;				// 계약 구분
	private	String flawCtrtNm;				// 계약 명
	private	String flawCtrtDt;				// 계약 일자
	private	String flawCtrtAmt;				// 계약 금액
	private	String flawCtrtDtFrom;			// 계약 기간 FROM
	private	String flawCtrtDtTo;			// 계약 기간 TO
	private	String flawCtrtMth;				// 계약 방법
	private	String flawOrderMthd;			// 발주 방식
	private	String flawCauseAct;			// 원인 행위

	/**
	 * @return the flawGrntyCtrtNo
	 */
	public String getFlawGrntyCtrtNo() {
		return flawGrntyCtrtNo;
	}
	/**
	 * @param flawGrntyCtrtNo the flawGrntyCtrtNo to set
	 */
	public void setFlawGrntyCtrtNo(String flawGrntyCtrtNo) {
		this.flawGrntyCtrtNo = flawGrntyCtrtNo;
	}
	/**
	 * @return the flawGrntySeq
	 */
	public String getFlawGrntySeq() {
		return flawGrntySeq;
	}
	/**
	 * @param flawGrntySeq the flawGrntySeq to set
	 */
	public void setFlawGrntySeq(String flawGrntySeq) {
		this.flawGrntySeq = flawGrntySeq;
	}
	/**
	 * @return the flawEndDt
	 */
	public String getFlawEndDt() {
		return flawEndDt;
	}
	/**
	 * @param flawEndDt the flawEndDt to set
	 */
	public void setFlawEndDt(String flawEndDt) {
		this.flawEndDt = flawEndDt;
	}
	/**
	 * @return the flawGrntyStartDt
	 */
	public String getFlawGrntyStartDt() {
		return flawGrntyStartDt;
	}
	/**
	 * @param flawGrntyStartDt the flawGrntyStartDt to set
	 */
	public void setFlawGrntyStartDt(String flawGrntyStartDt) {
		this.flawGrntyStartDt = flawGrntyStartDt;
	}
	/**
	 * @return the flawGrntyEndDt
	 */
	public String getFlawGrntyEndDt() {
		return flawGrntyEndDt;
	}
	/**
	 * @param flawGrntyEndDt the flawGrntyEndDt to set
	 */
	public void setFlawGrntyEndDt(String flawGrntyEndDt) {
		this.flawGrntyEndDt = flawGrntyEndDt;
	}
	/**
	 * @return the flawGrntySrbctAmt
	 */
	public String getFlawGrntySrbctAmt() {
		return flawGrntySrbctAmt;
	}
	/**
	 * @param flawGrntySrbctAmt the flawGrntySrbctAmt to set
	 */
	public void setFlawGrntySrbctAmt(String flawGrntySrbctAmt) {
		this.flawGrntySrbctAmt = flawGrntySrbctAmt;
	}
	/**
	 * @return the flawGrntyRate
	 */
	public String getFlawGrntyRate() {
		return flawGrntyRate;
	}
	/**
	 * @param flawGrntyRate the flawGrntyRate to set
	 */
	public void setFlawGrntyRate(String flawGrntyRate) {
		this.flawGrntyRate = flawGrntyRate;
	}
	/**
	 * @return the flawGrntyCtrtAmt
	 */
	public String getFlawGrntyCtrtAmt() {
		return flawGrntyCtrtAmt;
	}
	/**
	 * @param flawGrntyCtrtAmt the flawGrntyCtrtAmt to set
	 */
	public void setFlawGrntyCtrtAmt(String flawGrntyCtrtAmt) {
		this.flawGrntyCtrtAmt = flawGrntyCtrtAmt;
	}
	/**
	 * @return the flawGrntyInsuNo
	 */
	public String getFlawGrntyInsuNo() {
		return flawGrntyInsuNo;
	}
	/**
	 * @param flawGrntyInsuNo the flawGrntyInsuNo to set
	 */
	public void setFlawGrntyInsuNo(String flawGrntyInsuNo) {
		this.flawGrntyInsuNo = flawGrntyInsuNo;
	}
	/**
	 * @return the flawGrntyInsuCmpy
	 */
	public String getFlawGrntyInsuCmpy() {
		return flawGrntyInsuCmpy;
	}
	/**
	 * @param flawGrntyInsuCmpy the flawGrntyInsuCmpy to set
	 */
	public void setFlawGrntyInsuCmpy(String flawGrntyInsuCmpy) {
		this.flawGrntyInsuCmpy = flawGrntyInsuCmpy;
	}
	/**
	 * @return the flawGrntyInsuAddr
	 */
	public String getFlawGrntyInsuAddr() {
		return flawGrntyInsuAddr;
	}
	/**
	 * @param flawGrntyInsuAddr the flawGrntyInsuAddr to set
	 */
	public void setFlawGrntyInsuAddr(String flawGrntyInsuAddr) {
		this.flawGrntyInsuAddr = flawGrntyInsuAddr;
	}
	/**
	 * @return the flawGrntyInsuRprsntv
	 */
	public String getFlawGrntyInsuRprsntv() {
		return flawGrntyInsuRprsntv;
	}
	/**
	 * @param flawGrntyInsuRprsntv the flawGrntyInsuRprsntv to set
	 */
	public void setFlawGrntyInsuRprsntv(String flawGrntyInsuRprsntv) {
		this.flawGrntyInsuRprsntv = flawGrntyInsuRprsntv;
	}
	/**
	 * @return the flawGrntyRm
	 */
	public String getFlawGrntyRm() {
		return flawGrntyRm;
	}
	/**
	 * @param flawGrntyRm the flawGrntyRm to set
	 */
	public void setFlawGrntyRm(String flawGrntyRm) {
		this.flawGrntyRm = flawGrntyRm;
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
	 * @return the flawCtrtSe
	 */
	public String getFlawCtrtSe() {
		return flawCtrtSe;
	}
	/**
	 * @param flawCtrtSe the flawCtrtSe to set
	 */
	public void setFlawCtrtSe(String flawCtrtSe) {
		this.flawCtrtSe = flawCtrtSe;
	}
	/**
	 * @return the flawCtrtNm
	 */
	public String getFlawCtrtNm() {
		return flawCtrtNm;
	}
	/**
	 * @param flawCtrtNm the flawCtrtNm to set
	 */
	public void setFlawCtrtNm(String flawCtrtNm) {
		this.flawCtrtNm = flawCtrtNm;
	}
	/**
	 * @return the flawCtrtDt
	 */
	public String getFlawCtrtDt() {
		return flawCtrtDt;
	}
	/**
	 * @param flawCtrtDt the flawCtrtDt to set
	 */
	public void setFlawCtrtDt(String flawCtrtDt) {
		this.flawCtrtDt = flawCtrtDt;
	}
	/**
	 * @return the flawCtrtAmt
	 */
	public String getFlawCtrtAmt() {
		return flawCtrtAmt;
	}
	/**
	 * @param flawCtrtAmt the flawCtrtAmt to set
	 */
	public void setFlawCtrtAmt(String flawCtrtAmt) {
		this.flawCtrtAmt = flawCtrtAmt;
	}
	/**
	 * @return the flawCtrtDtFrom
	 */
	public String getFlawCtrtDtFrom() {
		return flawCtrtDtFrom;
	}
	/**
	 * @param flawCtrtDtFrom the flawCtrtDtFrom to set
	 */
	public void setFlawCtrtDtFrom(String flawCtrtDtFrom) {
		this.flawCtrtDtFrom = flawCtrtDtFrom;
	}
	/**
	 * @return the flawCtrtDtTo
	 */
	public String getFlawCtrtDtTo() {
		return flawCtrtDtTo;
	}
	/**
	 * @param flawCtrtDtTo the flawCtrtDtTo to set
	 */
	public void setFlawCtrtDtTo(String flawCtrtDtTo) {
		this.flawCtrtDtTo = flawCtrtDtTo;
	}
	/**
	 * @return the flawCtrtMth
	 */
	public String getFlawCtrtMth() {
		return flawCtrtMth;
	}
	/**
	 * @param flawCtrtMth the flawCtrtMth to set
	 */
	public void setFlawCtrtMth(String flawCtrtMth) {
		this.flawCtrtMth = flawCtrtMth;
	}
	/**
	 * @return the flawOrderMthd
	 */
	public String getFlawOrderMthd() {
		return flawOrderMthd;
	}
	/**
	 * @param flawOrderMthd the flawOrderMthd to set
	 */
	public void setFlawOrderMthd(String flawOrderMthd) {
		this.flawOrderMthd = flawOrderMthd;
	}
	/**
	 * @return the flawCauseAct
	 */
	public String getFlawCauseAct() {
		return flawCauseAct;
	}
	/**
	 * @param flawCauseAct the flawCauseAct to set
	 */
	public void setFlawCauseAct(String flawCauseAct) {
		this.flawCauseAct = flawCauseAct;
	}

}
