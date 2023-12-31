/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpFVO;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpInqireService;
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

@Service("gamOlnlpInqireService")
public class GamOlnlpInqireServiceImpl extends AbstractServiceImpl implements GamOlnlpInqireService{

	@Resource(name="gamOlnlpInqireDao")
    private GamOlnlpInqireDao gamOlnlpInqireDao;


	/**
	 * 공시지가 등록현황 목록 조회
	 */
	public List selectOlnlpInsertList(GisAssetsCodeVO vo) throws Exception {
		return (List)gamOlnlpInqireDao.selectOlnlpInsertList(vo);
	}


	/**
	 * 공시지가 등록현황 목록 총 수
	 */
	public int selectOlnlpInsertListTotCnt(GisAssetsCodeVO vo) throws Exception {
		return gamOlnlpInqireDao.selectOlnlpInsertListTotCnt(vo);
	}

	/**
	 * 공시지가 목록 조회
	 */
	public List selectOlnlpInqireList(GamOlnlpFVO vo) throws Exception {
   		return (List)gamOlnlpInqireDao.selectOlnlpInqireList(vo);
	}


	/**
	 * 공시지가 목록 총 수
	 */
	public int selectOlnlpInqireListTotCnt(GamOlnlpFVO vo) throws Exception {
		return gamOlnlpInqireDao.selectOlnlpInqireListTotCnt(vo);
    }

}