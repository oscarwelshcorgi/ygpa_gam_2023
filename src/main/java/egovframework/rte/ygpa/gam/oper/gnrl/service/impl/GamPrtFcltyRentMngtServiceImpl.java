package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;

/**
 * @Class Name : GamPrtFcltyRentMngtServiceImpl.java
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
@Service("gamPrtFcltyRentMngtService")
public class GamPrtFcltyRentMngtServiceImpl extends AbstractServiceImpl implements GamPrtFcltyRentMngtService {

	@Resource(name="gamPrtFcltyRentMngtDao")
    private GamPrtFcltyRentMngtDao gamPrtFcltyRentMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtList(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtList(searchVO);
    }

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentMngtListTotCnt(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtSum(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtSum(searchVO);
    }

    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFirst(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxNo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxNo(searchVO);
    }

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
    public void insertPrtFcltyRentMngtRenew(GamPrtFcltyRentMngtVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//항만시설사용 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxMngCnt(vo);

		//항만시설사용 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtRenew(vo);

		//항만시설사용상세정보 조회
		List detailList = gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailInfo(vo);

		GamPrtFcltyRentMngtDetailVO resultVo = null;

		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamPrtFcltyRentMngtDetailVO();
			resultVo = (GamPrtFcltyRentMngtDetailVO)detailList.get(i);

			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());

			//항만시설사용상세 복사등록
			gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetailRenew(resultVo);
		}

		GamPrtFcltyRentMngtVO updRentVO = new GamPrtFcltyRentMngtVO();

		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtRenewInfo(vo);

		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);

			gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtRenewInfo(updRentVO);
		}
	}

	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngt(vo);
	}

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtDetailList(GamPrtFcltyRentMngtVO vo) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailList(vo);
    }

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentMngtDetailListTotCnt(GamPrtFcltyRentMngtVO vo) throws Exception {
		return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailListTotCnt(vo);
	}

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentMngtLevReqestCnt(GamPrtFcltyRentMngtVO vo) throws Exception {
		return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtLevReqestCnt(vo);
	}

    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo) throws Exception {

		gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhoto(vo); //항만시설사용 사진정보 삭제

		gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail(vo); //항만시설사용 상세정보 삭제

		gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngt(vo); // 항만시설사용정보 삭제
	}

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail2(GamPrtFcltyRentMngtDetailVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail2(vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtPrmisnInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtPrmisnInfo(searchVO);
    }

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtPrmisnCancel(GamPrtFcltyRentMngtLevReqestVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtPrmisnCancel(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtFileList(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentMngtFileListTotCnt(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtFileListTotCnt(searchVO);
	}

    /**
	 * 파일을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFile(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFile(vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtFile(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtFile(vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtPhotoSingle(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhotoSingle(vo);
	}

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxKey(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtComment(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtRenewInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtRenewInfo(searchVO);
    }

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtRenewInfo(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtRenewInfo(vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCurrRenewInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 항만시설사용상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtDetailQuaycd(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailQuaycd(searchVO);
    }

    /**
	 * 신청저장시 항만시설사용테이블의 부두코드를 업데이트 한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtQuaycd(GamPrtFcltyRentMngtVO vo) throws Exception {
		gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtQuaycd(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamPrtFcltyRentMngtDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtBeforeQuarterInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCofixInfo(searchVO);
    }

    /**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfoMax(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCofixInfoMax(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService#selectChargeKndList()
	 */
	@Override
	public List selectChargeKndList(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamPrtFcltyRentMngtDao.selectChargeKndList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService#selectPrtFcltyRentMngtDetailInfo(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO)
	 */
	@Override
	public Map selectPrtFcltyRentMngtMasterInfo(GamPrtFcltyRentMngtVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMasterInfo(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService#selectRentFeeNoticeListCount(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO)
	 */
	@Override
	public int selectRentFeeNoticeListCount(GamPrtFcltyRentMngtVO searchVO)
			throws Exception {
		return gamPrtFcltyRentMngtDao.selectRentFeeNoticeListCount(searchVO);
	}
}