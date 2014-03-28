package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeePaySttusMngtVO;

/**
 * @Class Name : GamTrainPortRentFeePaySttusMngtServiceImpl.java
 * @Description : 철송장납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortRentFeePaySttusMngtService")

public class GamTrainPortRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamTrainPortRentFeePaySttusMngtService {

	@Resource(name="gamTrainPortRentFeePaySttusMngtDao")
    private GamTrainPortRentFeePaySttusMngtDao gamTrainPortRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 철송장임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentFeePaySttusMngtList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 철송장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentFeePaySttusMngtListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대납부현황관리목록
	 * @exception Exception
	 */
    public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePaySttusMngtSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtSum(searchVO);
    }

}
