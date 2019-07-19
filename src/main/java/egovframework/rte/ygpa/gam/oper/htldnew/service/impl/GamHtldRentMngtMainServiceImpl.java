/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldQuGtqyVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentRntfeeVO;

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

	@Resource(name="gamHtldRentCtrtDao")
	private GamHtldRentCtrtDao gamHtldRentCtrtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지 임대 상세목록 조회
	 * @param
	 * @return List 임대상세목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectHtldRentDetailList(GamHtldRentMngtMainVO vo) throws Exception {
		return getVirtualHtldRentFeeList((List<EgovMap>) gamHtldRentMngtMainDao.selectHtldRentDetailList(vo), vo);
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
	@SuppressWarnings("unchecked")
	protected List<EgovMap>getVirtualHtldRentFeeList(List<EgovMap> feeList, GamHtldRentMngtMainVO vo) throws Exception {
		List<EgovMap> resultList = new ArrayList<EgovMap>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate histDt = new LocalDate(dateFormat.parse(vo.getHistDt()));
		String cofixIntr = gamHtldRentMngtMainDao.selectCofixIntrrate(vo);

		BigDecimal totRntFee = new BigDecimal(0),  totPayinstIntr = new BigDecimal(0), totSupAmt = new BigDecimal(0),  totVat = new BigDecimal(0),  totPayAmt = new BigDecimal(0);
		int groupCount  = 0;

		for(int i=0; i<feeList.size(); i++) {
			EgovMap item = feeList.get(i);

			String mngYear = (item.get("mngYear") != null) ? (String)item.get("mngYear") : "";
			String mngNo = (item.get("mngNo") != null) ? (String)item.get("mngNo") : "";
			String mngSeq = (item.get("mngSeq") != null) ? (String)item.get("mngSeq") : "";
			String accnutYear = (item.get("accnutYear") != null) ? (String)item.get("accnutYear") : "";
			String rntfeeNticNo = (item.get("rntfeeNticNo") != null) ? (String)item.get("rntfeeNticNo") : "";
			String nticSeq = (item.get("nticSeq") != null) ? (String)item.get("nticSeq") : "";
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
			String rntfeeSeNm = (item.get("rntfeeSeNm") != null) ? (String)item.get("rntfeeSeNm") : "";
			String rentArSe = (item.get("rentArSe") != null) ? (String)item.get("rentArSe") : "";
			String rentArSeNm = (item.get("rentArSeNm") != null) ? (String)item.get("rentArSeNm") : "";
			String rentArStr = (item.get("rentArStr") != null) ? (String)item.get("rentArStr") : "";
			String applcRntfeeStr = (item.get("applcRntfeeStr") != null) ? (String)item.get("applcRntfeeStr") : "";
			BigDecimal nticVat = (item.get("nticVat") != null) ? (BigDecimal)item.get("nticVat") : new BigDecimal(0);
			BigDecimal nticPayAmt = (item.get("nticPayAmt") != null) ? (BigDecimal)item.get("nticPayAmt") : new BigDecimal(0);

			BigDecimal rntFee = new BigDecimal(0),  payinstIntr = new BigDecimal(0), supAmt = new BigDecimal(0),  vat = new BigDecimal(0),  payAmt = new BigDecimal(0);

			int mngGroupCount = getMngGropListCount(feeList, mngYear, mngNo, mngSeq, accnutYear, rntfeeNticNo, nticSeq);
			groupCount++;

			item.put("mngGroupCount", new BigDecimal(mngGroupCount));

			if(("0".equals(rntfeeSe)) && ("N".equals(nticYn))) {
				//일반고지자료이면서 고지가 되지 않은 가상의 자료
				rntFee = getRentFee(histDt, paySe, priceSe, rentAr, applcRntfee, detailPdBegin, detailPdEnd, aseRntfee, aseApplcBegin, aseApplcEnd);

				if("4".equals(paySe)) {
					payinstIntr = getRentFeeInstIntr(rntFee, cofixIntr, histDt, detailPdEnd);
				}

				if(mngGroupCount == 1) {
					//합산고지를 할 필요가 없는 자료
					supAmt = rntFee.add(payinstIntr).setScale(-1, RoundingMode.DOWN);
					vat = supAmt.divide(new BigDecimal(10));
					payAmt = supAmt.add(vat);
				} else {
					//합산고지를 할 필요가 있는 자료
					totRntFee = totRntFee.add(rntFee);
					totPayinstIntr = totPayinstIntr.add(payinstIntr);
				}

				LocalDate nticBeginDate = ("4".equals(paySe)) ? getQuarterStartDate(histDt) : new LocalDate(histDt.getYear(), 1, 1);
				LocalDate nticEndDate = ("4".equals(paySe)) ? getQuarterEndDate(histDt) : new LocalDate(histDt.getYear(), 12, 31);

				item.put("nticBeginDt", nticBeginDate.toString());
				item.put("nticEndDt", nticEndDate.toString());
				item.put("rntfee", rntFee);
				item.put("payinstIntr", payinstIntr);
				item.put("supAmt", supAmt);
				item.put("vat", vat);
				item.put("payAmt", payAmt);
			} else {
				//가상의 자료가 아닌 자료(즉 고지버튼을 누른 일반자료나 실적,면적,추가 고지자료
				rntFee = (item.get("rntfee") != null) ? (BigDecimal)item.get("rntfee") : new BigDecimal(0);
				payinstIntr = (item.get("payinstIntr") != null) ? (BigDecimal)item.get("payinstIntr") : new BigDecimal(0);
				supAmt = (item.get("supAmt") != null) ? (BigDecimal)item.get("supAmt") : new BigDecimal(0);
				if(mngGroupCount > 1) {
					totRntFee = totRntFee.add(rntFee);
					totPayinstIntr = totPayinstIntr.add(payinstIntr);
					totSupAmt = totSupAmt.add(supAmt);
				}
			}

			//가상 필드 추가
			if("0".equals(rntfeeSe)) { //일반고지 데이터이면
				item.put("detailPdStr", detailPdBegin + "~" + detailPdEnd);
				if(!"0".equals(rentArSe)) {
					if("3".equals(rentArSe)) {
						item.put("rentArStr", rentArSeNm); //숙성실
					} else {
						item.put("rentArStr", rentArStr + "/" + rentArSeNm); //물류부지, 제조부지
					}
				}
				if("2".equals(priceSe)) {
					item.put("applcRntfeeStr", applcRntfeeStr + "원/월");
				}
				if(aseRntfee.compareTo(new BigDecimal(0)) == 0) {
					item.put("aseRntfeeStr", "");
				}
				if(aseApplcBegin.length() > 0) {
					item.put("asePd", aseApplcBegin + "~" + aseApplcEnd);
				}
			} else { //일반고지데이터가 아니라면
				item.put("detailPdStr", rntfeeSeNm);
				item.put("applcRntfeeStr", "");
				if("1".equals(rntfeeSe)) { //실적평가데이터라면
					if(!"0".equals(rentArSe)) {
						if("3".equals(rentArSe)) {
							item.put("rentArStr", rentArSeNm); //숙성실
						} else {
							item.put("rentArStr", rentArStr + "/" + rentArSeNm); //물류부지, 제조부지
						}
					}
					if(aseRntfee.compareTo(new BigDecimal(0)) == 0) {
						item.put("aseRntfeeStr", "");
					}
					if(aseApplcBegin.length() > 0) {
						item.put("asePd", aseApplcBegin + "~" + aseApplcEnd);
					}
				} else { //실적평가데이터가 아니라면
					item.put("rentArStr", "");
					item.put("aseRntfeeStr", "");
					item.put("asePd", "");
				}
			}

			if(mngGroupCount > 1) {
				item.put("supAmt", null);
				item.put("vat", null);
				item.put("payAmt", null);
			} else {
				if("Y".equals(nticYn)) {
					//고지된 단행 데이터
					item.put("vat", nticVat);
					item.put("payAmt", nticPayAmt);
				}
			}

			resultList.add(item);

			if((mngGroupCount > 1) && (groupCount == mngGroupCount)) {
				//합산고지의 소계 레코드에 들어갈 항목 정의
				totSupAmt = totRntFee.add(totPayinstIntr).setScale(-1, RoundingMode.DOWN);
				if(("0".equals(rntfeeSe)) && ("N".equals(nticYn))) {
					//고지되지 않은 일반 가상의 데이터면....
					totVat = totSupAmt.divide(new BigDecimal(10));
					totPayAmt = totSupAmt.add(totVat);
				} else {
					//고지된 데이터라면...
					totVat = nticVat;
					totPayAmt = nticPayAmt;
				}
				EgovMap totItem = new EgovMap();
				Iterator<String> it = (Iterator<String>)item.keySet().iterator();
				while(it.hasNext()) {
					String key = it.next();
					totItem.put(key, item.get(key));
				}

				totItem.put("rntfeeSe", "9");  //소계레코드 구분자.

				totItem.put("rntfee", totRntFee);
				totItem.put("payinstIntr", totPayinstIntr);
				totItem.put("supAmt", totSupAmt);
				totItem.put("vat", totVat);
				totItem.put("payAmt", totPayAmt);

				totItem.put("detailPdStr", "소 계");
				totItem.put("rentArStr", "");
				totItem.put("assetsNm", "");
				totItem.put("assetsCd", "");
				totItem.put("applcRntfeeStr", "");
				totItem.put("aseRntfeeStr", "");
				totItem.put("asePd", "");
				totItem.put("rm", "");

				resultList.add(totItem);

				//합계 변수 초기화
				groupCount = 0;
				totRntFee = new BigDecimal(0);
				totPayinstIntr = new BigDecimal(0);
				totSupAmt = new BigDecimal(0);
				totVat = new BigDecimal(0);
				totPayAmt = new BigDecimal(0);

			} else if (mngGroupCount == 1) {
				groupCount = 0;
			}
		}
		return resultList;
	}

	/**
	 * 같은 관리코드 그룹의 레코드 수를 리턴
	 * @param list
	 * @param mngYear
	 * @param mngNo
	 * @param mngSeq
	 * @return
	 */
	protected int getMngGropListCount(List<EgovMap> list, String mngYear, String mngNo, String mngSeq, String accnutYear, String rntfeeNticNo, String nticSeq) {
		int count = 0;
		for(EgovMap item : list) {
			String year = (item.get("mngYear") != null) ? (String)item.get("mngYear") : "";
			String no = (item.get("mngNo") != null) ? (String)item.get("mngNo") : "";
			String seq = (item.get("mngSeq") != null) ? (String)item.get("mngSeq") : "";
			String aYear = (item.get("accnutYear") != null) ? (String)item.get("accnutYear") : "";
			String nNo = (item.get("rntfeeNticNo") != null) ? (String)item.get("rntfeeNticNo") : "";
			String nSeq = (item.get("nticSeq") != null) ? (String)item.get("nticSeq") : "";
			if(year.equals(mngYear) && no.equals(mngNo) && seq.equals(mngSeq)
				&& aYear.equals(accnutYear) && nNo.equals(rntfeeNticNo) && nSeq.equals(nticSeq) ) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 임대료 계산
	 * @param histDt - 고지예정일자
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
	protected BigDecimal getRentFee(LocalDate histDt, String paySe, String priceSe, BigDecimal rentAr, BigDecimal applcRntfee, String detailPdBegin, String detailPdEnd, BigDecimal aseRntfee, String aseApplcBegin, String aseApplcEnd) throws Exception {
		BigDecimal applcMonthFee = new BigDecimal(0), aseMonthFee = new BigDecimal(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate detailPdBeginDate = new LocalDate(dateFormat.parse(detailPdBegin));
		LocalDate detailPdEndDate = new LocalDate(dateFormat.parse(detailPdEnd));

		if("1".equals(priceSe)) {
			applcMonthFee = applcMonthFee.add(applcRntfee.multiply(rentAr));
			aseMonthFee = aseMonthFee.add(aseRntfee.multiply(rentAr));
		} else if("2".equals(priceSe)) {
			applcMonthFee = applcMonthFee.add(applcRntfee);
			aseMonthFee = aseMonthFee.add(aseRntfee);
		}

		LocalDate startDate = ("4".equals(paySe)) ? getQuarterStartDate(histDt) : new LocalDate(histDt.getYear(), 1, 1);
		LocalDate endDate = ("4".equals(paySe)) ? getQuarterEndDate(histDt) : new LocalDate(histDt.getYear(), 12, 31);

		if(startDate.compareTo(detailPdBeginDate) < 0) startDate = detailPdBeginDate;
		if(endDate.compareTo(detailPdEndDate) > 0) endDate = detailPdEndDate;

		BigDecimal resultFee = new BigDecimal(0);

		if(aseRntfee.compareTo(new BigDecimal(0)) <= 0) {
			resultFee = getTotalFee(startDate, endDate, applcMonthFee);
		} else {
			LocalDate aseApplcBeginDate = new LocalDate(dateFormat.parse(aseApplcBegin));
			LocalDate aseApplcEndDate = new LocalDate(dateFormat.parse(aseApplcEnd));

			if((aseApplcBeginDate.compareTo(startDate) <= 0) && (aseApplcEndDate.compareTo(startDate) >= 0)) {
				if(aseApplcEndDate.compareTo(endDate) >= 0) {
					resultFee = getTotalFee(startDate, endDate, aseMonthFee);
				} else {
					resultFee = getTotalFee(startDate, aseApplcEndDate, aseMonthFee);
					resultFee = resultFee.add(getTotalFee(aseApplcEndDate.plusDays(1), endDate, applcMonthFee));
				}
			} else if (((aseApplcBeginDate.compareTo(endDate) <= 0) && (aseApplcEndDate.compareTo(endDate) >= 0))) {
				resultFee = getTotalFee(startDate, aseApplcBeginDate.minusDays(1), applcMonthFee);
				resultFee = resultFee.add(getTotalFee(aseApplcBeginDate, endDate, aseMonthFee));
			} else {
				resultFee = getTotalFee(startDate, endDate, applcMonthFee);
			}
		}

		resultFee = resultFee.setScale(0, RoundingMode.DOWN);
		return resultFee;
	}

	/**
	 * 분납이자 계산
	 * @param rntFee : 임대료
	 * @param cofixIntr : 이자율
	 * @param histDt : 고지예정일자
	 * @param detailPdEnd : 상세계약만료일자
	 * @return 분납이자
	 */
	protected BigDecimal getRentFeeInstIntr(BigDecimal rntFee, String cofixIntr, LocalDate histDt, String detailPdEnd) {
		cofixIntr = cofixIntr.replace(" ", "");
		BigDecimal intrRate = new BigDecimal(cofixIntr);
		intrRate = intrRate.divide(new BigDecimal(100), 5, RoundingMode.CEILING);
		BigDecimal monthPayInstIntr = rntFee.multiply(intrRate).divide(new BigDecimal(12), 5, RoundingMode.CEILING);
		LocalDate nticEndDate = getQuarterEndDate(histDt);
		LocalDate detailPdEndDate = new LocalDate(detailPdEnd);
		if(histDt.getYear() < detailPdEndDate.getYear()) {
			detailPdEndDate = new LocalDate(histDt.getYear(), 12, 31);
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

	/**
	 * 임대료 저장
	 * @param feeInsertList
	 * @param feeUpdateList
	 * @param id
	 */
	public void updateHtldRntfee(List<GamHtldRentRntfeeVO> feeInsertList, List<GamHtldRentRntfeeVO> feeUpdateList, String id) throws Exception {
		for(GamHtldRentRntfeeVO item : feeUpdateList) {
			item.setUpdUsr(id);
			gamHtldRentMngtMainDao.updateHtldRntfee(item);
		}

		for(GamHtldRentRntfeeVO item : feeInsertList) {
			item.setRegUsr(id);
			item.setRntfeeSeq(gamHtldRentMngtMainDao.selectNextRntfeeSeq(item));
			gamHtldRentMngtMainDao.insertHtldRntfee(item);
		}
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainService#insertCopyAllRentContract(java.lang.String, java.lang.String)
	 */
	@Override
	public void insertCopyAllRentContract(GamHtldRentMngtMainVO searchVO) throws Exception {
		int year = Integer.parseInt(searchVO.getHistDt().substring(0, 4));
		searchVO.setMngYear(Integer.toString(year-1));
		gamHtldRentMngtMainDao.deleteHtldCopyCtrtNticDtls(searchVO);
		gamHtldRentMngtMainDao.deleteHtldCopyCtrtRntfee(searchVO);
		gamHtldRentMngtMainDao.deleteHtldCopyCtrtDtls(searchVO);
		gamHtldRentMngtMainDao.deleteHtldCopyCtrtMst(searchVO);
		gamHtldRentMngtMainDao.insertHtldCopyCtrt(searchVO);
		gamHtldRentMngtMainDao.insertHtldCopyCtrtDtls(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainService#selectHtldQuGtqyList(egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldQuGtqyVO)
	 */
	@Override
	public List selectHtldQuGtqyList(GamHtldQuGtqyVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldRentMngtMainDao.selectHtldQuGtqyList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainService#updateHtldQuGtqyList(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void updateHtldQuGtqyList(List<GamHtldQuGtqyVO> createList, List<GamHtldQuGtqyVO> updateList, List<GamHtldQuGtqyVO> deleteList) throws Exception {
		// TODO Auto-generated method stub
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		GamHtldQuGtqyVO vo=null;
		Map map;

		String usr = loginVO.getId();

		for(int i=0; i<deleteList.size(); i++) {
			vo = deleteList.get(i);
			gamHtldRentMngtMainDao.deleteHtldQuGtqyList(vo);
		}
		for(int i=0; i<updateList.size(); i++) {
			vo = updateList.get(i);
			vo.setUpdusr(usr);
			gamHtldRentMngtMainDao.updateHtldQuGtqyList(vo);
		}
		for(int i=0; i<createList.size(); i++) {
			vo = createList.get(i);
			vo.setRegister(usr);
			gamHtldRentMngtMainDao.insertHtldQuGtqyList(vo);
		}
	}

}
