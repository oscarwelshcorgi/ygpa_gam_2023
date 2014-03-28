package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.shed.service.impl.GamCmmnCntrRentSttusInqireDao;
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

    /**
	 * 공컨장치장임대현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireList(searchVO);
    }

    /**
	 * 공컨장치장임대현황 목록 총 갯수를 조회한다.
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
	 * @return vo
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireSum(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireSum(searchVO);
    }

    /**
	 * 공컨장치장임대현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireDetailList(GamCmmnCntrRentSttusInqireVO vo) throws Exception {
        return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailList(vo);
    }

    /**
	 * 공컨장치장임대현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireDetailListTotCnt(GamCmmnCntrRentSttusInqireVO vo) throws Exception {
		return gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailListTotCnt(vo);
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
}
