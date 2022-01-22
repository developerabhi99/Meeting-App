package nitmas.groupB.onestopsolution.LOGIN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_otp_screen.*
import nitmas.groupB.onestopsolution.R

class OtpScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_screen)

        otp.setOnClickListener{
            startActivity(Intent(this,SignUp::class.java))
        }

         digitone.setOnClickListener {

             digittwo.requestFocus()
         }
        digittwo.setOnClickListener {
            digitthree.requestFocus()
        }
        digitthree.setOnClickListener {
            digitfour.requestFocus()
        }


    }
    override fun onBackPressed(){
        moveTaskToBack(true);
    }
}