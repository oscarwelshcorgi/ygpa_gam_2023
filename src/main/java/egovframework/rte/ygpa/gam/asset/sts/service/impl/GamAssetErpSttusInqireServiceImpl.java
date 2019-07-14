package egovframework.rte.ygpa.gam.asset.sts.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.sts.service.GamAssetErpSttusInqireService;
import egovframework.rte.ygpa.gam.asset.sts.service.GamSttusInqireDefaultVO;

/**
 * @Class Name : gamAssetErpSttusInqireServiceImpl.java
 * @Description : 자산정보현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetErpSttusInqireService")

public class GamAssetErpSttusInqireServiceImpl  extends AbstractServiceImpl implements GamAssetErpSttusInqireService {

	@Resource(name="gamAssetErpSttusInqireDao")
    private GamAssetErpSttusInqireDao gamAssetErpSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산정보현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetErpSttusInqireList(GamSttusInqireDefaultVO searchVO) throws Exception {
        return gamAssetErpSttusInqireDao.selectAssetErpSttusInqireList(searchVO);
    }
    

   
}
