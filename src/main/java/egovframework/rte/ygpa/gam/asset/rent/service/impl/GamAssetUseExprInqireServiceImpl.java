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
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetUseExprInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetUseExprInqireVO;

/**
 * @Class Name : GamAssetUseExprInqireServiceImpl.java
 * @Description : 자산임대만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetUseExprInqireService")

public class GamAssetUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamAssetUseExprInqireService {

	@Resource(name="gamAssetUseExprInqireDao")
    private GamAssetUseExprInqireDao gamAssetUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetUseExprInqireList(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireList(searchVO);
    }

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetUseExprInqireListTotCnt(GamAssetUseExprInqireVO searchVO) throws Exception {
		return gamAssetUseExprInqireDao.selectAssetUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireSum(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireSum(searchVO);
    }

    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetUseExprInqireFirst(GamAssetUseExprInqireVO vo) throws Exception {
		gamAssetUseExprInqireDao.insertAssetUseExprInqireFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireMaxNo(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireMaxNo(searchVO);
    }

    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetUseExprInqireRenew(GamAssetUseExprInqireVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//자산임대 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamAssetUseExprInqireDao.selectAssetUseExprInqireMaxMngCnt(vo);

		//자산임대 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamAssetUseExprInqireDao.insertAssetUseExprInqireRenew(vo);

		//자산임대상세정보 조회
		List detailList = gamAssetUseExprInqireDao.selectAssetUseExprInqireDetailInfo(vo);

		GamAssetUseExprInqireDetailVO resultVo = null;

		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamAssetUseExprInqireDetailVO();
			resultVo = (GamAssetUseExprInqireDetailVO)detailList.get(i);

			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());

			//자산임대상세 복사등록
			gamAssetUseExprInqireDao.insertAssetUseExprInqireDetailRenew(resultVo);
		}

		GamAssetUseExprInqireVO updRentVO = new GamAssetUseExprInqireVO();

		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamAssetUseExprInqireDao.selectAssetUseExprInqireRenewInfo(vo);

		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);

			gamAssetUseExprInqireDao.updateAssetUseExprInqireRenewInfo(updRentVO);
		}
	}

	/**
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqire(GamAssetUseExprInqireVO vo) throws Exception {
		gamAssetUseExprInqireDao.updateAssetUseExprInqire(vo);
	}

	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetUseExprInqireDetailList(GamAssetUseExprInqireVO vo) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireDetailList(vo);
    }

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetUseExprInqireDetailListTotCnt(GamAssetUseExprInqireVO vo) throws Exception {
		return gamAssetUseExprInqireDao.selectAssetUseExprInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamAssetUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetUseExprInqireLevReqestCnt(GamAssetUseExprInqireVO vo) throws Exception {
		return gamAssetUseExprInqireDao.selectAssetUseExprInqireLevReqestCnt(vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqirePrmisnInfo(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqirePrmisnInfo(searchVO);
    }

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetUseExprInqireFileList(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetUseExprInqireFileListTotCnt(GamAssetUseExprInqireVO searchVO) throws Exception {
		return gamAssetUseExprInqireDao.selectAssetUseExprInqireFileListTotCnt(searchVO);
	}

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireMaxKey(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireComment(GamAssetUseExprInqireVO vo) throws Exception {
		gamAssetUseExprInqireDao.updateAssetUseExprInqireComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireRenewInfo(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireRenewInfo(searchVO);
    }

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireCurrRenewInfo(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireDetailQuaycd(GamAssetUseExprInqireVO searchVO) throws Exception {
        return gamAssetUseExprInqireDao.selectAssetUseExprInqireDetailQuaycd(searchVO);
    }

}
