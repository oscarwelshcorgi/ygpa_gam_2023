package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentSttusInqireDao;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentSttusInqireVO;

/**
 * @Class Name : GamHtldRentSttusInqireServiceImpl.java
 * @Description : 배후단지임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldRentSttusInqireService")
public class GamHtldRentSttusInqireServiceImpl extends AbstractServiceImpl implements GamHtldRentSttusInqireService {

    @Resource(name="gamHtldRentSttusInqireDao")
    private GamHtldRentSttusInqireDao gamHtldRentSttusInqireDao;

    /**
	 * 배후단지임대현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentSttusInqireList(GamHtldRentSttusInqireVO searchVO) throws Exception {
        return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireList(searchVO);
    }

    /**
	 * 배후단지임대현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentSttusInqireListTotCnt(GamHtldRentSttusInqireVO searchVO) throws Exception {
		return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamHtldRentSttusInqireVO selectHtldRentSttusInqireSum(GamHtldRentSttusInqireVO searchVO) throws Exception {
        return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireSum(searchVO);
    }

    /**
	 * 배후단지임대현황 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentSttusInqireDetailList(GamHtldRentSttusInqireVO vo) throws Exception {
        return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireDetailList(vo);
    }

    /**
	 * 배후단지임대현황 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentSttusInqireDetailListTotCnt(GamHtldRentSttusInqireVO vo) throws Exception {
		return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireDetailListTotCnt(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentSttusInqireFileList(GamHtldRentSttusInqireVO searchVO) throws Exception {
        return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentSttusInqireFileListTotCnt(GamHtldRentSttusInqireVO searchVO) throws Exception {
		return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireFileListTotCnt(searchVO);
	}
    
    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectOlnlpInfo() throws Exception {
        return gamHtldRentSttusInqireDao.selectOlnlpInfo();
    }
    
    
    /**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamHtldRentSttusInqireDao.selectCofixInfo();
    }
    
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamHtldRentSttusInqireVO selectHtldRentSttusInqireBeforeQuarterInfo(GamHtldRentSttusInqireVO searchVO) throws Exception {
        return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireBeforeQuarterInfo(searchVO);
    }
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamHtldRentSttusInqireVO selectHtldRentSttusInqireCofixInfo(GamHtldRentSttusInqireVO searchVO) throws Exception {
        return gamHtldRentSttusInqireDao.selectHtldRentSttusInqireCofixInfo(searchVO);
    }
}
