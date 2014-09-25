/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocPayCdVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamPopupSocPayCdDao")
public class GamPopupSocPayCdDao extends YGPAAbstractDAO {	
	/**
	 * 업체정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체정보 목록
	 * @exception Exception
	 */
    public List selectSocPayCdList(GamPopupSocPayCdVO searchVO) throws Exception {
    	System.out.println("##################test");
        return list("gamPopupSocPayCdDao.selectSocPayCdList_S", searchVO);
    }

    /**
	 * 업체정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체정보 목록 총 갯수
	 * @exception
	 */
    public int selectSocPayCdTotCnt(GamPopupSocPayCdVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupSocPayCdDao.selectSocPayCdTotCnt_S", searchVO);
    }
}
