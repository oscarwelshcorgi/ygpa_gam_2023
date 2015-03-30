/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
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
	
	private String flawExamUsr;
	
	/** 시설물업무구분 */
	private String fcltsJobSe;
	
	/** 시설물관리그룹구분 */
	private String fcltsMngGroupNo;
	
	/** 하자보수순번 */
	private String flawRprSeq;
	
	/** 자료수 */
	private int totCnt;
	
	/** 하자보수금액합계 */
	private long sumFlawRprAmt;
	
	private String atchFileSj;
	private String atchFileNmPhysicl;
	private String atchFileNmLogic;
	private String atchFileSeq;
	
	/**
	 * @return the atchFcltsJobSe
	 */
	public String getAtchFcltsJobSe() {
		return atchFcltsJobSe;
	}

	/**
	 * @param atchFcltsJobSe the atchFcltsJobSe to set
	 */
	public void setAtchFcltsJobSe(String atchFcltsJobSe) {
		this.atchFcltsJobSe = atchFcltsJobSe;
	}

	/**
	 * @return the atchFileNo
	 */
	public String getAtchFileNo() {
		return atchFileNo;
	}

	/**
	 * @param atchFileNo the atchFileNo to set
	 */
	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}

	/**
	 * @return the atchFcltsMngGroupNo
	 */
	public String getAtchFcltsMngGroupNo() {
		return atchFcltsMngGroupNo;
	}

	/**
	 * @param atchFcltsMngGroupNo the atchFcltsMngGroupNo to set
	 */
	public void setAtchFcltsMngGroupNo(String atchFcltsMngGroupNo) {
		this.atchFcltsMngGroupNo = atchFcltsMngGroupNo;
	}

	/**
	 * @return the atchFlawRprSeq
	 */
	public String getAtchFlawRprSeq() {
		return atchFlawRprSeq;
	}

	/**
	 * @param atchFlawRprSeq the atchFlawRprSeq to set
	 */
	public void setAtchFlawRprSeq(String atchFlawRprSeq) {
		this.atchFlawRprSeq = atchFlawRprSeq;
	}

	private String atchFcltsJobSe;
	private String atchFileNo;
	private String atchFcltsMngGroupNo;
	private String atchFlawRprSeq;
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
	 * @return the flawExamUsr
	 */
	public String getFlawExamUsr() {
		return flawExamUsr;
	}

	/**
	 * @param flawExamUsr the flawExamUsr to set
	 */
	public void setFlawExamUsr(String flawExamUsr) {
		this.flawExamUsr = flawExamUsr;
	}

	/**
	 * @return the atchFileSj
	 */
	public String getAtchFileSj() {
		return atchFileSj;
	}

	/**
	 * @param atchFileSj the atchFileSj to set
	 */
	public void setAtchFileSj(String atchFileSj) {
		this.atchFileSj = atchFileSj;
	}

	/**
	 * @return the atchFileNmPhysicl
	 */
	public String getAtchFileNmPhysicl() {
		return atchFileNmPhysicl;
	}

	/**
	 * @param atchFileNmPhysicl the atchFileNmPhysicl to set
	 */
	public void setAtchFileNmPhysicl(String atchFileNmPhysicl) {
		this.atchFileNmPhysicl = atchFileNmPhysicl;
	}

	/**
	 * @return the atchFileNmLogic
	 */
	public String getAtchFileNmLogic() {
		return atchFileNmLogic;
	}

	/**
	 * @param atchFileNmLogic the atchFileNmLogic to set
	 */
	public void setAtchFileNmLogic(String atchFileNmLogic) {
		this.atchFileNmLogic = atchFileNmLogic;
	}

	/**
	 * @return the atchFileSeq
	 */
	public String getAtchFileSeq() {
		return atchFileSeq;
	}

	/**
	 * @param atchFileSeq the atchFileSeq to set
	 */
	public void setAtchFileSeq(String atchFileSeq) {
		this.atchFileSeq = atchFileSeq;
	}

	/**
	 * @return the flawRprSeq
	 */
	public String getFlawRprSeq() {
		return flawRprSeq;
	}

	/**
	 * @param flawRprSeq the flawRprSeq to set
	 */
	public void setFlawRprSeq(String flawRprSeq) {
		this.flawRprSeq = flawRprSeq;
	}



}
