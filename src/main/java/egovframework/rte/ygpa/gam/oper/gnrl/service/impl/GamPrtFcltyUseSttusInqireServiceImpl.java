package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyUseSttusInqireServiceImpl.java
 * @Description : 항만시설사용현황조회 Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamPrtFcltyUseSttusInqireService")
public class GamPrtFcltyUseSttusInqireServiceImpl extends AbstractServiceImpl implements GamPrtFcltyUseSttusInqireService {

    @Resource(name="gamPrtFcltyUseSttusInqireDao")
    private GamPrtFcltyUseSttusInqireDao gamPrtFcltyUseSttusInqireDao;

    /**
	 * 항만시설사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireList(searchVO);
    }

    /**
	 * 항만시설사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireListTotCnt(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireSum(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireSum(searchVO);
    }

    /**
	 * 항만시설사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireDetailList(GamPrtFcltyUseSttusInqireVO vo) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailList(vo);
    }

    /**
	 * 항만시설사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireDetailListTotCnt(GamPrtFcltyUseSttusInqireVO vo) throws Exception {
		return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailListTotCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireFileList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireFileListTotCnt(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireFileListTotCnt(searchVO);
	}
    
    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectOlnlpInfo() throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectOlnlpInfo();
    }
    
    
    /**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectCofixInfo();
    }
    
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireBeforeQuarterInfo(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireBeforeQuarterInfo(searchVO);
    }
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireCofixInfo(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireCofixInfo(searchVO);
    }
    
}
