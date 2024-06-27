package com.example.assignment_3;

import com.example.assignment_3.Model.AccountModel;

import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;

public class DataSource {
    public static ArrayList<AccountModel> account = generate();
    private static ArrayList<AccountModel> generate() {
        ArrayList<AccountModel> account = new ArrayList<>();
        account.add(new AccountModel(R.drawable.satu, "adnandi252", "Pokemon satu ini", R.drawable.back_satu, "200", "150"));
        account.add(new AccountModel(R.drawable.dua, "", "Pokemon dua ini", R.drawable.back_dua, "340", "100"));
        account.add(new AccountModel(R.drawable.tiga, "fikhi_12", "pokemon tiga ini", R.drawable.back_tiga, "2.4Jt", "1"));
        account.add(new AccountModel(R.drawable.empat, "zulfikrisadrah", "Pokemon empat ini", R.drawable.back_empat, "2301", "232"));
        account.add(new AccountModel(R.drawable.lima, "maykoditya", "Pokemon lima ini", R.drawable.back_lima, "1022", "100"));
        account.add(new AccountModel(R.drawable.enam, "erullzzz", "Pokemon enam ini", R.drawable.back_enam, "5404", "3420"));
        account.add(new AccountModel(R.drawable.tujuh, "fhri33323", "Pokemon tujuh ini", R.drawable.back_tujuh, "59", "21"));
        account.add(new AccountModel(R.drawable.delapan, "muh_haikal", "Pokemon delapan ini", R.drawable.back_delapan, "21", "71"));
        account.add(new AccountModel(R.drawable.sembilan, "iqbal9494", "Pokemon sembilan ini", R.drawable.back_sembilan, "212", "120"));
        account.add(new AccountModel(R.drawable.sepuluh, "muh_aditya", "Pokemon sepuluh ini", R.drawable.back_sepuluh, "63", "19"));
        return account;
    }
}
