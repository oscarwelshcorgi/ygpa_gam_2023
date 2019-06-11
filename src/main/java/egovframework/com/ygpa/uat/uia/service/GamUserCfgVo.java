/**
 *
 */
package egovframework.com.ygpa.uat.uia.service;

import java.math.BigDecimal;

/**
 *
 * @author verstar
 * @since 2019. 6. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2019. 6. 10.		verstar		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamUserCfgVo {
	private String userId;
	private BigDecimal startLat;
	private BigDecimal startLon;
	private BigDecimal startZoom;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the startLat
	 */
	public BigDecimal getStartLat() {
		return startLat;
	}
	/**
	 * @param startLat the startLat to set
	 */
	public void setStartLat(BigDecimal startLat) {
		this.startLat = startLat;
	}
	/**
	 * @return the startLon
	 */
	public BigDecimal getStartLon() {
		return startLon;
	}
	/**
	 * @param startLon the startLon to set
	 */
	public void setStartLon(BigDecimal startLon) {
		this.startLon = startLon;
	}
	/**
	 * @return the startZoom
	 */
	public BigDecimal getStartZoom() {
		return startZoom;
	}
	/**
	 * @param startZoom the startZoom to set
	 */
	public void setStartZoom(BigDecimal startZoom) {
		this.startZoom = startZoom;
	}


}
