/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupHtldBizAssessService")
public class GamPopupHtldBizAssessServiceImpl extends AbstractServiceImpl implements GamPopupHtldBizAssessService {

	@Resource(name="gamPopupHtldBizAssessDao")
    private GamPopupHtldBizAssessDao gamPopupHtldBizAssessDao;

	@Resource(name="gamHtldRentCtrtDao")
    private GamHtldRentCtrtDao gamHtldRentCommDao;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 배후단지 임대계약 조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지 임대계약
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldRentBizAssessDetail(GamPopupHtldBizAssessVO searchVO) throws Exception {
		return gamPopupHtldBizAssessDao.selectHtldRentBizAssessDetail(searchVO);
	}

	/**
	 * 실적평가 등록
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */	
	public void updateBizAssess(GamPopupHtldBizAssessVO vo, String updUsr) throws Exception {
		vo.setUpdUsr(updUsr);
		gamPopupHtldBizAssessDao.updateBizAssess(vo);
		insertHtldRentHist(vo);
	}

	/**
	 * 배후단지 임대계약 이력등록
	 * @param rentData - 임대계약 
	 * @return
	 * @exception Exception
	 */	
	protected void insertHtldRentHist(GamPopupHtldBizAssessVO rentData) throws Exception {
		GamHtldRentCtrtVO ctrtVO = new GamHtldRentCtrtVO();
		ctrtVO.setMngYear(rentData.getMngYear());
		ctrtVO.setMngNo(rentData.getMngNo());
		ctrtVO.setMngSeq(rentData.getMngSeq());
		
		//임대계약 이력번호 생성
		String histSeq = gamHtldRentCommDao.selectNextHistSeq(ctrtVO);
		ctrtVO.setHistSeq(histSeq);
		
		//임대계약 이력등록
		gamHtldRentCommDao.insertHtldRentCtrtHist(ctrtVO);
		
		//임대계약 상세이력등록
		gamHtldRentCommDao.insertHtldRentCtrtDetailHist(ctrtVO);		
	}
	
}
