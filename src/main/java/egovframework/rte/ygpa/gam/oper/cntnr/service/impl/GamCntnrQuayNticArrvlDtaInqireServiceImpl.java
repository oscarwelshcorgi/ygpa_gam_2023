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
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireVO;

/**
 * @Class Name : GamCntnrQuayNticArrvlDtaInqireServiceImpl.java
 * @Description : 컨테이너부두임대고지도래현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayNticArrvlDtaInqireService")

public class GamCntnrQuayNticArrvlDtaInqireServiceImpl  extends AbstractServiceImpl implements GamCntnrQuayNticArrvlDtaInqireService {

	@Resource(name="gamCntnrQuayNticArrvlDtaInqireDao")
    private GamCntnrQuayNticArrvlDtaInqireDao gamCntnrQuayNticArrvlDtaInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대고지도래 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayNticArrvlDtaInqireList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireList(searchVO);
    }

    /**
	 * 컨테이너부두임대고지도래 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayNticArrvlDtaInqireListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireSum(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireSum(searchVO);
    }

	/**
	 * 컨테이너부두임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayNticArrvlDtaInqireDetailList(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception {
        return gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireDetailList(vo);
    }

    /**
	 * 컨테이너부두임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayNticArrvlDtaInqireDetailListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception {
		return gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamCntnrQuayNticArrvlDtaInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception {
		return gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayNticArrvlDtaInqireFileList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayNticArrvlDtaInqireFileListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireFileListTotCnt(searchVO);
	}
}
