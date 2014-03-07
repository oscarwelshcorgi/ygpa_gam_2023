/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsChargerFVO;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsInfoFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwInfoFVO;

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
@Repository("gamCmpyInfoMngtDao")
public class GamCmpyInfoMngtDao extends YGPAAbstractDAO{

	/**
	 * 업체정보관리 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GamEntrpsInfoFVO> selectCmpyInfoMngtList(GamEntrpsInfoFVO vo) throws Exception{
		return list("gamCmpyInfoMngtDao.selectCmpyInfoMngtList", vo);
	}

	
	/**
	 * 업체정보관리 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectCmpyInfoMngtListTotCnt(GamEntrpsInfoFVO vo) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCmpyInfoMngtDao.selectCmpyInfoMngtListTotCnt", vo);
	}

	
	/**
	 * 업체정보관리 상세화면
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public GamEntrpsInfoFVO selectCmpyInfoMngtDetail(GamEntrpsInfoFVO vo) throws Exception{
		return (GamEntrpsInfoFVO) selectByPk("gamCmpyInfoMngtDao.selectCmpyInfoMngtDetail", vo);
	}
	
	
	/**
	 * 업체담당자 정보 저장
	 * @param vo
	 */
	public void insertCmpyChargerMngt(GamEntrpsChargerFVO vo){
		insert("gamCmpyInfoMngtDao.insertCmpyChargerMngt", vo);
	}
}