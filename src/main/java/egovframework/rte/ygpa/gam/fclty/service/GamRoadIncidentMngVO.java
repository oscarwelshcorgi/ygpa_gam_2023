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
public class GamRoadIncidentMngVO extends ComDefaultVO {

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
	private	String cvlEngFcltsClCd;			// 토목 시설물 분류 코드
	private	String cvlEngFcltsClCdNm;		// 토목 시설물 분류 코드 명
	private	String fcltsExt;				// 시설물 연장
	private	String strctFmt;				// 구조 형식
	private	String upsideAltud;				// 천단 표고
	private	String berthDpwt;				// 선좌 수심
	private	String permWd;					// 상치 폭
	private	String apronWd;					// 에이프런 폭
	private	String apronPackKnd;			// 에이프런 포장 종류
	private	String apronPackGrdnt;			// 에이프런 포장 구배
	private	String csdhpShipScl;			// 접안 선박 규모
	private	String frostDmgWght;			// 상재 하중
	private	String baseBttmSoil;			// 기초 저면 토질
	private	String hndlFrght;				// 취급 화물
	private	String pileClbr;				// 말뚝 구경
	private	String pileExt;					// 말뚝 연장
	private	String pileQty;					// 말뚝 본수
	private	String sheetFileStndrd;			// 널말뚝 규격
	private	String hydrntQy;				// 급수전 수량
	private	String firepgQy;				// 소화전 수량
	private	String yardPackKnd;				// 야적장 포장 종류
	private	String yardAr;					// 야적장 면적
	private	String fenderKndCd;				// 방충재 종류 코드
	private	String fenderPmntItv;			// 방충재 배치 간격
	private	String fenderFmt;				// 방충재 형식
	private	String mrpostStndrd1;			// 계선주 규격_1
	private	String mrpostPmntItv1;			// 계선주 배치 간격_1
	private	String mrpostQy1;				// 계선주 수량_1
	private	String mrpostPwr1;				// 계선주 견인력_1
	private	String mrpostStndrd2;			// 계선주 규격_2
	private	String mrpostPmntItv2;			// 계선주 배치 간격_2
	private	String mrpostQy2;				// 계선주 수량_2
	private	String mrpostPwr2;				// 계선주 견인력_2
	private	String berth;					// 선석
	private	String stplHndlFrght;			// 주요 취급 화물
	private	String stplMoorShip;			// 주요 계류 선박
	private	String beginPtLoc;				// 시작 점 위치
	private	String endPtLoc;				// 종착 점 위치
	private	String wd;						// 폭
	private	String lt;						// 길이
	private	String packKnd;					// 포장 종류
	private	String upsideWd;				// 천단 폭
	private	String planHegh;				// 설계 파고
	private	String wavemainDir;				// 파랑주 방향
	private	String outerSwaveSlpRate;		// 외측소 파공 경사 비율
	private	String inSwaveSlpRate;			// 내측소 파공 경사 비율
	private	String outerSwaveCover;			// 외측소 파공 피복
	private	String inSwaveCover;			// 내측소 파공 피복
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시
	private	String prtFcltyLoc;				// 항만 시설 소재지
	private	String fcltySpecExistYn;		// 시설제원 존재 여부
	
	private	String sYear;				// 검색 년도
	private	String sDtFrom;				// 검색 시작일
	private	String sDtTo;				// 검색 종료일
	private	String sFcltsMngNo;				// 시설물 관리 번호
	private	String sFcltsMngGroupNo;			// 시설물 관리 그룹 번호

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
	 * @return the cvlEngFcltsClCd
	 */
	public String getCvlEngFcltsClCd() {
		return cvlEngFcltsClCd;
	}
	/**
	 * @param cvlEngFcltsClCd the cvlEngFcltsClCd to set
	 */
	public void setCvlEngFcltsClCd(String cvlEngFcltsClCd) {
		this.cvlEngFcltsClCd = cvlEngFcltsClCd;
	}
	/**
	 * @return the cvlEngFcltsClCdNm
	 */
	public String getCvlEngFcltsClCdNm() {
		return cvlEngFcltsClCdNm;
	}
	/**
	 * @param cvlEngFcltsClCdNm the cvlEngFcltsClCdNm to set
	 */
	public void setCvlEngFcltsClCdNm(String cvlEngFcltsClCdNm) {
		this.cvlEngFcltsClCdNm = cvlEngFcltsClCdNm;
	}
	/**
	 * @return the fcltsExt
	 */
	public String getFcltsExt() {
		return fcltsExt;
	}
	/**
	 * @param fcltsExt the fcltsExt to set
	 */
	public void setFcltsExt(String fcltsExt) {
		this.fcltsExt = fcltsExt;
	}
	/**
	 * @return the strctFmt
	 */
	public String getStrctFmt() {
		return strctFmt;
	}
	/**
	 * @param strctFmt the strctFmt to set
	 */
	public void setStrctFmt(String strctFmt) {
		this.strctFmt = strctFmt;
	}
	/**
	 * @return the upsideAltud
	 */
	public String getUpsideAltud() {
		return upsideAltud;
	}
	/**
	 * @param upsideAltud the upsideAltud to set
	 */
	public void setUpsideAltud(String upsideAltud) {
		this.upsideAltud = upsideAltud;
	}
	/**
	 * @return the berthDpwt
	 */
	public String getBerthDpwt() {
		return berthDpwt;
	}
	/**
	 * @param berthDpwt the berthDpwt to set
	 */
	public void setBerthDpwt(String berthDpwt) {
		this.berthDpwt = berthDpwt;
	}
	/**
	 * @return the permWd
	 */
	public String getPermWd() {
		return permWd;
	}
	/**
	 * @param permWd the permWd to set
	 */
	public void setPermWd(String permWd) {
		this.permWd = permWd;
	}
	/**
	 * @return the apronWd
	 */
	public String getApronWd() {
		return apronWd;
	}
	/**
	 * @param apronWd the apronWd to set
	 */
	public void setApronWd(String apronWd) {
		this.apronWd = apronWd;
	}
	/**
	 * @return the apronPackKnd
	 */
	public String getApronPackKnd() {
		return apronPackKnd;
	}
	/**
	 * @param apronPackKnd the apronPackKnd to set
	 */
	public void setApronPackKnd(String apronPackKnd) {
		this.apronPackKnd = apronPackKnd;
	}
	/**
	 * @return the apronPackGrdnt
	 */
	public String getApronPackGrdnt() {
		return apronPackGrdnt;
	}
	/**
	 * @param apronPackGrdnt the apronPackGrdnt to set
	 */
	public void setApronPackGrdnt(String apronPackGrdnt) {
		this.apronPackGrdnt = apronPackGrdnt;
	}
	/**
	 * @return the csdhpShipScl
	 */
	public String getCsdhpShipScl() {
		return csdhpShipScl;
	}
	/**
	 * @param csdhpShipScl the csdhpShipScl to set
	 */
	public void setCsdhpShipScl(String csdhpShipScl) {
		this.csdhpShipScl = csdhpShipScl;
	}
	/**
	 * @return the frostDmgWght
	 */
	public String getFrostDmgWght() {
		return frostDmgWght;
	}
	/**
	 * @param frostDmgWght the frostDmgWght to set
	 */
	public void setFrostDmgWght(String frostDmgWght) {
		this.frostDmgWght = frostDmgWght;
	}
	/**
	 * @return the baseBttmSoil
	 */
	public String getBaseBttmSoil() {
		return baseBttmSoil;
	}
	/**
	 * @param baseBttmSoil the baseBttmSoil to set
	 */
	public void setBaseBttmSoil(String baseBttmSoil) {
		this.baseBttmSoil = baseBttmSoil;
	}
	/**
	 * @return the hndlFrght
	 */
	public String getHndlFrght() {
		return hndlFrght;
	}
	/**
	 * @param hndlFrght the hndlFrght to set
	 */
	public void setHndlFrght(String hndlFrght) {
		this.hndlFrght = hndlFrght;
	}
	/**
	 * @return the pileClbr
	 */
	public String getPileClbr() {
		return pileClbr;
	}
	/**
	 * @param pileClbr the pileClbr to set
	 */
	public void setPileClbr(String pileClbr) {
		this.pileClbr = pileClbr;
	}
	/**
	 * @return the pileExt
	 */
	public String getPileExt() {
		return pileExt;
	}
	/**
	 * @param pileExt the pileExt to set
	 */
	public void setPileExt(String pileExt) {
		this.pileExt = pileExt;
	}
	/**
	 * @return the pileQty
	 */
	public String getPileQty() {
		return pileQty;
	}
	/**
	 * @param pileQty the pileQty to set
	 */
	public void setPileQty(String pileQty) {
		this.pileQty = pileQty;
	}
	/**
	 * @return the sheetFileStndrd
	 */
	public String getSheetFileStndrd() {
		return sheetFileStndrd;
	}
	/**
	 * @param sheetFileStndrd the sheetFileStndrd to set
	 */
	public void setSheetFileStndrd(String sheetFileStndrd) {
		this.sheetFileStndrd = sheetFileStndrd;
	}
	/**
	 * @return the hydrntQy
	 */
	public String getHydrntQy() {
		return hydrntQy;
	}
	/**
	 * @param hydrntQy the hydrntQy to set
	 */
	public void setHydrntQy(String hydrntQy) {
		this.hydrntQy = hydrntQy;
	}
	/**
	 * @return the firepgQy
	 */
	public String getFirepgQy() {
		return firepgQy;
	}
	/**
	 * @param firepgQy the firepgQy to set
	 */
	public void setFirepgQy(String firepgQy) {
		this.firepgQy = firepgQy;
	}
	/**
	 * @return the yardPackKnd
	 */
	public String getYardPackKnd() {
		return yardPackKnd;
	}
	/**
	 * @param yardPackKnd the yardPackKnd to set
	 */
	public void setYardPackKnd(String yardPackKnd) {
		this.yardPackKnd = yardPackKnd;
	}
	/**
	 * @return the yardAr
	 */
	public String getYardAr() {
		return yardAr;
	}
	/**
	 * @param yardAr the yardAr to set
	 */
	public void setYardAr(String yardAr) {
		this.yardAr = yardAr;
	}
	/**
	 * @return the fenderKndCd
	 */
	public String getFenderKndCd() {
		return fenderKndCd;
	}
	/**
	 * @param fenderKndCd the fenderKndCd to set
	 */
	public void setFenderKndCd(String fenderKndCd) {
		this.fenderKndCd = fenderKndCd;
	}
	/**
	 * @return the fenderPmntItv
	 */
	public String getFenderPmntItv() {
		return fenderPmntItv;
	}
	/**
	 * @param fenderPmntItv the fenderPmntItv to set
	 */
	public void setFenderPmntItv(String fenderPmntItv) {
		this.fenderPmntItv = fenderPmntItv;
	}
	/**
	 * @return the fenderFmt
	 */
	public String getFenderFmt() {
		return fenderFmt;
	}
	/**
	 * @param fenderFmt the fenderFmt to set
	 */
	public void setFenderFmt(String fenderFmt) {
		this.fenderFmt = fenderFmt;
	}
	/**
	 * @return the mrpostStndrd1
	 */
	public String getMrpostStndrd1() {
		return mrpostStndrd1;
	}
	/**
	 * @param mrpostStndrd1 the mrpostStndrd1 to set
	 */
	public void setMrpostStndrd1(String mrpostStndrd1) {
		this.mrpostStndrd1 = mrpostStndrd1;
	}
	/**
	 * @return the mrpostPmntItv1
	 */
	public String getMrpostPmntItv1() {
		return mrpostPmntItv1;
	}
	/**
	 * @param mrpostPmntItv1 the mrpostPmntItv1 to set
	 */
	public void setMrpostPmntItv1(String mrpostPmntItv1) {
		this.mrpostPmntItv1 = mrpostPmntItv1;
	}
	/**
	 * @return the mrpostQy1
	 */
	public String getMrpostQy1() {
		return mrpostQy1;
	}
	/**
	 * @param mrpostQy1 the mrpostQy1 to set
	 */
	public void setMrpostQy1(String mrpostQy1) {
		this.mrpostQy1 = mrpostQy1;
	}
	/**
	 * @return the mrpostPwr1
	 */
	public String getMrpostPwr1() {
		return mrpostPwr1;
	}
	/**
	 * @param mrpostPwr1 the mrpostPwr1 to set
	 */
	public void setMrpostPwr1(String mrpostPwr1) {
		this.mrpostPwr1 = mrpostPwr1;
	}
	/**
	 * @return the mrpostStndrd2
	 */
	public String getMrpostStndrd2() {
		return mrpostStndrd2;
	}
	/**
	 * @param mrpostStndrd2 the mrpostStndrd2 to set
	 */
	public void setMrpostStndrd2(String mrpostStndrd2) {
		this.mrpostStndrd2 = mrpostStndrd2;
	}
	/**
	 * @return the mrpostPmntItv2
	 */
	public String getMrpostPmntItv2() {
		return mrpostPmntItv2;
	}
	/**
	 * @param mrpostPmntItv2 the mrpostPmntItv2 to set
	 */
	public void setMrpostPmntItv2(String mrpostPmntItv2) {
		this.mrpostPmntItv2 = mrpostPmntItv2;
	}
	/**
	 * @return the mrpostQy2
	 */
	public String getMrpostQy2() {
		return mrpostQy2;
	}
	/**
	 * @param mrpostQy2 the mrpostQy2 to set
	 */
	public void setMrpostQy2(String mrpostQy2) {
		this.mrpostQy2 = mrpostQy2;
	}
	/**
	 * @return the mrpostPwr2
	 */
	public String getMrpostPwr2() {
		return mrpostPwr2;
	}
	/**
	 * @param mrpostPwr2 the mrpostPwr2 to set
	 */
	public void setMrpostPwr2(String mrpostPwr2) {
		this.mrpostPwr2 = mrpostPwr2;
	}
	/**
	 * @return the berth
	 */
	public String getBerth() {
		return berth;
	}
	/**
	 * @param berth the berth to set
	 */
	public void setBerth(String berth) {
		this.berth = berth;
	}
	/**
	 * @return the stplHndlFrght
	 */
	public String getStplHndlFrght() {
		return stplHndlFrght;
	}
	/**
	 * @param stplHndlFrght the stplHndlFrght to set
	 */
	public void setStplHndlFrght(String stplHndlFrght) {
		this.stplHndlFrght = stplHndlFrght;
	}
	/**
	 * @return the stplMoorShip
	 */
	public String getStplMoorShip() {
		return stplMoorShip;
	}
	/**
	 * @param stplMoorShip the stplMoorShip to set
	 */
	public void setStplMoorShip(String stplMoorShip) {
		this.stplMoorShip = stplMoorShip;
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
	 * @return the beginPtLoc
	 */
	public String getBeginPtLoc() {
		return beginPtLoc;
	}
	/**
	 * @param beginPtLoc the beginPtLoc to set
	 */
	public void setBeginPtLoc(String beginPtLoc) {
		this.beginPtLoc = beginPtLoc;
	}
	/**
	 * @return the endPtLoc
	 */
	public String getEndPtLoc() {
		return endPtLoc;
	}
	/**
	 * @param endPtLoc the endPtLoc to set
	 */
	public void setEndPtLoc(String endPtLoc) {
		this.endPtLoc = endPtLoc;
	}
	/**
	 * @return the wd
	 */
	public String getWd() {
		return wd;
	}
	/**
	 * @param wd the wd to set
	 */
	public void setWd(String wd) {
		this.wd = wd;
	}
	/**
	 * @return the lt
	 */
	public String getLt() {
		return lt;
	}
	/**
	 * @param lt the lt to set
	 */
	public void setLt(String lt) {
		this.lt = lt;
	}
	/**
	 * @return the packKnd
	 */
	public String getPackKnd() {
		return packKnd;
	}
	/**
	 * @param packKnd the packKnd to set
	 */
	public void setPackKnd(String packKnd) {
		this.packKnd = packKnd;
	}
	/**
	 * @return the upsideWd
	 */
	public String getUpsideWd() {
		return upsideWd;
	}
	/**
	 * @param upsideWd the upsideWd to set
	 */
	public void setUpsideWd(String upsideWd) {
		this.upsideWd = upsideWd;
	}
	/**
	 * @return the planHegh
	 */
	public String getPlanHegh() {
		return planHegh;
	}
	/**
	 * @param planHegh the planHegh to set
	 */
	public void setPlanHegh(String planHegh) {
		this.planHegh = planHegh;
	}
	/**
	 * @return the wavemainDir
	 */
	public String getWavemainDir() {
		return wavemainDir;
	}
	/**
	 * @param wavemainDir the wavemainDir to set
	 */
	public void setWavemainDir(String wavemainDir) {
		this.wavemainDir = wavemainDir;
	}
	/**
	 * @return the outerSwaveSlpRate
	 */
	public String getOuterSwaveSlpRate() {
		return outerSwaveSlpRate;
	}
	/**
	 * @param outerSwaveSlpRate the outerSwaveSlpRate to set
	 */
	public void setOuterSwaveSlpRate(String outerSwaveSlpRate) {
		this.outerSwaveSlpRate = outerSwaveSlpRate;
	}
	/**
	 * @return the inSwaveSlpRate
	 */
	public String getInSwaveSlpRate() {
		return inSwaveSlpRate;
	}
	/**
	 * @param inSwaveSlpRate the inSwaveSlpRate to set
	 */
	public void setInSwaveSlpRate(String inSwaveSlpRate) {
		this.inSwaveSlpRate = inSwaveSlpRate;
	}
	/**
	 * @return the outerSwaveCover
	 */
	public String getOuterSwaveCover() {
		return outerSwaveCover;
	}
	/**
	 * @param outerSwaveCover the outerSwaveCover to set
	 */
	public void setOuterSwaveCover(String outerSwaveCover) {
		this.outerSwaveCover = outerSwaveCover;
	}
	/**
	 * @return the inSwaveCover
	 */
	public String getInSwaveCover() {
		return inSwaveCover;
	}
	/**
	 * @param inSwaveCover the inSwaveCover to set
	 */
	public void setInSwaveCover(String inSwaveCover) {
		this.inSwaveCover = inSwaveCover;
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
	 * @return the sYear
	 */
	public String getsYear() {
		return sYear;
	}
	/**
	 * @param sYear the sYear to set
	 */
	public void setsYear(String sYear) {
		this.sYear = sYear;
	}
	/**
	 * @return the sDtFrom
	 */
	public String getsDtFrom() {
		return sDtFrom;
	}
	/**
	 * @param sDtFrom the sDtFrom to set
	 */
	public void setsDtFrom(String sDtFrom) {
		this.sDtFrom = sDtFrom;
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
	
}
