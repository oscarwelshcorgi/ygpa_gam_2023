/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 10.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltsMngRegistMngMntnRprDtlsVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String rnum;				// 번호
	private	String mntnRprCnstNm;		// 유지 보수 공사 명
	private	String plannerNm;			// 설계자
	private	String cnstChargNm;			// 공사 감독자
	private	String mntnRprCnstStartDt;	// 유지 보수 공사 시작 일자
	private	String mntnRprCnstEndDt;	// 유지 보수 공사 종료 일자
	private	String mntnRprCnstAmt;		// 유지 보수 공사 금액
	private	String rm;					// 비고
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String ctrtNo;				// 계약 번호
	private	String fcltsJobSe;			// 시설물 업무 구분
	private	String fcltsMngGroupNo;		// 시설물 관리 그룹 번호
	private	String mntnRprSeq;			// 유지 보수 순번
	private	String mntnRprSe;			// 유지 보수 구분
	private	String mntnRprPart;			// 유지 보수 부위
	private	String mntnRprCn;			// 유지 보수 내용
	private	String mntnRprBdgt;			// 유지 보수 예산
	private	String cnstrtr;				// 시공자
	private	String responEngineer;		// 책임 기술자
	private	String enforceYear;			// 시행 년도
	private	String mntnSubRprSe;		// SUB 유지 보수 구분
	private	String wrtDt;				// 작성 일자
	private	String wrtUsr;				// 작성자
	private	String mntnRprSeNm;			// 유지 보수 구분 명
	private	String mntnRprCnstDtFromTo;	// 유지 보수 공사 기간

	/**
	 * @return the rnum
	 */
	public String getRnum() {
		return rnum;
	}
	/**
	 * @param rnum the rnum to set
	 */
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	/**
	 * @return the mntnRprCnstNm
	 */
	public String getMntnRprCnstNm() {
		return mntnRprCnstNm;
	}
	/**
	 * @param mntnRprCnstNm the mntnRprCnstNm to set
	 */
	public void setMntnRprCnstNm(String mntnRprCnstNm) {
		this.mntnRprCnstNm = mntnRprCnstNm;
	}
	/**
	 * @return the plannerNm
	 */
	public String getPlannerNm() {
		return plannerNm;
	}
	/**
	 * @param plannerNm the plannerNm to set
	 */
	public void setPlannerNm(String plannerNm) {
		this.plannerNm = plannerNm;
	}
	/**
	 * @return the cnstChargNm
	 */
	public String getCnstChargNm() {
		return cnstChargNm;
	}
	/**
	 * @param cnstChargNm the cnstChargNm to set
	 */
	public void setCnstChargNm(String cnstChargNm) {
		this.cnstChargNm = cnstChargNm;
	}
	/**
	 * @return the mntnRprCnstStartDt
	 */
	public String getMntnRprCnstStartDt() {
		return mntnRprCnstStartDt;
	}
	/**
	 * @param mntnRprCnstStartDt the mntnRprCnstStartDt to set
	 */
	public void setMntnRprCnstStartDt(String mntnRprCnstStartDt) {
		this.mntnRprCnstStartDt = mntnRprCnstStartDt;
	}
	/**
	 * @return the mntnRprCnstEndDt
	 */
	public String getMntnRprCnstEndDt() {
		return mntnRprCnstEndDt;
	}
	/**
	 * @param mntnRprCnstEndDt the mntnRprCnstEndDt to set
	 */
	public void setMntnRprCnstEndDt(String mntnRprCnstEndDt) {
		this.mntnRprCnstEndDt = mntnRprCnstEndDt;
	}
	/**
	 * @return the mntnRprCnstAmt
	 */
	public String getMntnRprCnstAmt() {
		return mntnRprCnstAmt;
	}
	/**
	 * @param mntnRprCnstAmt the mntnRprCnstAmt to set
	 */
	public void setMntnRprCnstAmt(String mntnRprCnstAmt) {
		this.mntnRprCnstAmt = mntnRprCnstAmt;
	}
	/**
	 * @return the rm
	 */
	public String getRm() {
		return rm;
	}
	/**
	 * @param rm the rm to set
	 */
	public void setRm(String rm) {
		this.rm = rm;
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
	 * @return the ctrtNo
	 */
	public String getCtrtNo() {
		return ctrtNo;
	}
	/**
	 * @param ctrtNo the ctrtNo to set
	 */
	public void setCtrtNo(String ctrtNo) {
		this.ctrtNo = ctrtNo;
	}
	/**
	 * @return the fcltsJobSe
	 */
	public String getFcltsJobSe() {
		return fcltsJobSe;
	}
	/**
	 * @param fcltsJobSe the fcltsJobSe to set
	 */
	public void setFcltsJobSe(String fcltsJobSe) {
		this.fcltsJobSe = fcltsJobSe;
	}
	/**
	 * @return the fcltsMngGroupNo
	 */
	public String getFcltsMngGroupNo() {
		return fcltsMngGroupNo;
	}
	/**
	 * @param fcltsMngGroupNo the fcltsMngGroupNo to set
	 */
	public void setFcltsMngGroupNo(String fcltsMngGroupNo) {
		this.fcltsMngGroupNo = fcltsMngGroupNo;
	}
	/**
	 * @return the mntnRprSeq
	 */
	public String getMntnRprSeq() {
		return mntnRprSeq;
	}
	/**
	 * @param mntnRprSeq the mntnRprSeq to set
	 */
	public void setMntnRprSeq(String mntnRprSeq) {
		this.mntnRprSeq = mntnRprSeq;
	}
	/**
	 * @return the mntnRprSe
	 */
	public String getMntnRprSe() {
		return mntnRprSe;
	}
	/**
	 * @param mntnRprSe the mntnRprSe to set
	 */
	public void setMntnRprSe(String mntnRprSe) {
		this.mntnRprSe = mntnRprSe;
	}
	/**
	 * @return the mntnRprPart
	 */
	public String getMntnRprPart() {
		return mntnRprPart;
	}
	/**
	 * @param mntnRprPart the mntnRprPart to set
	 */
	public void setMntnRprPart(String mntnRprPart) {
		this.mntnRprPart = mntnRprPart;
	}
	/**
	 * @return the mntnRprCn
	 */
	public String getMntnRprCn() {
		return mntnRprCn;
	}
	/**
	 * @param mntnRprCn the mntnRprCn to set
	 */
	public void setMntnRprCn(String mntnRprCn) {
		this.mntnRprCn = mntnRprCn;
	}
	/**
	 * @return the mntnRprBdgt
	 */
	public String getMntnRprBdgt() {
		return mntnRprBdgt;
	}
	/**
	 * @param mntnRprBdgt the mntnRprBdgt to set
	 */
	public void setMntnRprBdgt(String mntnRprBdgt) {
		this.mntnRprBdgt = mntnRprBdgt;
	}
	/**
	 * @return the cnstrtr
	 */
	public String getCnstrtr() {
		return cnstrtr;
	}
	/**
	 * @param cnstrtr the cnstrtr to set
	 */
	public void setCnstrtr(String cnstrtr) {
		this.cnstrtr = cnstrtr;
	}
	/**
	 * @return the responEngineer
	 */
	public String getResponEngineer() {
		return responEngineer;
	}
	/**
	 * @param responEngineer the responEngineer to set
	 */
	public void setResponEngineer(String responEngineer) {
		this.responEngineer = responEngineer;
	}
	/**
	 * @return the enforceYear
	 */
	public String getEnforceYear() {
		return enforceYear;
	}
	/**
	 * @param enforceYear the enforceYear to set
	 */
	public void setEnforceYear(String enforceYear) {
		this.enforceYear = enforceYear;
	}
	/**
	 * @return the mntnSubRprSe
	 */
	public String getMntnSubRprSe() {
		return mntnSubRprSe;
	}
	/**
	 * @param mntnSubRprSe the mntnSubRprSe to set
	 */
	public void setMntnSubRprSe(String mntnSubRprSe) {
		this.mntnSubRprSe = mntnSubRprSe;
	}
	/**
	 * @return the wrtDt
	 */
	public String getWrtDt() {
		return wrtDt;
	}
	/**
	 * @param wrtDt the wrtDt to set
	 */
	public void setWrtDt(String wrtDt) {
		this.wrtDt = wrtDt;
	}
	/**
	 * @return the wrtUsr
	 */
	public String getWrtUsr() {
		return wrtUsr;
	}
	/**
	 * @param wrtUsr the wrtUsr to set
	 */
	public void setWrtUsr(String wrtUsr) {
		this.wrtUsr = wrtUsr;
	}
	/**
	 * @return the mntnRprSeNm
	 */
	public String getMntnRprSeNm() {
		return mntnRprSeNm;
	}
	/**
	 * @param mntnRprSeNm the mntnRprSeNm to set
	 */
	public void setMntnRprSeNm(String mntnRprSeNm) {
		this.mntnRprSeNm = mntnRprSeNm;
	}
	/**
	 * @return the mntnRprCnstDtFromTo
	 */
	public String getMntnRprCnstDtFromTo() {
		return mntnRprCnstDtFromTo;
	}
	/**
	 * @param mntnRprCnstDtFromTo the mntnRprCnstDtFromTo to set
	 */
	public void setMntnRprCnstDtFromTo(String mntnRprCnstDtFromTo) {
		this.mntnRprCnstDtFromTo = mntnRprCnstDtFromTo;
	}

}
