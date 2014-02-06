package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentSttusInqireVO;

/**
 * @Class Name : GamHtldRentSttusInqireService.java
 * @Description : 배후단지임대현황조회 (배후단지/배후단지/배후단지임대현황조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldRentSttusInqireService {
	
	/**
	 * 배후단지사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldRentSttusInqireList(GamHtldRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamHtldRentSttusInqireVO selectHtldRentSttusInqireSum(GamHtldRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 배후단지사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentSttusInqireListTotCnt(GamHtldRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 배후단지사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldRentSttusInqireDetailList(GamHtldRentSttusInqireVO vo) throws Exception;

    /**
	 * 배후단지사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentSttusInqireDetailListTotCnt(GamHtldRentSttusInqireVO vo) throws Exception;
    
}
