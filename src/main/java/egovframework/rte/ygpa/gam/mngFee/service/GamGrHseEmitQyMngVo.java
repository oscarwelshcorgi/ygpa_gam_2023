/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.com.cmm.ComDefaultVO;

/**
 *
 * @author Lee
 * @since 2014. 10. 27.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 27.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamGrHseEmitQyMngVo  extends ComDefaultVO{

		private	String	regUsr;				//등록자
		private	String	registDt;			//등록일시
		private	String	updUsr;				//수정자
		private	String	updtDt;				//수정일시
		/**
		 * @return the regUsr
		 */
		public String getRegUsr() {
			return regUsr;
		}
		/**
		 * @param regUsr the regUsr to set
		 */
		public void setRegUsr(String regUsr) {
			this.regUsr = regUsr;
		}
		/**
		 * @return the registDt
		 */
		public String getRegistDt() {
			return registDt;
		}
		/**
		 * @param registDt the registDt to set
		 */
		public void setRegistDt(String registDt) {
			this.registDt = registDt;
		}
		/**
		 * @return the updUsr
		 */
		public String getUpdUsr() {
			return updUsr;
		}
		/**
		 * @param updUsr the updUsr to set
		 */
		public void setUpdUsr(String updUsr) {
			this.updUsr = updUsr;
		}
		/**
		 * @return the updtDt
		 */
		public String getUpdtDt() {
			return updtDt;
		}
		/**
		 * @param updtDt the updtDt to set
		 */
		public void setUpdtDt(String updtDt) {
			this.updtDt = updtDt;
		}


}
