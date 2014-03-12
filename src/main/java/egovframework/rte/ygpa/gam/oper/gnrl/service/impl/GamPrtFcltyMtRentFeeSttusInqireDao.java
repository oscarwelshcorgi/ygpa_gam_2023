package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyMtRentFeeSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyMtRentFeeSttusInqireDao.java
 * @Description : 항만시설월별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyMtRentFeeSttusInqireDao")
public class GamPrtFcltyMtRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설월별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyMtRentFeeSttusInqireList(GamPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyMtRentFeeSttusInqireDao.selectPrtFcltyMtRentFeeSttusInqireList_D", searchVO);
    }
    
}
