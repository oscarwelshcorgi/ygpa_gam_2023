package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireVO;

/**
 * @Class Name : GamTrainPortRentSttusInqireServiceImpl.java
 * @Description : 철송장임대현황조회 (철송장/철송장/철송장임대현황조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortRentSttusInqireService")
public class GamTrainPortRentSttusInqireServiceImpl extends AbstractServiceImpl implements GamTrainPortRentSttusInqireService {

    @Resource(name="gamTrainPortRentSttusInqireDao")
    private GamTrainPortRentSttusInqireDao gamTrainPortRentSttusInqireDao;

    /**
	 * 철송장사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireList(searchVO);
    }

    /**
	 * 철송장사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireSum(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireSum(searchVO);
    }

    /**
	 * 철송장사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireDetailList(GamTrainPortRentSttusInqireVO vo) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailList(vo);
    }

    /**
	 * 철송장사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireDetailListTotCnt(GamTrainPortRentSttusInqireVO vo) throws Exception {
		return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailListTotCnt(vo);
	}

}
