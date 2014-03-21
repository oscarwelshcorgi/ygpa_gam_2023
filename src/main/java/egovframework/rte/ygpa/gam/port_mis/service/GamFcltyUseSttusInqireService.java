package egovframework.rte.ygpa.gam.port_mis.service;

import java.util.List;

/**
 * @Class Name : GamFcltyUseSttusInqireService.java
 * @Description : 항만시설사용현황조회(포트미스정보)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamFcltyUseSttusInqireService {
	
	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectFcltyUseSttusInqireList(GamFcltyUseSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectFcltyUseSttusInqireListTotCnt(GamFcltyUseSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설납부현황관리목록
	 * @exception Exception
	 */
    GamFcltyUseSttusInqireVO selectFcltyUseSttusInqireSum(GamFcltyUseSttusInqireVO searchVO) throws Exception;
    
}
