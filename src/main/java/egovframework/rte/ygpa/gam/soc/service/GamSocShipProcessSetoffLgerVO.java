/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 17.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamSocShipProcessSetoffLgerVO extends ComDefaultVO {
	
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
    
    /** 접안료(청) */
    private long sumR1Fare;
    
    /** 정박료(청) */
    private long sumR2Fare;
    
    /** 입항료(청) */
    private long sumR3Fare;
    
    /** 기타(청) */
    private long sumR6Fare;
    
    /** 총계(청) */
    private long sumRFare;
    
    /** 접안료(공사) */
    private long sumR1FarePa;
    
    /** 정박료(공사) */
    private long sumR2FarePa;
    
    /** 입항료(공사) */
    private long sumR3FarePa;
    
    /** 기타(공사) */
    private long sumR6FarePa;
    
    /** 총계(공사) */
    private long sumRFarePa;
    
    /** 접안료(전체) */
    private long sumR1All;
    
    /** 정박료(전체) */
    private long sumR2All;
    
    /** 입항료(전체) */
    private long sumR3All;
    
    /** 기타(전체) */
    private long sumR6All;
    
    /** 총계(전체) */
    private long sumRAll;
    
    


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
	 * @return the sumR1Fare
	 */
	public long getSumR1Fare() {
		return sumR1Fare;
	}

	/**
	 * @param sumR1Fare the sumR1Fare to set
	 */
	public void setSumR1Fare(long sumR1Fare) {
		this.sumR1Fare = sumR1Fare;
	}

	/**
	 * @return the sumR2Fare
	 */
	public long getSumR2Fare() {
		return sumR2Fare;
	}

	/**
	 * @param sumR2Fare the sumR2Fare to set
	 */
	public void setSumR2Fare(long sumR2Fare) {
		this.sumR2Fare = sumR2Fare;
	}

	/**
	 * @return the sumR3Fare
	 */
	public long getSumR3Fare() {
		return sumR3Fare;
	}

	/**
	 * @param sumR3Fare the sumR3Fare to set
	 */
	public void setSumR3Fare(long sumR3Fare) {
		this.sumR3Fare = sumR3Fare;
	}

	/**
	 * @return the sumR6Fare
	 */
	public long getSumR6Fare() {
		return sumR6Fare;
	}

	/**
	 * @param sumR6Fare the sumR6Fare to set
	 */
	public void setSumR6Fare(long sumR6Fare) {
		this.sumR6Fare = sumR6Fare;
	}

	/**
	 * @return the sumRFare
	 */
	public long getSumRFare() {
		return sumRFare;
	}

	/**
	 * @param sumRFare the sumRFare to set
	 */
	public void setSumRFare(long sumRFare) {
		this.sumRFare = sumRFare;
	}

	/**
	 * @return the sumR1FarePa
	 */
	public long getSumR1FarePa() {
		return sumR1FarePa;
	}

	/**
	 * @param sumR1FarePa the sumR1FarePa to set
	 */
	public void setSumR1FarePa(long sumR1FarePa) {
		this.sumR1FarePa = sumR1FarePa;
	}

	/**
	 * @return the sumR2FarePa
	 */
	public long getSumR2FarePa() {
		return sumR2FarePa;
	}

	/**
	 * @param sumR2FarePa the sumR2FarePa to set
	 */
	public void setSumR2FarePa(long sumR2FarePa) {
		this.sumR2FarePa = sumR2FarePa;
	}

	/**
	 * @return the sumR3FarePa
	 */
	public long getSumR3FarePa() {
		return sumR3FarePa;
	}

	/**
	 * @param sumR3FarePa the sumR3FarePa to set
	 */
	public void setSumR3FarePa(long sumR3FarePa) {
		this.sumR3FarePa = sumR3FarePa;
	}

	/**
	 * @return the sumR6FarePa
	 */
	public long getSumR6FarePa() {
		return sumR6FarePa;
	}

	/**
	 * @param sumR6FarePa the sumR6FarePa to set
	 */
	public void setSumR6FarePa(long sumR6FarePa) {
		this.sumR6FarePa = sumR6FarePa;
	}

	/**
	 * @return the sumRFarePa
	 */
	public long getSumRFarePa() {
		return sumRFarePa;
	}

	/**
	 * @param sumRFarePa the sumRFarePa to set
	 */
	public void setSumRFarePa(long sumRFarePa) {
		this.sumRFarePa = sumRFarePa;
	}

	/**
	 * @return the sumR1All
	 */
	public long getSumR1All() {
		return sumR1All;
	}

	/**
	 * @param sumR1All the sumR1All to set
	 */
	public void setSumR1All(long sumR1All) {
		this.sumR1All = sumR1All;
	}

	/**
	 * @return the sumR2All
	 */
	public long getSumR2All() {
		return sumR2All;
	}

	/**
	 * @param sumR2All the sumR2All to set
	 */
	public void setSumR2All(long sumR2All) {
		this.sumR2All = sumR2All;
	}

	/**
	 * @return the sumR3All
	 */
	public long getSumR3All() {
		return sumR3All;
	}

	/**
	 * @param sumR3All the sumR3All to set
	 */
	public void setSumR3All(long sumR3All) {
		this.sumR3All = sumR3All;
	}

	/**
	 * @return the sumR6All
	 */
	public long getSumR6All() {
		return sumR6All;
	}

	/**
	 * @param sumR6All the sumR6All to set
	 */
	public void setSumR6All(long sumR6All) {
		this.sumR6All = sumR6All;
	}

	/**
	 * @return the sumRAll
	 */
	public long getSumRAll() {
		return sumRAll;
	}

	/**
	 * @param sumRAll the sumRAll to set
	 */
	public void setSumRAll(long sumRAll) {
		this.sumRAll = sumRAll;
	}

	
    
    

}
