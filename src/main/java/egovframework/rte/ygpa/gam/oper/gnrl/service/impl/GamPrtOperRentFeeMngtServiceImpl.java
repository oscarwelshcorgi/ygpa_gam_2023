package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtOperRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtOperRentFeeMngtVO;

/**
 * @Class Name : GamPrtOperRentFeeMngtServiceImpl.java
 * @Description : 항만시설사용료관리 (항만시설/일반부두/항만시설사용료관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamPrtOperRentFeeMngtService")
public class GamPrtOperRentFeeMngtServiceImpl extends AbstractServiceImpl implements GamPrtOperRentFeeMngtService {

    @Resource(name="gamPrtOperRentFeeMngtDao")
    private GamPrtOperRentFeeMngtDao gamPrtOperRentFeeMngtDao;

    /**
	 * 항만시설사용현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    public List selectGamPrtOperRentFeeMngtList(GamPrtOperRentFeeMngtVO searchVO) throws Exception {
        return gamPrtOperRentFeeMngtDao.selectGamPrtOperRentFeeMngtList(searchVO);
    }

    /**
	 * 항만시설사용현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 총 갯수
	 * @exception Exception
	 */
    public int selectGamPrtOperRentFeeMngtListTotCnt(GamPrtOperRentFeeMngtVO searchVO) {
		return gamPrtOperRentFeeMngtDao.selectGamPrtOperRentFeeMngtListTotCnt(searchVO);
	}
}
