package com.cmy.databasePool;

public interface IMyPool {

    public void createConnection(int count);

    public PooledConnection getConnection();

}
