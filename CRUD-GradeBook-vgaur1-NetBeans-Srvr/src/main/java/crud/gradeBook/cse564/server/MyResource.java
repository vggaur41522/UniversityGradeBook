package crud.gradeBook.cse564.server;

import crud.gradeBook.cse564.server.GradeBook.Student.GradeItem;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("GradeBook")
public class MyResource {
    
    private File file;
    private Response response;
    MyResource()
    {       
        
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("SampleInputGradeBook.xml").getFile());
    }
    
    public void LoadXml(GradeBook eGrdBook) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);   
        jaxbMarshaller.marshal(eGrdBook, System.out);
        jaxbMarshaller.marshal(eGrdBook, file);
    }
    
    @Context
    private UriInfo context;
    
    /**
     * GET request handling for Single Student !!!!
     *
     * @return String that will be returned as a application/xml response.
     */
    @GET 
    @Path("{stId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getStudentResponse(@PathParam("stId") int stId
                                ) throws JAXBException {

        try
        {
            GradeBook e = new GradeBook();
            GradeBook.Student s = new GradeBook.Student();
            GradeBook.Student.GradeItem grItem = new GradeBook.Student.GradeItem();
            ArrayList<GradeBook.Student.GradeItem> grItemList = new ArrayList<GradeBook.Student.GradeItem>();
            ArrayList<GradeBook.Student> stList = new ArrayList<GradeBook.Student>();
            ArrayList<GradeBook.Student.GradeItem> opGrList = new ArrayList<GradeBook.Student.GradeItem>();
            ArrayList<GradeBook.Student> opStList = new ArrayList<GradeBook.Student>();

            JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GradeBook eGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);
            stList = (ArrayList<GradeBook.Student>) eGrdBook.getStudent();
            char fndFlg = 'N';
            for (int i=0;i< stList.size() ;i++) {
                if(stList.get(i).getId() == stId)
                {
                    s.setId(stList.get(i).getId());
                    grItemList = (ArrayList<GradeItem>) stList.get(i).getGradeItem();
                    s.setGradeItem(grItemList);
                    opStList.add(s);
                    e.setStudent(opStList);
                    fndFlg = 'Y';
                }
            }
            if(fndFlg == 'N')
            {
                if(stList.size() > 0 )
                    response = Response.status(Response.Status.NOT_FOUND).entity("Matching GradeBook not found!!!").build();
                else
                    response = Response.status(Response.Status.GONE).entity("Matching GradeBook not found!!!").build();
           
                return response;
            }
            response = Response.status(Response.Status.OK).entity(e).build();
            return response;
            
        }catch(JAXBException e1)
        {
            response = Response.status(Response.Status.BAD_REQUEST).entity("Matching GradeBook not found!!!").build();
            return response;
        }catch(RuntimeException e1){
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Matching GradeBook not found!!!").build();
            return response;
        }    
    }

    
    /* GET request handling for Single Student and Single GradeItem !!! */
    @GET 
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllResponse() throws JAXBException {

        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GradeBook eGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);
            
            response = Response.status(Response.Status.OK).entity(eGrdBook).build();
            return response;
            
        }catch(JAXBException e1)
        {
            response = Response.status(Response.Status.BAD_REQUEST).entity("Matching GradeBook not found!!!").build();
            return response;
        }catch(RuntimeException e1){
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Matching GradeBook not found!!!").build();
            return response;
        }    
    }

    
    
    /**
     *  GET request handling for Single Student and Single GradeItem !!! 
     *  
     * @return String that will be returned as an application/xml response.
     */
    @GET 
    @Path("{stId}/{gradeItem}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponse(@PathParam("stId") int stId,
                                 @PathParam("gradeItem") String gradeItem) throws JAXBException {

        try
        {
            GradeBook e = new GradeBook();
            GradeBook.Student s = new GradeBook.Student();
            GradeBook.Student.GradeItem g = new GradeBook.Student.GradeItem();
            ArrayList<GradeBook.Student.GradeItem> grList = new ArrayList<GradeBook.Student.GradeItem>();
            ArrayList<GradeBook.Student> stList = new ArrayList<GradeBook.Student>();
            ArrayList<GradeBook.Student.GradeItem> opGrList = new ArrayList<GradeBook.Student.GradeItem>();
            ArrayList<GradeBook.Student> opStList = new ArrayList<GradeBook.Student>();

            JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GradeBook eGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);

            stList = (ArrayList<GradeBook.Student>) eGrdBook.getStudent();
            for (int i=0;i< stList.size() ;i++) {
                System.out.println(i);
                if(stList.get(i).getId() == stId)
                {
                    grList = (ArrayList<GradeItem>) stList.get(i).getGradeItem();
                    for(int j=0;j<grList.size();j++)
                    {
                        if(grList.get(j).getItemName().equalsIgnoreCase(gradeItem))
                        {
                            s.setId(stList.get(i).getId());
                            g.setFeedback(grList.get(j).getFeedback());
                            g.setappealText(grList.get(j).getappealText());
                            g.setItemName(grList.get(j).getItemName());
                            g.setValue(grList.get(j).getValue());
                            g.setWeight(grList.get(j).getWeight());
                            opGrList.add(g);
                            opStList.add(s);
                            s.setGradeItem(opGrList);
                            e.setStudent(opStList);
                            response = Response.status(Response.Status.OK).entity(e).build();
                            return response;
                        }
                    }
                }
            }
            //LoadXml(eGrdBook);   
            if(stList.size() > 0 )
                response = Response.status(Response.Status.NOT_FOUND).entity("Matching GradeBook not found!!!").build();
            else
                response = Response.status(Response.Status.GONE).entity("Matching GradeBook not found!!!").build();
            
            return response;
        }catch(JAXBException e1)
        {
            response = Response.status(Response.Status.BAD_REQUEST).entity("Matching GradeBook not found!!!").build();
            return response;
        }catch(RuntimeException e1){
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Matching GradeBook not found!!!").build();
            return response;
        }   
    }

    @DELETE 
    @Path("{stId}/{gradeItem}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response getDeleteReq(@PathParam("stId") int stId,
                                 @PathParam("gradeItem") String gradeItem) throws JAXBException {
        try
        {
            GradeBook e = new GradeBook();
            ArrayList<GradeBook.Student> stList = new ArrayList<GradeBook.Student>();
            ArrayList<GradeBook.Student> opStList = new ArrayList<GradeBook.Student>();
            JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GradeBook eGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);

            response = Response.status(Response.Status.NO_CONTENT).build();

            stList = (ArrayList<GradeBook.Student>) eGrdBook.getStudent();
            char fndFlg = 'N';
            for (int i=0;i< stList.size() ;i++) {
                GradeBook.Student s = new GradeBook.Student();
                ArrayList<GradeBook.Student.GradeItem> opGrList = new ArrayList<GradeBook.Student.GradeItem>();
                ArrayList<GradeBook.Student.GradeItem> grList = new ArrayList<GradeBook.Student.GradeItem>();
                s.setId(stList.get(i).getId());
                grList = (ArrayList<GradeItem>) stList.get(i).getGradeItem();
                for(int j=0;j<grList.size();j++)
                {
                    GradeBook.Student.GradeItem g = new GradeBook.Student.GradeItem();
                    if((grList.get(j).getItemName().equalsIgnoreCase(gradeItem)) && (stList.get(i).getId() == stId))
                    {
                       fndFlg = 'Y';
                       System.out.println("Skipping Record --"+j);
                       continue;
                    }

                    g.setFeedback(grList.get(j).getFeedback());
                    g.setappealText(grList.get(j).getappealText());
                    g.setItemName(grList.get(j).getItemName());
                    g.setValue(grList.get(j).getValue());
                    g.setWeight(grList.get(j).getWeight());
                    opGrList.add(g);              
                }

                s.setGradeItem(opGrList);
                opStList.add(s);
                e.setStudent(opStList); 
            }

            if(fndFlg == 'N')
            {
                if(stList.size() > 0 )
                    response = Response.status(Response.Status.NOT_FOUND).build();
                else
                    response = Response.status(Response.Status.GONE).build();
                return response;
            }
            LoadXml(e); 
            response = Response.status(Response.Status.NO_CONTENT).entity(e).build();
            return response;
        }catch(JAXBException e1)
        {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            return response;
        }catch(RuntimeException e1){
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return response;
        }
        
    }

    
    @POST 
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response getPostReq(GradeBook newGrdBook) throws JAXBException {
        Response response;
        if(newGrdBook.getStudent().get(0).getId() == 919191988)
        {
            response = getAllPostReq(newGrdBook);
            return response;
        }
        else
        {
            GradeBook e = new GradeBook();
            ArrayList<GradeBook.Student> stList = new ArrayList<GradeBook.Student>();
            ArrayList<GradeBook.Student> opStList = new ArrayList<GradeBook.Student>();

            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                GradeBook orgGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);
                ArrayList<GradeBook.Student> newStList = (ArrayList<GradeBook.Student>) newGrdBook.getStudent();

                URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(newGrdBook.getStudent().get(0).getId()) + "/" + newGrdBook.getStudent().get(0).getGradeItem().get(0).getItemName());          
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(newGrdBook).build();

                stList = (ArrayList<GradeBook.Student>) orgGrdBook.getStudent();
                char fndFlg = 'N';
                char fndAtlstOne = 'N';
                for (int i=0;i< stList.size() ;i++) {
                    System.out.println(i);

                    GradeBook.Student s = new GradeBook.Student();
                    ArrayList<GradeBook.Student.GradeItem> opGrList = new ArrayList<GradeBook.Student.GradeItem>();
                    ArrayList<GradeBook.Student.GradeItem> grList = new ArrayList<GradeBook.Student.GradeItem>();

                    s.setId(stList.get(i).getId());
                    grList = (ArrayList<GradeItem>) stList.get(i).getGradeItem();

                    fndFlg = 'N';
                    if((newStList.get(0).getId()) == (stList.get(i).getId()))
                    {
                        fndFlg = 'Y';
                        fndAtlstOne = 'Y';
                    }            
                    for(int j=0;j<grList.size();j++)
                    {
                        if((fndFlg == 'Y') && 
                                (grList.get(j).getItemName().equalsIgnoreCase(newStList.get(0).getGradeItem().get(0).getItemName())))
                        {
                            System.out.println("Grade Item already Exists for Student Id");
                            response = Response.status(Response.Status.CONFLICT).entity(newGrdBook).build();
                            return response;
                        }
                        GradeBook.Student.GradeItem g = new GradeBook.Student.GradeItem();            
                        g.setFeedback(grList.get(j).getFeedback());
                        g.setappealText(grList.get(j).getappealText());
                        g.setItemName(grList.get(j).getItemName());
                        g.setValue(grList.get(j).getValue());
                        g.setWeight(grList.get(j).getWeight());
                        opGrList.add(g);
                    }

                    if(fndFlg == 'Y')
                    {
                        opGrList.add(newStList.get(0).getGradeItem().get(0));
                    }

                    s.setGradeItem(opGrList);
                    opStList.add(s);
                    e.setStudent(opStList); 

                }
                if(fndAtlstOne == 'Y')
                {
                    LoadXml(e);    
                    return response; 
                }
                else
                {
                    GradeBook.Student tmpStudent = newGrdBook.getStudent().get(0);
                    orgGrdBook.student.add(tmpStudent);
                    LoadXml(orgGrdBook); 
                    return response; 
                }
            }catch(JAXBException e1)
            {
                response = Response.status(Response.Status.BAD_REQUEST).entity(newGrdBook).build();
                return response;
            }catch(RuntimeException e1){
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(newGrdBook).build();
                return response;
            }
        }
    }

    
    /* Regular Update */
    @PUT 
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response getUpdateReq(GradeBook newGrdBook) throws JAXBException {
        Response response;
        try {
            URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(newGrdBook.getStudent().get(0).getId()) + "/" + newGrdBook.getStudent().get(0).getGradeItem().get(0).getItemName());
            response = Response.status(Response.Status.OK).entity(newGrdBook).build();

            GradeBook e = new GradeBook();
            ArrayList<GradeBook.Student> stList = new ArrayList<GradeBook.Student>();
            ArrayList<GradeBook.Student> opStList = new ArrayList<GradeBook.Student>();

            JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GradeBook orgGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);
            ArrayList<GradeBook.Student> newStList = (ArrayList<GradeBook.Student>) newGrdBook.getStudent();
            stList = (ArrayList<GradeBook.Student>) orgGrdBook.getStudent();
            char fndFlg = 'N';
            char fndAtleastOne = 'N';
            for (int i=0;i< stList.size() ;i++) {
                System.out.println(i);

                GradeBook.Student s = new GradeBook.Student();
                ArrayList<GradeBook.Student.GradeItem> opGrList = new ArrayList<GradeBook.Student.GradeItem>();
                ArrayList<GradeBook.Student.GradeItem> grList = new ArrayList<GradeBook.Student.GradeItem>();

                s.setId(stList.get(i).getId());
                grList = (ArrayList<GradeItem>) stList.get(i).getGradeItem();

                fndFlg = 'N';
                if((newStList.get(0).getId()) == (stList.get(i).getId()))
                {
                    fndFlg = 'Y';
                }

                for(int j=0;j<grList.size();j++)
                {
                    if((fndFlg == 'Y') && 
                            (grList.get(j).getItemName().equalsIgnoreCase(newStList.get(0).getGradeItem().get(0).getItemName())))
                    {
                        if(newGrdBook.getStudent().get(0).getGradeItem().get(0).getWeight() == 0)
                                newGrdBook.getStudent().get(0).getGradeItem().get(0).setWeight(orgGrdBook.getStudent().get(i).getGradeItem().get(j).getWeight());
                        GradeBook.Student.GradeItem tmpGrdItem = newGrdBook.getStudent().get(0).getGradeItem().get(0);
                        opGrList.add(tmpGrdItem);
                        fndAtleastOne = 'Y';
                    }
                    else
                    {
                        GradeBook.Student.GradeItem g = new GradeBook.Student.GradeItem();            
                        g.setFeedback(grList.get(j).getFeedback());
                        g.setappealText(grList.get(j).getappealText());
                        g.setItemName(grList.get(j).getItemName());
                        g.setValue(grList.get(j).getValue());
                        g.setWeight(grList.get(j).getWeight());
                        opGrList.add(g);
                    }
                }
                s.setGradeItem(opGrList);
                opStList.add(s);
                e.setStudent(opStList); 
            }
            if(fndAtleastOne == 'Y')
            {
                LoadXml(e);
                return response;    
            }
            else
            {
                if(stList.size() > 0)
                    response = Response.status(Response.Status.NOT_FOUND).entity(newGrdBook).build();
                else
                    response = Response.status(Response.Status.CONFLICT).entity(newGrdBook).build();
                return response;  
            }
        } catch (JAXBException e1) {
            response = Response.status(Response.Status.BAD_REQUEST).entity(newGrdBook).build();
            return response;  
        } catch (RuntimeException e1) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(newGrdBook).build();
            return response;  
        }
    }
    
    /* Grade Appeal by student , Post checking marks !!!!
     * Appeal made from Student window !!
    */
    @PUT 
    @Path("appeal")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateAppealReq(GradeBook newGrdBook) throws JAXBException {
        Response response;
        try {
            URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(newGrdBook.getStudent().get(0).getId()) + "/" + newGrdBook.getStudent().get(0).getGradeItem().get(0).getItemName());
            response = Response.status(Response.Status.OK).entity(newGrdBook).build();

            GradeBook e = new GradeBook();
            ArrayList<GradeBook.Student> stList = new ArrayList<GradeBook.Student>();
            ArrayList<GradeBook.Student> opStList = new ArrayList<GradeBook.Student>();

            JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GradeBook orgGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);
            ArrayList<GradeBook.Student> newStList = (ArrayList<GradeBook.Student>) newGrdBook.getStudent();
            stList = (ArrayList<GradeBook.Student>) orgGrdBook.getStudent();
            char fndFlg = 'N';
            char fndAtleastOne = 'N';
            for (int i=0;i< stList.size() ;i++) {
                System.out.println(i);

                GradeBook.Student s = new GradeBook.Student();
                ArrayList<GradeBook.Student.GradeItem> opGrList = new ArrayList<GradeBook.Student.GradeItem>();
                ArrayList<GradeBook.Student.GradeItem> grList = new ArrayList<GradeBook.Student.GradeItem>();

                s.setId(stList.get(i).getId());
                grList = (ArrayList<GradeItem>) stList.get(i).getGradeItem();

                fndFlg = 'N';
                if((newStList.get(0).getId()) == (stList.get(i).getId()))
                {
                    fndFlg = 'Y';
                }

                for(int j=0;j<grList.size();j++)
                {
                    if((fndFlg == 'Y') && 
                            (grList.get(j).getItemName().equalsIgnoreCase(newStList.get(0).getGradeItem().get(0).getItemName())))
                    {
                        //if(newGrdBook.getStudent().get(0).getGradeItem().get(0).getWeight() == 0)
                        newGrdBook.getStudent().get(0).getGradeItem().get(0).setWeight(orgGrdBook.getStudent().get(i).getGradeItem().get(j).getWeight());
                        //if(newGrdBook.getStudent().get(0).getGradeItem().get(0).getFeedback() == "")
                        newGrdBook.getStudent().get(0).getGradeItem().get(0).setFeedback(orgGrdBook.getStudent().get(i).getGradeItem().get(j).getFeedback());
                        newGrdBook.getStudent().get(0).getGradeItem().get(0).setValue(orgGrdBook.getStudent().get(i).getGradeItem().get(j).getValue());
                        if((newGrdBook.getStudent().get(0).getGradeItem().get(0).getappealText().equalsIgnoreCase("")) || (newGrdBook.getStudent().get(0).getGradeItem().get(0).getappealText().equalsIgnoreCase("-")))
                                newGrdBook.getStudent().get(0).getGradeItem().get(0).setappealText(orgGrdBook.getStudent().get(i).getGradeItem().get(j).getappealText());
                        
                        GradeBook.Student.GradeItem tmpGrdItem = newGrdBook.getStudent().get(0).getGradeItem().get(0);
                        opGrList.add(tmpGrdItem);
                        fndAtleastOne = 'Y';
                    }
                    else
                    {
                        GradeBook.Student.GradeItem g = new GradeBook.Student.GradeItem();            
                        g.setFeedback(grList.get(j).getFeedback());
                        g.setappealText(grList.get(j).getappealText());
                        g.setItemName(grList.get(j).getItemName());
                        g.setValue(grList.get(j).getValue());
                        g.setWeight(grList.get(j).getWeight());
                        opGrList.add(g);
                    }
                }
                s.setGradeItem(opGrList);
                opStList.add(s);
                e.setStudent(opStList); 
            }
            if(fndAtleastOne == 'Y')
            {
                LoadXml(e);
                return response;    
            }
            else
            {
                if(stList.size() > 0)
                    response = Response.status(Response.Status.NOT_FOUND).entity(newGrdBook).build();
                else
                    response = Response.status(Response.Status.CONFLICT).entity(newGrdBook).build();
                return response;  
            }
        } catch (JAXBException e1) {
            response = Response.status(Response.Status.BAD_REQUEST).entity(newGrdBook).build();
            return response;  
        } catch (RuntimeException e1) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(newGrdBook).build();
            return response;  
        }
    }

    
    @DELETE 
    @Path("DelGradeItems/{gradeItem}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response getAllDeleteReq(@PathParam("gradeItem") String gradeItem) throws JAXBException {
        try
        {
            GradeBook e = new GradeBook();
            ArrayList<GradeBook.Student> stList = new ArrayList<GradeBook.Student>();
            ArrayList<GradeBook.Student> opStList = new ArrayList<GradeBook.Student>();
            JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GradeBook eGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);

            response = Response.status(Response.Status.NO_CONTENT).build();

            stList = (ArrayList<GradeBook.Student>) eGrdBook.getStudent();
            char fndFlg = 'N';
            for (int i=0;i< stList.size();i++) {
                GradeBook.Student s = new GradeBook.Student();
                ArrayList<GradeBook.Student.GradeItem> opGrList = new ArrayList<GradeBook.Student.GradeItem>();
                ArrayList<GradeBook.Student.GradeItem> grList = new ArrayList<GradeBook.Student.GradeItem>();
                s.setId(stList.get(i).getId());
                grList = (ArrayList<GradeItem>) stList.get(i).getGradeItem();
                for(int j=0;j<grList.size();j++)
                {
                    GradeBook.Student.GradeItem g = new GradeBook.Student.GradeItem();
                    if((grList.get(j).getItemName().equalsIgnoreCase(gradeItem)) )
                    {
                       fndFlg = 'Y';
                       System.out.println("Skipping Record --"+j);
                       continue;
                    }

                    g.setFeedback(grList.get(j).getFeedback());
                    g.setappealText(grList.get(j).getappealText());
                    g.setItemName(grList.get(j).getItemName());
                    g.setValue(grList.get(j).getValue());
                    g.setWeight(grList.get(j).getWeight());
                    opGrList.add(g);              
                }

                s.setGradeItem(opGrList);
                opStList.add(s);
                e.setStudent(opStList); 
            }

            if(fndFlg == 'N')
            {
                if(stList.size() > 0 )
                    response = Response.status(Response.Status.NOT_FOUND).build();
                else
                    response = Response.status(Response.Status.GONE).build();
                return response;
            }
            LoadXml(e); 
            response = Response.status(Response.Status.NO_CONTENT).entity(e).build();
            return response;
        }catch(JAXBException e1)
        {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            return response;
        }catch(RuntimeException e1){
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return response;
        }
        
    }

    public Response getAllPostReq(GradeBook newGrdBook) throws JAXBException {
        Response response;
        GradeBook e = new GradeBook();
        ArrayList<GradeBook.Student> orgStList = new ArrayList<GradeBook.Student>();
        ArrayList<GradeBook.Student> opStList = new ArrayList<GradeBook.Student>();
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GradeBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GradeBook orgGrdBook = (GradeBook) jaxbUnmarshaller.unmarshal(file);
            ArrayList<GradeBook.Student> newStList = (ArrayList<GradeBook.Student>) newGrdBook.getStudent();

            URI locationURI = URI.create( context.getAbsolutePath() + "");          
            response = Response.status(Response.Status.CREATED).location(locationURI).entity(newGrdBook).build();
            
            orgStList = (ArrayList<GradeBook.Student>) orgGrdBook.getStudent();
            char fndFlg = 'N';
            for (int i=0;i< orgStList.size() ;i++) {
                System.out.println(i);

                GradeBook.Student s = new GradeBook.Student();
                ArrayList<GradeBook.Student.GradeItem> opGrList = new ArrayList<GradeBook.Student.GradeItem>();
                ArrayList<GradeBook.Student.GradeItem> orgGrList = new ArrayList<GradeBook.Student.GradeItem>();
                
                s.setId(orgStList.get(i).getId());
                orgGrList = (ArrayList<GradeItem>) orgStList.get(i).getGradeItem();
                fndFlg = 'N';
                for (int j=0;j<orgGrList.size();j++)
                {
                    if(orgStList.get(i).getGradeItem().get(j).getItemName().equalsIgnoreCase(newStList.get(0).getGradeItem().get(0).getItemName() ))
                    {
                        fndFlg = 'Y';
                    }
                    opGrList.add(orgStList.get(i).getGradeItem().get(j));                    
                }
                if(fndFlg == 'N')
                   opGrList.add(newStList.get(0).getGradeItem().get(0));
                               
                s.setGradeItem(opGrList);
                opStList.add(s);
                e.setStudent(opStList); 
                if(opStList.size()>0)
                    response = Response.status(Response.Status.CREATED).location(locationURI).entity(e).build();
                else
                    response = Response.status(Response.Status.BAD_REQUEST).entity(newGrdBook).build();
            }           
        }catch(JAXBException e1)
        {
            response = Response.status(Response.Status.BAD_REQUEST).entity(newGrdBook).build();
            return response;
        }catch(RuntimeException e1){
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(newGrdBook).build();
            return response;
        }
        if(orgStList.size()>0)
        {
            LoadXml(e);      
        }
        else
        {
            response = Response.status(Response.Status.CONFLICT).entity(newGrdBook).build();
        }
        return response;
    }

}
