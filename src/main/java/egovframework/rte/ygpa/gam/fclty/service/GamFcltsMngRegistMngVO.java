/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 10.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltsMngRegistMngVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	private	String fcltsNo;					// 시설물 번호
	private	String fcltsNm;					// 시설물 명
	private	String route;					// 노선
	private	String fcltsGbn;				// 시설물 종별
	private	String fcltsSe;					// 시설물 구분
	private	String fcltsKnd;				// 시설물 종류
	private	String locDo;					// 위치(시/도)
	private	String locSi;					// 위치(시/군/구)
	private	String locDong;					// 위치(읍/면/동)
	private	String locJibun;				// LOC_JIBUN
	private	String mngMainbdSe;				// 관리 주체 구분
	private	String mngMainbd;				// 관리 주체
	private	String owner;					// 소유자
	private	String bldDt;					// 준공 일자
	private	String flawEndDt;				// 하자 만료 일자
	private	String dtlsSpecYn;				// 상세 제원 유무
	private	String qcHistYn;				// 점검 이력 유무
	private	String mntnHistYn;				// 보수 이력 유무
	private	String planBeginDt;				// 설계 시작 일자
	private	String planEndDt;				// 설계 종료 일자
	private	String planner;					// 설계자
	private	String cnstBeginDt;				// 공사 시작 일자
	private	String cnstEndDt;				// 공사 종료 일자
	private	String cnstrtr;					// 시공자
	private	String cnstrctAmt;				// 시공 금액
	private	String erqProofPlanApplcEnnc;	// 내진 설계 적용 유무
	private	String inspectBeginDt;			// 감리 시작 일자
	private	String inspectEndDt;			// 감리 종료 일자
	private	String inspector;				// 감리자
	private	String cnstOrderBody;			// 공사 발주자
	private	String cnstNm;					// 공사 명
	private	String cnstSupervisor;			// 공사 감독관
	private	String planBookMnten;			// 설계 도서 보존
	private	String wrtDt;					// 작성 일자
	private	String wrtUsr;					// 작성자
	private	String lastUpdtDt;				// 최종 수정 일자
	private	String lastUpdtUsr;				// 최종 수정자
	private	String basicRm;					// 비고
	private	String maxShipScl;				// 최대 계류 선박 규모
	private	String extd;					// 연장
	private	String upsideAlt;				// 천단고
	private	String dpwt;					// 수심
	private	String fmt1Desc1;				// 구조형식-중력식-케이슨식
	private	String fmt1Desc2;				// 구조형식-중력식-L형블럭
	private	String fmt1Desc3;				// 구조형식-중력식-셀룰러블럭식
	private	String fmt1Desc4;				// 구조형식-중력식-현장타설식
	private	String fmt2Desc11;				// 구조형식-잔교식-말뚝식-구경
	private	String fmt2Desc12;				// 구조형식-잔교식-말뚝식-연장
	private	String fmt2Desc13;				// 구조형식-잔교식-말뚝식-본수
	private	String fmt2Desc2;				// 구조형식-잔교식-원통식
	private	String fmt2Desc3;				// 구조형식-잔교식-교각식
	private	String fmt3Desc1;				// 구조형식-널말뚝식-규격
	private	String fmt3Desc2;				// 구조형식-널말뚝식-기타
	private	String etcDtlsSpec;				// 기타 상세제원
	private	String fcltsMngGroupNo;			// 시설물 관리 그룹 번호
	private	String regUsr;					// 등록자
	private	String registDt;				// 등록일시
	private	String updUsr;					// 수정자
	private	String updtDt;					// 수정일시
	private	String loc;						// 위치
	private	String fcltsGbnNm;				// 시설물 종별 명
	private	String fcltsSeNm;				// 시설물 구분 명
	private	String fcltsKndNm;				// 시설물 종류 명
	private	String fcltsMngGroupNm;			// 시설물 관리 그룹 명
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
	private	String groundFloorCn;			// 지상 층수
	private	String topFloorCn;				// 옥탑 층수
	private	String underFloorCn;			// 지하 층수
	private	String highHt;					// 최고 높이
	private	String highFllorHt;				// 최고층 높이
	private	String highFllorHtPos;			// 최고층 해당층
	private	String baseSideDp;				// 기초 밑면 깊이
	private	String floorArRate;				// 건폐율
	private	String capaRate;				// 용적율
	private	String dtMaxUsageCn;			// 일시 최대 사용 인원
	private	String dtUsageCn;				// 1일 사용 인원
	private	String bldMntnMngSysYn;			// 건물유지관리시스템 유무
	private	String mntnMngAddFcltyYn;		// 유지관리 부대시설 유무
	private	String apptTp;					// 지정 형태
	private	String bridgeStartLoc;			// 교량 시점 위치
	private	String bridgeEndLoc;			// 교량 종점 위치
	private	String planLiveWght;			// 설계활 하중
	private	String allowPassWght;			// 허용 통행 하중
	private	String extLt;					// 연장 길이
	private	String extSpanCn;				// 연장 경간수
	private	String extMaxSpanLt;			// 연장 최대 경간장
	private	String paveWd;					// 보도 폭
	private	String roadWd;					// 차도 폭
	private	String sumPaveRoadWd;			// 합계 폭
	private	String upRoadCn;				// 상행 차로 수
	private	String downRoadCn;				// 하행 차로 수
	private	String sumUpDownRoadCn;			// 합계 차로 수
	private	String spanComp;				// 경간 구성
	private	String mainSpanFmt;				// 주 경간 형식
	private	String sunSpanFmt;				// 부 경간 형식
	private	String propSe;					// 받침 종류
	private	String buldConnSe;				// 신축 이음 종류
	private	String subPassLmtHt;			// 하부 통과 제한 높이
	private	String bridgePierFmt1;			// 교각 형식 1
	private	String bridgePierCn1;			// 교각 갯수 1
	private	String bridgePierBaseFmt1;		// 교각 기초 형식 1
	private	String bridgePierFmt2;			// 교각 형식 2
	private	String bridgePierCn2;			// 교각 갯수 2
	private	String bridgePierBaseFmt2;		// 교각 기초 형식 2
	private	String bridgePropFmt1;			// 교대 형식 1
	private	String bridgePropBaseFmt1;		// 교대 기초 형식 1
	private	String bridgePropFmt2;			// 교대 형식 2
	private	String bridgePropBaseFmt2;		// 교대 기초 형식 2
	private	String crossRoute;				// 교차 노선
	private	String crossRiverHighDpwt;		// 교차 하천 최고 수심
	private	String sFcltsNo;				// 검색 시설물 번호
	private	String sFcltsNm;				// 검색 시설물 명
	private	String sFcltsGbn;				// 검색 시설물 종별
	private	String sFcltsJobSe;				// 검색 시설물 업무 구분
	private	String sStartBldDt;				// 검색 시작 준공 일자
	private	String sEndBldDt;				// 검색 종료 준공 일자
	private	String totalCount;				// 조회 자료 수
	private	String sumCnstrctAmt;			// 시공 금액 합계

	/**
	 * @return the fcltsNo
	 */
	public String getFcltsNo() {
		return fcltsNo;
	}
	/**
	 * @param fcltsNo the fcltsNo to set
	 */
	public void setFcltsNo(String fcltsNo) {
		this.fcltsNo = fcltsNo;
	}
	/**
	 * @return the fcltsNm
	 */
	public String getFcltsNm() {
		return fcltsNm;
	}
	/**
	 * @param fcltsNm the fcltsNm to set
	 */
	public void setFcltsNm(String fcltsNm) {
		this.fcltsNm = fcltsNm;
	}
	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}
	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}
	/**
	 * @return the fcltsGbn
	 */
	public String getFcltsGbn() {
		return fcltsGbn;
	}
	/**
	 * @param fcltsGbn the fcltsGbn to set
	 */
	public void setFcltsGbn(String fcltsGbn) {
		this.fcltsGbn = fcltsGbn;
	}
	/**
	 * @return the fcltsSe
	 */
	public String getFcltsSe() {
		return fcltsSe;
	}
	/**
	 * @param fcltsSe the fcltsSe to set
	 */
	public void setFcltsSe(String fcltsSe) {
		this.fcltsSe = fcltsSe;
	}
	/**
	 * @return the fcltsKnd
	 */
	public String getFcltsKnd() {
		return fcltsKnd;
	}
	/**
	 * @param fcltsKnd the fcltsKnd to set
	 */
	public void setFcltsKnd(String fcltsKnd) {
		this.fcltsKnd = fcltsKnd;
	}
	/**
	 * @return the locDo
	 */
	public String getLocDo() {
		return locDo;
	}
	/**
	 * @param locDo the locDo to set
	 */
	public void setLocDo(String locDo) {
		this.locDo = locDo;
	}
	/**
	 * @return the locSi
	 */
	public String getLocSi() {
		return locSi;
	}
	/**
	 * @param locSi the locSi to set
	 */
	public void setLocSi(String locSi) {
		this.locSi = locSi;
	}
	/**
	 * @return the locDong
	 */
	public String getLocDong() {
		return locDong;
	}
	/**
	 * @param locDong the locDong to set
	 */
	public void setLocDong(String locDong) {
		this.locDong = locDong;
	}
	/**
	 * @return the locJibun
	 */
	public String getLocJibun() {
		return locJibun;
	}
	/**
	 * @param locJibun the locJibun to set
	 */
	public void setLocJibun(String locJibun) {
		this.locJibun = locJibun;
	}
	/**
	 * @return the mngMainbdSe
	 */
	public String getMngMainbdSe() {
		return mngMainbdSe;
	}
	/**
	 * @param mngMainbdSe the mngMainbdSe to set
	 */
	public void setMngMainbdSe(String mngMainbdSe) {
		this.mngMainbdSe = mngMainbdSe;
	}
	/**
	 * @return the mngMainbd
	 */
	public String getMngMainbd() {
		return mngMainbd;
	}
	/**
	 * @param mngMainbd the mngMainbd to set
	 */
	public void setMngMainbd(String mngMainbd) {
		this.mngMainbd = mngMainbd;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
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
	 * @return the dtlsSpecYn
	 */
	public String getDtlsSpecYn() {
		return dtlsSpecYn;
	}
	/**
	 * @param dtlsSpecYn the dtlsSpecYn to set
	 */
	public void setDtlsSpecYn(String dtlsSpecYn) {
		this.dtlsSpecYn = dtlsSpecYn;
	}
	/**
	 * @return the qcHistYn
	 */
	public String getQcHistYn() {
		return qcHistYn;
	}
	/**
	 * @param qcHistYn the qcHistYn to set
	 */
	public void setQcHistYn(String qcHistYn) {
		this.qcHistYn = qcHistYn;
	}
	/**
	 * @return the mntnHistYn
	 */
	public String getMntnHistYn() {
		return mntnHistYn;
	}
	/**
	 * @param mntnHistYn the mntnHistYn to set
	 */
	public void setMntnHistYn(String mntnHistYn) {
		this.mntnHistYn = mntnHistYn;
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
	 * @return the planner
	 */
	public String getPlanner() {
		return planner;
	}
	/**
	 * @param planner the planner to set
	 */
	public void setPlanner(String planner) {
		this.planner = planner;
	}
	/**
	 * @return the cnstBeginDt
	 */
	public String getCnstBeginDt() {
		return cnstBeginDt;
	}
	/**
	 * @param cnstBeginDt the cnstBeginDt to set
	 */
	public void setCnstBeginDt(String cnstBeginDt) {
		this.cnstBeginDt = cnstBeginDt;
	}
	/**
	 * @return the cnstEndDt
	 */
	public String getCnstEndDt() {
		return cnstEndDt;
	}
	/**
	 * @param cnstEndDt the cnstEndDt to set
	 */
	public void setCnstEndDt(String cnstEndDt) {
		this.cnstEndDt = cnstEndDt;
	}
	/**
	 * @return the cnstrtr
	 */
	public String getCnstrtr() {
		return cnstrtr;
	}
	/**
	 * @param cnstrtr the cnstrtr to set
	 */
	public void setCnstrtr(String cnstrtr) {
		this.cnstrtr = cnstrtr;
	}
	/**
	 * @return the cnstrctAmt
	 */
	public String getCnstrctAmt() {
		return cnstrctAmt;
	}
	/**
	 * @param cnstrctAmt the cnstrctAmt to set
	 */
	public void setCnstrctAmt(String cnstrctAmt) {
		this.cnstrctAmt = cnstrctAmt;
	}
	/**
	 * @return the erqProofPlanApplcEnnc
	 */
	public String getErqProofPlanApplcEnnc() {
		return erqProofPlanApplcEnnc;
	}
	/**
	 * @param erqProofPlanApplcEnnc the erqProofPlanApplcEnnc to set
	 */
	public void setErqProofPlanApplcEnnc(String erqProofPlanApplcEnnc) {
		this.erqProofPlanApplcEnnc = erqProofPlanApplcEnnc;
	}
	/**
	 * @return the inspectBeginDt
	 */
	public String getInspectBeginDt() {
		return inspectBeginDt;
	}
	/**
	 * @param inspectBeginDt the inspectBeginDt to set
	 */
	public void setInspectBeginDt(String inspectBeginDt) {
		this.inspectBeginDt = inspectBeginDt;
	}
	/**
	 * @return the inspectEndDt
	 */
	public String getInspectEndDt() {
		return inspectEndDt;
	}
	/**
	 * @param inspectEndDt the inspectEndDt to set
	 */
	public void setInspectEndDt(String inspectEndDt) {
		this.inspectEndDt = inspectEndDt;
	}
	/**
	 * @return the inspector
	 */
	public String getInspector() {
		return inspector;
	}
	/**
	 * @param inspector the inspector to set
	 */
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	/**
	 * @return the cnstOrderBody
	 */
	public String getCnstOrderBody() {
		return cnstOrderBody;
	}
	/**
	 * @param cnstOrderBody the cnstOrderBody to set
	 */
	public void setCnstOrderBody(String cnstOrderBody) {
		this.cnstOrderBody = cnstOrderBody;
	}
	/**
	 * @return the cnstNm
	 */
	public String getCnstNm() {
		return cnstNm;
	}
	/**
	 * @param cnstNm the cnstNm to set
	 */
	public void setCnstNm(String cnstNm) {
		this.cnstNm = cnstNm;
	}
	/**
	 * @return the cnstSupervisor
	 */
	public String getCnstSupervisor() {
		return cnstSupervisor;
	}
	/**
	 * @param cnstSupervisor the cnstSupervisor to set
	 */
	public void setCnstSupervisor(String cnstSupervisor) {
		this.cnstSupervisor = cnstSupervisor;
	}
	/**
	 * @return the planBookMnten
	 */
	public String getPlanBookMnten() {
		return planBookMnten;
	}
	/**
	 * @param planBookMnten the planBookMnten to set
	 */
	public void setPlanBookMnten(String planBookMnten) {
		this.planBookMnten = planBookMnten;
	}
	/**
	 * @return the wrtDt
	 */
	public String getWrtDt() {
		return wrtDt;
	}
	/**
	 * @param wrtDt the wrtDt to set
	 */
	public void setWrtDt(String wrtDt) {
		this.wrtDt = wrtDt;
	}
	/**
	 * @return the wrtUsr
	 */
	public String getWrtUsr() {
		return wrtUsr;
	}
	/**
	 * @param wrtUsr the wrtUsr to set
	 */
	public void setWrtUsr(String wrtUsr) {
		this.wrtUsr = wrtUsr;
	}
	/**
	 * @return the lastUpdtDt
	 */
	public String getLastUpdtDt() {
		return lastUpdtDt;
	}
	/**
	 * @param lastUpdtDt the lastUpdtDt to set
	 */
	public void setLastUpdtDt(String lastUpdtDt) {
		this.lastUpdtDt = lastUpdtDt;
	}
	/**
	 * @return the lastUpdtUsr
	 */
	public String getLastUpdtUsr() {
		return lastUpdtUsr;
	}
	/**
	 * @param lastUpdtUsr the lastUpdtUsr to set
	 */
	public void setLastUpdtUsr(String lastUpdtUsr) {
		this.lastUpdtUsr = lastUpdtUsr;
	}
	/**
	 * @return the basicRm
	 */
	public String getBasicRm() {
		return basicRm;
	}
	/**
	 * @param basicRm the basicRm to set
	 */
	public void setBasicRm(String basicRm) {
		this.basicRm = basicRm;
	}
	/**
	 * @return the maxShipScl
	 */
	public String getMaxShipScl() {
		return maxShipScl;
	}
	/**
	 * @param maxShipScl the maxShipScl to set
	 */
	public void setMaxShipScl(String maxShipScl) {
		this.maxShipScl = maxShipScl;
	}
	/**
	 * @return the extd
	 */
	public String getExtd() {
		return extd;
	}
	/**
	 * @param extd the extd to set
	 */
	public void setExtd(String extd) {
		this.extd = extd;
	}
	/**
	 * @return the upsideAlt
	 */
	public String getUpsideAlt() {
		return upsideAlt;
	}
	/**
	 * @param upsideAlt the upsideAlt to set
	 */
	public void setUpsideAlt(String upsideAlt) {
		this.upsideAlt = upsideAlt;
	}
	/**
	 * @return the dpwt
	 */
	public String getDpwt() {
		return dpwt;
	}
	/**
	 * @param dpwt the dpwt to set
	 */
	public void setDpwt(String dpwt) {
		this.dpwt = dpwt;
	}
	/**
	 * @return the fmt1Desc1
	 */
	public String getFmt1Desc1() {
		return fmt1Desc1;
	}
	/**
	 * @param fmt1Desc1 the fmt1Desc1 to set
	 */
	public void setFmt1Desc1(String fmt1Desc1) {
		this.fmt1Desc1 = fmt1Desc1;
	}
	/**
	 * @return the fmt1Desc2
	 */
	public String getFmt1Desc2() {
		return fmt1Desc2;
	}
	/**
	 * @param fmt1Desc2 the fmt1Desc2 to set
	 */
	public void setFmt1Desc2(String fmt1Desc2) {
		this.fmt1Desc2 = fmt1Desc2;
	}
	/**
	 * @return the fmt1Desc3
	 */
	public String getFmt1Desc3() {
		return fmt1Desc3;
	}
	/**
	 * @param fmt1Desc3 the fmt1Desc3 to set
	 */
	public void setFmt1Desc3(String fmt1Desc3) {
		this.fmt1Desc3 = fmt1Desc3;
	}
	/**
	 * @return the fmt1Desc4
	 */
	public String getFmt1Desc4() {
		return fmt1Desc4;
	}
	/**
	 * @param fmt1Desc4 the fmt1Desc4 to set
	 */
	public void setFmt1Desc4(String fmt1Desc4) {
		this.fmt1Desc4 = fmt1Desc4;
	}
	/**
	 * @return the fmt2Desc11
	 */
	public String getFmt2Desc11() {
		return fmt2Desc11;
	}
	/**
	 * @param fmt2Desc11 the fmt2Desc11 to set
	 */
	public void setFmt2Desc11(String fmt2Desc11) {
		this.fmt2Desc11 = fmt2Desc11;
	}
	/**
	 * @return the fmt2Desc12
	 */
	public String getFmt2Desc12() {
		return fmt2Desc12;
	}
	/**
	 * @param fmt2Desc12 the fmt2Desc12 to set
	 */
	public void setFmt2Desc12(String fmt2Desc12) {
		this.fmt2Desc12 = fmt2Desc12;
	}
	/**
	 * @return the fmt2Desc13
	 */
	public String getFmt2Desc13() {
		return fmt2Desc13;
	}
	/**
	 * @param fmt2Desc13 the fmt2Desc13 to set
	 */
	public void setFmt2Desc13(String fmt2Desc13) {
		this.fmt2Desc13 = fmt2Desc13;
	}
	/**
	 * @return the fmt2Desc2
	 */
	public String getFmt2Desc2() {
		return fmt2Desc2;
	}
	/**
	 * @param fmt2Desc2 the fmt2Desc2 to set
	 */
	public void setFmt2Desc2(String fmt2Desc2) {
		this.fmt2Desc2 = fmt2Desc2;
	}
	/**
	 * @return the fmt2Desc3
	 */
	public String getFmt2Desc3() {
		return fmt2Desc3;
	}
	/**
	 * @param fmt2Desc3 the fmt2Desc3 to set
	 */
	public void setFmt2Desc3(String fmt2Desc3) {
		this.fmt2Desc3 = fmt2Desc3;
	}
	/**
	 * @return the fmt3Desc1
	 */
	public String getFmt3Desc1() {
		return fmt3Desc1;
	}
	/**
	 * @param fmt3Desc1 the fmt3Desc1 to set
	 */
	public void setFmt3Desc1(String fmt3Desc1) {
		this.fmt3Desc1 = fmt3Desc1;
	}
	/**
	 * @return the fmt3Desc2
	 */
	public String getFmt3Desc2() {
		return fmt3Desc2;
	}
	/**
	 * @param fmt3Desc2 the fmt3Desc2 to set
	 */
	public void setFmt3Desc2(String fmt3Desc2) {
		this.fmt3Desc2 = fmt3Desc2;
	}
	/**
	 * @return the etcDtlsSpec
	 */
	public String getEtcDtlsSpec() {
		return etcDtlsSpec;
	}
	/**
	 * @param etcDtlsSpec the etcDtlsSpec to set
	 */
	public void setEtcDtlsSpec(String etcDtlsSpec) {
		this.etcDtlsSpec = etcDtlsSpec;
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
	 * @return the fcltsGbnNm
	 */
	public String getFcltsGbnNm() {
		return fcltsGbnNm;
	}
	/**
	 * @param fcltsGbnNm the fcltsGbnNm to set
	 */
	public void setFcltsGbnNm(String fcltsGbnNm) {
		this.fcltsGbnNm = fcltsGbnNm;
	}
	/**
	 * @return the fcltsSeNm
	 */
	public String getFcltsSeNm() {
		return fcltsSeNm;
	}
	/**
	 * @param fcltsSeNm the fcltsSeNm to set
	 */
	public void setFcltsSeNm(String fcltsSeNm) {
		this.fcltsSeNm = fcltsSeNm;
	}
	/**
	 * @return the fcltsKndNm
	 */
	public String getFcltsKndNm() {
		return fcltsKndNm;
	}
	/**
	 * @param fcltsKndNm the fcltsKndNm to set
	 */
	public void setFcltsKndNm(String fcltsKndNm) {
		this.fcltsKndNm = fcltsKndNm;
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
	 * @return the groundFloorCn
	 */
	public String getGroundFloorCn() {
		return groundFloorCn;
	}
	/**
	 * @param groundFloorCn the groundFloorCn to set
	 */
	public void setGroundFloorCn(String groundFloorCn) {
		this.groundFloorCn = groundFloorCn;
	}
	/**
	 * @return the topFloorCn
	 */
	public String getTopFloorCn() {
		return topFloorCn;
	}
	/**
	 * @param topFloorCn the topFloorCn to set
	 */
	public void setTopFloorCn(String topFloorCn) {
		this.topFloorCn = topFloorCn;
	}
	/**
	 * @return the underFloorCn
	 */
	public String getUnderFloorCn() {
		return underFloorCn;
	}
	/**
	 * @param underFloorCn the underFloorCn to set
	 */
	public void setUnderFloorCn(String underFloorCn) {
		this.underFloorCn = underFloorCn;
	}
	/**
	 * @return the highHt
	 */
	public String getHighHt() {
		return highHt;
	}
	/**
	 * @param highHt the highHt to set
	 */
	public void setHighHt(String highHt) {
		this.highHt = highHt;
	}
	/**
	 * @return the highFllorHt
	 */
	public String getHighFllorHt() {
		return highFllorHt;
	}
	/**
	 * @param highFllorHt the highFllorHt to set
	 */
	public void setHighFllorHt(String highFllorHt) {
		this.highFllorHt = highFllorHt;
	}
	/**
	 * @return the highFllorHtPos
	 */
	public String getHighFllorHtPos() {
		return highFllorHtPos;
	}
	/**
	 * @param highFllorHtPos the highFllorHtPos to set
	 */
	public void setHighFllorHtPos(String highFllorHtPos) {
		this.highFllorHtPos = highFllorHtPos;
	}
	/**
	 * @return the baseSideDp
	 */
	public String getBaseSideDp() {
		return baseSideDp;
	}
	/**
	 * @param baseSideDp the baseSideDp to set
	 */
	public void setBaseSideDp(String baseSideDp) {
		this.baseSideDp = baseSideDp;
	}
	/**
	 * @return the floorArRate
	 */
	public String getFloorArRate() {
		return floorArRate;
	}
	/**
	 * @param floorArRate the floorArRate to set
	 */
	public void setFloorArRate(String floorArRate) {
		this.floorArRate = floorArRate;
	}
	/**
	 * @return the capaRate
	 */
	public String getCapaRate() {
		return capaRate;
	}
	/**
	 * @param capaRate the capaRate to set
	 */
	public void setCapaRate(String capaRate) {
		this.capaRate = capaRate;
	}
	/**
	 * @return the dtMaxUsageCn
	 */
	public String getDtMaxUsageCn() {
		return dtMaxUsageCn;
	}
	/**
	 * @param dtMaxUsageCn the dtMaxUsageCn to set
	 */
	public void setDtMaxUsageCn(String dtMaxUsageCn) {
		this.dtMaxUsageCn = dtMaxUsageCn;
	}
	/**
	 * @return the dtUsageCn
	 */
	public String getDtUsageCn() {
		return dtUsageCn;
	}
	/**
	 * @param dtUsageCn the dtUsageCn to set
	 */
	public void setDtUsageCn(String dtUsageCn) {
		this.dtUsageCn = dtUsageCn;
	}
	/**
	 * @return the bldMntnMngSysYn
	 */
	public String getBldMntnMngSysYn() {
		return bldMntnMngSysYn;
	}
	/**
	 * @param bldMntnMngSysYn the bldMntnMngSysYn to set
	 */
	public void setBldMntnMngSysYn(String bldMntnMngSysYn) {
		this.bldMntnMngSysYn = bldMntnMngSysYn;
	}
	/**
	 * @return the mntnMngAddFcltyYn
	 */
	public String getMntnMngAddFcltyYn() {
		return mntnMngAddFcltyYn;
	}
	/**
	 * @param mntnMngAddFcltyYn the mntnMngAddFcltyYn to set
	 */
	public void setMntnMngAddFcltyYn(String mntnMngAddFcltyYn) {
		this.mntnMngAddFcltyYn = mntnMngAddFcltyYn;
	}
	/**
	 * @return the apptTp
	 */
	public String getApptTp() {
		return apptTp;
	}
	/**
	 * @param apptTp the apptTp to set
	 */
	public void setApptTp(String apptTp) {
		this.apptTp = apptTp;
	}
	/**
	 * @return the bridgeStartLoc
	 */
	public String getBridgeStartLoc() {
		return bridgeStartLoc;
	}
	/**
	 * @param bridgeStartLoc the bridgeStartLoc to set
	 */
	public void setBridgeStartLoc(String bridgeStartLoc) {
		this.bridgeStartLoc = bridgeStartLoc;
	}
	/**
	 * @return the bridgeEndLoc
	 */
	public String getBridgeEndLoc() {
		return bridgeEndLoc;
	}
	/**
	 * @param bridgeEndLoc the bridgeEndLoc to set
	 */
	public void setBridgeEndLoc(String bridgeEndLoc) {
		this.bridgeEndLoc = bridgeEndLoc;
	}
	/**
	 * @return the planLiveWght
	 */
	public String getPlanLiveWght() {
		return planLiveWght;
	}
	/**
	 * @param planLiveWght the planLiveWght to set
	 */
	public void setPlanLiveWght(String planLiveWght) {
		this.planLiveWght = planLiveWght;
	}
	/**
	 * @return the allowPassWght
	 */
	public String getAllowPassWght() {
		return allowPassWght;
	}
	/**
	 * @param allowPassWght the allowPassWght to set
	 */
	public void setAllowPassWght(String allowPassWght) {
		this.allowPassWght = allowPassWght;
	}
	/**
	 * @return the extLt
	 */
	public String getExtLt() {
		return extLt;
	}
	/**
	 * @param extLt the extLt to set
	 */
	public void setExtLt(String extLt) {
		this.extLt = extLt;
	}
	/**
	 * @return the extSpanCn
	 */
	public String getExtSpanCn() {
		return extSpanCn;
	}
	/**
	 * @param extSpanCn the extSpanCn to set
	 */
	public void setExtSpanCn(String extSpanCn) {
		this.extSpanCn = extSpanCn;
	}
	/**
	 * @return the extMaxSpanLt
	 */
	public String getExtMaxSpanLt() {
		return extMaxSpanLt;
	}
	/**
	 * @param extMaxSpanLt the extMaxSpanLt to set
	 */
	public void setExtMaxSpanLt(String extMaxSpanLt) {
		this.extMaxSpanLt = extMaxSpanLt;
	}
	/**
	 * @return the paveWd
	 */
	public String getPaveWd() {
		return paveWd;
	}
	/**
	 * @param paveWd the paveWd to set
	 */
	public void setPaveWd(String paveWd) {
		this.paveWd = paveWd;
	}
	/**
	 * @return the roadWd
	 */
	public String getRoadWd() {
		return roadWd;
	}
	/**
	 * @param roadWd the roadWd to set
	 */
	public void setRoadWd(String roadWd) {
		this.roadWd = roadWd;
	}
	/**
	 * @return the sumPaveRoadWd
	 */
	public String getSumPaveRoadWd() {
		return sumPaveRoadWd;
	}
	/**
	 * @param sumPaveRoadWd the sumPaveRoadWd to set
	 */
	public void setSumPaveRoadWd(String sumPaveRoadWd) {
		this.sumPaveRoadWd = sumPaveRoadWd;
	}
	/**
	 * @return the upRoadCn
	 */
	public String getUpRoadCn() {
		return upRoadCn;
	}
	/**
	 * @param upRoadCn the upRoadCn to set
	 */
	public void setUpRoadCn(String upRoadCn) {
		this.upRoadCn = upRoadCn;
	}
	/**
	 * @return the downRoadCn
	 */
	public String getDownRoadCn() {
		return downRoadCn;
	}
	/**
	 * @param downRoadCn the downRoadCn to set
	 */
	public void setDownRoadCn(String downRoadCn) {
		this.downRoadCn = downRoadCn;
	}
	/**
	 * @return the sumUpDownRoadCn
	 */
	public String getSumUpDownRoadCn() {
		return sumUpDownRoadCn;
	}
	/**
	 * @param sumUpDownRoadCn the sumUpDownRoadCn to set
	 */
	public void setSumUpDownRoadCn(String sumUpDownRoadCn) {
		this.sumUpDownRoadCn = sumUpDownRoadCn;
	}
	/**
	 * @return the spanComp
	 */
	public String getSpanComp() {
		return spanComp;
	}
	/**
	 * @param spanComp the spanComp to set
	 */
	public void setSpanComp(String spanComp) {
		this.spanComp = spanComp;
	}
	/**
	 * @return the mainSpanFmt
	 */
	public String getMainSpanFmt() {
		return mainSpanFmt;
	}
	/**
	 * @param mainSpanFmt the mainSpanFmt to set
	 */
	public void setMainSpanFmt(String mainSpanFmt) {
		this.mainSpanFmt = mainSpanFmt;
	}
	/**
	 * @return the sunSpanFmt
	 */
	public String getSunSpanFmt() {
		return sunSpanFmt;
	}
	/**
	 * @param sunSpanFmt the sunSpanFmt to set
	 */
	public void setSunSpanFmt(String sunSpanFmt) {
		this.sunSpanFmt = sunSpanFmt;
	}
	/**
	 * @return the propSe
	 */
	public String getPropSe() {
		return propSe;
	}
	/**
	 * @param propSe the propSe to set
	 */
	public void setPropSe(String propSe) {
		this.propSe = propSe;
	}
	/**
	 * @return the buldConnSe
	 */
	public String getBuldConnSe() {
		return buldConnSe;
	}
	/**
	 * @param buldConnSe the buldConnSe to set
	 */
	public void setBuldConnSe(String buldConnSe) {
		this.buldConnSe = buldConnSe;
	}
	/**
	 * @return the subPassLmtHt
	 */
	public String getSubPassLmtHt() {
		return subPassLmtHt;
	}
	/**
	 * @param subPassLmtHt the subPassLmtHt to set
	 */
	public void setSubPassLmtHt(String subPassLmtHt) {
		this.subPassLmtHt = subPassLmtHt;
	}
	/**
	 * @return the bridgePierFmt1
	 */
	public String getBridgePierFmt1() {
		return bridgePierFmt1;
	}
	/**
	 * @param bridgePierFmt1 the bridgePierFmt1 to set
	 */
	public void setBridgePierFmt1(String bridgePierFmt1) {
		this.bridgePierFmt1 = bridgePierFmt1;
	}
	/**
	 * @return the bridgePierCn1
	 */
	public String getBridgePierCn1() {
		return bridgePierCn1;
	}
	/**
	 * @param bridgePierCn1 the bridgePierCn1 to set
	 */
	public void setBridgePierCn1(String bridgePierCn1) {
		this.bridgePierCn1 = bridgePierCn1;
	}
	/**
	 * @return the bridgePierBaseFmt1
	 */
	public String getBridgePierBaseFmt1() {
		return bridgePierBaseFmt1;
	}
	/**
	 * @param bridgePierBaseFmt1 the bridgePierBaseFmt1 to set
	 */
	public void setBridgePierBaseFmt1(String bridgePierBaseFmt1) {
		this.bridgePierBaseFmt1 = bridgePierBaseFmt1;
	}
	/**
	 * @return the bridgePierFmt2
	 */
	public String getBridgePierFmt2() {
		return bridgePierFmt2;
	}
	/**
	 * @param bridgePierFmt2 the bridgePierFmt2 to set
	 */
	public void setBridgePierFmt2(String bridgePierFmt2) {
		this.bridgePierFmt2 = bridgePierFmt2;
	}
	/**
	 * @return the bridgePierCn2
	 */
	public String getBridgePierCn2() {
		return bridgePierCn2;
	}
	/**
	 * @param bridgePierCn2 the bridgePierCn2 to set
	 */
	public void setBridgePierCn2(String bridgePierCn2) {
		this.bridgePierCn2 = bridgePierCn2;
	}
	/**
	 * @return the bridgePierBaseFmt2
	 */
	public String getBridgePierBaseFmt2() {
		return bridgePierBaseFmt2;
	}
	/**
	 * @param bridgePierBaseFmt2 the bridgePierBaseFmt2 to set
	 */
	public void setBridgePierBaseFmt2(String bridgePierBaseFmt2) {
		this.bridgePierBaseFmt2 = bridgePierBaseFmt2;
	}
	/**
	 * @return the bridgePropFmt1
	 */
	public String getBridgePropFmt1() {
		return bridgePropFmt1;
	}
	/**
	 * @param bridgePropFmt1 the bridgePropFmt1 to set
	 */
	public void setBridgePropFmt1(String bridgePropFmt1) {
		this.bridgePropFmt1 = bridgePropFmt1;
	}
	/**
	 * @return the bridgePropBaseFmt1
	 */
	public String getBridgePropBaseFmt1() {
		return bridgePropBaseFmt1;
	}
	/**
	 * @param bridgePropBaseFmt1 the bridgePropBaseFmt1 to set
	 */
	public void setBridgePropBaseFmt1(String bridgePropBaseFmt1) {
		this.bridgePropBaseFmt1 = bridgePropBaseFmt1;
	}
	/**
	 * @return the bridgePropFmt2
	 */
	public String getBridgePropFmt2() {
		return bridgePropFmt2;
	}
	/**
	 * @param bridgePropFmt2 the bridgePropFmt2 to set
	 */
	public void setBridgePropFmt2(String bridgePropFmt2) {
		this.bridgePropFmt2 = bridgePropFmt2;
	}
	/**
	 * @return the bridgePropBaseFmt2
	 */
	public String getBridgePropBaseFmt2() {
		return bridgePropBaseFmt2;
	}
	/**
	 * @param bridgePropBaseFmt2 the bridgePropBaseFmt2 to set
	 */
	public void setBridgePropBaseFmt2(String bridgePropBaseFmt2) {
		this.bridgePropBaseFmt2 = bridgePropBaseFmt2;
	}
	/**
	 * @return the crossRoute
	 */
	public String getCrossRoute() {
		return crossRoute;
	}
	/**
	 * @param crossRoute the crossRoute to set
	 */
	public void setCrossRoute(String crossRoute) {
		this.crossRoute = crossRoute;
	}
	/**
	 * @return the crossRiverHighDpwt
	 */
	public String getCrossRiverHighDpwt() {
		return crossRiverHighDpwt;
	}
	/**
	 * @param crossRiverHighDpwt the crossRiverHighDpwt to set
	 */
	public void setCrossRiverHighDpwt(String crossRiverHighDpwt) {
		this.crossRiverHighDpwt = crossRiverHighDpwt;
	}
	/**
	 * @return the sFcltsNo
	 */
	public String getsFcltsNo() {
		return sFcltsNo;
	}
	/**
	 * @param sFcltsNo the sFcltsNo to set
	 */
	public void setsFcltsNo(String sFcltsNo) {
		this.sFcltsNo = sFcltsNo;
	}
	/**
	 * @return the sFcltsNm
	 */
	public String getsFcltsNm() {
		return sFcltsNm;
	}
	/**
	 * @param sFcltsNm the sFcltsNm to set
	 */
	public void setsFcltsNm(String sFcltsNm) {
		this.sFcltsNm = sFcltsNm;
	}
	/**
	 * @return the sFcltsGbn
	 */
	public String getsFcltsGbn() {
		return sFcltsGbn;
	}
	/**
	 * @param sFcltsGbn the sFcltsGbn to set
	 */
	public void setsFcltsGbn(String sFcltsGbn) {
		this.sFcltsGbn = sFcltsGbn;
	}
	/**
	 * @return the sFcltsJobSe
	 */
	public String getsFcltsJobSe() {
		return sFcltsJobSe;
	}
	/**
	 * @param sFcltsJobSe the sFcltsJobSe to set
	 */
	public void setsFcltsJobSe(String sFcltsJobSe) {
		this.sFcltsJobSe = sFcltsJobSe;
	}
	/**
	 * @return the sStartBldDt
	 */
	public String getsStartBldDt() {
		return sStartBldDt;
	}
	/**
	 * @param sStartBldDt the sStartBldDt to set
	 */
	public void setsStartBldDt(String sStartBldDt) {
		this.sStartBldDt = sStartBldDt;
	}
	/**
	 * @return the sEndBldDt
	 */
	public String getsEndBldDt() {
		return sEndBldDt;
	}
	/**
	 * @param sEndBldDt the sEndBldDt to set
	 */
	public void setsEndBldDt(String sEndBldDt) {
		this.sEndBldDt = sEndBldDt;
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
	 * @return the sumCnstrctAmt
	 */
	public String getSumCnstrctAmt() {
		return sumCnstrctAmt;
	}
	/**
	 * @param sumCnstrctAmt the sumCnstrctAmt to set
	 */
	public void setSumCnstrctAmt(String sumCnstrctAmt) {
		this.sumCnstrctAmt = sumCnstrctAmt;
	}

}
