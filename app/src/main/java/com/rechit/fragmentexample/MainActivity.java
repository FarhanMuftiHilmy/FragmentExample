package com.rechit.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener {
    private Button mButton;
    private boolean isFragmentDisplayed=false;
    private int mRadioButtonChoice=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.open_button);
    }


    public void openClick(View view) {
        if(!isFragmentDisplayed){
            displayFragment();
        } else{
            closeFragment();
        }
    }

    public void displayFragment(){
        //menambahkan data ke activity
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();
        isFragmentDisplayed=true;
        mButton.setText("Close");

    }

    public void closeFragment(){
        //menghapus data dari activity
        FragmentManager fragmentManager=getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if(simpleFragment!=null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        isFragmentDisplayed=false;
        mButton.setText("Open");

    }


    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice=choice;
        //cek jika  data berhasil terkirim ke activity
        Toast.makeText(this, "CHOICE"+choice, Toast.LENGTH_LONG).show();
    }
}