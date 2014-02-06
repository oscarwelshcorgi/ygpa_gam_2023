package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrShedRentSttusInqireService.java
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
public interface GamCmmnCntrShedRentSttusInqireService {
	
	/**
	 * 공컨장치장사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrShedRentSttusInqireList(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamCmmnCntrShedRentSttusInqireVO selectCmmnCntrShedRentSttusInqireSum(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrShedRentSttusInqireListTotCnt(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrShedRentSttusInqireDetailList(GamCmmnCntrShedRentSttusInqireVO vo) throws Exception;

    /**
	 * 공컨장치장사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrShedRentSttusInqireDetailListTotCnt(GamCmmnCntrShedRentSttusInqireVO vo) throws Exception;
    
}
