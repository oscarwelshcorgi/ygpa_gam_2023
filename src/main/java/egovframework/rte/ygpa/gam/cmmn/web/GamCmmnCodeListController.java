package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;

@Controller
public class GamCmmnCodeListController {

	 /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
    
	@RequestMapping(value="/cmmn/selectGamCmmnCodeList.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamCmmnCodeMngt";
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/cmmn/selectDeptCodeOptionsList.do", method=RequestMethod.POST)
	@ResponseBody Map selectDeptCodeOptionsList(@RequestParam("cmd") String cmd) throws Exception {
		System.out.println("#######부서코드");
		// 부서코드
		Map optMap = new HashMap();
		List list = new ArrayList();
		/*
		Map item = null;
		item = new HashMap();
		item.put("value","ORGNZT_0000000000000");
		item.put("name","항만운영팀");
		list.add(item);
		item = new HashMap();
		item.put("value","ORGNZT_0000000000001");
		item.put("name","물류기획팀");
		list.add(item);
		item = new HashMap();
		item.put("value","ORGNZT_0000000000002");
		item.put("name","경영지원팀");
		list.add(item);
		item = new HashMap();
		item.put("value","ORGNZT_0000000000003");
		item.put("name","재무회계팀");
		list.add(item);
		item = new HashMap();
		item.put("value","ORGNZT_0000000000004");
		item.put("name","마케팅팀");
		list.add(item);
		*/
		
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setTableNm("COMTNORGNZTINFO");
		List<CmmnDetailCode> DeptCode = cmmUseService.selectOgrnztIdDetail(vo);
		
		for(CmmnDetailCode DeptList : DeptCode){
			Map item = new HashMap();
			item.put("value", DeptList.getCode());
			item.put("name", DeptList.getCodeNm());
			list.add(item);
		}
		
				
		optMap.put("resultList", list);

		return optMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/cmmn/selectCmmnCdOptionsList.do", method=RequestMethod.POST)
	@ResponseBody Map selectCmmnCdOptionsList(@RequestParam("code_id") String cmd) throws Exception {
		// 자산코드
		Map optMap = new HashMap();
		/*
		Map item = null;
		List list = new ArrayList();
		item = new HashMap();
		item.put("value","A");
		item.put("name","건물");
		list.add(item);
		item = new HashMap();
		item.put("value","E");
		item.put("name","기타");
		list.add(item);
		item = new HashMap();
		item.put("value","L");
		item.put("name","토지");
		list.add(item);
		item = new HashMap();
		item.put("value","S");
		item.put("name","구축물");
		list.add(item);
		item = new HashMap();
		item.put("value","w");
		item.put("name","창고");
		list.add(item);
		*/
		
		
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		codeVo.setCodeId(cmd);  
		List<CmmnDetailCode> Cd = cmmUseService.selectCmmCodeDetail(codeVo);
		List list = new ArrayList();
		for(CmmnDetailCode CmnCdList : Cd){
			Map item = new HashMap();
			item.put("value", CmnCdList.getCode());
			item.put("name", CmnCdList.getCodeNm());
			list.add(item);
		}
		
		optMap.put("resultList", list);

		return optMap;
	}

}
