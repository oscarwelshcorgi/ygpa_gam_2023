package egovframework.rte.ygpa.gam.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * @Class Name : GisAssetsCdFVO.java
 * @Description : GisAssetsCdF VO class
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-15
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public class GamPopupRoadInfoMngtVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;

    /** 검색 도로명 */ 
    private String sRoadNm;
    /** 검색 건물본번 */ 
    private String sMainNo;
    /** 검색 건물부번 */ 
    private String sSubNo;

    
    /** 도로명코드(PK) */ 
    private String roadCode;
    /** 도로명 */ 
    private String roadNm;
    /** 도로명로마자 */ 
    private String roadNmEng;
    /** 읍면동일련번호(PK) */ 
    private String emdNo;
    /** 시도명 */ 
    private String sidoNm;
    /** 시도 로마자 */ 
    private String sidoNmEng;
    /** 시군구명 */ 
    private String sigunguNm;
    /** 시군구 로마자 */ 
    private String sigunguNmEng;
    /** 읍면동명 */ 
    private String emdNm;
    /** 읍면동 로마자 */ 
    private String emdNmEng;
    /** 읍면동구분 */ 
    private String emdGubun;
    /** 읍면동코드 */ 
    private String emdCode;
    /** 사용여부 */ 
    private String useYn;
    /** 변경사유 */ 
    private String modPd;
    /** 변경이력정보 */ 
    private String modPdHist;
    /** 고시일자 */ 
    private String gosiDt;
    /** 말소일자 */ 
    private String depDt;
    /** 관리번호(PK) */ 
    private String jusoMngNo;
    /** 지하여부 */ 
    private String jihaYn;
    /** 건물본번 */ 
    private String mainNo;
    /** 건물부번 */ 
    private String subNo;
    /** 기초구역번호 */ 
    private String baseRgnNo;
    /** 변경사유코드 */ 
    private String modPdCd;
    /** 변경전도로명주소 */ 
    private String befModJuso;
    /** 상세주소부여 여부 */ 
    private String detAddrYn;
    /** 일련번호(PK) */ 
    private String jibunSn;
    /** 법정동코드 */ 
    private String bjdCode;
    /** 법정리명 */ 
    private String riNm;
    /** 산여부 */ 
    private String mtYn;
    /** 지번본번 */ 
    private String lnmNo;
    /** 지번부번 */ 
    private String lnmSub;
    /** 대표여부 */ 
    private String mainYn;
	/**
	 * @return the roadCode
	 */
	public String getRoadCode() {
		return roadCode;
	}
	/**
	 * @param roadCode the roadCode to set
	 */
	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}
	/**
	 * @return the roadNm
	 */
	public String getRoadNm() {
		return roadNm;
	}
	/**
	 * @param roadNm the roadNm to set
	 */
	public void setRoadNm(String roadNm) {
		this.roadNm = roadNm;
	}
	/**
	 * @return the roadNmEng
	 */
	public String getRoadNmEng() {
		return roadNmEng;
	}
	/**
	 * @param roadNmEng the roadNmEng to set
	 */
	public void setRoadNmEng(String roadNmEng) {
		this.roadNmEng = roadNmEng;
	}
	/**
	 * @return the emdNo
	 */
	public String getEmdNo() {
		return emdNo;
	}
	/**
	 * @param emdNo the emdNo to set
	 */
	public void setEmdNo(String emdNo) {
		this.emdNo = emdNo;
	}
	/**
	 * @return the sidoNm
	 */
	public String getSidoNm() {
		return sidoNm;
	}
	/**
	 * @param sidoNm the sidoNm to set
	 */
	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}
	/**
	 * @return the sidoNmEng
	 */
	public String getSidoNmEng() {
		return sidoNmEng;
	}
	/**
	 * @param sidoNmEng the sidoNmEng to set
	 */
	public void setSidoNmEng(String sidoNmEng) {
		this.sidoNmEng = sidoNmEng;
	}
	/**
	 * @return the sigunguNm
	 */
	public String getSigunguNm() {
		return sigunguNm;
	}
	/**
	 * @param sigunguNm the sigunguNm to set
	 */
	public void setSigunguNm(String sigunguNm) {
		this.sigunguNm = sigunguNm;
	}
	/**
	 * @return the sigunguNmEng
	 */
	public String getSigunguNmEng() {
		return sigunguNmEng;
	}
	/**
	 * @param sigunguNmEng the sigunguNmEng to set
	 */
	public void setSigunguNmEng(String sigunguNmEng) {
		this.sigunguNmEng = sigunguNmEng;
	}
	/**
	 * @return the emdNm
	 */
	public String getEmdNm() {
		return emdNm;
	}
	/**
	 * @param emdNm the emdNm to set
	 */
	public void setEmdNm(String emdNm) {
		this.emdNm = emdNm;
	}
	/**
	 * @return the emdNmEng
	 */
	public String getEmdNmEng() {
		return emdNmEng;
	}
	/**
	 * @param emdNmEng the emdNmEng to set
	 */
	public void setEmdNmEng(String emdNmEng) {
		this.emdNmEng = emdNmEng;
	}
	/**
	 * @return the emdGubun
	 */
	public String getEmdGubun() {
		return emdGubun;
	}
	/**
	 * @param emdGubun the emdGubun to set
	 */
	public void setEmdGubun(String emdGubun) {
		this.emdGubun = emdGubun;
	}
	/**
	 * @return the emdCode
	 */
	public String getEmdCode() {
		return emdCode;
	}
	/**
	 * @param emdCode the emdCode to set
	 */
	public void setEmdCode(String emdCode) {
		this.emdCode = emdCode;
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
	 * @return the modPd
	 */
	public String getModPd() {
		return modPd;
	}
	/**
	 * @param modPd the modPd to set
	 */
	public void setModPd(String modPd) {
		this.modPd = modPd;
	}
	/**
	 * @return the modPdHist
	 */
	public String getModPdHist() {
		return modPdHist;
	}
	/**
	 * @param modPdHist the modPdHist to set
	 */
	public void setModPdHist(String modPdHist) {
		this.modPdHist = modPdHist;
	}
	/**
	 * @return the gosiDt
	 */
	public String getGosiDt() {
		return gosiDt;
	}
	/**
	 * @param gosiDt the gosiDt to set
	 */
	public void setGosiDt(String gosiDt) {
		this.gosiDt = gosiDt;
	}
	/**
	 * @return the depDt
	 */
	public String getDepDt() {
		return depDt;
	}
	/**
	 * @param depDt the depDt to set
	 */
	public void setDepDt(String depDt) {
		this.depDt = depDt;
	}
	/**
	 * @return the jusoMngNo
	 */
	public String getJusoMngNo() {
		return jusoMngNo;
	}
	/**
	 * @param jusoMngNo the jusoMngNo to set
	 */
	public void setJusoMngNo(String jusoMngNo) {
		this.jusoMngNo = jusoMngNo;
	}
	/**
	 * @return the jihaYn
	 */
	public String getJihaYn() {
		return jihaYn;
	}
	/**
	 * @param jihaYn the jihaYn to set
	 */
	public void setJihaYn(String jihaYn) {
		this.jihaYn = jihaYn;
	}
	/**
	 * @return the mainNo
	 */
	public String getMainNo() {
		return mainNo;
	}
	/**
	 * @param mainNo the mainNo to set
	 */
	public void setMainNo(String mainNo) {
		this.mainNo = mainNo;
	}
	/**
	 * @return the subNo
	 */
	public String getSubNo() {
		return subNo;
	}
	/**
	 * @param subNo the subNo to set
	 */
	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}
	/**
	 * @return the baseRgnNo
	 */
	public String getBaseRgnNo() {
		return baseRgnNo;
	}
	/**
	 * @param baseRgnNo the baseRgnNo to set
	 */
	public void setBaseRgnNo(String baseRgnNo) {
		this.baseRgnNo = baseRgnNo;
	}
	/**
	 * @return the modPdCd
	 */
	public String getModPdCd() {
		return modPdCd;
	}
	/**
	 * @param modPdCd the modPdCd to set
	 */
	public void setModPdCd(String modPdCd) {
		this.modPdCd = modPdCd;
	}
	/**
	 * @return the befModJuso
	 */
	public String getBefModJuso() {
		return befModJuso;
	}
	/**
	 * @param befModJuso the befModJuso to set
	 */
	public void setBefModJuso(String befModJuso) {
		this.befModJuso = befModJuso;
	}
	/**
	 * @return the detAddrYn
	 */
	public String getDetAddrYn() {
		return detAddrYn;
	}
	/**
	 * @param detAddrYn the detAddrYn to set
	 */
	public void setDetAddrYn(String detAddrYn) {
		this.detAddrYn = detAddrYn;
	}
	/**
	 * @return the jibunSn
	 */
	public String getJibunSn() {
		return jibunSn;
	}
	/**
	 * @param jibunSn the jibunSn to set
	 */
	public void setJibunSn(String jibunSn) {
		this.jibunSn = jibunSn;
	}
	/**
	 * @return the bjdCode
	 */
	public String getBjdCode() {
		return bjdCode;
	}
	/**
	 * @param bjdCode the bjdCode to set
	 */
	public void setBjdCode(String bjdCode) {
		this.bjdCode = bjdCode;
	}
	/**
	 * @return the riNm
	 */
	public String getRiNm() {
		return riNm;
	}
	/**
	 * @param riNm the riNm to set
	 */
	public void setRiNm(String riNm) {
		this.riNm = riNm;
	}
	/**
	 * @return the mtYn
	 */
	public String getMtYn() {
		return mtYn;
	}
	/**
	 * @param mtYn the mtYn to set
	 */
	public void setMtYn(String mtYn) {
		this.mtYn = mtYn;
	}
	/**
	 * @return the lnmNo
	 */
	public String getLnmNo() {
		return lnmNo;
	}
	/**
	 * @param lnmNo the lnmNo to set
	 */
	public void setLnmNo(String lnmNo) {
		this.lnmNo = lnmNo;
	}
	/**
	 * @return the lnmSub
	 */
	public String getLnmSub() {
		return lnmSub;
	}
	/**
	 * @param lnmSub the lnmSub to set
	 */
	public void setLnmSub(String lnmSub) {
		this.lnmSub = lnmSub;
	}
	/**
	 * @return the mainYn
	 */
	public String getMainYn() {
		return mainYn;
	}
	/**
	 * @param mainYn the mainYn to set
	 */
	public void setMainYn(String mainYn) {
		this.mainYn = mainYn;
	}
	/**
	 * @return the sRoadNm
	 */
	public String getsRoadNm() {
		return sRoadNm;
	}
	/**
	 * @param sRoadNm the sRoadNm to set
	 */
	public void setsRoadNm(String sRoadNm) {
		this.sRoadNm = sRoadNm;
	}
	/**
	 * @return the sMainNo
	 */
	public String getsMainNo() {
		return sMainNo;
	}
	/**
	 * @param sMainNo the sMainNo to set
	 */
	public void setsMainNo(String sMainNo) {
		this.sMainNo = sMainNo;
	}
	/**
	 * @return the sSubNo
	 */
	public String getsSubNo() {
		return sSubNo;
	}
	/**
	 * @param sSubNo the sSubNo to set
	 */
	public void setsSubNo(String sSubNo) {
		this.sSubNo = sSubNo;
	}



}
