/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamGrHseEmitQyMngVo;

/**
 *
 * @author Lee
 * @since 2014. 10. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 24.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamGrHseEmitQyMngDao")
public class GamGrHseEmitQyMngDao extends YGPAAbstractDAO{

	/**
	 * @param gamGrHseEmitQyMngVo
	 * @return
	 */
	public int selectGrHseEmitQyMngListTotCnt(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamGrHseEmitQyMngDao.selectGrHseEmitQyMngListTotCnt_S", gamGrHseEmitQyMngVo);
	}

	/**
	 * @param gamGrHseEmitQyMngVo
	 * @return
	 */
	public List selectGrHseEmitQyMngList(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) {
		return list("gamGrHseEmitQyMngDao.selectGrHseEmitQyMngList_D", gamGrHseEmitQyMngVo);
	}

	/**
	 * @param gamGrHseEmitQyMngVo
	 */
	public void InsertGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) {
		insert("gamGrHseEmitQyMngDao.InsertGrHseEmitQyMng_S",gamGrHseEmitQyMngVo);
	}

	/**
	 * @param gamGrHseEmitQyMngVo
	 */
	public void DeleteGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) {
		delete("gamGrHseEmitQyMngDao.DeleteGrHseEmitQyMng_S",gamGrHseEmitQyMngVo);
	}



}
