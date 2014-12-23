/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireVO;
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
	 * 시설물 사용현황 목록 조회
	 * @param searchVO
	 * @return
	 */
	public List selectFcltyUsageSttusInqireList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFcltyUsageSttusInqireList(vo);
	}

	/**
	 * 시설물 사용현황 목록 수
	 * @param searchVO
	 * @return
	 */
	public int selectFcltyUsageSttusInqireListTotCnt(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFcltyUsageSttusInqireListTotCnt(vo);
	}

	/**
	 * GIS 자산 목록 조회
	 * @param searchVO
	 * @return
	 */
	public List selectFcltyGisAssetsCdFList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFcltyGisAssetsCdFList(vo);
	}

	/**
	 * GIS 자산 목록 수
	 * @param searchVO
	 * @return
	 */
	public int selectFcltyGisAssetsCdFListTotCnt(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return gamFcltyUsageSttusInqireDao.selectFcltyGisAssetsCdFListTotCnt(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireService#selectFcltyUsageMngList(egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireVO)
	 */

}