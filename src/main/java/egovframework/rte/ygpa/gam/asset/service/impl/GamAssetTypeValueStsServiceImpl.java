package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.service.GamAssetTypeValueStsService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetTypeValueStsVO;

/**
 * @Class Name : GamAssetTypeValueStsServiceImpl.java
 * @Description : 자산종류별자산가치통계조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetTypeValueStsService")

public class GamAssetTypeValueStsServiceImpl  extends AbstractServiceImpl implements GamAssetTypeValueStsService {

	@Resource(name="gamAssetTypeValueStsDao")
    private GamAssetTypeValueStsDao gamAssetTypeValueStsDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산종류별자산가치통계조회 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetTypeValueStsList(GamAssetTypeValueStsVO searchVO) throws Exception {
        return gamAssetTypeValueStsDao.selectAssetTypeValueStsList(searchVO);
    }
    
    /**
   	 * 자산종류별자산가치통계조회 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
       public int selectAssetTypeValueStsListTotCnt(GamAssetTypeValueStsVO searchVO) throws Exception {
   		//return gamAssetRentMngtDao.selectAssetRentListTotCnt(searchVO);
    	   return gamAssetTypeValueStsDao.selectAssetTypeValueStsListTotCnt(searchVO);
   	}
    
}
