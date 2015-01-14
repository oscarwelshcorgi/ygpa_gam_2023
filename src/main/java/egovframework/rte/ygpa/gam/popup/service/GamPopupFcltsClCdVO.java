/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author 김종민
 * @since 2014. 11. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 5.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupFcltsClCdVO extends ComDefaultVO  {

	private static final long serialVersionUID = 1L;

	/** 자료컬럼 **/
	private String fcltsClCd;				// 시설물 분류 코드
	private String fcltsClCdNm;				// 시설물 분류 코드 명
	private String fcltsClUpperCd;			// 시설물 분류 상위 코드
	private String fcltsJobSe;				// 시설물 업무 구분
	private String fcltsJobSeNm;			// 시설물 업무 구분 명
	private String depthSort;				// 단계
	private String leafYn;					// LEAF 유무
	/** 조회조건 **/
	private String sFcltsClCd;				// 검색 시설물 분류 코드
	private String sFcltsClCdNm;			// 검색 시설물 분류 코드 명
	private String sFcltsJobSe;				// 검색 시설물 업무 구분
	private String sDepthSort;				// 검색 단계
	private String sLeafYn;					// 검색 LEAF 유무

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
	 * @return the fcltsJobSeNm
	 */
	public String getFcltsJobSeNm() {
		return fcltsJobSeNm;
	}
	/**
	 * @param fcltsJobSeNm the fcltsJobSeNm to set
	 */
	public void setFcltsJobSeNm(String fcltsJobSeNm) {
		this.fcltsJobSeNm = fcltsJobSeNm;
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
	 * @return the sDepthSort
	 */
	public String getsDepthSort() {
		return sDepthSort;
	}
	/**
	 * @param sDepthSort the sDepthSort to set
	 */
	public void setsDepthSort(String sDepthSort) {
		this.sDepthSort = sDepthSort;
	}
	/**
	 * @return the sLeafYn
	 */
	public String getsLeafYn() {
		return sLeafYn;
	}
	/**
	 * @param sLeafYn the sLeafYn to set
	 */
	public void setsLeafYn(String sLeafYn) {
		this.sLeafYn = sLeafYn;
	}

}
