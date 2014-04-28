package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayEntrpsRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamCntnrQuayEntrpsRentFeeSttusInqireServiceImpl.java
 * @Description : 컨테이너부두임대업체별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayEntrpsRentFeeSttusInqireService")

public class GamCntnrQuayEntrpsRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamCntnrQuayEntrpsRentFeeSttusInqireService {

	@Resource(name="gamCntnrQuayEntrpsRentFeeSttusInqireDao")
    private GamCntnrQuayEntrpsRentFeeSttusInqireDao gamCntnrQuayEntrpsRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대업체별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayEntrpsRentFeeSttusInqireList(GamCntnrQuayEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
        return gamCntnrQuayEntrpsRentFeeSttusInqireDao.selectCntnrQuayEntrpsRentFeeSttusInqireList(searchVO);
    }
    
    
    /**
	 * 컨테이너부두임대업체별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int
	 * @exception Exception
	 */
    public int selectCntnrQuayEntrpsRentFeeSttusInqireListTotCnt(GamCntnrQuayEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamCntnrQuayEntrpsRentFeeSttusInqireDao.selectCntnrQuayEntrpsRentFeeSttusInqireListTotCnt(searchVO);
    }
    
    
    /**
	 * 전체사용료합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamCntnrQuayEntrpsRentFeeSttusInqireVO selectCntnrQuayEntrpsRentFeeSttusInqireSum(GamCntnrQuayEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamCntnrQuayEntrpsRentFeeSttusInqireDao.selectCntnrQuayEntrpsRentFeeSttusInqireSum(searchVO);
    }
    
}
