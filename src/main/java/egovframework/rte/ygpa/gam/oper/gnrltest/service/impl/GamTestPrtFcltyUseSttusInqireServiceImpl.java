package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamTestPrtFcltyUseSttusInqireServiceImpl.java
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
@Service("gamTestPrtFcltyUseSttusInqireService")
public class GamTestPrtFcltyUseSttusInqireServiceImpl extends AbstractServiceImpl implements GamTestPrtFcltyUseSttusInqireService {

    @Resource(name="gamTestPrtFcltyUseSttusInqireDao")
    private GamTestPrtFcltyUseSttusInqireDao gamTestPrtFcltyUseSttusInqireDao;

    /**
	 * 항만시설사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireList(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireList(searchVO);
    }

    /**
	 * 항만시설사용현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireListTotCnt(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamTestPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireSum(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireSum(searchVO);
    }

    /**
	 * 항만시설사용현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireDetailList(GamTestPrtFcltyUseSttusInqireVO vo) throws Exception {
        return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailList(vo);
    }

    /**
	 * 항만시설사용현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireDetailListTotCnt(GamTestPrtFcltyUseSttusInqireVO vo) throws Exception {
		return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailListTotCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireFileList(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireFileListTotCnt(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireFileListTotCnt(searchVO);
	}
    
    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectOlnlpInfo() throws Exception {
        return gamTestPrtFcltyUseSttusInqireDao.selectOlnlpInfo();
    }
    
    
    /**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamTestPrtFcltyUseSttusInqireDao.selectCofixInfo();
    }
    
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireBeforeQuarterInfo(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireBeforeQuarterInfo(searchVO);
    }
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireCofixInfo(GamTestPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireCofixInfo(searchVO);
    }
    
}
