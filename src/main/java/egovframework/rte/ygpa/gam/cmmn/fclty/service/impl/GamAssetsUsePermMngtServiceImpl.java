/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;

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

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;


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
			vo.put("payinstIntrrate", assetsRent.get("payinstIntrrate"));
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
		gamAssetsUsePermMngtDAO.deleteAssetsUsagePdByStats(vo);	// 이전 통계 정보를 삭제한다.
		gamAssetsUsePermMngtDAO.insertAssetsUsagePdByStats(vo);	// 새로운 통계 정보를 생성한다.

// 전자결재 승인 후 처리 예정(주석 처리)
//		gamAssetsUsePermMngtDAO.confirmAssetUsePerm(vo);

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

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#insertBillCreateAdit(java.util.Map)
	 */
	@Override
	public void insertBillCreateAdit(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub
		gamAssetsUsePermMngtDAO.insertBillCreateAdit(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#deleteBillAdit(java.util.Map)
	 */
	@Override
	public void deleteBillAdit(Map<String, Object> vo) throws Exception {
		gamAssetsUsePermMngtDAO.deleteLevRequestSingle(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#selectElctrnSanctnPk()
	 */
	@Override
	public Map selectElctrnSanctnPk() {
		// TODO Auto-generated method stub
		return gamAssetsUsePermMngtDAO.selectElctrnSanctnPk();
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#insertElctrnSanctn(java.util.Map)
	 */
	@Override
	public String insertElctrnSanctn(Map paramMap) {
		// TODO Auto-generated method stub
		return gamAssetsUsePermMngtDAO.insertElctrnSanctn(paramMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#insertGwcallFwdIf(java.util.Map)
	 */
	@Override
	public void insertGwcallFwdIf(Map paramMap) {
		// TODO Auto-generated method stub
		gamAssetsUsePermMngtDAO.insertGwcallFwdIf(paramMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#updateAssetsRentF(java.util.Map)
	 */
	@Override
	public void updateAssetsRentF(Map paramMap) {
		// TODO Auto-generated method stub
		gamAssetsUsePermMngtDAO.updateAssetsRentF(paramMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService#gamPrmisnProceedingCancel(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO)
	 */
	@Override
	public Map gamPrmisnProceedingCancel(GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO) throws Exception {
		// TODO Auto-generated method stub

     	 Map map = new HashMap();
     	Map<String, Object> paramMap = new HashMap();
         String resultMsg = "";
         int resultCode = 1;

     	List assetRentList = gamAssetsUsePermMngtDAO.selectElctrnSanctnList(gamPrtFcltyRentMngtVO);

     	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

     	for(int i=0; assetRentList.size()>i; i++) {
     		paramMap.clear();
     		paramMap = (Map) assetRentList.get(i);
     		paramMap.put("regUsr", loginVO.getId());
     		paramMap.put("updUsr", loginVO.getId());

 /*   		System.out.println("##################################### 승낙취소시작!!");
           System.out.println("##################################### getPrtAtCode => " +  paramMap.get("prtAtCode").toString());
           System.out.println("##################################### getMngYear => " +  paramMap.get("mngYear").toString());
           System.out.println("##################################### getMngNo => " +  paramMap.get("mngNo").toString());
           System.out.println("##################################### getMngCnt => " +  paramMap.get("mngCnt").toString());
*/
           if(!paramMap.containsKey("prtAtCode") || !paramMap.containsKey("mngYear") || !paramMap.containsKey("mngNo") || !paramMap.containsKey("mngCnt")) {
               resultCode = 2;
               resultMsg = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
           }
           else {
        	   gamAssetsUsePermMngtDAO.cancelAssetUsePerm(paramMap);
        	   gamAssetsUsePermMngtDAO.deleteLevRequest(paramMap);
        	   gamAssetsUsePermMngtDAO.deleteAssetsUsagePdByStats(paramMap);

  	         resultCode = 0;
  	 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.proceedingCancel");
           }
     	}

     	gamAssetsUsePermMngtDAO.updateElctrnSanctn(gamPrtFcltyRentMngtVO);

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
	}

}
