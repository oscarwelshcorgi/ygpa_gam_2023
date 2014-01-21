package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeeInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeeInqireVO;

/**
 * @Class Name : GamCmmnCntrRentFeeInqireServiceImpl.java
 * @Description : 공컨장치장임대료조회 (항만시설/공컨장치장/공컨장치장임대료조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrRentFeeInqireService")
public class GamCmmnCntrRentFeeInqireImpl implements GamCmmnCntrRentFeeInqireService {
	
    @Resource(name="gamCmmnCntrRentFeeInqireDao")
    private GamCmmnCntrRentFeeInqireDao gamCmmnCntrRentFeeInqireDao;

    /**
	 * 컨테이너부두임대료관리를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 목록
	 * @exception Exception
	 */
    public List selectGamCmmnCntrRentFeeInqireList(GamCmmnCntrRentFeeInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentFeeInqireDao.selectGamCmmnCntrRentFeeInqireList(searchVO);
    }

    /**
	 * 컨테이너부두임대료관리 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 총 갯수
	 * @exception Exception
	 */
    public int selectGamCmmnCntrRentFeeInqireListTotCnt(GamCmmnCntrRentFeeInqireVO searchVO) {
		return gamCmmnCntrRentFeeInqireDao.selectGamCmmnCntrRentFeeInqireListTotCnt(searchVO);
	}
    
    /**
	 * 컨테이너부두임대료관리 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리정보
	 * @exception Exception
	 */
    public GamCmmnCntrRentFeeInqireVO selectGamCmmnCntrRentFeeInqireInfo(GamCmmnCntrRentFeeInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentFeeInqireDao.selectGamCmmnCntrRentFeeInqireInfo(searchVO);
    }
}
