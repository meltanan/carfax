package com.example.cars_list.ui

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.cars_list.R

inline fun <reified T : Fragment> FragmentActivity.addFragment(addToBackStack: Boolean = false) {
    supportFragmentManager.commit {
        if (addToBackStack) addToBackStack(null)
        add<T>(R.id.fragmentContainerView)
    }
}

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}