/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 17.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamInfoCommFcltySpecMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String gisPrtFcltyCd;			// GIS 항만 시설 코드
	private	String gisPrtFcltyCdDisplay;	// GIS 항만 시설 코드 DISPLAY
	private	String gisPrtFcltySeq;			// GIS 항만 시설 순번
	private	String gisAssetsPrtAtCode;		// GIS 자산 항코드
	private	String gisAssetsCd;				// GIS 자산 코드
	private	String gisAssetsSubCd;			// GIS 자산 SUB 코드
	private	String prtFcltySe;				// 항만시설 구분
	private	String prtFcltyNm;				// 항만시설 명
	private	String prtFcltyStndrd;			// 항만시설 규격
	private	String prtFcltyUnit;			// 항만시설 단위
	private	String prtFcltyInstlDt;			// 항만시설 설치 일자
	private	String prtFcltyChangeDt;		// 항만시설 변경 일자
	private	String prtFcltyMngEntrpsCd;		// 항만시설 관리 업체 코드
	private	String prtFcltyGisCd;			// 항만시설 GIS 코드
	private	String laCrdnt;					// 위도 좌표
	private	String loCrdnt;					// 경도 좌표
	private	String prtFcltyAr;				// 항만시설 면적
	private	String lat;						// 위도
	private	String lng;						// 경도
	private	String prtFcltyExprDt;			// 항만시설 만료일자
	private	String prtPrtFcltyCnt;			// 항만시설 수량
	private	String prtPrtFcltyMnger;		// 항만시설 담당
	private	String gisPrtFcltyCdSub;		// GIS 항만 시설 코드 서브
	private	String fcltsMngNo;				// 시설물 관리 번호
	private	String fcltsMngGroupNo;			// 시설물 관리 그룹 번호
	private	String gisPrtFcltyCdNm;			// GIS 항만 시설 코드 명
	private	String prtFcltySeNm;			// 항만시설 구분 명
	private	String gisAssetsPrtAtCodeNm;	// GIS 자산 항코드 명
	private	String fcltsMngGroupNm;			// 시설물 관리 그룹 명
	private	String prtFcltyMngEntrpsNm;		// 항만시설 관리 업체 명
	private	String gisAssetsLocCd;			// GIS 자산 위치 코드
	private	String gisAssetsNm;				// GIS 자산 명
	private	String gisAssetsLocplcLnm;		// GIS 자산 소재지
	private	String gisAssetsLocNm;			// GIS 자산 위치 명
	private	String loc;						// 위치
	private	String infoCommFcltsClCd;		// 정보 통신 시설물 분류 코드
	private	String infoCommFcltsClCdNm;		// 정보 통신 시설물 분류 코드 명
	private	String stndrd;					// 규격
	private	String instlSe;					// 설치 구분
	private	String instlNo;					// 설치 번호
	private	String func;					// 기능
	private	String ctrlMthd;				// 제어 방식
	private	String instlStndrd;				// 설치 규격
	private	String instlHt;					// 설치 높이
	private	String lampFmt;					// LAMP 형식
	private	String prdlstNm;				// 품목 명
	private	String model;					// 모델
	private	String ptlrDtls;				// 세부 내역
	private	String qy;						// 수량
	private	String maker;					// 제조사
	private	String acqAmt;					// 취득 금액
	private	String instlDt;					// 설치 일자
	private	String archFcltsMngNo;			// 건축 시설물 관리 번호
	private	String archFcltsNm;				// 건축 시설물 명
	private	String rm;						// 비고
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시
	private	String prtFcltyLoc;				// 항만 시설 소재지
	private	String fcltySpecExistYn;		// 시설제원 존재 여부
	private	String sFcltsMngNo;				// 검색 시설물 관리 번호
	private	String sLoc;					// 검색 위치
	private	String sFcltsClCd;				// 검색 시설물 분류 코드
	private	String sPrtFcltyNm;				// 검색 항만시설 명
	private	String sPrtAtCode;				// 검색 항구분
	private	String sFcltsMngGroupNo;		// 검색 시설물 관리 그룹 번호
	private	String sGisPrtFcltyCd;			// 검색 GIS 시설 코드
	private	String totalCount;				// 조회 자료 수
	private	String sumQy;					// 수량 합계
	private	String sumAcqAmt;				// 취득 금액 합계

	/**
	 * @return the gisPrtFcltyCd
	 */
	public String getGisPrtFcltyCd() {
		return gisPrtFcltyCd;
	}
	/**
	 * @param gisPrtFcltyCd the gisPrtFcltyCd to set
	 */
	public void setGisPrtFcltyCd(String gisPrtFcltyCd) {
		this.gisPrtFcltyCd = gisPrtFcltyCd;
	}
	/**
	 * @return the gisPrtFcltyCdDisplay
	 */
	public String getGisPrtFcltyCdDisplay() {
		return gisPrtFcltyCdDisplay;
	}
	/**
	 * @param gisPrtFcltyCdDisplay the gisPrtFcltyCdDisplay to set
	 */
	public void setGisPrtFcltyCdDisplay(String gisPrtFcltyCdDisplay) {
		this.gisPrtFcltyCdDisplay = gisPrtFcltyCdDisplay;
	}
	/**
	 * @return the gisPrtFcltySeq
	 */
	public String getGisPrtFcltySeq() {
		return gisPrtFcltySeq;
	}
	/**
	 * @param gisPrtFcltySeq the gisPrtFcltySeq to set
	 */
	public void setGisPrtFcltySeq(String gisPrtFcltySeq) {
		this.gisPrtFcltySeq = gisPrtFcltySeq;
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
	 * @return the prtFcltySe
	 */
	public String getPrtFcltySe() {
		return prtFcltySe;
	}
	/**
	 * @param prtFcltySe the prtFcltySe to set
	 */
	public void setPrtFcltySe(String prtFcltySe) {
		this.prtFcltySe = prtFcltySe;
	}
	/**
	 * @return the prtFcltyNm
	 */
	public String getPrtFcltyNm() {
		return prtFcltyNm;
	}
	/**
	 * @param prtFcltyNm the prtFcltyNm to set
	 */
	public void setPrtFcltyNm(String prtFcltyNm) {
		this.prtFcltyNm = prtFcltyNm;
	}
	/**
	 * @return the prtFcltyStndrd
	 */
	public String getPrtFcltyStndrd() {
		return prtFcltyStndrd;
	}
	/**
	 * @param prtFcltyStndrd the prtFcltyStndrd to set
	 */
	public void setPrtFcltyStndrd(String prtFcltyStndrd) {
		this.prtFcltyStndrd = prtFcltyStndrd;
	}
	/**
	 * @return the prtFcltyUnit
	 */
	public String getPrtFcltyUnit() {
		return prtFcltyUnit;
	}
	/**
	 * @param prtFcltyUnit the prtFcltyUnit to set
	 */
	public void setPrtFcltyUnit(String prtFcltyUnit) {
		this.prtFcltyUnit = prtFcltyUnit;
	}
	/**
	 * @return the prtFcltyInstlDt
	 */
	public String getPrtFcltyInstlDt() {
		return prtFcltyInstlDt;
	}
	/**
	 * @param prtFcltyInstlDt the prtFcltyInstlDt to set
	 */
	public void setPrtFcltyInstlDt(String prtFcltyInstlDt) {
		this.prtFcltyInstlDt = prtFcltyInstlDt;
	}
	/**
	 * @return the prtFcltyChangeDt
	 */
	public String getPrtFcltyChangeDt() {
		return prtFcltyChangeDt;
	}
	/**
	 * @param prtFcltyChangeDt the prtFcltyChangeDt to set
	 */
	public void setPrtFcltyChangeDt(String prtFcltyChangeDt) {
		this.prtFcltyChangeDt = prtFcltyChangeDt;
	}
	/**
	 * @return the prtFcltyMngEntrpsCd
	 */
	public String getPrtFcltyMngEntrpsCd() {
		return prtFcltyMngEntrpsCd;
	}
	/**
	 * @param prtFcltyMngEntrpsCd the prtFcltyMngEntrpsCd to set
	 */
	public void setPrtFcltyMngEntrpsCd(String prtFcltyMngEntrpsCd) {
		this.prtFcltyMngEntrpsCd = prtFcltyMngEntrpsCd;
	}
	/**
	 * @return the prtFcltyGisCd
	 */
	public String getPrtFcltyGisCd() {
		return prtFcltyGisCd;
	}
	/**
	 * @param prtFcltyGisCd the prtFcltyGisCd to set
	 */
	public void setPrtFcltyGisCd(String prtFcltyGisCd) {
		this.prtFcltyGisCd = prtFcltyGisCd;
	}
	/**
	 * @return the laCrdnt
	 */
	public String getLaCrdnt() {
		return laCrdnt;
	}
	/**
	 * @param laCrdnt the laCrdnt to set
	 */
	public void setLaCrdnt(String laCrdnt) {
		this.laCrdnt = laCrdnt;
	}
	/**
	 * @return the loCrdnt
	 */
	public String getLoCrdnt() {
		return loCrdnt;
	}
	/**
	 * @param loCrdnt the loCrdnt to set
	 */
	public void setLoCrdnt(String loCrdnt) {
		this.loCrdnt = loCrdnt;
	}
	/**
	 * @return the prtFcltyAr
	 */
	public String getPrtFcltyAr() {
		return prtFcltyAr;
	}
	/**
	 * @param prtFcltyAr the prtFcltyAr to set
	 */
	public void setPrtFcltyAr(String prtFcltyAr) {
		this.prtFcltyAr = prtFcltyAr;
	}
	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * @param lng the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * @return the prtFcltyExprDt
	 */
	public String getPrtFcltyExprDt() {
		return prtFcltyExprDt;
	}
	/**
	 * @param prtFcltyExprDt the prtFcltyExprDt to set
	 */
	public void setPrtFcltyExprDt(String prtFcltyExprDt) {
		this.prtFcltyExprDt = prtFcltyExprDt;
	}
	/**
	 * @return the prtPrtFcltyCnt
	 */
	public String getPrtPrtFcltyCnt() {
		return prtPrtFcltyCnt;
	}
	/**
	 * @param prtPrtFcltyCnt the prtPrtFcltyCnt to set
	 */
	public void setPrtPrtFcltyCnt(String prtPrtFcltyCnt) {
		this.prtPrtFcltyCnt = prtPrtFcltyCnt;
	}
	/**
	 * @return the prtPrtFcltyMnger
	 */
	public String getPrtPrtFcltyMnger() {
		return prtPrtFcltyMnger;
	}
	/**
	 * @param prtPrtFcltyMnger the prtPrtFcltyMnger to set
	 */
	public void setPrtPrtFcltyMnger(String prtPrtFcltyMnger) {
		this.prtPrtFcltyMnger = prtPrtFcltyMnger;
	}
	/**
	 * @return the gisPrtFcltyCdSub
	 */
	public String getGisPrtFcltyCdSub() {
		return gisPrtFcltyCdSub;
	}
	/**
	 * @param gisPrtFcltyCdSub the gisPrtFcltyCdSub to set
	 */
	public void setGisPrtFcltyCdSub(String gisPrtFcltyCdSub) {
		this.gisPrtFcltyCdSub = gisPrtFcltyCdSub;
	}
	/**
	 * @return the fcltsMngNo
	 */
	public String getFcltsMngNo() {
		return fcltsMngNo;
	}
	/**
	 * @param fcltsMngNo the fcltsMngNo to set
	 */
	public void setFcltsMngNo(String fcltsMngNo) {
		this.fcltsMngNo = fcltsMngNo;
	}
	/**
	 * @return the fcltsMngGroupNo
	 */
	public String getFcltsMngGroupNo() {
		return fcltsMngGroupNo;
	}
	/**
	 * @param fcltsMngGroupNo the fcltsMngGroupNo to set
	 */
	public void setFcltsMngGroupNo(String fcltsMngGroupNo) {
		this.fcltsMngGroupNo = fcltsMngGroupNo;
	}
	/**
	 * @return the gisPrtFcltyCdNm
	 */
	public String getGisPrtFcltyCdNm() {
		return gisPrtFcltyCdNm;
	}
	/**
	 * @param gisPrtFcltyCdNm the gisPrtFcltyCdNm to set
	 */
	public void setGisPrtFcltyCdNm(String gisPrtFcltyCdNm) {
		this.gisPrtFcltyCdNm = gisPrtFcltyCdNm;
	}
	/**
	 * @return the prtFcltySeNm
	 */
	public String getPrtFcltySeNm() {
		return prtFcltySeNm;
	}
	/**
	 * @param prtFcltySeNm the prtFcltySeNm to set
	 */
	public void setPrtFcltySeNm(String prtFcltySeNm) {
		this.prtFcltySeNm = prtFcltySeNm;
	}
	/**
	 * @return the gisAssetsPrtAtCodeNm
	 */
	public String getGisAssetsPrtAtCodeNm() {
		return gisAssetsPrtAtCodeNm;
	}
	/**
	 * @param gisAssetsPrtAtCodeNm the gisAssetsPrtAtCodeNm to set
	 */
	public void setGisAssetsPrtAtCodeNm(String gisAssetsPrtAtCodeNm) {
		this.gisAssetsPrtAtCodeNm = gisAssetsPrtAtCodeNm;
	}
	/**
	 * @return the fcltsMngGroupNm
	 */
	public String getFcltsMngGroupNm() {
		return fcltsMngGroupNm;
	}
	/**
	 * @param fcltsMngGroupNm the fcltsMngGroupNm to set
	 */
	public void setFcltsMngGroupNm(String fcltsMngGroupNm) {
		this.fcltsMngGroupNm = fcltsMngGroupNm;
	}
	/**
	 * @return the prtFcltyMngEntrpsNm
	 */
	public String getPrtFcltyMngEntrpsNm() {
		return prtFcltyMngEntrpsNm;
	}
	/**
	 * @param prtFcltyMngEntrpsNm the prtFcltyMngEntrpsNm to set
	 */
	public void setPrtFcltyMngEntrpsNm(String prtFcltyMngEntrpsNm) {
		this.prtFcltyMngEntrpsNm = prtFcltyMngEntrpsNm;
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
	 * @return the gisAssetsLocplcLnm
	 */
	public String getGisAssetsLocplcLnm() {
		return gisAssetsLocplcLnm;
	}
	/**
	 * @param gisAssetsLocplcLnm the gisAssetsLocplcLnm to set
	 */
	public void setGisAssetsLocplcLnm(String gisAssetsLocplcLnm) {
		this.gisAssetsLocplcLnm = gisAssetsLocplcLnm;
	}
	/**
	 * @return the gisAssetsLocNm
	 */
	public String getGisAssetsLocNm() {
		return gisAssetsLocNm;
	}
	/**
	 * @param gisAssetsLocNm the gisAssetsLocNm to set
	 */
	public void setGisAssetsLocNm(String gisAssetsLocNm) {
		this.gisAssetsLocNm = gisAssetsLocNm;
	}
	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}
	/**
	 * @return the infoCommFcltsClCd
	 */
	public String getInfoCommFcltsClCd() {
		return infoCommFcltsClCd;
	}
	/**
	 * @param infoCommFcltsClCd the infoCommFcltsClCd to set
	 */
	public void setInfoCommFcltsClCd(String infoCommFcltsClCd) {
		this.infoCommFcltsClCd = infoCommFcltsClCd;
	}
	/**
	 * @return the infoCommFcltsClCdNm
	 */
	public String getInfoCommFcltsClCdNm() {
		return infoCommFcltsClCdNm;
	}
	/**
	 * @param infoCommFcltsClCdNm the infoCommFcltsClCdNm to set
	 */
	public void setInfoCommFcltsClCdNm(String infoCommFcltsClCdNm) {
		this.infoCommFcltsClCdNm = infoCommFcltsClCdNm;
	}
	/**
	 * @return the stndrd
	 */
	public String getStndrd() {
		return stndrd;
	}
	/**
	 * @param stndrd the stndrd to set
	 */
	public void setStndrd(String stndrd) {
		this.stndrd = stndrd;
	}
	/**
	 * @return the instlSe
	 */
	public String getInstlSe() {
		return instlSe;
	}
	/**
	 * @param instlSe the instlSe to set
	 */
	public void setInstlSe(String instlSe) {
		this.instlSe = instlSe;
	}
	/**
	 * @return the instlNo
	 */
	public String getInstlNo() {
		return instlNo;
	}
	/**
	 * @param instlNo the instlNo to set
	 */
	public void setInstlNo(String instlNo) {
		this.instlNo = instlNo;
	}
	/**
	 * @return the func
	 */
	public String getFunc() {
		return func;
	}
	/**
	 * @param func the func to set
	 */
	public void setFunc(String func) {
		this.func = func;
	}
	/**
	 * @return the ctrlMthd
	 */
	public String getCtrlMthd() {
		return ctrlMthd;
	}
	/**
	 * @param ctrlMthd the ctrlMthd to set
	 */
	public void setCtrlMthd(String ctrlMthd) {
		this.ctrlMthd = ctrlMthd;
	}
	/**
	 * @return the instlStndrd
	 */
	public String getInstlStndrd() {
		return instlStndrd;
	}
	/**
	 * @param instlStndrd the instlStndrd to set
	 */
	public void setInstlStndrd(String instlStndrd) {
		this.instlStndrd = instlStndrd;
	}
	/**
	 * @return the instlHt
	 */
	public String getInstlHt() {
		return instlHt;
	}
	/**
	 * @param instlHt the instlHt to set
	 */
	public void setInstlHt(String instlHt) {
		this.instlHt = instlHt;
	}
	/**
	 * @return the lampFmt
	 */
	public String getLampFmt() {
		return lampFmt;
	}
	/**
	 * @param lampFmt the lampFmt to set
	 */
	public void setLampFmt(String lampFmt) {
		this.lampFmt = lampFmt;
	}
	/**
	 * @return the prdlstNm
	 */
	public String getPrdlstNm() {
		return prdlstNm;
	}
	/**
	 * @param prdlstNm the prdlstNm to set
	 */
	public void setPrdlstNm(String prdlstNm) {
		this.prdlstNm = prdlstNm;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the ptlrDtls
	 */
	public String getPtlrDtls() {
		return ptlrDtls;
	}
	/**
	 * @param ptlrDtls the ptlrDtls to set
	 */
	public void setPtlrDtls(String ptlrDtls) {
		this.ptlrDtls = ptlrDtls;
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
	 * @return the maker
	 */
	public String getMaker() {
		return maker;
	}
	/**
	 * @param maker the maker to set
	 */
	public void setMaker(String maker) {
		this.maker = maker;
	}
	/**
	 * @return the acqAmt
	 */
	public String getAcqAmt() {
		return acqAmt;
	}
	/**
	 * @param acqAmt the acqAmt to set
	 */
	public void setAcqAmt(String acqAmt) {
		this.acqAmt = acqAmt;
	}
	/**
	 * @return the instlDt
	 */
	public String getInstlDt() {
		return instlDt;
	}
	/**
	 * @param instlDt the instlDt to set
	 */
	public void setInstlDt(String instlDt) {
		this.instlDt = instlDt;
	}
	/**
	 * @return the archFcltsMngNo
	 */
	public String getArchFcltsMngNo() {
		return archFcltsMngNo;
	}
	/**
	 * @param archFcltsMngNo the archFcltsMngNo to set
	 */
	public void setArchFcltsMngNo(String archFcltsMngNo) {
		this.archFcltsMngNo = archFcltsMngNo;
	}
	/**
	 * @return the archFcltsNm
	 */
	public String getArchFcltsNm() {
		return archFcltsNm;
	}
	/**
	 * @param archFcltsNm the archFcltsNm to set
	 */
	public void setArchFcltsNm(String archFcltsNm) {
		this.archFcltsNm = archFcltsNm;
	}
	/**
	 * @return the rm
	 */
	public String getRm() {
		return rm;
	}
	/**
	 * @param rm the rm to set
	 */
	public void setRm(String rm) {
		this.rm = rm;
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
	 * @return the registDt
	 */
	public String getRegistDt() {
		return registDt;
	}
	/**
	 * @param registDt the registDt to set
	 */
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
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
	 * @return the updtDt
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * @param updtDt the updtDt to set
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	/**
	 * @return the prtFcltyLoc
	 */
	public String getPrtFcltyLoc() {
		return prtFcltyLoc;
	}
	/**
	 * @param prtFcltyLoc the prtFcltyLoc to set
	 */
	public void setPrtFcltyLoc(String prtFcltyLoc) {
		this.prtFcltyLoc = prtFcltyLoc;
	}
	/**
	 * @return the fcltySpecExistYn
	 */
	public String getFcltySpecExistYn() {
		return fcltySpecExistYn;
	}
	/**
	 * @param fcltySpecExistYn the fcltySpecExistYn to set
	 */
	public void setFcltySpecExistYn(String fcltySpecExistYn) {
		this.fcltySpecExistYn = fcltySpecExistYn;
	}
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
	 * @return the sumQy
	 */
	public String getSumQy() {
		return sumQy;
	}
	/**
	 * @param sumQy the sumQy to set
	 */
	public void setSumQy(String sumQy) {
		this.sumQy = sumQy;
	}
	/**
	 * @return the sumAcqAmt
	 */
	public String getSumAcqAmt() {
		return sumAcqAmt;
	}
	/**
	 * @param sumAcqAmt the sumAcqAmt to set
	 */
	public void setSumAcqAmt(String sumAcqAmt) {
		this.sumAcqAmt = sumAcqAmt;
	}

}
