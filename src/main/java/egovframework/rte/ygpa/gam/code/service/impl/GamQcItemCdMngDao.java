/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamQcItemCdMngVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 22.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamQcItemCdMngDao")
public class GamQcItemCdMngDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectQcItemCdMngList(GamQcItemCdMngVo searchVO) throws Exception {
		return list("gamQcItemCdMngDao.selectQcItemCdMngList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public GamQcItemCdMngVo selectQcItemCdMngListTotCnt(GamQcItemCdMngVo searchVO) throws Exception {
		return (GamQcItemCdMngVo)selectByPk("gamQcItemCdMngDao.selectQcItemCdMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectQcItemCdMngPk(GamQcItemCdMngVo searchVO) throws Exception {
		return (EgovMap)selectByPk("gamQcItemCdMngDao.selectQcItemCdMngPk_S", searchVO);
	}

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	public String selectQcItemCdMngNewQcItemCd(GamQcItemCdMngVo gamQcItemCdMngVo) {
		return (String)selectByPk("gamQcItemCdMngDao.selectQcItemCdMngNewQcItemCd_S", gamQcItemCdMngVo);
	}

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	public List selectQcItemCdMngTreeList(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		return list("gamQcItemCdMngDao.selectQcItemCdMngTreeList_D", gamQcItemCdMngVo);
	}

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	public List selectQcItemCdMngLowerDataCnt(GamQcItemCdMngVo gamQcItemCdMngVo) {
		return list("gamQcItemCdMngDao.selectQcItemCdMngLowerDataCnt_S", gamQcItemCdMngVo);
	}

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	public void insertQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		insert("gamQcItemCdMngDao.insertQcItemCdMng_S", gamQcItemCdMngVo);
	}

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	public void updateQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		update("gamQcItemCdMngDao.updateQcItemCdMng_S", gamQcItemCdMngVo);
	}

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	public void deleteQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		delete("gamQcItemCdMngDao.deleteQcItemCdMng_S", gamQcItemCdMngVo);
	}

	/**
	 * @param gamQcItemCdMngVo
	 * @return
	 */
	public void deleteQcItemCdMngLowerData(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		delete("gamQcItemCdMngDao.deleteQcItemCdMngLowerData_S", gamQcItemCdMngVo);
	}

}
