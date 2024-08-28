package lk.ijse.PosBackend.dao;

import lk.ijse.PosBackend.dao.custom.impl.CustomerDaoImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        CUSTOMER,ITEM,ORDER,USER;
    }

    public SuperDAO getDao(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDaoImpl();
           /* case ITEM:
                return new ItemDataProcess();
            case ORDER:
                return new OrderDataProcess();
            case USER:
                return new UserDataProcess();*/
            default:
                return null;
        }
    }
}
