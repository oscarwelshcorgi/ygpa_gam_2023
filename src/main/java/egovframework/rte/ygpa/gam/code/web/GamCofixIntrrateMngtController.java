/**
 *
 */
package egovframework.rte.ygpa.gam.code.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeVO;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateMngtService;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdVO;

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
 *  2015.3.3.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamCofixIntrrateMngtController {

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "gamCofixIntrrateMngtService")
	GamCofixIntrrateMngtService gamCofixIntrrateMngtService;

	/**
	 * 코픽스 이자율 조회 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamCofixIntrrateMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/code/GamCofixIntrrateMngt";
    }

    @RequestMapping(value="/code/selectCofixIntrrateList.do")
	@ResponseBody Map<String, Object> selectBupJungDongCodeList (GamCofixIntrrateDefaultVO searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List<?> cofixIntrrate = gamCofixIntrrateMngtService.selectCofixIntrrateList(searchVO);

		map.put("resultCode", 0);			// return ok
		if(cofixIntrrate!=null) map.put("totalCount", cofixIntrrate.size());
		else map.put("totalCount", 0);
    	map.put("resultList", cofixIntrrate);
    	map.put("searchOption", searchVO);

    	return map;
	}

    @RequestMapping(value="/code/updateCofixIntrrateList.do")
    public @ResponseBody Map updateCofixIntrrateList(
    		@RequestParam Map<String, Object> intrrateList)
            throws Exception {

     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        	List<GamCofixIntrrateVO> createList=null, updateList=null, deleteList=null;
        	if(intrrateList.containsKey("_cList")) {
    	    	createList = mapper.readValue((String)intrrateList.get("_cList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
    	    			GamCofixIntrrateVO.class));
//    	    	for(int i=0; i<createList.size(); i++) {
//    	    		GamCofixIntrrateVO vo = createList.get(i);
//    	    		vo.setRegUsr(loginVo.getId());
//    	    	}
        	}
        	if(intrrateList.containsKey("_uList")) {
    	    	updateList = mapper.readValue((String)intrrateList.get("_uList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
    	    			GamCofixIntrrateVO.class));
//    	    	for(int i=0; i<updateList.size(); i++) {
//    	    		GamHtldRentFeeMngtVO vo = updateList.get(i);
//    	    		vo.setUpdUsr(loginVo.getId());
//    	    	}
        	}
        	if(intrrateList.containsKey("_dList")) {
        		deleteList = mapper.readValue((String)intrrateList.get("_dList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
    	    			GamCofixIntrrateVO.class));
//    	    	for(int i=0; i<updateList.size(); i++) {
//    	    		GamHtldRentFeeMngtVO vo = updateList.get(i);
//    	    		vo.setUpdUsr(loginVo.getId());
//    	    	}
        	}

        	gamCofixIntrrateMngtService.mergeCofixIntrrate(createList, updateList, deleteList);

	         resultCode = 0;
	 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
    	}
    	catch(Exception e) {
	         resultCode = 0;
	 		 resultMsg  = egovMessageSource.getMessage("fail.common.update"); //정상적으로 처리되었습니다.
    	}

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

}
