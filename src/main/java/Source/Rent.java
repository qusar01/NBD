package Source;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date start;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date end;

    @ManyToOne
    @JoinColumn(name = "babysitter_id")
    private Babysitter babysitter;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Version
    private int version;

    public Rent() {

    }
    public Rent(Date start, Date end, Babysitter babysitter, Child child, Parent parent) {
        if(babysitter.isHired()) {
            throw new IllegalArgumentException("Opiekunka jest już wynajęta.");
        } else {
            this.start = start;
            this.end = end;
            this.babysitter = babysitter;
            this.child = child;
            this.parent = parent;

            Date now = new Date();
            long time1 = end.getTime() - start.getTime();
            long time2 = end.getTime() - now.getTime();
        }
    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return start;
    }

    public Date getEndDate() {
        return end;
    }

    public Babysitter getCaregiver() {
        return babysitter;
    }

    public Child getChild() {
        return child;
    }

    public Parent getParent() {
        return parent;
    }

    public Babysitter getBabysitter() { return babysitter; }

    public double getRentalCost() {
        long time = end.getTime() - start.getTime();
        double days = time / (24 * 60 * 60 * 1000);
        double hours = (time / (60 * 60 * 1000)) % 24;
        int workingDays = (int) days;
        double extraHours = hours + (days - workingDays) * 24;
        double cost = 50 * workingDays + extraHours;
        return cost + Babysitter.getBasePrice();
    }

    public String getRentInfo() {
        long time1 = end.getTime() - start.getTime();
        if (time1 < 0) {
            System.out.println("Can't create a rent in the past");
            return "";
        }

        StringBuilder info = new StringBuilder();
        info.append("Rent: ").append(id).append(" start date: ").append(start).append(" end date: ").append(end)
                .append(" babysitter cost: ").append(getRentalCost()).append(" zł").append("\n");
        return info.toString();
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setBabysitter(Babysitter babysitter) {
        this.babysitter = babysitter;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}




