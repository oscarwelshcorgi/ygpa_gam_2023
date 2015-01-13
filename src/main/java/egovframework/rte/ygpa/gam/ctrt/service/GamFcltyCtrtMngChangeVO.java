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
public class GamFcltyCtrtMngChangeVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String changeInfoCtrtNo;		// 계약 번호
	private	String changeInfoSeq;			// 순번
	private	String changeSe;				// 변경 구분
	private	String changeRsn;				// 변경 사유
	private	String changeDt;				// 변경 일자
	private	String changeCtrtDtFrom;		// 변경 계약 기간 FROM
	private	String changeCtrtDtTo;			// 변경 계약 기간 TO
	private	String changeCtrtAmt;			// 변경 계약 금액
	private	String lastCtrtAmt;				// 최종 계약 금액
	private	String changeRm;				// 비고
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시
	private	String changeSeNm;				// 변경 구분 명
	private	String changeInfoCtrtSe;		// 계약 구분
	private	String changeInfoCtrtNm;		// 계약 명
	private	String changeInfoCtrtDt;		// 계약 일자
	private	String changeInfoCtrtAmt;		// 계약 금액
	private	String changeInfoCtrtDtFrom;	// 계약 기간 FROM
	private	String changeInfoCtrtDtTo;		// 계약 기간 TO
	private	String changeInfoCtrtMth;		// 계약 방법
	private	String changeInfoOrderMthd;		// 발주 방식
	private	String changeInfoCauseAct;		// 원인 행위

	/**
	 * @return the changeInfoCtrtNo
	 */
	public String getChangeInfoCtrtNo() {
		return changeInfoCtrtNo;
	}
	/**
	 * @param changeInfoCtrtNo the changeInfoCtrtNo to set
	 */
	public void setChangeInfoCtrtNo(String changeInfoCtrtNo) {
		this.changeInfoCtrtNo = changeInfoCtrtNo;
	}
	/**
	 * @return the changeInfoSeq
	 */
	public String getChangeInfoSeq() {
		return changeInfoSeq;
	}
	/**
	 * @param changeInfoSeq the changeInfoSeq to set
	 */
	public void setChangeInfoSeq(String changeInfoSeq) {
		this.changeInfoSeq = changeInfoSeq;
	}
	/**
	 * @return the changeSe
	 */
	public String getChangeSe() {
		return changeSe;
	}
	/**
	 * @param changeSe the changeSe to set
	 */
	public void setChangeSe(String changeSe) {
		this.changeSe = changeSe;
	}
	/**
	 * @return the changeRsn
	 */
	public String getChangeRsn() {
		return changeRsn;
	}
	/**
	 * @param changeRsn the changeRsn to set
	 */
	public void setChangeRsn(String changeRsn) {
		this.changeRsn = changeRsn;
	}
	/**
	 * @return the changeDt
	 */
	public String getChangeDt() {
		return changeDt;
	}
	/**
	 * @param changeDt the changeDt to set
	 */
	public void setChangeDt(String changeDt) {
		this.changeDt = changeDt;
	}
	/**
	 * @return the changeCtrtDtFrom
	 */
	public String getChangeCtrtDtFrom() {
		return changeCtrtDtFrom;
	}
	/**
	 * @param changeCtrtDtFrom the changeCtrtDtFrom to set
	 */
	public void setChangeCtrtDtFrom(String changeCtrtDtFrom) {
		this.changeCtrtDtFrom = changeCtrtDtFrom;
	}
	/**
	 * @return the changeCtrtDtTo
	 */
	public String getChangeCtrtDtTo() {
		return changeCtrtDtTo;
	}
	/**
	 * @param changeCtrtDtTo the changeCtrtDtTo to set
	 */
	public void setChangeCtrtDtTo(String changeCtrtDtTo) {
		this.changeCtrtDtTo = changeCtrtDtTo;
	}
	/**
	 * @return the changeCtrtAmt
	 */
	public String getChangeCtrtAmt() {
		return changeCtrtAmt;
	}
	/**
	 * @param changeCtrtAmt the changeCtrtAmt to set
	 */
	public void setChangeCtrtAmt(String changeCtrtAmt) {
		this.changeCtrtAmt = changeCtrtAmt;
	}
	/**
	 * @return the lastCtrtAmt
	 */
	public String getLastCtrtAmt() {
		return lastCtrtAmt;
	}
	/**
	 * @param lastCtrtAmt the lastCtrtAmt to set
	 */
	public void setLastCtrtAmt(String lastCtrtAmt) {
		this.lastCtrtAmt = lastCtrtAmt;
	}
	/**
	 * @return the changeRm
	 */
	public String getChangeRm() {
		return changeRm;
	}
	/**
	 * @param changeRm the changeRm to set
	 */
	public void setChangeRm(String changeRm) {
		this.changeRm = changeRm;
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
	 * @return the changeSeNm
	 */
	public String getChangeSeNm() {
		return changeSeNm;
	}
	/**
	 * @param changeSeNm the changeSeNm to set
	 */
	public void setChangeSeNm(String changeSeNm) {
		this.changeSeNm = changeSeNm;
	}
	/**
	 * @return the changeInfoCtrtSe
	 */
	public String getChangeInfoCtrtSe() {
		return changeInfoCtrtSe;
	}
	/**
	 * @param changeInfoCtrtSe the changeInfoCtrtSe to set
	 */
	public void setChangeInfoCtrtSe(String changeInfoCtrtSe) {
		this.changeInfoCtrtSe = changeInfoCtrtSe;
	}
	/**
	 * @return the changeInfoCtrtNm
	 */
	public String getChangeInfoCtrtNm() {
		return changeInfoCtrtNm;
	}
	/**
	 * @param changeInfoCtrtNm the changeInfoCtrtNm to set
	 */
	public void setChangeInfoCtrtNm(String changeInfoCtrtNm) {
		this.changeInfoCtrtNm = changeInfoCtrtNm;
	}
	/**
	 * @return the changeInfoCtrtDt
	 */
	public String getChangeInfoCtrtDt() {
		return changeInfoCtrtDt;
	}
	/**
	 * @param changeInfoCtrtDt the changeInfoCtrtDt to set
	 */
	public void setChangeInfoCtrtDt(String changeInfoCtrtDt) {
		this.changeInfoCtrtDt = changeInfoCtrtDt;
	}
	/**
	 * @return the changeInfoCtrtAmt
	 */
	public String getChangeInfoCtrtAmt() {
		return changeInfoCtrtAmt;
	}
	/**
	 * @param changeInfoCtrtAmt the changeInfoCtrtAmt to set
	 */
	public void setChangeInfoCtrtAmt(String changeInfoCtrtAmt) {
		this.changeInfoCtrtAmt = changeInfoCtrtAmt;
	}
	/**
	 * @return the changeInfoCtrtDtFrom
	 */
	public String getChangeInfoCtrtDtFrom() {
		return changeInfoCtrtDtFrom;
	}
	/**
	 * @param changeInfoCtrtDtFrom the changeInfoCtrtDtFrom to set
	 */
	public void setChangeInfoCtrtDtFrom(String changeInfoCtrtDtFrom) {
		this.changeInfoCtrtDtFrom = changeInfoCtrtDtFrom;
	}
	/**
	 * @return the changeInfoCtrtDtTo
	 */
	public String getChangeInfoCtrtDtTo() {
		return changeInfoCtrtDtTo;
	}
	/**
	 * @param changeInfoCtrtDtTo the changeInfoCtrtDtTo to set
	 */
	public void setChangeInfoCtrtDtTo(String changeInfoCtrtDtTo) {
		this.changeInfoCtrtDtTo = changeInfoCtrtDtTo;
	}
	/**
	 * @return the changeInfoCtrtMth
	 */
	public String getChangeInfoCtrtMth() {
		return changeInfoCtrtMth;
	}
	/**
	 * @param changeInfoCtrtMth the changeInfoCtrtMth to set
	 */
	public void setChangeInfoCtrtMth(String changeInfoCtrtMth) {
		this.changeInfoCtrtMth = changeInfoCtrtMth;
	}
	/**
	 * @return the changeInfoOrderMthd
	 */
	public String getChangeInfoOrderMthd() {
		return changeInfoOrderMthd;
	}
	/**
	 * @param changeInfoOrderMthd the changeInfoOrderMthd to set
	 */
	public void setChangeInfoOrderMthd(String changeInfoOrderMthd) {
		this.changeInfoOrderMthd = changeInfoOrderMthd;
	}
	/**
	 * @return the changeInfoCauseAct
	 */
	public String getChangeInfoCauseAct() {
		return changeInfoCauseAct;
	}
	/**
	 * @param changeInfoCauseAct the changeInfoCauseAct to set
	 */
	public void setChangeInfoCauseAct(String changeInfoCauseAct) {
		this.changeInfoCauseAct = changeInfoCauseAct;
	}

}
