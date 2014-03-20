package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyUseSttusInqireService.java
 * @Description : 항만시설사용현황조회
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyUseSttusInqireService {
	
	/**
	 * 항만시설사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUseSttusInqireList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireSum(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyUseSttusInqireListTotCnt(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUseSttusInqireDetailList(GamPrtFcltyUseSttusInqireVO vo) throws Exception;

    /**
	 * 항만시설사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyUseSttusInqireDetailListTotCnt(GamPrtFcltyUseSttusInqireVO vo) throws Exception;
    
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUseSttusInqireFileList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyUseSttusInqireFileListTotCnt(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception;
}
