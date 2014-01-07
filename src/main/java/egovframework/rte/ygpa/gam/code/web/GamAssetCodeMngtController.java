package egovframework.rte.ygpa.gam.code.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.cmmn.AjaxXmlView;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamAssetCodeService;


/**
 * @author eunsungj
 *
 */
@Controller
public class GamAssetCodeMngtController {

    @Resource(name = "gamAssetCodeService")
    private GamAssetCodeService gamAssetCodeService;

    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

    @RequestMapping(value="/code/mngt/assetCodeMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/code/mngt/GamAssetCodeMngt";
    }

    @RequestMapping(value="/code/mngt/selectAssetCodeList.do")
    ModelAndView selectAssetList(@RequestParam Map<String,Object> searchOpt) throws Exception {
    	int totalCnt, page, firstIndex;
//
//    	searchOpt.put("prtAtCode", prtAtCode);
    	page = Integer.parseInt((String)searchOpt.get("page"));
    	if(page<0) page=0;
    	totalCnt = Integer.parseInt((String)searchOpt.get("recordCountPerPage"));
    	if(totalCnt<5) totalCnt=15;
    	firstIndex = (page-1)*totalCnt;
//    	if(searchOpt.containsKey("recordCountPerPage"))searchOpt.
//    	else 
    		searchOpt.put("recordCountPerPage", totalCnt);
    	searchOpt.put("firstIndex", firstIndex);
//    	
    	List gamAssetList = gamAssetCodeService.selectAssetCodeList(searchOpt);

        int totCnt = gamAssetCodeService.selectAssetCodeListTotCnt(searchOpt);

    	ModelAndView model = new ModelAndView(new AjaxXmlView());

    	model.addObject("searchOpt", searchOpt);
//    	model.addObject("firstIndex", 0);
    	model.addObject("page", page);
    	model.addObject("lastIndex", 15);
    	model.addObject("resultList", gamAssetList);
        model.addObject("totalCount", totCnt);
    	
    	return model;
    }

    @RequestMapping(value="/code/mngt/insertAssetCode.do")
    ModelAndView insertAssetCode(
    		@ModelAttribute("assetCode") Map assetCode,
            BindingResult bindingResult, Model model, SessionStatus status) throws Exception {
    	ModelAndView result = new ModelAndView(new AjaxXmlView());

    	beanValidator.validate(assetCode, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		result.addObject("assetCode", assetCode);
    		result.addObject("resultCode", "-1");
    		result.addObject("resultMsg", "Binding Error!!");
    	}
    	else {
    		gamAssetCodeService.insertAssetCode(assetCode);
    		
    		status.setComplete();
    		
    		result.addObject("resultCode", "0");
    		result.addObject("resultMsg", "Done.");

    	}
    	
    	return result;
    }
    
    @RequestMapping("/code/mngt/updateAssetCode.do")
    public ModelAndView updateAssetCode(
            @ModelAttribute("assetCode") Map assetCode, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {
    	ModelAndView result = new ModelAndView(new AjaxXmlView());

    	beanValidator.validate(assetCode, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		result.addObject("assetCode", assetCode);
    		result.addObject("resultCode", "-1");
    		result.addObject("resultMsg", "Binding Error!!");
    	}
    	else {
    		gamAssetCodeService.updateAssetCode(assetCode);
	        status.setComplete();
    		result.addObject("resultCode", "0");
    		result.addObject("resultMsg", "Done.");
    	}
        return result;
    }
    
    @RequestMapping("/code/mngt/deleteAssetCode.do")
    public ModelAndView deleteAssetCode(
            @ModelAttribute("assetCode") Map assetCode, SessionStatus status)
            throws Exception {
    	ModelAndView result = new ModelAndView(new AjaxXmlView());
    	gamAssetCodeService.deleteAssetCode(assetCode);
        status.setComplete();
		result.addObject("resultCode", "0");
		result.addObject("resultMsg", "Done.");
        return result;
    }

//    protected void initBinder(HttpServletRequest request,  ServletRequestDataBinder binder) throws Exception{
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//        binder.registerCustomEditor(Date.class, editor);
//}
//
//@Override
//protected Object formBackingObject(HttpServletRequest request)
//                throws Exception {
//        SpringJsonForm bean = new SpringJsonForm();
//        bean.setBirthday(new Date());
//        bean.setPlaceofbirth("Sydney");
//        return bean;
//}
//
//public void onSubmitAction(Object command, BindException errors) {
//        SpringJsonForm bean = (SpringJsonForm) command;
//}

}
