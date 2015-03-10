/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 10.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamElectyFcltySpecInqireVO extends ComDefaultVO{

	private static final long serialVersionUID = 1L;
	
	/*조회 조건*/
	private String sFcltsMngNo;
	private String sPrtAtCode;
	private String sFcltsMngGroupNo;
	private String sPrtFcltyNm;
	private String sLoc;
	private String sFcltsClCd;
	private String sGisPrtFcltyCd;
	
	private	String totalCount;				// 조회 자료 수
	private	String sumQy;					// 수량 합계
	private	String sumOilQty;				// 유량 합계
	private	String sumFuelConsum;			// 연료 소비량 합계
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
	 * @return the sumQy
	 */
	public String getSumQy() {
		return sumQy;
	}
	/**
	 * @param sumQy the sumQy to set
	 */
	public void setSumQy(String sumQy) {
		this.sumQy = sumQy;
	}
	/**
	 * @return the sumOilQty
	 */
	public String getSumOilQty() {
		return sumOilQty;
	}
	/**
	 * @param sumOilQty the sumOilQty to set
	 */
	public void setSumOilQty(String sumOilQty) {
		this.sumOilQty = sumOilQty;
	}
	/**
	 * @return the sumFuelConsum
	 */
	public String getSumFuelConsum() {
		return sumFuelConsum;
	}
	/**
	 * @param sumFuelConsum the sumFuelConsum to set
	 */
	public void setSumFuelConsum(String sumFuelConsum) {
		this.sumFuelConsum = sumFuelConsum;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
