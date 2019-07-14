package egovframework.rte.ygpa.gam.asset.sts.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.sts.service.GamSttusInqireDefaultVO;

/**
 * @Class Name : GamAssetSeSttusInqireDao.java
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
@Repository("gamAssetSeSttusInqireDao")
public class GamAssetSeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산정보현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetSeSttusInqireList(GamSttusInqireDefaultVO searchVO) throws Exception {
        return list("gamAssetSeSttusInqireDao.selectAssetSeSttusInqireList", searchVO);
    }

    public List selectAssetSeSttusInqireList1(GamSttusInqireDefaultVO searchVO) throws Exception {
    	return list("gamAssetSeSttusInqireDao.selectAssetSeSttusInqireList1", searchVO);
    }
    
    public List selectAssetSeSttusInqireList2(GamSttusInqireDefaultVO searchVO) throws Exception {
    	return list("gamAssetSeSttusInqireDao.selectAssetSeSttusInqireList2", searchVO);
    }
}
