package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyUseSttusInqireServiceImpl.java
 * @Description : 항만시설사용현황조회 Implement class
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamPrtFcltyUseSttusInqireService")
public class GamPrtFcltyUseSttusInqireServiceImpl extends AbstractServiceImpl implements GamPrtFcltyUseSttusInqireService {

    @Resource(name="gamPrtFcltyUseSttusInqireDao")
    private GamPrtFcltyUseSttusInqireDao gamPrtFcltyUseSttusInqireDao;

    /**
	 * 항만시설사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireList(searchVO);
    }

    /**
	 * 항만시설사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireListTotCnt(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireSum(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireSum(searchVO);
    }

    /**
	 * 항만시설사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireDetailList(GamPrtFcltyUseSttusInqireVO vo) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailList(vo);
    }

    /**
	 * 항만시설사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireDetailListTotCnt(GamPrtFcltyUseSttusInqireVO vo) throws Exception {
		return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailListTotCnt(vo);
	}

}
