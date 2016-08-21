/*==================================================================
*  IMPORTANT        : File for academic project for course CSE564
*  Category         : Academic project
*  Type             : Java Program
*  Description      : Code for project 2- CSE564
*  Author           : Varun Gaur
*  Modification Log :
*  Author           Date		Reason
*  Varun Gaur       2-Apr-2016		Original
* =================================================================*/
package gradeBookClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;


public class GradeBookClient {
    
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-vgaur1-NetBeans-Srvr/BlackBoard/GradeBook";

    public GradeBookClient() {        
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI);
    }

    public ClientResponse createGradeBookCl(Object requestEntity) throws UniformInterfaceException {
        return webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);
    }

    public ClientResponse createAllGradeBookCl(Object requestEntity) throws UniformInterfaceException {
        return webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);
    }
    
    public ClientResponse deleteAllGradeBookCl(String grItemName) throws UniformInterfaceException {
        return webResource.path("DelGradeItems").path(grItemName).accept(MediaType.APPLICATION_XML).delete(ClientResponse.class);
    }
    
    public ClientResponse deleteGradeBookCl(String id,String grItemName) throws UniformInterfaceException {
        return webResource.path(id).path(grItemName).accept(MediaType.APPLICATION_XML).delete(ClientResponse.class);
    }

    public ClientResponse updateGradeBookCl(Object requestEntity) throws UniformInterfaceException {
        return webResource.type(MediaType.APPLICATION_XML).put(ClientResponse.class, requestEntity);
    }
    
    public ClientResponse updateAppealGradeBookCl(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("appeal").type(MediaType.APPLICATION_XML).put(ClientResponse.class, requestEntity);
    }    

    public <T> T retrieveGradeBookCl(Class<T> responseType, String id, String grItemName) throws UniformInterfaceException {
        return webResource.path(id).path(grItemName).accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.destroy();
    }
    
}
