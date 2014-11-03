/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyCtrtSttusInqireDao")
public class GamFcltyCtrtSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 계약이력목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이력목록
	 * @exception Exception
	 */
    public List selectFcltyCtrtSttusInqireList(GamFcltyCtrtSttusInqireVO searchVO) throws Exception {
        return list("gamFcltyCtrtSttusInqireDao.selectFcltyCtrtSttusInqireList", searchVO);
    }


    /**
	 * 계약이력목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이력목록 총갯수 및 금액합계
	 * @exception Exception
	 */
    public GamFcltyCtrtSttusInqireVO selectFcltyCtrtSttusInqireInfoSum(GamFcltyCtrtSttusInqireVO searchVO) throws Exception {
    	return (GamFcltyCtrtSttusInqireVO) selectByPk("gamFcltyCtrtSttusInqireDao.selectFcltyCtrtSttusInqireInfoSum", searchVO);
    }


}
