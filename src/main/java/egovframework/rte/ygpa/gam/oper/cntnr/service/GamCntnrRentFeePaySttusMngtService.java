package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

/**
 * @Class Name : GamCntnrRentFeePaySttusMngtService.java
 * @Description : 컨테이너부두납부현황관리 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrRentFeePaySttusMngtService {
	
    /**
	 * 컨테이너부두납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrRentFeePaySttusMngtList(GamCntnrRentFeePaySttusMngtVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrRentFeePaySttusMngtListTotCnt(GamCntnrRentFeePaySttusMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두납부현황관리목록
	 * @exception Exception
	 */
    GamCntnrRentFeePaySttusMngtVO selectCntnrRentFeePaySttusMngtSum(GamCntnrRentFeePaySttusMngtVO searchVO) throws Exception;
    
}
