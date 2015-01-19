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
public class GamFcltyRepairHistInqireVO extends ComDefaultVO  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 업체 */
	private String sFlawRprEntrpsNm;
	
	/** 하자보수 시설명 */
	private String sPrtFcltyNm;
	
	/** 하자보수 시설코드 */
	private String sFcltsMngNo;
	
	/** 하자보수공사시작일 검색 Fr */
	private String sFlawRprStartDtFr;
	
	/** 하자보수공사시작일 검색 To */
	private String sFlawRprStartDtTo;
	
	private String fcltsJobSe;
	
	private String fcltsMngGroupNo;
	
	private String flawRprSeq;
	
	private String fcltsMngNo;
	

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

	/**
	 * @return the sFcltsMngNo
	 */
	public String getsFcltsMngNo() {
		return sFcltsMngNo;
	}

	/**
	 * @param sFcltsMngNo the sFcltsMngNo to set
	 */
	public void setsFcltsMngNo(String sFcltsMngNo) {
		this.sFcltsMngNo = sFcltsMngNo;
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
	public String getFlawRprSeq() {
		return flawRprSeq;
	}

	/**
	 * @param flawRprSeq the flawRprSeq to set
	 */
	public void setFlawRprSeq(String flawRprSeq) {
		this.flawRprSeq = flawRprSeq;
	}

	/**
	 * @return the fcltsMngNo
	 */
	public String getFcltsMngNo() {
		return fcltsMngNo;
	}

	/**
	 * @param fcltsMngNo the fcltsMngNo to set
	 */
	public void setFcltsMngNo(String fcltsMngNo) {
		this.fcltsMngNo = fcltsMngNo;
	}
	
	

	
	

}
