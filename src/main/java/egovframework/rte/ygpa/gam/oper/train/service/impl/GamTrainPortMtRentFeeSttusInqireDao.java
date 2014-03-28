package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortMtRentFeeSttusInqireVO;

/**
 * @Class Name : GamTrainPortMtRentFeeSttusInqireDao.java
 * @Description : 철송장임대월별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortMtRentFeeSttusInqireDao")
public class GamTrainPortMtRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 철송장임대월별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectTrainPortMtRentFeeSttusInqireList(GamTrainPortMtRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamTrainPortMtRentFeeSttusInqireDao.selectTrainPortMtRentFeeSttusInqireList_D", searchVO);
    }
    
}
