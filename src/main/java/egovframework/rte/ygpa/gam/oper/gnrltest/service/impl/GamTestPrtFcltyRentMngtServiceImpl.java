package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

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
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtVO;

/**
 * @Class Name : GamTestPrtFcltyRentMngtServiceImpl.java
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
@Service("gamTestPrtFcltyRentMngtService")
public class GamTestPrtFcltyRentMngtServiceImpl extends AbstractServiceImpl implements GamTestPrtFcltyRentMngtService {

	@Resource(name="gamTestPrtFcltyRentMngtDao")
    private GamTestPrtFcltyRentMngtDao gamTestPrtFcltyRentMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtList(searchVO);
    }

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentMngtListTotCnt(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtSum(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtSum(searchVO);
    }

    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFirst(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxNo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxNo(searchVO);
    }

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
    public void insertPrtFcltyRentMngtRenew(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//항만시설사용 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxMngCnt(vo);

		//항만시설사용 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtRenew(vo);

		//항만시설사용상세정보 조회
		List detailList = gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailInfo(vo);

		GamTestPrtFcltyRentMngtDetailVO resultVo = null;

		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamTestPrtFcltyRentMngtDetailVO();
			resultVo = (GamTestPrtFcltyRentMngtDetailVO)detailList.get(i);

			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());

			//항만시설사용상세 복사등록
			gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetailRenew(resultVo);
		}

		GamTestPrtFcltyRentMngtVO updRentVO = new GamTestPrtFcltyRentMngtVO();

		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtRenewInfo(vo);

		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);

			gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtRenewInfo(updRentVO);
		}
	}

	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngt(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngt(vo);
	}

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtDetailList(GamTestPrtFcltyRentMngtVO vo) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailList(vo);
    }

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentMngtDetailListTotCnt(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailListTotCnt(vo);
	}

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentMngtLevReqestCnt(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtLevReqestCnt(vo);
	}

    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngt(GamTestPrtFcltyRentMngtVO vo) throws Exception {

		gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhoto(vo); //항만시설사용 사진정보 삭제

		gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail(vo); //항만시설사용 상세정보 삭제

		gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngt(vo); // 항만시설사용정보 삭제
	}

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtDetailVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtDetailVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail2(GamTestPrtFcltyRentMngtDetailVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail2(vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtPrmisnInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtPrmisnInfo(searchVO);
    }

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamTestPrtFcltyRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtPrmisnCancel(GamTestPrtFcltyRentMngtLevReqestVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtPrmisnCancel(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtFileList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentMngtFileListTotCnt(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtFileListTotCnt(searchVO);
	}

    /**
	 * 파일을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFile(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFile(vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtFile(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtFile(vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtPhotoSingle(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhotoSingle(vo);
	}

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxKey(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtComment(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtRenewInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtRenewInfo(searchVO);
    }

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtRenewInfo(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtRenewInfo(vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCurrRenewInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 항만시설사용상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtDetailQuaycd(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailQuaycd(searchVO);
    }

    /**
	 * 신청저장시 항만시설사용테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtQuaycd(GamTestPrtFcltyRentMngtVO vo) throws Exception {
		gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtQuaycd(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtBeforeQuarterInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCofixInfo(searchVO);
    }

    /**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfoMax(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCofixInfoMax(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentMngtService#selectChargeKndList()
	 */
	@Override
	public List selectChargeKndList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentMngtDao.selectChargeKndList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentMngtService#selectPrtFcltyRentMngtDetailInfo(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentMngtVO)
	 */
	@Override
	public Map selectPrtFcltyRentMngtMasterInfo(GamTestPrtFcltyRentMngtVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMasterInfo(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentMngtService#selectRentFeeNoticeListCount(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentMngtVO)
	 */
	@Override
	public int selectRentFeeNoticeListCount(GamTestPrtFcltyRentMngtVO searchVO)
			throws Exception {
		return gamTestPrtFcltyRentMngtDao.selectRentFeeNoticeListCount(searchVO);
	}
}