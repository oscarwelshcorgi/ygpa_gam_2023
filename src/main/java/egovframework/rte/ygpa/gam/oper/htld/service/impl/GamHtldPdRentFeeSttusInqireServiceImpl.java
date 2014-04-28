package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldPdRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamHtldPdRentFeeSttusInqireServiceImpl.java
 * @Description : 배후단지임대기간별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldPdRentFeeSttusInqireService")

public class GamHtldPdRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamHtldPdRentFeeSttusInqireService {

	@Resource(name="gamHtldPdRentFeeSttusInqireDao")
    private GamHtldPdRentFeeSttusInqireDao gamHtldPdRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldPdRentFeeSttusInqireList(GamHtldPdRentFeeSttusInqireVO searchVO) throws Exception {
        return gamHtldPdRentFeeSttusInqireDao.selectHtldPdRentFeeSttusInqireList(searchVO);
    }
    
    
    /**
	 * 배후단지임대기간별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int
	 * @exception Exception
	 */
    public int selectHtldPdRentFeeSttusInqireListTotCnt(GamHtldPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamHtldPdRentFeeSttusInqireDao.selectHtldPdRentFeeSttusInqireListTotCnt(searchVO);
    }
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamHtldPdRentFeeSttusInqireVO selectHtldPdRentFeeSttusInqireSum(GamHtldPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamHtldPdRentFeeSttusInqireDao.selectHtldPdRentFeeSttusInqireSum(searchVO);
    }
    
}
