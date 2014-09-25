/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocFacCdVO;

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

@Repository("gamPopupSocFacCdDao")
public class GamPopupSocFacCdDao extends YGPAAbstractDAO {	
	/**
	 * 시설 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 시설 목록
	 * @exception Exception
	 */
    public List selectSocFacCdList(GamPopupSocFacCdVO searchVO) throws Exception {
        return list("gamPopupSocFacCdDao.selectSocFacCdList_S", searchVO);
    }

    /**
	 * 시설 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 시설 목록 총 갯수
	 * @exception
	 */
    public int selectSocFacCdTotCnt(GamPopupSocFacCdVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupSocFacCdDao.selectSocFacCdTotCnt_S", searchVO);
    }
}
