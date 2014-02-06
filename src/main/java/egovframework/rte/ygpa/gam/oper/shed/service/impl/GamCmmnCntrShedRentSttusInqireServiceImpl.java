package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrShedRentSttusInqireServiceImpl.java
 * @Description : 공컨장치장임대현황조회 (공컨장치장/공컨장치장/공컨장치장임대현황조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrShedRentSttusInqireService")
public class GamCmmnCntrShedRentSttusInqireServiceImpl extends AbstractServiceImpl implements GamCmmnCntrShedRentSttusInqireService {

    @Resource(name="gamCmmnCntrShedRentSttusInqireDao")
    private GamCmmnCntrShedRentSttusInqireDao gamCmmnCntrShedRentSttusInqireDao;

    /**
	 * 공컨장치장사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrShedRentSttusInqireList(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireList(searchVO);
    }

    /**
	 * 공컨장치장사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrShedRentSttusInqireListTotCnt(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception {
		return gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamCmmnCntrShedRentSttusInqireVO selectCmmnCntrShedRentSttusInqireSum(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireSum(searchVO);
    }

    /**
	 * 공컨장치장사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrShedRentSttusInqireDetailList(GamCmmnCntrShedRentSttusInqireVO vo) throws Exception {
        return gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireDetailList(vo);
    }

    /**
	 * 공컨장치장사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrShedRentSttusInqireDetailListTotCnt(GamCmmnCntrShedRentSttusInqireVO vo) throws Exception {
		return gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireDetailListTotCnt(vo);
	}

}
