/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo;

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
@Repository("gamCarMngDao")
public class GamCarMngDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectCarMngList(GamCarMngVo searchVO) throws Exception{
		return list("gamCarMngDao.selectCarMngList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectCarMngListTotCnt(GamCarMngVo searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCarMngDao.selectCarMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectCarMngPk(GamCarMngVo searchVO) {
		return (EgovMap)selectByPk("gamCarMngDao.selectCarMngPk_S", searchVO);
	}

	/**
	 * @param GamCarMngVo
	 */
	public void insertCarMng(GamCarMngVo gamCarMngVo) {
		insert("gamCarMngDao.insertCarMng_S", gamCarMngVo);
	}

	/**
	 * @param GamCarMngVo
	 */
	public void updateCarMng(GamCarMngVo gamCarMngVo) {
		update("gamCarMngDao.updateCarMng_S", gamCarMngVo);
	}

	/**
	 * @param GamCarMngVo
	 */
	public void deleteCarMng(GamCarMngVo gamCarMngVo ) throws Exception {
		delete("gamCarMngDao.deleteCarMng_S", gamCarMngVo);
	}

}
