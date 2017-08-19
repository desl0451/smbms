package cn.smbms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/sys/bill")
public class BillController {

	private Logger logger = Logger.getLogger(BillController.class);
	@Resource
	private BillService billService;

	@Resource
	private ProviderService providerService;

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
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
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
		// 总数量(表)
		int totalCount = billService.getBillCount(queryProductName, _queryProviderId, _queryIsPayment);
		// 总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// 控制首页和尾页
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
		return "billlist";
	}

	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "syserror";
	}

	/**
	 * 显示指定ID的Bill信息
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String getBillById(Model model, @PathVariable Integer id) {
		Bill bill = billService.getBillById(id);
		model.addAttribute("bill", bill);
		return "billview";
	}
}
