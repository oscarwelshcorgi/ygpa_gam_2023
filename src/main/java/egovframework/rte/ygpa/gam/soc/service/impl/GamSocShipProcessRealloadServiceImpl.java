/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessRealloadService;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessRealloadVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 15.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamSocShipProcessRealloadService")
public class GamSocShipProcessRealloadServiceImpl extends AbstractServiceImpl implements GamSocShipProcessRealloadService {
	
	@Resource(name="gamSocShipProcessRealloadDao")
    private GamSocShipProcessRealloadDao gamSocShipProcessRealloadDao;


	/**
	 * 투자비보전 처리실적 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	@Override
	public List selectSocShipProcessRealloadList(GamSocShipProcessRealloadVO searchVO) throws Exception {
		return gamSocShipProcessRealloadDao.selectSocShipProcessRealloadList(searchVO);
	}
	
	
	/**
	 * 투자비보전 처리실적 총갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전 처리실적 총갯수
	 * @exception Exception
	 */
	public int selectSocShipProcessRealloadListTotCnt(GamSocShipProcessRealloadVO searchVO)  throws Exception{
		return gamSocShipProcessRealloadDao.selectSocShipProcessRealloadListTotCnt(searchVO);
	}
	
	
	/**
	 * 투자비보전 처리실적 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	@Override
	public List selectSocShipProcessRealloadDetail(GamSocShipProcessRealloadVO searchVO) throws Exception {
		return gamSocShipProcessRealloadDao.selectSocShipProcessRealloadDetail(searchVO);
	}
	

	/**
	 * 투자비보전 처리실적 상세내역 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	@Override
	public GamSocShipProcessRealloadVO selectSocShipProcessRealloadDetailSum(GamSocShipProcessRealloadVO searchVO) throws Exception {
		return gamSocShipProcessRealloadDao.selectSocShipProcessRealloadDetailSum(searchVO);
	}
	
	
	/**
	 * 투자비보전 처리실적 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	@Override
	public List selectSocShipProcessRealloadListPrint(GamSocShipProcessRealloadVO searchVO) throws Exception {
		return gamSocShipProcessRealloadDao.selectSocShipProcessRealloadListPrint(searchVO);
	}
	
	
	/**
	 * 투자비보전 처리실적 총갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전 처리실적 총갯수
	 * @exception Exception
	 */
	public GamSocShipProcessRealloadVO selectSocShipProcessRealloadListPrintTotCnt(GamSocShipProcessRealloadVO searchVO)  throws Exception{
		return gamSocShipProcessRealloadDao.selectSocShipProcessRealloadListPrintTotCnt(searchVO);
	}

}
