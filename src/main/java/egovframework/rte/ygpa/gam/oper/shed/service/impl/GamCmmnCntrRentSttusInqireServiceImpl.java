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
import egovframework.rte.ygpa.gam.oper.shed.service.impl.GamCmmnCntrRentSttusInqireDao;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrRentSttusInqireServiceImpl.java
 * @Description : 공컨장치장임대현황조회
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrRentSttusInqireService")
public class GamCmmnCntrRentSttusInqireServiceImpl extends AbstractServiceImpl implements GamCmmnCntrRentSttusInqireService {

	@Resource(name="gamCmmnCntrRentSttusInqireDao")
    private GamCmmnCntrRentSttusInqireDao gamCmmnCntrRentSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireList(searchVO);
    }

    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireSum(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireSum(searchVO);
    }

	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireDetailList(GamCmmnCntrRentSttusInqireVO vo) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailList(vo);
    }

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireDetailListTotCnt(GamCmmnCntrRentSttusInqireVO vo) throws Exception {
		return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectOlnlpInfo() throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectOlnlpInfo();
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireLevReqestCnt(GamCmmnCntrRentSttusInqireVO vo) throws Exception {
		return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireLevReqestCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireFileList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireFileListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireFileListTotCnt(searchVO);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireBeforeQuarterInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireCofixInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireCofixInfo(searchVO);
    }

}
