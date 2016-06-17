/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticSchedulingService;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 17.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamHtldRentNticSchedulingService")
public class GamHtldRentNticSchedulingServiceImpl extends AbstractServiceImpl implements GamHtldRentNticSchedulingService {
	@Resource(name="gamHtldRentNticSchedulingDao")
	GamHtldRentNticSchedulingDao gamHtldRentNticScheduling;
	
	public void updateHtldRentNticIssueStatusRefresh() throws Exception {
		gamHtldRentNticScheduling.updateHtldRentNticIssueStatusRefreshRevcoll();
		gamHtldRentNticScheduling.updateHtldRentNticIssueStatusRefreshUnpaid();
	}
}
