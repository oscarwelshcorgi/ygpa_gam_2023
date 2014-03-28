package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamCntnrQuayPdRentFeeSttusInqireDao.java
 * @Description : 컨테이너부두임대기간별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayPdRentFeeSttusInqireDao")
public class GamCntnrQuayPdRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대기간별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayPdRentFeeSttusInqireList(GamCntnrQuayPdRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamCntnrQuayPdRentFeeSttusInqireDao.selectCntnrQuayPdRentFeeSttusInqireList_D", searchVO);
    }
    
}
