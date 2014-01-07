package egovframework.rte.cmmn;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.view.AbstractView;

public class AjaxXmlView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
 
		PrintWriter writer = response.getWriter();
//		Map<String, Object> outModel = new HashMap<String, Object>();
//		outModel.put("resultCode", model.get("resultCode"));
//		outModel.put("resultList", model.get("resultList"));
//
		model.remove("org.springframework.validation.BindingResult.erpAssetCdDefaultVO");
		JSONObject jsonObject = JSONObject.fromObject( model );  

		writer.write(jsonObject.toString());
		writer.close();
	}

}
