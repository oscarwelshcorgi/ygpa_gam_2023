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
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireVO;

/**
 * @Class Name : GamMarineCenterFeeExprInqireServiceImpl.java
 * @Description : 마린센터임대고지도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterFeeExprInqireService")

public class GamMarineCenterFeeExprInqireServiceImpl  extends AbstractServiceImpl implements GamMarineCenterFeeExprInqireService {

	@Resource(name="gamMarineCenterFeeExprInqireDao")
    private GamMarineCenterFeeExprInqireDao gamMarineCenterFeeExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterFeeExprInqireList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireList(searchVO);
    }

    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterFeeExprInqireListTotCnt(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
		return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireSum(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireSum(searchVO);
    }

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireMaxNo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireMaxNo(searchVO);
    }

	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterFeeExprInqireDetailList(GamMarineCenterFeeExprInqireVO vo) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireDetailList(vo);
    }

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterFeeExprInqireDetailListTotCnt(GamMarineCenterFeeExprInqireVO vo) throws Exception {
		return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamMarineCenterFeeExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterFeeExprInqireLevReqestCnt(GamMarineCenterFeeExprInqireVO vo) throws Exception {
		return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqirePrmisnInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterFeeExprInqireFileList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterFeeExprInqireFileListTotCnt(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
		return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireFileListTotCnt(searchVO);
	}

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireMaxKey(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireComment(GamMarineCenterFeeExprInqireVO vo) throws Exception {
		gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqireComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireRenewInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireRenewInfo(searchVO);
    }

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireCurrRenewInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireDetailQuaycd(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireDetailQuaycd(searchVO);
    }

}
