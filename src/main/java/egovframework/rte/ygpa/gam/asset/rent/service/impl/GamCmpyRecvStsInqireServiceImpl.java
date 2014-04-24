package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamCmpyRecvStsInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamCmpyRecvStsInqireVO;

/**
 * @Class Name : GamCmpyRecvStsInqireServiceImpl.java
 * @Description : 업체별수입현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmpyRecvStsInqireService")

public class GamCmpyRecvStsInqireServiceImpl  extends AbstractServiceImpl implements GamCmpyRecvStsInqireService {

	@Resource(name="gamCmpyRecvStsInqireDao")
    private GamCmpyRecvStsInqireDao gamCmpyRecvStsInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 업체별수입현황조회 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmpyRecvStsInqireList(GamCmpyRecvStsInqireVO searchVO) throws Exception {
        return gamCmpyRecvStsInqireDao.selectCmpyRecvStsInqireList(searchVO);
    }
    
    /**
	 * 업체별수입현황조회 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmpyRecvStsInqireListTotCnt(GamCmpyRecvStsInqireVO searchVO) throws Exception {
		return gamCmpyRecvStsInqireDao.selectCmpyRecvStsInqireListTotCnt(searchVO);
	}

    /**
	 * 업체별수입현황조회 자료수 총금액 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 통계자료 VO
	 * @exception
	 */
	public GamCmpyRecvStsInqireVO selectCmpyRecvStsInqireSum(GamCmpyRecvStsInqireVO searchVO) throws Exception {
		return gamCmpyRecvStsInqireDao.selectCmpyRecvStsInqireSum(searchVO);
	}

}
