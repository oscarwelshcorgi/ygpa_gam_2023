/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyRepairSttusInqireVO extends ComDefaultVO  {

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
	
	private String sFlawRprEntrpsNm;

	
	/** 하자보수 시설명 */
	private String sPrtFcltyNm;
	
	/** 시설물업무구분 */
	private String fcltsJobSe;
	
	/** 시설물관리그룹구분 */
	private String fcltsMngGroupNo;
	
	/** 하자보수순번 */
	private int flawRprSeq;
	
	/** 자료수 */
	private int totCnt;
	
	/** 하자보수금액합계 */
	private long sumFlawRprAmt;
	
	

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
	 * @return the sumFlawRprAmt
	 */
	public long getSumFlawRprAmt() {
		return sumFlawRprAmt;
	}

	/**
	 * @param sumFlawRprAmt the sumFlawRprAmt to set
	 */
	public void setSumFlawRprAmt(long sumFlawRprAmt) {
		this.sumFlawRprAmt = sumFlawRprAmt;
	}

	/**
	 * @return the sFlawRprEntrpsNm
	 */
	public String getsFlawRprEntrpsNm() {
		return sFlawRprEntrpsNm;
	}

	/**
	 * @param sFlawRprEntrpsNm the sFlawRprEntrpsNm to set
	 */
	public void setsFlawRprEntrpsNm(String sFlawRprEntrpsNm) {
		this.sFlawRprEntrpsNm = sFlawRprEntrpsNm;
	}


	/**
	 * @return the sPrtFcltyNm
	 */
	public String getsPrtFcltyNm() {
		return sPrtFcltyNm;
	}

	/**
	 * @param sPrtFcltyNm the sPrtFcltyNm to set
	 */
	public void setsPrtFcltyNm(String sPrtFcltyNm) {
		this.sPrtFcltyNm = sPrtFcltyNm;
	}

	

}
