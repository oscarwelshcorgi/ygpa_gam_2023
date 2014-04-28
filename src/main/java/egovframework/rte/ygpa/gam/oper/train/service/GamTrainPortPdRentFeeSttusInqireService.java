package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.oper.train.service.impl.GamTrainPortPdRentFeeSttusInqireDao;

/**
 * @Class Name : GamTrainPortPdRentFeeSttusInqireService.java
 * @Description : 철송장임대기간별사용료현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortPdRentFeeSttusInqireService {
	
    /**
	 * 철송장임대기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortPdRentFeeSttusInqireList(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception;
    
    
    /**
	 * 철송장임대기간별사용료현황 전체목록수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @exception Exception
	 */
    int selectTrainPortPdRentFeeSttusInqireListTotCnt(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception;
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamTrainPortPdRentFeeSttusInqireVO selectTrainPortPdRentFeeSttusInqireSum(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception;
    
}
