/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltsClCdVO  extends ComDefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 검색 메인시설물분류 **/
	private String sMainFcltsDiv;
	
	/** 검색 분류코드 **/
	private String sFcltsClCd;
	
	/**검색 분류코드명 **/
	private String sFcltsClCdNm;
	
	
	
	/** 분류코드 **/
	private String fcltsClCd;
	
	/**  수정전 분류코드 **/
	private String oriFcltsClCd;
	
	/** 분류코드명 **/
	private String fcltsClCdNm;
	
	/** 분류상위코드 **/
	private String fcltsClUpperCd;
	
	/** 단계 **/
	private String depthSort;
	
	/** LEAF여부 **/
	private String leafYn;
	
	/** 등록자 **/
	private String regUsr;
	
	/** 수정자 **/
	private String updUsr;
	
	

	/**
	 * @return the sMainFcltsDiv
	 */
	public String getsMainFcltsDiv() {
		return sMainFcltsDiv;
	}

	/**
	 * @param sMainFcltsDiv the sMainFcltsDiv to set
	 */
	public void setsMainFcltsDiv(String sMainFcltsDiv) {
		this.sMainFcltsDiv = sMainFcltsDiv;
	}

	/**
	 * @return the sFcltsClCd
	 */
	public String getsFcltsClCd() {
		return sFcltsClCd;
	}

	/**
	 * @param sFcltsClCd the sFcltsClCd to set
	 */
	public void setsFcltsClCd(String sFcltsClCd) {
		this.sFcltsClCd = sFcltsClCd;
	}

	/**
	 * @return the sFcltsClCdNm
	 */
	public String getsFcltsClCdNm() {
		return sFcltsClCdNm;
	}

	/**
	 * @param sFcltsClCdNm the sFcltsClCdNm to set
	 */
	public void setsFcltsClCdNm(String sFcltsClCdNm) {
		this.sFcltsClCdNm = sFcltsClCdNm;
	}

	/**
	 * @return the fcltsClCd
	 */
	public String getFcltsClCd() {
		return fcltsClCd;
	}

	/**
	 * @param fcltsClCd the fcltsClCd to set
	 */
	public void setFcltsClCd(String fcltsClCd) {
		this.fcltsClCd = fcltsClCd;
	}

	/**
	 * @return the oriFcltsClCd
	 */
	public String getOriFcltsClCd() {
		return oriFcltsClCd;
	}

	/**
	 * @param oriFcltsClCd the oriFcltsClCd to set
	 */
	public void setOriFcltsClCd(String oriFcltsClCd) {
		this.oriFcltsClCd = oriFcltsClCd;
	}

	/**
	 * @return the fcltsClCdNm
	 */
	public String getFcltsClCdNm() {
		return fcltsClCdNm;
	}

	/**
	 * @param fcltsClCdNm the fcltsClCdNm to set
	 */
	public void setFcltsClCdNm(String fcltsClCdNm) {
		this.fcltsClCdNm = fcltsClCdNm;
	}

	/**
	 * @return the fcltsClUpperCd
	 */
	public String getFcltsClUpperCd() {
		return fcltsClUpperCd;
	}

	/**
	 * @param fcltsClUpperCd the fcltsClUpperCd to set
	 */
	public void setFcltsClUpperCd(String fcltsClUpperCd) {
		this.fcltsClUpperCd = fcltsClUpperCd;
	}

	/**
	 * @return the depthSort
	 */
	public String getDepthSort() {
		return depthSort;
	}

	/**
	 * @param depthSort the depthSort to set
	 */
	public void setDepthSort(String depthSort) {
		this.depthSort = depthSort;
	}

	/**
	 * @return the leafYn
	 */
	public String getLeafYn() {
		return leafYn;
	}

	/**
	 * @param leafYn the leafYn to set
	 */
	public void setLeafYn(String leafYn) {
		this.leafYn = leafYn;
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


}
