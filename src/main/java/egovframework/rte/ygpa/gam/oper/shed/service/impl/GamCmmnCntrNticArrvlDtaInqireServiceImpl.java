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
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrNticArrvlDtaInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrNticArrvlDtaInqireVO;

/**
 * @Class Name : GamCmmnCntrNticArrvlDtaInqireServiceImpl.java
 * @Description : 공컨장치장임대고지도래현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrNticArrvlDtaInqireService")

public class GamCmmnCntrNticArrvlDtaInqireServiceImpl  extends AbstractServiceImpl implements GamCmmnCntrNticArrvlDtaInqireService {

	@Resource(name="gamCmmnCntrNticArrvlDtaInqireDao")
    private GamCmmnCntrNticArrvlDtaInqireDao gamCmmnCntrNticArrvlDtaInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대고지도래 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrNticArrvlDtaInqireList(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireList(searchVO);
    }

    /**
	 * 공컨장치장임대고지도래 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrNticArrvlDtaInqireListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireSum(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireSum(searchVO);
    }

	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrNticArrvlDtaInqireDetailList(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception {
        return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireDetailList(vo);
    }

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrNticArrvlDtaInqireDetailListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception {
		return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamCmmnCntrNticArrvlDtaInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrNticArrvlDtaInqireLevReqestCnt(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception {
		return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 공컨장치장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대정보
	 * @exception Exception
	 */
    public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqirePrmisnInfo(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrNticArrvlDtaInqireFileList(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrNticArrvlDtaInqireFileListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireFileListTotCnt(searchVO);
	}

}
