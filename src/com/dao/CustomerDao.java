package com.dao;
import com.model.Customer;
public interface CustomerDao {

	public boolean createCustomer(Customer cutomer);
	public boolean validateCustomer(Customer cutomer);
}
