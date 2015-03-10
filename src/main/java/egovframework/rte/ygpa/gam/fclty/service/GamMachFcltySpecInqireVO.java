/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 10.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamMachFcltySpecInqireVO extends ComDefaultVO {

	/*조회 조건*/
	private String sFcltsMngNo;
	private String sPrtAtCode;
	private String sFcltsMngGroupNo;
	private String sPrtFcltyNm;
	private String sLoc;
	private String sFcltsClCd;
	private String sGisPrtFcltyCd;
	
	private	String totalCount;				// 조회 자료 수
	private	String sumMfcAmt;				// 제조 금액 합계
	private	String sumContrAmt;				// 도급 금액 합계
	
	private	String sttusFcltsMngGroupNm;	// 장비현황 시설물 관리 그룹 명
	private	String sttusOperCmpny;			// 장비현황 운영 회사
	private	String sttusCcCount;			// 장비현황 C/C COUNT
	private	String sttusTcCountDisplay;		// 장비현황 T/C COUNT
	private	String sttusYtCount;			// 장비현황 Y/T COUNT
	private	String sttusCsCount;			// 장비현황 C/S COUNT
	private	String sttusRsCount;			// 장비현황 R/S COUNT
	private	String sttusThCount;			// 장비현황 T/H COUNT
	private	String sttusTcRtgcCount;		// 장비현황 T/C RTGC COUNT
	/**
	 * @return the sFcltsMngNo
	 */
	public String getsFcltsMngNo() {
		return sFcltsMngNo;
	}
	/**
	 * @param sFcltsMngNo the sFcltsMngNo to set
	 */
	public void setsFcltsMngNo(String sFcltsMngNo) {
		this.sFcltsMngNo = sFcltsMngNo;
	}
	/**
	 * @return the sPrtAtCode
	 */
	public String getsPrtAtCode() {
		return sPrtAtCode;
	}
	/**
	 * @param sPrtAtCode the sPrtAtCode to set
	 */
	public void setsPrtAtCode(String sPrtAtCode) {
		this.sPrtAtCode = sPrtAtCode;
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
	 * @return the sPrtFcltyNm
	 */
	public String getsPrtFcltyNm() {
		return sPrtFcltyNm;
	}
	/**
	 * @param sPrtFcltyNm the sPrtFcltyNm to set
	 */
	public void setsPrtFcltyNm(String sPrtFcltyNm) {
		this.sPrtFcltyNm = sPrtFcltyNm;
	}
	/**
	 * @return the sLoc
	 */
	public String getsLoc() {
		return sLoc;
	}
	/**
	 * @param sLoc the sLoc to set
	 */
	public void setsLoc(String sLoc) {
		this.sLoc = sLoc;
	}
	/**
	 * @return the sFcltsClCd
	 */
	public String getsFcltsClCd() {
		return sFcltsClCd;
	}
	/**
	 * @param sFcltsClCd the sFcltsClCd to set
	 */
	public void setsFcltsClCd(String sFcltsClCd) {
		this.sFcltsClCd = sFcltsClCd;
	}
	/**
	 * @return the sGisPrtFcltyCd
	 */
	public String getsGisPrtFcltyCd() {
		return sGisPrtFcltyCd;
	}
	/**
	 * @param sGisPrtFcltyCd the sGisPrtFcltyCd to set
	 */
	public void setsGisPrtFcltyCd(String sGisPrtFcltyCd) {
		this.sGisPrtFcltyCd = sGisPrtFcltyCd;
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
	 * @return the sumMfcAmt
	 */
	public String getSumMfcAmt() {
		return sumMfcAmt;
	}
	/**
	 * @param sumMfcAmt the sumMfcAmt to set
	 */
	public void setSumMfcAmt(String sumMfcAmt) {
		this.sumMfcAmt = sumMfcAmt;
	}
	/**
	 * @return the sumContrAmt
	 */
	public String getSumContrAmt() {
		return sumContrAmt;
	}
	/**
	 * @param sumContrAmt the sumContrAmt to set
	 */
	public void setSumContrAmt(String sumContrAmt) {
		this.sumContrAmt = sumContrAmt;
	}
	/**
	 * @return the sttusFcltsMngGroupNm
	 */
	public String getSttusFcltsMngGroupNm() {
		return sttusFcltsMngGroupNm;
	}
	/**
	 * @param sttusFcltsMngGroupNm the sttusFcltsMngGroupNm to set
	 */
	public void setSttusFcltsMngGroupNm(String sttusFcltsMngGroupNm) {
		this.sttusFcltsMngGroupNm = sttusFcltsMngGroupNm;
	}
	/**
	 * @return the sttusOperCmpny
	 */
	public String getSttusOperCmpny() {
		return sttusOperCmpny;
	}
	/**
	 * @param sttusOperCmpny the sttusOperCmpny to set
	 */
	public void setSttusOperCmpny(String sttusOperCmpny) {
		this.sttusOperCmpny = sttusOperCmpny;
	}
	/**
	 * @return the sttusCcCount
	 */
	public String getSttusCcCount() {
		return sttusCcCount;
	}
	/**
	 * @param sttusCcCount the sttusCcCount to set
	 */
	public void setSttusCcCount(String sttusCcCount) {
		this.sttusCcCount = sttusCcCount;
	}
	/**
	 * @return the sttusTcCountDisplay
	 */
	public String getSttusTcCountDisplay() {
		return sttusTcCountDisplay;
	}
	/**
	 * @param sttusTcCountDisplay the sttusTcCountDisplay to set
	 */
	public void setSttusTcCountDisplay(String sttusTcCountDisplay) {
		this.sttusTcCountDisplay = sttusTcCountDisplay;
	}
	/**
	 * @return the sttusYtCount
	 */
	public String getSttusYtCount() {
		return sttusYtCount;
	}
	/**
	 * @param sttusYtCount the sttusYtCount to set
	 */
	public void setSttusYtCount(String sttusYtCount) {
		this.sttusYtCount = sttusYtCount;
	}
	/**
	 * @return the sttusCsCount
	 */
	public String getSttusCsCount() {
		return sttusCsCount;
	}
	/**
	 * @param sttusCsCount the sttusCsCount to set
	 */
	public void setSttusCsCount(String sttusCsCount) {
		this.sttusCsCount = sttusCsCount;
	}
	/**
	 * @return the sttusRsCount
	 */
	public String getSttusRsCount() {
		return sttusRsCount;
	}
	/**
	 * @param sttusRsCount the sttusRsCount to set
	 */
	public void setSttusRsCount(String sttusRsCount) {
		this.sttusRsCount = sttusRsCount;
	}
	/**
	 * @return the sttusThCount
	 */
	public String getSttusThCount() {
		return sttusThCount;
	}
	/**
	 * @param sttusThCount the sttusThCount to set
	 */
	public void setSttusThCount(String sttusThCount) {
		this.sttusThCount = sttusThCount;
	}
	/**
	 * @return the sttusTcRtgcCount
	 */
	public String getSttusTcRtgcCount() {
		return sttusTcRtgcCount;
	}
	/**
	 * @param sttusTcRtgcCount the sttusTcRtgcCount to set
	 */
	public void setSttusTcRtgcCount(String sttusTcRtgcCount) {
		this.sttusTcRtgcCount = sttusTcRtgcCount;
	}
	
	
}
