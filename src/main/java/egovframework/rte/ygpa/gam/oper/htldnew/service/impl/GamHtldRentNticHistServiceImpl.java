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
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticHistService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticHistVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 12.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamHtldRentNticHistService")
public class GamHtldRentNticHistServiceImpl extends AbstractServiceImpl implements GamHtldRentNticHistService {
	@Resource(name="gamHtldRentNticHistDao")
	private GamHtldRentNticHistDao gamHtldRentNticHistDao;
	
	@Resource(name="gamPopupHtldRcivProcDao")
    private GamPopupHtldRcivProcDao gamPopupHtldRcivProcDao;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 업체정보 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception
	 */
	public Map<?, ?> selectEntrpsInfo(GamHtldRentNticHistVO searchVO) throws Exception {
		return gamHtldRentNticHistDao.selectEntrpsInfo(searchVO);
	}
	
	/**
	 * 업체별 고지 이력 목록 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectHtldRentNticHistList(GamHtldRentNticHistVO searchVO) throws Exception {
		return gamHtldRentNticHistDao.selectHtldRentNticHistList(searchVO);
	}
	
	/**
	 * 고지 상세 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception 
	 */
	public List<?> selectHistNticIssueList(GamHtldRentNticHistVO searchVO) throws Exception {
		return gamHtldRentNticHistDao.selectHistNticIssueList(searchVO);
	}

	/**
	 * 연체고지 상세 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception 
	 */
	public List<?> selectHistArrrgNticIssueList(GamHtldRentNticHistVO searchVO) throws Exception {
		return gamHtldRentNticHistDao.selectHistArrrgNticIssueList(searchVO);
	}

	/**
	 * 해당고지의 연체고지 자료수 조회
	 * @param GamHtldRentNticHistVO
	 * @return int
	 * @exception Exception
	 */
	public int selectHistArrrgNticIssueListCnt(GamHtldRentNticHistVO searchVO) throws Exception {
		return gamHtldRentNticHistDao.selectHistArrrgNticIssueListCnt(searchVO);
	}
	
	/**
     * 배후단지 연체고지 취소 
     * @param GamHtldRentNticHistVO
     * @return
     * @throws Exception
     */
	public void cancelArrrgNticIssue(GamHtldRentNticHistVO searchVO) throws Exception {
		//Parameter로 넘어온 연체데이터로 해당 고지정보와 연체정보를 가져온다.
		List<?> dlyInfoList = gamHtldRentNticHistDao.selectHistArrrgNticIssueCancelList(searchVO);
		if(dlyInfoList == null) throw processException("fail.nticArrg.cancel");
		if(dlyInfoList.size() < 1) throw processException("fail.nticArrg.cancel");
		
		EgovMap dlyInfo = (EgovMap)dlyInfoList.get(0);
		if(dlyInfo == null) throw processException("fail.nticArrg.cancel");
		
		//고지테이블 데이터를 변경한다.
		String dlySerNo = (dlyInfo.get("dlySerNo") != null) ? (String) dlyInfo.get("dlySerNo") : null;
		if(dlySerNo == null) throw processException("fail.nticArrg.cancel");
		
		if("01".equals(dlySerNo) && dlyInfoList.size() == 1) {
			//최초연체라면
			searchVO.setRcivSe("0");
			searchVO.setPrvBillDt((String)dlyInfo.get("firstBillDt"));
			searchVO.setBillDt((String)dlyInfo.get("prvBillDt"));
			searchVO.setDlyDueDt((String)dlyInfo.get("prvDueDt"));
			gamHtldRentNticHistDao.updateHtldNticDtlsUnarrrgAmt(searchVO);
		} else {
			//최초연체가 아니라면
			if(dlyInfoList.size() > 1) {
				EgovMap prevDlyInfo = (EgovMap) dlyInfoList.get(1);
				searchVO.setDlyBillAmnt((String)prevDlyInfo.get("dlyBillAmnt"));
				searchVO.setDbillAmnt((String)prevDlyInfo.get("dbillAmnt"));
				searchVO.setDlySerNo((String)prevDlyInfo.get("dlySerNo"));
				searchVO.setArrrgTariff((String)prevDlyInfo.get("arrrgTariff"));
				searchVO.setArrrgPayDates((String)prevDlyInfo.get("arrrgPayDates"));
				searchVO.setBillDt((String)prevDlyInfo.get("prvBillDt"));
				searchVO.setDlyDueDt((String)prevDlyInfo.get("prvDueDt"));
				gamHtldRentNticHistDao.updateHtldNticDtlsArrrgAmt(searchVO);
			} else {
				throw processException("fail.nticArrg.cancel");
			}
		}
		
		//최초연체가 아닐 경우 변경된 dlySerNo값을 되돌린다.
		searchVO.setDlySerNo(dlySerNo);

		//연체정보 삭제
		gamHtldRentNticHistDao.deleteUnpaidByPk(searchVO);
	}

	/**
	 * 지로 수납된 자료인지 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return String 'Y, N'
	 * @exception Exception
	 */
	public String selectCheckOcrResult(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return gamPopupHtldRcivProcDao.selectCheckOcrResult(searchVO);
	}

}
