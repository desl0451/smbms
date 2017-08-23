package cn.smbms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/sys/bill")
public class BillController {
	/**
	 * ��־����
	 */
	private Logger logger = Logger.getLogger(BillController.class);
	/**
	 * ����ҵ��
	 */
	@Resource
	private BillService billService;
	
	/**
	 * ��Ӧ��ҵ��
	 */
	@Resource
	private ProviderService providerService;
	/**
	 * ��ʾȫ������
	 * @param model
	 * @param queryProductName
	 * @param queryProviderId
	 * @param queryIsPayment
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.html")
	public String getBillList(Model model,
			@RequestParam(value = "queryProductName", required = false) String queryProductName,
			@RequestParam(value = "queryProviderId", required = false) String queryProviderId,
			@RequestParam(value = "queryIsPayment", required = false) String queryIsPayment,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) throws Exception {
		logger.info("getBillList------>queryProductName" + queryProductName);
		logger.info("getBillList------>queryProviderId" + queryProviderId);
		logger.info("getBillList------>queryIsPayment" + queryIsPayment);
		logger.info("getBillList------>pageIndex" + pageIndex);

		int _queryProviderId = 0;
		int _queryIsPayment = 0;
		List<Bill> billList = null;
		// ����ҳ������
		int pageSize = Constants.pageSize;
		// ��ǰҳ��
		int currentPageNo = 1;
		if (queryProductName == null) {
			queryProductName = "";
		}
		if (queryProviderId != null && !queryProviderId.equals("")) {
			_queryProviderId = Integer.parseInt(queryProviderId);
		}
		if (queryIsPayment != null && !queryIsPayment.equals("")) {
			_queryIsPayment = Integer.parseInt(queryIsPayment);
		}
		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/bill/syserror.html";
			}
		}
		// ������(��)
		int totalCount = billService.getBillCount(queryProductName, _queryProviderId, _queryIsPayment);
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
		billList = billService.getBillList(queryProductName, _queryProviderId, _queryIsPayment, currentPageNo,
				pageSize);
		List<Provider> providerList = providerService.getProviderAll();

		model.addAttribute("providerList", providerList);
		model.addAttribute("queryProductName", queryProductName);
		model.addAttribute("queryProviderId", _queryProviderId);
		model.addAttribute("queryIsPayment", _queryIsPayment);
		model.addAttribute("billList", billList);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "bill/billlist";
	}

	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "common/syserror";
	}

	/**
	 * ��ʾָ��ID��Bill��Ϣ
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String getBillById(Model model, @PathVariable Integer id) {
		Bill bill = billService.getBillById(id);
		model.addAttribute("bill", bill);
		return "bill/billview";
	}

	/**
	 * �޸�Bill��Ϣ
	 */
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public String modify(Model model, @PathVariable Integer id) {
		Bill bill = billService.getBillById(id);
		model.addAttribute("bill", bill);
		return "bill/billmodify";
	}

	/**
	 * �޸�����->���ع�Ӧ����Ϣ
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modify/providerlist.json")
	@ResponseBody
	public List<Provider> getProviderList() {
		List<Provider> providerList = new ArrayList<Provider>();
		providerList = providerService.getProviderAll();
		return providerList;
	}

	/**
	 * �޸���ҳ-������Bill��Ϣ
	 */
	@RequestMapping(value = "/modifysave.html", method = RequestMethod.POST)
	public String modifysave(Bill bill) {
		bill.setCreatedBy(1);
		bill.setCreationDate(new Date());
		if (billService.updateBill(bill)) {
			return "redirect:/sys/bill/list.html";
		}
		return "redirect:/sys/bill/list.html";
	}

	/**
	 * ��Ӷ���ҳ��
	 */
	@RequestMapping(value = "/addlist.html")
	public String addbillpage() {
		return "bill/billadd";
	}

	/**
	 * ���涩��
	 */
	@RequestMapping(value = "/insert.html", method = RequestMethod.POST)
	public String billSave(Bill bill) {
		bill.setCreatedBy(1);
		bill.setCreationDate(new Date());
		if (billService.addBill(bill)) {
			return "redirect:/sys/bill/list.html";
		}
		return "bill/billadd";
	}

	/**
	 * ɾ������
	 */
	@RequestMapping(value = "/delete/deleteprovider.json", method = RequestMethod.GET)
	@ResponseBody
	public String billDelete(@RequestParam String billid) {
		logger.debug("billController id====================" + billid);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(billid)) {
			hashMap.put("delResult", "notexist");
		} else {
			if (billService.deleteBill(Integer.parseInt(billid))) {
				hashMap.put("delResult", "true");
			} else {
				hashMap.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(hashMap);
	}
}
