package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.impl.GamCntnrRentFeePaySttusMngtDao;

/**
 * @Class Name : GamCntnrRentFeePaySttusMngtServiceImpl.java
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
@Service("gamCntnrRentFeePaySttusMngtService")

public class GamCntnrRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamCntnrRentFeePaySttusMngtService {

	@Resource(name="gamCntnrRentFeePaySttusMngtDao")
    private GamCntnrRentFeePaySttusMngtDao gamCntnrRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrRentFeePaySttusMngtList(GamCntnrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrRentFeePaySttusMngtDao.selectCntnrRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 컨테이너부두납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrRentFeePaySttusMngtListTotCnt(GamCntnrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCntnrRentFeePaySttusMngtDao.selectCntnrRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두납부현황관리목록
	 * @exception Exception
	 */
    public GamCntnrRentFeePaySttusMngtVO selectCntnrRentFeePaySttusMngtSum(GamCntnrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrRentFeePaySttusMngtDao.selectCntnrRentFeePaySttusMngtSum(searchVO);
    }

}
