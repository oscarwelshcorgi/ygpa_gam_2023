package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentSttusInqireVO;

/**
 * @Class Name : GamCntnrQuayRentSttusInqireServiceImpl.java
 * @Description : 컨테이너부두임대현황조회 (항만시설/컨테이너부두/컨테이너부두임대현황조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayRentSttusInqireService")
public class GamCntnrQuayRentSttusInqireImpl implements GamCntnrQuayRentSttusInqireService {
	
    @Resource(name="gamCntnrQuayRentSttusInqireDao")
    private GamCntnrQuayRentSttusInqireDao gamCntnrQuayRentSttusInqireDao;

    /**
	 * 컨테이너부두임대현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 목록
	 * @exception Exception
	 */
    public List selectGamCntnrQuayRentSttusInqireList(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
        return gamCntnrQuayRentSttusInqireDao.selectGamCntnrQuayRentSttusInqireList(searchVO);
    }

    /**
	 * 컨테이너부두임대현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 총 갯수
	 * @exception Exception
	 */
    public int selectGamCntnrQuayRentSttusInqireListTotCnt(GamCntnrQuayRentSttusInqireVO searchVO) {
		return gamCntnrQuayRentSttusInqireDao.selectGamCntnrQuayRentSttusInqireListTotCnt(searchVO);
	}
    
    /**
	 * 컨테이너부두임대현황 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 정보
	 * @exception Exception
	 */
    public GamCntnrQuayRentSttusInqireVO selectGamCntnrQuayRentSttusInqireInfo(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
        return gamCntnrQuayRentSttusInqireDao.selectGamCntnrQuayRentSttusInqireInfo(searchVO);
    }
}
