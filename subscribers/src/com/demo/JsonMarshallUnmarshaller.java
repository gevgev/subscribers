package com.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.type.JavaType;

import common.models.Subscriber;

public class JsonMarshallUnmarshaller {

	 Subscriber targetClass;

	 public ArrayList<Subscriber> unmarshal(String jsonString)
	 {
	    ObjectMapper mapper = new ObjectMapper();

	    AnnotationIntrospector introspector = new JacksonAnnotationIntrospector();

	    mapper.getDeserializationConfig().withAnnotationIntrospector(introspector);

	    mapper.getSerializationConfig().withAnnotationIntrospector(introspector);
	    JavaType type = mapper.getTypeFactory().
	                constructCollectionType(ArrayList.class, Subscriber.class) ;
	    try
	    {
	    	
//	    Class c1 = this.targetClass.getClass() ;
//	    Class c2 = this.targetClass.getClass() ;
	    	
	        @SuppressWarnings("unchecked")
			ArrayList<Subscriber> temp = (ArrayList<Subscriber>) mapper.readValue(jsonString,  type);
	    return temp ;
	    }
	   catch (JsonParseException e)
	   {
	    e.printStackTrace();
	   }
	   catch (JsonMappingException e) {
	       e.printStackTrace();
	   } catch (IOException e) {
	      e.printStackTrace();
	   }

	    return null ;
	 }  
}
	 

