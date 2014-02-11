package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireVO;

/**
 * @Class Name : GamAssetSttusInqireServiceImpl.java
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
@Service("gamAssetSttusInqireService")

public class GamAssetSttusInqireServiceImpl  extends AbstractServiceImpl implements GamAssetSttusInqireService {

	@Resource(name="gamAssetSttusInqireDao")
    private GamAssetSttusInqireDao gamAssetSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산정보현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetSttusInqireList(GamAssetSttusInqireVO searchVO) throws Exception {
        return gamAssetSttusInqireDao.selectAssetSttusInqireList(searchVO);
    }
    
    /**
	 * 자산정보현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetDisUseListTotCnt(GamAssetSttusInqireVO searchVO) throws Exception {
		return gamAssetSttusInqireDao.selectAssetSttusInqireListTotCnt(searchVO);
	}

}
