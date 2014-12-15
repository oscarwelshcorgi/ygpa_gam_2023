/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsMngNoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsMngNoVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupFcltsMngNoService")
public class GamPopupFcltsMngNoServiceImpl extends AbstractServiceImpl implements GamPopupFcltsMngNoService {
	@Resource(name="gamPopupFcltsMngNoDao")
	private GamPopupFcltsMngNoDao gamPopupFcltsMngNoDao;
	
	/**
	 * 시설물 관리 그룹 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List<?> selectFcltsMngNoList(GamPopupFcltsMngNoVO searchVO) throws Exception {
    	return gamPopupFcltsMngNoDao.selectFcltsMngNoList(searchVO);
    }

    /**
	 * 시설물 관리 그룹 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFcltsMngNoListTotCnt(GamPopupFcltsMngNoVO searchVO) throws Exception {
    	return gamPopupFcltsMngNoDao.selectFcltsMngNoListTotCnt(searchVO);
    }
}
