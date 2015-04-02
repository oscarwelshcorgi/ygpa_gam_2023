package egovframework.rte.ygpa.gam.oper.train.service.impl;

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
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtVO;

/**
 * @Class Name : GamTrainPortRentMngtServiceImpl.java
 * @Description : 철송장임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortRentMngtService")
public class GamTrainPortRentMngtServiceImpl extends AbstractServiceImpl implements GamTrainPortRentMngtService {

	@Resource(name="gamTrainPortRentMngtDao")
    private GamTrainPortRentMngtDao gamTrainPortRentMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 철송장임대사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentMngtList(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtList(searchVO);
    }

    /**
	 * 철송장임대사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentMngtListTotCnt(GamTrainPortRentMngtVO searchVO) throws Exception {
		return gamTrainPortRentMngtDao.selectTrainPortRentMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtSum(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtSum(searchVO);
    }

    /**
	 * 철송장임대사용 최초 신청을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtFirst(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.insertTrainPortRentMngtFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtMaxNo(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtMaxNo(searchVO);
    }

    /**
	 * 철송장임대사용 연장 신청을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
    public void insertTrainPortRentMngtRenew(GamTrainPortRentMngtVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//철송장임대사용 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamTrainPortRentMngtDao.selectTrainPortRentMngtMaxMngCnt(vo);

		//철송장임대사용 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamTrainPortRentMngtDao.insertTrainPortRentMngtRenew(vo);

		//철송장임대사용상세정보 조회
		List detailList = gamTrainPortRentMngtDao.selectTrainPortRentMngtDetailInfo(vo);

		GamTrainPortRentMngtDetailVO resultVo = null;

		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamTrainPortRentMngtDetailVO();
			resultVo = (GamTrainPortRentMngtDetailVO)detailList.get(i);

			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());

			//철송장임대사용상세 복사등록
			gamTrainPortRentMngtDao.insertTrainPortRentMngtDetailRenew(resultVo);
		}

		GamTrainPortRentMngtVO updRentVO = new GamTrainPortRentMngtVO();

		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamTrainPortRentMngtDao.selectTrainPortRentMngtRenewInfo(vo);

		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);

			gamTrainPortRentMngtDao.updateTrainPortRentMngtRenewInfo(updRentVO);
		}
	}

	/**
	 * 철송장임대사용정보를 수정한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngt(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.updateTrainPortRentMngt(vo);
	}

	/**
	 * 철송장임대사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentMngtDetailList(GamTrainPortRentMngtVO vo) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtDetailList(vo);
    }

    /**
	 * 철송장임대사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentMngtDetailListTotCnt(GamTrainPortRentMngtVO vo) throws Exception {
		return gamTrainPortRentMngtDao.selectTrainPortRentMngtDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamTrainPortRentMngtDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentMngtLevReqestCnt(GamTrainPortRentMngtVO vo) throws Exception {
		return gamTrainPortRentMngtDao.selectTrainPortRentMngtLevReqestCnt(vo);
	}

    /**
	 * 철송장임대사용 정보를 삭제한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngt(GamTrainPortRentMngtVO vo) throws Exception {

		gamTrainPortRentMngtDao.deleteTrainPortRentMngtPhoto(vo); //철송장임대사용 사진정보 삭제

		gamTrainPortRentMngtDao.deleteTrainPortRentMngtDetail(vo); //철송장임대사용 상세정보 삭제

		gamTrainPortRentMngtDao.deleteTrainPortRentMngt(vo); // 철송장임대사용정보 삭제
	}

	/**
	 * 철송장임대사용 상세정보를 삭제한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngtDetail(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.deleteTrainPortRentMngtDetail(vo);
	}

	/**
	 * 철송장임대사용 상세를 등록한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtDetail(GamTrainPortRentMngtDetailVO vo) throws Exception {
		gamTrainPortRentMngtDao.insertTrainPortRentMngtDetail(vo);
	}

	/**
	 * 철송장임대사용 상세를 수정한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtDetail(GamTrainPortRentMngtDetailVO vo) throws Exception {
		gamTrainPortRentMngtDao.updateTrainPortRentMngtDetail(vo);
	}

	/**
	 * 철송장임대사용 상세를 삭제한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngtDetail2(GamTrainPortRentMngtDetailVO vo) throws Exception {
		gamTrainPortRentMngtDao.deleteTrainPortRentMngtDetail2(vo);
	}

	/**
	 * 승낙할 철송장임대사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용정보
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtPrmisnInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtPrmisnInfo(searchVO);
    }

	/**
	 * 철송장임대사용 허가여부를 취소한다.
	 * @param vo GamTrainPortRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtPrmisnCancel(GamTrainPortRentMngtLevReqestVO vo) throws Exception {
		gamTrainPortRentMngtDao.updateTrainPortRentMngtPrmisnCancel(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentMngtFileList(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentMngtFileListTotCnt(GamTrainPortRentMngtVO searchVO) throws Exception {
		return gamTrainPortRentMngtDao.selectTrainPortRentMngtFileListTotCnt(searchVO);
	}

    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtFile(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.insertTrainPortRentMngtFile(vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtFile(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.updateTrainPortRentMngtFile(vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngtPhotoSingle(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.deleteTrainPortRentMngtPhotoSingle(vo);
	}

	/**
	 * 철송장임대사용 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용 목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtMaxKey(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtComment(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.updateTrainPortRentMngtComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용 목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtRenewInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtRenewInfo(searchVO);
    }

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtRenewInfo(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.updateTrainPortRentMngtRenewInfo(vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용 목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtCurrRenewInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 철송장임대사용상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtDetailQuaycd(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtDetailQuaycd(searchVO);
    }

    /**
	 * 신청저장시 철송장임대사용테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtQuaycd(GamTrainPortRentMngtVO vo) throws Exception {
		gamTrainPortRentMngtDao.updateTrainPortRentMngtQuaycd(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamTrainPortRentMngtDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtBeforeQuarterInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtCofixInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtCofixInfo(searchVO);
    }

    /**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대사용목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtCofixInfoMax(GamTrainPortRentMngtVO searchVO) throws Exception {
        return gamTrainPortRentMngtDao.selectTrainPortRentMngtCofixInfoMax(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtService#selectChargeKndList()
	 */
	@Override
	public List selectChargeKndList(GamTrainPortRentMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentMngtDao.selectChargeKndList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtService#selectTrainPortRentMngtDetailInfo(egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtVO)
	 */
	@Override
	public Map selectTrainPortRentMngtMasterInfo(GamTrainPortRentMngtVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentMngtDao.selectTrainPortRentMngtMasterInfo(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtService#selectRentFeeNoticeListCount(egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtVO)
	 */
	@Override
	public int selectRentFeeNoticeListCount(GamTrainPortRentMngtVO searchVO)
			throws Exception {
		return gamTrainPortRentMngtDao.selectRentFeeNoticeListCount(searchVO);
	}
}