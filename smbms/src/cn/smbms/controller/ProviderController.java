package cn.smbms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Provider;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/sys/provider")
public class ProviderController {
	private Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private ProviderService providerService;

	/**
	 * ��ʾȫ����Ӧ����Ϣ
	 * 
	 * @param model
	 * @param queryProCode
	 * @param queryProName
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.html")
	public String getProviderList(Model model,
			@RequestParam(value = "queryProCode", required = false) String queryProCode,
			@RequestParam(value = "queryProName", required = false) String queryProName,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) throws Exception {
		logger.info("getUserList----->queryUserName:" + queryProCode);
		logger.info("getUserList----->queryUserRole:" + queryProName);
		logger.info("getUserList----->pageIndex:" + pageIndex);
		List<Provider> providerList = null;
		// ����ҳ������
		int pageSize = Constants.pageSize;
		// ��ǰҳ��
		int currentPageNo = 1;
		if (queryProCode == null) {
			queryProCode = "";
		}
		if (queryProName == null) {
			queryProName = "";
		}
		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/pro/syserror.html";
			}
		}
		// ������(��)
		int totalCount = providerService.getProviderCount(queryProCode, queryProName);
		// ��ҳ��
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// ������ҳ��βҳ
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		providerList = providerService.getProviderList(queryProCode, queryProName, currentPageNo, pageSize);
		model.addAttribute("providerList", providerList);
		model.addAttribute("queryProCode", queryProCode);
		model.addAttribute("queryProName", queryProName);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);

		return "providerlist";
	}

	/**
	 * ��ת����ҳ
	 * 
	 * @return
	 */
	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "syserror";
	}

	/**
	 * ����ID��ȡ��Ӧ����Ϣ
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String getProviderByID(Model model, @PathVariable Integer id) {
		Provider provider = providerService.getProviderById(id);
		model.addAttribute("provider", provider);
		return "providerview";
	}

	/**
	 * �޸Ĺ�Ӧ����Ϣ
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public String modifyProvider(Model model, @PathVariable Integer id) {
		Provider provider = providerService.getProviderById(id);
		model.addAttribute("provider", provider);
		return "providermodify";
	}

	/*********************************************************************/
	/* ######��ӿ�ʼ###### */
	/*********************************************************************/
	/**
	 * ��ת�����ҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addProvider.html")
	public String addProvider(@ModelAttribute("provider") Provider provider) {
		return "provideradd";
	}

	/**
	 * ��ӹ�Ӧ����Ϣ
	 * 
	 * @param provider
	 * @return
	 */
	@RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
	public String addProviderSave(Provider provider, HttpSession session) {
		provider.setCreationDate(new Date());
		provider.setCreatedBy(1);
		if (providerService.add(provider)) {
			return "redirect:/sys/provider/list.html";
		}
		return "provideradd";
	}
	/*********************************************************************/
	/* ######��ӽ���###### */
	/*********************************************************************/

	/*********************************************************************/
	/* ######ɾ����ʼ###### */
	/*********************************************************************/
	/**
	 * ɾ����Ӧ����Ϣ
	 */
	@RequestMapping(value = "/delete/deleteprovider.json", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteProvider(@RequestParam String proid) {
		logger.debug("deleteProvider proid===================== " + proid);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(proid)) {
			hashMap.put("delResult", "null");
		} else {
			Provider provider = providerService.getProviderById(Integer.parseInt(proid));
			if (provider == null) {
				hashMap.put("delResult", "notexist");
			} else {
				if (providerService.delete(Integer.parseInt(proid))) {
					hashMap.put("delResult", "true");
				}
			}
		}
		return JSONArray.toJSONString(hashMap);
	}
}
