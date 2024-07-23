package com.example.example_mvvm_recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.example_mvvm_recyclerview.Fragment.StudentFragment
import com.example.example_mvvm_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startFragment(StudentFragment())
        binding.MenuNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuStudent -> startFragment(StudentFragment())
                R.id.menuGam -> notify("GAM")
                R.id.menuChats -> notify("Chats")
                R.id.menuOrder -> notify("Giỏ hàng")
            }
            true
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameContainer, fragment)
            commit()
        }
    }

    private fun notify(noify: String) {
        Toast.makeText(this, "bạn đã chuyển qua $noify", Toast.LENGTH_SHORT).show()
    }

}