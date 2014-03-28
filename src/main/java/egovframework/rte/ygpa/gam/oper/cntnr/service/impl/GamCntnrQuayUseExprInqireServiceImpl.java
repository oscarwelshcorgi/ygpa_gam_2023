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
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireVO;

/**
 * @Class Name : GamCntnrQuayUseExprInqireServiceImpl.java
 * @Description : 컨테이너부두임대만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayUseExprInqireService")

public class GamCntnrQuayUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamCntnrQuayUseExprInqireService {

	@Resource(name="gamCntnrQuayUseExprInqireDao")
    private GamCntnrQuayUseExprInqireDao gamCntnrQuayUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireList(searchVO);
    }

    /**
	 * 컨테이너부두임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireListTotCnt(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireSum(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireSum(searchVO);
    }

    /**
	 * 컨테이너부두임대 최초 신청을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireFirst(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireMaxNo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireMaxNo(searchVO);
    }

    /**
	 * 컨테이너부두임대 연장 신청을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireRenew(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//컨테이너부두임대 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireMaxMngCnt(vo);
		
		//컨테이너부두임대 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireRenew(vo); 
		
		//컨테이너부두임대상세정보 조회
		List detailList = gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailInfo(vo);
		
		GamCntnrQuayUseExprInqireDetailVO resultVo = null;
		
		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamCntnrQuayUseExprInqireDetailVO();
			resultVo = (GamCntnrQuayUseExprInqireDetailVO)detailList.get(i);
			
			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());
			
			//컨테이너부두임대상세 복사등록
			gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireDetailRenew(resultVo); 
		}
		
		GamCntnrQuayUseExprInqireVO updRentVO = new GamCntnrQuayUseExprInqireVO();
		
		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireRenewInfo(vo);
		
		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);
			
			gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireRenewInfo(updRentVO);
		}
	}

	/**
	 * 컨테이너부두임대정보를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqire(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqire(vo);
	}

	/**
	 * 컨테이너부두임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireDetailList(GamCntnrQuayUseExprInqireVO vo) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailList(vo);
    }

    /**
	 * 컨테이너부두임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireDetailListTotCnt(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailListTotCnt(vo);
	}
    
    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamCntnrQuayUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireLevReqestCnt(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireLevReqestCnt(vo);
	}

    /**
	 * 컨테이너부두임대 정보를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqire(GamCntnrQuayUseExprInqireVO vo) throws Exception {

		gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqirePhoto(vo); //컨테이너부두임대 사진정보 삭제

		gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqireDetail(vo); //컨테이너부두임대 상세정보 삭제

		gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqire(vo); // 컨테이너부두임대정보 삭제
	}

	/**
	 * 컨테이너부두임대 상세정보를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqireDetail(vo);
	}

	/**
	 * 컨테이너부두임대 상세를 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireDetailVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireDetail(vo);
	}

	/**
	 * 컨테이너부두임대 상세를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireDetailVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireDetail(vo);
	}

	/**
	 * 컨테이너부두임대 상세를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqireDetail2(GamCntnrQuayUseExprInqireDetailVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqireDetail2(vo);
	}

	/**
	 * 승낙할 컨테이너부두임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대정보
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqirePrmisnInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqirePrmisnInfo(searchVO);
    }

	/**
	 * 컨테이너부두임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqirePrmisn(GamCntnrQuayUseExprInqireLevReqestVO vo) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String cStartDt = EgovStringUtil.remove(vo.getGrUsagePdFrom(), '-');
		String cEndDt = EgovStringUtil.remove(vo.getGrUsagePdTo(), '-');

		String[] startRetVal = null; //고지방법별 사용시작일
		String[] endRetVal = null; //고지방법별 사용종료일
		int[] dayCnt = null; //고지방법별 해당기간의 날짜수
		int totDayCnt = 0; // 사용기간 총 일수
		int dayFee = 0; //일별 사용료
		
		int propNticPdFrom = 0; //고지기간 FROM 
		int propNticPdTo   = 0; //고지기간 TO 
		int propPayTmlmt   = 0; //납부기한

		totDayCnt = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(vo.getGrUsagePdTo(), 0, 0, 1)).getTime() - sdf.parse(vo.getGrUsagePdFrom()).getTime()) / 1000 / 60 / 60 / 24); //해당기간의 총 일자 수
		dayFee    = Integer.parseInt(vo.getGrFee()) / totDayCnt;
		
		int monthCnt = gamCntnrQuayUseExprInqireDao.selectUsagePdMonthCnt(vo);
		
		log.debug("################################################ monthCnt => " + monthCnt);

		if( vo.getNticMth().equals("1") ) {	// 일시납
			startRetVal = new String[1];
			endRetVal = new String[1];
			dayCnt = new int[1];

			startRetVal[0] = cStartDt;
			endRetVal[0] = cEndDt;

			try {
				dayCnt[0] = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(endRetVal[0], 0, 0, 1)).getTime() - sdf.parse(startRetVal[0]).getTime()) / 1000 / 60 / 60 / 24); //기간에 해당하는 날짜수 가져오기
			} catch (Exception e) {}
		} else if( vo.getNticMth().equals("2") ) { // 반기납 [추후 협의후 재작업 (2014.02.04)]
			startRetVal = new String[2];
			endRetVal = new String[2];
			dayCnt = new int[2];

			startRetVal[0] = cStartDt; //상반기
			endRetVal[0] = EgovDateUtil.addYearMonthDay(cStartDt, 0, 6, -1);

			startRetVal[1] = EgovDateUtil.addYearMonthDay(cStartDt, 0, 6, 0); //하반기
			endRetVal[1] = cEndDt;

			try {
				dayCnt[0] = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(endRetVal[0], 0, 0, 1)).getTime() - sdf.parse(startRetVal[0]).getTime()) / 1000 / 60 / 60 / 24); //상반기 날짜수

				dayCnt[1] = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(endRetVal[1], 0, 0, 1)).getTime() - sdf.parse(startRetVal[1]).getTime()) / 1000 / 60 / 60 / 24); //하반기 날짜수
			} catch (Exception e) {}
		} else if( vo.getNticMth().equals("3") ) { // 3분납 [추후 협의후 재작업 (2014.02.04)]
			int term = 0;
			startRetVal = new String[3];
			endRetVal = new String[3];
			dayCnt = new int[3];

			for( int i = 0; i < 3; i++ ) {
				if( i == 0 ) {
					startRetVal[i] = cStartDt;
				} else {
					startRetVal[i] = EgovDateUtil.addYearMonthDay(cStartDt, 0, term, 0);
				}
				if( i == 2 ) {
					endRetVal[i] = cEndDt;
				} else {
					endRetVal[i] = EgovDateUtil.addYearMonthDay(startRetVal[i], 0, 4, -1);
				}

				term = term + 4;

				try {
					dayCnt[i] = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(endRetVal[i], 0, 0, 1)).getTime() - sdf.parse(startRetVal[i]).getTime()) / 1000 / 60 / 60 / 24); //날짜수
				} catch (Exception e) {}
			}
	    } else if( vo.getNticMth().equals("4") ) { // 분기별 [추후 협의후 재작업 (2014.02.04)]
	    	int term = 0;
	    	startRetVal = new String[4];
			endRetVal = new String[4];
			dayCnt = new int[4];

			for( int i = 0; i < 4; i++ ) {
				if( i == 0 ) {
					startRetVal[i] = cStartDt;
				} else {
					startRetVal[i] = EgovDateUtil.addYearMonthDay(cStartDt, 0, term, 0);
				}
				if( i == 3 ) {
					endRetVal[i] = cEndDt;
				} else {
					endRetVal[i] = EgovDateUtil.addYearMonthDay(startRetVal[i], 0, 3, -1);
				}

				term = term + 3;

				try {
					dayCnt[i] = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(endRetVal[i], 0, 0, 1)).getTime() - sdf.parse(startRetVal[i]).getTime()) / 1000 / 60 / 60 / 24);
				} catch (Exception e) {}
			}
	    } else if( vo.getNticMth().equals("5") ) { // 월별
	    	startRetVal = new String[monthCnt];
			endRetVal = new String[monthCnt];
			dayCnt = new int[monthCnt];

			for( int i = 0; i < monthCnt; i++ ) {
				if( i == 0 ) {
					startRetVal[i] = cStartDt;
				} else {
					startRetVal[i] = EgovDateUtil.addYearMonthDay(cStartDt, 0, i, 0);
				}
				
				int j = monthCnt-1;
				if( i == j ) {
					endRetVal[i] = cEndDt;
				} else {
					endRetVal[i] = EgovDateUtil.addYearMonthDay(startRetVal[i], 0, 1, -1);
				}

				try {
					dayCnt[i] = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(endRetVal[i], 0, 0, 1)).getTime() - sdf.parse(startRetVal[i]).getTime()) / 1000 / 60 / 60 / 24);
				} catch (Exception e) {}
			}
		}

		for( int i = 0; i < startRetVal.length; i++ ) {
			int thisTimeFee = 0;
			int thisTimeVat = 0;

			vo.setNticCnt(Integer.toString(i+1)); //고지횟수
			
			if( "Pre".equals(vo.getPayMth()) ) { //납부방법(선납)
				propNticPdFrom = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propNticPdFrom"));
				propNticPdTo   = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propNticPdTo"));
				propPayTmlmt   = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propPayTmlmt"));
			} else { //납부방법(후납)
				propNticPdFrom = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propNticPdFromAfter"));
				propNticPdTo = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propNticPdToAfter"));
				propPayTmlmt = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propPayTmlmtAfter"));
			}
			
			vo.setNticPdFrom(EgovDateUtil.addDay(startRetVal[i], propNticPdFrom)); //고지기간 FROM
			vo.setConstPerTo(EgovDateUtil.addDay(startRetVal[i], propNticPdTo)); //고지기간 TO
		    vo.setPayTmlmt(EgovDateUtil.addDay(startRetVal[i], propPayTmlmt));   //납부기한

		    if( dayCnt[i] > 0 ) {
				thisTimeFee = dayFee * dayCnt[i]; //사용료
				vo.setFee(Integer.toString(thisTimeFee));
	
				if( "Y".equals(vo.getVatYn()) ) {
					thisTimeVat = thisTimeFee / 10;
					thisTimeFee = thisTimeFee + thisTimeVat;
	
					vo.setVat(Integer.toString(thisTimeVat)); //부가세
				}
	
				vo.setNticAmt(Integer.toString(thisTimeFee)); //고지금액
				
				log.debug("################################################ for => " + i + "##################################");
				log.debug("################################################ 월별 시작일 => " + startRetVal[i]);
				log.debug("################################################ 월별 종료일 => " + endRetVal[i]);
				log.debug("################################################ 월별 날짜수 => " + dayCnt[i]);
				log.debug("################################################ 고지기간 FROM => " + vo.getNticPdFrom());
				log.debug("################################################ 고지기간 TO => " + vo.getConstPerTo());
				log.debug("################################################ 납부기한 => " + vo.getPayTmlmt());
				log.debug("################################################ 사용료 => " + vo.getFee());
				log.debug("################################################ 부가세여부 => " + vo.getVatYn());
				log.debug("################################################ 부가세 => " + vo.getVat());
				log.debug("################################################ 고지금액 => " + vo.getNticAmt());
				log.debug("---------------------------------------------------------------------------------------------------");
				
				//징수의뢰 insert
				gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireLevReqest(vo);
		    }
			
		}

		//컨테이너부두임대 허가여부를 수정
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqirePrmisn(vo);
	}

	/**
	 * 컨테이너부두임대 허가여부를 취소한다.
	 * @param vo GamCntnrQuayUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqirePrmisnCancel(GamCntnrQuayUseExprInqireLevReqestVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqirePrmisnCancel(vo);
	}
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireFileList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireFileListTotCnt(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireFileListTotCnt(searchVO);
	}
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireFile(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireFile(vo);
	}
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireFile(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireFile(vo);
	}
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqirePhotoSingle(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqirePhotoSingle(vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireMaxKey(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireMaxKey(searchVO);
    }
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireComment(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireComment(vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireRenewInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireRenewInfo(searchVO);
    }
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireRenewInfo(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireRenewInfo(vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireCurrRenewInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireCurrRenewInfo(searchVO);
    }
    
    /**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireDetailQuaycd(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailQuaycd(searchVO);
    }
    
    /**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireQuaycd(GamCntnrQuayUseExprInqireVO vo) throws Exception {
		gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireQuaycd(vo);
	}
	
}
