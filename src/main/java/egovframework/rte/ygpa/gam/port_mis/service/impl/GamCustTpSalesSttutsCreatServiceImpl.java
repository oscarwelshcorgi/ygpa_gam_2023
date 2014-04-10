package egovframework.rte.ygpa.gam.port_mis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.port_mis.service.GamCustTpSalesSttutsCreatService;
import egovframework.rte.ygpa.gam.port_mis.service.GamCustTpSalesSttutsCreatVO;

/**
 * @Class Name : GamCustTpSalesSttutsCreatServiceImpl.java
 * @Description : 고객군들통계(포트미스정보) DAO Class
 * @Modification Information
 *
 * @author lsl
 * @since 2014-04-9
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCustTpSalesSttutsCreatService")
public class GamCustTpSalesSttutsCreatServiceImpl extends AbstractServiceImpl implements GamCustTpSalesSttutsCreatService {

	@Resource(name="gamCustTpSalesSttutsCreatDao")
    private GamCustTpSalesSttutsCreatDao gamCustTpSalesSttutsCreatDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectFcltyUseSttusInqireList(GamCustTpSalesSttutsCreatVO searchVO) throws Exception {
        return gamCustTpSalesSttutsCreatDao.selectFcltyUseSttusInqireList(searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFcltyUseSttusInqireListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) throws Exception {
		return gamCustTpSalesSttutsCreatDao.selectFcltyUseSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설납부현황관리목록
	 * @exception Exception
	 */
    public GamCustTpSalesSttutsCreatVO selectFcltyUseSttusInqireSum(GamCustTpSalesSttutsCreatVO searchVO) throws Exception {
        return gamCustTpSalesSttutsCreatDao.selectFcltyUseSttusInqireSum(searchVO);
    }
}
