package nl.jads.tosca.restapi;

import nl.jads.tosca.DefectPredictorKBApi;
import nl.jads.tosca.dto.BugReport;
import nl.jads.tosca.dto.FindBugInput;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/bugs")
//@Api()
public class BugPredictorService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(
//            value = "Returns the bugs in the IaC codes",
////			response = String.class,
//            responseContainer = "List")
    @Path("/tosca/json")
    public Response findBugs(FindBugInput findBugInput) throws IOException {
        DefectPredictorKBApi kbApi = new DefectPredictorKBApi();
        BugReport bugReport = kbApi.findBugs(findBugInput);
        kbApi.shutDown();
        return Response.ok(bugReport).build();
    }

    @POST
    @Path("/tosca/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        String fileLocation = "e://" + fileDetail.getFileName();
        System.out.println("Received a File :" + fileLocation);
        //saving file
//        try {
//            FileOutputStream out = new FileOutputStream(new File(fileLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//            out = new FileOutputStream(new File(fileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {e.printStackTrace();}
        FindBugInput findBugInput = new FindBugInput();
        findBugInput.setActionId(fileDetail.getFileName());
        findBugInput.setDeploymentId(fileDetail.getFileName());
        DefectPredictorKBApi kbApi = new DefectPredictorKBApi();
        BugReport bugReport = kbApi.findBugs(findBugInput);
        kbApi.shutDown();
        return Response.ok(bugReport).build();
    }
}
