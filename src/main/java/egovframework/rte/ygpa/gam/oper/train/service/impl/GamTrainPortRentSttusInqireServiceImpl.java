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
import egovframework.rte.ygpa.gam.oper.train.service.impl.GamTrainPortRentSttusInqireDao;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireVO;

/**
 * @Class Name : GamTrainPortRentSttusInqireServiceImpl.java
 * @Description : 철송장임대현황조회
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortRentSttusInqireService")
public class GamTrainPortRentSttusInqireServiceImpl extends AbstractServiceImpl implements GamTrainPortRentSttusInqireService {

	@Resource(name="gamTrainPortRentSttusInqireDao")
    private GamTrainPortRentSttusInqireDao gamTrainPortRentSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireList(searchVO);
    }

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireSum(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireSum(searchVO);
    }

	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireDetailList(GamTrainPortRentSttusInqireVO vo) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailList(vo);
    }

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireDetailListTotCnt(GamTrainPortRentSttusInqireVO vo) throws Exception {
		return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectOlnlpInfo() throws Exception {
        return gamTrainPortRentSttusInqireDao.selectOlnlpInfo();
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireLevReqestCnt(GamTrainPortRentSttusInqireVO vo) throws Exception {
		return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireLevReqestCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireFileList(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireFileListTotCnt(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireFileListTotCnt(searchVO);
	}

}
