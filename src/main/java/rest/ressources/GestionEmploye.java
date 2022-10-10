package rest.ressources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rest.entities.Employe;

@Path("employes")
@Api
//permet d�active Swagger pour cette classe.
public class GestionEmploye {
	
	public static  List<Employe> employes=new ArrayList<Employe>();
	
	

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String ajouterEmploye(Employe employe) {
		 if(employes.add(employe))
	 return "Add Successful";
		return "Echec";	
	}
	
	
	
	 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "GET all Employes")
	  @ApiResponses({
		  @ApiResponse(code=200 , message="Success")
	  })
	public  Response  displayEmployeesList() {
		
		if(employes.size()!=0)
			return Response.status(Status.FOUND).entity(employes).build();
		else
			return Response.status(Status.NOT_FOUND).build();
					
	}
	
	@GET
	@Path("{cin}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmploye(@PathParam("cin")int cin) {
		for (Employe info:employes) {
	       if(info.getCin()==cin) {
	    	   return  Response.status(Status.FOUND)
						.entity(info)
						.build(); 
	    	
	       }
		}
	       		
			return  Response.status(Status.NOT_FOUND).build();	
	}
	
		
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmploye(Employe e) {
		int index= this.getIndexByCin(e.getCin());
		if (index!=-1) {
			employes.set(index, e);
			return Response.status(Status.OK).entity("update successful").build();
			
		}
		return Response.status(Status.NOT_FOUND).entity("update unsuccessful").build();
	
	}
	

	@DELETE
	@Path("{cin}")
	public boolean deleteEmpl(@PathParam("cin")int cin){
		int index= getIndexByCin(cin);
		
		if (index!=-1) {
			employes.remove(index);
			return true;
		}else 
			return false;
			
    }
	
	@GET
	@Path("{cin}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getIndexByCin(int cin) {
		for(Employe emp: employes) {
			if (emp.getCin()==cin)
				return employes.indexOf(emp);
		}
		return -1;
	}
	
		
}
