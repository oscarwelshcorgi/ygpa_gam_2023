/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUseUnuseSttusInqireVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.impl.GamFcltyUsageSttusInqireDao;


/**
*
* @author jckim
* @since 2014. 12. 8.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2014. 12. 8.		jckim		최초 생성
*
* Copyright (C) 2013 by LFIT  All right reserved.
* </pre>
*/

@Service("gamFcltyUsageSttusInqireService")
public class GamFcltyUsageSttusInqireServiceImpl extends AbstractServiceImpl implements GamFcltyUsageSttusInqireService{
	@Resource(name="gamFcltyUsageSttusInqireDao")
    private GamFcltyUsageSttusInqireDao gamFcltyUsageSttusInqireDao;

	/**
	 * GIS 항만 시설
	 * @param vo
	 * @return
	 */
	public List selectFcltyGisPrtFcltyCdList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFcltyGisPrtFcltyCdList(vo);
	}

	/**
	 * GIS 항만 시설
	 * @param vo
	 * @return
	 */
	public int selectFcltyGisPrtFcltyCdListTotCnt(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFcltyGisPrtFcltyCdListTotCnt(vo);
	}

	/**
	 * 시설물 사용현황 목록 조회
	 * @param vo
	 * @return
	 */
	public List selectFcltyAssetsRentList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFcltyAssetsRentList(vo);
	}

	/**
	 * 시설물 사용현황 목록 수
	 * @param vo
	 * @return
	 */
	public int selectFcltyAssetsRentListTotCnt(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFcltyAssetsRentListTotCnt(vo);
	}

	/**
	 * 점검 관리 내역
	 * @param vo
	 * @return
	 */
	public List selectQcMngList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectQcMngList(vo);
	}

	

	/**
	 * 하자 보수 내역
	 * @param vo
	 * @return
	 */

	public List selectFlawList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFlawList(vo);
	}

	
	/**
	 * 유지 보수 내역
	 * @param vo
	 * @return
	 */
	public List selectMntnRprDtlsList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectMntnRprDtlsList(vo);
	}

	/**
	 * 시설물관리그룹명 조회
	 * @param vo
	 * @return
	 */

	public EgovMap selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamFcltyUsageSttusInqireDao.selectFcltsMngGroupNm(searchVO);
	}

}