/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import java.util.List;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 13.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 13.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamSocTotalBsnsSetoffDtlsService {
	
	/**
	 * 총사업비상계처리내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리내역 목록
	 * @exception Exception
	 */
    List selectSocTotalBsnsSetoffDtlsList(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception;
    
    
    /**
	 * 총사업비상계처리내역 목록 총갯수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리내역 목록 총갯수
	 * @exception Exception
	 */
    int selectSocTotalBsnsSetoffDtlsListTotCnt(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception;
    
    
    
    /**
	 * 총사업비상계처리상세내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리상세내역 목록
	 * @exception Exception
	 */
    List selectSocTotalBsnsSetoffDtlsDetail(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception;
    
    
    
    /**
	 * 총사업비상계처리상세내역 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총사업비상계처리상세내역 총갯수 및 금액합계
	 * @exception Exception
	 */
    GamSocTotalBsnsSetoffDtlsVO selectSocTotalBsnsSetoffDtlsDetailSum(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception;

}
