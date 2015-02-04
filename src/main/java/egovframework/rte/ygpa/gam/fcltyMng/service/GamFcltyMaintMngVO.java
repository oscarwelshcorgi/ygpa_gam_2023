/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyMaintMngVO extends ComDefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 시설물관리그룹 */
	private String sFcltsMngGroupNo;
	
	/** 공사계약 */
	private String sCtrtNo;
	
	
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
	

	/** 시설물업무구분 */
	private String fcltsJobSe;
	
	/** 시설물관리그룹 */
	private String fcltsMngGroupNo;
	
	/** 유지보수순번 */
	private String mntnRprSeq;
	
	
	/** 자료수 */
	private int totCnt;
	
	/** 공사금액합계 */
	private long sumMntnRprCnstAmt;
	
	/** 유지보수예산합계 */
	private long sumMntnRprBdgt;
	
	
	

	/**
	 * @return the sFcltsMngGroupNo
	 */
	public String getsFcltsMngGroupNo() {
		return sFcltsMngGroupNo;
	}

	/**
	 * @param sFcltsMngGroupNo the sFcltsMngGroupNo to set
	 */
	public void setsFcltsMngGroupNo(String sFcltsMngGroupNo) {
		this.sFcltsMngGroupNo = sFcltsMngGroupNo;
	}

	/**
	 * @return the sCtrtNo
	 */
	public String getsCtrtNo() {
		return sCtrtNo;
	}

	/**
	 * @param sCtrtNo the sCtrtNo to set
	 */
	public void setsCtrtNo(String sCtrtNo) {
		this.sCtrtNo = sCtrtNo;
	}

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
	 * @return the totCnt
	 */
	public int getTotCnt() {
		return totCnt;
	}

	/**
	 * @param totCnt the totCnt to set
	 */
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	/**
	 * @return the sumMntnRprCnstAmt
	 */
	public long getSumMntnRprCnstAmt() {
		return sumMntnRprCnstAmt;
	}

	/**
	 * @param sumMntnRprCnstAmt the sumMntnRprCnstAmt to set
	 */
	public void setSumMntnRprCnstAmt(long sumMntnRprCnstAmt) {
		this.sumMntnRprCnstAmt = sumMntnRprCnstAmt;
	}

	/**
	 * @return the sumMntnRprBdgt
	 */
	public long getSumMntnRprBdgt() {
		return sumMntnRprBdgt;
	}

	/**
	 * @param sumMntnRprBdgt the sumMntnRprBdgt to set
	 */
	public void setSumMntnRprBdgt(long sumMntnRprBdgt) {
		this.sumMntnRprBdgt = sumMntnRprBdgt;
	}
	
	
	
	
	
	

}
