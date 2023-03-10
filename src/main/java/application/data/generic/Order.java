package application.data.generic;

import org.hibernate.Criteria;

import java.util.ArrayList;

public class Order {
    private ArrayList<String> column=new ArrayList<>();
    private ArrayList<Integer> order=new ArrayList<>();

    public ArrayList<String> getColumn() {
        return column;
    }

    public ArrayList<Integer> getOrder() {
        return order;
    }

    public void addOrder(String column,int order){
        this.column.add(column);
        this.order.add(order);
    }

    public Order(String column,int order){
        addOrder(column, order);
    }

    public void setOrder(Criteria criteria,int index)throws Exception{
        if (index<0&&index>=order.size()) throw new IndexOutOfBoundsException("Index out of bounds");
        if (order.get(index)>0){
            criteria.addOrder(org.hibernate.criterion.Order.asc(column.get(index)));
        } else if (order.get(index)<0) {
            criteria.addOrder(org.hibernate.criterion.Order.desc(column.get(index)));
        }
    }

    public void setOrder(Criteria criteria)throws Exception{
        for (int i = 0; i < column.size(); i++) {
            this.setOrder(criteria,i);
        }
    }
}
