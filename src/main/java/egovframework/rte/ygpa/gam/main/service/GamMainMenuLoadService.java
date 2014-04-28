/**
 *
 */
package egovframework.rte.ygpa.gam.main.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 25.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamMainMenuLoadService {

	public List selectMenuList(String userId) throws Exception;

}
