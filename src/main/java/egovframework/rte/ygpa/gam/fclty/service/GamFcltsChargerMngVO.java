/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 5.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltsChargerMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String chargerNm;			// 담당자 명
	private	String fcltsJobSe;			// 시설물 업무 구분
	private	String chargerDisplayNm;	// 담당자 표시 명
	private	String chargerOfcPos;		// 담당자 직위
	private	String chargerDept;			// 담당자 부서
	private	String signFileNmLogic;		// 직인 파일 명 논리
	private	String signFileNmPhysicl;	// 직인 파일 명 물리
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String fcltsJobSeNm;		// 시설물 업무 구분 명
	private	String totalCount;				// 조회 자료 수
	private	String sChargerNm;			// 검색 담당자 명
	private	String sFcltsJobSe;			// 검색 시설물 업무 구분

	/**
	 * @return the chargerNm
	 */
	public String getChargerNm() {
		return chargerNm;
	}
	/**
	 * @param chargerNm the chargerNm to set
	 */
	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
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
	 * @return the chargerDisplayNm
	 */
	public String getChargerDisplayNm() {
		return chargerDisplayNm;
	}
	/**
	 * @param chargerDisplayNm the chargerDisplayNm to set
	 */
	public void setChargerDisplayNm(String chargerDisplayNm) {
		this.chargerDisplayNm = chargerDisplayNm;
	}
	/**
	 * @return the chargerOfcPos
	 */
	public String getChargerOfcPos() {
		return chargerOfcPos;
	}
	/**
	 * @param chargerOfcPos the chargerOfcPos to set
	 */
	public void setChargerOfcPos(String chargerOfcPos) {
		this.chargerOfcPos = chargerOfcPos;
	}
	/**
	 * @return the chargerDept
	 */
	public String getChargerDept() {
		return chargerDept;
	}
	/**
	 * @param chargerDept the chargerDept to set
	 */
	public void setChargerDept(String chargerDept) {
		this.chargerDept = chargerDept;
	}
	/**
	 * @return the signFileNmLogic
	 */
	public String getSignFileNmLogic() {
		return signFileNmLogic;
	}
	/**
	 * @param signFileNmLogic the signFileNmLogic to set
	 */
	public void setSignFileNmLogic(String signFileNmLogic) {
		this.signFileNmLogic = signFileNmLogic;
	}
	/**
	 * @return the signFileNmPhysicl
	 */
	public String getSignFileNmPhysicl() {
		return signFileNmPhysicl;
	}
	/**
	 * @param signFileNmPhysicl the signFileNmPhysicl to set
	 */
	public void setSignFileNmPhysicl(String signFileNmPhysicl) {
		this.signFileNmPhysicl = signFileNmPhysicl;
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
	 * @return the totalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the sChargerNm
	 */
	public String getsChargerNm() {
		return sChargerNm;
	}
	/**
	 * @param sChargerNm the sChargerNm to set
	 */
	public void setsChargerNm(String sChargerNm) {
		this.sChargerNm = sChargerNm;
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

}
