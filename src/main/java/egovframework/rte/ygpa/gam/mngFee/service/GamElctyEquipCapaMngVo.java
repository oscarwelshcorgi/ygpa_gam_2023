/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamElctyEquipCapaMngVo extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String	mngYear;			//관리 년도
	private	String	fcltsMngGroupNo;	//시설물 관리 그룹 번호
	private	String	mngSeq;				//관리 순번
	private	String	elctyEquipNm;		//전기 시설 명
	private	String	elctySe;			//전기 구분
	private	String	elctySeNm;			//전기 구분 명
	private	String	equipCapa;			//설비 용량
	private	String	ctrtCapa;			//계약 용량
	private	String	usageVolt;			//사용 전압
	private	String	regUsr;				//등록자
	private	String	registDt;			//등록일시
	private	String	updUsr;				//수정자
	private	String	updtDt;				//수정일시

	private String	dataCount;			//자료수
	private	String	sumEquipCapa;		//총 설비 용량
	private	String	sumCtrtCapa;		//총 계약 용량

	private	String	sMngYear;			//검색 관리 년도
	private	String	sElctySe;			//검색 전기 구분
	private	String	sElctyEquipNm;		//검색 전기 시설 명

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
	 * @return the mngSeq
	 */
	public String getMngSeq() {
		return mngSeq;
	}
	/**
	 * @param mngSeq the mngSeq to set
	 */
	public void setMngSeq(String mngSeq) {
		this.mngSeq = mngSeq;
	}
	/**
	 * @return the elctyEquipNm
	 */
	public String getElctyEquipNm() {
		return elctyEquipNm;
	}
	/**
	 * @param elctyEquipNm the elctyEquipNm to set
	 */
	public void setElctyEquipNm(String elctyEquipNm) {
		this.elctyEquipNm = elctyEquipNm;
	}
	/**
	 * @return the elctySe
	 */
	public String getElctySe() {
		return elctySe;
	}
	/**
	 * @param elctySe the elctySe to set
	 */
	public void setElctySe(String elctySe) {
		this.elctySe = elctySe;
	}
	/**
	 * @return the elctySeNm
	 */
	public String getElctySeNm() {
		return elctySeNm;
	}
	/**
	 * @param elctySeNm the elctySeNm to set
	 */
	public void setElctySeNm(String elctySeNm) {
		this.elctySeNm = elctySeNm;
	}
	/**
	 * @return the equipCapa
	 */
	public String getEquipCapa() {
		return equipCapa;
	}
	/**
	 * @param equipCapa the equipCapa to set
	 */
	public void setEquipCapa(String equipCapa) {
		this.equipCapa = equipCapa;
	}
	/**
	 * @return the ctrtCapa
	 */
	public String getCtrtCapa() {
		return ctrtCapa;
	}
	/**
	 * @param ctrtCapa the ctrtCapa to set
	 */
	public void setCtrtCapa(String ctrtCapa) {
		this.ctrtCapa = ctrtCapa;
	}
	/**
	 * @return the usageVolt
	 */
	public String getUsageVolt() {
		return usageVolt;
	}
	/**
	 * @param usageVolt the usageVolt to set
	 */
	public void setUsageVolt(String usageVolt) {
		this.usageVolt = usageVolt;
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
	/**
	 * @return the sumEquipCapa
	 */
	public String getSumEquipCapa() {
		return sumEquipCapa;
	}
	/**
	 * @param sumEquipCapa the sumEquipCapa to set
	 */
	public void setSumEquipCapa(String sumEquipCapa) {
		this.sumEquipCapa = sumEquipCapa;
	}
	/**
	 * @return the sumCtrtCapa
	 */
	public String getSumCtrtCapa() {
		return sumCtrtCapa;
	}
	/**
	 * @param sumCtrtCapa the sumCtrtCapa to set
	 */
	public void setSumCtrtCapa(String sumCtrtCapa) {
		this.sumCtrtCapa = sumCtrtCapa;
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
	 * @return the sElctySe
	 */
	public String getsElctySe() {
		return sElctySe;
	}
	/**
	 * @param sElctySe the sElctySe to set
	 */
	public void setsElctySe(String sElctySe) {
		this.sElctySe = sElctySe;
	}
	/**
	 * @return the sElctyEquipNm
	 */
	public String getsElctyEquipNm() {
		return sElctyEquipNm;
	}
	/**
	 * @param sElctyEquipNm the sElctyEquipNm to set
	 */
	public void setsElctyEquipNm(String sElctyEquipNm) {
		this.sElctyEquipNm = sElctyEquipNm;
	}

}
