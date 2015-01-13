package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

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
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyNticArrvlDtaInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyNticArrvlDtaInqireVO;

/**
 * @Class Name : GamPrtFcltyNticArrvlDtaInqireServiceImpl.java
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
@Service("gamPrtFcltyNticArrvlDtaInqireService")

public class GamPrtFcltyNticArrvlDtaInqireServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyNticArrvlDtaInqireService {

	@Resource(name="gamPrtFcltyNticArrvlDtaInqireDao")
    private GamPrtFcltyNticArrvlDtaInqireDao gamPrtFcltyNticArrvlDtaInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설고지도래 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireList(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireList(searchVO);
    }

    /**
	 * 항만시설고지도래 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireSum(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireSum(searchVO);
    }

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireDetailList(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
        return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailList(vo);
    }

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
		return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamPrtFcltyNticArrvlDtaInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireLevReqestCnt(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
		return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqirePrmisnInfo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireFileList(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireFileListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireFileListTotCnt(searchVO);
	}

}
