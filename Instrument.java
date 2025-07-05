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
//1.Identify the header
Stream prFile=InputStream(input);
Srting cnFile=convertToString(prFile);
List<map<String,String>> grFile=prFile.straem().
}

public static void convertToString(InputStream input){
 try(BufferReader bf=new BufferReader(new InputStreamReader(input))) {
 return bf.lines().collect(Collectors.joining("\n"));
 } catch (SapphireException e){
  throw new SapphireException("Input file can't be read",e);
 }

}
}