package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetTotalRentfeeInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetTotalRentfeeInqireVO;

/**
 * @Class Name : GamAssetTotalRentfeeInqireServiceImpl.java
 * @Description : 자산별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetTotalRentfeeInqireService")

public class GamAssetTotalRentfeeInqireServiceImpl  extends AbstractServiceImpl implements GamAssetTotalRentfeeInqireService {

	@Resource(name="gamAssetTotalRentfeeInqireDao")
    private GamAssetTotalRentfeeInqireDao gamAssetTotalRentfeeInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetTotalRentfeeInqireList(GamAssetTotalRentfeeInqireVO searchVO) throws Exception {
        return gamAssetTotalRentfeeInqireDao.selectAssetTotalRentfeeInqireList(searchVO);
    }
    
    /**
   	 * 자산별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
       public int selectAssetTotalRentfeeInqireListTotCnt(GamAssetTotalRentfeeInqireVO searchVO) throws Exception {
   		//return gamAssetRentMngtDao.selectAssetRentListTotCnt(searchVO);
    	   return gamAssetTotalRentfeeInqireDao.selectAssetTotalRentfeeInqireListTotCnt(searchVO);
   	}
       
       
       /**
   	 * 사용료합계
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return vo
   	 * @exception Exception
   	 */
       public GamAssetTotalRentfeeInqireVO selectAssetTotalRentfeeInqireSum(GamAssetTotalRentfeeInqireVO searchVO) throws Exception {
           return gamAssetTotalRentfeeInqireDao.selectAssetTotalRentfeeInqireSum(searchVO);
       }
    
}
