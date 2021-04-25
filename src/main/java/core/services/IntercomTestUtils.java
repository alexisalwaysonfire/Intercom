package core.services;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.IntercomTestException;
import pojos.Customer;
import pojos.CustomerToInvite;

public class IntercomTestUtils {

	static String urlCustomers = "https://s3.amazonaws.com/intercom-take-home-test/customers.txt";
	public static String genericErrorMsg = "something went wrong, please have a cup of coffee and ring the admin";

	static double officeLatitudeInRadians = Math.toRadians(53.339428);
	static double officeLongitudeInRadians = Math.toRadians(-6.257664);
	static double degreeGreatCircleOfEarthInkm = 111.32;

	public static ArrayList<Customer> returnListOfCustomers() throws IntercomTestException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Scanner sc = null;
		try {
			URL url = new URL(urlCustomers);
			ObjectMapper mapper = new ObjectMapper();
			sc = new Scanner(url.openStream());
			while (sc.hasNextLine()) {
				Customer customer = mapper.readValue(sc.nextLine(), Customer.class);
				customers.add(customer);
			}

		} catch (IOException e) {
			throw new IntercomTestException(genericErrorMsg, e);
		} finally {
			sc.close();
		}
		return customers;
	}

	public static void measureDistance(Customer customer, LinkedList<CustomerToInvite> customersToInviteList) {
		double customerLatitudeInRadians = Math.toRadians(Double.parseDouble(customer.getLatitude()));
		double customerLongitudeInRadians = Math.toRadians(Double.parseDouble(customer.getLongitude()));
		double tmp = Math.acos(Math.sin(officeLatitudeInRadians) * Math.sin(customerLatitudeInRadians) //
				+ Math.cos(officeLatitudeInRadians) * Math.cos(customerLatitudeInRadians) //
						* Math.cos(officeLongitudeInRadians - customerLongitudeInRadians));

		tmp = Math.toDegrees(tmp);
		double distance = tmp * degreeGreatCircleOfEarthInkm;
		if (distance <= 100) {
			CustomerToInvite cus = new CustomerToInvite();
			cus.setUser_id(customer.getUser_id());
			cus.setName(customer.getName());
			cus.setDistanceToTheOffice(distance);
			customersToInviteList.add(cus);
		}
	}

	public static void voidSortList(LinkedList<CustomerToInvite> customersToInviteList) {
		Collections.sort(customersToInviteList, new Comparator<CustomerToInvite>() {
			@Override
			public int compare(CustomerToInvite c1, CustomerToInvite c2) {
				return c1.getUser_id() - c2.getUser_id();
			}
		});
	}

	public static void printCustomersToInvite(LinkedList<CustomerToInvite> customersToInviteList) {
		for (CustomerToInvite customerToInvite : customersToInviteList) {
			System.out.println("ID " + "\"" + customerToInvite.getUser_id() + //
					"\"" + " NAME " + "\"" + customerToInvite.getName() + "\"");
		}
	}

}
