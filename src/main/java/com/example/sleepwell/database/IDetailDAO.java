package com.example.sleepwell.database;
import java.util.List;

public interface IDetailDAO {
    public void addDetail(Detail detail);

    public void updateDetail(Detail detail);

    public void deleteDetail(Detail detail);

    public Detail getDetail(int detailid);

    public List<Detail> getAllDetail();
}
