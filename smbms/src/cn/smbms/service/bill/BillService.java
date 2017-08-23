package cn.smbms.service.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;

public interface BillService {
	/**
	 * ����ָ��������������
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
	 * ����ID��ȡ��Ʒ
	 * 
	 * @param id
	 * @return
	 */
	public Bill getBillById(Integer id);

	/**
	 * ��Ӷ���
	 */
	public boolean addBill(Bill bill);

	/**
	 * �޸Ķ���
	 */
	public boolean updateBill(Bill bill);

	/**
	 * ɾ������
	 */
	public boolean deleteBill(Integer id);
}
