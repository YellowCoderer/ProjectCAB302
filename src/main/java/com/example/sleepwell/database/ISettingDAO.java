package com.example.sleepwell.database;

import java.util.List;

public interface ISettingDAO {
    public void addSetting(Setting setting);

    public void updateSetting(Setting setting);

    public void deleteSetting(Setting setting);

    public Detail getSetting(int settingid);

    public List<Setting> getAllSetting();
}
