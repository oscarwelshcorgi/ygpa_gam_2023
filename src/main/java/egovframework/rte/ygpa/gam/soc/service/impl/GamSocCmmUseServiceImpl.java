/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;

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
@Service("gamSocCmmUseService")
public class GamSocCmmUseServiceImpl implements GamSocCmmUseService {
	
	@Resource(name = "gamSocCmmUseDAO")
    private GamSocCmmUseDAO gamSocCmmUseDAO;

	@Override
	public List selectSocPrtAtCodeDetail()
			throws Exception {
		return gamSocCmmUseDAO.selectSocPrtAtCodeDetail();
	}
}
