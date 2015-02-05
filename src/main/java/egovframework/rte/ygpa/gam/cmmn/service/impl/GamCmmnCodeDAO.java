package egovframework.rte.ygpa.gam.cmmn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;

/**
 * @Class Name : CmmUseDAO.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
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
@Repository("gamCmmnCodeDAO")
public class GamCmmnCodeDAO extends YGPAAbstractDAO {

    /**
     * 항코드에 대한 위치 코드를 조회한다.
     *
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
	public List selectLocCodeList(Map vo) throws Exception {
		return list("gamCmmnCodeDAO.selectLocCodeList_D", vo);
	}

    /**
     * 위치 코드에 대한 부두코드를 조회한다.
     *
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
	public List selectQuayCodeList(Map vo) throws Exception {
		return list("gamCmmnCodeDAO.selectQuayCodeList_D", vo);
	}

	/**
	 * 공통코드를 필텋링 하여 조회한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectCmmnCdCodeList(Map vo) throws Exception {
		return list("gamCmmnCodeDAO.selectCmmnCdCodeList_D", vo);
	}
}
