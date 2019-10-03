package com.dao;

import java.sql.Connection;

import com.model.Customer;
import com.utilities.*;
import java.sql.*;
public class CustomerDaoImpl implements CustomerDao{
	private Connection conn = ConnectionManager.createConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	@Override
	public boolean createCustomer(Customer customer) {
		
		try
		{
			ps = conn.prepareStatement("insert into customer values(?,?)");
			ps.setString(1, customer.getUserName());
			ps.setString(2, customer.getPassword());
			int rowAffected = ps.executeUpdate();
			if(rowAffected>0) {
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean validateCustomer(Customer customer) {
		
		try
		{
			ps = conn.prepareStatement("Select * from customer where userid=? and passwd=?");
			ps.setString(1, customer.getUserName());
			ps.setString(2, customer.getPassword());
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}

}
