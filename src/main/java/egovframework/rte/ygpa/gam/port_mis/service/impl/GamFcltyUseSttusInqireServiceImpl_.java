package egovframework.rte.ygpa.gam.port_mis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireService_;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireVO_;

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
@Service("gamFcltyUseSttusInqireService_")
public class GamFcltyUseSttusInqireServiceImpl_ extends AbstractServiceImpl implements GamFcltyUseSttusInqireService_ {

	@Resource(name="gamFcltyUseSttusInqireDao_")
    private GamFcltyUseSttusInqireDao_ gamFcltyUseSttusInqireDao_;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectFcltyUseSttusInqireList(GamFcltyUseSttusInqireVO_ searchVO) throws Exception {
        return gamFcltyUseSttusInqireDao_.selectFcltyUseSttusInqireList(searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFcltyUseSttusInqireListTotCnt(GamFcltyUseSttusInqireVO_ searchVO) throws Exception {
		return gamFcltyUseSttusInqireDao_.selectFcltyUseSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설납부현황관리목록
	 * @exception Exception
	 */
    public GamFcltyUseSttusInqireVO_ selectFcltyUseSttusInqireSum(GamFcltyUseSttusInqireVO_ searchVO) throws Exception {
        return gamFcltyUseSttusInqireDao_.selectFcltyUseSttusInqireSum(searchVO);
    }
}
