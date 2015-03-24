/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupAtchDirFileService;
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

@Service("gamPopupAtchDirFileService")
public class GamPopupAtchDirFileServiceImpl extends AbstractServiceImpl implements GamPopupAtchDirFileService {

	@Resource(name="gamPopupAtchDirFileDao")
	private GamPopupAtchDirFileDao gamPopupAtchDirFileDao;

	/**
	 * @name selectAtchDirList
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public List selectAtchDirList(GamPopupAtchDirFileVO searchVO) throws Exception {
		return gamPopupAtchDirFileDao.selectAtchDirList(searchVO);
	}

	/**
	 * @name selectAtchFileList
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public List selectAtchFileList(GamPopupAtchDirFileVO searchVO) throws Exception {
		return gamPopupAtchDirFileDao.selectAtchFileList(searchVO);
	}

}
