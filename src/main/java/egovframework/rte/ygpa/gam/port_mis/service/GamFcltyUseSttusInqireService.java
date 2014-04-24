package egovframework.rte.ygpa.gam.port_mis.service;

import java.util.List;

/**
 * @Class Name : GamFcltyUseSttusInqireService.java
 * @Description : 항만시설사용현황조회(포트미스정보)
 * @Modification Information
 *   수정일          수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  domh          최초 생성
  *  2014.04.14  lsl          선석별 사용현황 조회처리 -- 기존 파일은 _처리 백업
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
	 * 면제금액합계, 할인금액합계, 고지금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamFcltyUseSttusInqireVO selectFcltyUseSttusInqireSum(GamFcltyUseSttusInqireVO searchVO) throws Exception;
    
}
