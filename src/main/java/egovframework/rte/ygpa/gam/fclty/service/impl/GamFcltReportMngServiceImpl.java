/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltReportMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltReportMngVO;

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

@Service("gamFcltReportMngService")
public class GamFcltReportMngServiceImpl extends AbstractServiceImpl implements GamFcltReportMngService{
	
	@Resource(name="gamFcltReportMngDao")
	private GamFcltReportMngDao gamFcltReportMngDao;
	
	
	/**
	 * 시설물관리대장인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */		
	public EgovMap selectFcltReportMng(GamFcltReportMngVO searchVO) throws Exception {
		return gamFcltReportMngDao.selectFcltReportMng(searchVO);
	}
	
	
	/**
	 * 안전점검 및 정밀안전진단계획 인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	public List<?> selectFcleQcMngList(GamFcltReportMngVO vo) throws Exception{
		return (List<?>)gamFcltReportMngDao.selectFcleQcMngList(vo);
	}
	
	
	/**
	 * 안전점검 및 정밀안전진단계획총갯수
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	public int selectFcleQcMngListTotalCount(GamFcltReportMngVO vo) throws Exception{
		return gamFcltReportMngDao.selectFcleQcMngListTotalCount(vo);
	}
	
	
	/**
	 * 보수.보강계획 리스트 인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	public List<?> selectFcleMntnRprMngList(GamFcltReportMngVO vo) throws Exception{
		return (List<?>)gamFcltReportMngDao.selectFcleMntnRprMngList(vo);
	}
	
	
	/**
	 * 보수.보강계획 리스트 총갯수
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	public int selectFcleMntnRprMngListTotalCount(GamFcltReportMngVO vo) throws Exception{
		return gamFcltReportMngDao.selectFcleMntnRprMngListTotalCount(vo);
	}

}
