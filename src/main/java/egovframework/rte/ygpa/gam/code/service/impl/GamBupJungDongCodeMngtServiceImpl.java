/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamBupJungDongCodeMngtService")
public class GamBupJungDongCodeMngtServiceImpl extends AbstractServiceImpl implements GamBupJungDongCodeMngtService{

	@Resource(name="gamBupJungDongCodeMngtDao")
  private GamBupJungDongCodeMngtDao gamBupJungDongCodeMngtDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeService#selectOlnlpInsertList(egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeVO)
	 */
	@Override
	public List selectBupJungDongCodeList(
			GamBupJungDongCodeDefaultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamBupJungDongCodeMngtDao.selectBupJungDongCodeList(vo);
	}



}