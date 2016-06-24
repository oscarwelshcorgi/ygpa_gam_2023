/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticReportService;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 19.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamHtldRentNticReportService")
public class GamHtldRentNticReportServiceImpl extends AbstractServiceImpl implements GamHtldRentNticReportService {
	@Resource(name="gamHtldRentNticReportDao")
	GamHtldRentNticReportDao gamHtldRentNticReportDao;
	
	/**
	 * 고지서 출력 마스터 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticPrintMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return gamHtldRentNticReportDao.selectNticPrintMaster(searchVO);
	}

	/**
	 * 연체 고지서 출력 마스터 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectArrrgNticPrintMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return gamHtldRentNticReportDao.selectArrrgNticPrintMaster(searchVO);
	}

	/**
	 * 출력용 고지 상세 리스트 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<?> selectNticIssueList(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return getNticDtlsReportList((List<EgovMap>)gamHtldRentNticReportDao.selectNticIssueList(searchVO));
	}
	
	protected List<EgovMap> getNticDtlsReportList(List<EgovMap> list) {
		List<EgovMap> resultList = new ArrayList<EgovMap>();
		for(EgovMap item : list) {
			String rntfeeSe = (item.get("rntfeeSe") != null) ? (String) item.get("rntfeeSe") : "";
			if("0".equals(rntfeeSe)) {
				String rentArStr = (item.get("rentArStr") != null) ? (String) item.get("rentArStr") : "";
				String rentArSe = (item.get("rentArSe") != null) ? (String) item.get("rentArSe") : "";
				String rentArSeNm = (item.get("rentArSeNm") != null) ? (String) item.get("rentArSeNm") : "";
				if(!"0".equals(rentArSe)) {
					rentArStr = rentArStr + "/" + rentArSeNm;
					if("3".equals(rentArSe)) {
						rentArStr = rentArSeNm;
					}
				}
				item.put("rentArStr", rentArStr);
				String nticDt = (String) item.get("nticDt");
				String aseApplcBegin = (String) item.get("aseApplcBegin");
				String aseApplcEnd =  (String) item.get("aseApplcEnd");
				if(aseApplcBegin != null) {
					if((nticDt.compareTo(aseApplcBegin) >= 0) && (nticDt.compareTo(aseApplcEnd) <= 0)) {
						item.put("applcRntfee", item.get("aseRntfee"));
						item.put("applcRntfeeSe", "1");
						item.put("applcRntfeeStr", item.get("aseRntfeeStr") + "(실적)");
					}
				}
			}
			resultList.add(item);
		}
		return resultList;
	}
}
