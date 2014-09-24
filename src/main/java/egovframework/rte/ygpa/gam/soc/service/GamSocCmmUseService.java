/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import java.util.List;


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

public interface GamSocCmmUseService {
	
	/**
     * 포트미스 항코드를 조회한다.
     *
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
    public List selectSocPrtAtCodeDetail() throws Exception;
    
}
