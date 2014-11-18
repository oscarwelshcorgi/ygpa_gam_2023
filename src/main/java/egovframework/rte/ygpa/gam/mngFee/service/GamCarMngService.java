/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamCarMngService {

	List selectCarMngList(GamCarMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	int selectCarMngListTotCnt(GamCarMngVo searchVO) throws Exception;

	/**
	 * @param gamCarMngVo
	 */
	void insertCarMng(GamCarMngVo gamCarMngVo)throws Exception;

	/**
	 * @param gamCarMngVo
	 */
	void updateCarMng(GamCarMngVo gamCarMngVo) throws Exception;

	/**
	 * @param gamCarMngVo
	 */
	void deleteCarMng(GamCarMngVo gamCarMngVo) throws Exception;

}
