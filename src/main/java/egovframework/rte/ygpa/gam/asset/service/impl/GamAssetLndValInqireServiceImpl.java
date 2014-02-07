package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.service.GamAssetLndValInqireService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetLndValInqireVO;

/**
 * @Class Name : GamAssetLndValInqireServiceImpl.java
 * @Description : 자산부지공시지가조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetLndValInqireService")

public class GamAssetLndValInqireServiceImpl  extends AbstractServiceImpl implements GamAssetLndValInqireService {

	@Resource(name="gamAssetLndValInqireDao")
    private GamAssetLndValInqireDao gamAssetLndValInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산부지공시지가 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetLndValInqireList(GamAssetLndValInqireVO searchVO) throws Exception {
        return gamAssetLndValInqireDao.selectAssetLndValInqireList(searchVO);
    }
    
    /**
	 * 자산부지공시지가 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetLndValInqireListTotCnt(GamAssetLndValInqireVO searchVO) throws Exception {
		return gamAssetLndValInqireDao.selectAssetLndValInqireListTotCnt(searchVO);
	}

}
