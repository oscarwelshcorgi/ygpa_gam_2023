/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyLgerVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocPrtFcltyFeeExmpRqestSttusService;
import egovframework.rte.ygpa.gam.soc.service.GamSocPrtFcltyFeeExmpRqestSttusVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 16.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamSocPrtFcltyFeeExmpRqestSttusService")
public class GamSocPrtFcltyFeeExmpRqestSttusServiceImpl extends AbstractServiceImpl implements GamSocPrtFcltyFeeExmpRqestSttusService {
	@Resource(name="gamSocPrtFcltyFeeExmpRqestSttusDao")
	private GamSocPrtFcltyFeeExmpRqestSttusDAO gamSocPrtFcltyFeeExmpRqestSttusDao;
	
	/**
	 * 항만시설사용료 면제요청현황 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용료 면제요청현황  리스트
	 * @exception
	 */
	public List selectSocPrtFcltyFeeExmpRqestSttusList(GamSocPrtFcltyFeeExmpRqestSttusVO searchVO) {
		return gamSocPrtFcltyFeeExmpRqestSttusDao.selectSocPrtFcltyFeeExmpRqestSttusList(searchVO);
	}

	/**
	 * 항만시설사용료 면제요청현황 리스트의 총계를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총계VO
	 * @exception
	 */
	public GamSocPrtFcltyFeeExmpRqestSttusVO selectSocPrtFcltyFeeExmpRqestSttusListTotSum(GamSocPrtFcltyFeeExmpRqestSttusVO searchVO) {
		return gamSocPrtFcltyFeeExmpRqestSttusDao.selectSocPrtFcltyFeeExmpRqestSttusListTotSum(searchVO);
	}
}
