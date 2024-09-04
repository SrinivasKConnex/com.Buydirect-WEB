package reusableComponents;

import testBase.TestBase;

public class SQLQuery extends TestBase {
	
	public static String logo_Query = "select Partner_Image_URL from [dbo].[Partner_Image] where Partner_ID="
			+ partnerid + " and Partner_Image_Types_ID=1";

	public static String Tender_name_Query = "select Tender_Name_Header from [dbo].[Partner_BuyDirect_Settings] where partner_id="
			+ partnerid;

	public static String Get_Partner_namephonenumber_query = "select Product_Name,partner_contact_number  from"
			+ "  Partner_BuyDirect_Settings as ps inner JOIN partner_profile as pp on pp.partner_id=ps.partner_id where ps.partner_id="
			+ partnerid;

	public static String Get_Partner_phone_number_query = "	Select Partner_Contact_Number from [dbo].[Partner_Profile] where partner_id="
			+ partnerid;
	public static String dl_status = "select IsDLRequired  from Partner_BuyDirect_Settings where partner_id="
			+ partnerid;

	public static String plastic_status = "select Is_Plastics_Page_Displayed  from Partner_BuyDirect_Settings where partner_id="
			+ partnerid;

	public static String bank_intro_pageOption = "select Is_Banking_Intro_Page_Displayed from partner_buydirect_settings where partner_id="
			+ partnerid;

	public static String Update_plastic_statustoTwo = "update [dbo].[Partner_BuyDirect_Settings] set Is_Plastics_Page_Displayed=2 where partner_id="
			+ partnerid;
	public static String Update_BankIntroPage_statustoOne = "update [dbo].[Partner_BuyDirect_Settings] set Is_Banking_Intro_Page_Displayed=1 where partner_id="
			+ partnerid;
	public static String Update_BankIntroPage_statusZero = "update [dbo].[Partner_BuyDirect_Settings] set Is_Banking_Intro_Page_Displayed=0 where partner_id="
			+ partnerid;

	public static String Update_plastic_statustoone = "update [dbo].[Partner_BuyDirect_Settings] set Is_Plastics_Page_Displayed=1 where partner_id="
			+ partnerid;
	public static String Update_plastic_statustoZero = "update [dbo].[Partner_BuyDirect_Settings] set Is_Plastics_Page_Displayed=0 where partner_id="
			+ partnerid;

	public static String Welcomebody_Text3_Query = "SELECT  REPLACE(REPLACE(REPLACE(Welcome_Message,'<PartnerName>',Tender_Name_Body), '<PartnerPhone>', Partner_Contact_Number),'|', ' ') AS WelcomeMessage "
			+ "			FROM [dbo].[Partner_BuyDirect_Settings] PBS INNER JOIN [dbo].[Partner_Profile] PP "
			+ "			ON PBS.Partner_ID = PP.Partner_ID WHERE PBS.Partner_ID =" + partnerid;

	public static String Get_Theamcolor = "select Theme_Color from [dbo].[Partner_BuyDirect_Settings] where partner_id="
			+ partnerid;

	public static String Get_WelcomePage_ButtonText = "select Welcome_Screen_Button_Text from [dbo].[Partner_BuyDirect_Settings] where partner_id="
			+ partnerid;

	public static String Get_WelcomePage_SigninText = "select Welcome_Screen_SignInHere_Text from [dbo].[Partner_BuyDirect_Settings] where partner_id="
			+ partnerid;
	public static String Get_TextNotification_Text = "select SMS_Checkbox_text  from [dbo].[Partner_BuyDirect_Settings] where partner_id="
			+ partnerid;
	public static String Get_TermsAndCondition_Text = "select Terms_Checkbox_text  from [dbo].[Partner_BuyDirect_Settings] where partner_id="
			+ partnerid;
	public static String check_EmailAlredayExistStatus = "select Category_ID from [dbo].[Partner_Error_Codes_Category_Message] where   Category_ID=1 and partner_id="
			+ partnerid;

	public static String getEmailAlreadyExistCostomizedErrorMessage = "select Message from [dbo].[Partner_Error_Codes_Category_Message] where   Category_ID=1 and partner_id="
			+ partnerid;

	public static String getEmailAlreadyExistDefaultErrorMessage = "select Message from  [dbo].[Legacy_Error_Codes] where error_code=1345";

	public static String Get_plastic_request_subtitle = "select Plastics_Page_Subtitle from partner_buydirect_settings where partner_id="
			+ partnerid;
	public static String Get_plastic_request_BodyText = " SELECT REPLACE(REPLACE(Plastics_Page_Body, '\\n', ''), '|', '') AS plasticRequestBodyText	FROM [dbo].[Partner_BuyDirect_Settings]  WHERE Partner_ID ="
			+ partnerid;

	public static String check_plastic_indicator_flag = "SELECT cdf.Consumer_CDF_Value FROM [dbo].[Consumer_CDF] AS cdf INNER JOIN [dbo].[Consumer] AS consumer ON consumer.consumer_id = cdf.consumer_id WHERE cdf.Partner_CDF_Definition_ID = 4 AND consumer.partner_id = "
			+ partnerid + " AND consumer.Consumer_Email =";

	public static String get_BankintroPage_BodyText = "	SELECT  REPLACE(Banking_Intro_Page_Body,'<PartnerName>',Tender_Name_Body) AS BankingIntroPageBody FROM [dbo].[Partner_BuyDirect_Settings] PBS INNER JOIN [dbo].[Partner_Profile] PP \r\n"
			+ "ON PBS.Partner_ID = PP.Partner_ID WHERE PBS.Partner_ID =" + partnerid;
	
	public static String get_ErrorPage_Title="select Error_Page_Header from partner_buydirect_settings where partner_id="+partnerid;
	
	public static String get_ErrorPage_BodyText="SELECT  REPLACE(REPLACE(Error_Page_Body,'<PartnerName>',Tender_Name_Body), '<PartnerPhone>', Partner_Contact_Number)  AS Error_Page_Body\r\n"
			+ "FROM [dbo].[Partner_BuyDirect_Settings] PBS INNER JOIN [dbo].[Partner_Profile] PP\r\n"
			+ "ON PBS.Partner_ID = PP.Partner_ID WHERE PBS.Partner_ID ="+partnerid;

	public static String get_PIVData="SELECT requst.Consumer_Request FROM [dbo].[Consumer_Request] AS   requst INNER JOIN [dbo].[Consumer] AS consumer ON consumer.consumer_id = requst.consumer_id  WHERE requst.Consumer_Request_Description_ID = 2 AND consumer.partner_id ="+partnerid+"  AND consumer.Consumer_Email =";

	public static String getBankingRequestBodyTextQuery="SELECT  REPLACE(Banking_Request_Body,'|', '') AS Banking_Request_Body \n"
			+ "		FROM [dbo].[Partner_BuyDirect_Settings]  WHERE Partner_ID ="+partnerid;

	public static String getCDWPendingBodyText="select CDW_Pending_Body from Partner_BuyDirect_Settings where partner_id="+partnerid;

}
