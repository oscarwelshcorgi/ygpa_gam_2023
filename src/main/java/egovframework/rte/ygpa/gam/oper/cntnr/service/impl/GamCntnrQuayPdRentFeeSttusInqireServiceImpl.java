package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayPdRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamCntnrQuayPdRentFeeSttusInqireServiceImpl.java
 * @Description : 컨테이너부두임대기간별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayPdRentFeeSttusInqireService")

public class GamCntnrQuayPdRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamCntnrQuayPdRentFeeSttusInqireService {

	@Resource(name="gamCntnrQuayPdRentFeeSttusInqireDao")
    private GamCntnrQuayPdRentFeeSttusInqireDao gamCntnrQuayPdRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayPdRentFeeSttusInqireList(GamCntnrQuayPdRentFeeSttusInqireVO searchVO) throws Exception {
        return gamCntnrQuayPdRentFeeSttusInqireDao.selectCntnrQuayPdRentFeeSttusInqireList(searchVO);
    }
    
    
    /**
	 * 컨테이너부두임대기간별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int
	 * @exception Exception
	 */
    public int selectCntnrQuayPdRentFeeSttusInqireListTotCnt(GamCntnrQuayPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamCntnrQuayPdRentFeeSttusInqireDao.selectCntnrQuayPdRentFeeSttusInqireListTotCnt(searchVO);
    }
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamCntnrQuayPdRentFeeSttusInqireVO selectCntnrQuayPdRentFeeSttusInqireSum(GamCntnrQuayPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamCntnrQuayPdRentFeeSttusInqireDao.selectCntnrQuayPdRentFeeSttusInqireSum(searchVO);
    }
    
}
