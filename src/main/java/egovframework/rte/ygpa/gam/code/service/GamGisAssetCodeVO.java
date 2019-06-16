package egovframework.rte.ygpa.gam.code.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Class Name : GamAssetDisUseMngtVO.java
 * @Description : 자산폐기등록 VO class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamGisAssetCodeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** GIS 자산 SUB 코드 */
    private String gisAssetsSubCd;

    /** GIS 자산 코드 */
    private String gisAssetsCd;

    /** GIS 자산 명 */
    private String gisAssetsNm;

    /** GIS 자산 관리 부서 코드 */
    private String gisAssetsMngDeptCd;

    /** GIS 자산 운영 부서 코드 */
    private String gisAssetsOperDeptCd;

    /** GIS 자산 소재지 */
    private String gisAssetsLocplc;

    /** GIS 자산 지번 */
    private String gisAssetsLnm;

    /** GIS 자산 지번SUB */
    private String gisAssetsLnmSub;

    /** GIS 자산 항코드 */
    private String gisAssetsPrtAtCode;

    /** GIS 자산 면적 */
    private String gisAssetsAr;

    /** GIS 자산 사용 여부 */
    private String gisAssetsUsageYn;

    /** GIS 자산 취득가액 */
    private String gisAssetsAcqPri;

    /** GIS 자산 규격 */
    private String gisAssetsStndrd;

    /** GIS 자산 준공년도 */
    private String gisAssetsBlddate;

    /** GIS 자산 준공 일자 */
    private String gisAssetsBldDt;

    /** GIS 자산 비고 */
    private String gisAssetsRm;

    /** 등록자 */
    private String regUsr;

    /** 등록일자 */
    private String registdt;

    /** 수정자 */
    private String updUsr;

    /** 수정일자 */
    private String updtdt;

    /** GIS 자산 부두 그룹 코드 */
    private String gisAssetsQuayGroupCd;

    /** GIS 자산 부두 코드 */
    private String gisAssetsQuayCd;

    /** GIS 자산 위치 코드 */
    private String gisAssetsLocCd;

    /** GIS 자산 구분 코드 */
    private String gisAssetsSeCd;

    /** GIS 자산 재산 구분 코드 */
    private String gisAssetsPrprtySeCd;

    /** GIS 자산 출자 방식 */
    private String gisAssetsInvstmntMthd;

    /** GIS 자산 GIS 코드 */
    private String gisAssetsGisCd;

    /** GIS 자산 실제 임대 면적 */
    private String gisAssetsRealRentAr;

    /** 도면 목록 등록 년도 */
    private String drwLstRegistYear;

    /** 도면 목록 순번 */
    private String drwLstSeq;

    /** GIS 자산 가치 금액 */
    private String gisAssetsValAmt;

    /** GIS 자산 가치 조회 일자 */
    private String gisAssetsValInqireDt;

    /** ERP 자산 구분 코드 */
    private String erpAssetsCls;

    /** ERP 자산 번호 */
    private String erpAssetsNo;

    /** ERP 자산 번호 순번 */
    private String erpAssetsNoSeq;

    /** ERP 자산 폐기 등록 여부 */
    private String erpAssetsDisuseRegistYn;

    /** ERP 자산 폐기 사유 */
    private String erpAssetsDisuseRsn;

    /** 운영부서 */
    private String operDeptCd;

    /** 관리부서 */
    private String mngDeptCd;

    /** GIS 자산 코드(조회조건) */
    private String sGisAssetsCd;

    /** GIS 자산 명(조회조건) */
    private String sGisAssetsNm;


/* 2019-06-13 jckim */

    /** 품목구분 */
    private String prdlstSe;
    /** 회계구분 */
    private String fsse;
    /** 대장가액 */
    private String regstrAmount;
    /** 현재 대장가액 */
    private String nowRegstrAmount;
    /** 출자금액 */
    private String invstmntAmount;
    /** 시가표준액 */
    private String mktcStdAm;
    /** 공시지가 */
    private String olnlp;
    /** 연면적 */
    private String totar;
    /** 수량 */
    private String qy;
    /** 단위 */
    private String unit;
    /** 주요 시설물 */
    private String mainFclts;
    /** 당초 허가기간 시작 */
    private String bgnnPrmisnPdBegin;
    /** 당초 허가기간 종료 */
    private String bgnnPrmisnPdEnd;
    /** 변경 허가기간 시작 */
    private String changePrmisnPdBegin;
    /** 변경 허가기간 종료 */
    private String changePrmisnPdEnd;

    /** 지목 */
    private String gisAssetsLndcgr;

    private String gisAssetsBupjungdongCd;

	/**
	 * @return the gisAssetsSubCd
	 */
	public String getGisAssetsSubCd() {
		return gisAssetsSubCd;
	}

	/**
	 * @param gisAssetsSubCd the gisAssetsSubCd to set
	 */
	public void setGisAssetsSubCd(String gisAssetsSubCd) {
		this.gisAssetsSubCd = gisAssetsSubCd;
	}

	/**
	 * @return the gisAssetsCd
	 */
	public String getGisAssetsCd() {
		return gisAssetsCd;
	}

	/**
	 * @param gisAssetsCd the gisAssetsCd to set
	 */
	public void setGisAssetsCd(String gisAssetsCd) {
		this.gisAssetsCd = gisAssetsCd;
	}

	/**
	 * @return the gisAssetsNm
	 */
	public String getGisAssetsNm() {
		return gisAssetsNm;
	}

	/**
	 * @param gisAssetsNm the gisAssetsNm to set
	 */
	public void setGisAssetsNm(String gisAssetsNm) {
		this.gisAssetsNm = gisAssetsNm;
	}

	/**
	 * @return the gisAssetsMngDeptCd
	 */
	public String getGisAssetsMngDeptCd() {
		return gisAssetsMngDeptCd;
	}

	/**
	 * @param gisAssetsMngDeptCd the gisAssetsMngDeptCd to set
	 */
	public void setGisAssetsMngDeptCd(String gisAssetsMngDeptCd) {
		this.gisAssetsMngDeptCd = gisAssetsMngDeptCd;
	}

	/**
	 * @return the gisAssetsOperDeptCd
	 */
	public String getGisAssetsOperDeptCd() {
		return gisAssetsOperDeptCd;
	}

	/**
	 * @param gisAssetsOperDeptCd the gisAssetsOperDeptCd to set
	 */
	public void setGisAssetsOperDeptCd(String gisAssetsOperDeptCd) {
		this.gisAssetsOperDeptCd = gisAssetsOperDeptCd;
	}

	/**
	 * @return the gisAssetsLocplc
	 */
	public String getGisAssetsLocplc() {
		return gisAssetsLocplc;
	}

	/**
	 * @param gisAssetsLocplc the gisAssetsLocplc to set
	 */
	public void setGisAssetsLocplc(String gisAssetsLocplc) {
		this.gisAssetsLocplc = gisAssetsLocplc;
	}

	/**
	 * @return the gisAssetsLnm
	 */
	public String getGisAssetsLnm() {
		return gisAssetsLnm;
	}

	/**
	 * @param gisAssetsLnm the gisAssetsLnm to set
	 */
	public void setGisAssetsLnm(String gisAssetsLnm) {
		this.gisAssetsLnm = gisAssetsLnm;
	}

	/**
	 * @return the gisAssetsLnmSub
	 */
	public String getGisAssetsLnmSub() {
		return gisAssetsLnmSub;
	}

	/**
	 * @param gisAssetsLnmSub the gisAssetsLnmSub to set
	 */
	public void setGisAssetsLnmSub(String gisAssetsLnmSub) {
		this.gisAssetsLnmSub = gisAssetsLnmSub;
	}

	/**
	 * @return the gisAssetsPrtAtCode
	 */
	public String getGisAssetsPrtAtCode() {
		return gisAssetsPrtAtCode;
	}

	/**
	 * @param gisAssetsPrtAtCode the gisAssetsPrtAtCode to set
	 */
	public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
		this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
	}

	/**
	 * @return the gisAssetsAr
	 */
	public String getGisAssetsAr() {
		return gisAssetsAr;
	}

	/**
	 * @param gisAssetsAr the gisAssetsAr to set
	 */
	public void setGisAssetsAr(String gisAssetsAr) {
		this.gisAssetsAr = gisAssetsAr;
	}

	/**
	 * @return the gisAssetsUsageYn
	 */
	public String getGisAssetsUsageYn() {
		return gisAssetsUsageYn;
	}

	/**
	 * @param gisAssetsUsageYn the gisAssetsUsageYn to set
	 */
	public void setGisAssetsUsageYn(String gisAssetsUsageYn) {
		this.gisAssetsUsageYn = gisAssetsUsageYn;
	}

	/**
	 * @return the gisAssetsAcqPri
	 */
	public String getGisAssetsAcqPri() {
		return gisAssetsAcqPri;
	}

	/**
	 * @param gisAssetsAcqPri the gisAssetsAcqPri to set
	 */
	public void setGisAssetsAcqPri(String gisAssetsAcqPri) {
		this.gisAssetsAcqPri = gisAssetsAcqPri;
	}

	/**
	 * @return the gisAssetsStndrd
	 */
	public String getGisAssetsStndrd() {
		return gisAssetsStndrd;
	}

	/**
	 * @param gisAssetsStndrd the gisAssetsStndrd to set
	 */
	public void setGisAssetsStndrd(String gisAssetsStndrd) {
		this.gisAssetsStndrd = gisAssetsStndrd;
	}

	/**
	 * @return the gisAssetsBlddate
	 */
	public String getGisAssetsBlddate() {
		return gisAssetsBlddate;
	}

	/**
	 * @param gisAssetsBlddate the gisAssetsBlddate to set
	 */
	public void setGisAssetsBlddate(String gisAssetsBlddate) {
		this.gisAssetsBlddate = gisAssetsBlddate;
	}

	/**
	 * @return the gisAssetsBldDt
	 */
	public String getGisAssetsBldDt() {
		return gisAssetsBldDt;
	}

	/**
	 * @param gisAssetsBldDt the gisAssetsBldDt to set
	 */
	public void setGisAssetsBldDt(String gisAssetsBldDt) {
		this.gisAssetsBldDt = gisAssetsBldDt;
	}

	/**
	 * @return the gisAssetsRm
	 */
	public String getGisAssetsRm() {
		return gisAssetsRm;
	}

	/**
	 * @param gisAssetsRm the gisAssetsRm to set
	 */
	public void setGisAssetsRm(String gisAssetsRm) {
		this.gisAssetsRm = gisAssetsRm;
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
	 * @return the registdt
	 */
	public String getRegistdt() {
		return registdt;
	}

	/**
	 * @param registdt the registdt to set
	 */
	public void setRegistdt(String registdt) {
		this.registdt = registdt;
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
	 * @return the updtdt
	 */
	public String getUpdtdt() {
		return updtdt;
	}

	/**
	 * @param updtdt the updtdt to set
	 */
	public void setUpdtdt(String updtdt) {
		this.updtdt = updtdt;
	}

	/**
	 * @return the gisAssetsQuayGroupCd
	 */
	public String getGisAssetsQuayGroupCd() {
		return gisAssetsQuayGroupCd;
	}

	/**
	 * @param gisAssetsQuayGroupCd the gisAssetsQuayGroupCd to set
	 */
	public void setGisAssetsQuayGroupCd(String gisAssetsQuayGroupCd) {
		this.gisAssetsQuayGroupCd = gisAssetsQuayGroupCd;
	}

	/**
	 * @return the gisAssetsLocCd
	 */
	public String getGisAssetsLocCd() {
		return gisAssetsLocCd;
	}

	/**
	 * @param gisAssetsLocCd the gisAssetsLocCd to set
	 */
	public void setGisAssetsLocCd(String gisAssetsLocCd) {
		this.gisAssetsLocCd = gisAssetsLocCd;
	}

	/**
	 * @return the gisAssetsSeCd
	 */
	public String getGisAssetsSeCd() {
		return gisAssetsSeCd;
	}

	/**
	 * @param gisAssetsSeCd the gisAssetsSeCd to set
	 */
	public void setGisAssetsSeCd(String gisAssetsSeCd) {
		this.gisAssetsSeCd = gisAssetsSeCd;
	}

	/**
	 * @return the gisAssetsPrprtySeCd
	 */
	public String getGisAssetsPrprtySeCd() {
		return gisAssetsPrprtySeCd;
	}

	/**
	 * @param gisAssetsPrprtySeCd the gisAssetsPrprtySeCd to set
	 */
	public void setGisAssetsPrprtySeCd(String gisAssetsPrprtySeCd) {
		this.gisAssetsPrprtySeCd = gisAssetsPrprtySeCd;
	}

	/**
	 * @return the gisAssetsInvstmntMthd
	 */
	public String getGisAssetsInvstmntMthd() {
		return gisAssetsInvstmntMthd;
	}

	/**
	 * @param gisAssetsInvstmntMthd the gisAssetsInvstmntMthd to set
	 */
	public void setGisAssetsInvstmntMthd(String gisAssetsInvstmntMthd) {
		this.gisAssetsInvstmntMthd = gisAssetsInvstmntMthd;
	}

	/**
	 * @return the gisAssetsGisCd
	 */
	public String getGisAssetsGisCd() {
		return gisAssetsGisCd;
	}

	/**
	 * @param gisAssetsGisCd the gisAssetsGisCd to set
	 */
	public void setGisAssetsGisCd(String gisAssetsGisCd) {
		this.gisAssetsGisCd = gisAssetsGisCd;
	}

	/**
	 * @return the gisAssetsRealRentAr
	 */
	public String getGisAssetsRealRentAr() {
		return gisAssetsRealRentAr;
	}

	/**
	 * @param gisAssetsRealRentAr the gisAssetsRealRentAr to set
	 */
	public void setGisAssetsRealRentAr(String gisAssetsRealRentAr) {
		this.gisAssetsRealRentAr = gisAssetsRealRentAr;
	}

	/**
	 * @return the drwLstRegistYear
	 */
	public String getDrwLstRegistYear() {
		return drwLstRegistYear;
	}

	/**
	 * @param drwLstRegistYear the drwLstRegistYear to set
	 */
	public void setDrwLstRegistYear(String drwLstRegistYear) {
		this.drwLstRegistYear = drwLstRegistYear;
	}

	/**
	 * @return the drwLstSeq
	 */
	public String getDrwLstSeq() {
		return drwLstSeq;
	}

	/**
	 * @param drwLstSeq the drwLstSeq to set
	 */
	public void setDrwLstSeq(String drwLstSeq) {
		this.drwLstSeq = drwLstSeq;
	}

	/**
	 * @return the gisAssetsValAmt
	 */
	public String getGisAssetsValAmt() {
		return gisAssetsValAmt;
	}

	/**
	 * @param gisAssetsValAmt the gisAssetsValAmt to set
	 */
	public void setGisAssetsValAmt(String gisAssetsValAmt) {
		this.gisAssetsValAmt = gisAssetsValAmt;
	}

	/**
	 * @return the gisAssetsValInqireDt
	 */
	public String getGisAssetsValInqireDt() {
		return gisAssetsValInqireDt;
	}

	/**
	 * @param gisAssetsValInqireDt the gisAssetsValInqireDt to set
	 */
	public void setGisAssetsValInqireDt(String gisAssetsValInqireDt) {
		this.gisAssetsValInqireDt = gisAssetsValInqireDt;
	}

	/**
	 * @return the erpAssetsSeCd
	 */
	public String getErpAssetsCls() {
		return erpAssetsCls;
	}

	/**
	 * @param erpAssetsSeCd the erpAssetsSeCd to set
	 */
	public void setErpAssetsCls(String erpAssetsCls) {
		this.erpAssetsCls = erpAssetsCls;
	}

	/**
	 * @return the erpAssetsNo
	 */
	public String getErpAssetsNo() {
		return erpAssetsNo;
	}

	/**
	 * @param erpAssetsNo the erpAssetsNo to set
	 */
	public void setErpAssetsNo(String erpAssetsNo) {
		this.erpAssetsNo = erpAssetsNo;
	}

	/**
	 * @return the erpAssetsNoSeq
	 */
	public String getErpAssetsNoSeq() {
		return erpAssetsNoSeq;
	}

	/**
	 * @param erpAssetsNoSeq the erpAssetsNoSeq to set
	 */
	public void setErpAssetsNoSeq(String erpAssetsNoSeq) {
		this.erpAssetsNoSeq = erpAssetsNoSeq;
	}

	/**
	 * @return the erpAssetsDisuseRegistYn
	 */
	public String getErpAssetsDisuseRegistYn() {
		return erpAssetsDisuseRegistYn;
	}

	/**
	 * @param erpAssetsDisuseRegistYn the erpAssetsDisuseRegistYn to set
	 */
	public void setErpAssetsDisuseRegistYn(String erpAssetsDisuseRegistYn) {
		this.erpAssetsDisuseRegistYn = erpAssetsDisuseRegistYn;
	}

	/**
	 * @return the erpAssetsDisuseRsn
	 */
	public String getErpAssetsDisuseRsn() {
		return erpAssetsDisuseRsn;
	}

	/**
	 * @param erpAssetsDisuseRsn the erpAssetsDisuseRsn to set
	 */
	public void setErpAssetsDisuseRsn(String erpAssetsDisuseRsn) {
		this.erpAssetsDisuseRsn = erpAssetsDisuseRsn;
	}

	/**
	 * @return the sGisAssetsCd
	 */
	public String getsGisAssetsCd() {
		return sGisAssetsCd;
	}

	/**
	 * @param sGisAssetsCd the sGisAssetsCd to set
	 */
	public void setsGisAssetsCd(String sGisAssetsCd) {
		this.sGisAssetsCd = sGisAssetsCd;
	}

	/**
	 * @return the sGisAssetsNm
	 */
	public String getsGisAssetsNm() {
		return sGisAssetsNm;
	}

	/**
	 * @param sGisAssetsNm the sGisAssetsNm to set
	 */
	public void setsGisAssetsNm(String sGisAssetsNm) {
		this.sGisAssetsNm = sGisAssetsNm;
	}

	public String getGisAssetsQuayCd() {
		return gisAssetsQuayCd;
	}

	public void setGisAssetsQuayCd(String gisAssetsQuayCd) {
		this.gisAssetsQuayCd = gisAssetsQuayCd;
	}

	/**
	 * @return the operDeptCd
	 */
	public String getOperDeptCd() {
		return operDeptCd;
	}

	/**
	 * @param operDeptCd the operDeptCd to set
	 */
	public void setOperDeptCd(String operDeptCd) {
		this.operDeptCd = operDeptCd;
	}

	/**
	 * @return the mngDeptCd
	 */
	public String getMngDeptCd() {
		return mngDeptCd;
	}

	/**
	 * @param mngDeptCd the mngDeptCd to set
	 */
	public void setMngDeptCd(String mngDeptCd) {
		this.mngDeptCd = mngDeptCd;
	}

	public String getGisAssetsBupjungdongCd() {
		return gisAssetsBupjungdongCd;
	}

	public void setGisAssetsBupjungdongCd(String gisAssetsBupjungdongCd) {
		this.gisAssetsBupjungdongCd = gisAssetsBupjungdongCd;
	}

	/**
	 * @return the prdlstSe
	 */
	public String getPrdlstSe() {
		return prdlstSe;
	}

	/**
	 * @param prdlstSe the prdlstSe to set
	 */
	public void setPrdlstSe(String prdlstSe) {
		this.prdlstSe = prdlstSe;
	}

	/**
	 * @return the fsse
	 */
	public String getFsse() {
		return fsse;
	}

	/**
	 * @param fsse the fsse to set
	 */
	public void setFsse(String fsse) {
		this.fsse = fsse;
	}

	/**
	 * @return the regstrAmount
	 */
	public String getRegstrAmount() {
		return regstrAmount;
	}

	/**
	 * @param regstrAmount the regstrAmount to set
	 */
	public void setRegstrAmount(String regstrAmount) {
		this.regstrAmount = regstrAmount;
	}

	/**
	 * @return the nowRegstrAmount
	 */
	public String getNowRegstrAmount() {
		return nowRegstrAmount;
	}

	/**
	 * @param nowRegstrAmount the nowRegstrAmount to set
	 */
	public void setNowRegstrAmount(String nowRegstrAmount) {
		this.nowRegstrAmount = nowRegstrAmount;
	}

	/**
	 * @return the invstmntAmount
	 */
	public String getInvstmntAmount() {
		return invstmntAmount;
	}

	/**
	 * @param invstmntAmount the invstmntAmount to set
	 */
	public void setInvstmntAmount(String invstmntAmount) {
		this.invstmntAmount = invstmntAmount;
	}

	/**
	 * @return the mktcStdAm
	 */
	public String getMktcStdAm() {
		return mktcStdAm;
	}

	/**
	 * @param mktcStdAm the mktcStdAm to set
	 */
	public void setMktcStdAm(String mktcStdAm) {
		this.mktcStdAm = mktcStdAm;
	}

	/**
	 * @return the olnlp
	 */
	public String getOlnlp() {
		return olnlp;
	}

	/**
	 * @param olnlp the olnlp to set
	 */
	public void setOlnlp(String olnlp) {
		this.olnlp = olnlp;
	}

	/**
	 * @return the totar
	 */
	public String getTotar() {
		return totar;
	}

	/**
	 * @param totar the totar to set
	 */
	public void setTotar(String totar) {
		this.totar = totar;
	}

	/**
	 * @return the qy
	 */
	public String getQy() {
		return qy;
	}

	/**
	 * @param qy the qy to set
	 */
	public void setQy(String qy) {
		this.qy = qy;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the mainFclts
	 */
	public String getMainFclts() {
		return mainFclts;
	}

	/**
	 * @param mainFclts the mainFclts to set
	 */
	public void setMainFclts(String mainFclts) {
		this.mainFclts = mainFclts;
	}

	/**
	 * @return the bgnnPrmisnPdBegin
	 */
	public String getBgnnPrmisnPdBegin() {
		return bgnnPrmisnPdBegin;
	}

	/**
	 * @param bgnnPrmisnPdBegin the bgnnPrmisnPdBegin to set
	 */
	public void setBgnnPrmisnPdBegin(String bgnnPrmisnPdBegin) {
		this.bgnnPrmisnPdBegin = bgnnPrmisnPdBegin;
	}

	/**
	 * @return the bgnnPrmisnPdEnd
	 */
	public String getBgnnPrmisnPdEnd() {
		return bgnnPrmisnPdEnd;
	}

	/**
	 * @param bgnnPrmisnPdEnd the bgnnPrmisnPdEnd to set
	 */
	public void setBgnnPrmisnPdEnd(String bgnnPrmisnPdEnd) {
		this.bgnnPrmisnPdEnd = bgnnPrmisnPdEnd;
	}

	/**
	 * @return the changePrmisnPdBegin
	 */
	public String getChangePrmisnPdBegin() {
		return changePrmisnPdBegin;
	}

	/**
	 * @param changePrmisnPdBegin the changePrmisnPdBegin to set
	 */
	public void setChangePrmisnPdBegin(String changePrmisnPdBegin) {
		this.changePrmisnPdBegin = changePrmisnPdBegin;
	}

	/**
	 * @return the changePrmisnPdEnd
	 */
	public String getChangePrmisnPdEnd() {
		return changePrmisnPdEnd;
	}

	/**
	 * @param changePrmisnPdEnd the changePrmisnPdEnd to set
	 */
	public void setChangePrmisnPdEnd(String changePrmisnPdEnd) {
		this.changePrmisnPdEnd = changePrmisnPdEnd;
	}

	/**
	 * @return the gisAssetsLndcgr
	 */
	public String getGisAssetsLndcgr() {
		return gisAssetsLndcgr;
	}

	/**
	 * @param gisAssetsLndcgr the gisAssetsLndcgr to set
	 */
	public void setGisAssetsLndcgr(String gisAssetsLndcgr) {
		this.gisAssetsLndcgr = gisAssetsLndcgr;
	}


}
