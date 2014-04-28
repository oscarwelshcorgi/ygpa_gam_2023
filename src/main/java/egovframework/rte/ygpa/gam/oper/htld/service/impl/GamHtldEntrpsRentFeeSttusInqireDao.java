package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamHtldEntrpsRentFeeSttusInqireDao.java
 * @Description : 배후단지임대업체별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldEntrpsRentFeeSttusInqireDao")
public class GamHtldEntrpsRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지임대업체별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectHtldEntrpsRentFeeSttusInqireList(GamHtldEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamHtldEntrpsRentFeeSttusInqireDao.selectHtldEntrpsRentFeeSttusInqireList_D", searchVO);
    }
    
    
    /**
   	 * 배후단지임대업체별사용료현황 전체목록수를 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return 목록
   	 * @exception Exception
   	 */
       public int selectHtldEntrpsRentFeeSttusInqireListTotCnt(GamHtldEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
       	return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyEntrpsRentFeeSttusInqireDao.selectHtldEntrpsRentFeeSttusInqireListTotCnt_S", searchVO);
       }
       
       
       /**
   	 * 전체사용료합계
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return vo
   	 * @exception Exception
   	 */
       public GamHtldEntrpsRentFeeSttusInqireVO selectHtldEntrpsRentFeeSttusInqireSum(GamHtldEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
       	return (GamHtldEntrpsRentFeeSttusInqireVO) selectByPk("gamPrtFcltyEntrpsRentFeeSttusInqireDao.selectHtldEntrpsRentFeeSttusInqireSum_S", searchVO);
       }
    
}
