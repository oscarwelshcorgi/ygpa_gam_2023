/**
 *
 */
package egovframework.com.ygpa.uat.uia.service;

/**
 *
 * @author verstar
 * @since 2019. 6. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2019. 6. 10.		verstar		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamUserCfgService {
	GamUserCfgVo selectUserCfgPk(GamUserCfgVo vo);
	void insertUserCfgPk(GamUserCfgVo vo);
	void updateUserCfgPk(GamUserCfgVo vo);
}
