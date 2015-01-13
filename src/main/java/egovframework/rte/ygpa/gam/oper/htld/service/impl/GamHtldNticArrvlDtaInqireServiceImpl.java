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
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldNticArrvlDtaInqireService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldNticArrvlDtaInqireVO;

/**
 * @Class Name : GamHtldNticArrvlDtaInqireServiceImpl.java
 * @Description : 배후단지임대고지도래현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldNticArrvlDtaInqireService")

public class GamHtldNticArrvlDtaInqireServiceImpl  extends AbstractServiceImpl implements GamHtldNticArrvlDtaInqireService {

	@Resource(name="gamHtldNticArrvlDtaInqireDao")
    private GamHtldNticArrvlDtaInqireDao gamHtldNticArrvlDtaInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대고지도래 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldNticArrvlDtaInqireList(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireList(searchVO);
    }

    /**
	 * 배후단지임대고지도래 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldNticArrvlDtaInqireListTotCnt(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireSum(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireSum(searchVO);
    }

	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldNticArrvlDtaInqireDetailList(GamHtldNticArrvlDtaInqireVO vo) throws Exception {
        return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireDetailList(vo);
    }

    /**
	 * 배후단지임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldNticArrvlDtaInqireDetailListTotCnt(GamHtldNticArrvlDtaInqireVO vo) throws Exception {
		return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamHtldNticArrvlDtaInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldNticArrvlDtaInqireLevReqestCnt(GamHtldNticArrvlDtaInqireVO vo) throws Exception {
		return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireLevReqestCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldNticArrvlDtaInqireFileList(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldNticArrvlDtaInqireFileListTotCnt(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireFileListTotCnt(searchVO);
	}

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireMaxKey(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireMaxKey(searchVO);
    }

}
