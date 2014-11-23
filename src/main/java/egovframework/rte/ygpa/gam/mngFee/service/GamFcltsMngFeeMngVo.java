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
public class GamFcltsMngFeeMngVo extends ComDefaultVO{

	private	String	mngMt;				//관리 월
	private	String	mngFeeJobSe;		//관리비 업무 구분
	private	String	mngFeeSj;			//관리비 제목
	private	String	fcltyMngFee;		//시설 관리 용역비
	private	String	elctyFee;			//전기 요금
	private	String	waterFee;			//상하수도 요금
	private	String	gasFee;				//도시가스 요금
	private	String	envFee;				//환경개선 부담금
	private	String	mngTotalFee;		//관리비 합계
	private	String	regUsr;				//등록자
	private	String	registDt;			//등록일시
	private	String	updUsr;				//수정자
	private	String	updtDt;				//수정일시

	private String  sMngMtYear;			// 검색 년
	private String  sMngMtMon;			// 검색 월
	private	String	sMngMt;				//검색 관리 월
	private	String	sMngFeeJobSe;		//검색 관리비 업무 구분

	private String orgnztId;			// 로그인





	/**
	 * @return the orgnztId
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * @param orgnztId the orgnztId to set
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}
	/**
	 * @return the sMngMtYear
	 */
	public String getsMngMtYear() {
		return sMngMtYear;
	}
	/**
	 * @param sMngMtYear the sMngMtYear to set
	 */
	public void setsMngMtYear(String sMngMtYear) {
		this.sMngMtYear = sMngMtYear;
	}
	/**
	 * @return the sMngMtMon
	 */
	public String getsMngMtMon() {
		return sMngMtMon;
	}
	/**
	 * @param sMngMtMon the sMngMtMon to set
	 */
	public void setsMngMtMon(String sMngMtMon) {
		this.sMngMtMon = sMngMtMon;
	}
	/**
	 * @return the mngMt
	 */
	public String getMngMt() {
		return mngMt;
	}
	/**
	 * @param mngMt the mngMt to set
	 */
	public void setMngMt(String mngMt) {
		this.mngMt = mngMt;
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
	 * @return the mngFeeSj
	 */
	public String getMngFeeSj() {
		return mngFeeSj;
	}
	/**
	 * @param mngFeeSj the mngFeeSj to set
	 */
	public void setMngFeeSj(String mngFeeSj) {
		this.mngFeeSj = mngFeeSj;
	}
	/**
	 * @return the fcltyMngFee
	 */
	public String getFcltyMngFee() {
		return fcltyMngFee;
	}
	/**
	 * @param fcltyMngFee the fcltyMngFee to set
	 */
	public void setFcltyMngFee(String fcltyMngFee) {
		this.fcltyMngFee = fcltyMngFee;
	}
	/**
	 * @return the elctyFee
	 */
	public String getElctyFee() {
		return elctyFee;
	}
	/**
	 * @param elctyFee the elctyFee to set
	 */
	public void setElctyFee(String elctyFee) {
		this.elctyFee = elctyFee;
	}
	/**
	 * @return the waterFee
	 */
	public String getWaterFee() {
		return waterFee;
	}
	/**
	 * @param waterFee the waterFee to set
	 */
	public void setWaterFee(String waterFee) {
		this.waterFee = waterFee;
	}
	/**
	 * @return the gasFee
	 */
	public String getGasFee() {
		return gasFee;
	}
	/**
	 * @param gasFee the gasFee to set
	 */
	public void setGasFee(String gasFee) {
		this.gasFee = gasFee;
	}
	/**
	 * @return the envFee
	 */
	public String getEnvFee() {
		return envFee;
	}
	/**
	 * @param envFee the envFee to set
	 */
	public void setEnvFee(String envFee) {
		this.envFee = envFee;
	}
	/**
	 * @return the mngTotalFee
	 */
	public String getMngTotalFee() {
		return mngTotalFee;
	}
	/**
	 * @param mngTotalFee the mngTotalFee to set
	 */
	public void setMngTotalFee(String mngTotalFee) {
		this.mngTotalFee = mngTotalFee;
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
	 * @return the sMngMt
	 */
	public String getsMngMt() {
		return sMngMt;
	}
	/**
	 * @param sMngMt the sMngMt to set
	 */
	public void setsMngMt(String sMngMt) {
		this.sMngMt = sMngMt;
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

}
