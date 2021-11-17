package si.fri.prpo.polnilnice.interceptor;

import si.fri.prpo.polnilnice.zrna.BelezenjeKlicevZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {

    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;

    @AroundInvoke
    public Object BelezenjeKlicev(InvocationContext context) throws Exception {
        belezenjeKlicevZrno.inc();
        return context.proceed();
    }

}
