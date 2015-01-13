package egovframework.rte.ygpa.gam.asset.rent.service.impl;

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
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireVO;

/**
 * @Class Name : GamAssetRentSttusInqireServiceImpl.java
 * @Description : 자산임대현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentSttusInqireService")

public class GamAssetRentSttusInqireServiceImpl  extends AbstractServiceImpl implements GamAssetRentSttusInqireService {

	@Resource(name="gamAssetRentSttusInqireDao")
    private GamAssetRentSttusInqireDao gamAssetRentSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentSttusInqireList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireList(searchVO);
    }

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentSttusInqireListTotCnt(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireSum(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireSum(searchVO);
    }

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireMaxNo(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireMaxNo(searchVO);
    }

	/**
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqire(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.updateAssetRentSttusInqire(vo);
	}

	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentSttusInqireDetailList(GamAssetRentSttusInqireVO vo) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireDetailList(vo);
    }

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentSttusInqireDetailListTotCnt(GamAssetRentSttusInqireVO vo) throws Exception {
		return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectOlnlpInfo() throws Exception {
        return gamAssetRentSttusInqireDao.selectOlnlpInfo();
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentSttusInqireLevReqestCnt(GamAssetRentSttusInqireVO vo) throws Exception {
		return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqirePrmisnInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentSttusInqireFileList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentSttusInqireFileListTotCnt(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireFileListTotCnt(searchVO);
	}

    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertAssetRentSttusInqireFile(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.insertAssetRentSttusInqireFile(vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireFile(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.updateAssetRentSttusInqireFile(vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteAssetRentSttusInqirePhotoSingle(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.deleteAssetRentSttusInqirePhotoSingle(vo);
	}

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireMaxKey(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireComment(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.updateAssetRentSttusInqireComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireRenewInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireRenewInfo(searchVO);
    }

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireRenewInfo(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.updateAssetRentSttusInqireRenewInfo(vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireCurrRenewInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireDetailQuaycd(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireDetailQuaycd(searchVO);
    }

    /**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireQuaycd(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.updateAssetRentSttusInqireQuaycd(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamAssetRentSttusInqireDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireBeforeQuarterInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireCofixInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSttusInqireCofixInfo(searchVO);
    }

}
