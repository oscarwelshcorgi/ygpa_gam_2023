/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 7.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamElctyUsageQyMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String usageYr;				// 사용 년도
	private	String mngFeeJobSe;			// 관리비 업무 구분
	private	String mngFeeFcltyCd;		// 관리비 시설 코드
	private	String usageQy;				// 사용 량
	private	String usageAmt;			// 사용 금액
	private	String peekQy;				// PEEK 량
	private	String regUsr;				// 등록자
	private	String registDt;			// 등록일시
	private	String updUsr;				// 수정자
	private	String updtDt;				// 수정일시
	private	String mngFeeJobSeNm;		// 관리비 업무 구분 명
	private	String mngFeeFcltyNm;		// 관리비 시설 명
	private	String sUsageYear;			// 검색 사용 년도
	private	String sStartUsageYear;		// 검색 시작 사용 년도
	private	String sEndUsageYear;		// 검색 종료 사용 년도
	private	String sMngFeeFcltyCd;		// 검색 관리비 코드
	private	String totalCount;			// 조회 자료 수
	private	String sumUsageQy;			// 사용 량 합계
	private	String sumUsageAmt;			// 사용 금액 합계
	private	String sumPeekQy;			// PEEK 량 합계

	/**
	 * @return the usageYr
	 */
	public String getUsageYr() {
		return usageYr;
	}
	/**
	 * @param usageYr the usageYr to set
	 */
	public void setUsageYr(String usageYr) {
		this.usageYr = usageYr;
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
	 * @return the usageAmt
	 */
	public String getUsageAmt() {
		return usageAmt;
	}
	/**
	 * @param usageAmt the usageAmt to set
	 */
	public void setUsageAmt(String usageAmt) {
		this.usageAmt = usageAmt;
	}
	/**
	 * @return the peekQy
	 */
	public String getPeekQy() {
		return peekQy;
	}
	/**
	 * @param peekQy the peekQy to set
	 */
	public void setPeekQy(String peekQy) {
		this.peekQy = peekQy;
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
	 * @return the mngFeeJobSeNm
	 */
	public String getMngFeeJobSeNm() {
		return mngFeeJobSeNm;
	}
	/**
	 * @param mngFeeJobSeNm the mngFeeJobSeNm to set
	 */
	public void setMngFeeJobSeNm(String mngFeeJobSeNm) {
		this.mngFeeJobSeNm = mngFeeJobSeNm;
	}
	/**
	 * @return the mngFeeFcltyNm
	 */
	public String getMngFeeFcltyNm() {
		return mngFeeFcltyNm;
	}
	/**
	 * @param mngFeeFcltyNm the mngFeeFcltyNm to set
	 */
	public void setMngFeeFcltyNm(String mngFeeFcltyNm) {
		this.mngFeeFcltyNm = mngFeeFcltyNm;
	}
	/**
	 * @return the sUsageYear
	 */
	public String getsUsageYear() {
		return sUsageYear;
	}
	/**
	 * @param sUsageYear the sUsageYear to set
	 */
	public void setsUsageYear(String sUsageYear) {
		this.sUsageYear = sUsageYear;
	}
	/**
	 * @return the sStartUsageYear
	 */
	public String getsStartUsageYear() {
		return sStartUsageYear;
	}
	/**
	 * @param sStartUsageYear the sStartUsageYear to set
	 */
	public void setsStartUsageYear(String sStartUsageYear) {
		this.sStartUsageYear = sStartUsageYear;
	}
	/**
	 * @return the sEndUsageYear
	 */
	public String getsEndUsageYear() {
		return sEndUsageYear;
	}
	/**
	 * @param sEndUsageYear the sEndUsageYear to set
	 */
	public void setsEndUsageYear(String sEndUsageYear) {
		this.sEndUsageYear = sEndUsageYear;
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
	 * @return the sumUsageQy
	 */
	public String getSumUsageQy() {
		return sumUsageQy;
	}
	/**
	 * @param sumUsageQy the sumUsageQy to set
	 */
	public void setSumUsageQy(String sumUsageQy) {
		this.sumUsageQy = sumUsageQy;
	}
	/**
	 * @return the sumUsageAmt
	 */
	public String getSumUsageAmt() {
		return sumUsageAmt;
	}
	/**
	 * @param sumUsageAmt the sumUsageAmt to set
	 */
	public void setSumUsageAmt(String sumUsageAmt) {
		this.sumUsageAmt = sumUsageAmt;
	}
	/**
	 * @return the sumPeekQy
	 */
	public String getSumPeekQy() {
		return sumPeekQy;
	}
	/**
	 * @param sumPeekQy the sumPeekQy to set
	 */
	public void setSumPeekQy(String sumPeekQy) {
		this.sumPeekQy = sumPeekQy;
	}

}
