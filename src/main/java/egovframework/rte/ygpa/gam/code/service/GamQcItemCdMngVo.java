/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamQcItemCdMngVo extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 자료컬럼 **/
	private String qcItemCd;				//점검 항목 코드
	private String qcItemNm;				//점검 항목 명
	private String fcltsJobSe;				//시설물 업무 구분
	private String fcltsJobSeNm;			//시설물 업무 구분 명
	private String depthSort;				//단계
	private String qcItemDtls;				//점검 항목 상세
	private String qcItemUpperCd;			//점검 항목 상위 코드
	private String qcItemUpperNm;			//점검 항목 상위 명
	private String useYn;					//사용 유무
	private	String regUsr;					//등록자
	private	String registDt;				//등록일시
	private	String updUsr;					//수정자
	private	String updtDt;					//수정일시
	/** 조회조건 **/
	private String sQcItemCd;				//점검 항목 코드
	private String sQcItemNm;				//점검 항목 명
	private String sFcltsJobSe;				//시설물 업무 구분
	private String sDepthSort;				//단계
	/** 합계컬럼 **/
	private String	dataCount;				//자료수

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
	 * @return the qcItemUpperCd
	 */
	public String getQcItemUpperCd() {
		return qcItemUpperCd;
	}
	/**
	 * @param qcItemUpperCd the qcItemUpperCd to set
	 */
	public void setQcItemUpperCd(String qcItemUpperCd) {
		this.qcItemUpperCd = qcItemUpperCd;
	}
	/**
	 * @return the qcItemUpperNm
	 */
	public String getQcItemUpperNm() {
		return qcItemUpperNm;
	}
	/**
	 * @param qcItemUpperNm the qcItemUpperNm to set
	 */
	public void setQcItemUpperNm(String qcItemUpperNm) {
		this.qcItemUpperNm = qcItemUpperNm;
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
	 * @return the registDt
	 */
	public String getRegistDt() {
		return registDt;
	}
	/**
	 * @param registDt the registDt to set
	 */
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
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
	/**
	 * @return the updtDt
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * @param updtDt the updtDt to set
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
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
	/**
	 * @return the dataCount
	 */
	public String getDataCount() {
		return dataCount;
	}
	/**
	 * @param dataCount the dataCount to set
	 */
	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}

}
