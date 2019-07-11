/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderPopupInqireService;

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

@Service("gamFenderPopupInqireService")
public class GamFenderPopupInqireServiceImpl extends AbstractServiceImpl implements GamFenderPopupInqireService {

	@Resource(name="gamFenderPopupInqireDao")
    private GamFenderPopupInqireDao gamFenderPopupInqireDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFenderPopupInqireService#selectFenderPopupInqire()
	 */
	@Override
	public Map selectFenderPopupInqire() throws Exception {
		// TODO Auto-generated method stub
		return gamFenderPopupInqireDao.selectFenderPopupInqire();
	}



}
