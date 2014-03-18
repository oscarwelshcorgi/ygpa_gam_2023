/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamBupJungDongCodeVO extends GamBupJungDongCodeDefaultVO {
	private String bupjungdongCd;
	private String bupjungdongNm;
	private String existYn;

	public String getBupjungdongCd() {
		return bupjungdongCd;
	}
	public void setBupjungdongCd(String bupjungdongCd) {
		this.bupjungdongCd = bupjungdongCd;
	}
	public String getBupjungdongNm() {
		return bupjungdongNm;
	}
	public void setBupjungdongNm(String bupjungdongNm) {
		this.bupjungdongNm = bupjungdongNm;
	}
	public String getExistYn() {
		return existYn;
	}
	public void setExistYn(String existYn) {
		this.existYn = existYn;
	}
}
