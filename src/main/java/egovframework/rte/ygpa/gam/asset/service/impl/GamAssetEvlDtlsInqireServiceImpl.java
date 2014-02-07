package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireVO;

/**
 * @Class Name : GamAssetEvlDtlsInqireServiceImpl.java
 * @Description : 자산가치평가내역조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetEvlDtlsInqireService")

public class GamAssetEvlDtlsInqireServiceImpl  extends AbstractServiceImpl implements GamAssetEvlDtlsInqireService {

	@Resource(name="gamAssetEvlDtlsInqireDao")
    private GamAssetEvlDtlsInqireDao gamAssetEvlDtlsInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산가치평가내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetEvlDtlsInqireList(GamAssetEvlDtlsInqireVO searchVO) throws Exception {
        return gamAssetEvlDtlsInqireDao.selectAssetEvlDtlsInqireList(searchVO);
    }
    
    /**
	 * 자산가치평가내역 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetEvlDtlsInqireListTotCnt(GamAssetEvlDtlsInqireVO searchVO) throws Exception {
		return gamAssetEvlDtlsInqireDao.selectAssetEvlDtlsInqireListTotCnt(searchVO);
	}

}
