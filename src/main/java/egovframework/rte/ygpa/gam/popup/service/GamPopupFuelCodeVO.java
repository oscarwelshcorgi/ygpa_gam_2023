/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 11. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 28.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamPopupFuelCodeVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;

	private	String	mngYear;	//관리 년도
	private	String	fuelCd;		//연료 코드
	private	String	fuelNm;		//연료 명
	private	String	energyUnit;	//에너지 단위
	private	String	energyTotalCalVal;	//에너지 총발열량
	private	String	energyNetCalVal;	//에너지 순발열량
	private	String	grHseUnit;	//온실가스 단위
	private	String	grHseCoef;	//온실가스 계수

	private	String	sMngYear;	//검색 관리 년도
	private	String	sFuelCd;	//검색 연료 코드
	private	String	sFuelNm;	//검색 연료 명

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
	 * @return the sFuelNm
	 */
	public String getsFuelNm() {
		return sFuelNm;
	}
	/**
	 * @param sFuelNm the sFuelNm to set
	 */
	public void setsFuelNm(String sFuelNm) {
		this.sFuelNm = sFuelNm;
	}

}
