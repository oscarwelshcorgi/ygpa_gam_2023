/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldAddNticService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldAddNticVO;

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
@Service("gamPopupHtldAddNticService")
public class GamPopupHtldAddNticServiceImpl extends AbstractServiceImpl implements GamPopupHtldAddNticService {
	@Resource(name="gamPopupHtldAddNticDao")
    private GamPopupHtldAddNticDao gamPopupHtldAddNticDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 추가정산 계약정보 조회
	 * @param GamPopupHtldBizAssessVO
	 * @return Map 면적평가정산
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldAddNticCtrtDetail(GamPopupHtldAddNticVO searchVO) throws Exception {
		return gamPopupHtldAddNticDao.selectHtldAddNticCtrtDetail(searchVO);
	}
	
	/**
	 * 추가정산 조회
	 * @param GamPopupHtldBizAssessVO
	 * @return Map 면적평가정산
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldAddNticDetail(GamPopupHtldAddNticVO searchVO) throws Exception {
		return gamPopupHtldAddNticDao.selectHtldAddNticDetail(searchVO);
	}

	/**
	 * 추가정산 등록
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	public void insertAddNtic(GamPopupHtldAddNticVO vo) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate histDt = new LocalDate(dateFormat.parse(vo.getHistDt()));
		LocalDate nticBeginDt = getQuarterStartDate(histDt);
		LocalDate nticEndDt = getQuarterEndDate(histDt);
		if("6".equals(vo.getPaySe())) {
			nticBeginDt = new LocalDate(histDt.getYear(), 1, 1);
			nticEndDt = new LocalDate(histDt.getYear(), 12, 31);
		}
		vo.setRntfeeSeq(gamPopupHtldAddNticDao.selectNextRntfeeSeq(vo));
		vo.setNticBeginDt(nticBeginDt.toString());
		vo.setNticEndDt(nticEndDt.toString());
		gamPopupHtldAddNticDao.insertAddNtic(vo);
	}

	/**
	 * 추가정산 수정
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	public void updateAddNtic(GamPopupHtldAddNticVO vo) throws Exception {
		gamPopupHtldAddNticDao.updateAddNtic(vo);
	}
	
	/**
	 * 추가정산 삭제
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	public void deleteAddNtic(GamPopupHtldAddNticVO vo) throws Exception {
		gamPopupHtldAddNticDao.deleteAddNtic(vo);
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
