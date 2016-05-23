/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 10.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamHtldRentMngtMainService")
public class GamHtldRentMngtMainServiceImpl extends AbstractServiceImpl implements GamHtldRentMngtMainService {
	
	@Resource(name="gamHtldRentMngtMainDao")
    private GamHtldRentMngtMainDao gamHtldRentMngtMainDao;

	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 배후단지 임대 상세목록 조회
	 * @param 
	 * @return List 임대상세목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectHtldRentDetailList(GamHtldRentMngtMainVO vo) throws Exception {
		return calcHtldRentFeeList((List<EgovMap>) gamHtldRentMngtMainDao.selectHtldRentDetailList(vo), vo);
	}

	/**
	 *  Cofix 이자율 조회
	 * @param 
	 * @return String 이자율
	 * @exception Exception
	 */
	public String selectCofixIntrrate(GamHtldRentMngtMainVO vo) throws Exception {
		return gamHtldRentMngtMainDao.selectCofixIntrrate(vo);
	}
	
	/**
	 * 배후단지 임대료 설정
	 * @param list - 임대계약 임대료 리스트 
	 * @return
	 * @exception Exception
	 */	
	protected List<EgovMap>calcHtldRentFeeList(List<EgovMap> feeList, GamHtldRentMngtMainVO vo) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate nticDate = new LocalDate(dateFormat.parse(vo.getsNticDt()));
		String cofixIntr = gamHtldRentMngtMainDao.selectCofixIntrrate(vo);
		for(EgovMap item : feeList) {
			String priceSe = (item.get("priceSe") != null) ? (String)item.get("priceSe") : "";
			String paySe = (item.get("paySe") != null) ? (String)item.get("paySe") : "";
			BigDecimal applcRntfee = (item.get("applcRntfee") != null) ? (BigDecimal)item.get("applcRntfee") : new BigDecimal(0);
			BigDecimal aseRntfee = (item.get("aseRntfee") != null) ? (BigDecimal)item.get("aseRntfee") : new BigDecimal(0);
			BigDecimal rentAr = (item.get("rentAr") != null) ? (BigDecimal)item.get("rentAr") : new BigDecimal(0);
			String detailPdBegin = (item.get("detailPdBegin") != null) ? (String)item.get("detailPdBegin") : "";
			String detailPdEnd = (item.get("detailPdEnd") != null) ? (String)item.get("detailPdEnd") : "";
			String aseApplcBegin = (item.get("aseApplcBegin") != null) ? (String)item.get("aseApplcBegin") : "";
			String aseApplcEnd = (item.get("aseApplcEnd") != null) ? (String)item.get("aseApplcEnd") : "";
			String nticYn = (item.get("nticYn") != null) ? (String)item.get("nticYn") : "N";
			String rntfeeSe = (item.get("rntfeeSe") != null) ? (String)item.get("rntfeeSe") : "0";
			
			if(("0".equals(rntfeeSe)) && ("N".equals(nticYn))) {
				BigDecimal rntFee = getRentFee(nticDate, paySe, priceSe, rentAr, applcRntfee, detailPdBegin, detailPdEnd, aseRntfee, aseApplcBegin, aseApplcEnd);
				item.put("rntfee", rntFee);
				BigDecimal payinstIntr = new BigDecimal(0);
				if("4".equals(paySe)) {
					payinstIntr = getRentFeeInstIntr(rntFee, cofixIntr, nticDate, detailPdEnd);
				}
				item.put("payinstIntr", payinstIntr);
				BigDecimal supAmt = rntFee.add(payinstIntr).setScale(-1, RoundingMode.DOWN);
				item.put("supAmt", supAmt);
				BigDecimal vat = supAmt.divide(new BigDecimal(10));
				item.put("vat", vat);
				BigDecimal payAmt = supAmt.add(vat);
				item.put("payAmt", payAmt);
			}
		}
		return feeList;
	}
		
	/**
	 * 임대료 계산
	 * @param nticDate - 고지예정일자
	 * @param paySe - 납부구분 - 연납, 분기납
	 * @param priceSe - 가격구분 - 면적당단가, 월단가
	 * @param rentAr - 임대면적
	 * @param applcRntfee - 적용단가
	 * @param detailPdBegin - 상세계약시작일자
	 * @param detailPdEnd - 상세계약종료일자
	 * @param aseRntfee - 실적평가단가
	 * @param aseApplcBegin - 실적평가시작일
	 * @param aseApplcEnd -- 실적평가종료일
	 * @return 임대료
	 * @throws Exception
	 */
	protected BigDecimal getRentFee(LocalDate nticDate, String paySe, String priceSe, BigDecimal rentAr, BigDecimal applcRntfee, String detailPdBegin, String detailPdEnd, BigDecimal aseRntfee, String aseApplcBegin, String aseApplcEnd) throws Exception {
		BigDecimal applcMonthFee = new BigDecimal(0), aseMonthFee = new BigDecimal(0); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		LocalDate detailPdBeginDate = new LocalDate(dateFormat.parse(detailPdBegin));
		LocalDate detailPdEndDate = new LocalDate(dateFormat.parse(detailPdEnd));
		LocalDate aseApplcBeginDate, aseApplcEndDate;
		
		if("1".equals(priceSe)) {
			applcMonthFee = applcMonthFee.add(applcRntfee.multiply(rentAr));
		} else if("2".equals(priceSe)) {
			applcMonthFee = applcMonthFee.add(applcRntfee);
		}

		if(aseRntfee.compareTo(new BigDecimal(0)) > 0) {
			aseApplcBeginDate = new LocalDate(dateFormat.parse(aseApplcBegin));
			aseApplcEndDate = new LocalDate(dateFormat.parse(aseApplcEnd));			
			if("1".equals(priceSe)) {
				aseMonthFee = aseMonthFee.add(aseRntfee.multiply(rentAr));
			} else if("2".equals(priceSe)) {
				aseMonthFee = aseMonthFee.add(aseRntfee);
			}
		} else {
			aseApplcBeginDate = detailPdBeginDate;
			aseApplcEndDate = detailPdEndDate;
			aseMonthFee = applcMonthFee;
		}
		
		LocalDate quarterStartDate;
		LocalDate quarterEndDate;
		
		if("4".equals(paySe)) {
			quarterStartDate = getQuarterStartDate(nticDate);
			quarterEndDate = getQuarterEndDate(nticDate);
		} else {
			quarterStartDate = new LocalDate(nticDate.getYear(), 1, 1);
			quarterEndDate = new LocalDate(nticDate.getYear(), 12, 31);
		}
		
		LocalDate appStartDate = quarterStartDate, appEndDate = quarterEndDate, aseStartDate = quarterStartDate, aseEndDate = quarterEndDate;
		
		if(appStartDate.compareTo(detailPdBeginDate) < 0) {
			appStartDate = detailPdBeginDate;
		}
		
		if(appEndDate.compareTo(detailPdEndDate) > 0) {
			appEndDate = detailPdEndDate;
		}
		
		if(aseStartDate.compareTo(aseApplcBeginDate) < 0) {
			aseStartDate = aseApplcBeginDate;
		}

		if(aseEndDate.compareTo(aseApplcEndDate) < 0) {
			aseEndDate = aseApplcEndDate;
		}
		
		BigDecimal resultFee = new BigDecimal(0);
		
		if((aseRntfee.compareTo(new BigDecimal(0)) > 0) && (aseRntfee.compareTo(applcRntfee) != 0)) {
			if((aseStartDate.compareTo(appStartDate) != 0) || (aseEndDate.compareTo(appEndDate) != 0)) {
				if((aseStartDate.compareTo(appStartDate) == 0) && (aseEndDate.compareTo(appEndDate) != 0)) {
					if((aseEndDate.compareTo(appEndDate) > 0)) {
						resultFee = getTotalFee(aseStartDate, appEndDate, aseMonthFee);
					} else {
						appStartDate = aseEndDate.plusDays(1);
						resultFee = getTotalFee(aseStartDate, aseEndDate, aseMonthFee);
						resultFee = resultFee.add(getTotalFee(appStartDate, appEndDate, applcMonthFee));
					}
				} else if((aseStartDate.compareTo(appStartDate) != 0) && (aseEndDate.compareTo(appEndDate) == 0)) {
					if((aseStartDate.compareTo(appStartDate) > 0)) {
						resultFee = getTotalFee(appStartDate, aseEndDate, aseMonthFee);
					}
					if((aseStartDate.compareTo(appStartDate) < 0)) {
						appEndDate = aseStartDate.minusDays(1);
						resultFee = getTotalFee(appStartDate, appEndDate, applcMonthFee);
						resultFee = resultFee.add(getTotalFee(aseStartDate, aseEndDate, aseMonthFee));
					}
				} else if((aseStartDate.compareTo(appStartDate) < 0) && (aseEndDate.compareTo(appEndDate) > 0)) {
					LocalDate tempAppEndDate = aseStartDate.minusDays(1);
					LocalDate tempAppStartDate = aseEndDate.plusDays(1);
					resultFee = getTotalFee(appStartDate, tempAppEndDate, applcMonthFee);
					resultFee = resultFee.add(getTotalFee(aseStartDate, aseEndDate, aseMonthFee));
					resultFee = resultFee.add(getTotalFee(tempAppStartDate, appEndDate, applcMonthFee));
				} else {
					resultFee = getTotalFee(appStartDate, appEndDate, aseMonthFee);
				}
			} else {
				resultFee = getTotalFee(aseStartDate, aseEndDate, aseMonthFee);
			}
		} else {
			resultFee = getTotalFee(appStartDate, appEndDate, applcMonthFee);
		}
		return resultFee;
	}
	
	/**
	 * 분납이자 계산
	 * @param rntFee : 임대료
	 * @param cofixIntr : 이자율
	 * @param nticDate : 고지예정일자
	 * @param detailPdEnd : 상세계약만료일자
	 * @return 분납이자
	 */
	protected BigDecimal getRentFeeInstIntr(BigDecimal rntFee, String cofixIntr, LocalDate nticDate, String detailPdEnd) {
		cofixIntr = cofixIntr.replace(" ", "");
		BigDecimal intrRate = new BigDecimal(cofixIntr);
		intrRate = intrRate.divide(new BigDecimal(100));
		BigDecimal monthPayInstIntr = rntFee.multiply(intrRate).divide(new BigDecimal(12));
		LocalDate nticEndDate = getQuarterEndDate(nticDate);
		LocalDate detailPdEndDate = new LocalDate(detailPdEnd);
		if(nticDate.getYear() < detailPdEndDate.getYear()) {
			detailPdEndDate = new LocalDate(nticDate.getYear(), 12, 31);
		}
		BigDecimal payinstIntr = new BigDecimal(0);
		if(nticEndDate.compareTo(detailPdEndDate) < 0) {
			nticEndDate = nticEndDate.plusDays(1);
			payinstIntr = getTotalFee(nticEndDate, detailPdEndDate, monthPayInstIntr);
		}
		payinstIntr = payinstIntr.setScale(-1, RoundingMode.DOWN);
		return payinstIntr;
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
		
		System.out.println("debug : " + monthFee);
		System.out.println("debug : " + totalFee);
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
