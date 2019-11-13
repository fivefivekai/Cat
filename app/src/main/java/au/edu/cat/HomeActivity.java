package au.edu.cat;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import au.edu.cat.ui.HomeFragment;
import au.edu.cat.ui.FavouritesFragment;

public class HomeActivity extends AppCompatActivity {
    private Fragment homeFragment;
    private Fragment starFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                hideAllFragment(transaction);
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        if(homeFragment==null){
                            homeFragment = new HomeFragment();
                            transaction.add(R.id.fl,homeFragment);
                        }else{
                            transaction.show(homeFragment);
                        }
                        break;
                    case R.id.navigation_favourites:
                        if(starFragment==null){
                            starFragment =new FavouritesFragment();
                            transaction.add(R.id.fl,starFragment);
                        }else{
                            transaction.show(starFragment);
                        }
                        break;

                }
                transaction.commit();

                return false;
            }
        });
        navView.setSelectedItemId(R.id.navigation_home);

    }

    public void hideAllFragment(FragmentTransaction transaction){
        if(homeFragment!=null){
            transaction.hide(homeFragment);
        }

        if(starFragment!=null){
            transaction.hide(starFragment);
        }

    }

}
