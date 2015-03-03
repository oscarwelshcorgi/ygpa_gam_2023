/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 3.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamMachFcltySpecMngVO extends ComDefaultVO {

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
	private	String mechFcltsClCd;			// 기계 시설물 분류 코드
	private	String mechFcltsClCdNm;			// 기계 시설물 분류 코드 명
	private	String mechFcltsSe;				// 기계 시설물 구분
	private	String eqpmnNm;					// 장비 명
	private	String eqpmnNo;					// 장비 번호
	private	String operCmpny;				// 운영 회사
	private	String mfcCmpny;				// 제작 회사
	private	String mfcAmt;					// 제작 비용
	private	String instlYrmt;				// 설치 년월
	private	String mfcChkUsr;				// 제조 검사자
	private	String outReach;				// 아웃 리치(m)
	private	String backReach;				// 백 리치(m)
	private	String refloatHt;				// 인양 높이(m)
	private	String processAblty;			// 처리 능력
	private	String driveWd;					// 주행 폭(m)
	private	String railWd;					// 레일 폭(m)
	private	String selfLoad;				// 자중(톤)
	private	String wheelWght;				// 윤 하중(톤/차륜)
	private	String eqpmnStndrd;				// 장비 규격
	private	String linkBridge;				// 연결 도교(m)
	private	String rubberFender;			// 고무 방충재(m)
	private	String elctyMthd;				// 전기 방식
	private	String capaTon;					// 적재 톤수(톤)
	private	String contrUsr;				// 도급자
	private	String contrAmt;				// 도급 금액
	private	String vntltnArcndtMthd;		// 환기 공조 방식
	private	String clngSrc;					// 냉방 열원
	private	String htngSrc;					// 난방 열원
	private	String waterTank;				// 물 탱크(톤)
	private	String oilSaveTank;				// 유류 저장 탱크(ℓ)
	private	String spictankFmt;				// 정화조 형식
	private	String prpos;					// 용도
	private	String fmt;						// 형식
	private	String stndrd;					// 규격
	private	String mfcDt;					// 제작 일자
	private	String sttusLvl;				// 상태 등급
	private	String structWqnt;				// 강재 중량
	private	String fenderInstlYr;			// 방충재 설치 년도
	private	String fenderInstlQy;			// 방충재 설치 수량
	private	String elctyMthdInstlYr;		// 전기 방식 설치 년도
	private	String elctyMthdInstlQy;		// 전기 방식 설치 수량
	private	String fenderStndrd;			// 방충재 규격
	private	String rateWght;				// 정격 하중
	private	String mechFcltsSeNm;			// 기계 시설물 구분 명
	private	String archFcltsMngNo;			// 건축 시설물 관리 번호
	private	String archFcltsNm;				// 건축 시설물 명
	private	String rm;						// 비고
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시
	private	String prtFcltyLoc;				// 항만 시설 소재지
	private	String cvlEngEqpmnNm;			// 하역 장비 명
	private	String shipEqpmnNm;				// 함선 장비 명
	private	String archEqpmnNm;				// 건물 장비 명
	private	String cvlEngInstlYrmt;			// 하역 설치 년월
	private	String shipInstlYrmt;			// 함선 설치 년월
	private	String archInstlYrmt;			// 건물 설치 년월
	private	String cvlEngMfcCmpny;			// 하역 제작 회사
	private	String shipMfcCmpny;			// 함선 제작 회사
	private	String cvlEngMfcAmt;			// 하역 하역 제작 비용
	private	String shipMfcAmt;				// 함선 제작 비용
	private	String cvlEngOperCmpny;			// 하역 운영 회사
	private	String archOperCmpny;			// 건물 운영 회사
	private	String fcltySpecExistYn;		// 시설제원 존재 여부
	private	String sFcltsMngNo;				// 검색 시설물 관리 번호
	private	String sLoc;					// 검색 위치
	private	String sFcltsClCd;				// 검색 시설물 분류 코드
	private	String sPrtFcltyNm;				// 검색 항만시설 명
	private	String sPrtAtCode;				// 검색 항구분
	private	String sFcltsMngGroupNo;		// 검색 시설물 관리 그룹 번호
	private	String sGisPrtFcltyCd;			// 검색 GIS 시설 코드
	private	String totalCount;				// 조회 자료 수
	private	String sumMfcAmt;				// 제조 금액 합계
	private	String sumContrAmt;				// 도급 금액 합계

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
	 * @return the mechFcltsClCd
	 */
	public String getMechFcltsClCd() {
		return mechFcltsClCd;
	}
	/**
	 * @param mechFcltsClCd the mechFcltsClCd to set
	 */
	public void setMechFcltsClCd(String mechFcltsClCd) {
		this.mechFcltsClCd = mechFcltsClCd;
	}
	/**
	 * @return the mechFcltsClCdNm
	 */
	public String getMechFcltsClCdNm() {
		return mechFcltsClCdNm;
	}
	/**
	 * @param mechFcltsClCdNm the mechFcltsClCdNm to set
	 */
	public void setMechFcltsClCdNm(String mechFcltsClCdNm) {
		this.mechFcltsClCdNm = mechFcltsClCdNm;
	}
	/**
	 * @return the mechFcltsSe
	 */
	public String getMechFcltsSe() {
		return mechFcltsSe;
	}
	/**
	 * @param mechFcltsSe the mechFcltsSe to set
	 */
	public void setMechFcltsSe(String mechFcltsSe) {
		this.mechFcltsSe = mechFcltsSe;
	}
	/**
	 * @return the eqpmnNm
	 */
	public String getEqpmnNm() {
		return eqpmnNm;
	}
	/**
	 * @param eqpmnNm the eqpmnNm to set
	 */
	public void setEqpmnNm(String eqpmnNm) {
		this.eqpmnNm = eqpmnNm;
	}
	/**
	 * @return the eqpmnNo
	 */
	public String getEqpmnNo() {
		return eqpmnNo;
	}
	/**
	 * @param eqpmnNo the eqpmnNo to set
	 */
	public void setEqpmnNo(String eqpmnNo) {
		this.eqpmnNo = eqpmnNo;
	}
	/**
	 * @return the operCmpny
	 */
	public String getOperCmpny() {
		return operCmpny;
	}
	/**
	 * @param operCmpny the operCmpny to set
	 */
	public void setOperCmpny(String operCmpny) {
		this.operCmpny = operCmpny;
	}
	/**
	 * @return the mfcCmpny
	 */
	public String getMfcCmpny() {
		return mfcCmpny;
	}
	/**
	 * @param mfcCmpny the mfcCmpny to set
	 */
	public void setMfcCmpny(String mfcCmpny) {
		this.mfcCmpny = mfcCmpny;
	}
	/**
	 * @return the mfcAmt
	 */
	public String getMfcAmt() {
		return mfcAmt;
	}
	/**
	 * @param mfcAmt the mfcAmt to set
	 */
	public void setMfcAmt(String mfcAmt) {
		this.mfcAmt = mfcAmt;
	}
	/**
	 * @return the instlYrmt
	 */
	public String getInstlYrmt() {
		return instlYrmt;
	}
	/**
	 * @param instlYrmt the instlYrmt to set
	 */
	public void setInstlYrmt(String instlYrmt) {
		this.instlYrmt = instlYrmt;
	}
	/**
	 * @return the mfcChkUsr
	 */
	public String getMfcChkUsr() {
		return mfcChkUsr;
	}
	/**
	 * @param mfcChkUsr the mfcChkUsr to set
	 */
	public void setMfcChkUsr(String mfcChkUsr) {
		this.mfcChkUsr = mfcChkUsr;
	}
	/**
	 * @return the outReach
	 */
	public String getOutReach() {
		return outReach;
	}
	/**
	 * @param outReach the outReach to set
	 */
	public void setOutReach(String outReach) {
		this.outReach = outReach;
	}
	/**
	 * @return the backReach
	 */
	public String getBackReach() {
		return backReach;
	}
	/**
	 * @param backReach the backReach to set
	 */
	public void setBackReach(String backReach) {
		this.backReach = backReach;
	}
	/**
	 * @return the refloatHt
	 */
	public String getRefloatHt() {
		return refloatHt;
	}
	/**
	 * @param refloatHt the refloatHt to set
	 */
	public void setRefloatHt(String refloatHt) {
		this.refloatHt = refloatHt;
	}
	/**
	 * @return the processAblty
	 */
	public String getProcessAblty() {
		return processAblty;
	}
	/**
	 * @param processAblty the processAblty to set
	 */
	public void setProcessAblty(String processAblty) {
		this.processAblty = processAblty;
	}
	/**
	 * @return the driveWd
	 */
	public String getDriveWd() {
		return driveWd;
	}
	/**
	 * @param driveWd the driveWd to set
	 */
	public void setDriveWd(String driveWd) {
		this.driveWd = driveWd;
	}
	/**
	 * @return the railWd
	 */
	public String getRailWd() {
		return railWd;
	}
	/**
	 * @param railWd the railWd to set
	 */
	public void setRailWd(String railWd) {
		this.railWd = railWd;
	}
	/**
	 * @return the selfLoad
	 */
	public String getSelfLoad() {
		return selfLoad;
	}
	/**
	 * @param selfLoad the selfLoad to set
	 */
	public void setSelfLoad(String selfLoad) {
		this.selfLoad = selfLoad;
	}
	/**
	 * @return the wheelWght
	 */
	public String getWheelWght() {
		return wheelWght;
	}
	/**
	 * @param wheelWght the wheelWght to set
	 */
	public void setWheelWght(String wheelWght) {
		this.wheelWght = wheelWght;
	}
	/**
	 * @return the eqpmnStndrd
	 */
	public String getEqpmnStndrd() {
		return eqpmnStndrd;
	}
	/**
	 * @param eqpmnStndrd the eqpmnStndrd to set
	 */
	public void setEqpmnStndrd(String eqpmnStndrd) {
		this.eqpmnStndrd = eqpmnStndrd;
	}
	/**
	 * @return the linkBridge
	 */
	public String getLinkBridge() {
		return linkBridge;
	}
	/**
	 * @param linkBridge the linkBridge to set
	 */
	public void setLinkBridge(String linkBridge) {
		this.linkBridge = linkBridge;
	}
	/**
	 * @return the rubberFender
	 */
	public String getRubberFender() {
		return rubberFender;
	}
	/**
	 * @param rubberFender the rubberFender to set
	 */
	public void setRubberFender(String rubberFender) {
		this.rubberFender = rubberFender;
	}
	/**
	 * @return the elctyMthd
	 */
	public String getElctyMthd() {
		return elctyMthd;
	}
	/**
	 * @param elctyMthd the elctyMthd to set
	 */
	public void setElctyMthd(String elctyMthd) {
		this.elctyMthd = elctyMthd;
	}
	/**
	 * @return the capaTon
	 */
	public String getCapaTon() {
		return capaTon;
	}
	/**
	 * @param capaTon the capaTon to set
	 */
	public void setCapaTon(String capaTon) {
		this.capaTon = capaTon;
	}
	/**
	 * @return the contrUsr
	 */
	public String getContrUsr() {
		return contrUsr;
	}
	/**
	 * @param contrUsr the contrUsr to set
	 */
	public void setContrUsr(String contrUsr) {
		this.contrUsr = contrUsr;
	}
	/**
	 * @return the contrAmt
	 */
	public String getContrAmt() {
		return contrAmt;
	}
	/**
	 * @param contrAmt the contrAmt to set
	 */
	public void setContrAmt(String contrAmt) {
		this.contrAmt = contrAmt;
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
	 * @return the waterTank
	 */
	public String getWaterTank() {
		return waterTank;
	}
	/**
	 * @param waterTank the waterTank to set
	 */
	public void setWaterTank(String waterTank) {
		this.waterTank = waterTank;
	}
	/**
	 * @return the oilSaveTank
	 */
	public String getOilSaveTank() {
		return oilSaveTank;
	}
	/**
	 * @param oilSaveTank the oilSaveTank to set
	 */
	public void setOilSaveTank(String oilSaveTank) {
		this.oilSaveTank = oilSaveTank;
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
	 * @return the prpos
	 */
	public String getPrpos() {
		return prpos;
	}
	/**
	 * @param prpos the prpos to set
	 */
	public void setPrpos(String prpos) {
		this.prpos = prpos;
	}
	/**
	 * @return the fmt
	 */
	public String getFmt() {
		return fmt;
	}
	/**
	 * @param fmt the fmt to set
	 */
	public void setFmt(String fmt) {
		this.fmt = fmt;
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
	 * @return the mfcDt
	 */
	public String getMfcDt() {
		return mfcDt;
	}
	/**
	 * @param mfcDt the mfcDt to set
	 */
	public void setMfcDt(String mfcDt) {
		this.mfcDt = mfcDt;
	}
	/**
	 * @return the sttusLvl
	 */
	public String getSttusLvl() {
		return sttusLvl;
	}
	/**
	 * @param sttusLvl the sttusLvl to set
	 */
	public void setSttusLvl(String sttusLvl) {
		this.sttusLvl = sttusLvl;
	}
	/**
	 * @return the structWqnt
	 */
	public String getStructWqnt() {
		return structWqnt;
	}
	/**
	 * @param structWqnt the structWqnt to set
	 */
	public void setStructWqnt(String structWqnt) {
		this.structWqnt = structWqnt;
	}
	/**
	 * @return the fenderInstlYr
	 */
	public String getFenderInstlYr() {
		return fenderInstlYr;
	}
	/**
	 * @param fenderInstlYr the fenderInstlYr to set
	 */
	public void setFenderInstlYr(String fenderInstlYr) {
		this.fenderInstlYr = fenderInstlYr;
	}
	/**
	 * @return the fenderInstlQy
	 */
	public String getFenderInstlQy() {
		return fenderInstlQy;
	}
	/**
	 * @param fenderInstlQy the fenderInstlQy to set
	 */
	public void setFenderInstlQy(String fenderInstlQy) {
		this.fenderInstlQy = fenderInstlQy;
	}
	/**
	 * @return the elctyMthdInstlYr
	 */
	public String getElctyMthdInstlYr() {
		return elctyMthdInstlYr;
	}
	/**
	 * @param elctyMthdInstlYr the elctyMthdInstlYr to set
	 */
	public void setElctyMthdInstlYr(String elctyMthdInstlYr) {
		this.elctyMthdInstlYr = elctyMthdInstlYr;
	}
	/**
	 * @return the elctyMthdInstlQy
	 */
	public String getElctyMthdInstlQy() {
		return elctyMthdInstlQy;
	}
	/**
	 * @param elctyMthdInstlQy the elctyMthdInstlQy to set
	 */
	public void setElctyMthdInstlQy(String elctyMthdInstlQy) {
		this.elctyMthdInstlQy = elctyMthdInstlQy;
	}
	/**
	 * @return the fenderStndrd
	 */
	public String getFenderStndrd() {
		return fenderStndrd;
	}
	/**
	 * @param fenderStndrd the fenderStndrd to set
	 */
	public void setFenderStndrd(String fenderStndrd) {
		this.fenderStndrd = fenderStndrd;
	}
	/**
	 * @return the rateWght
	 */
	public String getRateWght() {
		return rateWght;
	}
	/**
	 * @param rateWght the rateWght to set
	 */
	public void setRateWght(String rateWght) {
		this.rateWght = rateWght;
	}
	/**
	 * @return the mechFcltsSeNm
	 */
	public String getMechFcltsSeNm() {
		return mechFcltsSeNm;
	}
	/**
	 * @param mechFcltsSeNm the mechFcltsSeNm to set
	 */
	public void setMechFcltsSeNm(String mechFcltsSeNm) {
		this.mechFcltsSeNm = mechFcltsSeNm;
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
	 * @return the cvlEngEqpmnNm
	 */
	public String getCvlEngEqpmnNm() {
		return cvlEngEqpmnNm;
	}
	/**
	 * @param cvlEngEqpmnNm the cvlEngEqpmnNm to set
	 */
	public void setCvlEngEqpmnNm(String cvlEngEqpmnNm) {
		this.cvlEngEqpmnNm = cvlEngEqpmnNm;
	}
	/**
	 * @return the shipEqpmnNm
	 */
	public String getShipEqpmnNm() {
		return shipEqpmnNm;
	}
	/**
	 * @param shipEqpmnNm the shipEqpmnNm to set
	 */
	public void setShipEqpmnNm(String shipEqpmnNm) {
		this.shipEqpmnNm = shipEqpmnNm;
	}
	/**
	 * @return the archEqpmnNm
	 */
	public String getArchEqpmnNm() {
		return archEqpmnNm;
	}
	/**
	 * @param archEqpmnNm the archEqpmnNm to set
	 */
	public void setArchEqpmnNm(String archEqpmnNm) {
		this.archEqpmnNm = archEqpmnNm;
	}
	/**
	 * @return the cvlEngInstlYrmt
	 */
	public String getCvlEngInstlYrmt() {
		return cvlEngInstlYrmt;
	}
	/**
	 * @param cvlEngInstlYrmt the cvlEngInstlYrmt to set
	 */
	public void setCvlEngInstlYrmt(String cvlEngInstlYrmt) {
		this.cvlEngInstlYrmt = cvlEngInstlYrmt;
	}
	/**
	 * @return the shipInstlYrmt
	 */
	public String getShipInstlYrmt() {
		return shipInstlYrmt;
	}
	/**
	 * @param shipInstlYrmt the shipInstlYrmt to set
	 */
	public void setShipInstlYrmt(String shipInstlYrmt) {
		this.shipInstlYrmt = shipInstlYrmt;
	}
	/**
	 * @return the archInstlYrmt
	 */
	public String getArchInstlYrmt() {
		return archInstlYrmt;
	}
	/**
	 * @param archInstlYrmt the archInstlYrmt to set
	 */
	public void setArchInstlYrmt(String archInstlYrmt) {
		this.archInstlYrmt = archInstlYrmt;
	}
	/**
	 * @return the cvlEngMfcCmpny
	 */
	public String getCvlEngMfcCmpny() {
		return cvlEngMfcCmpny;
	}
	/**
	 * @param cvlEngMfcCmpny the cvlEngMfcCmpny to set
	 */
	public void setCvlEngMfcCmpny(String cvlEngMfcCmpny) {
		this.cvlEngMfcCmpny = cvlEngMfcCmpny;
	}
	/**
	 * @return the shipMfcCmpny
	 */
	public String getShipMfcCmpny() {
		return shipMfcCmpny;
	}
	/**
	 * @param shipMfcCmpny the shipMfcCmpny to set
	 */
	public void setShipMfcCmpny(String shipMfcCmpny) {
		this.shipMfcCmpny = shipMfcCmpny;
	}
	/**
	 * @return the cvlEngMfcAmt
	 */
	public String getCvlEngMfcAmt() {
		return cvlEngMfcAmt;
	}
	/**
	 * @param cvlEngMfcAmt the cvlEngMfcAmt to set
	 */
	public void setCvlEngMfcAmt(String cvlEngMfcAmt) {
		this.cvlEngMfcAmt = cvlEngMfcAmt;
	}
	/**
	 * @return the shipMfcAmt
	 */
	public String getShipMfcAmt() {
		return shipMfcAmt;
	}
	/**
	 * @param shipMfcAmt the shipMfcAmt to set
	 */
	public void setShipMfcAmt(String shipMfcAmt) {
		this.shipMfcAmt = shipMfcAmt;
	}
	/**
	 * @return the cvlEngOperCmpny
	 */
	public String getCvlEngOperCmpny() {
		return cvlEngOperCmpny;
	}
	/**
	 * @param cvlEngOperCmpny the cvlEngOperCmpny to set
	 */
	public void setCvlEngOperCmpny(String cvlEngOperCmpny) {
		this.cvlEngOperCmpny = cvlEngOperCmpny;
	}
	/**
	 * @return the archOperCmpny
	 */
	public String getArchOperCmpny() {
		return archOperCmpny;
	}
	/**
	 * @param archOperCmpny the archOperCmpny to set
	 */
	public void setArchOperCmpny(String archOperCmpny) {
		this.archOperCmpny = archOperCmpny;
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
	 * @return the sumMfcAmt
	 */
	public String getSumMfcAmt() {
		return sumMfcAmt;
	}
	/**
	 * @param sumMfcAmt the sumMfcAmt to set
	 */
	public void setSumMfcAmt(String sumMfcAmt) {
		this.sumMfcAmt = sumMfcAmt;
	}
	/**
	 * @return the sumContrAmt
	 */
	public String getSumContrAmt() {
		return sumContrAmt;
	}
	/**
	 * @param sumContrAmt the sumContrAmt to set
	 */
	public void setSumContrAmt(String sumContrAmt) {
		this.sumContrAmt = sumContrAmt;
	}

}
