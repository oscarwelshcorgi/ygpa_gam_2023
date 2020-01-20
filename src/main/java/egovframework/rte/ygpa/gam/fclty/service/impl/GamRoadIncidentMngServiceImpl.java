/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadIncidentMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadIncidentMngVO;

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

@Service("gamRoadIncidentMngService")
public class GamRoadIncidentMngServiceImpl extends AbstractServiceImpl implements GamRoadIncidentMngService {

	@Resource(name="gamRoadIncidentMngDao")
    private GamRoadIncidentMngDao gamRoadIncidentMngDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadIncidentMngService#selectFcltyMaintIncidentMngList(egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO)
	 */
	@Override
	public List selectRoadIncidentMngList(GamRoadIncidentMngVO searchVO) throws Exception {
		// TODO Auto-generated method stub

		return gamRoadIncidentMngDao.selectRoadIncidentMngList(searchVO);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadIncidentMngService#selectFcltyMaintIncidentMngList(egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO)
	 */
	@Override
	public List selectRoadIncidentMngDetailList(GamRoadIncidentMngVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		
		return gamRoadIncidentMngDao.selectRoadIncidentMngDetailList(searchVO);
	}
	
}
