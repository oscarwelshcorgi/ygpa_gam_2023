package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortPdRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamTrainPortPdRentFeeSttusInqireServiceImpl.java
 * @Description : 철송장임대기간별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortPdRentFeeSttusInqireService")

public class GamTrainPortPdRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamTrainPortPdRentFeeSttusInqireService {

	@Resource(name="gamTrainPortPdRentFeeSttusInqireDao")
    private GamTrainPortPdRentFeeSttusInqireDao gamTrainPortPdRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 철송장임대기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortPdRentFeeSttusInqireList(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception {
        return gamTrainPortPdRentFeeSttusInqireDao.selectTrainPortPdRentFeeSttusInqireList(searchVO);
    }
    
    
    /**
	 * 철송장임대기간별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int
	 * @exception Exception
	 */
    public int selectTrainPortPdRentFeeSttusInqireListTotCnt(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamTrainPortPdRentFeeSttusInqireDao.selectTrainPortPdRentFeeSttusInqireListTotCnt(searchVO);
    }
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamTrainPortPdRentFeeSttusInqireVO selectTrainPortPdRentFeeSttusInqireSum(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamTrainPortPdRentFeeSttusInqireDao.selectTrainPortPdRentFeeSttusInqireSum(searchVO);
    }
    
}
