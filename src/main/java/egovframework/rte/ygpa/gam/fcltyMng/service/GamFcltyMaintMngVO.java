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
	

	/** 시설물업무구분 */
	private String fcltsJobSe;
	
	/** 시설물관리그룹 */
	private String fcltsMngGroupNo;
	
	/** 유지보수순번 */
	private String mntnRprSeq;
	
	
	

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
	
	
	
	
	
	

}
