package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 21.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 21.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupQcItemCdVo extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 자료컬럼 **/
	private String qcItemCd;				// 점검 항목 코드
	private String qcItemNm;				// 점검 항목 명
	private String fcltsJobSe;				// 시설물 업무 구분
	private String fcltsJobSeNm;			// 시설물 업무 구분 명
	private String depthSort;				// 단계
	private String qcItemDtls;				// 점검 항목 상세
	private String useYn;					// 사용 유무
	/** 조회조건 **/
	private String sQcItemCd;				// 검색 점검 항목 코드
	private String sQcItemNm;				// 검색 점검 항목 명
	private String sFcltsJobSe;				// 검색 시설물 업무 구분
	private String sDepthSort;				// 검색 단계

	/**
	 * @return the qcItemCd
	 */
	public String getQcItemCd() {
		return qcItemCd;
	}
	/**
	 * @param qcItemCd the qcItemCd to set
	 */
	public void setQcItemCd(String qcItemCd) {
		this.qcItemCd = qcItemCd;
	}
	/**
	 * @return the qcItemNm
	 */
	public String getQcItemNm() {
		return qcItemNm;
	}
	/**
	 * @param qcItemNm the qcItemNm to set
	 */
	public void setQcItemNm(String qcItemNm) {
		this.qcItemNm = qcItemNm;
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
	 * @return the qcItemDtls
	 */
	public String getQcItemDtls() {
		return qcItemDtls;
	}
	/**
	 * @param qcItemDtls the qcItemDtls to set
	 */
	public void setQcItemDtls(String qcItemDtls) {
		this.qcItemDtls = qcItemDtls;
	}
	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * @return the sQcItemCd
	 */
	public String getsQcItemCd() {
		return sQcItemCd;
	}
	/**
	 * @param sQcItemCd the sQcItemCd to set
	 */
	public void setsQcItemCd(String sQcItemCd) {
		this.sQcItemCd = sQcItemCd;
	}
	/**
	 * @return the sQcItemNm
	 */
	public String getsQcItemNm() {
		return sQcItemNm;
	}
	/**
	 * @param sQcItemNm the sQcItemNm to set
	 */
	public void setsQcItemNm(String sQcItemNm) {
		this.sQcItemNm = sQcItemNm;
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

}
