/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author HNJ
 * @since 2015. 2. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 6.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltReportMngService {
	
	
	/**
	 * 시설물관리대장인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	EgovMap selectFcltReportMng(GamFcltReportMngVO vo) throws Exception;
	
	
	/**
	 * 안전점검 및 정밀안전진단계획 인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	List<?> selectFcleQcMngList(GamFcltReportMngVO vo) throws Exception;
	
	
	/**
	 * 안전점검 및 정밀안전진단계획총갯수
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	int selectFcleQcMngListTotalCount(GamFcltReportMngVO vo) throws Exception;
	
	
	/**
	 * 보수.보강계획 리스트 인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	List<?> selectFcleMntnRprMngList(GamFcltReportMngVO vo) throws Exception;
	
	
	/**
	 * 보수.보강계획 리스트 총갯수
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	int selectFcleMntnRprMngListTotalCount(GamFcltReportMngVO vo) throws Exception;

}
