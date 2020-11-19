package com.example.myapplication.Database;

import android.provider.BaseColumns;

public class UsersMaster {
    private UsersMaster() {
    }

    public static class Users implements BaseColumns{
        public static final String Table_Name = "users";
        public static final String Column_Name_Username = "username";
        public static final String Column_Name_Password = "password";
    }
}
