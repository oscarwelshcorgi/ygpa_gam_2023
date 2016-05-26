/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRntfeeBizAssessService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRntfeeBizAssessVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 26.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamPopupHtldRntfeeBizAssessService")
public class GamPopupHtldRntfeeBizAssessServiceImpl  extends AbstractServiceImpl implements GamPopupHtldRntfeeBizAssessService  {
	
	@Resource(name="gamPopupHtldRntfeeBizAssessDao")
    private GamPopupHtldRntfeeBizAssessDao gamPopupHtldRntfeeBizAssessDao;

	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 실적평가정산 조회
	 * @param GamPopupHtldBizAssessVO
	 * @return Map 실적평가정산
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldRntfeeBizAssessDetail(GamPopupHtldRntfeeBizAssessVO searchVO) throws Exception {
		return gamPopupHtldRntfeeBizAssessDao.selectHtldRntfeeBizAssessDetail(searchVO);
	}

	/**
	 * 실적평가정산 수정
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void updateRntfeeBizAssess(GamPopupHtldRntfeeBizAssessVO vo) throws Exception {
		vo.setRntfee(getRntfee(vo));
		gamPopupHtldRntfeeBizAssessDao.updateRntfeeBizAssess(vo);
	}
	
	/**
	 * 실적평가정산 삭제
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void deleteRntfeeBizAssess(GamPopupHtldRntfeeBizAssessVO vo) throws Exception {
		gamPopupHtldRntfeeBizAssessDao.deleteRntfeeBizAssess(vo);
	}
	
	/**
	 * 실적평가정산 수정임대료를 구함
	 * @param vo
	 * @return
	 */
	protected String getRntfee(GamPopupHtldRntfeeBizAssessVO vo) {
		BigDecimal applcRntfee = new BigDecimal(vo.getApplcRntfee().trim());
		LocalDate applcStartDate = new LocalDate(vo.getApplcBeginDt().trim());
		LocalDate applcEndDate = new LocalDate(vo.getApplcEndDt().trim());
		BigDecimal rentAr = new BigDecimal(vo.getRentAr().trim());
		BigDecimal applcMonthFee = new BigDecimal(0);
		
		if("1".equals(vo.getPriceSe())) {
			applcMonthFee = rentAr.multiply(applcRntfee);
		} else {
			applcMonthFee = applcRntfee;
		}
		BigDecimal result = getTotalFee(applcStartDate, applcEndDate, applcMonthFee).setScale(0, RoundingMode.DOWN);
		return result.toString();
	}
	
	/**
	 * 해당 기간의 사용료를 구함
	 * @param  시작일자, 종료일자, 월사용료
	 * @return 해당기간 사용료
	 * @exception Exception
	 */		
	protected BigDecimal getTotalFee(LocalDate fromDate, LocalDate toDate, BigDecimal monthFee) {
		BigDecimal totalFee;

		toDate=toDate.plusDays(1);

		Months months = Months.monthsBetween(fromDate, toDate);

		totalFee = monthFee.multiply(new BigDecimal(months.getMonths()));
		
		int startDay=fromDate.getDayOfMonth();
		if(startDay!=1) {
			LocalDate endOfMonth = fromDate.dayOfMonth().withMaximumValue();

			BigDecimal bd = new BigDecimal(endOfMonth.getDayOfMonth()-startDay+1);
			BigDecimal div = new BigDecimal(endOfMonth.getDayOfMonth());
			bd = bd.divide(div, 5, RoundingMode.CEILING);
			bd = bd.multiply(monthFee);
			totalFee = totalFee.add(bd);
		}
		int toDay=toDate.getDayOfMonth();
		if(toDay>1) {
			LocalDate endOfMonth = fromDate.dayOfMonth().withMaximumValue();

			BigDecimal bd = new BigDecimal(toDay-1);
			BigDecimal div = new BigDecimal(endOfMonth.getDayOfMonth());
			bd = bd.divide(div, 5, RoundingMode.CEILING);
			bd = bd.multiply(monthFee);
			totalFee = totalFee.add(bd);
		}
		totalFee = totalFee.setScale(1, RoundingMode.DOWN);

		return totalFee;
	}
}
