package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO;

/**
 * @Class Name : GamPrtFcltyRentFeePaySttusMngtServiceImpl.java
 * @Description : 항만시설납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamPrtFcltyRentFeePaySttusMngtService")

public class GamPrtFcltyRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyRentFeePaySttusMngtService {

	@Resource(name="gamPrtFcltyRentFeePaySttusMngtDao")
    private GamPrtFcltyRentFeePaySttusMngtDao gamPrtFcltyRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeePaySttusMngtList(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtListTotCnt(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설납부현황관리목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusMngtSum(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtSum(searchVO);
    }

}
