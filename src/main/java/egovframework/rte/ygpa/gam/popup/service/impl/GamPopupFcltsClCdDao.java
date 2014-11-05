/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFacilInfoVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsClCdVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 5.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamPopupFcltsClCdDao")
public class GamPopupFcltsClCdDao extends YGPAAbstractDAO {
    /**
	 * 시설물 분류 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectFcltsClCdList(GamPopupFcltsClCdVO searchVO) throws Exception {
    	return list("gamPopupFcltsClCdDao.selectFcltsClCdList", searchVO);
    }

    /**
	 * 시설물 분류 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFcltsClCdListTotCnt(GamPopupFcltsClCdVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupFcltsClCdDao.selectFcltsClCdListTotCnt", searchVO);
    }
}
