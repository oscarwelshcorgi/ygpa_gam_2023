package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyEntrpsRentFeeSttusInqireDao.java
 * @Description : 항만시설업체별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyEntrpsRentFeeSttusInqireDao")
public class GamPrtFcltyEntrpsRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설업체별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyEntrpsRentFeeSttusInqireList(GamPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyEntrpsRentFeeSttusInqireDao.selectPrtFcltyEntrpsRentFeeSttusInqireList_D", searchVO);
    }
    
    /**
   	 * 항만시설기간별사용료현황 전체목록수를 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return 목록
   	 * @exception Exception
   	 */
       public int selectPrtFcltyEntrpsRentFeeSttusInqireListTotCnt(GamPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
       	return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyEntrpsRentFeeSttusInqireDao.selectPrtFcltyEntrpsRentFeeSttusInqireListTotCnt_S", searchVO);
       }
       
       
       /**
   	 * 전체사용료합계
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return vo
   	 * @exception Exception
   	 */
       public GamPrtFcltyEntrpsRentFeeSttusInqireVO selectPrtFcltyEntrpsRentFeeSttusInqireSum(GamPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
       	return (GamPrtFcltyEntrpsRentFeeSttusInqireVO) selectByPk("gamPrtFcltyEntrpsRentFeeSttusInqireDao.selectPrtFcltyEntrpsRentFeeSttusInqireSum_S", searchVO);
       }
    
}
