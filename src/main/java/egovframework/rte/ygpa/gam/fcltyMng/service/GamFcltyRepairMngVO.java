/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 1.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 1.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyRepairMngVO extends ComDefaultVO  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 시설물업무구분 - 검색 */
	private String sFcltsJobSe;
	
	/** 하자보수명 - 검색 */
	private String sFlawRprNm;
	
	/** 하자보수구분 - 검색 */
	private String sFlawExamSe;
	
	/** 하자검사일 검색시작일 - 검색 */
	private String sFlawRprStartDtFr;
	
	/** 하자검사일 검색종료일 - 검색 */
	private String sFlawRprStartDtTo;
	
	
	
	
	/** 시행년도 */
	private String enforceYear;
	
	/** 하자유무 */
	private String flawEnnc;
	
	/** 하자보수유형 */
	private String flawRprTy;
	
	/** 계약번호 */
	private String ctrtNo;
	
	/** 하자보수명 */
	private String flawRprNm;
	
	/** 하자보수업체명 */
	private String flawRprEntrpsNm;
	
	/** 하자보수완료여부 */
	private String flawRprComptYn;
	
	/** 하자보수금액 */
	private long flawRprAmt;
	
	/** 하자발생일자 */
	private String flawOccrrncDt;
	
	/** 하자검사일자 */
	private String flawExamDt;
	
	/** 하자보수시작일자 */
	private String flawRprStartDt;
	
	/** 하자보수종료일자 */
	private String flawRprEndDt;
	
	/** 하자보수내용 */
	private String flawRprContents;
	
	/** 비고 */
	private String rm;
	
	/** 시설물업무구분 */
	private String fcltsJobSe;
	
	/** 시설물관리그룹구분 */
	private String fcltsMngGroupNo;
	
	/** 하자보수순번 */
	private int flawRprSeq;
	
	
	/** 하자검사구분 */
	private String flawExamSe;
	
	/** 하자검사결과 */
	private String flawExamResult;

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
	 * @return the sFlawRprNm
	 */
	public String getsFlawRprNm() {
		return sFlawRprNm;
	}

	/**
	 * @param sFlawRprNm the sFlawRprNm to set
	 */
	public void setsFlawRprNm(String sFlawRprNm) {
		this.sFlawRprNm = sFlawRprNm;
	}

	/**
	 * @return the sFlawExamSe
	 */
	public String getsFlawExamSe() {
		return sFlawExamSe;
	}

	/**
	 * @param sFlawExamSe the sFlawExamSe to set
	 */
	public void setsFlawExamSe(String sFlawExamSe) {
		this.sFlawExamSe = sFlawExamSe;
	}

	/**
	 * @return the sFlawRprStartDtFr
	 */
	public String getsFlawRprStartDtFr() {
		return sFlawRprStartDtFr;
	}

	/**
	 * @param sFlawRprStartDtFr the sFlawRprStartDtFr to set
	 */
	public void setsFlawRprStartDtFr(String sFlawRprStartDtFr) {
		this.sFlawRprStartDtFr = sFlawRprStartDtFr;
	}

	/**
	 * @return the sFlawRprStartDtTo
	 */
	public String getsFlawRprStartDtTo() {
		return sFlawRprStartDtTo;
	}

	/**
	 * @param sFlawRprStartDtTo the sFlawRprStartDtTo to set
	 */
	public void setsFlawRprStartDtTo(String sFlawRprStartDtTo) {
		this.sFlawRprStartDtTo = sFlawRprStartDtTo;
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
	 * @return the flawEnnc
	 */
	public String getFlawEnnc() {
		return flawEnnc;
	}

	/**
	 * @param flawEnnc the flawEnnc to set
	 */
	public void setFlawEnnc(String flawEnnc) {
		this.flawEnnc = flawEnnc;
	}

	/**
	 * @return the flawRprTy
	 */
	public String getFlawRprTy() {
		return flawRprTy;
	}

	/**
	 * @param flawRprTy the flawRprTy to set
	 */
	public void setFlawRprTy(String flawRprTy) {
		this.flawRprTy = flawRprTy;
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
	 * @return the flawRprNm
	 */
	public String getFlawRprNm() {
		return flawRprNm;
	}

	/**
	 * @param flawRprNm the flawRprNm to set
	 */
	public void setFlawRprNm(String flawRprNm) {
		this.flawRprNm = flawRprNm;
	}

	/**
	 * @return the flawRprEntrpsNm
	 */
	public String getFlawRprEntrpsNm() {
		return flawRprEntrpsNm;
	}

	/**
	 * @param flawRprEntrpsNm the flawRprEntrpsNm to set
	 */
	public void setFlawRprEntrpsNm(String flawRprEntrpsNm) {
		this.flawRprEntrpsNm = flawRprEntrpsNm;
	}

	/**
	 * @return the flawRprComptYn
	 */
	public String getFlawRprComptYn() {
		return flawRprComptYn;
	}

	/**
	 * @param flawRprComptYn the flawRprComptYn to set
	 */
	public void setFlawRprComptYn(String flawRprComptYn) {
		this.flawRprComptYn = flawRprComptYn;
	}

	/**
	 * @return the flawRprAmt
	 */
	public long getFlawRprAmt() {
		return flawRprAmt;
	}

	/**
	 * @param flawRprAmt the flawRprAmt to set
	 */
	public void setFlawRprAmt(long flawRprAmt) {
		this.flawRprAmt = flawRprAmt;
	}

	/**
	 * @return the flawOccrrncDt
	 */
	public String getFlawOccrrncDt() {
		return flawOccrrncDt;
	}

	/**
	 * @param flawOccrrncDt the flawOccrrncDt to set
	 */
	public void setFlawOccrrncDt(String flawOccrrncDt) {
		this.flawOccrrncDt = flawOccrrncDt;
	}

	/**
	 * @return the flawExamDt
	 */
	public String getFlawExamDt() {
		return flawExamDt;
	}

	/**
	 * @param flawExamDt the flawExamDt to set
	 */
	public void setFlawExamDt(String flawExamDt) {
		this.flawExamDt = flawExamDt;
	}

	/**
	 * @return the flawRprStartDt
	 */
	public String getFlawRprStartDt() {
		return flawRprStartDt;
	}

	/**
	 * @param flawRprStartDt the flawRprStartDt to set
	 */
	public void setFlawRprStartDt(String flawRprStartDt) {
		this.flawRprStartDt = flawRprStartDt;
	}

	/**
	 * @return the flawRprEndDt
	 */
	public String getFlawRprEndDt() {
		return flawRprEndDt;
	}

	/**
	 * @param flawRprEndDt the flawRprEndDt to set
	 */
	public void setFlawRprEndDt(String flawRprEndDt) {
		this.flawRprEndDt = flawRprEndDt;
	}

	/**
	 * @return the flawRprContents
	 */
	public String getFlawRprContents() {
		return flawRprContents;
	}

	/**
	 * @param flawRprContents the flawRprContents to set
	 */
	public void setFlawRprContents(String flawRprContents) {
		this.flawRprContents = flawRprContents;
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
	 * @return the flawRprSeq
	 */
	public int getFlawRprSeq() {
		return flawRprSeq;
	}

	/**
	 * @param flawRprSeq the flawRprSeq to set
	 */
	public void setFlawRprSeq(int flawRprSeq) {
		this.flawRprSeq = flawRprSeq;
	}

	/**
	 * @return the flawExamSe
	 */
	public String getFlawExamSe() {
		return flawExamSe;
	}

	/**
	 * @param flawExamSe the flawExamSe to set
	 */
	public void setFlawExamSe(String flawExamSe) {
		this.flawExamSe = flawExamSe;
	}

	/**
	 * @return the flawExamResult
	 */
	public String getFlawExamResult() {
		return flawExamResult;
	}

	/**
	 * @param flawExamResult the flawExamResult to set
	 */
	public void setFlawExamResult(String flawExamResult) {
		this.flawExamResult = flawExamResult;
	}
	
	

}
