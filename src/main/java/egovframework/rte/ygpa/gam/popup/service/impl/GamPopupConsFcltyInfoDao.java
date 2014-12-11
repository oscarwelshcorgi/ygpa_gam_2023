/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupConsFcltyInfoVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsClCdVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 18.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 18.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamPopupConsFcltyInfoDao")
public class GamPopupConsFcltyInfoDao extends YGPAAbstractDAO {
    /**
	 * 건축시설물 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectConsFcltyInfoList(GamPopupConsFcltyInfoVO searchVO) throws Exception {
    	return list("gamPopupConsFcltyInfoDAO.selectConsFcltyInfoList_D", searchVO);
    }

    /**
	 * 건축시설물 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectConsFcltyInfoListTotCnt(GamPopupConsFcltyInfoVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupConsFcltyInfoDAO.selectConsFcltyInfoListTotCnt_S", searchVO);
    }
}
