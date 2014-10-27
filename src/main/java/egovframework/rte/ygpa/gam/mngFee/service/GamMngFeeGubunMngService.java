/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

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
	 * @return
	 */
	List selectMngFeeGubunMngList(GamMngFeeGubunMngVo searchVO) throws Exception;

	/**
	 * @param gamMngFeeGubunMngVo
	 */
	void InsertMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) throws Exception;

	/**
	 * @param gamMngFeeGubunMngVo
	 */
	void DeleteMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo);

}
