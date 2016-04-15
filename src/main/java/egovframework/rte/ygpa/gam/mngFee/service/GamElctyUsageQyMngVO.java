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
	private	String mt01Qy;				// 01월 사용 량
	private	String mt02Qy;				// 02월 사용 량
	private	String mt03Qy;				// 03월 사용 량
	private	String mt04Qy;				// 04월 사용 량
	private	String mt05Qy;				// 05월 사용 량
	private	String mt06Qy;				// 06월 사용 량
	private	String mt07Qy;				// 07월 사용 량
	private	String mt08Qy;				// 08월 사용 량
	private	String mt09Qy;				// 09월 사용 량
	private	String mt10Qy;				// 10월 사용 량
	private	String mt11Qy;				// 11월 사용 량
	private	String mt12Qy;				// 12월 사용 량
	private	String mt01Amt;				// 01월 전기 요금
	private	String mt02Amt;				// 02월 전기 요금
	private	String mt03Amt;				// 03월 전기 요금
	private	String mt04Amt;				// 04월 전기 요금
	private	String mt05Amt;				// 05월 전기 요금
	private	String mt06Amt;				// 06월 전기 요금
	private	String mt07Amt;				// 07월 전기 요금
	private	String mt08Amt;				// 08월 전기 요금
	private	String mt09Amt;				// 09월 전기 요금
	private	String mt10Amt;				// 10월 전기 요금
	private	String mt11Amt;				// 11월 전기 요금
	private	String mt12Amt;				// 12월 전기 요금
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
	 * @return the mt01Qy
	 */
	public String getMt01Qy() {
		return mt01Qy;
	}
	/**
	 * @param mt01Qy the mt01Qy to set
	 */
	public void setMt01Qy(String mt01Qy) {
		this.mt01Qy = mt01Qy;
	}
	/**
	 * @return the mt02Qy
	 */
	public String getMt02Qy() {
		return mt02Qy;
	}
	/**
	 * @param mt02Qy the mt02Qy to set
	 */
	public void setMt02Qy(String mt02Qy) {
		this.mt02Qy = mt02Qy;
	}
	/**
	 * @return the mt03Qy
	 */
	public String getMt03Qy() {
		return mt03Qy;
	}
	/**
	 * @param mt03Qy the mt03Qy to set
	 */
	public void setMt03Qy(String mt03Qy) {
		this.mt03Qy = mt03Qy;
	}
	/**
	 * @return the mt04Qy
	 */
	public String getMt04Qy() {
		return mt04Qy;
	}
	/**
	 * @param mt04Qy the mt04Qy to set
	 */
	public void setMt04Qy(String mt04Qy) {
		this.mt04Qy = mt04Qy;
	}
	/**
	 * @return the mt05Qy
	 */
	public String getMt05Qy() {
		return mt05Qy;
	}
	/**
	 * @param mt05Qy the mt05Qy to set
	 */
	public void setMt05Qy(String mt05Qy) {
		this.mt05Qy = mt05Qy;
	}
	/**
	 * @return the mt06Qy
	 */
	public String getMt06Qy() {
		return mt06Qy;
	}
	/**
	 * @param mt06Qy the mt06Qy to set
	 */
	public void setMt06Qy(String mt06Qy) {
		this.mt06Qy = mt06Qy;
	}
	/**
	 * @return the mt07Qy
	 */
	public String getMt07Qy() {
		return mt07Qy;
	}
	/**
	 * @param mt07Qy the mt07Qy to set
	 */
	public void setMt07Qy(String mt07Qy) {
		this.mt07Qy = mt07Qy;
	}
	/**
	 * @return the mt08Qy
	 */
	public String getMt08Qy() {
		return mt08Qy;
	}
	/**
	 * @param mt08Qy the mt08Qy to set
	 */
	public void setMt08Qy(String mt08Qy) {
		this.mt08Qy = mt08Qy;
	}
	/**
	 * @return the mt09Qy
	 */
	public String getMt09Qy() {
		return mt09Qy;
	}
	/**
	 * @param mt09Qy the mt09Qy to set
	 */
	public void setMt09Qy(String mt09Qy) {
		this.mt09Qy = mt09Qy;
	}
	/**
	 * @return the mt10Qy
	 */
	public String getMt10Qy() {
		return mt10Qy;
	}
	/**
	 * @param mt10Qy the mt10Qy to set
	 */
	public void setMt10Qy(String mt10Qy) {
		this.mt10Qy = mt10Qy;
	}
	/**
	 * @return the mt11Qy
	 */
	public String getMt11Qy() {
		return mt11Qy;
	}
	/**
	 * @param mt11Qy the mt11Qy to set
	 */
	public void setMt11Qy(String mt11Qy) {
		this.mt11Qy = mt11Qy;
	}
	/**
	 * @return the mt12Qy
	 */
	public String getMt12Qy() {
		return mt12Qy;
	}
	/**
	 * @param mt12Qy the mt12Qy to set
	 */
	public void setMt12Qy(String mt12Qy) {
		this.mt12Qy = mt12Qy;
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
	/**
	 * @return the mt01Amt
	 */
	public String getMt01Amt() {
		return mt01Amt;
	}
	/**
	 * @param mt01Amt the mt01Amt to set
	 */
	public void setMt01Amt(String mt01Amt) {
		this.mt01Amt = mt01Amt;
	}
	/**
	 * @return the mt02Amt
	 */
	public String getMt02Amt() {
		return mt02Amt;
	}
	/**
	 * @param mt02Amt the mt02Amt to set
	 */
	public void setMt02Amt(String mt02Amt) {
		this.mt02Amt = mt02Amt;
	}
	/**
	 * @return the mt03Amt
	 */
	public String getMt03Amt() {
		return mt03Amt;
	}
	/**
	 * @param mt03Amt the mt03Amt to set
	 */
	public void setMt03Amt(String mt03Amt) {
		this.mt03Amt = mt03Amt;
	}
	/**
	 * @return the mt04Amt
	 */
	public String getMt04Amt() {
		return mt04Amt;
	}
	/**
	 * @param mt04Amt the mt04Amt to set
	 */
	public void setMt04Amt(String mt04Amt) {
		this.mt04Amt = mt04Amt;
	}
	/**
	 * @return the mt05Amt
	 */
	public String getMt05Amt() {
		return mt05Amt;
	}
	/**
	 * @param mt05Amt the mt05Amt to set
	 */
	public void setMt05Amt(String mt05Amt) {
		this.mt05Amt = mt05Amt;
	}
	/**
	 * @return the mt06Amt
	 */
	public String getMt06Amt() {
		return mt06Amt;
	}
	/**
	 * @param mt06Amt the mt06Amt to set
	 */
	public void setMt06Amt(String mt06Amt) {
		this.mt06Amt = mt06Amt;
	}
	/**
	 * @return the mt07Amt
	 */
	public String getMt07Amt() {
		return mt07Amt;
	}
	/**
	 * @param mt07Amt the mt07Amt to set
	 */
	public void setMt07Amt(String mt07Amt) {
		this.mt07Amt = mt07Amt;
	}
	/**
	 * @return the mt08Amt
	 */
	public String getMt08Amt() {
		return mt08Amt;
	}
	/**
	 * @param mt08Amt the mt08Amt to set
	 */
	public void setMt08Amt(String mt08Amt) {
		this.mt08Amt = mt08Amt;
	}
	/**
	 * @return the mt09Amt
	 */
	public String getMt09Amt() {
		return mt09Amt;
	}
	/**
	 * @param mt09Amt the mt09Amt to set
	 */
	public void setMt09Amt(String mt09Amt) {
		this.mt09Amt = mt09Amt;
	}
	/**
	 * @return the mt10Amt
	 */
	public String getMt10Amt() {
		return mt10Amt;
	}
	/**
	 * @param mt10Amt the mt10Amt to set
	 */
	public void setMt10Amt(String mt10Amt) {
		this.mt10Amt = mt10Amt;
	}
	/**
	 * @return the mt11Amt
	 */
	public String getMt11Amt() {
		return mt11Amt;
	}
	/**
	 * @param mt11Amt the mt11Amt to set
	 */
	public void setMt11Amt(String mt11Amt) {
		this.mt11Amt = mt11Amt;
	}
	/**
	 * @return the mt12Amt
	 */
	public String getMt12Amt() {
		return mt12Amt;
	}
	/**
	 * @param mt12Amt the mt12Amt to set
	 */
	public void setMt12Amt(String mt12Amt) {
		this.mt12Amt = mt12Amt;
	}

}
