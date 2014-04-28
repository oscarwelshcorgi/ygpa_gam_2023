package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayMtRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayMtRentFeeSttusInqireVO;

/**
 * @Class Name : GamCntnrQuayMtRentFeeSttusInqireServiceImpl.java
 * @Description : 컨테이너부두임대월별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayMtRentFeeSttusInqireService")

public class GamCntnrQuayMtRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamCntnrQuayMtRentFeeSttusInqireService {

	@Resource(name="gamCntnrQuayMtRentFeeSttusInqireDao")
    private GamCntnrQuayMtRentFeeSttusInqireDao gamCntnrQuayMtRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayMtRentFeeSttusInqireList(GamCntnrQuayMtRentFeeSttusInqireVO searchVO) throws Exception {
        return gamCntnrQuayMtRentFeeSttusInqireDao.selectCntnrQuayMtRentFeeSttusInqireList(searchVO);
    }

    /**
	 * 항만시설월별사용료현황 목록 자료수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int - 자료수
	 * @exception Exception
	 */
    public int selectCntnrQuayMtRentFeeSttusInqireListTotCnt(GamCntnrQuayMtRentFeeSttusInqireVO searchVO) throws Exception{
    	return gamCntnrQuayMtRentFeeSttusInqireDao.selectCntnrQuayMtRentFeeSttusInqireListTotCnt(searchVO);
    }

    /**
	 * 항만시설월별사용료현황 자료수, 합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return resultVO - 합계정보가 담긴 VO
	 * @exception Exception
	 */
    public GamCntnrQuayMtRentFeeSttusInqireVO selectCntnrQuayMtRentFeeSttusInqireSum(GamCntnrQuayMtRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamCntnrQuayMtRentFeeSttusInqireDao.selectCntnrQuayMtRentFeeSttusInqireSum(searchVO);
    }
    
}
