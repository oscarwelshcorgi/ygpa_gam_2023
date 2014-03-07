/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.code.service.GamCmpyInfoMngtService;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsChargerFVO;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsInfoFVO;

/**
 * 
 * @author kok
 * @since 2014. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 5.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamCmpyInfoMngtService")
public class GamCmpyInfoMngtServiceImpl extends AbstractServiceImpl implements GamCmpyInfoMngtService{

	@Resource(name="gamCmpyInfoMngtDao")
    private GamCmpyInfoMngtDao gamCmpyInfoMngtDao;
	
	/**
	 * 업체정보관리 목록
	 */
	public List<GamEntrpsInfoFVO> selectCmpyInfoMngtList(GamEntrpsInfoFVO vo) throws Exception {
   		return (List<GamEntrpsInfoFVO>)gamCmpyInfoMngtDao.selectCmpyInfoMngtList(vo);
	}
	
	/**
	 * 업체정보관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectCmpyInfoMngtListTotCnt(GamEntrpsInfoFVO vo) throws Exception {
		return gamCmpyInfoMngtDao.selectCmpyInfoMngtListTotCnt(vo);
    }

	
	/**
	 * 업체정보관리 상세
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public GamEntrpsInfoFVO selectCmpyInfoMngtDetail(GamEntrpsInfoFVO vo) throws Exception {
		return gamCmpyInfoMngtDao.selectCmpyInfoMngtDetail(vo);
	}

	
	/**
	 * 업체담당자 정보 등록
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public void insertCmpyChargerMngt(GamEntrpsChargerFVO vo) throws Exception {
		gamCmpyInfoMngtDao.insertCmpyChargerMngt(vo);
	}
}