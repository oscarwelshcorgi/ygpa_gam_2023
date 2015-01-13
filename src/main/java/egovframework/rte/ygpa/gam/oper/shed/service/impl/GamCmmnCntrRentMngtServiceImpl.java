package egovframework.rte.ygpa.gam.oper.shed.service.impl;

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
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtVO;

/**
 * @Class Name : GamCmmnCntrRentMngtServiceImpl.java
 * @Description : 공컨장치장임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrRentMngtService")
public class GamCmmnCntrRentMngtServiceImpl extends AbstractServiceImpl implements GamCmmnCntrRentMngtService {

	@Resource(name="gamCmmnCntrRentMngtDao")
    private GamCmmnCntrRentMngtDao gamCmmnCntrRentMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentMngtList(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtList(searchVO);
    }

    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentMngtListTotCnt(GamCmmnCntrRentMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtSum(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtSum(searchVO);
    }

    /**
	 * 공컨장치장임대 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentMngtFirst(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtMaxNo(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtMaxNo(searchVO);
    }

    /**
	 * 공컨장치장임대 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
    public void insertCmmnCntrRentMngtRenew(GamCmmnCntrRentMngtVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공컨장치장임대 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtMaxMngCnt(vo);

		//공컨장치장임대 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtRenew(vo);

		//공컨장치장임대상세정보 조회
		List detailList = gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtDetailInfo(vo);

		GamCmmnCntrRentMngtDetailVO resultVo = null;

		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamCmmnCntrRentMngtDetailVO();
			resultVo = (GamCmmnCntrRentMngtDetailVO)detailList.get(i);

			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());

			//공컨장치장임대상세 복사등록
			gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtDetailRenew(resultVo);
		}

		GamCmmnCntrRentMngtVO updRentVO = new GamCmmnCntrRentMngtVO();

		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtRenewInfo(vo);

		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);

			gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtRenewInfo(updRentVO);
		}
	}

	/**
	 * 공컨장치장임대정보를 수정한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngt(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngt(vo);
	}

	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentMngtDetailList(GamCmmnCntrRentMngtVO vo) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtDetailList(vo);
    }

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentMngtDetailListTotCnt(GamCmmnCntrRentMngtVO vo) throws Exception {
		return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamCmmnCntrRentMngtDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentMngtLevReqestCnt(GamCmmnCntrRentMngtVO vo) throws Exception {
		return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtLevReqestCnt(vo);
	}

    /**
	 * 공컨장치장임대 정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentMngt(GamCmmnCntrRentMngtVO vo) throws Exception {

		gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngtPhoto(vo); //공컨장치장임대 사진정보 삭제

		gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngtDetail(vo); //공컨장치장임대 상세정보 삭제

		gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngt(vo); // 공컨장치장임대정보 삭제
	}

	/**
	 * 공컨장치장임대 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngtDetail(vo);
	}

	/**
	 * 공컨장치장임대 상세를 등록한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtDetailVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtDetail(vo);
	}

	/**
	 * 공컨장치장임대 상세를 수정한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtDetailVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtDetail(vo);
	}

	/**
	 * 공컨장치장임대 상세를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentMngtDetail2(GamCmmnCntrRentMngtDetailVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngtDetail2(vo);
	}

	/**
	 * 승낙할 공컨장치장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대정보
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtPrmisnInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtPrmisnInfo(searchVO);
    }

	/**
	 * 공컨장치장임대 허가여부를 취소한다.
	 * @param vo GamCmmnCntrRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtPrmisnCancel(GamCmmnCntrRentMngtLevReqestVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtPrmisnCancel(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentMngtFileList(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentMngtFileListTotCnt(GamCmmnCntrRentMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtFileListTotCnt(searchVO);
	}

    /**
	 * 파일을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentMngtFile(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtFile(vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtFile(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtFile(vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentMngtPhotoSingle(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngtPhotoSingle(vo);
	}

	/**
	 * 공컨장치장임대 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtMaxKey(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtComment(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtRenewInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtRenewInfo(searchVO);
    }

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtRenewInfo(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtRenewInfo(vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtCurrRenewInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 공컨장치장임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtDetailQuaycd(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtDetailQuaycd(searchVO);
    }

    /**
	 * 신청저장시 공컨장치장임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtQuaycd(GamCmmnCntrRentMngtVO vo) throws Exception {
		gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtQuaycd(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamCmmnCntrRentMngtDao.selectCofixInfo();
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtBeforeQuarterInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtBeforeQuarterInfo(searchVO);
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtCofixInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtCofixInfo(searchVO);
    }

    /**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtCofixInfoMax(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtCofixInfoMax(searchVO);
    }

    /* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService#selectChargeKndList()
	 */
	@Override
	public List selectChargeKndList() throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentMngtDao.selectChargeKndList();
	}

}