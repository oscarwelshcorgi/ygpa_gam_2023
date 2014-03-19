package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

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
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseExprInqireVO;

/**
 * @Class Name : GamPrtFcltyUseExprInqireServiceImpl.java
 * @Description : 항만시설사용만기도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamPrtFcltyUseExprInqireService")

public class GamPrtFcltyUseExprInqireServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyUseExprInqireService {

	@Resource(name="gamPrtFcltyUseExprInqireDao")
    private GamPrtFcltyUseExprInqireDao gamPrtFcltyUseExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireList(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireList(searchVO);
    }

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireListTotCnt(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireSum(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireSum(searchVO);
    }

    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireFirst(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireMaxNo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxNo(searchVO);
    }

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireRenew(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//항만시설사용 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxMngCnt(vo);
		
		//항만시설사용 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireRenew(vo); 
		
		//항만시설사용상세정보 조회
		List detailList = gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailInfo(vo);
		
		GamPrtFcltyUseExprInqireDetailVO resultVo = null;
		
		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamPrtFcltyUseExprInqireDetailVO();
			resultVo = (GamPrtFcltyUseExprInqireDetailVO)detailList.get(i);
			
			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());
			
			//항만시설사용상세 복사등록
			gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireDetailRenew(resultVo); 
		}
		
		GamPrtFcltyUseExprInqireVO updRentVO = new GamPrtFcltyUseExprInqireVO();
		
		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireRenewInfo(vo);
		
		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);
			
			gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireRenewInfo(updRentVO);
		}
	}

	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqire(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqire(vo);
	}

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireDetailList(GamPrtFcltyUseExprInqireVO vo) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailList(vo);
    }

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireDetailListTotCnt(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailListTotCnt(vo);
	}
    
    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamPrtFcltyUseExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireLevReqestCnt(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireLevReqestCnt(vo);
	}

    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqire(GamPrtFcltyUseExprInqireVO vo) throws Exception {

		gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqirePhoto(vo); //항만시설사용 사진정보 삭제

		gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqireDetail(vo); //항만시설사용 상세정보 삭제

		gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqire(vo); // 항만시설사용정보 삭제
	}

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqireDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireDetailVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireDetailVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireDetail(vo);
	}

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqireDetail2(GamPrtFcltyUseExprInqireDetailVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqireDetail2(vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqirePrmisnInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqirePrmisnInfo(searchVO);
    }

	/**
	 * 항만시설사용 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqirePrmisn(GamPrtFcltyUseExprInqireLevReqestVO vo) throws Exception {

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
		
		int monthCnt = gamPrtFcltyUseExprInqireDao.selectUsagePdMonthCnt(vo);
		
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
				gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireLevReqest(vo);
		    }
			
		}

		//항만시설사용 허가여부를 수정
		gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqirePrmisn(vo);
	}

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqirePrmisnCancel(GamPrtFcltyUseExprInqireLevReqestVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqirePrmisnCancel(vo);
	}
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireFileList(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireFileListTotCnt(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireFileListTotCnt(searchVO);
	}
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireFile(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireFile(vo);
	}
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireFile(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireFile(vo);
	}
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqirePhotoSingle(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqirePhotoSingle(vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireMaxKey(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxKey(searchVO);
    }
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireComment(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireComment(vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireRenewInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireRenewInfo(searchVO);
    }
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireRenewInfo(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireRenewInfo(vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireCurrRenewInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireCurrRenewInfo(searchVO);
    }
    
    /**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireDetailQuaycd(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailQuaycd(searchVO);
    }
    
    /**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireQuaycd(GamPrtFcltyUseExprInqireVO vo) throws Exception {
		gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireQuaycd(vo);
	}
	
}
