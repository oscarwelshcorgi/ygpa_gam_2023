package egovframework.rte.ygpa.gam.oper.htld.service.impl;

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
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldUseExprInqireVO;

/**
 * @Class Name : GamHtldUseExprInqireServiceImpl.java
 * @Description : 배후단지임대만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldUseExprInqireService")

public class GamHtldUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamHtldUseExprInqireService {

	@Resource(name="gamHtldUseExprInqireDao")
    private GamHtldUseExprInqireDao gamHtldUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldUseExprInqireList(GamHtldUseExprInqireVO searchVO) throws Exception {
        return gamHtldUseExprInqireDao.selectHtldUseExprInqireList(searchVO);
    }

    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldUseExprInqireListTotCnt(GamHtldUseExprInqireVO searchVO) throws Exception {
		return gamHtldUseExprInqireDao.selectHtldUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldUseExprInqireVO selectHtldUseExprInqireSum(GamHtldUseExprInqireVO searchVO) throws Exception {
        return gamHtldUseExprInqireDao.selectHtldUseExprInqireSum(searchVO);
    }

	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldUseExprInqireDetailList(GamHtldUseExprInqireVO vo) throws Exception {
        return gamHtldUseExprInqireDao.selectHtldUseExprInqireDetailList(vo);
    }

    /**
	 * 배후단지임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldUseExprInqireDetailListTotCnt(GamHtldUseExprInqireVO vo) throws Exception {
		return gamHtldUseExprInqireDao.selectHtldUseExprInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamHtldUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldUseExprInqireLevReqestCnt(GamHtldUseExprInqireVO vo) throws Exception {
		return gamHtldUseExprInqireDao.selectHtldUseExprInqireLevReqestCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldUseExprInqireFileList(GamHtldUseExprInqireVO searchVO) throws Exception {
        return gamHtldUseExprInqireDao.selectHtldUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldUseExprInqireFileListTotCnt(GamHtldUseExprInqireVO searchVO) throws Exception {
		return gamHtldUseExprInqireDao.selectHtldUseExprInqireFileListTotCnt(searchVO);
	}

}
