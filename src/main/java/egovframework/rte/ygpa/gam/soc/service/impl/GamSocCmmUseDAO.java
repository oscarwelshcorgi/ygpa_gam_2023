/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;

/**
 * 
 * @author HNJ
 * @since 2014. 9. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 22.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamSocCmmUseDAO")
public class GamSocCmmUseDAO  extends YGPAAbstractDAO {
	
	
	/**
     * 주어진 조건에 따른 항코드를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List selectSocPortCodeDetail(GamSocCmmUseVO vo) throws Exception {
    	System.out.println("aaaa");
    	return list("gamSocCmmUseDAO.selectSocCmmUseDetail_D", vo);
    }
}
