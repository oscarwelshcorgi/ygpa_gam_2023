/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceService;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO;

/**
*
* @author LFIT
* @since 2019. 6. 19.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2019.6.19.		LFIT		최초 생성
*
* </pre>
*/

@Controller
public class GamRoadMaintenanceController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamRoadMaintenanceService")
	private GamRoadMaintenanceService gamRoadMaintenanceService;

    @Resource(name="gamRoadFileIdGnrService")
    EgovTableIdGnrService gamRoadFileIdGnrService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    protected static final Log LOG = LogFactory.getLog(GamRoadMaintenanceController.class);

	@RequestMapping(value="/fclty/gamRoadMaintenance.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamCvlEngFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamRoadMaintenance";

	}


	/**
	 * 임항도로 그룹 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamRoadMaintenanceList.do")
	public @ResponseBody Map gamRoadMaintenanceList(GamRoadMaintenanceVO searchVO)throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List gamRoadMngGroupList = gamRoadMaintenanceService.selectRoadMaintenanceList(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", gamRoadMngGroupList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@RequestMapping(value="/fclty/gamRoadMaintenanceFileList.do")
	@ResponseBody Map<String, Object> gamRoadMaintenanceFileList(GamRoadMaintenanceVO searchVO) throws Exception {

		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		// 내역 조회
		FileVO fileVO = new FileVO();
		List<FileVO> resultPhoto=null;
		List<FileVO> resultCompetInspctWtnnc=null;
		if(!"".equals(searchVO.getPhoto())) {
			fileVO.setAtchFileId(searchVO.getPhoto());
			resultPhoto = fileService.selectFileInfs(fileVO);
		}
		if(!"".equals(searchVO.getCompetInspctWtnnc())) {
			fileVO.setAtchFileId(searchVO.getCompetInspctWtnnc());
			resultCompetInspctWtnnc = fileService.selectFileInfs(fileVO);
		}

		map.put("resultCode", 0);			// return ok
		map.put("resultPhoto", resultPhoto);
		map.put("resultCompetInspctWtnnc", resultCompetInspctWtnnc);

		return map;

	}

	/**
	 * 임항도로 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamRoadMaintenanceDetailList.do")
	@ResponseBody Map<String, Object> gamRoadMaintenanceDetailList(GamRoadMaintenanceVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회

		/** List Data */
		List roadMaintenanceList = gamRoadMaintenanceService.selectRoadMaintenanceDetailList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", roadMaintenanceList);

    	return map;

	}


	@RequestMapping(value="/fclty/gamInsertRoadMaintenance.do")
	@ResponseBody Map<String, Object> gamInsertRoadMaintenance(final HttpServletRequest request, @RequestParam Map inputVO, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map insertRoadMaintenance = new HashMap();
    	List<HashMap<String,String>> insertObjList=null;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

//		beanValidator.validate(inputVO, bindingResult);

//		if (bindingResult.hasErrors()){
/*			model.addAttribute("inputVO", inputVO);
			return "/ygpa/gam/fclty/GamRoadMaintenance";
*/
//			Object[] error = bindingResult.getAllErrors().get(0).getArguments();
//			String msg = error[0].toString()+"가 부정확합니다.";

//    		map.put("resultCode", 1);
//   		map.put("resultMsg", msg);
//    		return map;
//		}

		insertRoadMaintenance = mapper.readValue((String)inputVO.get("detailForm"),
    		    new TypeReference<HashMap<String,String>>(){});

		insertObjList = mapper.readValue((String)inputVO.get("insertMntnObjList"),
				new TypeReference<List<HashMap<String,String>>>(){});

		// 파일 처리
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/*
		 * extract files
		 */
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(files!=null) {
			String photo = insertUpdateFile(files, "photo", (String)insertRoadMaintenance.get("photo"), null);
			insertRoadMaintenance.put("photo", photo);

			String[] delCompetInspctWtnnc = mapper.readValue((String)insertRoadMaintenance.get("delCompetInspctWtnnc"),
					new TypeReference<String[]>(){});
			String competInspctWtnnc = insertUpdateFile(files, "competInspctWtnnc", (String)insertRoadMaintenance.get("competInspctWtnnc"), null);
			insertRoadMaintenance.put("competInspctWtnnc", competInspctWtnnc);
		}


		try {
			gamRoadMaintenanceService.gamInsertRoadMaintenance (insertRoadMaintenance, insertObjList);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}

	@RequestMapping(value="/fclty/gamUpdateRoadMaintenance.do")
	@ResponseBody Map<String, Object> gamUpdateRoadMaintenance(final HttpServletRequest request, @RequestParam Map inputVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map insertRoadMaintenance = new HashMap();
    	List<HashMap<String,String>> insertObjList=null;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		insertRoadMaintenance = mapper.readValue((String)inputVO.get("detailForm"),
    		    new TypeReference<HashMap<String,String>>(){});

		insertObjList = mapper.readValue((String)inputVO.get("insertMntnObjList"),
				new TypeReference<List<HashMap<String,String>>>(){});

		// 파일 처리
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/*
		 * extract files
		 */
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(files!=null) {
			String delPhotoStr = (String)insertRoadMaintenance.get("delPhoto");
			String[] delPhoto = null;
			if(delPhotoStr!=null) {
				Integer[] delPhotoInt = mapper.readValue(delPhotoStr,
						new TypeReference<Integer[]>(){});
				delPhoto = new String[delPhotoInt.length];
					for(int i=0; i<delPhotoInt.length; i++) {
						delPhoto[i]=delPhotoInt[i].toString();
					}
			}
			String photo = insertUpdateFile(files, "photo", (String)insertRoadMaintenance.get("photo"), delPhoto);
			insertRoadMaintenance.put("photo", photo);

			String delCompetInspctWtnncStr = (String)insertRoadMaintenance.get("delCompetInspctWtnnc");

			String[] delCompetInspctWtnnc = null;
			if(delCompetInspctWtnncStr!=null) {
				Integer[] delCompetInspctWtnncInt = mapper.readValue(delCompetInspctWtnncStr,
					new TypeReference<Integer[]>(){});
				delCompetInspctWtnnc = new String[delCompetInspctWtnncInt.length];
				for(int i=0; i<delCompetInspctWtnncInt.length; i++) {
					delCompetInspctWtnnc[i]=delCompetInspctWtnncInt[i].toString();
				}
			}
			String competInspctWtnnc = insertUpdateFile(files, "competInspctWtnnc", (String)insertRoadMaintenance.get("competInspctWtnnc"), delCompetInspctWtnnc);
			insertRoadMaintenance.put("competInspctWtnnc", competInspctWtnnc);
		}

		try {
			gamRoadMaintenanceService.gamUpdateRoadMaintenance (insertRoadMaintenance, insertObjList);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
		return map;
	}


	private String insertUpdateFile(final Map<String, MultipartFile> files, String fileKeyPrefix, String fileId, String[] delFileSn) throws Exception {
		/*
		 * process files
		 */
		String uploadPath = EgovProperties.getProperty("prtfclty.fileStorePath");
		File saveFolder = new File(uploadPath);

		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
				.iterator();
		MultipartFile file;

		List<FileVO> fileList = null;
		List<FileVO> fileUpList = new ArrayList<FileVO>();

		String filePath;

		FileVO fileVo = null;

		boolean isNewFile=true;

		int maxFileSn=0;

		if(!"".equals(fileId)) {
			FileVO fvo = new FileVO();
			fvo.setAtchFileId(fileId);
			isNewFile=false;

			if(delFileSn!=null && delFileSn.length>0) {
				for(int i=0; i<delFileSn.length; i++) {
					fvo.setFileSn(delFileSn[i]);
					fileService.deleteFileInf(fvo);
				}
			}
			fileList = fileService.selectFileInfs(fvo);

			for(int i=0; i<fileList.size(); i++) {	// get Max sn
				FileVO vo = fileList.get(i);
				String sn_str = vo.getFileSn();
				try {
					int sn = Integer.parseInt(sn_str);
					if(sn>=maxFileSn) {
						maxFileSn=sn+1;
					}
				}
				catch(NumberFormatException e) {
					LOG.error("File SN invalid type.");
				}
			}
		}
		else {
			fileList = new ArrayList<FileVO>();
		}

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			System.out.println("[" + entry.getKey() + "]");

			file = entry.getValue();
			String[] tokens = file.getOriginalFilename().split("\\.(?=[^\\.]+$)");
			if (!"".equals(file.getOriginalFilename())) {
				if(entry.getKey().startsWith(fileKeyPrefix)) {
					fileVo = new FileVO();
					String fileSn="";
					if(isNewFile) fileId=gamRoadFileIdGnrService.getNextStringId();
					fileVo.setAtchFileId(fileId);
					fileSn = StringUtils.leftPad(Integer.toString(maxFileSn++), 2, "0");
					fileVo.setFileSn(fileSn);
					fileVo.setStreFileNm(fileId+fileSn);
					fileVo.setOrignlFileNm(file.getOriginalFilename());
					fileVo.setFileExtsn(tokens[1]);
					fileUpList.add(fileVo);
					filePath = uploadPath + fileVo.getStreFileNm();
					fileVo.setFileStreCours(uploadPath);
					file.transferTo(new File(filePath));
				}
			}
		}

		if(fileUpList.size()>0) {
			if(isNewFile) {
				fileService.insertFileInfs(fileUpList);
			}
			else {
				fileService.updateFileInfs(fileUpList);
			}
		}
		return fileId;
	}

	@RequestMapping(value="/fclty/gamDeleteRoadMaintenance.do")
	@ResponseBody Map<String, Object> gamDeleteRoadMaintenance(GamRoadMaintenanceVO deleteVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamRoadMaintenanceService.gamDeleteRoadMaintenance (deleteVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
		return map;
	}


	/**
	 * 임항도로 유지보수 출력
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */

    @RequestMapping(value="/fclty/gamRoadMaintenancePrint.do")
    public String gamRoadMaintenancePrint(GamRoadMaintenanceVO printVo, ModelMap model) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}


    	List roadMaintenanceDetailList = gamRoadMaintenanceService.selectRoadMaintenanceDetailPrint(printVo);

//    	roadMng = mapper.convertValue(searchOpt, GamRoadMngGroupVO.class);
//    	searchVO.setsFcltsMngGroupNo(roadMng.getFcltsMngGroupNo());

//		List roadMaintenanceList = gamRoadMaintenanceService.selectRoadMaintenanceList(searchVO);

    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("printVo", printVo);
    	model.addAttribute("roadMaintenanceDetailList", roadMaintenanceDetailList);

    	return "ygpa/gam/fclty/GamRoadMaintenancePrint";
    }

}

