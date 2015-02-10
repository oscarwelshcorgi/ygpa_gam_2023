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
	private	String sFcltsNo;				// 검색 시설물 번호
	private	String sFcltsNm;				// 검색 시설물 명
	private	String sFcltsGbn;				// 검색 시설물 종별
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
