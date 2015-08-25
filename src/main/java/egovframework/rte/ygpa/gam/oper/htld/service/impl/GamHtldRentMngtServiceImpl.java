package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.MonthDay;
import org.joda.time.Months;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldAssessVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentAttachFileVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 * @Class Name : GamHtldRentMngtServiceImpl.java
 * @Description : 배후단지임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldRentMngtService")
public class GamHtldRentMngtServiceImpl extends AbstractServiceImpl implements GamHtldRentMngtService {

	@Resource(name="gamHtldRentMngtDao")
    private GamHtldRentMngtDao gamHtldRentMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentMngtList(GamHtldRentDefaultVO searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtList(searchVO);
    }

    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentMngtListTotCnt(GamHtldRentDefaultVO searchVO) throws Exception {
		return gamHtldRentMngtDao.selectHtldRentMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public EgovMap selectHtldRentMngtSum(GamHtldRentDefaultVO searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldRentMngtDetailPk(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO)
	 */
	@Override
	public GamHtldRentMngtVO selectHtldRentMngtDetailPk(GamHtldRentMngtVO searchVO)
			throws Exception {
		return gamHtldRentMngtDao.selectHtldRentMngtDetailPk(searchVO);
	}

    /**
     * 배후단지 임대 정보를 등록한다.
     * @param vo
     * @throws Exception
     */
	public GamHtldRentMngtVO insertHtldRentMngt(GamHtldRentMngtVO rentVo, List<GamHtldRentMngtDetailVO> createList) throws Exception {
		String updtId=rentVo.getUpdUsr();
		//String deptCd=rentVo.getDeptcd();

		GamHtldRentMngtVO newRentVo = gamHtldRentMngtDao.selectHtldRentMngtMaxKey(rentVo);

		rentVo.setMngYear(newRentVo.getMngYear());
		rentVo.setMngNo(newRentVo.getMngNo());
		rentVo.setMngCnt(newRentVo.getMngCnt());

		GamHtldAssessVO assessVo = new GamHtldAssessVO();

		gamHtldRentMngtDao.insertHtldRentMngt(rentVo);

		for(int i=0; i<createList.size(); i++) {
			GamHtldRentMngtDetailVO detail=createList.get(i);
			detail.setPrtAtCode(rentVo.getPrtAtCode());
			detail.setMngYear(newRentVo.getMngYear());
			detail.setMngNo(newRentVo.getMngNo());
			detail.setMngCnt(newRentVo.getMngCnt());
			gamHtldRentMngtDao.insertHtldRentMngtDetail(detail);
		}

		insertHtldRentLevReqest(rentVo, createList);

		return rentVo;
	}

	private void insertHtldRentLevReqest(GamHtldRentMngtVO rentVo, List<GamHtldRentMngtDetailVO> rentDetail) throws Exception {
		BigDecimal monthFee = new BigDecimal(0);

		if(rentVo.getChrgeKnd()==null || "".equals(rentVo.getChrgeKnd())) {
			throw processException("fail.rent.insertNtic.msg");
		}
		gamHtldRentMngtDao.deleteBillByRentMngtVO(rentVo);

		for(int i=0; i<rentDetail.size(); i++) {
			GamHtldRentMngtDetailVO detail=rentDetail.get(i);
			monthFee = monthFee.add(new BigDecimal(detail.getApplcPrice()).multiply(new BigDecimal(detail.getUsageAr())));
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date from, to;
		LocalDate fromDate, toDate, startDate, periodDate;

		GamHtldRentMngtLevReqestVO levReqestVo = new GamHtldRentMngtLevReqestVO();

		levReqestVo.setPrtAtCode(rentVo.getPrtAtCode());
		levReqestVo.setMngYear(rentVo.getMngYear());
		levReqestVo.setMngNo(rentVo.getMngNo());
		levReqestVo.setMngCnt(rentVo.getMngCnt());
		int nticCnt=1;
		levReqestVo.setNticCnt(Integer.toString(nticCnt));
		levReqestVo.setChrgeKnd(rentVo.getChrgeKnd());
		levReqestVo.setEntrpscd(rentVo.getEntrpscd());
		levReqestVo.setEntrpsNm(rentVo.getEntrpsNm());
		levReqestVo.setVatYn(rentVo.getTaxtSe());
		levReqestVo.setNticMth(rentVo.getNticMth());
		levReqestVo.setRegUsr(rentVo.getRegUsr());
		levReqestVo.setRegistDt(rentVo.getRegistDt());
		levReqestVo.setDeptcd(rentVo.getDeptcd());
		levReqestVo.setVatYn(rentVo.getTaxtSe());
		levReqestVo.setFcltySe("1");

//		#nticPdFrom#, #nticPdTo#,
//		#fee#, #vat#, #vatYn#, #nticAmt#, #nticMth#, #regUsr#, #registDt#, #deptcd#

		if("1".equals(rentVo.getNticMth())) {
			from = dateFormat.parse(rentVo.getGrUsagePdFrom());
			to = dateFormat.parse(rentVo.getGrUsagePdTo());
			fromDate = new LocalDate(from);
			toDate = new LocalDate(to);

			BigDecimal grFee = getTotalFee(fromDate, toDate, monthFee);
			levReqestVo.setFee(grFee.toString());
			if("2".equals(rentVo.getTaxtSe())) {
				BigDecimal vat = grFee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
				levReqestVo.setVat(vat.toString());
				BigDecimal nticAmount = grFee.add(vat);
				levReqestVo.setNticAmt(nticAmount.toString());
			}
			else {
				levReqestVo.setVat("0");
				levReqestVo.setNticAmt(grFee.toString());
			}

			levReqestVo.setNticPdFrom(dateFormat.format(from));
			levReqestVo.setNticPdTo(dateFormat.format(to));
			gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
		}
		else {
//			vo.put("payinstIntrrate", rentVo.get("payinstIntrrate"));
			// 분납
			if("2".equals(rentVo.getNticMth())) {
				from = dateFormat.parse(rentVo.getGrUsagePdFrom());
				to = dateFormat.parse(rentVo.getGrUsagePdTo());
				fromDate = new LocalDate(from);
				toDate = new LocalDate(to);
				startDate = new LocalDate(fromDate);
				if(startDate.getMonthOfYear()<6) {
					periodDate = new LocalDate(startDate.getYear(), 6, 30);
				}
				else {
					periodDate = new LocalDate(startDate.getYear(), 12, 31);
				}
				while(toDate.compareTo(periodDate)>=0) {
					BigDecimal fee = getTotalFee(startDate, periodDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticPdFrom(dateFormat.format(startDate.toDate()));
					levReqestVo.setNticPdTo(dateFormat.format(periodDate.toDate()));
					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					nticCnt++;
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
					startDate=periodDate.plusDays(1);
					periodDate = periodDate.plusMonths(6);
					periodDate = periodDate.dayOfMonth().withMaximumValue();
				}
				if(toDate.compareTo(startDate)>0) {
					BigDecimal fee = getTotalFee(startDate, toDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					levReqestVo.setNticPdFrom(dateFormat.format(startDate));
					levReqestVo.setNticPdTo(dateFormat.format(toDate));
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
				}
			}
			else if("3".equals(rentVo.getNticMth())) {
				from = dateFormat.parse(rentVo.getGrUsagePdFrom());
				to = dateFormat.parse(rentVo.getGrUsagePdTo());
				fromDate = new LocalDate(from);
				toDate = new LocalDate(to);
				startDate = new LocalDate(fromDate);
				if(startDate.getMonthOfYear()<5) {
					periodDate = new LocalDate(startDate.getYear(), 4, 30);
				}
				else if(startDate.getMonthOfYear()<9) {
					periodDate = new LocalDate(startDate.getYear(), 8, 31);
				}
				else {
					periodDate = new LocalDate(startDate.getYear(), 12, 31);
				}
				while(toDate.compareTo(periodDate)>=0) {
					BigDecimal fee = getTotalFee(startDate, periodDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticPdFrom(dateFormat.format(startDate.toDate()));
					levReqestVo.setNticPdTo(dateFormat.format(periodDate.toDate()));
					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					nticCnt++;
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
					startDate=periodDate.plusDays(1);
					periodDate = periodDate.plusMonths(4);
					periodDate = periodDate.dayOfMonth().withMaximumValue();
				}
				if(toDate.compareTo(startDate)>0) {
					BigDecimal fee = getTotalFee(startDate, toDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					levReqestVo.setNticPdFrom(dateFormat.format(startDate));
					levReqestVo.setNticPdTo(dateFormat.format(toDate));
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
				}
			}
			else if("4".equals(rentVo.getNticMth())) {
				// 분기납
				from = dateFormat.parse(rentVo.getGrUsagePdFrom());
				to = dateFormat.parse(rentVo.getGrUsagePdTo());
				fromDate = new LocalDate(from);
				toDate = new LocalDate(to);
				startDate = new LocalDate(fromDate);
				if(startDate.getMonthOfYear()<4) {
					periodDate = new LocalDate(startDate.getYear(), 3, 31);
				}
				else if(startDate.getMonthOfYear()<7) {
					periodDate = new LocalDate(startDate.getYear(), 6, 30);
				}
				else if(startDate.getMonthOfYear()<10) {
					periodDate = new LocalDate(startDate.getYear(), 9, 30);
				}
				else {
					periodDate = new LocalDate(startDate.getYear(), 12, 31);
				}
				while(toDate.compareTo(periodDate)>=0) {
					BigDecimal fee = getTotalFee(startDate, periodDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticPdFrom(dateFormat.format(startDate.toDate()));
					levReqestVo.setNticPdTo(dateFormat.format(periodDate.toDate()));
					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					nticCnt++;
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
					startDate=periodDate.plusDays(1);
					periodDate = periodDate.plusMonths(3);
					periodDate = periodDate.dayOfMonth().withMaximumValue();
				}
				if(toDate.compareTo(startDate)>0) {
					BigDecimal fee = getTotalFee(startDate, toDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					levReqestVo.setNticPdFrom(dateFormat.format(startDate.toDate()));
					levReqestVo.setNticPdTo(dateFormat.format(toDate.toDate()));
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
				}
			}
			else if("5".equals(rentVo.getNticMth())) {
				// 월납
				from = dateFormat.parse(rentVo.getGrUsagePdFrom());
				to = dateFormat.parse(rentVo.getGrUsagePdTo());
				fromDate = new LocalDate(from);
				toDate = new LocalDate(to);
				startDate = new LocalDate(fromDate);
				periodDate = startDate.plusMonths(1);
				periodDate = periodDate.minusDays(1);
				while(toDate.compareTo(periodDate)>=0) {
					BigDecimal fee = getTotalFee(startDate, periodDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticPdFrom(dateFormat.format(startDate.toDate()));
					levReqestVo.setNticPdTo(dateFormat.format(periodDate.toDate()));
					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					nticCnt++;
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
					startDate=periodDate.plusDays(1);
					periodDate = periodDate.plusMonths(1);
					periodDate = periodDate.dayOfMonth().withMaximumValue();
				}
				if(toDate.compareTo(startDate)>0) {
					BigDecimal fee = getTotalFee(startDate, toDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					levReqestVo.setNticPdFrom(dateFormat.format(startDate.toDate()));
					levReqestVo.setNticPdTo(dateFormat.format(toDate.toDate()));
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
				}
			}
			else if("6".equals(rentVo.getNticMth())) {
				// 연납
				from = dateFormat.parse(rentVo.getGrUsagePdFrom());
				to = dateFormat.parse(rentVo.getGrUsagePdTo());
				fromDate = new LocalDate(from);
				toDate = new LocalDate(to);
				startDate = new LocalDate(fromDate);
				periodDate = new LocalDate(startDate.getYear(), 12, 31);
				while(toDate.compareTo(periodDate)>=0) {
					BigDecimal fee = getTotalFee(startDate, periodDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticPdFrom(dateFormat.format(startDate.toDate()));
					levReqestVo.setNticPdTo(dateFormat.format(periodDate.toDate()));
					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					nticCnt++;
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
					startDate=periodDate.plusDays(1);
					periodDate = periodDate.plusYears(1);
//					periodDate = periodDate.dayOfMonth().withMaximumValue();
				}
				if(toDate.compareTo(startDate)>0) {
					BigDecimal fee = getTotalFee(startDate, toDate, monthFee);

					levReqestVo.setFee(fee.toString());
					if("2".equals(rentVo.getTaxtSe())) {
						BigDecimal vat = fee.divide(new BigDecimal(10), -1, RoundingMode.CEILING);
						levReqestVo.setVat(vat.toString());
						BigDecimal nticAmount = fee.add(vat);
						levReqestVo.setNticAmt(nticAmount.toString());
					}
					else {
						levReqestVo.setVat("0");
						levReqestVo.setNticAmt(fee.toString());
					}

					levReqestVo.setNticCnt(Integer.toString(nticCnt));
					levReqestVo.setNticPdFrom(dateFormat.format(startDate.toDate()));
					levReqestVo.setNticPdTo(dateFormat.format(toDate.toDate()));
					gamHtldRentMngtDao.insertHtldRentBill(levReqestVo);
				}
			}
			else throw processException("fail.levinsert.type");
		}
	}

	public BigDecimal getTotalFee(LocalDate fromDate, LocalDate toDate, BigDecimal monthFee) {
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
			totalFee = totalFee.add(bd.setScale(-1, RoundingMode.CEILING));
		}
		int toDay=toDate.getDayOfMonth();
		if(toDay>1) {
			LocalDate endOfMonth = fromDate.dayOfMonth().withMaximumValue();

			BigDecimal bd = new BigDecimal(toDay-1);
			BigDecimal div = new BigDecimal(endOfMonth.getDayOfMonth());
			bd = bd.divide(div, 5, RoundingMode.CEILING);
			bd = bd.multiply(monthFee);
			totalFee = totalFee.add(bd.setScale(-1, RoundingMode.CEILING));
		}
		totalFee = totalFee.setScale(-1, RoundingMode.CEILING);

		return totalFee;
	}


    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
    public GamHtldRentMngtVO insertHtldRentMngtExtend(GamHtldRentMngtVO vo) throws Exception {
    	GamHtldRentMngtVO gamExtendVo = gamHtldRentMngtDao.selectHtldRentMngtExtend(vo);
    	GamHtldRentMngtVO gamVo = new GamHtldRentMngtVO();
    	gamVo.setQuayGroupCd(gamExtendVo.getQuayGroupCd());
    	gamVo.setPrtAtCode(gamExtendVo.getPrtAtCode());
    	gamVo.setMngYear(gamExtendVo.getMngYear());
    	gamVo.setMngNo(gamExtendVo.getMngNo());
    	gamVo.setMngCnt(gamExtendVo.getMngCnt());
    	Integer n = gamHtldRentMngtDao.selectHtldRentMngtExist(gamExtendVo);	// 연장된 자료가 존재하는지
    	if(n>0) processException("gam.asset.rent.extend.reject1");
		gamHtldRentMngtDao.insertHtldRentMngt(gamExtendVo);
		List detailList = gamHtldRentMngtDao.selectHtldRentMngtExtendDetailList(vo);
		for(int i=0; i<detailList.size(); i++) {
			GamHtldRentMngtDetailVO detail = (GamHtldRentMngtDetailVO)detailList.get(i);
			detail.setRegUsr(gamExtendVo.getRegUsr());
			detail.setMngNo(gamExtendVo.getMngNo());
			gamHtldRentMngtDao.insertHtldRentMngtDetail(detail);
		}
		return gamExtendVo;
	}

	/**
	 * 배후단지임대정보를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngt(GamHtldRentMngtVO rentVo, List<GamHtldRentMngtDetailVO> createList,  List<GamHtldRentMngtDetailVO> updateList,  List<GamHtldRentMngtDetailVO> deleteList) throws Exception {
		int i=0;
		String updtId=rentVo.getUpdUsr();
		List<EgovMap> list;
        ObjectMapper mapper = new ObjectMapper();

		//String deptCd=rentVo.getDeptcd();

		int nticCnt = gamHtldRentMngtDao.selectHtldRentMngtNticLevReqestCnt(rentVo);

		if(nticCnt!=0) {
			throw processException("fail.rent.updateNtic.msg");
		}

		list = gamHtldRentMngtDao.selectHtldRentMngtDetailList(rentVo);
    	List<GamHtldRentMngtDetailVO> detailList=new ArrayList();
    	for(i=0; i<list.size(); i++) {
    		GamHtldRentMngtDetailVO vo = mapper.convertValue(list.get(i), GamHtldRentMngtDetailVO.class);
    		detailList.add(vo);
    	}


		for(i=0; i<deleteList.size(); i++) {
			GamHtldRentMngtDetailVO d= deleteList.get(i);
			gamHtldRentMngtDao.deleteHtldRentMngtDetail(d);
			for(int j=0; j<detailList.size(); j++) {
				GamHtldRentMngtDetailVO item=detailList.get(j);
				if(item.getAssetsUsageSeq().equals(d.getAssetsUsageSeq())) {
					detailList.remove(j);
					break;
				}
			}
		}
		for(i=0; i<updateList.size(); i++) {
			GamHtldRentMngtDetailVO d= updateList.get(i);
			d.setUpdUsr(updtId);
			gamHtldRentMngtDao.updateHtldRentMngtDetail(d);
			for(int j=0; j<list.size(); j++) {
				GamHtldRentMngtDetailVO item=detailList.get(j);
				if(item.getAssetsUsageSeq().equals(d.getAssetsUsageSeq())) {
					detailList.set(j, d);
					break;
				}
			}
		}
		for(i=0; i<createList.size(); i++) {
			GamHtldRentMngtDetailVO d= createList.get(i);
			d.setRegUsr(updtId);
			d.setPrtAtCode(rentVo.getPrtAtCode());
			d.setMngYear(rentVo.getMngYear());
			d.setMngNo(rentVo.getMngNo());
			d.setMngCnt(rentVo.getMngCnt());
			d.setQuayGroupCd(rentVo.getQuayGroupCd());
			gamHtldRentMngtDao.insertHtldRentMngtDetail(d);
			detailList.add(d);
		}

		gamHtldRentMngtDao.updateHtldRentMngt(rentVo);

		insertHtldRentLevReqest(rentVo, detailList);
	}

	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentMngtDetailList(GamHtldRentMngtVO vo) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtDetailList(vo);
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentMngtLevReqestCnt(GamHtldRentMngtVO vo) throws Exception {
		return gamHtldRentMngtDao.selectHtldRentMngtLevReqestCnt(vo);
	}

    /**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngt(GamHtldRentMngtVO vo) throws Exception {
//		GamHtldRentMngtVO detail = gamHtldRentMngtDao.selectHtldRentMngtDetailPk(vo);
		int nticCnt = gamHtldRentMngtDao.selectHtldRentMngtNticLevReqestCnt(vo);

		if(nticCnt!=0) {
			processException("gam.asset.rent.delete.reject1");
		}

		gamHtldRentMngtDao.deleteHtldRentMngtPhoto(vo); //배후단지임대 사진정보 삭제

		gamHtldRentMngtDao.deleteHtldRentMngtDetails(vo); //배후단지임대 상세정보 삭제

		gamHtldRentMngtDao.deleteHtldRentMngt(vo); // 배후단지임대정보 삭제
	}

	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtPrmisnInfo(GamHtldRentMngtVO searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtPrmisnInfo(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtComment(GamHtldRentMngtVO vo) throws Exception {
		gamHtldRentMngtDao.updateHtldRentMngtComment(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamHtldRentMngtDao.selectCofixInfo();
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public EgovMap selectHtldRentMngtCofixInfo(Map searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtCofixInfo(searchVO);
    }

    /**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public EgovMap selectHtldRentMngtCofixInfoMax(Map searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtCofixInfoMax(searchVO);
    }

    /* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService#selectChargeKndList()
	 */
	@Override
	public List selectChargeKndList(GamHtldRentMngtVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldRentMngtDao.selectChargeKndList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldRentMngtFileList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentAttachFileVO)
	 */
	@Override
	public List selectHtldRentMngtFileList(GamHtldRentMngtVO vo)
			throws Exception {
		return gamHtldRentMngtDao.selectHtldRentMngtFileList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldAssessList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO)
	 */
	@Override
	public List selectHtldAssessList(GamHtldRentDefaultVO searchVO)
			throws Exception {
        return gamHtldRentMngtDao.selectHtldAssessList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldAssessSum(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO)
	 */
	@Override
	public EgovMap selectHtldAssessSum(GamHtldRentDefaultVO searchVO)
			throws Exception {
        return gamHtldRentMngtDao.selectHtldAssessSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldNticRcivList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO)
	 */
	@Override
	public List selectHtldNticRcivList(GamHtldRentDefaultVO searchVO)
			throws Exception {
        return gamHtldRentMngtDao.selectHtldNticRcivList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldNticRcivSum(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO)
	 */
	@Override
	public EgovMap selectHtldNticRcivSum(GamHtldRentDefaultVO searchVO)
			throws Exception {
        return gamHtldRentMngtDao.selectHtldNticRcivSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#createHtldRentMngtFirst()
	 */
	@Override
	public void createHtldRentMngtFirst() throws Exception {
		GamHtldRentDefaultVO searchVO = new GamHtldRentDefaultVO();
		searchVO.setRecordCountPerPage(9999);
		List<EgovMap> rentList=gamHtldRentMngtDao.selectHtldRentMngtList(searchVO);
		List<GamHtldRentMngtVO> masterList=new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0; i<rentList.size(); i++) {
			GamHtldRentMngtVO master = mapper.convertValue(rentList.get(i), GamHtldRentMngtVO.class);
			List<EgovMap> list = gamHtldRentMngtDao.selectHtldRentMngtDetailList(master);
	    	List<GamHtldRentMngtDetailVO> detailList=new ArrayList();
	    	for(int j=0; j<list.size(); j++) {
	    		GamHtldRentMngtDetailVO vo = mapper.convertValue(list.get(j), GamHtldRentMngtDetailVO.class);
	    		detailList.add(vo);
	    	}
	    	try {
	    		insertHtldRentLevReqest(master, detailList);
	    	}
	    	catch(Exception e) {
	    		log.warn("배후단지 임대정보 " + master.getMngYear() + "-"+master.getMngNo()+"-"+master.getMngCnt()+"의 사용료를 생성 할 수 없습니다. ( ex: "+e.getMessage()+" )");
	    	}
    	}
	}

}