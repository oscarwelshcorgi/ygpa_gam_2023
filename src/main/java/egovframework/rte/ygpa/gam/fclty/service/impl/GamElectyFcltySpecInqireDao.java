/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 10.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamElectyFcltySpecInqireDao")
public class GamElectyFcltySpecInqireDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectElectyFcltySpecInqireList(GamElectyFcltySpecInqireVO searchVO) {
		return list("gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public GamElectyFcltySpecInqireVO selectElectyFcltySpecInqireListSum(GamElectyFcltySpecInqireVO searchVO) {
		return (GamElectyFcltySpecInqireVO) selectByPk("gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireListSum_S", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public List selectElectyFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectElectyFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public EgovMap selectElectyFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap) selectByPk("gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @param gamFcltsAtchFileMngVO
	 * @return
	 */
	public EgovMap selectElectyFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap) selectByPk("gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String) selectByPk("gamElectyFcltySpecInqireDao.selectFcltsMngGroupNm", searchVO);
	}

	
	
}
