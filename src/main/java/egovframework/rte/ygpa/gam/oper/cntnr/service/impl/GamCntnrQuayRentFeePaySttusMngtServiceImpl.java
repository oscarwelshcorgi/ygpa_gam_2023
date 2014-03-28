package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtVO;

/**
 * @Class Name : GamCntnrQuayRentFeePaySttusMngtServiceImpl.java
 * @Description : 컨테이너부두납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayRentFeePaySttusMngtService")

public class GamCntnrQuayRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamCntnrQuayRentFeePaySttusMngtService {

	@Resource(name="gamCntnrQuayRentFeePaySttusMngtDao")
    private GamCntnrQuayRentFeePaySttusMngtDao gamCntnrQuayRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayRentFeePaySttusMngtList(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 컨테이너부두임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentFeePaySttusMngtListTotCnt(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대납부현황관리목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentFeePaySttusMngtVO selectCntnrQuayRentFeePaySttusMngtSum(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtSum(searchVO);
    }

}
