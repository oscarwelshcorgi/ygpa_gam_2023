package egovframework.rte.ygpa.gam.code.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamBuldMktcStdAmVO extends ComDefaultVO{
	
	//조회조건
	private String sSearch;
	private String sAdres;
	private String sLnm;
	private String sSlno;
	
	
	//기준년
	private String stdyy;
	//순번
	private String sn;
	//건물형태
	private String buldStle;
	//건물용도
	private String buldPrpos;
	//건물구조
	private String buldRescue;
	//건물위치
	private String buldLc;
	//건물지붕
	private String buldRf;
	//행정동코드
	private String adstrdCode;
	//주소
	private String adres;
	//지번
	private String lnm;
	//부번
	private String slno;
	//건물동
	private String bulddong;
	//건물호
	private String bdh;
	//물건지 주소
	private String thingPaprAdres;
	//연면적
	private String totar;
	//전용면적
	private String prvuseAr;
	//공용면적
	private String cmnuseAr;
	//준공일정
	private String competDe;
	//공부상 지목
	private String studyUpptLndcgr;
	//토지 면적
	private String ladAr;
	//시가표준액
	private String mktcStdAm;
	//관리가관명
	private String mgcNm;
	//데이터기준일자
	private String dataStdrDe;
	/**
	 * @return the stdyy
	 */
	public String getStdyy() {
		return stdyy;
	}
	/**
	 * @param stdyy the stdyy to set
	 */
	public void setStdyy(String stdyy) {
		this.stdyy = stdyy;
	}
	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * @return the buldStle
	 */
	public String getBuldStle() {
		return buldStle;
	}
	/**
	 * @param buldStle the buldStle to set
	 */
	public void setBuldStle(String buldStle) {
		this.buldStle = buldStle;
	}
	/**
	 * @return the buldPrpos
	 */
	public String getBuldPrpos() {
		return buldPrpos;
	}
	/**
	 * @param buldPrpos the buldPrpos to set
	 */
	public void setBuldPrpos(String buldPrpos) {
		this.buldPrpos = buldPrpos;
	}
	/**
	 * @return the buldRescue
	 */
	public String getBuldRescue() {
		return buldRescue;
	}
	/**
	 * @param buldRescue the buldRescue to set
	 */
	public void setBuldRescue(String buldRescue) {
		this.buldRescue = buldRescue;
	}
	/**
	 * @return the buldLc
	 */
	public String getBuldLc() {
		return buldLc;
	}
	/**
	 * @param buldLc the buldLc to set
	 */
	public void setBuldLc(String buldLc) {
		this.buldLc = buldLc;
	}
	/**
	 * @return the buldRf
	 */
	public String getBuldRf() {
		return buldRf;
	}
	/**
	 * @param buldRf the buldRf to set
	 */
	public void setBuldRf(String buldRf) {
		this.buldRf = buldRf;
	}
	/**
	 * @return the adstrdCode
	 */
	public String getAdstrdCode() {
		return adstrdCode;
	}
	/**
	 * @param adstrdCode the adstrdCode to set
	 */
	public void setAdstrdCode(String adstrdCode) {
		this.adstrdCode = adstrdCode;
	}
	/**
	 * @return the adres
	 */
	public String getAdres() {
		return adres;
	}
	/**
	 * @param adres the adres to set
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}
	/**
	 * @return the lnm
	 */
	public String getLnm() {
		return lnm;
	}
	/**
	 * @param lnm the lnm to set
	 */
	public void setLnm(String lnm) {
		this.lnm = lnm;
	}
	/**
	 * @return the slno
	 */
	public String getSlno() {
		return slno;
	}
	/**
	 * @param slno the slno to set
	 */
	public void setSlno(String slno) {
		this.slno = slno;
	}
	/**
	 * @return the bulddong
	 */
	public String getBulddong() {
		return bulddong;
	}
	/**
	 * @param buldDong the buldDong to set
	 */
	public void setBulddong(String bulddong) {
		this.bulddong = bulddong;
	}
	/**
	 * @return the bdh
	 */
	public String getBdh() {
		return bdh;
	}
	/**
	 * @param bdh the bdh to set
	 */
	public void setBdh(String bdh) {
		this.bdh = bdh;
	}
	/**
	 * @return the thingPaprAdres
	 */
	public String getThingPaprAdres() {
		return thingPaprAdres;
	}
	/**
	 * @param thingPaprAdres the thingPaprAdres to set
	 */
	public void setThingPaprAdres(String thingPaprAdres) {
		this.thingPaprAdres = thingPaprAdres;
	}
	/**
	 * @return the totar
	 */
	public String getTotar() {
		return totar;
	}
	/**
	 * @param totar the totar to set
	 */
	public void setTotar(String totar) {
		this.totar = totar;
	}
	/**
	 * @return the prvuseAr
	 */
	public String getPrvuseAr() {
		return prvuseAr;
	}
	/**
	 * @param prvuseAr the prvuseAr to set
	 */
	public void setPrvuseAr(String prvuseAr) {
		this.prvuseAr = prvuseAr;
	}
	/**
	 * @return the cmnuseAr
	 */
	public String getCmnuseAr() {
		return cmnuseAr;
	}
	/**
	 * @param cmnuseAr the cmnuseAr to set
	 */
	public void setCmnuseAr(String cmnuseAr) {
		this.cmnuseAr = cmnuseAr;
	}
	/**
	 * @return the competDe
	 */
	public String getCompetDe() {
		return competDe;
	}
	/**
	 * @param competDe the competDe to set
	 */
	public void setCompetDe(String competDe) {
		this.competDe = competDe;
	}
	/**
	 * @return the studyUpptLndcgr
	 */
	public String getStudyUpptLndcgr() {
		return studyUpptLndcgr;
	}
	/**
	 * @param studyUpptLndcgr the studyUpptLndcgr to set
	 */
	public void setStudyUpptLndcgr(String studyUpptLndcgr) {
		this.studyUpptLndcgr = studyUpptLndcgr;
	}
	/**
	 * @return the ladAr
	 */
	public String getLadAr() {
		return ladAr;
	}
	/**
	 * @param ladAr the ladAr to set
	 */
	public void setLadAr(String ladAr) {
		this.ladAr = ladAr;
	}
	/**
	 * @return the mktcStdAm
	 */
	public String getMktcStdAm() {
		return mktcStdAm;
	}
	/**
	 * @param mktcStdAm the mktcStdAm to set
	 */
	public void setMktcStdAm(String mktcStdAm) {
		this.mktcStdAm = mktcStdAm;
	}
	/**
	 * @return the mgcNm
	 */
	public String getMgcNm() {
		return mgcNm;
	}
	/**
	 * @param mgcNm the mgcNm to set
	 */
	public void setMgcNm(String mgcNm) {
		this.mgcNm = mgcNm;
	}
	/**
	 * @return the dataStdrDe
	 */
	public String getDataStdrDe() {
		return dataStdrDe;
	}
	/**
	 * @param dataStdrDe the dataStdrDe to set
	 */
	public void setDataStdrDe(String dataStdrDe) {
		this.dataStdrDe = dataStdrDe;
	}
	
	
	
	
	
	/**
	 * @return the sSearch
	 */
	public String getsSearch() {
		return sSearch;
	}
	/**
	 * @param sSearch the sSearch to set
	 */
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	/**
	 * @return the sAdres
	 */
	public String getsAdres() {
		return sAdres;
	}
	/**
	 * @param sAdres the sAdres to set
	 */
	public void setsAdres(String sAdres) {
		this.sAdres = sAdres;
	}
	/**
	 * @return the sLnm
	 */
	public String getsLnm() {
		return sLnm;
	}
	/**
	 * @param sLnm the sLnm to set
	 */
	public void setsLnm(String sLnm) {
		this.sLnm = sLnm;
	}
	/**
	 * @return the sSlno
	 */
	public String getsSlno() {
		return sSlno;
	}
	/**
	 * @param sSlno the sSlno to set
	 */
	public void setsSlno(String sSlno) {
		this.sSlno = sSlno;
	}
}