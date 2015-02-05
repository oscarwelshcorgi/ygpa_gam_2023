package egovframework.rte.ygpa.gam.cmmn.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.cmmn.service.GamCmmnCodeService;

/**
 * @Class Name : EgovCmmUseServiceImpl.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Service("gamCmmnCodeService")
public class GamCmmnCodeServiceImpl extends AbstractServiceImpl implements GamCmmnCodeService {

    @Resource(name = "gamCmmnCodeDAO")
    private GamCmmnCodeDAO gamCmmnCodeDAO;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.service.GamCmmnCodeService#selectLocCodeList(java.util.Map)
	 */
	@Override
	public List selectLocCodeList(Map vo) throws Exception {
		return gamCmmnCodeDAO.selectLocCodeList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.service.GamCmmnCodeService#selectQuayCodeList(java.util.Map)
	 */
	@Override
	public List selectQuayCodeList(Map vo) throws Exception {
		return gamCmmnCodeDAO.selectQuayCodeList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.service.GamCmmnCodeService#selectCmmnCdCodeList(java.util.Map)
	 */
	@Override
	public List selectCmmnCdCodeList(Map vo) throws Exception {
		return gamCmmnCodeDAO.selectCmmnCdCodeList(vo);
	}

}
