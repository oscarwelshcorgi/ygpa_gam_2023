/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireService;
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

@Service("gamFenderSttusInqireService")
public class GamFenderSttusInqireServiceImpl extends AbstractServiceImpl implements GamFenderSttusInqireService {

	@Resource(name="gamFenderSttusInqireDao")
    private GamFenderSttusInqireDao gamFenderSttusInqireDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO)
	 */
	@Override
	public List selectFenderMngGroupList(GamFenderMngGroupVO searchVO) throws Exception {
		// TODO Auto-generated method stub

		return gamFenderSttusInqireDao.selectFenderMngGroupList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO)
	 */
	@Override
	public List selectFenderSttusInqireList(GamFenderSttusInqireVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamFenderSttusInqireDao.selectFenderSttusInqireList(searchVO);
	}

}
