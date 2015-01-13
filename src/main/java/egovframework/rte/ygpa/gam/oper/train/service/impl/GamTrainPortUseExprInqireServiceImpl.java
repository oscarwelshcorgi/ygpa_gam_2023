package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortUseExprInqireVO;

/**
 * @Class Name : GamTrainPortUseExprInqireServiceImpl.java
 * @Description : 철송장임대만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortUseExprInqireService")

public class GamTrainPortUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamTrainPortUseExprInqireService {

	@Resource(name="gamTrainPortUseExprInqireDao")
    private GamTrainPortUseExprInqireDao gamTrainPortUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 철송장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortUseExprInqireList(GamTrainPortUseExprInqireVO searchVO) throws Exception {
        return gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireList(searchVO);
    }

    /**
	 * 철송장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortUseExprInqireListTotCnt(GamTrainPortUseExprInqireVO searchVO) throws Exception {
		return gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireSum(GamTrainPortUseExprInqireVO searchVO) throws Exception {
        return gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireSum(searchVO);
    }

	/**
	 * 철송장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortUseExprInqireDetailList(GamTrainPortUseExprInqireVO vo) throws Exception {
        return gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireDetailList(vo);
    }

    /**
	 * 철송장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortUseExprInqireDetailListTotCnt(GamTrainPortUseExprInqireVO vo) throws Exception {
		return gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireDetailListTotCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortUseExprInqireFileList(GamTrainPortUseExprInqireVO searchVO) throws Exception {
        return gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortUseExprInqireFileListTotCnt(GamTrainPortUseExprInqireVO searchVO) throws Exception {
		return gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireFileListTotCnt(searchVO);
	}

}
