package egovframework.rte.ygpa.gam.oper.gnrltest.service;

import java.util.List;

import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamTestPrtFcltyUseSttusInqireService.java
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
public interface GamTestPrtFcltyUseSttusInqireService {
	
	/**
	 * 항만시설사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUseSttusInqireList(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamTestPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireSum(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyUseSttusInqireListTotCnt(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUseSttusInqireDetailList(GamTestPrtFcltyUseSttusInqireVO vo) throws Exception;

    /**
	 * 항만시설사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyUseSttusInqireDetailListTotCnt(GamTestPrtFcltyUseSttusInqireVO vo) throws Exception;
    
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUseSttusInqireFileList(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyUseSttusInqireFileListTotCnt(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception;
    
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception;
    
    
    /**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception;
    
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireBeforeQuarterInfo(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception;
    
    
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireCofixInfo(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception;
}
