package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

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
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireVO;

/**
 * @Class Name : GamCntnrQuayUseExprInqireServiceImpl.java
 * @Description : 컨테이너부두임대만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayUseExprInqireService")

public class GamCntnrQuayUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamCntnrQuayUseExprInqireService {

	@Resource(name="gamCntnrQuayUseExprInqireDao")
    private GamCntnrQuayUseExprInqireDao gamCntnrQuayUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireList(searchVO);
    }

    /**
	 * 컨테이너부두임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireListTotCnt(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireSum(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireSum(searchVO);
    }

	/**
	 * 컨테이너부두임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireDetailList(GamCntnrQuayUseExprInqireVO vo) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailList(vo);
    }

    /**
	 * 컨테이너부두임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireDetailListTotCnt(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamCntnrQuayUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireLevReqestCnt(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireLevReqestCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireFileList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireFileListTotCnt(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireFileListTotCnt(searchVO);
	}

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireMaxKey(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireComment(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireComment(vo);
	}

}
