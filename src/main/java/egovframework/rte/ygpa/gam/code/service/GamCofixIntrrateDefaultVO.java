/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

/**
 *
 * @author EUNSUNGJ
 * @since 2015. 3. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 3.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamCofixIntrrateDefaultVO extends GamCofixIntrrateVO {

	/**
	 * 검색 시작 일자
	 */
	private String searchDtFrom;
	/**
	 * 검색 종료 일자
	 */
	private String searchDtTo;
	/**
	 * @return the searchDtFrom
	 */
	public String getSearchDtFrom() {
		return searchDtFrom;
	}
	/**
	 * @param searchDtFrom the searchDtFrom to set
	 */
	public void setSearchDtFrom(String searchDtFrom) {
		this.searchDtFrom = searchDtFrom;
	}
	/**
	 * @return the searchDtTo
	 */
	public String getSearchDtTo() {
		return searchDtTo;
	}
	/**
	 * @param searchDtTo the searchDtTo to set
	 */
	public void setSearchDtTo(String searchDtTo) {
		this.searchDtTo = searchDtTo;
	}


}
