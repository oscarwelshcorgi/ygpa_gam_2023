/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 6.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamSocApplyVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 공사관리청코드(조회조건) **/
	private String sPrtAtCode;
	
	/** 공사준공년도(조회조건) **/
	private String sCmplYr;
	
	/** 공사일련번호(조회조건) **/
	private String sConstNo;
	
	/** 면제요청청코드(조회조건) **/
	private String sAppPrtAtCode;
	
	/** 면제요청업체코드(조회조건) **/
	private String sAppAgentCode;
	
	/** 요청횟수(조회조건) **/
	private String sUseNo;
	
	/** 허가원부관리청코드 **/
	private String prtAtCode;

	/** 허가원부관리청명 **/
	private String prtAtCodeNm;
		
	/** 허가원부공사준공년도 **/
	private String cmplYr;
	
	/** 허가원부일련번호 **/
	private String constNo;
	
	/** 면제요청청코드 **/
	private String appPrtAtCode;

	/** 면제요청청명 **/
	private String appPrtAtCodeNm;
	
	/** 면제요청업체코드 **/
	private String appAgentCode;
	
	/** 면제요청신청횟수 **/
	private String useNo;
	
	/** 공사업체코드  ( soc_agent_detail_f에 등록된 업체만이 가능..)**/
	private String agentCode;
	
	/** 공사업체명 **/
	private String agentName;
	
	/** 보전처리대상 금액 **/
	private String exmpAmnt;
	
	/** 보전처리 누계액 **/
	private String exmpAcc;
	
	/** 보전처리 보전기간 from **/
	private String periodFr;
	
	/** 보전처리 보전기간 to **/
	private String periodTo;
	
	/** 보전처리 조건 1.금액, 2.기간 **/
	private String exmpCond;
	
	/** 적용요율 구분 1.과거, 2.현재 **/
	private String rateGubun;
	
	/** 특이사항 **/
	private String remark;
	
	/** 공사(상계)명칭 **/
	private String item;
	
	/** 사용여부 **/
	private String useYn;
	
	/** 사업자등록번호(면제화주처리시) **/
	private String bzRgstId;
	
	/** 신청일자 **/
	private String applDate;
	
	/** 허가일자 **/
	private String perfDt;
	
	/** 접안 외항 과거 요율 **/
	private String r11Rate;
	
	/** 접안 내항 고거 요율 **/
	private String r12Rate;

	/** 정박 외항 과거 요율 **/
	private String r21Rate;
	
	/** 정박 내항 고거 요율 **/
	private String r22Rate;
	
	/** 수정인 **/
	private String updtUid;
	
	/** 수정날짜 **/
	private String updtDate;

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
	 * @return the sCmplYr
	 */
	public String getsCmplYr() {
		return sCmplYr;
	}

	/**
	 * @param sCmplYr the sCmplYr to set
	 */
	public void setsCmplYr(String sCmplYr) {
		this.sCmplYr = sCmplYr;
	}

	/**
	 * @return the sConstNo
	 */
	public String getsConstNo() {
		return sConstNo;
	}

	/**
	 * @param sConstNo the sConstNo to set
	 */
	public void setsConstNo(String sConstNo) {
		this.sConstNo = sConstNo;
	}

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
	 * @return the sAppAgentCode
	 */
	public String getsAppAgentCode() {
		return sAppAgentCode;
	}

	/**
	 * @param sAppAgentCode the sAppAgentCode to set
	 */
	public void setsAppAgentCode(String sAppAgentCode) {
		this.sAppAgentCode = sAppAgentCode;
	}

	/**
	 * @return the sUseNo
	 */
	public String getsUseNo() {
		return sUseNo;
	}

	/**
	 * @param sUseNo the sUseNo to set
	 */
	public void setsUseNo(String sUseNo) {
		this.sUseNo = sUseNo;
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
	 * @return the prtAtCodeNm
	 */
	public String getPrtAtCodeNm() {
		return prtAtCodeNm;
	}

	/**
	 * @param prtAtCodeNm the prtAtCodeNm to set
	 */
	public void setPrtAtCodeNm(String prtAtCodeNm) {
		this.prtAtCodeNm = prtAtCodeNm;
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
	 * @return the appPrtAtCodeNm
	 */
	public String getAppPrtAtCodeNm() {
		return appPrtAtCodeNm;
	}

	/**
	 * @param appPrtAtCodeNm the appPrtAtCodeNm to set
	 */
	public void setAppPrtAtCodeNm(String appPrtAtCodeNm) {
		this.appPrtAtCodeNm = appPrtAtCodeNm;
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
	 * @return the agentCode
	 */
	public String getAgentCode() {
		return agentCode;
	}

	/**
	 * @param agentCode the agentCode to set
	 */
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}

	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
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
	 * @return the exmpAcc
	 */
	public String getExmpAcc() {
		return exmpAcc;
	}

	/**
	 * @param exmpAcc the exmpAcc to set
	 */
	public void setExmpAcc(String exmpAcc) {
		this.exmpAcc = exmpAcc;
	}

	/**
	 * @return the periodFr
	 */
	public String getPeriodFr() {
		return periodFr;
	}

	/**
	 * @param periodFr the periodFr to set
	 */
	public void setPeriodFr(String periodFr) {
		this.periodFr = periodFr;
	}

	/**
	 * @return the periodTo
	 */
	public String getPeriodTo() {
		return periodTo;
	}

	/**
	 * @param periodTo the periodTo to set
	 */
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	/**
	 * @return the exmpCond
	 */
	public String getExmpCond() {
		return exmpCond;
	}

	/**
	 * @param exmpCond the exmpCond to set
	 */
	public void setExmpCond(String exmpCond) {
		this.exmpCond = exmpCond;
	}

	/**
	 * @return the rateGubun
	 */
	public String getRateGubun() {
		return rateGubun;
	}

	/**
	 * @param rateGubun the rateGubun to set
	 */
	public void setRateGubun(String rateGubun) {
		this.rateGubun = rateGubun;
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
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	/**
	 * @return the bzRgstId
	 */
	public String getBzRgstId() {
		return bzRgstId;
	}

	/**
	 * @param bzRgstId the bzRgstId to set
	 */
	public void setBzRgstId(String bzRgstId) {
		this.bzRgstId = bzRgstId;
	}

	/**
	 * @return the applDate
	 */
	public String getApplDate() {
		return applDate;
	}

	/**
	 * @param applDate the applDate to set
	 */
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	/**
	 * @return the perfDt
	 */
	public String getPerfDt() {
		return perfDt;
	}

	/**
	 * @param perfDt the perfDt to set
	 */
	public void setPerfDt(String perfDt) {
		this.perfDt = perfDt;
	}

	/**
	 * @return the r11Rate
	 */
	public String getR11Rate() {
		return r11Rate;
	}

	/**
	 * @param r11Rate the r11Rate to set
	 */
	public void setR11Rate(String r11Rate) {
		this.r11Rate = r11Rate;
	}

	/**
	 * @return the r12Rate
	 */
	public String getR12Rate() {
		return r12Rate;
	}

	/**
	 * @param r12Rate the r12Rate to set
	 */
	public void setR12Rate(String r12Rate) {
		this.r12Rate = r12Rate;
	}

	/**
	 * @return the r21Rate
	 */
	public String getR21Rate() {
		return r21Rate;
	}

	/**
	 * @param r21Rate the r21Rate to set
	 */
	public void setR21Rate(String r21Rate) {
		this.r21Rate = r21Rate;
	}

	/**
	 * @return the r22Rate
	 */
	public String getR22Rate() {
		return r22Rate;
	}

	/**
	 * @param r22Rate the r22Rate to set
	 */
	public void setR22Rate(String r22Rate) {
		this.r22Rate = r22Rate;
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
	
	
}
