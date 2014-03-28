package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

/**
 * @Class Name : GamCntnrQuayRentSttusInqireService.java
 * @Description : 컨테이너부두임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayRentSttusInqireService {
	
	/**
	 * 컨테이너부두임대현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayRentSttusInqireList(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamCntnrQuayRentSttusInqireVO selectCntnrQuayRentSttusInqireSum(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayRentSttusInqireListTotCnt(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayRentSttusInqireDetailList(GamCntnrQuayRentSttusInqireVO vo) throws Exception;

    /**
	 * 컨테이너부두임대현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayRentSttusInqireDetailListTotCnt(GamCntnrQuayRentSttusInqireVO vo) throws Exception;
    
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayRentSttusInqireFileList(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayRentSttusInqireFileListTotCnt(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception;
}

