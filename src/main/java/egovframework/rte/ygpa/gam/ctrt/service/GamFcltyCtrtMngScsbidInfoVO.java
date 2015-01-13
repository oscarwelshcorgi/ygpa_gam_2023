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
public class GamFcltyCtrtMngScsbidInfoVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String scsbidCtrtNo;		// 계약 번호
	private	String scsbidSeq;			// 순번
	private	String scsbidRank;			// 낙찰 순위
	private	String scsbidEntrpsNm;		// 업체 명
	private	String scsbidBsnmNo;		// 사업자 번호
	private	String scsbidRprsntv;		// 대표자
	private	String scsbidTlphonNo;		// 전화 번호
	private	String scsbidFaxNo;			// FAX 번호
	private	String scsbidRm;			// 비고
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String scsbidCtrtSe;		// 계약 구분
	private	String scsbidCtrtNm;		// 계약 명
	private	String scsbidCtrtDt;		// 계약 일자
	private	String scsbidCtrtAmt;		// 계약 금액
	private	String scsbidCtrtDtFrom;	// 계약 기간 FROM
	private	String scsbidCtrtDtTo;		// 계약 기간 TO
	private	String scsbidCtrtMth;		// 계약 방법
	private	String scsbidOrderMthd;		// 발주 방식
	private	String scsbidCauseAct;		// 원인 행위

	/**
	 * @return the scsbidCtrtNo
	 */
	public String getScsbidCtrtNo() {
		return scsbidCtrtNo;
	}
	/**
	 * @param scsbidCtrtNo the scsbidCtrtNo to set
	 */
	public void setScsbidCtrtNo(String scsbidCtrtNo) {
		this.scsbidCtrtNo = scsbidCtrtNo;
	}
	/**
	 * @return the scsbidSeq
	 */
	public String getScsbidSeq() {
		return scsbidSeq;
	}
	/**
	 * @param scsbidSeq the scsbidSeq to set
	 */
	public void setScsbidSeq(String scsbidSeq) {
		this.scsbidSeq = scsbidSeq;
	}
	/**
	 * @return the scsbidRank
	 */
	public String getScsbidRank() {
		return scsbidRank;
	}
	/**
	 * @param scsbidRank the scsbidRank to set
	 */
	public void setScsbidRank(String scsbidRank) {
		this.scsbidRank = scsbidRank;
	}
	/**
	 * @return the scsbidEntrpsNm
	 */
	public String getScsbidEntrpsNm() {
		return scsbidEntrpsNm;
	}
	/**
	 * @param scsbidEntrpsNm the scsbidEntrpsNm to set
	 */
	public void setScsbidEntrpsNm(String scsbidEntrpsNm) {
		this.scsbidEntrpsNm = scsbidEntrpsNm;
	}
	/**
	 * @return the scsbidBsnmNo
	 */
	public String getScsbidBsnmNo() {
		return scsbidBsnmNo;
	}
	/**
	 * @param scsbidBsnmNo the scsbidBsnmNo to set
	 */
	public void setScsbidBsnmNo(String scsbidBsnmNo) {
		this.scsbidBsnmNo = scsbidBsnmNo;
	}
	/**
	 * @return the scsbidRprsntv
	 */
	public String getScsbidRprsntv() {
		return scsbidRprsntv;
	}
	/**
	 * @param scsbidRprsntv the scsbidRprsntv to set
	 */
	public void setScsbidRprsntv(String scsbidRprsntv) {
		this.scsbidRprsntv = scsbidRprsntv;
	}
	/**
	 * @return the scsbidTlphonNo
	 */
	public String getScsbidTlphonNo() {
		return scsbidTlphonNo;
	}
	/**
	 * @param scsbidTlphonNo the scsbidTlphonNo to set
	 */
	public void setScsbidTlphonNo(String scsbidTlphonNo) {
		this.scsbidTlphonNo = scsbidTlphonNo;
	}
	/**
	 * @return the scsbidFaxNo
	 */
	public String getScsbidFaxNo() {
		return scsbidFaxNo;
	}
	/**
	 * @param scsbidFaxNo the scsbidFaxNo to set
	 */
	public void setScsbidFaxNo(String scsbidFaxNo) {
		this.scsbidFaxNo = scsbidFaxNo;
	}
	/**
	 * @return the scsbidRm
	 */
	public String getScsbidRm() {
		return scsbidRm;
	}
	/**
	 * @param scsbidRm the scsbidRm to set
	 */
	public void setScsbidRm(String scsbidRm) {
		this.scsbidRm = scsbidRm;
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
	 * @return the scsbidCtrtSe
	 */
	public String getScsbidCtrtSe() {
		return scsbidCtrtSe;
	}
	/**
	 * @param scsbidCtrtSe the scsbidCtrtSe to set
	 */
	public void setScsbidCtrtSe(String scsbidCtrtSe) {
		this.scsbidCtrtSe = scsbidCtrtSe;
	}
	/**
	 * @return the scsbidCtrtNm
	 */
	public String getScsbidCtrtNm() {
		return scsbidCtrtNm;
	}
	/**
	 * @param scsbidCtrtNm the scsbidCtrtNm to set
	 */
	public void setScsbidCtrtNm(String scsbidCtrtNm) {
		this.scsbidCtrtNm = scsbidCtrtNm;
	}
	/**
	 * @return the scsbidCtrtDt
	 */
	public String getScsbidCtrtDt() {
		return scsbidCtrtDt;
	}
	/**
	 * @param scsbidCtrtDt the scsbidCtrtDt to set
	 */
	public void setScsbidCtrtDt(String scsbidCtrtDt) {
		this.scsbidCtrtDt = scsbidCtrtDt;
	}
	/**
	 * @return the scsbidCtrtAmt
	 */
	public String getScsbidCtrtAmt() {
		return scsbidCtrtAmt;
	}
	/**
	 * @param scsbidCtrtAmt the scsbidCtrtAmt to set
	 */
	public void setScsbidCtrtAmt(String scsbidCtrtAmt) {
		this.scsbidCtrtAmt = scsbidCtrtAmt;
	}
	/**
	 * @return the scsbidCtrtDtFrom
	 */
	public String getScsbidCtrtDtFrom() {
		return scsbidCtrtDtFrom;
	}
	/**
	 * @param scsbidCtrtDtFrom the scsbidCtrtDtFrom to set
	 */
	public void setScsbidCtrtDtFrom(String scsbidCtrtDtFrom) {
		this.scsbidCtrtDtFrom = scsbidCtrtDtFrom;
	}
	/**
	 * @return the scsbidCtrtDtTo
	 */
	public String getScsbidCtrtDtTo() {
		return scsbidCtrtDtTo;
	}
	/**
	 * @param scsbidCtrtDtTo the scsbidCtrtDtTo to set
	 */
	public void setScsbidCtrtDtTo(String scsbidCtrtDtTo) {
		this.scsbidCtrtDtTo = scsbidCtrtDtTo;
	}
	/**
	 * @return the scsbidCtrtMth
	 */
	public String getScsbidCtrtMth() {
		return scsbidCtrtMth;
	}
	/**
	 * @param scsbidCtrtMth the scsbidCtrtMth to set
	 */
	public void setScsbidCtrtMth(String scsbidCtrtMth) {
		this.scsbidCtrtMth = scsbidCtrtMth;
	}
	/**
	 * @return the scsbidOrderMthd
	 */
	public String getScsbidOrderMthd() {
		return scsbidOrderMthd;
	}
	/**
	 * @param scsbidOrderMthd the scsbidOrderMthd to set
	 */
	public void setScsbidOrderMthd(String scsbidOrderMthd) {
		this.scsbidOrderMthd = scsbidOrderMthd;
	}
	/**
	 * @return the scsbidCauseAct
	 */
	public String getScsbidCauseAct() {
		return scsbidCauseAct;
	}
	/**
	 * @param scsbidCauseAct the scsbidCauseAct to set
	 */
	public void setScsbidCauseAct(String scsbidCauseAct) {
		this.scsbidCauseAct = scsbidCauseAct;
	}

}
