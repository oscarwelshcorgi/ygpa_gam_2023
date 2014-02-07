package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

public class GamFcltyDrwInfoFVO extends ComDefaultVO{
	private static final long serialVersionUID = 1L;
    /** 도면 목록 등록 년도 */
    private String drwLstRegistYear;

    /** 도면 목록 순번 */
    private String drwLstSeq;

    /** 도면 목록 명 */
    private String drwLstNm;

    /** 도면 목록 관리 부서 코드 */
    private String drwLstMngDeptCd;

    /** 등록자 */
    private String regUsr;

    /** 등록일시 */
    private String registDt;

    /** 도면 목록 구분 코드 */
    private String drwLstSeCd;

    /** 도면 목록 GIS 코드 */
    private Long drwLstGisCd;

    public String getDrwLstRegistYear() {
        return drwLstRegistYear;
    }

    public void setDrwLstRegistYear(String drwLstRegistYear) {
        this.drwLstRegistYear = drwLstRegistYear;
    }

    public String getDrwLstSeq() {
        return drwLstSeq;
    }

    public void setDrwLstSeq(String drwLstSeq) {
        this.drwLstSeq = drwLstSeq;
    }

    public String getDrwLstNm() {
        return drwLstNm;
    }

    public void setDrwLstNm(String drwLstNm) {
        this.drwLstNm = drwLstNm;
    }

    public String getDrwLstMngDeptCd() {
        return drwLstMngDeptCd;
    }

    public void setDrwLstMngDeptCd(String drwLstMngDeptCd) {
        this.drwLstMngDeptCd = drwLstMngDeptCd;
    }

    public String getRegUsr() {
        return regUsr;
    }

    public void setRegUsr(String regUsr) {
        this.regUsr = regUsr;
    }

    public String getRegistDt() {
        return registDt;
    }

    public void setRegistDt(String registDt) {
        this.registDt = registDt;
    }

    public String getDrwLstSeCd() {
        return drwLstSeCd;
    }

    public void setDrwLstSeCd(String drwLstSeCd) {
        this.drwLstSeCd = drwLstSeCd;
    }

    public Long getDrwLstGisCd() {
        return drwLstGisCd;
    }

    public void setDrwLstGisCd(Long drwLstGisCd) {
        this.drwLstGisCd = drwLstGisCd;
    }
}