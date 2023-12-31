/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.soc.service.GamSocExmpMngtService;
import egovframework.rte.ygpa.gam.soc.service.GamSocExmpMngtVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamSocExmpMngtService")
public class GamSocExmpMngtServiceImpl implements GamSocExmpMngtService {

	@Resource(name="gamSocExmpMngtDAO")
	GamSocExmpMngtDAO gamSocExmpMngtDAO;
	
	
	/**
	 * 투자비보전내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	public EgovMap selectSocExmpMngtDetail(GamSocExmpMngtVO searchVO) {
		return gamSocExmpMngtDAO.selectSocExmpMngtDetailInquire(searchVO);
	}

	/**
	 * 투자비보전내역관리 데이터에 새로운 soc 일련번호를 가져온다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 새로운 soc 일련번호
	 * @exception
	 */
	public String selectSocExmpMngtGetNextSocNo(GamSocExmpMngtVO searchVO) {
		return gamSocExmpMngtDAO.selectSocExmpMngtGetNextSocNo(searchVO);
	}
	
	/**
	 * 투자비보전내역관리 데이터를 삽입한다. 
	 * @param insertVO - 삽입할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	public void insertSocExmpMngtDetail(Map<?, ?> vo) {
		gamSocExmpMngtDAO.insertSocExmpMngtDetail(vo);
	}

	/**
	 * 투자비보전내역관리 데이터를 수정한다. 
	 * @param searchVO - 수정할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	public void updateSocExmpMngtDetail(Map<?, ?> vo) {
		gamSocExmpMngtDAO.updateSocExmpMngtDetail(vo);
	}

	/**
	 * 투자비보전내역관리 데이터를 삭제한다. 
	 * @param searchVO - 삭제할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	public void deleteSocExmpMngtDetail(Map<?, ?> vo) {
		gamSocExmpMngtDAO.deleteSocExmpMngtDetail(vo);
	}
}
