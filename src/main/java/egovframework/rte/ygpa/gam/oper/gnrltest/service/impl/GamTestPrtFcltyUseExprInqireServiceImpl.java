package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

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
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseExprInqireVO;

/**
 * @Class Name : GamTestPrtFcltyUseExprInqireServiceImpl.java
 * @Description : 항만시설사용만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTestPrtFcltyUseExprInqireService")

public class GamTestPrtFcltyUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamTestPrtFcltyUseExprInqireService {

	@Resource(name="gamTestPrtFcltyUseExprInqireDao")
    private GamTestPrtFcltyUseExprInqireDao gamTestPrtFcltyUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireList(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireList(searchVO);
    }

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireListTotCnt(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireSum(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireSum(searchVO);
    }

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireDetailList(GamTestPrtFcltyUseExprInqireVO vo) throws Exception {
        return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailList(vo);
    }

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireDetailListTotCnt(GamTestPrtFcltyUseExprInqireVO vo) throws Exception {
		return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamTestPrtFcltyUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireLevReqestCnt(GamTestPrtFcltyUseExprInqireVO vo) throws Exception {
		return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqirePrmisnInfo(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireFileList(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireFileListTotCnt(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireFileListTotCnt(searchVO);
	}

}
