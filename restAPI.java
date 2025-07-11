/import org.json.JsonObject;
import org.json.JsonArray;
import sapphire.util.StringUtil;
import sapphire.util.DataSet;
import java.text.SimpleDateFormat;

//Retrieve request id request from input
String requestID=StringUtil.replaceAll(inputs_SDC,String,'');


//Now run several sql queries on the request to check if it is cancelled or previosly sent.

//Let start defining variables for rest Api

String restBody ='';
String restLog='';
String restStatus='';

//SQL queries to verify if it was cancelled and then collect the timestamp
if((getSQLDataSet(SQL_query_to_find_cancelled_request.toString()).getValue(0,"modifieddt")).getRowCount()>0){
  logger.info("The");


}

//Let's set variables for connecting to azure endpoint to fetch token 
def exAppURL =inputs.lv_Endpoin;
def clientId=inputs.lv_clientId;//stored in Prpoerties environment variables , dont hard code
def secretKey=inputs.lv_secretKey;//stored in Prpoerties environment variables , dont hard code

//Let's set up HTTPS Post request body
def authMsg="grant_type=client_credentials"
authScope= clientId+"./default"//This will tell azure AD to grant default permission set for app
authMsg= authMsg+authScope;
authCred= clientId+":"+secretKey;
def encodedAuth=Base64.encodeToString(authCred.getBytes());

//Let post the API request

def connectionReq = new URL(exAppURL).openConnection();

connectionReq.setRequestMethod("POST");
connectionReq.setDoOutput(True);//
connectionReq.setRequestProperty("Authorization","Basic "+encodedAuth);
connectionReq.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
connectionReq.setRequestProperty("Accept","application/json");

//