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
public class GamMngFeeGubunMngVo extends ComDefaultVO{

	private	String	mngFeeFcltySe;		//관리비 시설 구분
	private	String	mngFeeFcltySeNm;	//관리비 시설 구분 명
	private	String	regUsr;				//등록자
	private	String	registDt;			//등록일시
	private	String	updUsr;					//수정자
	private	String	updtDt;				//수정일시
	private	String	sMngFeeFcltySe;		// 검색 관리비 시설 구분
	private	String	sMngFeeFcltySeNm;	// 검색 관리비 시설 구분 명
	private String  oldMngFeeFcltySe;	//시설 구분 Old


	/**
	 * @return the oldMngFeeFcltySe
	 */
	public String getOldMngFeeFcltySe() {
		return oldMngFeeFcltySe;
	}
	/**
	 * @param oldMngFeeFcltySe the oldMngFeeFcltySe to set
	 */
	public void setOldMngFeeFcltySe(String oldMngFeeFcltySe) {
		this.oldMngFeeFcltySe = oldMngFeeFcltySe;
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
	 * @return the sMngFeeFcltySeNm
	 */
	public String getsMngFeeFcltySeNm() {
		return sMngFeeFcltySeNm;
	}
	/**
	 * @param sMngFeeFcltySeNm the sMngFeeFcltySeNm to set
	 */
	public void setsMngFeeFcltySeNm(String sMngFeeFcltySeNm) {
		this.sMngFeeFcltySeNm = sMngFeeFcltySeNm;
	}


}

