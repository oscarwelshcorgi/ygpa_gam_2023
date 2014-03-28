package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrPdRentFeeSttusInqireDao.java
 * @Description : 공컨장치장임대기간별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrPdRentFeeSttusInqireDao")
public class GamCmmnCntrPdRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대기간별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrPdRentFeeSttusInqireList(GamCmmnCntrPdRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrPdRentFeeSttusInqireDao.selectCmmnCntrPdRentFeeSttusInqireList_D", searchVO);
    }
    
}
