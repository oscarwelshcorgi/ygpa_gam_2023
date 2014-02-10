package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;

/**
 * @Class Name : GamAssetPopupInqireDao.java
 * @Description : 자산정보현황알림 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetPopupInqireDao")
public class GamAssetPopupInqireDao extends YGPAAbstractDAO {
	
    /**
	 * 자산정보현황 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 조회내역
	 * @exception Exception
	 */
	public GamAssetPopupInqireVO selectAssetPopupInqire(GamAssetPopupInqireVO searchVO) throws Exception {
		return (GamAssetPopupInqireVO) selectByPk("gamAssetPopupInqireDao.selectAssetPopupInqire_S", searchVO);
	}
    
}
