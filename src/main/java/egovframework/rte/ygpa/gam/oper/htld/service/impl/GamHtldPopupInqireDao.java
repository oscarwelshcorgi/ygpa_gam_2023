package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;

/**
 * @Class Name : GamHtldPopupInqireDao.java
 * @Description : 배후단지 정보현황알림 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 * 
 * 수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.06.17  sj          최초 생성
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("GamHtldPopupInqireDao")
public class GamHtldPopupInqireDao extends YGPAAbstractDAO {
	
    /**
	 * 배후단지 정보현황알림 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 조회내역
	 * @exception Exception
	 */
	public GamAssetPopupInqireVO selectHtldPopupInqire(GamAssetPopupInqireVO searchVO) throws Exception {
		return (GamAssetPopupInqireVO) selectByPk("gamHtldPopupInqireDao.selectHtldPopupInqire_S", searchVO);
	}
    
}
