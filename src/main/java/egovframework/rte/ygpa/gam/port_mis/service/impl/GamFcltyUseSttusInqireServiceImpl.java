package egovframework.rte.ygpa.gam.port_mis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireService;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireVO;

/**
 * @Class Name : GamFcltyUseSttusInqireServiceImpl.java
 * @Description : 항만시설사용현황조회(포트미스정보) Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamFcltyUseSttusInqireService")
public class GamFcltyUseSttusInqireServiceImpl extends AbstractServiceImpl implements GamFcltyUseSttusInqireService {

	@Resource(name="gamFcltyUseSttusInqireDao")
    private GamFcltyUseSttusInqireDao gamFcltyUseSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectFcltyUseSttusInqireList(GamFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamFcltyUseSttusInqireDao.selectFcltyUseSttusInqireList(searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFcltyUseSttusInqireListTotCnt(GamFcltyUseSttusInqireVO searchVO) throws Exception {
		return gamFcltyUseSttusInqireDao.selectFcltyUseSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설납부현황관리목록
	 * @exception Exception
	 */
    public GamFcltyUseSttusInqireVO selectFcltyUseSttusInqireSum(GamFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamFcltyUseSttusInqireDao.selectFcltyUseSttusInqireSum(searchVO);
    }
}
