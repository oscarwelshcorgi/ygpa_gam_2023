/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 16.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamSocFrghtProcessSetoffLgerVO extends ComDefaultVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 등록항구 */
    private String sAppPrtAtCode;
    
    /** 공사항구 */
    private String sPrtAtCode;
    
    /** 공사년도 */
    private String sCmplYr;
    
    /** 문서번호 */
    private String sConstNo;
    
    /** 요금종류 */
    private String sFeeTp;
    
    /** 적용요율 */
    private String sRateGubunNm;
    
    /** 신청횟수 */
    private String sUseNo;
    
    /** 신청업체 */
    private String sAppAgentCode;
    
    /** 보전처리대상금액 */
    private String sExmpAmnt;
    
    /** 상계기간시작일 */
    private String sDtFr;
    
    /** 상계기간종료일 */
    private String sDtTo;
    
    /** 신청시설코드 */
    private String sFacCode;
    
    /** 신청시설하위코드 */
    private String sFacSubCode;
    
    /** 보전누계액 */
    private String sExmpAcc;
    
    /** 입출항일자시작일 */
    private String sIoDt;
    
    /** 입출항일자종료일 */
    private String sIoDt2;
    
    
    
    /** 자료수 */
    private int totalCnt;
    
    /** 총계(청) */
    private long sumExmpAmnt;
    
    /** 총계(공사) */
    private long sumExmpAmntPa;
    
    /** 총계(전체) */
    private long sumAmnt;
    
    
    
    

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
	 * @return the sRateGubunNm
	 */
	public String getsRateGubunNm() {
		return sRateGubunNm;
	}

	/**
	 * @param sRateGubunNm the sRateGubunNm to set
	 */
	public void setsRateGubunNm(String sRateGubunNm) {
		this.sRateGubunNm = sRateGubunNm;
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
	 * @return the sExmpAmnt
	 */
	public String getsExmpAmnt() {
		return sExmpAmnt;
	}

	/**
	 * @param sExmpAmnt the sExmpAmnt to set
	 */
	public void setsExmpAmnt(String sExmpAmnt) {
		this.sExmpAmnt = sExmpAmnt;
	}

	/**
	 * @return the sDtFr
	 */
	public String getsDtFr() {
		return sDtFr;
	}

	/**
	 * @param sDtFr the sDtFr to set
	 */
	public void setsDtFr(String sDtFr) {
		this.sDtFr = sDtFr;
	}

	/**
	 * @return the sDtTo
	 */
	public String getsDtTo() {
		return sDtTo;
	}

	/**
	 * @param sDtTo the sDtTo to set
	 */
	public void setsDtTo(String sDtTo) {
		this.sDtTo = sDtTo;
	}

	/**
	 * @return the sFacCode
	 */
	public String getsFacCode() {
		return sFacCode;
	}

	/**
	 * @param sFacCode the sFacCode to set
	 */
	public void setsFacCode(String sFacCode) {
		this.sFacCode = sFacCode;
	}

	/**
	 * @return the sFacSubCode
	 */
	public String getsFacSubCode() {
		return sFacSubCode;
	}

	/**
	 * @param sFacSubCode the sFacSubCode to set
	 */
	public void setsFacSubCode(String sFacSubCode) {
		this.sFacSubCode = sFacSubCode;
	}

	/**
	 * @return the sExmpAcc
	 */
	public String getsExmpAcc() {
		return sExmpAcc;
	}

	/**
	 * @param sExmpAcc the sExmpAcc to set
	 */
	public void setsExmpAcc(String sExmpAcc) {
		this.sExmpAcc = sExmpAcc;
	}

	/**
	 * @return the sIoDt
	 */
	public String getsIoDt() {
		return sIoDt;
	}

	/**
	 * @param sIoDt the sIoDt to set
	 */
	public void setsIoDt(String sIoDt) {
		this.sIoDt = sIoDt;
	}

	/**
	 * @return the sIoDt2
	 */
	public String getsIoDt2() {
		return sIoDt2;
	}

	/**
	 * @param sIoDt2 the sIoDt2 to set
	 */
	public void setsIoDt2(String sIoDt2) {
		this.sIoDt2 = sIoDt2;
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
	 * @return the sumExmpAmnt
	 */
	public long getSumExmpAmnt() {
		return sumExmpAmnt;
	}

	/**
	 * @param sumExmpAmnt the sumExmpAmnt to set
	 */
	public void setSumExmpAmnt(long sumExmpAmnt) {
		this.sumExmpAmnt = sumExmpAmnt;
	}

	/**
	 * @return the sumExmpAmntPa
	 */
	public long getSumExmpAmntPa() {
		return sumExmpAmntPa;
	}

	/**
	 * @param sumExmpAmntPa the sumExmpAmntPa to set
	 */
	public void setSumExmpAmntPa(long sumExmpAmntPa) {
		this.sumExmpAmntPa = sumExmpAmntPa;
	}

	/**
	 * @return the sumAmnt
	 */
	public long getSumAmnt() {
		return sumAmnt;
	}

	/**
	 * @param sumAmnt the sumAmnt to set
	 */
	public void setSumAmnt(long sumAmnt) {
		this.sumAmnt = sumAmnt;
	}
    
    

}
