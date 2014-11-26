/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
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

@Repository("gamPopupCtrtNoDao")
public class GamPopupCtrtNoDao extends YGPAAbstractDAO {
	/**
	 * 계약정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCtrtNoList(GamPopupCtrtNoVO searchVO) throws Exception {
    	return list("gamPopupCtrtNoDao.selectCtrtNoList_D", searchVO);
    }

    /**
	 * 계약정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCtrtNoListTotCnt(GamPopupCtrtNoVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupCtrtNoDao.selectCtrtNoListTotCnt_S", searchVO);
    }
}

