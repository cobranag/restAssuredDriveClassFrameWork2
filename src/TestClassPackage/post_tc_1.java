package TestClassPackage;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import CommonfunctionPackage.API_Common_Function;
import CommonfunctionPackage.utility_Common_Function;
import requestRepositoryPackage.post_req_repository;
import io.restassured.path.json.JsonPath;

public class post_tc_1 {
	
	public static void execute() throws IOException
	{
		for (int i=0; i<5; i++)
		{
			int res_status_Code=API_Common_Function.response_statuscode(post_req_repository.base_URI(), 
					post_req_repository.post_resource(), post_req_repository.post_req_tc1());
			if(res_status_Code==201)
			{
				String responseBody=API_Common_Function.response_Body(post_req_repository.base_URI(),
						post_req_repository.post_resource(),post_req_repository.post_req_tc1());
				post_tc_1.validator(responseBody, res_status_Code);
				utility_Common_Function.evidencefilecreator("post_tc_1",post_req_repository.post_req_tc1(), responseBody);
				break;
			}
			else
			{
				System.out.println("Correct statusCode is not found hence retrying the API");
			}
		}
	}

	public static void validator(String responseBody, int res_status_Code) throws IOException
	{
		JsonPath jsp=new JsonPath(responseBody);
		String res_name=jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id=jsp.getString("id");
		String res_createdAt=jsp.getString("createdAt");
		
		String trim_date=res_createdAt.substring(0,10);
		
		LocalDateTime date=LocalDateTime.now();
		String exp_date=date.toString().substring(0, 10);
		
		JsonPath jspreq=new JsonPath(post_req_repository.post_req_tc1());
		String req_name=jspreq.getString("name");
		String req_job=jspreq.getString("job");
		
		Assert.assertEquals(res_status_Code, 201);
		Assert.assertEquals(req_name, res_name);
		Assert.assertEquals(req_job, res_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(trim_date, exp_date);
	}
}
