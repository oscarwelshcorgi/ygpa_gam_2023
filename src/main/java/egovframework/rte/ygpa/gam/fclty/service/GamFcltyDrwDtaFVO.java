package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

public class GamFcltyDrwDtaFVO extends ComDefaultVO{
	private static final long serialVersionUID = 1L;
    /** 도면 자료 코드 */
    private String drwDtaCd;

    /** 도면 목록 등록 년도 */
    private String drwLstRegistYear;

    /** 도면 목록 순번 */
    private String drwLstSeq;

    /** 도면 명 */
    private String drwNm;

    /** 도면 파일명 물리 */
    private String drwFilenmPhysicl;

    /** 도면 파일명 논리 */
    private String drwFilenmLogic;

    /** 도면 구분 코드 */
    private String drwSeCd;

    /** 도면 번호 */
    private String drwNo;

    /** 도면 작성 일자 */
    private String drwWritngDt;

    /** 등록일자 */
    private String registdt;

    /** 등록자 */
    private String regUsr;

    /** 도면 변경일 */
    private String drwChangedt;

    /** 도면 변경 내역 */
    private String drwChangeDtls;

    /** 도면 GIS 코드 */
    private Long drwGisCd;

    public String getDrwDtaCd() {
        return drwDtaCd;
    }

    public void setDrwDtaCd(String drwDtaCd) {
        this.drwDtaCd = drwDtaCd;
    }

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

    public String getDrwNm() {
        return drwNm;
    }

    public void setDrwNm(String drwNm) {
        this.drwNm = drwNm;
    }

    public String getDrwFilenmPhysicl() {
        return drwFilenmPhysicl;
    }

    public void setDrwFilenmPhysicl(String drwFilenmPhysicl) {
        this.drwFilenmPhysicl = drwFilenmPhysicl;
    }

    public String getDrwFilenmLogic() {
        return drwFilenmLogic;
    }

    public void setDrwFilenmLogic(String drwFilenmLogic) {
        this.drwFilenmLogic = drwFilenmLogic;
    }

    public String getDrwSeCd() {
        return drwSeCd;
    }

    public void setDrwSeCd(String drwSeCd) {
        this.drwSeCd = drwSeCd;
    }

    public String getDrwNo() {
        return drwNo;
    }

    public void setDrwNo(String drwNo) {
        this.drwNo = drwNo;
    }

    public String getDrwWritngDt() {
        return drwWritngDt;
    }

    public void setDrwWritngDt(String drwWritngDt) {
        this.drwWritngDt = drwWritngDt;
    }

    public String getRegistdt() {
        return registdt;
    }

    public void setRegistdt(String registdt) {
        this.registdt = registdt;
    }

    public String getRegUsr() {
        return regUsr;
    }

    public void setRegUsr(String regUsr) {
        this.regUsr = regUsr;
    }

    public String getDrwChangedt() {
        return drwChangedt;
    }

    public void setDrwChangedt(String drwChangedt) {
        this.drwChangedt = drwChangedt;
    }

    public String getDrwChangeDtls() {
        return drwChangeDtls;
    }

    public void setDrwChangeDtls(String drwChangeDtls) {
        this.drwChangeDtls = drwChangeDtls;
    }

    public Long getDrwGisCd() {
        return drwGisCd;
    }

    public void setDrwGisCd(Long drwGisCd) {
        this.drwGisCd = drwGisCd;
    }
}