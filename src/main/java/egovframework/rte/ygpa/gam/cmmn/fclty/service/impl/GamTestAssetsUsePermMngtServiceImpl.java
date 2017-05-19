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
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService;

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

@Service("gamTestAssetsUsePermMngtService")
public class GamTestAssetsUsePermMngtServiceImpl extends AbstractServiceImpl implements
		GamTestAssetsUsePermMngtService {

	@Resource(name="gamTestAssetsUsePermMngtDAO")
    private GamTestAssetsUsePermMngtDAO gamTestAssetsUsePermMngtDAO;
	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService#confirmAssetsRentUsePerm(java.util.Map)
	 */
	@Override
	public void confirmAssetsRentUsePerm(Map<String, Object> vo)
			throws Exception {
		Map assetsRent;

		assetsRent = gamTestAssetsUsePermMngtDAO.selectAssetRentByPk(vo);

		if(!assetsRent.containsKey("nticMth")) {
			throw processException("fail.levinsert.type");
		}

		assetsRent.put("regUsr", vo.get("regUsr"));
		assetsRent.put("updUsr", vo.get("regUsr"));
		assetsRent.put("chrgeKnd", vo.get("chrgeKnd"));
		vo.putAll(assetsRent);

		if("1".equals(assetsRent.get("nticMth"))) {
			// 일괄 납부
			gamTestAssetsUsePermMngtDAO.insertBillCreateOnce(vo);
		}
		else {
			vo.put("payinstIntrrate", assetsRent.get("payinstIntrrate"));
			// 분납
			if("2".equals(assetsRent.get("nticMth"))) {
				// 반기납
				vo.put("blTpNum", 6);
				gamTestAssetsUsePermMngtDAO.insertBillCreatePreHalf(vo);
			}
			else if("3".equals(assetsRent.get("nticMth"))) {
				// 3분납
				vo.put("blTpNum", 4);
				gamTestAssetsUsePermMngtDAO.insertBillCreatePreThird(vo);
			}
			else if("4".equals(assetsRent.get("nticMth"))) {
				// 분기납
				vo.put("blTpNum", 3);
				gamTestAssetsUsePermMngtDAO.insertBillCreatePreQuater(vo);
			}
			else if("5".equals(assetsRent.get("nticMth"))) {
				// 월납
				vo.put("blTpNum", 1);
				gamTestAssetsUsePermMngtDAO.insertBillCreatePreMonth(vo);
			}
			else if("6".equals(assetsRent.get("nticMth"))) {
				// 연납
				vo.put("blTpNum", 12);
				gamTestAssetsUsePermMngtDAO.insertBillCreatePreYear(vo);
			}
			else throw processException("fail.levinsert.type");
		}
		gamTestAssetsUsePermMngtDAO.deleteAssetsUsagePdByStats(vo);	// 이전 통계 정보를 삭제한다.
		gamTestAssetsUsePermMngtDAO.insertAssetsUsagePdByStats(vo);	// 새로운 통계 정보를 생성한다.

		gamTestAssetsUsePermMngtDAO.confirmAssetUsePerm(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService#cancelAssetsRentUsePerm(java.util.Map)
	 */
	@Override
	public void cancelAssetsRentUsePerm(Map<String, Object> vo)
			throws Exception {
		gamTestAssetsUsePermMngtDAO.cancelAssetUsePerm(vo);
		gamTestAssetsUsePermMngtDAO.deleteLevRequest(vo);
		gamTestAssetsUsePermMngtDAO.deleteAssetsUsagePdByStats(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService#confirmPrtFcltyUsePerm(java.util.Map)
	 */
	@Override
	public void confirmPrtFcltyUsePerm(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService#cancelPrtFcltyUsePerm(java.util.Map)
	 */
	@Override
	public void cancelPrtFcltyUsePerm(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService#insertBillCreateAdit(java.util.Map)
	 */
	@Override
	public void insertBillCreateAdit(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub
		gamTestAssetsUsePermMngtDAO.insertBillCreateAdit(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService#deleteBillAdit(java.util.Map)
	 */
	@Override
	public void deleteBillAdit(Map<String, Object> vo) throws Exception {
		gamTestAssetsUsePermMngtDAO.deleteLevRequestSingle(vo);
	}

}
