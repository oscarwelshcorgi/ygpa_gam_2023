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

@Repository("gamMapsAssetCodeMngtDAO")
public class GamMapsAssetCodeMngtDAO extends YGPAAbstractDAO {

	/**
	 * 자산코드 정보 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectAssetCodeInfoList(Map vo) throws Exception{
		return list("gamMapsAssetCodeMngtDAO.selectAssetCodeInfoList_D", vo);
	}

	/**
	 * 자산임대 정보 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectAssetRentInfoList(Map vo) throws Exception{
		return list("gamMapsAssetCodeMngtDAO.selectAssetRentByAssetsCodeList_D", vo);
	}

	/**
	 * 자산정보 팝업 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public EgovMap selectMapsAssetsCodeInfo(Map vo) throws Exception{
		return (EgovMap) selectByPk("gamMapsAssetCodeMngtDAO.selectMapsAssetsCodeInfo", vo);
	}

	public EgovMap selectMapsAssetsCodeUseSummary(Map vo) throws Exception{
		return (EgovMap) selectByPk("gamMapsAssetCodeMngtDAO.selectMapsAssetsCodeUseSummary", vo);
	}

	@SuppressWarnings("unchecked")
	public List selectMapsAssetsCodeUseInfo(Map vo) throws Exception{
		return list("gamMapsAssetCodeMngtDAO.selectMapsAssetsCodeUseInfo", vo);
	}
}
