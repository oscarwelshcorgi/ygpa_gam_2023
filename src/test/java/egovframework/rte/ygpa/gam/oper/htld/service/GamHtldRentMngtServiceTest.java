/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htld.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.TestInstance;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;

import egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtServiceImpl;

/**
 *
 * @author EUNSUNGJ
 * @since 2015. 6. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 6. 16.		EUNSUNGJ		최초 생성
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
public class GamHtldRentMngtServiceTest {
	@Resource(name="gamHtldRentMngtService")
	private GamHtldRentMngtService gamHtldRentMngtService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
//	private ApplicationContext context;

//	@Before
//	public void setUp() throws Exception {
//		String configLocation = "context-helloworld.xml";
//		context = new ClassPathXmlApplicationContext(configLocation);
//	}
//
//	@Test
//	public void testSayHello() {
//		HelloWorldService helloworld = (HelloWorldSer-vice)context.getBean("helloworld");
//		assertEquals( "Hello egov framework!!!", hel-loworld.sayHello() );
//	}

	/**
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtServiceImpl#insertHtldRentMngt(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO, java.util.List)}.
	 */
	@Test
	@Ignore
	public void testInsertHtldRentMngt1() {
		GamHtldRentMngtVO rentVo = new GamHtldRentMngtVO();
		GamHtldRentMngtDetailVO rentDetailVo = new GamHtldRentMngtDetailVO();
		List<GamHtldRentMngtDetailVO> createList = new ArrayList<GamHtldRentMngtDetailVO>();
		createList.add(rentDetailVo);
		try {
			rentVo.setPrtAtCode("622");
			rentVo.setRentAreaCd("WH");
			rentVo.setRentArea("WH");
			rentVo.setGrAr("6539");
			rentVo.setEntrpscd("00001275");
			rentVo.setEntrpsNm("(주)한진");
			rentVo.setDeptcd("20091");
			rentVo.setGrUsagePdFrom("2015-01-01");
			rentVo.setGrUsagePdTo("2016-12-31");
			rentVo.setTaxtSe("2");
			rentVo.setQuayGroupCd("H");
			rentVo.setChrgeKnd("HA");
			rentVo.setNticMth("1");
			rentDetailVo.setPrtAtCode(rentVo.getPrtAtCode());
			rentDetailVo.setAssetsUsageSeq("1");
			rentDetailVo.setGisAssetsCd("LHG");
			rentDetailVo.setGisAssetsSubCd("05");
			rentDetailVo.setUsageAr("6539");

			rentDetailVo.setUsagePdFrom(rentVo.getGrUsagePdFrom());
			rentDetailVo.setUsagePdTo(rentVo.getGrUsagePdTo());
			rentDetailVo.setApplcMth("6");
			rentDetailVo.setApplcPrice("1415");
			rentDetailVo.setQuayGroupCd(rentVo.getQuayGroupCd());
			gamHtldRentMngtService.insertHtldRentMngt(rentVo, createList);	// 일시납 1건
		}
		catch(Exception e) {
			assertTrue("Exception occur : "+ e.getMessage(), false);
		}
//		fail("Not yet implemented");
	}

	/**
	 * 반기별 사용료 생성
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtServiceImpl#insertHtldRentMngt(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO, java.util.List)}.
	 */
	@Test
	@Ignore
	public void testInsertHtldRentMngt2() {
		GamHtldRentMngtVO rentVo = new GamHtldRentMngtVO();
		GamHtldRentMngtDetailVO rentDetailVo = new GamHtldRentMngtDetailVO();
		List<GamHtldRentMngtDetailVO> createList = new ArrayList<GamHtldRentMngtDetailVO>();
		createList.add(rentDetailVo);
		try {
			rentVo.setPrtAtCode("622");
			rentVo.setRentAreaCd("WH");
			rentVo.setRentArea("WH");
			rentVo.setGrAr("68590");
			rentVo.setEntrpscd("00001275");
			rentVo.setEntrpsNm("(주)한진");
			rentVo.setDeptcd("20091");
			rentVo.setGrUsagePdFrom("2015-01-01");
			rentVo.setGrUsagePdTo("2016-12-31");
			rentVo.setTaxtSe("2");
			rentVo.setQuayGroupCd("H");
			rentVo.setChrgeKnd("HA");
			rentVo.setNticMth("2");
			rentDetailVo.setPrtAtCode(rentVo.getPrtAtCode());
			rentDetailVo.setAssetsUsageSeq("1");
			rentDetailVo.setGisAssetsCd("LHG");
			rentDetailVo.setGisAssetsSubCd("05");
			rentDetailVo.setUsageAr("68590");

			rentDetailVo.setUsagePdFrom(rentVo.getGrUsagePdFrom());
			rentDetailVo.setUsagePdTo(rentVo.getGrUsagePdTo());
			rentDetailVo.setApplcMth("6");
			rentDetailVo.setApplcPrice("1415");
			rentDetailVo.setQuayGroupCd(rentVo.getQuayGroupCd());
			gamHtldRentMngtService.insertHtldRentMngt(rentVo, createList);	// 일시납 1건
		}
		catch(Exception e) {
			assertTrue("Exception occur : "+ e.getMessage(), false);
		}
//		fail("Not yet implemented");
	}

	/**
	 * 3분기 별 사용료 생성
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtServiceImpl#insertHtldRentMngt(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO, java.util.List)}.
	 */
	@Test
	@Ignore
	public void testInsertHtldRentMngt3() {
		GamHtldRentMngtVO rentVo = new GamHtldRentMngtVO();
		GamHtldRentMngtDetailVO rentDetailVo = new GamHtldRentMngtDetailVO();
		List<GamHtldRentMngtDetailVO> createList = new ArrayList<GamHtldRentMngtDetailVO>();
		createList.add(rentDetailVo);
		try {
			rentVo.setPrtAtCode("622");
			rentVo.setRentAreaCd("WH");
			rentVo.setRentArea("WH");
			rentVo.setGrAr("68590");
			rentVo.setEntrpscd("00001275");
			rentVo.setEntrpsNm("(주)한진");
			rentVo.setDeptcd("20091");
			rentVo.setGrUsagePdFrom("2015-01-01");
			rentVo.setGrUsagePdTo("2016-12-31");
			rentVo.setTaxtSe("2");
			rentVo.setQuayGroupCd("H");
			rentVo.setChrgeKnd("HA");
			rentVo.setNticMth("3");
			rentDetailVo.setPrtAtCode(rentVo.getPrtAtCode());
			rentDetailVo.setAssetsUsageSeq("1");
			rentDetailVo.setGisAssetsCd("LHG");
			rentDetailVo.setGisAssetsSubCd("05");
			rentDetailVo.setUsageAr("68590");

			rentDetailVo.setUsagePdFrom(rentVo.getGrUsagePdFrom());
			rentDetailVo.setUsagePdTo(rentVo.getGrUsagePdTo());
			rentDetailVo.setApplcMth("6");
			rentDetailVo.setApplcPrice("1415");
			rentDetailVo.setQuayGroupCd(rentVo.getQuayGroupCd());
			gamHtldRentMngtService.insertHtldRentMngt(rentVo, createList);	// 일시납 1건
		}
		catch(Exception e) {
			assertTrue("Exception occur : "+ e.getMessage(), false);
		}
//		fail("Not yet implemented");
	}

	/**
	 * 4분기 별 사용료 생성
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtServiceImpl#insertHtldRentMngt(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO, java.util.List)}.
	 */
	@Test
	@Ignore
	public void testInsertHtldRentMngt4() {
		GamHtldRentMngtVO rentVo = new GamHtldRentMngtVO();
		GamHtldRentMngtDetailVO rentDetailVo = new GamHtldRentMngtDetailVO();
		List<GamHtldRentMngtDetailVO> createList = new ArrayList<GamHtldRentMngtDetailVO>();
		createList.add(rentDetailVo);
		try {
			rentVo.setPrtAtCode("622");
			rentVo.setRentAreaCd("WH");
			rentVo.setRentArea("WH");
			rentVo.setGrAr("68590");
			rentVo.setEntrpscd("00001275");
			rentVo.setEntrpsNm("(주)한진");
			rentVo.setDeptcd("20091");
			rentVo.setGrUsagePdFrom("2015-01-01");
			rentVo.setGrUsagePdTo("2016-12-31");
			rentVo.setTaxtSe("2");
			rentVo.setQuayGroupCd("H");
			rentVo.setChrgeKnd("HA");
			rentVo.setNticMth("4");
			rentDetailVo.setPrtAtCode(rentVo.getPrtAtCode());
			rentDetailVo.setGisAssetsPrtAtCode("622");
			rentDetailVo.setAssetsUsageSeq("1");
			rentDetailVo.setGisAssetsCd("LHG");
			rentDetailVo.setGisAssetsSubCd("05");
			rentDetailVo.setUsageAr("68590");

			rentDetailVo.setUsagePdFrom(rentVo.getGrUsagePdFrom());
			rentDetailVo.setUsagePdTo(rentVo.getGrUsagePdTo());
			rentDetailVo.setApplcMth("6");
			rentDetailVo.setApplcPrice("1415");
			rentDetailVo.setQuayGroupCd(rentVo.getQuayGroupCd());
			gamHtldRentMngtService.insertHtldRentMngt(rentVo, createList);	// 일시납 1건
		}
		catch(Exception e) {
			assertTrue("Exception occur : "+ e.getMessage(), false);
		}
//		fail("Not yet implemented");
	}

	/**
	 * 월별 별 사용료 생성
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtServiceImpl#insertHtldRentMngt(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO, java.util.List)}.
	 */
	@Test
	@Ignore
	public void testInsertHtldRentMngtMonth() {
		GamHtldRentMngtVO rentVo = new GamHtldRentMngtVO();
		GamHtldRentMngtDetailVO rentDetailVo = new GamHtldRentMngtDetailVO();
		List<GamHtldRentMngtDetailVO> createList = new ArrayList<GamHtldRentMngtDetailVO>();
		createList.add(rentDetailVo);
		try {
			rentVo.setPrtAtCode("622");
			rentVo.setRentAreaCd("WH");
			rentVo.setRentArea("WH");
			rentVo.setGrAr("68590");
			rentVo.setEntrpscd("00001275");
			rentVo.setEntrpsNm("(주)한진");
			rentVo.setDeptcd("20091");
			rentVo.setGrUsagePdFrom("2015-01-01");
			rentVo.setGrUsagePdTo("2016-12-31");
			rentVo.setTaxtSe("2");
			rentVo.setQuayGroupCd("H");
			rentVo.setChrgeKnd("HA");
			rentVo.setNticMth("5");
			rentDetailVo.setPrtAtCode(rentVo.getPrtAtCode());
			rentDetailVo.setAssetsUsageSeq("1");
			rentDetailVo.setGisAssetsCd("LHG");
			rentDetailVo.setGisAssetsSubCd("05");
			rentDetailVo.setUsageAr("68590");

			rentDetailVo.setUsagePdFrom(rentVo.getGrUsagePdFrom());
			rentDetailVo.setUsagePdTo(rentVo.getGrUsagePdTo());
			rentDetailVo.setApplcMth("6");
			rentDetailVo.setApplcPrice("1415");
			rentDetailVo.setQuayGroupCd(rentVo.getQuayGroupCd());
			gamHtldRentMngtService.insertHtldRentMngt(rentVo, createList);	// 일시납 1건
		}
		catch(Exception e) {
			assertTrue("Exception occur : "+ e.getMessage(), false);
		}
//		fail("Not yet implemented");
	}

	/**
	 * 연납 별 사용료 생성
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtServiceImpl#insertHtldRentMngt(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO, java.util.List)}.
	 */
	@Test
	@Ignore
	public void testInsertHtldRentMngtYear() {
		GamHtldRentMngtVO rentVo = new GamHtldRentMngtVO();
		GamHtldRentMngtDetailVO rentDetailVo = new GamHtldRentMngtDetailVO();
		List<GamHtldRentMngtDetailVO> createList = new ArrayList<GamHtldRentMngtDetailVO>();
		createList.add(rentDetailVo);
		try {
			rentVo.setPrtAtCode("622");
			rentVo.setRentAreaCd("WH");
			rentVo.setRentArea("WH");
			rentVo.setGrAr("68590");
			rentVo.setEntrpscd("00001275");
			rentVo.setEntrpsNm("(주)한진");
			rentVo.setDeptcd("20091");
			rentVo.setGrUsagePdFrom("2015-01-01");
			rentVo.setGrUsagePdTo("2016-12-31");
			rentVo.setTaxtSe("2");
			rentVo.setQuayGroupCd("H");
			rentVo.setChrgeKnd("HA");
			rentVo.setNticMth("6");
			rentDetailVo.setPrtAtCode(rentVo.getPrtAtCode());
			rentDetailVo.setAssetsUsageSeq("1");
			rentDetailVo.setGisAssetsPrtAtCode("622");
			rentDetailVo.setGisAssetsCd("LHG");
			rentDetailVo.setGisAssetsSubCd("05");
			rentDetailVo.setUsageAr("68590");

			rentDetailVo.setUsagePdFrom(rentVo.getGrUsagePdFrom());
			rentDetailVo.setUsagePdTo(rentVo.getGrUsagePdTo());
			rentDetailVo.setApplcMth("6");
			rentDetailVo.setApplcPrice("1415");
			rentDetailVo.setQuayGroupCd(rentVo.getQuayGroupCd());
			gamHtldRentMngtService.insertHtldRentMngt(rentVo, createList);	// 일시납 1건
		}
		catch(Exception e) {
			assertTrue("Exception occur : "+ e.getMessage(), false);
		}
//		fail("Not yet implemented");
	}

	/**
	 * 4분기 별 사용료 생성
	 * Test method for {@link egovframework.rte.ygpa.gam.oper.htld.service.impl.GamHtldRentMngtService#createHtldRentMngtFirst()}.
	 */
	@Test
	public void testCreateHtldRentMngtFirst() {
		try {
			gamHtldRentMngtService.createHtldRentMngtFirst();
		} catch (Exception e) {
			assertTrue("Exception occur : "+ e.getMessage(), false);
		}
	}

}
