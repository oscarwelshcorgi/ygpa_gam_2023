/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamCarRefuelSttusMngVo extends ComDefaultVO{

	private	String	carRegistNo;	//차량 등록 번호
	private	String	carKnd;			//차량 종류
	private	String	carPrpos;		//차량 용도
	private	String	carNm;			//차량 명
	private	String	carFmt;			//차량 형식
	private	String	carYrMdl;		//차량 연식
	private	String	carBodyNo;		//차대 번호
	private	String	turbineFmt;		//원동기 형식
	private	String	usageStrhld;	//사용 본거지
	private	String	ownerNm;		//소유자 명
	private	String	ownerAdres;		//소유자 주소
	private	String	carRegistDt;	//차량 등록 일자
	private	String	registGovOfc;	//등록 관청
	private	String	specMngNo;		//제원 관리 번호
	private	String	carLt;			//차량 길이
	private	String	carWd;			//차량 너비
	private	String	carHt;			//차량 높이
	private	String	carGrWqnt;		//차량 총 중량
	private	String	exhaustqy;		//배기량
	private	String	rateOutput;		//정격 출력
	private	String	rideQuotaCapa;	//승차 정원
	private	String	maxCapaQy;		//최대 적재 량
	private	String	cylinderCnt;	//기통 갯수
	private	String	fuelKnd;		//연료 종류
	private	String	fuelEfft;		//연비
	private	String	examValidBeginDt;	//검사 유효 시작일
	private	String	examValidEndDt;	//검사 유효 종료일
	private	String	acqPrce;		//취득 가격
	private	String	regUsr;			//등록자
	private	String	updUsr;			//수정자
	private	String	updtDt;			//수정일시
	private	String	registDt;		//등록일시

	private String sCarRegistNo;	//검색 차량 등록번호
	private String sCarNm;	//검색 차량 명
	private String sCarBodyNo;	//검색 차대 번호
	private String sRefuelMt;	//검색 주유 월
	private	String sFuelKnd1;	// 검색 연료 종류 1
	private	String sFuelKnd2;	// 검색 연료 종류 2
	private	String sFuelKnd3;	// 검색 연료 종류 3
	private	String sFuelKnd4;	// 검색 연료 종류 4
	private	String sFuelKnd5;	// 검색 연료 종류 5
	private	String sFuelKnd6;	// 검색 연료 종류 6

	private String refuelMt;		//주유 년/월
	private String refuelQy;		//주유 량

	private String check[];


	/**
	 * @return the sRefuelMt
	 */
	public String getsRefuelMt() {
		return sRefuelMt;
	}
	/**
	 * @param sRefuelMt the sRefuelMt to set
	 */
	public void setsRefuelMt(String sRefuelMt) {
		this.sRefuelMt = sRefuelMt;
	}
	/**
	 * @return the check
	 */
	public String[] getCheck() {
		return check;
	}
	/**
	 * @param check the check to set
	 */
	public void setCheck(String[] check) {
		this.check = check;
	}
	private	String	m1	;	//1월 주유
	private	String	m2	;	//2월 주유
	private	String	m3	;	//3월 주유
	private	String	m4	;	//4월 주유
	private	String	m5	;	//5월 주유
	private	String	m6	;	//6월 주유
	private	String	m7	;	//7월 주유
	private	String	m8	;	//8월 주유
	private	String	m9	;	//9월 주유
	private	String	m10	;	//10월 주유
	private	String	m11	;	//11월 주유
	private	String	m12	;	//12월 주유


	/**
	 * @return the m1
	 */
	public String getM1() {
		return m1;
	}
	/**
	 * @param m1 the m1 to set
	 */
	public void setM1(String m1) {
		this.m1 = m1;
	}
	/**
	 * @return the m2
	 */
	public String getM2() {
		return m2;
	}
	/**
	 * @param m2 the m2 to set
	 */
	public void setM2(String m2) {
		this.m2 = m2;
	}
	/**
	 * @return the m3
	 */
	public String getM3() {
		return m3;
	}
	/**
	 * @param m3 the m3 to set
	 */
	public void setM3(String m3) {
		this.m3 = m3;
	}
	/**
	 * @return the m4
	 */
	public String getM4() {
		return m4;
	}
	/**
	 * @param m4 the m4 to set
	 */
	public void setM4(String m4) {
		this.m4 = m4;
	}
	/**
	 * @return the m5
	 */
	public String getM5() {
		return m5;
	}
	/**
	 * @param m5 the m5 to set
	 */
	public void setM5(String m5) {
		this.m5 = m5;
	}
	/**
	 * @return the m6
	 */
	public String getM6() {
		return m6;
	}
	/**
	 * @param m6 the m6 to set
	 */
	public void setM6(String m6) {
		this.m6 = m6;
	}
	/**
	 * @return the m7
	 */
	public String getM7() {
		return m7;
	}
	/**
	 * @param m7 the m7 to set
	 */
	public void setM7(String m7) {
		this.m7 = m7;
	}
	/**
	 * @return the m8
	 */
	public String getM8() {
		return m8;
	}
	/**
	 * @param m8 the m8 to set
	 */
	public void setM8(String m8) {
		this.m8 = m8;
	}
	/**
	 * @return the m9
	 */
	public String getM9() {
		return m9;
	}
	/**
	 * @param m9 the m9 to set
	 */
	public void setM9(String m9) {
		this.m9 = m9;
	}
	/**
	 * @return the m10
	 */
	public String getM10() {
		return m10;
	}
	/**
	 * @param m10 the m10 to set
	 */
	public void setM10(String m10) {
		this.m10 = m10;
	}
	/**
	 * @return the m11
	 */
	public String getM11() {
		return m11;
	}
	/**
	 * @param m11 the m11 to set
	 */
	public void setM11(String m11) {
		this.m11 = m11;
	}
	/**
	 * @return the m12
	 */
	public String getM12() {
		return m12;
	}
	/**
	 * @param m12 the m12 to set
	 */
	public void setM12(String m12) {
		this.m12 = m12;
	}
	/**
	 * @return the refuelMt
	 */
	public String getRefuelMt() {
		return refuelMt;
	}
	/**
	 * @param refuelMt the refuelMt to set
	 */
	public void setRefuelMt(String refuelMt) {
		this.refuelMt = refuelMt;
	}
	/**
	 * @return the refuelQy
	 */
	public String getRefuelQy() {
		return refuelQy;
	}
	/**
	 * @param refuelQy the refuelQy to set
	 */
	public void setRefuelQy(String refuelQy) {
		this.refuelQy = refuelQy;
	}
	/**
	 * @return the sFuelKnd1
	 */
	public String getsFuelKnd1() {
		return sFuelKnd1;
	}
	/**
	 * @param sFuelKnd1 the sFuelKnd1 to set
	 */
	public void setsFuelKnd1(String sFuelKnd1) {
		this.sFuelKnd1 = sFuelKnd1;
	}
	/**
	 * @return the sFuelKnd2
	 */
	public String getsFuelKnd2() {
		return sFuelKnd2;
	}
	/**
	 * @param sFuelKnd2 the sFuelKnd2 to set
	 */
	public void setsFuelKnd2(String sFuelKnd2) {
		this.sFuelKnd2 = sFuelKnd2;
	}
	/**
	 * @return the sFuelKnd3
	 */
	public String getsFuelKnd3() {
		return sFuelKnd3;
	}
	/**
	 * @param sFuelKnd3 the sFuelKnd3 to set
	 */
	public void setsFuelKnd3(String sFuelKnd3) {
		this.sFuelKnd3 = sFuelKnd3;
	}
	/**
	 * @return the sFuelKnd4
	 */
	public String getsFuelKnd4() {
		return sFuelKnd4;
	}
	/**
	 * @param sFuelKnd4 the sFuelKnd4 to set
	 */
	public void setsFuelKnd4(String sFuelKnd4) {
		this.sFuelKnd4 = sFuelKnd4;
	}
	/**
	 * @return the sFuelKnd5
	 */
	public String getsFuelKnd5() {
		return sFuelKnd5;
	}
	/**
	 * @param sFuelKnd5 the sFuelKnd5 to set
	 */
	public void setsFuelKnd5(String sFuelKnd5) {
		this.sFuelKnd5 = sFuelKnd5;
	}
	/**
	 * @return the sFuelKnd6
	 */
	public String getsFuelKnd6() {
		return sFuelKnd6;
	}
	/**
	 * @param sFuelKnd6 the sFuelKnd6 to set
	 */
	public void setsFuelKnd6(String sFuelKnd6) {
		this.sFuelKnd6 = sFuelKnd6;
	}
	/**
	 * @return the sCarRegistNo
	 */
	public String getsCarRegistNo() {
		return sCarRegistNo;
	}
	/**
	 * @param sCarRegistNo the sCarRegistNo to set
	 */
	public void setsCarRegistNo(String sCarRegistNo) {
		this.sCarRegistNo = sCarRegistNo;
	}
	/**
	 * @return the sCarNm
	 */
	public String getsCarNm() {
		return sCarNm;
	}
	/**
	 * @param sCarNm the sCarNm to set
	 */
	public void setsCarNm(String sCarNm) {
		this.sCarNm = sCarNm;
	}
	/**
	 * @return the sCarBodyNo
	 */
	public String getsCarBodyNo() {
		return sCarBodyNo;
	}
	/**
	 * @param sCarBodyNo the sCarBodyNo to set
	 */
	public void setsCarBodyNo(String sCarBodyNo) {
		this.sCarBodyNo = sCarBodyNo;
	}
	/**
	 * @return the carRegistNo
	 */
	public String getCarRegistNo() {
		return carRegistNo;
	}
	/**
	 * @param carRegistNo the carRegistNo to set
	 */
	public void setCarRegistNo(String carRegistNo) {
		this.carRegistNo = carRegistNo;
	}
	/**
	 * @return the carKnd
	 */
	public String getCarKnd() {
		return carKnd;
	}
	/**
	 * @param carKnd the carKnd to set
	 */
	public void setCarKnd(String carKnd) {
		this.carKnd = carKnd;
	}
	/**
	 * @return the carPrpos
	 */
	public String getCarPrpos() {
		return carPrpos;
	}
	/**
	 * @param carPrpos the carPrpos to set
	 */
	public void setCarPrpos(String carPrpos) {
		this.carPrpos = carPrpos;
	}
	/**
	 * @return the carNm
	 */
	public String getCarNm() {
		return carNm;
	}
	/**
	 * @param carNm the carNm to set
	 */
	public void setCarNm(String carNm) {
		this.carNm = carNm;
	}
	/**
	 * @return the carFmt
	 */
	public String getCarFmt() {
		return carFmt;
	}
	/**
	 * @param carFmt the carFmt to set
	 */
	public void setCarFmt(String carFmt) {
		this.carFmt = carFmt;
	}
	/**
	 * @return the carYrMdl
	 */
	public String getCarYrMdl() {
		return carYrMdl;
	}
	/**
	 * @param carYrMdl the carYrMdl to set
	 */
	public void setCarYrMdl(String carYrMdl) {
		this.carYrMdl = carYrMdl;
	}
	/**
	 * @return the carBodyNo
	 */
	public String getCarBodyNo() {
		return carBodyNo;
	}
	/**
	 * @param carBodyNo the carBodyNo to set
	 */
	public void setCarBodyNo(String carBodyNo) {
		this.carBodyNo = carBodyNo;
	}
	/**
	 * @return the turbineFmt
	 */
	public String getTurbineFmt() {
		return turbineFmt;
	}
	/**
	 * @param turbineFmt the turbineFmt to set
	 */
	public void setTurbineFmt(String turbineFmt) {
		this.turbineFmt = turbineFmt;
	}
	/**
	 * @return the usageStrhld
	 */
	public String getUsageStrhld() {
		return usageStrhld;
	}
	/**
	 * @param usageStrhld the usageStrhld to set
	 */
	public void setUsageStrhld(String usageStrhld) {
		this.usageStrhld = usageStrhld;
	}
	/**
	 * @return the ownerNm
	 */
	public String getOwnerNm() {
		return ownerNm;
	}
	/**
	 * @param ownerNm the ownerNm to set
	 */
	public void setOwnerNm(String ownerNm) {
		this.ownerNm = ownerNm;
	}
	/**
	 * @return the ownerAdres
	 */
	public String getOwnerAdres() {
		return ownerAdres;
	}
	/**
	 * @param ownerAdres the ownerAdres to set
	 */
	public void setOwnerAdres(String ownerAdres) {
		this.ownerAdres = ownerAdres;
	}
	/**
	 * @return the carRegistDt
	 */
	public String getCarRegistDt() {
		return carRegistDt;
	}
	/**
	 * @param carRegistDt the carRegistDt to set
	 */
	public void setCarRegistDt(String carRegistDt) {
		this.carRegistDt = carRegistDt;
	}
	/**
	 * @return the registGovOfc
	 */
	public String getRegistGovOfc() {
		return registGovOfc;
	}
	/**
	 * @param registGovOfc the registGovOfc to set
	 */
	public void setRegistGovOfc(String registGovOfc) {
		this.registGovOfc = registGovOfc;
	}
	/**
	 * @return the specMngNo
	 */
	public String getSpecMngNo() {
		return specMngNo;
	}
	/**
	 * @param specMngNo the specMngNo to set
	 */
	public void setSpecMngNo(String specMngNo) {
		this.specMngNo = specMngNo;
	}
	/**
	 * @return the carLt
	 */
	public String getCarLt() {
		return carLt;
	}
	/**
	 * @param carLt the carLt to set
	 */
	public void setCarLt(String carLt) {
		this.carLt = carLt;
	}
	/**
	 * @return the carWd
	 */
	public String getCarWd() {
		return carWd;
	}
	/**
	 * @param carWd the carWd to set
	 */
	public void setCarWd(String carWd) {
		this.carWd = carWd;
	}
	/**
	 * @return the carHt
	 */
	public String getCarHt() {
		return carHt;
	}
	/**
	 * @param carHt the carHt to set
	 */
	public void setCarHt(String carHt) {
		this.carHt = carHt;
	}
	/**
	 * @return the carGrWqnt
	 */
	public String getCarGrWqnt() {
		return carGrWqnt;
	}
	/**
	 * @param carGrWqnt the carGrWqnt to set
	 */
	public void setCarGrWqnt(String carGrWqnt) {
		this.carGrWqnt = carGrWqnt;
	}
	/**
	 * @return the exhaustqy
	 */
	public String getExhaustqy() {
		return exhaustqy;
	}
	/**
	 * @param exhaustqy the exhaustqy to set
	 */
	public void setExhaustqy(String exhaustqy) {
		this.exhaustqy = exhaustqy;
	}
	/**
	 * @return the rateOutput
	 */
	public String getRateOutput() {
		return rateOutput;
	}
	/**
	 * @param rateOutput the rateOutput to set
	 */
	public void setRateOutput(String rateOutput) {
		this.rateOutput = rateOutput;
	}
	/**
	 * @return the rideQuotaCapa
	 */
	public String getRideQuotaCapa() {
		return rideQuotaCapa;
	}
	/**
	 * @param rideQuotaCapa the rideQuotaCapa to set
	 */
	public void setRideQuotaCapa(String rideQuotaCapa) {
		this.rideQuotaCapa = rideQuotaCapa;
	}
	/**
	 * @return the maxCapaQy
	 */
	public String getMaxCapaQy() {
		return maxCapaQy;
	}
	/**
	 * @param maxCapaQy the maxCapaQy to set
	 */
	public void setMaxCapaQy(String maxCapaQy) {
		this.maxCapaQy = maxCapaQy;
	}
	/**
	 * @return the cylinderCnt
	 */
	public String getCylinderCnt() {
		return cylinderCnt;
	}
	/**
	 * @param cylinderCnt the cylinderCnt to set
	 */
	public void setCylinderCnt(String cylinderCnt) {
		this.cylinderCnt = cylinderCnt;
	}
	/**
	 * @return the fuelKnd
	 */
	public String getFuelKnd() {
		return fuelKnd;
	}
	/**
	 * @param fuelKnd the fuelKnd to set
	 */
	public void setFuelKnd(String fuelKnd) {
		this.fuelKnd = fuelKnd;
	}
	/**
	 * @return the fuelEfft
	 */
	public String getFuelEfft() {
		return fuelEfft;
	}
	/**
	 * @param fuelEfft the fuelEfft to set
	 */
	public void setFuelEfft(String fuelEfft) {
		this.fuelEfft = fuelEfft;
	}
	/**
	 * @return the examValidBeginDt
	 */
	public String getExamValidBeginDt() {
		return examValidBeginDt;
	}
	/**
	 * @param examValidBeginDt the examValidBeginDt to set
	 */
	public void setExamValidBeginDt(String examValidBeginDt) {
		this.examValidBeginDt = examValidBeginDt;
	}
	/**
	 * @return the examValidEndDt
	 */
	public String getExamValidEndDt() {
		return examValidEndDt;
	}
	/**
	 * @param examValidEndDt the examValidEndDt to set
	 */
	public void setExamValidEndDt(String examValidEndDt) {
		this.examValidEndDt = examValidEndDt;
	}
	/**
	 * @return the acqPrce
	 */
	public String getAcqPrce() {
		return acqPrce;
	}
	/**
	 * @param acqPrce the acqPrce to set
	 */
	public void setAcqPrce(String acqPrce) {
		this.acqPrce = acqPrce;
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

}

