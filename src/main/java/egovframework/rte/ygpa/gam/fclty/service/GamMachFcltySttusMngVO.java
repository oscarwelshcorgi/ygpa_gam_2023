/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 20.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 20.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamMachFcltySttusMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String wharfNm;				// 부두 명
	private	String operCmpny;			// 운영 회사
	private	String ccCount;				// C/C 갯수
	private	String tcCount;				// T/C 갯수
	private	String ytCount;				// Y/T 갯수
	private	String csCount;				// 샷시 갯수
	private	String rsCount;				// REACH 갯수
	private	String thCount;				// TOP HANDLER 갯수
	private	String tcRtgcCount;			// T/C RTGC 갯수
	private	String tcRm;				// T/C 비고
	private	String fcltsMngGroupNo;		// 시설물 관리 그룹 번호
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String processType;			// PROCESS TYPE

	/**
	 * @return the wharfNm
	 */
	public String getWharfNm() {
		return wharfNm;
	}
	/**
	 * @param wharfNm the wharfNm to set
	 */
	public void setWharfNm(String wharfNm) {
		this.wharfNm = wharfNm;
	}
	/**
	 * @return the operCmpny
	 */
	public String getOperCmpny() {
		return operCmpny;
	}
	/**
	 * @param operCmpny the operCmpny to set
	 */
	public void setOperCmpny(String operCmpny) {
		this.operCmpny = operCmpny;
	}
	/**
	 * @return the ccCount
	 */
	public String getCcCount() {
		return ccCount;
	}
	/**
	 * @param ccCount the ccCount to set
	 */
	public void setCcCount(String ccCount) {
		this.ccCount = ccCount;
	}
	/**
	 * @return the tcCount
	 */
	public String getTcCount() {
		return tcCount;
	}
	/**
	 * @param tcCount the tcCount to set
	 */
	public void setTcCount(String tcCount) {
		this.tcCount = tcCount;
	}
	/**
	 * @return the ytCount
	 */
	public String getYtCount() {
		return ytCount;
	}
	/**
	 * @param ytCount the ytCount to set
	 */
	public void setYtCount(String ytCount) {
		this.ytCount = ytCount;
	}
	/**
	 * @return the csCount
	 */
	public String getCsCount() {
		return csCount;
	}
	/**
	 * @param csCount the csCount to set
	 */
	public void setCsCount(String csCount) {
		this.csCount = csCount;
	}
	/**
	 * @return the rsCount
	 */
	public String getRsCount() {
		return rsCount;
	}
	/**
	 * @param rsCount the rsCount to set
	 */
	public void setRsCount(String rsCount) {
		this.rsCount = rsCount;
	}
	/**
	 * @return the thCount
	 */
	public String getThCount() {
		return thCount;
	}
	/**
	 * @param thCount the thCount to set
	 */
	public void setThCount(String thCount) {
		this.thCount = thCount;
	}
	/**
	 * @return the tcRtgcCount
	 */
	public String getTcRtgcCount() {
		return tcRtgcCount;
	}
	/**
	 * @param tcRtgcCount the tcRtgcCount to set
	 */
	public void setTcRtgcCount(String tcRtgcCount) {
		this.tcRtgcCount = tcRtgcCount;
	}
	/**
	 * @return the tcRm
	 */
	public String getTcRm() {
		return tcRm;
	}
	/**
	 * @param tcRm the tcRm to set
	 */
	public void setTcRm(String tcRm) {
		this.tcRm = tcRm;
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
	 * @return the processType
	 */
	public String getProcessType() {
		return processType;
	}
	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}

}
