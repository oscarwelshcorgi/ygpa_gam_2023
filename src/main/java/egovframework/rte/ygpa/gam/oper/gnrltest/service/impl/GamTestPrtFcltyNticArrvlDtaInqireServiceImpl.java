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
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyNticArrvlDtaInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyNticArrvlDtaInqireVO;

/**
 * @Class Name : GamTestPrtFcltyNticArrvlDtaInqireServiceImpl.java
 * @Description : 항만시설고지도래현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTestPrtFcltyNticArrvlDtaInqireService")

public class GamTestPrtFcltyNticArrvlDtaInqireServiceImpl  extends AbstractServiceImpl implements GamTestPrtFcltyNticArrvlDtaInqireService {

	@Resource(name="gamTestPrtFcltyNticArrvlDtaInqireDao")
    private GamTestPrtFcltyNticArrvlDtaInqireDao gamTestPrtFcltyNticArrvlDtaInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설고지도래 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireList(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireList(searchVO);
    }

    /**
	 * 항만시설고지도래 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireListTotCnt(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireSum(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireSum(searchVO);
    }

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireDetailList(GamTestPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
        return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailList(vo);
    }

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt(GamTestPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
		return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamTestPrtFcltyNticArrvlDtaInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireLevReqestCnt(GamTestPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
		return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqirePrmisnInfo(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireFileList(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireFileListTotCnt(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireFileListTotCnt(searchVO);
	}

}
