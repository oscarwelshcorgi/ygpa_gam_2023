package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtVO;

/**
 * @Class Name : GamCntnrRentMngtServiceImpl.java
 * @Description : 컨테이너부두임대목록관리 (항만시설/컨테이너부두/컨테이너부두임대목록관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrRentMngtService")
public class GamCntnrRentMngtImpl extends AbstractServiceImpl implements GamCntnrRentMngtService {

    @Resource(name="gamCntnrRentMngtDao")
    private GamCntnrRentMngtDao gamCntnrRentMngtDao;

    /**
	 * 컨테이너부두임대목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
    public List selectGamCntnrRentMngtList(GamCntnrRentMngtVO searchVO) throws Exception {
        return gamCntnrRentMngtDao.selectGamCntnrRentMngtList(searchVO);
    }

    /**
	 * 컨테이너부두임대목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 총 갯수
	 * @exception Exception
	 */
    public int selectGamCntnrRentMngtListTotCnt(GamCntnrRentMngtVO searchVO) {
		return gamCntnrRentMngtDao.selectGamCntnrRentMngtListTotCnt(searchVO);
	}

    /**
	 * 컨테이너부두임대목록 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 정보
	 * @exception Exception
	 */
    public GamCntnrRentMngtVO selectGamCntnrRentMngtInfo(GamCntnrRentMngtVO searchVO) throws Exception {
        return gamCntnrRentMngtDao.selectGamCntnrRentMngtInfo(searchVO);
    }
}
