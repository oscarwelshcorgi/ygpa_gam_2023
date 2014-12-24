/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamFcltsMngGroupMngVo;

/**
 *
 * @author 김종민
 * @since 2014. 12. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltsMngGroupMngDao")
public class GamFcltsMngGroupMngDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFcltsMngGroupMngList(GamFcltsMngGroupMngVo searchVO) throws Exception {
		return list("gamFcltsMngGroupMngDao.selectFcltsMngGroupMngList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public GamFcltsMngGroupMngVo selectFcltsMngGroupMngListTotCnt(GamFcltsMngGroupMngVo searchVO) throws Exception {
		return (GamFcltsMngGroupMngVo)selectByPk("gamFcltsMngGroupMngDao.selectFcltsMngGroupMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectFcltsMngGroupMngPk(GamFcltsMngGroupMngVo searchVO) throws Exception {
		return (EgovMap)selectByPk("gamFcltsMngGroupMngDao.selectFcltsMngGroupMngPk_S", searchVO);
	}

	/**
	 * @param gamFcltsMngGroupMngVo
	 * @return
	 */
	public String selectFcltsMngGroupMngMaxGroupNo(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltsMngGroupMngDao.selectFcltsMngGroupMngMaxGroupNo_S", gamFcltsMngGroupMngVo);
	}

	/**
	 * @param gamFcltsMngGroupMngVo
	 * @return
	 */
	public void insertFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception {
		insert("gamFcltsMngGroupMngDao.insertFcltsMngGroupMng_S", gamFcltsMngGroupMngVo);
	}

	/**
	 * @param gamFcltsMngGroupMngVo
	 * @return
	 */
	public void updateFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception {
		update("gamFcltsMngGroupMngDao.updateFcltsMngGroupMng_S", gamFcltsMngGroupMngVo);
	}

	/**
	 * @param gamFcltsMngGroupMngVo
	 * @return
	 */
	public void deleteFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception {
		delete("gamFcltsMngGroupMngDao.deleteFcltsMngGroupMng_S", gamFcltsMngGroupMngVo);
	}

}
