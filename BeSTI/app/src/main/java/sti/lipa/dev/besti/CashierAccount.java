package sti.lipa.dev.besti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CashierAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_account);
    }
    public void cshrsched(View view){
        Intent elyen= new Intent (CashierAccount.this, CashierActivity.class);
        startActivity(elyen);
    }
    public void cshrpword(View view){
        Intent elyen= new Intent (CashierAccount.this, UpdatePasswordCashier.class);
        startActivity(elyen);
    }
    public void cshreserve(View view){
        Intent elyen= new Intent (CashierAccount.this, ListViewCashier.class);
        startActivity(elyen);
    }
}
