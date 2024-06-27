package com.example.assignment_5;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Account> accounts = generateDummyChats();
    private static ArrayList<Account> generateDummyChats() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Adnan", "adnandi252", "kata-kata ini....sasdad", R.drawable.satu, R.drawable.satu));
        accounts.add(new Account("Yusuf Fikry", "fikhi_12", "kata-kata ini 2...sdfrf", R.drawable.dua, R.drawable.dua));
        accounts.add(new Account("Mayko Raditya", "maykoooradit", "kata-kata ini 3.....ddedwehu", R.drawable.tiga, R.drawable.tiga));
        accounts.add(new Account("Zulfikri Sadrah", "zulsad", "kata-kata ini 4...43ewdefrt", R.drawable.empat, R.drawable.empat));
        accounts.add(new Account("Joy Abrian", "joyyrian", "kata-kata ini 5...dadedniq", R.drawable.lima, R.drawable.lima));
        return accounts;
    }
}
