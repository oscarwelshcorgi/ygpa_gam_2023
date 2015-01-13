/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireVO;

/**
 *
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyCtrtSttusInqireDao")
public class GamFcltyCtrtSttusInqireDao extends YGPAAbstractDAO {

	/**
	 * @name selectFcltyCtrtSttusInqireList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtSttusInqireList(GamFcltyCtrtSttusInqireVO searchVO) {
		return list("gamFcltyCtrtSttusInqireDao.selectFcltyCtrtSttusInqireList_D", searchVO);
	}

	/**
	 * @name selectFcltyCtrtSttusInqireListSum
	 * @param searchVO
	 * @return GamFcltyCtrtSttusInqireVO
	 */
	public GamFcltyCtrtSttusInqireVO selectFcltyCtrtSttusInqireListSum(GamFcltyCtrtSttusInqireVO searchVO) {
		return (GamFcltyCtrtSttusInqireVO)selectByPk("gamFcltyCtrtSttusInqireDao.selectFcltyCtrtSttusInqireListSum_S", searchVO);
	}

	/**
	 * @name selectEntrpsInfo
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectEntrpsInfo(Map searchVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtSttusInqireDao.selectEntrpsInfo_S", searchVO);
	}

}
