/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 1. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 1. 15.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamArchFcltySpecMngVO extends ComDefaultVO {

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
	private	String archFcltsClCd;			// 건축 시설물 분류 코드
	private	String archFcltsClCdNm;			// 건축 시설물 분류 코드 명
	private	String bldDt;					// 준공 일자
	private	String flawEndDt;				// 하자 만료 일자
	private	String baseFmt;					// 기초 형식
	private	String strctFmt;				// 구조 형식
	private	String ar;						// 연면적
	private	String archAr;					// 건축 면적
	private	String plotAr;					// 대지 면적
	private	String mainUsagePrpos;			// 주 사용 용도
	private	String prkAr;					// 주차 면적
	private	String isdPrkAr;				// 옥내 주차 면적
	private	String osdPrkAr;				// 옥외 주차 면적
	private	String prkCnt;					// 주차 대수
	private	String isdPrkCnt;				// 옥내 주차 대수
	private	String osdPrkCnt;				// 옥외 주차 대수
	private	String exhaustDuctEnnc;			// 배기 닥트 유무
	private	String vntltnArcndtMthd;		// 환기 공조 방식
	private	String wrtTankLoc;				// 물탱크 위치
	private	String sbtLoc;					// 변전실 위치
	private	String oilSavefcltyLoc;			// 유류 저장시설 위치
	private	String swgClupfcltyLoc;			// 오수 정화시설 위치
	private	String liftCntPsngr;			// 승강기 대수 승객용
	private	String liftCntCargo;			// 승강기 대수 화물용
	private	String liftCntEmgcy;			// 승강기 대수 비상용
	private	String liftOperMthd;			// 승강기 운영 방식
	private	String clngEnnc;				// 냉방 유무
	private	String clngSrc;					// 냉방 열원
	private	String htngEnnc;				// 난방 유무
	private	String htngSrc;					// 난방 열원
	private	String spictankFmt;				// 정화조 형식
	private	String elctyLeadInCapa;			// 전기 인입 용량
	private	String fcg;						// 외장
	private	String itr;						// 내장
	private	String ceil;					// 천장
	private	String roof;					// 지붕
	private	String roofWtprf;				// 지붕 방수
	private	String planCnstNm;				// 설계 공사 명
	private	String planExcCmpny;			// 설계 수행 회사
	private	String planBeginDt;				// 설계 시작 일자
	private	String planEndDt;				// 설계 종료 일자
	private	String cnstrctCnstNm;			// 시공 공사 명
	private	String cnstrctExcCmpny;			// 시공 수행 회사
	private	String cnstrctBeginDt;			// 시공 시작 일자
	private	String cnstrctEndDt;			// 시공 종료 일자
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
	private	String totalCount;				// 조회 자료 수
	private	String sumAr;					// 연면적 합계
	private	String sumArchAr;				// 건축 면적 합계
	private	String sumPlotAr;				// 대지 면적 합계

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
	 * @return the archFcltsClCd
	 */
	public String getArchFcltsClCd() {
		return archFcltsClCd;
	}
	/**
	 * @param archFcltsClCd the archFcltsClCd to set
	 */
	public void setArchFcltsClCd(String archFcltsClCd) {
		this.archFcltsClCd = archFcltsClCd;
	}
	/**
	 * @return the archFcltsClCdNm
	 */
	public String getArchFcltsClCdNm() {
		return archFcltsClCdNm;
	}
	/**
	 * @param archFcltsClCdNm the archFcltsClCdNm to set
	 */
	public void setArchFcltsClCdNm(String archFcltsClCdNm) {
		this.archFcltsClCdNm = archFcltsClCdNm;
	}
	/**
	 * @return the bldDt
	 */
	public String getBldDt() {
		return bldDt;
	}
	/**
	 * @param bldDt the bldDt to set
	 */
	public void setBldDt(String bldDt) {
		this.bldDt = bldDt;
	}
	/**
	 * @return the flawEndDt
	 */
	public String getFlawEndDt() {
		return flawEndDt;
	}
	/**
	 * @param flawEndDt the flawEndDt to set
	 */
	public void setFlawEndDt(String flawEndDt) {
		this.flawEndDt = flawEndDt;
	}
	/**
	 * @return the baseFmt
	 */
	public String getBaseFmt() {
		return baseFmt;
	}
	/**
	 * @param baseFmt the baseFmt to set
	 */
	public void setBaseFmt(String baseFmt) {
		this.baseFmt = baseFmt;
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
	 * @return the ar
	 */
	public String getAr() {
		return ar;
	}
	/**
	 * @param ar the ar to set
	 */
	public void setAr(String ar) {
		this.ar = ar;
	}
	/**
	 * @return the archAr
	 */
	public String getArchAr() {
		return archAr;
	}
	/**
	 * @param archAr the archAr to set
	 */
	public void setArchAr(String archAr) {
		this.archAr = archAr;
	}
	/**
	 * @return the plotAr
	 */
	public String getPlotAr() {
		return plotAr;
	}
	/**
	 * @param plotAr the plotAr to set
	 */
	public void setPlotAr(String plotAr) {
		this.plotAr = plotAr;
	}
	/**
	 * @return the mainUsagePrpos
	 */
	public String getMainUsagePrpos() {
		return mainUsagePrpos;
	}
	/**
	 * @param mainUsagePrpos the mainUsagePrpos to set
	 */
	public void setMainUsagePrpos(String mainUsagePrpos) {
		this.mainUsagePrpos = mainUsagePrpos;
	}
	/**
	 * @return the prkAr
	 */
	public String getPrkAr() {
		return prkAr;
	}
	/**
	 * @param prkAr the prkAr to set
	 */
	public void setPrkAr(String prkAr) {
		this.prkAr = prkAr;
	}
	/**
	 * @return the isdPrkAr
	 */
	public String getIsdPrkAr() {
		return isdPrkAr;
	}
	/**
	 * @param isdPrkAr the isdPrkAr to set
	 */
	public void setIsdPrkAr(String isdPrkAr) {
		this.isdPrkAr = isdPrkAr;
	}
	/**
	 * @return the osdPrkAr
	 */
	public String getOsdPrkAr() {
		return osdPrkAr;
	}
	/**
	 * @param osdPrkAr the osdPrkAr to set
	 */
	public void setOsdPrkAr(String osdPrkAr) {
		this.osdPrkAr = osdPrkAr;
	}
	/**
	 * @return the prkCnt
	 */
	public String getPrkCnt() {
		return prkCnt;
	}
	/**
	 * @param prkCnt the prkCnt to set
	 */
	public void setPrkCnt(String prkCnt) {
		this.prkCnt = prkCnt;
	}
	/**
	 * @return the isdPrkCnt
	 */
	public String getIsdPrkCnt() {
		return isdPrkCnt;
	}
	/**
	 * @param isdPrkCnt the isdPrkCnt to set
	 */
	public void setIsdPrkCnt(String isdPrkCnt) {
		this.isdPrkCnt = isdPrkCnt;
	}
	/**
	 * @return the osdPrkCnt
	 */
	public String getOsdPrkCnt() {
		return osdPrkCnt;
	}
	/**
	 * @param osdPrkCnt the osdPrkCnt to set
	 */
	public void setOsdPrkCnt(String osdPrkCnt) {
		this.osdPrkCnt = osdPrkCnt;
	}
	/**
	 * @return the exhaustDuctEnnc
	 */
	public String getExhaustDuctEnnc() {
		return exhaustDuctEnnc;
	}
	/**
	 * @param exhaustDuctEnnc the exhaustDuctEnnc to set
	 */
	public void setExhaustDuctEnnc(String exhaustDuctEnnc) {
		this.exhaustDuctEnnc = exhaustDuctEnnc;
	}
	/**
	 * @return the vntltnArcndtMthd
	 */
	public String getVntltnArcndtMthd() {
		return vntltnArcndtMthd;
	}
	/**
	 * @param vntltnArcndtMthd the vntltnArcndtMthd to set
	 */
	public void setVntltnArcndtMthd(String vntltnArcndtMthd) {
		this.vntltnArcndtMthd = vntltnArcndtMthd;
	}
	/**
	 * @return the wrtTankLoc
	 */
	public String getWrtTankLoc() {
		return wrtTankLoc;
	}
	/**
	 * @param wrtTankLoc the wrtTankLoc to set
	 */
	public void setWrtTankLoc(String wrtTankLoc) {
		this.wrtTankLoc = wrtTankLoc;
	}
	/**
	 * @return the sbtLoc
	 */
	public String getSbtLoc() {
		return sbtLoc;
	}
	/**
	 * @param sbtLoc the sbtLoc to set
	 */
	public void setSbtLoc(String sbtLoc) {
		this.sbtLoc = sbtLoc;
	}
	/**
	 * @return the oilSavefcltyLoc
	 */
	public String getOilSavefcltyLoc() {
		return oilSavefcltyLoc;
	}
	/**
	 * @param oilSavefcltyLoc the oilSavefcltyLoc to set
	 */
	public void setOilSavefcltyLoc(String oilSavefcltyLoc) {
		this.oilSavefcltyLoc = oilSavefcltyLoc;
	}
	/**
	 * @return the swgClupfcltyLoc
	 */
	public String getSwgClupfcltyLoc() {
		return swgClupfcltyLoc;
	}
	/**
	 * @param swgClupfcltyLoc the swgClupfcltyLoc to set
	 */
	public void setSwgClupfcltyLoc(String swgClupfcltyLoc) {
		this.swgClupfcltyLoc = swgClupfcltyLoc;
	}
	/**
	 * @return the liftCntPsngr
	 */
	public String getLiftCntPsngr() {
		return liftCntPsngr;
	}
	/**
	 * @param liftCntPsngr the liftCntPsngr to set
	 */
	public void setLiftCntPsngr(String liftCntPsngr) {
		this.liftCntPsngr = liftCntPsngr;
	}
	/**
	 * @return the liftCntCargo
	 */
	public String getLiftCntCargo() {
		return liftCntCargo;
	}
	/**
	 * @param liftCntCargo the liftCntCargo to set
	 */
	public void setLiftCntCargo(String liftCntCargo) {
		this.liftCntCargo = liftCntCargo;
	}
	/**
	 * @return the liftCntEmgcy
	 */
	public String getLiftCntEmgcy() {
		return liftCntEmgcy;
	}
	/**
	 * @param liftCntEmgcy the liftCntEmgcy to set
	 */
	public void setLiftCntEmgcy(String liftCntEmgcy) {
		this.liftCntEmgcy = liftCntEmgcy;
	}
	/**
	 * @return the liftOperMthd
	 */
	public String getLiftOperMthd() {
		return liftOperMthd;
	}
	/**
	 * @param liftOperMthd the liftOperMthd to set
	 */
	public void setLiftOperMthd(String liftOperMthd) {
		this.liftOperMthd = liftOperMthd;
	}
	/**
	 * @return the clngEnnc
	 */
	public String getClngEnnc() {
		return clngEnnc;
	}
	/**
	 * @param clngEnnc the clngEnnc to set
	 */
	public void setClngEnnc(String clngEnnc) {
		this.clngEnnc = clngEnnc;
	}
	/**
	 * @return the clngSrc
	 */
	public String getClngSrc() {
		return clngSrc;
	}
	/**
	 * @param clngSrc the clngSrc to set
	 */
	public void setClngSrc(String clngSrc) {
		this.clngSrc = clngSrc;
	}
	/**
	 * @return the htngEnnc
	 */
	public String getHtngEnnc() {
		return htngEnnc;
	}
	/**
	 * @param htngEnnc the htngEnnc to set
	 */
	public void setHtngEnnc(String htngEnnc) {
		this.htngEnnc = htngEnnc;
	}
	/**
	 * @return the htngSrc
	 */
	public String getHtngSrc() {
		return htngSrc;
	}
	/**
	 * @param htngSrc the htngSrc to set
	 */
	public void setHtngSrc(String htngSrc) {
		this.htngSrc = htngSrc;
	}
	/**
	 * @return the spictankFmt
	 */
	public String getSpictankFmt() {
		return spictankFmt;
	}
	/**
	 * @param spictankFmt the spictankFmt to set
	 */
	public void setSpictankFmt(String spictankFmt) {
		this.spictankFmt = spictankFmt;
	}
	/**
	 * @return the elctyLeadInCapa
	 */
	public String getElctyLeadInCapa() {
		return elctyLeadInCapa;
	}
	/**
	 * @param elctyLeadInCapa the elctyLeadInCapa to set
	 */
	public void setElctyLeadInCapa(String elctyLeadInCapa) {
		this.elctyLeadInCapa = elctyLeadInCapa;
	}
	/**
	 * @return the fcg
	 */
	public String getFcg() {
		return fcg;
	}
	/**
	 * @param fcg the fcg to set
	 */
	public void setFcg(String fcg) {
		this.fcg = fcg;
	}
	/**
	 * @return the itr
	 */
	public String getItr() {
		return itr;
	}
	/**
	 * @param itr the itr to set
	 */
	public void setItr(String itr) {
		this.itr = itr;
	}
	/**
	 * @return the ceil
	 */
	public String getCeil() {
		return ceil;
	}
	/**
	 * @param ceil the ceil to set
	 */
	public void setCeil(String ceil) {
		this.ceil = ceil;
	}
	/**
	 * @return the roof
	 */
	public String getRoof() {
		return roof;
	}
	/**
	 * @param roof the roof to set
	 */
	public void setRoof(String roof) {
		this.roof = roof;
	}
	/**
	 * @return the roofWtprf
	 */
	public String getRoofWtprf() {
		return roofWtprf;
	}
	/**
	 * @param roofWtprf the roofWtprf to set
	 */
	public void setRoofWtprf(String roofWtprf) {
		this.roofWtprf = roofWtprf;
	}
	/**
	 * @return the planCnstNm
	 */
	public String getPlanCnstNm() {
		return planCnstNm;
	}
	/**
	 * @param planCnstNm the planCnstNm to set
	 */
	public void setPlanCnstNm(String planCnstNm) {
		this.planCnstNm = planCnstNm;
	}
	/**
	 * @return the planExcCmpny
	 */
	public String getPlanExcCmpny() {
		return planExcCmpny;
	}
	/**
	 * @param planExcCmpny the planExcCmpny to set
	 */
	public void setPlanExcCmpny(String planExcCmpny) {
		this.planExcCmpny = planExcCmpny;
	}
	/**
	 * @return the planBeginDt
	 */
	public String getPlanBeginDt() {
		return planBeginDt;
	}
	/**
	 * @param planBeginDt the planBeginDt to set
	 */
	public void setPlanBeginDt(String planBeginDt) {
		this.planBeginDt = planBeginDt;
	}
	/**
	 * @return the planEndDt
	 */
	public String getPlanEndDt() {
		return planEndDt;
	}
	/**
	 * @param planEndDt the planEndDt to set
	 */
	public void setPlanEndDt(String planEndDt) {
		this.planEndDt = planEndDt;
	}
	/**
	 * @return the cnstrctCnstNm
	 */
	public String getCnstrctCnstNm() {
		return cnstrctCnstNm;
	}
	/**
	 * @param cnstrctCnstNm the cnstrctCnstNm to set
	 */
	public void setCnstrctCnstNm(String cnstrctCnstNm) {
		this.cnstrctCnstNm = cnstrctCnstNm;
	}
	/**
	 * @return the cnstrctExcCmpny
	 */
	public String getCnstrctExcCmpny() {
		return cnstrctExcCmpny;
	}
	/**
	 * @param cnstrctExcCmpny the cnstrctExcCmpny to set
	 */
	public void setCnstrctExcCmpny(String cnstrctExcCmpny) {
		this.cnstrctExcCmpny = cnstrctExcCmpny;
	}
	/**
	 * @return the cnstrctBeginDt
	 */
	public String getCnstrctBeginDt() {
		return cnstrctBeginDt;
	}
	/**
	 * @param cnstrctBeginDt the cnstrctBeginDt to set
	 */
	public void setCnstrctBeginDt(String cnstrctBeginDt) {
		this.cnstrctBeginDt = cnstrctBeginDt;
	}
	/**
	 * @return the cnstrctEndDt
	 */
	public String getCnstrctEndDt() {
		return cnstrctEndDt;
	}
	/**
	 * @param cnstrctEndDt the cnstrctEndDt to set
	 */
	public void setCnstrctEndDt(String cnstrctEndDt) {
		this.cnstrctEndDt = cnstrctEndDt;
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
	 * @return the sumAr
	 */
	public String getSumAr() {
		return sumAr;
	}
	/**
	 * @param sumAr the sumAr to set
	 */
	public void setSumAr(String sumAr) {
		this.sumAr = sumAr;
	}
	/**
	 * @return the sumArchAr
	 */
	public String getSumArchAr() {
		return sumArchAr;
	}
	/**
	 * @param sumArchAr the sumArchAr to set
	 */
	public void setSumArchAr(String sumArchAr) {
		this.sumArchAr = sumArchAr;
	}
	/**
	 * @return the sumPlotAr
	 */
	public String getSumPlotAr() {
		return sumPlotAr;
	}
	/**
	 * @param sumPlotAr the sumPlotAr to set
	 */
	public void setSumPlotAr(String sumPlotAr) {
		this.sumPlotAr = sumPlotAr;
	}

}
