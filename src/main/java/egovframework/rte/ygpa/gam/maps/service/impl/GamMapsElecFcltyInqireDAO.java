/**
 *
 */
package egovframework.rte.ygpa.gam.maps.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamMapsElecFcltyInqireDAO")
public class GamMapsElecFcltyInqireDAO extends YGPAAbstractDAO {

	/**
	 * 시설정보 팝업 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public EgovMap selectMapsFcltyCdInfo(Map vo) throws Exception{
		return (EgovMap) selectByPk("gamMapsElecFcltyInqireDAO.selectFcltySpecInfoByPk_S", vo);
	}

	/**
	 * 시설 파일 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectMapsFcltyFileList(Map vo) throws Exception{
		return (List) list("gamMapsElecFcltyInqireDAO.selectFcltySpecFileList_D", vo);
	}

}
