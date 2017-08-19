package cn.smbms.service.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;

public interface BillService {
	public int getBillCount(String queryProductName, Integer queryProviderId,Integer queryIsPayment) throws Exception;

	public List<Bill> getBillList(String queryProductName, Integer queryProviderId,Integer queryIsPayment,
			Integer currentPageNo, Integer pageSize) throws Exception;
	
	/**
	 * 根据ID提取商品
	 * @param id
	 * @return
	 */
	public Bill getBillById(Integer id);
}
