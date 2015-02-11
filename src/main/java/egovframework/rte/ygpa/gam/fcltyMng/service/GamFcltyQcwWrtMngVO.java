/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltyQcwWrtMngVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/**시설물업무구분(조회조건)*/
	private String sFcltsJobSe;
	
	/**점검진단구분(조회조건)*/
	private String sQcInspSe;
	
	/**점검관리명(조회조건)*/
	private String sQcMngNm;
	
	/**시설물관리그룹번호(조회조건)*/
	private String sFcltsMngGroupNo;
	
	/**점검관리순번(조회조건)*/
	private String sQcMngSeq;
	
	/**점검구분(조회조건)*/
	private String sQcSe;
	
	/**시행년도(조회조건)*/
	private String sEnforceYear;

	/**기계 점검항목목록 조건(조회조건)*/
	private String sMechCdStartChar;
	
	/**시설물관리그룹번호(출력용 조건)*/
	private String fcltsMngGroupNo;
	
	/**시설물업무구분(출력용 조건)*/
	private String fcltsJobSe;
	
	/**점검관리순번(출력용 조건)*/
	private String qcMngSeq;
	
	/**기계시설분류(출력용 조건)*/
	private String mechFcltsSe;

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

	/**
	 * @return the sQcInspSe
	 */
	public String getsQcInspSe() {
		return sQcInspSe;
	}

	/**
	 * @param sQcInspSe the sQcInspSe to set
	 */
	public void setsQcInspSe(String sQcInspSe) {
		this.sQcInspSe = sQcInspSe;
	}

	/**
	 * @return the sQcMngNm
	 */
	public String getsQcMngNm() {
		return sQcMngNm;
	}

	/**
	 * @param sQcMngNm the sQcMngNm to set
	 */
	public void setsQcMngNm(String sQcMngNm) {
		this.sQcMngNm = sQcMngNm;
	}

	/**
	 * @return the sFcltsMngGroupNo
	 */
	public String getsFcltsMngGroupNo() {
		return sFcltsMngGroupNo;
	}

	/**
	 * @param sFcltsMngGroupNo the sFcltsMngGroupNo to set
	 */
	public void setsFcltsMngGroupNo(String sFcltsMngGroupNo) {
		this.sFcltsMngGroupNo = sFcltsMngGroupNo;
	}

	/**
	 * @return the sQcMngSeq
	 */
	public String getsQcMngSeq() {
		return sQcMngSeq;
	}

	/**
	 * @param sQcMngSeq the sQcMngSeq to set
	 */
	public void setsQcMngSeq(String sQcMngSeq) {
		this.sQcMngSeq = sQcMngSeq;
	}

	/**
	 * @return the sQcSe
	 */
	public String getsQcSe() {
		return sQcSe;
	}

	/**
	 * @param sQcSe the sQcSe to set
	 */
	public void setsQcSe(String sQcSe) {
		this.sQcSe = sQcSe;
	}

	/**
	 * @return the sEnforceYear
	 */
	public String getsEnforceYear() {
		return sEnforceYear;
	}

	/**
	 * @param sEnforceYear the sEnforceYear to set
	 */
	public void setsEnforceYear(String sEnforceYear) {
		this.sEnforceYear = sEnforceYear;
	}

	/**
	 * @return the sMechCdStartChar
	 */
	public String getsMechCdStartChar() {
		return sMechCdStartChar;
	}

	/**
	 * @param sMechCdStartChar the sMechCdStartChar to set
	 */
	public void setsMechCdStartChar(String sMechCdStartChar) {
		this.sMechCdStartChar = sMechCdStartChar;
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
	 * @return the qcMngSeq
	 */
	public String getQcMngSeq() {
		return qcMngSeq;
	}

	/**
	 * @param qcMngSeq the qcMngSeq to set
	 */
	public void setQcMngSeq(String qcMngSeq) {
		this.qcMngSeq = qcMngSeq;
	}

	/**
	 * @return the mechFcltsSe
	 */
	public String getMechFcltsSe() {
		return mechFcltsSe;
	}

	/**
	 * @param mechFcltsSe the mechFcltsSe to set
	 */
	public void setMechFcltsSe(String mechFcltsSe) {
		this.mechFcltsSe = mechFcltsSe;
	}

}
