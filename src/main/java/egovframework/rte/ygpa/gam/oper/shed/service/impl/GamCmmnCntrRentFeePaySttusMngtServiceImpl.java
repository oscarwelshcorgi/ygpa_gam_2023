package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeePaySttusMngtVO;

/**
 * @Class Name : GamCmmnCntrRentFeePaySttusMngtServiceImpl.java
 * @Description : 공컨장치장납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrRentFeePaySttusMngtService")

public class GamCmmnCntrRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamCmmnCntrRentFeePaySttusMngtService {

	@Resource(name="gamCmmnCntrRentFeePaySttusMngtDao")
    private GamCmmnCntrRentFeePaySttusMngtDao gamCmmnCntrRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentFeePaySttusMngtList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 공컨장치장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentFeePaySttusMngtListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대납부현황관리목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePaySttusMngtSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtSum(searchVO);
    }

}
