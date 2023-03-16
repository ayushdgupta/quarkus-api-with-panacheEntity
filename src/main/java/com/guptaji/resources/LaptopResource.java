package com.guptaji.resources;

import com.guptaji.entity.Laptop;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/laptopInfo")
public class LaptopResource {

    // IMP link for DB Connectivity https://quarkus.io/guides/datasource, https://quarkus.io/guides/hibernate-orm


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLaptopInfo(){
        List<Laptop> laptopDetails = Laptop.listAll();
        return Response.ok(laptopDetails).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewLaptopInfo(Laptop laptop){
        Laptop.persist(laptop);
        if (laptop.isPersistent()){
            return Response.created(URI.create(("/laptopInfo/"+laptop.id))).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getLaptopInfo(@PathParam("id") long id){
        Laptop laptop = Laptop.findById(id);
        if (laptop == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(laptop).build();
        }
    }

    @PUT
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateLaptopInfo(@PathParam("id") long id, Laptop laptop){
        Optional<Laptop> laptopOptional = Laptop.findByIdOptional(id);
        if (laptopOptional.isPresent()){
            Laptop currentLaptopData = laptopOptional.get();
            if (Objects.nonNull(laptop.getName())){
                currentLaptopData.setName(laptop.getName());
            }

            Laptop.persist(currentLaptopData);
            if (currentLaptopData.isPersistent()){
                return Response.created(URI.create("/laptopInfo/"+id)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response deleteLaptopInfo(@PathParam("id") long id){
        Boolean isDeleted = Laptop.deleteById(id);
        if (isDeleted){
            return Response.ok("Done dana dan done").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
