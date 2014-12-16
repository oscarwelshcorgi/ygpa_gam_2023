/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author Lee
 * @since 2014. 10. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 24.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamMngFeeGubunMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	int selectMngFeeGubunMngListTotCnt(GamMngFeeGubunMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectMngFeeGubunMngList(GamMngFeeGubunMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectMngFeeGubunMngPk(GamMngFeeGubunMngVo searchVO) throws Exception;

	/**
	 * @param gamMngFeeGubunMngVo
	 */
	void insertMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) throws Exception;

	/**
	 * @param gamMngFeeGubunMngVo
	 */
	void updateMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) throws Exception;

	/**
	 * @param gamMngFeeGubunMngVo
	 */
	void deleteMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo)throws Exception;

	/**
	 * @param checkSe
	 * @return
	 */
	int checkSeFeeGubunMng(String checkSe) throws Exception;

}
