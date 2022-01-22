package nitmas.groupB.onestopsolution.LOGIN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_sign_up.*
import nitmas.groupB.onestopsolution.MAIN.MainActivity
import nitmas.groupB.onestopsolution.MAIN.MySingleton
import nitmas.groupB.onestopsolution.R
import org.json.JSONObject
import java.util.HashMap

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signup.setOnClickListener {
            var phuser= intent.getStringExtra("ph")
            updateusernumber(phuser.toString(),fullname.text.toString(),dept.text.toString(),regno.text.toString(),designation.text.toString())
        }

    }

    private fun updateusernumber(
        ph: String,
        fullname:String,
        dept:String,
        regno:String,
        designation:String
    ) {
        val URL = "http://3.14.149.246/New%20folder%20(4)/updatealluser.php"
        //String Request initialized
        var mStringRequest =
            object : StringRequest(Method.POST, URL, Response.Listener { response ->


                Toast.makeText(this,"SignedUp Successfully",Toast.LENGTH_SHORT).show()

                val i = Intent(this,MainActivity::class.java)
                val a = ph
                i.putExtra("ph", a)
                startActivity(i)


            }, Response.ErrorListener { error ->
                Toast.makeText(this,"not inserted",Toast.LENGTH_SHORT).show()
                Log.d("This is the error", "Error :" + error.toString())

            }) {
                override fun getBodyContentType(): String {
                    return "application/json"
                }

                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray {

                    val params2 = HashMap<String, String>()
                    params2.put("ph",ph)
                    params2.put("fullname", fullname)
                    params2.put("dept",dept)
                    params2.put("regno",regno)
                    params2.put("designation",designation)

                    return JSONObject(params2 as Map<*, *>).toString().toByteArray()
                }

            }
        this?.let { MySingleton.getInstance(it).addToRequestQueue(mStringRequest) }
    }
    override fun onBackPressed(){
        moveTaskToBack(true);
    }
}