/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderDurabilityService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderDurabilityVO;

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

@Service("gamFenderDurabilityService")
public class GamFenderDurabilityServiceImpl extends AbstractServiceImpl implements GamFenderDurabilityService {

	@Resource(name="gamFenderDurabilityDao")
    private GamFenderDurabilityDao gamFenderDurabilityDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService#selectFcltyMaintSttusInqireList(egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO)
	 */
	@Override
	public List selectFenderDurabilityList(GamFenderDurabilityVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamFenderDurabilityDao.selectFenderDurabilityList(searchVO);
	}


}
