/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamCofixIntrrateVO {
	private String objYrmt;
	private String blceStdrIntrrate;
	private String newManipAmtStdrIntrrate;
	private String annodt;
	/**
	 * @return the objYrmt
	 */
	public String getObjYrmt() {
		return objYrmt;
	}
	/**
	 * @param objYrmt the objYrmt to set
	 */
	public void setObjYrmt(String objYrmt) {
		this.objYrmt = objYrmt;
	}
	/**
	 * @return the blceStdrIntrrate
	 */
	public String getBlceStdrIntrrate() {
		return blceStdrIntrrate;
	}
	/**
	 * @param blceStdrIntrrate the blceStdrIntrrate to set
	 */
	public void setBlceStdrIntrrate(String blceStdrIntrrate) {
		this.blceStdrIntrrate = blceStdrIntrrate;
	}
	/**
	 * @return the newManipAmtStdrIntrrate
	 */
	public String getNewManipAmtStdrIntrrate() {
		return newManipAmtStdrIntrrate;
	}
	/**
	 * @param newManipAmtStdrIntrrate the newManipAmtStdrIntrrate to set
	 */
	public void setNewManipAmtStdrIntrrate(String newManipAmtStdrIntrrate) {
		this.newManipAmtStdrIntrrate = newManipAmtStdrIntrrate;
	}
	/**
	 * @return the annodt
	 */
	public String getAnnodt() {
		return annodt;
	}
	/**
	 * @param annodt the annodt to set
	 */
	public void setAnnodt(String annodt) {
		this.annodt = annodt;
	}
}
