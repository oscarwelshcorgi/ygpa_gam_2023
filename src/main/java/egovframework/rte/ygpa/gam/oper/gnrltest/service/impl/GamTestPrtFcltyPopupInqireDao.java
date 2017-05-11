package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyPopupInqireVO;

/**
 * @Class Name : GamTestPrtFcltyPopupInqireDao.java
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
@Repository("gamTestPrtFcltyPopupInqireDao")
public class GamTestPrtFcltyPopupInqireDao extends YGPAAbstractDAO {

    /**
	 * 자산정보현황 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 조회내역
	 * @exception Exception
	 */
	public GamTestPrtFcltyPopupInqireVO selectPrtFcltyPopupInqire(GamTestPrtFcltyPopupInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyPopupInqireVO) selectByPk("gamTestPrtFcltyPopupInqireDao.selectPrtFcltyPopupInqire_S", null);
	}

}
