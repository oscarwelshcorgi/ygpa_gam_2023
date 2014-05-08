package egovframework.rte.ygpa.gam.asset.service;

import java.util.List;

/**
 * @Class Name : GamAssetLndValInqireService.java
 * @Description : 자산부지공시지가조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetLndValInqireService {

    /**
	 * 자산부지공시지가 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetLndValInqireList(GamAssetLndValInqireVO searchVO) throws Exception;

    /**
	 * 자산부지공시지가 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetLndValInqireListTotCnt(GamAssetLndValInqireVO searchVO) throws Exception;

    /**
	 * 자산부지공시지가 목록 합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 합계정보가 담긴 VO
	 * @exception
	 */
    GamAssetLndValInqireVO selectAssetLndValInqireSum(GamAssetLndValInqireVO searchVO) throws Exception;

}
