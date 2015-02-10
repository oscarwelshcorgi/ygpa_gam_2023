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
public class GamFcltsMngRegistMngQcMngDtlsVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String rnum;				// 번호
	private	String fcltsMngGroupNo;		// 시설물 관리 그룹 번호
	private	String fcltsJobSe;			// 시설물 업무 구분
	private	String qcMngSeq;			// 점검 관리 순번
	private	String enforceYear;			// 시행 년도
	private	String qcMngNm;				// 점검 관리 명
	private	String qcSe;				// 점검 구분
	private	String qcInspTp;			// 점검 진단 자
	private	String qcInspSe;			// 점검 진단 구분
	private	String qcInspDt;			// 점검 진단 일자
	private	String qcInspInsttNm;		// 점검 진단 기관 명
	private	String responEngineerNm;	// 책임 기술자 명
	private	String qcBeginDt;			// 점검 시작 일자
	private	String qcEndDt;				// 점검 종료 일자
	private	String qcInspAmt;			// 점검 진단 금액
	private	String sttusEvlLvl;			// 상태 평가 등급
	private	String actionCn;			// 조치 내용
	private	String rm;					// 비고
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String qcInspResult;		// 점검 진단 결과
	private	String ctrtNo;				// 계약 번호
	private	String qcInspBdgt;			// 점검 진단 예산
	private	String wrtDt;				// 작성 일자
	private	String wrtUsr;				// 작성자
	private	String qcSeNm;				// 점검 구분 명
	private	String qcInspTpNm;			// 점검 진단 명
	private	String qcInspSeNm;			// 점검 진단 구분 명
	private	String sttusEvlLvlNm;		// 상태 평가 등급 명
	private	String qcInspDtFromTo;		// 점검 진단 기간

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
	 * @return the qcMngSeq
	 */
	public String getQcMngSeq() {
		return qcMngSeq;
	}
	/**
	 * @param qcMngSeq the qcMngSeq to set
	 */
	public void setQcMngSeq(String qcMngSeq) {
		this.qcMngSeq = qcMngSeq;
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
	 * @return the qcMngNm
	 */
	public String getQcMngNm() {
		return qcMngNm;
	}
	/**
	 * @param qcMngNm the qcMngNm to set
	 */
	public void setQcMngNm(String qcMngNm) {
		this.qcMngNm = qcMngNm;
	}
	/**
	 * @return the qcSe
	 */
	public String getQcSe() {
		return qcSe;
	}
	/**
	 * @param qcSe the qcSe to set
	 */
	public void setQcSe(String qcSe) {
		this.qcSe = qcSe;
	}
	/**
	 * @return the qcInspTp
	 */
	public String getQcInspTp() {
		return qcInspTp;
	}
	/**
	 * @param qcInspTp the qcInspTp to set
	 */
	public void setQcInspTp(String qcInspTp) {
		this.qcInspTp = qcInspTp;
	}
	/**
	 * @return the qcInspSe
	 */
	public String getQcInspSe() {
		return qcInspSe;
	}
	/**
	 * @param qcInspSe the qcInspSe to set
	 */
	public void setQcInspSe(String qcInspSe) {
		this.qcInspSe = qcInspSe;
	}
	/**
	 * @return the qcInspDt
	 */
	public String getQcInspDt() {
		return qcInspDt;
	}
	/**
	 * @param qcInspDt the qcInspDt to set
	 */
	public void setQcInspDt(String qcInspDt) {
		this.qcInspDt = qcInspDt;
	}
	/**
	 * @return the qcInspInsttNm
	 */
	public String getQcInspInsttNm() {
		return qcInspInsttNm;
	}
	/**
	 * @param qcInspInsttNm the qcInspInsttNm to set
	 */
	public void setQcInspInsttNm(String qcInspInsttNm) {
		this.qcInspInsttNm = qcInspInsttNm;
	}
	/**
	 * @return the responEngineerNm
	 */
	public String getResponEngineerNm() {
		return responEngineerNm;
	}
	/**
	 * @param responEngineerNm the responEngineerNm to set
	 */
	public void setResponEngineerNm(String responEngineerNm) {
		this.responEngineerNm = responEngineerNm;
	}
	/**
	 * @return the qcBeginDt
	 */
	public String getQcBeginDt() {
		return qcBeginDt;
	}
	/**
	 * @param qcBeginDt the qcBeginDt to set
	 */
	public void setQcBeginDt(String qcBeginDt) {
		this.qcBeginDt = qcBeginDt;
	}
	/**
	 * @return the qcEndDt
	 */
	public String getQcEndDt() {
		return qcEndDt;
	}
	/**
	 * @param qcEndDt the qcEndDt to set
	 */
	public void setQcEndDt(String qcEndDt) {
		this.qcEndDt = qcEndDt;
	}
	/**
	 * @return the qcInspAmt
	 */
	public String getQcInspAmt() {
		return qcInspAmt;
	}
	/**
	 * @param qcInspAmt the qcInspAmt to set
	 */
	public void setQcInspAmt(String qcInspAmt) {
		this.qcInspAmt = qcInspAmt;
	}
	/**
	 * @return the sttusEvlLvl
	 */
	public String getSttusEvlLvl() {
		return sttusEvlLvl;
	}
	/**
	 * @param sttusEvlLvl the sttusEvlLvl to set
	 */
	public void setSttusEvlLvl(String sttusEvlLvl) {
		this.sttusEvlLvl = sttusEvlLvl;
	}
	/**
	 * @return the actionCn
	 */
	public String getActionCn() {
		return actionCn;
	}
	/**
	 * @param actionCn the actionCn to set
	 */
	public void setActionCn(String actionCn) {
		this.actionCn = actionCn;
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
	 * @return the qcInspResult
	 */
	public String getQcInspResult() {
		return qcInspResult;
	}
	/**
	 * @param qcInspResult the qcInspResult to set
	 */
	public void setQcInspResult(String qcInspResult) {
		this.qcInspResult = qcInspResult;
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
	 * @return the qcInspBdgt
	 */
	public String getQcInspBdgt() {
		return qcInspBdgt;
	}
	/**
	 * @param qcInspBdgt the qcInspBdgt to set
	 */
	public void setQcInspBdgt(String qcInspBdgt) {
		this.qcInspBdgt = qcInspBdgt;
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
	 * @return the qcSeNm
	 */
	public String getQcSeNm() {
		return qcSeNm;
	}
	/**
	 * @param qcSeNm the qcSeNm to set
	 */
	public void setQcSeNm(String qcSeNm) {
		this.qcSeNm = qcSeNm;
	}
	/**
	 * @return the qcInspTpNm
	 */
	public String getQcInspTpNm() {
		return qcInspTpNm;
	}
	/**
	 * @param qcInspTpNm the qcInspTpNm to set
	 */
	public void setQcInspTpNm(String qcInspTpNm) {
		this.qcInspTpNm = qcInspTpNm;
	}
	/**
	 * @return the qcInspSeNm
	 */
	public String getQcInspSeNm() {
		return qcInspSeNm;
	}
	/**
	 * @param qcInspSeNm the qcInspSeNm to set
	 */
	public void setQcInspSeNm(String qcInspSeNm) {
		this.qcInspSeNm = qcInspSeNm;
	}
	/**
	 * @return the sttusEvlLvlNm
	 */
	public String getSttusEvlLvlNm() {
		return sttusEvlLvlNm;
	}
	/**
	 * @param sttusEvlLvlNm the sttusEvlLvlNm to set
	 */
	public void setSttusEvlLvlNm(String sttusEvlLvlNm) {
		this.sttusEvlLvlNm = sttusEvlLvlNm;
	}
	/**
	 * @return the qcInspDtFromTo
	 */
	public String getQcInspDtFromTo() {
		return qcInspDtFromTo;
	}
	/**
	 * @param qcInspDtFromTo the qcInspDtFromTo to set
	 */
	public void setQcInspDtFromTo(String qcInspDtFromTo) {
		this.qcInspDtFromTo = qcInspDtFromTo;
	}

}
