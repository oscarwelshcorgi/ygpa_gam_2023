/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessSetoffLgerService;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessSetoffLgerVO;


/**
 * 
 * @author HNJ
 * @since 2014. 10. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 17.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamSocShipProcessSetoffLgerService")
public class GamSocShipProcessSetoffLgerServiceImpl extends AbstractServiceImpl implements GamSocShipProcessSetoffLgerService {

	@Resource(name="gamSocShipProcessSetoffLgerDao")
    private GamSocShipProcessSetoffLgerDAO gamSocShipProcessSetoffLgerDao;


	/**
	 * 투자비보전(선석)상계처리대장 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	@Override
	public List selectSocShipProcessSetoffLgerList(GamSocShipProcessSetoffLgerVO searchVO) throws Exception {
		return gamSocShipProcessSetoffLgerDao.selectSocShipProcessSetoffLgerList(searchVO);
	}
	
	
	/**
	 * 투자비보전(선석)상계처리대장 총갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전(선석)상계처리대장 총갯수
	 * @exception Exception
	 */
	public int selectSocShipProcessSetoffLgerListTotCnt(GamSocShipProcessSetoffLgerVO searchVO)  throws Exception{
		return gamSocShipProcessSetoffLgerDao.selectSocShipProcessSetoffLgerListTotCnt(searchVO);
	}
	
	
	/**
	 * 투자비보전(선석)상계처리대장 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	@Override
	public List selectSocShipProcessSetoffLgerDetail(GamSocShipProcessSetoffLgerVO searchVO) throws Exception {
		return gamSocShipProcessSetoffLgerDao.selectSocShipProcessSetoffLgerDetail(searchVO);
	}
	

	/**
	 * 투자비보전(선석)상계처리대장 상세내역 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	@Override
	public GamSocShipProcessSetoffLgerVO selectSocShipProcessSetoffLgerDetailSum(GamSocShipProcessSetoffLgerVO searchVO) throws Exception {
		return gamSocShipProcessSetoffLgerDao.selectSocShipProcessSetoffLgerDetailSum(searchVO);
	}

}
