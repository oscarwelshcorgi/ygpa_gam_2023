package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.text.ParseException;
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
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetFeeExprInqireDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetFeeExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetFeeExprInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetFeeExprInqireVO;

/**
 * @Class Name : GamAssetFeeExprInqireServiceImpl.java
 * @Description : 자산임대고지도래자료조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetFeeExprInqireService")

public class GamAssetFeeExprInqireServiceImpl  extends AbstractServiceImpl implements GamAssetFeeExprInqireService {

	@Resource(name="gamAssetFeeExprInqireDao")
    private GamAssetFeeExprInqireDao gamAssetFeeExprInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetFeeExprInqireList(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireList(searchVO);
    }

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetFeeExprInqireListTotCnt(GamAssetFeeExprInqireVO searchVO) throws Exception {
		return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireSum(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireSum(searchVO);
    }

    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireFirst(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.insertAssetFeeExprInqireFirst(vo);
	}

    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireMaxNo(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireMaxNo(searchVO);
    }

    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireRenew(GamAssetFeeExprInqireVO vo) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//자산임대 복사등록된  MngCnt의 max값을 가져온다.
		String maxMngCnt = gamAssetFeeExprInqireDao.selectAssetFeeExprInqireMaxMngCnt(vo);

		//자산임대 복사등록
		vo.setMaxMngCnt(maxMngCnt);
		vo.setRegUsr(loginVO.getId());
		vo.setUpdUsr(loginVO.getId());
		vo.setReqstSeCd("2");
		gamAssetFeeExprInqireDao.insertAssetFeeExprInqireRenew(vo);

		//자산임대상세정보 조회
		List detailList = gamAssetFeeExprInqireDao.selectAssetFeeExprInqireDetailInfo(vo);

		GamAssetFeeExprInqireDetailVO resultVo = null;

		for( int i = 0 ; i < detailList.size() ; i++ ) {
			resultVo = new GamAssetFeeExprInqireDetailVO();
			resultVo = (GamAssetFeeExprInqireDetailVO)detailList.get(i);

			resultVo.setMngCnt(maxMngCnt);
			resultVo.setRegUsr(loginVO.getId());
			resultVo.setUpdUsr(loginVO.getId());

			//자산임대상세 복사등록
			gamAssetFeeExprInqireDao.insertAssetFeeExprInqireDetailRenew(resultVo);
		}

		GamAssetFeeExprInqireVO updRentVO = new GamAssetFeeExprInqireVO();

		//총사용료, 총면적, 총사용기간 조회
		updRentVO = gamAssetFeeExprInqireDao.selectAssetFeeExprInqireRenewInfo(vo);

		if( updRentVO != null ) {
			updRentVO.setPrtAtCode(vo.getPrtAtCode());
			updRentVO.setMngYear(vo.getMngYear());
			updRentVO.setMngNo(vo.getMngNo());
			updRentVO.setMaxMngCnt(maxMngCnt);

			gamAssetFeeExprInqireDao.updateAssetFeeExprInqireRenewInfo(updRentVO);
		}
	}

	/**
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqire(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.updateAssetFeeExprInqire(vo);
	}

	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetFeeExprInqireDetailList(GamAssetFeeExprInqireVO vo) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireDetailList(vo);
    }

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetFeeExprInqireDetailListTotCnt(GamAssetFeeExprInqireVO vo) throws Exception {
		return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireDetailListTotCnt(vo);
	}

    /**
   	 * 공시지가 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
       public List selectOlnlpInfo() throws Exception {
           return gamAssetFeeExprInqireDao.selectOlnlpInfo();
       }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetFeeExprInqireLevReqestCnt(GamAssetFeeExprInqireVO vo) throws Exception {
		return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireLevReqestCnt(vo);
	}

    /**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqire(GamAssetFeeExprInqireVO vo) throws Exception {

		gamAssetFeeExprInqireDao.deleteAssetFeeExprInqirePhoto(vo); //자산임대 사진정보 삭제

		gamAssetFeeExprInqireDao.deleteAssetFeeExprInqireDetail(vo); //자산임대 상세정보 삭제

		gamAssetFeeExprInqireDao.deleteAssetFeeExprInqire(vo); // 자산임대정보 삭제
	}

	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqireDetail(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.deleteAssetFeeExprInqireDetail(vo);
	}

	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireDetail(GamAssetFeeExprInqireDetailVO vo) throws Exception {
		gamAssetFeeExprInqireDao.insertAssetFeeExprInqireDetail(vo);
	}

	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireDetail(GamAssetFeeExprInqireDetailVO vo) throws Exception {
		gamAssetFeeExprInqireDao.updateAssetFeeExprInqireDetail(vo);
	}

	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqireDetail2(GamAssetFeeExprInqireDetailVO vo) throws Exception {
		gamAssetFeeExprInqireDao.deleteAssetFeeExprInqireDetail2(vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqirePrmisnInfo(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqirePrmisnInfo(searchVO);
    }

	/**
	 * 자산임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamAssetFeeExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqirePrmisn(GamAssetFeeExprInqireLevReqestVO vo) throws Exception {

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

		int monthCnt = gamAssetFeeExprInqireDao.selectUsagePdMonthCnt(vo);

		log.debug("################################################ monthCnt => " + monthCnt);

		if( vo.getNticMth().equals("2") ) { // 반기납 [추후 협의후 재작업 (2014.02.04)]
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
			} catch(ParseException e){
				
			}
			catch(Exception e) { log.warn("an error found"); }

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
				} catch(ParseException e){
					
				} catch(Exception e) { log.warn("an error found"); }
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
				} catch(ParseException e){
					
				}  catch(Exception e) { log.warn("an error found"); }

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
				} catch(ParseException e){
					
				}
				catch(Exception e) { log.warn("an error found"); }

			}
		}
	    else {	// 일시납
			startRetVal = new String[1];
			endRetVal = new String[1];
			dayCnt = new int[1];

			startRetVal[0] = cStartDt;
			endRetVal[0] = cEndDt;

			try {
				dayCnt[0] = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(endRetVal[0], 0, 0, 1)).getTime() - sdf.parse(startRetVal[0]).getTime()) / 1000 / 60 / 60 / 24); //기간에 해당하는 날짜수 가져오기
			} catch(ParseException e){
				
			}catch(Exception e) { log.warn("an error found"); }

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
				gamAssetFeeExprInqireDao.insertAssetFeeExprInqireLevReqest(vo);
		    }

		}

		//자산임대 허가여부를 수정
		gamAssetFeeExprInqireDao.updateAssetFeeExprInqirePrmisn(vo);
	}

	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetFeeExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqirePrmisnCancel(GamAssetFeeExprInqireLevReqestVO vo) throws Exception {
		gamAssetFeeExprInqireDao.updateAssetFeeExprInqirePrmisnCancel(vo);
	}

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetFeeExprInqireFileList(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireFileList(searchVO);
    }
;
    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetFeeExprInqireFileListTotCnt(GamAssetFeeExprInqireVO searchVO) throws Exception {
		return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireFileListTotCnt(searchVO);
	}

    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireFile(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.insertAssetFeeExprInqireFile(vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireFile(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.updateAssetFeeExprInqireFile(vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqirePhotoSingle(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.deleteAssetFeeExprInqirePhotoSingle(vo);
	}

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireMaxKey(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireMaxKey(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireComment(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.updateAssetFeeExprInqireComment(vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireRenewInfo(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireRenewInfo(searchVO);
    }

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireRenewInfo(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.updateAssetFeeExprInqireRenewInfo(vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireCurrRenewInfo(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireCurrRenewInfo(searchVO);
    }

    /**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireDetailQuaycd(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return gamAssetFeeExprInqireDao.selectAssetFeeExprInqireDetailQuaycd(searchVO);
    }

    /**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireQuaycd(GamAssetFeeExprInqireVO vo) throws Exception {
		gamAssetFeeExprInqireDao.updateAssetFeeExprInqireQuaycd(vo);
	}

}
