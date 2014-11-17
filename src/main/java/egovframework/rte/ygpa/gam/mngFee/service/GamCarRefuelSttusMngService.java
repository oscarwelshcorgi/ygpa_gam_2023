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

public interface GamCarRefuelSttusMngService {

	/**
	 * @param gamCarRefuelSttusMngVo
	 * @return
	 */
	int selectCarRefuelSttusMngListTotCnt(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception;

	/**
	 * @param gamCarRefuelSttusMngVo
	 * @return
	 */
	List selectCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception;

	/**
	 * @param gamCarRefuelSttusMngVo
	 */
	void insertCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception;

	/**
	 * @param gamCarRefuelSttusMngVo
	 */
	void updateCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception;

	/**
	 * @param gamCarRefuelSttusMngVo
	 * @throws Exception
	 */
	void deleteCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception;

}
