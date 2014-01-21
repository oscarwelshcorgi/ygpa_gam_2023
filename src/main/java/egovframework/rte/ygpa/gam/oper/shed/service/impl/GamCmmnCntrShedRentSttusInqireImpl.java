package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrShedRentSttusInqireServiceImpl.java
 * @Description : 공컨장치장임대현황조회 (항만시설/공컨장치장/공컨장치장임대현황조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrShedRentSttusInqireService")
public class GamCmmnCntrShedRentSttusInqireImpl implements GamCmmnCntrShedRentSttusInqireService {
	
    @Resource(name="gamCmmnCntrShedRentSttusInqireDao")
    private GamCmmnCntrShedRentSttusInqireDao gamCmmnCntrShedRentSttusInqireDao;

    /**
	 * 컨테이너부두임대현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 목록
	 * @exception Exception
	 */
    public List selectGamCmmnCntrShedRentSttusInqireList(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrShedRentSttusInqireDao.selectGamCmmnCntrShedRentSttusInqireList(searchVO);
    }

    /**
	 * 컨테이너부두임대현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 총 갯수
	 * @exception Exception
	 */
    public int selectGamCmmnCntrShedRentSttusInqireListTotCnt(GamCmmnCntrShedRentSttusInqireVO searchVO) {
		return gamCmmnCntrShedRentSttusInqireDao.selectGamCmmnCntrShedRentSttusInqireListTotCnt(searchVO);
	}
    
    /**
	 * 컨테이너부두임대현황 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 정보
	 * @exception Exception
	 */
    public GamCmmnCntrShedRentSttusInqireVO selectGamCmmnCntrShedRentSttusInqireInfo(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrShedRentSttusInqireDao.selectGamCmmnCntrShedRentSttusInqireInfo(searchVO);
    }
}
