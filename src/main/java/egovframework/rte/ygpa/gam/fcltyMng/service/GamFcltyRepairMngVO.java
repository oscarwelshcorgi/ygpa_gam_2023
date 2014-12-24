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
	
	
	/** 시설물업무구분 */
	private String fcltsJobSe;
	
	/** 시설물관리그룹구분 */
	private String fcltsMngGroupNo;
	
	/** 하자보수순번 */
	private int flawRprSeq;
	
	

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

	

}
