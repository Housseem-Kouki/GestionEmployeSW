package rest.utilities;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
import rest.ressources.GestionEmploye;

@ApplicationPath("rest")
public class RestActivator extends Application {
	
	public RestActivator() {
		super();
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setSchemes(new String[]{"http"});
		beanConfig.setHost("localhost:8086");
		beanConfig.setBasePath("/GestionEmploye/rest");
		beanConfig.setResourcePackage("io.swagger.resources");
		beanConfig.setResourcePackage("rest");
		beanConfig.setScan(true);
		}
	
	@Override
	public Set<Class<?>> getClasses() {
	Set<Class<?>> resources = new HashSet();
	resources.add(GestionEmploye.class);
	//resources.add(SecondResource.class);

	resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
	resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	return resources;
	}
}
