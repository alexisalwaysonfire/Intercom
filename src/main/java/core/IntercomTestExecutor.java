package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import core.services.IntercomTestUtils;
import exceptions.IntercomTestException;
import pojos.Customer;
import pojos.CustomerToInvite;

public class IntercomTestExecutor {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		try {
			ArrayList<Customer> customers = IntercomTestUtils.returnListOfCustomers();
			if (customers.isEmpty()) {
				System.out.println(IntercomTestUtils.genericErrorMsg);
			} else {
				LinkedList<CustomerToInvite> customersToInviteList = new LinkedList<CustomerToInvite>();
				for (Customer customer : customers) {
					IntercomTestUtils.measureDistance(customer, customersToInviteList);
				}

				if (customersToInviteList.isEmpty()) {
					System.out.println(IntercomTestUtils.genericErrorMsg);
				} else {
					IntercomTestUtils.voidSortList(customersToInviteList);
					IntercomTestUtils.printCustomersToInvite(customersToInviteList);
				}
			}

		} catch (IntercomTestException e) {
			System.out.println("woops " + e.getMessage());
		}
	}

}
