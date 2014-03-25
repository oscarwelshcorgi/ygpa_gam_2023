package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterPdRentfeeSttusInqireVO;

/**
 * @Class Name : GamMarineCenterPdRentfeeSttusInqireDao.java
 * @Description : 마린센터기간별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterPdRentfeeSttusInqireDao")
public class GamMarineCenterPdRentfeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터기간별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectMarineCenterPdRentfeeSttusInqireList(GamMarineCenterPdRentfeeSttusInqireVO searchVO) throws Exception {
        return list("gamMarineCenterPdRentfeeSttusInqireDao.selectMarineCenterPdRentfeeSttusInqireList_D", searchVO);
    }
    
    /**
	 * 마린센터기간별사용료현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterPdRentfeeSttusInqireListTotCnt(GamMarineCenterPdRentfeeSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterPdRentfeeSttusInqireDao.selectMarineCenterPdRentfeeSttusInqireListTotCnt_S", searchVO);
    }
    
}
