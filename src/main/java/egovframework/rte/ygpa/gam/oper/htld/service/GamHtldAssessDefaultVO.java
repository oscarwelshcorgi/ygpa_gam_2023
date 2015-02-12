/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htld.service;

/**
 *
 * @author EUNSUNGJ
 * @since 2015. 2. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamHtldAssessDefaultVO extends GamHtldAssessVO {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 구역
	 */
	private String rentArea;

	/**
	 * @return the rentArea
	 */
	public String getRentArea() {
		return rentArea;
	}

	/**
	 * @param rentArea the rentArea to set
	 */
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

}
