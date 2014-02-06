package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentfeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentfeeMngtVO;

/**
 * @Class Name : GamHtldRentfeeMngtServiceImpl.java
 * @Description : 배후단지임대료관리 (항만시설/배후단지/배후단지임대료관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldRentfeeMngtService")
public class GamHtldRentfeeMngtServiceImpl extends AbstractServiceImpl implements GamHtldRentfeeMngtService {

    @Resource(name="gamHtldRentfeeMngtDao")
    private GamHtldRentfeeMngtDao gamHtldRentfeeMngtDao;

    /**
	 * 배후단지임대료관리를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료관리 목록
	 * @exception Exception
	 */
    public List selectGamHtldRentfeeMngtList(GamHtldRentfeeMngtVO searchVO) throws Exception {
        return gamHtldRentfeeMngtDao.selectGamHtldRentfeeMngtList(searchVO);
    }

    /**
	 * 배후단지임대료관리 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료관리 총 갯수
	 * @exception Exception
	 */
    public int selectGamHtldRentfeeMngtListTotCnt(GamHtldRentfeeMngtVO searchVO) {
		return gamHtldRentfeeMngtDao.selectGamHtldRentfeeMngtListTotCnt(searchVO);
	}

    /**
	 * 배후단지임대료관리 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료관리정보
	 * @exception Exception
	 */
    public GamHtldRentfeeMngtVO selectGamHtldRentfeeMngtInfo(GamHtldRentfeeMngtVO searchVO) throws Exception {
        return gamHtldRentfeeMngtDao.selectGamHtldRentfeeMngtInfo(searchVO);
    }
}
