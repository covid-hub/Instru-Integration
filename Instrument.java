//Let's connect an instrument

package com....labvantage.sapphire.sdms.attachmenthandler

import sapphire.util
import sapphire.attachment.attachment
import sapphire.util.StringUtil
import sapphire.util.Array

import java.io.InputStream
import java.io.InputStreamReader
import java.io.BufferReader
import java.io.straem.Collectors

public static Instrment extends BaseAttachmentHandler 

public void datahandling (Attachment attachment<String,String>) throws SapphireException {
if (attachments.isEmpty || attachments ==Null){
  throw new SapphireException('Instrument input file is not found')
try {
  for (Attachment attachment : attachments) {
    List<map<String,String>> results=pasrseFile(attachment);
    replicateData(results)
    saveData(results)

    for(row:results){
      logger.info(row.toString())
    }
  }
}
catch{
  sapphireException('Input file cauld not be parsed')
}
}

public static void parseFile(Attachment input) throws SapphireException {

Stream prFile=InputStream(input);
Srting cnFile=convertToString(prFile);
//1.Identify the header

List<map<String,String>> header=cnFile.subString(0,cnFile.indexOf("/n"));//0 starting index and last index not including
List<String> headerList=Array.straem(header).map(String::trim).collect(Collectors.toList());//Trim the header line of whitespaces ans empty strings

Aray content=removeExtraLine(cnFile)

List<Map<String,String>> readyData = new HashMap();
for(lines:content.split('\n')){
  List<String> words = Arrays.asList(lines.split('\t',-1))
  List a = new LinkedHashMap()

if(header.size()!=words.size()){
  new SapphireException('No. of column and headers and  not matching')
}else{
  for(i=0;i<=header.size;i++){
     a.put(header(i),words(i))
  }
  readyData.add(a);
}
readyData.straem().filter(condition->readyData.get('Sample id').startsWith('S-'))//Ensuring the rows that only start with Sample ids.

}

public static Sring convertToString(InputStream input){
 try(BufferReader bf=new BufferReader(new InputStreamReader(input))) {
 return bf.lines().collect(Collectors.joining("\n"));
 } catch (SapphireException e){
  throw new SapphireException("Input file can't be read",e);
 }

}

public static String removeExtraLine(String ex){
  return Array.straem(ex.split("\n"))
  .filter(lamb->!lamb.trim().isEmpty())//removing the empty lines
  .filter(lamb->!lamb.startsWith("Sample id"))//Removing the 1st header row
  .collect(Collectors.toList())

}
}

public void storeInLV(List<Map<String,String>> results) {
  List<sTRING> uniqueSampleIds=Arrays.stream(results).distinct().collect(Collectors.toList());
  List<String> uniqueMethodIds=Arrays.stream(results).distinct().collect(Collectors.toList());

  DataSet sampleIds=sampleDetails(results);
  DataSet methodsIds=methodDetails(results);
 
}

public Dataset sampleDetails(List<Map<>>){
  //Write your sql query to filter unique samples and return them in a dataset
}

public Dataset methodDetails(List<Map<>>){
  //Write your sql query to filter unique samples and return them in a dataset
}

for (String sample:uniqueSampleIds) {
  PropertyList dataPops=new PropertyList();

  for(String methodId:methodsIds){
    ArrayList dataset=new arrayList();
    ArrayList paramids=new ArrayList();
    ArrayList enteredText=new ArrayList();


    
      List<Map<>> sampleData=results.stream()
      .filter(row->row.get(Sample_id).equals(sample))
      .filter(row->row.get(Method).equals(methodId))
      .collect(Collectors.toList())

      for (Map<String,String> eachMap:results){
        paramids.add(LV.ANALYTE)
        enteredText.add(eachMap.get(Reported.analyte));
        
 }
 dataPops.setproperty("paramids",paramids)
 dataPops.setproperty("enteredtext",enteredText)

  }
  getActionProcessor.ProcessAction(EnterDataSet.id,EnterDataSet.version,dataPops)


}