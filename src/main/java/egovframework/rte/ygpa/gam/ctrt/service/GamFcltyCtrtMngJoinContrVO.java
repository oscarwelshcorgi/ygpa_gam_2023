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

public class GamFcltyCtrtMngJoinContrVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String joinCtrtNo;			// 계약 번호
	private	String joinSeq;				// 순번
	private	String qotaRate;			// 지분 율
	private	String joinEntrpsNm;		// 업체 명
	private	String joinRprsntv;			// 대표자
	private	String joinTlphonNo;		// 전화 번호
	private	String joinFaxNo;			// FAX 번호
	private	String postNo;				// 우편 번호
	private	String roadnmAdres;			// 도로명 주소
	private	String lnmAdres;			// 지번 주소
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String dealRelate;			// 거래 관계
	private	String induty;				// 업종
	private	String stplPrdlst;			// 주요 품목
	private	String joinBsnmNo;			// 사업자 번호
	private	String charger;				// 담당자
	private	String chargerOfcPos;		// 담당자 직위
	private	String chargerMoblphonNo;	// 담당자 휴대폰 번호
	private	String chargerEmail;		// 담당자 E-MAIL
	private	String joinCtrtSe;			// 계약 구분
	private	String joinCtrtNm;			// 계약 명
	private	String joinCtrtDt;			// 계약 일자
	private	String joinCtrtAmt;			// 계약 금액
	private	String joinCtrtDtFrom;		// 계약 기간 FROM
	private	String joinCtrtDtTo;		// 계약 기간 TO
	private	String joinCtrtMth;			// 계약 방법
	private	String joinOrderMthd;		// 발주 방식
	private	String joinCauseAct;		// 원인 행위

	/**
	 * @return the joinCtrtNo
	 */
	public String getJoinCtrtNo() {
		return joinCtrtNo;
	}
	/**
	 * @param joinCtrtNo the joinCtrtNo to set
	 */
	public void setJoinCtrtNo(String joinCtrtNo) {
		this.joinCtrtNo = joinCtrtNo;
	}
	/**
	 * @return the joinSeq
	 */
	public String getJoinSeq() {
		return joinSeq;
	}
	/**
	 * @param joinSeq the joinSeq to set
	 */
	public void setJoinSeq(String joinSeq) {
		this.joinSeq = joinSeq;
	}
	/**
	 * @return the qotaRate
	 */
	public String getQotaRate() {
		return qotaRate;
	}
	/**
	 * @param qotaRate the qotaRate to set
	 */
	public void setQotaRate(String qotaRate) {
		this.qotaRate = qotaRate;
	}
	/**
	 * @return the joinEntrpsNm
	 */
	public String getJoinEntrpsNm() {
		return joinEntrpsNm;
	}
	/**
	 * @param joinEntrpsNm the joinEntrpsNm to set
	 */
	public void setJoinEntrpsNm(String joinEntrpsNm) {
		this.joinEntrpsNm = joinEntrpsNm;
	}
	/**
	 * @return the joinRprsntv
	 */
	public String getJoinRprsntv() {
		return joinRprsntv;
	}
	/**
	 * @param joinRprsntv the joinRprsntv to set
	 */
	public void setJoinRprsntv(String joinRprsntv) {
		this.joinRprsntv = joinRprsntv;
	}
	/**
	 * @return the joinTlphonNo
	 */
	public String getJoinTlphonNo() {
		return joinTlphonNo;
	}
	/**
	 * @param joinTlphonNo the joinTlphonNo to set
	 */
	public void setJoinTlphonNo(String joinTlphonNo) {
		this.joinTlphonNo = joinTlphonNo;
	}
	/**
	 * @return the joinFaxNo
	 */
	public String getJoinFaxNo() {
		return joinFaxNo;
	}
	/**
	 * @param joinFaxNo the joinFaxNo to set
	 */
	public void setJoinFaxNo(String joinFaxNo) {
		this.joinFaxNo = joinFaxNo;
	}
	/**
	 * @return the postNo
	 */
	public String getPostNo() {
		return postNo;
	}
	/**
	 * @param postNo the postNo to set
	 */
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	/**
	 * @return the roadnmAdres
	 */
	public String getRoadnmAdres() {
		return roadnmAdres;
	}
	/**
	 * @param roadnmAdres the roadnmAdres to set
	 */
	public void setRoadnmAdres(String roadnmAdres) {
		this.roadnmAdres = roadnmAdres;
	}
	/**
	 * @return the lnmAdres
	 */
	public String getLnmAdres() {
		return lnmAdres;
	}
	/**
	 * @param lnmAdres the lnmAdres to set
	 */
	public void setLnmAdres(String lnmAdres) {
		this.lnmAdres = lnmAdres;
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
	 * @return the dealRelate
	 */
	public String getDealRelate() {
		return dealRelate;
	}
	/**
	 * @param dealRelate the dealRelate to set
	 */
	public void setDealRelate(String dealRelate) {
		this.dealRelate = dealRelate;
	}
	/**
	 * @return the induty
	 */
	public String getInduty() {
		return induty;
	}
	/**
	 * @param induty the induty to set
	 */
	public void setInduty(String induty) {
		this.induty = induty;
	}
	/**
	 * @return the stplPrdlst
	 */
	public String getStplPrdlst() {
		return stplPrdlst;
	}
	/**
	 * @param stplPrdlst the stplPrdlst to set
	 */
	public void setStplPrdlst(String stplPrdlst) {
		this.stplPrdlst = stplPrdlst;
	}
	/**
	 * @return the joinBsnmNo
	 */
	public String getJoinBsnmNo() {
		return joinBsnmNo;
	}
	/**
	 * @param bsnmNo the bsnmNo to set
	 */
	public void setJoinBsnmNo(String joinBsnmNo) {
		this.joinBsnmNo = joinBsnmNo;
	}
	/**
	 * @return the charger
	 */
	public String getCharger() {
		return charger;
	}
	/**
	 * @param charger the charger to set
	 */
	public void setCharger(String charger) {
		this.charger = charger;
	}
	/**
	 * @return the chargerOfcPos
	 */
	public String getChargerOfcPos() {
		return chargerOfcPos;
	}
	/**
	 * @param chargerOfcPos the chargerOfcPos to set
	 */
	public void setChargerOfcPos(String chargerOfcPos) {
		this.chargerOfcPos = chargerOfcPos;
	}
	/**
	 * @return the chargerMoblphonNo
	 */
	public String getChargerMoblphonNo() {
		return chargerMoblphonNo;
	}
	/**
	 * @param chargerMoblphonNo the chargerMoblphonNo to set
	 */
	public void setChargerMoblphonNo(String chargerMoblphonNo) {
		this.chargerMoblphonNo = chargerMoblphonNo;
	}
	/**
	 * @return the chargerEmail
	 */
	public String getChargerEmail() {
		return chargerEmail;
	}
	/**
	 * @param chargerEmail the chargerEmail to set
	 */
	public void setChargerEmail(String chargerEmail) {
		this.chargerEmail = chargerEmail;
	}
	/**
	 * @return the joinCtrtSe
	 */
	public String getJoinCtrtSe() {
		return joinCtrtSe;
	}
	/**
	 * @param joinCtrtSe the joinCtrtSe to set
	 */
	public void setJoinCtrtSe(String joinCtrtSe) {
		this.joinCtrtSe = joinCtrtSe;
	}
	/**
	 * @return the joinCtrtNm
	 */
	public String getJoinCtrtNm() {
		return joinCtrtNm;
	}
	/**
	 * @param joinCtrtNm the joinCtrtNm to set
	 */
	public void setJoinCtrtNm(String joinCtrtNm) {
		this.joinCtrtNm = joinCtrtNm;
	}
	/**
	 * @return the joinCtrtDt
	 */
	public String getJoinCtrtDt() {
		return joinCtrtDt;
	}
	/**
	 * @param joinCtrtDt the joinCtrtDt to set
	 */
	public void setJoinCtrtDt(String joinCtrtDt) {
		this.joinCtrtDt = joinCtrtDt;
	}
	/**
	 * @return the joinCtrtAmt
	 */
	public String getJoinCtrtAmt() {
		return joinCtrtAmt;
	}
	/**
	 * @param joinCtrtAmt the joinCtrtAmt to set
	 */
	public void setJoinCtrtAmt(String joinCtrtAmt) {
		this.joinCtrtAmt = joinCtrtAmt;
	}
	/**
	 * @return the joinCtrtDtFrom
	 */
	public String getJoinCtrtDtFrom() {
		return joinCtrtDtFrom;
	}
	/**
	 * @param joinCtrtDtFrom the joinCtrtDtFrom to set
	 */
	public void setJoinCtrtDtFrom(String joinCtrtDtFrom) {
		this.joinCtrtDtFrom = joinCtrtDtFrom;
	}
	/**
	 * @return the joinCtrtDtTo
	 */
	public String getJoinCtrtDtTo() {
		return joinCtrtDtTo;
	}
	/**
	 * @param joinCtrtDtTo the joinCtrtDtTo to set
	 */
	public void setJoinCtrtDtTo(String joinCtrtDtTo) {
		this.joinCtrtDtTo = joinCtrtDtTo;
	}
	/**
	 * @return the joinCtrtMth
	 */
	public String getJoinCtrtMth() {
		return joinCtrtMth;
	}
	/**
	 * @param joinCtrtMth the joinCtrtMth to set
	 */
	public void setJoinCtrtMth(String joinCtrtMth) {
		this.joinCtrtMth = joinCtrtMth;
	}
	/**
	 * @return the joinOrderMthd
	 */
	public String getJoinOrderMthd() {
		return joinOrderMthd;
	}
	/**
	 * @param joinOrderMthd the joinOrderMthd to set
	 */
	public void setJoinOrderMthd(String joinOrderMthd) {
		this.joinOrderMthd = joinOrderMthd;
	}
	/**
	 * @return the joinCauseAct
	 */
	public String getJoinCauseAct() {
		return joinCauseAct;
	}
	/**
	 * @param joinCauseAct the joinCauseAct to set
	 */
	public void setJoinCauseAct(String joinCauseAct) {
		this.joinCauseAct = joinCauseAct;
	}

}
