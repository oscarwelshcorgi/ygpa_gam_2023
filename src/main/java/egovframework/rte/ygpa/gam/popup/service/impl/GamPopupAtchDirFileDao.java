/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupAtchDirFileVO;

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

@Repository("gamPopupAtchDirFileDao")
public class GamPopupAtchDirFileDao extends YGPAAbstractDAO {

	/**
	 * @name selectAtchDirList
	 * @param searchVO
	 * @return List
	 */
	public List selectAtchDirList(GamPopupAtchDirFileVO searchVO) {
		return list("gamPopupAtchDirFileDao.selectAtchDirList_D", searchVO);
	}

	/**
	 * @name selectAtchFileList
	 * @param searchVO
	 * @return List
	 */
	public List selectAtchFileList(GamPopupAtchDirFileVO searchVO) {
		return list("gamPopupAtchDirFileDao.selectAtchFileList_D", searchVO);
	}

}
