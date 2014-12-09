/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author Lee
 * @since 2014. 10. 27.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 27.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamFcltsMngFeeMngVo extends ComDefaultVO{

	private	String	mainMngMt;				//관리 월
	private	String	mainMngMtYear;			//관리 년
	private	String	mainMngMtMon;			//관리 월
	private	String	mainMngYrMt;			//관리 년-월
	private	String	mainMngFeeJobSe;		//관리비 업무 구분
	private	String	mainMngFeeSj;			//관리비 제목
	private	String	mainFcltyMngFee;		//시설 관리 용역비
	private	String	mainElctyFee;			//전기 요금
	private	String	mainWaterFee;			//상하수도 요금
	private	String	mainGasFee;				//도시가스 요금
	private	String	mainEnvFee;				//환경개선 부담금
	private	String	mainMngTotalFee;		//관리비 합계
	private	String	mainRegUsr;				//등록자
	private	String	mainRegistDt;			//등록일시
	private	String	mainUpdUsr;				//수정자
	private	String	mainUpdtDt;				//수정일시
	private String	mainNhtIsueYn;			//고지 여부

	private String	dataCount;				//자료수
	private	String	sumFcltyMngFee;			//총시설 관리 용역비
	private	String	sumElctyFee;			//총전기 요금
	private	String	sumWaterFee;			//총상하수도 요금
	private	String	sumGasFee;				//총도시가스 요금
	private	String	sumEnvFee;				//총환경개선 부담금
	private	String	sumMngTotalFee;			//총관리비 합계

	private	String	sStartMngYear;			//검색 시작 관리 년도
	private	String	sStartMngMt;			//검색 시작 관리 월
	private	String	sEndMngYear;			//검색 종료 관리 년도
	private	String	sEndMngMt;				//검색 종료 관리 월
	private	String	sMngFeeJobSe;			//검색 관리비 업무 구분

	/**
	 * @return the mainMngMt
	 */
	public String getMainMngMt() {
		return mainMngMt;
	}
	/**
	 * @param mainMngMt the mainMngMt to set
	 */
	public void setMainMngMt(String mainMngMt) {
		this.mainMngMt = mainMngMt;
	}
	/**
	 * @return the mainMngMtYear
	 */
	public String getMainMngMtYear() {
		return mainMngMtYear;
	}
	/**
	 * @param mainMngMtYear the mainMngMtYear to set
	 */
	public void setMainMngMtYear(String mainMngMtYear) {
		this.mainMngMtYear = mainMngMtYear;
	}
	/**
	 * @return the mainMngMtMon
	 */
	public String getMainMngMtMon() {
		return mainMngMtMon;
	}
	/**
	 * @param mainMngMtMon the mainMngMtMon to set
	 */
	public void setMainMngMtMon(String mainMngMtMon) {
		this.mainMngMtMon = mainMngMtMon;
	}
	/**
	 * @return the mainMngYrMt
	 */
	public String getMainMngYrMt() {
		return mainMngYrMt;
	}
	/**
	 * @param mainMngYrMt the mainMngYrMt to set
	 */
	public void setMainMngYrMt(String mainMngYrMt) {
		this.mainMngYrMt = mainMngYrMt;
	}
	/**
	 * @return the mainMngFeeJobSe
	 */
	public String getMainMngFeeJobSe() {
		return mainMngFeeJobSe;
	}
	/**
	 * @param mainMngFeeJobSe the mainMngFeeJobSe to set
	 */
	public void setMainMngFeeJobSe(String mainMngFeeJobSe) {
		this.mainMngFeeJobSe = mainMngFeeJobSe;
	}
	/**
	 * @return the mainMngFeeSj
	 */
	public String getMainMngFeeSj() {
		return mainMngFeeSj;
	}
	/**
	 * @param mainMngFeeSj the mainMngFeeSj to set
	 */
	public void setMainMngFeeSj(String mainMngFeeSj) {
		this.mainMngFeeSj = mainMngFeeSj;
	}
	/**
	 * @return the mainFcltyMngFee
	 */
	public String getMainFcltyMngFee() {
		return mainFcltyMngFee;
	}
	/**
	 * @param mainFcltyMngFee the mainFcltyMngFee to set
	 */
	public void setMainFcltyMngFee(String mainFcltyMngFee) {
		this.mainFcltyMngFee = mainFcltyMngFee;
	}
	/**
	 * @return the mainElctyFee
	 */
	public String getMainElctyFee() {
		return mainElctyFee;
	}
	/**
	 * @param mainElctyFee the mainElctyFee to set
	 */
	public void setMainElctyFee(String mainElctyFee) {
		this.mainElctyFee = mainElctyFee;
	}
	/**
	 * @return the mainWaterFee
	 */
	public String getMainWaterFee() {
		return mainWaterFee;
	}
	/**
	 * @param mainWaterFee the mainWaterFee to set
	 */
	public void setMainWaterFee(String mainWaterFee) {
		this.mainWaterFee = mainWaterFee;
	}
	/**
	 * @return the mainGasFee
	 */
	public String getMainGasFee() {
		return mainGasFee;
	}
	/**
	 * @param mainGasFee the mainGasFee to set
	 */
	public void setMainGasFee(String mainGasFee) {
		this.mainGasFee = mainGasFee;
	}
	/**
	 * @return the mainEnvFee
	 */
	public String getMainEnvFee() {
		return mainEnvFee;
	}
	/**
	 * @param mainEnvFee the mainEnvFee to set
	 */
	public void setMainEnvFee(String mainEnvFee) {
		this.mainEnvFee = mainEnvFee;
	}
	/**
	 * @return the mainMngTotalFee
	 */
	public String getMainMngTotalFee() {
		return mainMngTotalFee;
	}
	/**
	 * @param mainMngTotalFee the mainMngTotalFee to set
	 */
	public void setMainMngTotalFee(String mainMngTotalFee) {
		this.mainMngTotalFee = mainMngTotalFee;
	}
	/**
	 * @return the mainRegUsr
	 */
	public String getMainRegUsr() {
		return mainRegUsr;
	}
	/**
	 * @param mainRegUsr the mainRegUsr to set
	 */
	public void setMainRegUsr(String mainRegUsr) {
		this.mainRegUsr = mainRegUsr;
	}
	/**
	 * @return the mainRegistDt
	 */
	public String getMainRegistDt() {
		return mainRegistDt;
	}
	/**
	 * @param mainRegistDt the mainRegistDt to set
	 */
	public void setMainRegistDt(String mainRegistDt) {
		this.mainRegistDt = mainRegistDt;
	}
	/**
	 * @return the mainUpdUsr
	 */
	public String getMainUpdUsr() {
		return mainUpdUsr;
	}
	/**
	 * @param mainUpdUsr the mainUpdUsr to set
	 */
	public void setMainUpdUsr(String mainUpdUsr) {
		this.mainUpdUsr = mainUpdUsr;
	}
	/**
	 * @return the mainUpdtDt
	 */
	public String getMainUpdtDt() {
		return mainUpdtDt;
	}
	/**
	 * @param mainUpdtDt the mainUpdtDt to set
	 */
	public void setMainUpdtDt(String mainUpdtDt) {
		this.mainUpdtDt = mainUpdtDt;
	}
	/**
	 * @return the mainNhtIsueYn
	 */
	public String getMainNhtIsueYn() {
		return mainNhtIsueYn;
	}
	/**
	 * @param mainNhtIsueYn the mainNhtIsueYn to set
	 */
	public void setMainNhtIsueYn(String mainNhtIsueYn) {
		this.mainNhtIsueYn = mainNhtIsueYn;
	}
	/**
	 * @return the dataCount
	 */
	public String getDataCount() {
		return dataCount;
	}
	/**
	 * @param dataCount the dataCount to set
	 */
	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}
	/**
	 * @return the sumFcltyMngFee
	 */
	public String getSumFcltyMngFee() {
		return sumFcltyMngFee;
	}
	/**
	 * @param sumFcltyMngFee the sumFcltyMngFee to set
	 */
	public void setSumFcltyMngFee(String sumFcltyMngFee) {
		this.sumFcltyMngFee = sumFcltyMngFee;
	}
	/**
	 * @return the sumElctyFee
	 */
	public String getSumElctyFee() {
		return sumElctyFee;
	}
	/**
	 * @param sumElctyFee the sumElctyFee to set
	 */
	public void setSumElctyFee(String sumElctyFee) {
		this.sumElctyFee = sumElctyFee;
	}
	/**
	 * @return the sumWaterFee
	 */
	public String getSumWaterFee() {
		return sumWaterFee;
	}
	/**
	 * @param sumWaterFee the sumWaterFee to set
	 */
	public void setSumWaterFee(String sumWaterFee) {
		this.sumWaterFee = sumWaterFee;
	}
	/**
	 * @return the sumGasFee
	 */
	public String getSumGasFee() {
		return sumGasFee;
	}
	/**
	 * @param sumGasFee the sumGasFee to set
	 */
	public void setSumGasFee(String sumGasFee) {
		this.sumGasFee = sumGasFee;
	}
	/**
	 * @return the sumEnvFee
	 */
	public String getSumEnvFee() {
		return sumEnvFee;
	}
	/**
	 * @param sumEnvFee the sumEnvFee to set
	 */
	public void setSumEnvFee(String sumEnvFee) {
		this.sumEnvFee = sumEnvFee;
	}
	/**
	 * @return the sumMngTotalFee
	 */
	public String getSumMngTotalFee() {
		return sumMngTotalFee;
	}
	/**
	 * @param sumMngTotalFee the sumMngTotalFee to set
	 */
	public void setSumMngTotalFee(String sumMngTotalFee) {
		this.sumMngTotalFee = sumMngTotalFee;
	}
	/**
	 * @return the sStartMngYear
	 */
	public String getsStartMngYear() {
		return sStartMngYear;
	}
	/**
	 * @param sStartMngYear the sStartMngYear to set
	 */
	public void setsStartMngYear(String sStartMngYear) {
		this.sStartMngYear = sStartMngYear;
	}
	/**
	 * @return the sStartMngMt
	 */
	public String getsStartMngMt() {
		return sStartMngMt;
	}
	/**
	 * @param sStartMngMt the sStartMngMt to set
	 */
	public void setsStartMngMt(String sStartMngMt) {
		this.sStartMngMt = sStartMngMt;
	}
	/**
	 * @return the sEndMngYear
	 */
	public String getsEndMngYear() {
		return sEndMngYear;
	}
	/**
	 * @param sEndMngYear the sEndMngYear to set
	 */
	public void setsEndMngYear(String sEndMngYear) {
		this.sEndMngYear = sEndMngYear;
	}
	/**
	 * @return the sEndMngMt
	 */
	public String getsEndMngMt() {
		return sEndMngMt;
	}
	/**
	 * @param sEndMngMt the sEndMngMt to set
	 */
	public void setsEndMngMt(String sEndMngMt) {
		this.sEndMngMt = sEndMngMt;
	}
	/**
	 * @return the sMngFeeJobSe
	 */
	public String getsMngFeeJobSe() {
		return sMngFeeJobSe;
	}
	/**
	 * @param sMngFeeJobSe the sMngFeeJobSe to set
	 */
	public void setsMngFeeJobSe(String sMngFeeJobSe) {
		this.sMngFeeJobSe = sMngFeeJobSe;
	}

}
