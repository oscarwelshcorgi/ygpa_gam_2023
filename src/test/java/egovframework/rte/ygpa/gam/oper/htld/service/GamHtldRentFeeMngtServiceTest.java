/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htld.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.mock.core.matching.impl.AssertNotInvokedVerifyingMatchingInvocationHandler;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author EUNSUNGJ
 * @since 2015. 6. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 6. 17.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
//"classpath:/springmvc/egov-com-servlet.xml",
"classpath:/egovframework/spring/com/context-common.xml",
"classpath:/egovframework/spring/com/context-properties.xml",
"classpath:/egovframework/spring/com/context-datasource.xml",
"classpath:/egovframework/spring/com/context-sqlMap.xml",
"classpath:/egovframework/spring/com/context-idgen.xml",
"classpath:/egovframework/spring/com/context-excel.xml",
"classpath:/egovframework/spring/com/context-transaction.xml",
"classpath:/egovframework/spring/com/context-validator.xml",
"classpath:/egovframework/spring/context-ygpa-idgen.xml",
"classpath:/egovframework/spring/context-sqlMap.xml",
"classpath:/egovframework/spring/context-datasource.xml"
})
@Transactional(TransactionMode.ROLLBACK)
public class GamHtldRentFeeMngtServiceTest {

	@Resource(name="gamHtldRentFeeMngtService")
	private GamHtldRentFeeMngtService gamHtldRentFeeMngtService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentFeeMngtService#selectHtldRentFeeMngtList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeDefaultVO)}.
	 */
	@Test
	public void testSelectHtldRentFeeMngtList() {
		try {
			GamHtldRentFeeDefaultVO searchVO = new GamHtldRentFeeDefaultVO();

			searchVO.setNticPdFrom("2015-06-18");

			List<EgovMap> list = gamHtldRentFeeMngtService.selectHtldRentFeeMngtList(searchVO);
			int tot_cnt = gamHtldRentFeeMngtService.selectHtldRentFeeMngtListTotCnt(searchVO);
			for(int i=0; i<list.size(); i++) {
				System.out.println(">>>>> item ("+Integer.toString(i)+") : "+ list.get(i).toString());
			}

			assertTrue("wrong number of result count", list.size()==tot_cnt);
		}
		catch(Exception e) {
			assertTrue("Exception occur : "+ e.getMessage(), false);
		}
	}

}
