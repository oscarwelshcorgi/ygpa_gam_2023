package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO;

/**
 * @Class Name : GamAssetRentFeePayDtlsMngtServiceImpl.java
 * @Description : 자산임대료납부관리 Business Implement class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentFeePayDtlsMngtService")

public class GamAssetRentFeePayDtlsMngtServiceImpl  extends AbstractServiceImpl implements GamAssetRentFeePayDtlsMngtService {

	@Resource(name="gamAssetRentFeePayDtlsMngtDao")
    private GamAssetRentFeePayDtlsMngtDao gamAssetRentFeePayDtlsMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentFeePayDtlsList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsList(searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentFeePayDtlsListTotCnt(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료납부관리목록
	 * @exception Exception
	 */
    public GamAssetRentFeePayDtlsMngtVO selectAssetRentFeePayDtlsSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsSum(searchVO);
    }
    
    
    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamAssetRentFeePayDtlsMngtVO selectAssetRentFeePayDtlsMngtSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtSum(searchVO);
    }

}
