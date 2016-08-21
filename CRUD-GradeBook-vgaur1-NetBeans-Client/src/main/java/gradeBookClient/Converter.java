/*==================================================================
*  IMPORTANT        : File for academic project for course CSE564
*  Category         : Academic project
*  Source Name      : Converter.java
*  Type             : Java Program
*  Description      : Code for project 2- CSE564
*  Author           : Varun Gaur
*  Modification Log :
*  Author           Date		Reason
*  Varun Gaur       2-Apr-2016		Original
* =================================================================*/
package gradeBookClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;
import java.io.StringReader;


public class Converter {
    
    
    public static Object convertFromXmlToObject(Object xmlString, Class... type) throws JAXBException{
        
        Object result;

        StringReader sr = null;
        
        if (xmlString instanceof String){
            sr = new StringReader((String)xmlString);
        }

        JAXBContext context         = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller   = context.createUnmarshaller();
        result = unmarshaller.unmarshal(sr);
        
        return result;
    }
    
    public static String convertFromObjectToXml(Object source, Class... type) {
        
        String result;
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context     = JAXBContext.newInstance(type);
            Marshaller  marshaller  = context.createMarshaller();
            marshaller.marshal(source, sw);
            result = sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

