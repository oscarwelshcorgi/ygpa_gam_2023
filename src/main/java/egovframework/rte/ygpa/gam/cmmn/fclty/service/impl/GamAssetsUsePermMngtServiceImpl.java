/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.impl.GamAssetRentMngtDao;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 14.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamAssetsUsePermMngtServiceImpl")
public class GamAssetsUsePermMngtServiceImpl extends AbstractServiceImpl implements
		GamAssetsUsePermMngtService {

	@Resource(name="gamAssetRentMngtDao")
    private GamAssetRentMngtDao gamAssetRentMngtDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#confirmAssetsRentUsePerm(java.util.Map)
	 */
	@Override
	public void confirmAssetsRentUsePerm(Map<String, Object> vo)
			throws Exception {
		GamAssetRentMngtVO assetsRent = new GamAssetRentMngtVO();
		assetsRent.setPrtAtCode((String) vo.get("prtAtCode"));
		assetsRent.setMngYear((String) vo.get("mngYear"));
		assetsRent.setMngNo((String) vo.get("mngNo"));
		assetsRent.setMngCnt((String) vo.get("mngCnt"));
		assetsRent = gamAssetRentMngtDao.selectAssetRentPrmisnInfo(assetsRent);
		
		if(assetsRent.getNticMth()==null) {
			throw processException("fail.levinsert.type");
		}
		if("1".equals(assetsRent.getNticMth())) {
			// 일괄 납부
			
		}
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#cancelAssetsRentUsePerm(java.util.Map)
	 */
	@Override
	public void cancelAssetsRentUsePerm(Map<String, Object> vo)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#confirmPrtFcltyUsePerm(java.util.Map)
	 */
	@Override
	public void confirmPrtFcltyUsePerm(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#cancelPrtFcltyUsePerm(java.util.Map)
	 */
	@Override
	public void cancelPrtFcltyUsePerm(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub

	}

}
