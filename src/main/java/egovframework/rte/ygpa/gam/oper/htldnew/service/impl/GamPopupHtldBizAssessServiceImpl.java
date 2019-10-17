/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtDetailVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRntfeeBizAssessVO;

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

	@Resource(name="gamHtldRentCtrtHistDao")
    private GamHtldRentCtrtHistDao gamHtldRentCtrtHistDao;

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
	@SuppressWarnings("unchecked")
	public void updateBizAssess(GamPopupHtldBizAssessVO vo, String updUsr) throws Exception {

		if(vo.getAseApplcBegin()==null || "".equals(vo.getAseApplcBegin())) return;
		if(vo.getAseApplcEnd()==null || "".equals(vo.getAseApplcEnd())) return;

		deleteHtldRentBizAssess(vo);
		//임대료에 실적평가 정산부분 추가
		insertHtldRentBizAssess(vo);
	}

	/**
	 * 실적평가 등록
	 * @param GamPopupHtldBizAssessVO
	 * @return
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteBizAssess(GamPopupHtldBizAssessVO vo, String updUsr) throws Exception {
		//vo의 이력날짜에 최신 데이터를 가져온다.
		GamHtldRentCtrtVO rentVo = gamHtldRentCtrtHistDao.selectHtldRentCtrt(vo);
		List<GamHtldRentCtrtDetailVO> rentDetailList = (List<GamHtldRentCtrtDetailVO>) gamHtldRentCtrtHistDao.selectHtldRentCtrtDetail(vo);

		// 이력번호 생성
		String histSeq = gamHtldRentCtrtHistDao.selectNextHistSeq(vo);

		// 이력데이터와 실적평가데이터 추가
		rentVo.setHistDt(vo.getHistDt());
		rentVo.setHistSeq(histSeq);
		rentVo.setRegUsr(updUsr);
		rentVo.setUpdUsr(updUsr);
		gamHtldRentCtrtHistDao.insertHtldRentCtrtHist(rentVo);

		for(GamHtldRentCtrtDetailVO detailItem : rentDetailList) {
			detailItem.setHistDt(vo.getHistDt());
			detailItem.setHistSeq(histSeq);
			detailItem.setRegUsr(updUsr);
			detailItem.setUpdUsr(updUsr);
			if(detailItem.getRegistSeq().equals(vo.getRegistSeq())) {
				detailItem.setAseApplcBegin(null);
				detailItem.setAseApplcEnd(null);
				detailItem.setAseRntfee(null);
				detailItem.setApplcRsn(null);
			}
			gamHtldRentCtrtHistDao.insertHtldRentCtrtDetailHist(detailItem);
		}
		//임대료에 있는 실적정산분 삭제
		deleteHtldRentBizAssess(vo);
	}

	protected void insertHtldRentBizAssess(GamPopupHtldBizAssessVO vo) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate detailPdBeginDate = new LocalDate(dateFormat.parse(vo.getDetailPdBegin()));
		LocalDate aseApplcBeginDate = new LocalDate(dateFormat.parse(vo.getAseApplcBegin()));
		LocalDate aseApplcEndDate = new LocalDate(dateFormat.parse(vo.getAseApplcEnd()));
		LocalDate histDt = new LocalDate(dateFormat.parse(vo.getHistDt()));
		LocalDate quarterStartDate = getQuarterStartDate(histDt);
		if("6".equals(vo.getPaySe())) {
			quarterStartDate = new LocalDate(histDt.getYear(), 1, 1);
		}
		LocalDate compareDate = (quarterStartDate.compareTo(detailPdBeginDate) > 0) ? quarterStartDate : detailPdBeginDate;
		if(aseApplcBeginDate.compareTo(compareDate)<0) {
			LocalDate startDate = aseApplcBeginDate;
			LocalDate endDate = compareDate.minusDays(1);
			if(compareDate.compareTo(aseApplcEndDate) > 0) {
				endDate = aseApplcEndDate;
			}
			BigDecimal rentAr = new BigDecimal(vo.getRentAr().trim());
			BigDecimal applcRntfee = new BigDecimal(vo.getApplcRntfee().trim());
			BigDecimal aseRntfee = new BigDecimal(vo.getAseRntfee().trim());
			aseRntfee = aseRntfee.subtract(applcRntfee);
			BigDecimal aseMonthFee = new BigDecimal(0);
			if("1".equals(vo.getPriceSe())) {
				aseMonthFee = rentAr.multiply(aseRntfee);
			} else {
				aseMonthFee = aseRntfee;
			}
			BigDecimal rntfee = getTotalFee(startDate, endDate, aseMonthFee);

			LocalDate nticBeginDt = getQuarterStartDate(histDt);
			LocalDate nticEndDt = getQuarterEndDate(histDt);
			if("6".equals(vo.getPaySe())) {
				nticBeginDt = new LocalDate(histDt.getYear(), 1, 1);
				nticEndDt = new LocalDate(histDt.getYear(), 12, 31);
			}

			GamPopupHtldRntfeeBizAssessVO insertVO = new GamPopupHtldRntfeeBizAssessVO();
			insertVO.setMngYear(vo.getMngYear());
			insertVO.setMngNo(vo.getMngNo());
			insertVO.setMngSeq(vo.getMngSeq());
			insertVO.setRntfeeSeq(gamPopupHtldBizAssessDao.selectNextRntfeeSeq(vo));
			insertVO.setRentDetailRegistSeq(vo.getRegistSeq());
			insertVO.setNticBeginDt(nticBeginDt.toString());
			insertVO.setNticEndDt(nticEndDt.toString());
			insertVO.setApplcBeginDt(startDate.toString());
			insertVO.setApplcEndDt(endDate.toString());
			insertVO.setApplcRntfee(aseRntfee.toString());
			insertVO.setRntfee(rntfee.toString());
			insertVO.setRegUsr(vo.getUpdUsr());

			gamPopupHtldBizAssessDao.insertRntfeeBizAssess(insertVO);
		}
	}

	protected void deleteHtldRentBizAssess(GamPopupHtldBizAssessVO vo) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate histDt = new LocalDate(dateFormat.parse(vo.getHistDt()));
		LocalDate nticBeginDt = getQuarterStartDate(histDt);
		LocalDate nticEndDt = getQuarterEndDate(histDt);
		if("6".equals(vo.getPaySe())) {
			nticBeginDt = new LocalDate(histDt.getYear(), 1, 1);
			nticEndDt = new LocalDate(histDt.getYear(), 12, 31);
		}

		GamPopupHtldRntfeeBizAssessVO deleteVO = new GamPopupHtldRntfeeBizAssessVO();
		deleteVO.setMngYear(vo.getMngYear());
		deleteVO.setMngNo(vo.getMngNo());
		deleteVO.setMngSeq(vo.getMngSeq());
		deleteVO.setRentDetailRegistSeq(vo.getRegistSeq());
		deleteVO.setNticBeginDt(nticBeginDt.toString());
		deleteVO.setNticEndDt(nticEndDt.toString());

		gamPopupHtldBizAssessDao.deleteRntfeeBizAssess(deleteVO);
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

