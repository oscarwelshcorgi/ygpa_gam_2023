/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 23.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamSocExmpMngtVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/** 청코드(조회조건)  **/
	private String sAppPrtAtCode;

	/** 요금종류코드(조회조건)  **/
	private String sFeeTp;
	
	/** 회계년도(조회조건)  **/
	private String sFiscalYr;
	
	/** 관리번호(조회조건)  **/
	private String sSocNo;
	
	/** 면제요청청코드 **/
	private String appPrtAtCode;

	/** 면제요청청명  **/
	private String appPrtAtKorNm;
	
	/** 면제요청요금종류 **/
	private String feeTp;
	
	/** 회계년도 **/
	private String fiscalYr;
	
	/** 일련번호 **/
	private String socNo;
	
	/** 면제요청업체코드 **/
	private String appAgentCode;
	
	/** 면제요청업체명 **/
	private String appAgentName;
	
	/** 면제요청신헝횟수 **/ 
	private String useNo;
	
	/** 공사(허가원부)관리청코드  **/
	private String prtAtCode;

	/** 공사(허가원부)관리청명  **/
	private String prtAtKorNm;
	
	/** 공사(허가원부)준공년도  **/
	private String cmplYr;
	
	/** 공사(허가원부)일련번호  **/
	private String constNo;
	
	/** 업체코드(실제면제받은)  **/
	private String exmpAgentCode;
	
	/** 업체명(실제면제받은)  **/
	private String exmpAgentName;

	/** 선박 고유 키 **/
	private String vsslKey;
	
	/** 입항년도 **/
	private String yr;
	
	/** 입항횟수 **/
	private String serNo;
	
	/** 고지번호 **/
	private String billNo;
	
	/** 면제금액 **/
	private String exmpAmnt;
	
	/** 입출구분(외내항 구분) **/
	private String inOut;
	
	/** 입출구분명 **/
	private String inOutName;
	
	/** 징수의뢰사용횟수 **/
	private String billUseNo;
	
	/** 시설코드 **/
	private String facCode;
	
	/** 시설부코드 **/
	private String facSubCode;
	
	/** 시설명 **/
	private String facKorNm;
	
	/** 입출항일자 **/
	private String ioDt;
	
	/** 할인사유코드 **/
	private String dcCode;
	
	/** 할인율코드 **/
	private String dcRate;
	
	/** 고지일자 **/
	private String billDt;
	
	/** 기본요금 **/
	private String standardFee;
	
	/** 징수톤 **/
	private String realTn;
	
	/** 비고 **/
	private String remark;
	
	/** 면제유형 : 1.당해시설  2.타인사용료  3.다른시설 **/
	private String exmpType;
	
	/** 수정인 **/
	private String updtUid;
	
	/** 수정일시 **/
	private String updtDate;
	
	/** 호출부호 **/
	private String callLetter;

	/** 호출부호명  **/
	private String callLetterNm;
	
	/** 할인율명 **/
	private String dcRateNm;
	
	/** 실제 면제 받은 청코드 **/
	private String exmpPrtAtCode;
	
	/** 실제 면제 받은 청명 **/
	private String exmpPrtAtKorNm;
	
	/** 징수자 : MAP.해양항만청  PAT.항만공사  LGO.지자체 **/
	private String jingsuja;

	/**
	 * @return the sAppPrtAtCode
	 */
	public String getsAppPrtAtCode() {
		return sAppPrtAtCode;
	}

	/**
	 * @param sAppPrtAtCode the sAppPrtAtCode to set
	 */
	public void setsAppPrtAtCode(String sAppPrtAtCode) {
		this.sAppPrtAtCode = sAppPrtAtCode;
	}

	/**
	 * @return the sFeeTp
	 */
	public String getsFeeTp() {
		return sFeeTp;
	}

	/**
	 * @param sFeeTp the sFeeTp to set
	 */
	public void setsFeeTp(String sFeeTp) {
		this.sFeeTp = sFeeTp;
	}

	/**
	 * @return the sFiscalYr
	 */
	public String getsFiscalYr() {
		return sFiscalYr;
	}

	/**
	 * @param sFiscalYr the sFiscalYr to set
	 */
	public void setsFiscalYr(String sFiscalYr) {
		this.sFiscalYr = sFiscalYr;
	}

	/**
	 * @return the sSocNo
	 */
	public String getsSocNo() {
		return sSocNo;
	}

	/**
	 * @param sSocNo the sSocNo to set
	 */
	public void setsSocNo(String sSocNo) {
		this.sSocNo = sSocNo;
	}

	/**
	 * @return the appPrtAtCode
	 */
	public String getAppPrtAtCode() {
		return appPrtAtCode;
	}

	/**
	 * @param appPrtAtCode the appPrtAtCode to set
	 */
	public void setAppPrtAtCode(String appPrtAtCode) {
		this.appPrtAtCode = appPrtAtCode;
	}

	/**
	 * @return the appPrtAtKorNm
	 */
	public String getAppPrtAtKorNm() {
		return appPrtAtKorNm;
	}

	/**
	 * @param appPrtAtKorNm the appPrtAtKorNm to set
	 */
	public void setAppPrtAtKorNm(String appPrtAtKorNm) {
		this.appPrtAtKorNm = appPrtAtKorNm;
	}

	/**
	 * @return the feeTp
	 */
	public String getFeeTp() {
		return feeTp;
	}

	/**
	 * @param feeTp the feeTp to set
	 */
	public void setFeeTp(String feeTp) {
		this.feeTp = feeTp;
	}

	/**
	 * @return the fiscalYr
	 */
	public String getFiscalYr() {
		return fiscalYr;
	}

	/**
	 * @param fiscalYr the fiscalYr to set
	 */
	public void setFiscalYr(String fiscalYr) {
		this.fiscalYr = fiscalYr;
	}

	/**
	 * @return the socNo
	 */
	public String getSocNo() {
		return socNo;
	}

	/**
	 * @param socNo the socNo to set
	 */
	public void setSocNo(String socNo) {
		this.socNo = socNo;
	}

	/**
	 * @return the appAgentCode
	 */
	public String getAppAgentCode() {
		return appAgentCode;
	}

	/**
	 * @param appAgentCode the appAgentCode to set
	 */
	public void setAppAgentCode(String appAgentCode) {
		this.appAgentCode = appAgentCode;
	}

	/**
	 * @return the appAgentName
	 */
	public String getAppAgentName() {
		return appAgentName;
	}

	/**
	 * @param appAgentName the appAgentName to set
	 */
	public void setAppAgentName(String appAgentName) {
		this.appAgentName = appAgentName;
	}

	/**
	 * @return the useNo
	 */
	public String getUseNo() {
		return useNo;
	}

	/**
	 * @param useNo the useNo to set
	 */
	public void setUseNo(String useNo) {
		this.useNo = useNo;
	}

	/**
	 * @return the prtAtCode
	 */
	public String getPrtAtCode() {
		return prtAtCode;
	}

	/**
	 * @param prtAtCode the prtAtCode to set
	 */
	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}

	/**
	 * @return the prtAtKorNm
	 */
	public String getPrtAtKorNm() {
		return prtAtKorNm;
	}

	/**
	 * @param prtAtKorNm the prtAtKorNm to set
	 */
	public void setPrtAtKorNm(String prtAtKorNm) {
		this.prtAtKorNm = prtAtKorNm;
	}

	/**
	 * @return the cmplYr
	 */
	public String getCmplYr() {
		return cmplYr;
	}

	/**
	 * @param cmplYr the cmplYr to set
	 */
	public void setCmplYr(String cmplYr) {
		this.cmplYr = cmplYr;
	}

	/**
	 * @return the constNo
	 */
	public String getConstNo() {
		return constNo;
	}

	/**
	 * @param constNo the constNo to set
	 */
	public void setConstNo(String constNo) {
		this.constNo = constNo;
	}

	/**
	 * @return the exmpAgentCode
	 */
	public String getExmpAgentCode() {
		return exmpAgentCode;
	}

	/**
	 * @param exmpAgentCode the exmpAgentCode to set
	 */
	public void setExmpAgentCode(String exmpAgentCode) {
		this.exmpAgentCode = exmpAgentCode;
	}

	/**
	 * @return the exmpAgentName
	 */
	public String getExmpAgentName() {
		return exmpAgentName;
	}

	/**
	 * @param exmpAgentName the exmpAgentName to set
	 */
	public void setExmpAgentName(String exmpAgentName) {
		this.exmpAgentName = exmpAgentName;
	}

	/**
	 * @return the vsslKey
	 */
	public String getVsslKey() {
		return vsslKey;
	}

	/**
	 * @param vsslKey the vsslKey to set
	 */
	public void setVsslKey(String vsslKey) {
		this.vsslKey = vsslKey;
	}

	/**
	 * @return the yr
	 */
	public String getYr() {
		return yr;
	}

	/**
	 * @param yr the yr to set
	 */
	public void setYr(String yr) {
		this.yr = yr;
	}

	/**
	 * @return the serNo
	 */
	public String getSerNo() {
		return serNo;
	}

	/**
	 * @param serNo the serNo to set
	 */
	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}

	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	/**
	 * @return the exmpAmnt
	 */
	public String getExmpAmnt() {
		return exmpAmnt;
	}

	/**
	 * @param exmpAmnt the exmpAmnt to set
	 */
	public void setExmpAmnt(String exmpAmnt) {
		this.exmpAmnt = exmpAmnt;
	}

	/**
	 * @return the inOut
	 */
	public String getInOut() {
		return inOut;
	}

	/**
	 * @param inOut the inOut to set
	 */
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	/**
	 * @return the inOutName
	 */
	public String getInOutName() {
		return inOutName;
	}

	/**
	 * @param inOutName the inOutName to set
	 */
	public void setInOutName(String inOutName) {
		this.inOutName = inOutName;
	}

	/**
	 * @return the billUseNo
	 */
	public String getBillUseNo() {
		return billUseNo;
	}

	/**
	 * @param billUseNo the billUseNo to set
	 */
	public void setBillUseNo(String billUseNo) {
		this.billUseNo = billUseNo;
	}

	/**
	 * @return the facCode
	 */
	public String getFacCode() {
		return facCode;
	}

	/**
	 * @param facCode the facCode to set
	 */
	public void setFacCode(String facCode) {
		this.facCode = facCode;
	}

	/**
	 * @return the facSubCode
	 */
	public String getFacSubCode() {
		return facSubCode;
	}

	/**
	 * @param facSubCode the facSubCode to set
	 */
	public void setFacSubCode(String facSubCode) {
		this.facSubCode = facSubCode;
	}

	/**
	 * @return the facKorNm
	 */
	public String getFacKorNm() {
		return facKorNm;
	}

	/**
	 * @param facKorNm the facKorNm to set
	 */
	public void setFacKorNm(String facKorNm) {
		this.facKorNm = facKorNm;
	}

	/**
	 * @return the ioDt
	 */
	public String getIoDt() {
		return ioDt;
	}

	/**
	 * @param ioDt the ioDt to set
	 */
	public void setIoDt(String ioDt) {
		this.ioDt = ioDt;
	}

	/**
	 * @return the dcCode
	 */
	public String getDcCode() {
		return dcCode;
	}

	/**
	 * @param dcCode the dcCode to set
	 */
	public void setDcCode(String dcCode) {
		this.dcCode = dcCode;
	}

	/**
	 * @return the dcRate
	 */
	public String getDcRate() {
		return dcRate;
	}

	/**
	 * @param dcRate the dcRate to set
	 */
	public void setDcRate(String dcRate) {
		this.dcRate = dcRate;
	}

	/**
	 * @return the billDt
	 */
	public String getBillDt() {
		return billDt;
	}

	/**
	 * @param billDt the billDt to set
	 */
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}

	/**
	 * @return the standardFee
	 */
	public String getStandardFee() {
		return standardFee;
	}

	/**
	 * @param standardFee the standardFee to set
	 */
	public void setStandardFee(String standardFee) {
		this.standardFee = standardFee;
	}

	/**
	 * @return the realTn
	 */
	public String getRealTn() {
		return realTn;
	}

	/**
	 * @param realTn the realTn to set
	 */
	public void setRealTn(String realTn) {
		this.realTn = realTn;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the exmpType
	 */
	public String getExmpType() {
		return exmpType;
	}

	/**
	 * @param exmpType the exmpType to set
	 */
	public void setExmpType(String exmpType) {
		this.exmpType = exmpType;
	}

	/**
	 * @return the updtUid
	 */
	public String getUpdtUid() {
		return updtUid;
	}

	/**
	 * @param updtUid the updtUid to set
	 */
	public void setUpdtUid(String updtUid) {
		this.updtUid = updtUid;
	}

	/**
	 * @return the updtDate
	 */
	public String getUpdtDate() {
		return updtDate;
	}

	/**
	 * @param updtDate the updtDate to set
	 */
	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}

	/**
	 * @return the callLetter
	 */
	public String getCallLetter() {
		return callLetter;
	}

	/**
	 * @param callLetter the callLetter to set
	 */
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}

	/**
	 * @return the callLetterNm
	 */
	public String getCallLetterNm() {
		return callLetterNm;
	}

	/**
	 * @param callLetterNm the callLetterNm to set
	 */
	public void setCallLetterNm(String callLetterNm) {
		this.callLetterNm = callLetterNm;
	}

	/**
	 * @return the dcRateNm
	 */
	public String getDcRateNm() {
		return dcRateNm;
	}

	/**
	 * @param dcRateNm the dcRateNm to set
	 */
	public void setDcRateNm(String dcRateNm) {
		this.dcRateNm = dcRateNm;
	}

	/**
	 * @return the exmpPrtAtCode
	 */
	public String getExmpPrtAtCode() {
		return exmpPrtAtCode;
	}

	/**
	 * @param exmpPrtAtCode the exmpPrtAtCode to set
	 */
	public void setExmpPrtAtCode(String exmpPrtAtCode) {
		this.exmpPrtAtCode = exmpPrtAtCode;
	}

	/**
	 * @return the exmpPrtAtKorNm
	 */
	public String getExmpPrtAtKorNm() {
		return exmpPrtAtKorNm;
	}

	/**
	 * @param exmpPrtAtKorNm the exmpPrtAtKorNm to set
	 */
	public void setExmpPrtAtKorNm(String exmpPrtAtKorNm) {
		this.exmpPrtAtKorNm = exmpPrtAtKorNm;
	}

	/**
	 * @return the jingsuja
	 */
	public String getJingsuja() {
		return jingsuja;
	}

	/**
	 * @param jingsuja the jingsuja to set
	 */
	public void setJingsuja(String jingsuja) {
		this.jingsuja = jingsuja;
	}	
}
