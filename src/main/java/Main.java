import com.datastax.oss.driver.api.core.CqlSession;
import model.BabysitterDDL;


public class Main {
    public static void main(String[] args) {
        CqlSession session = BabysitterDDL.initSession();

//        BabysitterRepository babysitterRepository = new BabysitterRepository(session);
//
//        Babysitter b = new Babysitter(new UUID(2,4),"asd", 33, true, "ChildBabysitter");
//        babysitterRepository.add(b);
//
//        ParentRepository parentRepository = new ParentRepository(session);
//        Parent p = new Parent(new UUID(2,4),"Marcel", "Kowal");
//        parentRepository.add(p);
//        UUID id = new UUID(2,4);
//        LocalDate today = LocalDate.now();
//        LocalDate tomorrow = today.plusDays(1);
//        RentRepository rentRepository = new RentRepository(session);
//        Rent r = new Rent(id,today,tomorrow,id,id,id);
//        rentRepository.add(r);

        session.close();
    }
}