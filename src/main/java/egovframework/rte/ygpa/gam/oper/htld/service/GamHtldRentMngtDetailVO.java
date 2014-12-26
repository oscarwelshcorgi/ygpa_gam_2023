package egovframework.rte.ygpa.gam.oper.htld.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GamHtldRentMngtDetailVO.java
 * @Description : 배후단지사용목록관리 상세 VO class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamHtldRentMngtDetailVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;

	/** 자산사용순번 */
	private String assetsUsageSeq;

	/** 항코드 */
	private String prtAtCode;

	/** 관리년도 */
	private String mngYear;

	/** 관리번호 */
	private String mngNo;

	/** 관리횟수 */
	private String mngCnt;

	/** 부두그룹코드 */
	private String quayGroupCd;

	/** 부두코드 */
	private String quayCd;

	/** 자산항코드 */
	private String gisAssetsPrtAtCode;

	/** 자산코드 */
	private String gisAssetsCd;

	/** 자산부코드 */
	private String gisAssetsSubCd;

	/** 사용면적 */
	private String usageAr;

	/** 사용시작일 */
	private String usagePdFrom;

	/** 사용종료일 */
	private String usagePdTo;

	/** 사용용도 */
	private String usagePurps;

	/** 사용내역 */
	private String usageDtls;

	/** 사용용도 코드 */
	private String usagePrposCd;

	/** 면제구분코드 */
	private String exemptSe;

	/** 면제사유코드 */
	private String exemptRsnCd;

	/** 면제사유 */
	private String exemptRsn;

	/** 면제시작일자 */
	private String exemptPdFrom;

	/** 면제종료일자 */
	private String exemptPdTo;

	/** 산출내역 */
	private String computDtls;

	/** 공시지가 */
	private String olnlp;

	/** 적용단가 */
	private String applcPrice;

	/** 적용요율 */
	private String applcTariff;

	/** 적용방법 */
	private String applcMth;

	/** 포장여부 */
	private String packSe;

	/** 업체구분 */
	private String entrpsSe;

	/** 사용료계산구분 */
	private String feeCalcSe;

	/** 면제계산구분 */
	private String rdcxptFeeCalcSe;

	/** 면제금액 */
	private String rdcxptFee;

	/** 사용료 */
	private String fee;

	/** 사용종료일자 */
	private String trmnatDt;

	/** 사용종료사유 */
	private String trmnatRsn;

	/** GIS 코드 */
	private String gisCd;

	/** 등록자 */
	private String regUsr;

	/** 등록일시 */
	private String registDt;

	/** 수정자 */
	private String updUsr;

	/** 수정일시 */
	private String updtDt;

	public String getAssetsUsageSeq() {
		return assetsUsageSeq;
	}

	public void setAssetsUsageSeq(String assetsUsageSeq) {
		this.assetsUsageSeq = assetsUsageSeq;
	}

	public String getPrtAtCode() {
		return prtAtCode;
	}

	public void setPrtAtCode(String prtAtCode) {
		this.prtAtCode = prtAtCode;
	}

	public String getMngYear() {
		return mngYear;
	}

	public void setMngYear(String mngYear) {
		this.mngYear = mngYear;
	}

	public String getMngNo() {
		return mngNo;
	}

	public void setMngNo(String mngNo) {
		this.mngNo = mngNo;
	}

	public String getMngCnt() {
		return mngCnt;
	}

	public void setMngCnt(String mngCnt) {
		this.mngCnt = mngCnt;
	}

	public String getQuayGroupCd() {
		return quayGroupCd;
	}

	public void setQuayGroupCd(String quayGroupCd) {
		this.quayGroupCd = quayGroupCd;
	}

	public String getQuayCd() {
		return quayCd;
	}

	public void setQuayCd(String quayCd) {
		this.quayCd = quayCd;
	}

	public String getGisAssetsPrtAtCode() {
		return gisAssetsPrtAtCode;
	}

	public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
		this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
	}

	public String getGisAssetsCd() {
		return gisAssetsCd;
	}

	public void setGisAssetsCd(String gisAssetsCd) {
		this.gisAssetsCd = gisAssetsCd;
	}

	public String getGisAssetsSubCd() {
		return gisAssetsSubCd;
	}

	public void setGisAssetsSubCd(String gisAssetsSubCd) {
		this.gisAssetsSubCd = gisAssetsSubCd;
	}

	public String getUsageAr() {
		return usageAr;
	}

	public void setUsageAr(String usageAr) {
		this.usageAr = usageAr;
	}

	public String getUsagePdFrom() {
		return usagePdFrom;
	}

	public void setUsagePdFrom(String usagePdFrom) {
		this.usagePdFrom = usagePdFrom;
	}

	public String getUsagePdTo() {
		return usagePdTo;
	}

	public void setUsagePdTo(String usagePdTo) {
		this.usagePdTo = usagePdTo;
	}

	public String getUsagePurps() {
		return usagePurps;
	}

	public void setUsagePurps(String usagePurps) {
		this.usagePurps = usagePurps;
	}

	public String getUsageDtls() {
		return usageDtls;
	}

	public void setUsageDtls(String usageDtls) {
		this.usageDtls = usageDtls;
	}

	public String getUsagePrposCd() {
		return usagePrposCd;
	}

	public void setUsagePrposCd(String usagePrposCd) {
		this.usagePrposCd = usagePrposCd;
	}

	public String getExemptSe() {
		return exemptSe;
	}

	public void setExemptSe(String exemptSe) {
		this.exemptSe = exemptSe;
	}

	public String getExemptRsnCd() {
		return exemptRsnCd;
	}

	public void setExemptRsnCd(String exemptRsnCd) {
		this.exemptRsnCd = exemptRsnCd;
	}

	public String getExemptRsn() {
		return exemptRsn;
	}

	public void setExemptRsn(String exemptRsn) {
		this.exemptRsn = exemptRsn;
	}

	public String getExemptPdFrom() {
		return exemptPdFrom;
	}

	public void setExemptPdFrom(String exemptPdFrom) {
		this.exemptPdFrom = exemptPdFrom;
	}

	public String getExemptPdTo() {
		return exemptPdTo;
	}

	public void setExemptPdTo(String exemptPdTo) {
		this.exemptPdTo = exemptPdTo;
	}

	public String getComputDtls() {
		return computDtls;
	}

	public void setComputDtls(String computDtls) {
		this.computDtls = computDtls;
	}

	public String getOlnlp() {
		return olnlp;
	}

	public void setOlnlp(String olnlp) {
		this.olnlp = olnlp;
	}

	public String getApplcPrice() {
		return applcPrice;
	}

	public void setApplcPrice(String applcPrice) {
		this.applcPrice = applcPrice;
	}

	public String getApplcTariff() {
		return applcTariff;
	}

	public void setApplcTariff(String applcTariff) {
		this.applcTariff = applcTariff;
	}

	public String getApplcMth() {
		return applcMth;
	}

	public void setApplcMth(String applcMth) {
		this.applcMth = applcMth;
	}

	public String getPackSe() {
		return packSe;
	}

	public void setPackSe(String packSe) {
		this.packSe = packSe;
	}

	public String getEntrpsSe() {
		return entrpsSe;
	}

	public void setEntrpsSe(String entrpsSe) {
		this.entrpsSe = entrpsSe;
	}

	public String getFeeCalcSe() {
		return feeCalcSe;
	}

	public void setFeeCalcSe(String feeCalcSe) {
		this.feeCalcSe = feeCalcSe;
	}

	public String getRdcxptFeeCalcSe() {
		return rdcxptFeeCalcSe;
	}

	public void setRdcxptFeeCalcSe(String rdcxptFeeCalcSe) {
		this.rdcxptFeeCalcSe = rdcxptFeeCalcSe;
	}

	public String getRdcxptFee() {
		return rdcxptFee;
	}

	public void setRdcxptFee(String rdcxptFee) {
		this.rdcxptFee = rdcxptFee;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTrmnatDt() {
		return trmnatDt;
	}

	public void setTrmnatDt(String trmnatDt) {
		this.trmnatDt = trmnatDt;
	}

	public String getTrmnatRsn() {
		return trmnatRsn;
	}

	public void setTrmnatRsn(String trmnatRsn) {
		this.trmnatRsn = trmnatRsn;
	}

	public String getGisCd() {
		return gisCd;
	}

	public void setGisCd(String gisCd) {
		this.gisCd = gisCd;
	}

	public String getRegUsr() {
		return regUsr;
	}

	public void setRegUsr(String regUsr) {
		this.regUsr = regUsr;
	}

	public String getRegistDt() {
		return registDt;
	}

	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}

	public String getUpdUsr() {
		return updUsr;
	}

	public void setUpdUsr(String updUsr) {
		this.updUsr = updUsr;
	}

	public String getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

}
