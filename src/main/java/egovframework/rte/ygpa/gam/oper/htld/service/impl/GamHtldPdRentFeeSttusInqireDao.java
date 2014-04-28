package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamHtldPdRentFeeSttusInqireDao.java
 * @Description : 배후단지임대기간별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldPdRentFeeSttusInqireDao")
public class GamHtldPdRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지임대기간별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectHtldPdRentFeeSttusInqireList(GamHtldPdRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamHtldPdRentFeeSttusInqireDao.selectHtldPdRentFeeSttusInqireList_D", searchVO);
    }
    
    
    
    /**
   	 * 배후단지임대기간별사용료현황 전체목록수를 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return 목록
   	 * @exception Exception
   	 */
       public int selectHtldPdRentFeeSttusInqireListTotCnt(GamHtldPdRentFeeSttusInqireVO searchVO) throws Exception {
       	return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldPdRentFeeSttusInqireDao.selectHtldPdRentFeeSttusInqireListTotCnt_S", searchVO);
       }
       
       
       /**
   	 * 사용료합계, 감면사용료 합계
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return vo
   	 * @exception Exception
   	 */
       public GamHtldPdRentFeeSttusInqireVO selectHtldPdRentFeeSttusInqireSum(GamHtldPdRentFeeSttusInqireVO searchVO) throws Exception {
       	return (GamHtldPdRentFeeSttusInqireVO) selectByPk("gamHtldPdRentFeeSttusInqireDao.selectHtldPdRentFeeSttusInqireSum_S", searchVO);
       }
    
}
