/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldAreaAssessService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldAreaAssessVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 30.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 30.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamPopupHtldAreaAssessService")
public class GamPopupHtldAreaAssessServiceImpl  extends AbstractServiceImpl implements GamPopupHtldAreaAssessService {
	@Resource(name="gamPopupHtldAreaAssessDao")
    private GamPopupHtldAreaAssessDao gamPopupHtldAreaAssessDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 면적평가 조회
	 * @param GamPopupHtldBizAssessVO
	 * @return Map 면적평가정산
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldAreaAssessDetail(GamPopupHtldAreaAssessVO searchVO) throws Exception {
		return gamPopupHtldAreaAssessDao.selectHtldAreaAssessDetail(searchVO);
	}

	/**
	 * 면적평가 등록
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void insertAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate histDt = new LocalDate(dateFormat.parse(vo.getHistDt()));
		LocalDate nticBeginDt = getQuarterStartDate(histDt);
		LocalDate nticEndDt = getQuarterEndDate(histDt);
		if("6".equals(vo.getPaySe())) {
			nticBeginDt = new LocalDate(histDt.getYear(), 1, 1);
			nticEndDt = new LocalDate(histDt.getYear(), 12, 31);
		}
		vo.setRntfeeSeq(gamPopupHtldAreaAssessDao.selectNextRntfeeSeq(vo));
		vo.setNticBeginDt(nticBeginDt.toString());
		vo.setNticEndDt(nticEndDt.toString());
		vo.setRntfee(getAreaRntfee(vo));
		gamPopupHtldAreaAssessDao.insertAreaAssess(vo);
	}

	/**
	 * 면적평가 수정
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void updateAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception {
		vo.setRntfee(getAreaRntfee(vo));
		gamPopupHtldAreaAssessDao.updateAreaAssess(vo);
	}
	
	/**
	 * 면적평가 삭제
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void deleteAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception {
		gamPopupHtldAreaAssessDao.deleteAreaAssess(vo);
	}
	
	/**
	 * 면적평가의 임대료를 구한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String getAreaRntfee(GamPopupHtldAreaAssessVO vo) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate startDate = new LocalDate(dateFormat.parse(vo.getApplcBeginDt()));
		LocalDate endDate = new LocalDate(dateFormat.parse(vo.getApplcEndDt()));
		BigDecimal applcRentAr = new BigDecimal(vo.getApplcRentAr().trim());
		BigDecimal applcRntfee = new BigDecimal(vo.getApplcRntfee());
		BigDecimal areaMonthFee = new BigDecimal(0);
		
		if("1".equals(vo.getPriceSe())) {
			areaMonthFee = applcRentAr.multiply(applcRntfee);
		} else {
			areaMonthFee = applcRntfee;
		}
		
		return getTotalFee(startDate, endDate, areaMonthFee).toString();
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
	
	/**
	 * 기준일에 대한 분기시작일을 구함.
	 * @param baseDate - 기준일
	 * @return 분기시작일
	 */
	protected LocalDate getQuarterStartDate(LocalDate baseDate) {
		LocalDate retDate;
		
		if(baseDate.getMonthOfYear() < 4) {
			retDate = new LocalDate(baseDate.getYear(), 1, 1);
		}
		else if(baseDate.getMonthOfYear() < 7) {
			retDate = new LocalDate(baseDate.getYear(), 4, 1);
		}
		else if(baseDate.getMonthOfYear() < 10) {
			retDate = new LocalDate(baseDate.getYear(), 7, 1);
		}
		else {
			retDate = new LocalDate(baseDate.getYear(), 10, 1);
		}
		return retDate;
	}

	/**
	 * 기준일에 대한 분기종료일을 구함.
	 * @param baseDate - 기준일
	 * @return 분기종료일
	 */
	protected LocalDate getQuarterEndDate(LocalDate baseDate) {
		LocalDate retDate;
		
		if(baseDate.getMonthOfYear() < 4) {
			retDate = new LocalDate(baseDate.getYear(), 3, 31);
		}
		else if(baseDate.getMonthOfYear() < 7) {
			retDate = new LocalDate(baseDate.getYear(), 6, 30);
		}
		else if(baseDate.getMonthOfYear() < 10) {
			retDate = new LocalDate(baseDate.getYear(), 9, 30);
		}
		else {
			retDate = new LocalDate(baseDate.getYear(), 12, 31);
		}
		return retDate;
	}
	
}
