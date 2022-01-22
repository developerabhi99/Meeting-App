package nitmas.groupB.onestopsolution.MAIN

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_userdetail.*
import nitmas.groupB.onestopsolution.MAIN.Task.*
import nitmas.groupB.onestopsolution.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  val s:String=phonenumber.text.toString()



        val phuser= intent.getStringExtra("ph")
        Toast.makeText(this," ${phuser}  your login is SuccessFull"
            ,Toast.LENGTH_LONG).show()

        val bundle = Bundle()
        bundle.putString("my_channel", phuser)
        val f = channel()
        f.arguments = bundle
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.frame,f).commit()
        }

        bottom_navigation.setOnNavigationItemSelectedListener{ item ->
            //var selected: Fragment? =null
            when(item.itemId) {
                R.id.item1 -> {
                    // Respond to navigation item 1 click

                    val bundle = Bundle()
                    bundle.putString("my_channel", phuser)
                    val f = channel()
                    f.arguments = bundle
                    if (savedInstanceState == null) {
                        supportFragmentManager.beginTransaction().add(R.id.frame,f).commit()
                    }

                       // startActivity(Intent(this, Channels::class.java))
                    true
                }
                R.id.item2 -> {
                    // Respond to navigation item 2 click
                    replacefragment(test());
                   // startActivity(Intent(this, Test::class.java))
                    true
                }
                R.id.item3 -> {
                    // Respond to navigation item 1 click
                    replacefragment(performance());
                  //  startActivity(Intent(this, Performance::class.java))
                    true
                }
                R.id.item4 -> {
                    // Respond to navigation item 2 click
                   // replacefragment(userdetail());
//                    val mFragmentManager = supportFragmentManager
//                    val mFragmentTransaction = mFragmentManager.beginTransaction()
//                    val mFragment = userdetail()
//                    val mBundle = Bundle()
//                    mBundle.putString("mText","700320")
//                    userdetail().arguments = mBundle
//                    mFragmentTransaction.add(R.id.frame, mFragment)
//                    mFragmentTransaction.commit()

                    val bundle = Bundle()
                    bundle.putString("my_key", phuser)
                    val fg = userdetail()
                    fg.setArguments(bundle)
                    if (savedInstanceState == null) {
                        supportFragmentManager.beginTransaction().add(R.id.frame,fg).commit()
                    }
                    true
                }
                else -> false
            }
        }


    }

    private fun replacefragment(fragment: Any) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment as Fragment)
        fragmentTransaction.commit()



    }
    override fun onBackPressed(){
        moveTaskToBack(true);
    }
}