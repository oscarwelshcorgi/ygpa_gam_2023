package egovframework.rte.ygpa.gam.asset.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentVO;

/**
 * @Class Name : GamAssetRentServiceImpl.java
 * @Description : 자산임대관리 Business Implement class
 * @Modification Information
 *
 * @author 정윤후
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentService")

public class GamAssetRentServiceImpl implements GamAssetRentService {
	
	@Resource(name="gamAssetRentDao")
    private GamAssetRentDao gamAssetRentDao;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentList(GamAssetRentVO searchVO) throws Exception {
        return gamAssetRentDao.selectAssetRentList(searchVO);
    }

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentListTotCnt(GamAssetRentVO searchVO) throws Exception {
		return gamAssetRentDao.selectAssetRentListTotCnt(searchVO);
	}
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentVO selectAssetRentSum(GamAssetRentVO searchVO) throws Exception {
        return gamAssetRentDao.selectAssetRentSum(searchVO);
    }
    
    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentFirst(GamAssetRentVO vo) throws Exception {
		gamAssetRentDao.insertAssetRentFirst(vo);
	}
	
    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentVO selectAssetRentMaxNo(GamAssetRentVO searchVO) throws Exception {
        return gamAssetRentDao.selectAssetRentMaxNo(searchVO);
    }
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentRenew(GamAssetRentVO vo) throws Exception {
		gamAssetRentDao.insertAssetRentRenew(vo);
	}
	
	/**
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRent(GamAssetRentVO vo) throws Exception {
		gamAssetRentDao.updateAssetRent(vo);
	}
	
	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentDetailList(GamAssetRentVO vo) throws Exception {
        return gamAssetRentDao.selectAssetRentDetailList(vo);
    }

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentDetailListTotCnt(GamAssetRentVO vo) throws Exception {
		return gamAssetRentDao.selectAssetRentDetailListTotCnt(vo);
	}
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentLevReqestCnt(GamAssetRentVO vo) throws Exception {
		return gamAssetRentDao.selectAssetRentLevReqestCnt(vo);
	}
    
    /**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRent(GamAssetRentVO vo) throws Exception {
		
		gamAssetRentDao.deleteAssetRentPhoto(vo); //자산임대 사진정보 삭제
		
		gamAssetRentDao.deleteAssetRentDetail(vo); //자산임대 상세정보 삭제
		
		gamAssetRentDao.deleteAssetRent(vo); // 자산임대정보 삭제
	}
    
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentDetail(GamAssetRentVO vo) throws Exception {
		gamAssetRentDao.deleteAssetRentDetail(vo);
	}
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void insertAssetRentDetail(GamAssetRentDetailVO vo) throws Exception {
		gamAssetRentDao.insertAssetRentDetail(vo);
	}
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentDetail(GamAssetRentDetailVO vo) throws Exception {
		gamAssetRentDao.updateAssetRentDetail(vo);
	}
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentDetail2(GamAssetRentDetailVO vo) throws Exception {
		gamAssetRentDao.deleteAssetRentDetail2(vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    public GamAssetRentVO selectAssetRentPrmisnInfo(GamAssetRentVO searchVO) throws Exception {
        return gamAssetRentDao.selectAssetRentPrmisnInfo(searchVO);
    }

	/**
	 * 자산임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamAssetRentLevReqestVO
	 * @exception Exception
	 */
	public void updateAssetRentPrmisn(GamAssetRentLevReqestVO vo) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String cStartDt = EgovStringUtil.remove(vo.getGrUsagePdFrom(), '-');
		String cEndDt = EgovStringUtil.remove(vo.getGrUsagePdTo(), '-');
		
		String[] startRetVal = null; //고지방법별 사용시작일
		String[] endRetVal = null; //고지방법별 사용종료일
		int[] dayCnt = null; //고지방법별 해당기간의 날짜수
		int totDayCnt = 0; // 사용기간 총 일수
		int dayFee = 0; //일별 사용료
		
		totDayCnt = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(vo.getGrUsagePdTo(), 0, 0, 1)).getTime() - sdf.parse(vo.getGrUsagePdFrom()).getTime()) / 1000 / 60 / 60 / 24); //해당기간의 총 일자 수
		
		dayFee = Integer.parseInt(vo.getGrFee()) / totDayCnt;
		
		if( vo.getNticMth().equals("1") ) {	// 일시납
			startRetVal = new String[1];
			endRetVal = new String[1];
			dayCnt = new int[1];
			
			startRetVal[0] = cStartDt; 
			endRetVal[0] = cEndDt;     
			
			try {
				dayCnt[0] = (int)((sdf.parse(EgovDateUtil.addYearMonthDay(endRetVal[0], 0, 0, 1)).getTime() - sdf.parse(startRetVal[0]).getTime()) / 1000 / 60 / 60 / 24); //기간에 해당하는 날짜수 가져오기
			} catch (Exception e) {}
		} else if( vo.getNticMth().equals("2") ) { // 반기납
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
		} else if( vo.getNticMth().equals("3") ) { // 3분납
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
	    } else if( vo.getNticMth().equals("4") ) { // 분기별
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
	    	startRetVal = new String[12];
			endRetVal = new String[12];
			dayCnt = new int[12];
			
			for( int i = 0; i < 12; i++ ) {
				if( i == 0 ) {
					startRetVal[i] = cStartDt;
				} else {
					startRetVal[i] = EgovDateUtil.addYearMonthDay(cStartDt, 0, i, 0);
				}
				if( i == 11 ) {
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
			int thisTimeFee = 0 ; 
			int thisTimeVat = 0; 
			
			vo.setNticCnt(Integer.toString(i+1)); //고지횟수
			
			int propNticPdFrom = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propNticPdFrom"));
			int propNticPdTo   = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propNticPdTo"));
			int propPayTmlmt   = Integer.parseInt(EgovProperties.getProperty("ygam.asset.rent.propPayTmlmt"));
			
			vo.setNticPdFrom(EgovDateUtil.addDay(startRetVal[i], propNticPdFrom)); //고지기간 FROM
			vo.setConstPerTo(EgovDateUtil.addDay(startRetVal[i], propNticPdTo)); //고지기간 TO
		    vo.setPayTmlmt(EgovDateUtil.addDay(startRetVal[i], propPayTmlmt));   //납부기한
			
			
			thisTimeFee = dayFee * dayCnt[i]; //사용료 
			vo.setFee(Integer.toString(thisTimeFee));
			
			if( "Y".equals(vo.getVatYn()) ) { 
				thisTimeVat = thisTimeFee / 10;
				thisTimeFee = thisTimeFee + thisTimeVat;
				
				vo.setVat(Integer.toString(thisTimeVat)); //부가세
			}

			vo.setNticAmt(Integer.toString(thisTimeFee)); //고지금액
			
			
			//징수의뢰 insert
			gamAssetRentDao.insertAssetRentLevReqest(vo);
		}
		
		//자산임대 허가여부를 수정
		gamAssetRentDao.updateAssetRentPrmisn(vo);
	}
	
}
