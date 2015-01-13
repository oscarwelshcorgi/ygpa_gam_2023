package egovframework.rte.ygpa.gam.oper.train.service.impl;

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
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortNticArrvlDtaInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortNticArrvlDtaInqireVO;

/**
 * @Class Name : GamTrainPortNticArrvlDtaInqireServiceImpl.java
 * @Description : 철송장임대고지도래현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortNticArrvlDtaInqireService")

public class GamTrainPortNticArrvlDtaInqireServiceImpl  extends AbstractServiceImpl implements GamTrainPortNticArrvlDtaInqireService {

	@Resource(name="gamTrainPortNticArrvlDtaInqireDao")
    private GamTrainPortNticArrvlDtaInqireDao gamTrainPortNticArrvlDtaInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 철송장임대고지도래 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortNticArrvlDtaInqireList(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireList(searchVO);
    }

    /**
	 * 철송장임대고지도래 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortNticArrvlDtaInqireListTotCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireSum(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireSum(searchVO);
    }

	/**
	 * 철송장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortNticArrvlDtaInqireDetailList(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception {
        return gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireDetailList(vo);
    }

    /**
	 * 철송장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortNticArrvlDtaInqireDetailListTotCnt(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception {
		return gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamTrainPortNticArrvlDtaInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortNticArrvlDtaInqireLevReqestCnt(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception {
		return gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireLevReqestCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortNticArrvlDtaInqireFileList(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
        return gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortNticArrvlDtaInqireFileListTotCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
		return gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireFileListTotCnt(searchVO);
	}

}
