/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupCtrtNoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupCtrtNoVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 26.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamPopupCtrtNoService")
public class GamPopupCtrtNoServiceImpl extends AbstractServiceImpl implements GamPopupCtrtNoService {
	@Resource(name="gamPopupCtrtNoDao")
	private GamPopupCtrtNoDao gamPopupCtrtNoDao;
	
	/**
	 * 계약정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCtrtNoList(GamPopupCtrtNoVO searchVO) throws Exception {
    	return gamPopupCtrtNoDao.selectCtrtNoList(searchVO);
    }

    /**
	 * 계약정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCtrtNoListTotCnt(GamPopupCtrtNoVO searchVO) throws Exception {
    	return gamPopupCtrtNoDao.selectCtrtNoListTotCnt(searchVO);
    }

}
