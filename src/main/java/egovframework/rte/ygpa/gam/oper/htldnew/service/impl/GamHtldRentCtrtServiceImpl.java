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
	public void insertHtldRentCtrt(GamHtldRentCtrtVO insertRentData, List<GamHtldRentCtrtDetailVO> insertRentDetailList, String regUsr) throws Exception {
		//새로운 키 생성
		GamHtldRentCtrtVO mngKey = gamHtldRentCtrtDao.selectMngKeyValues();

		//임대계약 등록
		insertRentData.setMngYear(mngKey.getMngYear());
		insertRentData.setMngNo(mngKey.getMngNo());
		insertRentData.setMngSeq(mngKey.getMngSeq());
		gamHtldRentCtrtDao.insertHtldRentCtrt(insertRentData);
		
		//임대계약 상세목록 및 임대료 등록
		for(GamHtldRentCtrtDetailVO detailItem : insertRentDetailList) {
			detailItem.setMngYear(mngKey.getMngYear());
			detailItem.setMngNo(mngKey.getMngNo());
			detailItem.setMngSeq(mngKey.getMngSeq());
			detailItem.setRegistSeq(gamHtldRentCtrtDao.selectNextRegistSeq(mngKey));
			detailItem.setRegUsr(regUsr);
			gamHtldRentCtrtDao.insertHtldRentCtrtDetail(detailItem);
		}
		
		insertHtldRentHist(insertRentData);
		
	}
	
	/**
	 * 배후단지 임대계약 수정
	 * @param updateRentData - 수정할 임대계약, insertDetailList - 등록할 임대계약 상세목록, updateDetailList - 수정할 임대계약 상세목록, deleteDetailList - 삭제할 임대계약 상세목록, updtUsr - 수정자 
	 * @return
	 * @exception Exception
	 */	
	public void updateHtldRentCtrt(GamHtldRentCtrtVO updateRentData, List<GamHtldRentCtrtDetailVO> insertDetailList,
			List<GamHtldRentCtrtDetailVO> updateDetailList, List<GamHtldRentCtrtDetailVO> deleteDetailList, String updUsr) throws Exception {	
		//임대계약 수정
		updateRentData.setUpdUsr(updUsr);
		gamHtldRentCtrtDao.updateHtldRentCtrt(updateRentData);
		
		//임대계약 상세목록 수정
		if(deleteDetailList.size() > 0) {
			for(GamHtldRentCtrtDetailVO detailItem : deleteDetailList) {
				gamHtldRentCtrtDao.deleteHtldRentCtrtDetail(detailItem);
			}
		}
		
		if(updateDetailList.size() > 0) {
			for(GamHtldRentCtrtDetailVO detailItem : updateDetailList) {
				detailItem.setUpdUsr(updUsr);
				gamHtldRentCtrtDao.updateHtldRentCtrtDetail(detailItem);
			}
		}
		
		if(insertDetailList.size() > 0) {
			for(GamHtldRentCtrtDetailVO detailItem : insertDetailList) {
				detailItem.setRegUsr(updUsr);
				gamHtldRentCtrtDao.insertHtldRentCtrtDetail(detailItem);
			}
		}
		
		insertHtldRentHist(updateRentData);
	}
	
	/**
	 * 배후단지 임대계약 해지
	 * @param terminateRentData - 해지할 임대계약, updtUsr - 해지자 
	 * @return
	 * @exception Exception
	 */	
	public void terminateHtldRentCtrt(GamHtldRentCtrtVO terminateRentData, String updUsr) throws Exception {
		terminateRentData.setUpdUsr(updUsr);
		gamHtldRentCtrtDao.terminateHtldRentCtrt(terminateRentData);
		
		insertHtldRentHist(terminateRentData);
	}
	

	/**
	 * 배후단지 임대계약 이력등록
	 * @param rentData - 임대계약 
	 * @return
	 * @exception Exception
	 */	
	protected void insertHtldRentHist(GamHtldRentCtrtVO rentData) throws Exception {
		//임대계약 이력번호 생성
		String histSeq = gamHtldRentCtrtDao.selectNextHistSeq(rentData);
		rentData.setHistSeq(histSeq);
		
		//임대계약 이력등록
		gamHtldRentCtrtDao.insertHtldRentCtrtHist(rentData);
		
		//임대계약 상세이력등록
		gamHtldRentCtrtDao.insertHtldRentCtrtDetailHist(rentData);		
	}	
}
