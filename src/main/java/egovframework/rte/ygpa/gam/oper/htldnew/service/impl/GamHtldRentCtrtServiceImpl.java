/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtDetailVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 2.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 2.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamHtldRentCtrtService")
public class GamHtldRentCtrtServiceImpl extends AbstractServiceImpl implements GamHtldRentCtrtService {

	@Resource(name="gamHtldRentCtrtDao")
    private GamHtldRentCtrtDao gamHtldRentCtrtDao;
	
	@Resource(name="gamHtldRentCtrtHistDao")
    private GamHtldRentCtrtHistDao gamHtldRentCtrtHistDao;

	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 배후단지 임대계약 조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지 임대계약
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldRentCtrt(GamHtldRentCtrtVO searchVO) throws Exception {
		return gamHtldRentCtrtDao.selectHtldRentCtrt(searchVO);
	}
		
	/**
	 * 배후단지 임대계약 상세목록 조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지 임대계약 상세목록
	 * @exception Exception
	 */
	public List<?> selectHtldRentCtrtDetailList(GamHtldRentCtrtVO searchVO) throws Exception {
		return gamHtldRentCtrtDao.selectHtldRentCtrtDetailList(searchVO);
	}
	
	/**
	 * 배후단지 임대계약 등록
	 * @param insertRentData - 등록할 임대계약, insertRentDetailList - 등록할 임대계약 상세목록, regUsr - 등록자
	 * @return
	 * @exception Exception
	 */	
	public void insertHtldRentCtrt(GamHtldRentCtrtVO insertRentData, List<GamHtldRentCtrtDetailVO> rentDetailList, String regUsr) throws Exception {
		/**
		 * RENT테이블과 RENT_DETAIL 테이블은 등록할 때 한번만 등록된다.
		 * 수정이 이루어졌을 때는 이력테이블에서 처리된다.
		 */
		//새로운 키 생성
		GamHtldRentCtrtVO mngKey = gamHtldRentCtrtDao.selectMngKeyValues();
		
		//임대계약 등록
		insertRentData.setMngYear(mngKey.getMngYear());
		insertRentData.setMngNo(mngKey.getMngNo());
		insertRentData.setMngSeq(mngKey.getMngSeq());
		insertRentData.setHistDt(insertRentData.getCtrtBeginDt());
		gamHtldRentCtrtDao.insertHtldRentCtrt(insertRentData);
		
		//임대계약 상세목록 및 임대료 등록
		for(GamHtldRentCtrtDetailVO detailItem : rentDetailList) {
			detailItem.setMngYear(mngKey.getMngYear());
			detailItem.setMngNo(mngKey.getMngNo());
			detailItem.setMngSeq(mngKey.getMngSeq());
			detailItem.setRegistSeq(gamHtldRentCtrtDao.selectNextRegistSeq(mngKey));
			detailItem.setRegUsr(regUsr);
			gamHtldRentCtrtDao.insertHtldRentCtrtDetail(detailItem);
		}
		
		//임대계약 이력번호 생성
		String histSeq = gamHtldRentCtrtHistDao.selectNextHistSeq(insertRentData);
		
		//임대계약 이력등록
		insertRentData.setHistSeq(histSeq);
		gamHtldRentCtrtHistDao.insertHtldRentCtrtHist(insertRentData);
		
		//임대계약 상세목록 이력등록
		for(GamHtldRentCtrtDetailVO detailItem : rentDetailList) {
			detailItem.setHistSeq(histSeq);
			detailItem.setHistDt(detailItem.getDetailPdBegin());
			gamHtldRentCtrtHistDao.insertHtldRentCtrtDetailHist(detailItem);
		}
		
	}
	
	/**
	 * 배후단지 임대계약 수정
	 * @param updateRentData - 수정할 임대계약, insertDetailList - 등록할 임대계약 상세목록, updateDetailList - 수정할 임대계약 상세목록, deleteDetailList - 삭제할 임대계약 상세목록, updtUsr - 수정자 
	 * @return
	 * @exception Exception
	 */	
	public void updateHtldRentCtrt(GamHtldRentCtrtVO updateRentData, List<GamHtldRentCtrtDetailVO> rentDetailList, String updUsr) throws Exception {	
		/** 
		 * 일반적인 복합적인 DETAIL처리와 다르다. 
		 * RENT테이블과 RENT_DETAIL테이블에 날짜기준으로 이력날짜의 최근자료가 들어가 있다는 보장이 없다.
		 * 그래서 이력날짜에 따른 데이터는 이력테이블에만 들어가고 RENT테이블과 RENT_DETAIL테이블에는 들어가지 않는다.  
		 */
		updateRentData.setUpdUsr(updUsr);
		updateRentData.setRegUsr(updUsr);
		
		//임대계약 이력번호 생성
		String histSeq = gamHtldRentCtrtHistDao.selectNextHistSeq(updateRentData);
		
		//임대계약 수정
		updateRentData.setUpdUsr(updUsr);

		updateRentData.setHistSeq(histSeq);
		gamHtldRentCtrtHistDao.insertHtldRentCtrtHist(updateRentData);
		
		//임대계약 상세목록 이력등록		
		for(GamHtldRentCtrtDetailVO detailItem : rentDetailList) {
			detailItem.setMngYear(updateRentData.getMngYear());
			detailItem.setMngNo(updateRentData.getMngNo());
			detailItem.setMngSeq(updateRentData.getMngSeq());
			detailItem.setHistSeq(histSeq);
			detailItem.setHistDt(updateRentData.getHistDt());
			detailItem.setRegistSeq(gamHtldRentCtrtHistDao.selectNextRegistSeq(updateRentData));
			gamHtldRentCtrtHistDao.insertHtldRentCtrtDetailHist(detailItem);
		}
	}
	
	/**
	 * 배후단지 임대계약 해지
	 * @param terminateRentData - 해지할 임대계약, updtUsr - 해지자 
	 * @return
	 * @exception Exception
	 */	
	@SuppressWarnings("unchecked")
	public void terminateHtldRentCtrt(GamHtldRentCtrtVO terminateRentData, String updUsr) throws Exception {
		terminateRentData.setUpdUsr(updUsr);
		gamHtldRentCtrtDao.terminateHtldRentCtrt(terminateRentData);

		//vo의 이력날짜에 최신 데이터를 가져온다.
		GamHtldRentCtrtVO rentVo = gamHtldRentCtrtHistDao.selectHtldRentCtrt(terminateRentData);
		List<GamHtldRentCtrtDetailVO> rentDetailList = (List<GamHtldRentCtrtDetailVO>) gamHtldRentCtrtHistDao.selectHtldRentCtrtDetail(terminateRentData);
		
		// 이력번호 생성
		String histSeq = gamHtldRentCtrtHistDao.selectNextHistSeq(terminateRentData);
		
		// 이력데이터를 추가
		rentVo.setHistDt(terminateRentData.getHistDt());
		rentVo.setHistSeq(histSeq);
		rentVo.setRegUsr(updUsr);
		rentVo.setUpdUsr(updUsr);
		rentVo.setTermnDt(terminateRentData.getTermnDt());
		rentVo.setTermnRsn(terminateRentData.getTermnRsn());
		gamHtldRentCtrtHistDao.insertHtldRentCtrtHist(rentVo);
		
		for(GamHtldRentCtrtDetailVO detailItem : rentDetailList) {
			detailItem.setHistDt(terminateRentData.getHistDt());
			detailItem.setHistSeq(histSeq);
			detailItem.setRegUsr(updUsr);
			detailItem.setUpdUsr(updUsr);
			gamHtldRentCtrtHistDao.insertHtldRentCtrtDetailHist(detailItem);
		}
	}
	

}
