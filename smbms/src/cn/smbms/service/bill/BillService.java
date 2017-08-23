package cn.smbms.service.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;

public interface BillService {
	/**
	 * 返回指定条件订单数量
	 * @param queryProductName
	 * @param queryProviderId
	 * @param queryIsPayment
	 * @return
	 * @throws Exception
	 */
	public int getBillCount(String queryProductName, Integer queryProviderId, Integer queryIsPayment) throws Exception;

	public List<Bill> getBillList(String queryProductName, Integer queryProviderId, Integer queryIsPayment,
			Integer currentPageNo, Integer pageSize) throws Exception;

	/**
	 * 根据ID提取商品
	 * 
	 * @param id
	 * @return
	 */
	public Bill getBillById(Integer id);

	/**
	 * 添加订单
	 */
	public boolean addBill(Bill bill);

	/**
	 * 修改订单
	 */
	public boolean updateBill(Bill bill);

	/**
	 * 删除订单
	 */
	public boolean deleteBill(Integer id);
}
