/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;

import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2015. 6. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 6. 10.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

//@RunWith(UnitilsJUnit4TestClassRunner.class)
public class GamHtldRentMngtServiceImplTest {

	private GamHtldRentMngtServiceImpl htldRentMngtService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		htldRentMngtService = new GamHtldRentMngtServiceImpl();
	}

	/**
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtServiceImpl#getTotalFee(org.joda.time.LocalDate, org.joda.time.LocalDate, java.math.BigDecimal)}.
	 */
	@Test
	public void testGetTotalFee() {
		GamHtldRentMngtServiceImpl htldRent = new GamHtldRentMngtServiceImpl();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date from, to;
		LocalDate fromDate, toDate;
		try {
			from = dateFormat.parse("2010-01-01");
			to = dateFormat.parse("2014-12-31");
			BigDecimal monthFee=new BigDecimal(135000);

			fromDate = new LocalDate(from);
			toDate = new LocalDate(to);

			BigDecimal totalFee = htldRentMngtService.getTotalFee(fromDate, toDate, monthFee);

			assertTrue("the first result is = "+totalFee.toString(), new BigDecimal(8100000).equals(totalFee));

			from = dateFormat.parse("2010-01-01");
			to = dateFormat.parse("2014-12-30");

			fromDate = new LocalDate(from);
			toDate = new LocalDate(to);

			totalFee = htldRentMngtService.getTotalFee(fromDate, toDate, monthFee);

			assertTrue("the first result is = "+totalFee.toString(), new BigDecimal(8095650).equals(totalFee));

			from = dateFormat.parse("2010-01-02");
			to = dateFormat.parse("2014-12-31");

			fromDate = new LocalDate(from);
			toDate = new LocalDate(to);

			totalFee = htldRentMngtService.getTotalFee(fromDate, toDate, monthFee);

			assertTrue("the first result is = "+totalFee.toString(), new BigDecimal(8095650).equals(totalFee));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
