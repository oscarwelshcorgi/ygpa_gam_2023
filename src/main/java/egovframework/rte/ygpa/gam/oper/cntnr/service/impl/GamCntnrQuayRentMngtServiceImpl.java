package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

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
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentMngtService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentMngtVO;

/**
 * @Class Name : GamCntnrQuayRentMngtServiceImpl.java
 * @Description : 컨테이너부두임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayRentMngtService")
public class GamCntnrQuayRentMngtServiceImpl extends AbstractServiceImpl implements GamCntnrQuayRentMngtService {

	@Resource(name="gamCntnrQuayRentMngtDao")
    private GamCntnrQuayRentMngtDao gamCntnrQuayRentMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayRentMngtList(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtList(searchVO);
    }

    /**
	 * 컨테이너부두임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentMngtListTotCnt(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtSum(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtSum(searchVO);
    }

    /**
	 * 컨테이너부두임대 최초 신청을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtFirst(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtMaxNo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtMaxNo(searchVO);
    }

    /**
	 * 컨테이너부두임대 연장 신청을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
    public void insertCntnrQuayRentMngtRenew(GamCntnrQuayRentMngtVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//컨테이너부두임대 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtMaxMngCnt(vo);

		//컨테이너부두임대 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtRenew(vo);

		//컨테이너부두임대상세정보 조회
		List detailList = gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtDetailInfo(vo);

		GamCntnrQuayRentMngtDetailVO resultVo = null;

		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamCntnrQuayRentMngtDetailVO();
			resultVo = (GamCntnrQuayRentMngtDetailVO)detailList.get(i);

			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());

			//컨테이너부두임대상세 복사등록
			gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtDetailRenew(resultVo);
		}

		GamCntnrQuayRentMngtVO updRentVO = new GamCntnrQuayRentMngtVO();

		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtRenewInfo(vo);

		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);

			gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtRenewInfo(updRentVO);
		}
	}

	/**
	 * 컨테이너부두임대정보를 수정한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngt(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngt(vo);
	}

	/**
	 * 컨테이너부두임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayRentMngtDetailList(GamCntnrQuayRentMngtVO vo) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtDetailList(vo);
    }

    /**
	 * 컨테이너부두임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentMngtDetailListTotCnt(GamCntnrQuayRentMngtVO vo) throws Exception {
		return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamCntnrQuayRentMngtDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentMngtLevReqestCnt(GamCntnrQuayRentMngtVO vo) throws Exception {
		return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtLevReqestCnt(vo);
	}

    /**
	 * 컨테이너부두임대 정보를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngt(GamCntnrQuayRentMngtVO vo) throws Exception {

		gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtPhoto(vo); //컨테이너부두임대 사진정보 삭제

		gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtDetail(vo); //컨테이너부두임대 상세정보 삭제

		gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngt(vo); // 컨테이너부두임대정보 삭제
	}

	/**
	 * 컨테이너부두임대 상세정보를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtDetail(vo);
	}

	/**
	 * 컨테이너부두임대 상세를 등록한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtDetailVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtDetail(vo);
	}

	/**
	 * 컨테이너부두임대 상세를 수정한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtDetailVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtDetail(vo);
	}

	/**
	 * 컨테이너부두임대 상세를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngtDetail2(GamCntnrQuayRentMngtDetailVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtDetail2(vo);
	}

	/**
	 * 승낙할 컨테이너부두임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대정보
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtPrmisnInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtPrmisnInfo(searchVO);
    }


	/**
	 * 컨테이너부두임대 허가여부를 취소한다.
	 * @param vo GamCntnrQuayRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtPrmisnCancel(GamCntnrQuayRentMngtLevReqestVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtPrmisnCancel(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayRentMngtFileList(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentMngtFileListTotCnt(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtFileListTotCnt(searchVO);
	}

    /**
	 * 파일을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtFile(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtFile(vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtFile(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtFile(vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngtPhotoSingle(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtPhotoSingle(vo);
	}

	/**
	 * 컨테이너부두임대 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtMaxKey(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtComment(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtRenewInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtRenewInfo(searchVO);
    }

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtRenewInfo(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtRenewInfo(vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtCurrRenewInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 컨테이너부두임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtDetailQuaycd(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtDetailQuaycd(searchVO);
    }

    /**
	 * 신청저장시 컨테이너부두임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtQuaycd(GamCntnrQuayRentMngtVO vo) throws Exception {
		gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtQuaycd(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamCntnrQuayRentMngtDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtBeforeQuarterInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtCofixInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtCofixInfo(searchVO);
    }

    /**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtCofixInfoMax(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtCofixInfoMax(searchVO);
    }

    /* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService#selectChargeKndList()
	 */
	@Override
	public List selectChargeKndList() throws Exception {
		// TODO Auto-generated method stub
		return gamCntnrQuayRentMngtDao.selectChargeKndList();
	}
}