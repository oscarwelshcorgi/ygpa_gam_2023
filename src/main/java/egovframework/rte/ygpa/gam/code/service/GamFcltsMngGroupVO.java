/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 5.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltsMngGroupVO  extends ComDefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 검색 메인시설물분류 **/
	private String sMainFcltsDiv;
	
	/** 검색 분류코드 **/
	private String sFcltsMngGroup;
	
	/**검색 분류코드명 **/
	private String sFcltsMngGroupNm;
	
	
	
	/** 분류코드 **/
	private String fcltsClCd;
	
	/**  수정전 분류코드 **/
	private String oriFcltsMngGroup;
	
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
	 * @return the sFcltsMngGroup
	 */
	public String getsFcltsMngGroup() {
		return sFcltsMngGroup;
	}

	/**
	 * @param sFcltsMngGroup the sFcltsMngGroup to set
	 */
	public void setsFcltsMngGroup(String sFcltsMngGroup) {
		this.sFcltsMngGroup = sFcltsMngGroup;
	}

	/**
	 * @return the sFcltsMngGroupNm
	 */
	public String getsFcltsMngGroupNm() {
		return sFcltsMngGroupNm;
	}

	/**
	 * @param sFcltsMngGroupNm the sFcltsMngGroupNm to set
	 */
	public void setsFcltsMngGroupNm(String sFcltsMngGroupNm) {
		this.sFcltsMngGroupNm = sFcltsMngGroupNm;
	}

	/**
	 * @return the fcltsClCd
	 */
	public String getFcltsMngGroup() {
		return fcltsClCd;
	}

	/**
	 * @param fcltsClCd the fcltsClCd to set
	 */
	public void setFcltsMngGroup(String fcltsClCd) {
		this.fcltsClCd = fcltsClCd;
	}

	/**
	 * @return the oriFcltsMngGroup
	 */
	public String getOriFcltsMngGroup() {
		return oriFcltsMngGroup;
	}

	/**
	 * @param oriFcltsMngGroup the oriFcltsMngGroup to set
	 */
	public void setOriFcltsMngGroup(String oriFcltsMngGroup) {
		this.oriFcltsMngGroup = oriFcltsMngGroup;
	}

	/**
	 * @return the fcltsClCdNm
	 */
	public String getFcltsMngGroupNm() {
		return fcltsClCdNm;
	}

	/**
	 * @param fcltsClCdNm the fcltsClCdNm to set
	 */
	public void setFcltsMngGroupNm(String fcltsClCdNm) {
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
