package org.ziko.healthy.products;


import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.ziko.common.Product;

@ApplicationScoped
public class ProductConsumerResource {


    @Incoming("healthy-products")
    @Startup
    public void sink(Product product) {
        System.out.println(">> " + product.toString());
    }
}
