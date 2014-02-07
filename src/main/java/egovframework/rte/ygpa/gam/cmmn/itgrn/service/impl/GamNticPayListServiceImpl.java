/**
 * 
 */
package egovframework.rte.ygpa.gam.cmmn.itgrn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListService;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListVO;

/**
 * 
 * @author kok
 * @since 2014. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamNticPayListService")
public class GamNticPayListServiceImpl extends AbstractServiceImpl implements GamNticPayListService{

	@Resource(name="gamNticPayListDao")
    private GamNticPayListDao gamNticPayListDao;
	

	/**
	 * 납부현황목록조회
	 */
	public List<ComDefaultVO> selectNticPayList(ComDefaultVO vo) throws Exception {
   		return (List<ComDefaultVO>)gamNticPayListDao.selectNticPayList(vo);
	}
	
	
	/**
	 * 납부현황목록조회 카운트
	 */
	public int selectNticPayListTotCnt(ComDefaultVO vo) throws Exception {
		return gamNticPayListDao.selectNticPayListTotCnt(vo);
    }
	
	
	/**
	 * 납부현황목록조회 상세화면
	 * @param vo
	 * @return GamFcltyManageVO
	 */
	public GamNticPayListVO gamNticPayListSelectView(GamNticPayListVO vo) {
		GamNticPayListVO nticPayListVO = gamNticPayListDao.gamNticPayListSelectView(vo);		
		return nticPayListVO;
	}
}