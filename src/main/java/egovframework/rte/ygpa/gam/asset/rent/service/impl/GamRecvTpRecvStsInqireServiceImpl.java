package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamRecvTpRecvStsInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamRecvTpRecvStsInqireVO;

/**
 * @Class Name : GamRecvTpRecvStsInqireServiceImpl.java
 * @Description : 수입종류별수입현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamRecvTpRecvStsInqireService")

public class GamRecvTpRecvStsInqireServiceImpl  extends AbstractServiceImpl implements GamRecvTpRecvStsInqireService {

	@Resource(name="gamRecvTpRecvStsInqireDao")
    private GamRecvTpRecvStsInqireDao gamRecvTpRecvStsInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 수입종류별수입현황조회 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectRecvTpRecvStsInqireList(GamRecvTpRecvStsInqireVO searchVO) throws Exception {
        return gamRecvTpRecvStsInqireDao.selectRecvTpRecvStsInqireList(searchVO);
    }
    
    /**
	 * 수입종류별수입현황조회 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmpyRecvStsInqireListTotCnt(GamRecvTpRecvStsInqireVO searchVO) throws Exception {
		return gamRecvTpRecvStsInqireDao.selectRecvTpRecvStsInqireListTotCnt(searchVO);
	}

}
