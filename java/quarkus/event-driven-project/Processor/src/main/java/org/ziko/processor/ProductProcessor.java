package org.ziko.processor;


import io.quarkus.runtime.Startup;
import io.smallrye.reactive.messaging.Targeted;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.*;
import org.ziko.common.Product;

@ApplicationScoped
public class ProductProcessor {


    @Incoming("products")
    @Outgoing("healthy-products")
    @Outgoing("faulty-products")
    @Startup
    public Targeted filterByProductPrices(Message<Product> message) {

        if (message.getPayload().price() > 50) {
            System.out.println("Product successfully processed for" + message.getPayload().toString() + "and sent to healthy-products topic");
            message.ack();
            return Targeted.of("healthy-products", message.getPayload());
        } else {
            message.ack();
            System.out.println("Product successfully processed for" + message.getPayload().toString() + "and sent to faulty-products topic");
            return Targeted.of("faulty-products", message.getPayload());
        }
    }
}
