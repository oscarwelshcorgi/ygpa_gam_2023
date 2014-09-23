/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
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
@Service("gamSocCmmUseService")
public class GamSocCmmUseServiceImpl implements GamSocCmmUseService {
	
	@Resource(name = "gamSocCmmUseDAO")
    private GamSocCmmUseDAO gamSocCmmUseDAO;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.soc.service.SocCmmService#selectSocPortCodeDetail(egovframework.com.cmm.ComDefaultCodeVO)
	 */
	@Override
	public List selectSocPortCodeDetail(GamSocCmmUseVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		
		return gamSocCmmUseDAO.selectSocPortCodeDetail(vo);

	}

}
