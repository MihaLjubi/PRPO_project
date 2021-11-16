package si.fri.prpo.polnilnice.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("v1")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class PolnilnePostajeApplication extends Application {

}
