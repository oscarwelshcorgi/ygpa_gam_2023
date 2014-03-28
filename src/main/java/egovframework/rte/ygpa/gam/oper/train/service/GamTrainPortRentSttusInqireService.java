package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortRentSttusInqireService.java
 * @Description : 철송장임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentSttusInqireService {
	
	/**
	 * 철송장임대현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireSum(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 철송장임대현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 철송장임대현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentSttusInqireDetailList(GamTrainPortRentSttusInqireVO vo) throws Exception;

    /**
	 * 철송장임대현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentSttusInqireDetailListTotCnt(GamTrainPortRentSttusInqireVO vo) throws Exception;
    
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentSttusInqireFileList(GamTrainPortRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentSttusInqireFileListTotCnt(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
}

