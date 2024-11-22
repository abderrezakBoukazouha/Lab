package org.ziko.producer;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.ziko.common.Product;


import java.util.List;

@Path("/product")
public class ProductProducerResource {

    @Inject
    @Channel("products")
    Emitter<Product> emitter;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveProduct(List<Product> productList) {
        productList.forEach(product ->
        {
            emitter.send(product);
            System.out.println("Product" + product.toString() + " successfully sent to processor");
        });

        return Response.accepted().build();
    }
}
