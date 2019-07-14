package egovframework.rte.ygpa.gam.asset.sts.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.sts.service.GamSttusInqireDefaultVO;

/**
 * @Class Name : GamAssetErpSttusInqireDao.java
 * @Description : 자산정보현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetErpSttusInqireDao")
public class GamAssetErpSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산정보현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetErpSttusInqireList(GamSttusInqireDefaultVO searchVO) throws Exception {
        return list("gamAssetErpSttusInqireDao.selectAssetErpSttusInqireList", searchVO);
    }

}
