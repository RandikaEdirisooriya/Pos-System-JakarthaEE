package lk.ijse.PosBackend.bo;

import lk.ijse.PosBackend.bo.custom.impl.CustomerBOImpl;
import lk.ijse.PosBackend.bo.custom.impl.ItemBoImpl;
import lk.ijse.PosBackend.bo.custom.impl.OrderBoImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory=new BOFactory() : boFactory ;
    }

    public enum BOType {
        CUSTOMER,ITEM,ORDER,USER;
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();
           case ITEM:
                return new ItemBoImpl();
            case ORDER:
                return new OrderBoImpl();

            default:
                return null;
        }
    }
}
