package edu.uw.tcss450.team3.tiktalk;
//comment
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import edu.uw.tcss450.team3.tiktalk.model.UserInfoViewModel;
import edu.uw.tcss450.team3.tiktalk.ui.chat.ChatFragment;
import edu.uw.tcss450.team3.tiktalk.ui.chat.ChatListFragment;
import edu.uw.tcss450.team3.tiktalk.ui.connection.ConnectionFragment;
import edu.uw.tcss450.team3.tiktalk.ui.connection.ContactFragment;
import edu.uw.tcss450.team3.tiktalk.ui.home.HomeFragment;
import edu.uw.tcss450.team3.tiktalk.ui.weather.WeatherFirstFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ConnectionFragment connectionFragment = new ConnectionFragment();
    ContactFragment contactFragment = new ContactFragment();
    ChatListFragment chatListFragment = new ChatListFragment();
    WeatherFirstFragment weatherFirstFragment = new WeatherFirstFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityArgs args = MainActivityArgs.fromBundle(getIntent().getExtras());
        new ViewModelProvider(this,
                new UserInfoViewModel.UserInfoViewModelFactory(args.getEmail(), args.getJwt())
        ).get(UserInfoViewModel.class);

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeIcon:
                            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, homeFragment).commit();
                            return true;
                        case R.id.connectionIcon:
                            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, connectionFragment).commit();
                            return true;
                        case R.id.chatIcon:
                            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, chatListFragment).commit();
                            return true;
                        case R.id.weatherIcon:
                            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, weatherFirstFragment).commit();
                            return true;
                        default:
                            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, homeFragment).commit();
                            return true;
                }
            }
        });
    }
}