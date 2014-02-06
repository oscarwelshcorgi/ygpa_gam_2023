package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentSttusInqireVO;

/**
 * @Class Name : GamCntnrQuayRentSttusInqireServiceImpl.java
 * @Description : 컨테이너부두임대현황조회 (컨테이너부두/컨테이너부두/컨테이너부두임대현황조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayRentSttusInqireService")
public class GamCntnrQuayRentSttusInqireServiceImpl extends AbstractServiceImpl implements GamCntnrQuayRentSttusInqireService {

    @Resource(name="gamCntnrQuayRentSttusInqireDao")
    private GamCntnrQuayRentSttusInqireDao gamCntnrQuayRentSttusInqireDao;

    /**
	 * 컨테이너부두사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayRentSttusInqireList(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
        return gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireList(searchVO);
    }

    /**
	 * 컨테이너부두사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentSttusInqireListTotCnt(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
		return gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamCntnrQuayRentSttusInqireVO selectCntnrQuayRentSttusInqireSum(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
        return gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireSum(searchVO);
    }

    /**
	 * 컨테이너부두사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayRentSttusInqireDetailList(GamCntnrQuayRentSttusInqireVO vo) throws Exception {
        return gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireDetailList(vo);
    }

    /**
	 * 컨테이너부두사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentSttusInqireDetailListTotCnt(GamCntnrQuayRentSttusInqireVO vo) throws Exception {
		return gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireDetailListTotCnt(vo);
	}

}
