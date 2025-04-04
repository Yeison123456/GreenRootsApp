package com.example.greenroots.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.greenroots.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Configuracion de la barra de herramientas */
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Navegacion de los fragments*/
        var navHostFragment= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert  navHostFragment != null;

        navController = navHostFragment.getNavController();
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view) ;

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_open,
                R.string.navigation_close
        );

        // Añadir el DrawableToggle al DrawableLayout
        drawerLayout.addDrawerListener(drawerToggle);

        // Sincronizar el estado del DrawableToggle
        drawerToggle.syncState();

         appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.inicioFragment,
                R.id.PerfilFragment,
                R.id.ProductosFragment,
                R.id.CarritoFragment,
                R.id.CategoriasFragment
        ).setDrawerLayout(drawerLayout).build();



        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}