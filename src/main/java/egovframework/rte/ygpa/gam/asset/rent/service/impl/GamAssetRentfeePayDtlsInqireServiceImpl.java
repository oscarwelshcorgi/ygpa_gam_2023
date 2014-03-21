package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentfeePayDtlsInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentfeePayDtlsInqireVO;

/**
 * @Class Name : GamAssetRentfeePayDtlsInqireServiceImpl.java
 * @Description : 사용료납부내역조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentfeePayDtlsInqireService")

public class GamAssetRentfeePayDtlsInqireServiceImpl  extends AbstractServiceImpl implements GamAssetRentfeePayDtlsInqireService {

	@Resource(name="gamAssetRentfeePayDtlsInqireDao")
    private GamAssetRentfeePayDtlsInqireDao gamAssetRentfeePayDtlsInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 사용료납부내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentfeePayDtlsInqireList(GamAssetRentfeePayDtlsInqireVO searchVO) throws Exception {
        return gamAssetRentfeePayDtlsInqireDao.selectAssetRentfeePayDtlsInqireList(searchVO);
    }
    
    /**
   	 * 사용료납부내역 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
       public int selectAssetRentfeePayDtlsInqireListTotCnt(GamAssetRentfeePayDtlsInqireVO searchVO) throws Exception {
   		//return gamAssetRentMngtDao.selectAssetRentListTotCnt(searchVO);
    	   return gamAssetRentfeePayDtlsInqireDao.selectAssetRentfeePayDtlsInqireListTotCnt(searchVO);
   	}
    
}
