/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltsMngGroupVO  extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	
	/** 시설물 그룹번호 (조회조건) **/
	private String sFcltsMngGroupNo;
	
	/** 시설물 그룹명 (조회조건) **/
	private String sFcltsMngGroupNm;

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
