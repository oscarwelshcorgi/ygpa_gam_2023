/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyMaintMngVO extends ComDefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 시설물업무구분 */
	private String sFcltsJobSe;
	
	/** 유지보수공사명 */
	private String sMntnRprCnstNm;
	
	/** 유지보수구분 */
	private String sMntnRprSe;
	
	/** 유지보수공사시작일 검색 Fr */
	private String sMntnRprCnstStartDtFr;
	
	/** 유지보수공사시작일 검색 To */
	private String sMntnRprCnstStartDtTo;
	
	
	
	
	/** 시행년도 */
	private String enforceYear;
	
	/** 시설물업무구분 */
	private String fcltsJobSe;
	
	/** 유지보수구분 */
	private String mntnRprSe;
	
	/** 계약번호 */
	private String ctrtNo;
	
	/** 유지보수순번 */
	private String mntnRprSeq;
	
	/** 공사명 */
	private String mntnRprCnstNm;
	
	/** 유지보수부위 */
	private String mntnRprPart;
	
	/** 예산 */
	private long mntnRprBdgt;
	
	/** 공사시작일자 */
	private String mntnRprCnstStartDt;
	
	/** 공사금액 */
	private long mntnRprCnstAmt;
	
	/** 공사종료일자 */
	private String mntnRprCnstEndDt;
	
	/** 설계자 */
	private String plannerNm;
	
	/** 시공자 */
	private String cnstrtr;
	
	/** 책임기술자 */
	private String responEngineer;
	
	/** 공사감독자 */
	private String cnstChargNm;
	
	/** 유지보수내용 */
	private String mntnRprCn;
	
	/** 비고 */
	private String rm;
	
	/** 시설물관리그룹 */
	private String fcltsMngGroupNo;
	
	
	

	/**
	 * @return the sFcltsJobSe
	 */
	public String getsFcltsJobSe() {
		return sFcltsJobSe;
	}

	/**
	 * @param sFcltsJobSe the sFcltsJobSe to set
	 */
	public void setsFcltsJobSe(String sFcltsJobSe) {
		this.sFcltsJobSe = sFcltsJobSe;
	}

	/**
	 * @return the sMntnRprCnstNm
	 */
	public String getsMntnRprCnstNm() {
		return sMntnRprCnstNm;
	}

	/**
	 * @param sMntnRprCnstNm the sMntnRprCnstNm to set
	 */
	public void setsMntnRprCnstNm(String sMntnRprCnstNm) {
		this.sMntnRprCnstNm = sMntnRprCnstNm;
	}

	/**
	 * @return the sMntnRprSe
	 */
	public String getsMntnRprSe() {
		return sMntnRprSe;
	}

	/**
	 * @param sMntnRprSe the sMntnRprSe to set
	 */
	public void setsMntnRprSe(String sMntnRprSe) {
		this.sMntnRprSe = sMntnRprSe;
	}

	/**
	 * @return the sMntnRprCnstStartDtFr
	 */
	public String getsMntnRprCnstStartDtFr() {
		return sMntnRprCnstStartDtFr;
	}

	/**
	 * @param sMntnRprCnstStartDtFr the sMntnRprCnstStartDtFr to set
	 */
	public void setsMntnRprCnstStartDtFr(String sMntnRprCnstStartDtFr) {
		this.sMntnRprCnstStartDtFr = sMntnRprCnstStartDtFr;
	}

	/**
	 * @return the sMntnRprCnstStartDtTo
	 */
	public String getsMntnRprCnstStartDtTo() {
		return sMntnRprCnstStartDtTo;
	}

	/**
	 * @param sMntnRprCnstStartDtTo the sMntnRprCnstStartDtTo to set
	 */
	public void setsMntnRprCnstStartDtTo(String sMntnRprCnstStartDtTo) {
		this.sMntnRprCnstStartDtTo = sMntnRprCnstStartDtTo;
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
	 * @return the mntnRprBdgt
	 */
	public long getMntnRprBdgt() {
		return mntnRprBdgt;
	}

	/**
	 * @param mntnRprBdgt the mntnRprBdgt to set
	 */
	public void setMntnRprBdgt(long mntnRprBdgt) {
		this.mntnRprBdgt = mntnRprBdgt;
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
	 * @return the mntnRprCnstAmt
	 */
	public long getMntnRprCnstAmt() {
		return mntnRprCnstAmt;
	}

	/**
	 * @param mntnRprCnstAmt the mntnRprCnstAmt to set
	 */
	public void setMntnRprCnstAmt(long mntnRprCnstAmt) {
		this.mntnRprCnstAmt = mntnRprCnstAmt;
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
	
	
	
	
	
	

}
