package egovframework.rte.ygpa.gam.asset.sts.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.sts.service.GamAssetSeSttusInqireService;
import egovframework.rte.ygpa.gam.asset.sts.service.GamSttusInqireDefaultVO;

/**
 * @Class Name : gamAssetSeSttusInqireServiceImpl.java
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
@Service("gamAssetSeSttusInqireService")

public class GamAssetSeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamAssetSeSttusInqireService {

	@Resource(name="gamAssetSeSttusInqireDao")
    private GamAssetSeSttusInqireDao gamAssetSeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산정보현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetSeSttusInqireList(GamSttusInqireDefaultVO searchVO) throws Exception {
        return gamAssetSeSttusInqireDao.selectAssetSeSttusInqireList(searchVO);
    }
    
    public List selectAssetSeSttusInqireList1(GamSttusInqireDefaultVO searchVO) throws Exception {
    	return gamAssetSeSttusInqireDao.selectAssetSeSttusInqireList1(searchVO);
    }

    public List selectAssetSeSttusInqireList2(GamSttusInqireDefaultVO searchVO) throws Exception {
    	return gamAssetSeSttusInqireDao.selectAssetSeSttusInqireList2(searchVO);
    }
   
}
