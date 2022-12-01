package at.htl.leotour_backend.control;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class InitBean {

    public void onInit(@Observes StartupEvent event){

    }
}
