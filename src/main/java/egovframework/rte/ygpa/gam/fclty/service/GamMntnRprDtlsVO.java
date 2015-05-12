/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 5. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 5. 12.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamMntnRprDtlsVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String fcltsMngGroupNo;		// 시설물 관리 그룹 번호
	private	String fcltsJobSe;			// 시설물 업무 구분
	private	String mntnRprSeq;			// 유지 보수 순번
	private	String fcltsMngNo;			// 시설물 관리 번호
	private	String mntnRprCnstNm;		// 유지 보수 공사 명
	private	String mntnRprCnstStartDt;	// 유지 보수 공사 시작 일자
	private	String mntnRprCnstEndDt;	// 유지 보수 공사 종료 일자
	private	String mntnRprCnstAmt;		// 유지 보수 공사 금액
	private	String mntnRprSe;			// 유지 보수 구분
	private	String mntnRprSeNm;			// 유지 보수 구분 명
	private	String enforceYear;			// 시행 년도
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
	 * @return the mntnRprSeq
	 */
	public String getMntnRprSeq() {
		return mntnRprSeq;
	}
	/**
	 * @param mntnRprSeq the mntnRprSeq to set
	 */
	public void setMntnRprSeq(String mntnRprSeq) {
		this.mntnRprSeq = mntnRprSeq;
	}
	/**
	 * @return the fcltsMngNo
	 */
	public String getFcltsMngNo() {
		return fcltsMngNo;
	}
	/**
	 * @param fcltsMngNo the fcltsMngNo to set
	 */
	public void setFcltsMngNo(String fcltsMngNo) {
		this.fcltsMngNo = fcltsMngNo;
	}
	/**
	 * @return the mntnRprCnstNm
	 */
	public String getMntnRprCnstNm() {
		return mntnRprCnstNm;
	}
	/**
	 * @param mntnRprCnstNm the mntnRprCnstNm to set
	 */
	public void setMntnRprCnstNm(String mntnRprCnstNm) {
		this.mntnRprCnstNm = mntnRprCnstNm;
	}
	/**
	 * @return the mntnRprCnstStartDt
	 */
	public String getMntnRprCnstStartDt() {
		return mntnRprCnstStartDt;
	}
	/**
	 * @param mntnRprCnstStartDt the mntnRprCnstStartDt to set
	 */
	public void setMntnRprCnstStartDt(String mntnRprCnstStartDt) {
		this.mntnRprCnstStartDt = mntnRprCnstStartDt;
	}
	/**
	 * @return the mntnRprCnstEndDt
	 */
	public String getMntnRprCnstEndDt() {
		return mntnRprCnstEndDt;
	}
	/**
	 * @param mntnRprCnstEndDt the mntnRprCnstEndDt to set
	 */
	public void setMntnRprCnstEndDt(String mntnRprCnstEndDt) {
		this.mntnRprCnstEndDt = mntnRprCnstEndDt;
	}
	/**
	 * @return the mntnRprCnstAmt
	 */
	public String getMntnRprCnstAmt() {
		return mntnRprCnstAmt;
	}
	/**
	 * @param mntnRprCnstAmt the mntnRprCnstAmt to set
	 */
	public void setMntnRprCnstAmt(String mntnRprCnstAmt) {
		this.mntnRprCnstAmt = mntnRprCnstAmt;
	}
	/**
	 * @return the mntnRprSe
	 */
	public String getMntnRprSe() {
		return mntnRprSe;
	}
	/**
	 * @param mntnRprSe the mntnRprSe to set
	 */
	public void setMntnRprSe(String mntnRprSe) {
		this.mntnRprSe = mntnRprSe;
	}
	/**
	 * @return the mntnRprSeNm
	 */
	public String getMntnRprSeNm() {
		return mntnRprSeNm;
	}
	/**
	 * @param mntnRprSeNm the mntnRprSeNm to set
	 */
	public void setMntnRprSeNm(String mntnRprSeNm) {
		this.mntnRprSeNm = mntnRprSeNm;
	}
	/**
	 * @return the enforceYear
	 */
	public String getEnforceYear() {
		return enforceYear;
	}
	/**
	 * @param enforceYear the enforceYear to set
	 */
	public void setEnforceYear(String enforceYear) {
		this.enforceYear = enforceYear;
	}

}
