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
import egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO;

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

@Service("gamFenderInspectionService")
public class GamFenderInspectionServiceImpl extends AbstractServiceImpl implements GamFenderInspectionService {

	@Resource(name="gamFenderInspectionDao")
    private GamFenderInspectionDao gamFenderInspectionDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO)
	 */
	@Override
	public List selectFenderInspectionList(GamFenderInspectionVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamFenderInspectionDao.selectFenderInspectionList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#gamInsertFenderInspection(egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO)
	 */
	@Override
	public void gamInsertFenderInspection(Map insertFenderInspection, List fenderInspectionListOne, List fenderInspectionListTwo, List fenderInspectionListThree) {
		// TODO Auto-generated method stub
		String sn = gamFenderInspectionDao.gamInsertFenderInspection(insertFenderInspection);

		String year = (String) insertFenderInspection.get("year");
		String fcltsMngGroupNo = (String) insertFenderInspection.get("fcltsMngGroupNo");
		String register = (String) insertFenderInspection.get("register");
		Map insertObj1 = null;
		Map insertObj2 = null;
		Map insertObj3 = null;

		for(int i=0;i<fenderInspectionListOne.size();i++){
			insertObj1 = (Map) fenderInspectionListOne.get(i);
			insertObj1.put("year",year);
			insertObj1.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj1.put("sn", sn);
			insertObj1.put("se", "1");
			insertObj1.put("register", register);
			gamFenderInspectionDao.gamInsertFenderInspectionDetail(insertObj1);

		}
		for(int i=0;i<fenderInspectionListTwo.size();i++){
			insertObj2 = (Map) fenderInspectionListTwo.get(i);
			insertObj2.put("year",year);
			insertObj2.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj2.put("sn", sn);
			insertObj2.put("se", "2");
			insertObj2.put("register", register);
			gamFenderInspectionDao.gamInsertFenderInspectionDetail(insertObj2);

		}
		for(int i=0;i<fenderInspectionListThree.size();i++){
			insertObj3 = (Map) fenderInspectionListThree.get(i);
			insertObj3.put("year",year);
			insertObj3.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj3.put("sn", sn);
			insertObj3.put("se", "3");
			insertObj3.put("register", register);
			gamFenderInspectionDao.gamInsertFenderInspectionDetail(insertObj3);

		}

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#gamUpdateFenderInspection(egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO)
	 */
	@Override
	public void gamUpdateFenderInspection(Map insertFenderInspection, List fenderInspectionListOne, List fenderInspectionListTwo, List fenderInspectionListThree) {
		// TODO Auto-generated method stub
		gamFenderInspectionDao.gamUpdateFenderInspection(insertFenderInspection);

		String year = (String) insertFenderInspection.get("year");
		String fcltsMngGroupNo = (String) insertFenderInspection.get("fcltsMngGroupNo");
		String sn = (String) insertFenderInspection.get("sn");
		String register = (String) insertFenderInspection.get("register");
		Map deleteObj = new HashMap<String, String>();
		Map insertObj1 = null;
		Map insertObj2 = null;
		Map insertObj3 = null;

		deleteObj.put("year",year);
		deleteObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
		deleteObj.put("sn", sn);

		gamFenderInspectionDao.gamDeleteFenderInspectionDetail(deleteObj);

		for(int i=0;i<fenderInspectionListOne.size();i++){
			insertObj1 = (Map) fenderInspectionListOne.get(i);
			insertObj1.put("year",year);
			insertObj1.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj1.put("sn", sn);
			insertObj1.put("se", "1");
			insertObj1.put("register", register);
			gamFenderInspectionDao.gamInsertFenderInspectionDetail(insertObj1);

		}
		for(int i=0;i<fenderInspectionListTwo.size();i++){
			insertObj2 = (Map) fenderInspectionListTwo.get(i);
			insertObj2.put("year",year);
			insertObj2.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj2.put("sn", sn);
			insertObj2.put("se", "2");
			insertObj2.put("register", register);
			gamFenderInspectionDao.gamInsertFenderInspectionDetail(insertObj2);

		}
		for(int i=0;i<fenderInspectionListThree.size();i++){
			insertObj3 = (Map) fenderInspectionListThree.get(i);
			insertObj3.put("year",year);
			insertObj3.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj3.put("sn", sn);
			insertObj3.put("se", "3");
			insertObj3.put("register", register);
			gamFenderInspectionDao.gamInsertFenderInspectionDetail(insertObj3);

		}

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#deleteFenderInspection(egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO)
	 */
	@Override
	public void deleteFenderInspection(GamFenderInspectionVO deleteVO) throws Exception {
		// TODO Auto-generated method stub
		Map deleteObj = new HashMap<String, String>();

		deleteObj.put("year",deleteVO.getYear());
		deleteObj.put("fcltsMngGroupNo",deleteVO.getFcltsMngGroupNo());
		deleteObj.put("sn", deleteVO.getSn());

		gamFenderInspectionDao.gamDeleteFenderInspectionDetail(deleteObj);
		gamFenderInspectionDao.deleteFenderInspection(deleteVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#selectFenderMngGroupList(egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO)
	 */
	@Override
	public List selectFenderMngGroupList(GamFenderInspectionVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamFenderInspectionDao.selectFenderMngGroupList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#selectFenderInspectionDetailList(egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO)
	 */
	@Override
	public List selectFenderInspectionDetailList(GamFenderInspectionVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamFenderInspectionDao.selectFenderInspectionDetailList(searchVO);
	}


}
