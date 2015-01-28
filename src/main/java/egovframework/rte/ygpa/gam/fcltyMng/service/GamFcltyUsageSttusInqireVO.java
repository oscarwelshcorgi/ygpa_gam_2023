/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author jckim
 * @since 2014. 12. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 8.		jckim		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GamFcltyUsageSttusInqireVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 항코드 (조회조건) **/
	private String sPrtAtCode;

	/** 시설구분(조회조건) **/
	private String sFcltsJobSe;

	/** 시설명(조회조건) **/
	private String sPrtFcltyNm;

	/** 시설물 관리번호(조회조건) **/
	private String sFcltsMngNo;

	/** 사용기간 (조회조건) **/
	private String sUsagePdFrom;
	private String sUsagePdTo;

// ==== 자산 임대 ==== //
	/** GIS 자산 항코드 **/
	private String gisAssetsPrtAtCode;
	/** GIS 자산 코드**/
	private String gisAssetsCd;
	/** GIS 자산 서브 코드**/
	private String gisAssetsSubCd;

	/** 시설물 관리 번호 **/
	private String fcltsMngNo;
	/** 시설물 관리 그룹 **/
	private String fcltsMngGroupNo;


	// ==== 점검 관리 내역 ==== //
	/** 시설물 업무 구분 **/
	private String fcltsJobSe;
	/** 시설물 관리 순번 **/
	private int qcMngSeq;

	// ==== 점검 관리 결과 항목 ==== //
	/** 점검 항목 코드 **/
	private String qcItemCd;
	/** 순번 **/
	private int seq;
	/** 점검 결과 구분 **/
	private String inspResultChk;
	/** 점검 결과 내용 **/
	private String inspResultCn;

	// ==== 유지 보수 내역 ==== //
	private String mntnRprSeq;

	// ==== 하자 보수 내역 ==== //
	private int flawRprSeq;

	/**
	 * @return the sPrtAtCode
	 */
	public String getsPrtAtCode() {
		return sPrtAtCode;
	}

	/**
	 * @param sPrtAtCode the sPrtAtCode to set
	 */
	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
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
	 * @return the sUsagePdFrom
	 */
	public String getsUsagePdFrom() {
		return sUsagePdFrom;
	}

	/**
	 * @param sUsagePdFrom the sUsagePdFrom to set
	 */
	public void setsUsagePdFrom(String sUsagePdFrom) {
		this.sUsagePdFrom = sUsagePdFrom;
	}

	/**
	 * @return the sUsagePdTo
	 */
	public String getsUsagePdTo() {
		return sUsagePdTo;
	}

	/**
	 * @param sUsagePdTo the sUsagePdTo to set
	 */
	public void setsUsagePdTo(String sUsagePdTo) {
		this.sUsagePdTo = sUsagePdTo;
	}

	/**
	 * @return the gisAssetsPrtAtCode
	 */
	public String getGisAssetsPrtAtCode() {
		return gisAssetsPrtAtCode;
	}

	/**
	 * @param gisAssetsPrtAtCode the gisAssetsPrtAtCode to set
	 */
	public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
		this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
	}

	/**
	 * @return the gisAssetsCd
	 */
	public String getGisAssetsCd() {
		return gisAssetsCd;
	}

	/**
	 * @param gisAssetsCd the gisAssetsCd to set
	 */
	public void setGisAssetsCd(String gisAssetsCd) {
		this.gisAssetsCd = gisAssetsCd;
	}

	/**
	 * @return the gisAssetsSubCd
	 */
	public String getGisAssetsSubCd() {
		return gisAssetsSubCd;
	}

	/**
	 * @param gisAssetsSubCd the gisAssetsSubCd to set
	 */
	public void setGisAssetsSubCd(String gisAssetsSubCd) {
		this.gisAssetsSubCd = gisAssetsSubCd;
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
	 * @return the qcMngSeq
	 */
	public int getQcMngSeq() {
		return qcMngSeq;
	}

	/**
	 * @param qcMngSeq the qcMngSeq to set
	 */
	public void setQcMngSeq(int qcMngSeq) {
		this.qcMngSeq = qcMngSeq;
	}

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
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * @return the inspResultChk
	 */
	public String getInspResultChk() {
		return inspResultChk;
	}

	/**
	 * @param inspResultChk the inspResultChk to set
	 */
	public void setInspResultChk(String inspResultChk) {
		this.inspResultChk = inspResultChk;
	}

	/**
	 * @return the inspResultCn
	 */
	public String getInspResultCn() {
		return inspResultCn;
	}

	/**
	 * @param inspResultCn the inspResultCn to set
	 */
	public void setInspResultCn(String inspResultCn) {
		this.inspResultCn = inspResultCn;
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
	 * @return the flawRprSeq
	 */
	public int getFlawRprSeq() {
		return flawRprSeq;
	}

	/**
	 * @param flawRprSeq the flawRprSeq to set
	 */
	public void setFlawRprSeq(int flawRprSeq) {
		this.flawRprSeq = flawRprSeq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}