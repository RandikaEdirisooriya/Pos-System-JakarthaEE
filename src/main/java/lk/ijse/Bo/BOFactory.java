package lk.ijse.Bo;

import com.example.bootstrapposbackend.bo.custom.impl.CustomerBOImpl;
import com.example.bootstrapposbackend.bo.custom.impl.ItemBOImpl;
import com.example.bootstrapposbackend.bo.custom.impl.OrderBOImpl;
import com.example.bootstrapposbackend.bo.custom.impl.UserBOImpl;

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
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
