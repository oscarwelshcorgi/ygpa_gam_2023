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
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireVO;

/**
 * @Class Name : GamMarineCenterRentSttusInqireServiceImpl.java
 * @Description : 마린센터임대현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterRentSttusInqireService")

public class GamMarineCenterRentSttusInqireServiceImpl  extends AbstractServiceImpl implements GamMarineCenterRentSttusInqireService {

	@Resource(name="gamMarineCenterRentSttusInqireDao")
    private GamMarineCenterRentSttusInqireDao gamMarineCenterRentSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterRentSttusInqireList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireList(searchVO);
    }

    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterRentSttusInqireListTotCnt(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireSum(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireSum(searchVO);
    }

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireMaxNo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireMaxNo(searchVO);
    }

	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterRentSttusInqireDetailList(GamMarineCenterRentSttusInqireVO vo) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireDetailList(vo);
    }

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterRentSttusInqireDetailListTotCnt(GamMarineCenterRentSttusInqireVO vo) throws Exception {
		return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectOlnlpInfo() throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectOlnlpInfo();
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterRentSttusInqireLevReqestCnt(GamMarineCenterRentSttusInqireVO vo) throws Exception {
		return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqirePrmisnInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterRentSttusInqireFileList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterRentSttusInqireFileListTotCnt(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireFileListTotCnt(searchVO);
	}

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireMaxKey(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireMaxKey(searchVO);
    }

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireRenewInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireRenewInfo(searchVO);
    }

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireCurrRenewInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireDetailQuaycd(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireDetailQuaycd(searchVO);
    }

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireBeforeQuarterInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireCofixInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireCofixInfo(searchVO);
    }

}
