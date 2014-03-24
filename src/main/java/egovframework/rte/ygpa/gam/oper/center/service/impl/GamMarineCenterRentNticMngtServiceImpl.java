package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentNticMngtService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentNticMngtVO;

/**
 * @Class Name : GamMarineCenterRentNticMngtServiceImpl.java
 * @Description : 마린센터임대료납부관리 Business Implement class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterRentNticMngtService")

public class GamMarineCenterRentNticMngtServiceImpl  extends AbstractServiceImpl implements GamMarineCenterRentNticMngtService {

	@Resource(name="gamMarineCenterRentNticMngtDao")
    private GamMarineCenterRentNticMngtDao gamMarineCenterRentNticMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터임대료납부관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterRentNticList(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticList(searchVO);
    }

    /**
	 * 마린센터임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterRentNticListTotCnt(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대료납부관리목록
	 * @exception Exception
	 */
    public GamMarineCenterRentNticMngtVO selectMarineCenterRentNticSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticSum(searchVO);
    }

}
