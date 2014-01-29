package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;

/**
 * @Class Name : GamPrtFcltyRentFeeMngtServiceImpl.java
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
@Service("gamPrtFcltyRentFeeMngtService")
public class GamPrtFcltyRentFeeMngtServiceImpl extends AbstractServiceImpl implements GamPrtFcltyRentFeeMngtService {
	
    @Resource(name="gamPrtFcltyRentFeeMngtDao")
    private GamPrtFcltyRentFeeMngtDao gamPrtFcltyRentFeeMngtDao;

    /**
	 * 항만시설사용현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    public List selectGamPrtFcltyRentFeeMngtList(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeeMngtDao.selectGamPrtFcltyRentFeeMngtList(searchVO);
    }

    /**
	 * 항만시설사용현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 총 갯수
	 * @exception Exception
	 */
    public int selectGamPrtFcltyRentFeeMngtListTotCnt(GamPrtFcltyRentFeeMngtVO searchVO) {
		return gamPrtFcltyRentFeeMngtDao.selectGamPrtFcltyRentFeeMngtListTotCnt(searchVO);
	}
}
