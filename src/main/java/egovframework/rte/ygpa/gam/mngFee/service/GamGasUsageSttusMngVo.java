/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamGasUsageSttusMngVo extends ComDefaultVO{

	private	String	usageMt;	//사용 월
	private	String	prevMtUsageQy;	//전월 사용 량
	private	String	saidMtUsageQy;	//당월 사용 량
	private	String	applcCoef;	//적용 계수
	private	String	netUsageQy;	//순 사용 량
	private	String	regUsr;	//등록자
	private	String	registDt;	//등록일시
	private	String	updUsr;	//수정자
	private	String	updtDt;	//수정일시
	private	String	mngFeeFcltyCd;	//관리비 시설 코드
	private	String	mngFeeJobSe;	//관리비 업무 구분


	private	String	sUsageMt;	//검색 사용 월
	private	String	sMngFeeFcltyCd;	//검색 관리비 시설 코드
	private	String	sMngFeeJobSe;	//검색 관리비 업무 구분



	/**
	 * @return the sUsageMt
	 */
	public String getsUsageMt() {
		return sUsageMt;
	}
	/**
	 * @param sUsageMt the sUsageMt to set
	 */
	public void setsUsageMt(String sUsageMt) {
		this.sUsageMt = sUsageMt;
	}
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
	 * @return the usageMt
	 */
	public String getUsageMt() {
		return usageMt;
	}
	/**
	 * @param usageMt the usageMt to set
	 */
	public void setUsageMt(String usageMt) {
		this.usageMt = usageMt;
	}
	/**
	 * @return the prevMtUsageQy
	 */
	public String getPrevMtUsageQy() {
		return prevMtUsageQy;
	}
	/**
	 * @param prevMtUsageQy the prevMtUsageQy to set
	 */
	public void setPrevMtUsageQy(String prevMtUsageQy) {
		this.prevMtUsageQy = prevMtUsageQy;
	}
	/**
	 * @return the saidMtUsageQy
	 */
	public String getSaidMtUsageQy() {
		return saidMtUsageQy;
	}
	/**
	 * @param saidMtUsageQy the saidMtUsageQy to set
	 */
	public void setSaidMtUsageQy(String saidMtUsageQy) {
		this.saidMtUsageQy = saidMtUsageQy;
	}
	/**
	 * @return the applcCoef
	 */
	public String getApplcCoef() {
		return applcCoef;
	}
	/**
	 * @param applcCoef the applcCoef to set
	 */
	public void setApplcCoef(String applcCoef) {
		this.applcCoef = applcCoef;
	}
	/**
	 * @return the netUsageQy
	 */
	public String getNetUsageQy() {
		return netUsageQy;
	}
	/**
	 * @param netUsageQy the netUsageQy to set
	 */
	public void setNetUsageQy(String netUsageQy) {
		this.netUsageQy = netUsageQy;
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

}

