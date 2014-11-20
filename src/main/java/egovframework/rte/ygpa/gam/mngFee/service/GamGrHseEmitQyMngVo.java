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
public class GamGrHseEmitQyMngVo  extends ComDefaultVO{

	private	String	fuelCd;	//연료 코드
	private	String	mngYear;	//관리 년도
	private	String	mngMt;	//관리 월
	private	String	usageQy;	//사용 량
	private	String	energyUsageQy;	//에너지 사용 량
	private	String	grHseEmitQy;	//온실가스 배출 량
	private	String	fuelNm;	//연료 명
	private	String	energyUnit;	//에너지 단위
	private	String	energyTotalCalVal;	//에너지 총발열량
	private	String	energyNetCalVal;	//에너지 순발열량
	private	String	grHseUnit;	//온실가스 단위
	private	String	grHseCoef;	//온실가스 계수
	private	String	regUsr;	//등록자
	private	String	registDt;	//등록일시
	private	String	updtDt;	//수정일시
	private	String	updUsr;	//수정자

	private	String	sFuelCd;	//연료 코드
	private	String	sMngYear;	//관리 년도
	private	String	sMngMt;	//관리 월

	/**
	 * @return the fuelCd
	 */
	public String getFuelCd() {
		return fuelCd;
	}
	/**
	 * @param fuelCd the fuelCd to set
	 */
	public void setFuelCd(String fuelCd) {
		this.fuelCd = fuelCd;
	}
	/**
	 * @return the mngYear
	 */
	public String getMngYear() {
		return mngYear;
	}
	/**
	 * @param mngYear the mngYear to set
	 */
	public void setMngYear(String mngYear) {
		this.mngYear = mngYear;
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
	 * @return the usageQy
	 */
	public String getUsageQy() {
		return usageQy;
	}
	/**
	 * @param usageQy the usageQy to set
	 */
	public void setUsageQy(String usageQy) {
		this.usageQy = usageQy;
	}
	/**
	 * @return the grHseEmitQy
	 */
	public String getGrHseEmitQy() {
		return grHseEmitQy;
	}
	/**
	 * @param grHseEmitQy the grHseEmitQy to set
	 */
	public void setGrHseEmitQy(String grHseEmitQy) {
		this.grHseEmitQy = grHseEmitQy;
	}
	/**
	 * @return the energyUsageQy
	 */
	public String getEnergyUsageQy() {
		return energyUsageQy;
	}
	/**
	 * @param energyUsageQy the energyUsageQy to set
	 */
	public void setEnergyUsageQy(String energyUsageQy) {
		this.energyUsageQy = energyUsageQy;
	}
	/**
	 * @return the fuelNm
	 */
	public String getFuelNm() {
		return fuelNm;
	}
	/**
	 * @param fuelNm the fuelNm to set
	 */
	public void setFuelNm(String fuelNm) {
		this.fuelNm = fuelNm;
	}
	/**
	 * @return the energyUnit
	 */
	public String getEnergyUnit() {
		return energyUnit;
	}
	/**
	 * @param energyUnit the energyUnit to set
	 */
	public void setEnergyUnit(String energyUnit) {
		this.energyUnit = energyUnit;
	}
	/**
	 * @return the energyTotalCalVal
	 */
	public String getEnergyTotalCalVal() {
		return energyTotalCalVal;
	}
	/**
	 * @param energyTotalCalVal the energyTotalCalVal to set
	 */
	public void setEnergyTotalCalVal(String energyTotalCalVal) {
		this.energyTotalCalVal = energyTotalCalVal;
	}
	/**
	 * @return the energyNetCalVal
	 */
	public String getEnergyNetCalVal() {
		return energyNetCalVal;
	}
	/**
	 * @param energyNetCalVal the energyNetCalVal to set
	 */
	public void setEnergyNetCalVal(String energyNetCalVal) {
		this.energyNetCalVal = energyNetCalVal;
	}
	/**
	 * @return the grHseUnit
	 */
	public String getGrHseUnit() {
		return grHseUnit;
	}
	/**
	 * @param grHseUnit the grHseUnit to set
	 */
	public void setGrHseUnit(String grHseUnit) {
		this.grHseUnit = grHseUnit;
	}
	/**
	 * @return the grHseCoef
	 */
	public String getGrHseCoef() {
		return grHseCoef;
	}
	/**
	 * @param grHseCoef the grHseCoef to set
	 */
	public void setGrHseCoef(String grHseCoef) {
		this.grHseCoef = grHseCoef;
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
	 * @return the sFuelCd
	 */
	public String getsFuelCd() {
		return sFuelCd;
	}
	/**
	 * @param sFuelCd the sFuelCd to set
	 */
	public void setsFuelCd(String sFuelCd) {
		this.sFuelCd = sFuelCd;
	}
	/**
	 * @return the sMngYear
	 */
	public String getsMngYear() {
		return sMngYear;
	}
	/**
	 * @param sMngYear the sMngYear to set
	 */
	public void setsMngYear(String sMngYear) {
		this.sMngYear = sMngYear;
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

}
