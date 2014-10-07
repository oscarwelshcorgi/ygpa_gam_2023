/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.soc.service.GamSocApplyService;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyVO;
/**
 * 
 * @author 김종민
 * @since 2014. 10. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 7.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamSocApplyService")
public class GamSocApplyServiceImpl implements GamSocApplyService {
	@Resource(name="gamSocApplyDao")
	private GamSocApplyDAO gamSocApplyDao;
	
	public GamSocApplyVO selectSocApplyDetailInquire(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyDetailInquire(searchVO);
	}

	public int selectSocApplyDetailTotCnt(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyDetailTotCnt(searchVO);
	}

	public List selectSocApplyList(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyList(searchVO);
	}

	public int selectSocApplyListTotCnt(GamSocApplyVO searchVO) {
		return gamSocApplyDao.selectSocApplyListTotCnt(searchVO);
	}

	public void insertSocApplyDetail(GamSocApplyVO insertVO) {
		gamSocApplyDao.insertSocApplyDetail(insertVO);
	}

	public void updateSocApplyDetail(GamSocApplyVO updateVO) {
		gamSocApplyDao.updateSocApplyDetail(updateVO);
	}

	public void deleteSocApplyDetail(GamSocApplyVO deleteVO) {
		gamSocApplyDao.deleteSocApplyDetail(deleteVO);
	}
}
