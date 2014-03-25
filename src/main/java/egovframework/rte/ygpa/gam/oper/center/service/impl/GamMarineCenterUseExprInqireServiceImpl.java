package egovframework.rte.ygpa.gam.oper.center.service.impl;

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
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireVO;

/**
 * @Class Name : GamMarineCenterUseExprInqireServiceImpl.java
 * @Description : 마린센터임대만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterUseExprInqireService")

public class GamMarineCenterUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamMarineCenterUseExprInqireService {

	@Resource(name="gamMarineCenterUseExprInqireDao")
    private GamMarineCenterUseExprInqireDao gamMarineCenterUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireList(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireList(searchVO);
    }

    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireListTotCnt(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireSum(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireSum(searchVO);
    }

    /**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireFirst(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireMaxNo(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireMaxNo(searchVO);
    }

    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireRenew(GamMarineCenterUseExprInqireVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//마린센터임대 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireMaxMngCnt(vo);
		
		//마린센터임대 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireRenew(vo); 
		
		//마린센터임대상세정보 조회
		List detailList = gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailInfo(vo);
		
		GamMarineCenterUseExprInqireDetailVO resultVo = null;
		
		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamMarineCenterUseExprInqireDetailVO();
			resultVo = (GamMarineCenterUseExprInqireDetailVO)detailList.get(i);
			
			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());
			
			//마린센터임대상세 복사등록
			gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireDetailRenew(resultVo); 
		}
		
		GamMarineCenterUseExprInqireVO updRentVO = new GamMarineCenterUseExprInqireVO();
		
		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireRenewInfo(vo);
		
		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);
			
			gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireRenewInfo(updRentVO);
		}
	}

	/**
	 * 마린센터임대정보를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqire(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqire(vo);
	}

	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireDetailList(GamMarineCenterUseExprInqireVO vo) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailList(vo);
    }

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireDetailListTotCnt(GamMarineCenterUseExprInqireVO vo) throws Exception {
		return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailListTotCnt(vo);
	}
    
    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamMarineCenterUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireLevReqestCnt(GamMarineCenterUseExprInqireVO vo) throws Exception {
		return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireLevReqestCnt(vo);
	}

    /**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqire(GamMarineCenterUseExprInqireVO vo) throws Exception {

		gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqirePhoto(vo); //마린센터임대 사진정보 삭제

		gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqireDetail(vo); //마린센터임대 상세정보 삭제

		gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqire(vo); // 마린센터임대정보 삭제
	}

	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqireDetail(vo);
	}

	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireDetailVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireDetail(vo);
	}

	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireDetailVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireDetail(vo);
	}

	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqireDetail2(GamMarineCenterUseExprInqireDetailVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqireDetail2(vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqirePrmisnInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqirePrmisnInfo(searchVO);
    }

	/**
	 * 마린센터임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqirePrmisn(GamMarineCenterUseExprInqireLevReqestVO vo) throws Exception {

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
		
		int monthCnt = gamMarineCenterUseExprInqireDao.selectUsagePdMonthCnt(vo);
		
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
				gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireLevReqest(vo);
		    }
			
		}

		//마린센터임대 허가여부를 수정
		gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqirePrmisn(vo);
	}

	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqirePrmisnCancel(GamMarineCenterUseExprInqireLevReqestVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqirePrmisnCancel(vo);
	}
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireFileList(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireFileListTotCnt(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireFileListTotCnt(searchVO);
	}
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireFile(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireFile(vo);
	}
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireFile(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireFile(vo);
	}
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqirePhotoSingle(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqirePhotoSingle(vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireMaxKey(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireMaxKey(searchVO);
    }
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireComment(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireComment(vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireRenewInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireRenewInfo(searchVO);
    }
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireRenewInfo(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireRenewInfo(vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireCurrRenewInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireCurrRenewInfo(searchVO);
    }
    
    /**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireDetailQuaycd(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailQuaycd(searchVO);
    }
    
    /**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireQuaycd(GamMarineCenterUseExprInqireVO vo) throws Exception {
		gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireQuaycd(vo);
	}
	
}
