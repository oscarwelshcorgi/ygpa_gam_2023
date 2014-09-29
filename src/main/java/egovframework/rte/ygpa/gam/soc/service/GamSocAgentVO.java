/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 9. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 23.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamSocAgentVO extends ComDefaultVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/** 검색항코드 */
    private String sPrtAtCode;
    
    /** 공사준공년도 */
    private String sCmplYr;
    
    /** 공사일련번호 */
    private String sConstNo;
    
    /** 공사항만코드 */
    private String prtAtCode;
    
    /** 대표자 */
    private String agentOwner;
    
    /** 공사명 */
    private String socCnstNm;
    
    /** 공사승인일자 */
    private String aprvDt;
    
    /** 위치 */
    private String cnstLoc;
    
    /** 공사허가일자 */
    private String perfDt;
    
    /** 업체코드 */
    private String agentCode;
    
    /** 업체명 */
    private String agentName;
    
    /** 공사준공일자 */
    private String cmplDt;
    
    /** 주소 */
    private String agentAddr;
    
    /** 목적 */
    private String socObj;
    
    /** 시행기간 */
    private String socGigian;
    
    /** 국가비귀속 대상시설 */
    private String socPrivate;
    
    /** 국가귀속 대상시설 */
    private String socNation;
    
    /** 시행면적 */
    private String socWidth;
    
    /** 조사비 */
    private String reserachAmnt;
    
    /** 순공사비 */
    private String pureAmnt;
    
    /** 기타비용1 */
    private String extraAmnt1;
    
    /** 기타비용2 */
    private String extraAmnt2;
    
    /** 기타비용3 */
    private String extraAmnt3;
    
    /** 주요허가조건 */
    private String primeTxt;
    
    /** 변경사항 */
    private String modifyTxt;
    
    /** 착공일 */
    private String startDt;
    
    /** 변경일자 */
    private String modifyDt1;
    
    /** 무상사용허가기간 시작 */
    private String freefrDt;
    
    /** 무상사용허가기간 끝 */
    private String freetoDt;
    
    /** 시설관리권등록일 */
    private String manageDt;
    
    /** 무상사용승인일 */
    private String freeuseDt;
    
    /** 재산귀속일 */
    private String assetDt;
    
    /** 타인사용징수승인일 */
    private String otherDt;
    
    
    
    /** 공사준공년도 */
    private String cmplYr;
    
    /** 허가원부일련번호 */
    private String constNo;
    
    /** 공사지역항만코드 */
    private String socPrtAtCode;
    
    /** 공사변경일자2 */
    private String modifyDt2;
    
    /** 총사업비보전금액 */
    private String totalAmnt;
    
    /** 보전누계액 */
    private String accFee;
    
    /** 기타비용4 */
    private String extraAmnt4;
    
    /** 특이사항 */
    private String remark;
    
    /** 항만시설 및 사용료 징수범위 */
    private String range;
    
    /** 사업자등록번호 */
    private String bzRgstId;
    
    /** 등록자 */
    private String updtUid;
    
    /** 수정일시 */
    private String updtDate;
    
    
    
    /** 총갯수 */
    private int totalCnt;
    
    /** 총보전처리대상금액 */
    private long sumTotalAmnt;
    
    /** 총보전처리누계액 */
    private long sumAccFee;
    


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
	 * @return the agentOwner
	 */
	public String getAgentOwner() {
		return agentOwner;
	}

	/**
	 * @param agentOwner the agentOwner to set
	 */
	public void setAgentOwner(String agentOwner) {
		this.agentOwner = agentOwner;
	}

	/**
	 * @return the socCnstNm
	 */
	public String getSocCnstNm() {
		return socCnstNm;
	}

	/**
	 * @param socCnstNm the socCnstNm to set
	 */
	public void setSocCnstNm(String socCnstNm) {
		this.socCnstNm = socCnstNm;
	}

	/**
	 * @return the aprvDt
	 */
	public String getAprvDt() {
		return aprvDt;
	}

	/**
	 * @param aprvDt the aprvDt to set
	 */
	public void setAprvDt(String aprvDt) {
		this.aprvDt = aprvDt;
	}

	/**
	 * @return the cnstLoc
	 */
	public String getCnstLoc() {
		return cnstLoc;
	}

	/**
	 * @param cnstLoc the cnstLoc to set
	 */
	public void setCnstLoc(String cnstLoc) {
		this.cnstLoc = cnstLoc;
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
	 * @return the cmplDt
	 */
	public String getCmplDt() {
		return cmplDt;
	}

	/**
	 * @param cmplDt the cmplDt to set
	 */
	public void setCmplDt(String cmplDt) {
		this.cmplDt = cmplDt;
	}

	/**
	 * @return the agentAddr
	 */
	public String getAgentAddr() {
		return agentAddr;
	}

	/**
	 * @param agentAddr the agentAddr to set
	 */
	public void setAgentAddr(String agentAddr) {
		this.agentAddr = agentAddr;
	}

	/**
	 * @return the socObj
	 */
	public String getSocObj() {
		return socObj;
	}

	/**
	 * @param socObj the socObj to set
	 */
	public void setSocObj(String socObj) {
		this.socObj = socObj;
	}

	/**
	 * @return the socGigian
	 */
	public String getSocGigian() {
		return socGigian;
	}

	/**
	 * @param socGigian the socGigian to set
	 */
	public void setSocGigian(String socGigian) {
		this.socGigian = socGigian;
	}

	/**
	 * @return the socPrivate
	 */
	public String getSocPrivate() {
		return socPrivate;
	}

	/**
	 * @param socPrivate the socPrivate to set
	 */
	public void setSocPrivate(String socPrivate) {
		this.socPrivate = socPrivate;
	}

	/**
	 * @return the socNation
	 */
	public String getSocNation() {
		return socNation;
	}

	/**
	 * @param socNation the socNation to set
	 */
	public void setSocNation(String socNation) {
		this.socNation = socNation;
	}

	/**
	 * @return the socWidth
	 */
	public String getSocWidth() {
		return socWidth;
	}

	/**
	 * @param socWidth the socWidth to set
	 */
	public void setSocWidth(String socWidth) {
		this.socWidth = socWidth;
	}

	/**
	 * @return the reserachAmnt
	 */
	public String getReserachAmnt() {
		return reserachAmnt;
	}

	/**
	 * @param reserachAmnt the reserachAmnt to set
	 */
	public void setReserachAmnt(String reserachAmnt) {
		this.reserachAmnt = reserachAmnt;
	}

	/**
	 * @return the pureAmnt
	 */
	public String getPureAmnt() {
		return pureAmnt;
	}

	/**
	 * @param pureAmnt the pureAmnt to set
	 */
	public void setPureAmnt(String pureAmnt) {
		this.pureAmnt = pureAmnt;
	}

	/**
	 * @return the extraAmnt1
	 */
	public String getExtraAmnt1() {
		return extraAmnt1;
	}

	/**
	 * @param extraAmnt1 the extraAmnt1 to set
	 */
	public void setExtraAmnt1(String extraAmnt1) {
		this.extraAmnt1 = extraAmnt1;
	}

	/**
	 * @return the extraAmnt2
	 */
	public String getExtraAmnt2() {
		return extraAmnt2;
	}

	/**
	 * @param extraAmnt2 the extraAmnt2 to set
	 */
	public void setExtraAmnt2(String extraAmnt2) {
		this.extraAmnt2 = extraAmnt2;
	}

	/**
	 * @return the extraAmnt3
	 */
	public String getExtraAmnt3() {
		return extraAmnt3;
	}

	/**
	 * @param extraAmnt3 the extraAmnt3 to set
	 */
	public void setExtraAmnt3(String extraAmnt3) {
		this.extraAmnt3 = extraAmnt3;
	}

	/**
	 * @return the primeTxt
	 */
	public String getPrimeTxt() {
		return primeTxt;
	}

	/**
	 * @param primeTxt the primeTxt to set
	 */
	public void setPrimeTxt(String primeTxt) {
		this.primeTxt = primeTxt;
	}

	/**
	 * @return the modifyTxt
	 */
	public String getModifyTxt() {
		return modifyTxt;
	}

	/**
	 * @param modifyTxt the modifyTxt to set
	 */
	public void setModifyTxt(String modifyTxt) {
		this.modifyTxt = modifyTxt;
	}

	/**
	 * @return the startDt
	 */
	public String getStartDt() {
		return startDt;
	}

	/**
	 * @param startDt the startDt to set
	 */
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	/**
	 * @return the modifyDt1
	 */
	public String getModifyDt1() {
		return modifyDt1;
	}

	/**
	 * @param modifyDt1 the modifyDt1 to set
	 */
	public void setModifyDt1(String modifyDt1) {
		this.modifyDt1 = modifyDt1;
	}

	/**
	 * @return the freefrDt
	 */
	public String getFreefrDt() {
		return freefrDt;
	}

	/**
	 * @param freefrDt the freefrDt to set
	 */
	public void setFreefrDt(String freefrDt) {
		this.freefrDt = freefrDt;
	}

	/**
	 * @return the freetoDt
	 */
	public String getFreetoDt() {
		return freetoDt;
	}

	/**
	 * @param freetoDt the freetoDt to set
	 */
	public void setFreetoDt(String freetoDt) {
		this.freetoDt = freetoDt;
	}

	/**
	 * @return the manageDt
	 */
	public String getManageDt() {
		return manageDt;
	}

	/**
	 * @param manageDt the manageDt to set
	 */
	public void setManageDt(String manageDt) {
		this.manageDt = manageDt;
	}

	/**
	 * @return the freeuseDt
	 */
	public String getFreeuseDt() {
		return freeuseDt;
	}

	/**
	 * @param freeuseDt the freeuseDt to set
	 */
	public void setFreeuseDt(String freeuseDt) {
		this.freeuseDt = freeuseDt;
	}

	/**
	 * @return the assetDt
	 */
	public String getAssetDt() {
		return assetDt;
	}

	/**
	 * @param assetDt the assetDt to set
	 */
	public void setAssetDt(String assetDt) {
		this.assetDt = assetDt;
	}

	/**
	 * @return the otherDt
	 */
	public String getOtherDt() {
		return otherDt;
	}

	/**
	 * @param otherDt the otherDt to set
	 */
	public void setOtherDt(String otherDt) {
		this.otherDt = otherDt;
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
	 * @return the socPrtAtCode
	 */
	public String getSocPrtAtCode() {
		return socPrtAtCode;
	}

	/**
	 * @param socPrtAtCode the socPrtAtCode to set
	 */
	public void setSocPrtAtCode(String socPrtAtCode) {
		this.socPrtAtCode = socPrtAtCode;
	}

	/**
	 * @return the modifyDt2
	 */
	public String getModifyDt2() {
		return modifyDt2;
	}

	/**
	 * @param modifyDt2 the modifyDt2 to set
	 */
	public void setModifyDt2(String modifyDt2) {
		this.modifyDt2 = modifyDt2;
	}

	/**
	 * @return the totalAmnt
	 */
	public String getTotalAmnt() {
		return totalAmnt;
	}

	/**
	 * @param totalAmnt the totalAmnt to set
	 */
	public void setTotalAmnt(String totalAmnt) {
		this.totalAmnt = totalAmnt;
	}

	/**
	 * @return the accFee
	 */
	public String getAccFee() {
		return accFee;
	}

	/**
	 * @param accFee the accFee to set
	 */
	public void setAccFee(String accFee) {
		this.accFee = accFee;
	}

	/**
	 * @return the extraAmnt4
	 */
	public String getExtraAmnt4() {
		return extraAmnt4;
	}

	/**
	 * @param extraAmnt4 the extraAmnt4 to set
	 */
	public void setExtraAmnt4(String extraAmnt4) {
		this.extraAmnt4 = extraAmnt4;
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
	 * @return the range
	 */
	public String getRange() {
		return range;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(String range) {
		this.range = range;
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
	 * @return the totalCnt
	 */
	public int getTotalCnt() {
		return totalCnt;
	}

	/**
	 * @param totalCnt the totalCnt to set
	 */
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	/**
	 * @return the sumTotalAmnt
	 */
	public long getSumTotalAmnt() {
		return sumTotalAmnt;
	}

	/**
	 * @param sumTotalAmnt the sumTotalAmnt to set
	 */
	public void setSumTotalAmnt(long sumTotalAmnt) {
		this.sumTotalAmnt = sumTotalAmnt;
	}

	/**
	 * @return the sumAccFee
	 */
	public long getSumAccFee() {
		return sumAccFee;
	}

	/**
	 * @param sumAccFee the sumAccFee to set
	 */
	public void setSumAccFee(long sumAccFee) {
		this.sumAccFee = sumAccFee;
	}

	

}
