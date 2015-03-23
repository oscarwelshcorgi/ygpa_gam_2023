/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySttusMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 21.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 21.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupMachFcltySttusService {

	/**
	 * @name selectMachFcltySttusPk
	 * @param gamMachFcltySttusMngVO
	 * @return EgovMap
	 * @exception Exception
	 */
	EgovMap selectMachFcltySttusPk(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception;

	/**
	 * @name insertMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception;

	/**
	 * @name updateMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception;

	/**
	 * @name deleteMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception;

}
