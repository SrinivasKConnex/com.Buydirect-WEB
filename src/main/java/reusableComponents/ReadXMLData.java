package reusableComponents;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import dbModel.Consumer;



public class ReadXMLData {

	public static Consumer readXMLData(String dbData) {
		Consumer consumer = new Consumer();
		try {
			// Parse XML string to JsonNode
			XmlMapper xmlMapper = new XmlMapper();
			JsonNode jsonNode = xmlMapper.readTree(dbData);

			// Convert JsonNode to JSONObject
			JSONObject dbDataJson = (JSONObject) new JSONParser().parse(jsonNode.toString());

			// Extract and set DOB
			String dob = (String) dbDataJson.get("DOB");
			consumer.setDob(dob);

			// Extract and set Name
			JSONObject nameObj = (JSONObject) dbDataJson.get("Name");
			Consumer.Name name = new Consumer.Name();
			name.setFirstName((String) nameObj.get("FirstName"));
			name.setLastName((String) nameObj.get("LastName"));
			consumer.setName(name);

			// Extract and set Address
			JSONObject addressObj = (JSONObject) dbDataJson.get("Address");
			Consumer.Address address = new Consumer.Address();
			address.setAddress1((String) addressObj.get("StreetName"));
			address.setZip((String) addressObj.get("Zip"));
			address.setAddressLine2((String) addressObj.get("AddressLine2"));
			address.setPoBox((String) addressObj.get("PoBox"));
			address.setCity((String) addressObj.get("City"));

			JSONObject stateObj = (JSONObject) addressObj.get("State");
			Consumer.addressState addstate = new Consumer.addressState();
			addstate.setStateAbbreviation((String) stateObj.get("StateAbbreviation"));
			address.setState(addstate);
			consumer.setAddress(address);

			// Extract and set Contact
			JSONObject contactObj = (JSONObject) dbDataJson.get("Contact");
			Consumer.Contact contact = new Consumer.Contact();
			contact.setMobileNumber((String) contactObj.get("MobileNumber"));
			consumer.setContact(contact);

			JSONObject DriversLicenseobj = (JSONObject) dbDataJson.get("DriversLicense");
			String LicenseNumber = (String) DriversLicenseobj.get("License");
			Consumer.DriversLicense DL = new Consumer.DriversLicense();
			DL.setLicense(LicenseNumber);
			
			JSONObject dlstateobj = (JSONObject) DriversLicenseobj.get("State");
			Consumer.State ss=new Consumer.State();
			ss.setStateAbbreviation((String) dlstateobj.get("StateAbbreviation"));
			DL.setState(ss);
			consumer.setDriversLicense(DL);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return consumer;
	}

}
