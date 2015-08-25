package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyPopupInqireVO;

/**
 * @Class Name : GamPrtFcltyPopupInqireDao.java
 * @Description : 항만시설 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 * #########################
 *  신규 추가 sj
 * #########################
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyPopupInqireDao")
public class GamPrtFcltyPopupInqireDao extends YGPAAbstractDAO {

    /**
	 * 자산정보현황 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 조회내역
	 * @exception Exception
	 */
	public GamPrtFcltyPopupInqireVO selectPrtFcltyPopupInqire(GamPrtFcltyPopupInqireVO searchVO) throws Exception {
		return (GamPrtFcltyPopupInqireVO) selectByPk("gamPrtFcltyPopupInqireDao.selectPrtFcltyPopupInqire_S", null);
	}

}
