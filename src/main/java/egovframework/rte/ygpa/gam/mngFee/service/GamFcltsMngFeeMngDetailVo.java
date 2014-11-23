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
public class GamFcltsMngFeeMngDetailVo extends ComDefaultVO{

	private	String	mngMt;         //관리 월
	private	String	mngFeeJobSe;   //관리비 업무 구분
	private	String	entrpscd;      //업체코드
	private	String	mngFee;        //관리비
	private	String	elctyFee;      //전기 요금
	private	String	waterFee;      //상하수도 요금
	private	String	gasFee;        //도시가스 요금
	private	String	mngTotalFee;   //관리비 합계
	private	String	regUsr;        //등록자
	private	String	updUsr;        //수정자
	private	String	updtDt;        //수정일시
	private	String	registDt;      //등록일시
	private	String	usageAr;       //사용 면적
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
	 * @return the entrpscd
	 */
	public String getEntrpscd() {
		return entrpscd;
	}
	/**
	 * @param entrpscd the entrpscd to set
	 */
	public void setEntrpscd(String entrpscd) {
		this.entrpscd = entrpscd;
	}
	/**
	 * @return the mngFee
	 */
	public String getMngFee() {
		return mngFee;
	}
	/**
	 * @param mngFee the mngFee to set
	 */
	public void setMngFee(String mngFee) {
		this.mngFee = mngFee;
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
	 * @return the usageAr
	 */
	public String getUsageAr() {
		return usageAr;
	}
	/**
	 * @param usageAr the usageAr to set
	 */
	public void setUsageAr(String usageAr) {
		this.usageAr = usageAr;
	}















}
