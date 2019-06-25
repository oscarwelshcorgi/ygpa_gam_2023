/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireVO;

/**
*
* @author LFIT
* @since 2019. 6. 19.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2019.6.19.		LFIT		최초 생성
*
* </pre>
*/

@Service("gamFenderMaintenanceService")
public class GamFenderMaintenanceServiceImpl extends AbstractServiceImpl implements GamFenderMaintenanceService {

	@Resource(name="gamFenderMaintenanceDao")
    private GamFenderMaintenanceDao gamFenderMaintenanceDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO)
	 */
	@Override
	public List selectFenderMaintenanceList(GamFenderMaintenanceVO searchVO) throws Exception {
		// TODO Auto-generated method stub

		return gamFenderMaintenanceDao.selectFenderMaintenanceList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO)
	 */
	@Override
	public List selectFenderMaintenanceDetailList(GamFenderMaintenanceVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamFenderMaintenanceDao.selectFenderMaintenanceDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceService#gamInsertFenderMaintenance(egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO)
	 */
	@Override
	public void gamInsertFenderMaintenance(Map insertFenderMaintenance, List insertObjList) throws Exception {
		// TODO Auto-generated method stub
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String usrId = user.getId();
		
		String year = (String) insertFenderMaintenance.get("year");
		String fcltsMngGroupNo = (String) insertFenderMaintenance.get("fcltsMngGroupNo");
		Map insertObj = null;

		
		insertFenderMaintenance.put("register", usrId);
		String sn = gamFenderMaintenanceDao.gamInsertFenderMaintenance(insertFenderMaintenance);
		
		// 유지보수 대상시설물 입력처리
		for(int i=0;i<insertObjList.size();i++){
			insertObj = (Map) insertObjList.get(i);
			insertObj.put("year",year);
			insertObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj.put("sn", sn);
			gamFenderMaintenanceDao.gamInsertFenderMaintenanceDetail(insertObj);

		}
		
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceService#gamUpdateFenderMaintenance(egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO)
	 */
	@Override
	public void gamUpdateFenderMaintenance(Map insertFenderMaintenance, List insertObjList) throws Exception {
		// TODO Auto-generated method stub
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String usrId = user.getId();
		
		insertFenderMaintenance.put("updusr", usrId);
		gamFenderMaintenanceDao.gamUpdateFenderMaintenance(insertFenderMaintenance);
		
		String year = (String) insertFenderMaintenance.get("year");
		String fcltsMngGroupNo = (String) insertFenderMaintenance.get("fcltsMngGroupNo");
		String sn = (String)insertFenderMaintenance.get("sn");
		Map insertObj = null;
		
		ObjectMapper mapper = new ObjectMapper();
		GamFenderMaintenanceVO deleteVO = null;
		
		deleteVO = mapper.convertValue(insertFenderMaintenance, GamFenderMaintenanceVO.class);
		
		gamFenderMaintenanceDao.gamDeleteFenderMaintenanceDetail(deleteVO);
		
		// 유지보수 대상시설물 입력처리
		for(int i=0;i<insertObjList.size();i++){
			insertObj = (Map) insertObjList.get(i);
			insertObj.put("year",year);
			insertObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj.put("sn", sn);
			insertObj.put("register", usrId);
			
			gamFenderMaintenanceDao.gamInsertFenderMaintenanceDetail(insertObj);

		}
		
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceService#deleteFenderMaintenance(egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO)
	 */
	@Override
	public void gamDeleteFenderMaintenance(GamFenderMaintenanceVO deleteVO) throws Exception {
		// TODO Auto-generated method stub
		gamFenderMaintenanceDao.gamDeleteFenderMaintenanceDetail(deleteVO);
		gamFenderMaintenanceDao.gamDeleteFenderMaintenance(deleteVO);
		
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceService#selectFenderMaintenanceDetailPrint(egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO)
	 */
	@Override
	public List selectFenderMaintenanceDetailPrint(GamFenderMaintenanceVO printVo) throws Exception {
		// TODO Auto-generated method stub
		return gamFenderMaintenanceDao.selectFenderMaintenanceDetailPrint(printVo);
	}

}
