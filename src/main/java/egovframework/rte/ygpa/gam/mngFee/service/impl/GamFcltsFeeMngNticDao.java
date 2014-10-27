/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticVo;

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
@Repository("gamFcltsFeeMngNticDao")
public class GamFcltsFeeMngNticDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectFcltsFeeMngNticListTotCnt(GamFcltsFeeMngNticVo searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltsFeeMngNticDao.selectFcltsFeeMngNticListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFcltsFeeMngNticList(GamFcltsFeeMngNticVo searchVO) {
		return list("gamFcltsFeeMngNticDao.selectFcltsFeeMngNticList_D", searchVO);
	}

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	public void InsertFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		insert("gamFcltsFeeMngNticDao.InsertFcltsFeeMngNtic_D", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	public void DeleteFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		delete("gamFcltsFeeMngNticDao.DeleteFcltsFeeMngNtic", gamFcltsFeeMngNticVo);
	}


}
