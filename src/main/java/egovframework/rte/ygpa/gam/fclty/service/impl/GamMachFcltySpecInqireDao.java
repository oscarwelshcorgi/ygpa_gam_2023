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
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngVO;

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

@Repository("gamMachFcltySpecInqireDao")
public class GamMachFcltySpecInqireDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectMachFcltySpecInqireList(GamMachFcltySpecInqireVO searchVO) {
		return list("gamMachFcltySpecInqireDao.selectMachFcltySpecInqireList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public GamMachFcltySpecInqireVO selectMachFcltySpecInqireListSum(GamMachFcltySpecInqireVO searchVO) {
		return (GamMachFcltySpecInqireVO) selectByPk("gamMachFcltySpecInqireDao.selectMachFcltySpecInqireListSum_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectMachFcltySpecInqireCvlEngStatusList(GamMachFcltySpecInqireVO searchVO) {
		return list("gamMachFcltySpecInqireDao.selectMachFcltySpecInqireCvlEngStatusList_D", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public List gamSelectMachFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamMachFcltySpecInqireDao.gamSelectMachFcltySpecInqireAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectMachFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamMachFcltySpecInqireDao.selectMachFcltySpecInqireFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public EgovMap selectMachFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap) selectByPk("gamMachFcltySpecInqireDao.selectMachFcltySpecInqireAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @param gamFcltsAtchFileMngVO
	 * @return
	 */
	public EgovMap selectMachFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap) selectByPk("gamMachFcltySpecInqireDao.selectMachFcltySpecInqireFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String) selectByPk("gamMachFcltySpecInqireDao.selectFcltsMngGroupNm", searchVO);
	}

	
	
}
