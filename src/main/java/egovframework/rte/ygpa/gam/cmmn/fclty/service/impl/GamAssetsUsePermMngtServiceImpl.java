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

@Service("gamAssetsUsePermMngtService")
public class GamAssetsUsePermMngtServiceImpl extends AbstractServiceImpl implements
		GamAssetsUsePermMngtService {

	@Resource(name="gamAssetsUsePermMngtDAO")
    private GamAssetsUsePermMngtDAO gamAssetsUsePermMngtDAO;
	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#confirmAssetsRentUsePerm(java.util.Map)
	 */
	@Override
	public void confirmAssetsRentUsePerm(Map<String, Object> vo)
			throws Exception {
		Map assetsRent;

		assetsRent = gamAssetsUsePermMngtDAO.selectAssetRentByPk(vo);

		if(!assetsRent.containsKey("nticMth")) {
			throw processException("fail.levinsert.type");
		}

		assetsRent.put("regUsr", vo.get("regUsr"));
		assetsRent.put("updUsr", vo.get("regUsr"));
		assetsRent.put("chrgeKnd", vo.get("chrgeKnd"));
		vo.putAll(assetsRent);

		if("1".equals(assetsRent.get("nticMth"))) {
			// 일괄 납부
			gamAssetsUsePermMngtDAO.insertBillCreateOnce(vo);
		}
		else {
//			vo.put("payinstIntrrate", assetsRent.get("payinstIntrrate"));
			// 분납
			if("2".equals(assetsRent.get("nticMth"))) {
				// 반기납
				vo.put("blTpNum", 6);
				gamAssetsUsePermMngtDAO.insertBillCreatePreHalf(vo);
			}
			else if("3".equals(assetsRent.get("nticMth"))) {
				// 3분납
				vo.put("blTpNum", 4);
				gamAssetsUsePermMngtDAO.insertBillCreatePreThird(vo);
			}
			else if("4".equals(assetsRent.get("nticMth"))) {
				// 분기납
				vo.put("blTpNum", 3);
				gamAssetsUsePermMngtDAO.insertBillCreatePreQuater(vo);
			}
			else if("5".equals(assetsRent.get("nticMth"))) {
				// 월납
				vo.put("blTpNum", 1);
				gamAssetsUsePermMngtDAO.insertBillCreatePreMonth(vo);
			}
			else if("6".equals(assetsRent.get("nticMth"))) {
				// 연납
				vo.put("blTpNum", 12);
				gamAssetsUsePermMngtDAO.insertBillCreatePreYear(vo);
			}
			else throw processException("fail.levinsert.type");
		}
		gamAssetsUsePermMngtDAO.confirmAssetUsePerm(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#cancelAssetsRentUsePerm(java.util.Map)
	 */
	@Override
	public void cancelAssetsRentUsePerm(Map<String, Object> vo)
			throws Exception {
		gamAssetsUsePermMngtDAO.cancelAssetUsePerm(vo);
		gamAssetsUsePermMngtDAO.deleteLevRequest(vo);
		gamAssetsUsePermMngtDAO.deleteAssetsUsagePdByStats(vo);
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
