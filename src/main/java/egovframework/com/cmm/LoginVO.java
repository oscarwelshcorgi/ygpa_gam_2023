package egovframework.com.cmm;

import java.io.Serializable;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.03.03    박지욱          최초 생성
 *
 *  @author 공통서비스 개발팀 박지욱
 *  @since 2009.03.03
 *  @version 1.0
 *  @see
 *
 */
public class LoginVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -8274004534207618049L;

	/** 아이디 */
	private String id;
	/** 이름 */
	private String name;
	/** 주민등록번호 */
	private String ihidNum;
	/** 이메일주소 */
	private String email;
	/** 비밀번호 */
	private String password;
	/** 비밀번호 힌트 */
	private String passwordHint;
	/** 비밀번호 정답 */
	private String passwordCnsr;
	/** 사용자구분 */
	private String userSe;
	/** 사원번호 */
	private String emplNo;
	/** 조직(부서)ID */
	private String orgnztId;
	/** 조직(부서)명 */
	private String orgnztNm;
	/** 부두 그룹 코드 */
	private String quayGroupCd;
	/** 고유아이디 */
	private String uniqId;
	/** 로그인 후 이동할 페이지 */
	private String url;
	/** 사용자 IP정보 */
	private String ip;
	/** GPKI인증 DN */
	private String dn;
	/** 관리 시설 코드 */
	private String mngFcltyCd;

	/**
	 * ERP 연동용 부서 코드 (YGPA)
	 */
	private String deptCd;
	/**
	 * id attribute 를 리턴한다.
	 * @return String
	 */
	public String getId() {
		return id;
	}
	/**
	 * id attribute 값을 설정한다.
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * name attribute 를 리턴한다.
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * name attribute 값을 설정한다.
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * ihidNum attribute 를 리턴한다.
	 * @return String
	 */
	public String getIhidNum() {
		return ihidNum;
	}
	/**
	 * ihidNum attribute 값을 설정한다.
	 * @param ihidNum String
	 */
	public void setIhidNum(String ihidNum) {
		this.ihidNum = ihidNum;
	}
	/**
	 * email attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * email attribute 값을 설정한다.
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * password attribute 를 리턴한다.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * password attribute 값을 설정한다.
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * passwordHint attribute 를 리턴한다.
	 * @return String
	 */
	public String getPasswordHint() {
		return passwordHint;
	}
	/**
	 * passwordHint attribute 값을 설정한다.
	 * @param passwordHint String
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	/**
	 * passwordCnsr attribute 를 리턴한다.
	 * @return String
	 */
	public String getPasswordCnsr() {
		return passwordCnsr;
	}
	/**
	 * passwordCnsr attribute 값을 설정한다.
	 * @param passwordCnsr String
	 */
	public void setPasswordCnsr(String passwordCnsr) {
		this.passwordCnsr = passwordCnsr;
	}
	/**
	 * userSe attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserSe() {
		return userSe;
	}
	/**
	 * userSe attribute 값을 설정한다.
	 * @param userSe String
	 */
	public void setUserSe(String userSe) {
		this.userSe = userSe;
	}
	/**
	 * orgnztId attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * orgnztId attribute 값을 설정한다.
	 * @param orgnztId String
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}
	/**
	 * uniqId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * uniqId attribute 값을 설정한다.
	 * @param uniqId String
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	/**
	 * url attribute 를 리턴한다.
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * url attribute 값을 설정한다.
	 * @param url String
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * ip attribute 를 리턴한다.
	 * @return String
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * ip attribute 값을 설정한다.
	 * @param ip String
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * dn attribute 를 리턴한다.
	 * @return String
	 */
	public String getDn() {
		return dn;
	}
	/**
	 * dn attribute 값을 설정한다.
	 * @param dn String
	 */
	public void setDn(String dn) {
		this.dn = dn;
	}
	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * @param orgnztNm the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	/**
	 * @return the quayGroupCd
	 */
	public String getQuayGroupCd() {
		return quayGroupCd;
	}
	/**
	 * @param quayGroupCd the quayGroupCd to set
	 */
	public void setQuayGroupCd(String quayGroupCd) {
		this.quayGroupCd = quayGroupCd;
	}
	public String getEmplNo() {
		return emplNo;
	}
	public void setEmplNo(String emplNo) {
		this.emplNo = emplNo;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	/**
	 * @return the mngFcltyCd
	 */
	public String getMngFcltyCd() {
		return mngFcltyCd;
	}
	/**
	 * @param mngFcltyCd the mngFcltyCd to set
	 */
	public void setMngFcltyCd(String mngFcltyCd) {
		this.mngFcltyCd = mngFcltyCd;
	}

}
