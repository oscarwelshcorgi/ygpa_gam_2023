package egovframework.rte.ygpa.gam.code.service;

import java.math.BigDecimal;
import java.util.Date;

public class GisAssetsCodeVO {
	
	
    /** GIS 자산 코드 */
    private String gisAssetsCd;

    /** GIS 자산 항코드 */
    private String gisAssetsPrtAtCode;

    /** GIS 자산 SUB 코드 */
    private String gisAssetsSubCd;

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

    /** GIS 자산 면적 */
    private BigDecimal gisAssetsAr;

    /** GIS 자산 사용 여부 */
    private String gisAssetsUsageYn;

    /** GIS 자산 취득가액 */
    private Long gisAssetsAcqPri;

    /** GIS 자산 규격 */
    private String gisAssetsStndrd;

    /** GIS 자산 준공년도 */
    private String gisAssetsBlddate;

    /** GIS 자산 준공 일자 */
    private Date gisAssetsBldDt;

    /** GIS 자산 비고 */
    private String gisAssetsRm;

    /** 등록자 */
    private String regUsr;

    /** 등록일자 */
    private Date registdt;

    /** 수정자 */
    private String updUsr;

    /** 수정일자 */
    private Date updtdt;

    /** GIS 자산 부두 그룹 코드 */
    private String gisAssetsQuayGroupCd;

    /** GIS 자산 위치 코드 */
    private String gisAssetsLocCd;

    /** GIS 자산 구분 코드 */
    private String gisAssetsSeCd;

    /** GIS 자산 재산 구분 코드 */
    private String gisAssetsPrprtySeCd;

    /** GIS 자산 출자 방식 */
    private String gisAssetsInvstmntMthd;

    /** GIS 자산 GIS 코드 */
    private Long gisAssetsGisCd;

    /** GIS 자산 실제 임대 면적 */
    private BigDecimal gisAssetsRealRentAr;

    /** 도면 목록 등록 년도 */
    private String drwLstRegistYear;

    /** 도면 목록 순번 */
    private String drwLstSeq;

    /** GIS 자산 가치 금액 */
    private Long gisAssetsValAmt;

    /** GIS 자산 가치 조회 일자 */
    private String gisAssetsValInqireDt;

    /** ERP 자산 구분 코드 */
    private String erpAssetsSeCd;

    /** ERP 자산 번호 */
    private Long erpAssetsNo;

    /** ERP 자산 번호 순번 */
    private Long erpAssetsNoSeq;

    /** ERP 자산 폐기 등록 여부 */
    private String erpAssetsDisuseRegistYn;

    /** ERP 자산 폐기 사유 */
    private String erpAssetsDisuseRsn;


    public String getGisAssetsCd() {
        return gisAssetsCd;
    }

    public void setGisAssetsCd(String gisAssetsCd) {
        this.gisAssetsCd = gisAssetsCd;
    }

    public String getGisAssetsPrtAtCode() {
        return gisAssetsPrtAtCode;
    }

    public void setGisAssetsPrtAtCode(String gisAssetsPrtAtCode) {
        this.gisAssetsPrtAtCode = gisAssetsPrtAtCode;
    }

    public String getGisAssetsSubCd() {
        return gisAssetsSubCd;
    }

    public void setGisAssetsSubCd(String gisAssetsSubCd) {
        this.gisAssetsSubCd = gisAssetsSubCd;
    }

    public String getGisAssetsNm() {
        return gisAssetsNm;
    }

    public void setGisAssetsNm(String gisAssetsNm) {
        this.gisAssetsNm = gisAssetsNm;
    }

    public String getGisAssetsMngDeptCd() {
        return gisAssetsMngDeptCd;
    }

    public void setGisAssetsMngDeptCd(String gisAssetsMngDeptCd) {
        this.gisAssetsMngDeptCd = gisAssetsMngDeptCd;
    }

    public String getGisAssetsOperDeptCd() {
        return gisAssetsOperDeptCd;
    }

    public void setGisAssetsOperDeptCd(String gisAssetsOperDeptCd) {
        this.gisAssetsOperDeptCd = gisAssetsOperDeptCd;
    }

    public String getGisAssetsLocplc() {
        return gisAssetsLocplc;
    }

    public void setGisAssetsLocplc(String gisAssetsLocplc) {
        this.gisAssetsLocplc = gisAssetsLocplc;
    }

    public String getGisAssetsLnm() {
        return gisAssetsLnm;
    }

    public void setGisAssetsLnm(String gisAssetsLnm) {
        this.gisAssetsLnm = gisAssetsLnm;
    }

    public String getGisAssetsLnmSub() {
        return gisAssetsLnmSub;
    }

    public void setGisAssetsLnmSub(String gisAssetsLnmSub) {
        this.gisAssetsLnmSub = gisAssetsLnmSub;
    }

    public BigDecimal getGisAssetsAr() {
        return gisAssetsAr;
    }

    public void setGisAssetsAr(BigDecimal gisAssetsAr) {
        this.gisAssetsAr = gisAssetsAr;
    }

    public String getGisAssetsUsageYn() {
        return gisAssetsUsageYn;
    }

    public void setGisAssetsUsageYn(String gisAssetsUsageYn) {
        this.gisAssetsUsageYn = gisAssetsUsageYn;
    }

    public Long getGisAssetsAcqPri() {
        return gisAssetsAcqPri;
    }

    public void setGisAssetsAcqPri(Long gisAssetsAcqPri) {
        this.gisAssetsAcqPri = gisAssetsAcqPri;
    }

    public String getGisAssetsStndrd() {
        return gisAssetsStndrd;
    }

    public void setGisAssetsStndrd(String gisAssetsStndrd) {
        this.gisAssetsStndrd = gisAssetsStndrd;
    }

    public String getGisAssetsBlddate() {
        return gisAssetsBlddate;
    }

    public void setGisAssetsBlddate(String gisAssetsBlddate) {
        this.gisAssetsBlddate = gisAssetsBlddate;
    }

    public Date getGisAssetsBldDt() {
        return gisAssetsBldDt;
    }

    public void setGisAssetsBldDt(Date gisAssetsBldDt) {
        this.gisAssetsBldDt = gisAssetsBldDt;
    }

    public String getGisAssetsRm() {
        return gisAssetsRm;
    }

    public void setGisAssetsRm(String gisAssetsRm) {
        this.gisAssetsRm = gisAssetsRm;
    }

    public String getRegUsr() {
        return regUsr;
    }

    public void setRegUsr(String regUsr) {
        this.regUsr = regUsr;
    }

    public Date getRegistdt() {
        return registdt;
    }

    public void setRegistdt(Date registdt) {
        this.registdt = registdt;
    }

    public String getUpdUsr() {
        return updUsr;
    }

    public void setUpdUsr(String updUsr) {
        this.updUsr = updUsr;
    }

    public Date getUpdtdt() {
        return updtdt;
    }

    public void setUpdtdt(Date updtdt) {
        this.updtdt = updtdt;
    }

    public String getGisAssetsQuayGroupCd() {
        return gisAssetsQuayGroupCd;
    }

    public void setGisAssetsQuayGroupCd(String gisAssetsQuayGroupCd) {
        this.gisAssetsQuayGroupCd = gisAssetsQuayGroupCd;
    }

    public String getGisAssetsLocCd() {
        return gisAssetsLocCd;
    }

    public void setGisAssetsLocCd(String gisAssetsLocCd) {
        this.gisAssetsLocCd = gisAssetsLocCd;
    }

    public String getGisAssetsSeCd() {
        return gisAssetsSeCd;
    }

    public void setGisAssetsSeCd(String gisAssetsSeCd) {
        this.gisAssetsSeCd = gisAssetsSeCd;
    }

    public String getGisAssetsPrprtySeCd() {
        return gisAssetsPrprtySeCd;
    }

    public void setGisAssetsPrprtySeCd(String gisAssetsPrprtySeCd) {
        this.gisAssetsPrprtySeCd = gisAssetsPrprtySeCd;
    }

    public String getGisAssetsInvstmntMthd() {
        return gisAssetsInvstmntMthd;
    }

    public void setGisAssetsInvstmntMthd(String gisAssetsInvstmntMthd) {
        this.gisAssetsInvstmntMthd = gisAssetsInvstmntMthd;
    }

    public Long getGisAssetsGisCd() {
        return gisAssetsGisCd;
    }

    public void setGisAssetsGisCd(Long gisAssetsGisCd) {
        this.gisAssetsGisCd = gisAssetsGisCd;
    }

    public BigDecimal getGisAssetsRealRentAr() {
        return gisAssetsRealRentAr;
    }

    public void setGisAssetsRealRentAr(BigDecimal gisAssetsRealRentAr) {
        this.gisAssetsRealRentAr = gisAssetsRealRentAr;
    }

    public String getDrwLstRegistYear() {
        return drwLstRegistYear;
    }

    public void setDrwLstRegistYear(String drwLstRegistYear) {
        this.drwLstRegistYear = drwLstRegistYear;
    }

    public String getDrwLstSeq() {
        return drwLstSeq;
    }

    public void setDrwLstSeq(String drwLstSeq) {
        this.drwLstSeq = drwLstSeq;
    }

    public Long getGisAssetsValAmt() {
        return gisAssetsValAmt;
    }

    public void setGisAssetsValAmt(Long gisAssetsValAmt) {
        this.gisAssetsValAmt = gisAssetsValAmt;
    }

    public String getGisAssetsValInqireDt() {
        return gisAssetsValInqireDt;
    }

    public void setGisAssetsValInqireDt(String gisAssetsValInqireDt) {
        this.gisAssetsValInqireDt = gisAssetsValInqireDt;
    }

    public String getErpAssetsSeCd() {
        return erpAssetsSeCd;
    }

    public void setErpAssetsSeCd(String erpAssetsSeCd) {
        this.erpAssetsSeCd = erpAssetsSeCd;
    }

    public Long getErpAssetsNo() {
        return erpAssetsNo;
    }

    public void setErpAssetsNo(Long erpAssetsNo) {
        this.erpAssetsNo = erpAssetsNo;
    }

    public Long getErpAssetsNoSeq() {
        return erpAssetsNoSeq;
    }

    public void setErpAssetsNoSeq(Long erpAssetsNoSeq) {
        this.erpAssetsNoSeq = erpAssetsNoSeq;
    }

    public String getErpAssetsDisuseRegistYn() {
        return erpAssetsDisuseRegistYn;
    }

    public void setErpAssetsDisuseRegistYn(String erpAssetsDisuseRegistYn) {
        this.erpAssetsDisuseRegistYn = erpAssetsDisuseRegistYn;
    }

    public String getErpAssetsDisuseRsn() {
        return erpAssetsDisuseRsn;
    }

    public void setErpAssetsDisuseRsn(String erpAssetsDisuseRsn) {
        this.erpAssetsDisuseRsn = erpAssetsDisuseRsn;
    }
}