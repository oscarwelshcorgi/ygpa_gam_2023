package egovframework.rte.ygpa.gam.oper.center.service.impl;

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
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireVO;

/**
 * @Class Name : GamMarineCenterUseExprInqireServiceImpl.java
 * @Description : 마린센터임대만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterUseExprInqireService")

public class GamMarineCenterUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamMarineCenterUseExprInqireService {

	@Resource(name="gamMarineCenterUseExprInqireDao")
    private GamMarineCenterUseExprInqireDao gamMarineCenterUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireList(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireList(searchVO);
    }

    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireListTotCnt(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireSum(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireSum(searchVO);
    }

	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireDetailList(GamMarineCenterUseExprInqireVO vo) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailList(vo);
    }

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireDetailListTotCnt(GamMarineCenterUseExprInqireVO vo) throws Exception {
		return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamMarineCenterUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireLevReqestCnt(GamMarineCenterUseExprInqireVO vo) throws Exception {
		return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqirePrmisnInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireFileList(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireFileListTotCnt(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireFileListTotCnt(searchVO);
	}

}
