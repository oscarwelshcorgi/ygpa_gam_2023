/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
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
@Repository("gamPopupFcltsMngNoDao")
public class GamPopupFcltsMngNoDao extends YGPAAbstractDAO {
    /**
	 * 시설물번호 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectFcltsMngNoList(GamPopupFcltsMngNoVO searchVO) throws Exception {
    	return list("gamPopupFcltsMngNoDao.selectFcltsMngNoList_D", searchVO);
    }

    /**
	 * 시설물번호 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFcltsMngNoListTotCnt(GamPopupFcltsMngNoVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupFcltsMngNoDao.selectFcltsMngNoListTotCnt_S", searchVO);
    }
}

