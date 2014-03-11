/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpFVO;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpMngtService;
import egovframework.rte.ygpa.gam.code.service.GisAssetsCodeVO;
/**
 * 
 * @author kok
 * @since 2014. 3. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamOlnlpMngtService")
public class GamOlnlpMngtServiceImpl extends AbstractServiceImpl implements GamOlnlpMngtService{

	@Resource(name="gamOlnlpMngtDao")
    private GamOlnlpMngtDao gamOlnlpMngtDao;
	
	
	/**
	 * 공시지가 등록현황 목록 조회
	 */
	public List<GisAssetsCodeVO> selectOlnlpInsertList(GisAssetsCodeVO vo) throws Exception {
		return (List<GisAssetsCodeVO>)gamOlnlpMngtDao.selectOlnlpInsertList(vo);
	}
	
	
	/**
	 * 공시지가 등록현황 목록 총 수
	 */
	public int selectOlnlpInsertListTotCnt(GisAssetsCodeVO vo) throws Exception {
		return gamOlnlpMngtDao.selectOlnlpInsertListTotCnt(vo);
	}
	
	/**
	 * 공시지가 목록 조회
	 */
	public List<GamOlnlpFVO> selectOlnlpMngtList(GamOlnlpFVO vo) throws Exception {
   		return (List<GamOlnlpFVO>)gamOlnlpMngtDao.selectOlnlpMngtList(vo);
	}
	
	
	/**
	 * 공시지가 목록 총 수
	 */
	public int selectOlnlpMngtListTotCnt(GamOlnlpFVO vo) throws Exception {
		return gamOlnlpMngtDao.selectOlnlpMngtListTotCnt(vo);
    }

	
	/**
	 * 공시지가 관리 등록화면
	 */
	public void insertOlnlpMngt(GamOlnlpFVO vo) throws Exception {
		gamOlnlpMngtDao.insertOlnlpMngt(vo);
	}
	
	
	/**
	 * 공시지가 관리 수정화면
	 */
	public void updateOlnlpMngt(GamOlnlpFVO vo) throws Exception {
		gamOlnlpMngtDao.updateOlnlpMngt(vo);
	}
	

	/**
	 * 공시지가 관리 삭제
	 */
	public void deleteOlnlpMngt(GamOlnlpFVO vo) {
		gamOlnlpMngtDao.deleteOlnlpMngt(vo);
	}
}