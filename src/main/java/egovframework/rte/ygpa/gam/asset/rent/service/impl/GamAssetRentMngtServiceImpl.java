package egovframework.rte.ygpa.gam.asset.rent.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;


import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 * @Class Name : GamAssetRentMngtServiceImpl.java
 * @Description : 항만시설사용목록관리 Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */ 
@Service("gamAssetRentMngtService")
public class GamAssetRentMngtServiceImpl extends AbstractServiceImpl implements GamAssetRentMngtService {

	@Resource(name="gamAssetRentMngtDao")
    private GamAssetRentMngtDao gamAssetRentMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentMngtList(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtList(searchVO);
    }

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentMngtListTotCnt(GamAssetRentMngtVO searchVO) throws Exception {
		return gamAssetRentMngtDao.selectAssetRentMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtSum(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtSum(searchVO);
    }

    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtFirst(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.insertAssetRentMngtFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtMaxNo(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtMaxNo(searchVO);
    }

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
    public void insertAssetRentMngtRenew(GamAssetRentMngtVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//항만시설사용 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamAssetRentMngtDao.selectAssetRentMngtMaxMngCnt(vo);

		//항만시설사용 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamAssetRentMngtDao.insertAssetRentMngtRenew(vo);

		//항만시설사용상세정보 조회
		List detailList = gamAssetRentMngtDao.selectAssetRentMngtDetailInfo(vo);

		GamAssetRentDetailVO resultVo = null;

		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamAssetRentDetailVO();
			resultVo = (GamAssetRentDetailVO)detailList.get(i);

			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());

			//항만시설사용상세 복사등록
			gamAssetRentMngtDao.insertAssetRentMngtDetailRenew(resultVo);
		}

		GamAssetRentMngtVO updRentVO = new GamAssetRentMngtVO();

		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamAssetRentMngtDao.selectAssetRentMngtRenewInfo(vo);

		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);

			gamAssetRentMngtDao.updateAssetRentMngtRenewInfo(updRentVO);
		}
	}

	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentMngt(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.updateAssetRentMngt(vo);
	}

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentMngtDetailList(GamAssetRentMngtVO vo) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtDetailList(vo);
    }

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentMngtDetailListTotCnt(GamAssetRentMngtVO vo) throws Exception {
		return gamAssetRentMngtDao.selectAssetRentMngtDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectOlnlpInfo(GamGisAssetCodeVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectOlnlpInfo(searchVO);
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentMngtLevReqestCnt(GamAssetRentMngtVO vo) throws Exception {
		return gamAssetRentMngtDao.selectAssetRentMngtLevReqestCnt(vo);
	}

    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngt(GamAssetRentMngtVO vo) throws Exception {

		gamAssetRentMngtDao.deleteAssetRentMngtPhoto(vo); //항만시설사용 사진정보 삭제

		gamAssetRentMngtDao.deleteAssetRentMngtDetail(vo); //항만시설사용 상세정보 삭제

		gamAssetRentMngtDao.deleteAssetRentMngt(vo); // 항만시설사용정보 삭제
	}

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngtDetail(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.deleteAssetRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtDetail(GamAssetRentDetailVO vo) throws Exception {
		gamAssetRentMngtDao.insertAssetRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtDetail(GamAssetRentDetailVO vo) throws Exception {
		gamAssetRentMngtDao.updateAssetRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngtDetail2(GamAssetRentDetailVO vo) throws Exception {
		gamAssetRentMngtDao.deleteAssetRentMngtDetail2(vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtPrmisnInfo(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtPrmisnInfo(searchVO);
    }

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamAssetRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtPrmisnCancel(GamAssetRentLevReqestVO vo) throws Exception {
		gamAssetRentMngtDao.updateAssetRentMngtPrmisnCancel(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentMngtFileList(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentMngtFileListTotCnt(GamAssetRentMngtVO searchVO) throws Exception {
		return gamAssetRentMngtDao.selectAssetRentMngtFileListTotCnt(searchVO);
	}

    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtFile(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.insertAssetRentMngtFile(vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtFile(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.updateAssetRentMngtFile(vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngtPhotoSingle(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.deleteAssetRentMngtPhotoSingle(vo);
	}

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtMaxKey(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtComment(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.updateAssetRentMngtComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtRenewInfo(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtRenewInfo(searchVO);
    }

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtRenewInfo(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.updateAssetRentMngtRenewInfo(vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtCurrRenewInfo(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 항만시설사용상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtDetailQuaycd(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtDetailQuaycd(searchVO);
    }

    /**
	 * 신청저장시 항만시설사용테이블의 부두코드를 업데이트 한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtQuaycd(GamAssetRentMngtVO vo) throws Exception {
		gamAssetRentMngtDao.updateAssetRentMngtQuaycd(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamAssetRentMngtDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtBeforeQuarterInfo(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtCofixInfo(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtCofixInfo(searchVO);
    }

    /**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtCofixInfoMax(GamAssetRentMngtVO searchVO) throws Exception {
        return gamAssetRentMngtDao.selectAssetRentMngtCofixInfoMax(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtService#selectChargeKndList()
	 */
	@Override
	public List selectChargeKndList(GamAssetRentMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamAssetRentMngtDao.selectChargeKndList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtService#selectAssetRentMngtDetailInfo(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO)
	 */
	@Override
	public Map selectAssetRentMngtMasterInfo(GamAssetRentMngtVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return gamAssetRentMngtDao.selectAssetRentMngtMasterInfo(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtService#selectRentFeeNoticeListCount(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO)
	 */
	@Override
	public int selectRentFeeNoticeListCount(GamAssetRentMngtVO searchVO)
			throws Exception {
		return gamAssetRentMngtDao.selectRentFeeNoticeListCount(searchVO);
	}
}