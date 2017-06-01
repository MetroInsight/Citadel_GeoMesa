package com.example.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.simple.JSONObject;

public class kafkaProducer {

	 public void SendData()
	 {

      // create instance for properties to access producer configs
      Properties props = new Properties();

      //Assign localhost id
      props.put("bootstrap.servers", "localhost:9092");


      props.put("key.serializer",
              "org.apache.kafka.common.serialization.StringSerializer");

      props.put("value.serializer",
              "org.apache.kafka.common.serialization.StringSerializer");

      Producer<String, String> producer = new KafkaProducer<String, String>(props);

      long startTime = System.currentTimeMillis();
      
    //Assign topicName to string variable
      String topicName = "test";//args[0].toString();
      
      for(int i = 0; i < 10; i++)
      {
    	  //creating value in json
    	  JSONObject obj = new JSONObject();
    	  
    	  obj.put("RecordNum", new Integer(i));
    	  obj.put("DataType","BP");
    	  obj.put("DataValueInt", new Integer(100));
    	  obj.put("DataValueDouble", new Double(1000.21));
    	  obj.put("is_verified", new Boolean(true));
    	  
    	  String value=obj.toJSONString();
    	  
    	  //end creating value in json
    	  
    	  ProducerRecord<String,String> record= new ProducerRecord<String, String>(topicName,value);
    	 
    	  
    	  producer.send(record);
    	  try {
			Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  
      }//end for
      
      producer.close();
  }//end send Data	
}//end class
