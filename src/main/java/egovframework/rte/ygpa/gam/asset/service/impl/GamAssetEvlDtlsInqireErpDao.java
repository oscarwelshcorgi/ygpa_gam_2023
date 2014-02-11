package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAErpAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireVO;

/**
 * @Class Name : GamAssetEvlDtlsInqireErpDao.java
 * @Description : ERP감가상각내역조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-02-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetEvlDtlsInqireErpDao")
public class GamAssetEvlDtlsInqireErpDao extends YGPAErpAbstractDAO {
	
	/**
	 * ERP감가상각내역을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP감가상각내역
	 * @exception Exception
	 */
	public GamAssetEvlDtlsInqireVO selectAssetEvlDtlsInqireErp(GamAssetEvlDtlsInqireVO searchVO) throws Exception {
		return (GamAssetEvlDtlsInqireVO) selectByPk("gamAssetEvlDtlsInqireErpDao.selectAssetEvlDtlsInqireErp_S", searchVO);
	}
	
}
