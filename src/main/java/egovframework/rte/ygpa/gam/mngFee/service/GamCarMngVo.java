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
public class GamCarMngVo extends ComDefaultVO{

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
	private String sFuelKnd;	//검색 차량 종류

	private	String	oldCarRegistNo;	// 예전 차량 등록 번호(update 용)


	/**
	 * @return the oldCarRegistNo
	 */
	public String getOldCarRegistNo() {
		return oldCarRegistNo;
	}
	/**
	 * @param oldCarRegistNo the oldCarRegistNo to set
	 */
	public void setOldCarRegistNo(String oldCarRegistNo) {
		this.oldCarRegistNo = oldCarRegistNo;
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
	 * @return the sFuelKnd
	 */
	public String getsFuelKnd() {
		return sFuelKnd;
	}
	/**
	 * @param sCarBodyNo the sCarBodyNo to set
	 */
	public void setsFuelKnd(String sFuelKnd) {
		this.sFuelKnd = sFuelKnd;
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

