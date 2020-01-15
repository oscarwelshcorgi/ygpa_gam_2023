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
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceService;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadSttusInqireVO;

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

@Service("gamRoadMaintenanceService")
public class GamRoadMaintenanceServiceImpl extends AbstractServiceImpl implements GamRoadMaintenanceService {

	@Resource(name="gamRoadMaintenanceDao")
    private GamRoadMaintenanceDao gamRoadMaintenanceDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadSttusInqireService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO)
	 */
	@Override
	public List selectRoadMaintenanceList(GamRoadMaintenanceVO searchVO) throws Exception {
		// TODO Auto-generated method stub

		return gamRoadMaintenanceDao.selectRoadMaintenanceList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadSttusInqireService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO)
	 */
	@Override
	public List selectRoadMaintenanceDetailList(GamRoadMaintenanceVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamRoadMaintenanceDao.selectRoadMaintenanceDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceService#gamInsertRoadMaintenance(egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO)
	 */
	@Override
	public void gamInsertRoadMaintenance(Map insertRoadMaintenance, List insertObjList) throws Exception {
		// TODO Auto-generated method stub
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String usrId = user.getId();
		
		String year = (String) insertRoadMaintenance.get("year");
		String fcltsMngGroupNo = (String) insertRoadMaintenance.get("fcltsMngGroupNo");
		Map insertObj = null;

		
		insertRoadMaintenance.put("register", usrId);
		String sn = gamRoadMaintenanceDao.gamInsertRoadMaintenance(insertRoadMaintenance);
		
		// 유지보수 대상시설물 입력처리
		for(int i=0;i<insertObjList.size();i++){
			insertObj = (Map) insertObjList.get(i);
			insertObj.put("year",year);
			insertObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj.put("sn", sn);
			gamRoadMaintenanceDao.gamInsertRoadMaintenanceDetail(insertObj);

		}
		
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceService#gamUpdateRoadMaintenance(egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO)
	 */
	@Override
	public void gamUpdateRoadMaintenance(Map insertRoadMaintenance, List insertObjList) throws Exception {
		// TODO Auto-generated method stub
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String usrId = user.getId();
		
		insertRoadMaintenance.put("updusr", usrId);
		gamRoadMaintenanceDao.gamUpdateRoadMaintenance(insertRoadMaintenance);
		
		String year = (String) insertRoadMaintenance.get("year");
		String fcltsMngGroupNo = (String) insertRoadMaintenance.get("fcltsMngGroupNo");
		String sn = (String)insertRoadMaintenance.get("sn");
		Map insertObj = null;
		
		ObjectMapper mapper = new ObjectMapper();
		GamRoadMaintenanceVO deleteVO = null;
		
		deleteVO = mapper.convertValue(insertRoadMaintenance, GamRoadMaintenanceVO.class);
		
		gamRoadMaintenanceDao.gamDeleteRoadMaintenanceDetail(deleteVO);
		
		// 유지보수 대상시설물 입력처리
		for(int i=0;i<insertObjList.size();i++){
			insertObj = (Map) insertObjList.get(i);
			insertObj.put("year",year);
			insertObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj.put("sn", sn);
			insertObj.put("register", usrId);
			
			gamRoadMaintenanceDao.gamInsertRoadMaintenanceDetail(insertObj);

		}
		
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceService#deleteRoadMaintenance(egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO)
	 */
	@Override
	public void gamDeleteRoadMaintenance(GamRoadMaintenanceVO deleteVO) throws Exception {
		// TODO Auto-generated method stub
		gamRoadMaintenanceDao.gamDeleteRoadMaintenanceDetail(deleteVO);
		gamRoadMaintenanceDao.gamDeleteRoadMaintenance(deleteVO);
		
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceService#selectRoadMaintenanceDetailPrint(egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO)
	 */
	@Override
	public List selectRoadMaintenanceDetailPrint(GamRoadMaintenanceVO printVo) throws Exception {
		// TODO Auto-generated method stub
		return gamRoadMaintenanceDao.selectRoadMaintenanceDetailPrint(printVo);
	}

}
