package com.example.tourist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.*;


public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_map:
                Intent intent_cart = new Intent(getApplicationContext(), Map.class);
                startActivity(intent_cart);
                break;
            case R.id.navigation_home:
                break;
            case R.id.navigation_profile:
                Intent intent_con = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent_con);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.openMenu));
    }
    public void addToFavorite(View view) {
        String idString = view.getResources().getResourceEntryName(view.getId());
        String nameId = "title" + idString.charAt(idString.length()-1);
        TextView name = findViewById(
                getResources().getIdentifier(nameId, "id", getPackageName()));
        String strName = name.getText().toString();
        Profile.addToFavorite(strName);
    }
}
