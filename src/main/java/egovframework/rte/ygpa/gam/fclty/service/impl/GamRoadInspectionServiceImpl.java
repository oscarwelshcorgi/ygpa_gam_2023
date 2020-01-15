/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionService;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionVO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO;

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

@Service("gamRoadInspectionService")
public class GamRoadInspectionServiceImpl extends AbstractServiceImpl implements GamRoadInspectionService {

	@Resource(name="gamRoadInspectionDao")
    private GamRoadInspectionDao gamRoadInspectionDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO)
	 */
	@Override
	public List selectRoadInspectionList(GamRoadInspectionVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamRoadInspectionDao.selectRoadInspectionList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionService#gamInsertRoadInspection(egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionVO)
	 */
	@Override
	public void gamInsertRoadInspection(Map insertRoadInspection, List roadInspectionListOne, List roadInspectionListTwo, List roadInspectionListThree) {
		// TODO Auto-generated method stub
		String sn = gamRoadInspectionDao.gamInsertRoadInspection(insertRoadInspection);

		String year = (String) insertRoadInspection.get("year");
		String fcltsMngGroupNo = (String) insertRoadInspection.get("fcltsMngGroupNo");
		String register = (String) insertRoadInspection.get("register");
		Map insertObj1 = null;
		Map insertObj2 = null;
		Map insertObj3 = null;

		for(int i=0;i<roadInspectionListOne.size();i++){
			insertObj1 = (Map) roadInspectionListOne.get(i);
			insertObj1.put("year",year);
			insertObj1.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj1.put("sn", sn);
			insertObj1.put("se", "1");
			insertObj1.put("register", register);
			gamRoadInspectionDao.gamInsertRoadInspectionDetail(insertObj1);

		}
		for(int i=0;i<roadInspectionListTwo.size();i++){
			insertObj2 = (Map) roadInspectionListTwo.get(i);
			insertObj2.put("year",year);
			insertObj2.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj2.put("sn", sn);
			insertObj2.put("se", "2");
			insertObj2.put("register", register);
			gamRoadInspectionDao.gamInsertRoadInspectionDetail(insertObj2);

		}
		for(int i=0;i<roadInspectionListThree.size();i++){
			insertObj3 = (Map) roadInspectionListThree.get(i);
			insertObj3.put("year",year);
			insertObj3.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj3.put("sn", sn);
			insertObj3.put("se", "3");
			insertObj3.put("register", register);
			gamRoadInspectionDao.gamInsertRoadInspectionDetail(insertObj3);

		}

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionService#gamUpdateRoadInspection(egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionVO)
	 */
	@Override
	public void gamUpdateRoadInspection(Map insertRoadInspection, List roadInspectionListOne, List roadInspectionListTwo, List roadInspectionListThree) {
		// TODO Auto-generated method stub
		gamRoadInspectionDao.gamUpdateRoadInspection(insertRoadInspection);

		String year = (String) insertRoadInspection.get("year");
		String fcltsMngGroupNo = (String) insertRoadInspection.get("fcltsMngGroupNo");
		String sn = (String) insertRoadInspection.get("sn");
		String register = (String) insertRoadInspection.get("register");
		Map deleteObj = new HashMap<String, String>();
		Map insertObj1 = null;
		Map insertObj2 = null;
		Map insertObj3 = null;

		deleteObj.put("year",year);
		deleteObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
		deleteObj.put("sn", sn);

		gamRoadInspectionDao.gamDeleteRoadInspectionDetail(deleteObj);

		for(int i=0;i<roadInspectionListOne.size();i++){
			insertObj1 = (Map) roadInspectionListOne.get(i);
			insertObj1.put("year",year);
			insertObj1.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj1.put("sn", sn);
			insertObj1.put("se", "1");
			insertObj1.put("register", register);
			gamRoadInspectionDao.gamInsertRoadInspectionDetail(insertObj1);

		}
		for(int i=0;i<roadInspectionListTwo.size();i++){
			insertObj2 = (Map) roadInspectionListTwo.get(i);
			insertObj2.put("year",year);
			insertObj2.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj2.put("sn", sn);
			insertObj2.put("se", "2");
			insertObj2.put("register", register);
			gamRoadInspectionDao.gamInsertRoadInspectionDetail(insertObj2);

		}
		for(int i=0;i<roadInspectionListThree.size();i++){
			insertObj3 = (Map) roadInspectionListThree.get(i);
			insertObj3.put("year",year);
			insertObj3.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj3.put("sn", sn);
			insertObj3.put("se", "3");
			insertObj3.put("register", register);
			gamRoadInspectionDao.gamInsertRoadInspectionDetail(insertObj3);

		}

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionService#deleteRoadInspection(egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionVO)
	 */
	@Override
	public void deleteRoadInspection(GamRoadInspectionVO deleteVO) throws Exception {
		// TODO Auto-generated method stub
		Map deleteObj = new HashMap<String, String>();

		deleteObj.put("year",deleteVO.getYear());
		deleteObj.put("fcltsMngGroupNo",deleteVO.getFcltsMngGroupNo());
		deleteObj.put("sn", deleteVO.getSn());

		gamRoadInspectionDao.gamDeleteRoadInspectionDetail(deleteObj);
		gamRoadInspectionDao.deleteRoadInspection(deleteVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionService#selectRoadMngGroupList(egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionVO)
	 */
	@Override
	public List selectRoadMngGroupList(GamRoadInspectionVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamRoadInspectionDao.selectRoadMngGroupList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionService#selectRoadInspectionDetailList(egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO)
	 */
	@Override
	public List selectRoadInspectionDetailList(GamRoadInspectionVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamRoadInspectionDao.selectRoadInspectionDetailList(searchVO);
	}


}
