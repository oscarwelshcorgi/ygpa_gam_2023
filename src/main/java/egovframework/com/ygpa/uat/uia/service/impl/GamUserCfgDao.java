/**
 *
 */
package egovframework.com.ygpa.uat.uia.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.ygpa.uat.uia.service.GamUserCfgVo;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetFeeExprInqireVO;

/**
 *
 * @author verstar
 * @since 2019. 6. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2019. 6. 10.		verstar		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamUserCfgDao")
public class GamUserCfgDao extends YGPAAbstractDAO {

	/**
	 * @param vo
	 * @return
	 */
	public GamUserCfgVo selectUserCfgPk(GamUserCfgVo vo) {
		return (GamUserCfgVo) selectByPk("gamUserCfgDao.selectUserCfgPk_S", vo);
	}

	/**
	 * @param vo
	 */
	public void insertUserCfgPk(GamUserCfgVo vo) {
		insert("gamUserCfgDao.insertUserCfg_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateUserCfgPk(GamUserCfgVo vo) {
		update("gamUserCfgDao.updateUserCfg_S", vo);
	}

}
