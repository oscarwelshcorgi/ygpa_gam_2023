package egovframework.rte.ygpa.gam.oper.shed.service.impl;

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
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireVO;

/**
 * @Class Name : GamCmmnCntrUseExprInqireServiceImpl.java
 * @Description : 공컨장치장임대만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrUseExprInqireService")

public class GamCmmnCntrUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamCmmnCntrUseExprInqireService {

	@Resource(name="gamCmmnCntrUseExprInqireDao")
    private GamCmmnCntrUseExprInqireDao gamCmmnCntrUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrUseExprInqireList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
        return gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireList(searchVO);
    }

    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrUseExprInqireListTotCnt(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		return gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireSum(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
        return gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireSum(searchVO);
    }

	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrUseExprInqireDetailList(GamCmmnCntrUseExprInqireVO vo) throws Exception {
        return gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireDetailList(vo);
    }

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrUseExprInqireDetailListTotCnt(GamCmmnCntrUseExprInqireVO vo) throws Exception {
		return gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamCmmnCntrUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrUseExprInqireLevReqestCnt(GamCmmnCntrUseExprInqireVO vo) throws Exception {
		return gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireLevReqestCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrUseExprInqireFileList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
        return gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrUseExprInqireFileListTotCnt(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		return gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireFileListTotCnt(searchVO);
	}

}
