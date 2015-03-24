/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 24.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupAtchDirFileService {

	/**
	 * @name selectAtchDirList
	 * @param searchVO
	 * @return List
	 */
	List selectAtchDirList(GamPopupAtchDirFileVO searchVO) throws Exception;

	/**
	 * @name selectAtchFileList
	 * @param searchVO
	 * @return List
	 */
	List selectAtchFileList(GamPopupAtchDirFileVO searchVO) throws Exception;

}
