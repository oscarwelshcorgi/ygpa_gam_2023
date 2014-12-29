/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author Lee
 * @since 2014. 10. 27.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 27.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamMngFeeCodeMngVo extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String	mngFeeFcltyCd;		//관리비 시설 코드
	private	String	mngFeeFcltySe;		//관리비 시설 구분
	private	String	mngFeeFcltyNm;		//관리비 시설 명
	private	String	mngFeeJobSe;		//관리비 업무 구분
	private	String	regUsr;				//등록자
	private	String	registDt;			//등록일시
	private	String	updUsr;				//수정자
	private	String	updtDt;				//수정일시
	private	String	mngFeeFcltySeNm;	//관리비 시설 구분 명
	private	String	mngFeeJobSeNm;		//관리비 업무 구분 명

	private	String	sMngFeeFcltyCd;		//검색 관리비 시설 코드
	private	String	sMngFeeFcltySe;		//검색 관리비 시설 구분
	private	String	sMngFeeFcltyNm;		//검색 관리비 시설 명
	private	String	sMngFeeJobSe;		//검색 관리비 업무 구분

	/**
	 * @return the sMngFeeFcltyCd
	 */
	public String getsMngFeeFcltyCd() {
		return sMngFeeFcltyCd;
	}
	/**
	 * @param sMngFeeFcltyCd the sMngFeeFcltyCd to set
	 */
	public void setsMngFeeFcltyCd(String sMngFeeFcltyCd) {
		this.sMngFeeFcltyCd = sMngFeeFcltyCd;
	}
	/**
	 * @return the sMngFeeFcltySe
	 */
	public String getsMngFeeFcltySe() {
		return sMngFeeFcltySe;
	}
	/**
	 * @param sMngFeeFcltySe the sMngFeeFcltySe to set
	 */
	public void setsMngFeeFcltySe(String sMngFeeFcltySe) {
		this.sMngFeeFcltySe = sMngFeeFcltySe;
	}
	/**
	 * @return the sMngFeeFcltyNm
	 */
	public String getsMngFeeFcltyNm() {
		return sMngFeeFcltyNm;
	}
	/**
	 * @param sMngFeeFcltyNm the sMngFeeFcltyNm to set
	 */
	public void setsMngFeeFcltyNm(String sMngFeeFcltyNm) {
		this.sMngFeeFcltyNm = sMngFeeFcltyNm;
	}
	/**
	 * @return the sMngFeeJobSe
	 */
	public String getsMngFeeJobSe() {
		return sMngFeeJobSe;
	}
	/**
	 * @param sMngFeeJobSe the sMngFeeJobSe to set
	 */
	public void setsMngFeeJobSe(String sMngFeeJobSe) {
		this.sMngFeeJobSe = sMngFeeJobSe;
	}
	/**
	 * @return the mngFeeFcltyCd
	 */
	public String getMngFeeFcltyCd() {
		return mngFeeFcltyCd;
	}
	/**
	 * @param mngFeeFcltyCd the mngFeeFcltyCd to set
	 */
	public void setMngFeeFcltyCd(String mngFeeFcltyCd) {
		this.mngFeeFcltyCd = mngFeeFcltyCd;
	}
	/**
	 * @return the mngFeeFcltySe
	 */
	public String getMngFeeFcltySe() {
		return mngFeeFcltySe;
	}
	/**
	 * @param mngFeeFcltySe the mngFeeFcltySe to set
	 */
	public void setMngFeeFcltySe(String mngFeeFcltySe) {
		this.mngFeeFcltySe = mngFeeFcltySe;
	}
	/**
	 * @return the mngFeeFcltyNm
	 */
	public String getMngFeeFcltyNm() {
		return mngFeeFcltyNm;
	}
	/**
	 * @param mngFeeFcltyNm the mngFeeFcltyNm to set
	 */
	public void setMngFeeFcltyNm(String mngFeeFcltyNm) {
		this.mngFeeFcltyNm = mngFeeFcltyNm;
	}
	/**
	 * @return the mngFeeJobSe
	 */
	public String getMngFeeJobSe() {
		return mngFeeJobSe;
	}
	/**
	 * @param mngFeeJobSe the mngFeeJobSe to set
	 */
	public void setMngFeeJobSe(String mngFeeJobSe) {
		this.mngFeeJobSe = mngFeeJobSe;
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
	 * @return the mngFeeFcltySeNm
	 */
	public String getMngFeeFcltySeNm() {
		return mngFeeFcltySeNm;
	}
	/**
	 * @param mngFeeFcltySeNm the mngFeeFcltySeNm to set
	 */
	public void setMngFeeFcltySeNm(String mngFeeFcltySeNm) {
		this.mngFeeFcltySeNm = mngFeeFcltySeNm;
	}
	/**
	 * @return the mngFeeJobSeNm
	 */
	public String getMngFeeJobSeNm() {
		return mngFeeJobSeNm;
	}
	/**
	 * @param mngFeeJobSeNm the mngFeeJobSeNm to set
	 */
	public void setMngFeeJobSeNm(String mngFeeJobSeNm) {
		this.mngFeeJobSeNm = mngFeeJobSeNm;
	}

}
