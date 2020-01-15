/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadSttusInqireService;
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

@Service("gamRoadSttusInqireService")
public class GamRoadSttusInqireServiceImpl extends AbstractServiceImpl implements GamRoadSttusInqireService {

	@Resource(name="gamRoadSttusInqireDao")
    private GamRoadSttusInqireDao gamRoadSttusInqireDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadSttusInqireService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO)
	 */
	@Override
	public List selectRoadMngGroupList(GamRoadMngGroupVO searchVO) throws Exception {
		// TODO Auto-generated method stub

		return gamRoadSttusInqireDao.selectRoadMngGroupList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamRoadSttusInqireService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO)
	 */
	@Override
	public List selectRoadSttusInqireList(GamRoadSttusInqireVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamRoadSttusInqireDao.selectRoadSttusInqireList(searchVO);
	}

}
