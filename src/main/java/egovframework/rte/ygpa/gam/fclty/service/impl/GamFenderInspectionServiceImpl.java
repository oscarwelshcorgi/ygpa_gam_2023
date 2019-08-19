/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	public void gamInsertFenderInspection(GamFenderInspectionVO inputVO) {
		// TODO Auto-generated method stub
		gamFenderInspectionDao.gamInsertFenderInspection(inputVO);

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#gamUpdateFenderInspection(egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO)
	 */
	@Override
	public void gamUpdateFenderInspection(GamFenderInspectionVO inputVO) {
		// TODO Auto-generated method stub
		gamFenderInspectionDao.gamUpdateFenderInspection(inputVO);

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#deleteFenderInspection(egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO)
	 */
	@Override
	public void deleteFenderInspection(GamFenderInspectionVO deleteVO) throws Exception {
		// TODO Auto-generated method stub
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
