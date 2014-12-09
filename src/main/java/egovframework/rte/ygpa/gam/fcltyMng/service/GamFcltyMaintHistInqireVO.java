/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

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

public class GamFcltyMaintHistInqireVO extends ComDefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 시공업체 */
	private String sCnstrtr;
	
	/** 유지보수 시설명 */
	private String sPrtFcltyNm;
	
	/** 유지보수공사시작일 검색 Fr */
	private String sMntnRprCnstStartDtFr;
	
	/** 유지보수공사시작일 검색 To */
	private String sMntnRprCnstStartDtTo;


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
	 * @return the sCnstrtr
	 */
	public String getsCnstrtr() {
		return sCnstrtr;
	}

	/**
	 * @param sCnstrtr the sCnstrtr to set
	 */
	public void setsCnstrtr(String sCnstrtr) {
		this.sCnstrtr = sCnstrtr;
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
