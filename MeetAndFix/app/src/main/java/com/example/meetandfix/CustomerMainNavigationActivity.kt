package com.example.meetandfix
import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.meetandfix.fragments.CustomerCalendarFragment



import com.example.meetandfix.fragments.CustomerNotificationsFragment
import com.example.meetandfix.fragments.CustomerProfileFragment
import com.example.meetandfix.fragments.CustomerSearchFragment
import kotlinx.android.synthetic.main.fragment_customer_search.*
import kotlinx.android.synthetic.main.navigation_layout.*

class CustomerMainNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_layout)

        //Instanciamos los fragmentos
        val customerNotificationsFragment = CustomerNotificationsFragment()
        val customerCalendarFragment = CustomerCalendarFragment()
        val customerSearchFragment = CustomerSearchFragment()
        val customerProfileFragment = CustomerProfileFragment()

        //Hacemos de las notificaciones el fragmento actual al inciar
        makeCurrentFragment(customerNotificationsFragment)

        //Cambiamos de fragmento dependiendo del item que este seleccionado
        bottom_navigation_menu_cliente.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_cliente_notificaciones -> makeCurrentFragment(customerNotificationsFragment)
                R.id.menu_cliente_calendario -> makeCurrentFragment(customerCalendarFragment)
                R.id.menu_cliente_buscar -> makeCurrentFragment(customerSearchFragment)
                R.id.menu_cliente_perfil -> makeCurrentFragment(customerProfileFragment)
            }
            true

        }


    }


    //Función para cambiar el fragmeto actual
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
        }



}