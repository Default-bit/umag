import com.sun.source.doctree.SeeTree;

import java.util.*;

enum Type {
    TYPE_SUPPLY,
    TYPE_SALE,
}

class Event {
    private String id;
    private int time;
    private String barcode;
    private int price, quantity;
    private Type type;

    public Event(String id, int time, String barcode, int price, int quantity, Type type) {
        this.id = id;
        this.time = time;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Type getType() {
        return type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        if (o1.getTime() == o2.getTime()) {
            if (o1.getType() == o2.getType()) {
                return o1.getId().compareTo(o2.getId());
            }
            return (o1.getType() == Type.TYPE_SUPPLY ? -1 : 1);
        }
        return o1.getTime() - o2.getTime();
    }
}

class Database {
    private Map<String, Event> db;
    private Map<String, Set<Event>> idb;

    public Database() {
        db = new HashMap<>();
        idb = new HashMap<>();
    }

    public void addEvent(Event event) {
        db.put(event.getId(), event);
        if (!idb.containsKey(event.getBarcode()))
            idb.put(event.getBarcode(), new TreeSet<Event>(new EventComparator()));
        idb.get(event.getBarcode()).add(event);
    }

    public void delEvent(Event event) {
        db.remove(event.getId());
        idb.get(event.getBarcode()).remove(event);
        if (idb.get(event.getBarcode()).isEmpty())
            idb.remove(event.getBarcode());
    }

    public void updEvent(String id, Event event) {
        idb.get(db.get(id).getBarcode()).remove(db.get(id));
        db.put(id, event);
        idb.get(db.get(id).getBarcode()).add(event);
    }

    public long calculate(String barcode, int fromTime, int toTime) {
        Queue<Event> q = new ArrayDeque<>();
        long netProfit = 0;
        for (Event event : idb.get(barcode)) {
            if (event.getType() == Type.TYPE_SUPPLY) {
                q.add(event);
            } else {
                int price = event.getPrice();
                int quantity = event.getQuantity();
                while (!q.isEmpty() && quantity > 0) {
                    if (quantity >= q.peek().getQuantity()) {
                        quantity -= q.peek().getQuantity();
                        if (event.getTime() >= fromTime && event.getTime() <= toTime) {
                            netProfit += (long) (price - q.peek().getPrice()) * q.peek().getQuantity();
                        }
                        q.poll();
                    } else {
                        if (event.getTime() >= fromTime && event.getTime() <= toTime) {
                            netProfit += (long) (price - q.peek().getPrice()) * quantity;
                        }
                        q.peek().setQuantity(q.peek().getQuantity() - quantity);
                        quantity = 0;
                    }
                }
                if (quantity > 0 && event.getTime() >= fromTime && event.getTime() <= toTime) {
                    netProfit += (long) quantity * price;
                }
            }
        }
        return netProfit;
    }

}

class Algo {

    public static void main(String[] args) {
        Database db = new Database();

        db.addEvent(new Event("1", 1, "1", 10, 15, Type.TYPE_SALE));
        db.addEvent(new Event("2", 2, "1", 15, 4, Type.TYPE_SUPPLY));
        db.addEvent(new Event("3", 4, "1", 21, 20, Type.TYPE_SALE));

        System.out.println(db.calculate("1", 1, 2));
        System.out.println(db.calculate("1", 3, 3));
    }
}