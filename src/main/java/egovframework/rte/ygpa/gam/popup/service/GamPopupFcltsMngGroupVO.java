/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 14.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupFcltsMngGroupVO extends ComDefaultVO  {
	private static final long serialVersionUID = 1L;
	
	/** 시설물 관리 그룹 번호 (조회조건) **/
	public String sFcltsMngGroupNo;
	
	/** 시설물 관리 그룹명 (조회조건) **/
	public String sFcltsMngGroupNm;
	
	private String sFcltsMngGroup;

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
	
}
