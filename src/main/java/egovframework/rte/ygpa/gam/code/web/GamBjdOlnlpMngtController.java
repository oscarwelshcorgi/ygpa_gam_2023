/**
 *
 */
package egovframework.rte.ygpa.gam.code.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpLinkVO;
import egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtService;
import egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtVO;

/**
 * 공시지가 관리 컨트롤러
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
@Controller
public class GamBjdOlnlpMngtController {

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "gamBjdOlnlpMngtService")
	GamBjdOlnlpMngtService gamBjdOlnlpMngtService;

	/**
	 * 법정동 코드 조회 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamBjdOlnlpMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/code/GamBjdOlnlpMngt";
    }

    @RequestMapping(value="/code/selectBjdOlnlpList.do")
	@ResponseBody Map<String, Object> selectBjdOlnlpList(GamBjdOlnlpMngtVO searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List<?> bupjungdongCodeList = gamBjdOlnlpMngtService.selectBjdOlnlpList(searchVO);
    	int totalCount = gamBjdOlnlpMngtService.selectBjdOlnlpListTotCnt(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totalCount);
    	map.put("resultList", bupjungdongCodeList);
    	map.put("searchOption", searchVO);

    	return map;
	}

	@RequestMapping(value="/code/mergeGamBjdOlnlpMngt.do")
	@ResponseBody Map<String, Object> mergeGamOlnlpMngt(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	int resultCode = -1;
    	String resultMsg = "";

		insertList = mapper.readValue((String)dataList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)dataList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)dataList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		userList = new ArrayList();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		insertList.addAll(updateList);	// combine list

		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

		gamBjdOlnlpMngtService.mergeOlnlpMngt(mergeMap);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));

		return map;
	}


	@RequestMapping(value="/code/applyBjdOlnlpMngt.do")
	@ResponseBody Map<String, Object> applyOlnlpData(@RequestParam Map<String, Object> dataList) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		try {
			gamBjdOlnlpMngtService.applyOlnlpData();
		} catch(IOException e){
			
		}
		catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.merge"));
			return map;
		}
        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		return map;
	}





	@RequestMapping(value="/code/selectBjdOlnlpLink.do")
	@ResponseBody Map<String, Object> selectBjdOlnlpLink(GamBjdOlnlpLinkVO gamBjdOlnlpLinkVO) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Map<String,Object> map = new HashMap<String,Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	// 자산 등록 법정동 코드만 조회(전남 전체 쿼리 수정 적용하면 됨.)
    	List bupjungdongList = gamBjdOlnlpMngtService.selectBupjungdongCd();

    	Calendar cal = Calendar.getInstance();
    	String newYear = new SimpleDateFormat("yyyy").format(cal.getTime());
    	cal.add(Calendar.YEAR, -1);
    	String oldYear = new SimpleDateFormat("yyyy").format(cal.getTime());

    	for(int i=0; i<bupjungdongList.size(); i++) {
    		int pageNo = 1;
    		Map bupjungdong = (Map) bupjungdongList.get(i);
    		List bjdOlnlpLinkList = getBjdOlnlpLink((String)bupjungdong.get("bupjungdongCd"), newYear,pageNo );

    		if(bjdOlnlpLinkList.size() <= 0) {
    			cal.add(Calendar.YEAR, -1);
    			bjdOlnlpLinkList = getBjdOlnlpLink((String)bupjungdong.get("bupjungdongCd"), oldYear, pageNo);
    		}

    		if(bjdOlnlpLinkList.size() > 0) {
    			for(int j=0; j<bjdOlnlpLinkList.size(); j++) {
    				Map bjdOlnlpLinkData = (Map) bjdOlnlpLinkList.get(j);
    				Map bjdOlnlpLinkVO = new HashMap();
    				String mnnmSlno = "0000";
    				String mnnmSlnoSub = "0000";

    				mnnmSlno = bjdOlnlpLinkData.get("mnnmSlno").toString().split("-")[0];
    				if(bjdOlnlpLinkData.get("mnnmSlno").toString().contains("-")) {
    					mnnmSlnoSub = bjdOlnlpLinkData.get("mnnmSlno").toString().split("-")[1];
    				}

//    				System.out.println("고유번호 : "+bjdOlnlpLinkData.get("pnu"));
//    				System.out.println("법정동명 : "+bjdOlnlpLinkData.get("ldCodeNm"));
//    				System.out.println("지번 : "+StringUtils.leftPad(mnnmSlno,4,"0"));
//    				System.out.println("부번 : "+StringUtils.leftPad(mnnmSlnoSub,4,"0"));

    				bjdOlnlpLinkVO.put("olnlpApplcYear",bjdOlnlpLinkData.get("stdrYear"));
    				bjdOlnlpLinkVO.put("bupjungdongCd",bjdOlnlpLinkData.get("ldCode"));
    				bjdOlnlpLinkVO.put("lnm",StringUtils.leftPad(mnnmSlno,4,"0")+"-"+StringUtils.leftPad(mnnmSlnoSub,4,"0"));
    				bjdOlnlpLinkVO.put("mt",bjdOlnlpLinkData.get("stdLandAt"));
    				bjdOlnlpLinkVO.put("beginDt",bjdOlnlpLinkData.get("stdrYear")+"-01-01");
    				bjdOlnlpLinkVO.put("endDt",bjdOlnlpLinkData.get("stdrYear")+"-12-01");
    				bjdOlnlpLinkVO.put("olnlp",bjdOlnlpLinkData.get("pblntfPclnd"));
    				bjdOlnlpLinkVO.put("regUsr",loginVO.getId());

    				gamBjdOlnlpMngtService.insertBupjungdongOlnlpF(bjdOlnlpLinkVO);

					if(j == 99 && bjdOlnlpLinkList.size() == 100) {
						pageNo++;
						j=-1 ;
						bjdOlnlpLinkList = getBjdOlnlpLink((String)bupjungdong.get("bupjungdongCd"), newYear, pageNo);
			    		if(bjdOlnlpLinkList.size() <= 0) {
			    			cal.add(Calendar.YEAR, -1);
			    			bjdOlnlpLinkList = getBjdOlnlpLink((String)bupjungdong.get("bupjungdongCd"), oldYear, pageNo);
			    		}
					}

    			}
    		}

    		// 인서트 로직
    	}



    	map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.request.msg"));
		return map;
	}

	protected List getBjdOlnlpLink(String pnu, String stdrYear, int pageNo) throws Exception{
		List<Map> gamBjdOlnlpLinkList = new ArrayList<Map>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        StringBuilder urlBuilder = new StringBuilder("http://openapi.nsdi.go.kr/nsdi/IndvdLandPriceService/attr/getIndvdLandPriceAttr"); /* URL */
        StringBuilder parameter  = new StringBuilder();
        parameter.append("?" + URLEncoder.encode("authkey","UTF-8") + "=02097cfafcc9b69debc654"); /*authkey Key*/
        parameter.append("&" + URLEncoder.encode("pnu","UTF-8") + "=" + URLEncoder.encode(pnu, "UTF-8")); /* 고유번호(8자리 이상) */
        parameter.append("&" + URLEncoder.encode("stdrYear","UTF-8") + "=" + URLEncoder.encode(stdrYear, "UTF-8")); /* 기준년도(YYYY: 4자리) */
        parameter.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /* 응답결과 형식(xml 또는 json) */
        parameter.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /* 검색건수 */
        parameter.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8")); /* 페이지 번호 */

        URL url = new URL(urlBuilder.toString() + parameter.toString());
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("conntent-type", "application/json");

		BufferedReader rd;
		if(conn.getResponseCode()>=200&&conn.getResponseCode()<=300){
			rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}else{
			rd=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		//		StringBuilder sb = new StringBuilder();
		String result = "";
		String line;
		while((line=rd.readLine())!=null){
//			sb.append(line);
			result = result + line.trim();
		}

		// xml 파싱하기
        InputSource is = new InputSource(new StringReader(result));
        builder = factory.newDocumentBuilder();
        doc = builder.parse(is);

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        // XPathExpression expr = xpath.compile("/response/body/items/item");
        XPathExpression expr = xpath.compile("//fields/field");


        XPathExpression expr1 = xpath.compile("//totalCount");
        NodeList nodeList1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
//        System.out.println(nodeList1.item(0).getTextContent());


        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList child = nodeList.item(i).getChildNodes();
            Map data = new HashMap();
            for (int j = 0; j < child.getLength(); j++) {
                Node node = child.item(j);
                data.put(node.getLocalName(), node.getTextContent());
            }
            gamBjdOlnlpLinkList.add(data);
        }

		return gamBjdOlnlpLinkList;
	}


}
