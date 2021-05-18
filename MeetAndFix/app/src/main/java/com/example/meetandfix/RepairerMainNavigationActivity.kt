package com.example.meetandfix
import androidx.fragment.app.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meetandfix.fragments.*
import kotlinx.android.synthetic.main.navigation_repairer_layout.*

class RepairerMainNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_repairer_layout)

        //Instanciamos los fragmentos
        val repairerNotificationsFragment = RepairerNotificationsFragment()
        val repairerCalendarFragment = RepairerCalendarFragment()
        val repairerStoreFragment = RepairerStoreFragment()
        val repairerProfileFragment = RepairerProfileFragment()

        //Hacemos de las notificaciones el fragmento actual al inciar
        makeCurrentFragment(repairerNotificationsFragment)


        //Cambiamos de fragmento dependiendo del item que este seleccionado
        bottom_navigation_menu_reparador.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_reparador_notificaciones -> makeCurrentFragment(repairerNotificationsFragment)
                R.id.menu_reparador_calendario -> makeCurrentFragment(repairerCalendarFragment)
                R.id.menu_reparador_negocio -> makeCurrentFragment(repairerStoreFragment)
                R.id.menu_reparador_perfil -> makeCurrentFragment(repairerProfileFragment)
            }
            true

        }



    }

    //Función para cambiar el fragmeto actual
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper_repairer, fragment)
            commit()
        }


}