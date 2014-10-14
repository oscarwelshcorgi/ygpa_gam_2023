/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyDtlsService;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyDtlsVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 14.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamSocApplyDtlsService")
public class GamSocApplyDtlsServiceImpl extends AbstractServiceImpl implements GamSocApplyDtlsService {
	@Resource(name="gamSocApplyDtlsDao")
	private GamSocApplyDtlsDAO gamSocApplyDtlsDao;
	
	/**
	 * 투자비보전신청내역 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역 리스트
	 * @exception
	 */	
	public List selectSocApplyDtlsList(GamSocApplyDtlsVO searchVO) {
		return gamSocApplyDtlsDao.selectSocApplyDtlsList(searchVO);
	}

	/**
	 * 투자비보전신청내역 리스트의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */	
	public int selectSocApplyDtlsListTotCnt(GamSocApplyDtlsVO searchVO) {
		return gamSocApplyDtlsDao.selectSocApplyDtlsListTotCnt(searchVO);
	}
}
