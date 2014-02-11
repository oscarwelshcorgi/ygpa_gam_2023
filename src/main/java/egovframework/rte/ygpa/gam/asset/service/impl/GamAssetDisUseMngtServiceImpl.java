package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtVO;

/**
 * @Class Name : GamAssetDisUseMngtServiceImpl.java
 * @Description : 자산폐기등록 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetDisUseMngtService")

public class GamAssetDisUseMngtServiceImpl  extends AbstractServiceImpl implements GamAssetDisUseMngtService {

	@Resource(name="gamAssetDisUseMngtDao")
    private GamAssetDisUseMngtDao gamAssetDisUseMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * GIS자산코드 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetDisUseList(GamAssetDisUseMngtVO searchVO) throws Exception {
        return gamAssetDisUseMngtDao.selectAssetDisUseList(searchVO);
    }
    
    /**
	 * GIS자산코드 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetDisUseListTotCnt(GamAssetDisUseMngtVO searchVO) throws Exception {
		return gamAssetDisUseMngtDao.selectAssetDisUseListTotCnt(searchVO);
	}

    /**
	 * ERP 자산 폐기 정보를 수정한다.
	 * @param vo GamAssetDisUseMngtVO
	 * @exception Exception
	 */
	public void updateAssetDisUse(GamAssetDisUseMngtVO vo) throws Exception {
		gamAssetDisUseMngtDao.updateAssetDisUse(vo);
	}

}
